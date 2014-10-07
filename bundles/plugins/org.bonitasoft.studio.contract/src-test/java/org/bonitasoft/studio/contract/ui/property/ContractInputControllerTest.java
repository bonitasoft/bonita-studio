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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.bonitasoft.studio.contract.core.ContractDefinitionValidator;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author Romain Bioteau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ContractInputControllerTest {

    private ContractInputController contractInputController;

    @Mock
    private TableViewer viewer ;

    private WritableList inputList;

    @Mock
    private ContractDefinitionValidator contractDefinitionValidator;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        contractInputController = new ContractInputController(contractDefinitionValidator);
        inputList = new WritableList(new Realm() {

            @Override
            public boolean isCurrent() {
                return true;
            }
        });
        when(viewer.getInput()).thenReturn(inputList);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @SuppressWarnings("unchecked")
    @Test
    public void should_addInput_add_a_new_contract_input_in_ObservableList() throws Exception {
        assertThat(inputList.isEmpty()).isTrue();
        final ContractInput input = contractInputController.addInput(viewer);
        assertThat(inputList).containsOnly(input);
        assertThat(input.getName()).isNullOrEmpty();
        assertThat(input.getType()).isEqualTo(ContractInputType.TEXT);
        verify(viewer).editElement(input, 0);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void should_removeInput_remove_selected_input_from_ObservableList() throws Exception {
        final ContractInput input1 = ProcessFactory.eINSTANCE.createContractInput();
        final ContractInput input2 = ProcessFactory.eINSTANCE.createContractInput();
        final ContractInput input3 = ProcessFactory.eINSTANCE.createContractInput();
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        contract.getInputs().add(input1);
        contract.getInputs().add(input2);
        contract.getInputs().add(input3);
        inputList.add(input1);
        inputList.add(input2);
        inputList.add(input3);

        when(viewer.getSelection()).thenReturn(new StructuredSelection(Arrays.asList(input2, input3)));
        contractInputController.removeInput(viewer);
        assertThat(inputList).containsOnly(input1);
        verify(contractDefinitionValidator).clearMessages(input2);
        verify(contractDefinitionValidator).clearMessages(input3);
        verify(contractDefinitionValidator).validateDuplicatedInputs(contract);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void should_removeInput_never_refresh_duplicated_inputs() throws Exception {
        final ContractInput input1 = ProcessFactory.eINSTANCE.createContractInput();
        final ContractInput input2 = ProcessFactory.eINSTANCE.createContractInput();
        final ContractInput input3 = ProcessFactory.eINSTANCE.createContractInput();
        inputList.add(input1);
        inputList.add(input2);
        inputList.add(input3);

        when(viewer.getSelection()).thenReturn(new StructuredSelection(Arrays.asList(input2, input3)));
        contractInputController.removeInput(viewer);
        assertThat(inputList).containsOnly(input1);
        verify(contractDefinitionValidator).clearMessages(input2);
        verify(contractDefinitionValidator).clearMessages(input3);
        verify(contractDefinitionValidator, never()).validateDuplicatedInputs(any(Contract.class));
    }

}
