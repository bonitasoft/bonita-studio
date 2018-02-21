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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.validation.constraints.process;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.condition.conditionModel.ConditionModelFactory;
import org.bonitasoft.studio.condition.conditionModel.Operation_Compare;
import org.bonitasoft.studio.condition.conditionModel.Operation_Greater;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.validation.IValidationContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author Romain Bioteau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ConditionExpressionConstraintTest {

    private static final ExpressionFactory expressionFactory = ExpressionFactory.eINSTANCE;

    @Mock
    private IValidationContext context;

    @Spy
    private ConditionExpressionConstraint constraintUnderTest;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        when(context.createSuccessStatus()).thenReturn(Status.OK_STATUS);
        when(context.createFailureStatus(anyObject())).thenReturn(new Status(IStatus.ERROR, "unknown", ""));
    }

    @Test
    public void shouldPerformBatchValidation_ReturnValidStatus_ForAnyOtherExpressionType_ThanConditionType() throws Exception {
        SequenceFlow f = ProcessFactory.eINSTANCE.createSequenceFlow();
        Expression expression = expressionFactory.createExpression();
        expression.setType(ExpressionConstants.VARIABLE_TYPE);
        f.setCondition(expression);
        when(context.getTarget()).thenReturn(f);
        assertThat(constraintUnderTest.performBatchValidation(context).isOK()).isTrue();
    }

    @Test
    public void shouldPerformBatchValidation_ReturnValidStatus_ForValidConditionType() throws Exception {
        SequenceFlow f = ProcessFactory.eINSTANCE.createSequenceFlow();
        Expression expression = expressionFactory.createExpression();
        expression.setType(ExpressionConstants.CONDITION_TYPE);
        expression.setName("1 > 2");
        expression.setContent("1 > 2");
        expression.setReturnType(Boolean.class.getName());
        f.setCondition(expression);
        when(context.getTarget()).thenReturn(f);
        Operation_Compare op = ConditionModelFactory.eINSTANCE.createOperation_Compare();
        Operation_Greater operation_Greater = ConditionModelFactory.eINSTANCE.createOperation_Greater();
        op.setOp(operation_Greater);
        doReturn(op).when(constraintUnderTest).getCompareOperation(expression);
        assertThat(constraintUnderTest.performBatchValidation(context).isOK()).isTrue();
    }

    @Test
    public void shouldPerformBatchValidation_ReturnFailureStatus_ForInvalidConditionType() throws Exception {
        SequenceFlow f = ProcessFactory.eINSTANCE.createSequenceFlow();
        Expression expression = expressionFactory.createExpression();
        expression.setType(ExpressionConstants.CONDITION_TYPE);
        expression.setName("a = a");
        expression.setContent("a = a");
        expression.setReturnType(Boolean.class.getName());
        f.setCondition(expression);
        when(context.getTarget()).thenReturn(f);
        Operation_Compare op = ConditionModelFactory.eINSTANCE.createOperation_Compare();
        doReturn(op).when(constraintUnderTest).getCompareOperation(expression);
        assertThat(constraintUnderTest.performBatchValidation(context).isOK()).isFalse();
        
        doReturn(null).when(constraintUnderTest).getCompareOperation(expression);
        assertThat(constraintUnderTest.performBatchValidation(context).isOK()).isFalse();
    }

}
