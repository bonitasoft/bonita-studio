/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.validators.ui.handler;

import org.bonitasoft.studio.validators.ui.wizard.SelectUserValidatorWizard;
import org.bonitasoft.studio.validators.ui.wizard.ValidatorWizard;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;


/**
 * @author Romain Bioteau
 *
 */
public class EditValidatorHandler extends AbstractHandler {

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		SelectUserValidatorWizard selectWizard = new SelectUserValidatorWizard() ;
		WizardDialog selectDialog = new WizardDialog(Display.getCurrent().getActiveShell(),selectWizard) ;
		if(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeAllEditors(true)){
			if(selectDialog.open() == Dialog.OK){
				ValidatorWizard wizard = new ValidatorWizard(selectWizard.getValidatorDescriptor()) ;
				WizardDialog wd = new WizardDialog(Display.getCurrent().getActiveShell(),wizard) ;
				wd.open();
			}
		}
		return null;
	}

}
