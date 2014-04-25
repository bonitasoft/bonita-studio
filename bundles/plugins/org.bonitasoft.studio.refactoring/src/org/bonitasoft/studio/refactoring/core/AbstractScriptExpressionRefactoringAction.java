/**
 * Copyright (C) 2013 BonitaSoft S.A.
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

package org.bonitasoft.studio.refactoring.core;

import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.refactoring.ui.BonitaCompareEditorInput;
import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareUI;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
 * @author Aurelie Zara
 * @author Romain Bioteau
 */

public abstract class AbstractScriptExpressionRefactoringAction implements IWorkbenchWindowActionDelegate {

    private List<Expression> scriptExpressions;

    private CompoundCommand compoundCommand;

    private EditingDomain domain;

    private List<Expression> refactoredScriptExpression;

    private boolean askConfirmation = false;

    private boolean cancelled;

    private RefactoringOperationType operationType;

    private String oldName;

    private String newName;

    private EObject newValue;

    public AbstractScriptExpressionRefactoringAction(EObject newValue, String oldName, String newName, List<Expression> scriptExpressions,
            List<Expression> refactoredScriptExpression, CompoundCommand compoundCommand,
            EditingDomain domain, RefactoringOperationType operationType) {
        this.scriptExpressions = scriptExpressions;
        this.refactoredScriptExpression = refactoredScriptExpression;
        this.compoundCommand = compoundCommand;
        this.operationType = operationType;
        this.oldName = oldName;
        this.newName = newName;
        this.newValue = newValue;
    }

    @Override
    public void run(IAction action) {
        setCancelled(false);
        if (askConfirmation()) {
            final BonitaCompareEditorInput editorInput = createCompareEditorInput();
            Display.getDefault().syncExec(new Runnable() {

                @Override
                public void run() {

                    CompareUI.openCompareDialog(editorInput);
                }
            });

            if (editorInput.applyChanges()) {
                doRefactor();
            } else {
                setCancelled(true);
            }
        } else {// Apply refactor by default
            doRefactor();
        }
    }

    private BonitaCompareEditorInput createCompareEditorInput() {
        final CompareConfiguration config = new CompareConfiguration();
        config.setRightEditable(true);
        config.setLeftEditable(false);
        config.setLeftLabel(Messages.currentScript);
        config.setRightLabel(Messages.refactoredScript);
        config.setProperty(CompareConfiguration.USE_OUTLINE_VIEW, true);
        return new BonitaCompareEditorInput(config, scriptExpressions, refactoredScriptExpression, operationType, oldName,
                newName);
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    protected void doRefactor() {
        for (int i = 0; i < scriptExpressions.size(); i++) {
            Expression updatedExpression = refactoredScriptExpression.get(i);
            Expression originalExpression = scriptExpressions.get(i);
            if (!originalExpression.getContent().equals(updatedExpression.getContent())) {
                compoundCommand.append(SetCommand.create(domain, originalExpression, ExpressionPackage.Literals.EXPRESSION__CONTENT,
                        updatedExpression.getContent()));
                if (ExpressionConstants.CONDITION_TYPE.equals(originalExpression.getType())) {
                    compoundCommand.append(SetCommand.create(domain, originalExpression, ExpressionPackage.Literals.EXPRESSION__NAME,
                            updatedExpression.getContent()));
                }
            }

            if (operationType == RefactoringOperationType.REMOVE) {
                EObject reference = getReferencedObjectInScriptsOperation(originalExpression);
                if (reference != null) {
                    compoundCommand.append(RemoveCommand.create(domain, originalExpression, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS,
                            reference));
                }
            }
            if (operationType == RefactoringOperationType.UPDATE) {
                EObject referencedObject = getReferencedObjectInScriptsOperation(originalExpression);
                compoundCommand.append(RemoveCommand.create(domain, originalExpression, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS,
                        referencedObject));
                compoundCommand.append(AddCommand.create(domain, originalExpression, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS,
                        ExpressionHelper.createDependencyFromEObject(getNewValue())));
            }
        }
    }

    public EObject getNewValue() {
        return newValue;
    }

    protected abstract EObject getReferencedObjectInScriptsOperation(Expression expr);

    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
    }

    @Override
    public void dispose() {

    }

    @Override
    public void init(IWorkbenchWindow window) {

    }

    public void setAskConfirmation(boolean askConfirmation) {
        this.askConfirmation = askConfirmation;
    }

    public boolean askConfirmation() {
        return askConfirmation;
    }

    public String getOldName() {
        return oldName;
    }

    public String getNewName() {
        return newName;
    }

    public void setEditingDomain(EditingDomain domain) {
        this.domain = domain;
    }

}
