/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.team.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditorPlugin;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDocumentProvider.ResourceSetInfo;
import org.bonitasoft.studio.team.TeamPlugin;
import org.bonitasoft.studio.team.repository.Repository;
import org.bonitasoft.studio.team.ui.dialog.ConflictsQuestionDialog;
import org.bonitasoft.studio.team.ui.handler.SVNSelectionProvider;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.mapping.ResourceMapping;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.team.core.TeamException;
import org.eclipse.team.core.mapping.ISynchronizationContext;
import org.eclipse.team.core.subscribers.SubscriberScopeManager;
import org.eclipse.team.core.synchronize.SyncInfo;
import org.eclipse.team.internal.ui.Utils;
import org.eclipse.team.internal.ui.synchronize.RefreshModelParticipantJob;
import org.eclipse.team.svn.core.IStateFilter;
import org.eclipse.team.svn.core.connector.SVNRevision;
import org.eclipse.team.svn.core.operation.CompositeOperation;
import org.eclipse.team.svn.core.operation.local.MarkAsMergedOperation;
import org.eclipse.team.svn.core.operation.local.RevertOperation;
import org.eclipse.team.svn.core.operation.local.UpdateOperation;
import org.eclipse.team.svn.core.synchronize.UpdateSubscriber;
import org.eclipse.team.svn.core.utility.FileUtility;
import org.eclipse.team.svn.ui.SVNUIMessages;
import org.eclipse.team.svn.ui.action.local.UpdateAction;
import org.eclipse.team.svn.ui.mapping.UpdateModelParticipant;
import org.eclipse.team.svn.ui.mapping.UpdateSubscriberContext;
import org.eclipse.team.svn.ui.synchronize.action.CommitActionHelper;
import org.eclipse.team.svn.ui.utility.UIMonitorUtility;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * @author aurelie
 */
public class SharedResourceSynchronizer {

    private final IResource[] resources;
    private List<IResource> toRevert = new ArrayList<IResource>();
    private SyncInfo[] syncInfos;
    private Repository currentRepository;

    public SharedResourceSynchronizer(final IResource[] resources) {
        this.currentRepository = (Repository) RepositoryManager.getInstance().getCurrentRepository();
        this.resources = (IResource[]) new SVNSelectionProvider(currentRepository).getSelection().toList()
                .toArray(new IResource[] {});
    }

    /**
     * get conflicting resources from a resource list
     *
     * @param toFilter
     * @return
     */
    protected IResource[] getResourcesInState(
            final SyncInfo[] toFilter, final int resourceState) {
        final List<IResource> selectedResources = new ArrayList<IResource>();
        for (final SyncInfo info : toFilter) {
            if (!isProvidedScriptResource(info)) {
                if (SyncInfo.getDirection(info.getKind()) == resourceState) {
                    selectedResources.add(info.getLocal());
                }
            }
        }
        return selectedResources.toArray(new IResource[] {});
    }

    /**
     * @param info
     * @return
     */
    protected boolean isProvidedScriptResource(final SyncInfo info) {
        return info.getLocal().getProjectRelativePath().toString().contains("providedscripts")
                || info.getLocal().getProjectRelativePath().toString().contains("Provided");
    }

    public void handleConflictingResources(final IProgressMonitor monitor) {
        if (syncInfos == null) {
            synchronize();
        }
        final IResource[] conflicting = getResourcesInState(syncInfos, SyncInfo.CONFLICTING);
        if (conflicting.length > 0) {
            final IResource[] resourceToKeep = openConflictingResourceQuestion(conflicting);
            toRevert = new ArrayList<IResource>();
            markAsMerged(resourceToKeep, monitor);
            revertResources(monitor, conflicting, resourceToKeep);
        }
    }

    public void synchronize() {
        syncInfos = doSynchronizeResources();
    }

    /**
     * @return
     */
    protected SyncInfo[] doSynchronizeResources() {
        stopResourceListening();

        final Job synchronizeSVNJob = createSynchronizeSVNJob();
        synchronizeSVNJob.schedule();
        try {
            Job.getJobManager().join(RefreshModelParticipantJob.getFamily(), AbstractRepository.NULL_PROGRESS_MONITOR);
        } catch (final OperationCanceledException e) {
            BonitaStudioLog.error(e);
        } catch (final InterruptedException e) {
            BonitaStudioLog.debug(e.getMessage(), TeamPlugin.PLUGIN_ID);
        }

        startResourceListening();
        return getSyncInfos(resources,
                true,
                IResource.DEPTH_INFINITE,
                AbstractRepository.NULL_PROGRESS_MONITOR);
    }

    protected void startResourceListening() {
        for (final IEditorInput input : getOpenedEditorInput()) {
            final ResourceSetInfo resourceSetInfo = ProcessDiagramEditorPlugin.getInstance().getDocumentProvider()
                    .getResourceSetInfo(input);
            resourceSetInfo.startResourceListening();
        }
    }

    protected void stopResourceListening() {
        for (final IEditorInput input : getOpenedEditorInput()) {
            final ResourceSetInfo resourceSetInfo = ProcessDiagramEditorPlugin.getInstance().getDocumentProvider()
                    .getResourceSetInfo(input);
            resourceSetInfo.stopResourceListening();
        }
    }

    private List<IEditorInput> getOpenedEditorInput() {
        final List<IEditorInput> inputs = new ArrayList<IEditorInput>();
        if (PlatformUI.isWorkbenchRunning()
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null) {
            final IEditorReference[] editorReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                    .getEditorReferences();
            for (final IEditorReference ref : editorReferences) {
                if (ref.getEditor(false) instanceof ProcessDiagramEditor) {
                    try {
                        inputs.add(ref.getEditorInput());
                    } catch (final PartInitException e) {
                        BonitaStudioLog.error(e);
                    }
                }
            }
        }
        return inputs;
    }

    protected Job createSynchronizeSVNJob() {
        final ResourceMapping[] mappings = Utils.getResourceMappings(resources);
        final SubscriberScopeManager manager = UpdateSubscriberContext.createWorkspaceScopeManager(mappings, true,
                CommitActionHelper.isIncludeChangeSets(SVNUIMessages.ConsultChangeSets_message1));
        final UpdateSubscriberContext context = UpdateSubscriberContext.createContext(manager,
                ISynchronizationContext.THREE_WAY);
        final UpdateModelParticipant participant = new UpdateModelParticipant(context);
        return new RefreshModelParticipantJob(participant, "Synchronize SVN", "Synchronize SVN", mappings, null);
    }

    /**
     * @param monitor
     * @param conflicting
     * @param resourceToKeep
     */
    protected void revertResources(final IProgressMonitor monitor, final IResource[] conflicting,
            final IResource[] resourceToKeep) {
        if (resourceToKeep.length != conflicting.length && conflicting.length > 0) {
            for (final IResource r : conflicting) {
                toRevert.add(r);
            }
            for (final IResource notReverted : resourceToKeep) {
                toRevert.remove(notReverted);
            }
            BonitaStudioLog.info("Reverting following resources:\n" + toRevert, TeamPlugin.PLUGIN_ID);
            final RevertOperation revertOperation = new RevertOperation(toRevert.toArray(new IResource[] {}), false);
            revertOperation.run(monitor);
            BonitaStudioLog.debug(revertOperation.getStatus().getMessage(), TeamPlugin.PLUGIN_ID);
            UpdateOperation updateOperation = new UpdateOperation(toRevert.toArray(new IResource[] {}), false);
            updateOperation.run(monitor);
            BonitaStudioLog.debug(updateOperation.getStatus().getMessage(), TeamPlugin.PLUGIN_ID);
        }
    }

    protected void markAsMerged(final IResource[] resourceToKeep, final IProgressMonitor monitor) {
        if (resourceToKeep.length > 0) {
            final MarkAsMergedOperation markAsMergedOperation = new MarkAsMergedOperation(resourceToKeep, true,
                    "conflict merge:use local", true, false);
            markAsMergedOperation.run(monitor);
            BonitaStudioLog.debug(markAsMergedOperation.getStatus().getMessage(), TeamPlugin.PLUGIN_ID);
        }
    }

    protected SyncInfo[] getSyncInfos(final IResource[] resources,
            final boolean refresh, final int depth,
            final IProgressMonitor monitor) {
        final UpdateSubscriber subscriber = UpdateSubscriber.instance();
        final Set<SyncInfo> set = new HashSet<SyncInfo>();
        if (refresh) {
            try {
                /*
                 * Avoid that If the resource doesn't exist on repository a
                 * pop-up appeared, and that If the resource is deleted locally
                 * a stacktrace appeared in log
                 */
                subscriber.refresh(FileUtility.filterResources(resources,
                        IStateFilter.SF_DELETED), depth, monitor);
            } catch (final TeamException e) {
                BonitaStudioLog.error(e);
            }
        }
        try {
            collectSyncInfo(set, resources, subscriber);
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
        return set.toArray(new SyncInfo[set.size()]);
    }

    /**
     * @param resources
     * @param subscriber
     * @return
     * @throws CoreException
     */
    protected void collectSyncInfo(final Set<SyncInfo> set, final IResource[] resources, final UpdateSubscriber subscriber)
            throws CoreException {
        for (final IResource r : resources) {
            final SyncInfo syncInfo = subscriber.getSyncInfo(r);
            if (syncInfo != null) {
                set.add(syncInfo);
            }
            if (r instanceof IContainer) {
                collectSyncInfo(set, ((IContainer) r).members(), subscriber);
            }
        }
    }

    public IResource[] openConflictingResourceQuestion(
            final IResource[] resources) {
        final List<IResource> keep = new ArrayList<IResource>();
        UIMonitorUtility.getDisplay().syncExec(new Runnable() {

            @Override
            public void run() {
                final ConflictsQuestionDialog dialog = new ConflictsQuestionDialog(
                        UIMonitorUtility.getShell(), resources);
                dialog.open();
                final IResource[] resourcesToCommit = dialog
                        .getResourceToCommit();
                for (final IResource iResource : resourcesToCommit) {
                    keep.add(iResource);
                }
            }
        });
        return keep.toArray(new IResource[] {});
    }

    public List<IResource> getResourcesToRevert() {
        return toRevert;
    }

    public IResource[] getResourcesWithIncomingChanges() {
        if (syncInfos == null) {
            synchronize();
        }
        return getResourcesInState(syncInfos, SyncInfo.INCOMING);
    }

    public void update(IProgressMonitor monitor) {
        final CompositeOperation updateOperation = UpdateAction.getUpdateOperation(
                new IResource[] { currentRepository.getProject() },
                SVNRevision.HEAD);
        updateOperation.run(monitor);
        final IStatus status = updateOperation.getStatus();
        BonitaStudioLog.debug(status.getMessage(), TeamPlugin.PLUGIN_ID);
        currentRepository.repositoryUpdated(monitor);
    }
}
