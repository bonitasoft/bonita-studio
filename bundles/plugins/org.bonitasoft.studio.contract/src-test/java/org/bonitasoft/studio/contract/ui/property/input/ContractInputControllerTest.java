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
package org.bonitasoft.studio.contract.ui.property.input;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.contract.core.validation.ContractDefinitionValidator;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.assertions.ContractAssert;
import org.bonitasoft.studio.model.process.assertions.ContractInputAssert;
import org.bonitasoft.studio.swt.AbstractSWTTestCase;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
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
public class ContractInputControllerTest extends AbstractSWTTestCase {

    private ContractInputController contractInputController;

    @Mock
    private TreeViewer viewer;

    private WritableValue observableValue;

    @Mock
    private ContractDefinitionValidator contractDefinitionValidator;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        createDisplayAndRealm();
        FileActionDialog.setDisablePopup(true);
        contractInputController = spy(new ContractInputController(contractDefinitionValidator));
        observableValue = new WritableValue(Realm.getDefault());
        when(viewer.getInput()).thenReturn(observableValue);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        dispose();
    }


    @Test
    public void should_addInput_add_a_new_root_contract_input_if_no_selection_in_viewer() throws Exception {
        when(viewer.getSelection()).thenReturn(new StructuredSelection());
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        observableValue.setValue(contract);
        final ContractInput input = contractInputController.add(viewer);
        assertThat(contract.getInputs()).containsOnly(input);
        assertThat(input.getName()).isNullOrEmpty();
        assertThat(input.getType()).isEqualTo(ContractInputType.TEXT);
        verify(viewer).editElement(input, 0);
    }

    @Test
    public void should_addInput_add_a_new_root_contract_input_if_root_selection_in_viewer() throws Exception {
        final ContractInput input1 = ProcessFactory.eINSTANCE.createContractInput();
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        contract.getInputs().add(input1);
        observableValue.setValue(contract);

        when(viewer.getSelection()).thenReturn(new StructuredSelection(Arrays.asList(input1)));

        final ContractInput input = contractInputController.add(viewer);
        ContractAssert.assertThat(contract).hasInputs(input1, input);
    }

    @Test
    public void should_addInput_add_a_new_contract_input_sibling_selection_in_viewer() throws Exception {
        final ContractInput input1 = ProcessFactory.eINSTANCE.createContractInput();
        final ContractInput input2 = ProcessFactory.eINSTANCE.createContractInput();
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        contract.getInputs().add(input1);
        input1.getInputs().add(input2);
        observableValue.setValue(contract);

        when(viewer.getSelection()).thenReturn(new StructuredSelection(Arrays.asList(input2)));

        final ContractInput input = contractInputController.add(viewer);
        ContractInputAssert.assertThat(input1).hasInputs(input2, input);
    }

    @Test
    public void should_addChildInput_add_a_new_child_contract_input_to_selection_in_viewer() throws Exception {
        final ContractInput input1 = ProcessFactory.eINSTANCE.createContractInput();
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        contract.getInputs().add(input1);
        observableValue.setValue(contract);

        when(viewer.getSelection()).thenReturn(new StructuredSelection(Arrays.asList(input1)));

        final ContractInput input = contractInputController.addChildInput(viewer);
        ContractInputAssert.assertThat(input1).hasInputs(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_addChildInput_throw_IllegalArgumentException_if_selection_in_viewer_is_empty() throws Exception {
        final ContractInput input1 = ProcessFactory.eINSTANCE.createContractInput();
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        contract.getInputs().add(input1);
        observableValue.setValue(contract);

        when(viewer.getSelection()).thenReturn(new StructuredSelection());

        final ContractInput input = contractInputController.addChildInput(viewer);
        ContractInputAssert.assertThat(input1).hasInputs(input);
    }

    @Test
    public void should_removeInput_remove_selected_root_input_from_contract() throws Exception {
        final ContractInput input1 = ProcessFactory.eINSTANCE.createContractInput();
        final ContractInput input2 = ProcessFactory.eINSTANCE.createContractInput();
        final ContractInput input3 = ProcessFactory.eINSTANCE.createContractInput();
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        contract.getInputs().add(input1);
        contract.getInputs().add(input2);
        contract.getInputs().add(input3);
        observableValue.setValue(contract);

        when(viewer.getSelection()).thenReturn(new StructuredSelection(Arrays.asList(input2, input3)));
        contractInputController.remove(viewer);
        assertThat(((Contract) observableValue.getValue()).getInputs()).containsOnly(input1);
        verify(contractDefinitionValidator).clearMessages(input2);
        verify(contractDefinitionValidator).clearMessages(input3);
        verify(contractDefinitionValidator).validate(contract);
        verify(viewer).refresh(true);
    }

    @Test
    public void should_removeInput_remove_selected_children_input_from_contract() throws Exception {
        final ContractInput input1 = ProcessFactory.eINSTANCE.createContractInput();
        final ContractInput child1 = ProcessFactory.eINSTANCE.createContractInput();
        final ContractInput child2 = ProcessFactory.eINSTANCE.createContractInput();
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        contract.getInputs().add(input1);
        input1.getInputs().add(child1);
        child1.getInputs().add(child2);
        observableValue.setValue(contract);

        when(viewer.getSelection()).thenReturn(new StructuredSelection(Arrays.asList(child1)));
        contractInputController.remove(viewer);
        assertThat(((Contract) observableValue.getValue()).getInputs()).containsOnly(input1);
        verify(contractDefinitionValidator).clearMessages(child1);
        verify(contractDefinitionValidator).clearMessages(child2);
        verify(contractDefinitionValidator).validate(contract);
        verify(viewer).refresh(true);
    }

    @Test
    public void should_removeInput_never_refresh_duplicated_inputs() throws Exception {
        final ContractInput input1 = ProcessFactory.eINSTANCE.createContractInput();
        final ContractInput input2 = ProcessFactory.eINSTANCE.createContractInput();
        final ContractInput input3 = ProcessFactory.eINSTANCE.createContractInput();
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        contract.getInputs().add(input1);
        contract.getInputs().add(input3);
        observableValue.setValue(contract);

        when(viewer.getSelection()).thenReturn(new StructuredSelection(Arrays.asList(input2)));
        contractInputController.remove(viewer);
        assertThat(((Contract) observableValue.getValue()).getInputs()).containsOnly(input1, input3);
        verify(contractDefinitionValidator).clearMessages(input2);
        verify(contractDefinitionValidator, never()).validate(any(Contract.class));
        verify(viewer, never()).refresh(true);
    }

    @Test
    public void should_removeInput_do_nothing_if_no_confirmation() throws Exception {
        doReturn(false).when(contractInputController).openConfirmation(anyList());
        final ContractInput input1 = ProcessFactory.eINSTANCE.createContractInput();
        final ContractInput input2 = ProcessFactory.eINSTANCE.createContractInput();
        final ContractInput input3 = ProcessFactory.eINSTANCE.createContractInput();
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        contract.getInputs().add(input1);
        contract.getInputs().add(input2);
        contract.getInputs().add(input3);
        observableValue.setValue(contract);

        when(viewer.getSelection()).thenReturn(new StructuredSelection(Arrays.asList(input2, input3)));
        contractInputController.remove(viewer);
        assertThat(((Contract) observableValue.getValue()).getInputs()).containsOnly(input1, input2, input3);
    }

    @Test
    public void should_moveUp_do_nothing() throws Exception {
        contractInputController.moveUp(viewer);
    }

    @Test
    public void should_moveDown_do_nothing() throws Exception {
        contractInputController.moveDown(viewer);
    }


}
