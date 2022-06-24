/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.validation.constraints;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.validation.IValidationContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OperatorConsistencyConstraintTest {

    @Mock
    private IValidationContext context;

    @Before
    public void setUp() throws Exception {
        when(context.createSuccessStatus()).thenReturn(Status.OK_STATUS);
        when(context.createFailureStatus(any())).thenReturn(new Status(IStatus.ERROR, "unknown", ""));
    }

    @Test
    public void testPerformBatchValidationForDocumentMultipleWithSetDocumentOperator() {
        final IStatus status = performValidationWithDocumentMultiplicityAndOperatorType(true, ExpressionConstants.SET_DOCUMENT_OPERATOR);
        Assertions.assertThat(status.isOK()).isFalse();
    }

    @Test
    public void testPerformBatchValidationForDocumentMultipleWithSetListDocumentOperator() {
        final IStatus status = performValidationWithDocumentMultiplicityAndOperatorType(true, ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR);
        Assertions.assertThat(status.isOK()).isTrue();
    }

    @Test
    public void testPerformBatchValidationForDocumentSimpleWithSetListDocumentOperator() {
        final IStatus status = performValidationWithDocumentMultiplicityAndOperatorType(false, ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR);
        Assertions.assertThat(status.isOK()).isFalse();
    }

    @Test
    public void testPerformBatchValidationForDocumentSimpleWithSetDocumentOperator() {
        final IStatus status = performValidationWithDocumentMultiplicityAndOperatorType(false, ExpressionConstants.SET_DOCUMENT_OPERATOR);
        Assertions.assertThat(status.isOK()).isTrue();
    }

    @Test
    public void testPerformBatchValidationReturnOkIfNoLeftOperand() {
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        final Operator operator = ExpressionFactory.eINSTANCE.createOperator();
        operator.setType(ExpressionConstants.SET_DOCUMENT_OPERATOR);
        operation.setOperator(operator);
        final Expression leftOperand = ExpressionFactory.eINSTANCE.createExpression();
        operation.setLeftOperand(leftOperand);
        when(context.getTarget()).thenReturn(operation);

        final OperatorConsistencyConstraint operatorConsistency = new OperatorConsistencyConstraint();
        final IStatus status = operatorConsistency.performBatchValidation(context);
        Assertions.assertThat(status.isOK()).isTrue();
    }


    private IStatus performValidationWithDocumentMultiplicityAndOperatorType(final boolean isMultiple, final String operatorType) {
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        final Operator operator = ExpressionFactory.eINSTANCE.createOperator();
        operator.setType(operatorType);
        operation.setOperator(operator);
        final Expression leftOperand = ExpressionFactory.eINSTANCE.createExpression();
        leftOperand.setType(ExpressionConstants.DOCUMENT_REF_TYPE);
        leftOperand.setName("doc");
        final Document documentReferenced = ProcessFactory.eINSTANCE.createDocument();
        documentReferenced.setMultiple(isMultiple);
        leftOperand.getReferencedElements().add(documentReferenced);
        operation.setLeftOperand(leftOperand);
        when(context.getTarget()).thenReturn(operation);

        final OperatorConsistencyConstraint operatorConsistency = new OperatorConsistencyConstraint();
        final IStatus status = operatorConsistency.performBatchValidation(context);
        return status;
    }

}
