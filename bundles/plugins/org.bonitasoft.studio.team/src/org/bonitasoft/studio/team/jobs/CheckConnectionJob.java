/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
/**
 * 
 */
package org.bonitasoft.studio.team.jobs;

import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.team.svn.core.operation.SVNNullProgressMonitor;
import org.eclipse.team.svn.core.resource.IRepositoryLocation;
import org.eclipse.team.svn.core.utility.SVNUtility;

/**
 * @author Romain
 */
public class CheckConnectionJob extends Job {

    public static final String OFFLINE = "workspace_offline"; //$NON-NLS-1$
    private IRepositoryLocation location;

    public CheckConnectionJob(IRepositoryLocation location) {
        super(CheckConnectionJob.class.getName());
        this.location = location;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    protected IStatus run(IProgressMonitor arg0) {
        boolean oldConnectionState = CommonRepositoryPlugin.getPlugin().getPreferenceStore().getBoolean(OFFLINE);
        if (location != null) {
            Exception ex = SVNUtility.validateRepositoryLocation(location, new SVNNullProgressMonitor());
            boolean newConnectionState = ex != null;
            if (newConnectionState == oldConnectionState) {
                if (!newConnectionState) {
                    return Status.OK_STATUS;
                } else {
                    return Status.CANCEL_STATUS;
                }
            }

            CommonRepositoryPlugin.getPlugin().getPreferenceStore().setValue(OFFLINE, ex != null);
            if (!newConnectionState) {
                return Status.OK_STATUS;
            }
        }
        return Status.CANCEL_STATUS;

    }

}
