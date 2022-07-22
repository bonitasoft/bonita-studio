/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.jface;

import org.bonitasoft.studio.common.Messages;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Mickael Istria
 *
 */
public class CustomWizardDialog extends WizardDialog {

	protected String finishLabel;
	protected String closeLabel;
    private boolean askWhenShellCloses = !FileActionDialog.getDisablePopup();

	/**
	 * @param parentShell
	 * @param newWizard
	 */
	public CustomWizardDialog(Shell parentShell, IWizard newWizard, String finishLabel) {
		super(parentShell, newWizard);
		this.finishLabel = finishLabel;
	}
	

	/**
	 * @param parentShell
	 * @param newWizard
	 */
	public CustomWizardDialog(Shell parentShell, IWizard newWizard, String finishLabel,boolean isModal) {
		super(parentShell, newWizard);
		if(!isModal){
            setShellStyle(SWT.CLOSE | SWT.MAX | SWT.TITLE | SWT.BORDER | SWT.RESIZE
                    | getDefaultOrientation());
		}
		this.finishLabel = finishLabel;
	}
	
	
	public CustomWizardDialog(Shell parentShell, IWizard newWizard, String finishLabel,String closeLabel) {
		this(parentShell, newWizard,finishLabel);
		this.closeLabel = closeLabel ;
	}
	
	public CustomWizardDialog(Shell parentShell, IWizard newWizard, String finishLabel,String closeLabel,boolean isModal) {
		this(parentShell, newWizard,finishLabel,isModal);
		this.closeLabel = closeLabel ;
	}
	
	public CustomWizardDialog(Shell parentShell, IWizard newWizard) {
		this(parentShell, newWizard,IDialogConstants.FINISH_LABEL);
	}
	
	/**
	 * @param shell
	 * @param wizard
	 * @param b
	 * @param finishLabel
	 */
	public CustomWizardDialog(Shell shell, IWizard wizard, boolean askWhenShellCloses, String finishLabel) {
		this(shell,wizard,finishLabel);
		this.askWhenShellCloses = askWhenShellCloses;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		super.createButtonsForButtonBar(parent);
		getButton(IDialogConstants.FINISH_ID).setText(finishLabel);
		if(closeLabel != null){
			getButton(IDialogConstants.CANCEL_ID).setText(closeLabel);
		}
	}
	
	/**
	 * Prevent the wizard to close accidentally by pressing escape (or the red cross)
	 * @see org.eclipse.jface.window.Window#canHandleShellCloseEvent()
	 */
	@Override
	protected boolean canHandleShellCloseEvent() {
		if(askWhenShellCloses){
			Boolean close = MessageDialog.openQuestion(getShell(), Messages.handleShellCloseEventTitle, Messages.handleShellCloseEventMessage);
			if(close){
				return super.canHandleShellCloseEvent();
			} else {
				return false;
			}
		}else{
			return true;
		}
	}


	public boolean isAskWhenShellCloses() {
		return askWhenShellCloses;
	}


	public void setAskWhenShellCloses(boolean askWhenShellCloses) {
		this.askWhenShellCloses = askWhenShellCloses && !FileActionDialog.getDisablePopup();
	}
}
