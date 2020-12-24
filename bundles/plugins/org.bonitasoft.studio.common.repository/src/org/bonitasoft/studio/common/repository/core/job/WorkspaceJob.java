/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

import org.eclipse.core.internal.resources.Workspace;
import org.eclipse.core.internal.utils.Policy;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * Fork of org.eclipse.core.internal.resources.InternalWorkspaceJob
 * Do not use static accessor in constructor.
 */
public abstract class WorkspaceJob extends Job {

    private final Workspace workspace;

    public WorkspaceJob(String name, IWorkspace workspace) {
        super(name);
        this.workspace = (Workspace) workspace;
    }

    @Override
    public final IStatus run(IProgressMonitor monitor) {
        monitor = Policy.monitorFor(monitor);
        try {
            int depth = -1;
            try {
                workspace.prepareOperation(null, monitor);
                workspace.beginOperation(true);
                depth = workspace.getWorkManager().beginUnprotected();
                return runInWorkspace(monitor);
            } catch (final OperationCanceledException e) {
                workspace.getWorkManager().operationCanceled();
                return Status.CANCEL_STATUS;
            } finally {
                if (depth >= 0)
                    workspace.getWorkManager().endUnprotected(depth);
                workspace.endOperation(null, false);
            }
        } catch (final CoreException e) {
            return e.getStatus();
        }
    }

    protected abstract IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException;

}
