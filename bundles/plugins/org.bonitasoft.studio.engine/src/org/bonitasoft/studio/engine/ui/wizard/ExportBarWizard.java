/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.engine.ui.wizard;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.engine.operation.ExportBarOperation;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.ui.dialog.MultiStatusDialog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.internal.WorkbenchPlugin;

/**
 * @author Romain Bioteau
 */
public class ExportBarWizard extends Wizard {

    protected ExportBarWizardPage page;

    public ExportBarWizard() {
        setDefaultPageImageDescriptor(Pics.getWizban());
        setNeedsProgressMonitor(true);
        IDialogSettings workbenchSettings = WorkbenchPlugin.getDefault()
                .getDialogSettings();
        IDialogSettings wizardSettings = workbenchSettings
                .getSection("ExportBarWizard"); //$NON-NLS-1$
        if (wizardSettings == null) {
            wizardSettings = workbenchSettings.addNewSection("ExportBarWizard"); //$NON-NLS-1$
        }
        setDialogSettings(wizardSettings);
        setWindowTitle(Messages.buildTitle);
    }

    @Override
    public void addPages() {
        page = new ExportBarWizardPage();
        addPage(page);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        try {
            IStatus status = page.finish(createExportBarOperation());
            if (status.getSeverity() == IStatus.CANCEL) {
                return false;
            }
            if (statusContainsError(status)) {
                if (status instanceof MultiStatus) {
                    new MultiStatusDialog(Display.getDefault().getActiveShell(),
                            Messages.buildFailedTitle,
                            String.format(Messages.invalidConfigurationForEnv, page.getConfigurationId()),
                            new String[] { IDialogConstants.CLOSE_LABEL },
                            (MultiStatus) status)
                                    .open();
                } else {
                    String message = status.getMessage();
                    if (message == null || message.isEmpty()) {
                        message = Messages.exportErrorOccuredMsg;
                    }
                    new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.exportErrorOccured, message,
                            status, IStatus.ERROR).open();
                }
            } else {
                MessageDialog.openInformation(Display.getDefault().getActiveShell(), Messages.exportSuccessTitle,
                        Messages.exportSuccessMsg);
            }
            return !statusContainsError(status);
        } catch (InvocationTargetException e) {
            BonitaStudioLog.error(e);
        } catch (InterruptedException e) {
            BonitaStudioLog.error(e);
        }
        return false;
    }

    protected ExportBarOperation createExportBarOperation() {
        return new ExportBarOperation();
    }

    private boolean statusContainsError(IStatus validationStatus) {
        if (validationStatus != null) {
            if (validationStatus.getChildren().length > 0) {
                for (IStatus s : validationStatus.getChildren()) {
                    if (s.getSeverity() == IStatus.WARNING || s.getSeverity() == IStatus.ERROR) {
                        return true;
                    }
                }
            } else {
                return validationStatus.getSeverity() == IStatus.WARNING
                        || validationStatus.getSeverity() == IStatus.ERROR;
            }
        }
        return false;
    }

}
