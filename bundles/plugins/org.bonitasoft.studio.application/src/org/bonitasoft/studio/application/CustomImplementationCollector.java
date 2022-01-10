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
package org.bonitasoft.studio.application;

import static java.util.function.Predicate.not;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.migration.dependencies.DependentArtifactCollector;
import org.bonitasoft.studio.common.repository.core.migration.dependencies.DependentArtifactCollectorRegistry;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connector.model.implementation.JarDependencies;
import org.bonitasoft.studio.connectors.repository.ConnectorImplRepositoryStore;
import org.bonitasoft.studio.connectors.repository.DependencyConnectorImplFileStore;
import org.bonitasoft.studio.identity.actors.repository.ActorFilterImplRepositoryStore;
import org.bonitasoft.studio.identity.actors.repository.DependencyActorFilterImplFileStore;
import org.eclipse.e4.core.di.annotations.Creatable;

@Creatable
public class CustomImplementationCollector implements DependentArtifactCollector<ConnectorImplementation> {

    private RepositoryAccessor repositoryAccessor;

    @Inject
    public CustomImplementationCollector(RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
    }
    
    @PostConstruct
    void init(DependentArtifactCollectorRegistry registry) {
        registry.register(ConnectorImplementation.class, this);
    }

    @Override
    public Collection<ConnectorImplementation> findArtifactDependingOn(String jarName) {
        var connectorImplRepositoryStore = repositoryAccessor.getRepositoryStore(ConnectorImplRepositoryStore.class);
        var actorFilterImplRepositoryStore = repositoryAccessor
                .getRepositoryStore(ActorFilterImplRepositoryStore.class);

        return Stream.concat(connectorImplRepositoryStore.getChildren().stream()
                .filter(not(DependencyConnectorImplFileStore.class::isInstance)),
                actorFilterImplRepositoryStore.getChildren().stream()
                        .filter(not(DependencyActorFilterImplFileStore.class::isInstance)))
                .map(t -> {
                    try {
                        return t.getContent();
                    } catch (ReadFileStoreException e) {
                       return null;
                    }
                })
                .filter(Objects::nonNull)
                .filter(withJarDependency(jarName))
                .collect(Collectors.toSet());
    }

    private Predicate<ConnectorImplementation> withJarDependency(String jarName) {
        return impl -> Optional.ofNullable(impl.getJarDependencies())
                .map(JarDependencies::getJarDependency)
                .stream()
                .anyMatch(deps -> deps.contains(jarName));
    }
}
