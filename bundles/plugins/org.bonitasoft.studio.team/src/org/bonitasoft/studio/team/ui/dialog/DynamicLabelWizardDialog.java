/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.dialog;

import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.team.ui.wizard.ConnectToRepositoryWizard;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Romain Bioteau
 *
 */
public class DynamicLabelWizardDialog extends CustomWizardDialog {

	public DynamicLabelWizardDialog(Shell parentShell, IWizard newWizard, String finishLabel) {
		super(parentShell, newWizard,finishLabel);
		this.finishLabel = finishLabel ;
	}

	protected void nextPressed() {
		if(getWizard() instanceof ConnectToRepositoryWizard){
			if(((ConnectToRepositoryWizard)getWizard()).nextPressed()){
				super.nextPressed();	
			}
		}else{
			super.nextPressed();
		}
	}

	public void setNextLabel(String label){
		Button nextButton = getButton(IDialogConstants.NEXT_ID) ;
		nextButton.setText(label) ;
	}

	public void setFinishLabel(String label){
		Button finishButton = getButton(IDialogConstants.FINISH_ID) ;
		finishButton.setText(label) ;
	}

	public String getFinishLabel() {
		return finishLabel;
	}

}
