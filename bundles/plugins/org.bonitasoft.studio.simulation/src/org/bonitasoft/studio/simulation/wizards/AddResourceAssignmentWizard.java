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

import org.bonitasoft.studio.model.simulation.ResourceUsage;
import org.bonitasoft.studio.model.simulation.SimulationActivity;
import org.bonitasoft.studio.model.simulation.SimulationFactory;
import org.bonitasoft.studio.model.simulation.SimulationPackage;
import org.bonitasoft.studio.simulation.i18n.Messages;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Baptiste Mesta
 *
 */
public class AddResourceAssignmentWizard extends Wizard {

	
	private final SimulationActivity eObject;
	private final TransactionalEditingDomain editingDomain;
	private AddResourceAssignmentWizardPage page;
	private final ResourceUsage resourceUsage;

	/**
	 * @param eObject2
	 * @param editingDomain
	 */
	public AddResourceAssignmentWizard(SimulationActivity eObject2, TransactionalEditingDomain editingDomain) {
		this.eObject = eObject2;
		this.editingDomain = editingDomain;
		this.resourceUsage = null;
	}
	/**
	 * @param eObject
	 * @param editingDomain
	 */
	public AddResourceAssignmentWizard(ResourceUsage resourceUsage, TransactionalEditingDomain editingDomain) {
		this.resourceUsage = resourceUsage;
		if(resourceUsage != null){
			eObject = (SimulationActivity) resourceUsage.eContainer();
		}else{
			eObject = null ;
		}
		
		this.editingDomain = editingDomain;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		page = new AddResourceAssignmentWizardPage(resourceUsage,eObject);
		addPage(page);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		page.setErrorMessage(null);
		String resourceID = page.getResourceID();
		if(resourceID.length()<=0){
			page.setErrorMessage(Messages.EditSimulationResourceWizard_ErrorEmptyName);
			return false;
		}
		String quantityText = page.getQuantity();
		int quantity = 0;
		try{
			quantity = Integer.parseInt(quantityText);
		} catch (NumberFormatException e) {
			page.setErrorMessage(Messages.EditSimulationResourceWizard_ErrorQuantity);
			return false;
		}
		long duration = page.getDuration();
		
		if(resourceUsage == null){
			ResourceUsage ru = SimulationFactory.eINSTANCE.createResourceUsage();
			ru.setDuration(duration);
			ru.setQuantity(quantity);
			ru.setResourceID(resourceID);
			ru.setUseActivityDuration(page.getUseActivityDuration());
			editingDomain.getCommandStack().execute(
			new AddCommand(editingDomain, eObject, SimulationPackage.Literals.SIMULATION_ACTIVITY__RESOURCES_USAGES,ru));
		}else{

			editingDomain.getCommandStack().execute(
					new SetCommand(editingDomain, resourceUsage, SimulationPackage.Literals.RESOURCE_USAGE__DURATION,duration));
			editingDomain.getCommandStack().execute(
					new SetCommand(editingDomain, resourceUsage, SimulationPackage.Literals.RESOURCE_USAGE__QUANTITY,quantity));
			editingDomain.getCommandStack().execute(
					new SetCommand(editingDomain, resourceUsage, SimulationPackage.Literals.RESOURCE_USAGE__RESOURCE_ID,resourceID));
			editingDomain.getCommandStack().execute(
					new SetCommand(editingDomain, resourceUsage, SimulationPackage.Literals.RESOURCE_USAGE__USE_ACTIVITY_DURATION,page.getUseActivityDuration()));
		}
		return true;
	}

}
