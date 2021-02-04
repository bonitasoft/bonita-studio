/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.operations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.extension.ILockedResourceStatus;
import org.bonitasoft.studio.team.TeamRepositoryUtil;
import org.bonitasoft.studio.team.i18n.Messages;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.widgets.Display;
import org.eclipse.team.svn.core.connector.ISVNConnector;
import org.eclipse.team.svn.core.connector.SVNChangeStatus;
import org.eclipse.team.svn.core.connector.SVNConnectorException;
import org.eclipse.team.svn.core.connector.SVNDepth;
import org.eclipse.team.svn.core.connector.SVNEntryInfo;
import org.eclipse.team.svn.core.operation.SVNNullProgressMonitor;
import org.eclipse.team.svn.core.resource.IRepositoryLocation;
import org.eclipse.team.svn.core.svnstorage.SVNRemoteStorage;
import org.eclipse.team.svn.core.utility.FileUtility;
import org.eclipse.team.svn.core.utility.SVNUtility;

public class ScanResourcesLockOperation implements IWorkspaceRunnable, ILockedResourceStatus {

    public enum LockStatus {
        LOCALLY_LOCKED, OTHER_LOCKED, BROKEN, STOLEN, NOT_LOCKED
    }

    private boolean localyLocked;
    private boolean lockedByOther;
    private boolean broken;
    private boolean stolen;
    private LockStatus lockStatus;
    private final IResource[] resourcesToScan;
    private final Map<IResource, LockStatus> statusMap = new HashMap<IResource, LockStatus>();
    private final Map<IResource, String> lockOwners = new HashMap<IResource, String>();

    public ScanResourcesLockOperation(final IResource resourceToLock) {
        resourcesToScan = new IResource[] { resourceToLock };
    }

    public ScanResourcesLockOperation(final IResource[] resources) {
        resourcesToScan = resources;
    }

    @Override
    public void run(IProgressMonitor monitor) throws CoreException {
        if (monitor == null) {
            monitor = AbstractRepository.NULL_PROGRESS_MONITOR;
        }
        final List<SVNChangeStatus> lockStatuses = new ArrayList<SVNChangeStatus>();
        if (resourcesToScan.length != 0) {
            final IRepositoryLocation location = SVNRemoteStorage.instance().getRepositoryLocation(resourcesToScan[0]);
            final ISVNConnector proxy = location.acquireSVNProxy();
            try {
                for (final IResource resource : resourcesToScan) {
                    SVNChangeStatus[] changeStatuses = new SVNChangeStatus[0];
                    if (FileUtility.isConnected(resource)) {
                        try {
                            final SVNEntryInfo svnInfo = SVNUtility.getSVNInfo(resource.getLocation().toFile(), proxy);
                            if (svnInfo != null) {
                                changeStatuses = SVNUtility.status(proxy,
                                        FileUtility.getWorkingCopyPath(resource),
                                        SVNDepth.IMMEDIATES,
                                        ISVNConnector.Options.SERVER_SIDE,
                                        new SVNNullProgressMonitor());
                            }
                        } catch (final SVNConnectorException e) {
                            if (e.getErrorId() != 155007) {// error code == not a
                                // working copy == no locks
                                TeamRepositoryUtil.checkConnectionState(location);
                                BonitaStudioLog.error(e);
                                Display.getDefault().syncExec(new Runnable() {

                                    @Override
                                    public void run() {
                                        new BonitaErrorDialog(Display.getDefault().getActiveShell(),
                                                Messages.invalidRemoteLocationTitle,
                                                Messages.invalidRemoteLocationMsg, e).open();
                                    }
                                });

                            }
                        }
                        // filter out resources which don't have locks
                        for (final SVNChangeStatus status : changeStatuses) {
                            if (status.wcLock != null
                                    || status.reposLock != null) {
                                lockStatuses.add(status);
                            }
                        }
                    }
                }
            } finally {
                location.releaseSVNProxy(proxy);
            }

            lockStatus = LockStatus.NOT_LOCKED;
            if (!lockStatuses.isEmpty()) {
                for (final IResource resource : resourcesToScan) {
                    for (final SVNChangeStatus status : lockStatuses) {
                        final Path path = new Path(status.path);
                        final String name = path.lastSegment();
                        if (name.equals(resource.getName())) {
                            if (isLockedLocaly(status)) {
                                // locally locked
                                lockStatus = LockStatus.LOCALLY_LOCKED;
                                lockOwners.put(resource, status.wcLock.owner);
                                statusMap.put(resource, LockStatus.LOCALLY_LOCKED);
                            } else if (isLockByOther(status)) {
                                // other locked
                                lockStatus = LockStatus.OTHER_LOCKED;
                                lockOwners.put(resource, status.reposLock.owner);
                                statusMap.put(resource, LockStatus.OTHER_LOCKED);
                            } else if (isLockBroken(status)) {
                                // broken
                                lockStatus = LockStatus.BROKEN;
                                lockOwners.put(resource, status.wcLock.owner);
                                statusMap.put(resource, LockStatus.BROKEN);
                            } else if (isLockStolen(status)) {
                                // stolen
                                lockStatus = LockStatus.STOLEN;
                                lockOwners.put(resource, status.reposLock.owner);
                                statusMap.put(resource, LockStatus.STOLEN);
                            }
                        }
                    }
                }
            }
            localyLocked = lockStatus == LockStatus.LOCALLY_LOCKED;
            stolen = lockStatus == LockStatus.STOLEN;
            lockedByOther = lockStatus == LockStatus.OTHER_LOCKED;
        }
    }

    private boolean isLockStolen(final SVNChangeStatus status) {
        return status.wcLock != null
                && status.reposLock != null
                && !status.wcLock.token
                        .equals(status.reposLock.token);
    }

    private boolean isLockBroken(final SVNChangeStatus status) {
        return status.wcLock != null
                && status.reposLock == null;
    }

    private boolean isLockByOther(final SVNChangeStatus status) {
        return status.wcLock == null
                && status.reposLock != null;
    }

    private boolean isLockedLocaly(final SVNChangeStatus status) {
        return status.wcLock != null
                && status.reposLock != null
                && status.wcLock.owner
                        .equals(status.reposLock.owner)
                && status.wcLock.token
                        .equals(status.reposLock.token);
    }

    /**
     * @return the localyLocked
     */
    @Override
    public boolean isLocalyLocked() {
        return localyLocked;
    }

    /**
     * @return the lockedByOther
     */
    @Override
    public boolean isLockedByOther() {
        return lockedByOther || isStolen();
    }

    public boolean isLockedByOther(final IResource resource) {
        final LockStatus status = statusMap.get(resource);
        return LockStatus.OTHER_LOCKED.equals(status);
    }

    /**
     * @return the broken
     */
    public boolean isBroken() {
        return broken;
    }

    /**
     * @return the stolen
     */
    public boolean isStolen() {
        return stolen;
    }

    /**
     * @return
     */
    @Override
    public Map<IResource, String> getLockOwners() {
        return lockOwners;
    }

    /**
     * @return the statusMap
     */
    public Map<IResource, LockStatus> getStatusMap() {
        return statusMap;
    }

    /**
     * @return
     */
    public String getLockOwner(final IResource resource) {
        return lockOwners.get(resource);
    }

}
