/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.operations;

import org.bonitasoft.studio.team.i18n.Messages;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.team.svn.core.operation.CompositeOperation;
import org.eclipse.team.svn.core.operation.local.RefreshResourcesOperation;
import org.eclipse.team.svn.core.operation.local.UnlockOperation;

public class ReleaseResourcesLockOperation implements IWorkspaceRunnable {

    private final IResource[] resources;
    private IStatus status;

    public ReleaseResourcesLockOperation(final IResource[] resourceToLock) {
        resources = resourceToLock;
    }

    public ReleaseResourcesLockOperation(final IResource resourceToLock) {
        this(new IResource[] { resourceToLock });
    }

    @Override
    public void run(final IProgressMonitor monitor) throws CoreException {
        final UnlockOperation mainOp = new UnlockOperation(resources);
        final CompositeOperation op = new CompositeOperation(mainOp
                .getId(), Messages.class);
        op.add(mainOp);
        op.add(new RefreshResourcesOperation(resources));
        op.run(monitor);
        status = op.getStatus();
    }

    public IStatus getStatus() {
        return status;
    }


}
