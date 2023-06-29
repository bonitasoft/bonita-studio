/**
 * Copyright (C) 2020 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.validation.constraints.connector;

import static org.bonitasoft.studio.connector.model.definition.CloudProblematicsConnectors.WARNING_CONNECTORS;

import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;

public class CloudWarningConnectors extends AbstractLiveValidationMarkerConstraint {

    public static final String ID = "org.bonitasoft.studio.validation.constraints.cloudWarningConnectors";

    @Override
    protected IStatus performLiveValidation(IValidationContext context) {
        return null;
    }

    @Override
    protected IStatus performBatchValidation(IValidationContext context) {
        Connector connector = (Connector) context.getTarget();
        String definitionId = connector.getDefinitionId();
        if (WARNING_CONNECTORS.containsKey(definitionId)) {
            return context.createFailureStatus(WARNING_CONNECTORS.get(definitionId));
        }
        return context.createSuccessStatus();
    }

    @Override
    protected String getConstraintId() {
        return ID;
    }

}
