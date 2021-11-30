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

package org.bonitasoft.studio.refactoring.core;

import java.util.List;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.refactoring.core.script.ScriptContainer;
import org.bonitasoft.studio.refactoring.ui.BonitaCompareEditorInput;
import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareUI;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
 * @author Aurelie Zara
 * @author Romain Bioteau
 */

public class ScriptRefactoringAction<T extends RefactorPair<? extends EObject, ? extends EObject>>
        implements IWorkbenchWindowActionDelegate {

    private final List<ScriptContainer<?>> scriptExpressions;
    private final CompoundCommand compoundCommand;
    private boolean askConfirmation = false;
    private boolean cancelled;
    private final RefactoringOperationType operationType;
    protected List<T> pairsToRefactor;
    private final TransactionalEditingDomain domain;

    public ScriptRefactoringAction(final List<T> pairsToRefactor,
            final List<ScriptContainer<?>> scriptExpressions,
            final CompoundCommand compoundCommand,
            final TransactionalEditingDomain domain,
            final RefactoringOperationType operationType) {
        this.scriptExpressions = scriptExpressions;
        this.compoundCommand = compoundCommand;
        this.operationType = operationType;
        this.pairsToRefactor = pairsToRefactor;
        this.domain = domain;
    }

    @Override
    public void run(final IAction action) {
        setCancelled(false);
        if (askConfirmation()) {
            final BonitaCompareEditorInput editorInput = createCompareEditorInput();
            Display.getDefault().syncExec(new Runnable() {

                @Override
                public void run() {
                    CompareUI.openCompareDialog(editorInput);
                    editorInput.dispose();
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
        String oldNames = "";
        String newNames = "";
        boolean canBeContainedInscript = true;
        for (final RefactorPair<?, ?> pairRefactor : pairsToRefactor) {
            oldNames += oldNames.isEmpty() ? pairRefactor.getOldValueName() : "," + pairRefactor.getOldValueName();
            newNames += newNames.isEmpty() ? pairRefactor.getNewValueName() : "," + pairRefactor.getNewValueName();
            canBeContainedInscript = canBeContainedInscript && pairRefactor.canBeContainedInScript();
        }
        return new BonitaCompareEditorInput(config, scriptExpressions, operationType, oldNames,
                newNames, canBeContainedInscript);
    }

    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }

    protected void doRefactor() {
        for (final ScriptContainer<?> scriptContainer : scriptExpressions) {
            final CompoundCommand applyUpdate = scriptContainer.applyUpdate(domain);
            if (!applyUpdate.isEmpty()) {
                compoundCommand.append(applyUpdate);
            }

            if (operationType == RefactoringOperationType.REMOVE) {
                compoundCommand.append(scriptContainer.removeDependencies(domain, pairsToRefactor));
            }
            if (operationType == RefactoringOperationType.UPDATE) {
                final CompoundCommand updateDependencies = scriptContainer.updateDependencies(domain, pairsToRefactor);
                if (!updateDependencies.isEmpty()) {
                    compoundCommand.append(updateDependencies);
                }
            }
        }
    }

    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void selectionChanged(final IAction action, final ISelection selection) {
    }

    @Override
    public void dispose() {

    }

    @Override
    public void init(final IWorkbenchWindow window) {

    }

    public void setAskConfirmation(final boolean askConfirmation) {
        this.askConfirmation = askConfirmation;
    }

    public boolean askConfirmation() {
        return askConfirmation;
    }

}
