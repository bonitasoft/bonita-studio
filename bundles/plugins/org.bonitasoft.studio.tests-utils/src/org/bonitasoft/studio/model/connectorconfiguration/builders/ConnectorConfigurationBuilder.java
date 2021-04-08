/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.model.connectorconfiguration.builders;

import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationFactory;

/**
 * @author Romain Bioteau
 */
public class ConnectorConfigurationBuilder {

    public static ConnectorConfigurationBuilder aConnectorConfiguration() {
        return new ConnectorConfigurationBuilder();
    }

    private final ConnectorConfiguration instance;

    public ConnectorConfigurationBuilder() {
        instance = ConnectorConfigurationFactory.eINSTANCE.createConnectorConfiguration();
    }

    public ConnectorConfigurationBuilder withDefinitionId(final String definitionId) {
        instance.setDefinitionId(definitionId);
        return this;
    }

    public ConnectorConfigurationBuilder withDefinitionVersion(final String definitionVersion) {
        instance.setVersion(definitionVersion);
        return this;
    }

    public ConnectorConfigurationBuilder havingParameters(final ConnectorParameterBuilder... parameters) {
        for (final ConnectorParameterBuilder parameter : parameters) {
            instance.getParameters().add(parameter.build());
        }
        return this;
    }

    public ConnectorConfiguration build() {
        return instance;
    }
}
