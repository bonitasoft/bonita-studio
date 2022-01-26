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

import static com.google.common.base.Preconditions.checkArgument;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.process.ContractContainer;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.bonitasoft.studio.refactoring.core.emf.IRefactorOperationFactory;
import org.bonitasoft.studio.refactoring.core.script.GroovyScriptRefactoringOperationFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

public class ContractInputRefactorOperationFactory implements IRefactorOperationFactory {

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.refactoring.core.emf.IRefactorOperationFactory#createRefactorOperation(org.eclipse.emf.edit.domain.EditingDomain,
     * org.eclipse.emf.ecore.EObject, java.lang.Object)
     */
    @Override
    public RefactorContractInputOperation createRefactorOperation(
            final TransactionalEditingDomain domain, final EObject item, final Object newValue) {
        checkArgument(item instanceof ContractInput);
        final RefactorContractInputOperation refactorContractInputOperation = new RefactorContractInputOperation(
                ModelHelper.getFirstContainerOfType(item,
                        ContractContainer.class),
                new GroovyScriptRefactoringOperationFactory(), RefactoringOperationType.UPDATE);
        refactorContractInputOperation.setEditingDomain(domain);
        refactorContractInputOperation.setAskConfirmation(true);
        refactorContractInputOperation.addItemToRefactor(inputWithNewName((ContractInput) item, newValue),
                (ContractInput) item);
        return refactorContractInputOperation;
    }

    private ContractInput inputWithNewName(final ContractInput input, final Object newValue) {
        final ContractInput copy = EcoreUtil.copy(input);
        if (newValue instanceof String) {
            copy.setName((String) newValue);
        }
        if (newValue instanceof ContractInputType) {
            copy.setType((ContractInputType) newValue);
        }
        return copy;
    }

}
