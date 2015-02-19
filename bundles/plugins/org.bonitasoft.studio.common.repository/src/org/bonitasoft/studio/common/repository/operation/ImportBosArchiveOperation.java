/**
 * Copyright (C) 2012-2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.operation;

import static org.bonitasoft.studio.common.Messages.bosProductName;
import static org.bonitasoft.studio.common.jface.FileActionDialog.activateYesNoToAll;
import static org.bonitasoft.studio.common.jface.FileActionDialog.deactivateYesNoToAll;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.bonitasoft.studio.common.ProcessesValidationAction;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.filestore.FileStoreChangeEvent;
import org.bonitasoft.studio.common.repository.filestore.FileStoreChangeEvent.EventType;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 *
 */
public class ImportBosArchiveOperation {

    private static final String TMP_IMPORT_PROJECT = "tmpImport";
    private String archiveFile;
    private IRepository currentRepository;
    private Status importStatus;
    private final IResourceImporter iResourceImporter;
    private final boolean launchValidationafterImport;

    private boolean validate = true;

    public ImportBosArchiveOperation() {
        this(true);
    }

    public ImportBosArchiveOperation(final boolean launchValidationafterImport) {
        iResourceImporter = new IResourceImporter();
        this.launchValidationafterImport = launchValidationafterImport;
    }

    public IStatus run(final IProgressMonitor monitor) {
        Assert.isNotNull(archiveFile);
        Assert.isNotNull(currentRepository);
        final File archive = new File(archiveFile);
        Assert.isTrue(archive.exists());

        currentRepository.disableBuild();
        IContainer container;
        try {
            container = createTempProject(archive, monitor);
        } catch (final CoreException e1) {
            restoreBuildState();
            return new Status(IStatus.ERROR, CommonRepositoryPlugin.PLUGIN_ID, "Failed to create temporary project", e1);
        }

        currentRepository.notifyFileStoreEvent(new FileStoreChangeEvent(EventType.PRE_IMPORT, null));
        final List<IRepositoryStore<? extends IRepositoryFileStore>> allRepositories = currentRepository.getAllStores();

        activateYesNoToAll();
        final Map<String, IRepositoryStore<? extends IRepositoryFileStore>> repositoryMap = new HashMap<String, IRepositoryStore<? extends IRepositoryFileStore>>();
        for (final IRepositoryStore<? extends IRepositoryFileStore> repository : allRepositories) {
            repositoryMap.put(repository.getName(), repository);
        }

        IContainer rootContainer = null;
        try {
            rootContainer = getRootContainer(container, repositoryMap);
            if (rootContainer == null) {
                restoreBuildState();
                return new Status(IStatus.ERROR, CommonRepositoryPlugin.PLUGIN_ID, Messages.bind(Messages.invalidArchive, new Object[] { bosProductName }));
            }
        } catch (final CoreException e1) {
            restoreBuildState();
            return new Status(IStatus.ERROR, CommonRepositoryPlugin.PLUGIN_ID, "Failed to retrieve root container", e1);
        }


        checkArchiveCompatibility(rootContainer);

        activateYesNoToAll();
        iResourceImporter.setResourcesToOpen(getResourcesToOpen(rootContainer));
        try {
            iResourceImporter.run(rootContainer, currentRepository, monitor);
        } catch (final ResourceImportException e) {
            return new Status(IStatus.ERROR, CommonRepositoryPlugin.PLUGIN_ID, "Failed to import resources in " + currentRepository.getName(), e);
        } finally {
            restoreBuildState();
        }

        deactivateYesNoToAll();

        final MultiStatus status = new MultiStatus(CommonRepositoryPlugin.PLUGIN_ID, 0, null, null);
        for (final String fileName : iResourceImporter.getFailedProcesses()) {
            status.add(ValidationStatus.error(String.format("Failed to import %s", fileName)));
        }

        currentRepository.refresh(Repository.NULL_PROGRESS_MONITOR);
        currentRepository.notifyFileStoreEvent(new FileStoreChangeEvent(EventType.POST_IMPORT, null));

        if (launchValidationafterImport) {
            validateAllAfterImport();
        }

        return status;
    }

    protected void restoreBuildState() {
        if (!currentRepository.isBuildEnable()) {
            currentRepository.enableBuild();
        }
        cleanTmpProject();
    }

    public IStatus getValidationsStatus() {
        return importStatus;
    }

    protected void validateAllAfterImport() {
        final ImportBosArchiveStatusBuilder statusBuilder = new ImportBosArchiveStatusBuilder();
        if (validate) {
            for (final IRepositoryFileStore diagramFileStore : iResourceImporter.getImportedProcesses()) {

                Display.getDefault().syncExec(new Runnable() {

                    @Override
                    public void run() {
                        final AbstractProcess process = (AbstractProcess) diagramFileStore.getContent();
                        final ProcessesValidationAction validationAction = new ProcessesValidationAction(Collections.singletonList(process));
                        validationAction.performValidation();
                        statusBuilder.addStatus(process, validationAction.getStatus());
                    }

                });

            }
        }
        importStatus = statusBuilder.done();
    }

    public void setCurrentRepository(final IRepository currentRepository) {
        this.currentRepository = currentRepository;
    }

    protected IContainer getRootContainer(
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

    public void openFilesToOpen() {
        for (final IRepositoryFileStore f : getFileStoresToOpen()) {
            f.open();
        }
    }

    public List<IRepositoryFileStore> getFileStoresToOpen() {
        return iResourceImporter.getFileStoresToOpen();
    }



    protected Set<String> getResourcesToOpen(final IContainer container) {
        final Properties manifestProperties = getManifestInfo(container);
        if (manifestProperties != null) {
            final String toOpen = manifestProperties.getProperty(ExportBosArchiveOperation.TO_OPEN);
            if (toOpen != null) {
                final String[] array = toOpen.split(",");
                return new HashSet<String>(Arrays.asList(array));
            }
        }
        //No manifest means import all .proc
        return null;
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
                throw new RuntimeException(Messages.bind(Messages.incompatibleProductVersion, ProductVersion.CURRENT_VERSION, version));
            }
        }
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
            importStatus = new Status(IStatus.ERROR, CommonRepositoryPlugin.PLUGIN_ID, e.getMessage(), e);
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


    public void setArchiveFile(final String archiveFile) {
        this.archiveFile = archiveFile;
    }

    public void disableValidation() {
        validate = false;
    }

    public void enableValidation() {
        validate = true;
    }
}
