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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Stream;

import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.RestAPIExtensionNature;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.internal.team.GitProjectImpl;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.model.GAV;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectDefaultConfiguration;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.team.GitProject;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.ui.PlatformUtil;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.eclipse.m2e.core.project.IMavenProjectImportResult;
import org.eclipse.m2e.core.project.MavenProjectInfo;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;
import org.eclipse.m2e.core.ui.internal.UpdateMavenProjectJob;

public class BonitaProjectImpl implements BonitaProject {

    private static final String BONITA_VERSION_PROPERTY = "bonita.version";
    private static final String BONITA_RUNTIME_VERSION_PROPERTY = "bonita-runtime.version";
    private static final String APP_MODULE_SUFFIX = "-app";
    private static final String PARENT_SUFFIX = "-parent";

    private MavenProjectHelper mavenProjectHelper = new MavenProjectHelper();
    private String id;

    public BonitaProjectImpl(String id) {
        this.id = id;
    }

    @Override
    public boolean exists() {
        return getAppProject().exists() && getParentProject().exists();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getDisplayName() {
        try {
            return getMavenModel().getName();
        } catch (CoreException e) {
            return id;
        }
    }

    private Model getMavenModel() throws CoreException {
        var appProject = getAppProject();
        var mavenFacade = MavenPlugin.getMavenProjectRegistry().create(appProject, new NullProgressMonitor());
        if (mavenFacade != null && mavenFacade.getMavenProject() != null) {
            return mavenFacade.getMavenProject().getModel();
        }
        if (appProject.exists()
                && appProject.getFile(IMavenConstants.POM_FILE_NAME).exists()) {
            return MavenProjectHelper
                    .readModel(appProject.getFile(IMavenConstants.POM_FILE_NAME).getLocation().toFile());
        }
        throw new CoreException(
                Status.error(String.format("Project '%s' does not exists.", appProject)));
    }

    @Override
    public ProjectMetadata getProjectMetadata(IProgressMonitor monitor) throws CoreException {
        return ProjectMetadata.read(getAppProject(), monitor);
    }

    @Override
    public void open(IProgressMonitor monitor) throws CoreException {
        for (var project : getRelatedProjects()) {
            project.open(monitor);
        }
        currentRepository().open(monitor);
    }

    private IRepository currentRepository() {
        return RepositoryManager.getInstance().getCurrentRepository().orElseThrow();
    }

    @Override
    public void close(IProgressMonitor monitor) throws CoreException {
        currentRepository().close(monitor);
        for (var project : getRelatedProjects()) {
            project.close(monitor);
        }
    }

    @Override
    public void delete(IProgressMonitor monitor) throws CoreException {
        for (var project : getRelatedProjects()) {
            project.delete(true, true, monitor);
        }
    }

    @Override
    public List<IProject> getRelatedProjects() {
        return BonitaProject.getRelatedProjects(getId());
    }

    @Override
    public IProject getParentProject() {
        return BonitaProject.getParentProject(getId());
    }

    @Override
    public IProject getBdmParentProject() {
        return BonitaProject.getBdmParentProject(getId());
    }

    @Override
    public IProject getBdmDaoClientProject() {
        return BonitaProject.getBdmDaoClientProject(getId());
    }

    @Override
    public IProject getBdmModelProject() {
        return BonitaProject.getBdmModelProject(getId());
    }

    @Override
    public IProject getAppProject() {
        return BonitaProject.getAppProject(getId());
    }

    private IProject getProject(String name) {
        return ResourcesPlugin.getWorkspace().getRoot().getProject(name);
    }

    @Override
    public void refresh(IProgressMonitor monitor) throws CoreException {
        refresh(false, monitor);
    }

    @Override
    public void refresh(boolean updateConfiguration, IProgressMonitor monitor) throws CoreException {
        new UpdateMavenProjectJob(getRelatedProjects().toArray(IProject[]::new), false, false, updateConfiguration,
                true, true)
                        .run(monitor);
        currentRepository().getProjectDependenciesStore().analyze(monitor);
    }

    @Override
    public void update(ProjectMetadata metadata, IProgressMonitor monitor) throws CoreException {
        var project = getAppProject();
        var oldMetadata = getProjectMetadata(new NullProgressMonitor());
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

        mavenProjectHelper.saveModel(parentProject, parentModel, new NullProgressMonitor());

        var bdmParentProject = getBdmParentProject();
        if (bdmParentProject.exists()) {
            var bdmParentModel = mavenProjectHelper.getMavenModel(bdmParentProject);
            bdmParentModel.getParent().setGroupId(metadata.getGroupId());
            bdmParentModel.getParent().setArtifactId(newProjectId + PARENT_SUFFIX);
            bdmParentModel.getParent().setVersion(metadata.getVersion());
            bdmParentModel.setArtifactId(newProjectId + "-bdm-parent");
            mavenProjectHelper.saveModel(bdmParentProject, bdmParentModel, new NullProgressMonitor());

            var bdmModelProject = getBdmModelProject();
            var bdmModelModel = mavenProjectHelper.getMavenModel(bdmModelProject);
            bdmModelModel.getParent().setGroupId(metadata.getGroupId());
            bdmModelModel.getParent().setArtifactId(newProjectId + "-bdm-parent");
            bdmModelModel.getParent().setVersion(metadata.getVersion());
            bdmModelModel.setArtifactId(newProjectId + "-bdm-model");
            mavenProjectHelper.saveModel(bdmModelProject, bdmModelModel, new NullProgressMonitor());

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

            mavenProjectHelper.saveModel(bdmDaoClientProject, bdmDaoClientModel, new NullProgressMonitor());
            if (Objects.equals(projectId, newProjectId)) {
                new UpdateMavenProjectJob(new IProject[] { bdmParentProject, bdmModelProject, bdmDaoClientProject },
                        false,
                        false,
                        false,
                        true,
                        true).run(new NullProgressMonitor());
            }
        }

        if (!Objects.equals(projectId, newProjectId)) {
            mavenProjectHelper.saveModel(project, model, new NullProgressMonitor());
            if (currentRepository().closeAllEditors(false)) {
                ResourcesPlugin.getWorkspace().run(renameProjectsOperation(project, projectId, newProjectId), monitor);
                PlatformUtil.openIntroIfNoOtherEditorOpen();
            }
        } else {
            if (nameChanged) {
                mavenProjectHelper.saveModel(project, model, false, new NullProgressMonitor());
            }
            new UpdateMavenProjectJob(getRelatedProjects().toArray(IProject[]::new), false, false, false,
                    false, true)
                            .run(monitor);
        }
        updateRestApiExtension(oldMetadata, metadata, new NullProgressMonitor());
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
                        var mavenModel = mavenProjectHelper.getMavenModel(p);
                        Properties properties = mavenModel.getProperties();
                        if (properties.containsKey(BONITA_RUNTIME_VERSION_PROPERTY)) {
                            properties.setProperty(BONITA_RUNTIME_VERSION_PROPERTY,
                                    metadata.getBonitaRuntimeVersion());
                        }
                        if (properties.containsKey(BONITA_VERSION_PROPERTY)) {
                            properties.setProperty(BONITA_VERSION_PROPERTY,
                                    metadata.getBonitaRuntimeVersion());
                        }
                        var oldBdmClientDao = new GAV(oldMetadata.getGroupId(),
                                oldMetadata.getArtifactId() + "-bdm-model",
                                oldMetadata.getVersion(),
                                null,
                                "jar",
                                "provided");
                        mavenModel.getDependencies().stream()
                                .filter(d -> new GAV(d).isSameAs(oldBdmClientDao))
                                .findFirst()
                                .ifPresent(d -> {
                                    d.setGroupId(metadata.getGroupId());
                                    d.setArtifactId(metadata.getArtifactId() + "-bdm-model");
                                    d.setVersion(metadata.getVersion());
                                });
                        mavenProjectHelper.saveModel(p, mavenModel, false, monitor);
                    } catch (CoreException e) {
                        BonitaStudioLog.error(e);
                    }
                });
    }

    @Override
    public IRunnableWithProgress newConnectProviderOperation() throws CoreException {
        return getAdapter(GitProject.class).newConnectProviderOperation();
    }

    @Override
    public File getGitDir() {
        return getAdapter(GitProject.class).getGitDir();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getAdapter(Class<T> adapter) {
        if (IProject.class.equals(adapter)) {
            return (T) getAppProject();
        } else if (GitProject.class.equals(adapter)) {
            return (T) new GitProjectImpl(getParentProject());
        } else {
            return getAdapter(GitProject.class).getAdapter(adapter);
        }
    }

    @Override
    public void createDefaultIgnoreFile() throws CoreException {
        getAdapter(GitProject.class).createDefaultIgnoreFile();
    }

    @Override
    public void commitAll(String commitMessage, IProgressMonitor monitor) throws CoreException {
        getAdapter(GitProject.class).commitAll(commitMessage, monitor);
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

                currentRepository().close(new NullProgressMonitor());

                var appProject = getProject(oldProjectId + "-app");
                appProject.delete(false, true, new NullProgressMonitor());

                var bdmParentProject = getProject(oldProjectId + "-bdm-parent");
                if (bdmParentProject.exists()) {
                    IProject bdmModelProject = getProject(oldProjectId + "-bdm-model");
                    var descriptorFile = bdmModelProject.getFile(".project").getLocation().toFile();
                    descriptorFile.delete();
                    bdmModelProject.delete(false, true, new NullProgressMonitor());

                    var bdmDaoClientProject = getProject(oldProjectId + "-bdm-dao-client");
                    descriptorFile = bdmDaoClientProject.getFile(".project").getLocation().toFile();
                    descriptorFile.delete();
                    bdmDaoClientProject.delete(false, true, new NullProgressMonitor());

                    descriptorFile = bdmParentProject.getFile(".project").getLocation().toFile();
                    descriptorFile.delete();
                    bdmParentProject.delete(false, true, new NullProgressMonitor());
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
                            projectImportConfiguration, new NullProgressMonitor());
                    importResults.stream()
                            .map(IMavenProjectImportResult::getProject)
                            .filter(Objects::nonNull)
                            .forEach(projectsToUpdate::add);
                    IFolder bdmFolder = appProject.getFolder("bdm");
                    if (bdmFolder.exists()) {
                        bdmFolder.delete(true, new NullProgressMonitor());
                    }
                    bdmFolder.createLink(Path.fromOSString("PARENT-1-PROJECT_LOC/bdm"),
                            IResource.REPLACE | IResource.ALLOW_MISSING_LOCAL, new NullProgressMonitor());
                }
                BonitaProjectImpl.this.id = newProjectId;
                var repository = RepositoryManager.getInstance().getRepository(newProjectId);
                repository.open(monitor);
            }

        };
    }

    private MavenProjectInfo projectInfo(IFolder bdmModule) {
        return new MavenProjectInfo(null, bdmModule.getFile(IMavenConstants.POM_FILE_NAME).getLocation().toFile(), null,
                null);
    }

    @Override
    public IScopeContext getScopeContext() {
        return new ProjectScope(getAppProject());
    }

    @Override
    public String getBonitaVersion() {
        var parentProject = getParentProject();
        if (parentProject.isOpen()) {
            try {
                return parentProject.getDescription().getComment();
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        } else if (parentProject.getLocation() != null) {
            var projectFile = new File(parentProject.getLocation().toFile(), ".project");
            if (projectFile.exists()) {
                try (var fis = new FileInputStream(projectFile)) {
                    var description = ResourcesPlugin.getWorkspace().loadProjectDescription(fis);
                    return description.getComment();
                } catch (final IOException | CoreException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
        return null;
    }
}
