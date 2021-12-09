package org.bonitasoft.studio.designer.core;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.designer.core.repository.WebFragmentRepositoryStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.designer.core.repository.WebWidgetRepositoryStore;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

import com.sun.nio.file.SensitivityWatchEventModifier;

public class UIDWorkspaceSynchronizer {

    private static boolean enabled;
    private IRepository repository;
    private WatchService watcher;
    private ExecutorService executor;
    private Map<WatchKey, Path> keys = new HashMap<>();
    private Future<?> pollingRunnable;

    public UIDWorkspaceSynchronizer(IRepository repository) {
        this.repository = repository;
    }

    public void connect() throws IOException {
        this.executor = Executors.newSingleThreadExecutor();
        watcher = FileSystems.getDefault().newWatchService();

        Consumer<Path> register = registerWatchKeys(keys);

        IProject project = repository.getProject();
        IFolder webPageFolder = repository.getRepositoryStore(WebPageRepositoryStore.class).getResource();
        IFolder webWidgetsFolder = repository.getRepositoryStore(WebWidgetRepositoryStore.class).getResource();
        IFolder webFragmentsFolder = repository.getRepositoryStore(WebFragmentRepositoryStore.class).getResource();

        register.accept(webPageFolder.getLocation().toFile().toPath());
        register.accept(webWidgetsFolder.getLocation().toFile().toPath());
        register.accept(webFragmentsFolder.getLocation().toFile().toPath());

        enabled = true;
        pollingRunnable = executor.submit(() -> {
            while (true) {
                final WatchKey key;
                try {
                    key = watcher.take(); // wait for a key to be available
                } catch (InterruptedException ex) {
                    return;
                }

                final Path dir = keys.get(key);
                if (dir == null) {
                    continue;
                }

                key.pollEvents().stream()
                        .filter(e -> (e.kind() != OVERFLOW))
                        .forEach(e -> {
                            Path p = ((WatchEvent<Path>) e).context();
                            final Path absPath = dir.resolve(p);
                            Path relativePath = project.getLocation().toFile().toPath().relativize(absPath);
                            var path = org.eclipse.core.runtime.Path.fromOSString(relativePath.toString());
                            if (absPath.toFile().isDirectory()) {
                                if (e.kind() == ENTRY_CREATE) {
                                    register.accept(absPath);
                                }
                                if (e.kind() == ENTRY_MODIFY) {
                                    refreshResource(project.getFolder(path));
                                } else {
                                    refreshResource(project.getFolder(path).getParent());
                                }
                            } else {
                                if (e.kind() == ENTRY_CREATE || e.kind() == ENTRY_MODIFY) {
                                    refreshResource(project.getFile(path));
                                } else {
                                    refreshResource(project.getFile(path).getParent());
                                }
                            }
                        });

                boolean valid = key.reset(); // IMPORTANT: The key must be reset after processed
                if (!valid) {
                    AbstractFileStore.refreshExplorerView();
                }
            }
        });
    }

    private void refreshResource(IResource resource) {
        if (enabled && resource != null && resource.exists()) {
            try {
                resource.refreshLocal(IResource.DEPTH_ONE, null);
            } catch (CoreException e2) {
                BonitaStudioLog.error(e2);
            }
        }
    }

    public void disconnect() throws IOException {
        if (pollingRunnable != null) {
            pollingRunnable.cancel(true);
        }
        keys.clear();
        watcher.close();
        watcher = null;
        executor.shutdown();
        executor = null;
    }

    private Consumer<Path> registerWatchKeys(final Map<WatchKey, Path> keys) {
        return p -> {
            if (!p.toFile().exists() || !p.toFile().isDirectory()) {
                throw new RuntimeException("folder " + p + " does not exist or is not a directory");
            }
            try {
                Files.walkFileTree(p, new SimpleFileVisitor<Path>() {

                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        String dirPath = dir.toString();
                        if (dirPath.contains("web_widgets/pb") ||
                                dirPath.contains("web_widgets/.metadata") ||
                                dirPath.contains("web_page/.metadata") ||
                                dirPath.contains("web_fragments/.metadata")) {
                            return FileVisitResult.CONTINUE;
                        }
                        WatchKey watchKey = dir.register(watcher,
                                new WatchEvent.Kind[] { ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY },
                                SensitivityWatchEventModifier.HIGH);
                        keys.put(watchKey, dir);
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException e) {
                throw new RuntimeException("Error registering path " + p);
            }
        };
    }

    public static void disable() {
        enabled = false;
    }

    public static void enable() {
        new WorkspaceJob("Refreshing UID resources...") {

            @Override
            public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
                AbstractRepository currentRepository = RepositoryManager.getInstance().getCurrentRepository();
                WebPageRepositoryStore pageRepositoryStore = currentRepository
                        .getRepositoryStore(WebPageRepositoryStore.class);
                pageRepositoryStore.getResource().refreshLocal(IResource.DEPTH_INFINITE, monitor);
                WebFragmentRepositoryStore fragmentRepositoryStore = RepositoryManager.getInstance()
                        .getCurrentRepository().getRepositoryStore(WebFragmentRepositoryStore.class);
                fragmentRepositoryStore.getResource().refreshLocal(IResource.DEPTH_INFINITE, monitor);
                WebWidgetRepositoryStore widgetRepositoryStore = RepositoryManager.getInstance().getCurrentRepository()
                        .getRepositoryStore(WebWidgetRepositoryStore.class);
                widgetRepositoryStore.getResource().refreshLocal(IResource.DEPTH_INFINITE, monitor);
                AbstractFileStore.refreshExplorerView();
                return org.eclipse.core.runtime.Status.OK_STATUS;
            }
        }.schedule();
        enabled = true;
    }

}
