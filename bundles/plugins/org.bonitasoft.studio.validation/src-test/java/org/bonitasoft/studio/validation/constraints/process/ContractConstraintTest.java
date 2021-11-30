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
package org.bonitasoft.studio.validation.constraints.process;

import static org.bonitasoft.studio.model.process.builders.ContractBuilder.aContract;
import static org.bonitasoft.studio.model.process.builders.ContractConstraintBuilder.aContractConstraint;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.eclipse.emf.validation.IValidationContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class ContractConstraintTest {

    @Mock
    private IValidationContext ctx;

    @Test
    public void should_create_a_failure_if_constraint_does_not_depends_on_an_input() throws Exception {
        final ContractConstraint contractConstraint = new ContractConstraint();
        when(ctx.getTarget()).thenReturn(aContract().havingConstraint(aContractConstraint()).in(aTask().withName("Task1")).build().getConstraints().get(0));

        contractConstraint.performBatchValidation(ctx);

        verify(ctx).createFailureStatus(any());
    }

    @Test
    public void should_create_a_failure_if_constraint_depends_on_an_input_that_does_not_exists_in_the_contract() throws Exception {
        final ContractConstraint contractConstraint = new ContractConstraint();
        when(ctx.getTarget()).thenReturn(
                aContract().havingConstraint(aContractConstraint().havingInput("firstName")).in(aTask().withName("Task1")).build().getConstraints().get(0));

        contractConstraint.performBatchValidation(ctx);

        verify(ctx).createFailureStatus(any());
    }

    @Test
    public void should_create_a_success_if_constraint_depends_on_an_input_existing_in_the_contract() throws Exception {
        final ContractConstraint contractConstraint = new ContractConstraint();
        when(ctx.getTarget()).thenReturn(
                aContract()
                        .havingInput(aContractInput().withName("firstName"))
                        .havingConstraint(aContractConstraint().havingInput("firstName")).in(aTask().withName("Task1")).build().getConstraints().get(0));

        contractConstraint.performBatchValidation(ctx);

        verify(ctx).createSuccessStatus();
    }

}
