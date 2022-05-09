/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.ui.wizard.listener;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;

public class WizardDoubleClickListener implements IDoubleClickListener {

    private WizardDialog wizardContainer;

    public WizardDoubleClickListener(WizardDialog wizardContainer) {
        this.wizardContainer = wizardContainer;
    }

    /**
     * [leave / go to the next page of] the wizard when you double click on an element of a viewer.
     */
    @Override
    public void doubleClick(DoubleClickEvent event) {
        IWizardPage currentPage = wizardContainer.getCurrentPage();
        if (currentPage.getNextPage() != null && currentPage.canFlipToNextPage()) {
            wizardContainer.showPage(currentPage.getNextPage());
        } else {
            IWizard wizard = currentPage.getWizard();
            if (wizard.canFinish() && wizard.performFinish()) {
                wizardContainer.close();
            }
        }
    }
}
