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
package org.bonitasoft.studio.contract.ui.property.input.edit;

import static com.google.common.base.Preconditions.checkArgument;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.contract.core.refactoring.RefactorContractInputOperation;
import org.bonitasoft.studio.contract.core.validation.ContractDefinitionValidator;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractContainer;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;
import org.eclipse.ui.views.properties.PropertyEditingSupport;

/**
 * @author Romain Bioteau
 */
public class InputNamePropertyEditingSupport extends PropertyEditingSupport {

    private final ContractDefinitionValidator contractDefinitionValidator;

    public InputNamePropertyEditingSupport(final AdapterFactoryContentProvider propertySourceProvider,
            final ColumnViewer viewer,
            final ContractDefinitionValidator contractDefinitionValidator) {
        super(viewer, propertySourceProvider, ProcessPackage.Literals.CONTRACT_INPUT__NAME.getName());
        this.contractDefinitionValidator = contractDefinitionValidator;
    }

    @Override
    protected void setValue(final Object element, final Object value) {
        checkArgument(element instanceof ContractInput);
        final ContractInput input = (ContractInput) element;
        final String oldName = input.getName();
        super.setValue(element, value);
        final String newName = input.getName();
        //   final IStatus status = contractDefinitionValidator.validate(ModelHelper.getFirstContainerOfType(input, Contract.class));
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
        contractDefinitionValidator.validate(ModelHelper.getFirstContainerOfType(input, Contract.class));
        //recompute error decorator label for duplicated input
        getViewer().refresh(true);
    }

    @Override
    protected void initializeCellEditorValue(final CellEditor cellEditor, final ViewerCell cell) {
        super.initializeCellEditorValue(cellEditor, cell);
        final Text textControl = (Text) cellEditor.getControl();
        textControl.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBotConstants.SWTBOT_ID_INPUT_NAME_TEXTEDITOR);
    }

}
