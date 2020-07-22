/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.expression.editor.viewer;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.IBonitaVariableContext;
import org.bonitasoft.studio.common.emf.tools.EMFModelUpdater;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.jface.databinding.DialogSupport;
import org.bonitasoft.studio.expression.editor.ExpressionProviderService;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.ExpressionTypeContentProvider;
import org.bonitasoft.studio.expression.editor.provider.ExpressionTypeLabelProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.runtime.Assert;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.dialogs.DialogTray;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.Workbench;

public class EditExpressionDialog extends TrayDialog implements IBonitaVariableContext {

    private static final String DEFAULT_NAME_SCRIPT = "newScript";

    private static final int HEIGHT = 600;

    private static final int WIDTH = 800;

    protected Expression inputExpression;

    private TableViewer expressionTypeViewer;

    protected final EObject context;

    protected Composite contentComposite;

    protected EMFDataBindingContext dataBindingContext;

    protected final EditingDomain domain;

    protected IExpressionEditor currentExpressionEditor;

    private ISelection oldSelection;

    protected final ViewerFilter[] viewerTypeFilters;

    private final boolean isPassword;

    private boolean isPageFlowContext;

    private final Listener openTrayListener = event -> {
        if (getShell() != null
                && currentExpressionEditor != null
                && currentExpressionEditor.provideDialogTray()
                && getTray() == null) {
            openTray(currentExpressionEditor.createDialogTray());
        } else {
            closeTray();
        }
    };

    protected Control helpControl;

    private final ExpressionViewer expressionViewer;

    private Set<String> filteredEditor = new HashSet<>();

    private ExpressionNameResolver expressionNameResolver;

    private EMFModelUpdater<Expression> expressionUpdater;

    @Override
    public void openTray(final DialogTray tray) throws IllegalStateException, UnsupportedOperationException {
        super.openTray(tray);
        getShell().removeListener(SWT.Move, getShell().getListeners(SWT.Move)[0]);
        getShell().removeListener(SWT.Resize, getShell().getListeners(SWT.Resize)[0]);
    }

    protected EditExpressionDialog(final Shell parentShell,
            final boolean isPassword,
            final Expression inputExpression,
            final EObject context,
            final EditingDomain domain,
            final ViewerFilter[] viewerTypeFilters,
            final ExpressionViewer expressionViewer) {
        super(parentShell);
        this.inputExpression = inputExpression;
        if (this.inputExpression == null) {
            this.inputExpression = ExpressionFactory.eINSTANCE.createExpression();
            this.inputExpression.setType(ExpressionConstants.CONSTANT_TYPE);
        }
        expressionUpdater = new EMFModelUpdater<Expression>().from(this.inputExpression);
        this.inputExpression = expressionUpdater.getWorkingCopy();
        this.context = context;
        this.domain = domain;
        this.viewerTypeFilters = viewerTypeFilters;
        this.isPassword = isPassword;
        this.expressionViewer = expressionViewer;
        setHelpAvailable(true);
        if (isResizable()) {
            setShellStyle(SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.MAX | SWT.RESIZE
                    | getDefaultOrientation());
        } else {
            setShellStyle(SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL
                    | getDefaultOrientation());
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.Dialog#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents(Composite parent) {
        configureContext();
        final Control content = super.createContents(parent);
        String expressionType = inputExpression.getType();
        if (ExpressionConstants.CONSTANT_TYPE.equals(expressionType)) {
            if (!isSupportedConstantType(inputExpression.getReturnType())) {
                expressionType = ExpressionConstants.SCRIPT_TYPE;
            }
        }
        final IExpressionProvider currentProvider = ExpressionProviderService.getInstance()
                .getExpressionProvider(expressionType);
        if (currentProvider != null && expressionTypeViewer != null) {
            final StructuredSelection selection = new StructuredSelection(currentProvider);
            expressionTypeViewer.setSelection(selection);
            oldSelection = selection;
            expressionTypeViewer.getSelection();
            showContent(currentProvider.getExpressionType());
            updateOKButton();
        }
        if (expressionTypeViewer != null) {
            expressionTypeViewer.addSelectionChangedListener(event -> {
                if (!event.getSelection().equals(oldSelection)) {
                    oldSelection = event.getSelection();
                    expressionTypeChanged(event.getSelection());
                    updateOKButton();
                }
            });
        }
        return content;
    }

    protected void updateOKButton() {
        final Button okButton = getButton(OK);
        if (okButton != null && !okButton.isDisposed()) {
            okButton.setEnabled(currentExpressionEditor != null ? currentExpressionEditor.canFinish() : false);
        }
    }

    private void configureContext() {
        final IEclipseContext e4Context = ((Workbench) PlatformUI.getWorkbench()).getContext();
        while (!Objects.equals(e4Context.getActiveLeaf(), e4Context)) {
            e4Context.getActiveLeaf().deactivate();
        }
        getShell().setData("org.eclipse.e4.ui.shellContext", e4Context.createChild("expressionDialogContext"));
    }

    private boolean isSupportedConstantType(final String returnType) {
        return returnType.equals(String.class.getName()) ||
                returnType.equals(Boolean.class.getName()) ||
                returnType.equals(Double.class.getName()) ||
                returnType.equals(Float.class.getName()) ||
                returnType.equals(Long.class.getName()) ||
                returnType.equals(Integer.class.getName());
    }

    @Override
    protected void configureShell(final Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(Messages.editExpression);
    }

    @Override
    protected Point getInitialSize() {
        return new Point(WIDTH, HEIGHT);
    }

    @Override
    protected Control createDialogArea(final Composite parent) {
        final Composite composite = (Composite) super.createDialogArea(parent);

        composite.setLayout(GridLayoutFactory.swtDefaults().numColumns(2).margins(10, 5).spacing(10, 0).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createExpressionTypePanel(composite);
        createExpressionContentPanel(composite);

        return composite;
    }

    @Override
    protected void initializeBounds() {
        super.initializeBounds();
        updateTrayVisibility();
    }

    protected void updateTrayVisibility() {
        if (helpControl != null) {
            helpControl.setVisible(false);
            if (currentExpressionEditor != null && currentExpressionEditor.provideDialogTray()) {
                final ToolItem item = ((ToolBar) helpControl).getItem(0);
                item.setSelection(true);
                openTrayListener.handleEvent(new Event());
            } else if (getTray() != null) {
                closeTray();
            }
        }
    }

    protected void createExpressionContentPanel(final Composite parentForm) {
        contentComposite = new Composite(parentForm, SWT.NONE);
        contentComposite
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(WIDTH - 50, SWT.DEFAULT).create());
        contentComposite.setLayout(GridLayoutFactory.fillDefaults().create());
    }

    protected void createExpressionTypePanel(final Composite parentForm) {
        final Composite parentComposite = new Composite(parentForm, SWT.NONE);
        parentComposite
                .setLayoutData(GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.FILL).grab(false, true).create());
        parentComposite.setLayout(GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 1).create());

        final Label expressionTypeLabel = new Label(parentComposite, SWT.NONE);
        expressionTypeLabel.setText(Messages.expressionTypeLabel);
        expressionTypeLabel.setLayoutData(GridDataFactory.swtDefaults().grab(true, false).create());

        expressionTypeViewer = new TableViewer(parentComposite, SWT.FULL_SELECTION | SWT.BORDER | SWT.SINGLE);
        expressionTypeViewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        expressionTypeViewer.setContentProvider(new ExpressionTypeContentProvider());
        expressionTypeViewer.setLabelProvider(new ExpressionTypeLabelProvider());
        expressionTypeViewer.setSorter(new ViewerSorter() {

            @Override
            public int compare(final Viewer viewer, final Object e1, final Object e2) {
                final IExpressionProvider p1 = (IExpressionProvider) e1;
                final IExpressionProvider p2 = (IExpressionProvider) e2;
                return p1.getTypeLabel().compareTo(p2.getTypeLabel());
            }
        });
        if (viewerTypeFilters != null) {
            expressionTypeViewer.setFilters(viewerTypeFilters);
        }
        if (!filteredEditor.isEmpty()) {
            expressionTypeViewer.addFilter(filterEditor());
        }
        ColumnViewerToolTipSupport.enableFor(expressionTypeViewer, ToolTip.NO_RECREATE);
        expressionTypeViewer.setInput(expressionViewer.getInput());
    }

    private ViewerFilter filterEditor() {
        return new ViewerFilter() {

            @Override
            public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
                return !(element instanceof IExpressionProvider
                        && filteredEditor.contains(((IExpressionProvider) element).getExpressionType()));
            }
        };
    }

    @Override
    protected boolean isResizable() {
        return true;
    }

    protected void expressionTypeChanged(final ISelection selection) {
        if (!selection.isEmpty()) {
            final IExpressionProvider provider = (IExpressionProvider) ((StructuredSelection) selection).getFirstElement();
            BusyIndicator.showWhile(Display.getDefault(), () -> {
                showContent(provider.getExpressionType());
                updateTrayVisibility();
                contentComposite.layout(true, true);
            });
        }
    }

    @Override
    protected Button createButton(final Composite parent, final int id, final String label, final boolean defaultButton) {
        final Button button = super.createButton(parent, id, label, defaultButton);
        if (id == OK) {
            if (currentExpressionEditor != null) {
                button.setEnabled(currentExpressionEditor.canFinish());
            }
        }
        return button;

    }

    protected void showContent(final String type) {
        final IExpressionProvider provider = ExpressionProviderService.getInstance().getExpressionProvider(type);

        Assert.isNotNull(provider);

        for (final Control c : contentComposite.getChildren()) {
            c.dispose();
        }

        if (currentExpressionEditor != null) {
            currentExpressionEditor.dispose();
        }
        if (dataBindingContext != null) {
            dataBindingContext.dispose();
        }
        currentExpressionEditor = provider.getExpressionEditor(inputExpression, context);
        if (currentExpressionEditor != null) {
            currentExpressionEditor.setIsPageFlowContext(isPageFlowContext);
            dataBindingContext = new EMFDataBindingContext();

            currentExpressionEditor.createExpressionEditor(contentComposite, dataBindingContext, isPassword);

            final UpdateValueStrategy selectionToExpressionType = new UpdateValueStrategy();
            final IConverter convert = new Converter(IExpressionProvider.class, String.class) {

                @Override
                public Object convert(final Object from) {
                    return from != null ? ((IExpressionProvider) from).getExpressionType() : null;
                }
            };
            selectionToExpressionType.setConverter(convert);

            if (domain != null) {
                domain.getCommandStack().execute(
                        SetCommand.create(domain, inputExpression, ExpressionPackage.Literals.EXPRESSION__TYPE, type));
            } else {
                inputExpression.setType(type);
            }
            inputExpression.setName(shouldClearName() ? "" : inputExpression.getName());
            if (expressionNameResolver != null) {
                inputExpression.setName(expressionNameResolver.getName(inputExpression));
            }
            currentExpressionEditor.bindExpression(dataBindingContext, context, inputExpression, viewerTypeFilters,
                    expressionViewer);
            currentExpressionEditor.addListener(event -> {
                final Button okButton = getButton(OK);
                if (okButton != null && !okButton.isDisposed()) {
                    okButton.setEnabled(currentExpressionEditor.canFinish());
                }
            });
            DialogSupport.create(this, dataBindingContext);
        }
    }

    private boolean shouldClearName() {
        final String type = inputExpression.getType();
        return !(ExpressionConstants.SCRIPT_TYPE.equals(type) || ExpressionConstants.QUERY_TYPE.equals(type));
    }

    @Override
    protected void okPressed() {
        if (currentExpressionEditor != null) {
            currentExpressionEditor.okPressed();
        }
        expressionUpdater.update();
        super.okPressed();
    }

    @Override
    public boolean close() {
        if (getTray() != null) {
            closeTray();
        }
        if (dataBindingContext != null) {
            dataBindingContext.dispose();
        }
        if (currentExpressionEditor != null) {
            currentExpressionEditor.dispose();
        }
        return super.close();
    }

    @Override
    protected boolean canHandleShellCloseEvent() {
        if (FileActionDialog.getDisablePopup()) {
            return true;
        }
        if (MessageDialog.openQuestion(getShell(), Messages.handleShellCloseEventTitle,
                Messages.handleShellCloseEventMessage)) {
            return super.canHandleShellCloseEvent();
        }
        return false;
    }

    @Override
    protected Control createHelpControl(final Composite parent) {
        helpControl = super.createHelpControl(parent);
        final ToolItem item = ((ToolBar) helpControl).getItem(0);
        final Listener[] listeners = item.getListeners(SWT.Selection);
        if (listeners.length > 0) {
            for (final Listener l : listeners) {
                item.removeListener(SWT.Selection, l);
            }
        }
        item.addListener(SWT.Selection, openTrayListener);
        helpControl.setVisible(currentExpressionEditor == null);
        return helpControl;
    }

    public Expression getExpression() {
        return inputExpression;
    }

    @Override
    public boolean isPageFlowContext() {

        return isPageFlowContext;
    }

    @Override
    public void setIsPageFlowContext(final boolean isPageFlowContext) {
        this.isPageFlowContext = isPageFlowContext;
        if (currentExpressionEditor != null) {
            currentExpressionEditor.setIsPageFlowContext(isPageFlowContext);
        }

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#isOverViewContext()
     */
    @Override
    public boolean isOverViewContext() {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#setIsOverviewContext(boolean)
     */
    @Override
    public void setIsOverviewContext(final boolean isOverviewContext) {
    }

    public void setEditorFilters(final Set<String> filteredEditor) {
        this.filteredEditor = filteredEditor;
    }

    public void setExpressionNameResolver(ExpressionNameResolver expressionNameResolver) {
        this.expressionNameResolver = expressionNameResolver;
    }

}
