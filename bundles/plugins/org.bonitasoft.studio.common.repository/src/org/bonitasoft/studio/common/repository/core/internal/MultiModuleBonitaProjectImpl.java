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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.MultiModuleProject;
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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.egit.core.internal.util.ResourceUtil;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.eclipse.m2e.core.internal.project.ProjectConfigurationManager;
import org.eclipse.m2e.core.project.MavenProjectInfo;
import org.eclipse.m2e.core.project.MavenUpdateRequest;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

public class MultiModuleBonitaProjectImpl extends BonitaProjectImpl implements BonitaProject, MultiModuleProject {

    private static final String APP_MODULE_SUFFIX = "-app";
    private static final String PARENT_SUFFIX = "-parent";
    private static final String PARENT_GITIGNORE_TEMPLATE = ".gitignore.parent.template";
    private static final String APP_MODULE = "app";
    private MavenProjectHelper mavenProjectHelper = new MavenProjectHelper();

    public MultiModuleBonitaProjectImpl(IRepository repository) {
        super(repository);
    }

    @Override
    public void open(IProgressMonitor monitor) throws CoreException {
        super.open(monitor);
        getParentProject().open(monitor);
    }

    @Override
    public void close(IProgressMonitor monitor) throws CoreException {
        getParentProject().close(monitor);
        super.close(monitor);
    }

    @Override
    public void delete(IProgressMonitor monitor) throws CoreException {
        var parentProject = getParentProject();
        var subMonitor = SubMonitor.convert(monitor)
                .newChild(IProgressMonitor.UNKNOWN, SubMonitor.SUPPRESS_SUBTASK);
        repository.getProject().delete(false, true, monitor);
        parentProject.delete(true, true, subMonitor);
    }

    @Override
    public IProject getParentProject() {
        var metadata = getProjectMetadata(new NullProgressMonitor());
        return repository.getProject().getWorkspace().getRoot().getProject(metadata.getArtifactId());
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
        var parentModel = MavenProjectHelper.readModel(parentProject.getFile(IMavenConstants.POM_FILE_NAME).getLocation().toFile());
        parentModel.setGroupId(metadata.getGroupId());
        parentModel.setVersion(metadata.getVersion());
        parentModel.setArtifactId(newProjectId + PARENT_SUFFIX);
        parentModel.getProperties().setProperty(ProjectDefaultConfiguration.BONITA_RUNTIME_VERSION,
                metadata.getBonitaRuntimeVersion());

        mavenProjectHelper.saveModel(parentProject, parentModel, monitor);

        if (!Objects.equals(projectId, newProjectId)) {
            mavenProjectHelper.saveModel(project, model, monitor);
            if (repository.closeAllEditors()) {
                WorkspaceModifyOperation operation = renameProjectsOperation(project, newProjectId, parentProject);
                try {
                    operation.run(monitor);
                } catch (InvocationTargetException | InterruptedException e) {
                    throw new CoreException(Status.error("Failed to update project metadata", e));
                }
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
        if (!Objects.equals(oldMetadata.getBonitaRuntimeVersion(), metadata.getBonitaRuntimeVersion())) {
            updateRestApiExtensionBontitaRuntimeVersion(metadata.getBonitaRuntimeVersion(), monitor);
        }
    }

    private WorkspaceModifyOperation renameProjectsOperation(IProject project, String newProjectId, IProject parentProject) {
        WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {

            @Override
            protected void execute(IProgressMonitor monitor)
                    throws CoreException, InvocationTargetException, InterruptedException {
                repository.close(SubMonitor.convert(monitor).newChild(IProgressMonitor.UNKNOWN,
                        SubMonitor.SUPPRESS_SUBTASK));
                repository.getProject().delete(false, true, SubMonitor.convert(monitor)
                        .newChild(IProgressMonitor.UNKNOWN, SubMonitor.SUPPRESS_SUBTASK));
                var description = parentProject.getDescription();
                description.setName(newProjectId);
                parentProject.move(description, IResource.FORCE | IResource.SHALLOW, SubMonitor.convert(monitor)
                        .newChild(IProgressMonitor.UNKNOWN, SubMonitor.SUPPRESS_SUBTASK));
                var newParentProject = project.getWorkspace().getRoot().getProject(newProjectId);
                var appModule = newParentProject.getFolder(APP_MODULE);
                var pomFile = appModule.getFile(IMavenConstants.POM_FILE_NAME);
                var projectImportConfiguration = new ProjectImportConfiguration();
                projectImportConfiguration.setProjectNameTemplate(newProjectId + APP_MODULE_SUFFIX);
                var projectInfo = new MavenProjectInfo(null, pomFile.getLocation().toFile(), null, null);
                var importProjects = MavenPlugin.getProjectConfigurationManager().importProjects(
                        List.of(projectInfo),
                        projectImportConfiguration, SubMonitor.convert(monitor)
                                .newChild(IProgressMonitor.UNKNOWN, SubMonitor.SUPPRESS_SUBTASK));
                var appProject = importProjects.get(0).getProject();
                var appProjectDesc = appProject.getDescription();
                appProjectDesc.setName(newProjectId + APP_MODULE_SUFFIX);
                appProject.move(appProjectDesc, IResource.FORCE | IResource.SHALLOW, SubMonitor.convert(monitor)
                        .newChild(IProgressMonitor.UNKNOWN, SubMonitor.SUPPRESS_SUBTASK));
                repository = RepositoryManager.getInstance().getRepository(newProjectId + APP_MODULE_SUFFIX);
                repository.open(monitor);
            }
        };
        return operation;
    }

    @Override
    public IRunnableWithProgress newConnectProviderOperation() throws CoreException {
        return connectProviderOperation(getParentProject());
    }

    @Override
    public void createDefaultIgnoreFile() throws CoreException {
        super.createDefaultIgnoreFile();
        var parentGitIgnoreFile = getParentProject().getFile(Constants.DOT_GIT_IGNORE);
        try (var is = getParentGitIgnoreTemplate().openStream()) {
            if (parentGitIgnoreFile.exists()) {
                parentGitIgnoreFile.setContents(is, true, true, new NullProgressMonitor());
            } else {
                parentGitIgnoreFile.create(is, true, new NullProgressMonitor());
            }
        } catch (IOException e) {
            throw new CoreException(Status.error("Failed create parent project .gitignore file.", e));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getAdapter(Class<T> adapter) {
        if (Repository.class.equals(adapter)) {
            return (T) ResourceUtil.getRepository(getParentProject());
        }else if(IProject.class.equals(adapter)) {
            return (T) repository.getProject();
        }
        return null;
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
    
    static URL getParentGitIgnoreTemplate() throws IOException {
        return FileLocator
                .toFileURL(MultiModuleBonitaProjectImpl.class.getResource(PARENT_GITIGNORE_TEMPLATE));
    }

}
