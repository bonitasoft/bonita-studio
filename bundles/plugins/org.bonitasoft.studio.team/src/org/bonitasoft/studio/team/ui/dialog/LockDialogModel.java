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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.team.ui.dialog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.team.core.SVNLockManager;
import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.team.operations.ScanResourcesLockOperation.LockStatus;
import org.bonitasoft.studio.team.repository.Repository;
import org.bonitasoft.studio.team.ui.provider.TeamRepositoryLabelDecorator;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.team.svn.core.resource.IRepositoryResource;
import org.eclipse.team.svn.core.svnstorage.SVNRemoteStorage;

/**
 * @author Romain Bioteau
 *
 */
public class LockDialogModel {

    private final List<IRepositoryFileStore> resourcesToLock = new ArrayList<IRepositoryFileStore>();
    private final List<IRepositoryFileStore> resourcesToUnlock = new ArrayList<IRepositoryFileStore>();
    private final SVNLockManager lockManager;
    private final TeamRepositoryLabelDecorator decorator;
    private final Repository sharedRepository;

    public LockDialogModel(final Repository repository, final SVNLockManager lockManager, final TeamRepositoryLabelDecorator decorator) {
        this.lockManager = lockManager;
        this.decorator = decorator;
        sharedRepository = repository;
    }

    public List<IRepositoryFileStore> getResourcesToUnlock() {
        return resourcesToUnlock;
    }

    public List<IRepositoryFileStore> getResourcesToLock() {
        return resourcesToLock;
    }

    protected void addResourceToUnlock(final IRepositoryFileStore resource) {
        if (!resourcesToUnlock.contains(resource)) {
            resourcesToUnlock.add(resource);
        }
    }

    protected void addResourceToLock(final IRepositoryFileStore resource) {
        if (!resourcesToLock.contains(resource)) {
            resourcesToLock.add(resource);
        }
    }

    private void runLockResourceOperation() {
        try {
            for (final IRepositoryFileStore fStore : resourcesToLock) {
                lockManager.lock(fStore);
            }
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
    }

    private void runReleaseLockAResourceOperation() {
        try {
            for (final IRepositoryFileStore fStore : resourcesToUnlock) {
                lockManager.releaseLock(fStore);
                final Set<IResource> resources = new HashSet<IResource>();
                resources.add(fStore.getResource());
                resources.addAll(fStore.getRelatedResources());
                lockManager.runBreakLockOperation(resources.toArray(new IResource[resources.size()]));
            }
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
    }

    public void applyChanges() {
        runReleaseLockAResourceOperation();
        runLockResourceOperation();
        lockManager.refreshLocks();
    }

    public void handleLockSelection(final IRepositoryFileStore fileStore) {
        final IResource resource = fileStore.getResource();
        addResourceToLock(fileStore);
        decorator.updateLockStatus(resource, LockStatus.LOCALLY_LOCKED);
        decorator.updateLockOwner(resource, sharedRepository.getUser());
    }


    public void handleUnlockSelection(final IRepositoryFileStore fileStore) {
        final IResource resource = fileStore.getResource();
        final IRepositoryResource repResource = asRepositoryFile(resource);
        if (repResource != null && !isResourceIsOpened(fileStore)) {
            addResourceToUnlock(fileStore);
            decorator.updateLockStatus(resource, LockStatus.NOT_LOCKED);
        } else if (repResource != null && isResourceIsOpened(fileStore)) {
            if (openEditModeConfirmation(fileStore)) {
                addResourceToUnlock(fileStore);
                addResourceToLock(fileStore);
                decorator.updateLockStatus(resource, LockStatus.LOCALLY_LOCKED);
                decorator.updateLockOwner(resource, sharedRepository.getUser());
            }
        } else {
            decorator.updateLockStatus(resource, LockStatus.NOT_LOCKED);
        }
    }

    protected IRepositoryResource asRepositoryFile(final IResource resource) {
        return SVNRemoteStorage.instance().asRepositoryResource(resource);
    }

    protected boolean openEditModeConfirmation(final IRepositoryFileStore fileStore) {
        return MessageDialog.openConfirm(Display.getCurrent().getActiveShell(), Messages.openInEditModeConfirmationTitle,
                Messages.bind(Messages.openInEditModeConfirmationMessage, getLockOwner(fileStore.getResource()),
                        fileStore.getName()));
    }

    protected boolean isResourceIsOpened(final IRepositoryFileStore artifact) {
        return PlatformUtil.getOpenEditor(artifact.getName()) != null;
    }

    public LockStatus getLockStatus(final IResource resource) {
        return decorator.getLockStatus(resource);
    }

    public IRepository getSharedRepository() {
        return sharedRepository;
    }

    public String getLockOwner(final IResource resource) {
        return decorator.getLockOwner(resource);
    }

    public TeamRepositoryLabelDecorator getDecorator() {
        return decorator;
    }

    public boolean isLockEnabled(final IStructuredSelection selection) {
        if (selection == null) {
            return false;
        }
        final Object elem = selection.getFirstElement();
        if (elem instanceof IRepositoryFileStore) {
            final IResource resource = ((IRepositoryFileStore) elem).getResource();
            if (resource != null) {
                final LockStatus status = getLockStatus(resource);
                return SVNLockManager.canBeLocked(status);
            }
        }
        return false;
    }

    public boolean isUnLockEnabled(final IStructuredSelection selection) {
        if (selection == null) {
            return false;
        }
        final Object elem = selection.getFirstElement();
        if (elem instanceof IRepositoryFileStore) {
            final IResource resource = ((IRepositoryFileStore) elem).getResource();
            if (resource != null) {
                final LockStatus status = getLockStatus(resource);
                if (!isResourceIsOpened((IRepositoryFileStore) elem) && LockStatus.LOCALLY_LOCKED == status) {
                    return true;
                }
                return SVNLockManager.canBeUnlocked(status);
            }
        }
        return false;
    }
}
