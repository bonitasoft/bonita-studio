package org.bonitasoft.studio.importer.bos.operation;

import static org.bonitasoft.studio.common.Messages.bosProductName;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.operation.ExportBosArchiveOperation;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

public class TempProjectManager {

    protected static final String TMP_IMPORT_PROJECT = "tmpImport";
    protected IStatus validationStatus;

    protected IContainer createTempProject(final File archive, final IProgressMonitor monitor) throws CoreException {
        final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        final IProject container = root.getProject(TMP_IMPORT_PROJECT);
        if (container.exists()) {
            try {
                container.refreshLocal(IResource.DEPTH_ZERO, Repository.NULL_PROGRESS_MONITOR);
                container.delete(true, true, Repository.NULL_PROGRESS_MONITOR);
                container.refreshLocal(IResource.DEPTH_ZERO, Repository.NULL_PROGRESS_MONITOR);
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        container.create(Repository.NULL_PROGRESS_MONITOR);
        container.open(Repository.NULL_PROGRESS_MONITOR);
        try {
            PlatformUtil.unzipZipFiles(archive, container.getLocation().toFile(), Repository.NULL_PROGRESS_MONITOR);
        } catch (final Exception e) {
            validationStatus = new Status(IStatus.ERROR, CommonRepositoryPlugin.PLUGIN_ID, e.getMessage(), e);
            BonitaStudioLog.error(e);
            Display.getDefault().syncExec(new Runnable() {

                @Override
                public void run() {
                    MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.importBonita6xTitle,
                            Messages.bind(Messages.importBonita6xError, new Object[] { archive.getName() }));
                }
            });

        }
        container.refreshLocal(IResource.DEPTH_INFINITE, Repository.NULL_PROGRESS_MONITOR);
        return container;
    }

    protected void restoreBuildState(Repository currentRepository) {
        if (!currentRepository.isBuildEnable()) {
            currentRepository.enableBuild();
        }
        cleanTmpProject();
    }

    protected void cleanTmpProject() {
        final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        final IProject container = root.getProject(TMP_IMPORT_PROJECT);
        if (container.exists()) {
            try {
                container.close(Repository.NULL_PROGRESS_MONITOR);
                container.refreshLocal(IResource.DEPTH_ZERO, Repository.NULL_PROGRESS_MONITOR);
                container.delete(true, true, Repository.NULL_PROGRESS_MONITOR);
                container.refreshLocal(IResource.DEPTH_ZERO, Repository.NULL_PROGRESS_MONITOR);
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    public IContainer getTempProject(File archive, IProgressMonitor monitor, Repository currentRepository)
            throws InvocationTargetException {
        IContainer container;
        try {
            container = createTempProject(archive, monitor);
        } catch (final CoreException e1) {
            restoreBuildState(currentRepository);
            throw new InvocationTargetException(e1, "Failed to create temporary project");
        }
        return container;
    }

    public Map<String, IRepositoryStore<? extends IRepositoryFileStore>> getRepositoryMap(Repository currentRepository) {
        final List<IRepositoryStore<? extends IRepositoryFileStore>> allRepositories = currentRepository.getAllStores();
        final Map<String, IRepositoryStore<? extends IRepositoryFileStore>> repositoryMap = new HashMap<String, IRepositoryStore<? extends IRepositoryFileStore>>();
        for (final IRepositoryStore<? extends IRepositoryFileStore> repository : allRepositories) {
            repositoryMap.put(repository.getName(), repository);
        }
        return repositoryMap;
    }

    protected IContainer createRootContainer(
            IContainer container,
            final Map<String, IRepositoryStore<? extends IRepositoryFileStore>> repositoryMap) throws CoreException {
        boolean isValid = false;
        while (container != null && !isValid) {
            IResource lastVisited = null;
            for (final IResource r : container.members(IResource.FOLDER)) {
                if (repositoryMap.get(r.getName()) != null) {
                    isValid = true;
                    break;
                }
                lastVisited = r;
            }
            if (isValid) {
                break;
            }
            if (lastVisited instanceof IFolder) {
                container = (IFolder) lastVisited;
            } else {
                container = null;
            }
        }
        if (!isValid) {
            return null;
        }
        return container;
    }

    protected IContainer getRootContainer(IContainer tmpContainer,
            Map<String, IRepositoryStore<? extends IRepositoryFileStore>> repositoryMap, Repository currentRepository)
            throws InvocationTargetException {
        IContainer rootContainer;
        try {
            rootContainer = createRootContainer(tmpContainer, repositoryMap);
            if (rootContainer == null) {
                restoreBuildState(currentRepository);
                throw new InvocationTargetException(new FileNotFoundException(),
                        Messages.bind(Messages.invalidArchive, new Object[] { bosProductName }));
            }
        } catch (final CoreException e1) {
            restoreBuildState(currentRepository);
            throw new InvocationTargetException(e1, "Failed to retrieve root container");
        }
        return rootContainer;
    }

    protected void checkArchiveCompatibility(final IContainer container) {
        final Properties manifestProperties = getManifestInfo(container);
        if (manifestProperties != null) {
            final String version = manifestProperties.getProperty(ExportBosArchiveOperation.VERSION);
            if (!ProductVersion.canBeImported(version)) {
                cleanTmpProject();
                Display.getDefault().syncExec(new Runnable() {

                    @Override
                    public void run() {
                        MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.importErrorTitle,
                                Messages.bind(Messages.incompatibleProductVersion, ProductVersion.CURRENT_VERSION, version));
                    }
                });
                throw new RuntimeException(
                        Messages.bind(Messages.incompatibleProductVersion, ProductVersion.CURRENT_VERSION, version));
            }
        }
    }

    public Set<String> getResourcesToOpen(final IContainer container) {
        final Properties manifestProperties = getManifestInfo(container);
        if (manifestProperties != null) {
            final String toOpen = manifestProperties.getProperty(ExportBosArchiveOperation.TO_OPEN);
            if (toOpen != null) {
                final String[] array = toOpen.split(",");
                return new HashSet<String>(Arrays.asList(array));
            }
        }
        return Collections.emptySet();
    }

    protected Properties getManifestInfo(final IContainer container) {
        final IFile file = container.getFile(Path.fromOSString(ExportBosArchiveOperation.BOS_ARCHIVE_MANIFEST));
        if (file.exists()) {
            final Properties p = new Properties();
            InputStream contents = null;
            try {
                contents = file.getContents();
                p.load(contents);
            } catch (final Exception e) {
                BonitaStudioLog.error(e);
                return null;
            } finally {
                if (contents != null) {
                    try {
                        contents.close();
                    } catch (final IOException e) {
                        BonitaStudioLog.error(e);
                    }
                }
            }
            return p;
        }
        return null;
    }
}
