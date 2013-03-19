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
package org.bonitasoft.studio.data.operation;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.DataUtil;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.MultiInstantiation;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.operation.IRunnableWithProgress;


/**
 * @author Romain Bioteau
 *
 */
public class RefactorDataOperation implements IRunnableWithProgress {

	private AbstractProcess parentProcess;
	private Data newData;
	private Data oldData;
	private EditingDomain domain;
	private boolean updateDataReferences = false;

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		Assert.isNotNull(parentProcess) ;
		Assert.isNotNull(oldData) ;
		Assert.isNotNull(domain) ;
		monitor.beginTask(Messages.refactoringData, IProgressMonitor.UNKNOWN) ;
		CompoundCommand cc = new CompoundCommand("Refactor Data Command") ;

		if(newData !=null){
			//TODO : find an intelligent way to search references to the oldData and replace them with the new one instead of writing specific code for each usecase
			updateDataReferenceInVariableExpressions(cc);
			if(updateDataReferences ){
				updateDataInListsOfData(cc);
				updateDataReferenceInMultinstanciation(cc);
			}
		}else{
			removeAllDataReferences(cc);
		}
		domain.getCommandStack().execute(cc) ;
		monitor.done() ;
	}

	private void removeAllDataReferences(CompoundCommand cc) {
		List<Expression> expressions = ModelHelper.getAllItemsOfType(parentProcess, ExpressionPackage.Literals.EXPRESSION) ;
		for(Expression exp : expressions){
			if(ExpressionConstants.VARIABLE_TYPE.equals(exp.getType()) && exp.getName().equals(oldData.getName())){
				//update name and content
				cc.append(SetCommand.create(domain, exp, ExpressionPackage.Literals.EXPRESSION__NAME,""));
				cc.append(SetCommand.create(domain, exp, ExpressionPackage.Literals.EXPRESSION__CONTENT, ""));
				//update return type
				cc.append(SetCommand.create(domain, exp, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE, String.class.getName()));
				cc.append(SetCommand.create(domain, exp, ExpressionPackage.Literals.EXPRESSION__TYPE, ExpressionConstants.CONSTANT_TYPE));
				//update referenced data
				cc.append(RemoveCommand.create(domain, exp, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS,exp.getReferencedElements()));
			}else if(ExpressionConstants.SCRIPT_TYPE.equals(exp.getType()) ){
				Set<EObject> toRemove = new HashSet<EObject>();
				for(EObject dep : exp.getReferencedElements()){
					if(dep instanceof Data){
						if(oldData.getName().equals(((Data) dep).getName()) && dep.eClass().equals(oldData.eClass())){
							toRemove.add(dep);
						}
					}
				}
				if(!toRemove.isEmpty()){
					cc.append(RemoveCommand.create(domain, exp, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS,toRemove));
				}
			}

		}
	}

	protected void updateDataInListsOfData(CompoundCommand cc) {
		List<Data> data = ModelHelper.getAllItemsOfType(parentProcess, ProcessPackage.Literals.DATA);
		for(Data d : data){
			if(!d.equals(newData) && d.getName().equals(oldData.getName())){
				Data copy = EcoreUtil.copy(newData);
				EObject container = d.eContainer();
				EReference eContainmentFeature = d.eContainmentFeature();
				if(container != null && container.eGet(eContainmentFeature) instanceof Collection<?>){
					List<?> dataList =  (List<?>) container.eGet(eContainmentFeature) ;
					int index = dataList.indexOf(d) ;
					cc.append(RemoveCommand.create(domain, container, eContainmentFeature, d)) ;
					cc.append(AddCommand.create(domain, container, eContainmentFeature, copy,index)) ;
				}else if(container != null && container.eGet(eContainmentFeature) instanceof Data){
					cc.append(SetCommand.create(domain, container, eContainmentFeature, copy)) ;
				}
			}
		}
	}

	protected void updateDataReferenceInVariableExpressions(CompoundCommand cc) {
		List<Expression> expressions = ModelHelper.getAllItemsOfType(parentProcess, ExpressionPackage.Literals.EXPRESSION) ;
		for(Expression exp : expressions){
			if(ExpressionConstants.VARIABLE_TYPE.equals(exp.getType()) && exp.getName().equals(oldData.getName())){
				//update name and content
				cc.append(SetCommand.create(domain, exp, ExpressionPackage.Literals.EXPRESSION__NAME, newData.getName()));
				cc.append(SetCommand.create(domain, exp, ExpressionPackage.Literals.EXPRESSION__CONTENT, newData.getName()));
				//update return type
				cc.append(SetCommand.create(domain, exp, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE, DataUtil.getTechnicalTypeFor(newData)));
				//update referenced data
				cc.append(RemoveCommand.create(domain, exp, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS, exp.getReferencedElements()));
				cc.append(AddCommand.create(domain, exp, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS, EcoreUtil.copy(newData)));
			}
		}
	}

	protected void updateDataReferenceInMultinstanciation(CompoundCommand cc) {
		List<MultiInstantiation> multiInstanciations = ModelHelper.getAllItemsOfType(parentProcess, ProcessPackage.Literals.MULTI_INSTANTIATION);
		for (MultiInstantiation multiInstantiation : multiInstanciations) {
			final Data inputData = multiInstantiation.getInputData();
			if(inputData != null && inputData.equals(oldData)){
				cc.append(SetCommand.create(domain, multiInstantiation, ProcessPackage.Literals.MULTI_INSTANTIATION__INPUT_DATA, newData));
			}
			final Data outputData = multiInstantiation.getOutputData();
			if(outputData != null && outputData.equals(oldData)){
				cc.append(SetCommand.create(domain, multiInstantiation, ProcessPackage.Literals.MULTI_INSTANTIATION__OUTPUT_DATA, newData));
			}
			final Data collectionToMultiinstanciateData = multiInstantiation.getCollectionDataToMultiInstantiate();
			if(collectionToMultiinstanciateData != null && collectionToMultiinstanciateData.equals(oldData)){
				cc.append(SetCommand.create(domain, multiInstantiation, ProcessPackage.Literals.MULTI_INSTANTIATION__COLLECTION_DATA_TO_MULTI_INSTANTIATE, newData));
			}
			final Data listDataContainingOutputResults = multiInstantiation.getListDataContainingOutputResults();
			if(listDataContainingOutputResults != null && listDataContainingOutputResults.equals(oldData)){
				cc.append(SetCommand.create(domain, multiInstantiation, ProcessPackage.Literals.MULTI_INSTANTIATION__LIST_DATA_CONTAINING_OUTPUT_RESULTS, newData));
			}
		}
	}

	public void setContainer(AbstractProcess parentProcess){
		this.parentProcess = parentProcess ;
	}

	public void setNewData(Data newData){
		this.newData = newData ;
	}

	public void setEditingDomain(EditingDomain domain){
		this.domain = domain ;
	}

	public void setOldData(Data oldData){
		this.oldData = oldData ;
	}

	public void setUpdateDataReferences(boolean updateDataReferences) {
		this.updateDataReferences = updateDataReferences;
	}
}
