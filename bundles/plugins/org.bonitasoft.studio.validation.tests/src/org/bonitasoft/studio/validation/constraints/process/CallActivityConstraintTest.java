/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.DataBuilder.aData;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.model.expression.builders.ExpressionBuilder;
import org.bonitasoft.studio.model.process.InputMappingAssignationType;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.builders.CallActivityBuilder;
import org.bonitasoft.studio.model.process.builders.ContractBuilder;
import org.bonitasoft.studio.model.process.builders.ContractInputBuilder;
import org.bonitasoft.studio.model.process.builders.InputMappingBuilder;
import org.bonitasoft.studio.model.process.builders.PoolBuilder;
import org.bonitasoft.studio.properties.sections.callActivity.CallActivityHelper;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CallActivityConstraintTest {

    @Mock
    private CallActivityHelper helper;

    @Test
    public void testCallActivityFailureWithMissingData() throws Exception {
        final CallActivityConstraint callActivityConstraint = spy(new CallActivityConstraint());
        doReturn(helper).when(callActivityConstraint).createHelper(any());
        when(helper.getCalledProcess()).thenReturn(aPool().build());
        final IValidationContext aValidationContext = aValidationContext(
                CallActivityBuilder
                        .aCallActivity()
                        .withName("Call Activity")
                        .havingCalledActivityName(ExpressionBuilder.aConstantExpression().withContent("ChildPool"))
                        .havingCalledActivityVersion(ExpressionBuilder.aConstantExpression().withContent("1.0"))
                        .havingInputMappings(
                        InputMappingBuilder
                                        .anInputMapping()
                                        .setSubProcessTarget(InputMappingAssignationType.DATA, "subProcData")
                                        .setProcessSource(ExpressionBuilder.aVariableExpression().withContent("procData").build())
                                        .build())
                        .build());
        final IStatus status = callActivityConstraint.performBatchValidation(aValidationContext);
        assertThat(status.isOK()).isFalse();
    }

    @Test
    public void testCallActivitySuccess() throws Exception {
        final CallActivityConstraint callActivityConstraint = spy(new CallActivityConstraint());
        final Pool childPool = PoolBuilder
                .aPool()
                .havingData(aData().withName("subProcData"))
                .havingContract(ContractBuilder.aContract().havingInput(ContractInputBuilder.aContractInput().withName("contractInput")))
                .build();
        doReturn(helper).when(callActivityConstraint).createHelper(any());
        when(helper.getCalledProcess()).thenReturn(childPool);
        when(helper.getCallActivityData()).thenReturn(childPool.getData());
        final IValidationContext aValidationContext = aValidationContext(
                CallActivityBuilder
                        .aCallActivity()
                        .withName("Call Activity")
                        .havingCalledActivityName(ExpressionBuilder.aConstantExpression().withContent("ChildPool"))
                        .havingCalledActivityVersion(ExpressionBuilder.aConstantExpression().withContent("1.0"))
                        .havingInputMappings(
                                InputMappingBuilder
                                        .anInputMapping()
                                        .setSubProcessTarget(InputMappingAssignationType.DATA, "subProcData")
                                        .setProcessSource(ExpressionBuilder.aVariableExpression().withContent("procData").build())
                                        .build(),
                                InputMappingBuilder
                                        .anInputMapping()
                                        .setSubProcessTarget(InputMappingAssignationType.CONTRACT_INPUT, "contractInput")
                                        .setProcessSource(ExpressionBuilder.aVariableExpression().withContent("procData").build())
                                        .build())
                        .build());
        final IStatus status = callActivityConstraint.performBatchValidation(aValidationContext);
        assertThat(status.isOK()).isTrue();
    }

    private IValidationContext aValidationContext(final EObject target) {
        final IValidationContext context = mock(IValidationContext.class);
        doReturn(target).when(context).getTarget();
        when(context.createSuccessStatus()).thenReturn(Status.OK_STATUS);
        when(context.createFailureStatus(any())).thenReturn(new Status(IStatus.ERROR, "unknown", ""));
        return context;
    }
}
