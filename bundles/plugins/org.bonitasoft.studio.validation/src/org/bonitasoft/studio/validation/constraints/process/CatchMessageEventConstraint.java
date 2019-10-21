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

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.CorrelationTypeActive;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.StartMessageEvent;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.gmf.runtime.notation.NotationPackage;

/**
 * @author Romain Bioteau
 */
public class CatchMessageEventConstraint extends AbstractLiveValidationMarkerConstraint {

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
     */
    @Override
    protected IStatus performLiveValidation(final IValidationContext ctx) {
        final EStructuralFeature featureTriggered = ctx.getFeature();
        if (featureTriggered.equals(ProcessPackage.Literals.ABSTRACT_CATCH_MESSAGE_EVENT__EVENT)) {
            final String eventId = (String) ctx.getFeatureNewValue();
            if (eventId == null || eventId.trim().isEmpty()) {
                return ctx.createFailureStatus(new Object[] { Messages.Validation_CatchEventNoMessage });
            }
        } else if (featureTriggered.equals(NotationPackage.Literals.VIEW__ELEMENT)) {
            final Object element = ctx.getFeatureNewValue();
            if (element instanceof AbstractCatchMessageEvent) {
                final String eventId = ((AbstractCatchMessageEvent) element).getEvent();
                if (eventId == null || eventId.trim().isEmpty()) {
                    return ctx.createFailureStatus(new Object[] { Messages.Validation_CatchEventNoMessage });
                }
            }

        }
        return ctx.createSuccessStatus();
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraints.nocatchevent";
    }

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final AbstractCatchMessageEvent event = (AbstractCatchMessageEvent) ctx.getTarget();
        final String eventId = event.getEvent();
        if (eventId == null || eventId.trim().isEmpty()) {
            return ctx.createFailureStatus(new Object[] { Messages.Validation_CatchEventNoMessage });
        }
        if (event instanceof StartMessageEvent && !ModelHelper.isInEvenementialSubProcessPool(event)) {
            if (event.getIncomingMessag() != null) {
                if (event.getIncomingMessag().getSource() != null) {
                    for (final Message message : event.getIncomingMessag().getSource().getEvents()) {
                        if (message.getName().equals(eventId)) {
                            if (message.getCorrelation().getCorrelationType() != CorrelationTypeActive.INACTIVE) {
                                return ctx.createFailureStatus(new Object[] { Messages.Validation_StartMessageEventWithCorrelation });
                            }
                        }
                    }
                }
            }
        }
        return ctx.createSuccessStatus();
    }

}
