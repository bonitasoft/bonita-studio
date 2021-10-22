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
package org.bonitasoft.studio.businessobject.core;

import javax.annotation.PostConstruct;

import org.apache.maven.artifact.Artifact;
import org.bonitasoft.studio.businessobject.core.operation.GenerateBDMOperation;
import org.bonitasoft.studio.businessobject.core.repository.BDMArtifactDescriptor;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.maven.AddDependencyOperation;
import org.bonitasoft.studio.common.repository.core.maven.RemoveDependencyOperation;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

public class UpdateProjectBDMDependency implements EventHandler {

    private RepositoryAccessor repositoryAccessor;

    @PostConstruct
    public void registerHandler(IEventBroker eventBroker, RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
        eventBroker.subscribe(GenerateBDMOperation.BDM_DEPLOYED_TOPIC, this);
    }

    @Override
    public void handleEvent(final Event event) {
        execute(event);
    }

    private void execute(final Event event) {
        final BDMArtifactDescriptor bdmArtifactDescriptor = (BDMArtifactDescriptor) event
                .getProperty(GenerateBDMOperation.BDM_ARTIFACT_DESCRIPTOR);

        var removeBDMClientDependencyOperation = new RemoveDependencyOperation(
                bdmArtifactDescriptor.getGroupId(),
                GenerateBDMOperation.BDM_CLIENT,
                bdmArtifactDescriptor.getVersion(),
                Artifact.SCOPE_PROVIDED);
        var addBDMClientDependencyOperation = new AddDependencyOperation(
                bdmArtifactDescriptor.getGroupId(),
                GenerateBDMOperation.BDM_CLIENT,
                bdmArtifactDescriptor.getVersion(),
                Artifact.SCOPE_PROVIDED);

        try {
            // Force a proper java project update by remove/adding the dependency in the project 
            removeBDMClientDependencyOperation.run(new NullProgressMonitor());
            addBDMClientDependencyOperation.run(new NullProgressMonitor());
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
        }
        
        new WorkspaceJob("Update Project BDM dependency") {

            @Override
            public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
                // Update bdm model and dao list cache
                var currentRepository = repositoryAccessor.getCurrentRepository();
                BusinessObjectModelRepositoryStore businessObjectModelRepositoryStore = currentRepository
                        .getRepositoryStore(BusinessObjectModelRepositoryStore.class);
                businessObjectModelRepositoryStore.allBusinessObjectDao(currentRepository.getJavaProject());
                return Status.OK_STATUS;
            }
        }.schedule();
    }

}
