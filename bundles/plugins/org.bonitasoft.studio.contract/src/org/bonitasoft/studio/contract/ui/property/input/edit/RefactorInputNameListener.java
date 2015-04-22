/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.contract.ui.property.input.edit;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.contract.core.refactoring.RefactorContractInputOperation;
import org.bonitasoft.studio.model.process.ContractContainer;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

public class RefactorInputNameListener implements ICellEditorListener {

    private final ContractInput input;
    private final Text textEditor;

    public RefactorInputNameListener(final ContractInput input, final Text textEditor) {
        this.input = input;
        this.textEditor = textEditor;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ICellEditorListener#applyEditorValue()
     */
    @Override
    public void applyEditorValue() {
        final String oldValue = input.getName();
        final String newValue = textEditor.getText();
        refactorInput(oldValue, newValue, input);
    }

    private void refactorInput(final String oldName, final String newName, final ContractInput input) {
        if (oldName != null && !oldName.equals(newName)) {
            final RefactorContractInputOperation refactorContractInputOperation = new RefactorContractInputOperation(ModelHelper.getFirstContainerOfType(input,
                    ContractContainer.class), RefactoringOperationType.UPDATE);
            refactorContractInputOperation.setEditingDomain(TransactionUtil.getEditingDomain(input));
            refactorContractInputOperation.setAskConfirmation(true);
            final ContractInput oldItem = EcoreUtil.copy(input);
            oldItem.setName(oldName);
            refactorContractInputOperation.addItemToRefactor(input, oldItem);
            final IProgressService service = PlatformUI.getWorkbench().getProgressService();
            try {
                service.busyCursorWhile(refactorContractInputOperation);
            } catch (InvocationTargetException | InterruptedException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ICellEditorListener#cancelEditor()
     */
    @Override
    public void cancelEditor() {
        // do nothing
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ICellEditorListener#editorValueChanged(boolean, boolean)
     */
    @Override
    public void editorValueChanged(final boolean oldValidState, final boolean newValidState) {
        // do nothing
    }

}
