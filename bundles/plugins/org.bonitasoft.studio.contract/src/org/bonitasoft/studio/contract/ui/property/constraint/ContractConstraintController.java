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
package org.bonitasoft.studio.contract.ui.property.constraint;

import java.util.List;

import org.bonitasoft.studio.common.databinding.CustomEMFEditObservables;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.contract.core.ContractDefinitionValidator;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.contract.ui.property.IViewerController;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;


/**
 * @author Romain Bioteau
 *
 */
public class ContractConstraintController implements IViewerController {

    private final ContractDefinitionValidator contractValidator;

    public ContractConstraintController(final ContractDefinitionValidator contractValidator) {
        this.contractValidator = contractValidator;
    }

    @Override
    public ContractConstraint add(final ColumnViewer viewer) {
        final ContractConstraint defaultConstraint = createDefaultConstraint();
        final IObservableList constraintsObservable = (IObservableList) viewer.getInput();
        constraintsObservable.add(defaultConstraint);
        viewer.editElement(defaultConstraint, 0);
        return defaultConstraint;
    }


    private ContractConstraint createDefaultConstraint() {
        final ContractConstraint contractConstraint = ProcessFactory.eINSTANCE.createContractConstraint();
        return contractConstraint;
    }

    @Override
    public void remove(final ColumnViewer viewer) {
        final IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
        final List<?> selectedInput = selection.toList();
        Contract contract = null;
        if (openConfirmation(selectedInput)) {
            for (final Object constraint : selectedInput) {
                final ContractConstraint contractConstraint = (ContractConstraint) constraint;
                contract = ModelHelper.getFirstContainerOfType(contractConstraint, Contract.class);
                contractValidator.clearMessages(contractConstraint);
                CustomEMFEditObservables.observeList(contract, ProcessPackage.Literals.CONTRACT__CONSTRAINTS).remove(contractConstraint);

            }
            if (contract != null) {
                contractValidator.validateDuplicatedConstraints(contract);
            }
        }
    }

    @Override
    public void moveUp(final ColumnViewer viewer) {
        final ContractConstraint selectedConstraint = getSelectedConstraint(viewer);
        final Contract contract = ModelHelper.getFirstContainerOfType(selectedConstraint, Contract.class);
        final IObservableList list = CustomEMFEditObservables.observeList(contract, ProcessPackage.Literals.CONTRACT__CONSTRAINTS);
        final int index = list.indexOf(selectedConstraint);
        if (index > 0) {
            list.move(index, index - 1);
        }
        //refresh button enablement
        viewer.setSelection(new StructuredSelection());
        viewer.setSelection(new StructuredSelection(selectedConstraint));
    }

    @Override
    public void moveDown(final ColumnViewer viewer) {
        final ContractConstraint selectedConstraint = getSelectedConstraint(viewer);
        final Contract contract = ModelHelper.getFirstContainerOfType(selectedConstraint, Contract.class);
        final IObservableList list = CustomEMFEditObservables.observeList(contract, ProcessPackage.Literals.CONTRACT__CONSTRAINTS);
        final int index = list.indexOf(selectedConstraint);
        if (index < list.size() - 1) {
            list.move(index, index + 1);
        }
        //refresh button enablement
        viewer.setSelection(new StructuredSelection());
        viewer.setSelection(new StructuredSelection(selectedConstraint));
    }

    protected ContractConstraint getSelectedConstraint(final ColumnViewer viewer) {
        final IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
        return (ContractConstraint) selection.getFirstElement();
    }

    protected boolean openConfirmation(final List<?> selectedInput) {
        final StringBuilder message = new StringBuilder(Messages.removeConstraintConfirmationMessage);
        for (final Object constraint : selectedInput) {
            message.append(SWT.CR);
            message.append("- " + ((ContractConstraint) constraint).getName());
        }
        return MessageDialog.openConfirm(Display.getDefault().getActiveShell(), Messages.removeConstraintConfirmationTitle, message.toString());
    }


}
