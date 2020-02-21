/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.document.ui;

import org.bonitasoft.studio.common.jface.dialog.FinishAndAddCustomWizardDialog;
import org.bonitasoft.studio.document.i18n.Messages;
import org.eclipse.swt.widgets.Shell;

public class AdditionalResourceWizardDialog extends FinishAndAddCustomWizardDialog {

    public AdditionalResourceWizardDialog(Shell parentShell, AdditionalResourceWizard newWizard,
            boolean withFinishAndAddButton) {
        super(parentShell, newWizard, withFinishAndAddButton);
        setTitle(Messages.newAdditionalResource);
    }

    @Override
    protected void actionOnFinishAndAdd() {
        AdditionalResourceWizardDialog documentWizardDialog = new AdditionalResourceWizardDialog(getParentShell(),
                new AdditionalResourceWizard(getWizard().getPool()), true);
        documentWizardDialog.open();
    }

    @Override
    protected AdditionalResourceWizard getWizard() {
        return (AdditionalResourceWizard) super.getWizard();
    }
}
