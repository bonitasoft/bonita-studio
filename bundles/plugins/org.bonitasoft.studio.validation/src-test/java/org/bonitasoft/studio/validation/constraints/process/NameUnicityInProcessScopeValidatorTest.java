/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyVararg;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import org.bonitasoft.studio.model.parameter.builders.ParameterBuilder;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.builders.CallActivityBuilder;
import org.bonitasoft.studio.model.process.builders.ContractBuilder;
import org.bonitasoft.studio.model.process.builders.ContractInputBuilder;
import org.bonitasoft.studio.model.process.builders.DataBuilder;
import org.bonitasoft.studio.model.process.builders.DocumentBuilder;
import org.bonitasoft.studio.model.process.builders.PoolBuilder;
import org.bonitasoft.studio.model.process.builders.TaskBuilder;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.validation.IValidationContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NameUnicityInProcessScopeValidatorTest {

    @Mock
    private IValidationContext ctx;

    @Mock
    private IStatus failureSTatus;

    @Before
    public void setup() {
        doReturn(Status.OK_STATUS).when(ctx).createSuccessStatus();
        doReturn(failureSTatus).when(ctx).createFailureStatus(anyVararg());
    }

    @Test
    public void testPerformBatchValidationOnEmptyPool() throws Exception {
        final Pool pool = PoolBuilder.aPool().build();
        doReturn(pool).when(ctx).getTarget();

        final IStatus res = new NameUnicityInProcessScopeValidator().performBatchValidation(ctx);
        assertThat(res).isEqualTo(Status.OK_STATUS);
    }

    @Test
    public void testPerformBatchValidationOnEmptyTask() throws Exception {
        final Pool pool = PoolBuilder.aPool().havingElements(TaskBuilder.aTask()).build();
        doReturn(pool.getElements().get(0)).when(ctx).getTarget();

        final IStatus res = new NameUnicityInProcessScopeValidator().performBatchValidation(ctx);
        assertThat(res).isEqualTo(Status.OK_STATUS);
    }

    @Test
    public void testPerformBatchValidationOnPool_Success_withSameNameForProcessContractInputAndTaskData() throws Exception {
        final Pool pool = PoolBuilder.aPool().havingContract(ContractBuilder.aContract().havingInput(ContractInputBuilder.aContractInput().withName("test")))
                .havingElements(TaskBuilder.aTask().havingData(DataBuilder.aData().withName("test"))).build();
        doReturn(pool).when(ctx).getTarget();

        final IStatus res = new NameUnicityInProcessScopeValidator().performBatchValidation(ctx);
        assertThat(res).isEqualTo(Status.OK_STATUS);
    }

    @Test
    public void testPerformBatchValidationOnPool_Failure_withSameNameForProcessContractInputAndProcessData() throws Exception {
        final Pool pool = PoolBuilder.aPool()
                .havingContract(ContractBuilder.aContract().havingInput(ContractInputBuilder.aContractInput().withName("test")))
                .havingData(DataBuilder.aData().withName("test")).build();
        doReturn(pool).when(ctx).getTarget();

        final NameUnicityInProcessScopeValidator nameUnicityInProcessScopeValidator = spy(new NameUnicityInProcessScopeValidator());
        doReturn(failureSTatus).when(nameUnicityInProcessScopeValidator).createMultiStatusConstraint(any(IValidationContext.class), anyListOf(IStatus.class));
        final IStatus res = nameUnicityInProcessScopeValidator.performBatchValidation(ctx);
        assertThat(res.isOK()).isFalse();
    }

    @Test
    public void testPerformBatchValidationOnPool_Failure_withSeveralSameNameForProcessContractInputAndProcessData() throws Exception {
        final Pool pool = PoolBuilder.aPool()
                .havingContract(ContractBuilder.aContract().havingInput(ContractInputBuilder.aContractInput().withName("test").build(),
                        ContractInputBuilder.aContractInput().withName("test1").build()))
                .havingData(DataBuilder.aData().withName("test"), DataBuilder.aData().withName("test")).build();
        doReturn(pool).when(ctx).getTarget();

        final NameUnicityInProcessScopeValidator nameUnicityInProcessScopeValidator = spy(new NameUnicityInProcessScopeValidator());
        doReturn(failureSTatus).when(nameUnicityInProcessScopeValidator).createMultiStatusConstraint(any(IValidationContext.class), anyListOf(IStatus.class));
        final IStatus res = nameUnicityInProcessScopeValidator.performBatchValidation(ctx);
        assertThat(res.isOK()).isFalse();
    }

    @Test
    public void testPerformBatchValidationOnTask_Failure_withSameNameForStepContractInputAndStepData() throws Exception {
        final Pool pool = PoolBuilder.aPool()
                .havingElements(TaskBuilder.aTask()
                        .havingContract(ContractBuilder.aContract().havingInput(ContractInputBuilder.aContractInput().withName("test")))
                        .havingData(DataBuilder.aData().withName("test"))).build();
        doReturn(pool.getElements().get(0)).when(ctx).getTarget();

        final NameUnicityInProcessScopeValidator nameUnicityInProcessScopeValidator = spy(new NameUnicityInProcessScopeValidator());
        doReturn(failureSTatus).when(nameUnicityInProcessScopeValidator).createMultiStatusConstraint(any(IValidationContext.class), anyListOf(IStatus.class));
        final IStatus res = nameUnicityInProcessScopeValidator.performBatchValidation(ctx);
        assertThat(res.isOK()).isFalse();
    }

    @Test
    public void testPerformBatchValidationOnCallActivityTask_Failure_withSameNameForParameterAndStepData() throws Exception {
        final Pool pool = PoolBuilder.aPool()
                .havingParameters(ParameterBuilder.aParameter().withName("test"))
                .havingElements(CallActivityBuilder.aCallActivity().havingData(DataBuilder.aData().withName("test"))).build();
        doReturn(pool.getElements().get(0)).when(ctx).getTarget();

        final NameUnicityInProcessScopeValidator nameUnicityInProcessScopeValidator = spy(new NameUnicityInProcessScopeValidator());
        doReturn(failureSTatus).when(nameUnicityInProcessScopeValidator).createMultiStatusConstraint(any(IValidationContext.class), anyListOf(IStatus.class));
        final IStatus res = nameUnicityInProcessScopeValidator.performBatchValidation(ctx);
        assertThat(res.isOK()).isFalse();
    }

    @Test
    public void testPerformBatchValidationOnServiceTask_Success_withSameNameForParameterAndProcessData() throws Exception {
        final Pool pool = PoolBuilder.aPool()
                .havingParameters(ParameterBuilder.aParameter().withName("test"))
                .havingData(DataBuilder.aData().withName("test"))
                .havingElements(TaskBuilder.aTask()).build();
        doReturn(pool.getElements().get(0)).when(ctx).getTarget();

        final IStatus res = new NameUnicityInProcessScopeValidator().performBatchValidation(ctx);
        assertThat(res).isEqualTo(Status.OK_STATUS);
    }

    @Test
    public void testPerformBatchValidationOnServiceTask_Success_withNotSameNameForParameterAndStepData() throws Exception {
        final Pool pool = PoolBuilder.aPool()
                .havingDocuments(DocumentBuilder.aDocument().withName("test3"))
                .havingContract(ContractBuilder.aContract().havingInput(ContractInputBuilder.aContractInput().withName("test5")))
                .havingParameters(ParameterBuilder.aParameter().withName("test1"))
                .havingData(DataBuilder.aData().withName("test4"))
                .havingElements(
                        TaskBuilder.aTask()
                                .havingData(DataBuilder.aData().withName("test2"))
                                .havingContract(ContractBuilder.aContract().havingInput(ContractInputBuilder.aContractInput().withName("test6")))).build();
        doReturn(pool.getElements().get(0)).when(ctx).getTarget();

        final IStatus res = new NameUnicityInProcessScopeValidator().performBatchValidation(ctx);
        assertThat(res).isEqualTo(Status.OK_STATUS);
    }

}
