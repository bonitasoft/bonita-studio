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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;

public class CreateBonitaProjectOperation implements IWorkspaceRunnable {

    private IProject project;
    private final IWorkspace workspace;
    private final ProjectMetadata metadata;
    private final Set<String> builders = new HashSet<>();
    private final List<String> natures = new ArrayList<>();

    public CreateBonitaProjectOperation(final IWorkspace workspace, final ProjectMetadata metadata) {
        this.workspace = workspace;
        this.metadata = metadata;
    }

    @Override
    public void run(final IProgressMonitor monitor) throws CoreException {
        project = workspace.getRoot().getProject(metadata.getName());
        if (project.exists()) {
            throw new CoreException(new Status(IStatus.ERROR, getClass(),
                    String.format("%s project already exists.", metadata.getName())));
        }
        project.create(AbstractRepository.NULL_PROGRESS_MONITOR);
        project.open(AbstractRepository.NULL_PROGRESS_MONITOR);
        MavenProjectModelBuilder mavenProjectBuilder = newProjectBuilder(metadata);
        createDefaultPomFile(project, mavenProjectBuilder, monitor);
        project.setDescription(
                new ProjectDescriptionBuilder()
                        .withProjectName(project.getName())
                        .withComment(ProductVersion.CURRENT_VERSION)
                        .havingNatures(natures)
                        .havingBuilders(builders)
                        .build(),
                        AbstractRepository.NULL_PROGRESS_MONITOR);
    }

    public static MavenProjectModelBuilder newProjectBuilder(ProjectMetadata metadata) {
        MavenProjectModelBuilder mavenProjectBuilder = new MavenProjectModelBuilder();
        mavenProjectBuilder.setDisplayName(metadata.getName());
        mavenProjectBuilder.setArtifactId(metadata.getArtifactId());
        mavenProjectBuilder.setGroupId(metadata.getGroupId());
        mavenProjectBuilder.setBonitaVersion(ProductVersion.mavenVersion());
        mavenProjectBuilder.setVersion(metadata.getVersion());
        mavenProjectBuilder.setDescription(metadata.getDescription());
        return mavenProjectBuilder;
    }

    public static void createDefaultPomFile(IProject project,
            MavenProjectModelBuilder mavenProjectBuilder,
            IProgressMonitor monitor) throws CoreException {
        MavenProjectHelper mavenProjectHelper = new MavenProjectHelper();
        IFile pomFile = project.getFile("pom.xml");
        if (pomFile.exists()) {
            Model model = mavenProjectHelper.getMavenModel(project);
            mavenProjectBuilder.setGroupId(model.getGroupId());
            mavenProjectBuilder.setArtifactId(model.getArtifactId());
            mavenProjectBuilder.setVersion(model.getVersion());
            if (model.getName() != null && !model.getName().isBlank()) {
                mavenProjectBuilder.setDisplayName(model.getName());
            }
            if (model.getDescription() != null && !model.getDescription().isBlank()) {
                mavenProjectBuilder.setDescription(model.getDescription());
            }
            String backupFileName = "pom.xml.old";
            IFile backupFile = project.getFile(backupFileName);
            while (backupFile.exists()) {
                backupFileName = nextBackupFileName(backupFileName);
                backupFile = project.getFile(nextBackupFileName(backupFileName));
            }
            pomFile.copy(backupFile.getProjectRelativePath(), true, new NullProgressMonitor());
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
        mavenProjectHelper.saveModel(project, mavenProjectBuilder.toMavenModel());
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
