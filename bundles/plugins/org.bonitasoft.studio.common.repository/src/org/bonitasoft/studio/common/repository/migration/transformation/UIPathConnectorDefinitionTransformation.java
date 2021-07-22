/**
 * Copyright (C) 2021 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.repository.migration.transformation;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.repository.migration.ProcessModelTransformation;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationFactory;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.process.Connector;
import org.eclipse.emf.ecore.EObject;


public class UIPathConnectorDefinitionTransformation implements ProcessModelTransformation {

    private static final String UIPATH_STARTJOB_ID = "uipath-startjob";
    private static final Set<String> UI_PATH_DEFINITIONS_IDS = new HashSet<>();
    static {
        UI_PATH_DEFINITIONS_IDS.add("uipath-getjob");
        UI_PATH_DEFINITIONS_IDS.add(UIPATH_STARTJOB_ID);
        UI_PATH_DEFINITIONS_IDS.add("uipath-add-queueItem");
    }
    private static final String SOURCE_DEFINITION_VERSION = "2.0.0";
    private static final String TARGET_DEFINITION_VERSION = "2.1.0";
    private static final String ORGANIZATION_UNIT_ID_INPUT = "organizationUnitId";
    private static final String RUNTIME_TYPE_INPUT = "runtimeType";
    private static final String SOURCE_INPUT = "source";

    @Override
    public void transform(EObject modelObject) {
        ConnectorConfiguration configuration = (ConnectorConfiguration) modelObject;
        List<ConnectorParameter> parameters = configuration.getParameters();
        parameters.add(createParamenter(ORGANIZATION_UNIT_ID_INPUT,"", String.class.getName()));
        if(configuration.getDefinitionId().equals(UIPATH_STARTJOB_ID)) {
            parameters.add(createParamenter(RUNTIME_TYPE_INPUT,"Development", String.class.getName()));
            parameters.add(createParamenter(SOURCE_INPUT, "Manual", String.class.getName()));
        }
        EObject container = configuration.eContainer();
        if(container instanceof Connector) {
            ((Connector) container).setDefinitionVersion(TARGET_DEFINITION_VERSION);
        }
        configuration.setVersion(TARGET_DEFINITION_VERSION);
    }

    private ConnectorParameter createParamenter(String key, String value, String returnType) {
        ConnectorParameter connectorParameter = ConnectorConfigurationFactory.eINSTANCE.createConnectorParameter();
        connectorParameter.setKey(key);
        connectorParameter.setExpression(ExpressionHelper.createConstantExpression(value, returnType));
        return connectorParameter;
    }

    @Override
    public boolean appliesTo(EObject modelObject) {
        return modelObject instanceof ConnectorConfiguration
                && UI_PATH_DEFINITIONS_IDS.contains(((ConnectorConfiguration)modelObject).getDefinitionId())
                && Objects.equals(((ConnectorConfiguration)modelObject).getVersion(), SOURCE_DEFINITION_VERSION);
    }

}
