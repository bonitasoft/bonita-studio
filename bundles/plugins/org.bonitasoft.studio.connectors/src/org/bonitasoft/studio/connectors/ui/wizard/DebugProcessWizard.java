/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connectors.ui.wizard;

import java.util.Set;

import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Romain Bioteau
 */
public class DebugProcessWizard extends Wizard implements IWizard {

    private MainProcess process;
    private ConnectorsSelectionPage page;
    private Set<EObject> excludedObject;

    public DebugProcessWizard(MainProcess process) {
        this.process = process;
        setWindowTitle(Messages.debugProcessWizardtitle);
    }

    @Override
    public void addPages() {
        page = new ConnectorsSelectionPage(process);
        addPage(page);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        excludedObject = page.getExcludedConnectors();
        return true;
    }

    public Set<EObject> getExcludedObjects() {
        if (excludedObject == null) {
            excludedObject = page.getExcludedConnectors();
        }
        return excludedObject;
    }

}
