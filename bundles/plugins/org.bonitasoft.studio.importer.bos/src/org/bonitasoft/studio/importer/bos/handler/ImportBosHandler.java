package org.bonitasoft.studio.importer.bos.handler;

import static org.bonitasoft.studio.common.Messages.bonitaStudioModuleName;
import static org.bonitasoft.studio.ui.wizard.WizardPageBuilder.newPage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.maven.MavenRepositoryRegistry;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup;
import org.bonitasoft.studio.dependencies.operation.DependenciesUpdateOperationFactory;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.importer.bos.model.ImportArchiveModel;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.importer.bos.status.BosImportStatusDialogHandler;
import org.bonitasoft.studio.importer.bos.validator.DependencyLookupConflictHandler;
import org.bonitasoft.studio.importer.bos.wizard.BosArchiveContentPage;
import org.bonitasoft.studio.importer.bos.wizard.ExtensionsPreviewPage;
import org.bonitasoft.studio.importer.bos.wizard.ImportBosArchivePage;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.dialog.SkippableProgressMonitorJobsDialog;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.m2e.core.repository.IRepository;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

public class ImportBosHandler {

    private SwitchRepositoryStrategy switchRepositoryStrategy;
    private DependenciesUpdateOperationFactory processConfigurationUpdateOperationFactory;
    private MavenRepositoryRegistry mavenRepositoryRegistry;
    private RepositoryAccessor repositoryAccessor;
    private ExceptionDialogHandler exceptionDialogHandler;

    @Inject
    public ImportBosHandler(RepositoryAccessor repositoryAccessor,
            MavenRepositoryRegistry mavenRepositoryRegistry,
            ExceptionDialogHandler exceptionDialogHandler,
            SwitchRepositoryStrategy switchRepositoryStrategy,
            DependenciesUpdateOperationFactory processConfigurationUpdateOperationFactory) {
        this.repositoryAccessor = repositoryAccessor;
        this.mavenRepositoryRegistry = mavenRepositoryRegistry;
        this.exceptionDialogHandler = exceptionDialogHandler;
        this.switchRepositoryStrategy = switchRepositoryStrategy;
        this.processConfigurationUpdateOperationFactory = processConfigurationUpdateOperationFactory;
    }

    @Execute
    public void execute(Shell activeShell,
            @org.eclipse.e4.core.di.annotations.Optional @Named("org.bonitasoft.studio.importer.bos.commandparameter.file") String file,
            @org.eclipse.e4.core.di.annotations.Optional @Named("org.bonitasoft.studio.importer.bos.commandparameter.targetProjectName") String projectName) {
        IObservableList<DependencyLookup> dependenciesLookup = new WritableList<>();
        IObservableList<IRepository> mavenRepositories = new WritableList<>();
        String targetProject = switchRepositoryStrategy.getTargetRepository();
        updateRemoteRepositories(targetProject, mavenRepositories, mavenRepositoryRegistry, repositoryAccessor);
        var dependencyConflictHandler = new DependencyLookupConflictHandler(repositoryAccessor, dependenciesLookup);
        switchRepositoryStrategy
                .addTargetProjectChangeListener(newTargetProject -> updateRemoteRepositories(newTargetProject,
                        mavenRepositories, mavenRepositoryRegistry, repositoryAccessor));
        switchRepositoryStrategy
                .addTargetProjectChangeListener(dependencyConflictHandler::setTargetProject);
        ImportBosArchivePage importBosArchivePage = new ImportBosArchivePage(repositoryAccessor,
                switchRepositoryStrategy,
                exceptionDialogHandler,
                file);
        BosArchiveContentPage bosArchiveContentPage = new BosArchiveContentPage(importBosArchivePage);
        ExtensionsPreviewPage dependenciesPreviewControlSupplier = new ExtensionsPreviewPage(
                dependenciesLookup,
                mavenRepositories,
                importBosArchivePage,
                dependencyConflictHandler,
                exceptionDialogHandler);
        final Optional<ImportArchiveModel> archiveModel = WizardBuilder.<ImportArchiveModel> newWizard()
                .withTitle(Messages.importBosArchiveTitle)
                .needProgress()
                .havingPage(newPage()
                        .withTitle(Messages.importBosArchiveTitle)
                        .withDescription(
                                NLS.bind(Messages.importFileDescription, bonitaStudioModuleName))
                        .withControl(importBosArchivePage)
                        .withNextPageButtonLabel(Messages.detailsPageButtonLabel),
                        newPage()
                                .withTitle(Messages.bosArchiveContentTitle)
                                .withDescription(Messages.bosArchiveContentDescription)
                                .withControl(bosArchiveContentPage)
                                .withNextPageButtonLabel(Messages.extensionPageLabel),
                        newPage()
                                .withTitle(Messages.extensionsPreviewTitle)
                                .withDescription(Messages.extensionsPreviewDescription)
                                .withControl(dependenciesPreviewControlSupplier)
                                .withBackPageButtonLabel(Messages.detailsPageButtonLabel))
                .canFinishProvider(wizardDialog -> Objects.equals(wizardDialog.getCurrentPage().getTitle(),
                        Messages.extensionsPreviewTitle))
                .onFinish(container -> onFinishOperation(importBosArchivePage, repositoryAccessor, container))
                .withSize(SWT.DEFAULT, 800)
                .open(activeShell, Messages.importButtonLabel);

        try {
            archiveModel
                    .ifPresent(model -> importArchive(activeShell,
                            model,
                            importBosArchivePage,
                            dependenciesPreviewControlSupplier,
                            dependenciesLookup,
                            repositoryAccessor));
        } catch (final Throwable t) {
            exceptionDialogHandler.openErrorDialog(activeShell, Messages.errorWhileImporting_message, t);
        }
    }

    private void updateRemoteRepositories(String targetRepository,
            IObservableList<IRepository> mavenRepositories,
            MavenRepositoryRegistry mavenRepositoryRegistry,
            RepositoryAccessor repositoryAccessor) {
        mavenRepositories.clear();
        if (targetRepository != null && !targetRepository.isBlank()) {
            try {
                mavenRepositoryRegistry.updateRegistry().run(AbstractRepository.NULL_PROGRESS_MONITOR);
            } catch (InvocationTargetException | InterruptedException e) {
                BonitaStudioLog.error(e);
            }
            mavenRepositories.addAll(mavenRepositoryRegistry.getGlobalRepositories());
            AbstractRepository repository = repositoryAccessor.getRepository(targetRepository);
            if (repository != null && repository.exists()) {
                mavenRepositories.addAll(mavenRepositoryRegistry.getProjectRepositories(repository.getProject()));
            }
        }
    }

    protected Optional<ImportArchiveModel> onFinishOperation(ImportBosArchivePage bosArchiveControlSupplier,
            RepositoryAccessor repositoryAccessor, IWizardContainer container) {
        if (switchRepositoryStrategy.isCreateNewProject()) {
            try {
                container.run(true, false,
                        monitor -> {
                            repositoryAccessor
                                    .createNewRepository(bosArchiveControlSupplier.getProjectMetadata(), monitor);
                            reparseArchive(bosArchiveControlSupplier, repositoryAccessor, monitor);
                        });
            } catch (InvocationTargetException | InterruptedException e) {
                throw new RuntimeException(Messages.failSwitchRepository, e);
            }
        }
        return Optional.ofNullable(bosArchiveControlSupplier.getArchiveModel());
    }

    private void reparseArchive(ImportBosArchivePage bosArchiveControlSupplier,
            RepositoryAccessor repositoryAccessor, IProgressMonitor monitor) {
        try {
            final ImportArchiveModel archiveModel = bosArchiveControlSupplier.getArchiveModel().getBosArchive()
                    .toImportModel(repositoryAccessor.getRepository(switchRepositoryStrategy.getTargetRepository()),
                            monitor);
            bosArchiveControlSupplier.setArchiveModel(archiveModel);
        } catch (final IOException e) {
            throw new RuntimeException(Messages.errorReadArchive, e);
        }

    }

    private AbstractRepository getTargetRepository(RepositoryAccessor repositoryAccessor) {
        String targetRepository = switchRepositoryStrategy.getTargetRepository();
        AbstractRepository currentRepository = repositoryAccessor.getCurrentRepository();
        if (Objects.equals(targetRepository, currentRepository.getName())) {
            return currentRepository;
        }
        return repositoryAccessor.getRepository(targetRepository);
    }

    protected void importArchive(Shell activeShell, ImportArchiveModel model,
            ImportBosArchivePage bosArchiveControlSupplier,
            ExtensionsPreviewPage dependenciesPreviewControlSupplier,
            IObservableList<DependencyLookup> dependenciesLookup,
            RepositoryAccessor repositoryAccessor) {
        final SkippableProgressMonitorJobsDialog progressManager = new SkippableProgressMonitorJobsDialog(activeShell);
        File archiveFile = new File(bosArchiveControlSupplier.getFilePath());
        final ImportBosArchiveOperation operation = new ImportBosArchiveOperation(archiveFile, progressManager, model,
                repositoryAccessor, processConfigurationUpdateOperationFactory);
        if (dependenciesPreviewControlSupplier.hasRunPreview(model)) {
            operation
                    .manualDependencyResolution()
                    .addDependenciesLooukp(dependenciesLookup);
        }
        operation.setCurrentRepository(getTargetRepository(repositoryAccessor));
        try {
            progressManager.run(true, false, operation);
        } catch (final InvocationTargetException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (bosArchiveControlSupplier.shouldDeleteTempFile()) {
                archiveFile.delete();
            }
        }

        activeShell.getDisplay().asyncExec(() -> openEndImportDialog(operation,
                repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class), activeShell,
                repositoryAccessor.getCurrentRepository().getName()));
    }

    protected void openEndImportDialog(ImportBosArchiveOperation operation, DiagramRepositoryStore store,
            Shell activeShell,
            String repositoryName) {
        int result = -1;
        if (switchRepositoryStrategy.isCreateNewProject()) {
            result = new BosImportStatusDialogHandler(operation.getStatus(), store,
                    NLS.bind(Messages.importWithSwitchRepositorySuccessful,
                            repositoryName),
                    NLS.bind(Messages.importWithSwitchRepositoryError,
                            repositoryName)).open(activeShell);
        } else {
            result = new BosImportStatusDialogHandler(operation.getStatus(), store).open(activeShell);
        }
        if (result != IDialogConstants.PROCEED_ID) {
            operation.openFilesToOpen();
        }
    }

}
