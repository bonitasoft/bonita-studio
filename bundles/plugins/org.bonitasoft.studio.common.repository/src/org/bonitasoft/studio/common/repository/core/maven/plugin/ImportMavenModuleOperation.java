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
package org.bonitasoft.studio.common.repository.core.maven.plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.project.IProjectConfigurationManager;
import org.eclipse.m2e.core.project.MavenProjectInfo;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;

public class ImportMavenModuleOperation implements IWorkspaceRunnable {

    private File projectRoot;
    private IProjectConfigurationManager projectConfigurationManager;

    public ImportMavenModuleOperation(File projectRoot) {
        this.projectRoot = projectRoot;
        this.projectConfigurationManager = MavenPlugin.getProjectConfigurationManager();
    }

    @Override
    public void run(final IProgressMonitor monitor) throws CoreException {
        if (projectRoot == null || !projectRoot.exists()
                || !projectRoot.toPath().resolve("pom.xml").toFile().exists()) {
            throw new CoreException(Status.error(String.format("No project found at %s", projectRoot)));
        }
        var pomFile = projectRoot.toPath().resolve("pom.xml").toFile();
        var mavenModel = MavenProjectHelper.readModel(pomFile);
        IProject targetProject = ResourcesPlugin.getWorkspace().getRoot().getProject(mavenModel.getArtifactId());
        if (targetProject.exists()) {
            throw new CoreException(Status.error(
                    String.format("A project named %s already exists in the workspace.", mavenModel.getArtifactId())));
        }
        var projectsToImport = new ArrayList<MavenProjectInfo>();
        projectsToImport.add(new MavenProjectInfo(null, pomFile, null, null));
        var modules = mavenModel.getModules().stream()
                .map(module -> pomFile.getParentFile().toPath().resolve(module).resolve("pom.xml").toFile())
                .filter(File::exists)
                .map(f -> new MavenProjectInfo(null, f, null, null))
                .collect(Collectors.toList());
        projectsToImport.addAll(modules);
        var projectImportConfiguration = new ProjectImportConfiguration();
        projectImportConfiguration.setProjectNameTemplate("[artifactId]");
        projectConfigurationManager.importProjects(
                projectsToImport,
                projectImportConfiguration, monitor);
    }


}
