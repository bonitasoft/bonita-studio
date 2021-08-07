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
package org.bonitasoft.studio.application.operation;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.ProjectDependenciesStore;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.preferences.RepositoryPreferenceConstant;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableWithProgress;

public class SetProjectMetadataOperation implements IRunnableWithProgress {

    private MavenProjectHelper mavenProjectHelper;
    private IStatus status = Status.OK_STATUS;
    private boolean createNewProject = false;
    private RepositoryAccessor repositoryAccessor;
    private ProjectMetadata metadata;
    private List<Dependency> dependencies = new ArrayList<>();

    public SetProjectMetadataOperation(ProjectMetadata metadata,
            RepositoryAccessor repositoryAccessor,
            MavenProjectHelper mavenProjectHelper) {
        this.metadata = metadata;
        this.repositoryAccessor = repositoryAccessor;
        this.mavenProjectHelper = mavenProjectHelper;
    }

    public SetProjectMetadataOperation createNewProject() {
        this.createNewProject = true;
        return this;
    }

    public SetProjectMetadataOperation additionalDependencies(List<Dependency> dependencies) {
        this.dependencies = dependencies;
        return this;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.updatingProjectMetadata, IProgressMonitor.UNKNOWN);
        try {
            if (createNewProject) {
                createNewProject(monitor);
            } else {
                editProject(monitor);
            }
            CommonRepositoryPlugin.getDefault().getPreferenceStore()
                    .setValue(RepositoryPreferenceConstant.DEFAULT_GROUPID, metadata.getGroupId());
        } catch (CoreException e) {
            status = e.getStatus();
        }

    }

    private void editProject(IProgressMonitor monitor)
            throws CoreException, InvocationTargetException, InterruptedException {
        AbstractRepository repository = repositoryAccessor.getCurrentRepository();
        IProject project = repository.getProject();
        Model model = mavenProjectHelper.getMavenModel(project);
        boolean nameChanged = !Objects.equals(model.getName(), metadata.getName());
        model.setName(metadata.getName());
        model.setDescription(metadata.getDescription());
        model.setGroupId(metadata.getGroupId());
        String artifactId = metadata.getArtifactId();
        if(Strings.isNullOrEmpty(artifactId)) {
            artifactId = ProjectMetadata.toArtifactId(metadata.getName());
        }
        model.setArtifactId(artifactId);
        model.setVersion(metadata.getVersion());
        mavenProjectHelper.saveModel(project, model, monitor);
        if (nameChanged) {
            repository.rename(model.getName(), monitor);
        }
    }

    private void createNewProject(IProgressMonitor monitor) throws CoreException {
        IRepository newRepository = repositoryAccessor.createNewRepository(metadata, monitor);
        if (!dependencies.isEmpty()) {
            IProject project = newRepository.getProject();
            Model model = mavenProjectHelper.getMavenModel(project);
            dependencies.stream().forEach(model.getDependencies()::add);
            mavenProjectHelper.saveModel(project, model, monitor);
            ProjectDependenciesStore projectDependenciesStore = newRepository.getProjectDependenciesStore();
            if (projectDependenciesStore != null) {
                projectDependenciesStore.analyze(monitor);
            }
        }
    }

    public IStatus getStatus() {
        return status;
    }

}
