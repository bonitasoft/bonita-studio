/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.jface.databinding;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.dialog.DialogPageSupport;
import org.eclipse.jface.wizard.WizardPage;


/**
 * @author Romain Bioteau
 *
 */
public class WizardPageSupportWithoutMessages extends DialogPageSupport {

    private WizardPageSupportWithoutMessages(WizardPage wizardPage, DataBindingContext dbc) {
        super(wizardPage, dbc);
    }

    /**
     * Connect the validation result from the given data binding context to the
     * given wizard page. Upon creation, the wizard page support will use the
     * context's validation result to determine whether the page is complete.
     * The page's error message will not be set at this time ensuring that the
     * wizard page does not show an error right away. Upon any validation result
     * change, {@link WizardPage#setPageComplete(boolean)} will be called
     * reflecting the new validation result, and the wizard page's error message
     * will be updated according to the current validation result.
     * 
     * @param wizardPage
     * @param dbc
     * @return an instance of WizardPageSupport
     */
    public static WizardPageSupportWithoutMessages create(WizardPage wizardPage,
            DataBindingContext dbc) {
        return new WizardPageSupportWithoutMessages(wizardPage, dbc);
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
