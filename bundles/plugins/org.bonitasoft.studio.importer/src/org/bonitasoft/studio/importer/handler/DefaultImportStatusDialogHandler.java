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
package org.bonitasoft.studio.importer.handler;

import java.util.Optional;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Romain Bioteau
 */
public class DefaultImportStatusDialogHandler implements ImportStatusDialogHandler {

    protected final IStatus importStatus;
    protected Optional<String> customSuccessMessage = Optional.empty();
    protected Optional<String> customErrorMessage = Optional.empty();

    public DefaultImportStatusDialogHandler(final IStatus importStatus) {
        this.importStatus = importStatus;
    }

    public DefaultImportStatusDialogHandler(final IStatus importStatus, String customSuccessMessage,
            String customErrorMessage) {
        this.importStatus = importStatus;
        this.customSuccessMessage = Optional.ofNullable(customSuccessMessage);
        this.customErrorMessage = Optional.ofNullable(customErrorMessage);
    }

    @Override
    public void open(final Shell parentShell) {
        if (importStatus.getSeverity() == IStatus.WARNING
                && (importStatus.getChildren() == null || importStatus.getChildren().length == 0)) {
            MessageDialog.openWarning(parentShell, org.bonitasoft.studio.importer.i18n.Messages.importResultTitle,
                    importStatus.getMessage());
        } else {
            switch (importStatus.getSeverity()) {
                case IStatus.OK:
                    MessageDialog.openInformation(parentShell,
                            org.bonitasoft.studio.importer.i18n.Messages.importResultTitle,
                            customSuccessMessage
                                    .orElse(org.bonitasoft.studio.importer.i18n.Messages.importSucessfulMessage));
                    break;
                case IStatus.INFO:
                    openImportStatus(parentShell, customSuccessMessage
                            .orElse(org.bonitasoft.studio.importer.i18n.Messages.importSucessfulMessage));
                    break;
                default:
                    openImportStatus(parentShell,
                            customErrorMessage.orElse(org.bonitasoft.studio.importer.i18n.Messages.importStatusMsg));
                    break;
            }
        }
    }

    protected void openImportStatus(Shell parentShell, String message) {
        new ImportStatusDialog(parentShell, importStatus, message, false).open();
    }

}
