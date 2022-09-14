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

import org.bonitasoft.studio.model.process.BoundaryEvent;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.gmf.runtime.notation.NotationPackage;

/**
 * @author Aurelien Pupier
 * @author Romain Bioteau
 *         Check that there is one and only one ExceptionTransition from a Boundary Event.
 */
public class ExceptionTransitionConstraint extends AbstractLiveValidationMarkerConstraint {

    @Override
    protected IStatus performLiveValidation(final IValidationContext ctx) {
        final EStructuralFeature feature = ctx.getFeature();
        if (feature.equals(ProcessPackage.Literals.SOURCE_ELEMENT__OUTGOING)) {
            final BoundaryEvent event = (BoundaryEvent) ctx.getTarget();
            final int size = event.getOutgoing().size();
            if (size == 0) {
                return ctx.createFailureStatus(Messages.validation_exceptionTransitionError_noTransition);
            } else if (size == 1) {
                return ctx.createSuccessStatus();
            } else {
                return ctx.createFailureStatus(Messages.validation_exceptionTransitionError_tooMuchTransition);
            }
        } else if (feature.equals(NotationPackage.Literals.VIEW__ELEMENT)) {
            final Object object = ctx.getFeatureNewValue();
            if (object instanceof BoundaryEvent) {
                final BoundaryEvent event = (BoundaryEvent) object;
                final int size = event.getOutgoing().size();
                if (size == 0) {
                    return ctx.createFailureStatus(Messages.validation_exceptionTransitionError_noTransition);
                } else if (size == 1) {
                    return ctx.createSuccessStatus();
                } else {
                    return ctx.createFailureStatus(Messages.validation_exceptionTransitionError_tooMuchTransition);
                }
            }
        }
        return ctx.createSuccessStatus();
    }

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final BoundaryEvent target = (BoundaryEvent) ctx.getTarget();
        final int size = target.getOutgoing().size();
        if (size == 0) {
            return ctx.createFailureStatus(Messages.validation_exceptionTransitionError_noTransition);
        } else if (size == 1) {
            return ctx.createSuccessStatus();
        } else {
            return ctx.createFailureStatus(Messages.validation_exceptionTransitionError_tooMuchTransition);
        }
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraint.ExceptionTransition";
    }

}
