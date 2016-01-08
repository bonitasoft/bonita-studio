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

import static com.google.common.collect.Iterables.tryFind;
import static org.eclipse.core.runtime.Path.fromOSString;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.xbean.classloader.NonLockingJarFileClassLoader;
import org.bonitasoft.engine.bpm.bar.BusinessArchive;
import org.bonitasoft.studio.common.DateUtil;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.extension.ExtensionContextInjectionFactory;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.BonitaBPMProjectMigrationOperation;
import org.bonitasoft.studio.common.repository.core.BonitaHomeHandler;
import org.bonitasoft.studio.common.repository.core.CreateBonitaBPMProjectOperation;
import org.bonitasoft.studio.common.repository.core.ProjectClasspathFactory;
import org.bonitasoft.studio.common.repository.core.ProjectManifestFactory;
import org.bonitasoft.studio.common.repository.filestore.FileStoreChangeEvent;
import org.bonitasoft.studio.common.repository.jdt.JDTTypeHierarchyManager;
import org.bonitasoft.studio.common.repository.model.IJavaContainer;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.operation.ExportBosArchiveOperation;
import org.bonitasoft.studio.common.repository.preferences.RepositoryPreferenceConstant;
import org.bonitasoft.studio.common.repository.store.RepositoryStoreComparator;
import org.bonitasoft.studio.pics.Pics;
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
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.internal.core.ClasspathValidation;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.xml.sax.InputSource;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;

/**
 * @author Romain Bioteau
 */
public class Repository implements IRepository, IJavaContainer {

    private static final String REPOSITORY_STORE_EXTENSION_POINT_ID = "org.bonitasoft.studio.repositoryStore";

    public static final IProgressMonitor NULL_PROGRESS_MONITOR = new NullProgressMonitor();

    private static final String CLASS = "class";

    private final IProject project;

    private SortedMap<Class<?>, IRepositoryStore<? extends IRepositoryFileStore>> stores;

    private final JDTTypeHierarchyManager jdtTypeHierarchyManager;

    private boolean migrationEnabled = false;

    private final ExtensionContextInjectionFactory extensionContextInjectionFactory;

    private final ProjectManifestFactory projectManifestFactory;

    private final IWorkspace workspace;

    private final ProjectClasspathFactory bonitaBPMProjectClasspath;

    private boolean isLoaded = false;

    public Repository(final IWorkspace workspace,
            final IProject project,
            final ExtensionContextInjectionFactory extensionContextInjectionFactory,
            final JDTTypeHierarchyManager jdtTypeHierarchyManager,
            final ProjectManifestFactory projectManifestFactory,
            final ProjectClasspathFactory bonitaBPMProjectClasspath,
            final boolean migrationEnabled) {
        this.workspace = workspace;
        this.project = project;
        this.jdtTypeHierarchyManager = jdtTypeHierarchyManager;
        this.migrationEnabled = migrationEnabled;
        this.extensionContextInjectionFactory = extensionContextInjectionFactory;
        this.projectManifestFactory = projectManifestFactory;
        this.bonitaBPMProjectClasspath = bonitaBPMProjectClasspath;
    }

    @Override
    public Repository create(final IProgressMonitor monitor) {
        final long init = System.currentTimeMillis();
        if (BonitaStudioLog.isLoggable(IStatus.OK)) {
            BonitaStudioLog.debug("Creating repository " + project.getName() + "...", CommonRepositoryPlugin.PLUGIN_ID);
        }
        try {
            disableBuild();
            if (!project.exists()) {
                workspace.run(newProjectWorkspaceOperation(project.getName(), workspace), monitor);
            }
            open(monitor);
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        } finally {
            enableBuild();
            try {
                getProject().build(IncrementalProjectBuilder.FULL_BUILD, monitor);
            } catch (final CoreException e) {
                BonitaStudioLog.error(e, CommonRepositoryPlugin.PLUGIN_ID);
            }
            if (BonitaStudioLog.isLoggable(IStatus.OK)) {
                final long duration = System.currentTimeMillis() - init;
                BonitaStudioLog.debug("Repository " + project.getName() + " created in " + DateUtil.getDisplayDuration(duration),
                        CommonRepositoryPlugin.PLUGIN_ID);
            }
        }
        return this;
    }

    protected CreateBonitaBPMProjectOperation newProjectWorkspaceOperation(final String projectName, final IWorkspace workspace) {
        return new CreateBonitaBPMProjectOperation(workspace, projectName).addNature("org.eclipse.xtext.ui.shared.xtextNature")
                .addNature("org.bonitasoft.studio.common.repository.bonitaNature").addNature(JavaCore.NATURE_ID).addNature("org.eclipse.pde.PluginNature")
                .addNature("org.eclipse.jdt.groovy.core.groovyNature").addBuilder("org.eclipse.jdt.core.javabuilder")
                .addBuilder("org.eclipse.xtext.ui.shared.xtextBuilder").addBuilder("org.eclipse.pde.ManifestBuilder")
                .addBuilder("org.eclipse.pde.SchemaBuilder");
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

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepository#getProject()
     */
    @Override
    public IProject getProject() {
        return project;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepository#open()
     */
    @Override
    public Repository open(final IProgressMonitor monitor) {
        try {
            if (!project.isOpen()) {
                BonitaStudioLog.log("Opening project: " + project.getName());
                project.open(NULL_PROGRESS_MONITOR);
                final JavaProject jProject = (JavaProject) project.getNature(JavaCore.NATURE_ID);
                if (jProject != null) {
                    if (!jProject.isOpen()) {
                        jProject.open(NULL_PROGRESS_MONITOR);
                    }
                    new ClasspathValidation(jProject).validate();
                } else {
                    BonitaStudioLog.log("Cannot retrieve the JavaProject Nature from the project: " + project.getName());
                    project.open(NULL_PROGRESS_MONITOR);//Open anyway
                }
            }
            updateStudioShellText();
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
        try {
            projectManifestFactory.createProjectManifest(project, monitor);
            initBonitaHome(monitor);
            initRepositoryStores(monitor);
            enableBuild();
            bonitaBPMProjectClasspath.create(this, monitor);
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
        return this;
    }

    protected void initBonitaHome(final IProgressMonitor monitor) throws CoreException {
        final BonitaHomeHandler bonitaHomeHandler = getBonitaHomeHandler();
        bonitaHomeHandler.initBonitaHome(monitor);
    }

    protected void updateStudioShellText() {
        Display.getDefault().asyncExec(new Runnable() {

            @Override
            public void run() {
                if (PlatformUI.isWorkbenchRunning() && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null) {
                    final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
                    final String currentName = shell.getText();
                    final int index = currentName.indexOf(" - ");
                    String newName = index > 0 ? currentName.substring(0, index)
                            : currentName;
                    if (!getName()
                            .equals(RepositoryPreferenceConstant.DEFAULT_REPOSITORY_NAME)) {
                        newName = newName + " - " + getName();
                    }
                    shell.setText(newName);
                }
            }
        });
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepository#close()
     */
    @Override
    public void close() {
        try {
            BonitaStudioLog.debug("Closing repository " + project.getName(), CommonRepositoryPlugin.PLUGIN_ID);
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
    }

    protected synchronized void initRepositoryStores(final IProgressMonitor monitor) {
        isLoaded = false;
        if (stores == null || stores.isEmpty()) {
            disableBuild();
            stores = new TreeMap<Class<?>, IRepositoryStore<? extends IRepositoryFileStore>>(new Comparator<Class<?>>() {

                @Override
                public int compare(final Class<?> o1, final Class<?> o2) {
                    return o1.getName().compareTo(o2.getName());
                }

            });
            final IConfigurationElement[] repositoryStoreConfigurationElements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(
                    REPOSITORY_STORE_EXTENSION_POINT_ID);
            for (final IConfigurationElement configuration : repositoryStoreConfigurationElements) {
                try {
                    final IRepositoryStore<? extends IRepositoryFileStore> store = createRepositoryStore(configuration, monitor);
                    if (migrationEnabled()) {
                        try {
                            store.migrate(monitor);
                        } catch (final MigrationException e) {
                            BonitaStudioLog.error(e, CommonRepositoryPlugin.PLUGIN_ID);
                        }
                    }
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
            throw new CoreException(new Status(IStatus.ERROR, CommonRepositoryPlugin.PLUGIN_ID, "Failed to instantiate Repository store", e));
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
            RepositoryManager.getInstance().getPreferenceStore().setValue(RepositoryPreferenceConstant.BUILD_ENABLE, enableAutobuild);
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
            RepositoryManager.getInstance().getPreferenceStore().setValue(RepositoryPreferenceConstant.BUILD_ENABLE, false);
        }
    }

    @Override
    public void build(IProgressMonitor monitor) {
        if (isBuildEnable()) {
            try {
                if (monitor == null) {
                    monitor = NULL_PROGRESS_MONITOR;
                }
                if (!getProject().isSynchronized(IResource.DEPTH_INFINITE)) {
                    getProject().refreshLocal(IResource.DEPTH_INFINITE, monitor);
                }
                new ProjectClasspathFactory().refresh(this, monitor);
                project.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, monitor);
            } catch (final Exception ex) {
                BonitaStudioLog.error(ex);
            }
        }
    }

    @Override
    public boolean isBuildEnable() {
        return RepositoryManager.getInstance().getPreferenceStore().getBoolean(RepositoryPreferenceConstant.BUILD_ENABLE);
    }

    @Override
    public IJavaProject getJavaProject() {
        if (getProject() != null && getProject().isAccessible()) {
            try {
                final IJavaProject project = (IJavaProject) getProject().getNature(JavaCore.NATURE_ID);
                return project;
            } catch (final CoreException ex) {
                BonitaStudioLog.error(ex);
                return null;
            }
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
            if (CommonRepositoryPlugin.getCurrentRepository().equals(getName())) {
                RepositoryManager.getInstance().setRepository(RepositoryPreferenceConstant.DEFAULT_REPOSITORY_NAME, monitor);
            }
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
        } else {
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
        final List<IRepositoryStore<? extends IRepositoryFileStore>> result = new ArrayList<IRepositoryStore<? extends IRepositoryFileStore>>(stores.values());
        Collections.sort(result, new RepositoryStoreComparator());
        return result;
    }

    @Override
    public List<IRepositoryStore<? extends IRepositoryFileStore>> getAllSharedStores() {
        final List<IRepositoryStore<? extends IRepositoryFileStore>> result = new ArrayList<IRepositoryStore<? extends IRepositoryFileStore>>();
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
        if (isShared()) {
            return Pics.getImage("shared-repository.png", CommonRepositoryPlugin.getDefault());
        } else {
            return Pics.getImage("local-repository.png", CommonRepositoryPlugin.getDefault());
        }
    }

    @Override
    public void exportToArchive(final String fileName) {
        final ExportBosArchiveOperation operation = new ExportBosArchiveOperation();
        operation.setDestinationPath(fileName);
        final Set<IResource> allResources = new HashSet<IResource>();
        for (final IRepositoryStore<?> store : getAllExportableStores()) {
            allResources.add(store.getResource());
            allResources.addAll(allRelatedResources(store));
        }
        operation.setResources(allResources);
        final IStatus status = operation.run(NULL_PROGRESS_MONITOR);
        if (!status.isOK()) {
            logErrorStatus(status);
        } else {
            BonitaStudioLog.info(String.format("%s archive exported successfully.", fileName), CommonRepositoryPlugin.PLUGIN_ID);
        }
    }

    private List<IResource> allRelatedResources(final IRepositoryStore<?> store) {
        final List<IResource> relatedResources = new ArrayList<IResource>();
        for (final IRepositoryFileStore fs : store.getChildren()) {
            for (final IRepositoryFileStore related : fs.getRelatedFileStore()) {
                relatedResources.add(related.getResource());
            }
        }
        return relatedResources;
    }

    protected void logErrorStatus(final IStatus status) {
        final StringBuilder sb = new StringBuilder();
        if (status.isMultiStatus()) {
            for (final IStatus childStatus : status.getChildren()) {
                sb.append(childStatus.getMessage()).append("\n");
            }

        }
        BonitaStudioLog.error("Export to archive failed.\n" + status.getMessage() + "\n" + sb.toString(), CommonRepositoryPlugin.PLUGIN_ID);
    }

    @Override
    public IRepositoryFileStore getFileStore(final IResource resource) {
        for (final IRepositoryStore<? extends IRepositoryFileStore> store : getAllStores()) {
            final IRepositoryFileStore file = store.getChild(resource.getName());
            if (file != null) {
                return file;
            }
        }
        return null;
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
        final List<IRepositoryStore<? extends IRepositoryFileStore>> result = new ArrayList<IRepositoryStore<? extends IRepositoryFileStore>>();
        for (final IRepositoryStore<? extends IRepositoryFileStore> store : getAllStores()) {
            if (store.canBeShared()) {
                result.add(store);
            }
        }
        Collections.sort(result, new RepositoryStoreComparator());
        return result;

    }

    @Override
    public ClassLoader createProjectClassloader(final IProgressMonitor monitor) {
        final List<URL> jars = new ArrayList<URL>();
        try {
            final ProjectClasspathFactory bonitaBPMProjectClasspath = new ProjectClasspathFactory();
            if (!bonitaBPMProjectClasspath.classpathExists(this)) {
                bonitaBPMProjectClasspath.create(this, monitor);
            }

            // Synchronize with build jobs
            Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD, NULL_PROGRESS_MONITOR);
            Job.getJobManager().join(ResourcesPlugin.FAMILY_MANUAL_BUILD, NULL_PROGRESS_MONITOR);

            final IProject project = getProject();
            final String workspacePath = project.getLocation().toFile().getParent();
            final String outputPath = workspacePath + getJavaProject().getOutputLocation().toString();
            jars.add(new File(outputPath).toURI().toURL());
            for (final IClasspathEntry entry : getJavaProject().getRawClasspath()) {
                if (entry.getEntryKind() == IClasspathEntry.CPE_LIBRARY) {
                    File jar = entry.getPath().toFile();
                    if (!jar.exists()) { // jar location relative to project
                        jar = new File(workspacePath + File.separator + jar);
                    }
                    jars.add(jar.toURI().toURL());
                }
            }
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }

        return new NonLockingJarFileClassLoader(getName() + "_URLClassLoader", jars.toArray(new URL[jars.size()]), BusinessArchive.class.getClassLoader());
    }

    @Override
    public List<IRepositoryStore<? extends IRepositoryFileStore>> getAllExportableStores() {
        final List<IRepositoryStore<? extends IRepositoryFileStore>> result = new ArrayList<IRepositoryStore<? extends IRepositoryFileStore>>();
        for (final IRepositoryStore<? extends IRepositoryFileStore> store : getAllStores()) {
            if (store.canBeExported()) {
                result.add(store);
            }
        }
        Collections.sort(result, new RepositoryStoreComparator());
        return result;
    }

    @Override
    public IRepositoryFileStore asRepositoryFileStore(final Path path) throws IOException, CoreException {
        final IPath resourcePath = fromOSString(path.toString()).makeRelativeTo(project.getLocation());
        final IResource iResource = isFile(resourcePath) ? project.getFile(resourcePath) : project.getFolder(resourcePath);
        if (!iResource.exists()) {
            iResource.getParent().refreshLocal(IResource.DEPTH_INFINITE, NULL_PROGRESS_MONITOR);
            if (!iResource.exists()) {
                throw new FileNotFoundException(path.toFile().getAbsolutePath());
            }
        }

        final IPath projectRelativePath = iResource.getProjectRelativePath();
        if (projectRelativePath.segmentCount() > 0) {
            final IPath iPath = projectRelativePath.removeFirstSegments(0);
            if (iPath.segmentCount() > 1) {
                final String storeName = iPath.segments()[0];
                final IFile file = getProject().getFile(iPath);
                final IRepositoryStore<? extends IRepositoryFileStore> repositoryStore = getRepositoryStoreByName(storeName);
                if (belongToRepositoryStore(repositoryStore, file)) {
                    return repositoryStore.createRepositoryFileStore(file.getName());
                }
            }
        }
        return null;
    }

    private boolean isFile(final IPath resourcePath) {
        return resourcePath.getFileExtension() != null;
    }

    @Override
    public IRepositoryStore<? extends IRepositoryFileStore> getRepositoryStoreByName(final String storeName) throws CoreException {
        final Optional<IRepositoryStore<? extends IRepositoryFileStore>> foundStore = tryFind(getAllStores(), storeWithName(storeName));
        if (!foundStore.isPresent()) {
            throw new CoreException(new Status(IStatus.ERROR, CommonRepositoryPlugin.PLUGIN_ID, String.format("No repository store found with name: %s",
                    storeName)));
        }
        return foundStore.get();
    }

    private Predicate<? super IRepositoryStore<? extends IRepositoryFileStore>> storeWithName(final String storeName) {
        return new Predicate<IRepositoryStore<? extends IRepositoryFileStore>>() {

            @Override
            public boolean apply(final IRepositoryStore<? extends IRepositoryFileStore> store) {
                return store.getName().equals(storeName);
            }
        };
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
        for (final IRepositoryStore<?> store : getAllStores()) {
            store.migrate(monitor);
        }
        workspace.run(newProjectMigrationOperation(project), monitor);
    }

    protected BonitaBPMProjectMigrationOperation newProjectMigrationOperation(final IProject project) {
        return new BonitaBPMProjectMigrationOperation(project, this).addNature("org.eclipse.xtext.ui.shared.xtextNature")
                .addNature("org.bonitasoft.studio.common.repository.bonitaNature").addNature(JavaCore.NATURE_ID).addNature("org.eclipse.pde.PluginNature")
                .addNature("org.eclipse.jdt.groovy.core.groovyNature").addBuilder("org.eclipse.jdt.core.javabuilder")
                .addBuilder("org.eclipse.xtext.ui.shared.xtextBuilder").addBuilder("org.eclipse.pde.ManifestBuilder")
                .addBuilder("org.eclipse.pde.SchemaBuilder");
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
    public BonitaHomeHandler getBonitaHomeHandler() {
        return new BonitaHomeHandler(getProject());
    }

}
