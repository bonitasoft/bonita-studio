/**
 * Copyright (C) 2014-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.expression.editor.viewer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.bonitasoft.engine.bpm.document.DocumentValue;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.emf.common.command.CompoundCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReadOnlyExpressionViewerTest {

    @Mock
    private ReadOnlyExpressionViewer roew;

    @Test
    public void testGetNewOperatorTypeForAssignmentToDocument() {
        final String currentOperatorType = ExpressionConstants.ASSIGNMENT_OPERATOR;
        final String newStorageType = ExpressionConstants.DOCUMENT_REF_TYPE;
        final String expectedOperator = ExpressionConstants.SET_DOCUMENT_OPERATOR;
        testGetNewOperatorTypeFor(currentOperatorType, newStorageType, expectedOperator);
    }

    @Test
    public void testGetNewOperatorTypeForAssignmentToDocumentList() {
        final String currentOperatorType = ExpressionConstants.ASSIGNMENT_OPERATOR;
        final String newStorageType = ExpressionConstants.DOCUMENT_REF_TYPE;
        final String expectedOperator = ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR;
        doCallRealMethod().when(roew).getNewOperatorTypeFor(any(Operator.class), any(Expression.class));

        final Operator currentOperator = ExpressionFactory.eINSTANCE.createOperator();
        currentOperator.setType(currentOperatorType);
        final Expression newStorageExpression = ExpressionFactory.eINSTANCE.createExpression();
        newStorageExpression.setType(newStorageType);
        final Document documentReferenced = ProcessFactory.eINSTANCE.createDocument();
        documentReferenced.setMultiple(true);
        newStorageExpression.getReferencedElements().add(documentReferenced);

        final String newOperatorType = roew.getNewOperatorTypeFor(currentOperator, newStorageExpression);

        assertThat(newOperatorType).isEqualTo(expectedOperator);
    }

    @Test
    public void testGetNewOperatorTypeForBusinessDataSetterToDocument() {
        final String currentOperatorType = ExpressionConstants.BUSINESS_DATA_JAVA_SETTER_OPERATOR;
        final String newStorageType = ExpressionConstants.DOCUMENT_REF_TYPE;
        final String expectedOperator = ExpressionConstants.SET_DOCUMENT_OPERATOR;
        testGetNewOperatorTypeFor(currentOperatorType, newStorageType, expectedOperator);
    }

    @Test
    public void testGetNewOperatorTypeForDocRefToBD() {
        final String currentOperatorType = ExpressionConstants.SET_DOCUMENT_OPERATOR;
        final String newStorageType = ExpressionConstants.BUSINESS_DATA_TYPE;
        final String expectedOperator = ExpressionConstants.BUSINESS_DATA_JAVA_SETTER_OPERATOR;
        testGetNewOperatorTypeFor(currentOperatorType, newStorageType, expectedOperator);
    }

    @Test
    public void testGetNewOperatorTypeForDataToBD() {
        final String currentOperatorType = ExpressionConstants.ASSIGNMENT_OPERATOR;
        final String newStorageType = ExpressionConstants.BUSINESS_DATA_TYPE;
        final String expectedOperator = ExpressionConstants.BUSINESS_DATA_JAVA_SETTER_OPERATOR;
        testGetNewOperatorTypeFor(currentOperatorType, newStorageType, expectedOperator);
    }

    @Test
    public void testGetNewOperatorTypeForDocumentToVariable() {
        final String currentOperatorType = ExpressionConstants.SET_DOCUMENT_OPERATOR;
        final String newStorageType = ExpressionConstants.VARIABLE_TYPE;
        final String expectedOperator = ExpressionConstants.ASSIGNMENT_OPERATOR;
        testGetNewOperatorTypeFor(currentOperatorType, newStorageType, expectedOperator);
    }

    @Test
    public void testGetNewOperatorTypeForSearchIndexToVariable() {
        final String currentOperatorType = ExpressionConstants.ASSIGNMENT_OPERATOR;
        final String newStorageType = ExpressionConstants.SEARCH_INDEX_TYPE;
        testGetNewOperatorTypeFor(currentOperatorType, newStorageType, currentOperatorType);
    }

    @Test
    public void testGetNewOperatorTypeForTransientVarToVariable() {
        final String currentOperatorType = ExpressionConstants.ASSIGNMENT_OPERATOR;
        final String newStorageType = ExpressionConstants.TRANSIENT_VARIABLE_TYPE;
        testGetNewOperatorTypeFor(currentOperatorType, newStorageType, currentOperatorType);
    }

    @Test
    public void testGetNewOperatorTypeForBDToVariable() {
        final String currentOperatorType = ExpressionConstants.BUSINESS_DATA_JAVA_SETTER_OPERATOR;
        final String newStorageType = ExpressionConstants.VARIABLE_TYPE;
        final String expectedOperator = ExpressionConstants.ASSIGNMENT_OPERATOR;
        testGetNewOperatorTypeFor(currentOperatorType, newStorageType, expectedOperator);
    }

    @Test
    public void testGetNewOperatorTypeForSetDocumentToDocument() {
        final String currentOperatorType = ExpressionConstants.SET_DOCUMENT_OPERATOR;
        final String newStorageType = ExpressionConstants.DOCUMENT_REF_TYPE;
        testGetNewOperatorTypeFor(currentOperatorType, newStorageType, currentOperatorType);
    }

    @Test
    public void testGetNewOperatorTypeForBDJavaSetterToBD() {
        final String currentOperatorType = ExpressionConstants.BUSINESS_DATA_JAVA_SETTER_OPERATOR;
        final String newStorageType = ExpressionConstants.BUSINESS_DATA_TYPE;
        testGetNewOperatorTypeFor(currentOperatorType, newStorageType, currentOperatorType);
    }

    @Test
    public void testGetNewOperatorTypeForBDCretaeToBD() {
        final String currentOperatorType = ExpressionConstants.CREATE_BUSINESS_DATA_OPERATOR;
        final String newStorageType = ExpressionConstants.BUSINESS_DATA_TYPE;
        testGetNewOperatorTypeFor(currentOperatorType, newStorageType, currentOperatorType);
    }

    private void testGetNewOperatorTypeFor(final String currentOperatorType, final String newStorageType, final String expectedOperator) {
        doCallRealMethod().when(roew).getNewOperatorTypeFor(any(Operator.class), any(Expression.class));

        final Operator currentOperator = ExpressionFactory.eINSTANCE.createOperator();
        currentOperator.setType(currentOperatorType);
        final Expression newStorageExpression = ExpressionFactory.eINSTANCE.createExpression();
        newStorageExpression.setType(newStorageType);
        if (ExpressionConstants.DOCUMENT_REF_TYPE.equals(newStorageType)) {
            final Document documentReferenced = ProcessFactory.eINSTANCE.createDocument();
            newStorageExpression.getReferencedElements().add(documentReferenced);
        }

        final String newOperatorType = roew.getNewOperatorTypeFor(currentOperator, newStorageExpression);

        assertThat(newOperatorType).isEqualTo(expectedOperator);
    }

    @Test
    public void testUpdateRightOperandWithDocument() {
        doCallRealMethod().when(roew).updateRightOperand(any(CompoundCommand.class), any(Operation.class), any(String.class), any(Expression.class));

        final Operation operation = createEmptyOperation();

        final Expression newLeftOperand = ExpressionFactory.eINSTANCE.createExpression();
        operation.setLeftOperand(newLeftOperand);

        roew.updateRightOperand(new CompoundCommand(), operation, ExpressionConstants.SET_DOCUMENT_OPERATOR, newLeftOperand);

        verify(roew).appendCommandToSetReturnType(any(CompoundCommand.class), any(Expression.class), eq(DocumentValue.class.getName()));
    }

    protected Operation createEmptyOperation() {
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        final Expression oldRightOperand = ExpressionFactory.eINSTANCE.createExpression();
        operation.setRightOperand(oldRightOperand);
        final Expression leftOperand = ExpressionFactory.eINSTANCE.createExpression();
        operation.setLeftOperand(leftOperand);
        return operation;
    }

    @Test
    public void testUpdateRightOperandWithDocumentMultiple() {
        doCallRealMethod().when(roew).updateRightOperand(any(CompoundCommand.class), any(Operation.class), any(String.class), any(Expression.class));

        final Operation operation = createEmptyOperation();

        final Expression newLeftOperand = ExpressionFactory.eINSTANCE.createExpression();
        final Document documentReferenced = ProcessFactory.eINSTANCE.createDocument();
        documentReferenced.setMultiple(true);
        newLeftOperand.getReferencedElements().add(documentReferenced);
        operation.setLeftOperand(newLeftOperand);

        roew.updateRightOperand(new CompoundCommand(), operation, ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR, newLeftOperand);

        verify(roew).appendCommandToSetReturnType(any(CompoundCommand.class), any(Expression.class), eq(List.class.getName()));
    }

    @Test
    public void testUpdateRightOperandWithDocumentFromDocumentAndClearedExpression() {
        doCallRealMethod().when(roew).updateRightOperand(any(CompoundCommand.class), any(Operation.class), any(String.class), any(Expression.class));

        final Operation operation = createEmptyOperation();

        final Expression newLeftOperand = ExpressionFactory.eINSTANCE.createExpression();
        newLeftOperand.setReturnType(String.class.getName());
        operation.setLeftOperand(newLeftOperand);

        roew.updateRightOperand(new CompoundCommand(), operation, ExpressionConstants.SET_DOCUMENT_OPERATOR, newLeftOperand);

        verify(roew).appendCommandToSetReturnType(any(CompoundCommand.class), any(Expression.class), eq(DocumentValue.class.getName()));
    }

    @Test
    public void testUpdateRightOperandWithPrimitiveType() {
        doCallRealMethod().when(roew).updateRightOperand(any(CompoundCommand.class), any(Operation.class), any(String.class), any(Expression.class));

        final Operation operation = createOperationWithEmptyConstantRightOperand();

        final Expression newLeftOperand = ExpressionFactory.eINSTANCE.createExpression();
        newLeftOperand.setReturnType(String.class.getName());
        operation.setLeftOperand(newLeftOperand);

        roew.updateRightOperand(new CompoundCommand(), operation, ExpressionConstants.ASSIGNMENT_OPERATOR, newLeftOperand);

        verify(roew).appendCommandToSetReturnType(any(CompoundCommand.class), any(Expression.class), eq(String.class.getName()));
    }

    @Test
    public void testUpdateRightOperandWithBooleanPrimitiveType() {
        doCallRealMethod().when(roew).updateRightOperand(any(CompoundCommand.class), any(Operation.class), any(String.class), any(Expression.class));

        final Operation operation = createOperationWithEmptyConstantRightOperand();

        final Expression newLeftOperand = ExpressionFactory.eINSTANCE.createExpression();
        newLeftOperand.setReturnType(Boolean.class.getName());
        operation.setLeftOperand(newLeftOperand);

        roew.updateRightOperand(new CompoundCommand(), operation, ExpressionConstants.ASSIGNMENT_OPERATOR, newLeftOperand);

        verify(roew).appendCommandToSetReturnType(any(CompoundCommand.class), any(Expression.class), eq(Boolean.class.getName()));
    }

    protected Operation createOperationWithEmptyConstantRightOperand() {
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        final Expression oldRightOperand = ExpressionHelper.createConstantExpression("", "");
        operation.setRightOperand(oldRightOperand);
        final Expression oldLeftOperand = ExpressionFactory.eINSTANCE.createExpression();
        oldLeftOperand.setReturnType(String.class.getName());
        operation.setLeftOperand(oldLeftOperand);
        return operation;
    }

    @Test
    public void testUpdateRightOperandNotDoneWithNoLeftOperand() {
        doCallRealMethod().when(roew).updateRightOperand(any(CompoundCommand.class), any(Operation.class), any(String.class), any(Expression.class));

        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        final Expression oldRightOperand = ExpressionFactory.eINSTANCE.createExpression();
        operation.setRightOperand(oldRightOperand);
        roew.updateRightOperand(new CompoundCommand(), operation, ExpressionConstants.SET_DOCUMENT_OPERATOR, null);

        verify(roew, times(0)).appendCommandToSetReturnType(any(CompoundCommand.class), any(Expression.class), eq(DocumentValue.class.getName()));
    }
}
