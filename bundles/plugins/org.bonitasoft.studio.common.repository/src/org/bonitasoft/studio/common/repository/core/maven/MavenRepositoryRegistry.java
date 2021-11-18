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
package org.bonitasoft.studio.common.repository.core.maven;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.maven.artifact.repository.ArtifactRepository;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.internal.repository.RepositoryRegistry;
import org.eclipse.m2e.core.repository.IRepository;
import org.eclipse.m2e.core.repository.IRepositoryRegistry;

@Creatable
public class MavenRepositoryRegistry {

    private MavenProjectHelper mavenProjectHelper = new MavenProjectHelper();

    public List<IRepository> getGlobalRepositories() {
        return getRepositoryRegistry().getRepositories(IRepositoryRegistry.SCOPE_SETTINGS);
    }

    public List<IRepository> getProjectRepositories(IProject project) {
        IRepositoryRegistry repositoryRegistry = getRepositoryRegistry();
        List<IRepository> projectRepositories = repositoryRegistry
                .getRepositories(IRepositoryRegistry.SCOPE_PROJECT);

        try {
            Set<String> projectRepositoriesUrls = mavenProjectHelper
                    .getProjectMavenRepositories(project)
                    .stream()
                    .map(ArtifactRepository::getUrl)
                    .collect(Collectors.toSet());
            return projectRepositories.stream()
                    .filter(r -> projectRepositoriesUrls.contains(r.getUrl()))
                    .collect(Collectors.toList());
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
        }
        return Collections.emptyList();
    }

    protected IRepositoryRegistry getRepositoryRegistry() {
        return MavenPlugin.getRepositoryRegistry();
    }
    
    public IRunnableWithProgress updateRegistry() {
        return monitor -> {
            try {
                ((RepositoryRegistry) getRepositoryRegistry()).updateRegistry(monitor);
            } catch (CoreException e) {
                throw new InvocationTargetException(e);
            }
        };
    }

}
