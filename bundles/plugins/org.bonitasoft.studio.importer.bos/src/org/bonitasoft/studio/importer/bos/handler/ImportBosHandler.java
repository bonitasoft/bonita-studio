package org.bonitasoft.studio.importer.bos.handler;

import static com.google.common.base.Strings.isNullOrEmpty;
import static org.bonitasoft.studio.common.Messages.bonitaStudioModuleName;
import static org.bonitasoft.studio.ui.wizard.WizardBuilder.newWizard;
import static org.bonitasoft.studio.ui.wizard.WizardPageBuilder.newPage;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.importer.bos.status.BosImportStatusDialogHandler;
import org.bonitasoft.studio.importer.bos.wizard.ImportBosArchiveControlSupplier;
import org.bonitasoft.studio.importer.ui.dialog.SkippableProgressMonitorJobsDialog;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;

public class ImportBosHandler {

    @Execute
    public void execute() {
        final ImportBosArchiveControlSupplier bosArchiveControlSupplier = newImportBosArchiveControlSupplier();
        if (newWizard()
                .withTitle(Messages.importBosArchiveTitle)
                .havingPage(newPage()
                        .withTitle(Messages.importFileTitle)
                        .withDescription(
                                Messages.bind(Messages.importFileDescription, new Object[] { bonitaStudioModuleName }))
                        .withControl(bosArchiveControlSupplier))
                .open(Display.getDefault().getActiveShell(), Messages.importButtonLabel) == Dialog.OK) {
            final File selectedFile = new File(bosArchiveControlSupplier.getFilePath());
            final SkippableProgressMonitorJobsDialog progressManager = new SkippableProgressMonitorJobsDialog(
                    Display.getDefault().getActiveShell());
            final ImportBosArchiveOperation operation = new ImportBosArchiveOperation(selectedFile, progressManager,
                    bosArchiveControlSupplier.getArchiveModel());
            try {
                progressManager.run(true, false, operation);
            } catch (final InvocationTargetException | InterruptedException e) {
                final Throwable t = e instanceof InvocationTargetException
                        ? ((InvocationTargetException) e).getTargetException() : e;
                BonitaStudioLog.error("Import has failed for file " + selectedFile.getName(), e);
                String message = Messages.errorWhileImporting_message;
                if (t != null && !isNullOrEmpty(t.getMessage())) {
                    message = t.getMessage();
                }
                new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.errorWhileImporting_title, message, e)
                        .open();
            }
            operation.openFilesToOpen();
            PlatformUtil.openIntroIfNoOtherEditorOpen();
            Display.getDefault().asyncExec(() -> {
                new BosImportStatusDialogHandler(operation.getStatus(),
                        RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class))
                                .open(Display.getDefault().getActiveShell());
            });
        }
    }

    private ImportBosArchiveControlSupplier newImportBosArchiveControlSupplier() {
        return new ImportBosArchiveControlSupplier();
    }

}
