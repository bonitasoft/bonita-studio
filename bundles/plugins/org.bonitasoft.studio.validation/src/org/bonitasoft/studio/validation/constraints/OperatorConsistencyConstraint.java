/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.validation.constraints;

import org.bonitasoft.studio.expression.editor.operation.OperatorLabelProvider;
import org.bonitasoft.studio.expression.editor.operation.OperatorViewerFilter;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;

public class OperatorConsistencyConstraint extends AbstractLiveValidationMarkerConstraint {

    private final OperatorLabelProvider labelProvider = new OperatorLabelProvider();

    @Override
    protected IStatus performBatchValidation(final IValidationContext context) {
        final Operation operation = (Operation) context.getTarget();
        final OperatorViewerFilter filter = new OperatorViewerFilter(operation);
        final Operator operator = operation.getOperator();
        final Expression leftOperand = operation.getLeftOperand();
        if (!isLeftOperandEmpty(leftOperand)
                && operator != null
                && !filter.select(null, null, operator.getType())) {
            return context.createFailureStatus(
                    Messages.bind(Messages.validation_OperatorConsistency,
                            new String[] { labelProvider.getText(operator),
                                    leftOperand.getName() }));
        }
        return context.createSuccessStatus();
    }

    private boolean isLeftOperandEmpty(final Expression leftOperand) {
        return leftOperand == null || leftOperand.getName() == null;
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.operatorconsistency";
    }

}
