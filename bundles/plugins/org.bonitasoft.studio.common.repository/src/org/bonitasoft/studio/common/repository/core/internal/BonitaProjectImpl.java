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
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.BuildScheduler;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.internal.team.GitProjectImpl;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.team.GitProject;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.m2e.core.MavenPlugin;

import com.google.common.base.Objects;

public class BonitaProjectImpl implements BonitaProject {

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
        var mavenFacade = MavenPlugin.getMavenProjectRegistry().getProjects().stream()
                .filter(facade -> Objects.equal(appProject, facade.getProject()))
                .findFirst()
                .orElse(null);
        if (mavenFacade != null && mavenFacade.getMavenProject() != null) {
            return mavenFacade.getMavenProject().getModel();
        }
        if (appProject.exists()
                && appProject.getFile(MavenProjectHelper.POM_FILE_NAME).exists()) {
            return MavenProjectHelper
                    .readModel(appProject.getFile(MavenProjectHelper.POM_FILE_NAME).getLocation().toFile());
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
            if(project.getLocation() != null
                    && Files.exists(project.getLocation().toFile().toPath().resolve(".project"))) {
                project.open(monitor);
            }
        }
        currentRepository().orElseThrow().open(monitor);
    }

    private Optional<IRepository> currentRepository() {
        return RepositoryManager.getInstance().getCurrentRepository();
    }

    @Override
    public void close(IProgressMonitor monitor) throws CoreException {
        var gitDir = getGitDir();
        if (gitDir.exists()) {
            try {
                newDiconnectProviderOperation().run(new NullProgressMonitor());
            } catch (InvocationTargetException | InterruptedException e) {
                BonitaStudioLog.error(e);
            }
        }
        currentRepository().orElseThrow().close(monitor);
        List<IProject> relatedProjects = getRelatedProjects();
        for (var project : relatedProjects) {
            project.close(monitor);
        }
    }

    @Override
    public void delete(IProgressMonitor monitor) throws CoreException {
        List<IProject> relatedProjects = getRelatedProjects();
        for (var project : relatedProjects) {
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

    @Override
    public IProject getExtensionsParentProject() {
        return BonitaProject.getExtensionsParentProject(getId());
    }

    @Override
    public List<IProject> getExtensionsProjects() {
        return BonitaProject.getExtensionsProjects(getId());
    }

    @Override
    public void refresh(IProgressMonitor monitor) throws CoreException {
        refresh(false, monitor);
    }

    @Override
    public void refresh(boolean updateConfiguration, IProgressMonitor monitor) throws CoreException {
        monitor.beginTask(Messages.refresh, IProgressMonitor.UNKNOWN);
        // schedule the 3 jobs immediately with the same rule to ensure sequencing
        var refreshJob = new Job("Refresh resources") {

            @Override
            protected IStatus run(IProgressMonitor monitor) {
                var accessor = BonitaProjectImpl.this.getAdapter(RepositoryAccessor.class);
                if (accessor != null) {
                    var repository = accessor.getCurrentRepository().orElse(null);
                    if (repository != null) {
                        for (var store : repository.getAllStores()) {
                            store.refresh();
                        }
                    }
                }
                return Status.OK_STATUS;
            }
        };
        var analyzeJob = new Job("Analyze project dependencies") {

            @Override
            protected IStatus run(IProgressMonitor monitor) {
                currentRepository()
                        .map(IRepository::getProjectDependenciesStore)
                        .ifPresent(depStore -> depStore.analyze(new NullProgressMonitor()));
                return Status.OK_STATUS;
            }
        };
        refreshJob.addJobChangeListener(new JobChangeAdapter() {
            @Override
            public void done(IJobChangeEvent event) {
                var updateJob = BonitaProject.updateMavenProjectsJob(getRelatedProjects(), updateConfiguration);
                updateJob.addJobChangeListener(new JobChangeAdapter() {
                    @Override
                    public void done(IJobChangeEvent event) {
                        BuildScheduler.scheduleJobWithBuildRule(analyzeJob);
                    }
                });
                BuildScheduler.scheduleJobWithBuildRule(updateJob);
            }
        });
        BuildScheduler.scheduleJobWithBuildRule(refreshJob);
    }

    @Override
    public void update(ProjectMetadata metadata, IProgressMonitor monitor) throws CoreException {
        var op = new UpdateProjectMetadataOperation(this, metadata);
        ResourcesPlugin.getWorkspace().run(op, monitor);
        this.id = metadata.getArtifactId();
    }

    @Override
    public IRunnableWithProgress newConnectProviderOperation() throws CoreException {
        var gitProject = getAdapter(GitProject.class);
        if (gitProject == null) {
            throw new CoreException(Status.error(String.format("%s is not a Git project", id)));
        }
        return gitProject.newConnectProviderOperation();
    }
    
    @Override
    public IRunnableWithProgress newDiconnectProviderOperation() throws CoreException {
        var gitProject = getAdapter(GitProject.class);
        if (gitProject == null) {
            throw new CoreException(Status.error(String.format("%s is not a Git project", id)));
        }
        return gitProject.newDiconnectProviderOperation();
    }

    @Override
    public File getGitDir() {
        var gitProject = getAdapter(GitProject.class);
        if (gitProject != null) {
            return gitProject.getGitDir();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getAdapter(Class<T> adapter) {
        var appProject = getAppProject();
        if (IProject.class.equals(adapter)) {
            return (T) appProject;
        } else if (GitProject.class.equals(adapter)) {
            return (T) new GitProjectImpl(getParentProject());
        } else if (RepositoryAccessor.class.equals(adapter)) {
            return (T) RepositoryManager.getInstance().getAccessor();
        } else if (IJavaProject.class.equals(adapter)) {
            if (appProject.exists() && appProject.isAccessible()) {
                return (T) JavaCore.create(appProject);
            }
            return null;
        } else {
            return getAdapter(GitProject.class).getAdapter(adapter);
        }
    }

    @Override
    public void createDefaultIgnoreFile() throws CoreException {
        var gitProject = getAdapter(GitProject.class);
        if (gitProject == null) {
            throw new CoreException(Status.error(String.format("%s is not a Git project", id)));
        }
        gitProject.createDefaultIgnoreFile();
    }

    @Override
    public void commitAll(String commitMessage, IProgressMonitor monitor) throws CoreException {
        var gitProject = getAdapter(GitProject.class);
        if (gitProject == null) {
            throw new CoreException(Status.error(String.format("%s is not a Git project", id)));
        }
        gitProject.commitAll(commitMessage, monitor);
    }

    @Override
    public void addModule(IProject parentProject, String module, IProgressMonitor monitor) throws CoreException {
        var parentModel = MavenProjectHelper.getMavenModel(parentProject);
        if (parentModel != null && parentModel.getModules().stream().noneMatch(module::equals)) {
            parentModel.getModules().add(module);
            MavenProjectHelper.saveModel(parentProject, parentModel, new NullProgressMonitor());
        }
    }

    @Override
    public void removeModule(IProject parentProject, String module, IProgressMonitor monitor) throws CoreException {
        var parentModel = MavenProjectHelper.getMavenModel(parentProject);
        parentModel.getModules().removeIf(module::equals);
        MavenProjectHelper.saveModel(parentProject, parentModel, new NullProgressMonitor());
        IFolder moduleFolder = parentProject.getFolder(module);
        if (moduleFolder.exists()) {
            moduleFolder.delete(true, new NullProgressMonitor());
        }
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
            var projectFile = new File(parentProject.getLocation().toFile(),
                    IProjectDescription.DESCRIPTION_FILE_NAME);
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
