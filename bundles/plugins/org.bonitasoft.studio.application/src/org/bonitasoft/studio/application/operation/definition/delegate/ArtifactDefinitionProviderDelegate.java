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
package org.bonitasoft.studio.application.operation.definition.delegate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.maven.model.Dependency;
import org.bonitasoft.plugin.analyze.report.model.Definition;
import org.bonitasoft.studio.application.operation.definition.ArtifactDefinitionProvider;

public class ArtifactDefinitionProviderDelegate implements ArtifactDefinitionProvider {

    private List<ArtifactDefinitionProvider> delegates = new ArrayList<>();

    public ArtifactDefinitionProviderDelegate(ArtifactDefinitionProvider... delegates) {
        this.delegates = List.of(delegates);
    }

    @Override
    public Collection<Definition> getDefinitions(Dependency dependency) {
        return delegates.stream()
                .flatMap(delegate -> delegate.getDefinitions(dependency).stream())
                .collect(Collectors.toList());
    }

}
