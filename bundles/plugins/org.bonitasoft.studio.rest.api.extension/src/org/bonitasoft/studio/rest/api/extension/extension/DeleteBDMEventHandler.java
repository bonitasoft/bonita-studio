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
package org.bonitasoft.studio.rest.api.extension.extension;

import jakarta.annotation.PostConstruct;

import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.common.event.BdmEvents;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.maven.RemoveDependencyOperation;
import org.bonitasoft.studio.maven.ExtensionRepositoryStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

public class DeleteBDMEventHandler implements EventHandler {

    private RepositoryAccessor accessor;

    @PostConstruct
    public void register(IEventBroker eventBroker, RepositoryAccessor accessor) {
        this.accessor = accessor;
        eventBroker.subscribe(BdmEvents.BDM_DELETED_TOPIC, this);
    }

    @Override
    public void handleEvent(final Event event) {
        var dependency = event.getProperty(BdmEvents.DEPENDENCY_PROPERTY);
        if (dependency instanceof Dependency) {
            for (var fStore : accessor.getRepositoryStore(ExtensionRepositoryStore.class).getChildren()) {
                var op = new RemoveDependencyOperation((Dependency) dependency);
                op.setProject(fStore.getProject());
                try {
                    op.disableAnalyze().run(new NullProgressMonitor());
                } catch (CoreException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
    }
}
