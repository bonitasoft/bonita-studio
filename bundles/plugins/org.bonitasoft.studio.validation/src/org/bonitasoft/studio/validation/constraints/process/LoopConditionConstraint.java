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

import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.MultiInstanceType;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;

/**
 * @author Romain Bioteau
 */
public class LoopConditionConstraint extends AbstractLiveValidationMarkerConstraint {

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraints.nonemptyloopcondition";
    }

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        if (ctx.getTarget() instanceof Activity) {
            final Activity activity = (Activity) ctx.getTarget();
            if (activity.getType() == MultiInstanceType.STANDARD) {
                if (activity.getLoopCondition() == null || activity.getLoopCondition().getContent() == null
                        || activity.getLoopCondition().getContent().isEmpty()) {
                    return ctx.createFailureStatus(new Object[] { activity.getName() });
                }
            }
        }
        return ctx.createSuccessStatus();
    }

}
