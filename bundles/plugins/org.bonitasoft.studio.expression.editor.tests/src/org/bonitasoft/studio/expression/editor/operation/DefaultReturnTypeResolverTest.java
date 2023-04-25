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
package org.bonitasoft.studio.expression.editor.operation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.expression.builders.OperationBuilder.anOperation;
import static org.bonitasoft.studio.model.expression.builders.OperatorBuilder.anOperator;

import org.bonitasoft.engine.bpm.document.DocumentValue;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.junit.Rule;
import org.junit.Test;

public class DefaultReturnTypeResolverTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();
    
    @Test
    public void should_return_left_operand_return_type_for_ASSIGNMENT_operator() throws Exception {
        WritableValue writableValue = new WritableValue(Realm.getDefault());
        writableValue.setValue(anOperation()
                .havingLeftOperand(anExpression().withReturnType(String.class.getName()))
                .havingOperator(anOperator().withType(ExpressionConstants.ASSIGNMENT_OPERATOR))
                .havingRightOperand(anExpression()).build());
        final DefaultReturnTypeResolver defaultReturnTypeResolver = new DefaultReturnTypeResolver(writableValue);

        final String returnType = defaultReturnTypeResolver.guessRightOperandReturnType();

        assertThat(returnType).isEqualTo(String.class.getName());
    }

    @Test
    public void should_return_DocumentValue_return_type_for_SET_DOCUMENT_operator() throws Exception {
        WritableValue writableValue = new WritableValue();
        writableValue.setValue(anOperation()
                .havingLeftOperand(anExpression().withReturnType(String.class.getName()))
                .havingOperator(anOperator().withType(ExpressionConstants.SET_DOCUMENT_OPERATOR))
                .havingRightOperand(anExpression()).build());
        final DefaultReturnTypeResolver defaultReturnTypeResolver = new DefaultReturnTypeResolver(writableValue);

        final String returnType = defaultReturnTypeResolver.guessRightOperandReturnType();

        assertThat(returnType).isEqualTo(DocumentValue.class.getName());
    }

    @Test
    public void should_return_setter_parameter_return_type_for_JAVA_METHOD_OPERATOR_operator() throws Exception {
        WritableValue writableValue = new WritableValue();
        writableValue.setValue(anOperation()
                .havingLeftOperand(anExpression().withReturnType(String.class.getName()))
                .havingOperator(anOperator().withType(ExpressionConstants.JAVA_METHOD_OPERATOR).havingInputTypes(Boolean.class.getName()))
                .havingRightOperand(anExpression()).build());
        final DefaultReturnTypeResolver defaultReturnTypeResolver = new DefaultReturnTypeResolver(writableValue);

        final String returnType = defaultReturnTypeResolver.guessRightOperandReturnType();

        assertThat(returnType).isEqualTo(Boolean.class.getName());
    }
}
