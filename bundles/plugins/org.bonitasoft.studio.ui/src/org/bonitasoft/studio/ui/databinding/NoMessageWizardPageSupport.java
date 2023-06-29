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
package org.bonitasoft.studio.ui.databinding;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.dialog.DialogPageSupport;
import org.eclipse.jface.wizard.WizardPage;


public class NoMessageWizardPageSupport extends DialogPageSupport {

    private NoMessageWizardPageSupport(WizardPage wizardPage, DataBindingContext dbc) {
        super(wizardPage, dbc);
    }

    public static NoMessageWizardPageSupport create(WizardPage wizardPage,
            DataBindingContext dbc) {
        return new NoMessageWizardPageSupport(wizardPage, dbc);
    }

    @Override
    protected void handleStatusChanged() {
        boolean pageComplete = true;
        if (currentStatusStale) {
            pageComplete = false;
        } else if (currentStatus != null) {
            pageComplete = !currentStatus.matches(IStatus.ERROR
                    | IStatus.CANCEL);
        }
        ((WizardPage) getDialogPage()).setPageComplete(pageComplete);
    }

}
