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
import org.bonitasoft.studio.model.simulation.DataChange;
import org.bonitasoft.studio.model.simulation.SimulationActivity;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

/**
 * @author Baptiste Mesta
 */
public class SimulationConstraint extends AbstractLiveValidationMarkerConstraint {

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final EObject eObj = ctx.getTarget();
        if (eObj instanceof Connection) {
            final Connection connection = (Connection) eObj;
            if (connection.isUseExpression()
                    && (connection.getExpression() == null || connection.getExpression().getContent() == null || connection.getExpression().getContent().trim()
                            .isEmpty())) {
                return ctx.createFailureStatus(new Object[] { Messages.Validation_SimulationEmptyExpression });
            }
        }

        if (eObj instanceof SimulationActivity) {
            final SimulationActivity activity = (SimulationActivity) eObj;
            for (final DataChange dc : activity.getDataChange()) {
                if (dc.getValue() == null || dc.getValue() == null || dc.getValue().getContent().trim().isEmpty() || dc.getData() == null) {
                    return ctx.createFailureStatus(new Object[] { Messages.Validation_SimulationEmptyExpression });
                }
            }

        }
        return ctx.createSuccessStatus();
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraints.SimulationConstraint";
    }

}
