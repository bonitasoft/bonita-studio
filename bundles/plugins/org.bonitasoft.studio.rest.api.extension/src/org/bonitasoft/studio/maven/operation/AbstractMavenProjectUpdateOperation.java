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
import java.util.Objects;

import org.bonitasoft.studio.businessobject.maven.UpdateMavenProjectConfiguration;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionRepositoryStore;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.egit.core.op.ConnectProviderOperation;
import org.eclipse.m2e.core.ui.internal.UpdateMavenProjectJob;
import org.eclipse.team.core.RepositoryProvider;
import org.eclipse.team.svn.core.operation.local.management.ShareProjectOperation;
import org.eclipse.team.svn.core.operation.local.management.ShareProjectOperation.IFolderNameMapper;
import org.eclipse.team.svn.core.resource.IRepositoryLocation;
import org.eclipse.team.svn.core.svnstorage.SVNRemoteStorage;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

public abstract class AbstractMavenProjectUpdateOperation implements IWorkspaceRunnable {

    protected IStatus status = Status.OK_STATUS;
    private boolean updateAfter;

    public AbstractMavenProjectUpdateOperation(boolean updateAfter) {
        this.updateAfter = updateAfter;
    }
    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.actions.WorkspaceModifyOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void run(final IProgressMonitor monitor) throws CoreException {
        final IProject project = doRun(monitor);
        if (project != null) {
            shareProject(project, monitor);
            if (updateAfter) {
                newUpdateMavenProjectJob(project,
                        UpdateMavenProjectConfiguration.IS_OFFLINE,
                        UpdateMavenProjectConfiguration.FORCE_UPDATE_DEPENDENCIES,
                        UpdateMavenProjectConfiguration.UPDATE_CONFIGURATION,
                        UpdateMavenProjectConfiguration.CLEAN_PROJECT,
                        UpdateMavenProjectConfiguration.REFRESH_FROM_LOCAL).schedule();
            }
        }
        monitor.done();
    }

    protected void shareProject(final IProject project, final IProgressMonitor monitor) throws CoreException {
        final IProject parentProject = getParentProject(project);
        if (RepositoryProvider.getProvider(parentProject, "org.eclipse.team.svn.core.svnnature") != null) {
            final IRepositoryLocation location = SVNRemoteStorage.instance().getRepositoryLocation(parentProject);
            if (location != null) {
                new ShareProjectOperation(
                        new IProject[] { project },
                        location,
                        folderNameMapper(parentProject, RestAPIExtensionRepositoryStore.STORE_NAME),
                        null,
                        ShareProjectOperation.LAYOUT_DEFAULT,
                        false,
                        "Sharing project " + project.getName())
                                .run(monitor);
            }
        } else if (RepositoryProvider.getProvider(parentProject, "org.eclipse.egit.core.GitProvider") != null) {
            ConnectProviderOperation connectProviderOperation = new ConnectProviderOperation(project,
                    new File(parentProject.getLocation().toFile(), ".git"));
            connectProviderOperation.execute(monitor);
        }
    }

    private IProject getParentProject(IProject project) {
        File file = project.getLocation().toFile().getParentFile().getParentFile();
        return ResourcesPlugin.getWorkspace().getRoot().getProject(file.getName());
    }

    protected IFolderNameMapper folderNameMapper(final IProject parentProject, final String repositoryStoreName) {
        return new IFolderNameMapper() {

            @Override
            public String getRepositoryFolderName(final IProject project) {
                return parentProject.getName() + "/trunk/" + repositoryStoreName + "/" + project.getName();
            }
        };
    }

    protected abstract IProject doRun(final IProgressMonitor monitor) throws CoreException;

    protected UpdateMavenProjectJob newUpdateMavenProjectJob(final IProject project,
            final boolean isOffline,
            final boolean forceUpdateDependencies,
            final boolean updateConfiguration,
            final boolean cleanProject,
            final boolean refreshFromLocal) {
        return new UpdateMavenProjectJob(new IProject[] { project },
                isOffline,
                forceUpdateDependencies,
                updateConfiguration,
                cleanProject,
                refreshFromLocal) {

            /*
             * (non-Javadoc)
             * @see org.eclipse.core.runtime.jobs.Job#belongsTo(java.lang.Object)
             */
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
