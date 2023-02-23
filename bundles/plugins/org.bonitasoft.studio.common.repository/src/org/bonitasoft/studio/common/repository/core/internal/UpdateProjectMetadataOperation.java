/**
 * Copyright (C) 2022 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.RestAPIExtensionNature;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.model.GAV;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectDefaultConfiguration;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.ui.PlatformUtil;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.project.IMavenProjectImportResult;
import org.eclipse.m2e.core.project.MavenProjectInfo;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;
import org.eclipse.m2e.core.ui.internal.UpdateMavenProjectJob;

public class UpdateProjectMetadataOperation implements IWorkspaceRunnable {

    private static final String DAO_CLIENT_MODULE = "dao-client";
    private static final String BDM_MODEL_MODULE = "model";
    private static final String BDM_DAO_CLIENT_SUFFIX = "-bdm-dao-client";
    private static final String BDM_MODEL_SUFFIX = "-bdm-model";
    private static final String BDM_PARENT_SUFFIX = "-bdm-parent";
    private static final String BONITA_VERSION_PROPERTY = "bonita.version";
    private static final String BONITA_RUNTIME_VERSION_PROPERTY = "bonita-runtime.version";
    private static final String APP_MODULE_SUFFIX = "-app";
    private static final String PARENT_SUFFIX = "-parent";

    private ProjectMetadata metadata;
    private BonitaProject project;

    public UpdateProjectMetadataOperation(BonitaProject project, ProjectMetadata metadata) {
        this.project = project;
        this.metadata = metadata;
    }

    @Override
    public void run(IProgressMonitor monitor) throws CoreException {
        var appProject = project.getAppProject();
        var oldMetadata = project.getProjectMetadata(new NullProgressMonitor());
        var projectId = project.getId();
        var model = MavenProjectHelper.getMavenModel(appProject);
        var newProjectId = metadata.getArtifactId();

        var parent = model.getParent();
        parent.setGroupId(metadata.getGroupId());
        parent.setArtifactId(newProjectId + PARENT_SUFFIX);
        parent.setVersion(metadata.getVersion());

        model.setArtifactId(newProjectId);
        model.setName(metadata.getName());
        model.setDescription(metadata.getDescription());

        var parentProject = project.getParentProject();
        var parentModel = MavenProjectHelper
                .readModel(parentProject.getFile(MavenProjectHelper.POM_FILE_NAME).getLocation().toFile());
        parentModel.setGroupId(metadata.getGroupId());
        parentModel.setVersion(metadata.getVersion());
        parentModel.setArtifactId(newProjectId + PARENT_SUFFIX);
        parentModel.getProperties().setProperty(ProjectDefaultConfiguration.BONITA_RUNTIME_VERSION,
                metadata.getBonitaRuntimeVersion());

        MavenProjectHelper.saveModel(parentProject, parentModel, new NullProgressMonitor());

        var bdmParentProject = project.getBdmParentProject();
        if (bdmParentProject.exists()) {
            var bdmParentModel = MavenProjectHelper.getMavenModel(bdmParentProject);
            parent = bdmParentModel.getParent();
            parent.setGroupId(metadata.getGroupId());
            parent.setArtifactId(newProjectId + PARENT_SUFFIX);
            parent.setVersion(metadata.getVersion());
            bdmParentModel.setArtifactId(newProjectId + BDM_PARENT_SUFFIX);
            MavenProjectHelper.saveModel(bdmParentProject, bdmParentModel, new NullProgressMonitor());

            var bdmModelProject = project.getBdmModelProject();
            var bdmModelModel = MavenProjectHelper.getMavenModel(bdmModelProject);
            parent = bdmModelModel.getParent();
            parent.setGroupId(metadata.getGroupId());
            parent.setArtifactId(newProjectId + BDM_PARENT_SUFFIX);
            parent.setVersion(metadata.getVersion());
            bdmModelModel.setArtifactId(newProjectId + BDM_MODEL_SUFFIX);
            MavenProjectHelper.saveModel(bdmModelProject, bdmModelModel, new NullProgressMonitor());

            var bdmDaoClientProject = project.getBdmDaoClientProject();
            var bdmDaoClientModel = MavenProjectHelper.getMavenModel(bdmDaoClientProject);
            parent = bdmDaoClientModel.getParent();
            parent.setGroupId(metadata.getGroupId());
            parent.setArtifactId(newProjectId + BDM_PARENT_SUFFIX);
            parent.setVersion(metadata.getVersion());
            bdmDaoClientModel.setArtifactId(newProjectId + BDM_DAO_CLIENT_SUFFIX);

            bdmDaoClientModel.getDependencies().stream()
                    .filter(d -> Objects.equals(projectId + BDM_MODEL_SUFFIX, d.getArtifactId()))
                    .findFirst()
                    .ifPresent(d -> d.setArtifactId(newProjectId + BDM_MODEL_SUFFIX));

            model.getDependencies().stream()
                    .filter(d -> Objects.equals(projectId + BDM_MODEL_SUFFIX, d.getArtifactId()))
                    .findFirst()
                    .ifPresent(d -> d.setArtifactId(newProjectId + BDM_MODEL_SUFFIX));

            MavenProjectHelper.saveModel(bdmDaoClientProject, bdmDaoClientModel, new NullProgressMonitor());
            if (Objects.equals(projectId, newProjectId)) {
                new UpdateMavenProjectJob(new IProject[] { bdmParentProject, bdmModelProject, bdmDaoClientProject },
                        false,
                        false,
                        false,
                        true,
                        true).run(new NullProgressMonitor());
            }
        }

        MavenProjectHelper.saveModel(appProject, model, new NullProgressMonitor());
        updateRestApiExtension(oldMetadata, metadata, new NullProgressMonitor());
        if (!Objects.equals(projectId, newProjectId)) {
            if (currentRepository().closeAllEditors(false)) {
                renameProjects(projectId, newProjectId, monitor);
            }
        } else {
            new UpdateMavenProjectJob(project.getRelatedProjects().toArray(IProject[]::new), false, false, false,
                    false, true)
                            .run(monitor);
        }
    }

    private void renameProjects(String oldProjectId, String newProjectId, IProgressMonitor monitor)
            throws CoreException {

        currentRepository().close(new NullProgressMonitor());

        var appProject = BonitaProject.getProject(oldProjectId + APP_MODULE_SUFFIX);
        appProject.delete(false, true, new NullProgressMonitor());

        var bdmParentProject = BonitaProject.getProject(oldProjectId + BDM_PARENT_SUFFIX);
        if (bdmParentProject.exists()) {
            IProject bdmModelProject = BonitaProject.getProject(oldProjectId + BDM_MODEL_SUFFIX);
            var descriptorFile = bdmModelProject.getFile(IProjectDescription.DESCRIPTION_FILE_NAME).getLocation()
                    .toFile();
            descriptorFile.delete();
            bdmModelProject.delete(false, true, new NullProgressMonitor());

            var bdmDaoClientProject = BonitaProject.getProject(oldProjectId + BDM_DAO_CLIENT_SUFFIX);
            descriptorFile = bdmDaoClientProject.getFile(IProjectDescription.DESCRIPTION_FILE_NAME).getLocation()
                    .toFile();
            descriptorFile.delete();
            bdmDaoClientProject.delete(false, true, new NullProgressMonitor());

            descriptorFile = bdmParentProject.getFile(IProjectDescription.DESCRIPTION_FILE_NAME).getLocation().toFile();
            descriptorFile.delete();
            bdmParentProject.delete(false, true, new NullProgressMonitor());
        }

        var parentProject = BonitaProject.getProject(oldProjectId);
        var description = parentProject.getDescription();
        description.setName(newProjectId);
        parentProject.move(description, IResource.FORCE | IResource.SHALLOW, monitor);
        var newParentProject = BonitaProject.getProject(newProjectId);

        var projectsToUpdate = new ArrayList<>();
        projectsToUpdate.add(newParentProject);

        var appModule = newParentProject.getFolder(BonitaProject.APP_MODULE);
        var projectImportConfiguration = new ProjectImportConfiguration();
        projectImportConfiguration.setProjectNameTemplate(newProjectId + APP_MODULE_SUFFIX);
        var importResults = MavenPlugin.getProjectConfigurationManager().importProjects(
                List.of(projectInfo(appModule)),
                projectImportConfiguration, monitor);
        appProject = importResults.get(0).getProject();
        description = appProject.getDescription();
        description.setName(newProjectId + APP_MODULE_SUFFIX);
        appProject.move(description, IResource.FORCE | IResource.SHALLOW, monitor);

        // Reimport bdm modules
        var bdmModule = newParentProject.getFolder(BonitaProject.BDM_MODULE);
        if (bdmModule.exists()) {
            var projectInfoToImport = new ArrayList<MavenProjectInfo>();
            projectInfoToImport.add(projectInfo(bdmModule));
            projectInfoToImport.add(projectInfo(bdmModule.getFolder(BDM_MODEL_MODULE)));
            projectInfoToImport.add(projectInfo(bdmModule.getFolder(DAO_CLIENT_MODULE)));
            projectImportConfiguration = new ProjectImportConfiguration();
            projectImportConfiguration.setProjectNameTemplate("[artifactId]");
            importResults = MavenPlugin.getProjectConfigurationManager().importProjects(
                    projectInfoToImport,
                    projectImportConfiguration, new NullProgressMonitor());
            importResults.stream()
                    .map(IMavenProjectImportResult::getProject)
                    .filter(Objects::nonNull)
                    .forEach(projectsToUpdate::add);
            IFolder bdmFolder = appProject.getFolder(BonitaProject.BDM_MODULE);
            if (bdmFolder.exists()) {
                bdmFolder.delete(true, new NullProgressMonitor());
            }
            bdmFolder.createLink(Path.fromOSString("PARENT-1-PROJECT_LOC/bdm"),
                    IResource.REPLACE | IResource.ALLOW_MISSING_LOCAL, new NullProgressMonitor());
        }
        var repository = RepositoryManager.getInstance().getRepository(newProjectId);
        repository.open(monitor);
    }

    private void updateRestApiExtension(ProjectMetadata oldMetadata,
            ProjectMetadata metadata, IProgressMonitor monitor) {
        Stream.of(ResourcesPlugin.getWorkspace().getRoot().getProjects())
                .filter(IProject::isOpen)
                .filter(p -> {
                    try {
                        return p.hasNature(RestAPIExtensionNature.NATURE_ID);
                    } catch (CoreException e) {
                        BonitaStudioLog.error(e);
                        return false;
                    }
                })
                .forEach(p -> {
                    try {
                        var mavenModel = MavenProjectHelper.getMavenModel(p);
                        Properties properties = mavenModel.getProperties();
                        if (properties.containsKey(BONITA_RUNTIME_VERSION_PROPERTY)) {
                            properties.setProperty(BONITA_RUNTIME_VERSION_PROPERTY,
                                    metadata.getBonitaRuntimeVersion());
                        }
                        if (properties.containsKey(BONITA_VERSION_PROPERTY)) {
                            properties.setProperty(BONITA_VERSION_PROPERTY,
                                    metadata.getBonitaRuntimeVersion());
                        }
                        var oldBdmModel = new GAV(oldMetadata.getGroupId(),
                                oldMetadata.getArtifactId() + BDM_MODEL_SUFFIX,
                                oldMetadata.getVersion(),
                                null,
                                "jar",
                                "provided");
                        mavenModel.getDependencies().stream()
                                .filter(d -> new GAV(d).isSameAs(oldBdmModel))
                                .findFirst()
                                .ifPresent(d -> {
                                    d.setGroupId(metadata.getGroupId());
                                    d.setArtifactId(metadata.getArtifactId() + BDM_MODEL_SUFFIX);
                                    d.setVersion(metadata.getVersion());
                                });
                        var oldBdmDaoClient = new GAV(oldMetadata.getGroupId(),
                                oldMetadata.getArtifactId() + BDM_DAO_CLIENT_SUFFIX,
                                oldMetadata.getVersion(),
                                null,
                                "jar",
                                "provided");
                        mavenModel.getDependencies().stream()
                                .filter(d -> new GAV(d).isSameAs(oldBdmDaoClient))
                                .findFirst()
                                .ifPresent(d -> {
                                    d.setGroupId(metadata.getGroupId());
                                    d.setArtifactId(metadata.getArtifactId() + BDM_DAO_CLIENT_SUFFIX);
                                    d.setVersion(metadata.getVersion());
                                });
                        MavenProjectHelper.saveModel(p, mavenModel, false, monitor);
                    } catch (CoreException e) {
                        BonitaStudioLog.error(e);
                    }
                });
    }

    private IRepository currentRepository() {
        return RepositoryManager.getInstance().getCurrentRepository().orElseThrow();
    }

    private MavenProjectInfo projectInfo(IFolder bdmModule) {
        return new MavenProjectInfo(null, bdmModule.getFile(MavenProjectHelper.POM_FILE_NAME).getLocation().toFile(),
                null,
                null);
    }

}
