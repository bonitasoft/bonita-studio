/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.simulation.commands;
import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.simulation.wizards.ManageLoadProfilesWizard;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Display;


public class ManageLoadProfilesCommand extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		ManageLoadProfilesWizard manageLoadProfilesWizard = new ManageLoadProfilesWizard();
		
		new CustomWizardDialog(Display.getCurrent().getActiveShell(), manageLoadProfilesWizard, IDialogConstants.OK_LABEL){
			protected void createButtonsForButtonBar(org.eclipse.swt.widgets.Composite parent) {
				super.createButtonsForButtonBar(parent);
				getButton(IDialogConstants.CANCEL_ID).setVisible(false);
				
			};
		}.open();
		return null;
	}

}
