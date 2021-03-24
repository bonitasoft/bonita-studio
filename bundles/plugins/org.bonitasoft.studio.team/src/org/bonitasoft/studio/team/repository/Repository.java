/*******************************************************************************
 * Copyright (C) 2009, 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.repository;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.extension.ExtensionContextInjectionFactory;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.ProjectFileChangeListener;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.filestore.FileStoreChangeEvent;
import org.bonitasoft.studio.common.repository.filestore.FileStoreChangeEvent.EventType;
import org.bonitasoft.studio.common.repository.jdt.JDTTypeHierarchyManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.team.RepositoryFileChangeLog;
import org.bonitasoft.studio.team.TeamPlugin;
import org.bonitasoft.studio.team.TeamRepositoryUtil;
import org.bonitasoft.studio.team.core.SVNLockManager;
import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.team.jobs.SharedRepositoryUpdateBackgroundJob;
import org.bonitasoft.studio.team.operations.CommitResourcesOperation;
import org.bonitasoft.studio.team.operations.ScanResourcesLockOperation;
import org.bonitasoft.studio.team.operations.UpdateRepositoryResourcesOperation;
import org.bonitasoft.studio.team.preferences.TeamPreferenceInitializer;
import org.bonitasoft.studio.team.preferences.TeamPreferencesConstants;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.egit.core.GitProvider;
import org.eclipse.egit.core.op.ConnectProviderOperation;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.team.core.RepositoryProvider;
import org.eclipse.team.svn.core.IStateFilter;
import org.eclipse.team.svn.core.SVNTeamPlugin;
import org.eclipse.team.svn.core.connector.ISVNConnector;
import org.eclipse.team.svn.core.connector.ISVNCredentialsPrompt;
import org.eclipse.team.svn.core.connector.SVNConnectorException;
import org.eclipse.team.svn.core.extension.CoreExtensionsManager;
import org.eclipse.team.svn.core.resource.IRepositoryLocation;
import org.eclipse.team.svn.core.resource.IRepositoryRoot;
import org.eclipse.team.svn.core.svnstorage.SVNRemoteStorage;
import org.eclipse.team.svn.core.utility.FileUtility;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Romain Bioteau
 */
public class Repository extends AbstractRepository {

    private static final long INTERVAL = 30000;
    private static final String GITIGNORE_TEMPLATE = ".gitignore.template";
    private static final String ENCODING = "UTF-8";

    private final String PRODUCTID_WORKSPACEAPI = "org.bonitasoft.studio.workspaceAPI"; // SP
    private final SharedRepositoryUpdateBackgroundJob updateJob;
    private SVNLockManager lockManagement;

    public Repository(final IWorkspace workspace, final IProject project,
            final ExtensionContextInjectionFactory extensionContextInjectionFactory,
            final JDTTypeHierarchyManager jdtTypeHierarchyManager,
            IEventBroker eventBroker,
            final boolean migrationEnabled) {
        super(workspace,
                project, 
                extensionContextInjectionFactory,
                jdtTypeHierarchyManager, 
                eventBroker,
                migrationEnabled);
        updateJob = new SharedRepositoryUpdateBackgroundJob("Update shared repository job", INTERVAL, this);
    }

    @Override
    protected ProjectFileChangeListener createProjectFileChangeListener() {
        return new ProjectFileChangeListenerEx(this);
    }

    protected void initializeLockManager() {
        lockManagement = createLockManager();
    }

    @Override
    protected void connect(IProject project) throws CoreException {
        File gitDir = new File(project.getLocation().toFile().getAbsolutePath(),
                Constants.DOT_GIT);
        if (gitDir.exists() && !isShared(GitProvider.ID)) {
            ConnectProviderOperation op = new ConnectProviderOperation(project);
            op.setRefreshResources(false);
            op.execute(AbstractRepository.NULL_PROGRESS_MONITOR);
        }
    }

    @Override
    protected synchronized void initRepositoryStores(final IProgressMonitor monitor) {
        super.initRepositoryStores(monitor);
        initializeLockManager();
    }

    protected SVNLockManager createLockManager() {
        return new SVNLockManager(getRepositoryStore(DiagramRepositoryStore.class));
    }

    @Override
    public AbstractRepository create(ProjectMetadata metadata, IProgressMonitor monitor) {
        //Initialize SVN extensions
        if (PlatformUI.isWorkbenchRunning() && !PlatformUtil.isHeadless()) {
            CoreExtensionsManager.instance().getAccessibleClients();
            new TeamPreferenceInitializer().initializeDefaultPreferences();
        }
        return super.create(metadata, monitor);
    }

    @Override
    public void close() {
        stopUpdateJob();
        super.close();
    }

    public boolean isAutoShare() {
        final IPreferenceStore preferenceStore = TeamPlugin.getDefault()
                .getPreferenceStore();
        final String applyAllState = preferenceStore
                .getString(TeamPreferencesConstants.ApplyAllState);
        if (TeamPreferencesConstants.CUSTOM.equals(applyAllState)) {
            return preferenceStore.getBoolean(getName());
        } else {
            return TeamPreferencesConstants.AUTO.equals(applyAllState);
        }
    }

    @Override
    public boolean isShared() {
        if (!getProject().exists()) {
            return false;
        }
        if (getProject().isAccessible()) {
            return RepositoryProvider.getProvider(getProject()) != null;
        } else {
            Path gitFolder = getProject().getLocation().toFile().toPath().resolve(".git");
            Path svnFolder = getProject().getLocation().toFile().toPath().resolve(".svn");
            return gitFolder.toFile().exists() || svnFolder.toFile().exists();
        }
    }


    @Override
    public boolean isShared(String providerId) {
        IProject project = getProject();
        IPath location = project.getLocation();
        if (project.isAccessible()) {
            return RepositoryProvider.getProvider(project, providerId) != null;
        } else if(location != null){
            if(providerId.equals("org.eclipse.egit.core.GitProvider")){
                return location.toFile().toPath().resolve(".git").toFile().exists();
            }else if(providerId.equals("org.eclipse.team.svn.core.svnnature")){
                return location.toFile().toPath().resolve(".svn").toFile().exists();
            }
        }
        return false;
    }

    @Override
    public void handleFileStoreEvent(final FileStoreChangeEvent event) {
        super.handleFileStoreEvent(event);
        final IRepositoryFileStore file = event.getFileStore();
        if (PlatformUI.isWorkbenchRunning()) {
            if (importEventOnSharedRepository(event, file)
                    || file != null && file.isShared() && TeamRepositoryUtil.isRepositoryOnLine()) {
                if (PRODUCTID_WORKSPACEAPI.equals(Platform.getProduct().getId())) {
                    final IProgressService progressService = PlatformUI.getWorkbench().getProgressService();
                    Display.getDefault().asyncExec(new Runnable() {

                        @Override
                        public void run() {
                            try {
                                progressService.busyCursorWhile(synchronizationRunnable(event, file));
                            } catch (final InvocationTargetException e) {
                                BonitaStudioLog
                                        .error(String.format("Failed to synchronize resource %s", file.getName()), e);
                            } catch (final InterruptedException e) {
                                BonitaStudioLog
                                        .error(String.format("Failed to synchronize resource %s", file.getName()), e);
                            }
                        }
                    });
                } else {
                    final Display display = Display.getDefault();
                    display.syncExec(new Runnable() {

                        @Override
                        public void run() {
                            BusyIndicator.showWhile(display, new Runnable() {

                                @Override
                                public void run() {
                                    try {
                                        synchronizationRunnable(event, file).run(NULL_PROGRESS_MONITOR);
                                    } catch (final InvocationTargetException e) {
                                        BonitaStudioLog.error(
                                                String.format("Failed to synchronize resource %s", file.getName()), e);
                                    } catch (final InterruptedException e) {
                                        BonitaStudioLog.error(
                                                String.format("Failed to synchronize resource %s", file.getName()), e);
                                    }
                                }
                            });
                        }

                    });

                }
            }
        }
    }

    protected IRunnableWithProgress synchronizationRunnable(final FileStoreChangeEvent event,
            final IRepositoryFileStore file) {
        return new IRunnableWithProgress() {

            @Override
            public void run(final IProgressMonitor monitor) throws InvocationTargetException,
                    InterruptedException {
                monitor.beginTask(Messages.synchronization, IProgressMonitor.UNKNOWN);
                try {
                    switch (event.getEvent()) {
                        case PRE_OPEN:
                            preOpen(file);
                            break;
                        case POST_CLOSE:
                            postClose(file, event);
                            break;
                        case POST_DELETE:
                            postDelete(file);
                            break;
                        case PRE_SAVE:
                            break;
                        case POST_SAVE:
                            postSave(file);
                            break;
                        case PRE_IMPORT:
                            preImport(Repository.this);
                            break;
                        case POST_IMPORT:
                            postImport(Repository.this);
                            break;
                        case PRE_DELETE:
                        case POST_OPEN:
                        case PRE_CLOSE:
                            break;
                        default:
                            throw new IllegalStateException("Unhandled file store event");
                    }
                } catch (final Exception e) {
                    BonitaStudioLog.error(e);
                }
                monitor.done();
            }
        };
    }

    protected boolean importEventOnSharedRepository(
            final FileStoreChangeEvent event, final IRepositoryFileStore file) {
        return file == null && isShared()
                && (event.getEvent() == EventType.POST_IMPORT || event.getEvent() == EventType.PRE_IMPORT)
                && TeamRepositoryUtil.isRepositoryOnLine();
    }

    protected void preImport(
            final Repository sharedRepository) {
        if (isAutoShare()) {
            final IRepository currentRepo = RepositoryManager.getInstance().getCurrentRepository();
            if (currentRepo instanceof Repository) {
                ((Repository) currentRepo).stopUpdateJob();
            }

        }
    }

    protected void postSave(final IRepositoryFileStore file) {
        if (isAutoShare()) {
            try {
                new CommitResourcesOperation(file.getParentStore().getResource(),
                        new RepositoryFileChangeLog(file).getComment(),
                        lockManagement).run(NULL_PROGRESS_MONITOR);
            } catch (final InvocationTargetException e) {
                BonitaStudioLog.error(e);
            } catch (final InterruptedException e) {
                BonitaStudioLog.error(e);
            }

        }
    }

    protected void postImport(final IRepository repository) {
        if (isAutoShare()) {

            try {
                new CommitResourcesOperation(repository.getProject(),
                        "Import new Bos Archive",
                        lockManagement).run(NULL_PROGRESS_MONITOR);
            } catch (final InvocationTargetException e) {
                BonitaStudioLog.log("error while commiting changed files in " + repository.getName());
                BonitaStudioLog.error(e);
            } catch (final InterruptedException e) {
                BonitaStudioLog.log("error while commiting changed files in " + repository.getName());
                BonitaStudioLog.error(e);
            }

            final IRepository currentRepo = RepositoryManager.getInstance().getCurrentRepository();
            if (currentRepo instanceof Repository) {
                ((Repository) currentRepo).startUpdateJob();
            }
        }
    }

    protected SVNRemoteStorage getSVNRemoteStorage() {
        return SVNRemoteStorage.instance();
    }

    protected void postClose(final IRepositoryFileStore file, final FileStoreChangeEvent event)
            throws InvocationTargetException, InterruptedException,
            CoreException {
        file.setReadOnly(false);
        boolean askForReleaseLock = true;
        if (event != null) {
            final Object value = event.getParameter(AbstractFileStore.ASK_ACTION_ON_CLOSE);
            if (value != null) {
                askForReleaseLock = (Boolean) value;
            }
        }
        final ScanResourcesLockOperation scan = lockManagement.runScanResourcesLockOperation(file.getResource());
        if (scan.isLocalyLocked() && askForReleaseLock) {
            final Set<IResource> resourcesToLock = new HashSet<>();
            resourcesToLock.add(file.getResource());
            resourcesToLock.addAll(file.getRelatedResources());
            if (resourcesToLock.size() > 1) {
                lockManagement.releaseLock(resourcesToLock.toArray(new IResource[resourcesToLock.size()]));
            } else {
                lockManagement.releaseLock(file.getResource(), isAutoShare(), Display.getDefault());
            }
        }
    }

    protected void preOpen(final IRepositoryFileStore file) throws CoreException {
        final IResource resource = file.getResource();
        if (resource != null) {
            if (isNew(resource) && isAutoShare()) {
                try {
                    new CommitResourcesOperation(resource,
                            "Added file: [" + file.getDisplayName() + "]", lockManagement).run(NULL_PROGRESS_MONITOR);
                } catch (final InvocationTargetException e) {
                    BonitaStudioLog.error(e);
                } catch (final InterruptedException e) {
                    BonitaStudioLog.error(e);
                }
            }
            if (file.isReadOnly() && isAutoShare()) {
                // update it and reset it to readOnly
                file.setReadOnly(false);
                try {
                    new UpdateRepositoryResourcesOperation(resource).run(NULL_PROGRESS_MONITOR);
                } catch (final InvocationTargetException e) {
                    BonitaStudioLog.error(e);
                } catch (final InterruptedException e) {
                    BonitaStudioLog.error(e);
                }
                file.setReadOnly(true);
            } else {
                if (isAutoShare()) {
                    try {
                        new UpdateRepositoryResourcesOperation(resource).run(NULL_PROGRESS_MONITOR);
                    } catch (final InvocationTargetException e) {
                        BonitaStudioLog.error(e);
                    } catch (final InterruptedException e) {
                        BonitaStudioLog.error(e);
                    }
                }
                // try to lock it
                final ScanResourcesLockOperation scan = new ScanResourcesLockOperation(resource);
                resource.getWorkspace().run(scan, NULL_PROGRESS_MONITOR);
                // artifact already locker in an other session
                if (scan.isLockedByOther()) {
                    file.setReadOnly(true);
                    Display.getDefault().syncExec(new Runnable() {

                        @Override
                        public void run() {
                            MessageDialog.openWarning(Display.getDefault().getActiveShell(),
                                    Messages.openInReadOnly_title,
                                    NLS.bind(Messages.openInReadOnly_msg, scan.getLockOwner(resource)));
                        }
                    });

                } else if (!scan.isLocalyLocked() && !file.isReadOnly()) {
                    // not locked -> lock it
                    final Set<IResource> resourcesToLock = new HashSet<>();
                    resourcesToLock.add(resource);
                    resourcesToLock.addAll(file.getRelatedResources());
                    lockManagement.lock(resourcesToLock.toArray(new IResource[resourcesToLock.size()]));
                }
            }
        }

    }

    protected void postDelete(final IRepositoryFileStore file) {
        if (isAutoShare()) {
            try {
                new CommitResourcesOperation(file.getParentStore().getResource(),
                        "Removed file: [" + file.getName() + "]",
                        lockManagement).run(NULL_PROGRESS_MONITOR);
            } catch (final InvocationTargetException e) {
                BonitaStudioLog.error(e);
            } catch (final InterruptedException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    public boolean isNew(final IResource resource) {
        return FileUtility.filterResources(new IResource[] { resource }, IStateFilter.SF_NEW).length > 0;
    }

    public void stopUpdateJob() {
        updateJob.stop();
        updateJob.cancel();
        try {
            Job.getJobManager().join(SharedRepositoryUpdateBackgroundJob.FAMILY, NULL_PROGRESS_MONITOR);
        } catch (final InterruptedException e) {
            BonitaStudioLog.error(e);
        }
    }

    public void startUpdateJob() {
        updateJob.schedule(1000);
    }

    public IRepositoryLocation getLocationForRepository() {
        if (!new File(getProject().getLocation().toFile(), ".svn").exists()) {
            return null;
        }
        final SVNRemoteStorage storage = getSVNRemoteStorage();
        final RepositoryProvider provider = RepositoryProvider.getProvider(getProject());
        if (provider == null) {
            return null;
        }
        return storage.getRepositoryLocation(getProject());
    }

    @Override
    public boolean isOnline() {
        if (!isShared(SVNTeamPlugin.NATURE_ID)) {
            return true;
        }
        final IRepositoryLocation locationForRepository = getLocationForRepository();
        if (locationForRepository == null) {
            return false;
        }
        final IRepositoryRoot root = locationForRepository.getRoot();
        try {
            root.getChildren();
        } catch (final SVNConnectorException e) {
            return false;
        }
        return true;
    }

    public String getUser() {
        final IRepositoryLocation location = getLocationForRepository();
        if (location != null) {
            String username = location.getUsername();
            if (username == null || username.isEmpty()) {
                final ISVNConnector acquireSVNProxy = location.acquireSVNProxy();
                final ISVNCredentialsPrompt prompt = acquireSVNProxy.getPrompt();
                prompt.prompt(location, location.getUrl());
                username = prompt.getUsername();
                location.releaseSVNProxy(acquireSVNProxy);
            }
            return username;
        }
        return null;
    }

    public void repositoryUpdated(IProgressMonitor monitor) {
        getAllStores().stream().forEach(IRepositoryStore::repositoryUpdated);
    }

    @Override
    public void migrate(IProgressMonitor monitor) throws CoreException, MigrationException {
        migrateGitIgnoreFile(monitor);
        super.migrate(monitor);
    }

    private void migrateGitIgnoreFile(IProgressMonitor monitor) throws CoreException, MigrationException {
        IFile gitIgnore = getProject().getFile(".gitignore");
        if (gitIgnore.exists()) {
            try (InputStream is = gitIgnore.getContents()) {
                List<String> existingEntries = retrieveGitignoreEntries(is, ENCODING);
                List<String> entriesToAdd = retrieveEntriesToAdd(existingEntries);
                if (!entriesToAdd.isEmpty()) {
                    existingEntries.add(System.lineSeparator());
                    existingEntries.addAll(entriesToAdd);
                    String newContent = existingEntries.stream().reduce("", (s1, s2) -> s1 + System.lineSeparator() + s2);
                    gitIgnore.setContents(new ByteArrayInputStream(newContent.getBytes(ENCODING)),
                            IResource.KEEP_HISTORY | IResource.FORCE, monitor);
                }
            } catch (IOException e) {
                throw new MigrationException("Failed to update .gitignore file.", e);
            }
        }
    }

    private List<String> retrieveEntriesToAdd(List<String> existingEntries) throws IOException {
        URL gitignoreTemplateUrl = getGitignoreTemplateFileURL();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(gitignoreTemplateUrl.openStream(), ENCODING))) {
            List<String> entries = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                if (lineIsValid(line) && !existingEntries.contains(line)) {
                    entries.add(line);
                }
            }
            return entries;
        }
    }

    private boolean lineIsValid(String line) {
        return line != null && !line.isEmpty() && !line.startsWith("#");
    }

    private static List<String> retrieveGitignoreEntries(InputStream is, String encoding) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, encoding))) {
            List<String> entries = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                entries.add(line);
            }
            return entries;
        }
    }

    public static URL getGitignoreTemplateFileURL() throws IOException {
        return FileLocator.toFileURL(Repository.class.getResource(GITIGNORE_TEMPLATE));
    }

}
