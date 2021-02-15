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
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
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

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.ProjectDependenciesStore;
import org.bonitasoft.studio.common.repository.core.maven.JarLookupOperation;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.migration.ProjectDependenciesMigrationOperation;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup;
import org.bonitasoft.studio.common.repository.filestore.FileStoreChangeEvent;
import org.bonitasoft.studio.common.repository.filestore.FileStoreChangeEvent.EventType;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.store.LocalDependenciesStore;
import org.bonitasoft.studio.designer.core.operation.MigrateUIDOperation;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.importer.bos.BosArchiveImporterPlugin;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.importer.bos.model.AbstractFileModel;
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
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ImportBosArchiveOperation implements IRunnableWithProgress {

    private static final Set<String> SELECTED_FRAGMENT_TYPES = new HashSet<>();
    static {
        SELECTED_FRAGMENT_TYPES.add("CONNECTOR");
        SELECTED_FRAGMENT_TYPES.add("ACTOR_FILTER");
        SELECTED_FRAGMENT_TYPES.add("JAR");
    }
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

    public ImportBosArchiveOperation addDependencies(Collection<Dependency> dependencies) {
        this.dependencies.addAll(dependencies);
        return this;
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
            monitor.subTask("");
            doMigrateToMavenDependencies(importArchiveModel, monitor);
            doUpdateProjectDependencies(monitor);
        } finally {
            FileActionDialog.setDisablePopup(disablePopup);
            restoreBuildState();
        }

        currentRepository.build(monitor);
        currentRepository.handleFileStoreEvent(new FileStoreChangeEvent(EventType.POST_IMPORT, null));

        if (launchValidationafterImport) {
            DiagramRepositoryStore repositoryStore = repositoryAccessor
                    .getRepositoryStore(DiagramRepositoryStore.class);
            repositoryStore.computeProcesses(monitor);
            
            validateAllAfterImport(monitor, statusBuilder);
            
            repositoryStore.resetComputedProcesses();
        }

      
    }

    protected void doMigrateToMavenDependencies(ImportArchiveModel importArchiveModel, IProgressMonitor monitor)
            throws InvocationTargetException, InterruptedException {
        Path tempDirectory = null;
        Path lookupWorkdir = null;
        BosArchive bosArchive = importArchiveModel.getBosArchive();
        Model importedMavenModel = bosArchive.readMavenProject();
        if (!manualDependencyResolution && importedMavenModel == null) {
            // Automatic dependency lookup
            try {

                tempDirectory = Files.createTempDirectory("lib");
                lookupWorkdir = Files.createTempDirectory("lookupWorkdir");
                // Create a tmp folder containing the lib folder to execute the lookup operation
                bosArchive.extractLibFolder(tempDirectory);

                var projectDependenciesMigrationOperation = new ProjectDependenciesMigrationOperation(
                        tempDirectory.toFile())
                                .addRemoteRespository(
                                        JarLookupOperation.MAVEN_CENTRAL_REPOSITORY_URL);
                Set<String> usedDependencies = dependencyUsageAnalysis(importArchiveModel, monitor);
                projectDependenciesMigrationOperation.run(monitor);
                LocalDependenciesStore localDependencyStore = currentRepository.getLocalDependencyStore();
                projectDependenciesMigrationOperation.getResult().stream()
                        .filter(dl -> dl.getFileName() == null
                                || usedDependencies.contains(new File(dl.getFileName()).getName()))
                        .map(dl -> installLocalDependency(dl, localDependencyStore, monitor))
                        .map(DependencyLookup::toMavenDependency)
                        .forEach(dependencies::add);
            } catch (IOException e) {
                throw new InvocationTargetException(e);
            } finally {
                if (tempDirectory != null) {
                    FileUtil.deleteDir(tempDirectory.toFile());
                }
                if (lookupWorkdir != null) {
                    FileUtil.deleteDir(lookupWorkdir.toFile());
                }
            }
        }
    }

    private Set<String> dependencyUsageAnalysis(ImportArchiveModel importArchiveModel, IProgressMonitor monitor) {
        Set<String> usedDependencies = new HashSet<>();
        BosArchive bosArchive = importArchiveModel.getBosArchive();
        List<AbstractFileModel> files = Stream.concat(
                importArchiveModel.getStores().stream()
                        .filter(store -> DiagramRepositoryStore.STORE_NAME.equals(store.getFolderName()))
                        .flatMap(store -> store.getFiles().stream()),
                importArchiveModel.getStores().stream()
                        .filter(store -> ProcessConfigurationRepositoryStore.STORE_NAME.equals(store.getFolderName()))
                        .flatMap(store -> store.getFiles().stream()))
                .collect(Collectors.toList());

        monitor.beginTask(Messages.dependenciesUsageAnalysis, files.size());
        XPath xPath = XPathFactory.newInstance().newXPath();
        files.stream().forEach(fileModel -> {
            String path = fileModel.getPath();
            try (InputStream is = bosArchive.getZipFile().getInputStream(bosArchive.getEntry(path))) {
                Document document = asXMLDocument(is);
                NodeList nodes = (NodeList) xPath.evaluate("//fragments[@type='JAR' or @type='CONNECTOR' or @type='ACTOR_FILTER'][@exported='true' or not(@exported)]/@value", document, XPathConstants.NODESET);
                for (int i = 0; i < nodes.getLength(); ++i) {
                    Node item = nodes.item(i);
                    usedDependencies.add(item.getTextContent());
                }
            } catch (IOException | XPathExpressionException e) {
                BonitaStudioLog.error(e);
            } finally {
                monitor.worked(1);
            }
        });
        monitor.done();
        return usedDependencies;
    }
    
    private static Document asXMLDocument(InputStream source) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        factory.setNamespaceAware(true);
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(new InputSource(source));
        } catch (SAXException | IOException | ParserConfigurationException e) {
            return null;
        }
    }

    private DependencyLookup installLocalDependency(DependencyLookup dependencyLookup,
            LocalDependenciesStore localDependencyStore, IProgressMonitor monitor) {
        if (dependencyLookup.getStatus() == DependencyLookup.Status.NOT_FOUND) {
            try {
                localDependencyStore.install(dependencyLookup, monitor);
            } catch (CoreException e) {
                status.add(e.getStatus());
            }
        }
        return dependencyLookup;
    }

    protected void doUpdateProjectDependencies(IProgressMonitor monitor) {
        monitor.beginTask(Messages.updateProjectDependencies, IProgressMonitor.UNKNOWN);
        MavenProjectHelper mavenProjectHelper = new MavenProjectHelper();
        try {
            Model mavenModel = mavenProjectHelper.getMavenModel(currentRepository.getProject());
            dependencies.stream()
                    .forEach(newDependeny -> {
                        Optional<Dependency> dependency = mavenProjectHelper.findDependency(mavenModel,
                                newDependeny.getGroupId(),
                                newDependeny.getArtifactId());
                        if (dependency.isPresent()) {
                            mavenModel.removeDependency(dependency.get());
                            mavenModel.addDependency(newDependeny);
                        } else {
                            mavenModel.addDependency(newDependeny);
                        }
                    });
            mavenProjectHelper.saveModel(currentRepository.getProject(), mavenModel, monitor);
            IStatus installStatus = currentRepository.getLocalDependencyStore().runBonitaProjectStoreInstall(monitor);
            if (!installStatus.isOK()) {
                status.add(installStatus);
            }
            ProjectDependenciesStore projectDependenciesStore = currentRepository.getProjectDependenciesStore();
            if (projectDependenciesStore != null) {
                projectDependenciesStore.analyze(monitor);
            }
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
            status.add(e.getStatus());
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
        IRepositoryFileStore repositoryFileStore;
        try (ZipFile zipFile = bosArchive.getZipFile();) {
            repositoryFileStore = unit.doImport(zipFile,
                    monitor);
            if (repositoryFileStore == null && (unit instanceof ImportFileStoreModel)
                    && ((ImportFileStoreModel) unit).isStoreResource()) {
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
