/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.SearchIndex;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.refactoring.core.AbstractRefactorOperation;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @author Aurelie Zara
 * @author Romain Bioteau
 */
public class RefactorSearchIndexOperation extends AbstractRefactorOperation<SearchIndex, SearchIndex, SearchIndexRefactorPair> {

    private final Pool pool;

    public RefactorSearchIndexOperation(final Pool pool) {
        super(RefactoringOperationType.UPDATE);
        this.pool = pool;
    }

    /**
     * @param exp
     * @return
     */
    private boolean isExpressionIsReferenceToCurrentSearchIndex(final SearchIndex searchIndex, final Expression exp) {
        return exp.getType().equals(ExpressionConstants.SEARCH_INDEX_TYPE)
                && exp.getName() != null
                && exp.getName().equals(searchIndex.getName().getName());
    }

    @Override
    protected CompoundCommand doBuildCompoundCommand(final CompoundCommand compoundCommand, final IProgressMonitor monitor) {
        monitor.beginTask(Messages.updatingSearchIndexReferences, IProgressMonitor.UNKNOWN);
        for (final SearchIndexRefactorPair pairTorefactor : pairsToRefactor) {
            final String newValue = pairTorefactor.getNewValue().getName().getContent();
            if (newValue != null) {
                monitor.beginTask(Messages.updatingSearchIndexReferences, IProgressMonitor.UNKNOWN);
                final EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(pool);
                final List<Expression> expressions = ModelHelper.getAllItemsOfType(pool, ExpressionPackage.Literals.EXPRESSION);
                for (final Expression exp : expressions) {
                    if (isExpressionIsReferenceToCurrentSearchIndex(pairTorefactor.getOldValue(), exp)) {
                        compoundCommand.append(SetCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__NAME, newValue));
                        compoundCommand.append(SetCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__CONTENT, newValue));
                        if (exp.getReferencedElements() != null && !exp.getReferencedElements().isEmpty()) {
                            compoundCommand.append(RemoveCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS,
                                    exp.getReferencedElements()));
                            compoundCommand.append(AddCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS,
                                    ExpressionHelper.createDependencyFromEObject(ExpressionHelper.createConstantExpression(newValue, String.class.getName()))));
                        }
                    }

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
