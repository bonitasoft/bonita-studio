/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.repository.Repository;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Romain Bioteau
 */
public class BonitaBPMProjectMigrationOperation implements IWorkspaceRunnable {

    private final IProject project;
    private final Set<String> builders = new HashSet<>();
    private final List<String> natures = new ArrayList<>();
    private final Repository repository;

    public BonitaBPMProjectMigrationOperation(final IProject project, final Repository repository) {
        this.project = project;
        this.repository = repository;
    }

    @Override
    public void run(final IProgressMonitor monitor) throws CoreException {
        //In order to force the reorder of natures we must reset description
        project.setDescription(
                new ProjectDescriptionBuilder().withProjectName(project.getName())
                        .withComment(ProductVersion.CURRENT_VERSION).build(),
                IResource.FORCE,
                monitor);
        project.setDescription(new ProjectDescriptionBuilder()
                .withProjectName(project.getName())
                .withComment(ProductVersion.CURRENT_VERSION)
                .havingNatures(natures)
                .havingBuilders(builders).build(),
                IResource.FORCE,
                monitor);

        final ProjectClasspathFactory bonitaBPMProjectClasspath = new ProjectClasspathFactory();
        bonitaBPMProjectClasspath.delete(repository, monitor);
        bonitaBPMProjectClasspath.create(repository, monitor);
    }

    public BonitaBPMProjectMigrationOperation addBuilder(final String builderId) {
        builders.add(builderId);
        return this;
    }

    public BonitaBPMProjectMigrationOperation addNature(final String natureId) {
        natures.add(natureId);
        return this;
    }
}
