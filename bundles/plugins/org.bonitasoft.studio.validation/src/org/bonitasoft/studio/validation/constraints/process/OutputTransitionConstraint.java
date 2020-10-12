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

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.SourceElement;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.osgi.util.NLS;

/**
 * @author Baptiste Mesta
 */
public class OutputTransitionConstraint extends AbstractLiveValidationMarkerConstraint {

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final EObject eObj = ctx.getTarget();
        if (eObj instanceof SourceElement) {
            final SourceElement sourceElement = (SourceElement) eObj;
            final Set<SequenceFlow> flowWithConditions = new HashSet<>();
            final Set<SequenceFlow> flowWithoutConditions = new HashSet<>();
            final Set<SequenceFlow> flowWithDefaultConditions = new HashSet<>();
            for (final Connection c : sourceElement.getOutgoing()) {
                if (c instanceof SequenceFlow) {
                    if (((SequenceFlow) c).isIsDefault()) {
                        flowWithDefaultConditions.add((SequenceFlow) c);
                    } else if (existCondition((SequenceFlow) c) || existDecisionTable((SequenceFlow) c)) {
                        flowWithConditions.add((SequenceFlow) c);
                    } else {
                        flowWithoutConditions.add((SequenceFlow) c);
                    }
                }
            }
            if (!flowWithConditions.isEmpty() && flowWithoutConditions.isEmpty() && flowWithDefaultConditions.isEmpty()) {
                return ctx.createFailureStatus(NLS.bind(Messages.missingDefaultSequenceFlow, sourceElement.getName()));
            }
        }

        return ctx.createSuccessStatus();
    }


    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.outputTransitions";
    }

    protected boolean existDecisionTable(final SequenceFlow sequenceFlow) {
        return sequenceFlow.getDecisionTable() != null && !sequenceFlow.getDecisionTable().getLines().isEmpty();
    }

    protected boolean existCondition(final SequenceFlow sequenceFlow) {
        return sequenceFlow.getCondition() != null && sequenceFlow.getCondition().getContent() != null
                && !sequenceFlow.getCondition().getContent().trim().isEmpty();
    }

}
