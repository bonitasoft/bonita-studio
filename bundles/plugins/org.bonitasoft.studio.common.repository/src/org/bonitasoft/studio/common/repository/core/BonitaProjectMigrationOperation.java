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
import java.util.Objects;
import java.util.Set;

import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

/**
 * @author Romain Bioteau
 */
public class BonitaProjectMigrationOperation implements IWorkspaceRunnable {

    private final IProject project;
    private final Set<String> builders = new HashSet<>();
    private final List<String> natures = new ArrayList<>();
    private MavenProjectHelper mavenProjectHelper = new MavenProjectHelper();

    public BonitaProjectMigrationOperation(final IProject project) {
        this.project = project;
    }

    @Override
    public void run(final IProgressMonitor monitor) throws CoreException {
        var projectName = project.getName();
        //In order to force the reorder of natures we must reset description
        String currentVersion = project.getDescription().getComment();
        if (ProductVersion.canBeMigrated(currentVersion)) {
            project.setDescription(new ProjectDescriptionBuilder()
                    .withProjectName(project.getName())
                    .withComment(ProductVersion.CURRENT_VERSION)
                    .havingNatures(natures)
                    .havingBuilders(builders).build(),
                    IResource.FORCE,
                    monitor);
        }
        ProjectMetadata projectMetadata = ProjectMetadata.read(project);
        IProjectDescription description = project.getDescription();
        if (!Objects.equals(projectName, description.getName())) {
            description.setName(projectName);
            ((org.eclipse.core.internal.resources.Project) project).writeDescription(description, IResource.FORCE, true,
                    false);
        }
        if (!Objects.equals(projectMetadata.getName(), projectName)) {
            Model model = mavenProjectHelper.getMavenModel(project);
            model.setName(projectName);
            mavenProjectHelper.saveModel(project, model);
        }
    }

    public BonitaProjectMigrationOperation addBuilder(final String builderId) {
        builders.add(builderId);
        return this;
    }

    public BonitaProjectMigrationOperation addNature(final String natureId) {
        natures.add(natureId);
        return this;
    }
}
