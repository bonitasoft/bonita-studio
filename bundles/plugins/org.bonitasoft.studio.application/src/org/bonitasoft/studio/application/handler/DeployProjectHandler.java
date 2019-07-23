/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.handler;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Named;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.operation.DeployProjectOperation;
import org.bonitasoft.studio.application.operation.ValidateProjectOperation;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.importer.ui.dialog.SkippableProgressMonitorJobsDialog;
import org.bonitasoft.studio.ui.dialog.MultiStatusDialog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class DeployProjectHandler {

    @Execute
    public void buildProject(@Named(IServiceConstants.ACTIVE_SHELL) Shell activeShell, RepositoryAccessor repositoryAccessor)
            throws InvocationTargetException, InterruptedException {
        IStatus validationStatus = performValidation(activeShell, repositoryAccessor);
        if (validationStatus.isOK()) {
            performDeploy(activeShell, repositoryAccessor);
        } else {
            if (validationStatus instanceof MultiStatus) {
                new MultiStatusDialog(activeShell, Messages.validationErrorTitle, Messages.validationError,
                        new String[] { IDialogConstants.OK_LABEL }, (MultiStatus) validationStatus).open();
            } else {
                MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.validationErrorTitle,
                        String.format("%s\n\n%s", Messages.validationError, validationStatus.getMessage()));
            }
        }
    }

    private void performDeploy(Shell activeShell, RepositoryAccessor repositoryAccessor)
            throws InterruptedException, InvocationTargetException {
        DeployProjectOperation operation = new DeployProjectOperation(repositoryAccessor);
        PlatformUI.getWorkbench().getProgressService().run(true, false, operation);
        if (!operation.getStatus().isOK()) {
            MessageDialog.openError(activeShell, Messages.deployErrorTitle, operation.getStatus().getMessage());
            BonitaStudioLog.error(operation.getStatus().getMessage(), operation.getStatus().getException());
        }
    }

    private IStatus performValidation(Shell activeShell, RepositoryAccessor repositoryAccessor)
            throws InvocationTargetException, InterruptedException {
        SkippableProgressMonitorJobsDialog dialog = new SkippableProgressMonitorJobsDialog(activeShell);
        dialog.canBeSkipped();
        ValidateProjectOperation operation = new ValidateProjectOperation(repositoryAccessor);
        dialog.run(true, false, operation);
        return operation.getStatus();
    }

}
