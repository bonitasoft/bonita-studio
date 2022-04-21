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
package org.bonitasoft.studio.migration.custom.migration.connector;

import java.util.List;
import java.util.Objects;

import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Model;

public class UpdateDeprecatedGroovyConnector extends UpdateConnectorDefinitionMigration {

    private static final String DEPRECATED_GROOVY_DEF_ID = "scripting-groovy";
    private static final String DEPRECATED_GROOVY_DEF_VERSION = "1.0.2";

    private static final String CURRENT_GROOVY_DEF_ID = "scripting-groovy-script";
    private static final String CURRENT_GROOVY_DEF_VERSION = "1.0.1";

    private String oldContentValue;

    @Override
    protected String getNewDefinitionVersion() {
        return CURRENT_GROOVY_DEF_VERSION;
    }

    @Override
    protected String getOldDefinitionVersion() {
        return DEPRECATED_GROOVY_DEF_VERSION;
    }

    @Override
    protected String getNewDefinitionId() {
        return CURRENT_GROOVY_DEF_ID;
    }

    @Override
    protected boolean shouldUpdateVersion(String defId) {
        return Objects.equals(defId, DEPRECATED_GROOVY_DEF_ID);
    }

    @Override
    protected boolean shouldUpdateId(String defId) {
        return Objects.equals(defId, DEPRECATED_GROOVY_DEF_ID);
    }

    @Override
    protected void updateConfiguration(Instance connectorConfigInstance, Model model) {
        super.updateConfiguration(connectorConfigInstance, model);

        List<Instance> parameters = connectorConfigInstance.get("parameters");
        parameters.stream()
                .filter(p -> Objects.equals(p.get("key"), "script"))
                .forEach(p -> p.set("key", "fakeScriptExpression"));

        Instance scriptParameter = model.newInstance("connectorconfiguration.ConnectorParameter");
        scriptParameter.set("key", "script");
        parameters.add(scriptParameter);

        Instance variableParameter = model.newInstance("connectorconfiguration.ConnectorParameter");
        variableParameter.set("key", "variables");
        parameters.add(variableParameter);
    }

}
