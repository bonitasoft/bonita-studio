/**
 * Copyright (C) 2009-2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connectors.handler;

import java.io.File;

import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.repository.ImportConnectorArchiveOperation;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

public class ImportConnectorHandler extends AbstractHandler {

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        try {
            final FileDialog fileDialog = new FileDialog(Display.getDefault().getActiveShell(), SWT.OPEN);
            fileDialog.setFilterExtensions(new String[] { "*.zip" });
            fileDialog.setText(getDialogTitle());
            final String fileName = fileDialog.open();
            if (fileName != null) {
                final IProgressService service = PlatformUI.getWorkbench().getProgressService();
                final ImportConnectorArchiveOperation importOp = newImportOperation();
                importOp.setFile(new File(fileName));
                service.run(true, false, importOp);
                final IStatus status = importOp.getStatus();
                switch (status.getSeverity()) {
                    case IStatus.OK:
                        MessageDialog.openInformation(Display.getDefault().getActiveShell(), getImportSuccessTitle(), getImportSuccessMessage());
                        break;
                    case IStatus.WARNING:
                        MessageDialog.openWarning(Display.getDefault().getActiveShell(), getImportSuccessTitle(), status.getMessage());
                        break;
                    case IStatus.ERROR:
                        MessageDialog.openError(Display.getDefault().getActiveShell(), getFailedImportTitle(),
                                Messages.bind(getFailedImportMessage(), status.getMessage()));
                        break;
                    default:
                        break;
                }
            }
            return null;
        } catch (final Exception ex) {
            throw new ExecutionException(ex.getMessage(), ex);
        }
    }

    protected ImportConnectorArchiveOperation newImportOperation() {
        return new ImportConnectorArchiveOperation();
    }

    protected String getFailedImportMessage() {
        return Messages.importFailedMsg;
    }

    protected String getFailedImportTitle() {
        return Messages.importFailedTitle;
    }

    protected String getImportSuccessMessage() {
        return Messages.importSuccessfulMsg;
    }

    protected String getImportSuccessTitle() {
        return Messages.importSuccessfulTitle;
    }

    protected String getDialogTitle() {
        return Messages.importConnectorArchive;
    }

}
