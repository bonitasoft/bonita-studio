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
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.MultiModuleProject;
import org.bonitasoft.studio.common.repository.core.internal.team.GitProjectImpl;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectDefaultConfiguration;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.store.LocalDependenciesStore;
import org.bonitasoft.studio.common.ui.PlatformUtil;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.eclipse.m2e.core.internal.project.ProjectConfigurationManager;
import org.eclipse.m2e.core.project.IMavenProjectImportResult;
import org.eclipse.m2e.core.project.MavenProjectInfo;
import org.eclipse.m2e.core.project.MavenUpdateRequest;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;
import org.eclipse.m2e.core.ui.internal.UpdateMavenProjectJob;

public class MultiModuleBonitaProjectImpl extends BonitaProjectImpl implements MultiModuleProject {

    private static final String APP_MODULE_SUFFIX = "-app";
    private static final String PARENT_SUFFIX = "-parent";
    private static final String APP_MODULE = "app";
    private MavenProjectHelper mavenProjectHelper = new MavenProjectHelper();

    public MultiModuleBonitaProjectImpl(IRepository repository) {
        super(repository);
        gitProjectDelegate = new GitProjectImpl(getParentProject());
    }

    @Override
    public void open(IProgressMonitor monitor) throws CoreException {
        for(var project : getRelatedProjects()) {
            project.open(monitor);
        }
        super.open(monitor);
    }

    @Override
    public void close(IProgressMonitor monitor) throws CoreException {
        super.close(monitor);
        for(var project : getRelatedProjects()) {
            project.close(monitor);
        }
    }

    @Override
    public void delete(IProgressMonitor monitor) throws CoreException {
       for(var project : getRelatedProjects()) {
           project.delete(true,  true, monitor);
       }
    }

    @Override
    public List<IProject> getRelatedProjects() {
        var relatedProjects = new ArrayList<IProject>();
        var bdmParentProject = getBdmParentProject();
        if(bdmParentProject.exists()) {
            relatedProjects.add(bdmParentProject);
        }
        var bdmModelProject = getBdmModelProject();
        if(bdmModelProject.exists()) {
            relatedProjects.add(bdmModelProject);
        }
        var bdmDaoClientProject = getBdmDaoClientProject();
        if(bdmDaoClientProject.exists()) {
            relatedProjects.add(bdmDaoClientProject);
        }
        var parentProject = getParentProject();
        if(parentProject.exists()) {
            relatedProjects.add(parentProject);
        }
        var appProject = getAppProject();
        if(appProject.exists()) {
            relatedProjects.add(appProject);
        }
        return relatedProjects;
    }

    @Override
    public IProject getParentProject() {
        return getProject(getId());
    }

    @Override
    public IProject getBdmParentProject() {
        return getProject(getId() + "-bdm-parent");
    }

    @Override
    public IProject getBdmDaoClientProject() {
        return getProject(getId() + "-bdm-dao-client");
    }

    @Override
    public IProject getBdmModelProject() {
        return getProject(getId() + "-bdm-model");
    }

    @Override
    public IProject getAppProject() {
        return repository.getProject();
    }

    private IProject getProject(String name) {
        return ResourcesPlugin.getWorkspace().getRoot().getProject(name);
    }

    @Override
    public void refresh(IProgressMonitor monitor) throws CoreException {
        new UpdateMavenProjectJob(getRelatedProjects().toArray(IProject[]::new), false, false, false, true, true).run(monitor);
        repository.getProjectDependenciesStore().analyze(monitor);
    }

    @Override
    public void update(ProjectMetadata metadata, IProgressMonitor monitor) throws CoreException {
        var project = repository.getProject();
        var oldMetadata = getProjectMetadata(monitor);
        var projectId = oldMetadata.getArtifactId();
        var model = mavenProjectHelper.getMavenModel(project);
        boolean nameChanged = !Objects.equals(project.getName(), metadata.getName());
        var newProjectId = metadata.getArtifactId();

        model.getParent().setGroupId(metadata.getGroupId());
        model.getParent().setArtifactId(newProjectId + PARENT_SUFFIX);
        model.getParent().setVersion(metadata.getVersion());

        model.setArtifactId(newProjectId);
        model.setName(metadata.getName());
        model.setDescription(metadata.getDescription());

        var parentProject = getParentProject();
        var parentModel = MavenProjectHelper
                .readModel(parentProject.getFile(IMavenConstants.POM_FILE_NAME).getLocation().toFile());
        parentModel.setGroupId(metadata.getGroupId());
        parentModel.setVersion(metadata.getVersion());
        parentModel.setArtifactId(newProjectId + PARENT_SUFFIX);
        parentModel.getProperties().setProperty(ProjectDefaultConfiguration.BONITA_RUNTIME_VERSION,
                metadata.getBonitaRuntimeVersion());

        mavenProjectHelper.saveModel(parentProject, parentModel, monitor);

        var bdmParentProject = getBdmParentProject();
        if (bdmParentProject.exists()) {
            var bdmParentModel = mavenProjectHelper.getMavenModel(bdmParentProject);
            bdmParentModel.getParent().setGroupId(metadata.getGroupId());
            bdmParentModel.getParent().setArtifactId(newProjectId + PARENT_SUFFIX);
            bdmParentModel.getParent().setVersion(metadata.getVersion());
            bdmParentModel.setArtifactId(newProjectId + "-bdm-parent");
            mavenProjectHelper.saveModel(bdmParentProject, bdmParentModel, monitor);

            var bdmModelProject = getBdmModelProject();
            var bdmModelModel = mavenProjectHelper.getMavenModel(bdmModelProject);
            bdmModelModel.getParent().setGroupId(metadata.getGroupId());
            bdmModelModel.getParent().setArtifactId(newProjectId + "-bdm-parent");
            bdmModelModel.getParent().setVersion(metadata.getVersion());
            bdmModelModel.setArtifactId(newProjectId + "-bdm-model");
            mavenProjectHelper.saveModel(bdmModelProject, bdmModelModel, monitor);

            var bdmDaoClientProject = getBdmDaoClientProject();
            var bdmDaoClientModel = mavenProjectHelper.getMavenModel(bdmDaoClientProject);
            bdmDaoClientModel.getParent().setGroupId(metadata.getGroupId());
            bdmDaoClientModel.getParent().setArtifactId(newProjectId + "-bdm-parent");
            bdmDaoClientModel.getParent().setVersion(metadata.getVersion());
            bdmDaoClientModel.setArtifactId(newProjectId + "-bdm-dao-client");

            bdmDaoClientModel.getDependencies().stream()
                    .filter(d -> Objects.equals(projectId + "-bdm-model", d.getArtifactId()))
                    .findFirst()
                    .ifPresent(d -> d.setArtifactId(newProjectId + "-bdm-model"));
            
            model.getDependencies().stream()
                .filter(d -> Objects.equals(projectId + "-bdm-model", d.getArtifactId()))
                .findFirst()
                .ifPresent(d -> d.setArtifactId(newProjectId + "-bdm-model"));

            mavenProjectHelper.saveModel(bdmDaoClientProject, bdmDaoClientModel, monitor);
            if (Objects.equals(projectId, newProjectId)) {
                new UpdateMavenProjectJob(new IProject[] { bdmParentProject, bdmModelProject, bdmDaoClientProject },
                        false,
                        false,
                        false,
                        true,
                        true).run(monitor);
            }
        }

        if (!Objects.equals(projectId, newProjectId)) {
            mavenProjectHelper.saveModel(project, model, monitor);
            if (repository.closeAllEditors(false)) {
                ResourcesPlugin.getWorkspace().run(renameProjectsOperation(project, projectId, newProjectId), monitor);
                PlatformUtil.openIntroIfNoOtherEditorOpen();
            }
        } else {
            if (nameChanged) {
                mavenProjectHelper.saveModel(project, model, false, monitor);
            }
            parentProject = project.getWorkspace().getRoot().getProject(newProjectId + "-parent");
            ((ProjectConfigurationManager) MavenPlugin.getProjectConfigurationManager())
                    .updateProjectConfiguration(new MavenUpdateRequest(parentProject, false, false), false, false, true,
                            monitor);
        }
        updateRestApiExtension(oldMetadata, metadata, monitor);
    }

    @Override
    public void removeBdmProjects(IProgressMonitor monitor) throws CoreException {
        var mavenProjectHelper = new MavenProjectHelper();
        var parentProject = getParentProject();
        var parentModel = mavenProjectHelper.getMavenModel(parentProject);
        parentModel.getModules().removeIf("bdm"::equals);
        mavenProjectHelper.saveModel(parentProject, parentModel, new NullProgressMonitor());

        getBdmModelProject().delete(true, true, new NullProgressMonitor());
        getBdmDaoClientProject().delete(true, true, new NullProgressMonitor());
        getBdmParentProject().delete(true, true, new NullProgressMonitor());

        parentProject.getFolder("bdm").delete(true, new NullProgressMonitor());
    }

    private IWorkspaceRunnable renameProjectsOperation(IProject project, String oldProjectId, String newProjectId) {
        return new IWorkspaceRunnable() {

            @Override
            public void run(IProgressMonitor monitor)
                    throws CoreException {

                repository.close(monitor);

                var appProject = getProject(oldProjectId + "-app");
                appProject.delete(false, true, monitor);

                var bdmParentProject = getProject(oldProjectId + "-bdm-parent");
                if (bdmParentProject.exists()) {
                    IProject bdmModelProject = getProject(oldProjectId + "-bdm-model");
                    var descriptorFile = bdmModelProject.getFile(".project").getLocation().toFile();
                    descriptorFile.delete();
                    bdmModelProject.delete(false, true, monitor);

                    var bdmDaoClientProject = getProject(oldProjectId + "-bdm-dao-client");
                    descriptorFile = bdmDaoClientProject.getFile(".project").getLocation().toFile();
                    descriptorFile.delete();
                    bdmDaoClientProject.delete(false, true, monitor);

                    descriptorFile = bdmParentProject.getFile(".project").getLocation().toFile();
                    descriptorFile.delete();
                    bdmParentProject.delete(false, true, monitor);
                }

                var parentProject = getProject(oldProjectId);
                var description = parentProject.getDescription();
                description.setName(newProjectId);
                parentProject.move(description, IResource.FORCE | IResource.SHALLOW, monitor);
                var newParentProject = getProject(newProjectId);

                var projectsToUpdate = new ArrayList<>();
                projectsToUpdate.add(newParentProject);

                var appModule = newParentProject.getFolder(APP_MODULE);
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
                var bdmModule = newParentProject.getFolder("bdm");
                if (bdmModule.exists()) {
                    var projectInfoToImport = new ArrayList<MavenProjectInfo>();
                    projectInfoToImport.add(projectInfo(bdmModule));
                    projectInfoToImport.add(projectInfo(bdmModule.getFolder("model")));
                    projectInfoToImport.add(projectInfo(bdmModule.getFolder("dao-client")));
                    projectImportConfiguration = new ProjectImportConfiguration();
                    projectImportConfiguration.setProjectNameTemplate("[artifactId]");
                    importResults = MavenPlugin.getProjectConfigurationManager().importProjects(
                            projectInfoToImport,
                            projectImportConfiguration, monitor);
                    importResults.stream()
                            .map(IMavenProjectImportResult::getProject)
                            .filter(Objects::nonNull)
                            .forEach(projectsToUpdate::add);
                    IFolder bdmFolder = appProject.getFolder("bdm");
                    if (bdmFolder.exists()) {
                        bdmFolder.delete(true, monitor);
                    }
                    bdmFolder.createLink(Path.fromOSString("PARENT-1-PROJECT_LOC/bdm"),
                            IResource.REPLACE | IResource.ALLOW_MISSING_LOCAL, monitor);
                }

                repository = RepositoryManager.getInstance().getRepository(appProject.getName());
                repository.open(monitor);

                new UpdateMavenProjectJob(projectsToUpdate.toArray(IProject[]::new),
                        false,
                        false,
                        false,
                        false,
                        true).run(monitor);

            }

        };
    }

    private MavenProjectInfo projectInfo(IFolder bdmModule) {
        return new MavenProjectInfo(null, bdmModule.getFile(IMavenConstants.POM_FILE_NAME).getLocation().toFile(), null,
                null);
    }

    @Override
    public Collection<? extends IResource> getExportableResources() {
        var resources = new ArrayList<IResource>();
        IProject project = getParentProject();
        IFile pomFile = project
                .getFile(IMavenConstants.POM_FILE_NAME);
        if (pomFile.exists()) {
            resources.add(pomFile);
        }
        IFolder appModule = project.getFolder(APP_MODULE);
        IFile appPomFile = appModule
                .getFile(IMavenConstants.POM_FILE_NAME);
        if (appPomFile.exists()) {
            resources.add(appPomFile);
        }
        IFolder depStore = appModule.getFolder(LocalDependenciesStore.NAME);
        if (depStore.exists()) {
            resources.add(depStore);
        }
        IFolder resourcesFolder = appModule.getFolder("src/main/resources");
        if (resourcesFolder.exists()) {
            resources.add(resourcesFolder);
        }
        return resources;
    }



}
