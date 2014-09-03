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

import org.assertj.core.api.Assertions;
import org.bonitasoft.engine.bpm.document.DocumentValue;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
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
    }

    @Test
    public void testValidateSetDocumentOperationValid() {
        final OperationReturnTypesValidator validator = new OperationReturnTypesValidator();
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        final Operator operator = ExpressionFactory.eINSTANCE.createOperator();
        operator.setType(ExpressionConstants.SET_DOCUMENT_OPERATOR);
        operation.setOperator(operator);
        final Expression dataExpression = ExpressionHelper.createConstantExpression("doc", String.class.getName());
        validator.setDataExpression(dataExpression);
        final Expression expr = ExpressionHelper.createGroovyScriptExpression("", DocumentValue.class.getName());
        operation.setLeftOperand(dataExpression);
        operation.setRightOperand(expr);

        final IStatus status = validator.validate(expr);

        Assertions.assertThat(status.isOK()).isTrue();
    }

}
