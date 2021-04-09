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

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.studio.application.operation.definition.ConnectorConfigurationCollector;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.filestore.DefinitionConfigurationFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.connectors.repository.ConnectorConfRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.process.ActorFilter;
import org.bonitasoft.studio.model.process.Connector;

public class ProjectConnectorConfigurationCollector implements ConnectorConfigurationCollector {

    private DiagramRepositoryStore diagramStore;
    private ConnectorConfRepositoryStore confStore;

    public ProjectConnectorConfigurationCollector(DiagramRepositoryStore diagramStore,
            ConnectorConfRepositoryStore confStore) {
        this.diagramStore = diagramStore;
        this.confStore = confStore;
    }

    @Override
    public Collection<ConnectorConfiguration> getConfigurations(String definitionId, String definitionVersion) {
        if (!diagramStore.hasComputedProcesses()) {
            return Collections.emptyList();
        }
        return Stream.concat(diagramStore.getComputedProcesses().stream()
                .flatMap(process -> ModelHelper.getAllElementOfTypeIn(process, Connector.class).stream())
                .filter(c -> !(c instanceof ActorFilter))
                .map(Connector::getConfiguration)
                .filter(Objects::nonNull),
                confStore.getChildren().stream()
                        .map(loadConfiguration())
                        .filter(Objects::nonNull))
                .filter(conf -> Objects.equals(conf.getDefinitionId(), definitionId)
                        && Objects.equals(conf.getVersion(), definitionVersion))
                .collect(Collectors.toList());
    }

    private Function<? super DefinitionConfigurationFileStore, ? extends ConnectorConfiguration> loadConfiguration() {
        return fStore -> {
            try {
                return fStore.getContent();
            } catch (ReadFileStoreException e) {
                BonitaStudioLog.error(e);
                return null;
            }
        };
    }

}
