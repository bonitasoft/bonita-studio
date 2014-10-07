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
package org.bonitasoft.studio.contract.ui.property;

import java.util.List;

import org.bonitasoft.studio.contract.core.ContractDefinitionValidator;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;


/**
 * @author Romain Bioteau
 *
 */
public class ContractInputController {

    private final ContractDefinitionValidator contractValidator;

    public ContractInputController(final ContractDefinitionValidator contractValidator) {
        this.contractValidator = contractValidator;
    }

    public ContractInput addInput(final TableViewer viewer) {
        final IObservableList input = (IObservableList) viewer.getInput();
        final ContractInput defaultInput = createDefaultInput();
        input.add(defaultInput);
        viewer.editElement(defaultInput, 0);
        return defaultInput;
    }

    private ContractInput createDefaultInput() {
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.setType(ContractInputType.TEXT);
        contractInput.setMapping(ProcessFactory.eINSTANCE.createContractInputMapping());
        return contractInput;
    }

    public void removeInput(final TableViewer viewer) {
        final IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
        final IObservableList viewerInput = (IObservableList) viewer.getInput();
        final List<?> selectedInput = selection.toList();
        Contract contract = null;
        for (final Object input : selectedInput) {
            contract = (Contract) ((ContractInput) input).eContainer();
            contractValidator.clearMessages(input);
        }
        viewerInput.removeAll(selectedInput);
        if (contract != null) {
            contractValidator.validateDuplicatedInputs(contract);
        }
    }
}
