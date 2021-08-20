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
package org.bonitasoft.studio.model.process.builders;

import org.bonitasoft.studio.model.Buildable;
import org.bonitasoft.studio.model.connectorconfiguration.builders.ConnectorConfigurationBuilder;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.process.ConnectableElement;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.ProcessFactory;

/**
 * @author Romain Bioteau
 */
public class ConnectorBuilder extends ElementBuilder<Connector, ConnectorBuilder> {

    public static ConnectorBuilder aConnector() {
        return new ConnectorBuilder();
    }

    public ConnectorBuilder withDefinitionId(final String definitionId) {
        getBuiltInstance().setDefinitionId(definitionId);
        return getThis();
    }

    public ConnectorBuilder withDefinitionVersion(final String definitionVersion) {
        getBuiltInstance().setDefinitionVersion(definitionVersion);
        return getThis();
    }

    public ConnectorBuilder onEvent(final String event) {
        getBuiltInstance().setEvent(event);
        return getThis();
    }

    public ConnectorBuilder havingConfiguration(final ConnectorConfigurationBuilder connectorConfiguration) {
        getBuiltInstance().setConfiguration(connectorConfiguration.build());
        return getThis();
    }

    @SafeVarargs
    public final ConnectorBuilder havingOutput(final Buildable<? extends Operation>... outputOperation) {
        for (final Buildable<? extends Operation> output : outputOperation) {
            getBuiltInstance().getOutputs().add(output.build());
        }
        return getThis();
    }

    public ConnectorBuilder in(final Buildable<? extends ConnectableElement> container) {
        container.build().getConnectors().add(getBuiltInstance());
        return getThis();
    }

    @Override
    protected Connector newInstance() {
        return ProcessFactory.eINSTANCE.createConnector();
    }

}
