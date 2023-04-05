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

import java.util.HashMap;

import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.importer.handler.DefaultImportStatusDialogHandler;
import org.bonitasoft.studio.importer.handler.ImportStatusDialog;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.validation.common.operation.RunProcessesValidationOperation;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;


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
    protected int openImportStatus(final Shell parentShell, String message) {
        final ImportStatusDialog messageDialog = new ImportStatusDialog(parentShell, importStatus, message,
                importStatus instanceof BosArchiveImportStatus
                        && !((BosArchiveImportStatus) importStatus).getProcessesWithErrors().isEmpty());
        final int result = messageDialog.open();
        if (importStatus instanceof BosArchiveImportStatus) {
            if (result == IDialogConstants.OPEN_ID) {
                openDiagrams((BosArchiveImportStatus) importStatus);
            } else if (result == IDialogConstants.PROCEED_ID) {
                executeCommand("org.bonitasoft.studio.application.command.deployArtifacts");
            }
        }
        return result;
    }

    private void executeCommand(String command) {
        ECommandService eCommandService = PlatformUI.getWorkbench().getService(ECommandService.class);
        EHandlerService eHandlerService = PlatformUI.getWorkbench().getService(EHandlerService.class);
        ParameterizedCommand parameterizedCommand = eCommandService.createCommand(command, new HashMap<>());
        eHandlerService.executeHandler(parameterizedCommand);
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
