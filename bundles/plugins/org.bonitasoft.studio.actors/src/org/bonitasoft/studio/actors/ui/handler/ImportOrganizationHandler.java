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
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.ui.dialog.MultiStatusDialog;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.databinding.validation.ValidationStatus;
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

public class ImportOrganizationHandler extends AbstractHandler {

    @Execute
    public void execute(RepositoryAccessor repositoryAccessor) throws ExecutionException {
        final OrganizationRepositoryStore organizationStore = repositoryAccessor
                .getRepositoryStore(OrganizationRepositoryStore.class);
        final FileDialog fd = new FileDialog(Display.getDefault().getActiveShell(), SWT.OPEN);
        fd.setFilterExtensions(new String[] { "*.xml" });
        final String filePath = fd.open();
        if (filePath != null) {
            final IProgressService service = PlatformUI.getWorkbench().getProgressService();
            try {
                service.run(false, false, new IRunnableWithProgress() {

                    @Override
                    public void run(final IProgressMonitor monitor) throws InvocationTargetException,
                            InterruptedException {
                        monitor.beginTask(Messages.importingOrganization, IProgressMonitor.UNKNOWN);
                        File file = new File(filePath);
                        try(FileInputStream fis = new FileInputStream(file)) {
                            IStatus status = organizationStore.validate(file.getName(), fis);
                            if(status.getSeverity() == IStatus.ERROR) {
                                MessageDialog.openError(Display.getDefault().getActiveShell(), 
                                        Messages.importOrganizationFailedTitle, 
                                        status.getMessage());
                                return;
                            }
                        } catch (IOException e1) {
                            MessageDialog.openError(Display.getDefault().getActiveShell(), 
                                    Messages.importOrganizationFailedTitle, 
                                    e1.getMessage());
                            return;
                        } 
                        
                        try(FileInputStream fis = new FileInputStream(file)) {
                            final String id = file.getName();
                            FileActionDialog.setThrowExceptionOnCancel(true);
                            final OrganizationFileStore fStore = organizationStore.importInputStream(id, fis);
                            if (fStore != null && fStore.isCorrectlySyntaxed()) {
                                final IStatus status = validateImportedOrganization(fStore);
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
                                if (fStore != null) {
                                    fStore.delete();
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
                            final OrganizationFileStore fStore = organizationStore.getChild(file.getName()
                                    .replace(".xml", "." + OrganizationRepositoryStore.ORGANIZATION_EXT), true);
                            if (fStore != null) {
                                fStore.delete();
                            }
                            new BonitaErrorDialog(Display.getDefault().getActiveShell(),
                                    Messages.importOrganizationFailedTitle, Messages.importOrganizationFailedMessage, e)
                                            .open();
                        } finally {
                            FileActionDialog.setThrowExceptionOnCancel(false);
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
        try {
            return new OrganizationValidator().validate(fileStore.getContent());
        } catch (ReadFileStoreException e) {
            return ValidationStatus.error(e.getMessage());
        }
    }

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        RepositoryAccessor repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
        execute(repositoryAccessor);
        return null;
    }

}
