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
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.migration.BonitaProjectMigrator;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.internal.IMavenToolbox;
import org.eclipse.m2e.core.internal.preferences.MavenPreferenceConstants;
import org.eclipse.m2e.core.project.IProjectConfigurationManager;
import org.eclipse.m2e.core.project.LocalProjectScanner;
import org.eclipse.m2e.core.project.MavenProjectInfo;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;
import org.eclipse.m2e.core.ui.internal.M2EUIPluginActivator;

public class ImportBonitaProjectOperation implements IWorkspaceRunnable {

    private MigrationReport report = MigrationReport.emptyReport();
    private File projectRoot;
    private IProjectConfigurationManager projectConfigurationManager;
    private String projectId;
    private boolean isNewProject;

    public ImportBonitaProjectOperation(File projectRoot) {
        this.projectRoot = projectRoot;
        this.projectConfigurationManager = MavenPlugin.getProjectConfigurationManager();
    }

    public ImportBonitaProjectOperation newProject() {
        this.isNewProject = true;
        return this;
    }

    @Override
    public void run(final IProgressMonitor monitor) throws CoreException {
        if (projectRoot == null || !projectRoot.exists()
                || (!isNewProject && !projectRoot.toPath().resolve(".project").toFile().exists())) {
            throw new CoreException(Status.error(String.format("No project found at %s", projectRoot)));
        }
        if (!isNewProject) {
            report = new BonitaProjectMigrator(projectRoot.toPath()).run(monitor);
        }
        var appPomFile = projectRoot.toPath().resolve("app").resolve("pom.xml").toFile();
        var mavenModel = MavenProjectHelper.readModel(appPomFile);
        projectId = mavenModel.getArtifactId();
        IProject targetProject = ResourcesPlugin.getWorkspace().getRoot().getProject(projectId);
        if (targetProject.exists()) {
            throw new CoreException(
                    Status.error(String.format("A project with id %s already exists in the workspace.", projectId)));
        }

        if (!isNewProject) {
            removeUidProvidedWidgets();
        }
        var projectInWs = ResourcesPlugin.getWorkspace().getRoot().getLocation().append(projectId).toFile();
        if (!Objects.equals(projectRoot.toPath(), projectInWs.toPath())) {
            try {
                FileUtil.copyDirectory(projectRoot.toPath(), projectInWs.toPath());
            } catch (IOException e) {
                throw new CoreException(Status.error("Failed to copy project in workspace.", e));
            }
            // Remove source project when present in workspace
            if (Objects.equals(projectRoot.toPath().getParent(), projectInWs.toPath().getParent())) {
                FileUtil.deleteDir(projectRoot);
            }
        }

        var pomFile = projectInWs.toPath().resolve("pom.xml").toFile();
        var localProjectScanner = new LocalProjectScanner(
                List.of(pomFile.getParentFile().getAbsolutePath()),
                false,
                MavenPlugin.getMavenModelManager());
        try {
            localProjectScanner.run(monitor);
        } catch (InterruptedException e) {
            throw new CoreException(Status.error("Failed to scan local projects", e));
        }
        var store = M2EUIPluginActivator.getDefault().getPreferenceStore();
        store.setValue(MavenPreferenceConstants.P_AUTO_UPDATE_CONFIGURATION, false);
        try {
            for (var mavenProject : flatten(localProjectScanner.getProjects()).stream()
                    .sorted((p1, p2) -> bdmProjects().test(p1) ? -1 : 1).toList()) {
                var importConfiguration = createProjectImportConfiguration(mavenProject);
                if (importConfiguration != null) {
                    projectConfigurationManager.importProjects(List.of(mavenProject),
                            importConfiguration,
                            monitor);
                }
            }
        } finally {
            store.setValue(MavenPreferenceConstants.P_AUTO_UPDATE_CONFIGURATION, true);
        }
    }

    private ProjectImportConfiguration createProjectImportConfiguration(MavenProjectInfo mavenProject)
            throws CoreException {
        try (var in = Files.newInputStream(mavenProject.getPomFile().toPath())) {
            var projectImportConfiguration = new ProjectImportConfiguration();
            var model = IMavenToolbox.of(MavenPlugin.getMaven()).readModel(in);
            if (projectId.equals(model.getArtifactId())) {
                projectImportConfiguration.setProjectNameTemplate(projectId + "-app");
            } else if ((projectId + "-parent").equals(model.getArtifactId())) {
                projectImportConfiguration.setProjectNameTemplate(projectId);
            } else {
                projectImportConfiguration.setProjectNameTemplate("[artifactId]");
            }
            return projectImportConfiguration;
        } catch (IOException e) {
            BonitaStudioLog.error(e);
            return null;
        }
    }

    protected void removeUidProvidedWidgets() throws CoreException {
        var widgetsFolder = projectRoot.toPath().resolve("app").resolve("web_widgets");
        if (Files.exists(widgetsFolder)) {
            try {
                Files.find(widgetsFolder,
                        1,
                        // Provided widget folder matcher
                        (path, attr) -> path.getFileName().toString().startsWith("pb") && Files.isDirectory(path))
                        .forEach(widget -> {
                            try {
                                FileUtil.deleteDir(widget);
                            } catch (IOException e) {
                                throw new UncheckedIOException(e);
                            }
                        });
            } catch (IOException | UncheckedIOException e) {
                throw new CoreException(Status.error("Failed to delete provided widgets.", e));
            }
        }
    }

    private Predicate<? super MavenProjectInfo> bdmProjects() {
        return mpf -> mpf.getPomFile().toPath().toString().contains("/model/pom.xml")
                || mpf.getPomFile().toPath().toString().contains("/dao-client/pom.xml")
                || mpf.getPomFile().toPath().toString().contains("bdm/pom.xml");
    }

    public BonitaProject getBonitaProject() {
        return BonitaProject.create(projectId);
    }

    private static Collection<MavenProjectInfo> flatten(Collection<MavenProjectInfo> projects) {
        var flatList = new ArrayList<MavenProjectInfo>();
        for (MavenProjectInfo t : projects) {
            flatList.add(t);
            if (t.getProjects() != null) {
                flatList.addAll(flatten(t.getProjects()));
            }
        }
        return flatList;
    }

    public MigrationReport getReport() {
        return report;
    }

}
