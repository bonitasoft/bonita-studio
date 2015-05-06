/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.contract.ui.wizard;

import org.bonitasoft.studio.common.jface.dialog.FinishAndAddCustomWizardDialog;
import org.bonitasoft.studio.contract.ui.property.ContractPropertySection;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;

/**
 * @author aurelie
 */
public class AddInputContractFromDataWizardDialog extends FinishAndAddCustomWizardDialog {

    private final ContractPropertySection contractPropertySection;

    /**
     * @param parentShell
     * @param newWizard
     * @param withFinishAndAddButton
     */
    public AddInputContractFromDataWizardDialog(final Shell parentShell, final IWizard newWizard, final ContractPropertySection contractPropertySection,
            final boolean withFinishAndAddButton) {
        super(parentShell, newWizard, withFinishAndAddButton);
        this.contractPropertySection = contractPropertySection;
    }

    @Override
    public void moveFinishButton() {
        final Button cancelButton = getButton(IDialogConstants.CANCEL_ID);
        final Button finishButton = getButton(IDialogConstants.FINISH_ID);
        getFinishAndNewButton().moveAbove(finishButton);
        finishButton.setText(IDialogConstants.FINISH_LABEL);
        //  finishButton.moveAbove(getFinishAndNewButton());
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.jface.dialog.FinishAndAddCustomWizardDialog#actionOnFinishAndAdd()
     */
    @Override
    protected void actionOnFinishAndAdd() {
        if (contractPropertySection != null) {
            contractPropertySection.openAddInputWizardDialog();
        }
    }
}
