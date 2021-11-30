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

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.assertions.ContractAssert.assertThat;
import static org.bonitasoft.studio.model.process.builders.ContractBuilder.aContract;
import static org.bonitasoft.studio.model.process.builders.ContractConstraintBuilder.aContractConstraint;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.assertions.ContractInputAssert;
import org.bonitasoft.studio.model.process.provider.ProcessItemProviderAdapterFactory;
import org.bonitasoft.studio.refactoring.core.script.IScriptRefactoringOperation;
import org.bonitasoft.studio.refactoring.core.script.IScriptRefactoringOperationFactory;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.progress.IProgressService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class ContractInputControllerTest {

    private ContractInputController contractInputController;

    @Mock
    private TreeViewer viewer;

    private WritableValue observableValue;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    @Mock
    private IScriptRefactoringOperationFactory scriptRefactoringOperationFactory;
    @Mock
    private IScriptRefactoringOperation refactorScriptOperation;
    @Mock
    private IProgressService progressService;
    @Mock
    private Display display;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        FileActionDialog.setDisablePopup(true);
        contractInputController = spy(new ContractInputController(progressService));
        doReturn(new TransactionalEditingDomainImpl(new ProcessItemProviderAdapterFactory())).when(contractInputController).editingDomain(any(Contract.class));
        doReturn(scriptRefactoringOperationFactory).when(contractInputController).scriptRefactoringOperationFactory();
        observableValue = new WritableValue(Realm.getDefault());
        when(viewer.getInput()).thenReturn(observableValue);
        final Tree tree = mock(Tree.class);
        when(tree.getDisplay()).thenReturn(display);
        when(viewer.getControl()).thenReturn(tree);
    }

    @Test
    public void should_create_new_input_with_a_default_name_and_type() throws Exception {
        when(viewer.getSelection()).thenReturn(new StructuredSelection());
        final Contract contract = aContract().build();
        observableValue.setValue(contract);

        final ContractInput input = contractInputController.add(viewer);

        ContractInputAssert.assertThat(input).hasName("input1").hasType(ContractInputType.TEXT);
        verify(display).asyncExec(notNull());
    }

    @Test
    public void should_add_a_new_root_contract_input_when_selection_is_empty() throws Exception {
        when(viewer.getSelection()).thenReturn(new StructuredSelection());
        final Contract contract = aContract().build();
        observableValue.setValue(contract);

        final ContractInput input = contractInputController.add(viewer);

        assertThat(contract.getInputs()).containsOnly(input);
    }

    @Test
    public void should_add_a_new_root_contract_input_when_root_input_selected() throws Exception {
        final Contract contract = aContract().havingInput(aContractInput()).build();
        observableValue.setValue(contract);
        when(viewer.getSelection()).thenReturn(new StructuredSelection(Arrays.asList(contract.getInputs().get(0))));

        final ContractInput input = contractInputController.add(viewer);

        assertThat(contract).hasInputs(input);
    }

    @Test
    public void should_add_a_new_contract_input_when_sibling_input_selected() throws Exception {
        final Contract contract = aContract().havingInput(aContractInput().havingInput(aContractInput())).build();
        observableValue.setValue(contract);
        final ContractInput rootInput = contract.getInputs().get(0);
        when(viewer.getSelection()).thenReturn(new StructuredSelection(Arrays.asList(rootInput.getInputs().get(0))));

        final ContractInput input = contractInputController.add(viewer);

        ContractInputAssert.assertThat(rootInput).hasInputs(input);
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

        verify(display).asyncExec(notNull());
    }

    public void should_addChildInput_return_null_if_selection_in_viewer_is_empty() throws Exception {
        final ContractInput input1 = ProcessFactory.eINSTANCE.createContractInput();
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        contract.getInputs().add(input1);
        observableValue.setValue(contract);

        when(viewer.getSelection()).thenReturn(new StructuredSelection());

        final ContractInput input = contractInputController.addChildInput(viewer);
        ContractInputAssert.assertThat(input1).isNull();
    }

    @Test
    public void should_remove_selected_input_from_contract() throws Exception {
        final ContractInput input1 = ProcessFactory.eINSTANCE.createContractInput();
        final ContractInput input2 = ProcessFactory.eINSTANCE.createContractInput();
        final ContractInput input3 = ProcessFactory.eINSTANCE.createContractInput();
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        contract.getInputs().add(input1);
        contract.getInputs().add(input2);
        contract.getInputs().add(input3);
        aTask().build().setContract(contract);
        observableValue.setValue(contract);

        when(viewer.getSelection()).thenReturn(new StructuredSelection(Arrays.asList(input2, input3)));
        contractInputController.remove(viewer);

        verify(progressService).run(eq(true), eq(true), notNull());
    }

    @Test
    public void should_remove_selected_children_input_from_contract() throws Exception {
        final ContractInput input1 = ProcessFactory.eINSTANCE.createContractInput();
        final ContractInput child1 = ProcessFactory.eINSTANCE.createContractInput();
        final ContractInput child2 = ProcessFactory.eINSTANCE.createContractInput();
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        contract.getInputs().add(input1);
        input1.getInputs().add(child1);
        child1.getInputs().add(child2);
        aTask().build().setContract(contract);
        observableValue.setValue(contract);

        when(viewer.getSelection()).thenReturn(new StructuredSelection(Arrays.asList(child1)));
        contractInputController.remove(viewer);

        assertThat(((Contract) observableValue.getValue()).getInputs()).containsOnly(input1);
    }

    @Test
    public void should_not_remove_input_contained_in_a_removed_container() throws Exception {
        final ContractInput input1 = ProcessFactory.eINSTANCE.createContractInput();
        final ContractInput input2 = ProcessFactory.eINSTANCE.createContractInput();
        final ContractInput input3 = ProcessFactory.eINSTANCE.createContractInput();
        final Contract contract = aContract().in(aTask()).build();
        contract.getInputs().add(input1);
        contract.getInputs().add(input3);
        observableValue.setValue(contract);

        when(viewer.getSelection()).thenReturn(new StructuredSelection(Arrays.asList(input2)));
        contractInputController.remove(viewer);
        assertThat(((Contract) observableValue.getValue()).getInputs()).containsOnly(input1, input3);
    }

    @Test
    public void should_not_remove_if_confirmation_is_rejected() throws Exception {
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
    public void should_open_an_error_dialog_if_remove_operation_failed() throws Exception {
        final IProgressService mockProgressService = mock(IProgressService.class);
        contractInputController = spy(new ContractInputController(mockProgressService));
        doReturn(new TransactionalEditingDomainImpl(new ProcessItemProviderAdapterFactory())).when(contractInputController).editingDomain(any(Contract.class));

        final Contract contract = aContract().havingInput(aContractInput()).in(aTask()).build();
        observableValue.setValue(contract);
        when(viewer.getSelection()).thenReturn(new StructuredSelection(contract.getInputs()));
        final InvocationTargetException error = new InvocationTargetException(new Throwable());
        doThrow(error).when(mockProgressService).run(anyBoolean(), anyBoolean(), any(IRunnableWithProgress.class));

        contractInputController.remove(viewer);

        verify(contractInputController).openErrorDialog(error);
    }

    @Test
    public void should_remove_single_referenced_constraint() throws Exception {
        final Contract contract = aContract()
                .havingInput(aContractInput().withName("employee"))
                .havingConstraint(aContractConstraint().havingInput("employee"))
                .in(aTask()).build();
        doReturn(false).when(contractInputController).shouldAskConfirmation();
        observableValue.setValue(contract);
        when(viewer.getSelection()).thenReturn(new StructuredSelection(contract.getInputs()));

        contractInputController.remove(viewer);

        verify(progressService).run(eq(true), eq(true), notNull());
    }

    @Test
    public void should_not_remove_multi_referenced_constraint() throws Exception {
        final Contract contract = aContract()
                .havingInput(aContractInput().withName("employee"))
                .havingConstraint(aContractConstraint().havingInput("employee", "manager"))
                .in(aTask()).build();
        doReturn(false).when(contractInputController).shouldAskConfirmation();
        observableValue.setValue(contract);
        when(viewer.getSelection()).thenReturn(new StructuredSelection(contract.getInputs()));

        contractInputController.remove(viewer);

        Assertions.assertThat(contract.getConstraints()).hasSize(1);
    }

    @Test
    public void should_throw_UnsupportedOperationException_when_moveUp() throws Exception {
        thrown.expect(UnsupportedOperationException.class);

        contractInputController.moveUp(viewer);
    }

    @Test
    public void should_throw_UnsupportedOperationException_when_moveDown() throws Exception {
        thrown.expect(UnsupportedOperationException.class);

        contractInputController.moveDown(viewer);
    }

}
