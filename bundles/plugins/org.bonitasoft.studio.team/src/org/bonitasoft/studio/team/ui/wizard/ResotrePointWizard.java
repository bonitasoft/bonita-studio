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

import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.team.TeamPlugin;
import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.team.operations.CreateWorkspaceBackupOperation;
import org.bonitasoft.studio.team.operations.RestoreRepositoryOperation;
import org.bonitasoft.studio.team.repository.Repository;
import org.bonitasoft.studio.team.ui.wizard.page.ChooseRestoreActionWizardPage;
import org.bonitasoft.studio.team.ui.wizard.page.ChooseRestorePointWizardPage;
import org.bonitasoft.studio.team.ui.wizard.page.CreateRestorePointWizardPage;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;
import org.eclipse.team.svn.core.resource.IRepositoryContainer;

/**
 * @author Romain Bioteau
 */
public class ResotrePointWizard extends Wizard {

    private ChooseRestoreActionWizardPage chooseRestoreActionWizardPage;
    private CreateRestorePointWizardPage chooseLocalRepoWizardPage;
    private ChooseRestorePointWizardPage chooseRestorePointWizardPage;
    private CreateRestorePointWizardPage chooseLocalRepoWithCommentWizardPage;

    public ResotrePointWizard() {
        setNeedsProgressMonitor(true);
        setWindowTitle(Messages.restorePointsWindowTitle);
    }

    @Override
    public void addPages() {
        chooseRestoreActionWizardPage = new ChooseRestoreActionWizardPage();
        chooseLocalRepoWithCommentWizardPage = new CreateRestorePointWizardPage();
        chooseRestorePointWizardPage = new ChooseRestorePointWizardPage(CommonRepositoryPlugin.getCurrentRepository());
        addPage(chooseRestoreActionWizardPage);
        addPage(chooseRestorePointWizardPage);
        addPage(chooseLocalRepoWithCommentWizardPage);
    }

    @Override
    public boolean canFinish() {
        return chooseLocalRepoWithCommentWizardPage.isPageComplete() || chooseRestorePointWizardPage.isPageComplete();
    }

    @Override
    public IWizardPage getNextPage(final IWizardPage page) {
        if (page.equals(chooseRestoreActionWizardPage)) {
            if (((ChooseRestoreActionWizardPage) page).isBackupAction()) {
                return chooseLocalRepoWithCommentWizardPage;
            } else {
                return chooseRestorePointWizardPage;
            }
        } else if (page.equals(chooseLocalRepoWithCommentWizardPage)) {
            return null;
        } else if (page.equals(chooseLocalRepoWizardPage)) {
            return chooseRestorePointWizardPage;
        }
        return super.getNextPage(page);
    }

    public String getSelectedWorkspace() {
        return CommonRepositoryPlugin.getCurrentRepository();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        if (chooseRestoreActionWizardPage.isBackupAction()) {
            return backup();
        } else {
            return restore();
        }
    }

    private boolean restore() {
        final IRepositoryContainer remoteWorkspace = chooseRestorePointWizardPage.getRemoteWorkspaceResource();
        final String workspace = chooseRestorePointWizardPage.getSelectedWorkspace();
        if (MessageDialog.openQuestion(getShell(), Messages.restoreWarningTitle,
                Messages.bind(Messages.restoreWarningMsg, workspace))) {
            final RestoreRepositoryOperation restoreRepositoryOperation = new RestoreRepositoryOperation(remoteWorkspace, workspace);
            try {
                getContainer().run(true, false, restoreRepositoryOperation);
            } catch (final InvocationTargetException e) {
                BonitaStudioLog.error(e);
                new BonitaErrorDialog(getShell(), Messages.backupFailTitle, Messages.backupFailMsg, new Status(IStatus.ERROR,
                        TeamPlugin.PLUGIN_ID, e.getMessage(), e), IStatus.ERROR).open();
                return false;
            } catch (final InterruptedException e) {
                BonitaStudioLog.error(e);
                new BonitaErrorDialog(getShell(), Messages.backupFailTitle, Messages.backupFailMsg, new Status(IStatus.ERROR,
                        TeamPlugin.PLUGIN_ID, e.getMessage(), e), IStatus.ERROR).open();
                return false;
            }
            MessageDialog.openInformation(getShell(), Messages.restoreSuccessTitle,
                    Messages.bind(Messages.restoreSuccessMsg, workspace));
            return true;
        }
        return false;
    }

    private boolean backup() {
        final String workspace = chooseLocalRepoWithCommentWizardPage.getSelectedWorkspace();
        final String comment = chooseLocalRepoWithCommentWizardPage.getComments();
        final CreateWorkspaceBackupOperation createWorkspaceBackupOperation = new CreateWorkspaceBackupOperation(((Repository) RepositoryManager
                .getInstance().getCurrentRepository()).getLocationForRepository(), workspace, comment);
        try {
            getContainer().run(true, false, createWorkspaceBackupOperation);
        } catch (final InvocationTargetException e) {
            BonitaStudioLog.error(e);
            new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.backupFailTitle, Messages.backupFailMsg, new Status(IStatus.ERROR,
                    TeamPlugin.PLUGIN_ID, e.getMessage(), e), IStatus.ERROR).open();
            return false;
        } catch (final InterruptedException e) {
            BonitaStudioLog.error(e);
            new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.backupFailTitle, Messages.backupFailMsg, new Status(IStatus.ERROR,
                    TeamPlugin.PLUGIN_ID, e.getMessage(), e), IStatus.ERROR).open();
            return false;
        }

        MessageDialog.openInformation(Display.getDefault().getActiveShell(), Messages.backupSuccessTitle, Messages.bind(Messages.backupSuccessMsg, workspace));
        return true;
    }

}
