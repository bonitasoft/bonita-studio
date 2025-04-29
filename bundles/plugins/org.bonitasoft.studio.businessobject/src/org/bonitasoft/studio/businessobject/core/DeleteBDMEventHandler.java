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
import org.bonitasoft.studio.common.event.BdmEvents;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.maven.RemoveDependencyOperation;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

public class DeleteBDMEventHandler implements EventHandler {

    @PostConstruct
    public void registerHandler(IEventBroker eventBroker) {
        eventBroker.subscribe(BdmEvents.BDM_DELETED_TOPIC, this);
    }

    @Override
    public void handleEvent(final Event event) {
        var dependency = event.getProperty(BdmEvents.DEPENDENCY_PROPERTY);
        if (dependency instanceof Dependency) {
            var op = new RemoveDependencyOperation(parametrized((Dependency) dependency));
            try {
                op.disableAnalyze().run(new NullProgressMonitor());
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    private static Dependency parametrized(Dependency modelMavenDependency) {
        var dep = modelMavenDependency.clone();
        dep.setGroupId("${project.groupId}");
        dep.setVersion("${project.version}");
        return dep;
    }
}
