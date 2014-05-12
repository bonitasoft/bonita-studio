/**
 * Copyright (C) 2012-2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.data.operation;

import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.common.DataUtil;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.MultiInstantiation;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.refactoring.core.AbstractRefactorOperation;
import org.bonitasoft.studio.refactoring.core.AbstractScriptExpressionRefactoringAction;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @author Romain Bioteau
 * 
 */
public class RefactorDataOperation extends AbstractRefactorOperation {

    private AbstractProcess parentProcess;

    private Data newData;

    private Data oldData;

    private boolean updateDataReferences = false;

	private EStructuralFeature dataContainmentFeature;

	private EObject directContainer;

    public RefactorDataOperation(RefactoringOperationType operationType) {
        super(operationType);
    }

    @Override
    protected void doExecute(IProgressMonitor monitor) {
        Assert.isNotNull(parentProcess);
        Assert.isNotNull(oldData);
        monitor.beginTask(Messages.refactoringData, IProgressMonitor.UNKNOWN);
        if (newData != null) {
            updateDataReferenceInVariableExpressions(compoundCommand);
            updateDataReferenceInExpressions(compoundCommand);
            if (updateDataReferences) {
                updateDataReferenceInMultinstanciation(compoundCommand);
                List<?> dataList = (List<?>) parentProcess.eGet(dataContainmentFeature);
                int index = dataList.indexOf(oldData);
                compoundCommand.append(RemoveCommand.create(domain, directContainer, dataContainmentFeature, oldData));
                compoundCommand.append(AddCommand.create(domain, directContainer, dataContainmentFeature, newData, index));
            } else {
            	for (EStructuralFeature feature : oldData.eClass().getEAllStructuralFeatures()) {
            		compoundCommand.append(SetCommand.create(domain, oldData, feature, newData.eGet(feature)));
            	}
            }
        } else {
            removeAllDataReferences(compoundCommand);
        }
        if(RefactoringOperationType.REMOVE.equals(operationType)){
        	compoundCommand.append(DeleteCommand.create(domain, oldData));
        }
    }

    private void updateDataReferenceInExpressions(CompoundCommand finalCommand) {
        List<Expression> expressions = ModelHelper.getAllItemsOfType(parentProcess, ExpressionPackage.Literals.EXPRESSION);
        for (Expression exp : expressions) {
            if (!ExpressionConstants.SCRIPT_TYPE.equals(exp.getType())
            		&& !ExpressionConstants.PATTERN_TYPE.equals(exp.getType())
            		&& !ExpressionConstants.CONDITION_TYPE.equals(exp.getType())) {
                for (EObject dependency : exp.getReferencedElements()) {
                    if (dependency instanceof Data) {
                        if (((Data) dependency).getName().equals(oldData.getName())) {
                            finalCommand.append(RemoveCommand.create(domain, exp, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS, dependency));
                            finalCommand.append(AddCommand.create(domain, exp, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS,
                                    ExpressionHelper.createDependencyFromEObject(newData)));
                        }
                    }
                }
            }
        }
    }

    private void removeAllDataReferences(CompoundCommand cc) {
        List<Expression> expressions = null;
        if (oldData.eContainer() instanceof Pool) {
            expressions = ModelHelper.getAllItemsOfType(parentProcess, ExpressionPackage.Literals.EXPRESSION);
        } else {
            expressions = ModelHelper.getAllItemsOfType(oldData.eContainer(), ExpressionPackage.Literals.EXPRESSION);
        }
        for (Expression exp : expressions) {
            if (ExpressionConstants.VARIABLE_TYPE.equals(exp.getType()) && exp.getName().equals(oldData.getName())) {
                // update name and content
                cc.append(SetCommand.create(domain, exp, ExpressionPackage.Literals.EXPRESSION__NAME, ""));
                cc.append(SetCommand.create(domain, exp, ExpressionPackage.Literals.EXPRESSION__CONTENT, ""));
                // update return type
                cc.append(SetCommand.create(domain, exp, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE, String.class.getName()));
                cc.append(SetCommand.create(domain, exp, ExpressionPackage.Literals.EXPRESSION__TYPE, ExpressionConstants.CONSTANT_TYPE));
                // update referenced data
                cc.append(RemoveCommand.create(domain, exp, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS, exp.getReferencedElements()));
            }
        }
    }

    protected void updateDataInListsOfData(CompoundCommand cc) {
        List<Data> data = ModelHelper.getAllItemsOfType(parentProcess, ProcessPackage.Literals.DATA);
        for (Data d : data) {
            if (!d.equals(newData) && d.getName().equals(oldData.getName())) {
                Data copy = EcoreUtil.copy(newData);
                EObject container = d.eContainer();
                EReference eContainmentFeature = d.eContainmentFeature();
                if (container != null && container.eGet(eContainmentFeature) instanceof Collection<?>) {
                    List<?> dataList = (List<?>) container.eGet(eContainmentFeature);
                    int index = dataList.indexOf(d);
                    cc.append(RemoveCommand.create(domain, container, eContainmentFeature, d));
                    cc.append(AddCommand.create(domain, container, eContainmentFeature, copy, index));
                } else if (container != null && container.eGet(eContainmentFeature) instanceof Data) {
                    cc.append(SetCommand.create(domain, container, eContainmentFeature, copy));
                }
            }
        }
    }

    protected void updateDataReferenceInVariableExpressions(CompoundCommand cc) {
        List<Expression> expressions = ModelHelper.getAllItemsOfType(parentProcess, ExpressionPackage.Literals.EXPRESSION);
        for (Expression exp : expressions) {
            if (ExpressionConstants.VARIABLE_TYPE.equals(exp.getType()) && exp.getName().equals(oldData.getName())) {
                // update name and content
                cc.append(SetCommand.create(domain, exp, ExpressionPackage.Literals.EXPRESSION__NAME, newData.getName()));
                cc.append(SetCommand.create(domain, exp, ExpressionPackage.Literals.EXPRESSION__CONTENT, newData.getName()));
                // update return type
                cc.append(SetCommand.create(domain, exp, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE, DataUtil.getTechnicalTypeFor(newData)));
            }
        }
    }

    protected void updateDataReferenceInMultinstanciation(CompoundCommand cc) {
        List<MultiInstantiation> multiInstanciations = ModelHelper.getAllItemsOfType(parentProcess, ProcessPackage.Literals.MULTI_INSTANTIATION);
        for (MultiInstantiation multiInstantiation : multiInstanciations) {
            final Data inputData = multiInstantiation.getInputData();
            if (inputData != null && inputData.equals(oldData)) {
                cc.append(SetCommand.create(domain, multiInstantiation, ProcessPackage.Literals.MULTI_INSTANTIATION__INPUT_DATA, newData));
            }
            final Data outputData = multiInstantiation.getOutputData();
            if (outputData != null && outputData.equals(oldData)) {
                cc.append(SetCommand.create(domain, multiInstantiation, ProcessPackage.Literals.MULTI_INSTANTIATION__OUTPUT_DATA, newData));
            }
            final Data collectionToMultiinstanciateData = multiInstantiation.getCollectionDataToMultiInstantiate();
            if (collectionToMultiinstanciateData != null && collectionToMultiinstanciateData.equals(oldData)) {
                cc.append(SetCommand.create(domain, multiInstantiation, ProcessPackage.Literals.MULTI_INSTANTIATION__COLLECTION_DATA_TO_MULTI_INSTANTIATE,
                        newData));
            }
            final Data listDataContainingOutputResults = multiInstantiation.getListDataContainingOutputResults();
            if (listDataContainingOutputResults != null && listDataContainingOutputResults.equals(oldData)) {
                cc.append(SetCommand.create(domain, multiInstantiation, ProcessPackage.Literals.MULTI_INSTANTIATION__LIST_DATA_CONTAINING_OUTPUT_RESULTS,
                        newData));
            }
        }
    }

    public void setContainer(AbstractProcess parentProcess) {
        this.parentProcess = parentProcess;
    }

    public void setNewData(Data newData) {
        this.newData = newData;
    }

    public void setOldData(Data oldData) {
        this.oldData = oldData;
    }

    public void setUpdateDataReferences(boolean updateDataReferences) {
        this.updateDataReferences = updateDataReferences;
    }

    @Override
    protected AbstractScriptExpressionRefactoringAction getScriptExpressionRefactoringAction(EObject newValue, String oldName, String newName,
            List<Expression> scriptExpressions, List<Expression> refactoredScriptExpression, CompoundCommand compoundCommand, EditingDomain domain,
            RefactoringOperationType operationType) {
        return new DataScriptExpressionRefactoringAction(newValue, oldName, newName, scriptExpressions, refactoredScriptExpression, compoundCommand, domain,
                operationType);
    }

    @Override
    protected EObject getContainer() {
        return parentProcess;
    }

    @Override
    protected EObject getOldValue() {
        return oldData;
    }

    @Override
    protected String getOldValueName() {
        return oldData.getName();
    }

    @Override
    protected EObject getNewValue() {
        return newData;
    }

    @Override
    protected String getNewValueName() {
        if (newData != null) {
            return newData.getName();
        }
        return EMPTY_VALUE;
    }

	public void setDataContainmentFeature(EStructuralFeature dataContainmentFeature) {
		this.dataContainmentFeature = dataContainmentFeature;
	}

	public void setDirectDataContainer(EObject container) {
		this.directContainer = container;
	}

}
