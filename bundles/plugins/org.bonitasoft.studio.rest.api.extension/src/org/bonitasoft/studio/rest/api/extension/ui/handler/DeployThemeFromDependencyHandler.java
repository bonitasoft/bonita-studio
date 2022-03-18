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
package org.bonitasoft.studio.rest.api.extension.ui.handler;

import java.util.Objects;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.engine.http.HttpClientFactory;
import org.bonitasoft.studio.theme.DependencyThemeFileStore;
import org.bonitasoft.studio.theme.ThemeRepositoryStore;
import org.eclipse.e4.core.di.annotations.Execute;

public class DeployThemeFromDependencyHandler extends DeployRestApiExtensionFromDependencyHandler {

    @Override
    @Execute
    public void execute(RepositoryAccessor repositoryAccessor, HttpClientFactory httpClientFactory, String groupId,
            String artifactId, String version, String classifier) {
        repositoryAccessor.getCurrentRepository().getProjectDependenciesStore()
                .getThemes().stream()
                .filter(theme -> Objects.equals(theme.getArtifact().getGroupId(), groupId))
                .filter(theme -> Objects.equals(theme.getArtifact().getArtifactId(), artifactId))
                .filter(theme -> Objects.equals(theme.getArtifact().getVersion(), version))
                .filter(theme -> Objects.equals(theme.getArtifact().getClassifier(), classifier))
                .findFirst().ifPresent(theme -> {
                    deploy(new DependencyThemeFileStore(theme,
                            repositoryAccessor.getRepositoryStore(ThemeRepositoryStore.class)),
                            httpClientFactory);
                });
    }

}
