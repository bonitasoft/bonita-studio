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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.AbstractRefactorOperation;
import org.bonitasoft.studio.common.DataUtil;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.refactoring.BonitaGroovyRefactoringAction;
import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.MultiInstantiation;
import org.bonitasoft.studio.model.process.Pool;
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
import org.eclipse.jdt.core.JavaModelException;
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
	private CompoundCommand finalCommand;
	private List<Expression> scriptExpressions;
	private boolean canExecute = true;
	private int operationType;

	public boolean isCanExecute() {
		return canExecute;
	}

	public void setCanExecute(boolean canExecute) {
		this.canExecute = canExecute;
	}

	public RefactorDataOperation(int operationType){
		this.scriptExpressions=new ArrayList<Expression>();
		this.operationType = operationType;
	}

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		Assert.isNotNull(parentProcess) ;
		Assert.isNotNull(oldData) ;
		Assert.isNotNull(domain);
		Assert.isNotNull(finalCommand);
		monitor.beginTask(Messages.refactoringData, IProgressMonitor.UNKNOWN) ;
		//CompoundCommand finalCommand=new CompoundCommand("Refactor Data Command");
		//CompoundCommand cc = new CompoundCommand("Refactor Data Command") ;
		//finalCommand.append(cc);
		if(newData !=null && canExecute){
			//TODO : find an intelligent way to search references to the oldData and replace them with the new one instead of writing specific code for each usecase
			updateDataReferenceInVariableExpressions(finalCommand);
			//domain.getCommandStack().execute(cc) ;
			//	cc =  new CompoundCommand("Refactor Data Command") ;
			//finalCommand.append(cc);
			updateDataReferenceInExpressions(finalCommand);
			if(updateDataReferences ){
				//	cc =  new CompoundCommand("Refactor Data Command") ;
				//	finalCommand.append(cc);
				updateDataInListsOfData(finalCommand);
				//domain.getCommandStack().execute(cc) ;
				//	cc =  new CompoundCommand("Refactor Data Command") ;
				//finalCommand.append(cc);
				updateDataReferenceInMultinstanciation(finalCommand);
				//domain.getCommandStack().execute(cc) ;
			}
		}else{
			removeAllDataReferences(finalCommand);
		}

		//	domain.getCommandStack().execute(finalCommand) ;
		monitor.done() ;
	}

	private void updateDataReferenceInExpressions(CompoundCommand finalCommand) {
		List<Expression> expressions = ModelHelper.getAllItemsOfType(parentProcess, ExpressionPackage.Literals.EXPRESSION) ;
		for(Expression exp : expressions){
			//CompoundCommand cc =  new CompoundCommand("Refactor Data Command") ;
			for(EObject dependency : exp.getReferencedElements()){
				if(dependency instanceof Data){
					if(((Data)dependency).getName().equals(oldData.getName())){
						finalCommand.append(RemoveCommand.create(domain, exp, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS, dependency));
						finalCommand.append(AddCommand.create(domain, exp, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS, ExpressionHelper.createDependencyFromEObject(newData)));
					}
				}
			}
			//		if(!cc.isEmpty()){
			//				for(org.eclipse.emf.common.command.Command c :cc.getCommandList()){
			//					if(c.canExecute()){
			//						domain.getCommandStack().execute(c) ;
			//					}
			//				}
			//			finalCommand.append(cc);
			//			}
		}
	}

	private void removeAllDataReferences(CompoundCommand cc) {
		List<Expression> expressions=null;
		if (oldData.eContainer() instanceof Pool){
			expressions = ModelHelper.getAllItemsOfType(parentProcess, ExpressionPackage.Literals.EXPRESSION) ;
		} else {
			expressions = ModelHelper.getAllItemsOfType(oldData.eContainer(), ExpressionPackage.Literals.EXPRESSION);
		}
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
			}else if(ExpressionConstants.SCRIPT_TYPE.equals(exp.getType()) || ExpressionConstants.CONDITION_TYPE.equals(exp.getType())){
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
			} else {
				if (ExpressionConstants.CONDITION_TYPE.equals(exp.getType())){
					
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

	public void setCompoundCommand(CompoundCommand finalCommand){
		this.finalCommand = finalCommand;
	}

	public void updateReferencesInScripts(){
		String newName=null;
		if (newData!=null){
			newName=newData.getName();
		} else {
			newName=AbstractRefactorOperation.EMPTY_VALUE;
		}
			scriptExpressions = ModelHelper.findAllScriptAndConditionsExpressionWithReferencedElement(oldData.eContainer(),oldData);
			if (!scriptExpressions.isEmpty() && !oldData.getName().equals(newName)){
				try {
					BonitaGroovyRefactoringAction action = new BonitaGroovyRefactoringAction(oldData.getName(), newName,scriptExpressions , finalCommand, domain,operationType);
					action.run(null);
					canExecute  = action.getStatus();
					//if (canExecute){
					//	for (Expression script:scriptExpressions){
						//	Object referencedObject = getDataToRemove(script);
						//	finalCommand.append(RemoveCommand.create(domain, script, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS  , referencedObject));
						//	finalCommand.append(AddCommand.create(domain, script, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS  ,EcoreUtil.copy(newData)));
					//	}
					//}
				} catch (JavaModelException e) {
					BonitaStudioLog.error(e);
				}
			} else {
				canExecute = true;
			}
		}

	public EObject getDataToRemove(Expression expr){
		for (EObject object:expr.getReferencedElements()){
			if (object instanceof Data && ((Data) object).getName().equals(oldData.getName())){
				return object;
			}
		}
		return null;
	}

}
