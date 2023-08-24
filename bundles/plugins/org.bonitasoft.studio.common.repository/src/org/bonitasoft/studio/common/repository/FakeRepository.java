package org.bonitasoft.studio.common.repository;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.extension.ExtensionContextInjectionFactory;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.DatabaseHandler;
import org.bonitasoft.studio.common.repository.core.ProjectDependenciesStore;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.bonitasoft.studio.common.repository.filestore.FileStoreChangeEvent;
import org.bonitasoft.studio.common.repository.jdt.JDTTypeHierarchyManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.store.LocalDependenciesStore;
import org.bonitasoft.studio.common.repository.store.RepositoryStoreComparator;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.jdt.core.IJavaProject;

public class FakeRepository implements IRepository {

    public static final IRepository INSTANCE = new FakeRepository();

    private static final String REPOSITORY_STORE_EXTENSION_POINT_ID = "org.bonitasoft.studio.repositoryStore";

    private SortedMap<Class<?>, IRepositoryStore<? extends IRepositoryFileStore>> stores;

    private ExtensionContextInjectionFactory extensionContextInjectionFactory = new ExtensionContextInjectionFactory();

    @Override
    public void handleFileStoreEvent(FileStoreChangeEvent event) {
    }

    @Override
    public boolean exists() {
        return false;
    }

    @Override
    public boolean isLoaded() {
        return false;
    }

    @Override
    public boolean isShared() {
        return false;
    }

    @Override
    public boolean isShared(String providerId) {
        return false;
    }

    @Override
    public IProject getProject() {
        return null;
    }

    @Override
    public void delete(IProgressMonitor monitor) {

    }

    @Override
    public AbstractRepository open(IProgressMonitor monitor) {
        return null;
    }

    @Override
    public void close(IProgressMonitor monitor) {

    }

    @Override
    public <T> T getRepositoryStore(Class<T> repositoryStoreClass) {
        return null;
    }

    @Override
    public Optional<IRepositoryStore<? extends IRepositoryFileStore>> getRepositoryStoreByName(String storeName) {
        return getAllStores().stream().filter(store -> Objects.equals(store.getName(), storeName)).findFirst();
    }

    @Override
    public List<IRepositoryStore<? extends IRepositoryFileStore>> getAllStores() {
        if (stores == null) {
            initRepositoryStores();
        }
        return stores.values().stream()
                .distinct()
                .sorted(new RepositoryStoreComparator())
                .collect(Collectors.toList());
    }

    private void initRepositoryStores() {
        stores = new TreeMap<>((o1, o2) -> o1.getName().compareTo(o2.getName()));
        final IConfigurationElement[] repositoryStoreConfigurationElements = BonitaStudioExtensionRegistryManager
                .getInstance().getConfigurationElements(
                        REPOSITORY_STORE_EXTENSION_POINT_ID);
        for (final IConfigurationElement configuration : repositoryStoreConfigurationElements) {
            try {
                final IRepositoryStore<? extends IRepositoryFileStore> store = createRepositoryStore(configuration);
                stores.put(store.getClass(), store);
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private IRepositoryStore<? extends IRepositoryFileStore> createRepositoryStore(
            final IConfigurationElement configuration) throws CoreException {
        try {
            return extensionContextInjectionFactory.make(configuration, "class", IRepositoryStore.class);
        } catch (final ClassNotFoundException e) {
            throw new CoreException(new Status(IStatus.ERROR, CommonRepositoryPlugin.PLUGIN_ID,
                    "Failed to instantiate Repository store", e));
        }
    }

    @Override
    public List<IRepositoryStore<? extends IRepositoryFileStore>> getAllSharedStores() {
        return null;
    }

    @Override
    public List<IRepositoryStore<? extends IRepositoryFileStore>> getAllExportableStores() {
        return null;
    }

    @Override
    public IStatus exportToArchive(String file) {
        return null;
    }

    @Override
    public IRepositoryFileStore getFileStore(IResource resource) {
        return null;
    }

    @Override
    public ProjectDependenciesStore getProjectDependenciesStore() {
        return null;
    }

    @Override
    public IRepositoryStore<? extends IRepositoryFileStore> getRepositoryStore(IResource resource) {
        return null;
    }

    @Override
    public List<IRepositoryStore<? extends IRepositoryFileStore>> getAllShareableStores() {
        return null;
    }

    @Override
    public IRepositoryFileStore asRepositoryFileStore(Path path, boolean force) throws IOException, CoreException {
        return null;
    }

    @Override
    public void migrate(MigrationReport report, IProgressMonitor monitor) throws CoreException, MigrationException {

    }

    @Override
    public IRepository create(ProjectMetadata metadata, IProgressMonitor monitor) {
        return null;
    }

    @Override
    public boolean isOnline() {
        return false;
    }

    @Override
    public DatabaseHandler getDatabaseHandler() {
        return null;
    }

    @Override
    public void addProjectListener(IBonitaProjectListener listener) {

    }

    @Override
    public LocalDependenciesStore getLocalDependencyStore() {
        return null;
    }

    @Override
    public void rename(String name, IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {

    }

    @Override
    public boolean closeAllEditors(boolean save) {
        return false;
    }

    @Override
    public String getBonitaRuntimeVersion() {
        return null;
    }

    @Override
    public List<IBonitaProjectListener> getProjectListeners() {
        return null;
    }

    @Override
    public void build(IProgressMonitor monitor) {
    }

    @Override
    public IJavaProject getJavaProject() {
        return null;
    }

    @Override
    public JDTTypeHierarchyManager getJdtTypeHierarchyManager() {
        return null;
    }

    @Override
    public void disableOpenIntroListener() {
    }

    @Override
    public boolean isOpenIntroListenerEnabled() {
        return false;
    }

    @Override
    public String getProjectId() {
        return null;
    }

}
