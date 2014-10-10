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
package org.bonitasoft.studio.contract.ui.expression;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.contract.AbstractSWTTestCase;
import org.bonitasoft.studio.contract.core.expression.ContractInputExpressionProvider;
import org.bonitasoft.studio.model.expression.assertions.ExpressionAssert;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author Romain Bioteau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ContractInputExpressionEditorTest extends AbstractSWTTestCase {

    @Spy
    private ContractInputExpressionEditor contractInputExpressionEditor;
    private Composite parent;
    private Task task;
    private Contract contract;
    private EMFDataBindingContext dataBindingContext;

    private Contract creatContractWithInput(final String... inputNames) {
        final Contract contract = createEmptyContract();
        for (final String input : inputNames) {
            final ContractInput newInput = ProcessFactory.eINSTANCE.createContractInput();
            newInput.setName(input);
            newInput.setType(ContractInputType.TEXT);
            contract.getInputs().add(newInput);
        }
        return contract;
    }

    private Contract createEmptyContract() {
        return ProcessFactory.eINSTANCE.createContract();
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        parent = createDisplayAndRealm();
        task = ProcessFactory.eINSTANCE.createTask();
        contract = creatContractWithInput("firstName", "lastName");
        task.setContract(contract);
        doReturn(new ContractInputExpressionProvider()).when(contractInputExpressionEditor).getContractInputExpressionProvider();
        dataBindingContext = new EMFDataBindingContext(Realm.getDefault());
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        dispose();
    }

    @Test
    public void should_bindExpression_convert_viewer_selection_to_contract_input_expression() throws Exception {
        contractInputExpressionEditor.createExpressionEditor(parent, dataBindingContext);
        contractInputExpressionEditor.bindExpression(dataBindingContext, task,
                ExpressionHelper.createConstantExpression("", String.class.getName()), null, null);
        final Object elementAt = contractInputExpressionEditor.getViewer().getElementAt(1);

        assertThat(contractInputExpressionEditor.canFinish()).isFalse();

        contractInputExpressionEditor.getViewer().setSelection(new StructuredSelection(elementAt));
        ExpressionAssert.assertThat(contractInputExpressionEditor.getExpression())
                .hasName("lastName")
                .hasContent("lastName")
                .hasReturnType(String.class.getName())
                .hasType(ExpressionConstants.CONTRACT_INPUT_TYPE);

        assertThat(contractInputExpressionEditor.canFinish()).isTrue();
    }


}
