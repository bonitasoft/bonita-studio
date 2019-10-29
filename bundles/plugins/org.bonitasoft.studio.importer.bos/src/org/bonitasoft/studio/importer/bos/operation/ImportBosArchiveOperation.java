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

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.zip.ZipFile;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.filestore.FileStoreChangeEvent;
import org.bonitasoft.studio.common.repository.filestore.FileStoreChangeEvent.EventType;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.designer.core.repository.MigrateUIDOperation;
import org.bonitasoft.studio.importer.bos.BosArchiveImporterPlugin;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.importer.bos.model.BosArchive;
import org.bonitasoft.studio.importer.bos.model.ImportArchiveModel;
import org.bonitasoft.studio.importer.bos.model.ImportFileStoreModel;
import org.bonitasoft.studio.importer.bos.model.ImportStoreModel;
import org.bonitasoft.studio.importer.bos.model.ImportableUnit;
import org.bonitasoft.studio.importer.bos.status.ImportBosArchiveStatusBuilder;
import org.bonitasoft.studio.importer.bos.validator.BosImporterStatusProvider;
import org.bonitasoft.studio.importer.bos.validator.ValidationException;
import org.bonitasoft.studio.ui.dialog.SkippableProgressMonitorJobsDialog;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Display;

public class ImportBosArchiveOperation implements IRunnableWithProgress {

    private File archive;
    private Repository currentRepository;
    private IStatus validationStatus;
    private final boolean launchValidationafterImport;

    private boolean validate = true;
    private MultiStatus status;
    private SkippableProgressMonitorJobsDialog progressDialog;
    private Optional<ImportArchiveModel> archiveModel = Optional.empty();
    private final List<IRepositoryFileStore> fileStoresToOpen = new ArrayList<>();
    private final List<IRepositoryFileStore> importedProcesses = new ArrayList<>();
    private final List<IRepositoryFileStore> importedFileStores = new ArrayList<>();

    public ImportBosArchiveOperation(File selectedFile, SkippableProgressMonitorJobsDialog progressManager,
            ImportArchiveModel importArchiveModel, RepositoryAccessor repositoryAccessor) {
        this(selectedFile, progressManager, importArchiveModel, true, repositoryAccessor);
    }

    public ImportBosArchiveOperation(File selectedFile, SkippableProgressMonitorJobsDialog progressManager,
            ImportArchiveModel root, boolean launchValidationafterImport, RepositoryAccessor repositoryAccessor) {
        this(launchValidationafterImport, repositoryAccessor);
        progressDialog = progressManager;
        archive = selectedFile;
        archiveModel = Optional.ofNullable(root);
    }

    public ImportBosArchiveOperation(RepositoryAccessor repositoryAccessor) {
        this(true, repositoryAccessor);
    }

    public ImportBosArchiveOperation(final boolean launchValidationafterImport, RepositoryAccessor repositoryAccessor) {
        this.launchValidationafterImport = launchValidationafterImport;
        currentRepository = repositoryAccessor.getCurrentRepository();
    }

    @Override
    public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        Assert.isNotNull(archive);
        Assert.isNotNull(currentRepository);
        ImportBosArchiveStatusBuilder statusBuilder = createStatusBuilder();
        monitor.beginTask(Messages.retrivingDataToImport, IProgressMonitor.UNKNOWN);
        status = new MultiStatus(CommonRepositoryPlugin.PLUGIN_ID, 0, null, null);
        currentRepository.disableBuild();
        currentRepository.handleFileStoreEvent(new FileStoreChangeEvent(EventType.PRE_IMPORT, null));
        final boolean disablePopup = FileActionDialog.getDisablePopup();
        final ImportArchiveModel importArchiveModel = archiveModel
                .orElseGet(parseArchive(archive, currentRepository, monitor));
        monitor.worked(1);
        try {
            FileActionDialog.setDisablePopup(true);
            doImport(importArchiveModel, monitor);
        } finally {
            FileActionDialog.setDisablePopup(disablePopup);
            restoreBuildState();
        }

        currentRepository.build(monitor);
        currentRepository.handleFileStoreEvent(new FileStoreChangeEvent(EventType.POST_IMPORT, null));

        if (launchValidationafterImport) {
            validateAllAfterImport(monitor, statusBuilder);
        }
    }

    protected ImportBosArchiveStatusBuilder createStatusBuilder() {
        return new ImportBosArchiveStatusBuilder();
    }

    private void doImport(ImportArchiveModel importArchiveModel, IProgressMonitor monitor) {
        monitor.beginTask(Messages.importBosArchive,
                (int) importArchiveModel.getStores().stream().flatMap(ImportStoreModel::importableUnits).count());
        importArchiveModel.getStores().stream().sorted(srcStoresFirst()).flatMap(ImportStoreModel::importableUnits)
                .forEach(unit -> {
                    monitor.subTask(NLS.bind(Messages.importing, unit.getName()));
                    importUnit(unit, importArchiveModel.getBosArchive(), monitor);
                    monitor.worked(1);
                });
        migrateUID(monitor);
    }

    private Comparator<? super ImportStoreModel> srcStoresFirst() {
        return (f1, f2) -> {
            if(f1.getFolderName().startsWith("src")) {
                return -1;
            }
            if(f2.getFolderName().startsWith("src")) {
                return 1;
            }
            if(f1.getFolderName().equals("diagrams")) {
                return 1;
            }
            if(f2.getFolderName().equals("diagrams")) {
                return -1;
            }
            return f1.getFolderName().compareTo(f2.getFolderName());
        };
    }

    protected void migrateUID(IProgressMonitor monitor) {
        try {
            new MigrateUIDOperation().run(monitor);
        } catch (InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
        }
    }

    private void importUnit(ImportableUnit unit, BosArchive bosArchive, IProgressMonitor monitor) {
        IRepositoryFileStore repositoryFileStore;
        try (ZipFile zipFile = bosArchive.getZipFile();) {
            repositoryFileStore = unit.doImport(zipFile,
                    monitor);
            if (repositoryFileStore == null && (unit instanceof ImportFileStoreModel)) {
                status.add(ValidationStatus
                        .error(String.format("Failed to import %s", ((ImportFileStoreModel) unit).getFileName()))); // TODO The ImportFileStoreModel should have a status ...
            }
            if (repositoryFileStore != null) {
                importedFileStores.add(repositoryFileStore);
            }
            if (repositoryFileStore != null && repositoryFileStore.getName().endsWith(".proc")) {
                importedProcesses.add(repositoryFileStore);
            }
            if (repositoryFileStore != null && unit instanceof ImportFileStoreModel
                    && ((ImportFileStoreModel) unit).shouldOpen()) {
                fileStoresToOpen.add(repositoryFileStore);
            }
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Supplier<? extends ImportArchiveModel> parseArchive(final File archive, final Repository repository,
            final IProgressMonitor monitor) {
        return () -> {
            final ParseBosArchiveOperation parseBosArchiveOperation = newParseBosOperation(archive, repository);
            try {
                parseBosArchiveOperation.run(monitor);
            } catch (InvocationTargetException | InterruptedException e) {
                BonitaStudioLog.error(e);
            }
            return parseBosArchiveOperation.getImportArchiveModel();
        };
    }

    protected ParseBosArchiveOperation newParseBosOperation(final File archive, final Repository repository) {
        return new ParseBosArchiveOperation(archive, repository);
    }

    protected void restoreBuildState() {
        if (!currentRepository.isBuildEnable()) {
            currentRepository.enableBuild();
        }
    }

    public void setProgressDialog(final SkippableProgressMonitorJobsDialog progressDialog) {
        this.progressDialog = progressDialog;
    }

    protected void validateAllAfterImport(final IProgressMonitor monitor, ImportBosArchiveStatusBuilder statusBuilder) {
        if (progressDialog != null) {
            progressDialog.canBeSkipped();
        }
        if (validate) {
            final List<BosImporterStatusProvider> validators = getValidators();
            for (final BosImporterStatusProvider validator : validators) {
                try {
                    validator.buildStatus(this, statusBuilder, monitor);
                } catch (final ValidationException e) {
                    statusBuilder
                            .addStatus(new Status(IStatus.ERROR, BosArchiveImporterPlugin.PLUGIN_ID, "Validation error", e));
                }
            }
        }
        validationStatus = monitor.isCanceled()
                ? ValidationStatus.warning(org.bonitasoft.studio.importer.bos.i18n.Messages.skippedValidationMessage)
                : statusBuilder
                        .done();
    }

    protected List<BosImporterStatusProvider> getValidators() {
        final List<BosImporterStatusProvider> validators = newArrayList();
        for (final IConfigurationElement element : BonitaStudioExtensionRegistryManager.getInstance()
                .getConfigurationElements("org.bonitasoft.studio.importer.bos.validator")) {
            try {
                validators.add((BosImporterStatusProvider) element.createExecutableExtension("class"));
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        return validators;
    }

    public void setCurrentRepository(final Repository currentRepository) {
        this.currentRepository = currentRepository;
    }

    public void openFilesToOpen() {
        for (final IRepositoryFileStore f : fileStoresToOpen) {
            Display.getDefault().asyncExec(f::open);
        }
    }

    public void setArchiveFile(final String filePath) {
        this.archive = new File(filePath);
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

    public List<IRepositoryFileStore> getFileStoresToOpen() {
        return fileStoresToOpen;
    }

    public List<IRepositoryFileStore> getImportedProcesses() {
        return importedProcesses;
    }

    public List<IRepositoryFileStore> getImportedFileStores() {
        return importedFileStores;
    }

}
