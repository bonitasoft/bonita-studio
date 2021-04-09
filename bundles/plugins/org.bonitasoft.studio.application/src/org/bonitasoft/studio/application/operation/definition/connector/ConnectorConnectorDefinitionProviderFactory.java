/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.application.operation.definition.connector;

import org.bonitasoft.studio.application.operation.definition.ConnectorDefinitionProvider;
import org.bonitasoft.studio.application.operation.definition.ConnectorDefinitionProviderFactory;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;

public class ConnectorConnectorDefinitionProviderFactory implements ConnectorDefinitionProviderFactory {
    
    private ConnectorDefRepositoryStore connectorDefStore;

    public ConnectorConnectorDefinitionProviderFactory(ConnectorDefRepositoryStore connectorDefStore) {
        this.connectorDefStore = connectorDefStore;
    }

    @Override
    public ConnectorDefinitionProvider get() {
        return new ConnectorConnectorDefinitionProvider(connectorDefStore);
    }

}
