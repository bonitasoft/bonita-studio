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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.EMFModelUpdater;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.jface.databinding.DialogSupport;
import org.bonitasoft.studio.expression.editor.ExpressionProviderService;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.ExpressionEditionAdapter;
import org.bonitasoft.studio.expression.editor.provider.ExpressionTypeContentProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.ui.widget.NativeTabFolderWidget;
import org.bonitasoft.studio.ui.widget.NativeTabItemWidget;
import org.eclipse.core.runtime.Assert;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.Workbench;

public class EditExpressionDialog extends TrayDialog {

    private static final String EXPRESSION_TYPE_KEY = "expression.type";
    private static final int HEIGHT = 700;
    private static final int WIDTH = 1000;

    protected Expression inputExpression;
    protected final EObject context;
    protected Composite contentComposite;
    protected EMFDataBindingContext dataBindingContext;
    protected final EditingDomain domain;
    protected ViewerFilter[] viewerTypeFilters = new ViewerFilter[0];
    private final boolean isPassword;
    protected Control helpControl;
    private final ExpressionViewer expressionViewer;
    private Set<String> filteredEditor = new HashSet<>();
    private ExpressionNameResolver expressionNameResolver;
    private EMFModelUpdater<Expression> expressionUpdater;
    private Map<String, Expression> lastExpressionByType = new HashMap<>();
    private IExpressionEditor currentExpressionEditor;
    private NativeTabFolderWidget tabFolder;

    protected EditExpressionDialog(final Shell parentShell,
            final boolean isPassword,
            final Expression inputExpression,
            final EObject context,
            final EditingDomain domain,
            final ViewerFilter[] viewerTypeFilters,
            final ExpressionViewer expressionViewer,
            ExpressionNameResolver expressionNameResolver) {
        super(parentShell);
        this.inputExpression = inputExpression;
        this.expressionNameResolver = expressionNameResolver;
        if (this.inputExpression == null) {
            this.inputExpression = ExpressionFactory.eINSTANCE.createExpression();
            this.inputExpression.setType(ExpressionConstants.CONSTANT_TYPE);
        }
        expressionUpdater = new EMFModelUpdater<Expression>().from(this.inputExpression);
        boolean shouldClearName = shouldClearName();
        this.inputExpression = ExpressionEditionAdapter.adapt(expressionUpdater.getWorkingCopy());
        if(shouldClearName) {
            this.inputExpression.setName(null);
        }
        this.inputExpression.setName(expressionNameResolver.getName(this.inputExpression));
        this.context = context;
        this.domain = domain;
        this.viewerTypeFilters = viewerTypeFilters;
        this.isPassword = isPassword;
        this.expressionViewer = expressionViewer;
        setShellStyle(SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.MAX | SWT.RESIZE
                | getDefaultOrientation());
    }

    @Override
    protected Control createContents(Composite parent) {
        configureContext();
        return super.createContents(parent);
    }

    protected void updateOKButton() {
        final Button okButton = getButton(OK);
        if (okButton != null && !okButton.isDisposed()) {
            okButton.setEnabled(currentExpressionEditor != null && currentExpressionEditor.canFinish());
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

        composite.setLayout(GridLayoutFactory.swtDefaults().numColumns(1).margins(10, 5).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createTabFolder(composite);

        return composite;
    }

    protected Composite createTabFolder(final Composite parentForm) {
        tabFolder = new NativeTabFolderWidget.Builder().createIn(parentForm);
        tabFolder.setLayout(GridLayoutFactory.fillDefaults().create());
        tabFolder.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        tabFolder.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                NativeTabItemWidget item = tabFolder.getItem(tabFolder.getSelectionIndex());
                if (item != null && item.getItem().getData(EXPRESSION_TYPE_KEY) != null) {
                    String expressionType = (String) item.getItem().getData(EXPRESSION_TYPE_KEY);
                    showContent(item, expressionType);
                    updateOKButton();
                }
            }

        });
        createContentComposite(tabFolder.getTabFolder());
        ExpressionTypeContentProvider expressionTypeContentProvider = new ExpressionTypeContentProvider();
        Stream.of(expressionTypeContentProvider.getElements(expressionViewer.getInput()))
                .filter(provider -> Stream.of(viewerTypeFilters).allMatch(f -> f.select(null, null, provider)))
                .filter(provider -> filterEditor().select(null, null, provider))
                .map(IExpressionProvider.class::cast)
                .sorted((e1, e2) -> e1.getTypeLabel().compareTo(e2.getTypeLabel()))
                .map(provider -> createTabItem(tabFolder, provider, inputExpression))
                .collect(Collectors.toList());
        return tabFolder.getTabFolder();
    }

    protected void createContentComposite(Composite parent) {
        contentComposite = new Composite(parent, SWT.NONE);
        contentComposite
                .setLayoutData(GridDataFactory.fillDefaults()
                        .grab(true, true)
                        .create());
        contentComposite.setLayout(GridLayoutFactory.fillDefaults().extendedMargins(10, 10, 0, 10).create());
    }

    private String defaultExpressionType() {
        String expressionType = inputExpression.getType();
        if (ExpressionConstants.CONSTANT_TYPE.equals(expressionType)
                && (!isSupportedConstantType(inputExpression.getReturnType())
                        || !inputExpression.hasContent())) {
            return ExpressionConstants.SCRIPT_TYPE;
        }
        return expressionType;
    }

    private NativeTabItemWidget createTabItem(NativeTabFolderWidget folder, IExpressionProvider provider, Expression input) {
        NativeTabItemWidget nativeItem = new NativeTabItemWidget.Builder().withText(provider.getTypeLabel())
                .createIn(folder);
        nativeItem.getItem().setData(EXPRESSION_TYPE_KEY, provider.getExpressionType());

        IExpressionEditor expressionEditor = provider.getExpressionEditor(input, context);
        nativeItem.getItem().setData("editor", expressionEditor);
        String defaultExpressionType = defaultExpressionType();
        if (defaultExpressionType.equals(provider.getExpressionType())) {
            folder.setSelection(nativeItem);
        }
        return nativeItem;
    }

    private ViewerFilter filterEditor() {
        return new ViewerFilter() {

            @Override
            public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
                return filteredEditor != null ? !(element instanceof IExpressionProvider
                        && filteredEditor.contains(((IExpressionProvider) element).getExpressionType())) : Boolean.TRUE;
            }
        };
    }

    @Override
    protected boolean isResizable() {
        return true;
    }

    @Override
    protected Button createButton(final Composite parent, final int id, final String label,
            final boolean defaultButton) {
        final Button button = super.createButton(parent, id, label, defaultButton);
        if (id == OK && currentExpressionEditor != null) {
            button.setEnabled(currentExpressionEditor.canFinish());
        }
        return button;
    }

    protected void showContent(NativeTabItemWidget item, final String type) {
        if (type == null) {
            return;
        }
        lastExpressionByType.put(inputExpression.getType(), EcoreUtil.copy(inputExpression));
        Expression storedExpression = lastExpressionByType.getOrDefault(type, inputExpression);
        expressionUpdater.editWorkingCopy(storedExpression);
        final IExpressionProvider provider = ExpressionProviderService.getInstance()
                .getExpressionProvider(type);

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
            dataBindingContext = new EMFDataBindingContext();
            currentExpressionEditor.createExpressionEditor(contentComposite,
                    dataBindingContext,
                    isPassword);
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
            if (item != null) {
                item.setControl(contentComposite);
                contentComposite.layout(true);
            }
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

    public Expression getExpression() {
        return inputExpression;
    }

    public void setEditorFilters(final Set<String> filteredEditor) {
        this.filteredEditor = filteredEditor;
    }

}
