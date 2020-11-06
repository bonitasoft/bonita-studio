/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
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
import java.util.Map;
import java.util.Map.Entry;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.team.TeamPlugin;
import org.bonitasoft.studio.team.TeamRepositoryUtil;
import org.bonitasoft.studio.team.core.SVNLockManager;
import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.team.preferences.TeamPreferencesConstants;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.team.svn.core.operation.local.RefreshResourcesOperation;
import org.eclipse.team.svn.core.operation.local.UpdateOperation;
import org.eclipse.team.svn.core.resource.IRepositoryLocation;
import org.eclipse.team.svn.core.resource.IRepositoryResource;
import org.eclipse.team.svn.ui.action.local.CommitAction;
import org.eclipse.team.svn.ui.utility.CommitActionUtility;

/**
 * @author Romain Bioteau
 */
public class MigrateRepositoryOperation implements IRunnableWithProgress {

    private final IRepositoryResource selectedWorkspace;
    private final IRepositoryLocation repoLocation;
    private final String sourceVersion;
    private IStatus status;
    private boolean saveRepoBeforeMigration = true;

    public MigrateRepositoryOperation(final IRepositoryResource selectedWorkspace, final IRepositoryLocation repoLocation,
            final String sourceVersion) {
        this.selectedWorkspace = selectedWorkspace;
        this.repoLocation = repoLocation;
        this.sourceVersion = sourceVersion;
        status = org.eclipse.core.runtime.Status.OK_STATUS;
    }

    public IStatus getStatus() {
        return status;
    }

    @Override
    public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.migrateWorkspace + " " + selectedWorkspace.getName() + "...", IProgressMonitor.UNKNOWN);
        final Map<String, Object> backUpSettings = TeamRepositoryUtil.backUpSettings();
        TeamRepositoryUtil.setApplyAllState(TeamPreferencesConstants.MANUAL);
        try {
            if (saveRepoBeforeMigration) {
                final CreateWorkspaceBackupOperation createBackupoperation = new CreateWorkspaceBackupOperation(repoLocation,
                        selectedWorkspace.getName(),
                        "Migration from " + sourceVersion + " to " + ProductVersion.CURRENT_VERSION);
                createBackupoperation.run(monitor);

            }
            final long timesatmp = System.currentTimeMillis(); //Need to be before ConnectToRepo Command
            final ConnectoToRepositoryOperation connectToRepositoryCmd = new ConnectoToRepositoryOperation(selectedWorkspace,
                    repoLocation, true);
            connectToRepositoryCmd.run(monitor);

            AbstractRepository currentRepository = RepositoryManager.getInstance().getCurrentRepository();
            try {
                currentRepository.disableBuild();
                unlockAllResources(monitor);
                IProject project = currentRepository.getProject();
                IFile lastRestoreFile = project.getFile(TeamRepositoryUtil.LEGACY_LAST_RESTORE);
                if (lastRestoreFile.exists()) {
                    IFile destination = project.getFile(TeamRepositoryUtil.LAST_RESTORE);
                    lastRestoreFile.move(destination.getProjectRelativePath(), true, null);
                }
                IFolder settings = project.getFolder(".settings");
                if (settings.exists()) {
                    settings.delete(true, null);
                }
                RepositoryManager.getInstance().getCurrentRepository().migrate(monitor);
            } catch (final CoreException e) {
                BonitaStudioLog.error(e, TeamPlugin.PLUGIN_ID);
                status = new Status(IStatus.ERROR, TeamPlugin.PLUGIN_ID, e.getMessage(), e);
                return;
            } catch (final MigrationException e) {
                BonitaStudioLog.error(e, TeamPlugin.PLUGIN_ID);
                status = new Status(IStatus.ERROR, TeamPlugin.PLUGIN_ID, e.getMessage(), e);
                return;
            } finally {
                currentRepository.enableBuild();
            }

            TeamRepositoryUtil.updateLastRestore(timesatmp);

            final CommitAction action = new CommitAction();
            action.selectionChanged(null,
                    new StructuredSelection(RepositoryManager.getInstance().getCurrentRepository().getProject()));
            final CommitActionUtility commitUtils = new CommitActionUtility(action);
            final CommitResourcesOperation commitOperation = new CommitResourcesOperation(
                    commitUtils.getAllResources(),
                    "Repository migration from " + sourceVersion + " to " + ProductVersion.CURRENT_VERSION,
                    new SVNLockManager(RepositoryManager.getInstance().getCurrentRepository()
                            .getRepositoryStore(DiagramRepositoryStore.class)));
            commitOperation.setBreakLocks(true);
            commitOperation.run(monitor);

            new UpdateOperation(new IResource[] { RepositoryManager.getInstance().getCurrentRepository().getProject() },
                    false).run(monitor);
            new RefreshResourcesOperation(
                    new IResource[] { RepositoryManager.getInstance().getCurrentRepository().getProject() },
                    IResource.DEPTH_INFINITE,
                    RefreshResourcesOperation.REFRESH_ALL).run(monitor);
        } finally {
            //come back to old value
            final IPreferenceStore preferenceStore = TeamPlugin.getDefault().getPreferenceStore();
            for (final Entry<String, Object> entry : backUpSettings.entrySet()) {
                final Object value = entry.getValue();
                if (value instanceof String) {
                    preferenceStore.setValue(entry.getKey(), (String) value);
                } else {
                    preferenceStore.setValue(entry.getKey(), (Boolean) value);
                }
            }
        }
    }

    private void unlockAllResources(final IProgressMonitor monitor) throws CoreException {
        IProject project = RepositoryManager.getInstance().getCurrentRepository().getProject();
        List<IResource> resources = new ArrayList<>();
        project.accept(new IResourceVisitor() {

            @Override
            public boolean visit(IResource resource) throws CoreException {
                resources.add(resource);
                return resource instanceof IContainer;
            }
        });
        ReleaseResourcesLockOperation scanResourcesLockOperation = new ReleaseResourcesLockOperation(
                resources.toArray(new IResource[] {}));
        scanResourcesLockOperation.run(monitor);
    }

    public boolean isSaveRepoBeforeMigration() {
        return saveRepoBeforeMigration;
    }

    public void setSaveRepoBeforeMigration(final boolean saveRepoBeforeMigration) {
        this.saveRepoBeforeMigration = saveRepoBeforeMigration;
    }

}
