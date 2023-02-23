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

import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.XORGateway;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.gmf.runtime.notation.NotationPackage;

/**
 * @author Baptiste Mesta
 */
public class XORGatewayConstraint extends AbstractLiveValidationMarkerConstraint {

    public static final String ID = "org.bonitasoft.studio.validation.xorGateway";

    @Override
    protected IStatus performLiveValidation(final IValidationContext ctx) {
        final EStructuralFeature feature = ctx.getFeature();
        if (feature.equals(ProcessPackage.Literals.SOURCE_ELEMENT__OUTGOING)) {
            final XORGateway gate = (XORGateway) ctx.getTarget();
            return validateXOROutgoing(ctx, gate);
        } else if (feature.equals(NotationPackage.Literals.VIEW__ELEMENT)) {
            final Object object = ctx.getFeatureNewValue();
            if (object instanceof XORGateway) {
                final XORGateway gate = (XORGateway) object;
                return validateXOROutgoing(ctx, gate);
            }
        }

        return ctx.createSuccessStatus();
    }

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final EObject eObj = ctx.getTarget();
        if (eObj instanceof XORGateway) {
            final XORGateway gateway = (XORGateway) eObj;
            return validateXOROutgoing(ctx, gateway);
        }

        return ctx.createSuccessStatus();
    }

    private IStatus validateXOROutgoing(final IValidationContext ctx, final XORGateway gateway) {
        if (gateway.getOutgoing().size() <= 1) {
            return ctx.createSuccessStatus();
        } else {
            int nbDefault = 0;
            for (final Connection connection : gateway.getOutgoing()) {
                if (connection instanceof SequenceFlow) {
                    final SequenceFlow sequenceFlow = (SequenceFlow) connection;
                    if (!sequenceFlow.isIsDefault() && !existCondition(sequenceFlow) && !existDecisionTable(sequenceFlow)) {
                        return ctx.createFailureStatus(new Object[] { Messages.Validation_NotConditionalTransitionAfterXORGateway });
                    }
                    if (sequenceFlow.isIsDefault()) {
                        nbDefault++;
                    }
                    if (nbDefault > 1) {
                        return ctx.createFailureStatus(new Object[] { Messages.Validation_SeveralDefaultTransitionsAfterXORGateway });
                    }
                }
            }
        }
        return ctx.createSuccessStatus();
    }

    @Override
    protected String getConstraintId() {
        return ID;
    }

    private boolean existDecisionTable(final SequenceFlow sequenceFlow) {
        return sequenceFlow.getDecisionTable() != null && !sequenceFlow.getDecisionTable().getLines().isEmpty();
    }

    protected boolean existCondition(final SequenceFlow sequenceFlow) {
        return sequenceFlow.getCondition() != null && sequenceFlow.getCondition().getContent() != null
                && !sequenceFlow.getCondition().getContent().trim().isEmpty();
    }

}
