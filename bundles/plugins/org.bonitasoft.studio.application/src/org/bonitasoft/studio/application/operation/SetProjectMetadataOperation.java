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
import java.util.Objects;

import org.apache.maven.model.Model;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
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
    private ProjectMetadata meatadata;

    public SetProjectMetadataOperation(ProjectMetadata meatadata, 
            RepositoryAccessor repositoryAccessor,
            MavenProjectHelper mavenProjectHelper) {
        this.meatadata = meatadata;
        this.repositoryAccessor = repositoryAccessor;
        this.mavenProjectHelper = mavenProjectHelper;
    }
    
    public SetProjectMetadataOperation createNewProject() {
        this.createNewProject = true;
        return this;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.updatingProjectMetadata, IProgressMonitor.UNKNOWN);
        AbstractRepository repository = repositoryAccessor.getCurrentRepository();
            try {
                if (createNewProject) {
                    repositoryAccessor.createNewRepository(meatadata, monitor);
                } else { 
                    IProject project = repository.getProject();
                    Model model = mavenProjectHelper.getMavenModel(project);
                    boolean nameChanged = !Objects.equals(model.getName(), meatadata.getName());
                    model.setName(meatadata.getName());
                    model.setDescription(meatadata.getDescription());
                    model.setGroupId(meatadata.getGroupId());
                    model.setArtifactId(meatadata.getArtifactId());
                    model.setVersion(meatadata.getVersion());
                    mavenProjectHelper.saveModel(project, model);
                    if (nameChanged) {
                        repository.rename(model.getName(), monitor);
                    }
                }
            } catch (CoreException e) {
                status = e.getStatus();
            }
       
    }

    public IStatus getStatus() {
        return status;
    }

}
