/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.validation.constraints.process;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.validation.IValidationContext;

/**
 * @author Aurelien Pupier
 */
public class OperationNotEmptyConstraint extends
        AbstractLiveValidationMarkerConstraint {

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final Object target = ctx.getTarget();
        if (target instanceof AbstractCatchMessageEvent) {
            final EList<Operation> operations = ((AbstractCatchMessageEvent) target).getMessageContent();
            final List<String> errorsFound = checkOperations(operations);
            if (!errorsFound.isEmpty()) {
                return ctx.createFailureStatus(new Object[] { Messages.validation_messageContentWithEmptyFields });
            }
        } else if (target instanceof Activity) {
            final EList<Operation> operations = ((Activity) target).getOperations();
            final List<String> errorsFound = checkOperations(operations);
            if (!errorsFound.isEmpty()) {
                return ctx.createFailureStatus(new Object[] { Messages.validationTaskOperationWithEmptyFields });
            }
        }
        return ctx.createSuccessStatus();
    }

    private List<String> checkOperations(final EList<Operation> operations) {
        final List<String> errorsFound = new ArrayList<String>();
        for (final Operation operation : operations) {
            if (operation.getLeftOperand() == null 
                    || operation.getLeftOperand().getName() == null
                    || operation.getLeftOperand().getName().isEmpty()) {
                errorsFound.add("An operation has no Left Operand.");
            }
            boolean deleteOperator = false;
            final Operator operator = operation.getOperator();
            if (operation != null) {
                deleteOperator = ExpressionConstants.DELETION_OPERATOR.equals(operator.getType());
            }
            if (!deleteOperator) {
                if (operation.getRightOperand() == null || operation.getRightOperand().getName() == null) {
                    errorsFound.add("An operation has no Right Operand.");
                }
            }
        }
        return errorsFound;
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraint.non.empty.operation";
    }

}
