/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.sections.index;

import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.SearchIndex;
import org.bonitasoft.studio.refactoring.core.AbstractRefactorOperation;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;

/**
 * @author Aurelie Zara
 * @author Romain Bioteau
 */
public class RemoveSearchReferencesOperation extends AbstractRefactorOperation<SearchIndex, SearchIndex, SearchIndexRefactorPair> {

    private final Pool pool;

    public RemoveSearchReferencesOperation(final Pool pool, final SearchIndex removedSearchIndexName) {
        super(RefactoringOperationType.REMOVE);
        addItemToRefactor(null, removedSearchIndexName);
        this.pool = pool;
    }

    @Override
    protected CompoundCommand doBuildCompoundCommand(final CompoundCommand compoundCommand, final IProgressMonitor monitor) {
        for (final SearchIndexRefactorPair pairToRefactor : pairsToRefactor) {
            final List<Expression> expressions = ModelHelper.getAllItemsOfType(getContainer(pairToRefactor.getOldValue()),
                    ExpressionPackage.Literals.EXPRESSION);
            for (final Expression exp : expressions) {
                if (ExpressionConstants.SEARCH_INDEX_TYPE.equals(exp.getType()) && exp.getName().equals(pairToRefactor.getOldValueName())) {
                    // update name and content
                    compoundCommand.append(SetCommand.create(getEditingDomain(), exp, ExpressionPackage.Literals.EXPRESSION__NAME, ""));
                    compoundCommand.append(SetCommand.create(getEditingDomain(), exp, ExpressionPackage.Literals.EXPRESSION__CONTENT, ""));
                    // update return type
                    compoundCommand.append(SetCommand.create(getEditingDomain(), exp, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE,
                            String.class.getName()));
                    compoundCommand.append(SetCommand.create(getEditingDomain(), exp, ExpressionPackage.Literals.EXPRESSION__TYPE,
                            ExpressionConstants.CONSTANT_TYPE));
                    // update referenced searchIndex
                    compoundCommand.append(RemoveCommand.create(getEditingDomain(), exp, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS,
                            exp.getReferencedElements()));
                }
            }
        }
        return compoundCommand;
    }

    @Override
    protected EObject getContainer(final SearchIndex oldValue) {
        return pool;
    }

    @Override
    protected SearchIndexRefactorPair createRefactorPair(final SearchIndex newItem, final SearchIndex oldItem) {
        return new SearchIndexRefactorPair(newItem, oldItem);
    }

}
