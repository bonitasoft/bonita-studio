/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.jobs;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.job.WorkspaceJob;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.team.TeamPlugin;
import org.bonitasoft.studio.team.core.SharedResourceSynchronizer;
import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.team.preferences.TeamPreferencesConstants;
import org.bonitasoft.studio.team.repository.Repository;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.team.core.TeamException;
import org.eclipse.team.svn.core.resource.IRepositoryLocation;
import org.eclipse.team.svn.core.resource.events.ResourceStatesChangedEvent;
import org.eclipse.team.svn.core.svnstorage.SVNRemoteStorage;
import org.eclipse.team.svn.core.synchronize.UpdateSubscriber;

/**
 * @author Baptiste Mesta
 */
public class SharedRepositoryUpdateBackgroundJob extends WorkspaceJob {

    public static final String FAMILY = "updateWorkspaceJobFamiliy"; //$NON-NLS-1$

    private boolean reschedule = true;

    protected long repeatDelay = 0;

    private boolean isRunning = false;

    private final IRepository repository;

    @Override
    public boolean shouldSchedule() {
        return reschedule;
    }

    public void stop() {
        SVNRemoteStorage.instance().removeResourceStatesListener(ResourceStatesChangedEvent.class,
                UpdateSubscriber.instance());
        if (reschedule) {
            BonitaStudioLog.debug(getName() + " has been stopped for " + repository.getName(), TeamPlugin.PLUGIN_ID);
        }
        reschedule = false;
    }

    public SharedRepositoryUpdateBackgroundJob(final String name, final long interval, final IRepository repository) {
        super(name, repository.getProject().getWorkspace());
        repeatDelay = interval;
        this.repository = repository;
    }

    @Override
    public boolean shouldRun() {
        if (!PlatformUtil.isHeadless()) {
            if (repository.equals(RepositoryManager.getInstance().getCurrentRepository())) {
                if (repository.isShared("org.eclipse.team.svn.core.svnnature")) {
                    if (!((Repository) repository).isAutoShare()) {
                        schedule(repeatDelay);
                        return false;
                    } else {
                        return !isRunning;
                    }

                }
            }
        }
        return false;
    }

    @Override
    public boolean belongsTo(final Object family) {
        return FAMILY.equals(family);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.core.job.WorkspaceJob#runInWorkspace(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    protected IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
        if (monitor == null) {
            monitor = AbstractRepository.NULL_PROGRESS_MONITOR;
        }
        BonitaStudioLog.debug("Running " + getName() + " fo repository " + repository.getName() + "...",
                TeamPlugin.PLUGIN_ID);
        SVNRemoteStorage.instance().addResourceStatesListener(ResourceStatesChangedEvent.class, UpdateSubscriber.instance());
        isRunning = true;
        final IResource[] projects = new IResource[] { RepositoryManager.getInstance().getCurrentRepository().getProject() };
        monitor.beginTask(Messages.team_updateWorkspace, IProgressMonitor.UNKNOWN);

        IRepositoryLocation location = null;
        monitor.subTask(Messages.team_connectToRepo);
        // check repository status
        try {
            location = SVNRemoteStorage.instance().getRepositoryLocation(projects[0]);
        } catch (final Exception e) {
            TeamPlugin.getDefault().getPreferenceStore().setValue(TeamPreferencesConstants.OFFLINE, true);
            isRunning = false;
            return Status.CANCEL_STATUS;
        }
        if (location == null) {
            // repository offline
            isRunning = false;
            return Status.CANCEL_STATUS;
        }

        monitor.worked(5);
        monitor.subTask(Messages.team_synchronizeData);
        // update subscriber and check for changes
        final UpdateSubscriber update = UpdateSubscriber.instance();
        try {
            update.refresh(projects, IResource.DEPTH_INFINITE, monitor);
        } catch (final TeamException e1) {
            BonitaStudioLog.error(e1);
        }

        monitor.worked(10);
        monitor.subTask(Messages.team_checkConflicts);
        final SharedResourceSynchronizer sharedResourceSynchronizer = new SharedResourceSynchronizer(
                new IResource[] { repository.getProject() });
        sharedResourceSynchronizer.synchronize();
        sharedResourceSynchronizer.handleConflictingResources(monitor);
        monitor.subTask(Messages.team_updateWorkspace);
        // update updatable resources
        sharedResourceSynchronizer.update(monitor);

        monitor.done();
        isRunning = false;
        BonitaStudioLog.debug(getName() + " has finished for " + repository.getName(), TeamPlugin.PLUGIN_ID);
        cancel();
        schedule(repeatDelay);
        return Status.OK_STATUS;
    }

}
