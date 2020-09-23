/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.wizard;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.team.operations.ConnectoToRepositoryOperation;
import org.bonitasoft.studio.team.operations.MigrateRepositoryOperation;
import org.bonitasoft.studio.team.repository.Repository;
import org.bonitasoft.studio.team.ui.wizard.page.ConnectToRepositoryWizardPage;
import org.bonitasoft.studio.team.ui.wizard.page.ListWorkspacesInRepositoryWizardPage;
import org.bonitasoft.studio.team.ui.wizard.page.MigrateRepositoryWizardPage;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.team.svn.core.connector.SVNConnectorException;
import org.eclipse.team.svn.core.resource.IRepositoryLocation;
import org.eclipse.team.svn.core.resource.IRepositoryResource;

/**
 * @author Baptiste Mesta
 */
public class ConnectToRepositoryWizard extends Wizard {

    private ConnectToRepositoryWizardPage page;
    private ListWorkspacesInRepositoryWizardPage listArtifactPage;
    private MigrateRepositoryWizardPage migrationPage;
    private final ConnectionInfo connectionInfo;
    private final RemoteRepositoryFinder remoteRepositoryFinder;

    public ConnectToRepositoryWizard() {
        connectionInfo = new ConnectionInfo();
        remoteRepositoryFinder = new RemoteRepositoryFinder(connectionInfo);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {
        setNeedsProgressMonitor(true);
        page = new ConnectToRepositoryWizardPage(connectionInfo);
        addPage(page);
        listArtifactPage = new ListWorkspacesInRepositoryWizardPage(remoteRepositoryFinder);
        addPage(listArtifactPage);
        migrationPage = new MigrateRepositoryWizardPage();
        addPage(migrationPage);
    }

    public IRepositoryResource getSelectedWorkspace() {
        return listArtifactPage.getSelectedWorkspace();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        final IRepositoryResource selectedWorkspace = listArtifactPage.getSelectedWorkspace();
        connectionInfo.saveHistory();
        if (getContainer().getCurrentPage() instanceof ListWorkspacesInRepositoryWizardPage) {
            return connect(selectedWorkspace, remoteRepositoryFinder.getLocation());
        } else {
            return migrate(selectedWorkspace, remoteRepositoryFinder.getLocation());
        }

    }

    private boolean migrate(final IRepositoryResource selectedWorkspace, final IRepositoryLocation repoLocation) {
        final String sourceVersion = listArtifactPage.getWorkspaceVersion(selectedWorkspace);
        final MigrateRepositoryOperation op = new MigrateRepositoryOperation(selectedWorkspace, repoLocation, sourceVersion);
        try {
            getContainer().run(true, false, op);
        } catch (final Exception e1) {
            BonitaStudioLog.error(e1);
            MessageDialog.openError(Display.getCurrent().getActiveShell(), "Workspace Migration Error", e1.getMessage());
            return false;
        }
        if (!op.getStatus().isOK()) {
            MessageDialog.openError(Display.getCurrent().getActiveShell(), "Workspace Migration Error", op.getStatus().getMessage());
            return false;
        }
        return true;
    }

    private boolean connect(final IRepositoryResource selectedWorkspace, final IRepositoryLocation repoLocation) {
        final ConnectoToRepositoryOperation connectToRepositoryOperation = new ConnectoToRepositoryOperation(selectedWorkspace, repoLocation);
        try {
            getContainer().run(true, false, connectToRepositoryOperation);
        } catch (final InvocationTargetException e) {
            BonitaStudioLog.error(e);
            return false;
        } catch (final InterruptedException e) {
            BonitaStudioLog.error(e);
            return false;
        }
        final IRepository currentRepository = RepositoryManager.getInstance().getCurrentRepository();
        if (currentRepository instanceof Repository) {
            ((Repository) currentRepository).startUpdateJob();
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.wizard.Wizard#createPageControls(org.eclipse.swt.widgets
     * .Composite)
     */
    @Override
    public void createPageControls(final Composite pageContainer) {
        super.createPageControls(pageContainer);
        setWindowTitle(Messages.team_checkOurPorject);
    }

    public boolean nextPressed() {
        if (page.equals(((WizardDialog) getContainer()).getCurrentPage())) {
            final IRunnableWithProgress runnable = new IRunnableWithProgress() {

                /*
                 * (non-Javadoc)
                 * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
                 */
                @Override
                public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    monitor.beginTask(Messages.team_connectToRepo, 11);
                    if (remoteRepositoryFinder.isRepositoryValid()) {
                        Display.getDefault().syncExec(() -> page.setErrorMessage(null));
                        monitor.setTaskName(Messages.team_connectToRepo);
                        monitor.worked(1);
                        try {
                            remoteRepositoryFinder.execute(monitor);
                            listArtifactPage.setRemoteRepositoryFinder(remoteRepositoryFinder);
                        } catch (final SVNConnectorException e) {
                            throw new InvocationTargetException(e);
                        }
                        monitor.worked(1);
                    } else {
                        Display.getDefault().syncExec(()->page.setErrorMessage(Messages.team_cantconnect));
                    }
                }

            };
            try {
                getContainer().run(true, false, runnable);
            } catch (final InvocationTargetException | InterruptedException e) {
                BonitaStudioLog.error(e);
                return false;
            }
            listArtifactPage.updateInput();
        }
        return page.getErrorMessage() == null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#canFinish()
     */
    @Override
    public boolean canFinish() {
        if (getContainer().getCurrentPage() instanceof MigrateRepositoryWizardPage) {
            return true;
        }
        return listArtifactPage.isPageComplete();
    }

    public IRepositoryLocation getRepositoryLocation() {
        return remoteRepositoryFinder.getLocation();
    }

    public String getSelectedWorkspaceVersion() {
        return listArtifactPage.getWorkspaceVersion(getSelectedWorkspace());
    }
}
