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
import static org.mockito.Mockito.spy;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.forms.IMessageManager;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class ContractConstraintsTableViewerTest {

    @Rule
    public RealmWithDisplay realm = new RealmWithDisplay();

    private ContractConstraintsTableViewer viewer;
    private Composite parent;
    private ContractConstraint constraint;
    private ContractConstraint constraint2;
    @Mock
    private IMessageManager messageManager;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        parent = realm.createComposite();
        FileActionDialog.setDisablePopup(true);
        viewer = new ContractConstraintsTableViewer(parent, new FormToolkit(parent.getDisplay()));
        ContractConstraintController inputController = spy(
                new ContractConstraintController(new WritableValue(aContract().build(), Contract.class)));
        viewer.initialize(inputController, messageManager, new EMFDataBindingContext());
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("name");
        input.setType(ContractInputType.TEXT);
        contract.getInputs().add(input);
        constraint = ProcessFactory.eINSTANCE.createContractConstraint();
        constraint.setName("c1");
        constraint2 = ProcessFactory.eINSTANCE.createContractConstraint();
        constraint2.setName("c2");
        contract.getConstraints().add(constraint);
        contract.getConstraints().add(constraint2);
        viewer.setInput(
                EMFObservables.observeList(Realm.getDefault(), contract, ProcessPackage.Literals.CONTRACT__CONSTRAINTS));
    }

    @Test
    public void shoud_createAddListener_add_a_selection_listener_that_add_a_contract_constraint() throws Exception {
        final Button button = new Button(parent, SWT.PUSH);
        viewer.createAddListener(button);
        assertThat(viewer.getTable().getItemCount()).isEqualTo(2);
        button.notifyListeners(SWT.Selection, new Event());
        assertThat(viewer.getTable().getItemCount()).isEqualTo(3);
    }

    @Test
    public void shoud_createRemoveListener_add_a_selection_listener_that_remove_selected_contract_constraints()
            throws Exception {
        final Button button = new Button(parent, SWT.PUSH);
        viewer.createRemoveListener(button);
        viewer.setSelection(new StructuredSelection(constraint));
        assertThat(viewer.getTable().getItemCount()).isEqualTo(2);
        button.notifyListeners(SWT.Selection, new Event());
        assertThat(viewer.getTable().getItemCount()).isEqualTo(1);
    }

    @Test
    public void shoud_createMoveUpListener_add_a_selection_listener_that_moveUp_selected_contract_constraint()
            throws Exception {
        final Button button = new Button(parent, SWT.PUSH);
        viewer.createMoveUpListener(button);
        assertThat(viewer.getTable().getItem(1).getData()).isEqualTo(constraint2);
        viewer.setSelection(new StructuredSelection(constraint2));
        button.notifyListeners(SWT.Selection, new Event());
        assertThat(viewer.getTable().getItem(0).getData()).isEqualTo(constraint2);
    }

    @Test
    public void shoud_createMoveUpListener_add_a_selection_listener_that_moveDown_selected_contract_constraint()
            throws Exception {
        final Button button = new Button(parent, SWT.PUSH);
        viewer.createMoveDownListener(button);
        assertThat(viewer.getTable().getItem(0).getData()).isEqualTo(constraint);
        viewer.setSelection(new StructuredSelection(constraint));
        button.notifyListeners(SWT.Selection, new Event());
        assertThat(viewer.getTable().getItem(1).getData()).isEqualTo(constraint);
    }

    @Test
    public void should_getStyledText_strip_expression_charriage() throws Exception {
        final ContractConstraint constraint = ProcessFactory.eINSTANCE.createContractConstraint();
        constraint.setExpression("toto == true && \n titi == false \r");
        final StyledString styledText = viewer.getExpressionStyledText(constraint);
        assertThat(styledText.toString()).doesNotContain("\n").doesNotContain("\r");
    }

    @Test
    public void should_getStyledText_supportNull() throws Exception {
        final ContractConstraint constraint = ProcessFactory.eINSTANCE.createContractConstraint();
        constraint.setExpression(null);
        final StyledString styledText = viewer.getExpressionStyledText(constraint);
        assertThat(styledText.toString()).doesNotContain("\n").doesNotContain("\r");
    }

}
