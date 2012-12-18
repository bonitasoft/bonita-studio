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

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.simulation.SimulationData;
import org.bonitasoft.studio.model.simulation.SimulationDataContainer;
import org.bonitasoft.studio.model.simulation.SimulationPackage;
import org.bonitasoft.studio.simulation.commands.AddSimulationDataOperation;
import org.bonitasoft.studio.simulation.commands.EditSimulationDataOperation;
import org.bonitasoft.studio.simulation.i18n.Messages;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Baptiste Mesta
 *
 */
public class AddSimulationDataWizard extends Wizard {

	private final TransactionalEditingDomain editingDomain;
	private SimulationDataContainer element;
	private SimulationData data;
	private AddSimulationDataWizardPage page;


	/**
	 * @param container
	 * @param editingDomain
	 */
	public AddSimulationDataWizard(SimulationDataContainer container, TransactionalEditingDomain editingDomain) {
		super();
		setWindowTitle(Messages.AddSimulationDataWizardPage_title);
		
		this.element = container;
		this.editingDomain = editingDomain;
	}
	
	public AddSimulationDataWizard(SimulationData data, TransactionalEditingDomain editingDomain){
		super();
		setWindowTitle(Messages.AddSimulationDataWizardPage_title);
		this.data = data;
		this.editingDomain = editingDomain;
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		EClass dataClass = page.getDataClass();
		page.setErrorMessage(null);
		if(page.getDataName() == null || page.getDataName().length()==0){
			page.setErrorMessage(Messages.EditSimulationResourceWizard_ErrorEmptyName);
		}
		if(data == null){
			AddSimulationDataOperation operation = null;
			if(dataClass.equals(SimulationPackage.eINSTANCE.getSimulationBoolean())){
				operation = new AddSimulationDataOperation(editingDomain,
						element,
						page.getDataName(),
						page.getDataDescription(),
						page.getProbabilityOfTrue(),
						page.getDataExpression(),
						page.isExpressionBased());
			} else if(dataClass.equals(SimulationPackage.eINSTANCE.getSimulationNumberData())){

				operation =new AddSimulationDataOperation(editingDomain,
						element,
						page.getDataName(),
						page.getDataDescription(),
						page.getRanges(),
						page.getDataExpression(),
						page.isExpressionBased());
				
			} else if(dataClass.equals(SimulationPackage.eINSTANCE.getSimulationLiteralData())){

				operation =new AddSimulationDataOperation(editingDomain,
						element,
						page.getDataName(),
						page.getDataDescription(),
						page.getDataExpression(),
						page.getLiterals(),
						page.isExpressionBased());
			}
			if(operation == null){
				return false;
			}
			try {
				OperationHistoryFactory.getOperationHistory().execute(operation, new NullProgressMonitor(), null);
			} catch (ExecutionException e) {
				BonitaStudioLog.error(e);
				page.setErrorMessage(e.getLocalizedMessage());
				return false;
			}
			 
			data = (SimulationData) operation.getCommandResult().getReturnValue() ;
			
		}else{
			EditSimulationDataOperation operation = null;
			if(dataClass.equals(SimulationPackage.eINSTANCE.getSimulationBoolean())){
				operation = new EditSimulationDataOperation(editingDomain,
						data,
						page.getDataName(),
						page.getDataDescription(),
						page.getProbabilityOfTrue(),
						page.getDataExpression(),
						page.isExpressionBased());
			} else if(dataClass.equals(SimulationPackage.eINSTANCE.getSimulationNumberData())){

				operation =new EditSimulationDataOperation(editingDomain,
						data,
						page.getDataName(),
						page.getDataDescription(),
						page.getRanges(),
						page.getDataExpression(),
						page.isExpressionBased());
				
			} else if(dataClass.equals(SimulationPackage.eINSTANCE.getSimulationLiteralData())){

				operation =new EditSimulationDataOperation(editingDomain,
						data,
						page.getDataName(),
						page.getDataDescription(),
						page.getDataExpression(),
						page.getLiterals(),
						page.isExpressionBased());
			}
			if(operation == null){
				return false;
			}
			try {
				OperationHistoryFactory.getOperationHistory().execute(operation, new NullProgressMonitor(), null);
			} catch (ExecutionException e) {
				BonitaStudioLog.error(e);
				page.setErrorMessage(e.getLocalizedMessage());
				return false;
			}
		}
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {

		page = new AddSimulationDataWizardPage(element,data);
		addPage(page);
		super.addPages();
	}
	
	/**
	 * @return
	 */
	public SimulationData getCreatedData() {
		return data;
	}



	
}
