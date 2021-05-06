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
package org.bonitasoft.studio.application.views.extension.card.zoom;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.maven.model.Dependency;
import org.bonitasoft.plugin.analyze.report.model.Implementation;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependency;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.provider.ConnectorDefinitionRegistry;
import org.bonitasoft.studio.common.repository.provider.ExtendedConnectorDefinition;
import org.bonitasoft.studio.identity.actors.repository.ActorFilterDefRepositoryStore;
import org.eclipse.swt.widgets.Composite;

public class ActorFilterZoomControl extends ConnectorZoomControl {

    public ActorFilterZoomControl(Composite parent, ZoomListener zoomListener, Dependency dep,
            BonitaArtifactDependency bonitaDep) {
        super(parent, zoomListener, dep, bonitaDep);
    }

    @Override
    protected List<ExtendedConnectorDefinition> getDefinitions() {
        ConnectorDefinitionRegistry registry = RepositoryManager.getInstance()
                .getRepositoryStore(ActorFilterDefRepositoryStore.class).getResourceProvider()
                .getConnectorDefinitionRegistry();
        return projectDependenciesStore.getActorFilterDefinitions().stream()
                .filter(def -> Objects.equals(def.getArtifact().getGroupId(), dep.getGroupId()))
                .filter(def -> Objects.equals(def.getArtifact().getArtifactId(), dep.getArtifactId()))
                .filter(def -> Objects.equals(def.getArtifact().getVersion(), dep.getVersion()))
                .map(def -> registry.find(def.getDefinitionId(), def.getDefinitionVersion()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .sorted((def1, def2) -> def1.getConnectorDefinitionLabel().compareTo(def2.getConnectorDefinitionLabel()))
                .collect(Collectors.toList());
    }

    @Override
    protected List<Implementation> getImplementations(ExtendedConnectorDefinition def) {
        return projectDependenciesStore.getActorFilterImplementations().stream()
                .filter(impl -> Objects.equals(impl.getDefinitionId(), def.getId()))
                .filter(impl -> Objects.equals(impl.getDefinitionVersion(), def.getVersion()))
                .collect(Collectors.toList());
    }

    @Override
    protected String getDetailsTitle() {
        return Messages.actorFilters;
    }

    @Override
    protected String getDetailsHint() {
        return String.format(Messages.actorFiltersHint, bonitaDep.getName());
    }

}
