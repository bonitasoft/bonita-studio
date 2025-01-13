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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.BonitaProjectNature;
import org.bonitasoft.studio.common.repository.core.maven.BonitaProjectBuilder;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.m2e.core.internal.IMavenConstants;

public class CreateBonitaProjectOperation implements IWorkspaceRunnable {

    private IProject project;
    private final IWorkspace workspace;
    private final ProjectMetadata metadata;
    private final Set<String> builders = new HashSet<>();
    private final List<String> natures = new ArrayList<>();

    public CreateBonitaProjectOperation(IWorkspace workspace, ProjectMetadata metadata) {
        this.workspace = workspace;
        this.metadata = metadata;
    }

    @Override
    public void run(final IProgressMonitor monitor) throws CoreException {
        var projectId = metadata.getArtifactId();
        if (Strings.isNullOrEmpty(projectId)) {
            projectId = ProjectMetadata.toArtifactId(metadata.getName());
        }
        if (workspace.getRoot().getProject(projectId).exists()) {
            throw new CoreException(new Status(IStatus.ERROR, getClass(),
                    String.format("%s project already exists.", metadata.getName())));
        }
        var projectRoot = workspace.getRoot().getLocation().toFile().toPath().resolve(projectId);
        var appModule = projectRoot.resolve(BonitaProject.APP_MODULE);
        try {
            Files.createDirectories(appModule);
        } catch (IOException e) {
            throw new CoreException(Status.error("Failed to create folder.", e));
        }
        createDefaultPomFile(projectRoot, metadata);

        var pomFile = appModule.resolve(IMavenConstants.POM_FILE_NAME);
        MavenAppModuleModelBuilder mavenProjectBuilder = new MavenAppModuleModelBuilder();
        mavenProjectBuilder.setIncludeAdminApp(metadata.includeAdminApp());
        Model appModel = newProjectBuilder(metadata, mavenProjectBuilder).toMavenModel();
        MavenProjectHelper.saveModel(pomFile, appModel);

        var importBonitaProjectOperation = new ImportBonitaProjectOperation(projectRoot.toFile()).newProject();
        importBonitaProjectOperation.run(monitor);
        var bonitaProject = importBonitaProjectOperation.getBonitaProject();
        project = bonitaProject.getParentProject();
        bonitaProject.getParentProject().setDescription(
                new ProjectDescriptionBuilder()
                        .withProjectName(project.getName())
                        .withComment(ProductVersion.CURRENT_VERSION)
                        .havingNatures(List.of(IMavenConstants.NATURE_ID))
                        .havingBuilders(List.of(IMavenConstants.BUILDER_ID))
                        .build(),
                monitor);
        IProject appProject = bonitaProject.getAppProject();
        appProject.setDescription(new ProjectDescriptionBuilder()
                .withProjectName(appProject.getName())
                .withComment(ProductVersion.CURRENT_VERSION)
                .havingNatures(appProjectNatures())
                .havingBuilders(appProjectBuilders())
                .build(), monitor);
    }

    private Collection<String> appProjectNatures() {
        return List.of(BonitaProjectNature.NATURE_ID,
                JavaCore.NATURE_ID,
                IMavenConstants.NATURE_ID,
                "org.eclipse.jdt.groovy.core.groovyNature");
    }

    private Collection<String> appProjectBuilders() {
        return List.of(BonitaProjectBuilder.ID,
                JavaCore.BUILDER_ID,
                IMavenConstants.BUILDER_ID);
    }

    public static MavenModelBuilder newProjectBuilder(ProjectMetadata metadata, MavenModelBuilder mavenProjectBuilder) {
        mavenProjectBuilder.setDisplayName(metadata.getName());
        String artifactId = metadata.getArtifactId();
        if (Strings.isNullOrEmpty(artifactId)) {
            artifactId = ProjectMetadata.toArtifactId(metadata.getName());
        }
        mavenProjectBuilder.setArtifactId(artifactId);
        mavenProjectBuilder.setGroupId(metadata.getGroupId());
        String bonitaRuntimeVersion = metadata.getBonitaRuntimeVersion();
        var minorVersionString = bonitaRuntimeVersion == null ? null
                : ProductVersion.toMinorVersionString(ProductVersion.minorVersion(bonitaRuntimeVersion));
        if (!Objects.equals(minorVersionString, ProductVersion.minorVersion())) {
            bonitaRuntimeVersion = ProductVersion.BONITA_RUNTIME_VERSION;
        }
        mavenProjectBuilder.setBonitaVersion(bonitaRuntimeVersion);
        mavenProjectBuilder.setVersion(metadata.getVersion());
        mavenProjectBuilder.setDescription(metadata.getDescription());
        return mavenProjectBuilder;
    }

    public static void createDefaultPomFile(Path project,
            ProjectMetadata metadata) throws CoreException {
        var pomFile = project.resolve("pom.xml");
        if (Files.exists(pomFile)) {
            backupExistingPomFile(pomFile, metadata);
        }
        var builder = newProjectBuilder(metadata,
                new MavenParentProjectModelBuilder(metadata.isUseSnapshotRepository()));
        MavenProjectHelper.saveModel(project.resolve("pom.xml"), builder.toMavenModel());
    }

    public static void backupExistingPomFile(Path pomFile,
            ProjectMetadata metadata) throws CoreException {
        var model = MavenProjectHelper.readModel(pomFile.toFile());
        metadata.setGroupId(model.getGroupId());
        metadata.setArtifactId(model.getArtifactId());
        metadata.setVersion(model.getVersion());
        if (model.getName() != null && !model.getName().isBlank()) {
            metadata.setName(model.getName());
        } else {
            metadata.setName(model.getArtifactId());
        }
        if (model.getDescription() != null && !model.getDescription().isBlank()) {
            metadata.setDescription(model.getDescription());
        }
        String backupFileName = "pom.xml.old";
        var project = pomFile.getParent();
        var backupFile = project.resolve(backupFileName);
        while (Files.exists(backupFile)) {
            backupFileName = nextBackupFileName(backupFileName);
            backupFile = project.resolve(nextBackupFileName(backupFileName));
        }
        try {
            Files.copy(pomFile, backupFile);
            BonitaStudioLog.info("Existing pom.xml has been backed up to " + backupFileName);
        } catch (IOException e) {
            throw new CoreException(Status.error("Failed to backup existing pom.xml", e));
        }
    }

    private static String nextBackupFileName(String backupFileName) {
        if (backupFileName.endsWith(".old")) {
            return backupFileName + ".1";
        } else {
            String[] split = backupFileName.split("\\.");
            String index = split[split.length - 1];
            return "pom.xml.old." + (Integer.valueOf(index) + 1);
        }
    }

    public CreateBonitaProjectOperation addBuilder(final String builderId) {
        builders.add(builderId);
        return this;
    }

    public CreateBonitaProjectOperation addNature(final String natureId) {
        natures.add(natureId);
        return this;
    }

    public IProject getProject() {
        return project;
    }

}
