/**
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.engine.ui.contributionItem;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.jface.ValidationDialog;
import org.bonitasoft.studio.diagram.custom.contributionItem.ListProcessContributionItem;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.engine.operation.ExportBarOperation;
import org.bonitasoft.studio.engine.operation.ProcessValidationOperation;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.ui.dialog.MultiStatusDialog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class BuildProcessContributionItem extends ListProcessContributionItem {

    @Override
    protected Listener createSelectionListener(AbstractProcess process) {
        return e -> {
            getPath(Display.getDefault().getActiveShell()).ifPresent(path -> {
                if (validateBeforExport(process)) {
                    ExportBarOperation exportBarOperation = getExportOperation();
                    exportBarOperation.addProcessToDeploy(process);
                    exportBarOperation.setTargetFolder(path);
                    try {
                        PlatformUI.getWorkbench().getProgressService().run(true, false, exportBarOperation);
                        displayOperatonStatus(exportBarOperation, path, process.getName());
                    } catch (InvocationTargetException | InterruptedException e1) {
                        throw new RuntimeException(
                                String.format("An error occurred while building bar for process %s", process.getName()),
                                e1);
                    }
                }
            });

        };
    }

    private boolean validateBeforExport(AbstractProcess process) {
        ProcessValidationOperation processValidationOperation = new ProcessValidationOperation(process);
        processValidationOperation.run();
        IStatus status = processValidationOperation.getStatus();
        if (status.getSeverity() == IStatus.ERROR || status.getSeverity() == IStatus.WARNING) {
            if (!FileActionDialog.getDisablePopup()) {
                String generalMessage = status.getSeverity() == IStatus.ERROR
                        ? Messages.errorValidationInDiagramToExport
                        : Messages.warningValidationInDiagramToExport;
                String errorMessage = String.format("%s\n%s%s", generalMessage, process.getName(),
                        Messages.errorValidationContinueAnywayMessage);
                return new ValidationDialog(Display.getDefault().getActiveShell(),
                        Messages.validationFailedTitle, errorMessage,
                        ValidationDialog.YES_NO).open() != ValidationDialog.NO;
            }
        }
        return true;
    }

    protected ExportBarOperation getExportOperation() {
        return new ExportBarOperation();
    }

    private void displayOperatonStatus(ExportBarOperation exportBarOperation, String path, String processName) {
        IStatus status = exportBarOperation.getStatus();
        Shell shell = Display.getDefault().getActiveShell();
        if (status instanceof MultiStatus && status.getSeverity() == IStatus.ERROR) {
            new MultiStatusDialog(Display.getDefault().getActiveShell(),
                    Messages.buildFailedTitle,
                    String.format(Messages.invalidConfigurationForEnv, exportBarOperation.getConfigurationId()),
                    new String[] { IDialogConstants.CLOSE_LABEL },
                    (MultiStatus) status)
                            .open();
        } else {
            switch (status.getSeverity()) {
                case IStatus.CANCEL:
                    break;
                case IStatus.OK:
                    MessageDialog.openInformation(shell, Messages.buildDoneTitle,
                            String.format(Messages.buildDoneMessage, processName, path));
                    break;
                case IStatus.WARNING:
                    MessageDialog.openWarning(shell, Messages.buildDoneTitle, status.getMessage());
                    break;
                default:
                    MessageDialog.openError(shell, Messages.buildFailedTitle, status.getMessage());
            }

        }

    }

    private Optional<String> getPath(Shell shell) {
        final DirectoryDialog directoryDialog = new DirectoryDialog(shell, SWT.SAVE | SWT.SHEET);
        directoryDialog.setMessage(Messages.selectDestinationTitle);
        return Optional.ofNullable(directoryDialog.open());
    }

}
