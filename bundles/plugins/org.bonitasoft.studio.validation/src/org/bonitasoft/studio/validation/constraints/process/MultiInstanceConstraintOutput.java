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

import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.MultiInstanceType;
import org.bonitasoft.studio.model.process.MultiInstantiable;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

/**
 * @author Florine Boudin
 */
public class MultiInstanceConstraintOutput extends AbstractLiveValidationMarkerConstraint {

    private IStatus validateMultiInstantiation(final IValidationContext ctx, final MultiInstantiable multiInstantiable) {
        if (!multiInstantiable.isUseCardinality() && multiInstantiable.getOutputData() != null
                && multiInstantiable.getListDataContainingOutputResults() == null) {
            return ctx.createFailureStatus(new Object[] { Messages.Validation_MultiInstantiationOutputData });
        }

        return ctx.createSuccessStatus();
    }

    @Override
    protected IStatus performBatchValidation(final IValidationContext context) {
        final EObject eObj = context.getTarget();
        if (eObj instanceof Activity) {
            final Activity act = (Activity) eObj;
            if (act.getType() == MultiInstanceType.PARALLEL || act.getType() == MultiInstanceType.SEQUENTIAL) {
                return validateMultiInstantiation(context, act);
            }
        }
        return context.createSuccessStatus();
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.multiInstanceOutput";
    }

}
