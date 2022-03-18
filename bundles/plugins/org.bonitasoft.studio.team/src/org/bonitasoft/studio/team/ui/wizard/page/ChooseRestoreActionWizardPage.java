/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.wizard.page;

import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.team.TeamRepositoryUtil;
import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.team.repository.Repository;
import org.bonitasoft.studio.team.ui.dialog.DynamicLabelWizardDialog;
import org.bonitasoft.studio.team.ui.provider.RestorePointContentProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.team.svn.core.connector.SVNConnectorException;
import org.eclipse.team.svn.core.resource.IRepositoryContainer;

/**
 * @author Romain Bioteau
 */
public class ChooseRestoreActionWizardPage extends WizardPage {

    private Composite radioComposite;
    private Button backupWorkspaceRadio;
    private Button restoreWorkspaceRadio;

    public ChooseRestoreActionWizardPage() {
        super(ChooseRestoreActionWizardPage.class.getName());
        setTitle(Messages.restoreActionTitle);
        setDescription(Messages.restoreActionDesc);
        setImageDescriptor(Pics.getWizban());
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(1, false));
        final Label commentLabel = new Label(composite, SWT.NONE);
        commentLabel.setText(Messages.chooseARestoreAction);

        radioComposite = new Composite(composite, SWT.NONE);
        final RowLayout rl = new RowLayout(SWT.VERTICAL);
        rl.spacing = 20;
        radioComposite.setLayout(rl);

        backupWorkspaceRadio = new Button(radioComposite, SWT.RADIO);
        backupWorkspaceRadio.setText(Messages.createARestorePointAction);
        restoreWorkspaceRadio = new Button(radioComposite, SWT.RADIO);

        backupWorkspaceRadio.setSelection(true);
        final int nbRestorePoint = new RestorePointContentProvider().getElements(repositoryContainer()).length;
        if (nbRestorePoint > 0) {
            restoreWorkspaceRadio.setEnabled(true);
        } else {
            restoreWorkspaceRadio.setEnabled(false);
        }
        restoreWorkspaceRadio.setText(Messages.bind(Messages.restoreAction, nbRestorePoint));

        setControl(composite);
    }

    private IRepositoryContainer repositoryContainer() {
        try {
            return TeamRepositoryUtil.getRemoteRepository((Repository) RepositoryManager
                    .getInstance().getCurrentRepository());
        } catch (final SVNConnectorException e) {
            new BonitaErrorDialog(getShell(), Messages.invalidRemoteLocationTitle, Messages.invalidRemoteLocationMsg, e).open();
        }
        return null;
    }

    @Override
    public boolean isPageComplete() {
        return super.isPageComplete();
    }

    public boolean isBackupAction() {
        return backupWorkspaceRadio.getSelection();
    }

    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (visible) {
            ((DynamicLabelWizardDialog) getContainer()).setFinishLabel(((DynamicLabelWizardDialog) getContainer()).getFinishLabel());
            final int nbRestorePoint = new RestorePointContentProvider().getElements(repositoryContainer()).length;
            if (nbRestorePoint > 0) {
                restoreWorkspaceRadio.setEnabled(true);
            } else {
                backupWorkspaceRadio.setSelection(true);
                restoreWorkspaceRadio.setSelection(false);
                restoreWorkspaceRadio.setEnabled(false);
            }
            restoreWorkspaceRadio.setText(Messages.bind(Messages.restoreAction, nbRestorePoint));
        }
    }

}
