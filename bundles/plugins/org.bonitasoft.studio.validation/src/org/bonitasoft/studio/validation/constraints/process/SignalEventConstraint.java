/**
 * Copyright (C) 2010 BonitaSoft S.A.
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

import org.bonitasoft.bpm.model.process.ProcessPackage;
import org.bonitasoft.bpm.model.process.SignalEvent;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.gmf.runtime.notation.NotationPackage;

/**
 * @author Romain Bioteau
 */
public class SignalEventConstraint extends AbstractLiveValidationMarkerConstraint {

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
     */
    @Override
    protected IStatus performLiveValidation(final IValidationContext ctx) {
        final EStructuralFeature featureTriggered = ctx.getFeature();
        if (featureTriggered.equals(ProcessPackage.Literals.SIGNAL_EVENT__SIGNAL_CODE)) {
            final String eventId = (String) ctx.getFeatureNewValue();
            if (eventId == null || eventId.trim().isEmpty()) {
                return ctx.createFailureStatus(new Object[] { Messages.validation_signalEvent_codeNotSet });
            }
        } else if (featureTriggered.equals(NotationPackage.Literals.VIEW__ELEMENT)) {
            final Object element = ctx.getFeatureNewValue();
            if (element instanceof SignalEvent) {
                final String eventId = ((SignalEvent) element).getSignalCode();
                if (eventId == null || eventId.trim().isEmpty()) {
                    return ctx.createFailureStatus(new Object[] { Messages.validation_signalEvent_codeNotSet });
                }
            }
        }
        return ctx.createSuccessStatus();
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraints.SignalEvent";
    }

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final SignalEvent signalEvent = (SignalEvent) ctx.getTarget();
        if (signalEvent.getSignalCode() == null || signalEvent.getSignalCode().trim().isEmpty()) {
            return ctx.createFailureStatus(new Object[] { Messages.validation_signalEvent_codeNotSet });
        }
        return ctx.createSuccessStatus();
    }

}
