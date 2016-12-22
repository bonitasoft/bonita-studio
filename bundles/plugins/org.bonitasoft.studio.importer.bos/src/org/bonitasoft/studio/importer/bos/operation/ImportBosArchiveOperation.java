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
package org.bonitasoft.studio.importer.bos.operation;

import static com.google.common.collect.Lists.newArrayList;
import static org.bonitasoft.studio.common.Messages.bosProductName;
import static org.bonitasoft.studio.common.jface.FileActionDialog.activateYesNoToAll;
import static org.bonitasoft.studio.common.jface.FileActionDialog.deactivateYesNoToAll;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.filestore.FileStoreChangeEvent;
import org.bonitasoft.studio.common.repository.filestore.FileStoreChangeEvent.EventType;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.operation.ExportBosArchiveOperation;
import org.bonitasoft.studio.importer.bos.BosArchiveImporterPlugin;
import org.bonitasoft.studio.importer.bos.status.ImportBosArchiveStatusBuilder;
import org.bonitasoft.studio.importer.bos.validator.BosImporterValidator;
import org.bonitasoft.studio.importer.bos.validator.ValidationException;
import org.bonitasoft.studio.importer.ui.dialog.SkippableProgressMonitorJobsDialog;
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
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 */
public class ImportBosArchiveOperation implements IRunnableWithProgress {

    private static final String TMP_IMPORT_PROJECT = "tmpImport";
    private String archiveFile;
    private Repository currentRepository;
    private IStatus validationStatus;
    private final IResourceImporter iResourceImporter;
    private final boolean launchValidationafterImport;

    private boolean validate = true;
    private MultiStatus status;
    private SkippableProgressMonitorJobsDialog progressDialog;

    public ImportBosArchiveOperation() {
        this(true);
    }

    public ImportBosArchiveOperation(final boolean launchValidationafterImport) {
        iResourceImporter = new IResourceImporter();
        this.launchValidationafterImport = launchValidationafterImport;
    }

    @Override
    public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        Assert.isNotNull(archiveFile);
        Assert.isNotNull(currentRepository);
        final File archive = new File(archiveFile);
        Assert.isTrue(archive.exists());

        status = new MultiStatus(CommonRepositoryPlugin.PLUGIN_ID, 0, null, null);
        currentRepository.disableBuild();
        IContainer container;
        try {
            container = createTempProject(archive, monitor);
        } catch (final CoreException e1) {
            restoreBuildState();
            throw new InvocationTargetException(e1, "Failed to create temporary project");
        }

        currentRepository.handleFileStoreEvent(new FileStoreChangeEvent(EventType.PRE_IMPORT, null));
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
                throw new InvocationTargetException(new FileNotFoundException(), Messages.bind(Messages.invalidArchive, new Object[] { bosProductName }));
            }
        } catch (final CoreException e1) {
            restoreBuildState();
            throw new InvocationTargetException(e1, "Failed to retrieve root container");
        }

        checkArchiveCompatibility(rootContainer);

        activateYesNoToAll();
        iResourceImporter.setResourcesToOpen(getResourcesToOpen(rootContainer));
        try {
            iResourceImporter.run(rootContainer, currentRepository, monitor);
        } catch (final ResourceImportException e) {
            throw new InvocationTargetException(e, "Failed to import resources in " + currentRepository.getName());
        } finally {
            restoreBuildState();
        }

        deactivateYesNoToAll();

        for (final String fileName : iResourceImporter.getFailedProcesses()) {
            status.add(ValidationStatus.error(String.format("Failed to import %s", fileName)));
        }

        currentRepository.build(monitor);
        currentRepository.handleFileStoreEvent(new FileStoreChangeEvent(EventType.POST_IMPORT, null));

        if (launchValidationafterImport) {
            validateAllAfterImport(monitor);
        }
    }

    protected void restoreBuildState() {
        if (!currentRepository.isBuildEnable()) {
            currentRepository.enableBuild();
        }
        cleanTmpProject();
    }

    public void setProgressDialog(final SkippableProgressMonitorJobsDialog progressDialog) {
        this.progressDialog = progressDialog;
    }

    protected void validateAllAfterImport(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        if (progressDialog != null) {
            progressDialog.canBeSkipped();
        }
        final ImportBosArchiveStatusBuilder statusBuilder = new ImportBosArchiveStatusBuilder();
        if (validate) {
            final List<BosImporterValidator> validators = getValidators();
            for (final BosImporterValidator validator : validators) {
                try {
                    validator.validate(iResourceImporter, statusBuilder, monitor);
                } catch (final ValidationException e) {
                    statusBuilder.addStatus(new Status(IStatus.ERROR, BosArchiveImporterPlugin.PLUGIN_ID, "Validation error", e));
                }
            }
        }
        validationStatus = monitor.isCanceled() ? ValidationStatus.warning(org.bonitasoft.studio.importer.bos.i18n.Messages.skippedValidationMessage)
                : statusBuilder
                        .done();
    }

    protected List<BosImporterValidator> getValidators() {
        final List<BosImporterValidator> validators = newArrayList();
        for (final IConfigurationElement element : BonitaStudioExtensionRegistryManager.getInstance()
                .getConfigurationElements("org.bonitasoft.studio.importer.bos.validator")) {
            try {
                validators.add((BosImporterValidator) element.createExecutableExtension("class"));
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        return validators;
    }

    public void setCurrentRepository(final Repository currentRepository) {
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

    public ImportBosArchiveOperation disableValidation() {
        validate = false;
        return this;
    }

    public ImportBosArchiveOperation enableValidation() {
        validate = true;
        return this;
    }

    public IStatus getStatus() {
        if (status != null && !status.isOK()) {
            return status;
        }
        return validationStatus;

    }

}
