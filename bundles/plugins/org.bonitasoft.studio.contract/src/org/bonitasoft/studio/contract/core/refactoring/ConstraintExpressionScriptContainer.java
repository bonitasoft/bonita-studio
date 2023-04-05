/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.contract.core.refactoring;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.refactoring.core.RefactorPair;
import org.bonitasoft.studio.refactoring.core.script.IScriptRefactoringOperation;
import org.bonitasoft.studio.refactoring.core.script.IScriptRefactoringOperationFactory;
import org.bonitasoft.studio.refactoring.core.script.ReferenceDiff;
import org.bonitasoft.studio.refactoring.core.script.ScriptContainer;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

public class ConstraintExpressionScriptContainer extends ScriptContainer<ContractConstraint> {

    private final IScriptRefactoringOperationFactory scriptRefactoringOperationFactory;

    public ConstraintExpressionScriptContainer(final ContractConstraint constraint,
            final IScriptRefactoringOperationFactory scriptRefactoringOperationFactory) {
        super(constraint, ProcessPackage.Literals.CONTRACT_CONSTRAINT__EXPRESSION, null);
        this.scriptRefactoringOperationFactory = scriptRefactoringOperationFactory;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.refactoring.core.groovy.ScriptContainer#getName()
     */
    @Override
    public String getName() {
        return (String) getModelElement().eGet(ProcessPackage.Literals.CONTRACT_CONSTRAINT__NAME);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.refactoring.core.groovy.ScriptContainer#updateScript(java.util.List,
     * org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void updateScript(final List<ReferenceDiff> referenceDiffs, final IProgressMonitor monitor)
            throws InvocationTargetException, InterruptedException {
        final IScriptRefactoringOperation scriptRefactoringOperation = scriptRefactoringOperationFactory
                .createScriptOperationFactory(getScript(),
                        referenceDiffs);
        scriptRefactoringOperation.run(monitor);
        setNewScript(scriptRefactoringOperation.getRefactoredScript());
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.refactoring.core.groovy.ScriptContainer#updateDependencies(java.util.List)
     */
    @Override
    public CompoundCommand updateDependencies(final TransactionalEditingDomain editingDomain,
            final List<? extends RefactorPair<? extends EObject, ? extends EObject>> pairsToRefactor) {
        final CompoundCommand compoundCommand = new CompoundCommand();
        final ContractConstraint constraint = getModelElement();
        for (final String inputName : constraint.getInputNames()) {
            for (final RefactorPair<? extends EObject, ? extends EObject> pair : pairsToRefactor) {
                final String oldValueName = pair.getOldValueName();
                if (oldValueName.equals(inputName) && !oldValueName.equals(pair.getNewValueName())) {
                    compoundCommand.append(RemoveCommand.create(editingDomain, constraint,
                            ProcessPackage.Literals.CONTRACT_CONSTRAINT__INPUT_NAMES,
                            inputName));
                    compoundCommand.append(AddCommand.create(editingDomain, constraint,
                            ProcessPackage.Literals.CONTRACT_CONSTRAINT__INPUT_NAMES,
                            pair.getNewValueName()));
                }
            }
        }
        return compoundCommand;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.refactoring.core.groovy.ScriptContainer#removeDependencies(java.util.List)
     */
    @Override
    public CompoundCommand removeDependencies(final TransactionalEditingDomain editingDomain,
            final List<? extends RefactorPair<? extends EObject, ? extends EObject>> pairsToRefactor) {
        final CompoundCommand compoundCommand = new CompoundCommand();
        final ContractConstraint constraint = getModelElement();
        for (final String inputName : constraint.getInputNames()) {
            for (final RefactorPair<? extends EObject, ? extends EObject> pair : pairsToRefactor) {
                final String oldValueName = pair.getOldValueName();
                if (oldValueName.equals(inputName)) {
                    compoundCommand.append(RemoveCommand.create(editingDomain, constraint,
                            ProcessPackage.Literals.CONTRACT_CONSTRAINT__INPUT_NAMES,
                            inputName));
                }
            }
        }
        return compoundCommand;
    }

}
