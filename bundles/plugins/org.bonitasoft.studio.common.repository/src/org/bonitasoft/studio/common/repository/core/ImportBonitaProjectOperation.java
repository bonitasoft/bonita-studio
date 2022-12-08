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
package org.bonitasoft.studio.common.repository.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.migration.BonitaProjectMigrator;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.project.IMavenProjectImportResult;
import org.eclipse.m2e.core.project.IProjectConfigurationManager;
import org.eclipse.m2e.core.project.LocalProjectScanner;
import org.eclipse.m2e.core.project.MavenProjectInfo;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;

public class ImportBonitaProjectOperation implements IWorkspaceRunnable {

    private MigrationReport report = MigrationReport.emptyReport();
    private File projectRoot;
    private IProject project;
    private IProjectConfigurationManager projectConfigurationManager;

    public ImportBonitaProjectOperation(File projectRoot) {
        this.projectRoot = projectRoot;
        this.projectConfigurationManager = MavenPlugin.getProjectConfigurationManager();
    }

    @Override
    public void run(final IProgressMonitor monitor) throws CoreException {
        if (projectRoot == null || !projectRoot.exists()
                || !projectRoot.toPath().resolve(".project").toFile().exists()) {
            throw new CoreException(Status.error(String.format("No project found at %s", projectRoot)));
        }
        report = new BonitaProjectMigrator(projectRoot.toPath()).run(monitor);
        var originalPomFile = projectRoot.toPath().resolve("pom.xml").toFile();
        var mavenModel = MavenProjectHelper.readModel(originalPomFile);
        var metadata = ProjectMetadata.read(mavenModel);
        var modules = mavenModel.getModules().stream()
                .map(module -> originalPomFile.getParentFile().toPath().resolve(module).resolve("pom.xml").toFile())
                .filter(File::exists)
                .map(f -> new MavenProjectInfo(null, f, null, null))
                .collect(Collectors.toList());
        var projectId = projectId(modules, metadata.getArtifactId());
        IProject targetProject = ResourcesPlugin.getWorkspace().getRoot().getProject(projectId);
        if (targetProject.exists()) {
            throw new CoreException(
                    Status.error(String.format("A project with id %s already exists in the workspace.", projectId)));
        }
        var projectInWs = ResourcesPlugin.getWorkspace().getRoot().getLocation().append(projectId).toFile();
        if (!Objects.equals(projectRoot.toPath(), projectInWs.toPath())) {
            try {
                FileUtil.copyDirectory(projectRoot.toPath(), projectInWs.toPath());
            } catch (IOException e) {
                throw new CoreException(Status.error("Failed to copy project in workspace.", e));
            }
        }

        var pomFile = projectInWs.toPath().resolve("pom.xml").toFile();
        var localProjectScanner = new LocalProjectScanner(
                ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile(),
                pomFile.getParentFile().getAbsolutePath(),
                false,
                MavenPlugin.getMavenModelManager());

        try {
            localProjectScanner.run(monitor);
        } catch (InterruptedException e1) {
            BonitaStudioLog.error(e1);
        }
        modules = mavenModel.getModules().stream()
                .map(module -> pomFile.getParentFile().toPath().resolve(module).resolve("pom.xml").toFile())
                .filter(File::exists)
                .map(f -> new MavenProjectInfo(null, f, null, null))
                .collect(Collectors.toList());

        var projectImportConfiguration = new BonitaProjectImportConfiguration(projectId);
        var importResult = projectConfigurationManager.importProjects(
                flatten(localProjectScanner.getProjects()),
                projectImportConfiguration, monitor);

        project = importResult.stream()
                .map(IMavenProjectImportResult::getProject)
                .filter(Objects::nonNull)
                .filter(p -> Objects.equals(p.getName(), projectId + "-app"))
                .findFirst()
                .orElseThrow();
    }

    private static Collection<MavenProjectInfo> flatten(Collection<MavenProjectInfo> projects) {
        var flatList = new ArrayList<MavenProjectInfo>();
        for (MavenProjectInfo t : projects) {
            flatList.add(t);
            if (t.getProjects() != null) {
                flatList.addAll((List<MavenProjectInfo>) flatten(t.getProjects()));
            }
        }
        return flatList;
    }

    private String projectId(List<MavenProjectInfo> modules, String artifactId) {
        if (!modules.isEmpty() && artifactId.endsWith("-parent")) {
            return artifactId.substring(0, artifactId.length() - "-parent".length());
        }
        return artifactId;
    }

    public MigrationReport getReport() {
        return report;
    }

    public IProject getProject() {
        return project;
    }

    class BonitaProjectImportConfiguration extends ProjectImportConfiguration {

        private String projectId;

        public BonitaProjectImportConfiguration(String projectId) {
            this.projectId = projectId;
        }

        @Override
        public String getProjectName(Model model) {
            if (projectId.equals(model.getArtifactId())) {
                setProjectNameTemplate(projectId + "-app");
            } else if ((projectId + "-parent").equals(model.getArtifactId())) {
                setProjectNameTemplate(projectId);
            } else {
                setProjectNameTemplate("[artifactId]");
            }
            return super.getProjectName(model);
        }

    }

}
