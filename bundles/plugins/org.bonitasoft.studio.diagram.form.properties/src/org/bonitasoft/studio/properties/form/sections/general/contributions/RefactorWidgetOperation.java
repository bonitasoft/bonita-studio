/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.form.sections.general.contributions;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.NamingUtils;
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
import org.bonitasoft.studio.refactoring.core.AbstractScriptExpressionRefactoringAction;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.bonitasoft.studio.refactoring.core.WidgetScriptExpressionRefactoringAction;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AbstractOverrideableCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @author Romain Bioteau
 * 
 */
public class RefactorWidgetOperation extends AbstractRefactorOperation {

    private final Widget widget;

    private final String newName;

    private Widget widgetCopy;

    public RefactorWidgetOperation(Widget widget, String newName) {
        super(RefactoringOperationType.UPDATE);
        this.widget = widget;
        this.newName = newName;
        this.widgetCopy = (Widget) FormFactory.eINSTANCE.create(widget.eClass());
        widgetCopy.setName(newName);

    }

    @Override
    protected void doExecute(IProgressMonitor monitor) {
        monitor.beginTask(Messages.updatingWidgetReferences, IProgressMonitor.UNKNOWN);
        final List<Expression> expressions = ModelHelper.getAllItemsOfType(ModelHelper.getPageFlow(widget), ExpressionPackage.Literals.EXPRESSION);
        List<Expression> expressionsList = new ArrayList<Expression>();
        for (Expression exp : expressions) {
            if (!ModelHelper.isAExpressionReferencedElement(exp)) {
                expressionsList.add(exp);
            }
        }

        if (compoundCommand == null) {
            compoundCommand = new CompoundCommand();
        }
        compoundCommand.append(SetCommand.create(domain, widget, ProcessPackage.Literals.ELEMENT__NAME, newName));
        for (Expression exp : expressionsList) {
            String fieldExpressionName = exp.getName();
            String oldExpressionName = WidgetHelper.FIELD_PREFIX + widget.getName();
            if (ExpressionConstants.FORM_FIELD_TYPE.equals(exp.getType()) && fieldExpressionName.equals(oldExpressionName)) {
                // update name and content
                compoundCommand.append(SetCommand.create(domain, exp, ExpressionPackage.Literals.EXPRESSION__NAME, WidgetHelper.FIELD_PREFIX
                        + newName));
                compoundCommand.append(SetCommand.create(domain, exp, ExpressionPackage.Literals.EXPRESSION__CONTENT, WidgetHelper.FIELD_PREFIX
                        + newName));
                compoundCommand.append(RemoveCommand.create(domain, exp, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS,
                        exp.getReferencedElements()));
                compoundCommand.append(AddCommand.create(domain, exp, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS,
                        ExpressionHelper.createDependencyFromEObject(widgetCopy)));
            }
        }
        
        if (widget.eContainer() instanceof Form && ModelHelper.formIsCustomized((Form) widget.eContainer())) {
            final String srcName = widget.getName();
 
            compoundCommand.append(SetCommand.create(domain, widget, ProcessPackage.eINSTANCE.getElement_Name(), NamingUtils.convertToId(newName)));
            
            compoundCommand.append(new AbstractOverrideableCommand(domain, "Change Id in template") {
				
				@Override
				public void doUndo() {
					FormsUtils.changeIdInTemplate((Form)widget.eContainer(), newName, srcName);
				}
				
				@Override
				public void doRedo() {
					FormsUtils.changeIdInTemplate((Form)widget.eContainer(), srcName, newName);					
				}
				
				@Override
				public void doExecute() {
					FormsUtils.changeIdInTemplate((Form)widget.eContainer(), srcName, newName);
				}
			});
        }
    }

    @Override
    protected AbstractScriptExpressionRefactoringAction getScriptExpressionRefactoringAction(EObject newValue, String oldName, String newName,
            List<Expression> scriptExpressions, List<Expression> refactoredScriptExpression, CompoundCommand compoundCommand, EditingDomain domain,
            RefactoringOperationType operationType) {
        return new WidgetScriptExpressionRefactoringAction(newValue, oldName, newName, scriptExpressions, refactoredScriptExpression, compoundCommand, domain,
                operationType);
    }

    @Override
    protected EObject getContainer() {
        return ModelHelper.getPageFlow(widget);
    }

    @Override
    protected EObject getOldValue() {
        return widget;
    }

    @Override
    protected String getOldValueName() {
        return "field_"+widget.getName();
    }

    @Override
    protected EObject getNewValue() {
        return widgetCopy;
    }

    @Override
    protected String getNewValueName() {
        return "field_"+newName;
    }

}
