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
package org.bonitasoft.studio.common.diagram.refactoring;

import static com.google.common.collect.Iterables.filter;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.predicate.ExpressionPredicates;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.refactoring.core.AbstractRefactorOperation;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.bonitasoft.studio.refactoring.core.WidgetRefactorPair;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;

/**
 * @author Aurelie Zara
 * @author Romain Bioteau
 */
public class RemoveWidgetReferencesOperation extends AbstractRefactorOperation<Widget, Widget, WidgetRefactorPair> {

    private final EObject container;

    public RemoveWidgetReferencesOperation(final EObject container, final Widget widgetToRemove) {
        super(RefactoringOperationType.REMOVE);
        this.container = container;
        addItemToRefactor(null, widgetToRemove);
    }

    @Override
    protected CompoundCommand doBuildCompoundCommand(final CompoundCommand compoundCommand, final IProgressMonitor monitor) {
        monitor.beginTask(Messages.removingWidgetReferences, IProgressMonitor.UNKNOWN);
        for (final Expression exp : filter(ModelHelper.getAllElementOfTypeIn(container, Expression.class),
                ExpressionPredicates.withExpressionType(ExpressionConstants.FORM_FIELD_TYPE))) {
            for (final WidgetRefactorPair pairToRefactor : pairsToRefactor) {
                if (exp.getName().equals(pairToRefactor.getNewValueName())) {
                    // update name and content
                    compoundCommand.append(SetCommand.create(getEditingDomain(), exp, ExpressionPackage.Literals.EXPRESSION__NAME, ""));
                    compoundCommand.append(SetCommand.create(getEditingDomain(), exp, ExpressionPackage.Literals.EXPRESSION__CONTENT, ""));
                    // update return type
                    compoundCommand.append(SetCommand.create(getEditingDomain(), exp, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE,
                            String.class.getName()));
                    compoundCommand.append(SetCommand
                            .create(getEditingDomain(), exp, ExpressionPackage.Literals.EXPRESSION__TYPE, ExpressionConstants.CONSTANT_TYPE));
                    // update referenced data
                    compoundCommand.append(RemoveCommand.create(getEditingDomain(), exp, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS,
                            exp.getReferencedElements()));
                }
            }
        }
        return compoundCommand;
    }

    @Override
    protected EObject getContainer(final Widget oldValue) {
        return container;
    }

    @Override
    protected WidgetRefactorPair createRefactorPair(final Widget newItem, final Widget oldItem) {
        return new WidgetRefactorPair(newItem, oldItem);
    }

}
