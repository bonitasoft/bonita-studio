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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.validation.IValidationContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Baptiste Mesta
 */
@RunWith(MockitoJUnitRunner.class)
public class OperationLeftOperandConsistencyConstraintTest {

    private static final ExpressionFactory expressionFactory = ExpressionFactory.eINSTANCE;

    @Mock
    private IValidationContext context;

    @Spy
    private OperationLeftOperandConsistencyConstraint constraint;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        when(context.createSuccessStatus()).thenReturn(Status.OK_STATUS);
        when(context.createFailureStatus(any())).thenReturn(new Status(IStatus.ERROR, "unknown", ""));
    }

    @Test
    public void should_performBatchValidation_return_valid_status_for_type_that_is_not_variable_nor_constant() throws Exception {
        //given
        final Operation operation = expressionFactory.createOperation();
        final Expression expression = expressionFactory.createExpression();
        expression.setType("TOTO");
        expression.setContent("dataName");
        operation.setLeftOperand(expression);
        when(context.getTarget()).thenReturn(operation);

        //when
        final IStatus status = constraint.performBatchValidation(context);

        //then
        assertThat(status.isOK()).isTrue();
    }

    @Test
    public void should_performBatchValidation_fail_if_type_is_data_and_referenced_element_is_missing() throws Exception {
        //given
        final Operation operation = expressionFactory.createOperation();
        final Expression expression = expressionFactory.createExpression();
        expression.setType(ExpressionConstants.VARIABLE_TYPE);
        expression.setName("dataName");
        expression.setContent("dataName");
        operation.setLeftOperand(expression);
        when(context.getTarget()).thenReturn(operation);

        //when
        final IStatus status = constraint.performBatchValidation(context);

        //then
        assertThat(status.isOK()).isFalse();
    }

    @Test
    public void should_performBatchValidation_fail_if_type_is_CONSTANT() throws Exception {
        //given
        final Operation operation = expressionFactory.createOperation();
        final Expression expression = expressionFactory.createExpression();
        expression.setType(ExpressionConstants.CONSTANT_TYPE);
        expression.setName("dataName");
        expression.setContent("dataName");
        operation.setLeftOperand(expression);
        when(context.getTarget()).thenReturn(operation);

        //when
        final IStatus status = constraint.performBatchValidation(context);

        //then
        assertThat(status.isOK()).isFalse();
    }

}
