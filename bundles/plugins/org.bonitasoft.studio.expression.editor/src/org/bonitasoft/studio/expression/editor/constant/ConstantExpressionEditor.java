/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.expression.editor.constant;

import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.SelectionAwareExpressionEditor;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 * 
 */
public class ConstantExpressionEditor extends SelectionAwareExpressionEditor implements IExpressionEditor {

    private Text valueText;

    private ComboViewer typeCombo;

    private Expression inputExpression;

    private boolean isPageFlowContext = false;

    @Override
    public Control createExpressionEditor(Composite parent, EMFDataBindingContext ctx) {
        return createExpressionEditor(parent, ctx, false);
    }

    @Override
    public Control createExpressionEditor(Composite parent, EMFDataBindingContext ctx, boolean isPassword) {
        Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        mainComposite.setLayout(new GridLayout(2, false));

        Label valueLabel = new Label(mainComposite, SWT.NONE);
        valueLabel.setText(Messages.value);
        if (isPassword) {
            valueText = new Text(mainComposite, SWT.BORDER | SWT.PASSWORD);
        } else {
            valueText = new Text(mainComposite, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
        }

        valueText.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(300, 80).create());

        Label typeLabel = new Label(mainComposite, SWT.NONE);
        typeLabel.setText(Messages.returnType);

        typeCombo = new ComboViewer(mainComposite, SWT.BORDER | SWT.READ_ONLY);
        typeCombo.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        typeCombo.setContentProvider(new IStructuredContentProvider() {

            @Override
            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            }

            @Override
            public void dispose() {
            }

            @Override
            public Object[] getElements(Object inputElement) {
                return new String[] {
                        String.class.getName()
                        , Boolean.class.getName()
                        , Long.class.getName()
                        , Float.class.getName()
                        , Double.class.getName()
                        , Integer.class.getName()
                };
            }
        });

        typeCombo.setLabelProvider(new ConstantTypeLabelProvider());
        typeCombo.setInput(new Object());

        return mainComposite;
    }

    @Override
    public void bindExpression(EMFDataBindingContext dataBindingContext, EObject context, Expression inputExpression, ViewerFilter[] filters,
            ExpressionViewer expressionViewer) {
        this.inputExpression = inputExpression;
        IObservableValue contentModelObservable = EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__CONTENT);
        IObservableValue returnTypeModelObservable = EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE);

        dataBindingContext.bindValue(SWTObservables.observeText(valueText, SWT.Modify), contentModelObservable);
        UpdateValueStrategy targetToModel = new UpdateValueStrategy();
        targetToModel.setAfterConvertValidator(new IValidator() {

            @Override
            public IStatus validate(Object value) {
                if (value == null || value.toString().isEmpty()) {
                    return ValidationStatus.error(Messages.returnTypeIsMandatory);
                }
                return ValidationStatus.ok();
            }
        });
        ControlDecorationSupport.create(
                dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(typeCombo), returnTypeModelObservable, targetToModel, null), SWT.LEFT);
        typeCombo.getCombo().setEnabled(!inputExpression.isReturnTypeFixed());

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#canFinish()
     */
    @Override
    public boolean canFinish() {
        return typeCombo != null && !typeCombo.getCombo().isDisposed() && typeCombo.getCombo().getText() != null && !typeCombo.getCombo().getText().isEmpty();
    }

    @Override
    public void okPressed() {
        final String expressionContent = inputExpression.getContent();
        if (expressionContent != null && !expressionContent.equals(inputExpression.getName())) {
            inputExpression.setName(expressionContent);
        }
    }

    @Override
    public Control getTextControl() {
        return valueText;
    }

    @Override
    public boolean isPageFlowContext() {

        return isPageFlowContext;
    }

    @Override
    public void setIsPageFlowContext(boolean isPageFlowContext) {
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
    public void setIsOverviewContext(boolean isOverviewContext) {
    }

}
