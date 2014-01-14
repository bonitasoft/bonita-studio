/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.data.ui.wizard;

import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.data.ui.property.section.AbstractDataSection;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

/**
 * @author aurelie zara
 * 
 */
public class DataWizardDialog extends WizardDialog {

	private static final int CREATE_AND_NEW_ID = 1245;
	private final AbstractDataSection dataSection;
	private Button createAndNewButton;

	// Minimum dialog width (in dialog units)
	private static final int MIN_DIALOG_WIDTH = 350;

	// Minimum dialog height (in dialog units)
	private static final int MIN_DIALOG_HEIGHT = 270;

	public DataWizardDialog(Shell parentShell, IWizard newWizard,
			AbstractDataSection dataSection) {
		super(parentShell, newWizard);
		this.dataSection = dataSection;
	}

	@Override
	protected Point getInitialSize() {
		Point shellSize = super.getInitialSize();
		return new Point(Math.max(
				convertHorizontalDLUsToPixels(MIN_DIALOG_WIDTH), shellSize.x),
				Math.max(convertVerticalDLUsToPixels(MIN_DIALOG_HEIGHT),
						shellSize.y));
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		super.createButtonsForButtonBar(parent);
		if (dataSection!=null ) {
			createAndNewButton = super.createButton(parent, CREATE_AND_NEW_ID,
					Messages.createAndNewButton, true);
			setButtonLayoutData(createAndNewButton);
		}
		Button cancelButton = getButton(IDialogConstants.CANCEL_ID);
		cancelButton.setText(IDialogConstants.CANCEL_LABEL);
		Button finishButton = getButton(IDialogConstants.FINISH_ID);
		
		cancelButton.moveBelow(null);
		if (dataSection!=null) {
			createAndNewButton.moveAbove(cancelButton);
			finishButton.setText(IDialogConstants.FINISH_LABEL);
		} else {
			finishButton.setText(IDialogConstants.OK_LABEL);
			
		}
		finishButton.moveAbove(createAndNewButton);
	}

	@Override
	protected void buttonPressed(int buttonId) {
		super.buttonPressed(buttonId);
		if (buttonId == CREATE_AND_NEW_ID) {
			getWizard().performFinish();
			close();
			if (dataSection != null) {
				dataSection.addData();
			}
		}
	}

	@Override
	public void updateButtons() {
		super.updateButtons();
		boolean canFinish = getWizard().canFinish();
		if (dataSection != null) {
			createAndNewButton.setEnabled(canFinish);
		}
	}
}
