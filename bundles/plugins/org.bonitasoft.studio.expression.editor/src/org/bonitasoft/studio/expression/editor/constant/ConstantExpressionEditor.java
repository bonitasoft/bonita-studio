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
package org.bonitasoft.studio.expression.editor.constant;

import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.SelectionAwareExpressionEditor;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class ConstantExpressionEditor extends SelectionAwareExpressionEditor implements IExpressionEditor {

    private Text valueText;

    private ComboViewer typeCombo;

    private Expression inputExpression;

    private boolean isPageFlowContext = false;

    private CLabel errorLabel;

    @Override
    public Control createExpressionEditor(Composite parent, EMFDataBindingContext ctx) {
        return createExpressionEditor(parent, ctx, false);
    }

    @Override
    public Control createExpressionEditor(Composite parent, EMFDataBindingContext ctx, boolean isPassword) {
        Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().create());

        Label valueLabel = new Label(mainComposite, SWT.NONE);
        valueLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).create());

        valueLabel.setText(Messages.value);
        if (isPassword) {
            valueText = new Text(mainComposite, SWT.BORDER | SWT.PASSWORD);
        } else {
            valueText = new Text(mainComposite, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
        }

        valueText.setLayoutData(GridDataFactory.fillDefaults().grab(true, true)
                .indent(0, -LayoutConstants.getSpacing().y + 1).hint(300, 80).create());

        Composite comboComposite = new Composite(mainComposite, SWT.NONE);
        comboComposite.setLayout(GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 0).create());
        comboComposite.setLayoutData(GridDataFactory.fillDefaults().create());

        Composite comboHeaderComposite = new Composite(comboComposite, SWT.NONE);
        comboHeaderComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        comboHeaderComposite.setLayoutData(GridDataFactory.fillDefaults().create());

        Label typeLabel = new Label(comboHeaderComposite, SWT.NONE);
        typeLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).create());
        typeLabel.setText(Messages.returnType);

        LocalResourceManager resourceManager = new LocalResourceManager(JFaceResources.getResources(), parent);
        Color errorColor = resourceManager.createColor(new RGB(214, 77, 77));

        errorLabel = new CLabel(comboHeaderComposite, SWT.NONE);
        errorLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).create());
        errorLabel.setForeground(errorColor);

        typeCombo = new ComboViewer(comboComposite, SWT.BORDER | SWT.READ_ONLY);
        typeCombo.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        typeCombo.setContentProvider(ArrayContentProvider.getInstance());
        typeCombo.setLabelProvider(new ConstantTypeLabelProvider());
        typeCombo.setInput(new String[] {
                String.class.getName(), Boolean.class.getName(), Long.class.getName(), Float.class.getName(),
                Double.class.getName(), Integer.class.getName()
        });

        return mainComposite;
    }

    @Override
    public void bindExpression(EMFDataBindingContext dataBindingContext, EObject context, Expression inputExpression,
            ViewerFilter[] filters,
            ExpressionViewer expressionViewer) {
        this.inputExpression = inputExpression;
        IObservableValue contentModelObservable = EMFObservables.observeValue(inputExpression,
                ExpressionPackage.Literals.EXPRESSION__CONTENT);
        IObservableValue returnTypeModelObservable = EMFObservables.observeValue(inputExpression,
                ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE);

        Object value = returnTypeModelObservable.getValue();
        dataBindingContext.bindValue(SWTObservables.observeText(valueText, SWT.Modify), contentModelObservable);
        UpdateValueStrategy targetToModel = new UpdateValueStrategy();
        targetToModel.setAfterGetValidator(new IValidator() {

            @Override
            public IStatus validate(Object value) {
                if (value == null || value.toString().isEmpty()) {
                    return ValidationStatus.error(Messages.returnTypeIsMandatory);
                }
                return ValidationStatus.ok();
            }
        });

        Binding bindValue = dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(typeCombo),
                returnTypeModelObservable, targetToModel, null);
        IObservableValue validationStatus = bindValue.getValidationStatus();
        validationStatus.addValueChangeListener(handleValidationStatusChanged());
        typeCombo.getCombo().setEnabled(!inputExpression.isReturnTypeFixed());

        statusChanged((IStatus) validationStatus.getValue());
    }

    private IValueChangeListener handleValidationStatusChanged() {
        return event -> {
            statusChanged((IStatus) event.diff.getNewValue());
        };
    }

    private void statusChanged(IStatus status) {
        if (!status.isOK()) {
            errorLabel.setText(status.getMessage());
            errorLabel.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_ERROR_TSK));
        } else {
            errorLabel.setText("");
            errorLabel.setImage(null);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#canFinish()
     */
    @Override
    public boolean canFinish() {
        return typeCombo != null && !typeCombo.getCombo().isDisposed() && typeCombo.getCombo().getText() != null
                && !typeCombo.getCombo().getText().isEmpty();
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
