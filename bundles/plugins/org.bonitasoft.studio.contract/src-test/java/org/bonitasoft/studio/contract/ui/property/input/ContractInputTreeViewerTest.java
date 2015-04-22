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

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.swt.AbstractSWTTestCase;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.forms.IMessageManager;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.progress.IProgressService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class ContractInputTreeViewerTest extends AbstractSWTTestCase {

    private ContractInputTreeViewer inputTreeViewer;
    private Composite parent;
    private ContractInput input;
    @Mock
    private IProgressService progressService;
    @Mock
    private IMessageManager messageManager;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        parent = createDisplayAndRealm();
        FileActionDialog.setDisablePopup(true);
        inputTreeViewer = new ContractInputTreeViewer(parent, new FormToolkit(display), progressService);
        final ContractInputController inputController = new ContractInputController();
        inputTreeViewer.initialize(inputController, messageManager, new EMFDataBindingContext());
        final WritableValue contractObservableValue = new WritableValue();
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("name");
        input.setType(ContractInputType.TEXT);
        contract.getInputs().add(input);
        contractObservableValue.setValue(contract);
        inputTreeViewer.setInput(contractObservableValue);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        dispose();
    }

    @Test
    public void shoud_createAddListener_add_a_selection_listener_that_add_a_contract_input() throws Exception {
        final Button button = new Button(parent, SWT.PUSH);
        inputTreeViewer.createAddListener(button);
        assertThat(inputTreeViewer.getTree().getItemCount()).isEqualTo(1);
        button.notifyListeners(SWT.Selection, new Event());
        assertThat(inputTreeViewer.getTree().getItemCount()).isEqualTo(2);
    }

    @Test
    public void shoud_createRemoveListener_add_a_selection_listener_that_remove_selected_contract_inputs() throws Exception {
        final Button button = new Button(parent, SWT.PUSH);
        inputTreeViewer.createRemoveListener(button);
        inputTreeViewer.setSelection(new StructuredSelection(input));
        assertThat(inputTreeViewer.getTree().getItemCount()).isEqualTo(1);
        button.notifyListeners(SWT.Selection, new Event());
        assertThat(inputTreeViewer.getTree().getItemCount()).isEqualTo(0);
    }

    @Test
    public void shoud_createAddChildListener_add_a_selection_listener_that_add_a_child_input_to_the_selected_contract_inputs() throws Exception {
        final Button button = new Button(parent, SWT.PUSH);
        inputTreeViewer.createAddChildListener(button);
        inputTreeViewer.setSelection(new StructuredSelection(input));
        assertThat(inputTreeViewer.getTree().getItemCount()).isEqualTo(1);
        button.notifyListeners(SWT.Selection, new Event());
        assertThat(input.getInputs()).hasSize(1);
    }

}
