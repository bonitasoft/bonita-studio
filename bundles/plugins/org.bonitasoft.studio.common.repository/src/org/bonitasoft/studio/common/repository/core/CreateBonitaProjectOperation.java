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
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.BonitaProjectNature;
import org.bonitasoft.studio.common.repository.core.maven.BonitaProjectBuilder;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.eclipse.m2e.core.project.IMavenProjectImportResult;
import org.eclipse.m2e.core.project.MavenProjectInfo;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;

public class CreateBonitaProjectOperation implements IWorkspaceRunnable {

    private static final String APP_MODULE = "app";
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
        project = workspace.getRoot().getProject(projectId);
        if (project.exists()) {
            throw new CoreException(new Status(IStatus.ERROR, getClass(),
                    String.format("%s project already exists.", metadata.getName())));
        }
        project.create(AbstractRepository.NULL_PROGRESS_MONITOR);
        project.open(AbstractRepository.NULL_PROGRESS_MONITOR);
        project.setDescription(
                new ProjectDescriptionBuilder()
                        .withProjectName(project.getName())
                        .withComment(ProductVersion.CURRENT_VERSION)
                        .havingNatures(rootProjectNatures())
                        .havingBuilders(rootProjectBuilders())
                        .build(),
                AbstractRepository.NULL_PROGRESS_MONITOR);
        createDefaultPomFile(project, metadata);
    
        if (metadata.getIsMultiModule()) {
            IFolder appModule = project.getFolder(APP_MODULE);
            appModule.create(true, true, monitor);
            var pomFile = appModule.getFile(IMavenConstants.POM_FILE_NAME);
            var mavenProjectHelper = new MavenProjectHelper();
            Model appModel = newProjectBuilder(metadata, new MavenAppModuleModelBuilder()).toMavenModel();
            mavenProjectHelper.saveModel(pomFile, appModel, new NullProgressMonitor());
            IProject appProject = importAppModuleProject(pomFile, appModel.getArtifactId() + "-app", monitor);
            appProject.setDescription(new ProjectDescriptionBuilder()
                    .withProjectName(appProject.getName())
                    .withComment(ProductVersion.CURRENT_VERSION)
                    .havingNatures(appProjectNatures())
                    .havingBuilders(appProjectBuilders())
                    .build(), monitor);

        }
    }

    private IProject importAppModuleProject(IFile pomFile, String nameTemplate, IProgressMonitor monitor)
            throws CoreException {
        var projectImportConfiguration = new ProjectImportConfiguration();
        projectImportConfiguration.setProjectNameTemplate(nameTemplate);
        var projectInfo = new MavenProjectInfo(null, pomFile.getLocation().toFile(), null, null);
        List<IMavenProjectImportResult> projects = MavenPlugin.getProjectConfigurationManager().importProjects(
                List.of(projectInfo),
                projectImportConfiguration, monitor);
        IProject appProject = projects.get(0).getProject();
        return appProject;
    }

    private Collection<String> rootProjectNatures() {
        var natures = new ArrayList<String>();
        natures.add(IMavenConstants.NATURE_ID);
        if (!metadata.getIsMultiModule()) {
            natures.add(BonitaProjectNature.NATURE_ID);
            natures.add("org.eclipse.jdt.groovy.core.groovyNature");
            natures.add(JavaCore.NATURE_ID);
        }
        return natures;
    }

    private Collection<String> rootProjectBuilders() {
        var builders = new ArrayList<String>();
        builders.add(IMavenConstants.BUILDER_ID);
        if (!metadata.getIsMultiModule()) {
            builders.add(BonitaProjectBuilder.ID);
            builders.add(JavaCore.BUILDER_ID);
        }
        return builders;
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
        var minorVersionString = ProductVersion.toMinorVersionString(ProductVersion.minorVersion(bonitaRuntimeVersion));
        if (!Objects.equals(minorVersionString, ProductVersion.minorVersion())) {
            bonitaRuntimeVersion = ProductVersion.BONITA_RUNTIME_VERSION;
        }
        mavenProjectBuilder.setBonitaVersion(bonitaRuntimeVersion);
        mavenProjectBuilder.setVersion(metadata.getVersion());
        mavenProjectBuilder.setDescription(metadata.getDescription());
        return mavenProjectBuilder;
    }

    public static void createDefaultPomFile(IProject project,
            ProjectMetadata metadata) throws CoreException {
        var mavenProjectHelper = new MavenProjectHelper();
        IFile pomFile = project.getFile("pom.xml");
        if (pomFile.exists()) {
            backupExistingPomFile(project, metadata, mavenProjectHelper, pomFile);
        }
        File pom = pomFile.getLocation().toFile();
        try {
            if (!pom.exists() && !pom.createNewFile()) {
                throw new CoreException(new Status(IStatus.ERROR, CreateBonitaProjectOperation.class,
                        "Failed to create pom.xml file.",
                        new IOException("Failed to create pom.xml file.")));
            }
        } catch (IOException e) {
            throw new CoreException(
                    new Status(IStatus.ERROR, CreateBonitaProjectOperation.class, "Failed to create pom.xml file.", e));
        }
        mavenProjectHelper.saveModel(project, newProjectBuilder(metadata,
                metadata.getIsMultiModule() ? new MavenParentProjectModelBuilder() : new MavenProjectModelBuilder())
                        .toMavenModel(),
                true,
                new NullProgressMonitor());
    }

    public static void backupExistingPomFile(IProject project,
            ProjectMetadata metadata,
            MavenProjectHelper mavenProjectHelper,
            IFile pomFile) throws CoreException {
        Model model = mavenProjectHelper.getMavenModel(project);
        metadata.setGroupId(model.getGroupId());
        metadata.setArtifactId(model.getArtifactId());
        metadata.setVersion(model.getVersion());
        if (model.getName() != null && !model.getName().isBlank()) {
            metadata.setName(model.getName());
        }
        if (model.getDescription() != null && !model.getDescription().isBlank()) {
            metadata.setDescription(model.getDescription());
        }
        String backupFileName = "pom.xml.old";
        IFile backupFile = project.getFile(backupFileName);
        while (backupFile.exists()) {
            backupFileName = nextBackupFileName(backupFileName);
            backupFile = project.getFile(nextBackupFileName(backupFileName));
        }
        pomFile.copy(backupFile.getProjectRelativePath(), true, new NullProgressMonitor());
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
