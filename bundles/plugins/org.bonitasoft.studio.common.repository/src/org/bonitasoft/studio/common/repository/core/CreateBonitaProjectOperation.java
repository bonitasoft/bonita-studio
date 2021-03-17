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
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;

public class CreateBonitaProjectOperation implements IWorkspaceRunnable {

    private static final String DEFAULT_VERSION = "1.0.0-SNAPSHOT";
    private static final String DEFAULT_GROUP_ID = "com.company";
    private IProject project;
    private final IWorkspace workspace;
    private final String projectName;
    private final Set<String> builders = new HashSet<>();
    private final List<String> natures = new ArrayList<>();

    public CreateBonitaProjectOperation(final IWorkspace workspace, final String projectName) {
        this.workspace = workspace;
        this.projectName = projectName;
    }

    @Override
    public void run(final IProgressMonitor monitor) throws CoreException {
        project = workspace.getRoot().getProject(projectName);
        if (project.exists()) {
            throw new CoreException(new Status(IStatus.ERROR, getClass(),
                    String.format("%s project already exists.", projectName)));
        }
        project.create(monitor);
        project.open(monitor);
        MavenProjectModelBuilder mavenProjectBuilder = defaultMavenProjectBuilder(project.getName());
        createDefaultPomFile(project, mavenProjectBuilder, monitor);
        project.setDescription(
                new ProjectDescriptionBuilder()
                        .withProjectName(project.getName())
                        .withComment(ProductVersion.CURRENT_VERSION)
                        .havingNatures(natures)
                        .havingBuilders(builders)
                        .build(),
                monitor);
    }

    public static MavenProjectModelBuilder defaultMavenProjectBuilder(String displayName) {
        MavenProjectModelBuilder mavenProjectBuilder = new MavenProjectModelBuilder();
        mavenProjectBuilder.setDisplayName(displayName);
        mavenProjectBuilder.setArtifactId(toArtifactId(mavenProjectBuilder.getDisplayName()));
        mavenProjectBuilder.setGroupId(DEFAULT_GROUP_ID);
        mavenProjectBuilder.setBonitaVersion(ProductVersion.mavenVersion());
        mavenProjectBuilder.setVersion(DEFAULT_VERSION);
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
            if (model.getName() != null && model.getName().isBlank()) {
                mavenProjectBuilder.setDisplayName(model.getName());
            }
            if (model.getDescription() != null && model.getDescription().isBlank()) {
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

    private static String toArtifactId(String displayName) {
        String artifactId = displayName.toLowerCase().replace(" ", "-");
        if (!artifactId.matches("[A-Za-z0-9_\\-.]+")) { // not a valid artifact id
            return "my-project";
        }
        return artifactId;
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
