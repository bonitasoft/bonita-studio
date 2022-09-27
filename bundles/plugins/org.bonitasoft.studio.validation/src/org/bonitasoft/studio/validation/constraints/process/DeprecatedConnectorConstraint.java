/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.validation.constraints.process;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

public class DeprecatedConnectorConstraint extends AbstractLiveValidationMarkerConstraint {

    private static final Map<String, String> CONNECTOR_REPLACEMENTS = new HashMap<>();
    static {
        CONNECTOR_REPLACEMENTS.put("scripting-groovy", "scripting-groovy-script");
        CONNECTOR_REPLACEMENTS.put("AlfrescoUploadFileByPath", "cmis-uploadnewdocument");
        CONNECTOR_REPLACEMENTS.put("AlfrescoCreateFolderByPath", "cmis-createfolder");
        CONNECTOR_REPLACEMENTS.put("AlfrescoDeleteItemById", "cmis-deletedocument");
        CONNECTOR_REPLACEMENTS.put("AlfrescoDeleteFileByPath", "cmis-deletedocument");
    }

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final EObject eObj = ctx.getTarget();
        checkArgument(eObj instanceof Connector);
        final Connector configuration = (Connector) eObj;
        if (CONNECTOR_REPLACEMENTS.containsKey(configuration.getDefinitionId())) {
            return ctx.createFailureStatus(String.format(Messages.deprecatedConnectorDefinition,
                    configuration.getDefinitionId(), CONNECTOR_REPLACEMENTS.get(configuration.getDefinitionId())));
        }
        return ctx.createSuccessStatus();
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraints.deprecated.connector";
    }

}
