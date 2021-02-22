/**
 * Copyright (C) 2011-2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.diagram.custom.repository;

import java.util.Set;

import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationUtil;

/**
 * @author Romain Bioteau
 */
public class PoolNotificationListener extends AdapterImpl implements NotificationListener {

    @Override
    public void notifyChanged(final Notification notification) {
        final ProcessConfigurationRepositoryStore processConfStore = RepositoryManager.getInstance().getRepositoryStore(
                ProcessConfigurationRepositoryStore.class);
        final DiagramRepositoryStore diagramStore = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);

        // Listen for changes to features.
        switch (notification.getFeatureID(AbstractProcess.class)) {
            case ProcessPackage.ABSTRACT_PROCESS__ELEMENTS:
                if (NotificationUtil.isElementAddedToSlot(notification) || NotificationUtil.isElementRemovedFromSlot(notification)) {
                    final Object newValue = notification.getNewValue();
                    if (newValue instanceof Pool) { //Pool added
                        handlePoolAdded(notification, processConfStore);
                    } else if (newValue == null && notification.getOldValue() instanceof Pool) {//Pool removed
                        handlePoolRemoved(processConfStore, diagramStore);
                    }
                }
                break;
        }
    }

    protected IWorkspaceRunnable handlePoolRemoved(final ProcessConfigurationRepositoryStore processConfStore,
            final DiagramRepositoryStore diagramStore) {
        return new IWorkspaceRunnable() {

            @Override
            public void run(final IProgressMonitor arg0) throws CoreException {
                final Set<String> poolIds = diagramStore.getAllProcessIds();
                for (final IRepositoryFileStore file : processConfStore.getChildren()) {
                    String id = file.getName();
                    id = id.substring(0, id.lastIndexOf("."));
                    if (!poolIds.contains(id)) {
                        file.delete();
                    }
                }
            }
        };

    }

    protected IWorkspaceRunnable handlePoolAdded(final Notification notification,
            final ProcessConfigurationRepositoryStore processConfStore) {
        return new IWorkspaceRunnable() {

            @Override
            public void run(final IProgressMonitor arg0) throws CoreException {
                final Pool pool = (Pool) notification.getNewValue();
                pool.eAdapters().add(PoolNotificationListener.this);

                // check that process is not empty
                final String processUUID = ModelHelper.getEObjectID(pool);
                final IRepositoryFileStore confFile = processConfStore.createRepositoryFileStore(processUUID + ".conf");
                final Configuration conf = ConfigurationFactory.eINSTANCE.createConfiguration();
                conf.setVersion(ModelVersion.CURRENT_DIAGRAM_VERSION);
                confFile.save(conf);
            }
        };

    }

}
