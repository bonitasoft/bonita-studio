/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.wizard.page;

import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.team.operations.CreateWorkspaceBackupOperation;
import org.bonitasoft.studio.team.ui.dialog.DynamicLabelWizardDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 */
public class CreateRestorePointWizardPage extends WizardPage {

    private Text commentText;

    public CreateRestorePointWizardPage() {
        super(CreateRestorePointWizardPage.class.getName());
        setTitle(Messages.bind(Messages.chooseWorkspaceToBackupTitle, getSelectedWorkspace()));
        setDescription(Messages.bind(Messages.chooseWorkspaceToBackupDesc, getSelectedWorkspace()));
        setImageDescriptor(Pics.getWizban());
    }

    @Override
    public boolean canFlipToNextPage() {
        return false;
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
        commentLabel.setText(Messages.addCommentLabel);
        commentLabel.setLayoutData(GridDataFactory.swtDefaults().indent(SWT.DEFAULT, 10).create());
        commentText = new Text(composite, SWT.BORDER | SWT.MULTI | SWT.WRAP);
        commentText.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        commentText.setText(CreateWorkspaceBackupOperation.DEFAULT_COMMENT);

        setControl(composite);
    }

    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (visible) {
            ((DynamicLabelWizardDialog) getContainer()).setFinishLabel(Messages.create);
        }
    }

    @Override
    public boolean isPageComplete() {
        return super.isPageComplete() && getContainer().getCurrentPage().equals(this);
    }

    public String getSelectedWorkspace() {
        return CommonRepositoryPlugin.getCurrentRepository();
    }

    public String getComments() {
        if (commentText != null) {
            return commentText.getText();
        } else {
            return "";
        }
    }

}
