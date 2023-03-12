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

import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ThrowLinkEvent;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.gmf.runtime.notation.NotationPackage;

/**
 * @author Baptiste Mesta
 */
public class ThrowLinkEventConstraint extends AbstractLiveValidationMarkerConstraint {

    @Override
    protected IStatus performLiveValidation(final IValidationContext ctx) {
        final EStructuralFeature feature = ctx.getFeature();
        if (feature.equals(ProcessPackage.Literals.THROW_LINK_EVENT__TO)) {
            final ThrowLinkEvent event = (ThrowLinkEvent) ctx.getTarget();
            if (event.getTo() == null || event.getTo().getName().trim().isEmpty()) {
                return ctx.createFailureStatus(new Object[] { Messages.Validation_LinkEvent_MissingTarget });
            }
        } else if (feature.equals(NotationPackage.Literals.VIEW__ELEMENT)) {
            final Object object = ctx.getFeatureNewValue();
            if (object instanceof ThrowLinkEvent) {
                final ThrowLinkEvent event = (ThrowLinkEvent) object;
                if (event.getTo() == null || event.getTo().getName().trim().isEmpty()) {
                    return ctx.createFailureStatus(new Object[] { Messages.Validation_LinkEvent_MissingTarget });
                }
            }
        }

        return ctx.createSuccessStatus();
    }

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final ThrowLinkEvent event = (ThrowLinkEvent) ctx.getTarget();
        if (!event.getOutgoing().isEmpty()) {
            return ctx.createFailureStatus(new Object[] { Messages.outgoingTransitionNotSupported });
        }

        if (event.getTo() == null || event.getTo().getName().trim().isEmpty()) {
            return ctx.createFailureStatus(new Object[] { Messages.Validation_LinkEvent_MissingTarget });
        }
        return ctx.createSuccessStatus();
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraints.throwlinkevent";
    }

}
