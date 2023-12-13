/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.operation;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.internal.ui.DebugUIPlugin;
import org.eclipse.debug.internal.ui.IInternalDebugUIConstants;
import org.eclipse.egit.core.op.ConnectProviderOperation;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.m2e.core.ui.internal.UpdateMavenProjectJob;
import org.eclipse.team.core.RepositoryProvider;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

public abstract class AbstractMavenProjectUpdateOperation implements IWorkspaceRunnable {

    protected IStatus status = Status.OK_STATUS;

    @Override
    public void run(final IProgressMonitor monitor) throws CoreException {
        // Disable PREF_WAIT_FOR_BUILD to avoid invalid UI thread access
        var pref = DebugUIPlugin.getDefault().getPreferenceStore();
        var waitFor = pref.getString(IInternalDebugUIConstants.PREF_WAIT_FOR_BUILD);
        pref.setValue(IInternalDebugUIConstants.PREF_WAIT_FOR_BUILD, MessageDialogWithToggle.NEVER);
        try {
            final IProject project = doRun(monitor);
            if (project != null) {
                shareProject(project, monitor);
            }
        } finally {
            pref.setValue(IInternalDebugUIConstants.PREF_WAIT_FOR_BUILD, waitFor);
            monitor.done();
        }

    }

    protected void shareProject(final IProject project, final IProgressMonitor monitor) throws CoreException {
        var parentProject = getParentProject(project);
        if (RepositoryProvider.getProvider(parentProject, "org.eclipse.egit.core.GitProvider") != null) {
            var connectProviderOperation = new ConnectProviderOperation(project,
                    new File(parentProject.getLocation().toFile(), ".git"));
            connectProviderOperation.execute(monitor);
        }
    }

    private IProject getParentProject(IProject project) {
        var file = project.getLocation().toFile().getParentFile().getParentFile().getParentFile();
        return ResourcesPlugin.getWorkspace().getRoot().getProject(file.getName());
    }

    protected abstract IProject doRun(final IProgressMonitor monitor) throws CoreException;

    protected UpdateMavenProjectJob newUpdateMavenProjectJob(final IProject project,
            final boolean isOffline,
            final boolean forceUpdateDependencies,
            final boolean updateConfiguration,
            final boolean cleanProject,
            final boolean refreshFromLocal) {
        return new UpdateMavenProjectJob(List.of(project),
                isOffline,
                forceUpdateDependencies,
                updateConfiguration,
                cleanProject,
                refreshFromLocal) {

            @Override
            public boolean belongsTo(final Object family) {
                return Objects.equals(AbstractMavenProjectUpdateOperation.class, family);
            }
        };
    }

    public WorkspaceModifyOperation asWorkspaceModifyOperation() {
        return new WorkspaceModifyOperation() {

            @Override
            protected void execute(final IProgressMonitor monitor)
                    throws CoreException, InvocationTargetException, InterruptedException {
                AbstractMavenProjectUpdateOperation.this.run(monitor);
            }
        };
    }

    public IStatus getStatus() {
        return status;
    }

}
