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
package org.bonitasoft.studio.expression.editor.operation;

import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.process.builders.DataBuilder.aData;
import static org.bonitasoft.studio.model.process.builders.JavaObjectDataBuilder.aJavaObjectData;
import static org.bonitasoft.studio.model.process.builders.LongDataTypeBuilder.aLongDataType;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.bonitasoft.engine.bpm.document.DocumentValue;
import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.core.runtime.IStatus;
import org.junit.Test;

public class OperationReturnTypesValidatorTest {

    @Test
    public void testValidateConstantNotInteger() {
        final OperationReturnTypesValidator validator = new OperationReturnTypesValidator();
        final Expression dataExpression = ExpressionHelper.createConstantExpression("data", Integer.class.getName());
        final Expression expr = ExpressionHelper.createConstantExpression("5.2", Integer.class.getName());
        createOperation(dataExpression, expr, ExpressionConstants.ASSIGNMENT_OPERATOR);
        final IStatus status = validator.validate(expr);
        Assertions.assertThat(status.isOK()).isFalse();
    }

    private Operation createOperation(final Expression dataExpression, final Expression expr, final String operatorType) {
        final Operation createOperation = ExpressionFactory.eINSTANCE.createOperation();
        createOperation.setLeftOperand(dataExpression);
        createOperation.setRightOperand(expr);
        final Operator assignement = ExpressionFactory.eINSTANCE.createOperator();
        assignement.setType(operatorType);
        createOperation.setOperator(assignement);
        return createOperation;
    }

    @Test
    public void testValidateConstantInteger() {
        final OperationReturnTypesValidator validator = new OperationReturnTypesValidator();
        final Expression dataExpression = ExpressionHelper.createConstantExpression("data", Double.class.getName());
        final Expression expr = ExpressionHelper.createConstantExpression("5.2", Double.class.getName());
        createOperation(dataExpression, expr, ExpressionConstants.ASSIGNMENT_OPERATOR);
        final IStatus status = validator.validate(expr);
        Assertions.assertThat(status.isOK()).isTrue();
    }

    @Test
    public void testValidateConstantNotDouble() {
        final OperationReturnTypesValidator validator = new OperationReturnTypesValidator();
        final Expression dataExpression = ExpressionHelper.createConstantExpression("data", Double.class.getName());
        final Expression expr = ExpressionHelper.createConstantExpression("not a double", Double.class.getName());
        createOperation(dataExpression, expr, ExpressionConstants.ASSIGNMENT_OPERATOR);
        final IStatus status = validator.validate(expr);
        Assertions.assertThat(status.isOK()).isFalse();
    }

    @Test
    public void testValidateConstantDouble() {
        final OperationReturnTypesValidator validator = new OperationReturnTypesValidator();
        final Expression dataExpression = ExpressionHelper.createConstantExpression("data", Double.class.getName());
        final Expression expr = ExpressionHelper.createConstantExpression("5.5", Double.class.getName());
        createOperation(dataExpression, expr, ExpressionConstants.ASSIGNMENT_OPERATOR);
        final IStatus status = validator.validate(expr);
        Assertions.assertThat(status.isOK()).isTrue();
    }

    @Test
    public void testValidateNotMatchingReturnTypeWithData() {
        final OperationReturnTypesValidator validator = new OperationReturnTypesValidator();
        final Expression dataExpression = ExpressionHelper.createConstantExpression("data", String.class.getName());
        final Expression expr = ExpressionHelper.createConstantExpression("5.5", Double.class.getName());
        createOperation(dataExpression, expr, ExpressionConstants.ASSIGNMENT_OPERATOR);
        final IStatus status = validator.validate(expr);
        Assertions.assertThat(status.isOK()).isFalse();
    }

    @Test
    public void testValidateSetDocumentOperationInvalid() {
        final OperationReturnTypesValidator validator = new OperationReturnTypesValidator();
        final Expression dataExpression = ExpressionHelper.createConstantExpression("doc", String.class.getName());
        final Expression expr = ExpressionHelper.createConstantExpression("5.5", Double.class.getName());
        final Operation operation = createOperation(dataExpression, expr, ExpressionConstants.SET_DOCUMENT_OPERATOR);
        final IStatus status = validator.validate(expr);

        Assertions.assertThat(status.isOK()).isFalse();
        Assertions.assertThat(status.getMessage())
                .isEqualTo(Messages.incompatibleType + " " + Messages.messageOperationWithDocumentInForm);

        final Task task = ProcessFactory.eINSTANCE.createTask();
        task.getOperations().add(operation);

        final IStatus taskStatus = validator.validate(expr);
        Assertions.assertThat(taskStatus.isOK()).isFalse();
        Assertions.assertThat(taskStatus.getMessage())
                .isEqualTo(Messages.incompatibleType + " " + Messages.messageOperationWithDocumentInTask);

    }


    @Test
    public void testValidateSetDocumentListOperationInValid() {
        final OperationReturnTypesValidator validator = new OperationReturnTypesValidator();
        final Expression dataExpression = ExpressionHelper.createConstantExpression("doc", String.class.getName());
        final Expression expr = ExpressionHelper.createGroovyScriptExpression("doc", DocumentValue.class.getName());
        createOperation(dataExpression, expr, ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR);
        final IStatus status = validator.validate(expr);
        Assertions.assertThat(status.isOK()).isFalse();
    }

    @Test
    public void testValidateSetDocumentListOperationValid() {
        final OperationReturnTypesValidator validator = new OperationReturnTypesValidator();
        final Expression dataExpression = ExpressionHelper.createListDocumentExpressionWithDependency("doc");
        final Expression expr = ExpressionHelper.createGroovyScriptExpression("polop", List.class.getName());
        createOperation(dataExpression, expr, ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR);
        final IStatus status = validator.validate(expr);
        Assertions.assertThat(status.isOK()).isTrue();
    }

    @Test
    public void testValidateSetDocumentListOperationValidWithArraylist() {
        final OperationReturnTypesValidator validator = new OperationReturnTypesValidator();
        final Expression leftOperand = ExpressionHelper.createListDocumentExpressionWithDependency("doc");
        final Expression rightOperand = ExpressionHelper.createGroovyScriptExpression("plop", ArrayList.class.getName());
        createOperation(leftOperand, rightOperand, ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR);
        final IStatus status = validator.validate(rightOperand);
        Assertions.assertThat(status.isOK()).isTrue();
    }

    @Test
    public void shouldValidateSetDocumentListOperation_add_error_message_when_right_operand_is_String_in_Operation_of_Task_Or_Form()
            throws Exception {
        final Expression leftOperand = ExpressionHelper.createListDocumentExpressionWithDependency("doc");
        final Expression rightOperand = ExpressionHelper.createConstantExpression("toto", "toto", String.class.getName());
        final Operation operation = createOperation(leftOperand, rightOperand,
                ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR);
        final OperationReturnTypesValidator validator = new OperationReturnTypesValidator();

        // test in a task operation
        final Task task = ProcessFactory.eINSTANCE.createTask();
        task.getOperations().add(operation);

        final IStatus status = validator.validate(rightOperand);
        Assertions.assertThat(status.isOK()).isFalse();
        Assertions.assertThat(status.getMessage())
                .isEqualTo(Messages.incompatibleType + " " + Messages.messageOperationWithListDocumentInTask);
    }

    @Test
    public void shouldValidateSetListDocumentOperation_add_info_message_when_expression_is_empty_in_Operation_of_Task()
            throws Exception {
        final Expression leftOperand = ExpressionHelper.createListDocumentExpressionWithDependency("doc");
        final Expression rightOperand = ExpressionHelper.createConstantExpression("", "", String.class.getName());
        final Operation operation = createOperation(leftOperand, rightOperand,
                ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR);
        final OperationReturnTypesValidator validator = new OperationReturnTypesValidator();

        // Test task Action
        final Task task = ProcessFactory.eINSTANCE.createTask();
        task.getOperations().add(operation);

        IStatus status = validator.validateSetListDocumentOperation(rightOperand, operation);
        Assertions.assertThat(status.isOK()).isFalse();
        Assertions.assertThat(status.getMessage()).isEqualTo(Messages.messageOperationWithListDocumentInTask);
    }


    @Test
    public void should_fail_when_assigning_a_business_object_in_a_process_data() throws Exception {
        final Expression leftOperand = ExpressionHelper
                .createVariableExpression(aJavaObjectData().withName("employee").withClassname("org.test.Employee")
                        .build());
        final Expression rightOperand = anExpression().withExpressionType(ExpressionConstants.QUERY_TYPE)
                .withName("Employee.findById")
                .withContent("SELECT AN EMPLOYEE").withReturnType("org.test.Employee").build();
        final Operation operation = createOperation(leftOperand, rightOperand, ExpressionConstants.ASSIGNMENT_OPERATOR);

        final OperationReturnTypesValidator validator = new OperationReturnTypesValidator();
        final IStatus status = validator.validate(operation.getRightOperand());

        StatusAssert.assertThat(status).isNotOK().hasMessage(Messages.cannotStoreBusinessObjectInProcessVariable);
    }

    @Test
    public void should_fail_when_assigning_a_business_object_in_a_process_data_using_java_method_operator()
            throws Exception {
        final Expression leftOperand = ExpressionHelper
                .createVariableExpression(aJavaObjectData().withName("employee").withClassname("org.test.Employee")
                        .build());
        final Expression rightOperand = anExpression().withExpressionType(ExpressionConstants.QUERY_TYPE)
                .withName("Employee.findById")
                .withContent("SELECT AN EMPLOYEE").withReturnType("org.test.Employee").build();
        final Operation operation = createOperation(leftOperand, rightOperand, ExpressionConstants.JAVA_METHOD_OPERATOR);

        final OperationReturnTypesValidator validator = new OperationReturnTypesValidator();
        final IStatus status = validator.validate(operation.getRightOperand());

        StatusAssert.assertThat(status).isNotOK().hasMessage(Messages.cannotStoreBusinessObjectInProcessVariable);
    }

    @Test
    public void should_not_fail_when_assigning_a_primitive_query_result_in_a_process_data() throws Exception {
        final Expression leftOperand = ExpressionHelper
                .createVariableExpression(aData().havingDataType(aLongDataType()).withName("nbEmployee").build());
        final Expression rightOperand = anExpression().withExpressionType(ExpressionConstants.QUERY_TYPE)
                .withName("Employee.countForfind")
                .withContent("SELECT AN EMPLOYEE").withReturnType(Long.class.getName()).build();
        final Operation operation = createOperation(leftOperand, rightOperand, ExpressionConstants.ASSIGNMENT_OPERATOR);

        final OperationReturnTypesValidator validator = new OperationReturnTypesValidator();
        final IStatus status = validator.validate(operation.getRightOperand());

        StatusAssert.assertThat(status).isOK();
    }
}
