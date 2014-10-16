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

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.bonitasoft.engine.bpm.document.DocumentValue;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.core.runtime.IStatus;
import org.junit.Test;

public class OperationReturnTypesValidatorTest {

    @Test
    public void testValidateConstantNotInteger() {
        final OperationReturnTypesValidator validator = new OperationReturnTypesValidator();
        final Expression dataExpression = ExpressionHelper.createConstantExpression("data", Integer.class.getName());
        validator.setDataExpression(dataExpression);
        final Expression expr = ExpressionHelper.createConstantExpression("5.2", Integer.class.getName());
        final IStatus status = validator.validate(expr);
        Assertions.assertThat(status.isOK()).isFalse();
    }

    @Test
    public void testValidateConstantInteger() {
        final OperationReturnTypesValidator validator = new OperationReturnTypesValidator();
        final Expression dataExpression = ExpressionHelper.createConstantExpression("data", Double.class.getName());
        validator.setDataExpression(dataExpression);
        final Expression expr = ExpressionHelper.createConstantExpression("5.2", Double.class.getName());
        final IStatus status = validator.validate(expr);
        Assertions.assertThat(status.isOK()).isTrue();
    }

    @Test
    public void testValidateConstantNotDouble() {
        final OperationReturnTypesValidator validator = new OperationReturnTypesValidator();
        final Expression dataExpression = ExpressionHelper.createConstantExpression("data", Double.class.getName());
        validator.setDataExpression(dataExpression);
        final Expression expr = ExpressionHelper.createConstantExpression("not a double", Double.class.getName());
        final IStatus status = validator.validate(expr);
        Assertions.assertThat(status.isOK()).isFalse();
    }

    @Test
    public void testValidateConstantDouble() {
        final OperationReturnTypesValidator validator = new OperationReturnTypesValidator();
        final Expression dataExpression = ExpressionHelper.createConstantExpression("data", Double.class.getName());
        validator.setDataExpression(dataExpression);
        final Expression expr = ExpressionHelper.createConstantExpression("5.5", Double.class.getName());
        final IStatus status = validator.validate(expr);
        Assertions.assertThat(status.isOK()).isTrue();
    }

    @Test
    public void testValidateNotMatchingReturnTypeWithData() {
        final OperationReturnTypesValidator validator = new OperationReturnTypesValidator();
        final Expression dataExpression = ExpressionHelper.createConstantExpression("data", String.class.getName());
        validator.setDataExpression(dataExpression);
        final Expression expr = ExpressionHelper.createConstantExpression("5.5", Double.class.getName());
        final IStatus status = validator.validate(expr);
        Assertions.assertThat(status.isOK()).isFalse();
    }

    @Test
    public void testValidateSetDocumentOperationInvalid() {
        final OperationReturnTypesValidator validator = new OperationReturnTypesValidator();
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        final Operator operator = ExpressionFactory.eINSTANCE.createOperator();
        operator.setType(ExpressionConstants.SET_DOCUMENT_OPERATOR);
        operation.setOperator(operator);
        final Expression dataExpression = ExpressionHelper.createConstantExpression("doc", String.class.getName());
        validator.setDataExpression(dataExpression);
        final Expression expr = ExpressionHelper.createConstantExpression("5.5", Double.class.getName());
        operation.setLeftOperand(dataExpression);
        operation.setRightOperand(expr);

        final IStatus status = validator.validate(expr);

        Assertions.assertThat(status.isOK()).isFalse();
        Assertions.assertThat(status.getMessage()).isEqualTo(Messages.incompatibleType + " " + Messages.messageOperationWithDocumentInForm);

        final Task task = ProcessFactory.eINSTANCE.createTask();
        task.getOperations().add(operation);

        final IStatus taskStatus = validator.validate(expr);
        Assertions.assertThat(taskStatus.isOK()).isFalse();
        Assertions.assertThat(taskStatus.getMessage()).isEqualTo(Messages.incompatibleType + " " + Messages.messageOperationWithDocumentInTask);

    }

    @Test
    public void testValidateSetDocumentOperationValid() {
        final Expression leftOperand = ExpressionHelper.createDocumentExpressionWithDependency("doc");

        final Expression rightOperand = ExpressionHelper.createGroovyScriptExpression("myList", DocumentValue.class.getName());
        rightOperand.setContent("[]");

        final Operator operator = ExpressionFactory.eINSTANCE.createOperator();
        operator.setType(ExpressionConstants.SET_DOCUMENT_OPERATOR);

        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        operation.setOperator(operator);
        operation.setLeftOperand(leftOperand);
        operation.setRightOperand(rightOperand);

        final OperationReturnTypesValidator validator = new OperationReturnTypesValidator();
        validator.setDataExpression(leftOperand);

        final IStatus status = validator.validate(rightOperand);

        Assertions.assertThat(status.isOK()).isTrue();
    }

    @Test
    public void testValidateSetDocumentListOperationInValid() {
        final OperationReturnTypesValidator validator = new OperationReturnTypesValidator();
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        final Operator operator = ExpressionFactory.eINSTANCE.createOperator();
        operator.setType(ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR);
        operation.setOperator(operator);
        final Expression dataExpression = ExpressionHelper.createConstantExpression("doc", String.class.getName());
        validator.setDataExpression(dataExpression);
        final Expression expr = ExpressionHelper.createGroovyScriptExpression("doc", DocumentValue.class.getName());
        operation.setLeftOperand(dataExpression);
        operation.setRightOperand(expr);

        final IStatus status = validator.validate(expr);

        Assertions.assertThat(status.isOK()).isFalse();
    }

    @Test
    public void testValidateSetDocumentListOperationValid() {
        final OperationReturnTypesValidator validator = new OperationReturnTypesValidator();
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        final Operator operator = ExpressionFactory.eINSTANCE.createOperator();
        operator.setType(ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR);
        operation.setOperator(operator);

        final Expression dataExpression = ExpressionHelper.createListDocumentExpressionWithDependency("doc");
        validator.setDataExpression(dataExpression);
        final Expression expr = ExpressionHelper.createGroovyScriptExpression("polop", List.class.getName());
        operation.setLeftOperand(dataExpression);
        operation.setRightOperand(expr);

        final IStatus status = validator.validate(expr);

        Assertions.assertThat(status.isOK()).isTrue();
    }




    @Test
    public void testValidateSetDocumentListOperationValidWithArraylist() {
        final OperationReturnTypesValidator validator = new OperationReturnTypesValidator();
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        final Operator operator = ExpressionFactory.eINSTANCE.createOperator();
        operator.setType(ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR);
        operation.setOperator(operator);
        final Expression leftOperand = ExpressionHelper.createListDocumentExpressionWithDependency("doc");
        validator.setDataExpression(leftOperand);
        final Expression rightOperand = ExpressionHelper.createGroovyScriptExpression("plop", ArrayList.class.getName());
        operation.setLeftOperand(leftOperand);
        operation.setRightOperand(rightOperand);

        final IStatus status = validator.validate(rightOperand);
        Assertions.assertThat(status.isOK()).isTrue();
    }


    @Test
    public void shouldValidateSetDocumentListOperation_add_error_message_when_right_operand_is_String_in_Operation_of_Task_Or_Form() throws Exception {

        final Expression leftOperand = ExpressionHelper.createListDocumentExpressionWithDependency("doc");

        final Expression rightOperand = ExpressionHelper.createConstantExpression("toto", "toto", String.class.getName());

        final Operator operator = ExpressionFactory.eINSTANCE.createOperator();
        operator.setType(ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR);

        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        operation.setOperator(operator);
        operation.setLeftOperand(leftOperand);
        operation.setRightOperand(rightOperand);

        final OperationReturnTypesValidator validator = new OperationReturnTypesValidator();
        validator.setDataExpression(leftOperand);


        // test in a task operation
        final Task task = ProcessFactory.eINSTANCE.createTask();
        task.getOperations().add(operation);

        final IStatus status = validator.validate(rightOperand);
        Assertions.assertThat(status.isOK()).isFalse();
        Assertions.assertThat(status.getMessage()).isEqualTo(Messages.incompatibleType + " " + Messages.messageOperationWithListDocumentInTask);

    }

    @Test
    public void shouldValidateSetListDocumentOperation_add_info_message_when_expression_is_empty_in_Operation_of_Task_Or_Form() throws Exception {

        final Expression leftOperand = ExpressionHelper.createListDocumentExpressionWithDependency("doc");

        final Expression rightOperand = ExpressionHelper.createConstantExpression("", "", String.class.getName());

        final Operator operator = ExpressionFactory.eINSTANCE.createOperator();
        operator.setType(ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR);

        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        operation.setOperator(operator);
        operation.setLeftOperand(leftOperand);
        operation.setRightOperand(rightOperand);

        final OperationReturnTypesValidator validator = new OperationReturnTypesValidator();
        validator.setDataExpression(leftOperand);

        // Test task Action
        final Task task = ProcessFactory.eINSTANCE.createTask();
        task.getOperations().add(operation);

        IStatus status = validator.validateSetListDocumentOperation(rightOperand, operation);
        Assertions.assertThat(status.isOK()).isFalse();
        Assertions.assertThat(status.getMessage()).isEqualTo(Messages.messageOperationWithListDocumentInTask);

        // Test widget Action
        final FileWidget widget = FormFactory.eINSTANCE.createFileWidget();
        widget.setAction(operation);

        status = validator.validateSetListDocumentOperation(rightOperand, operation);
        Assertions.assertThat(status.isOK()).isFalse();
        Assertions.assertThat(status.getMessage()).isEqualTo(Messages.messageOperationWithListDocumentInForm);

    }

    @Test
    public void shouldValidateSetDocumentOperation_add_info_message_when_rightOperandExpression_is_empty_in_Operation_of_Task_Or_Form() throws Exception {

        // set left operand
        final Expression leftOperand = ExpressionHelper.createDocumentExpressionWithDependency("doc");

        // set right operand
        final Expression rightOperand = ExpressionHelper.createConstantExpression("", "", String.class.getName());

        // set operator
        final Operator operator = ExpressionFactory.eINSTANCE.createOperator();
        operator.setType(ExpressionConstants.SET_DOCUMENT_OPERATOR);

        // Create Operation
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        operation.setOperator(operator);
        operation.setLeftOperand(leftOperand);
        operation.setRightOperand(rightOperand);

        final OperationReturnTypesValidator validator = new OperationReturnTypesValidator();
        validator.setDataExpression(leftOperand);

        // test in a task operation
        final Task task = ProcessFactory.eINSTANCE.createTask();
        task.getOperations().add(operation);

        final IStatus status = validator.validateSetDocumentOperation(rightOperand, operation);
        Assertions.assertThat(status.isOK()).isFalse();
        Assertions.assertThat(status.getMessage()).isEqualTo(Messages.messageOperationWithDocumentInTask);

        // test in a widget action
        final FileWidget widget = FormFactory.eINSTANCE.createFileWidget();
        widget.setAction(operation);

        final IStatus widgetStatus = validator.validateSetDocumentOperation(rightOperand, operation);
        Assertions.assertThat(widgetStatus.isOK()).isFalse();
        Assertions.assertThat(widgetStatus.getMessage()).isEqualTo(Messages.messageOperationWithDocumentInForm);

    }

}
