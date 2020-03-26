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
package org.bonitasoft.studio.configuration.ui.wizard;

import org.bonitasoft.studio.configuration.ui.wizard.page.SelectProcessWizardPage;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Romain Bioteau
 *
 */
public class SelectProcessWizard extends Wizard {

	private MainProcess diagram;
	private SelectProcessWizardPage page;

	public SelectProcessWizard() {
		super() ;
		setDefaultPageImageDescriptor(Pics.getWizban()) ;
		setForcePreviousAndNextButtons(false) ;
	}

	public SelectProcessWizard(MainProcess diagram) {
		this() ;
		this.diagram = diagram ;
	}

	@Override
	public void addPages() {
		page = new SelectProcessWizardPage(diagram) ;
		addPage(page) ;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		return true;
	}

	public AbstractProcess getProcess() {
		return (AbstractProcess) page.getSelection();
	}

	

}
