/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.businessobject.core;

import jakarta.annotation.PostConstruct;

import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.event.BdmEvents;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.maven.AddDependencyOperation;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

public class DeployedBDMEventHandler implements EventHandler {

	@PostConstruct
	public void registerHandler(IEventBroker eventBroker) {
		eventBroker.subscribe(BdmEvents.BDM_DEPLOYED_TOPIC, this);
	}

	@Override
	public void handleEvent(final Event event) {
		var dependency = event.getProperty(BdmEvents.DEPENDENCY_PROPERTY);
		if (dependency instanceof Dependency) {
			var parametrized = parametrized((Dependency) dependency);
			var addBDMClientDependencyOperation = new AddDependencyOperation(parametrized);
			try {
				addBDMClientDependencyOperation.disableAnalyze().run(new NullProgressMonitor());
				var project = RepositoryManager.getInstance().getCurrentProject();
				project.ifPresent(p -> {
					try {
						p.getAppProject().build(IncrementalProjectBuilder.INCREMENTAL_BUILD, new NullProgressMonitor());
					} catch (CoreException e) {
						BonitaStudioLog.error(e);
					}
				});
			} catch (CoreException e) {
				BonitaStudioLog.error(e);
			}

			new WorkspaceJob("Update Project BDM dependency") {

				@Override
				public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
					// Update bdm model and dao list cache
					RepositoryManager.getInstance().getCurrentRepository()
							.map(r -> r.getRepositoryStore(BusinessObjectModelRepositoryStore.class))
							.ifPresent(BusinessObjectModelRepositoryStore::updateBusinessObjectDao);
					return Status.OK_STATUS;
				}
			}.schedule();
		}
	}

	private static Dependency parametrized(Dependency modelMavenDependency) {
		var dep = modelMavenDependency.clone();
		dep.setGroupId("${project.groupId}");
		dep.setVersion("${project.version}");
		return dep;
	}

}
