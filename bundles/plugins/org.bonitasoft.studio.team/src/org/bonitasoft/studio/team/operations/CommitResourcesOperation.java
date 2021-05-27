/*******************************************************************************
 * Copyright (C) 2009, 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.team.TeamPlugin;
import org.bonitasoft.studio.team.core.SVNLockManager;
import org.bonitasoft.studio.team.core.SharedResourceSynchronizer;
import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.team.repository.Repository;
import org.bonitasoft.studio.team.ui.handler.RepositoryRecursiveTeamAction;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.team.svn.core.operation.CompositeOperation;
import org.eclipse.team.svn.core.operation.local.RefreshResourcesOperation;
import org.eclipse.team.svn.core.operation.local.UpdateOperation;
import org.eclipse.team.svn.ui.utility.CommitActionUtility;

public class CommitResourcesOperation implements IRunnableWithProgress {

    private final IResource[] resource;
    private final String commitMessage;
    private boolean breakLocks = false;
    private SVNLockManager lockManager;
    private final SharedResourceSynchronizer sharedResourceSynchronizer;

    /**
     * @param resource
     */
    public CommitResourcesOperation(final IResource resource, final String commitMessage, final SVNLockManager lockManager) {
        this(new IResource[] { resource }, commitMessage, lockManager);
    }

    /**
     * @param resource
     * @param lockManager
     */
    public CommitResourcesOperation(final IResource[] resource, final String commitMessage,
            final SVNLockManager lockManager) {
        this.resource = resource;
        this.commitMessage = commitMessage != null ? commitMessage : "";
        sharedResourceSynchronizer = new SharedResourceSynchronizer(resource);
        this.lockManager = lockManager;
    }

    public boolean breakLocks() {
        return breakLocks;
    }

    /**
     * Force the commit of all resources, releasing all locks
     *
     * @param breakLocks
     */
    public void setBreakLocks(final boolean breakLocks) {
        this.breakLocks = breakLocks;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        if (monitor == null) {
            monitor = AbstractRepository.NULL_PROGRESS_MONITOR;
        }
        final List<IResource> toCommit = getResourcesToCommit();
        final IRepository currentRepo = getCurrentRepository();

        manageConflictingCommit(monitor, toCommit);

        final CommitActionUtility commitUtils = getCommitActionUtility(toCommit);
        final IResource[] allResources = commitUtils.getAllResources();
        boolean canCommit = true;
        if (allResources.length > 0) {
            canCommit = canCommit(monitor, currentRepo, allResources);
            if (canCommit) {
                commitResource(monitor, commitUtils, allResources);
            }
        }
        if (canCommit) {
            if (currentRepo instanceof Repository) {
                ((Repository) currentRepo).startUpdateJob();
            }
        }

    }

    /**
     * @param monitor
     * @param currentRepo
     * @param allResources
     * @throws InvocationTargetException
     */
    protected boolean canCommit(final IProgressMonitor monitor, final IRepository currentRepo,
            final IResource[] allResources) throws InvocationTargetException {
        final ScanResourcesLockOperation scanOp = lockManager.runScanResourcesLockOperation(allResources, monitor);
        if (breakLocks) {
            try {
                lockManager.releaseAllLocks(scanOp.getStatusMap(), monitor);
            } catch (final ExecutionException e) {
                BonitaStudioLog.error(e);
            }
        } else {
            if (scanOp.isLockedByOther()) {
                displayLockResourceWarning(allResources, scanOp);
                if (currentRepo instanceof Repository) {
                    ((Repository) currentRepo).startUpdateJob();
                }

                return false;
            }
        }
        return true;
    }

    /**
     * @param monitor
     * @param commitUtils
     * @param allResources
     */
    protected void commitResource(final IProgressMonitor monitor, final CommitActionUtility commitUtils,
            final IResource[] allResources) {
        final CompositeOperation op = runCompositeCommitOperation(commitUtils, allResources, monitor);
        if (!op.getStatus().isOK()) {
            displayCommitFailedError(op);
        } else {
            BonitaStudioLog.debug(op.getStatus().getMessage(), TeamPlugin.PLUGIN_ID);
        }
    }

    /**
     * @param commitUtils
     * @param allResources
     * @return
     */
    protected CompositeOperation runCompositeCommitOperation(final CommitActionUtility commitUtils,
            final IResource[] allResources,
            final IProgressMonitor monitor) {
        final CompositeOperation op = commitUtils.getCompositeCommitOperation(allResources, new IResource[0],
                new IResource[0], commitMessage, true, null,
                null, true);
        op.run(monitor);
        return op;
    }

    /**
     * @param toCommit
     */
    protected List<IResource> getResourcesToCommit() {
        final List<IResource> toCommit = new ArrayList<IResource>();
        for (final IResource iResource : resource) {
            toCommit.add(iResource);
        }
        return toCommit;
    }

    /**
     * @param toCommit
     * @return
     */
    protected CommitActionUtility getCommitActionUtility(final List<IResource> toCommit) {
        return new CommitActionUtility(new RepositoryRecursiveTeamAction(toCommit.toArray(new IResource[toCommit.size()])));
    }

    /**
     * @return
     */
    protected IRepository getCurrentRepository() {
        final IRepository currentRepo = RepositoryManager.getInstance().getCurrentRepository();
        if (currentRepo instanceof Repository) {
            ((Repository) currentRepo).stopUpdateJob();
        }
        return currentRepo;
    }

    /**
     * @param monitor
     * @param toCommit
     * @param conflicting
     */
    protected void manageConflictingCommit(final IProgressMonitor monitor, final List<IResource> toCommit) {
        sharedResourceSynchronizer.synchronize();
        sharedResourceSynchronizer.handleConflictingResources(monitor);
        final List<IResource> toRevert = sharedResourceSynchronizer.getResourcesToRevert();
        new UpdateOperation(toRevert.toArray(new IResource[] {}), false).run(monitor);
        new RefreshResourcesOperation(toRevert.toArray(new IResource[] {})).run(monitor);
    }

    /**
     * @param status
     */
    protected void displayCommitFailedError(final CompositeOperation op) {
        BonitaStudioLog.log(op.getStatus().getMessage());
        for (final IStatus status : op.getStatus().getChildren()) {
            Display.getDefault().asyncExec(new Runnable() {

                @Override
                public void run() {
                    BonitaErrorDialog.openError(Display.getDefault().getActiveShell(),
                            Messages.commitFailedTitle,
                            Messages.commitFailedMessage,
                            status);
                }
            });
            BonitaStudioLog.log(status.getMessage());
        }
    }

    /**
     * @param allResources
     * @param scanOp
     */
    protected void displayLockResourceWarning(final IResource[] allResources, final ScanResourcesLockOperation scanOp) {
        Display.getDefault().asyncExec(new Runnable() {

            @Override
            public void run() {
                MessageDialog.openWarning(Display.getDefault().getActiveShell(), Messages.lockedResourceTitle,
                        getLockedResourceMessage(scanOp, allResources));

            }
        });
    }

    protected String getLockedResourceMessage(final ScanResourcesLockOperation scanOp, final IResource[] resources) {
        final StringBuilder sb = new StringBuilder();
        sb.append(Messages.lockedResourceMsg + "\n");
        for (final IResource resource : resources) {
            if (scanOp.getLockOwner(resource) != null) {
                sb.append(resource.getName() + " (" + scanOp.getLockOwner(resource) + ")\n");
            } else {
                sb.append(resource.getName() + "\n");
            }
        }
        return sb.toString();
    }

    protected void setLockManager(final SVNLockManager lockManager) {
        this.lockManager = lockManager;
    }

}
