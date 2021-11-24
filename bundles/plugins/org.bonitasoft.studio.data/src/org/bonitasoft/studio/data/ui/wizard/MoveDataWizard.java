/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

package org.bonitasoft.studio.data.ui.wizard;

import org.bonitasoft.studio.model.process.DataAware;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Romain Bioteau
 *
 */
public class MoveDataWizard extends Wizard implements IWizard {

	private DataAware sourceContainer;
	private MoveDataWizardPage page ;
	private DataAware selectedDataAwareElement;
	
	public MoveDataWizard(TransactionalEditingDomain editingDomain,DataAware parentProcess) {
		this(parentProcess);
	}

	public MoveDataWizard(DataAware parentProcess) {
		this.sourceContainer = parentProcess ;
	}

	@Override
	public void addPages() {
		page = new MoveDataWizardPage(sourceContainer);
		addPage(page);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		selectedDataAwareElement = page.getSelectedDataAwareElement();
		return selectedDataAwareElement != null;
	}

	/**
	 * @return the selectedConnectableElement
	 */
	public DataAware getSelectedDataAwareElement() {
		return selectedDataAwareElement;
	}

}
