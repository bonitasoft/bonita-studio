/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.expression.editor.viewer;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.provider.IExpressionValidator;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.core.databinding.ValidationStatusProvider;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;


/**
 * @author Romain Bioteau
 *
 */
public class ExpressionViewerValidator extends ValidationStatusProvider implements IValidator {

    private final List<IExpressionValidator> validators = new ArrayList<IExpressionValidator>();
    private EObject context;
    private final List<IExpressionValidationListener> listeners = new ArrayList<IExpressionValidationListener>();
    private Expression expression;
    private WritableValue validationStatus = new WritableValue(Status.OK_STATUS, IStatus.class);

    public void addValidator(final IExpressionValidator expressionValidator){
        if(!validators.contains(expressionValidator)){
            validators.add(expressionValidator);
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.databinding.validation.IValidator#validate(java.lang.Object)
     */
    @Override
    public IStatus validate(final Object value) {
        final IStatus iStatus = updateMessage(doValidate(value, getExpression()));
        validationStatus = new WritableValue(iStatus, IStatus.class);
        fireValidationStatusChanged(iStatus);
        return iStatus;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setContext(final EObject context) {
        this.context = context;
    }

    public EObject getContext() {
        return context;
    }

    public void setExpression(final Expression expression) {
        this.expression = expression;
    }

    protected IStatus updateMessage(final IStatus status) {
        String message = status.getMessage();
        if (!status.isOK()) {
            if (status instanceof MultiStatus) {
                final StringBuilder sb = new StringBuilder();
                for (final IStatus statusChild : status.getChildren()) {
                    sb.append(statusChild.getMessage());
                    sb.append("\n");
                }
                if (sb.length() > 0) {
                    sb.delete(sb.length() - 1, sb.length());
                }
                message = sb.toString();
                return ValidationStatus.error(message);
            }
        }
        return status;

    }

    public IStatus doValidate(final Object value,final Expression expression) {
        final IExpressionValidator delagateValidator = getExpressionValidator(expression);
        if (delagateValidator != null) {
            delagateValidator.setDomain(TransactionUtil.getEditingDomain(expression));
            delagateValidator.setContext(getContext());
            delagateValidator.setInputExpression(expression);
            Object toValidate = value;
            if (toValidate == null) {
                toValidate = "";
            }
            return delagateValidator.validate(toValidate);
        }
        return ValidationStatus.ok();
    }




    protected IExpressionValidator getExpressionValidator(final Expression selectedExpression) {
        if (selectedExpression != null) {
            for (final IExpressionValidator validator : validators) {
                if (validator.isRelevantForExpressionType(selectedExpression.getType())) {
                    return validator;
                }
            }
        }

        for (final IExpressionValidator validator : validators) {
            if (validator.isRelevantForExpressionType(ExpressionConstants.ALL_TYPES)) {
                return validator;
            }
        }

        return null;
    }

    protected void fireValidationStatusChanged(final IStatus status) {
        for (final IExpressionValidationListener listener : listeners) {
            listener.validationStatusChanged(status);
        }
    }

    public void addValidationsStatusChangedListener(final IExpressionValidationListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void removeValidationsStatusChangedListener(final IExpressionValidationListener listener) {
        if (listeners.contains(listener)) {
            listeners.remove(listener);
        }
    }

    @Override
    public IObservableList getModels() {
        return new WritableList();
    }

    @Override
    public IObservableList getTargets() {
        return new WritableList();
    }

    @Override
    public IObservableValue getValidationStatus() {
        return validationStatus;
    }

}
