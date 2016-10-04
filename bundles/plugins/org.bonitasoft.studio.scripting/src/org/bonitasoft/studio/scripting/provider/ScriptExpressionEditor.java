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
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.internal.core.search.JavaSearchScope;
import org.eclipse.jdt.internal.ui.dialogs.FilteredTypesSelectionDialog;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.DialogTray;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 */
public class ScriptExpressionEditor extends SelectionAwareExpressionEditor implements IExpressionEditor {

    private final String languageId;

    private Composite mainComposite;

    private Text expressionNameText;

    //  private Combo expressionInterpreterCombo;

    private IExpressionEditor editor;

    private ComboViewer typeCombo;

    private Button browseClassesButton;

    private Expression inputExpression;

    private IObservableValue returnTypeModelObservable = null;

    private boolean isPageFlowContext = false;

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

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#createExpressionEditor(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public Control createExpressionEditor(final Composite parent, final EMFDataBindingContext ctx) {
        mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        GridLayout layout = new GridLayout(4, false);
        layout.marginBottom = 0;
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.marginTop = 10;
        layout.marginRight = 0;
        layout.marginLeft = 0;
        mainComposite.setLayout(layout);

        final Label scriptNameLabel = new Label(mainComposite, SWT.NONE);
        scriptNameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        scriptNameLabel.setText(Messages.name + " *");

        expressionNameText = new Text(mainComposite, SWT.BORDER | SWT.SINGLE);
        expressionNameText.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).indent(5, 0).span(2, 1).create());
        //
        //        final Label interpreterLabel = new Label(mainComposite, SWT.NONE);
        //        interpreterLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        //        interpreterLabel.setText(Messages.interpreter);

        //        expressionInterpreterCombo = new Combo(mainComposite, SWT.READ_ONLY | SWT.BORDER);
        //        expressionInterpreterCombo.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).create());
        //
        //        for (final IScriptLanguageProvider provider : ScriptLanguageService.getInstance().getScriptLanguageProviders()) {
        //            expressionInterpreterCombo.add(provider.getLanguageName());
        //        }
        //
        //        if (expressionInterpreterCombo.getItemCount() < 2) {
        //            expressionInterpreterCombo.setEnabled(false);
        //        }

        final IScriptLanguageProvider provider = ScriptLanguageService.getInstance().getScriptLanguageProvider(languageId);
        editor = provider.getExpressionEditor();
        editor.setIsPageFlowContext(isPageFlowContext);
        final Composite editorComposite = new Composite(mainComposite, SWT.NONE);
        editorComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(4, 1).create());
        layout = new GridLayout(1, false);
        layout.marginBottom = 0;
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.marginTop = 10;
        layout.marginRight = 0;
        layout.marginLeft = 0;
        editorComposite.setLayout(layout);
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
        typeCombo.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.CENTER).indent(10, 0).create());
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
        typeCombo.getCombo().addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(final ModifyEvent e) {
                fireSelectionChanged();
            }
        });

        browseClassesButton = new Button(typeComposite, SWT.PUSH);
        browseClassesButton.setText(Messages.browse);
        browseClassesButton.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).create());
        browseClassesButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                openClassSelectionDialog();
            }
        });
    }

    /**
     * @param classText
     */
    @SuppressWarnings("restriction")
    private void openClassSelectionDialog() {
        final JavaSearchScope scope = new JavaSearchScope();
        try {
            scope.add(RepositoryManager.getInstance().getCurrentRepository().getJavaProject());
        } catch (final Exception ex) {
            BonitaStudioLog.error(ex);
        }
        final FilteredTypesSelectionDialog searchDialog = new FilteredTypesSelectionDialog(Display.getDefault().getActiveShell(), false, null, scope,
                IJavaSearchConstants.TYPE);
        if (searchDialog.open() == Dialog.OK) {
            final String selectedTypeName = ((IType) searchDialog.getFirstResult()).getFullyQualifiedName();
            typeCombo.setInput(selectedTypeName);
            inputExpression.setReturnType(selectedTypeName);
        }
    }

    @Override
    public void bindExpression(final EMFDataBindingContext dataBindingContext, final EObject context, final Expression inputExpression,
            final ViewerFilter[] filters,
            final ExpressionViewer viewer) {
        this.inputExpression = inputExpression;
        final IObservableValue nameModelObservable = EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__NAME);
        //   final IObservableValue interpreterModelObservable = EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__INTERPRETER);

        final UpdateValueStrategy opposite = new UpdateValueStrategy();
        opposite.setConverter(new BooleanInverserConverter());

        final UpdateValueStrategy targetToModel = new UpdateValueStrategy();
        targetToModel.setAfterConvertValidator(new EmptyInputValidator(Messages.name));
        ControlDecorationSupport.create(
                dataBindingContext.bindValue(SWTObservables.observeText(expressionNameText, SWT.Modify), nameModelObservable, targetToModel, null), SWT.LEFT);
        //        dataBindingContext.bindValue(SWTObservables.observeSelection(expressionInterpreterCombo), interpreterModelObservable);
        nameModelObservable.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent arg0) {
                fireSelectionChanged();
            }
        });

        editor.bindExpression(dataBindingContext, context, inputExpression, filters, viewer);

        if (inputExpression.getReturnType() != null) {
            typeCombo.setInput(inputExpression.getReturnType());
        } else {
            typeCombo.setInput(new Object());
        }
        returnTypeModelObservable = EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE);
        final String defaultReturnType = viewer.getDefaultReturnType();
        if (defaultReturnType != null && !inputExpression.isReturnTypeFixed() && shouldChangeReturnType(inputExpression.getReturnType(), defaultReturnType)) {
            returnTypeModelObservable.setValue(defaultReturnType);
        }
        dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(typeCombo), returnTypeModelObservable);
        dataBindingContext.bindValue(SWTObservables.observeText(typeCombo.getCombo()), returnTypeModelObservable);

        typeCombo.getCombo().setEnabled(!inputExpression.isReturnTypeFixed());
        browseClassesButton.setEnabled(!inputExpression.isReturnTypeFixed());
    }

    private boolean shouldChangeReturnType(final String inputExpressionReturnType, final String defaultReturnType) {
        return Object.class.getName().equals(inputExpressionReturnType)
                || !new ExpressionReturnTypeFilter(repositoryAccessor()).compatibleReturnTypes(inputExpressionReturnType, defaultReturnType);
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
        return expressionNameText != null && !expressionNameText.isDisposed() && !expressionNameText.getText().isEmpty() && editor.canFinish()
                && typeCombo != null && !typeCombo.getCombo().isDisposed() && !typeCombo.getCombo().getText().trim().isEmpty();
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
