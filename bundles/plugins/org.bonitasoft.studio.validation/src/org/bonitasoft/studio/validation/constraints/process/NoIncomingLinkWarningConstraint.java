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
package org.bonitasoft.studio.validation.constraints.process;

import org.bonitasoft.studio.model.process.CatchLinkEvent;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.StartErrorEvent;
import org.bonitasoft.studio.model.process.StartEvent;
import org.bonitasoft.studio.model.process.StartMessageEvent;
import org.bonitasoft.studio.model.process.StartSignalEvent;
import org.bonitasoft.studio.model.process.StartTimerEvent;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

/**
 * @author Romain Bioteau
 */
public class NoIncomingLinkWarningConstraint extends
        AbstractLiveValidationMarkerConstraint {

    private boolean dontNeedIncomingLink(final Object object) {
        return object instanceof SubProcessEvent
                || object instanceof StartEvent
                || object instanceof StartTimerEvent
                || object instanceof StartErrorEvent
                || object instanceof StartMessageEvent
                || object instanceof StartSignalEvent
                || object instanceof StartTimerEvent
                || object instanceof CatchLinkEvent;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.bonitasoft.studio.validation.constraints.live.AbstractLiveValidationMarkerConstraint#performBatchValidation(org.eclipse.emf.validation.IValidationContext
     * )
     */
    @Override
    protected IStatus performBatchValidation(final IValidationContext context) {
        final EObject flowElement = context.getTarget();
        if (dontNeedIncomingLink(flowElement)) {
            return context.createSuccessStatus();
        } else if (flowElement instanceof FlowElement) {
            if (((FlowElement) flowElement).getIncoming().isEmpty()) {
                return context.createFailureStatus(new Object[] { Messages.Validation_NoIncomingLink });
            }
        }

        return context.createSuccessStatus();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.validation.constraints.live.AbstractLiveValidationMarkerConstraint#getConstraintId()
     */
    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraints.noincomingwarning";
    }

}
