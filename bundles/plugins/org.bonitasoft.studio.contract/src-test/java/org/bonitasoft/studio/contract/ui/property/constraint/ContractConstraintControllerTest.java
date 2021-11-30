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
package org.bonitasoft.studio.contract.ui.property.constraint;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.ContractBuilder.aContract;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class ContractConstraintControllerTest {

    private ContractConstraintController controller;

    @Mock
    private TreeViewer viewer;

    private Contract contract;

    @Rule
    public RealmWithDisplay realm = new RealmWithDisplay();

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        FileActionDialog.setDisablePopup(true);
        controller = spy(new ContractConstraintController(new WritableValue(aContract().build(), Contract.class)));
        contract = ProcessFactory.eINSTANCE.createContract();
        when(viewer.getInput())
                .thenReturn(EMFObservables.observeList(contract, ProcessPackage.Literals.CONTRACT__CONSTRAINTS));
    }

    @Test
    public void should_add_add_a_new_contract_constraint() throws Exception {
        final ContractConstraint constraint = controller.add(viewer);
        assertThat(contract.getConstraints()).containsOnly(constraint);
        assertThat(constraint.getName()).isEqualTo("constraint1");
        assertThat(constraint.getExpression()).isEqualTo("return true;");
        verify(viewer).editElement(constraint, 0);
    }

    @Test
    public void should_remove_remove_selected_constraint_from_contract() throws Exception {
        final ContractConstraint c1 = ProcessFactory.eINSTANCE.createContractConstraint();
        final ContractConstraint c2 = ProcessFactory.eINSTANCE.createContractConstraint();
        final ContractConstraint c3 = ProcessFactory.eINSTANCE.createContractConstraint();
        contract.getConstraints().add(c1);
        contract.getConstraints().add(c2);
        contract.getConstraints().add(c3);

        when(viewer.getSelection()).thenReturn(new StructuredSelection(Arrays.asList(c2, c3)));
        controller.remove(viewer);
        assertThat(contract.getConstraints()).containsOnly(c1);
    }

    @Test
    public void should_remove_never_refresh_duplicated_inputs() throws Exception {
        final ContractConstraint c1 = ProcessFactory.eINSTANCE.createContractConstraint();
        final ContractConstraint c2 = ProcessFactory.eINSTANCE.createContractConstraint();
        final ContractConstraint c3 = ProcessFactory.eINSTANCE.createContractConstraint();
        contract.getConstraints().add(c1);
        contract.getConstraints().add(c3);

        when(viewer.getSelection()).thenReturn(new StructuredSelection(Arrays.asList(c2)));
        controller.remove(viewer);
        assertThat(contract.getConstraints()).containsOnly(c1, c3);
        verify(viewer, never()).refresh(true);
    }

    @Test
    public void should_remove_do_nothing_if_no_confirmation() throws Exception {
        doReturn(false).when(controller).openConfirmation(anyList());
        final ContractConstraint c1 = ProcessFactory.eINSTANCE.createContractConstraint();
        final ContractConstraint c2 = ProcessFactory.eINSTANCE.createContractConstraint();
        final ContractConstraint c3 = ProcessFactory.eINSTANCE.createContractConstraint();
        contract.getConstraints().add(c1);
        contract.getConstraints().add(c2);
        contract.getConstraints().add(c3);

        when(viewer.getSelection()).thenReturn(new StructuredSelection(Arrays.asList(c1, c2)));
        controller.remove(viewer);
        assertThat(contract.getConstraints()).containsOnly(c1, c2, c3);
    }

    @Test
    public void should_moveUp_decrease_index_of_selected_constraint() throws Exception {
        final ContractConstraint c1 = ProcessFactory.eINSTANCE.createContractConstraint();
        final ContractConstraint c2 = ProcessFactory.eINSTANCE.createContractConstraint();
        final ContractConstraint c3 = ProcessFactory.eINSTANCE.createContractConstraint();
        contract.getConstraints().add(c1);
        contract.getConstraints().add(c2);
        contract.getConstraints().add(c3);
        assertThat(contract.getConstraints()).containsExactly(c1, c2, c3);
        when(viewer.getSelection()).thenReturn(new StructuredSelection(Arrays.asList(c2)));
        controller.moveUp(viewer);
        assertThat(contract.getConstraints()).containsExactly(c2, c1, c3);
    }

    @Test
    public void should_moveUp_do_nothing_when_selection_is_first_element() throws Exception {
        final ContractConstraint c1 = ProcessFactory.eINSTANCE.createContractConstraint();
        final ContractConstraint c2 = ProcessFactory.eINSTANCE.createContractConstraint();
        final ContractConstraint c3 = ProcessFactory.eINSTANCE.createContractConstraint();
        contract.getConstraints().add(c1);
        contract.getConstraints().add(c2);
        contract.getConstraints().add(c3);
        assertThat(contract.getConstraints()).containsExactly(c1, c2, c3);
        when(viewer.getSelection()).thenReturn(new StructuredSelection(Arrays.asList(c1)));
        controller.moveUp(viewer);
        assertThat(contract.getConstraints()).containsExactly(c1, c2, c3);
    }

    @Test
    public void should_moveDown_decrease_index_of_selected_constraint() throws Exception {
        final ContractConstraint c1 = ProcessFactory.eINSTANCE.createContractConstraint();
        final ContractConstraint c2 = ProcessFactory.eINSTANCE.createContractConstraint();
        final ContractConstraint c3 = ProcessFactory.eINSTANCE.createContractConstraint();
        contract.getConstraints().add(c1);
        contract.getConstraints().add(c2);
        contract.getConstraints().add(c3);
        assertThat(contract.getConstraints()).containsExactly(c1, c2, c3);
        when(viewer.getSelection()).thenReturn(new StructuredSelection(Arrays.asList(c2)));
        controller.moveDown(viewer);
        assertThat(contract.getConstraints()).containsExactly(c1, c3, c2);
    }

    @Test
    public void should_moveDown_do_nothing_when_selection_is_last_element() throws Exception {
        final ContractConstraint c1 = ProcessFactory.eINSTANCE.createContractConstraint();
        final ContractConstraint c2 = ProcessFactory.eINSTANCE.createContractConstraint();
        final ContractConstraint c3 = ProcessFactory.eINSTANCE.createContractConstraint();
        contract.getConstraints().add(c1);
        contract.getConstraints().add(c2);
        contract.getConstraints().add(c3);
        assertThat(contract.getConstraints()).containsExactly(c1, c2, c3);
        when(viewer.getSelection()).thenReturn(new StructuredSelection(Arrays.asList(c3)));
        controller.moveDown(viewer);
        assertThat(contract.getConstraints()).containsExactly(c1, c2, c3);
    }

}
