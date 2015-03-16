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
package org.bonitasoft.studio.validation.constraints.process;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.emf.validation.IValidationContext;
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
public class ContractConstraintTest {


    private ContractConstraint contractConstraint;

    @Mock
    private IValidationContext ctx;

    private Task task;

    private Contract contract;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        contractConstraint = new ContractConstraint();
        task = ProcessFactory.eINSTANCE.createTask();
        contract = ProcessFactory.eINSTANCE.createContract();

        task.setContract(contract);
        when(ctx.getTarget()).thenReturn(task);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_performBatch_call_ContractDefinitionValidator_and_create_a_failure() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("");
        input.setType(ContractInputType.TEXT);
        contract.getInputs().add(input);
        contractConstraint.performBatchValidation(ctx);
        verify(ctx).createFailureStatus(any());
    }

    @Test
    public void should_performBatch_call_ContractDefinitionValidator_and_create_a_success() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("name");
        input.setType(ContractInputType.TEXT);
        contract.getInputs().add(input);
        contractConstraint.performBatchValidation(ctx);
        verify(ctx).createSuccessStatus();
    }

    @Test
    public void should_performBatch_notcall_ContractDefinitionValidator_and_create_a_success() throws Exception {
        when(ctx.getTarget()).thenReturn(null);
        contractConstraint.performBatchValidation(ctx);
        verify(ctx).createSuccessStatus();
    }

    @Test
    public void should_performLive_create_a_success() throws Exception {
        contractConstraint.performLiveValidation(ctx);
        verify(ctx).createSuccessStatus();
    }


}
