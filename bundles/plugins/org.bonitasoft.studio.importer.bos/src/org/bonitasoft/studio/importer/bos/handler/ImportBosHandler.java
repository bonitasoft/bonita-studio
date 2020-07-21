package org.bonitasoft.studio.importer.bos.handler;

import static org.bonitasoft.studio.common.Messages.bonitaStudioModuleName;
import static org.bonitasoft.studio.ui.wizard.WizardPageBuilder.newPage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import javax.inject.Named;

import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.importer.bos.model.ImportArchiveModel;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.importer.bos.status.BosImportStatusDialogHandler;
import org.bonitasoft.studio.importer.bos.wizard.ImportBosArchiveControlSupplier;
import org.bonitasoft.studio.team.TeamRepositoryUtil;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.dialog.SkippableProgressMonitorJobsDialog;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

public class ImportBosHandler {

    private SwitchRepositoryStrategy switchRepositoryStrategy;

    @Execute
    public void execute(Shell activeShell,
            RepositoryAccessor repositoryAccessor,
            ExceptionDialogHandler exceptionDialogHandler,
            @org.eclipse.e4.core.di.annotations.Optional @Named("org.bonitasoft.studio.importer.bos.commandparameter.file") String file,
            @org.eclipse.e4.core.di.annotations.Optional @Named("org.bonitasoft.studio.importer.bos.commandparameter.targetProjectName") String projectName) {
        final ImportBosArchiveControlSupplier bosArchiveControlSupplier = newImportBosArchiveControlSupplier(
                repositoryAccessor, exceptionDialogHandler, file,
                projectName == null ? repositoryAccessor.getCurrentRepository().getName() : projectName);
        final Optional<ImportArchiveModel> archiveModel = WizardBuilder.<ImportArchiveModel> newWizard()
                .withTitle(Messages.importBosArchiveTitle)
                .needProgress()
                .havingPage(newPage()
                        .withTitle(Messages.importBosArchiveTitle)
                        .withDescription(
                                Messages.bind(Messages.importFileDescription, new Object[] { bonitaStudioModuleName }))
                        .withControl(bosArchiveControlSupplier))
                .onFinish(container -> onFinishOperation(bosArchiveControlSupplier, repositoryAccessor, container))
                .withSize(SWT.DEFAULT, 700)
                .open(activeShell, Messages.importButtonLabel);

        try {
            archiveModel
                    .ifPresent(model -> importArchive(activeShell, model, bosArchiveControlSupplier,
                            repositoryAccessor));
        } catch (final Throwable t) {
            exceptionDialogHandler.openErrorDialog(activeShell, Messages.errorWhileImporting_message, t);
        }
    }

    protected Optional<ImportArchiveModel> onFinishOperation(ImportBosArchiveControlSupplier bosArchiveControlSupplier,
            RepositoryAccessor repositoryAccessor, IWizardContainer container) {
        if (switchRepositoryStrategy.isSwitchRepository()) {
            try {
                container.run(true, false, this::switchToRepository);
            } catch (final InvocationTargetException | InterruptedException e) {
                throw new RuntimeException(Messages.failSwitchRepository, e);
            }
            if (switchRepositoryStrategy.isRebuildModel()) {
                try {
                    container.run(true, false,
                            monitor -> reparseArchive(bosArchiveControlSupplier, repositoryAccessor, monitor));
                } catch (InvocationTargetException | InterruptedException e) {
                    throw new RuntimeException(Messages.errorReadArchive, e);
                }
            }
        }
        return Optional.ofNullable(bosArchiveControlSupplier.getArchiveModel());
    }

    private void reparseArchive(ImportBosArchiveControlSupplier bosArchiveControlSupplier,
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
        return repositoryAccessor.getRepository(switchRepositoryStrategy.getTargetRepository());
    }

    private void switchToRepository(IProgressMonitor monitor) {
        TeamRepositoryUtil.switchToRepository(switchRepositoryStrategy.getTargetRepository(), monitor);
    }

    protected void importArchive(Shell activeShell, ImportArchiveModel model,
            ImportBosArchiveControlSupplier bosArchiveControlSupplier,
            RepositoryAccessor repositoryAccessor) {
        final SkippableProgressMonitorJobsDialog progressManager = new SkippableProgressMonitorJobsDialog(activeShell);
        File archiveFile = new File(bosArchiveControlSupplier.getFilePath());
        final ImportBosArchiveOperation operation = createImportOperation(model, archiveFile, progressManager,
                repositoryAccessor);
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

    protected ImportBosArchiveOperation createImportOperation(ImportArchiveModel model, File archive,
            final SkippableProgressMonitorJobsDialog progressManager, RepositoryAccessor repositoryAccessor) {
        return new ImportBosArchiveOperation(archive, progressManager, model, repositoryAccessor);
    }

    protected void openEndImportDialog(ImportBosArchiveOperation operation, DiagramRepositoryStore store, Shell activeShell,
            String repositoryName) {
        int result = -1;
        if (switchRepositoryStrategy.isSwitchRepository()) {
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
            PlatformUtil.openIntroIfNoOtherEditorOpen();
        }
    }

    protected ImportBosArchiveControlSupplier newImportBosArchiveControlSupplier(RepositoryAccessor repositoryAccessor,
            ExceptionDialogHandler exceptionDialogHandler, String file, String projectName) {
        switchRepositoryStrategy = new SwitchRepositoryStrategy(repositoryAccessor, projectName);
        return new ImportBosArchiveControlSupplier(repositoryAccessor, switchRepositoryStrategy, exceptionDialogHandler,
                file);
    }

}
