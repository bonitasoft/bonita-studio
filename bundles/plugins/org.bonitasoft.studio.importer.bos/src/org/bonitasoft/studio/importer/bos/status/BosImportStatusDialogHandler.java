/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.importer.bos.status;

import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.importer.handler.DefaultImportStatusDialogHandler;
import org.bonitasoft.studio.importer.handler.ImportStatusDialog;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.validation.common.operation.RunProcessesValidationOperation;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Romain Bioteau
 */
public class BosImportStatusDialogHandler extends DefaultImportStatusDialogHandler {

    private final DiagramRepositoryStore store;

    public BosImportStatusDialogHandler(final IStatus importStatus, final DiagramRepositoryStore store) {
        super(importStatus);
        this.store = store;
    }

    public BosImportStatusDialogHandler(final IStatus importStatus, final DiagramRepositoryStore store,
            String successMessage, String errorMessage) {
        super(importStatus, successMessage, errorMessage);
        this.store = store;
    }

    @Override
    protected void openImportStatus(final Shell parentShell, String message) {
        final ImportStatusDialog messageDialog = new ImportStatusDialog(parentShell, importStatus, message,
                importStatus instanceof BosArchiveImportStatus
                        && !((BosArchiveImportStatus) importStatus).getProcessesWithErrors().isEmpty());
        final int result = messageDialog.open();
        if (importStatus instanceof BosArchiveImportStatus && result == IDialogConstants.OPEN_ID) {
            openDiagrams((BosArchiveImportStatus) importStatus);
        }
    }

    protected void openDiagrams(final BosArchiveImportStatus bosArchiveStatus) {
        for (final AbstractProcess process : bosArchiveStatus.getProcessesWithErrors()) {
            final DiagramFileStore diagramFileStore = store.getDiagram(process.getName(), process.getVersion());
            if (diagramFileStore != null && !diagramFileStore.isOpened()) {
                diagramFileStore.open();
            }
        }
        RunProcessesValidationOperation.showValidationPart();
    }

}
