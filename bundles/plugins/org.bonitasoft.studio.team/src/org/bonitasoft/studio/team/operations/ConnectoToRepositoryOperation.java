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
import java.util.regex.Pattern;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.team.TeamPlugin;
import org.bonitasoft.studio.team.TeamRepositoryUtil;
import org.bonitasoft.studio.team.i18n.Messages;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.team.svn.core.IStateFilter;
import org.eclipse.team.svn.core.connector.ISVNConnector.Options;
import org.eclipse.team.svn.core.connector.SVNConnectorException;
import org.eclipse.team.svn.core.connector.SVNDepth;
import org.eclipse.team.svn.core.connector.SVNRevision;
import org.eclipse.team.svn.core.operation.AbstractActionOperation;
import org.eclipse.team.svn.core.operation.CompositeOperation;
import org.eclipse.team.svn.core.operation.IActionOperation;
import org.eclipse.team.svn.core.operation.local.RefreshResourcesOperation;
import org.eclipse.team.svn.core.operation.local.UpdateOperation;
import org.eclipse.team.svn.core.operation.local.management.ShareProjectOperation;
import org.eclipse.team.svn.core.operation.remote.CheckoutAsOperation;
import org.eclipse.team.svn.core.operation.remote.DeleteResourcesOperation;
import org.eclipse.team.svn.core.operation.remote.LocateProjectsOperation;
import org.eclipse.team.svn.core.operation.remote.SetRevisionAuthorNameOperation;
import org.eclipse.team.svn.core.operation.remote.management.AddRepositoryLocationOperation;
import org.eclipse.team.svn.core.operation.remote.management.SaveRepositoryLocationsOperation;
import org.eclipse.team.svn.core.resource.IRepositoryContainer;
import org.eclipse.team.svn.core.resource.IRepositoryLocation;
import org.eclipse.team.svn.core.resource.IRepositoryResource;
import org.eclipse.team.svn.core.svnstorage.SVNRemoteStorage;
import org.eclipse.team.svn.core.utility.FileUtility;
import org.eclipse.team.svn.core.utility.SVNUtility;
import org.eclipse.team.svn.ui.action.IResourceSelector;
import org.eclipse.team.svn.ui.action.remote.RefreshAction;
import org.eclipse.team.svn.ui.extension.ExtensionsManager;
import org.eclipse.team.svn.ui.operation.RefreshRemoteResourcesOperation;
import org.eclipse.team.svn.ui.operation.RefreshRepositoryLocationsOperation;
import org.eclipse.team.svn.ui.utility.CommitActionUtility;

/**
 * @author Romain Bioteau
 */
public class ConnectoToRepositoryOperation implements IRunnableWithProgress {

    private final IRepositoryResource selectedWorkspace;
    private final IRepositoryLocation repoLocation;
    private final boolean bypassVersion;
    private final boolean switchRepo;

    public ConnectoToRepositoryOperation(final IRepositoryResource selectedRepository,
            final IRepositoryLocation repoLocation) {
        this(selectedRepository, repoLocation, true, false);
    }

    public ConnectoToRepositoryOperation(final IRepositoryResource selectedRepository,
            final IRepositoryLocation repoLocation, final boolean bypassVersion) {
        this(selectedRepository, repoLocation, true, bypassVersion);
    }

    public ConnectoToRepositoryOperation(final IRepositoryResource selectedWorkspace, final IRepositoryLocation repoLocation,
            final boolean switchRepo,
            final boolean bypassVersion) {
        this.selectedWorkspace = selectedWorkspace;
        this.repoLocation = repoLocation;
        this.bypassVersion = bypassVersion;
        this.switchRepo = switchRepo;
    }

    private boolean isRemoteRepositoryEmpty() throws InvocationTargetException {
        try {
            return ((IRepositoryContainer) selectedWorkspace).getChildren().length == 0;
        } catch (final SVNConnectorException e) {
            throw new InvocationTargetException(e);
        }
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        if (monitor == null) {
            monitor = AbstractRepository.NULL_PROGRESS_MONITOR;
        }
        monitor.beginTask(" ", IProgressMonitor.UNKNOWN);
        if (!bypassVersion && !isRemoteRepositoryEmpty()
                && !TeamRepositoryUtil.checkVersion((IRepositoryContainer) selectedWorkspace)) {
            throw new InvocationTargetException(
                    new Exception("Impossible to connect to a workspace with different version"));
        }

        boolean shouldntBeAdded = false;
        Assert.isNotNull(repoLocation);
        final CompositeOperation op = new CompositeOperation("saving repository location", Messages.class);

        if (SVNRemoteStorage.instance().getRepositoryLocation(repoLocation.getId()) != null) {//The location already exists
            shouldntBeAdded = true;
        }

        AbstractActionOperation mainOp = null;
        if (shouldntBeAdded) {
            BonitaStudioLog.debug("Connecting to repository " + repoLocation.getName(), TeamPlugin.PLUGIN_ID);
            mainOp = new AbstractActionOperation("Operation_CommitLocationChanges", Messages.class) { //$NON-NLS-1$

                @Override
                protected void runImpl(final IProgressMonitor monitor) throws Exception {
                    final String pass = repoLocation.getPassword();
                    repoLocation.reconfigure();
                    if (repoLocation.getPassword() == null || repoLocation.getPassword().length() == 0) {
                        repoLocation.setPassword(pass);
                    }
                }
            };
        } else {
            mainOp = new AddRepositoryLocationOperation(repoLocation);
        }

        op.add(mainOp);

        op.add(new SaveRepositoryLocationsOperation());
        if (shouldntBeAdded) {
            op.add(new RefreshRepositoryLocationsOperation(new IRepositoryLocation[] { repoLocation }, true));
        } else {
            op.add(new RefreshRepositoryLocationsOperation(false));
        }
        op.run(monitor);

        final LocateProjectsOperation locateProjectsOperation = new LocateProjectsOperation(
                new IRepositoryResource[] { selectedWorkspace },
                ExtensionsManager.getInstance().getCurrentCheckoutFactory().getLocateFilter(), 5);
        locateProjectsOperation.run(monitor);

        final List<IRepositoryResource> resourcesList = new ArrayList<>();
        for (final IRepositoryResource proj : locateProjectsOperation.getRepositoryResources()) {
            final IRepositoryResource tmpResource = SVNUtility.copyOf(proj);
            tmpResource.setSelectedRevision(SVNRevision.HEAD);
            resourcesList.add(tmpResource);
        }
        final IRepositoryResource[] repositoryResources = resourcesList.toArray(new IRepositoryResource[] {});

        //checkout project if exists
        if (repositoryResources != null && repositoryResources.length > 0) {
            new RefreshAction() {

                @Override
                protected IRepositoryResource[] getSelectedRepositoryResources() {
                    return repositoryResources;
                };
            }.runImpl(null);

            new CheckoutAsOperation(selectedWorkspace.getName(), repositoryResources[0], SVNDepth.INFINITY, false)
                    .run(monitor);
        }

        if (switchRepo) {
            cleanCommitedProvidedWidgets(monitor);
            TeamRepositoryUtil.switchToRepository(selectedWorkspace.getName(), false, false, monitor);
        }
        TeamRepositoryUtil.setConnectionDate(selectedWorkspace.getName());

        //share new project as it doesn't exist yet
        if (repositoryResources != null && repositoryResources.length == 0) {
            shareNewRepository(monitor);
        }
        commit(RepositoryManager.getInstance().getCurrentRepository().getProject(), Messages.team_firstCommitComment,
                monitor);
    }

    protected void cleanCommitedProvidedWidgets(final IProgressMonitor monitor) {
        final IProject checkoutedProject = ResourcesPlugin.getWorkspace().getRoot()
                .getProject(selectedWorkspace.getName());
        final IFolder widgetFolder = checkoutedProject.getFolder("web_widgets");
        if (widgetFolder.exists()) {
            try {
                for (final IResource widget : widgetFolder.members()) {
                    if (Pattern.matches("web_widgets/pb.*", widget.getProjectRelativePath().toString())) {
                        deleteRemoteResource(widget,
                                String.format("'%s' provided widget removed from source control", widget.getName()),
                                monitor);
                        PlatformUtil.delete(widget.getLocation().toFile(), monitor);
                        BonitaStudioLog.warning(
                                String.format("'%s' provided widget has been removed from source control", widget.getName()),
                                TeamPlugin.PLUGIN_ID);
                    }
                }
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }

    }

    private IStatus deleteRemoteResource(IResource resource, String message, IProgressMonitor monitor) {
        final IRepositoryResource[] resources = new IRepositoryResource[] {
                SVNRemoteStorage.instance().asRepositoryResource(resource) };
        final IRepositoryResource[] commonParents = SVNUtility.getCommonParents(resources);

        final DeleteResourcesOperation mainOp = new DeleteResourcesOperation(resources, message);
        final CompositeOperation op = new CompositeOperation(mainOp.getId(), mainOp.getMessagesClass());

        op.add(mainOp);
        op.add(new RefreshRemoteResourcesOperation(commonParents));
        op.add(new SetRevisionAuthorNameOperation(mainOp, Options.FORCE), new IActionOperation[] { mainOp });
        op.add(new UpdateOperation(new IResource[] { resource }, true));
        op.run(monitor);
        return op.getStatus();
    }

    private IStatus commit(final IResource parentResource, String message, final IProgressMonitor monitor) {
        final IResourceSelector selector = new IResourceSelector() {

            @Override
            public IResource[] getSelectedResources() {
                return new IResource[] { parentResource };
            }

            @Override
            public IResource[] getSelectedResources(IStateFilter filter) {
                return FileUtility.getResourcesRecursive(this.getSelectedResources(), filter, IResource.DEPTH_ZERO);
            }

            @Override
            public IResource[] getSelectedResourcesRecursive(IStateFilter filter) {
                return this.getSelectedResourcesRecursive(filter, IResource.DEPTH_INFINITE);
            }

            @Override
            public IResource[] getSelectedResourcesRecursive(IStateFilter filter, int depth) {
                return FileUtility.getResourcesRecursive(this.getSelectedResources(), filter, depth, null, monitor);
            }
        };
        final CommitActionUtility commitUtils = new CommitActionUtility(selector);
        final CompositeOperation commitOp = commitUtils.getCompositeCommitOperation(commitUtils.getAllResources(),
                new IResource[0],
                message, true, null, null);

        commitOp.add(new RefreshResourcesOperation(
                new IResource[] { parentResource },
                IResource.DEPTH_INFINITE, RefreshResourcesOperation.REFRESH_ALL));
        commitOp.run(monitor);

        return commitOp.getStatus();
    }

    private void shareNewRepository(final IProgressMonitor monitor) {
        final IActionOperation share = new ShareProjectOperation(
                new IProject[] { RepositoryManager.getInstance().getCurrentRepository().getProject() }, repoLocation, null,
                selectedWorkspace.getName(), ShareProjectOperation.LAYOUT_SINGLE, true, Messages.team_shareWorkspaceComment);
        share.run(monitor);
    }

}
