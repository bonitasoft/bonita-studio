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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.team.core.SVNLockManager;
import org.bonitasoft.studio.team.i18n.Messages;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.team.svn.core.connector.SVNConnectorException;
import org.eclipse.team.svn.core.operation.remote.CreateFolderOperation;
import org.eclipse.team.svn.core.operation.remote.PreparedBranchTagOperation;
import org.eclipse.team.svn.core.resource.IRepositoryContainer;
import org.eclipse.team.svn.core.resource.IRepositoryLocation;
import org.eclipse.team.svn.core.resource.IRepositoryResource;

/**
 * @author Romain Bioteau
 */
public class CreateWorkspaceBackupOperation implements IRunnableWithProgress {

    public static final String DEFAULT_COMMENT = "";

    public static final String RESOTRE_POINT_TIMESTAMP_PATTERN = "yyyyMMdd-HHmmss";

    private final String workspaceName;
    private String backupName;
    private final IRepositoryLocation repositoryLocation;
    private String comment;

    public CreateWorkspaceBackupOperation(final IRepositoryLocation location, final String workspaceName) {
        this(location, workspaceName, null);
    }

    public CreateWorkspaceBackupOperation(final IRepositoryLocation location, final String workspaceName,
            final String comment) {
        this.workspaceName = workspaceName;
        repositoryLocation = location;
        this.comment = comment;
    }

    private String createBackupName() {
        final SimpleDateFormat formater = new SimpleDateFormat(RESOTRE_POINT_TIMESTAMP_PATTERN);
        return workspaceName + "_" + formater.format(new Date());
    }

    public String getBackupName() {
        return backupName;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        Assert.isNotNull(workspaceName);
        Assert.isNotNull(repositoryLocation);
        if (monitor == null) {
            monitor = AbstractRepository.NULL_PROGRESS_MONITOR;
        }
        monitor.beginTask(Messages.createARestorePointAction, IProgressMonitor.UNKNOWN);

        IRepositoryContainer remoteWorkspace = null;
        IRepositoryResource[] children;
        try {
            children = repositoryLocation.getRoot().getChildren();
        } catch (final SVNConnectorException e) {
            throw new InvocationTargetException(e);
        }
        for (final IRepositoryResource resource : children) {
            if (resource.getName().equals(workspaceName)) {
                remoteWorkspace = (IRepositoryContainer) resource;
            }
        }

        Assert.isNotNull(remoteWorkspace);

        IRepositoryContainer trunk = null;
        IRepositoryContainer tag = null;
        try {
            children = remoteWorkspace.getChildren();
        } catch (final SVNConnectorException e) {
            throw new InvocationTargetException(e);
        }
        for (final IRepositoryResource res : children) {
            if (res.getName().equals("trunk")) {
                trunk = (IRepositoryContainer) res;
            } else if (res.getName().equals("tags")) {
                tag = (IRepositoryContainer) res;
            }
        }

        Assert.isNotNull(trunk);
        Assert.isNotNull(tag);

        backupName = createBackupName();

        //Synchronize Repository before backup
        final IProject project = RepositoryManager.getInstance().getCurrentRepository().getProject();
        if (Objects.equals(project.getName(), workspaceName)) {
            final IResource[] resources = new IResource[] { project };
            final CommitResourcesOperation commitOP = new CommitResourcesOperation(resources,
                    "Synchronization before backup : " + backupName,
                    new SVNLockManager(RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class)));
            commitOP.setBreakLocks(true);
            commitOP.run(monitor);
        }

        if (comment == null) {
            comment = DEFAULT_COMMENT;
        }

        final CreateFolderOperation op = new CreateFolderOperation(tag, backupName, comment);
        op.run(monitor);

        tag.refresh();

        IRepositoryResource dest = null;

        try {
            children = tag.getChildren();
        } catch (final SVNConnectorException e) {
            throw new InvocationTargetException(e);
        }
        for (final IRepositoryResource child : children) {
            if (child.getName().equals(backupName)) {
                dest = child;
            }
        }

        Assert.isNotNull(dest);
        IRepositoryResource[] trunkChildren;
        try {
            trunkChildren = trunk.getChildren();
        } catch (final SVNConnectorException e) {
            throw new InvocationTargetException(e);
        }
        final PreparedBranchTagOperation createBranchOp = new PreparedBranchTagOperation("Create Restore", trunkChildren,
                dest,
                "Create Restore Point " + backupName, false);
        createBranchOp.run(monitor);
        repositoryLocation.getRoot().refresh();
    }

}
