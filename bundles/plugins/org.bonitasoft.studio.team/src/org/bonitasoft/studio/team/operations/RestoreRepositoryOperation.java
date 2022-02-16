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

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.team.TeamPlugin;
import org.bonitasoft.studio.team.TeamRepositoryUtil;
import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.team.repository.Repository;
import org.bonitasoft.studio.team.ui.provider.RestorePointLabelProvider;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.team.svn.core.connector.ISVNConnector.Options;
import org.eclipse.team.svn.core.connector.SVNConnectorException;
import org.eclipse.team.svn.core.operation.CompositeOperation;
import org.eclipse.team.svn.core.operation.remote.CopyResourcesOperation;
import org.eclipse.team.svn.core.operation.remote.DeleteResourcesOperation;
import org.eclipse.team.svn.core.operation.remote.SetRevisionAuthorNameOperation;
import org.eclipse.team.svn.core.resource.IRepositoryContainer;
import org.eclipse.team.svn.core.resource.IRepositoryResource;
import org.eclipse.team.svn.ui.operation.RefreshRemoteResourcesOperation;

/**
 * @author Romain Bioteau
 */
public class RestoreRepositoryOperation implements IRunnableWithProgress {

    public static final String RESOTRE_POINT_TIMESTAMP_PATTERN = "yyyyMMdd-HHmmss";

    private final String workspaceName;
    private final IRepositoryContainer tagToRestore;

    public RestoreRepositoryOperation(final IRepositoryContainer tagToRestore, final String workspaceName) {
        this.workspaceName = workspaceName;
        this.tagToRestore = tagToRestore;
    }

    private boolean needMigration() {
        return !ProductVersion.sameMinorVersion(TeamRepositoryUtil.getRemoteRepositoryVersion(tagToRestore));
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        Assert.isNotNull(workspaceName);
        Assert.isNotNull(tagToRestore);
        if (monitor == null) {
            monitor = AbstractRepository.NULL_PROGRESS_MONITOR;
        }
        monitor.beginTask(Messages.restoreAction, IProgressMonitor.UNKNOWN);

        final long timestamp = System.currentTimeMillis();
        IRepositoryContainer trunk = null;
        final IRepositoryContainer parentDir = (IRepositoryContainer) tagToRestore.getParent().getParent();
        for (final IRepositoryResource resource : getChildren(parentDir)) {
            if (resource.getName().equals("trunk")) {
                trunk = (IRepositoryContainer) resource;
            }
        }

        Assert.isNotNull(trunk);

        final CreateWorkspaceBackupOperation backupOperation = new CreateWorkspaceBackupOperation(tagToRestore.getRepositoryLocation(), workspaceName,
                Messages.bind(
                        Messages.beforeRestoreTo, new RestorePointLabelProvider().getText(tagToRestore)));
        backupOperation.run(monitor);

        IRepositoryContainer remoteRepo = null;
        try {
            remoteRepo = TeamRepositoryUtil.getRemoteRepository((Repository) RepositoryManager.getInstance().getRepository(
                    workspaceName));
        } catch (final SVNConnectorException e) {
            BonitaStudioLog.error(e);
            return;
        }

        BonitaStudioLog.debug("Deleting trunk", TeamPlugin.PLUGIN_ID);
        final DeleteResourcesOperation deleteOp = new DeleteResourcesOperation(getChildren(trunk), "Delete trunk");
        deleteOp.run(monitor);
        BonitaStudioLog.debug("Trunk deleted", TeamPlugin.PLUGIN_ID);

        BonitaStudioLog.debug("Copying tag to trunk", TeamPlugin.PLUGIN_ID);
        final CopyResourcesOperation moveOp = new CopyResourcesOperation(trunk, getChildren(tagToRestore), "Restore Workspace from tag", "");
        final CompositeOperation op = new CompositeOperation(moveOp.getId(), moveOp.getMessagesClass());
        op.add(moveOp);
        op.add(new SetRevisionAuthorNameOperation(moveOp, Options.FORCE));
        op.add(new RefreshRemoteResourcesOperation(new IRepositoryResource[] { parentDir }));
        op.run(monitor);
        BonitaStudioLog.debug("Tag copied into trunk", TeamPlugin.PLUGIN_ID);

        parentDir.refresh();

        final IRepository repo = RepositoryManager.getInstance().getRepository(workspaceName);
        BOSEngineManager.getInstance().stop();
        repo.delete(monitor);

        if (needMigration()) {
            final MigrateRepositoryOperation migrateOp = new MigrateRepositoryOperation(remoteRepo, tagToRestore.getRepositoryLocation(),
                    TeamRepositoryUtil.getRemoteRepositoryVersion(tagToRestore));
            migrateOp.run(monitor);
        } else {
            new ConnectoToRepositoryOperation(remoteRepo, tagToRestore.getRepositoryLocation()).run(monitor);
            TeamRepositoryUtil.updateLastRestore(timestamp);

            final IRepository currentRepository = RepositoryManager.getInstance().getCurrentRepository();
            if (currentRepository instanceof Repository) {
                ((Repository) currentRepository).startUpdateJob();
            }
        }
    }

    private IRepositoryResource[] getChildren(final IRepositoryContainer container) throws InvocationTargetException {
        try {
            return container.getChildren();
        } catch (final SVNConnectorException e) {
            throw new InvocationTargetException(e);
        }
    }

}
