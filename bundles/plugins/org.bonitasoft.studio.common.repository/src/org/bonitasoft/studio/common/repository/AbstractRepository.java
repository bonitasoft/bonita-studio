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

import java.io.File;
import java.io.FileInputStream;
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
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.DateUtil;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.extension.ExtensionContextInjectionFactory;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.core.BonitaProjectMigrationOperation;
import org.bonitasoft.studio.common.repository.core.CreateBonitaProjectOperation;
import org.bonitasoft.studio.common.repository.core.DatabaseHandler;
import org.bonitasoft.studio.common.repository.core.MavenProjectModelBuilder;
import org.bonitasoft.studio.common.repository.core.ProjectDependenciesStore;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectDependenciesStore;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.filestore.FileStoreChangeEvent;
import org.bonitasoft.studio.common.repository.jdt.JDTTypeHierarchyManager;
import org.bonitasoft.studio.common.repository.migration.ProcessModelTransformation;
import org.bonitasoft.studio.common.repository.migration.transformation.ConditionExpressionTransformation;
import org.bonitasoft.studio.common.repository.migration.transformation.DatabaseDriverJarReferenceFragmentTransformation;
import org.bonitasoft.studio.common.repository.migration.transformation.DiagramVersionTransformation;
import org.bonitasoft.studio.common.repository.migration.transformation.JavaGetterExpressionTransformation;
import org.bonitasoft.studio.common.repository.migration.transformation.UIPathConnectorDefinitionTransformation;
import org.bonitasoft.studio.common.repository.model.IJavaContainer;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.operation.ExportBosArchiveOperation;
import org.bonitasoft.studio.common.repository.preferences.RepositoryPreferenceConstant;
import org.bonitasoft.studio.common.repository.store.LocalDependenciesStore;
import org.bonitasoft.studio.common.repository.store.RepositoryStoreComparator;
import org.bonitasoft.studio.pics.Pics;
import org.codehaus.groovy.eclipse.core.compiler.GroovySnippetCompiler;
import org.eclipse.core.internal.resources.ProjectDescriptionReader;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.team.svn.core.SVNTeamPlugin;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.internal.ViewIntroAdapterPart;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.osgi.framework.Version;
import org.xml.sax.InputSource;

public abstract class AbstractRepository implements IRepository, IJavaContainer {

    private static final String REPOSITORY_STORE_EXTENSION_POINT_ID = "org.bonitasoft.studio.repositoryStore";

    public static final IProgressMonitor NULL_PROGRESS_MONITOR = new NullProgressMonitor();

    public static final Set<String> LEGACY_REPOSITORIES = new HashSet<>();

    static {
        LEGACY_REPOSITORIES.add("application_resources");
        LEGACY_REPOSITORIES.add("forms");
        LEGACY_REPOSITORIES.add("widgets");
        LEGACY_REPOSITORIES.add("looknfeels");
        LEGACY_REPOSITORIES.add("validators");
        LEGACY_REPOSITORIES.add("src-validators");
        LEGACY_REPOSITORIES.add("simulation");
        LEGACY_REPOSITORIES.add("customTypes");
        LEGACY_REPOSITORIES.add("src-customTypes");
    }

    private static final List<ProcessModelTransformation> PROCESS_MODEL_TRANSFORMATIONS = List.of(
            new DiagramVersionTransformation(),
            new UIPathConnectorDefinitionTransformation(),
            new JavaGetterExpressionTransformation(),
            new ConditionExpressionTransformation(),
            new DatabaseDriverJarReferenceFragmentTransformation());

    private static final String CLASS = "class";

    private final IProject project;

    protected SortedMap<Class<?>, IRepositoryStore<? extends IRepositoryFileStore>> stores;

    private final JDTTypeHierarchyManager jdtTypeHierarchyManager;

    private boolean migrationEnabled = false;

    private final ExtensionContextInjectionFactory extensionContextInjectionFactory;

    private final IWorkspace workspace;

    private boolean isLoaded = false;

    private ProjectFileChangeListener projectFileListener;

    private List<IBonitaProjectListener> projectListeners = new ArrayList<>();

    private boolean enableOpenIntroListener = true;

    private ProjectDependenciesStore projectDependenciesStore;

    private IEventBroker eventBroker;

    private GroovySnippetCompiler groovyCompiler;

    public AbstractRepository(final IWorkspace workspace,
            final IProject project,
            final ExtensionContextInjectionFactory extensionContextInjectionFactory,
            final JDTTypeHierarchyManager jdtTypeHierarchyManager,
            IEventBroker eventBroker,
            final boolean migrationEnabled) {
        this.workspace = workspace;
        this.project = project;
        this.jdtTypeHierarchyManager = jdtTypeHierarchyManager;
        this.migrationEnabled = migrationEnabled;
        this.extensionContextInjectionFactory = extensionContextInjectionFactory;
        this.eventBroker = eventBroker;
        this.projectFileListener = createProjectFileChangeListener();
    }

    protected ProjectFileChangeListener createProjectFileChangeListener() {
        return new ProjectFileChangeListener(this);
    }

    @Override
    public AbstractRepository create(ProjectMetadata metadata, final IProgressMonitor monitor) {
        final long init = System.currentTimeMillis();
        if (BonitaStudioLog.isLoggable(IStatus.OK)) {
            BonitaStudioLog.debug("Creating repository " + project.getName() + "...", CommonRepositoryPlugin.PLUGIN_ID);
        }
        try {
            disableBuild();
            if (!project.exists()) {
                workspace.run(newProjectWorkspaceOperation(metadata, workspace), monitor);
            }
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        } finally {
            if (BonitaStudioLog.isLoggable(IStatus.OK)) {
                final long duration = System.currentTimeMillis() - init;
                BonitaStudioLog.debug(
                        "Repository " + project.getName() + " created in " + DateUtil.getDisplayDuration(duration),
                        CommonRepositoryPlugin.PLUGIN_ID);
            }
        }
        return this;
    }

    protected void hookResourceListeners() {
        ResourcesPlugin.getWorkspace().addResourceChangeListener(projectFileListener);
    }

    protected void removeResourceListeners() {
        ResourcesPlugin.getWorkspace().removeResourceChangeListener(projectFileListener);
    }

    protected CreateBonitaProjectOperation newProjectWorkspaceOperation(final ProjectMetadata metadata,
            final IWorkspace workspace) {
        return new CreateBonitaProjectOperation(workspace, metadata)
                .addNature(BonitaProjectNature.NATURE_ID)
                .addNature(JavaCore.NATURE_ID)
                .addNature("org.eclipse.jdt.groovy.core.groovyNature")
                .addNature(IMavenConstants.NATURE_ID)
                .addBuilder(IMavenConstants.BUILDER_ID)
                .addBuilder("org.eclipse.jdt.core.javabuilder");
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepository#getName()
     */
    @Override
    public String getName() {
        return project.getName();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepository#isShared()
     */
    @Override
    public boolean isShared() {
        return false;
    }

    @Override
    public IProject getProject() {
        return project;
    }

    @Override
    public AbstractRepository open(final IProgressMonitor monitor) {
        SubMonitor subMonitor = SubMonitor.convert(monitor)
                .newChild(IProgressMonitor.UNKNOWN, SubMonitor.SUPPRESS_SUBTASK);
        try {
            if (!project.isOpen()) {
                BonitaStudioLog.log("Opening project: " + project.getName());
                project.open(NULL_PROGRESS_MONITOR);
            }
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
        try {
            connect(project);
            initRepositoryStores(NULL_PROGRESS_MONITOR);
            MavenPlugin.getProjectConfigurationManager()
                    .updateProjectConfiguration(project, subMonitor);
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }

        this.projectDependenciesStore = new MavenProjectDependenciesStore(project, eventBroker);
        this.projectDependenciesStore
                .analyze(subMonitor);

        enableBuild();
        for (IBonitaProjectListener listener : getProjectListeners()) {
            listener.projectOpened(this, monitor);
        }

        if (migrationEnabled()) {
            try {
                RepositoryManager.getInstance().setCurrentRepository(this);
                migrate(subMonitor);
            } catch (final MigrationException | CoreException e) {
                BonitaStudioLog.error(e, CommonRepositoryPlugin.PLUGIN_ID);
            }
        }
        hookResourceListeners();
        updateCurrentRepositoryPreference();

        return this;
    }

    protected void connect(IProject project) throws CoreException {
        //May be implemented by sublcasses
    }

    protected void updateCurrentRepositoryPreference() {
        CommonRepositoryPlugin.getDefault().getPreferenceStore().setValue(
                RepositoryPreferenceConstant.CURRENT_REPOSITORY,
                getName());
        try {
            ((ScopedPreferenceStore) CommonRepositoryPlugin.getDefault().getPreferenceStore()).save();
        } catch (final IOException e) {
            BonitaStudioLog.error(e);
        }
    }

    @Override
    public void close() {
        try {
            BonitaStudioLog.debug("Closing repository " + project.getName(), CommonRepositoryPlugin.PLUGIN_ID);
            closeAllEditors();
            if (project.isOpen()) {
                if (stores != null) {
                    for (final IRepositoryStore<? extends IRepositoryFileStore> store : stores.values()) {
                        store.close();
                    }
                }
                project.close(NULL_PROGRESS_MONITOR);
            }
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
        if (stores != null) {
            stores.clear();
            stores = null;
        }
        isLoaded = false;
        removeResourceListeners();
        for (IBonitaProjectListener listener : getProjectListeners()) {
            listener.projectClosed(this, NULL_PROGRESS_MONITOR);
        }
    }

    private void enableOpenIntroListener() {
        this.enableOpenIntroListener = true;
    }

    public boolean isOpenIntroListenerEnabled() {
        return this.enableOpenIntroListener;
    }

    private void disableOpenIntroListener() {
        this.enableOpenIntroListener = false;
    }

    @Override
    public boolean closeAllEditors() {
        disableOpenIntroListener();
        final AtomicBoolean allEditorClosed = new AtomicBoolean(true);
        if (PlatformUI.isWorkbenchRunning()) {
            Display.getDefault().syncExec(() -> {

                final IWorkbenchWindow activeWorkbenchWindow = PlatformUI
                        .getWorkbench().getActiveWorkbenchWindow();
                if (activeWorkbenchWindow != null
                        && activeWorkbenchWindow.getActivePage() != null) {
                    if (!activeWorkbenchWindow.getActivePage().closeAllEditors(false)) {
                        allEditorClosed.set(false);;
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

    protected synchronized void initRepositoryStores(final IProgressMonitor monitor) {
        isLoaded = false;
        if (stores == null || stores.isEmpty()) {
            disableBuild();
            stores = new TreeMap<>(new Comparator<Class<?>>() {

                @Override
                public int compare(final Class<?> o1, final Class<?> o2) {
                    return o1.getName().compareTo(o2.getName());
                }

            });
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
        isLoaded = true;
    }

    private boolean migrationEnabled() {
        return migrationEnabled;
    }

    @SuppressWarnings("unchecked")
    protected IRepositoryStore<? extends IRepositoryFileStore> createRepositoryStore(
            final IConfigurationElement configuration, final IProgressMonitor monitor) throws CoreException {
        final IRepositoryStore<? extends IRepositoryFileStore> store;
        try {
            store = extensionContextInjectionFactory.make(configuration, CLASS, IRepositoryStore.class);
            monitor.subTask(Messages.bind(Messages.creatingStore, store.getDisplayName()));
            store.createRepositoryStore(this);
            monitor.worked(1);
            return store;
        } catch (final ClassNotFoundException e) {
            throw new CoreException(new Status(IStatus.ERROR, CommonRepositoryPlugin.PLUGIN_ID,
                    "Failed to instantiate Repository store", e));
        }
    }

    @Override
    public void enableBuild() {
        Job.getJobManager().wakeUp(ResourcesPlugin.FAMILY_AUTO_BUILD);
        final IWorkspaceDescription desc = workspace.getDescription();
        if (desc != null && !desc.isAutoBuilding()) {
            final boolean enableAutobuild = PlatformUI.isWorkbenchRunning();
            desc.setAutoBuilding(enableAutobuild);
            try {
                workspace.setDescription(desc);
            } catch (final CoreException e) {
                BonitaStudioLog.error(e, CommonRepositoryPlugin.PLUGIN_ID);
            }
            RepositoryManager.getInstance().getPreferenceStore().setValue(RepositoryPreferenceConstant.BUILD_ENABLE,
                    enableAutobuild);
            try {
                project.build(IncrementalProjectBuilder.AUTO_BUILD, NULL_PROGRESS_MONITOR);
            } catch (CoreException e) {
                BonitaStudioLog.error(e, CommonRepositoryPlugin.PLUGIN_ID);
            }
        }
    }

    @Override
    public void disableBuild() {
        final IWorkspaceDescription desc = workspace.getDescription();
        if (desc != null && desc.isAutoBuilding()) {
            desc.setAutoBuilding(false);
            try {
                workspace.setDescription(desc);
            } catch (final CoreException e) {
                BonitaStudioLog.error(e, CommonRepositoryPlugin.PLUGIN_ID);
            }
            RepositoryManager.getInstance().getPreferenceStore().setValue(RepositoryPreferenceConstant.BUILD_ENABLE,
                    false);
        }
    }

    @Override
    public void build(IProgressMonitor monitor) {
        if (isBuildEnable()) {
            try {
                if (monitor == null) {
                    monitor = NULL_PROGRESS_MONITOR;
                }
                if (!getProject().isSynchronized(IResource.DEPTH_ONE)) {
                    getProject().refreshLocal(IResource.DEPTH_ONE, monitor);
                }
                project.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, monitor);
            } catch (final Exception ex) {
                BonitaStudioLog.error(ex);
            }
        }
    }

    @Override
    public boolean isBuildEnable() {
        return RepositoryManager.getInstance().getPreferenceStore()
                .getBoolean(RepositoryPreferenceConstant.BUILD_ENABLE);
    }

    @Override
    public IJavaProject getJavaProject() {
        if (getProject() != null && getProject().isAccessible()) {
            return JavaCore.create(project);
        }
        return null;
    }

    @Override
    public void delete(final IProgressMonitor monitor) {
        BonitaStudioLog.debug("Deleting repository " + project.getName(), CommonRepositoryPlugin.PLUGIN_ID);
        try {
            if (project.isOpen()) {
                build(NULL_PROGRESS_MONITOR);
            }
            project.delete(true, true, NULL_PROGRESS_MONITOR);
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
    }

    @Override
    public <T> T getRepositoryStore(final Class<T> repositoryStoreClass) {
        if (!isLoaded()) {
            initRepositoryStores(NULL_PROGRESS_MONITOR);
            enableBuild();
        }
        return repositoryStoreClass.cast(stores.get(repositoryStoreClass));
    }

    @Override
    public String getVersion() {
        if (project.isOpen()) {
            try {
                return project.getDescription().getComment();
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        } else if(project.getLocation() != null) {
            final File projectFile = new File(project.getLocation().toFile(), ".project");
            if (projectFile.exists()) {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(projectFile);
                    final InputSource source = new InputSource(fis);
                    final ProjectDescriptionReader reader = new ProjectDescriptionReader();
                    final IProjectDescription desc = reader.read(source);
                    return desc.getComment();
                } catch (final FileNotFoundException e) {
                    BonitaStudioLog.error(e);
                } finally {
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (final IOException e) {
                            BonitaStudioLog.error(e);
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public synchronized List<IRepositoryStore<? extends IRepositoryFileStore>> getAllStores() {
        if (stores == null) {
            initRepositoryStores(NULL_PROGRESS_MONITOR);
            enableBuild();
        }
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
    public String getDisplayName() {
        return getName() + " [" + getVersion() + "]";
    }

    @Override
    public Image getIcon() {
        if (isShared(SVNTeamPlugin.NATURE_ID)) {
            return Pics.getImage("svnIcon.png", CommonRepositoryPlugin.getDefault());
        } else if (isShared()) {
            return Pics.getImage("git.png", CommonRepositoryPlugin.getDefault());
        } else {
            return Pics.getImage("local-repository.png", CommonRepositoryPlugin.getDefault());
        }
    }

    @Override
    public IStatus exportToArchive(final String fileName) {
        final ExportBosArchiveOperation operation = new ExportBosArchiveOperation();
        operation.setDestinationPath(fileName);
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
                    || (resource instanceof IProject && ((IProject) resource).isOpen() && ((IProject) resource).hasNature(BonitaProjectNature.NATURE_ID))
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
        if (!project.isAccessible() || project.getLocation() == null) {
            return null;
        }
        final IPath resourcePath = fromOSString(path.toString()).makeRelativeTo(project.getLocation());
        if (resourcePath.isRoot() || resourcePath.isEmpty()
                || Objects.equals(org.eclipse.core.runtime.Path.fromOSString(".."), resourcePath)) {
            return null;
        }
        IResource iResource = null;
        try {
            iResource = isFile(resourcePath) ? project.getFile(resourcePath) : project.getFolder(resourcePath);
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
    public void migrate(final IProgressMonitor monitor) throws CoreException, MigrationException {
        Assert.isNotNull(project);
        monitor.beginTask(String.format(Messages.migratingRepository, getName()), IProgressMonitor.UNKNOWN);

        IFile groovyPrefs = getProject().getFile(".settings/org.eclipse.jdt.groovy.core.prefs");
        if (groovyPrefs.exists()) {
            groovyPrefs.delete(true, monitor);
        }
        IFile jdtPrefs = getProject().getFile(".settings/org.eclipse.jdt.core.prefs");
        if (jdtPrefs.exists()) {
            jdtPrefs.delete(true, monitor);
        }
        if (!project.getFile(IMavenConstants.POM_FILE_NAME).exists()) {
            var metadata = ProjectMetadata.defaultMetadata();
            metadata.setName(project.getName());
            metadata.setArtifactId(ProjectMetadata.toArtifactId(project.getName()));
            MavenProjectModelBuilder mavenProjectBuilder = CreateBonitaProjectOperation.newProjectBuilder(metadata);
            CreateBonitaProjectOperation.createDefaultPomFile(project, mavenProjectBuilder);
        }

        for (final IRepositoryStore<?> store : getAllStores()) {
            store.createRepositoryStore(this);
            store.migrate(monitor);
        }
        //Remove legacy repositories
        Version oldVersion = null;
        try {
            oldVersion = Version.parseVersion(getVersion());
        } catch (IllegalArgumentException e) {
            oldVersion = new Version("6.0.0");
        }
        removeLegacyFolders(oldVersion, monitor);
        workspace.run(newProjectMigrationOperation(project), monitor);
    }

    protected void removeLegacyFolders(Version oldVersion, final IProgressMonitor monitor) {
        if (oldVersion.compareTo(new Version("7.8.0")) < 0) {
            LEGACY_REPOSITORIES.stream().forEach(folderName -> removeFolder(folderName, monitor));
        }
    }

    protected void removeFolder(String folderName, final IProgressMonitor monitor) {
        IFolder resourceFolder = project.getFolder(folderName);
        if (resourceFolder.exists()) {
            try {
                resourceFolder.delete(true, monitor);
            } catch (CoreException e) {
                BonitaStudioLog.error(String.format("Failed to delete folder %s during migration", folderName),
                        CommonRepositoryPlugin.PLUGIN_ID);
            }
            BonitaStudioLog.info(String.format("Folder %s has been removed during migration", folderName),
                    CommonRepositoryPlugin.PLUGIN_ID);
        }
    }

    protected BonitaProjectMigrationOperation newProjectMigrationOperation(final IProject project) {
        return new BonitaProjectMigrationOperation(project)
                .addNature(BonitaProjectNature.NATURE_ID)
                .addNature(JavaCore.NATURE_ID)
                .addNature("org.eclipse.jdt.groovy.core.groovyNature")
                .addNature(IMavenConstants.NATURE_ID)
                .addBuilder(IMavenConstants.BUILDER_ID)
                .addBuilder("org.eclipse.jdt.core.javabuilder");
    }

    protected IRunnableWithProgress migrationRunnable() {
        return new IRunnableWithProgress() {

            @Override
            public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                try {
                    migrate(monitor);
                } catch (CoreException | MigrationException e) {
                    throw new InvocationTargetException(e);
                }
            }
        };
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

    public IScopeContext getScopeContext() {
        return new ProjectScope(project);
    }

    @Override
    public DatabaseHandler getDatabaseHandler() {
        return new DatabaseHandler(getProject());
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepository#isShared(java.lang.String)
     */
    @Override
    public boolean isShared(String providerId) {
        return false;
    }

    public void runMigrationInDialog() {
        try {
            new ProgressMonitorDialog(
                    Display.getDefault().getActiveShell())
                            .run(true, false, migrationRunnable());
        } catch (InvocationTargetException | InterruptedException e) {
            CommonRepositoryPlugin.getDefault().openErrorDialog(
                    Display.getDefault().getActiveShell(),
                    Messages.migrationFailedMessage, e);
        }
    }

    public void validateRepositoryVersion() {
        projectFileListener.checkVersion(getProject());
    }

    @Override
    public void rename(String newName, IProgressMonitor monitor)
            throws InvocationTargetException, InterruptedException {
        if (closeAllEditors()) {
            WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {

                @Override
                protected void execute(IProgressMonitor monitor)
                        throws CoreException, InvocationTargetException, InterruptedException {
                    getProjectListeners().stream().forEach(l -> l.projectClosed(AbstractRepository.this, monitor));
                    disableBuild();
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
    public List<ProcessModelTransformation> getProcessModelTransformations() {
        return PROCESS_MODEL_TRANSFORMATIONS;
    }

    private List<IBonitaProjectListener> getProjectListeners() {
        return Collections.unmodifiableList(projectListeners);
    }

    @Override
    public LocalDependenciesStore getLocalDependencyStore() {
        return new LocalDependenciesStore(getProject());
    }

}
