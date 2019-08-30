package org.bonitasoft.studio.importer.bos.handler;

import static org.bonitasoft.studio.common.Messages.bonitaStudioModuleName;
import static org.bonitasoft.studio.ui.wizard.WizardPageBuilder.newPage;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import javax.inject.Named;

import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.importer.bos.model.ImportArchiveModel;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.importer.bos.status.BosImportStatusDialogHandler;
import org.bonitasoft.studio.importer.bos.wizard.ImportBosArchiveControlSupplier;
import org.bonitasoft.studio.importer.ui.dialog.SkippableProgressMonitorJobsDialog;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

public class ImportBosHandler {

    @Execute
    public void execute(Shell activeShell,
            RepositoryAccessor repositoryAccessor,
            ExceptionDialogHandler exceptionDialogHandler,
            @org.eclipse.e4.core.di.annotations.Optional @Named("org.bonitasoft.studio.importer.bos.commandparameter.file") String file) {
        final ImportBosArchiveControlSupplier bosArchiveControlSupplier = newImportBosArchiveControlSupplier(
                repositoryAccessor, exceptionDialogHandler, file);
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
                    .ifPresent(model -> importArchive(activeShell, model, new File(bosArchiveControlSupplier.getFilePath()),
                            repositoryAccessor));
        } catch (final Throwable t) {
            exceptionDialogHandler.openErrorDialog(activeShell, Messages.errorWhileImporting_message, t);
        }
    }

    protected Optional<ImportArchiveModel> onFinishOperation(ImportBosArchiveControlSupplier bosArchiveControlSupplier,
            RepositoryAccessor repositoryAccessor, IWizardContainer container) {
        return Optional.ofNullable(bosArchiveControlSupplier.getArchiveModel());
    }

    protected void importArchive(Shell activeShell, ImportArchiveModel model, File archive,
            RepositoryAccessor repositoryAccessor) {
        final SkippableProgressMonitorJobsDialog progressManager = new SkippableProgressMonitorJobsDialog(activeShell);
        final ImportBosArchiveOperation operation = createImportOperation(model, archive, progressManager,
                repositoryAccessor);
        operation.setCurrentRepository(getTargetRepository(repositoryAccessor));
        try {
            progressManager.run(true, false, operation);
        } catch (final InvocationTargetException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        operation.openFilesToOpen();
        PlatformUtil.openIntroIfNoOtherEditorOpen();
        activeShell.getDisplay().asyncExec(() -> openEndImportDialog(operation.getStatus(),
                repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class), activeShell,
                repositoryAccessor.getCurrentRepository().getName()));
    }

    protected ImportBosArchiveOperation createImportOperation(ImportArchiveModel model, File archive,
            final SkippableProgressMonitorJobsDialog progressManager, RepositoryAccessor repositoryAccessor) {
        return new ImportBosArchiveOperation(archive, progressManager, model, repositoryAccessor);
    }

    protected void openEndImportDialog(IStatus status, DiagramRepositoryStore store, Shell activeShell,
            String repositoryName) {
        new BosImportStatusDialogHandler(status, store).open(activeShell);
    }

    protected Repository getTargetRepository(RepositoryAccessor repositoryAccessor) {
        return repositoryAccessor.getCurrentRepository();
    }

    protected ImportBosArchiveControlSupplier newImportBosArchiveControlSupplier(RepositoryAccessor repositoryAccessor,
            ExceptionDialogHandler exceptionDialogHandler, String file) {
        return new ImportBosArchiveControlSupplier(repositoryAccessor, exceptionDialogHandler, file);
    }

}
