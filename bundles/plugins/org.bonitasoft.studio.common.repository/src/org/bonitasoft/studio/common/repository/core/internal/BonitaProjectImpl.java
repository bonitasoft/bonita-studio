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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.RestAPIExtensionNature;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectDefaultConfiguration;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.store.LocalDependenciesStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.egit.core.RepositoryUtil;
import org.eclipse.egit.core.internal.util.ResourceUtil;
import org.eclipse.egit.core.op.ConnectProviderOperation;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.m2e.core.internal.IMavenConstants;

public class BonitaProjectImpl implements BonitaProject {

    private static final String BONITA_VERSION_PROPERTY = "bonita.version";
    private static final String BONITA_RUNTIME_VERSION_PROPERTY = "bonita-runtime.version";
    private static final String GITIGNORE_TEMPLATE = ".gitignore.template";

    protected IRepository repository;
    private MavenProjectHelper mavenProjectHelper = new MavenProjectHelper();

    public BonitaProjectImpl(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public String getName() {
        return repository.getName();
    }

    @Override
    public String getId() {
        try {
            return getMavenModel().getArtifactId();
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
            return getName();
        }
    }

    @Override
    public String getDisplayName() {
        try {
            return getMavenModel().getName();
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
            return getName();
        }
    }

    private Model getMavenModel() throws CoreException {
        return MavenProjectHelper.readModel(repository.getProject().getFile("pom.xml").getLocation().toFile());
    }

    @Override
    public ProjectMetadata getProjectMetadata(IProgressMonitor monitor) {
        return ProjectMetadata.read(repository.getProject(), monitor);
    }

    @Override
    public void open(IProgressMonitor monitor) throws CoreException {
        repository.open(monitor);
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
    public void update(ProjectMetadata metadata, IProgressMonitor monitor) throws CoreException {
        var project = repository.getProject();
        var model = mavenProjectHelper.getMavenModel(project);
        var artifactId = metadata.getArtifactId();
        model.setGroupId(metadata.getGroupId());
        model.setArtifactId(artifactId);
        model.setVersion(metadata.getVersion());
        model.setName(metadata.getName());
        model.setDescription(metadata.getDescription());
        model.getProperties().setProperty(ProjectDefaultConfiguration.BONITA_RUNTIME_VERSION,
                metadata.getBonitaRuntimeVersion());
        mavenProjectHelper.saveModel(project, model, false, monitor);

        updateRestApiExtensionBontitaRuntimeVersion(metadata.getBonitaRuntimeVersion(), monitor);
        if (!Objects.equals(project.getName(), metadata.getArtifactId())) {
            try {
                repository.rename(model.getArtifactId(), monitor);
            } catch (InvocationTargetException | InterruptedException e) {
                throw new CoreException(Status.error("An error occured while renaming the project", e));
            }
        }
    }

    protected void updateRestApiExtensionBontitaRuntimeVersion(String newBonitaRuntimeVersion,
            IProgressMonitor monitor) {
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
                                    newBonitaRuntimeVersion);
                        }
                        if (properties.containsKey(BONITA_VERSION_PROPERTY)) {
                            properties.setProperty(BONITA_VERSION_PROPERTY,
                                    newBonitaRuntimeVersion);
                        }
                        mavenProjectHelper.saveModel(p, mavenModel, false, monitor);
                    } catch (CoreException e) {
                        BonitaStudioLog.error(e);
                    }
                });
    }

    @Override
    public IRunnableWithProgress newConnectProviderOperation() throws CoreException {
        return connectProviderOperation(repository.getProject());
    }

    protected IRunnableWithProgress connectProviderOperation(IProject project) {
        var op = new ConnectProviderOperation(project);
        List<IProject> embeddedProjects = findEmbeddedProjects(project);
        return new IRunnableWithProgress() {

            @Override
            public void run(final IProgressMonitor monitor)
                    throws InvocationTargetException {
                File gitDir = new File(project.getLocation().toFile().getAbsolutePath(),
                        Constants.DOT_GIT);
                try (Repository repository = FileRepositoryBuilder
                        .create(gitDir)) {
                    repository.create();
                    if (!gitDir.toString().contains("..")) //$NON-NLS-1$
                        project.refreshLocal(IResource.DEPTH_ONE,
                                new NullProgressMonitor());
                    RepositoryUtil.INSTANCE.addConfiguredRepository(gitDir);
                    var gitIgnore = project.getFile(Constants.GITIGNORE_FILENAME);
                    if (!gitIgnore.exists()) {
                        gitIgnore.create(new ByteArrayInputStream(new byte[0]), true, new NullProgressMonitor());
                    }
                    op.execute(monitor);
                    for (IProject project : embeddedProjects) {
                        ConnectProviderOperation connectProviderOperation = new ConnectProviderOperation(project,
                                gitDir);
                        connectProviderOperation.execute(monitor);
                    }
                } catch (CoreException | IOException ce) {
                    throw new InvocationTargetException(ce);
                }
            }
        };
    }

    protected List<IProject> findEmbeddedProjects(IProject project) {
        IPath parentLocation = project.getLocation();
        return Stream.of(project.getWorkspace().getRoot().getProjects())
                .filter(Objects::nonNull)
                .filter(p -> !project.equals(p))
                .filter(p -> p.getLocation() != null)
                .filter(p -> parentLocation.isPrefixOf(p.getLocation()))
                .collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getAdapter(Class<T> adapter) {
        if (Repository.class.equals(adapter)) {
            return (T) ResourceUtil.getRepository(repository.getProject());
        }else if(IProject.class.equals(adapter)) {
            return (T) repository.getProject();
        }
        return null;
    }

    @Override
    public void createDefaultIgnoreFile() throws CoreException {
        var project = repository.getProject();
        IFile gitIgnoreFile = project.getFile(Constants.DOT_GIT_IGNORE);
        try (var content = FileLocator
                .toFileURL(BonitaProjectImpl.class.getResource(GITIGNORE_TEMPLATE)).openStream()) {
        if (gitIgnoreFile.exists()) {
            gitIgnoreFile.setContents(content, true, true, new NullProgressMonitor());
        } else {
            gitIgnoreFile.create(content, true, new NullProgressMonitor());
        }
        }catch (IOException e) {
            throw new CoreException(Status.error("Failed create parent project .gitignore file.", e));
        }
        
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
        if(resourcesFolder.exists()) {
            resources.add(resourcesFolder);
        }
        return resources;
    }

}
