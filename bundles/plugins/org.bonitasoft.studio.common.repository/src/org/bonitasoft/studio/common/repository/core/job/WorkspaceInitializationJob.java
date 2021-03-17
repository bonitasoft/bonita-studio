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
package org.bonitasoft.studio.common.repository.core.job;

import javax.inject.Inject;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * @author Romain Bioteau
 */
public class WorkspaceInitializationJob extends WorkspaceJob {

    public static final Object WORKSPACE_INIT_FAMILY = new Object();

    @Inject
    private RepositoryAccessor repositoryAccessor;

    public WorkspaceInitializationJob(final RepositoryAccessor repositoryAccessor) {
        super("Workspace Initialization Job");
        this.repositoryAccessor = repositoryAccessor;
    }

    protected void setRepositoryAccessor(final RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.resources.WorkspaceJob#runInWorkspace(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public IStatus runInWorkspace(final IProgressMonitor monitor) throws CoreException {
        BonitaStudioLog.info("Initializing workspace..", CommonRepositoryPlugin.PLUGIN_ID);
        repositoryAccessor.start(monitor);
        return Status.OK_STATUS;
    }

    @Override
    public boolean belongsTo(final Object family) {
        return WORKSPACE_INIT_FAMILY.equals(family);
    }
}
