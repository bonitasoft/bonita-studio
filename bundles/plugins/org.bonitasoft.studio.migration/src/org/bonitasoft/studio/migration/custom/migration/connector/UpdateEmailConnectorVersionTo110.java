/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.migration.custom.migration.connector;

import java.util.List;
import java.util.Objects;

import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Model;


public class UpdateEmailConnectorVersionTo110 extends UpdateConnectorDefinitionMigration {

    private static final String EMAIL_CONNECTOR_DEFINITION = "email";

    @Override
    protected String getNewDefinitionVersion() {
        return "1.1.0";
    }

    @Override
    protected String getOldDefinitionVersion() {
        return "1.0.0";
    }

    @Override
    protected boolean shouldUpdateVersion(final String defId) {
        return EMAIL_CONNECTOR_DEFINITION.equals(defId);
    }

    @Override
    protected void updateConfiguration(Instance connectorConfigInstance, Model model) {
        List<Instance> parameters = connectorConfigInstance.get("parameters");
        parameters.stream()
                .filter(p -> Objects.equals(p.get("key"), "from") && p.get("expression") != null)
                .findFirst()
                .ifPresent(fromParameter -> {
                    Instance returnPathParameter = model.newInstance("connectorconfiguration.ConnectorParameter");
                    returnPathParameter.set("key", "returnPath");
                    Instance fromExpression = fromParameter.get("expression");
                    returnPathParameter.set("expression", fromExpression.copy());
                    parameters.add(returnPathParameter);
                });
    }
}
