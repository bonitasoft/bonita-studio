/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.handler;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryNameValidator;
import org.bonitasoft.studio.team.TeamRepositoryUtil;
import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.ui.notification.BonitaNotificator;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

public class CreateNewLocalRepoHandler extends AbstractHandler {

    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        final InputDialog inputDialog = new InputDialog(shell, Messages.createNewLocalRepoDialogTitle,
                Messages.createNewLocalRepoDialogMessage,
                Messages.createNewLocalRepoDialogDefaultName, new RepositoryNameValidator(true));

        if (inputDialog.open() == Window.OK) {
            final boolean closed = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                    .closeAllEditors(true);
            if (closed) {
                final IProgressService progressManager = PlatformUI.getWorkbench().getProgressService();
                final String repoName = inputDialog.getValue();
                final IRunnableWithProgress runnable = new IRunnableWithProgress() {

                    @Override
                    public void run(final IProgressMonitor monitor) throws InvocationTargetException,
                            InterruptedException {
                        TeamRepositoryUtil.createNewLocalRepository(repoName, monitor);
                    }
                };
                try {
                    progressManager.run(true, false, runnable);
                } catch (final InvocationTargetException e) {
                    BonitaStudioLog.error(e);
                } catch (final InterruptedException e) {
                    BonitaStudioLog.error(e);
                }
                BonitaNotificator.openNotification(Messages.switchRepositorySuccessful_Title,
                        Messages.bind(Messages.switchRepositorySuccessful_Message, repoName));
            }
        }

        return null;
    }

}
