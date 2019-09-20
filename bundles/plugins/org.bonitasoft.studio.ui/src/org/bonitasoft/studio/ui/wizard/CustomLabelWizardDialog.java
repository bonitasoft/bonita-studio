/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.ui.wizard;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

public class CustomLabelWizardDialog extends WizardDialog {

    private String finishLabel = IDialogConstants.FINISH_LABEL;
    private String cancelLabel = IDialogConstants.CANCEL_LABEL;

    public CustomLabelWizardDialog(Shell parentShell, IWizard newWizard, String finishLabel) {
        super(parentShell, newWizard);
        this.finishLabel = finishLabel;
    }

    public CustomLabelWizardDialog(Shell parentShell, IWizard newWizard, String finishLabel, boolean isModal) {
        super(parentShell, newWizard);
        if (!isModal) {
            setShellStyle(SWT.CLOSE | SWT.MAX | SWT.TITLE | SWT.BORDER | SWT.RESIZE | getDefaultOrientation());
        }
        this.finishLabel = finishLabel;
    }

    public CustomLabelWizardDialog(Shell parentShell, IWizard newWizard, String finishLabel, String cancelLabel) {
        this(parentShell, newWizard, finishLabel);
        this.cancelLabel = cancelLabel;
    }

    public CustomLabelWizardDialog(Shell parentShell, IWizard newWizard, String finishLabel, String cancelLabel,
            boolean isModal) {
        this(parentShell, newWizard, finishLabel, isModal);
        this.cancelLabel = cancelLabel;
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        super.createButtonsForButtonBar(parent);
        Button finishButton = getButton(IDialogConstants.FINISH_ID);
        finishButton.setText(finishLabel);
        finishButton.forceFocus();
        if (cancelLabel != null) {
            getButton(IDialogConstants.CANCEL_ID).setText(cancelLabel);
            getButton(IDialogConstants.CANCEL_ID).forceFocus();
        }
    }

}
