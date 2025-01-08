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
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.model.AppProjectConfiguration;
import org.bonitasoft.studio.common.repository.core.migration.BonitaProjectMigrator;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.eclipse.m2e.core.internal.IMavenToolbox;
import org.eclipse.m2e.core.internal.preferences.MavenPreferenceConstants;
import org.eclipse.m2e.core.project.IProjectConfigurationManager;
import org.eclipse.m2e.core.project.LocalProjectScanner;
import org.eclipse.m2e.core.project.MavenProjectInfo;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;
import org.osgi.service.prefs.BackingStoreException;

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
        // We check for an homonym project first. In this case, we do not need to migrate and migration steps will confuse the 2 projects.
        String projectIdBeforeMigr = readProjectId();
        if (projectIdBeforeMigr != null
                && ResourcesPlugin.getWorkspace().getRoot().getProject(projectIdBeforeMigr).exists()) {
            throw new CoreException(
                    Status.error(String.format("A project with id %s already exists in the workspace.",
                            projectIdBeforeMigr)));
        }
        // Migrate the project to import.
        if (!isNewProject) {
            report = new BonitaProjectMigrator(projectRoot.toPath())
                    .run(monitor);
        }
        var generatedSourcesFolder = projectRoot.toPath().resolve(BonitaProject.APP_MODULE)
                .resolve(AppProjectConfiguration.GENERATED_GROOVY_SOURCES_FODLER);
        if (!Files.exists(generatedSourcesFolder)) {
            try {
                Files.createDirectories(generatedSourcesFolder);
            } catch (IOException e) {
                BonitaStudioLog.error(e);
            }
        }
        // Just in case a migration step changed the project id... check for homonym project again
        projectId = readProjectId();
        if (!Objects.equals(projectId, projectIdBeforeMigr)
                && ResourcesPlugin.getWorkspace().getRoot().getProject(projectId).exists()) {
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
        var store = DefaultScope.INSTANCE.getNode(IMavenConstants.PLUGIN_ID);
        var autoUpdate = store.getBoolean(MavenPreferenceConstants.P_AUTO_UPDATE_CONFIGURATION, true);
        setAutoUpdateConfiguration(store, false);
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
            setAutoUpdateConfiguration(store, autoUpdate);
        }
    }

    /**
     * Read the project id from the maven app module
     * 
     * @return artifactId of app module
     * @throws CoreException reading failure
     */
    private String readProjectId() throws CoreException {
        var appPomFile = projectRoot.toPath().resolve(BonitaProject.APP_MODULE).resolve("pom.xml").toFile();
        if(appPomFile.exists()) {
            var mavenModel = MavenProjectHelper.readModel(appPomFile);
            return mavenModel.getArtifactId();
        }
        var rootPomFile = projectRoot.toPath().resolve("pom.xml").toFile();
        if(rootPomFile.exists()) {
            var mavenModel = MavenProjectHelper.readModel(rootPomFile);
            return mavenModel.getArtifactId();
        }
        // Project cloned from version below 7.13.0
        return null;
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

    private void setAutoUpdateConfiguration(IEclipsePreferences store, boolean enanbled) {
        store.putBoolean(MavenPreferenceConstants.P_AUTO_UPDATE_CONFIGURATION, enanbled);
        try {
            store.sync();
        } catch (BackingStoreException e) {
            BonitaStudioLog.error(e);
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
