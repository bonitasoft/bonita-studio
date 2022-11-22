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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.migration.BonitaProjectMigrator;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.bonitasoft.studio.common.ui.PlatformUtil;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.project.IMavenProjectImportResult;
import org.eclipse.m2e.core.project.IProjectConfigurationManager;
import org.eclipse.m2e.core.project.MavenProjectInfo;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;

public class ImportBonitaProjectOperation implements IWorkspaceRunnable {

    // AVOID IMPORTING NO SUPPORTED MODULES
    private static final Set<String> MODULES_TO_IMPORT = Set.of("app");
    private final Set<String> builders = new HashSet<>();
    private final List<String> natures = new ArrayList<>();
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
        if (projectRoot == null || !projectRoot.exists() || !projectRoot.toPath().resolve("pom.xml").toFile().exists()) {
            throw new CoreException(Status.error(String.format("No project found at %s", projectRoot)));
        }
        var originalPomFile = projectRoot.toPath().resolve("pom.xml").toFile();
        var mavenModel = MavenProjectHelper.readModel(originalPomFile);
        var metadata = ProjectMetadata.read(mavenModel);
        var modules = mavenModel.getModules().stream()
                .map(module -> originalPomFile.getParentFile().toPath().resolve(module).resolve("pom.xml").toFile())
                .filter(File::exists)
                .map(f -> new MavenProjectInfo(null, f, null, null))
                .collect(Collectors.toList());
        metadata.setIsMultiModule(!modules.isEmpty() && modules.stream().anyMatch(this::shouldImportModule));
        var projectId = projectId(modules, metadata.getArtifactId());
        IProject targetProject = ResourcesPlugin.getWorkspace().getRoot().getProject(projectId);
        if(targetProject.exists()) {
            throw new CoreException(Status.error(String.format("A project with id %s already exists in the workspace.", projectId)));
        }
        File projectInWs = ResourcesPlugin.getWorkspace().getRoot().getLocation().append(projectId).toFile();
        try {
            copyDirectory(projectRoot.getAbsolutePath(), projectInWs.getAbsolutePath());
        } catch (IOException e) {
            throw new CoreException(Status.error("Failed to copy project in workspace.",e));
        }
        var pomFile = projectInWs.toPath().resolve("pom.xml").toFile();
        modules = mavenModel.getModules().stream()
                .map(module -> pomFile.getParentFile().toPath().resolve(module).resolve("pom.xml").toFile())
                .filter(File::exists)
                .map(f -> new MavenProjectInfo(null, f, null, null))
                .collect(Collectors.toList());
        if (modules.isEmpty() || modules.stream().noneMatch(this::shouldImportModule)) {
            var projectImportConfiguration = new ProjectImportConfiguration();
            projectImportConfiguration.setProjectNameTemplate(projectId);
            var results = projectConfigurationManager.importProjects(
                    List.of(new MavenProjectInfo(null, pomFile, null, null)),
                    projectImportConfiguration, monitor);

            var importResult = results.get(0);
            project = importResult.getProject();
            report = new BonitaProjectMigrator(project).run(monitor);
            project.setDescription(
                    new ProjectDescriptionBuilder()
                            .withProjectName(project.getName())
                            .withComment(ProductVersion.CURRENT_VERSION)
                            .havingNatures(BonitaProject.NATRUES)
                            .havingBuilders(BonitaProject.BUILDERS)
                            .build(),
                    AbstractRepository.NULL_PROGRESS_MONITOR);
        } else {
            var results = new ArrayList<IMavenProjectImportResult>();

            // Import parent project
            var projectImportConfiguration = new ProjectImportConfiguration();
            projectImportConfiguration.setProjectNameTemplate(projectId);
            results.addAll(projectConfigurationManager.importProjects(
                    List.of(new MavenProjectInfo(null, pomFile, null, null)),
                    projectImportConfiguration, monitor));

            // Import modules projects
            for (var module : modules) {
                if (shouldImportModule(module)) {
                    var moduleMetadata = ProjectMetadata.read(module.getPomFile());
                    projectImportConfiguration = new ProjectImportConfiguration();
                    if (Objects.equals(projectId, moduleMetadata.getArtifactId())) {
                        projectImportConfiguration.setProjectNameTemplate(moduleMetadata.getProjectName());
                    }
                    results.addAll(projectConfigurationManager.importProjects(
                            List.of(module),
                            projectImportConfiguration, monitor));
                }
            }

            for (var res : results) {
                if (res.getProject() != null && res.getProject().getName().endsWith("-app")) {
                    project = res.getProject();
                    // TODO
                    //report = new BonitaProjectMigrator(project).run(monitor);
                    //                    project.setDescription(
                    //                            new ProjectDescriptionBuilder()
                    //                                    .withProjectName(project.getName())
                    //                                    .withComment(ProductVersion.CURRENT_VERSION)
                    //                                    .havingNatures(
                    //                                            List.of(BonitaProjectNature.NATURE_ID,
                    //                                                    JavaCore.NATURE_ID,
                    //                                                    IMavenConstants.NATURE_ID,
                    //                                                    "org.eclipse.jdt.groovy.core.groovyNature"))
                    //                                    .havingBuilders(List.of(BonitaProjectBuilder.ID,
                    //                                            JavaCore.BUILDER_ID,
                    //                                            IMavenConstants.BUILDER_ID))
                    //                                    .build(),
                    //                            AbstractRepository.NULL_PROGRESS_MONITOR);
                }
                //                else if (res.getProject() != null && res.getProject().getName().equals(projectId)) {
                //                    res.getProject().setDescription(
                //                            new ProjectDescriptionBuilder()
                //                                    .withProjectName(res.getProject().getName())
                //                                    .withComment(ProductVersion.CURRENT_VERSION)
                //                                    .havingNatures(List.of(IMavenConstants.NATURE_ID))
                //                                    .havingBuilders(List.of(IMavenConstants.BUILDER_ID))
                //                                    .build(),
                //                            AbstractRepository.NULL_PROGRESS_MONITOR);
                //                }
            }
        }
    }
    
    private static void copyDirectory(String sourceDirectoryLocation, String destinationDirectoryLocation) 
            throws IOException {
              Files.walk(Paths.get(sourceDirectoryLocation))
                .forEach(source -> {
                    Path destination = Paths.get(destinationDirectoryLocation, source.toString()
                      .substring(sourceDirectoryLocation.length()));
                    try {
                        Files.copy(source, destination);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
          }

    private boolean shouldImportModule(MavenProjectInfo module) {
        return MODULES_TO_IMPORT.contains(module.getPomFile().getParentFile().getName());
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

    public ImportBonitaProjectOperation addBuilder(final String builderId) {
        builders.add(builderId);
        return this;
    }

    public ImportBonitaProjectOperation addNature(final String natureId) {
        natures.add(natureId);
        return this;
    }

}
