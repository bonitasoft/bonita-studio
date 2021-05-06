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

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipFile;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.bonitasoft.studio.businessobject.maven.InstallBDMDependenciesEventHandler;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.InputStreamSupplier;
import org.bonitasoft.studio.common.repository.core.ProjectDependenciesStore;
import org.bonitasoft.studio.common.repository.core.maven.DefinitionUsageOperation;
import org.bonitasoft.studio.common.repository.core.maven.DependencyUsageOperation;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.MavenRepositoryRegistry;
import org.bonitasoft.studio.common.repository.core.maven.ProjectDependenciesLookupOperation;
import org.bonitasoft.studio.common.repository.core.maven.migration.ProjectDependenciesMigrationOperation;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup;
import org.bonitasoft.studio.common.repository.filestore.FileStoreChangeEvent;
import org.bonitasoft.studio.common.repository.filestore.FileStoreChangeEvent.EventType;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.store.LocalDependenciesStore;
import org.bonitasoft.studio.dependencies.repository.DependencyFileStore;
import org.bonitasoft.studio.designer.core.operation.MigrateUIDOperation;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.importer.bos.BosArchiveImporterPlugin;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.importer.bos.model.AbstractFolderModel;
import org.bonitasoft.studio.importer.bos.model.ArchiveInputStreamSupplier;
import org.bonitasoft.studio.importer.bos.model.BosArchive;
import org.bonitasoft.studio.importer.bos.model.ImportArchiveModel;
import org.bonitasoft.studio.importer.bos.model.ImportFileStoreModel;
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
import org.eclipse.m2e.core.repository.IRepository;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Display;

public class ImportBosArchiveOperation implements IRunnableWithProgress {

    private File archive;
    private AbstractRepository currentRepository;
    private IStatus validationStatus;
    private final boolean launchValidationafterImport;

    private boolean validate = true;
    private MultiStatus status;
    private SkippableProgressMonitorJobsDialog progressDialog;
    private Optional<ImportArchiveModel> archiveModel = Optional.empty();
    private final List<IRepositoryFileStore> fileStoresToOpen = new ArrayList<>();
    private final List<IRepositoryFileStore> importedProcesses = new ArrayList<>();
    private final List<IRepositoryFileStore> importedFileStores = new ArrayList<>();
    private RepositoryAccessor repositoryAccessor;
    private Set<Dependency> dependencies = new HashSet<>();
    private boolean manualDependencyResolution = false;
    private Set<DependencyLookup> dependenciesLookup = new HashSet<>();
    private MavenRepositoryRegistry mavenRepositoryRegistry = new MavenRepositoryRegistry();

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
        this.repositoryAccessor = repositoryAccessor;
        currentRepository = repositoryAccessor.getCurrentRepository();
    }

    public ImportBosArchiveOperation manualDependencyResolution() {
        this.manualDependencyResolution = true;
        return this;
    }

    public ImportBosArchiveOperation addDependency(Dependency dependency) {
        this.dependencies.add(dependency);
        return this;
    }

    public ImportBosArchiveOperation addDependenciesLooukp(Collection<DependencyLookup> dependenciesLookup) {
        this.dependenciesLookup.addAll(dependenciesLookup);
        return this;
    }

    @Override
    public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        Assert.isNotNull(archive);
        Assert.isNotNull(currentRepository);

        InstallBDMDependenciesEventHandler.disableProjectUpateJob();
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
            monitor.subTask("");
            Model importedMavenModel = existingMavenModel(importArchiveModel);
            LocalDependenciesStore localDependencyStore = currentRepository.getLocalDependencyStore();
            if (!manualDependencyResolution && importedMavenModel == null) {
                dependenciesLookup = doMigrateToMavenDependencies(importArchiveModel, monitor);
                dependenciesLookup.stream()
                        .forEach(dl -> dl.setSelected(dl.isUsed()));
            } else if (!manualDependencyResolution) {
                ProjectDependenciesLookupOperation operation = new ProjectDependenciesLookupOperation(
                        importedMavenModel,
                        new ArchiveLocalDependencyInputStreamSupplier(importArchiveModel.getBosArchive()));
                mavenRepositoryRegistry.updateRegistry().run(monitor);
                mavenRepositoryRegistry
                        .getGlobalRepositories()
                        .stream()
                        .map(IRepository::getUrl)
                        .forEach(operation::addRemoteRespository);
                operation.run(monitor);
                dependenciesLookup = operation.getResult();
                dependenciesLookup.stream()
                        .forEach(dl -> dl.setSelected(true));
            }
            
            dependenciesLookup.stream()
                    .filter(DependencyLookup::isSelected)
                    .map(dl -> installLocalDependency(dl, localDependencyStore))
                    .map(DependencyLookup::toMavenDependency)
                    .forEach(dependencies::add);
            doUpdateProjectDependencies(monitor);
        } finally {
            FileActionDialog.setDisablePopup(disablePopup);
            restoreBuildState();
        }

        currentRepository.build(monitor);
        monitor.subTask("");
        currentRepository.handleFileStoreEvent(new FileStoreChangeEvent(EventType.POST_IMPORT, null));

        if (launchValidationafterImport) {
            DiagramRepositoryStore repositoryStore = repositoryAccessor
                    .getRepositoryStore(DiagramRepositoryStore.class);
            repositoryStore.computeProcesses(monitor);

            validateAllAfterImport(monitor, statusBuilder);

            repositoryStore.resetComputedProcesses();
        }
        InstallBDMDependenciesEventHandler.enableProjectUpateJob();

    }

    protected Model existingMavenModel(final ImportArchiveModel importArchiveModel) {
        return importArchiveModel.getBosArchive().getMavenProject();
    }

    protected Set<DependencyLookup> doMigrateToMavenDependencies(ImportArchiveModel importArchiveModel,
            IProgressMonitor monitor)
            throws InvocationTargetException, InterruptedException {
        Set<String> usedDependencies = dependencyUsageAnalysis(importArchiveModel, monitor);
        Set<String> usedDefinitions = definitionUsageAnalysis(importArchiveModel, monitor);
        BosArchive bosArchive = importArchiveModel.getBosArchive();
        // Automatic dependency lookup
        List<InputStreamSupplier> jars = new ArrayList<>();
        try {
            jars = bosArchive.loadJarInputStreamSuppliers();
            var projectDependenciesMigrationOperation = new ProjectDependenciesMigrationOperation(jars)
                    .addUsedDependencies(usedDependencies)
                    .addUsedDefinitions(usedDefinitions);
            mavenRepositoryRegistry.updateRegistry().run(monitor);
            mavenRepositoryRegistry
                    .getGlobalRepositories()
                    .stream()
                    .map(IRepository::getUrl)
                    .forEach(projectDependenciesMigrationOperation::addRemoteRespository);
            projectDependenciesMigrationOperation.run(monitor);
            return projectDependenciesMigrationOperation.getResult();
        } finally {
            for (InputStreamSupplier jar : jars) {
                try {
                    jar.close();
                } catch (Exception e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
    }

    public static Set<String> definitionUsageAnalysis(ImportArchiveModel importArchiveModel, IProgressMonitor monitor)
            throws InvocationTargetException, InterruptedException {
        BosArchive bosArchive = importArchiveModel.getBosArchive();
        List<InputStreamSupplier> files = importArchiveModel.getStores().stream()
                .filter(store -> DiagramRepositoryStore.STORE_NAME.equals(store.getFolderName()))
                .flatMap(store -> store.getFiles().stream())
                .filter(f -> f.getFileName().endsWith(".proc"))
                .map(f -> new ArchiveInputStreamSupplier(bosArchive.getArchiveFile(), bosArchive.getEntry(f.getPath())))
                .collect(Collectors.toList());

        DefinitionUsageOperation operation = new DefinitionUsageOperation(files);
        operation.run(monitor);
        return operation.getUsedDefinitions();
    }

    public static Set<String> dependencyUsageAnalysis(ImportArchiveModel importArchiveModel, IProgressMonitor monitor)
            throws InvocationTargetException, InterruptedException {
        BosArchive bosArchive = importArchiveModel.getBosArchive();
        List<InputStreamSupplier> files = Stream.concat(
                importArchiveModel.getStores().stream()
                        .filter(store -> DiagramRepositoryStore.STORE_NAME.equals(store.getFolderName()))
                        .flatMap(store -> store.getFiles().stream())
                        .filter(f -> f.getFileName().endsWith(".proc")),
                importArchiveModel.getStores().stream()
                        .filter(store -> ProcessConfigurationRepositoryStore.STORE_NAME.equals(store.getFolderName()))
                        .flatMap(store -> store.getFiles().stream())
                        .filter(f -> f.getFileName().endsWith(".conf")))
                .map(f -> new ArchiveInputStreamSupplier(bosArchive.getArchiveFile(), bosArchive.getEntry(f.getPath())))
                .collect(Collectors.toList());

        DependencyUsageOperation operation = new DependencyUsageOperation(files);
        operation.run(monitor);
        return operation.getUsedDependencies();
    }

    private DependencyLookup installLocalDependency(DependencyLookup dependencyLookup,
            LocalDependenciesStore localDependencyStore) {
        try {
            localDependencyStore.install(dependencyLookup);
        } catch (CoreException e) {
            status.add(e.getStatus());
        }
        return dependencyLookup;
    }

    protected void doUpdateProjectDependencies(IProgressMonitor monitor) {
        monitor.beginTask(Messages.updateProjectDependencies, IProgressMonitor.UNKNOWN);
        MavenProjectHelper mavenProjectHelper = new MavenProjectHelper();
        try {
            Model mavenModel = mavenProjectHelper.getMavenModel(currentRepository.getProject());
            dependencies.stream()
                    .filter(dep -> mavenProjectHelper.findDependency(mavenModel, dep).isEmpty())
                    .forEach(mavenModel::addDependency);
            mavenProjectHelper.saveModel(currentRepository.getProject(), mavenModel);
            ProjectDependenciesStore projectDependenciesStore = currentRepository.getProjectDependenciesStore();
            if (projectDependenciesStore != null) {
                projectDependenciesStore.analyze(monitor);
            }
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
            status.add(e.getStatus());
        } finally {
            monitor.done();
        }
    }

    protected ImportBosArchiveStatusBuilder createStatusBuilder() {
        return new ImportBosArchiveStatusBuilder();
    }

    private void doImport(ImportArchiveModel importArchiveModel, IProgressMonitor monitor) {
        monitor.beginTask(Messages.importBosArchive,
                (int) importArchiveModel.getStores().stream().flatMap(AbstractFolderModel::importableUnits).count());
        importArchiveModel.getStores().stream()
                .sorted(srcStoresFirst())
                .flatMap(AbstractFolderModel::importableUnits)
                .forEach(unit -> {
                    monitor.subTask(NLS.bind(Messages.importing, unit.getName()));
                    importUnit(unit, importArchiveModel.getBosArchive(), monitor);
                    monitor.worked(1);
                });
        migrateUID(monitor);
    }

    private Comparator<? super AbstractFolderModel> srcStoresFirst() {
        return (f1, f2) -> {
            if (f1.getFolderName().startsWith("src")) {
                return -1;
            }
            if (f2.getFolderName().startsWith("src")) {
                return 1;
            }
            if (f1.getFolderName().equals("diagrams")) {
                return 1;
            }
            if (f2.getFolderName().equals("diagrams")) {
                return -1;
            }
            return f1.getFolderName().compareTo(f2.getFolderName());
        };
    }

    protected void migrateUID(IProgressMonitor monitor) {
        try {
            MigrateUIDOperation migrateUIDOperation = new MigrateUIDOperation();
            migrateUIDOperation.run(monitor);
            Arrays.asList(migrateUIDOperation.getStatus().getChildren()).stream()
                    .filter(s -> Objects.equals(s.getSeverity(), IStatus.ERROR))
                    .forEach(status::add);
        } catch (InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
        }
    }

    private void importUnit(ImportableUnit unit, BosArchive bosArchive, IProgressMonitor monitor) {
        try (ZipFile zipFile = bosArchive.getZipFile();) {
            IRepositoryFileStore repositoryFileStore = unit.doImport(zipFile, monitor);
            if (repositoryFileStore == null && (unit instanceof ImportFileStoreModel)
                    && ((ImportFileStoreModel) unit).isStoreResource()) {
                status.add(ValidationStatus
                        .error(String.format("Failed to import %s", ((ImportFileStoreModel) unit).getFileName())));
            }
            if (repositoryFileStore != null && DependencyFileStore.NULL != repositoryFileStore) {
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

    private Supplier<? extends ImportArchiveModel> parseArchive(final File archive, final AbstractRepository repository,
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

    protected ParseBosArchiveOperation newParseBosOperation(final File archive, final AbstractRepository repository) {
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
                            .addStatus(new Status(IStatus.ERROR, BosArchiveImporterPlugin.PLUGIN_ID, "Validation error",
                                    e));
                }
            }
        }
        validationStatus = monitor.isCanceled()
                ? ValidationStatus.warning(org.bonitasoft.studio.importer.bos.i18n.Messages.skippedValidationMessage)
                : statusBuilder
                        .done();
    }

    protected List<BosImporterStatusProvider> getValidators() {
        final List<BosImporterStatusProvider> validators = new ArrayList<>();
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

    public void setCurrentRepository(final AbstractRepository currentRepository) {
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

    public RepositoryAccessor getRepositoryAccessor() {
        return repositoryAccessor;
    }

}
