/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.repository;

import java.lang.reflect.InvocationTargetException;
import java.util.WeakHashMap;

import org.bonitasoft.studio.common.Pair;
import org.bonitasoft.studio.team.i18n.Messages;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.team.core.RepositoryProvider;
import org.eclipse.team.svn.core.IConnectedProjectInformation;
import org.eclipse.team.svn.core.connector.ISVNConnector;
import org.eclipse.team.svn.core.connector.SVNConnectorException;
import org.eclipse.team.svn.core.connector.SVNDepth;
import org.eclipse.team.svn.core.operation.SVNNullProgressMonitor;
import org.eclipse.team.svn.core.resource.IRepositoryLocation;
import org.eclipse.team.svn.core.svnstorage.SVNRemoteStorage;
import org.eclipse.team.svn.core.utility.SVNUtility;


public class CheckConnectionOperation implements IRunnableWithProgress {

    private final IProject project;
    private IStatus status;
    private static WeakHashMap<IRepositoryLocation, Pair<Long, IStatus>> cache = new WeakHashMap<>();

    public CheckConnectionOperation(IProject project) {
        this.project = project;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.connectingToRemote, IProgressMonitor.UNKNOWN);
        status = ValidationStatus.error("Not connected");//$NON-NLS
        final RepositoryProvider provider = RepositoryProvider.getProvider(project);
        if (provider instanceof IConnectedProjectInformation) {
            final SVNRemoteStorage storage = SVNRemoteStorage.instance();
            if (provider != null) {
                final IRepositoryLocation repositoryLocation = storage.getRepositoryLocation(project);
                final Pair<Long, IStatus> lastConnectionTry = cache.get(repositoryLocation);
                if (lastConnectionTry != null && System.currentTimeMillis() - 5000 < lastConnectionTry.getFirst()) {
                    status = lastConnectionTry.getSecond();
                } else {
                    final ISVNConnector proxy = repositoryLocation.acquireSVNProxy();
                    try {
                        SVNUtility.info(proxy, SVNUtility.getEntryRevisionReference(repositoryLocation.getRepositoryRoot()), SVNDepth.IMMEDIATES,
                                new SVNNullProgressMonitor());
                        status = ValidationStatus.ok();
                    } catch (final SVNConnectorException e) {

                    } finally {
                        repositoryLocation.releaseSVNProxy(proxy);
                    }
                    cache.put(repositoryLocation, new Pair<Long, IStatus>(System.currentTimeMillis(), status));
                }
            }
        }

    }

    public IStatus getStatus() {
        return status;
    }

}
