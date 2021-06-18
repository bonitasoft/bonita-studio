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
package org.bonitasoft.studio.tests.util;

import static java.util.function.Predicate.not;

import java.util.stream.Collectors;

import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.RemoveDependencyOperation;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectDefaultConfiguration;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.configuration.repository.EnvironmentFileStore;
import org.bonitasoft.studio.identity.organization.repository.OrganizationFileStore;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;

public class ProjectUtil {

    /**
     * Removes all current project user artifacts and extensions
     * @throws CoreException 
     */
    public static void cleanProject() throws CoreException {
        var project = RepositoryManager.getInstance().getAccessor().getCurrentRepository();
        project.getAllShareableStores().stream()
                .flatMap(s -> s.getChildren().stream())
                .filter(not(OrganizationFileStore.class::isInstance))
                .filter(not(EnvironmentFileStore.class::isInstance))
                .filter(IRepositoryFileStore::canBeDeleted)
                .forEach(IRepositoryFileStore::delete);
        removeUserExtensions();
    }
    
    public static void removeUserExtensions() throws CoreException {
        IProject project =  RepositoryManager.getInstance().getAccessor().getCurrentRepository().getProject();
        Model mavenModel = new MavenProjectHelper().getMavenModel(project);
        var dependenciesToRemove = mavenModel.getDependencies()
                .stream()
                .filter(not(ProjectDefaultConfiguration::isInternalDependency))
                .collect(Collectors.toList());
        if (!dependenciesToRemove.isEmpty()) {
            new RemoveDependencyOperation(dependenciesToRemove).run(new NullProgressMonitor());
        }
    }

}
