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
package org.bonitasoft.studio.properties.sections.iteration;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.MultiInstantiable;
import org.bonitasoft.studio.refactoring.core.RefactorDataOperation;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

public class IteratorRefactorOperationFactory {

    public RefactorDataOperation createRefactorOperation(TransactionalEditingDomain domain,  
            Expression expression,
            String oldValue,
            String newValue) {
        final MultiInstantiable parentFlowElement = ModelHelper.getFirstContainerOfType(expression, MultiInstantiable.class);
        final Data oldData = ExpressionHelper.dataFromIteratorExpression(
                parentFlowElement, expression, mainProcess(parentFlowElement));
        oldData.setName(oldValue);
        final RefactorDataOperation refactorOperation = new RefactorDataOperation(RefactoringOperationType.UPDATE);
        refactorOperation.setEditingDomain(domain);
        refactorOperation.setAskConfirmation(true);
        refactorOperation.setDataContainer(ModelHelper.getFirstContainerOfType(expression, DataAware.class));
        refactorOperation.addItemToRefactor(dataWithNewName(oldData,newValue), oldData);
        return refactorOperation;
    }

    private MainProcess mainProcess(final EObject parentFlowElement) {
        return ModelHelper.getMainProcess(parentFlowElement);
    }

    private Data dataWithNewName(final Data data, final String newValue) {
        final Data copy = EcoreUtil.copy(data);
        copy.setName(newValue);
        return copy;
    }

}
