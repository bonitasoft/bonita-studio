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
package org.bonitasoft.studio.scripting.provider;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import org.bonitasoft.studio.common.jface.databinding.converter.BooleanInverserConverter;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.expression.editor.constant.ExpressionReturnTypeContentProvider;
import org.bonitasoft.studio.expression.editor.filter.ExpressionReturnTypeFilter;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.SelectionAwareExpressionEditor;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.scripting.extensions.IScriptLanguageProvider;
import org.bonitasoft.studio.scripting.extensions.ScriptLanguageService;
import org.bonitasoft.studio.scripting.i18n.Messages;
import org.bonitasoft.studio.ui.converter.ConverterBuilder;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.internal.core.search.JavaSearchScope;
import org.eclipse.jdt.internal.ui.dialogs.FilteredTypesSelectionDialog;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.DialogTray;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 */
public class ScriptExpressionEditor extends SelectionAwareExpressionEditor implements IExpressionEditor {

    private final String languageId;

    private Composite mainComposite, inputNameComposite;

    private Text expressionNameText;

    private IExpressionEditor editor;

    private ComboViewer typeCombo;

    private Button browseClassesButton;

    private Expression inputExpression;

    private IObservableValue returnTypeModelObservable = null;

    private boolean isPageFlowContext = false;

    private CLabel errorLabel;

    private Binding bindValue;

    public ScriptExpressionEditor(final Expression expression) {
        if (expression != null) {
            if (expression.getInterpreter() == null || expression.getInterpreter().isEmpty()) {
                expression.setInterpreter(ScriptLanguageService.getInstance().getDefaultLanguage());
            }
            languageId = expression.getInterpreter();
        } else {
            languageId = "";
        }
    }

    @Override
    public Control createExpressionEditor(final Composite parent, final EMFDataBindingContext ctx) {
        mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().create());

        new Label(mainComposite, SWT.NONE)
                .setLayoutData(GridDataFactory.fillDefaults().indent(0, -LayoutConstants.getSpacing().y + 1).create()); //filler

        inputNameComposite = new Composite(mainComposite, SWT.NONE);
        inputNameComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).create());
        inputNameComposite.setLayoutData(GridDataFactory.fillDefaults().create());

        final Label scriptNameLabel = new Label(inputNameComposite, SWT.NONE);
        scriptNameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).create());
        scriptNameLabel.setText(Messages.name);

        expressionNameText = new Text(inputNameComposite, SWT.BORDER | SWT.SINGLE);
        expressionNameText.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).grab(false, false)
                .hint(400, SWT.DEFAULT).create());

        LocalResourceManager resourceManager = new LocalResourceManager(JFaceResources.getResources(), parent);
        Color errorColor = resourceManager.createColor(new RGB(214, 77, 77));

        errorLabel = new CLabel(inputNameComposite, SWT.NONE);
        errorLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).create());
        errorLabel.setForeground(errorColor);

        final IScriptLanguageProvider provider = ScriptLanguageService.getInstance().getScriptLanguageProvider(languageId);
        editor = provider.getExpressionEditor();
        editor.setIsPageFlowContext(isPageFlowContext);
        final Composite editorComposite = new Composite(mainComposite, SWT.NONE);
        editorComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        editorComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        editor.createExpressionEditor(editorComposite, ctx);

        createReturnTypeComposite(editorComposite);

        return mainComposite;
    }

    protected void createReturnTypeComposite(final Composite parent) {
        final Composite typeComposite = new Composite(parent, SWT.NONE);
        typeComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        final GridLayout gl = new GridLayout(3, false);
        gl.marginWidth = 0;
        gl.marginHeight = 0;
        typeComposite.setLayout(gl);

        final Label typeLabel = new Label(typeComposite, SWT.NONE);
        typeLabel.setText(Messages.returnType);
        typeLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).create());

        typeCombo = new ComboViewer(typeComposite, SWT.BORDER);
        typeCombo.getCombo().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.CENTER).indent(10, 0).create());
        typeCombo.setContentProvider(new ExpressionReturnTypeContentProvider());
        typeCombo.setLabelProvider(new LabelProvider());
        typeCombo.setSorter(new ViewerSorter() {

            @Override
            public int compare(final Viewer viewer, final Object e1, final Object e2) {
                final String t1 = (String) e1;
                final String t2 = (String) e2;
                return t1.compareTo(t2);
            }
        });
        typeCombo.getCombo().addModifyListener(e -> fireSelectionChanged());

        browseClassesButton = new Button(typeComposite, SWT.PUSH);
        browseClassesButton.setText(Messages.browse);
        browseClassesButton.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).create());
        browseClassesButton.addListener(SWT.Selection, event -> openClassSelectionDialog());
    }

    private String toDisplayName(Object className) {
        if (className != null) {
            String displayName = className.toString();
            try {
                Class<?> clazz = Class.forName(displayName);
                if (clazz.isArray()) {
                    return clazz.getCanonicalName();
                }
                return displayName;
            } catch (ClassNotFoundException e) {
                return displayName;
            }
        }
        return null;
    }


    @SuppressWarnings("restriction")
    private void openClassSelectionDialog() {
        final JavaSearchScope scope = new JavaSearchScope(true);
        try {
            scope.add(RepositoryManager.getInstance().getCurrentRepository().getJavaProject());
        } catch (final Exception ex) {
            BonitaStudioLog.error(ex);
        }
        final FilteredTypesSelectionDialog searchDialog = new FilteredTypesSelectionDialog(
                Display.getDefault().getActiveShell(), false, null, scope,
                IJavaSearchConstants.TYPE);
        if (searchDialog.open() == Dialog.OK) {
            final String selectedTypeName = ((IType) searchDialog.getFirstResult()).getFullyQualifiedName();
            typeCombo.setInput(selectedTypeName);
            inputExpression.setReturnType(selectedTypeName);
        }
    }

    @Override
    public void bindExpression(final EMFDataBindingContext dataBindingContext, final EObject context,
            final Expression inputExpression,
            final ViewerFilter[] filters,
            final ExpressionViewer viewer) {
        this.inputExpression = inputExpression;
        final IObservableValue nameModelObservable = EMFObservables.observeValue(inputExpression,
                ExpressionPackage.Literals.EXPRESSION__NAME);

        final UpdateValueStrategy opposite = new UpdateValueStrategy();
        opposite.setConverter(new BooleanInverserConverter());

        IObservableValue nameTextObservable = SWTObservables.observeText(expressionNameText, SWT.Modify);

        final UpdateValueStrategy targetToModel = new UpdateValueStrategy();
        targetToModel.setAfterConvertValidator(new EmptyInputValidator(Messages.name));
        bindValue = dataBindingContext.bindValue(nameTextObservable, nameModelObservable, targetToModel, null);
        nameTextObservable.addValueChangeListener(handleValidationStatusChanged());
        nameModelObservable.addValueChangeListener(event -> fireSelectionChanged());

        editor.bindExpression(dataBindingContext, context, inputExpression, filters, viewer);

        if (inputExpression.getReturnType() != null) {
            typeCombo.setInput(inputExpression.getReturnType());
        } else {
            typeCombo.setInput(new Object());
        }
        returnTypeModelObservable = EMFObservables.observeValue(inputExpression,
                ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE);
        final String defaultReturnType = viewer.getDefaultReturnType();
        if (defaultReturnType != null && !inputExpression.isReturnTypeFixed()
                && shouldChangeReturnType(inputExpression.getReturnType(), defaultReturnType)) {
            returnTypeModelObservable.setValue(defaultReturnType);
        }
        dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(typeCombo), returnTypeModelObservable);
        dataBindingContext.bindValue(SWTObservables.observeText(typeCombo.getCombo()), returnTypeModelObservable,
                updateValueStrategy().withConverter(ConverterBuilder.<String, String> newConverter()
                        .withConvertFunction(this::toClassName)
                        .create())
                        .create(),
                updateValueStrategy().withConverter(ConverterBuilder.<String, String> newConverter()
                        .withConvertFunction(this::toDisplayName)
                        .create())
                        .create());

        typeCombo.getCombo().setEnabled(!inputExpression.isReturnTypeFixed());
        browseClassesButton.setEnabled(!inputExpression.isReturnTypeFixed());
    }

    private String toClassName(String comboText) {
        if (comboText != null) {
            if (comboText.endsWith("[]")) {
                return "[L" + comboText.substring(0, comboText.length() - 2) + ";";
            }
        }
        return comboText;
    }

    private IValueChangeListener handleValidationStatusChanged() {
        return event -> statusChanged((IStatus) bindValue.getValidationStatus().getValue());
    }

    private void statusChanged(IStatus status) {
        if (!status.isOK()) {
            errorLabel.setText(status.getMessage());
            errorLabel.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_ERROR_TSK));

        } else {
            errorLabel.setText("");
            errorLabel.setImage(null);
        }
        inputNameComposite.layout();
    }

    private boolean shouldChangeReturnType(final String inputExpressionReturnType, final String defaultReturnType) {
        return Object.class.getName().equals(inputExpressionReturnType)
                || !new ExpressionReturnTypeFilter(repositoryAccessor()).compatibleReturnTypes(inputExpressionReturnType,
                        defaultReturnType);
    }

    private RepositoryAccessor repositoryAccessor() {
        final RepositoryAccessor repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
        return repositoryAccessor;
    }

    @Override
    public void addListener(final Listener listener) {
        super.addListener(listener);
        editor.addListener(listener);
    }

    @Override
    public boolean canFinish() {
        return expressionNameText != null && !expressionNameText.isDisposed() && !expressionNameText.getText().isEmpty()
                && editor.canFinish()
                && typeCombo != null && !typeCombo.getCombo().isDisposed()
                && !typeCombo.getCombo().getText().trim().isEmpty();
    }

    @Override
    public void dispose() {
        if (editor != null) {
            editor.dispose();
        }
        if (returnTypeModelObservable != null) {
            returnTypeModelObservable.dispose();
        }
        super.dispose();
    }

    @Override
    public void okPressed() {
        editor.okPressed();
    }

    @Override
    public boolean provideDialogTray() {
        return editor.provideDialogTray();
    }

    @Override
    public DialogTray createDialogTray() {
        return editor.createDialogTray();
    }

    @Override
    public Control getTextControl() {
        return editor.getTextControl();
    }

    @Override
    public boolean isPageFlowContext() {

        return isPageFlowContext;
    }

    @Override
    public void setIsPageFlowContext(final boolean isPageFlowContext) {
        this.isPageFlowContext = isPageFlowContext;

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

}
