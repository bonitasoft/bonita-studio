/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.operation.extension.participant.definition;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.bonitasoft.studio.application.operation.extension.participant.definition.actorfilter.ActorFilterArtifactDefinitionProvider;
import org.bonitasoft.studio.application.operation.extension.participant.definition.actorfilter.ActorFilterConnectorDefinitionProviderFactory;
import org.bonitasoft.studio.application.operation.extension.participant.definition.actorfilter.ProjectActorFilterConfigurationCollector;
import org.bonitasoft.studio.application.operation.extension.participant.definition.connector.ConnectorArtifactDefinitionProvider;
import org.bonitasoft.studio.application.operation.extension.participant.definition.connector.ConnectorConnectorDefinitionProviderFactory;
import org.bonitasoft.studio.application.operation.extension.participant.definition.connector.ProjectConnectorConfigurationCollector;
import org.bonitasoft.studio.application.operation.extension.participant.definition.delegate.ArtifactDefinitionProviderDelegate;
import org.bonitasoft.studio.application.operation.extension.participant.definition.delegate.ConnectorConfigurationCollectorDelegate;
import org.bonitasoft.studio.application.operation.extension.participant.definition.delegate.ConnectorDefinitionProviderFactoryDelegate;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.extension.update.DependencyUpdate;
import org.bonitasoft.studio.common.repository.extension.update.participant.ExtensionUpdateParticipant;
import org.bonitasoft.studio.common.repository.extension.update.participant.ExtensionUpdateParticipantFactory;
import org.bonitasoft.studio.common.repository.extension.update.participant.ExtensionUpdateParticipantFactoryRegistry;
import org.bonitasoft.studio.connector.model.definition.migration.ConnectorConfigurationMigratorFactory;
import org.bonitasoft.studio.connectors.repository.ConnectorConfRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.identity.actors.repository.ActorFilterConfRepositoryStore;
import org.bonitasoft.studio.identity.actors.repository.ActorFilterDefRepositoryStore;

public class DefinitionUpdateParticipantFactory implements ExtensionUpdateParticipantFactory {

    private RepositoryAccessor repositoryAccessor;
    private ConnectorConfigurationMigratorFactory migratorFactory;

    @Inject
    public DefinitionUpdateParticipantFactory(RepositoryAccessor repositoryAccessor,
            ConnectorConfigurationMigratorFactory migratorFactory) {
        this.repositoryAccessor = repositoryAccessor;
        this.migratorFactory = migratorFactory;
    }

    @PostConstruct
    public void register() {
        ExtensionUpdateParticipantFactoryRegistry.getInstance().register(this);
    }

    @Override
    public ExtensionUpdateParticipant create(List<DependencyUpdate> dependenciesUpdate) {
        ArtifactDefinitionProvider artifactDefinitionProvider = getArtifactDefinitionProvider();
        ConnectorDefinitionProviderFactory connectorDefinitionProviderFactory = getConnectorDefinitionProviderFactory();
        ConnectorConfigurationCollector connectorConfigurationCollector = getConnectorConfigurationCollector();
        return new DefinitionUpdateParticipant(artifactDefinitionProvider,
                dependenciesUpdate,
                connectorDefinitionProviderFactory,
                migratorFactory,
                connectorConfigurationCollector);
    }

    private ConnectorConfigurationCollector getConnectorConfigurationCollector() {
        DiagramRepositoryStore diagramStore = repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class);
        return new ConnectorConfigurationCollectorDelegate(new ProjectConnectorConfigurationCollector(diagramStore,
                repositoryAccessor.getRepositoryStore(ConnectorConfRepositoryStore.class)),
                new ProjectActorFilterConfigurationCollector(diagramStore,
                        repositoryAccessor.getRepositoryStore(ActorFilterConfRepositoryStore.class)));
    }

    private ConnectorDefinitionProviderFactory getConnectorDefinitionProviderFactory() {
        return new ConnectorDefinitionProviderFactoryDelegate(new ConnectorConnectorDefinitionProviderFactory(
                repositoryAccessor.getRepositoryStore(ConnectorDefRepositoryStore.class)),
                new ActorFilterConnectorDefinitionProviderFactory(
                        repositoryAccessor.getRepositoryStore(ActorFilterDefRepositoryStore.class)));
    }

    private ArtifactDefinitionProvider getArtifactDefinitionProvider() {
        return new ArtifactDefinitionProviderDelegate(
                new ConnectorArtifactDefinitionProvider(repositoryAccessor.getCurrentRepository()),
                new ActorFilterArtifactDefinitionProvider(repositoryAccessor.getCurrentRepository()));
    }
}
