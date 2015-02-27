/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.importer.handler;

import org.bonitasoft.studio.common.ProcessesValidationAction;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.repository.operation.BosArchiveImportStatus;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.importer.i18n.Messages;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;


/**
 * @author Romain Bioteau
 *
 */
public class ImportStatusDialogHandler {

    private final IStatus importStatus;
    private final DiagramRepositoryStore store;

    public ImportStatusDialogHandler(final IStatus importStatus, final DiagramRepositoryStore store) {
        this.importStatus = importStatus;
        this.store = store;
    }

    public void open(final Shell parentShell) {
        //Dot not display during tests
        if (!FileActionDialog.getDisablePopup()) {
            if (importStatus.isOK()) {
                MessageDialog.openInformation(parentShell, org.bonitasoft.studio.importer.i18n.Messages.importResultTitle, getDialogMessage(importStatus));
            } else {
                final ImportErrorMessageDialog messageDialog = new ImportErrorMessageDialog(parentShell, getDialogMessage(importStatus),
                        importStatus instanceof BosArchiveImportStatus);
                final int result = messageDialog.open();
                if (result == IDialogConstants.OPEN_ID) {
                    openDiagrams();
                }
            }
        }
    }

    protected void openDiagrams() {
        final BosArchiveImportStatus bosArchiveStatus = (BosArchiveImportStatus) importStatus;
        for (final AbstractProcess process : bosArchiveStatus.getProcessesWithErrors()) {
            final DiagramFileStore diagramFileStore = store.getDiagram(process.getName(), process.getVersion());
            if (diagramFileStore != null && !diagramFileStore.isOpened()) {
                diagramFileStore.open();
            }
        }
        ProcessesValidationAction.showValidationPart();
    }

    protected String getDialogMessage(final IStatus status){
        if (status.isOK()) {
            return org.bonitasoft.studio.importer.i18n.Messages.importSucessfulMessage;
        }
        if (status instanceof MultiStatus) {
            final StringBuilder sb = new StringBuilder();
            if (status instanceof BosArchiveImportStatus) {
                sb.append(Messages.processesWithErrorAfterImport);
                sb.append(SWT.CR);
            }
            for (final IStatus childStatus : status.getChildren()) {
                if (!childStatus.isOK()) {
                    sb.append(childStatus.getMessage());
                    sb.append(SWT.CR);
                }
            }
            if (status instanceof BosArchiveImportStatus) {
                sb.append(Messages.openDiagramWithErrors);
            }
            return sb.toString();
        }
        return status.getMessage();
    }

}
