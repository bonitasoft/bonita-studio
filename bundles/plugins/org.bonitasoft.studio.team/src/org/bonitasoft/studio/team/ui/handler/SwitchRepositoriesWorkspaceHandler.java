/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.handler;

import static org.bonitasoft.studio.common.Messages.bonitaStudioModuleName;
import static org.bonitasoft.studio.common.Messages.bosProductName;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.team.TeamRepositoryUtil;
import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.team.repository.Repository;
import org.bonitasoft.studio.team.ui.dialog.SwitchRepositoryDialog;
import org.bonitasoft.studio.ui.notification.BonitaNotificator;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.team.svn.core.connector.SVNConnectorException;
import org.eclipse.team.svn.core.resource.IRepositoryContainer;
import org.eclipse.team.svn.core.resource.IRepositoryResource;
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
                                if (repo.isShared()) {
                                    final String localWorkspaceVersion = repo.getVersion();
                                    final IRepositoryContainer remoteWorkspace = TeamRepositoryUtil
                                            .getRemoteRepository((Repository) repo);
                                    if (remoteWorkspace != null) {
                                        final boolean badLocalVersion = !localWorkspaceVersion
                                                .equals(ProjectUtil.NEW_WORKSPACE)
                                                && !ProductVersion.sameMinorVersion(localWorkspaceVersion);
                                        final boolean badRemoteVersion = checkRemoteVersion(remoteWorkspace);
                                        if (badLocalVersion && badRemoteVersion) {
                                            MessageDialog.openWarning(
                                                    Display.getDefault().getActiveShell(),
                                                    Messages.badWorkspaceVersionTitle,
                                                    Messages.bind(Messages.badWorkspaceVersionMessage,
                                                            new Object[] { repo.getName(), localWorkspaceVersion,
                                                                    ProductVersion.CURRENT_VERSION, bonitaStudioModuleName,
                                                                    bosProductName }));

                                            IRepository fallbackRepo = RepositoryManager.getInstance().getAllRepositories()
                                                    .stream()
                                                    .filter(repo -> !repo.isShared())
                                                    .findFirst()
                                                    .orElse(null);
                                            if (fallbackRepo != null) {
                                                TeamRepositoryUtil.switchToRepository(fallbackRepo.getName(), monitor);
                                            } else {
                                                IRepository defaultRepo = RepositoryManager.getInstance().getRepository(
                                                        org.bonitasoft.studio.common.repository.Messages.defaultRepositoryName);
                                                String name = defaultRepo.getName();
                                                if (defaultRepo.exists()) {
                                                    name = NamingUtils.generateNewName(RepositoryManager.getInstance()
                                                            .getAllRepositories()
                                                            .stream().map(IRepository::getName).collect(Collectors.toSet()),
                                                            name, 1);
                                                }
                                                TeamRepositoryUtil.switchToRepository(name, monitor);
                                            }
                                            return;
                                        } else if (!badRemoteVersion) {
                                            final long timestamp = TeamRepositoryUtil.getConnectionDate(repo.getName());
                                            final long remoteTimestamp = TeamRepositoryUtil
                                                    .getLastRestoreDate(repo.getName());
                                            if (remoteTimestamp > 0 && timestamp > 0 && timestamp < remoteTimestamp) {
                                                Display.getDefault().syncExec(new Runnable() {

                                                    @Override
                                                    public void run() {
                                                        final Shell shell = Display.getDefault().getActiveShell() != null
                                                                ? Display.getDefault()
                                                                        .getActiveShell()
                                                                : Display.getCurrent().getActiveShell();
                                                        MessageDialog.openWarning(shell, Messages.reconnectWorkspaceTitle,
                                                                Messages.bind(Messages.reconnectWorkspaceMsg,
                                                                        repo.getName()));
                                                    }
                                                });
                                                repo.delete(monitor);
                                                return;
                                            }
                                        }
                                    }
                                }

                                Display.getDefault().asyncExec(new Runnable() {

                                    @Override
                                    public void run() {
                                        Shell shell = Display.getDefault().getActiveShell();
                                        if (shell == null) {
                                            shell = Display.getCurrent().getActiveShell();
                                        }
                                        BonitaNotificator.openNotification(Messages.switchRepositorySuccessful_Title,
                                                Messages.bind(Messages.switchRepositorySuccessful_Message, repo.getName()));
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

    private boolean checkRemoteVersion(final IRepositoryContainer container) throws SVNConnectorException {
        IRepositoryContainer trunk = null;
        for (final IRepositoryResource res : container.getChildren()) {
            if (res.getName().equals("trunk")) {
                trunk = (IRepositoryContainer) res;
            }
        }
        return !ProductVersion.sameMinorVersion(TeamRepositoryUtil.getRemoteRepositoryVersion(trunk));
    }
}
