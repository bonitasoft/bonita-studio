/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.diagram.refactoring;

import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.emf.tools.WidgetHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.refactoring.core.AbstractRefactorOperation;
import org.bonitasoft.studio.refactoring.core.AbstractScriptExpressionRefactoringAction;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.bonitasoft.studio.refactoring.core.WidgetScriptExpressionRefactoringAction;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @author Aurelie Zara
 * @author Romain Bioteau
 * 
 */
public class RemoveWidgetReferencesOperation extends AbstractRefactorOperation {

    private Widget widgetToRemove;

    private EObject container;

    private EditingDomain editingDomain;

    public RemoveWidgetReferencesOperation(EObject container, Widget widgetToRemove) {
        super(RefactoringOperationType.REMOVE);
        this.container = container;
        this.widgetToRemove = widgetToRemove;
    }

    @Override
    protected void doExecute(IProgressMonitor monitor) {
        monitor.beginTask(Messages.removingWidgetReferences, IProgressMonitor.UNKNOWN);
        List<Expression> expressions = ModelHelper.getAllItemsOfType(container, ExpressionPackage.Literals.EXPRESSION);
        for (Expression exp : expressions) {
            if (ExpressionConstants.FORM_FIELD_TYPE.equals(exp.getType()) && exp.getName().equals(getNewValueName())) {
                // update name and content
                compoundCommand.append(SetCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__NAME, ""));
                compoundCommand.append(SetCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__CONTENT, ""));
                // update return type
                compoundCommand.append(SetCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE, String.class.getName()));
                compoundCommand.append(SetCommand
                        .create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__TYPE, ExpressionConstants.CONSTANT_TYPE));
                // update referenced data
                compoundCommand.append(RemoveCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS,
                        exp.getReferencedElements()));
            }
        }
    }

    @Override
    protected EObject getContainer() {
        return container;
    }

    @Override
    protected EObject getOldValue() {
        return widgetToRemove;
    }

    @Override
    protected EObject getNewValue() {
        return null;
    }

    @Override
    protected String getOldValueName() {
        return WidgetHelper.FIELD_PREFIX + widgetToRemove.getName();
    }

    @Override
    protected String getNewValueName() {
        return AbstractRefactorOperation.EMPTY_VALUE;
    }

    @Override
    protected AbstractScriptExpressionRefactoringAction getScriptExpressionRefactoringAction(EObject newValue, String oldName, String newName,
            List<Expression> scriptExpressions, List<Expression> refactoredScriptExpression, CompoundCommand compoundCommand, EditingDomain domain,
            RefactoringOperationType operationType) {
        return new WidgetScriptExpressionRefactoringAction(getNewValue(), getOldValueName(), getNewValueName(), scriptExpressions,
                refactoredScriptExpression, compoundCommand, domain,
                operationType);
    }

}
