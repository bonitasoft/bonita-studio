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
package org.bonitasoft.studio.contract.ui.property.input;

import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Sets.newHashSet;
import static org.bonitasoft.studio.common.emf.tools.ModelHelper.getAllElementOfTypeIn;

import java.util.List;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.jface.databinding.CustomEMFEditObservables;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.contract.ui.property.IViewerController;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

import com.google.common.base.Function;

/**
 * @author Romain Bioteau
 */
public class ContractInputController implements IViewerController {

    @Override
    public ContractInput add(final ColumnViewer viewer) {
        final IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();

        final IObservableValue contractObservable = (IObservableValue) viewer.getInput();
        final ContractInput parentInput = (ContractInput) selection.getFirstElement();
        final ContractInput defaultInput = createDefaultInput((Contract) contractObservable.getValue());
        if (parentInput != null) {
            final EObject eContainer = parentInput.eContainer();
            if (eContainer instanceof ContractInput) {
                CustomEMFEditObservables.observeList(eContainer, ProcessPackage.Literals.CONTRACT_INPUT__INPUTS).add(defaultInput);
            } else {
                Assert.isLegal(eContainer instanceof Contract);
                CustomEMFEditObservables.observeList(eContainer, ProcessPackage.Literals.CONTRACT__INPUTS).add(defaultInput);
            }
        } else {
            CustomEMFEditObservables.observeList((EObject) contractObservable.getValue(), ProcessPackage.Literals.CONTRACT__INPUTS).add(defaultInput);
        }
        viewer.editElement(defaultInput, 0);
        return defaultInput;
    }

    public ContractInput addChildInput(final ColumnViewer viewer) {
        final IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
        Assert.isLegal(!selection.isEmpty());

        final ContractInput parentInput = (ContractInput) selection.getFirstElement();
        final ContractInput defaultInput = createDefaultInput(ModelHelper.getFirstContainerOfType(parentInput, Contract.class));
        CustomEMFEditObservables.observeList(parentInput, ProcessPackage.Literals.CONTRACT_INPUT__INPUTS).add(defaultInput);
        Display.getDefault().asyncExec(new Runnable() {

            @Override
            public void run() {
                if (viewer != null && viewer.getControl() != null && !viewer.getControl().isDisposed()) {
                    viewer.editElement(defaultInput, 0);
                }
            }
        });

        return defaultInput;
    }

    private ContractInput createDefaultInput(final Contract contract) {
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.setName(defaultContractInputName(contract));
        contractInput.setType(ContractInputType.TEXT);
        contractInput.setMapping(ProcessFactory.eINSTANCE.createContractInputMapping());
        return contractInput;
    }

    private String defaultContractInputName(final Contract contract) {
        return NamingUtils.generateNewName(
                newHashSet(transform(getAllElementOfTypeIn(contract, ContractInput.class), toInputName())), "input");
    }

    private Function<ContractInput, String> toInputName() {
        return new Function<ContractInput, String>() {

            @Override
            public String apply(final ContractInput input) {
                return input.getName();
            }
        };
    }

    @Override
    public void remove(final ColumnViewer viewer) {
        final IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
        final List<?> selectedInput = selection.toList();
        Contract contract = null;
        if (openConfirmation(selectedInput)) {
            for (final Object input : selectedInput) {
                final ContractInput contractInput = (ContractInput) input;
                contract = ModelHelper.getFirstContainerOfType(contractInput, Contract.class);
                //Parent input has been removed in current selection
                if (contract == null) {
                    continue;
                }
                final EObject eContainer = contractInput.eContainer();
                if (eContainer instanceof ContractInput) {
                    CustomEMFEditObservables.observeList(eContainer, ProcessPackage.Literals.CONTRACT_INPUT__INPUTS).remove(contractInput);
                } else {
                    Assert.isLegal(eContainer instanceof Contract);
                    CustomEMFEditObservables.observeList(eContainer, ProcessPackage.Literals.CONTRACT__INPUTS).remove(contractInput);
                }
            }
            if (contract != null) {
                viewer.refresh(true);
            }
        }
    }

    protected boolean openConfirmation(final List<?> selectedInput) {
        final StringBuilder message = new StringBuilder(Messages.removeInputConfirmationMessage);
        for (final Object input : selectedInput) {
            message.append(SWT.CR);
            message.append("- " + ((ContractInput) input).getName());
        }
        return FileActionDialog.getDisablePopup() ? true : MessageDialog.openConfirm(Display.getDefault().getActiveShell(),
                Messages.removeInputConfirmationTitle, message.toString());
    }

    @Override
    public void moveUp(final ColumnViewer viewer) {
        //Not implemented yet
    }

    @Override
    public void moveDown(final ColumnViewer viewer) {
        //Not implemented yet
    }

}
