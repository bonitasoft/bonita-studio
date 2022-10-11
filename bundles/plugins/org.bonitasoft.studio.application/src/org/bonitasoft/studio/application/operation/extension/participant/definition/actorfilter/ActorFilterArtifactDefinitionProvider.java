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

import java.util.Collection;
import java.util.stream.Collectors;

import org.apache.maven.model.Dependency;
import org.bonitasoft.plugin.analyze.report.model.Definition;
import org.bonitasoft.studio.application.operation.extension.participant.definition.ArtifactDefinitionProvider;
import org.bonitasoft.studio.common.repository.model.IRepository;

public class ActorFilterArtifactDefinitionProvider implements ArtifactDefinitionProvider {

    private IRepository repository;

    public ActorFilterArtifactDefinitionProvider(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Definition> getDefinitions(Dependency dependency) {
        return repository.getProjectDependenciesStore().getActorFilterDefinitions().stream()
                .filter(def -> providedByExtension(def, dependency))
                .collect(Collectors.toList());
    }

}
