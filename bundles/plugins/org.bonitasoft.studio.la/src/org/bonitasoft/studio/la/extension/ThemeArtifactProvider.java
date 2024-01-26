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
package org.bonitasoft.studio.la.extension;

import java.util.Objects;
import java.util.Optional;

import jakarta.inject.Inject;

import org.apache.maven.model.Dependency;
import org.bonitasoft.plugin.analyze.report.model.Artifact;
import org.bonitasoft.plugin.analyze.report.model.Theme;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.eclipse.e4.core.di.annotations.Creatable;

@Creatable
public class ThemeArtifactProvider {

    private RepositoryAccessor repositoryAccessor;

    @Inject
    public ThemeArtifactProvider(RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
    }

    public Optional<Theme> findTheme(Dependency dependency) {
        return repositoryAccessor.getCurrentRepository().orElseThrow().getProjectDependenciesStore().getThemes().stream()
                .filter(theme -> isSameArtifact(theme.getArtifact(), dependency))
                .findAny();
    }

    private boolean isSameArtifact(Artifact artifact, Dependency dependency) {
        return Objects.equals(dependency.getGroupId(), artifact.getGroupId())
                && Objects.equals(dependency.getArtifactId(), artifact.getArtifactId())
                && Objects.equals(dependency.getVersion(), artifact.getVersion())
                && Objects.equals(dependency.getClassifier(), artifact.getClassifier());
    }

}
