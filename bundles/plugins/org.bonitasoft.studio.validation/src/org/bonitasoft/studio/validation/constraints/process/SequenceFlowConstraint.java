/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.validation.constraints.process;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.SequenceFlowConditionType;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

/**
 * @author Romain Bioteau
 */
public class SequenceFlowConstraint extends AbstractLiveValidationMarkerConstraint {

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final EObject eObj = ctx.getTarget();
        if (eObj instanceof SequenceFlow) {
            final SequenceFlow sequenceFlow = (SequenceFlow) eObj;
            // task must have at least an actor (or group)
            if (!sequenceFlow.isIsDefault() && sequenceFlow.getConditionType() == SequenceFlowConditionType.EXPRESSION && sequenceFlow.getCondition() != null) {
                if (sequenceFlow.getCondition().getContent() != null && !sequenceFlow.getCondition().getContent().isEmpty()) {
                    final Expression exp = sequenceFlow.getCondition();
                    if (!exp.getReturnType().equals(Boolean.class.getName())) {
                        return ctx.createFailureStatus(new Object[] { Messages.SequenceFlow_Expression_ReturnBoolean });
                    }
                }

            }
        }

        return ctx.createSuccessStatus();
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraints.sequenceflow";
    }

}
