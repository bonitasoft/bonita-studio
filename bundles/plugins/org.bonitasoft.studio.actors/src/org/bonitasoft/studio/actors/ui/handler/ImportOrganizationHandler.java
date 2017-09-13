/**
 * Copyright (C) 2012-2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.actors.ui.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CancellationException;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.repository.OrganizationFileStore;
import org.bonitasoft.studio.actors.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.actors.validator.OrganizationValidator;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.ui.dialog.MultiStatusDialog;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

public class ImportOrganizationHandler {

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    @Execute
    public void execute(RepositoryAccessor repositoryAccessor) throws ExecutionException {
        final OrganizationRepositoryStore organizationStore = repositoryAccessor
                .getRepositoryStore(OrganizationRepositoryStore.class);
        final FileDialog fd = new FileDialog(Display.getDefault().getActiveShell(), SWT.OPEN);
        fd.setFilterExtensions(new String[] { "*.xml;*.zip" });
        final String filePath = fd.open();
        if (filePath != null) {
            final IProgressService service = PlatformUI.getWorkbench().getProgressService();
            try {
                service.run(false, false, new IRunnableWithProgress() {

                    @Override
                    public void run(final IProgressMonitor monitor) throws InvocationTargetException,
                            InterruptedException {
                        monitor.beginTask(Messages.importingOrganization, IProgressMonitor.UNKNOWN);
                        FileInputStream fis = null;
                        try {
                            fis = new FileInputStream(filePath);
                            final String id = new File(filePath).getName();
                            FileActionDialog.setThrowExceptionOnCancel(true);
                            final OrganizationFileStore file = organizationStore.importInputStream(id, fis);

                            if (file != null && file.isCorrectlySyntaxed()) {
                                final IStatus status = validateImportedOrganization(file);
                                if (!status.isOK()) {
                                    new MultiStatusDialog(Display.getDefault().getActiveShell(),
                                            Messages.importOrganizationWithWarningTitle,
                                            Messages.importOrganizationWithWarningMessage,
                                            MessageDialog.WARNING,
                                            new String[] { IDialogConstants.CLOSE_LABEL },
                                            (MultiStatus) status).open();
                                } else {
                                    MessageDialog.openInformation(Display.getDefault().getActiveShell(),
                                            Messages.importOrganizationSuccessfullTitle,
                                            Messages.importOrganizationSuccessfullMessage);
                                }

                            } else {
                                fis.close();
                                if (file != null) {
                                    file.delete();
                                }
                                MessageDialog.openError(Display.getDefault().getActiveShell(),
                                        Messages.importOrganizationFailedTitle, Messages.importOrganizationFailedMessage);
                            }
                        } catch (final CancellationException ce) {
                            String message = Messages.importOrganizationCancelledMessage;
                            if (ce.getMessage() != null) {
                                message = Messages.importOrganizationCancelledMessage + ":\n" + ce.getMessage();
                            }
                            MessageDialog.openWarning(Display.getDefault().getActiveShell(),
                                    Messages.importOrganizationCancelledTitle, message);
                        } catch (final Exception e) {
                            BonitaStudioLog.error(e);
                            final OrganizationFileStore file = organizationStore.getChild(new File(filePath).getName()
                                    .replace(".xml", "." + OrganizationRepositoryStore.ORGANIZATION_EXT));
                            if (file != null) {
                                file.delete();
                            }
                            new BonitaErrorDialog(Display.getDefault().getActiveShell(),
                                    Messages.importOrganizationFailedTitle, Messages.importOrganizationFailedMessage, e)
                                            .open();
                        } finally {
                            FileActionDialog.setThrowExceptionOnCancel(false);
                            if (fis != null) {
                                try {
                                    fis.close();
                                } catch (final IOException e) {

                                }
                            }
                        }

                    }

                });
            } catch (final Exception e) {
                BonitaStudioLog.error(e);
            } finally {
                FileActionDialog.setThrowExceptionOnCancel(false);
            }

        }
    }

    protected IStatus validateImportedOrganization(final OrganizationFileStore fileStore) {
        return new OrganizationValidator().validate(fileStore.getContent());
    }
}
