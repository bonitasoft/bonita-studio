/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.team.core;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.extension.IGetLockStatusOperation;
import org.bonitasoft.studio.common.repository.extension.ILockedResourceStatus;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.team.operations.LockResourceOperation;
import org.bonitasoft.studio.team.operations.ReleaseResourcesLockOperation;
import org.bonitasoft.studio.team.operations.ScanResourcesLockOperation;
import org.bonitasoft.studio.team.operations.ScanResourcesLockOperation.LockStatus;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.team.svn.core.operation.remote.BreakLockOperation;
import org.eclipse.team.svn.core.resource.IRepositoryFile;
import org.eclipse.team.svn.core.resource.IRepositoryLocation;
import org.eclipse.team.svn.core.resource.IRepositoryResource;
import org.eclipse.team.svn.core.svnstorage.SVNRemoteStorage;
import org.eclipse.team.svn.core.utility.FileUtility;

public class SVNLockManager implements IGetLockStatusOperation {

    private DiagramRepositoryStore store;

    public SVNLockManager() {
        //Leave empty constructor for Extension Point
    }

    public SVNLockManager(final DiagramRepositoryStore store) {
        this.store = store;
    }

    public void releaseLock(final IResource resource) throws CoreException {
        if (resource != null) {
            resource.getWorkspace().run(new ReleaseResourcesLockOperation(resource), AbstractRepository.NULL_PROGRESS_MONITOR);
        }
    }

    public void releaseLock(final IResource[] resources) throws CoreException {
        if (resources != null) {
            ResourcesPlugin.getWorkspace().run(new ReleaseResourcesLockOperation(resources), AbstractRepository.NULL_PROGRESS_MONITOR);
        }
    }

    public void lock(final IResource resource) {
        try {
            ResourcesPlugin.getWorkspace().run(new LockResourceOperation(resource), AbstractRepository.NULL_PROGRESS_MONITOR);
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
    }

    public void lock(final IResource[] resources) throws CoreException {
        if (resources != null) {
            final LockResourceOperation releaseLockOperation = new LockResourceOperation(resources);
            ResourcesPlugin.getWorkspace().run(releaseLockOperation, AbstractRepository.NULL_PROGRESS_MONITOR);
        }
    }

    public void lock(final IRepositoryFileStore fileStore) throws CoreException {
        final Set<IResource> resourcesToLock = new HashSet<IResource>();
        resourcesToLock.add(fileStore.getResource());
        resourcesToLock.addAll(fileStore.getRelatedResources());
        lock(resourcesToLock.toArray(new IResource[resourcesToLock.size()]));
    }

    public void releaseLock(final IRepositoryFileStore fileStore) throws CoreException {
        final Set<IResource> resourcesToLock = new HashSet<IResource>();
        resourcesToLock.add(fileStore.getResource());
        resourcesToLock.addAll(fileStore.getRelatedResources());
        releaseLock(resourcesToLock.toArray(new IResource[resourcesToLock.size()]));
    }

    public void releaseLock(final IResource resource, final boolean isAutoShared, Display display) throws CoreException {
        if (!isAutoShared) {
            display.asyncExec(new Runnable() {

                @Override
                public void run() {
                    if (openReleaseLockQuestion(resource)) {
                        try {
                            releaseLock(resource);
                        } catch (final CoreException e) {
                            BonitaStudioLog.error(e);
                        }
                    }
                }
            });
        } else {
            releaseLock(resource);
        }
    }


    protected boolean openReleaseLockQuestion(final IResource resource) {
        return MessageDialog.openQuestion(Display.getDefault().getActiveShell(), Messages.releaseLockQuestionTitle,
                Messages.bind(Messages.releaseLockQuestionMessage, resource.getName()));
    }

    public void runBreakLockOperation(final IRepositoryResource repResource) {
        if (repResource != null) {
            final IRepositoryResource[] resources = new IRepositoryResource[] { repResource };
            final BreakLockOperation mainOp = new BreakLockOperation(resources);
            mainOp.run(AbstractRepository.NULL_PROGRESS_MONITOR);
        }
    }

    public ScanResourcesLockOperation runScanResourcesLockOperation(final IResource resource) throws CoreException, InvocationTargetException {
        return runScanResourcesLockOperation(new IResource[] { resource }, AbstractRepository.NULL_PROGRESS_MONITOR);
    }

    /**
     * @param allResources
     * @param monitor
     * @return
     * @throws InvocationTargetException
     */
    public ScanResourcesLockOperation runScanResourcesLockOperation(final IResource[] allResources, final IProgressMonitor monitor)
            throws InvocationTargetException {
        final ScanResourcesLockOperation scanOp = new ScanResourcesLockOperation(allResources);
        try {
            ResourcesPlugin.getWorkspace().run(scanOp, monitor);
        } catch (final CoreException e1) {
            throw new InvocationTargetException(e1);
        }
        return scanOp;
    }


    public void releaseAllLocks(final Map<IResource, LockStatus> lockStatus, final IProgressMonitor monitor) throws ExecutionException {
        for (final IResource resource : lockStatus.keySet()) {
            final IRepositoryLocation location = SVNRemoteStorage.instance().getRepositoryLocation(resource.getProject());
            final IRepositoryFile repResource = location.getRoot().asRepositoryFile(location.getRoot().getUrl()
                    + "/trunk"
                    + "/" + resource.getProjectRelativePath().toString(), false);
            if (repResource != null) {
                final BreakLockOperation mainOp = new BreakLockOperation(new IRepositoryResource[] { repResource });
                mainOp.run(monitor);
            }
        }
    }

    public void refreshLocks() {
        for (final DiagramFileStore f : store.getChildren()) {
            refreshLock(f);
        }
    }

    public synchronized void refreshLock(final IRepositoryFileStore fileStore) {
        if (fileStore instanceof DiagramFileStore) {
            final DiagramFileStore diagramFileStore = (DiagramFileStore) fileStore;
            if (diagramFileStore.isOpened()) {
                try {
                    final IFile resource = diagramFileStore.getResource();
                    final ScanResourcesLockOperation scanResourcesLockOperation = runScanResourcesLockOperation(resource);
                    final LockStatus lockStatus = scanResourcesLockOperation.getStatusMap().get(resource);
                    final boolean isReadOnly = canBeUnlocked(lockStatus);
                    Display.getDefault().asyncExec(new Runnable() {

                        @Override
                        public void run() {
                            diagramFileStore.setReadOnly(isReadOnly);
                            if (!isReadOnly) {
                                lock(resource);
                            }
                        }
                    });
                } catch (final InvocationTargetException e) {
                    BonitaStudioLog.error(e);
                } catch (final CoreException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
    }

    public static boolean canBeUnlocked(final LockStatus status) {
        return LockStatus.OTHER_LOCKED.equals(status) || LockStatus.STOLEN.equals(status) && status != null;
    }

    public static boolean canBeLocked(final LockStatus status) {
        return LockStatus.NOT_LOCKED.equals(status) || LockStatus.BROKEN.equals(status) || status == null;
    }


    public IStatus runBreakLockOperation(final IResource[] resources) {
        final Set<IRepositoryResource> repositoryResources = new HashSet<IRepositoryResource>();
        for (final IResource resource : resources) {
            final IRepositoryResource repositoryResource = SVNRemoteStorage.instance().asRepositoryResource(resource);
            if (repositoryResource != null) {
                repositoryResources.add(repositoryResource);
            }
        }
        final BreakLockOperation mainOp = new BreakLockOperation(repositoryResources.toArray(new IRepositoryResource[repositoryResources.size()]));
        mainOp.run(AbstractRepository.NULL_PROGRESS_MONITOR);
        return mainOp.getStatus();
    }

    @Override
    public ILockedResourceStatus scanLock(final IResource resource) throws InvocationTargetException, CoreException {
        if (FileUtility.isConnected(resource)) {
            return runScanResourcesLockOperation(resource);
        }
        return new ILockedResourceStatus() {

            @Override
            public boolean isLockedByOther() {
                return false;
            }

            @Override
            public boolean isLocalyLocked() {
                return false;
            }

            @Override
            public Map<IResource, String> getLockOwners() {
                return Collections.emptyMap();
            }
        };

    }
}
