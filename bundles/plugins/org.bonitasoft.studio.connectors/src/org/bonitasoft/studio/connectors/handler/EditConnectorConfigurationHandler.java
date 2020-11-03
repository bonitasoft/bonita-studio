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

package org.bonitasoft.studio.connectors.handler;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.connectors.ui.wizard.EditConnectorConfigurationWizard;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

/**
 * @author Aurelie Zara
 *
 */
public class EditConnectorConfigurationHandler extends AbstractHandler {

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		EditConnectorConfigurationWizard editConfigurationWizard = new EditConnectorConfigurationWizard((EObject)null, getConnectorFeature(), getConnectorFeatureToCheckUniqueID());
		 WizardDialog selectDialog = new WizardDialog(Display.getCurrent().getActiveShell(),editConfigurationWizard); ;
	    selectDialog.open();
		return null;
	}



protected EStructuralFeature getConnectorFeature() {
    return ProcessPackage.Literals.CONNECTABLE_ELEMENT__CONNECTORS;
}

protected Set<EStructuralFeature> getConnectorFeatureToCheckUniqueID() {
    Set<EStructuralFeature> res = new HashSet<EStructuralFeature>();
    res.add(ProcessPackage.Literals.CONNECTABLE_ELEMENT__CONNECTORS);
    return res;
}



}