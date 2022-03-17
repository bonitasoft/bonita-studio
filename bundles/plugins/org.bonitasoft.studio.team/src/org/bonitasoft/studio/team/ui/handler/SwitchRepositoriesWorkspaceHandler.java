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
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.team.TeamRepositoryUtil;
import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.team.ui.dialog.SwitchRepositoryDialog;
import org.bonitasoft.studio.ui.notification.BonitaNotificator;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Baptiste Mesta
 */
public class SwitchRepositoriesWorkspaceHandler extends AbstractHandler {

    private final boolean force;

    /**
     * @param force
     */
    public SwitchRepositoriesWorkspaceHandler(final boolean force) {
        this.force = force;
    }

    /**
     * @param force
     */
    public SwitchRepositoriesWorkspaceHandler() {
        force = false;
    }

    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final SwitchRepositoryDialog dialog = new SwitchRepositoryDialog(
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), force);
        if (dialog.open() == IDialogConstants.OK_ID) {
            final IProgressService progressManager = PlatformUI.getWorkbench().getProgressService();
            final IRepository repo = dialog.getRepository();
            if (repo != null) {
                final boolean closed = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .closeAllEditors(true);
                if (closed) {
                    final IRunnableWithProgress runnable = new IRunnableWithProgress() {

                        @Override
                        public void run(final IProgressMonitor monitor)
                                throws InvocationTargetException, InterruptedException {
                            try {
                                TeamRepositoryUtil.switchToRepository(repo.getName(), monitor);
                                Display.getDefault().asyncExec(new Runnable() {

                                    @Override
                                    public void run() {
                                        BonitaNotificator.openInfoNotification(Messages.switchRepositorySuccessful_Title,
                                                NLS.bind(Messages.switchRepositorySuccessful_Message, repo.getName()));
                                    }
                                });
                            } catch (final Exception e) {
                                BonitaStudioLog.error(e);
                            }
                        }
                    };
                    try {
                        progressManager.run(true, false, runnable);
                    } catch (final InvocationTargetException e) {
                        BonitaStudioLog.error(e);
                    } catch (final InterruptedException e) {
                        BonitaStudioLog.error(e);
                    }
                }
            }
        }

        return null;
    }

}
