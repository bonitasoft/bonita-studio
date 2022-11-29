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
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Stream;

import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.RestAPIExtensionNature;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.internal.team.GitProjectImpl;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.model.GAV;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectDefaultConfiguration;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.team.GitProject;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.store.LocalDependenciesStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.eclipse.m2e.core.ui.internal.UpdateMavenProjectJob;

public class BonitaProjectImpl implements BonitaProject {

    private static final String BONITA_VERSION_PROPERTY = "bonita.version";
    private static final String BONITA_RUNTIME_VERSION_PROPERTY = "bonita-runtime.version";


    protected IRepository repository;
    private MavenProjectHelper mavenProjectHelper = new MavenProjectHelper();
    protected GitProject gitProjectDelegate;

    public BonitaProjectImpl(IRepository repository) {
        this.repository = repository;
        gitProjectDelegate = new GitProjectImpl(repository.getProject());
    }

    @Override
    public String getId() {
        try {
            return getMavenModel().getArtifactId();
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
            return repository.getName();
        }
    }

    @Override
    public String getDisplayName() {
        try {
            return getMavenModel().getName();
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
            return repository.getName();
        }
    }

    private Model getMavenModel() throws CoreException {
        var mavenFacade = MavenPlugin.getMavenProjectRegistry().create(repository.getProject()
                .getFile(IMavenConstants.POM_FILE_NAME), true, new NullProgressMonitor());
        if (mavenFacade != null && mavenFacade.getMavenProject() != null) {
            return mavenFacade.getMavenProject().getModel();
        }
        if (repository.getProject().exists()) {
            return MavenProjectHelper
                    .readModel(repository.getProject().getFile(IMavenConstants.POM_FILE_NAME).getLocation().toFile());
        }
        throw new CoreException(
                Status.error(String.format("Project '%s' does not exists.", repository.getProject().getName())));
    }

    @Override
    public ProjectMetadata getProjectMetadata(IProgressMonitor monitor) throws CoreException {
        return ProjectMetadata.read(repository.getProject(), monitor);
    }

    @Override
    public void open(IProgressMonitor monitor) throws CoreException {
        repository.open(monitor);
        refresh(monitor);
    }

    @Override
    public void close(IProgressMonitor monitor) throws CoreException {
        repository.close(monitor);
    }

    @Override
    public void delete(IProgressMonitor monitor) throws CoreException {
        repository.delete(monitor);
    }

    @Override
    public void refresh(IProgressMonitor monitor) throws CoreException {
        var project = repository.getProject();
        new UpdateMavenProjectJob(new IProject[] { project }, false, false, false, true, true).run(monitor);
        repository.getProjectDependenciesStore().analyze(monitor);
    }

    @Override
    public void update(ProjectMetadata metadata, IProgressMonitor monitor) throws CoreException {
        var project = repository.getProject();
        var model = mavenProjectHelper.getMavenModel(project);
        var oldMetadata = getProjectMetadata(monitor);
        var artifactId = metadata.getArtifactId();
        model.setGroupId(metadata.getGroupId());
        model.setArtifactId(artifactId);
        model.setVersion(metadata.getVersion());
        model.setName(metadata.getName());
        model.setDescription(metadata.getDescription());
        model.getProperties().setProperty(ProjectDefaultConfiguration.BONITA_RUNTIME_VERSION,
                metadata.getBonitaRuntimeVersion());
        mavenProjectHelper.saveModel(project, model, false, monitor);

        updateRestApiExtension(oldMetadata, metadata, monitor);
        if (!Objects.equals(project.getName(), metadata.getArtifactId())) {
            try {
                repository.rename(model.getArtifactId(), monitor);
            } catch (InvocationTargetException | InterruptedException e) {
                throw new CoreException(Status.error("An error occured while renaming the project", e));
            }
        }
    }

    protected void updateRestApiExtension(ProjectMetadata oldMetadata,
            ProjectMetadata metadata, IProgressMonitor monitor) {
        Stream.of(repository.getProject().getWorkspace().getRoot().getProjects())
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
        return gitProjectDelegate.newConnectProviderOperation();
    }

    @Override
    public File getGitDir() {
        return gitProjectDelegate.getGitDir();
    }

    

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getAdapter(Class<T> adapter) {
        if (IProject.class.equals(adapter)) {
            return (T) repository.getProject();
        } else {
            return gitProjectDelegate.getAdapter(adapter);
        }
    }

    @Override
    public void createDefaultIgnoreFile() throws CoreException {
        gitProjectDelegate.createDefaultIgnoreFile();
    }

    @Override
    public Collection<? extends IResource> getExportableResources() {
        var resources = new ArrayList<IResource>();
        IProject project = repository.getProject();
        IFile pomFile = project
                .getFile(IMavenConstants.POM_FILE_NAME);
        if (pomFile.exists()) {
            resources.add(pomFile);
        }
        IFolder depStore = project.getFolder(LocalDependenciesStore.NAME);
        if (depStore.exists()) {
            resources.add(depStore);
        }
        IFolder resourcesFolder = project.getFolder("src/main/resources");
        if (resourcesFolder.exists()) {
            resources.add(resourcesFolder);
        }
        return resources;
    }

}
