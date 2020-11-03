package org.bonitasoft.studio.importer.bos.handler;

import static org.bonitasoft.studio.common.Messages.bonitaStudioModuleName;
import static org.bonitasoft.studio.ui.wizard.WizardPageBuilder.newPage;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.importer.bos.model.ImportWorkspaceModel;
import org.bonitasoft.studio.importer.bos.operation.ImportWorkspaceOperation;
import org.bonitasoft.studio.importer.bos.status.BosImportStatusDialogHandler;
import org.bonitasoft.studio.importer.bos.wizard.ImportWorkspaceControlSupplier;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.dialog.SkippableProgressMonitorJobsDialog;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.widgets.Shell;

public class ImportWorkspaceHandler {

    @Execute
    public void execute(Shell activeShell, RepositoryAccessor repositoryAccessor,
            ExceptionDialogHandler exceptionDialogHandler) {
        final ImportWorkspaceControlSupplier bosArchiveControlSupplier = newControlSupplier(
                repositoryAccessor, exceptionDialogHandler);
        final Optional<ImportWorkspaceModel> worksapceModel = WizardBuilder.<ImportWorkspaceModel> newWizard()
                .withTitle(Messages.importWorkspaceTitle)
                .needProgress()
                .havingPage(newPage()
                        .withTitle(Messages.importWorkspaceTitle)
                        .withDescription(
                                Messages.bind(Messages.importWorkspaceDescription, new Object[] { bonitaStudioModuleName }))
                        .withControl(bosArchiveControlSupplier))
                .onFinish(container -> onFinishOperation(bosArchiveControlSupplier, repositoryAccessor, container))
                .open(activeShell, Messages.importButtonLabel);

        try {
            worksapceModel
                    .ifPresent(
                            model -> importArchive(activeShell, model, repositoryAccessor));
        } catch (final Throwable t) {
            exceptionDialogHandler.openErrorDialog(activeShell, Messages.errorWhileImporting_message, t);
        }
    }

    protected Optional<ImportWorkspaceModel> onFinishOperation(ImportWorkspaceControlSupplier bosArchiveControlSupplier,
            RepositoryAccessor repositoryAccessor, IWizardContainer container) {
        return Optional.ofNullable(bosArchiveControlSupplier.getWorkspaceModel());
    }

    private void importArchive(Shell activeShell, ImportWorkspaceModel model, RepositoryAccessor repositoryAccessor) {
        final SkippableProgressMonitorJobsDialog progressManager = new SkippableProgressMonitorJobsDialog(activeShell);
        final ImportWorkspaceOperation operation = new ImportWorkspaceOperation(model, repositoryAccessor);
        try {
            progressManager.run(true, false, operation);
        } catch (final InvocationTargetException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        PlatformUtil.openIntroIfNoOtherEditorOpen();
        activeShell.getDisplay().asyncExec(() -> openEndImportDialog(operation.getStatus(),
                repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class), activeShell,
                repositoryAccessor.getCurrentRepository().getName()));
    }

    protected void openEndImportDialog(IStatus status, DiagramRepositoryStore store, Shell activeShell,
            String repositoryName) {
        new BosImportStatusDialogHandler(status, store).open(activeShell);
    }

    protected ImportWorkspaceControlSupplier newControlSupplier(RepositoryAccessor repositoryAccessor,
            ExceptionDialogHandler exceptionDialogHandler) {
        return new ImportWorkspaceControlSupplier(repositoryAccessor, exceptionDialogHandler);
    }

}
