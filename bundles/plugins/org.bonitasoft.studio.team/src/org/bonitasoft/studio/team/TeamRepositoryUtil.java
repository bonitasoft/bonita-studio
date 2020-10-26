/*******************************************************************************
 * Copyright (C) 2009, 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.team.jobs.CheckConnectionJob;
import org.bonitasoft.studio.team.operations.ScanResourcesLockOperation;
import org.bonitasoft.studio.team.preferences.TeamPreferencesConstants;
import org.bonitasoft.studio.team.repository.Repository;
import org.bonitasoft.studio.team.ui.handler.RepositoryRecursiveTeamAction;
import org.eclipse.core.internal.resources.ProjectDescription;
import org.eclipse.core.internal.resources.ProjectDescriptionReader;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Display;
import org.eclipse.team.svn.core.SVNTeamPlugin;
import org.eclipse.team.svn.core.connector.SVNConnectorException;
import org.eclipse.team.svn.core.history.SVNRemoteResourceRevision;
import org.eclipse.team.svn.core.operation.CompositeOperation;
import org.eclipse.team.svn.core.operation.SVNNullProgressMonitor;
import org.eclipse.team.svn.core.operation.remote.GetFileContentOperation;
import org.eclipse.team.svn.core.operation.remote.GetLogMessagesOperation;
import org.eclipse.team.svn.core.resource.IRepositoryContainer;
import org.eclipse.team.svn.core.resource.IRepositoryFile;
import org.eclipse.team.svn.core.resource.IRepositoryLocation;
import org.eclipse.team.svn.core.resource.IRepositoryResource;
import org.eclipse.team.svn.core.utility.SVNUtility;
import org.eclipse.team.svn.ui.utility.CommitActionUtility;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.xml.sax.InputSource;

/**
 * @author Baptiste Mesta
 */
public class TeamRepositoryUtil {

    public final static int NOT_LOCK = 0;
    public final static int LOCK_BY_OTHER = 1;
    public final static int LOCK_BY_ME = 2;
    public static final String LEGACY_LAST_RESTORE = "LAST_RESTORE";
    public static final String LAST_RESTORE = ".LAST_RESTORE";
    private static final String PROJECT_FILE = ".project";

    public static void switchToRepository(final String newWorkspaceFolder,
            final IProgressMonitor monitor) {
        switchToRepository(newWorkspaceFolder, true, false, monitor);
    }

    public static synchronized void createNewLocalRepository(
            final String repositoryName,
            final IProgressMonitor monitor) {
        switchToRepository(repositoryName, false, true, monitor);
    }

    public static synchronized void switchToRepository(
            final String repositoryName,
            final boolean startUpdateJob,
            final boolean migrateExistingContent,
            IProgressMonitor monitor) {
        switchToRepository(repositoryName, startUpdateJob, migrateExistingContent, true, monitor);
    }

    public static synchronized void switchToRepository(
            final String repositoryName,
            final boolean startUpdateJob,
            final boolean migrateExistingContent,
            final boolean stopServerBeforeSwitching,
            IProgressMonitor monitor) {
        if (monitor == null) {
            monitor = AbstractRepository.NULL_PROGRESS_MONITOR;
        }
        IRepository currentRepository = RepositoryManager.getInstance()
                .getCurrentRepository();
        if (Objects.equals(currentRepository.getName(), repositoryName)) {
            return;
        }
        currentRepository.close();
        try {
            WorkspaceModifyOperation workspaceModifyOperation = new WorkspaceModifyOperation() {

                @Override
                protected void execute(final IProgressMonitor monitor)
                        throws CoreException, InvocationTargetException, InterruptedException {
                    monitor.beginTask(Messages.team_switchingProject,
                            IProgressMonitor.UNKNOWN);
                    monitor.subTask(Messages.team_openingNewWorkspace);
                    BonitaStudioLog.info("Switching repository to "
                            + repositoryName, TeamPlugin.PLUGIN_ID);
                    RepositoryManager.getInstance().setRepository(repositoryName, migrateExistingContent, monitor);
                    // update studio name
                    final AbstractRepository currentRepo = RepositoryManager.getInstance().getCurrentRepository();
                    BonitaStudioLog.info(
                            "Repository switched to " + currentRepo.getName(),
                            TeamPlugin.PLUGIN_ID);
                    PlatformUtil.openIntro();
                }
            };
            workspaceModifyOperation.run(monitor);
            Display.getDefault().asyncExec(
                    () -> {
                        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().resetPerspective();
                        PlatformUtil.openIntroIfNoOtherEditorOpen();
                    });
        } catch (final InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
        } finally {
            currentRepository = RepositoryManager.getInstance()
                    .getCurrentRepository();
            if (currentRepository instanceof Repository && startUpdateJob) {
                ((Repository) currentRepository).startUpdateJob();
            }
        }

    }

    public static boolean isRepositoryOnLine() {
        return !TeamRepositoryUtil.isLocalRepository()
                && RepositoryManager.getInstance().getCurrentRepository().isShared(SVNTeamPlugin.NATURE_ID)
                && RepositoryManager.getInstance().getCurrentRepository().isOnline();
    }

    public static boolean isRepositoryValid(final IRepositoryLocation location) {
        boolean isConnected = false;
        if (location != null && location.getUrl() != null) {
            final Exception exception = SVNUtility.validateRepositoryLocation(location, new SVNNullProgressMonitor());
            isConnected = exception == null;
        }
        return isConnected;
    }

    public static boolean isLocalRepository() {
        final AbstractRepository currentRepository = RepositoryManager.getInstance().getCurrentRepository();
        if (currentRepository.getProject().isOpen()) {
            return !currentRepository.isShared();
        }
        return false;
    }

    public static boolean isLocalRepository(final String repositoryName) {
        final IRepository repository = RepositoryManager.getInstance()
                .getRepository(repositoryName);
        return !repository.isShared();
    }

    /**
     * @param location
     */
    public static void checkConnectionState(final IRepositoryLocation location) {
        final CheckConnectionJob job = new CheckConnectionJob(location);
        job.setUser(false);
        job.schedule();
    }

    public static int getLockState(final IRepositoryFileStore file) {
        if (TeamRepositoryUtil.isLocalRepository()) {
            return NOT_LOCK;
        }

        final ScanResourcesLockOperation scanResourcesLockOperation = new ScanResourcesLockOperation(
                file.getResource());
        try {
            file.getResource().getWorkspace().run(scanResourcesLockOperation, AbstractRepository.NULL_PROGRESS_MONITOR);
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }

        if (scanResourcesLockOperation.isLocalyLocked()) {
            return LOCK_BY_ME;
        }
        if (scanResourcesLockOperation.isLockedByOther()) {
            return LOCK_BY_OTHER;
        }

        return NOT_LOCK;
    }

    public static boolean checkVersion(
            final IRepositoryContainer selectedWorkspace) {
        try {
            for (final IRepositoryResource iRepositoryResource : selectedWorkspace
                    .getChildren()) {
                if (iRepositoryResource instanceof IRepositoryContainer
                        && iRepositoryResource.getName().equals("trunk")) {//$NON-NLS-1$
                    return ProductVersion
                            .sameVersion(getRemoteRepositoryVersion((IRepositoryContainer) iRepositoryResource));
                }
            }
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
            return false;
        }

        return false;
    }

    public static String getRestorePointComment(
            final IRepositoryContainer backupResource) {
        final GetLogMessagesOperation logOp = new GetLogMessagesOperation(
                backupResource);
        logOp.run(new NullProgressMonitor());
        logOp.getMessages();
        final SVNRemoteResourceRevision rev = new SVNRemoteResourceRevision(
                backupResource,
                logOp.getMessages()[logOp.getMessages().length - 1]);
        return rev.getComment();
    }

    public static synchronized IRepositoryContainer getRemoteRepository(
            final Repository repository) throws SVNConnectorException {
        final IRepositoryLocation location = repository.getLocationForRepository();
        if (location == null) {
            return null;
        }
        for (final IRepositoryResource resource : location.getRoot()
                .getChildren()) {
            if (resource.getName().equals(repository.getName())) {
                return (IRepositoryContainer) resource;
            }
        }
        throw new IllegalStateException(
                String.format("No repository found with name %s on remote location %s", repository.getName(), location));
    }

    public static String getRemoteRepositoryVersion(
            final IRepositoryContainer resource) {
        String version = "6.0-";
        try {
            for (final IRepositoryResource child : resource.getChildren()) {
                if (child instanceof IRepositoryFile
                        && PROJECT_FILE.equals(child.getName())) {
                    final GetFileContentOperation op = new GetFileContentOperation(
                            child);
                    op.run(AbstractRepository.NULL_PROGRESS_MONITOR);
                    try {
                        final ProjectDescriptionReader reader = new ProjectDescriptionReader();
                        final InputSource source = new InputSource(
                                op.getContent());
                        final ProjectDescription description = reader
                                .read(source);
                        version = description.getComment();
                        break;
                    } catch (final Exception e) {
                        BonitaStudioLog.error(e);
                    }
                }
            }

        } catch (final SVNConnectorException e) {
            BonitaStudioLog.error(e);
        }
        return version;
    }

    public static void updateLastRestore(final long timestamp) {
        try {
            final IProject processProject = RepositoryManager.getInstance()
                    .getCurrentRepository().getProject();
            final IFile file = processProject.getFile(LAST_RESTORE);
            final ByteArrayInputStream stream = new ByteArrayInputStream(String
                    .valueOf(timestamp).getBytes());
            if (file.exists()) {
                file.setContents(stream, IResource.FORCE,
                        new NullProgressMonitor());
            } else {
                file.create(stream, true, new NullProgressMonitor());
            }
            stream.close();
            final CommitActionUtility commitUtils = new CommitActionUtility(
                    new RepositoryRecursiveTeamAction(
                            new IProject[] { processProject }));
            final CompositeOperation commitOp = commitUtils
                    .getCompositeCommitOperation(new IResource[] { file }, new IResource[0],
                            "Last Restore", true, null, null);
            commitOp.run(AbstractRepository.NULL_PROGRESS_MONITOR);
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
    }

    public static long getLastRestoreDate(final String repositoryName) {
        try {
            final IRepository repository = RepositoryManager
                    .getInstance().getRepository(repositoryName);
            if (repository instanceof Repository) {
                final IRepositoryContainer remoteWorkspace = getRemoteRepository((Repository) repository);
                IRepositoryContainer trunk = null;
                for (final IRepositoryResource res : remoteWorkspace.getChildren()) {
                    if (res.getName().equals("trunk")) {
                        trunk = (IRepositoryContainer) res;
                        break;
                    }
                }

                for (final IRepositoryResource child : trunk.getChildren()) {
                    if (child.getName().equals(LAST_RESTORE)) {
                        final GetFileContentOperation op = new GetFileContentOperation(
                                child);
                        op.run(new NullProgressMonitor());
                        try {
                            final String timestampString = toString(op.getContent());
                            if (timestampString != null
                                    && timestampString.trim().length() > 0) {
                                return Long.valueOf(timestampString);
                            }
                        } catch (final Exception e) {
                            BonitaStudioLog.error(e);
                        }
                    }
                }
            }
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
        return 0;

    }

    private static String toString(final InputStream is) throws Exception {
        final Writer writer = new StringWriter();
        final char[] buffer = new char[1024];
        try {
            final Reader reader = new BufferedReader(new InputStreamReader(is,
                    "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
            is.close();
        }

        return writer.toString();
    }

    public static Properties getBatchReconnections() throws Exception {
        final IPath path = ResourcesPlugin.getWorkspace().getRoot()
                .getLocation();
        final File propFile = new File(path.toFile(),
                "restartConnection.properties");
        if (propFile.exists()) {
            final Properties p = new Properties();
            final FileInputStream fis = new FileInputStream(propFile);
            p.load(fis);
            fis.close();
            return p;
        }
        return null;
    }

    public static void removeBatchReconnections() throws Exception {
        final IPath path = ResourcesPlugin.getWorkspace().getRoot()
                .getLocation();
        final File propFile = new File(path.toFile(),
                "restartConnection.properties");
        propFile.delete();
    }

    public static IRepositoryContainer getRemoteRepository(
            final String workspaceName, final IRepositoryLocation location) {
        try {
            for (final IRepositoryResource resource : location.getRoot()
                    .getChildren()) {
                if (resource.getName().equals(workspaceName)) {
                    return (IRepositoryContainer) resource;
                }
            }
        } catch (final SVNConnectorException e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    public static void createBacthReconnection(final String name,
            final IRepositoryLocation repositoryLocation) throws Exception {
        final IPath path = ResourcesPlugin.getWorkspace().getRoot()
                .getLocation();
        final File propFile = new File(path.toFile(),
                "restartConnection.properties");
        propFile.delete();
        propFile.createNewFile();
        final Properties p = new Properties();
        p.put("WORKSPACE_NAME", name);
        p.put("WORKSPACE_URL", repositoryLocation.getUrl());
        p.put("WORKSPACE_USER", repositoryLocation.getUsername());
        p.put("WORKSPACE_PASSWORD", repositoryLocation.getPassword());
        final FileOutputStream w = new FileOutputStream(propFile);
        p.store(w, "");
        w.close();
    }

    public static void setAutoShare(final String repositoryName,
            final boolean autoShare) {
        TeamPlugin.getDefault().getPreferenceStore()
                .setValue(repositoryName, autoShare);
    }

    /**
     * @param type
     *        : AUTO, CUSTOM or MANUAL
     */
    public static void setApplyAllState(final String type) {
        TeamPlugin.getDefault().getPreferenceStore()
                .setValue(TeamPreferencesConstants.ApplyAllState, type);
    }

    public static boolean isCustomSynchronization() {
        final String applyAllState = TeamPlugin.getDefault()
                .getPreferenceStore()
                .getString(TeamPreferencesConstants.ApplyAllState);
        return TeamPreferencesConstants.CUSTOM.equals(applyAllState);
    }

    public static boolean isSynchroAll() {
        final String applyAllState = TeamPlugin.getDefault()
                .getPreferenceStore()
                .getString(TeamPreferencesConstants.ApplyAllState);
        return TeamPreferencesConstants.AUTO.equals(applyAllState);
    }

    public static boolean isManualAll() {
        final String applyAllState = TeamPlugin.getDefault()
                .getPreferenceStore()
                .getString(TeamPreferencesConstants.ApplyAllState);
        return TeamPreferencesConstants.MANUAL.equals(applyAllState);
    }

    public static Map<String, Object> backUpSettings() {
        final Map<String, Object> backup = new HashMap<String, Object>();
        final IPreferenceStore preferenceStore = TeamPlugin.getDefault()
                .getPreferenceStore();

        for (final IRepository repository : RepositoryManager.getInstance()
                .getAllRepositories()) {
            if (repository.isShared()) {
                backup.put(repository.getName(),
                        preferenceStore.getBoolean(repository.getName()));
            }
        }
        backup.put(TeamPreferencesConstants.ApplyAllState, preferenceStore
                .getString(TeamPreferencesConstants.ApplyAllState));
        return backup;
    }

    public static String getLastConnectionDateConstant(final String workspace) {
        return workspace + TeamPreferencesConstants.WORKSPACE_CONNECTION_DATE;
    }

    public static void setConnectionDate(final String workspace) {
        TeamPlugin
                .getDefault()
                .getPreferenceStore()
                .setValue(getLastConnectionDateConstant(workspace),
                        System.currentTimeMillis());
    }

    public static long getConnectionDate(final String workspace) {
        return TeamPlugin.getDefault().getPreferenceStore()
                .getLong(getLastConnectionDateConstant(workspace));
    }

    public static List<IRepository> getSharedRepositories() {
        final List<IRepository> result = new ArrayList<IRepository>();
        for (final IRepository repo : RepositoryManager.getInstance()
                .getAllRepositories()) {
            if (repo.isShared()) {
                result.add(repo);
            }
        }
        return result;
    }

    /**
     * @param resourceUri: a platform resource URI
     * @return a Tooltip for Diagram editors in read-only mode
     */
    public static String computeLockOwnerTitleTooltip(final URI resourceUri, final IProgressMonitor progressMonitor) {
        String lockOwnerTooltipText = Messages.readOnlyMode + " ";
        if (TeamRepositoryUtil.isRepositoryOnLine() && resourceUri.isPlatformResource()) {
            final String platformString = resourceUri.toPlatformString(true);
            final IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(platformString);
            final ScanResourcesLockOperation operation = new ScanResourcesLockOperation(resource);
            try {
                operation.run(progressMonitor);
                final String lockOwnerName = operation.getLockOwner(resource);
                if (lockOwnerName != null) {
                    lockOwnerTooltipText += NLS.bind(Messages.lockEditorTitleTooltip, lockOwnerName);
                }
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        return lockOwnerTooltipText;
    }

}
