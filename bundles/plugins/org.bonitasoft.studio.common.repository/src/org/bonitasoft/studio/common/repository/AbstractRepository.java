/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.repository;

import static org.eclipse.core.runtime.Path.fromOSString;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.DateUtil;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.extension.ExtensionContextInjectionFactory;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.ActiveOrganizationProvider;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.CreateBonitaProjectOperation;
import org.bonitasoft.studio.common.repository.core.DatabaseHandler;
import org.bonitasoft.studio.common.repository.core.ProjectDependenciesStore;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectDependenciesStore;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.migration.report.AsciidocMigrationReportWriter;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReportWriter;
import org.bonitasoft.studio.common.repository.core.migration.step.RemoveLegacyFolderStep;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.filestore.FileStoreChangeEvent;
import org.bonitasoft.studio.common.repository.jdt.JDTTypeHierarchyManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.operation.ExportBosArchiveOperation;
import org.bonitasoft.studio.common.repository.preferences.RepositoryPreferenceConstant;
import org.bonitasoft.studio.common.repository.store.LocalDependenciesStore;
import org.bonitasoft.studio.common.repository.store.RepositoryStoreComparator;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.common.ui.PlatformUtil;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.internal.ViewIntroAdapterPart;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

public abstract class AbstractRepository implements IRepository {

    private static final String REPOSITORY_STORE_EXTENSION_POINT_ID = "org.bonitasoft.studio.repositoryStore";

    public static final IProgressMonitor NULL_PROGRESS_MONITOR = new NullProgressMonitor();

    private static final String CLASS = "class";

    private BonitaProject project;

    protected SortedMap<Class<?>, IRepositoryStore<? extends IRepositoryFileStore>> stores;

    private final JDTTypeHierarchyManager jdtTypeHierarchyManager;

    private final ExtensionContextInjectionFactory extensionContextInjectionFactory;

    private final IWorkspace workspace;

    private boolean isLoaded = false;

    private List<IBonitaProjectListener> projectListeners = new ArrayList<>();

    private static Object openCloseLock = new Object();

    private ReentrantLock loadStoreLock = new ReentrantLock();

    private boolean enableOpenIntroListener = true;

    private ProjectDependenciesStore projectDependenciesStore;

    private IEventBroker eventBroker;

    private MigrationReportWriter reportWriter = new AsciidocMigrationReportWriter();

    protected AbstractRepository(final IWorkspace workspace,
            BonitaProject project,
            final ExtensionContextInjectionFactory extensionContextInjectionFactory,
            final JDTTypeHierarchyManager jdtTypeHierarchyManager,
            IEventBroker eventBroker) {
        this.workspace = workspace;
        this.project = project;
        this.jdtTypeHierarchyManager = jdtTypeHierarchyManager;
        this.extensionContextInjectionFactory = extensionContextInjectionFactory;
        this.eventBroker = eventBroker;
    }

    @Override
    public String getProjectId() {
        return project.getId();
    }

    @Override
    public AbstractRepository create(ProjectMetadata metadata, final IProgressMonitor monitor) {
        final long init = System.currentTimeMillis();
        BonitaStudioLog.debug("Creating project " + project.getId() + "...", CommonRepositoryPlugin.PLUGIN_ID);
        try {
            if (!project.exists()) {
                CreateBonitaProjectOperation createBonitaProjectOperation = newProjectWorkspaceOperation(metadata,
                        workspace);
                workspace.run(createBonitaProjectOperation, monitor);
                final long duration = System.currentTimeMillis() - init;
                BonitaStudioLog.debug(
                        "Project " + project.getId() + " created in " + DateUtil.getDisplayDuration(duration),
                        CommonRepositoryPlugin.PLUGIN_ID);
            }
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
        return this;
    }

    private Path reportPath() {
        return project.getAppProject().getFile(MigrationReportWriter.DEFAULT_REPORT_FILE_NAME).getLocation().toFile()
                .toPath();
    }

    protected CreateBonitaProjectOperation newProjectWorkspaceOperation(ProjectMetadata metadata,
            IWorkspace workspace) {
        return new CreateBonitaProjectOperation(workspace, metadata);
    }

    @Override
    public boolean isShared() {
        return false;
    }

    @Override
    public IProject getProject() {
        return project.getAppProject();
    }

    @Override
    public AbstractRepository open(final IProgressMonitor monitor) {
        synchronized (openCloseLock) {
            try {
                if (!project.getAppProject().isOpen()) {
                    BonitaStudioLog.log("Opening project: " + project.getId());
                    project.getAppProject().open(NULL_PROGRESS_MONITOR);
                }
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
            try {
                initRepositoryStores(NULL_PROGRESS_MONITOR);
                connect(project.getAppProject());
                this.projectDependenciesStore = new MavenProjectDependenciesStore(project, eventBroker);
                RepositoryManager.getInstance().setCurrentRepository(this);
                AbstractFileStore.refreshExplorerView();
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }

            try {
                project.refresh(new NullProgressMonitor());
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }

            for (IBonitaProjectListener listener : getProjectListeners()) {
                listener.projectOpened(this, monitor);
            }
            updateCurrentRepositoryPreference();
            return this;
        }
    }

    @Override
    public void close(IProgressMonitor monitor) {
        synchronized (openCloseLock) {
            try {
                BonitaStudioLog.debug("Closing repository " + project.getId(), CommonRepositoryPlugin.PLUGIN_ID);
                closeAllEditors(false);
                for (IBonitaProjectListener listener : getProjectListeners()) {
                    listener.projectClosed(this, monitor);
                }
                var appProject = project.getAppProject();
                if (appProject.exists() && appProject.isOpen()) {
                    new ActiveOrganizationProvider().flush();
                }
                if (stores != null) {
                    for (final IRepositoryStore<? extends IRepositoryFileStore> store : stores.values()) {
                        store.close();
                    }
                }
                if (appProject.exists() && appProject.isOpen()) {
                    appProject.close(monitor);
                }
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
            if (stores != null) {
                stores.clear();
                stores = null;
            }
            isLoaded = false;
        }
    }

    protected void connect(IProject project) throws CoreException {
        //May be implemented by sublcasses
    }

    protected void updateCurrentRepositoryPreference() {
        CommonRepositoryPlugin.getDefault().getPreferenceStore().setValue(
                RepositoryPreferenceConstant.CURRENT_REPOSITORY,
                project.getId());
        try {
            ((ScopedPreferenceStore) CommonRepositoryPlugin.getDefault().getPreferenceStore()).save();
        } catch (final IOException e) {
            BonitaStudioLog.error(e);
        }
    }

    private void enableOpenIntroListener() {
        this.enableOpenIntroListener = true;
    }

    @Override
    public boolean isOpenIntroListenerEnabled() {
        return this.enableOpenIntroListener;
    }

    @Override
    public void disableOpenIntroListener() {
        this.enableOpenIntroListener = false;
    }

    @Override
    public boolean closeAllEditors(boolean save) {
        disableOpenIntroListener();
        final AtomicBoolean allEditorClosed = new AtomicBoolean(true);
        if (PlatformUI.isWorkbenchRunning()) {
            Display.getDefault().syncExec(() -> {

                final IWorkbenchWindow activeWorkbenchWindow = PlatformUI
                        .getWorkbench().getActiveWorkbenchWindow();
                if (activeWorkbenchWindow != null
                        && activeWorkbenchWindow.getActivePage() != null) {
                    if (!activeWorkbenchWindow.getActivePage().closeAllEditors(save)) {
                        allEditorClosed.set(false);
                        return;
                    }
                }
                enableOpenIntroListener();
                while (activeWorkbenchWindow.getActivePage().getActiveEditor() != null
                        && !(activeWorkbenchWindow.getActivePage().getActivePart() instanceof ViewIntroAdapterPart)) {
                    Display.getDefault().readAndDispatch();
                }

            });
        }
        return allEditorClosed.get();
    }

    protected void initRepositoryStores(final IProgressMonitor monitor) {
        loadStoreLock.lock();
        try {
            isLoaded = false;
            if (stores == null || stores.isEmpty()) {
                createStores(monitor);
                registerBonitaProjectListeners();
            }
            isLoaded = true;
        } finally {
            loadStoreLock.unlock();
        }

    }

    protected void registerBonitaProjectListeners() {
        Objects.requireNonNull(stores);
        asStoreList(stores).stream()
                .filter(IBonitaProjectListener.class::isInstance)
                .map(IBonitaProjectListener.class::cast)
                .forEach(this::addProjectListener);
    }

    protected void createStores(IProgressMonitor monitor) {
        stores = new TreeMap<>((o1, o2) -> o1.getName().compareTo(o2.getName()));
        final IConfigurationElement[] repositoryStoreConfigurationElements = BonitaStudioExtensionRegistryManager
                .getInstance().getConfigurationElements(
                        REPOSITORY_STORE_EXTENSION_POINT_ID);
        for (final IConfigurationElement configuration : repositoryStoreConfigurationElements) {
            try {
                final IRepositoryStore<? extends IRepositoryFileStore> store = createRepositoryStore(configuration,
                        monitor);
                stores.put(store.getClass(), store);
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    protected IRepositoryStore<? extends IRepositoryFileStore> createRepositoryStore(
            final IConfigurationElement configuration, final IProgressMonitor monitor) throws CoreException {
        final IRepositoryStore<? extends IRepositoryFileStore> store;
        try {
            store = extensionContextInjectionFactory.make(configuration, CLASS, IRepositoryStore.class);
            String displayName = IDisplayable.toDisplayName(store).orElseGet(store::getName);
            monitor.subTask(NLS.bind(Messages.creatingStore, displayName));
            store.createRepositoryStore(this);
            monitor.worked(1);
            return store;
        } catch (final ClassNotFoundException e) {
            throw new CoreException(new Status(IStatus.ERROR, CommonRepositoryPlugin.PLUGIN_ID,
                    "Failed to instantiate Repository store", e));
        }
    }

    @Override
    public void build(IProgressMonitor monitor) {
        try {
            if (monitor == null) {
                monitor = NULL_PROGRESS_MONITOR;
            }
            if (!getProject().isSynchronized(IResource.DEPTH_ONE)) {
                getProject().refreshLocal(IResource.DEPTH_ONE, monitor);
            }
            project.getAppProject().build(IncrementalProjectBuilder.INCREMENTAL_BUILD, monitor);
        } catch (final Exception ex) {
            BonitaStudioLog.error(ex);
        }
    }

    @Override
    public IJavaProject getJavaProject() {
        return project.getAdapter(IJavaProject.class);
    }

    @Override
    public void delete(final IProgressMonitor monitor) {
        BonitaStudioLog.debug("Deleting project " + project.getId(), CommonRepositoryPlugin.PLUGIN_ID);
        try {
            if (project.getAppProject().isOpen()) {
                build(NULL_PROGRESS_MONITOR);
            }
            project.getAppProject().delete(true, true, NULL_PROGRESS_MONITOR);
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
    }

    @Override
    public <T> T getRepositoryStore(final Class<T> repositoryStoreClass) {
        if (!isLoaded()) {
            initRepositoryStores(NULL_PROGRESS_MONITOR);
        }
        return repositoryStoreClass.cast(stores.get(repositoryStoreClass));
    }

    @Override
    public List<IRepositoryStore<? extends IRepositoryFileStore>> getAllStores() {
        initRepositoryStores(NULL_PROGRESS_MONITOR);
        return asStoreList(stores);
    }

    private List<IRepositoryStore<? extends IRepositoryFileStore>> asStoreList(
            SortedMap<Class<?>, IRepositoryStore<? extends IRepositoryFileStore>> stores) {
        return stores.values().stream()
                .distinct()
                .sorted(new RepositoryStoreComparator())
                .collect(Collectors.toList());
    }

    @Override
    public List<IRepositoryStore<? extends IRepositoryFileStore>> getAllSharedStores() {
        final List<IRepositoryStore<? extends IRepositoryFileStore>> result = new ArrayList<>();
        for (final IRepositoryStore<? extends IRepositoryFileStore> sotre : getAllStores()) {
            if (sotre.isShared()) {
                result.add(sotre);
            }
        }
        Collections.sort(result, new RepositoryStoreComparator());
        return result;
    }

    @Override
    public IStatus exportToArchive(final String fileName) {
        final ExportBosArchiveOperation operation = new ExportBosArchiveOperation();
        operation.setDestinationPath(fileName);
        operation.setBonitaProject(RepositoryManager.getInstance().getCurrentProject().orElseThrow());
        final Set<IRepositoryFileStore> fileStores = new HashSet<>();
        for (final IRepositoryStore<?> store : getAllExportableStores()) {
            for (final IRepositoryFileStore fs : store.getChildren()) {
                if (fs.canBeExported()) {
                    fileStores.add(fs);
                    fileStores.addAll(fs.getRelatedFileStore());
                }
            }
        }
        operation.setFileStores(fileStores);
        final IStatus status = operation.run(NULL_PROGRESS_MONITOR);
        if (!status.isOK()) {
            logErrorStatus(status);
        } else {
            BonitaStudioLog.info(String.format("%s archive exported successfully.", fileName),
                    CommonRepositoryPlugin.PLUGIN_ID);
        }
        return status;
    }

    protected void logErrorStatus(final IStatus status) {
        final StringBuilder sb = new StringBuilder();
        if (status.isMultiStatus()) {
            for (final IStatus childStatus : status.getChildren()) {
                sb.append(childStatus.getMessage()).append("\n");
            }

        }
        BonitaStudioLog.error("Export to archive failed.\n" + status.getMessage() + "\n" + sb.toString(),
                CommonRepositoryPlugin.PLUGIN_ID);
    }

    @Override
    public IRepositoryFileStore getFileStore(final IResource resource) {
        try {
            if (resource == null
                    || (resource instanceof IProject && ((IProject) resource).isOpen()
                            && ((IProject) resource).hasNature(BonitaProjectNature.NATURE_ID))
                    || (resource instanceof IProject && !((IProject) resource).isOpen())) {
                return null;
            }
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
        }
        for (final IRepositoryStore<? extends IRepositoryFileStore> store : getAllStores()) {
            IFolder container = store.getResource();
            if (Objects.equals(resource.getParent(), container)
                    || Objects.equals(resource.getParent(), resource.getWorkspace().getRoot())) {
                IRepositoryFileStore fStore = store.getChild(resource.getName(), false);
                if (fStore != null) {
                    return fStore;
                }
            }
        }
        return null;
    }

    @Override
    public ProjectDependenciesStore getProjectDependenciesStore() {
        return projectDependenciesStore;
    }

    @Override
    public IRepositoryStore<? extends IRepositoryFileStore> getRepositoryStore(final IResource resource) {
        for (final IRepositoryStore<? extends IRepositoryFileStore> store : getAllStores()) {
            if (resource instanceof IFile) {
                final IResource storeResource = store.getResource();
                IResource parent = resource.getParent();
                while (parent != null && !storeResource.equals(parent)) {
                    parent = parent.getParent();
                }
                if (parent != null) {
                    return store;
                }
            } else {
                if (store.getResource() != null && store.getResource().equals(resource)) {
                    return store;
                }
            }
        }
        return null;
    }

    @Override
    public void handleFileStoreEvent(final FileStoreChangeEvent event) {
        jdtTypeHierarchyManager.handleFileStoreEvent(event);
    }

    @Override
    public List<IRepositoryStore<? extends IRepositoryFileStore>> getAllShareableStores() {
        final List<IRepositoryStore<? extends IRepositoryFileStore>> result = new ArrayList<>();
        for (final IRepositoryStore<? extends IRepositoryFileStore> store : getAllStores()) {
            if (store.canBeShared()) {
                result.add(store);
            }
        }
        Collections.sort(result, new RepositoryStoreComparator());
        return result;

    }

    @Override
    public List<IRepositoryStore<? extends IRepositoryFileStore>> getAllExportableStores() {
        final List<IRepositoryStore<? extends IRepositoryFileStore>> result = new ArrayList<>();
        for (final IRepositoryStore<? extends IRepositoryFileStore> store : getAllStores()) {
            if (store.canBeExported()) {
                result.add(store);
            }
        }
        Collections.sort(result, new RepositoryStoreComparator());
        return result;
    }

    @Override
    public IRepositoryFileStore asRepositoryFileStore(final Path path, boolean force)
            throws IOException, CoreException {
        var appProject = project.getAppProject();
        if (!appProject.isAccessible() || appProject.getLocation() == null) {
            return null;
        }
        final IPath resourcePath = fromOSString(path.toString()).makeRelativeTo(appProject.getLocation());
        if (resourcePath.isRoot() || resourcePath.isEmpty()
                || Objects.equals(org.eclipse.core.runtime.Path.fromOSString(".."), resourcePath)) {
            return null;
        }
        IResource iResource = null;
        try {
            iResource = isFile(resourcePath) ? appProject.getFile(resourcePath) : appProject.getFolder(resourcePath);
        } catch (IllegalArgumentException e) {
            return null;
        }
        if (!iResource.exists()) {
            if (force) {
                iResource.getParent().refreshLocal(IResource.DEPTH_ONE, NULL_PROGRESS_MONITOR);
                if (!iResource.exists()) {
                    throw new FileNotFoundException(path.toFile().getAbsolutePath());
                }
            } else {
                return null;
            }
        }

        final IPath projectRelativePath = iResource.getProjectRelativePath();
        if (projectRelativePath.segmentCount() > 0) {
            final IPath iPath = projectRelativePath.removeFirstSegments(0);
            if (iPath.segmentCount() > 1) {
                final String storeName = iPath.segments()[0];
                final IFile file = getProject().getFile(iPath);
                return getRepositoryStoreByName(storeName)
                        .filter(repositoryStore -> belongToRepositoryStore(repositoryStore, file))
                        .map(repositoryStore -> repositoryStore
                                .createRepositoryFileStore(file.getName()))
                        .orElse(null);
            }
        }
        return null;
    }

    private boolean isFile(final IPath resourcePath) {
        return resourcePath.getFileExtension() != null;
    }

    @Override
    public java.util.Optional<IRepositoryStore<? extends IRepositoryFileStore>> getRepositoryStoreByName(
            final String storeName) {
    	if(RemoveLegacyFolderStep.legacyRepositories().contains(storeName)) {
    		return Optional.empty();
    	}
        return getAllStores().stream().filter(store -> Objects.equals(store.getName(), storeName)).findFirst();
    }

    private boolean belongToRepositoryStore(final IRepositoryStore<?> store, final IFile file) {
        final IFolder parentFolder = store.getResource();
        if (parentFolder == null) {
            return false;
        }
        IContainer current = file.getParent();
        while (current != null && !parentFolder.equals(current)) {
            current = current.getParent();
        }
        return current != null && parentFolder.equals(current);
    }

    @Override
    public void migrate(MigrationReport report, IProgressMonitor monitor) throws CoreException, MigrationException {
        Assert.isNotNull(project);

        // Force deps analysis before migration
        getProjectDependenciesStore().analyze(new NullProgressMonitor());

        var orderedStores = getAllStores().stream()
                .sorted(Comparator.comparingInt(IRepositoryStore::getImportOrder))
                .collect(Collectors.toList());
        for (var store : orderedStores) {
            store.createRepositoryStore(this);
            try {
                store.migrate(monitor).merge(report);
            } catch (MigrationException e) {
                throw new CoreException(
                        Status.error("An error occured during the migration", e));
            }
        }
        try {
            report.executePostMigrationOperations(monitor);
        } catch (InvocationTargetException | InterruptedException e) {
            throw new CoreException(Status.error("An error occured during post migration operations", e));
        }
        if (!report.isEmpty()) {
            try {
                reportWriter.write(report, reportPath());
                project.getAppProject().getFile(MigrationReportWriter.DEFAULT_REPORT_FILE_NAME).refreshLocal(
                        IResource.DEPTH_ONE,
                        new NullProgressMonitor());
            } catch (IOException e) {
                throw new CoreException(new Status(IStatus.ERROR, AbstractRepository.class, e.getMessage(), e));
            } finally {
                report = MigrationReport.emptyReport();
            }
        }
        project.refresh(monitor);
        build(monitor);
    }

    @Override
    public boolean isOnline() {
        return true;
    }

    @Override
    public boolean exists() {
        return project.exists();
    }

    @Override
    public JDTTypeHierarchyManager getJdtTypeHierarchyManager() {
        return jdtTypeHierarchyManager;
    }

    @Override
    public boolean isLoaded() {
        return isLoaded;
    }

    @Override
    public DatabaseHandler getDatabaseHandler() {
        return new DatabaseHandler(getProject());
    }

    @Override
    public boolean isShared(String providerId) {
        return false;
    }

    @Override
    public void rename(String newName, IProgressMonitor monitor)
            throws InvocationTargetException, InterruptedException {
        if (closeAllEditors(false)) {
            WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {

                @Override
                protected void execute(IProgressMonitor monitor)
                        throws CoreException, InvocationTargetException, InterruptedException {
                    getProjectListeners().stream().forEach(l -> l.projectClosed(AbstractRepository.this, monitor));
                    getProject().move(org.eclipse.core.runtime.Path.fromOSString(newName), true, monitor);
                    RepositoryManager.getInstance().setRepository(newName, monitor);
                }
            };
            operation.run(monitor);
            PlatformUtil.openIntroIfNoOtherEditorOpen();
        }
    }

    @Override
    public void addProjectListener(IBonitaProjectListener listener) {
        if (!projectListeners.contains(listener)) {
            projectListeners.add(listener);
        }
    }

    @Override
    public List<IBonitaProjectListener> getProjectListeners() {
        return Collections.unmodifiableList(projectListeners);
    }

    @Override
    public LocalDependenciesStore getLocalDependencyStore() {
        return new LocalDependenciesStore(getProject().getLocation().toFile().toPath());
    }

    @Override
    public String getBonitaRuntimeVersion() {
        try {
            return project.getProjectMetadata(new NullProgressMonitor()).getBonitaRuntimeVersion();
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
            return ProductVersion.BONITA_RUNTIME_VERSION;
        }
    }

}
