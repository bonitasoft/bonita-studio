/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.document.refactoring;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.EMFModelUpdater;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.model.ModelSearch;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.refactoring.core.AbstractRefactorOperation;
import org.bonitasoft.studio.refactoring.core.DataRefactorPair;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;

public class RefactorDocumentOperation extends AbstractRefactorOperation<Document, Document, DocumentRefactorPair> {

    public RefactorDocumentOperation(final RefactoringOperationType operationType) {
        super(operationType);
    }

    @Override
    protected CompoundCommand doBuildCompoundCommand(final CompoundCommand compoundCommand, final IProgressMonitor monitor) {
        final CompoundCommand deleteCommands = new CompoundCommand(
                "Compound commands containing all delete operations to do at last step");
        for (final DocumentRefactorPair pairToRefactor : pairsToRefactor) {
            doExecute(compoundCommand, deleteCommands, pairToRefactor);
        }
        compoundCommand.appendIfCanExecute(deleteCommands);
        return compoundCommand;
    }

    private void doExecute(final CompoundCommand compoundCommand, final CompoundCommand deleteCommands,
            final DocumentRefactorPair pairToRefactor) {
        if (pairToRefactor.getNewValue() != null) {
            doExecuteRefactorDocumentOperation(compoundCommand, pairToRefactor);
        } else {
            doExecuteDeleteDocumentOperation(compoundCommand, deleteCommands, pairToRefactor);
        }
    }

    private void doExecuteRefactorDocumentOperation(final CompoundCommand compoundCommand,
            final DocumentRefactorPair pairToRefactor) {
        updateDocumentInDocumentExpressions(compoundCommand, pairToRefactor);
        EMFModelUpdater<EObject> updater = new EMFModelUpdater<>().from(pairToRefactor.getOldValue());
        updater.editWorkingCopy(pairToRefactor.getNewValue());
        compoundCommand.append(updater.createUpdateCommand(getEditingDomain()));
        updateContractInputDataReference(compoundCommand, pairToRefactor, pairToRefactor.getNewValueName());
    }

    private void doExecuteDeleteDocumentOperation(final CompoundCommand compoundCommand,
            final CompoundCommand deleteCommands,
            final DocumentRefactorPair pairToRefactor) {
        removeAllDocumentReferences(compoundCommand, pairToRefactor);
        deleteCommands.append(DeleteCommand.create(getEditingDomain(), pairToRefactor.getOldValue()));
        updateContractInputDataReference(deleteCommands, pairToRefactor, null);
    }

    protected void updateDocumentInDocumentExpressions(final CompoundCommand compoundCommand,
            final DocumentRefactorPair pairToRefactor) {
        final Document newValue = pairToRefactor.getNewValue();
        if (newValue != null) {
            final List<Expression> expressions = ModelHelper.getAllItemsOfType(getContainer(pairToRefactor.getOldValue()),
                    ExpressionPackage.Literals.EXPRESSION);
            for (final Expression exp : expressions) {
                updateDocumentInExpression(compoundCommand, pairToRefactor, newValue, exp);
            }
        } else {
            removeAllDocumentReferences(compoundCommand, pairToRefactor);
        }
    }

    private void updateDocumentInExpression(final CompoundCommand cc, final DocumentRefactorPair pairToRefactor,
            final Document newValue, final Expression exp) {
        if (isMatchingDocumentExpression(pairToRefactor, exp)) {
            // update name and content
            cc.append(SetCommand.create(getEditingDomain(), exp, ExpressionPackage.Literals.EXPRESSION__NAME,
                    newValue.getName()));
            cc.append(SetCommand.create(getEditingDomain(), exp, ExpressionPackage.Literals.EXPRESSION__CONTENT,
                    newValue.getName()));
            updateReturnType(cc, newValue, exp);
            updateDependencies(cc, pairToRefactor, exp);
        }
    }

    private void updateReturnType(final CompoundCommand cc, final Document newValue, final Expression exp) {
        final String newReturnType;
        if (newValue.isMultiple()) {
            newReturnType = List.class.getName();
        } else {
            if (ExpressionConstants.DOCUMENT_REF_TYPE.equals(exp.getType())) {
                newReturnType = String.class.getName();
            } else {
                newReturnType = org.bonitasoft.engine.bpm.document.Document.class.getName();
            }
        }
        cc.append(SetCommand.create(getEditingDomain(), exp, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE,
                newReturnType));
    }

    private void updateDependencies(final CompoundCommand cc, final DocumentRefactorPair pairToRefactor,
            final Expression exp) {
        for (final EObject dependency : exp.getReferencedElements()) {
            if (dependency instanceof Document
                    && ((Document) dependency).getName().equals(pairToRefactor.getOldValueName())) {
                EMFModelUpdater<EObject> updater = new EMFModelUpdater<>().from(dependency);
                updater.editWorkingCopy(ExpressionHelper.createDependencyFromEObject(pairToRefactor.getNewValue()));
                cc.append(updater.createUpdateCommand(getEditingDomain()));
            }
        }
    }

    private boolean isMatchingDocumentExpression(final DocumentRefactorPair pairToRefactor, final Expression exp) {
        return (ExpressionConstants.DOCUMENT_TYPE.equals(exp.getType())
                || ExpressionConstants.DOCUMENT_LIST_TYPE.equals(exp.getType())
                || ExpressionConstants.CONSTANT_TYPE.equals(exp.getType())
                || ExpressionConstants.DOCUMENT_REF_TYPE.equals(exp.getType()))
                && exp.getName() != null
                && exp.getName()
                        .equals(
                                pairToRefactor
                                        .getOldValue()
                                        .getName());
    }

    private void removeAllDocumentReferences(final CompoundCommand cc, final DocumentRefactorPair pairToRefactor) {
        final List<Expression> expressions = retrieveExpressionsInTheContainer(pairToRefactor);
        for (final Expression exp : expressions) {
            if (isMatchingDocumentExpression(pairToRefactor, exp)) {
                removeDocumentReference(cc, exp);
            }
        }
    }

    private void removeDocumentReference(final CompoundCommand cc, final Expression exp) {
        // update name and content
        cc.append(SetCommand.create(getEditingDomain(), exp, ExpressionPackage.Literals.EXPRESSION__NAME, ""));
        cc.append(SetCommand.create(getEditingDomain(), exp, ExpressionPackage.Literals.EXPRESSION__CONTENT, ""));
        // update return type
        cc.append(SetCommand.create(getEditingDomain(), exp, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE,
                String.class.getName()));
        cc.append(SetCommand.create(getEditingDomain(), exp, ExpressionPackage.Literals.EXPRESSION__TYPE,
                ExpressionConstants.CONSTANT_TYPE));
        // update referenced document
        cc.append(RemoveCommand.create(getEditingDomain(), exp, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS,
                exp.getReferencedElements()));
    }

    private List<Expression> retrieveExpressionsInTheContainer(final DocumentRefactorPair pairToRefactor) {
        final List<Expression> expressions = ModelHelper.getAllItemsOfType(getContainer(pairToRefactor.getOldValue()),
                ExpressionPackage.Literals.EXPRESSION);
        return expressions;
    }

    @Override
    protected DocumentRefactorPair createRefactorPair(final Document newItem, final Document oldItem) {
        return new DocumentRefactorPair(newItem, oldItem);
    }

    @Override
    protected Pool getContainer(final Document oldValue) {
        return (Pool) ModelHelper.getParentProcess(oldValue);
    }
    
    private void updateContractInputDataReference(final CompoundCommand cc,
            final DocumentRefactorPair pairToRefactor,String newName) {
        ModelSearch modelSearch = new ModelSearch(Collections::emptyList);
        modelSearch.getAllItemsOfType(getContainer(pairToRefactor.getOldValue()), ContractInput.class).stream()
                .filter(input -> Objects.equals(pairToRefactor.getOldValue().getName(),
                        input.getDataReference()))
                .forEach(input -> cc.append(SetCommand.create(getEditingDomain(), input,
                        ProcessPackage.Literals.CONTRACT_INPUT__DATA_REFERENCE, newName)));
    }

}
