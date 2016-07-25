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
package org.bonitasoft.studio.properties.form.sections.general.contributions;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.emf.tools.WidgetHelper;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.properties.sections.forms.FormsUtils;
import org.bonitasoft.studio.refactoring.core.AbstractRefactorOperation;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.bonitasoft.studio.refactoring.core.WidgetRefactorPair;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AbstractOverrideableCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;

/**
 * @author Romain Bioteau
 */
public class RefactorWidgetOperation extends AbstractRefactorOperation<Widget, Widget, WidgetRefactorPair> {

    public RefactorWidgetOperation(final Widget widget, final String newName) {
        super(RefactoringOperationType.UPDATE);
        final Widget widgetCopy = (Widget) FormFactory.eINSTANCE.create(widget.eClass());
        widgetCopy.setName(newName);
        addItemToRefactor(widgetCopy, widget);

    }

    @Override
    protected CompoundCommand doBuildCompoundCommand(final CompoundCommand compoundCommand, final IProgressMonitor monitor) {
        monitor.beginTask(Messages.updatingWidgetReferences, IProgressMonitor.UNKNOWN);
        for (final WidgetRefactorPair pairToRefactor : pairsToRefactor) {
            final Widget widget = pairToRefactor.getOldValue();
            final String newReferenceName = pairToRefactor.getNewValueName();
            final List<Expression> expressions = ModelHelper.getAllItemsOfType(ModelHelper.getPageFlow(widget), ExpressionPackage.Literals.EXPRESSION);
            final List<Expression> expressionsList = new ArrayList<Expression>();
            for (final Expression exp : expressions) {
                if (!ModelHelper.isReferencedElementIsInExpression(exp)) {
                    expressionsList.add(exp);
                }
            }
            compoundCommand
                    .append(SetCommand.create(getEditingDomain(), widget, ProcessPackage.Literals.ELEMENT__NAME, pairToRefactor.getNewValue().getName()));

            for (final Expression exp : expressionsList) {
                final String fieldExpressionName = exp.getName();
                final String oldExpressionName = WidgetHelper.FIELD_PREFIX + widget.getName();
                if (ExpressionConstants.FORM_FIELD_TYPE.equals(exp.getType()) && fieldExpressionName.equals(oldExpressionName)) {
                    // update name and content
                    compoundCommand.append(SetCommand.create(getEditingDomain(), exp, ExpressionPackage.Literals.EXPRESSION__NAME, newReferenceName));
                    compoundCommand.append(SetCommand.create(getEditingDomain(), exp, ExpressionPackage.Literals.EXPRESSION__CONTENT, newReferenceName));
                    compoundCommand.append(RemoveCommand.create(getEditingDomain(), exp, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS,
                            exp.getReferencedElements()));
                    compoundCommand.append(AddCommand.create(getEditingDomain(), exp, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS,
                            ExpressionHelper.createDependencyFromEObject(pairToRefactor.getNewValue())));
                }
            }

            if (widget.eContainer() instanceof Form && ModelHelper.formIsCustomized((Form) widget.eContainer())) {
                final String srcName = widget.getName();
                compoundCommand.append(new AbstractOverrideableCommand(getEditingDomain(), "Change Id in template") {

                    @Override
                    public void doUndo() {
                        FormsUtils.changeIdInTemplate((Form) widget.eContainer(), pairToRefactor.getNewValue().getName(), srcName);
                    }

                    @Override
                    public void doRedo() {
                        FormsUtils.changeIdInTemplate((Form) widget.eContainer(), srcName, pairToRefactor.getNewValue().getName());
                    }

                    @Override
                    public void doExecute() {
                        FormsUtils.changeIdInTemplate((Form) widget.eContainer(), srcName, pairToRefactor.getNewValue().getName());
                    }

                    @Override
                    public boolean doCanExecute() {
                        return true;
                    }
                });
            }
        }
        return compoundCommand;
    }

    @Override
    protected EObject getContainer(final Widget widget) {
        return ModelHelper.getPageFlow(widget);
    }

    @Override
    protected WidgetRefactorPair createRefactorPair(final Widget newItem, final Widget oldItem) {
        return new WidgetRefactorPair(newItem, oldItem);
    }

}
