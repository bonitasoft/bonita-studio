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
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.team.svn.core.operation.AbstractActionOperation;
import org.eclipse.team.svn.core.operation.CompositeOperation;
import org.eclipse.team.svn.core.operation.local.LockOperation;
import org.eclipse.team.svn.core.operation.local.RefreshResourcesOperation;
import org.eclipse.team.svn.core.utility.ProgressMonitorUtility;

public class LockResourceOperation implements IWorkspaceRunnable {

    private final IResource[] resourcesToLock;

    public LockResourceOperation(final IResource resourceToLock) {
        resourcesToLock = new IResource[] { resourceToLock };
    }

    public LockResourceOperation(final IResource[] resourcesToLock) {
        this.resourcesToLock = resourcesToLock;
    }

    @Override
    public void run(final IProgressMonitor monitor) throws CoreException {
        final LockOperation mainOp = new LockOperation(resourcesToLock, "lock",
                true);
        final CompositeOperation op = new CompositeOperation(mainOp
                .getId(), Messages.class);
        op.add(mainOp);
        op.add(new RefreshResourcesOperation(resourcesToLock));
        ProgressMonitorUtility.doTaskScheduled(
                new AbstractActionOperation("Lock resources", Messages.class) {

                    @Override
                    protected void runImpl(final IProgressMonitor monitor)
                            throws Exception {
                        op.run(monitor);
                    }

                }, true).setPriority(Job.SHORT);
    }

}
