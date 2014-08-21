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
package org.bonitasoft.studio.expression.editor.operation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import org.bonitasoft.engine.bpm.document.DocumentValue;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StorageViewerChangedListenerTest {

    @Mock
    private OperationViewer operationViewer;
    @Mock
    private SelectionChangedEvent event;
    @Mock
    private OperationReturnTypesValidator validator;
    @Mock
    private ExpressionViewer actionExpression;

    @Before
    public void setUp() {
        doReturn(validator).when(operationViewer).getOperationReturnTypeValidator();
        doReturn(actionExpression).when(operationViewer).getActionExpression();
    }

    @Test
    public void testUpdateRightOperandWithDocumentAndEmptyRightOperand() {
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();

        final Expression rightOperand = ExpressionFactory.eINSTANCE.createExpression();
        rightOperand.setType(ExpressionConstants.CONSTANT_TYPE);
        operation.setRightOperand(rightOperand);
        final Operator operator = ExpressionFactory.eINSTANCE.createOperator();
        operator.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
        operation.setOperator(operator);

        final StorageViewerChangedListener svcl = new StorageViewerChangedListener(operationViewer, operation);
        final Expression leftoperand = ExpressionFactory.eINSTANCE.createExpression();
        leftoperand.setType(ExpressionConstants.DOCUMENT_REF_TYPE);
        leftoperand.setName("docName");
        operation.setLeftOperand(leftoperand);
        doReturn(new StructuredSelection(leftoperand)).when(event).getSelection();

        svcl.selectionChanged(event);

        assertThat(operation.getOperator().getType()).isEqualTo(ExpressionConstants.SET_DOCUMENT_OPERATOR);
        assertThat(operation.getRightOperand().getReturnType()).isEqualTo(DocumentValue.class.getName());
        assertThat(operation.getRightOperand().getType()).isEqualTo(ExpressionConstants.SCRIPT_TYPE);
        assertThat(operation.getRightOperand().getInterpreter()).isEqualTo(ExpressionConstants.GROOVY);
    }

    @Test
    public void testUpdateRightOperandWithDocumentAndNotEmptyRightOperand() {
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();

        final Expression rightOperand = ExpressionFactory.eINSTANCE.createExpression();
        rightOperand.setType(ExpressionConstants.CONSTANT_TYPE);
        rightOperand.setName("docName");
        rightOperand.setContent("content");
        final String testReturnType = "testReturnType";
        rightOperand.setReturnType(testReturnType);
        operation.setRightOperand(rightOperand);
        final Operator operator = ExpressionFactory.eINSTANCE.createOperator();
        operator.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
        operation.setOperator(operator);

        final StorageViewerChangedListener svcl = new StorageViewerChangedListener(operationViewer, operation);
        final Expression leftoperand = ExpressionFactory.eINSTANCE.createExpression();
        leftoperand.setType(ExpressionConstants.DOCUMENT_REF_TYPE);
        leftoperand.setName("docName");
        operation.setLeftOperand(leftoperand);
        doReturn(new StructuredSelection(leftoperand)).when(event).getSelection();

        svcl.selectionChanged(event);

        assertThat(operation.getOperator().getType()).isEqualTo(ExpressionConstants.SET_DOCUMENT_OPERATOR);
        assertThat(operation.getRightOperand().getReturnType()).isEqualTo(testReturnType);
        assertThat(operation.getRightOperand().getType()).isEqualTo(ExpressionConstants.CONSTANT_TYPE);
    }

    @Test
    public void testUpdateRightOperandWithDataOfPrimitiveTypeAndNotEmptyRightOperand() {
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();

        final Expression rightOperand = ExpressionFactory.eINSTANCE.createExpression();
        rightOperand.setType(ExpressionConstants.CONSTANT_TYPE);
        rightOperand.setName("rightName");
        rightOperand.setContent("content");
        rightOperand.setReturnType(DocumentValue.class.getName());
        operation.setRightOperand(rightOperand);
        final Operator operator = ExpressionFactory.eINSTANCE.createOperator();
        operator.setType(ExpressionConstants.DOCUMENT_REF_TYPE);
        operation.setOperator(operator);

        final StorageViewerChangedListener svcl = new StorageViewerChangedListener(operationViewer, operation);
        final Expression leftoperand = ExpressionFactory.eINSTANCE.createExpression();
        leftoperand.setType(ExpressionConstants.VARIABLE_TYPE);
        leftoperand.setName("carName");
        leftoperand.setReturnType(Integer.class.getName());
        operation.setLeftOperand(leftoperand);
        doReturn(new StructuredSelection(leftoperand)).when(event).getSelection();

        svcl.selectionChanged(event);

        assertThat(operation.getOperator().getType()).isEqualTo(ExpressionConstants.ASSIGNMENT_OPERATOR);
        assertThat(operation.getRightOperand().getReturnType()).isEqualTo(Integer.class.getName());
    }

    @Test
    public void testUpdateRightOperandWithDataOfNotPrimitiveTypeAndNotEmptyRightOperand() {
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();

        final Expression rightOperand = ExpressionFactory.eINSTANCE.createExpression();
        rightOperand.setType(ExpressionConstants.CONSTANT_TYPE);
        rightOperand.setName("rightName");
        rightOperand.setContent("content");
        final String testReturnType = "testReturnType";
        rightOperand.setReturnType(testReturnType);
        operation.setRightOperand(rightOperand);
        final Operator operator = ExpressionFactory.eINSTANCE.createOperator();
        operator.setType(ExpressionConstants.DOCUMENT_REF_TYPE);
        operation.setOperator(operator);

        final StorageViewerChangedListener svcl = new StorageViewerChangedListener(operationViewer, operation);
        final Expression leftoperand = ExpressionFactory.eINSTANCE.createExpression();
        leftoperand.setType(ExpressionConstants.VARIABLE_TYPE);
        leftoperand.setName("carName");
        leftoperand.setReturnType("newTypeNotPrimitive");
        operation.setLeftOperand(leftoperand);
        doReturn(new StructuredSelection(leftoperand)).when(event).getSelection();

        svcl.selectionChanged(event);

        assertThat(operation.getOperator().getType()).isEqualTo(ExpressionConstants.ASSIGNMENT_OPERATOR);
        assertThat(operation.getRightOperand().getReturnType()).isEqualTo(testReturnType);
    }

}
