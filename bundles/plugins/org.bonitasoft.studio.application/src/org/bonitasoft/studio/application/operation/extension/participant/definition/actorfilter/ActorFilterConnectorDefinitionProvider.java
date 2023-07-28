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
package org.bonitasoft.studio.application.operation.extension.participant.definition.actorfilter;

import java.util.Optional;

import org.bonitasoft.studio.application.operation.extension.participant.definition.ConnectorDefinitionProvider;
import org.bonitasoft.studio.common.repository.provider.ExtendedConnectorDefinition;
import org.bonitasoft.bpm.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.identity.actors.repository.ActorFilterDefRepositoryStore;


public class ActorFilterConnectorDefinitionProvider implements ConnectorDefinitionProvider {
    
    private ActorFilterDefRepositoryStore actotFilterDefStore;

    public ActorFilterConnectorDefinitionProvider(ActorFilterDefRepositoryStore actotFilterDefStore) {
        this.actotFilterDefStore = actotFilterDefStore;
    }

    @Override
    public Optional<ConnectorDefinition> find(String id, String version) {
        return actotFilterDefStore.getResourceProvider().getConnectorDefinitionRegistry()
                .find(id, version)
                .map(ExtendedConnectorDefinition::getConnectorDefinition);
    }

}
