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
 
package org.bonitasoft.studio.application.actions.wizards;

import java.util.List;

import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Romain Bioteau
 *
 */
public class ChooseProcessesWizard extends Wizard {

	private List<AbstractProcess> processes ;
	private ChooseProcessesWizardPage page;
	
	@Override
	public void addPages() {
		page = new ChooseProcessesWizardPage();
		addPage(page);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		processes = page.getProcesses() ;
		return processes.size() > 0;
	}

	public List<AbstractProcess> getProcesses() {
		return processes;
	}

}
