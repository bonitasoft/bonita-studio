/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.handler;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.team.core.SVNLockManager;
import org.bonitasoft.studio.team.operations.LockResourceOperation;
import org.bonitasoft.studio.team.operations.ScanResourcesLockOperation;
import org.bonitasoft.studio.team.operations.ScanResourcesLockOperation.LockStatus;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.team.svn.core.SVNMessages;
import org.eclipse.team.svn.ui.SVNTeamUIPlugin;
import org.eclipse.team.svn.ui.action.local.CommitAction;
import org.eclipse.team.svn.ui.preferences.SVNTeamPreferences;
import org.eclipse.ui.IEditorReference;

public class CommitWorkspaceHandler extends AbstractConnectedHandler {

    private final class CommitJobListener extends JobChangeAdapter {

        @Override
        public void scheduled(final IJobChangeEvent event) {
            final Job job = event.getJob();
            if (SVNMessages.Operation_Commit.equals(job.getName())) {
                isCommitScheduled = true;
            }
        }

        @Override
        public void done(final IJobChangeEvent event) {
            final Job job = event.getJob();
            if (SVNMessages.Operation_Commit.equals(job.getName())) {
                if (job.getResult().isOK()) {
                    Display.getDefault().asyncExec(getPostCommitRunnable());
                }
            }
        }
    }

    boolean isCommitScheduled = false;

    public CommitWorkspaceHandler() {
        Job.getJobManager().addJobChangeListener(new CommitJobListener());
    }

    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final CommitAction action = new CommitAction();
        action.selectionChanged(null,
                new StructuredSelection(RepositoryManager.getInstance().getCurrentRepository().getProject()));
        action.runImpl(null);
        return null;
    }

    private Runnable getPostCommitRunnable() {
        return new Runnable() {

            @Override
            public void run() {
                final SVNLockManager lockManager = new SVNLockManager(
                        RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class));
                final DiagramRepositoryStore store = RepositoryManager.getInstance().getCurrentRepository()
                        .getRepositoryStore(DiagramRepositoryStore.class);
                try {
                    final List<DiagramFileStore> diagramsToReopen = new ArrayList<DiagramFileStore>();
                    final IResource[] resources = getResourcesToScan(store, diagramsToReopen);
                    final ScanResourcesLockOperation scanOp = lockManager.runScanResourcesLockOperation(resources,
                            AbstractRepository.NULL_PROGRESS_MONITOR);
                    handleResourceLockStatus(scanOp.getStatusMap(), diagramsToReopen);
                } catch (final InvocationTargetException e) {
                    BonitaStudioLog.error(e);
                }
            }
        };

    }

    protected void handleResourceLockStatus(final Map<IResource, LockStatus> lockStatusMap,
            final List<DiagramFileStore> diagramsToReopen) {
        for (final DiagramFileStore file : diagramsToReopen) {
            final LockStatus status = lockStatusMap.get(file.getResource());
            if (notLocked(status) && isKeepLocks()) {
                lockResource(file);
            } else if (status == null || LockStatus.NOT_LOCKED.equals(status)) {
                reOpenDiagramInReadOnlyMode(file);
            }
        }
    }

    protected boolean isKeepLocks() {
        return SVNTeamUIPlugin.instance().getPreferenceStore().getBoolean(SVNTeamPreferences.COMMIT_DIALOG_KEEP_LOCKS);
    }

    protected void lockResource(final DiagramFileStore file) {
        final LockResourceOperation lockResourceOperation = new LockResourceOperation(file.getResource());
        try {
            lockResourceOperation.run(AbstractRepository.NULL_PROGRESS_MONITOR);
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
    }

    protected boolean notLocked(final LockStatus status) {
        return status == null || LockStatus.NOT_LOCKED.equals(status);
    }

    protected void reOpenDiagramInReadOnlyMode(final DiagramFileStore file) {
        file.close();
        file.setReadOnly(true);
        file.open();
    }

    protected IResource[] getResourcesToScan(final DiagramRepositoryStore store,
            final List<DiagramFileStore> diagramsToReopen) {
        final List<IResource> resources = new ArrayList<IResource>();
        for (final DiagramFileStore file : store.getChildren()) {
            if (!file.isReadOnly()) {
                if (getOpenedEditor(file) != null) {
                    resources.add(file.getResource());
                    diagramsToReopen.add(file);
                }
            }
        }
        return resources.toArray(new IResource[resources.size()]);
    }

    public IEditorReference getOpenedEditor(final DiagramFileStore file) {
        return PlatformUtil.getOpenEditor(file.getName());
    }

}
