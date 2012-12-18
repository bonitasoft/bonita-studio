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
package org.bonitasoft.studio.simulation.wizards;

import org.bonitasoft.studio.model.simulation.SimulationCalendar;
import org.bonitasoft.studio.model.simulation.SimulationFactory;
import org.bonitasoft.studio.simulation.i18n.Messages;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Baptiste Mesta
 *
 */
public class EditCalendarWizard extends Wizard {

	private EditCalendarWizardPage editCalendarWizard;
	private final EObject eObject;
	private final EStructuralFeature feature;

	/**
	 * 
	 */
	public EditCalendarWizard(EObject eObject, EStructuralFeature feature) {
		this.eObject = eObject;
		this.feature = feature;
		setWindowTitle(Messages.EditCalendarWizard_Title);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		editCalendarWizard = new EditCalendarWizardPage((SimulationCalendar) eObject.eGet(feature));
		addPage(editCalendarWizard);
		super.addPages();
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		editCalendarWizard.setErrorMessage(null);
		SimulationCalendar resourceCalendar;
		if(eObject.eGet(feature) == null){
			resourceCalendar = SimulationFactory.eINSTANCE.createSimulationCalendar();
		}else{
			resourceCalendar = (SimulationCalendar) eObject.eGet(feature);
		}
		//TODO calendar
		resourceCalendar.getDaysOfWeek().removeAll(resourceCalendar.getDaysOfWeek());
		resourceCalendar.getDaysOfWeek().addAll(editCalendarWizard.getDays());
		
		eObject.eSet(feature,resourceCalendar);
		
		return true;
	}

}
