/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.jface.dialog;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public abstract class FinishAndAddCustomWizardDialog extends CustomWizardDialog {

    private static final int CREATE_AND_NEW_ID = 1245;
    private Button finishAndNewButton;

    protected boolean withFinishAndAddButton = false;

    public FinishAndAddCustomWizardDialog(final Shell parentShell, final IWizard newWizard, final boolean withFinishAndAddButton) {
        super(parentShell, newWizard);
        this.withFinishAndAddButton = withFinishAndAddButton;
    }

    public FinishAndAddCustomWizardDialog(final Shell parentShell, final IWizard newWizard, final String finishLabel) {
        super(parentShell, newWizard, finishLabel);
    }

    @Override
    protected void createButtonsForButtonBar(final Composite parent) {
        super.createButtonsForButtonBar(parent);
        if (withFinishAndAddButton) {
            finishAndNewButton = super.createButton(parent, CREATE_AND_NEW_ID, Messages.createAndNewButton, true);
            setButtonLayoutData(finishAndNewButton);

            moveCancelButton();
            moveFinishButton();
        }
    }

    public void moveFinishButton() {
        final Button cancelButton = getButton(IDialogConstants.CANCEL_ID);
        final Button finishButton = getButton(IDialogConstants.FINISH_ID);
        finishAndNewButton.moveAbove(cancelButton);
        finishButton.setText(IDialogConstants.FINISH_LABEL);
        finishButton.moveAbove(finishAndNewButton);
    }

    private Button moveCancelButton() {
        final Button cancelButton = getButton(IDialogConstants.CANCEL_ID);
        cancelButton.setText(IDialogConstants.CANCEL_LABEL);
        cancelButton.moveBelow(null);
        return cancelButton;
    }

    @Override
    protected void buttonPressed(final int buttonId) {
        super.buttonPressed(buttonId);
        if (buttonId == CREATE_AND_NEW_ID && getWizard().performFinish()) {
                close();
                Display.getDefault().asyncExec(this::actionOnFinishAndAdd);
        }
    }

    /**
     * The current Wizard has been closed (and perform finish done). Implement in this method how to open the new one.
     */
    protected abstract void actionOnFinishAndAdd();

    @Override
    public void updateButtons() {
        super.updateButtons();
        if (withFinishAndAddButton) {
            final boolean canFinish = getWizard().canFinish();
            finishAndNewButton.setEnabled(canFinish);
        }
    }

    /**
     * @return the finishAndNewButton
     */
    public Button getFinishAndNewButton() {
        return finishAndNewButton;
    }

}
