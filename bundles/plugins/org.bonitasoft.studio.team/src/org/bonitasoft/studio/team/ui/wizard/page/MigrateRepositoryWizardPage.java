/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.wizard.page;

import static org.bonitasoft.studio.common.Messages.bosProductName;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.team.ui.dialog.DynamicLabelWizardDialog;
import org.bonitasoft.studio.team.ui.wizard.ConnectToRepositoryWizard;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 * 
 */
public class MigrateRepositoryWizardPage extends WizardPage {


	private Text warnings;

	public MigrateRepositoryWizardPage() {
		super(MigrateRepositoryWizardPage.class.getName());
		setTitle(Messages.migrateWorkspace_title);
		setDescription(Messages.bind(Messages.migrateWorkspace_desc,ProductVersion.CURRENT_VERSION));
		setImageDescriptor(Pics.getWizban());
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if(visible){
			((DynamicLabelWizardDialog)getContainer()).setFinishLabel(Messages.migrateWorkspace);
			setPageComplete(true) ;
			warnings.setText(migrationWarningMessage());
		}else{
			((DynamicLabelWizardDialog)getContainer()).setFinishLabel(((DynamicLabelWizardDialog)getContainer()).getFinishLabel());
		}
	}

    private String migrationWarningMessage() {
        String oldVersion = ((ConnectToRepositoryWizard) getWizard()).getSelectedWorkspaceVersion();
        String message = Messages.bind(Messages.migrationWarning,
                new Object[] { ((ConnectToRepositoryWizard) getWizard()).getSelectedWorkspace().getName(), oldVersion,
                        ProductVersion.CURRENT_VERSION, bosProductName });
        if (ProductVersion.isBefore780Version(oldVersion)) {
            return message + System.lineSeparator() + System.lineSeparator() + Messages.legacyFormsRemoved;
        }
        return message;
    }
	
	@Override
	public boolean isPageComplete() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
		warnings = new Text(composite, SWT.BORDER | SWT.WRAP | SWT.MULTI | SWT.READ_ONLY) ;
		warnings.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
		
		setPageComplete(true) ;
		setControl(composite);
	}

}
