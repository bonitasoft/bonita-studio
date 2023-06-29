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
package org.bonitasoft.studio.contract.core.expression;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.emf.ecore.EObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Romain Bioteau
 *
 */
public class ContractInputExpressionProviderTest {

    private ContractInputExpressionProvider contractInputExpressionProvider;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        contractInputExpressionProvider = new ContractInputExpressionProvider();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_getExpressionType_returns_ContractInputType() throws Exception {
        assertThat(contractInputExpressionProvider.getExpressionType()).isEqualTo(ExpressionConstants.CONTRACT_INPUT_TYPE);
    }

    @Test
    public void should_getTypeLabel_returns_label() throws Exception {
        assertThat(contractInputExpressionProvider.getTypeLabel()).isEqualTo(Messages.contractInputTypeLabel);
    }

    @Test
    public void should_isRelevant_returns_true_if_context_is_in_a_Task() throws Exception {
        final EObject context = ProcessFactory.eINSTANCE.createTask();
        assertThat(contractInputExpressionProvider.isRelevantFor(context)).isTrue();
    }

    @Test
    public void should_isRelevant_returns_true_if_context_is_a_Pool() throws Exception {
        final EObject context = ProcessFactory.eINSTANCE.createPool();
        assertThat(contractInputExpressionProvider.isRelevantFor(context)).isTrue();
    }


    @Test
    public void should_isRelevant_returns_true_if_context_is_a_data_in_a_Pool() throws Exception {
        final Pool context = ProcessFactory.eINSTANCE.createPool();
        final Data data = ProcessFactory.eINSTANCE.createData();
        context.getData().add(data);
        assertThat(contractInputExpressionProvider.isRelevantFor(data)).isTrue();
    }

    @Test
    public void should_getProposalLabel_returns_expression_name() throws Exception {
        final Expression expression = ExpressionHelper.createConstantExpression("test", String.class.getName());
        assertThat(contractInputExpressionProvider.getProposalLabel(expression)).isEqualTo(expression.getName());
    }

    @Test
    public void should_getExpressions_returns_available_contract_int_as_expressions() throws Exception {
        final Task task = ProcessFactory.eINSTANCE.createTask();
        task.setContract(createEmptyContract());
        assertThat(contractInputExpressionProvider.getExpressions(task)).isEmpty();
        task.setContract(creatContractWithInput("firstName", "lastName"));
        assertThat(contractInputExpressionProvider.getExpressions(task)).hasSize(2)
                .extracting("name", "returnType")
                .contains(
                        tuple("firstName", String.class.getName()),
                        tuple("lastName", String.class.getName()));
    }

    @Test
    public void should_getExpressions_returns_available_contract_int_as_expressions_on_pool() throws Exception {
        final Pool pool = ProcessFactory.eINSTANCE.createPool();
        pool.setContract(createEmptyContract());
        assertThat(contractInputExpressionProvider.getExpressions(pool)).isEmpty();
        pool.setContract(creatContractWithInput("firstName", "lastName"));
        assertThat(contractInputExpressionProvider.getExpressions(pool)).hasSize(2)
                .extracting("name", "returnType")
                .contains(
                        tuple("firstName", String.class.getName()),
                        tuple("lastName", String.class.getName()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_getExpressions_throw_IllegalArgumentException() throws Exception {
        final Task task = ProcessFactory.eINSTANCE.createTask();
        contractInputExpressionProvider.getExpressions(task);
    }

    @Test
    public void should_getIcon_return_emf_icon() throws Exception {
        final Contract creatContractWithInput = creatContractWithInput("name");
        assertThat(contractInputExpressionProvider.getIcon(ExpressionHelper.createContractInputExpression(creatContractWithInput.getInputs().get(0))))
                .isNotNull();
    }

    @Test
    public void should_getIcon_return_null() throws Exception {
        assertThat(contractInputExpressionProvider.getIcon(null))
                .isNull();

        assertThat(contractInputExpressionProvider.getIcon(ExpressionHelper.createConstantExpression("", "")))
                .isNull();
    }

    private Contract creatContractWithInput(final String...inputNames) {
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

}
