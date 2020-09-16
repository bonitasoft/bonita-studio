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
package org.bonitasoft.studio.engine.export.expression.converter.comparison;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.bonitasoft.engine.expression.ExpressionType;
import org.bonitasoft.engine.expression.InvalidExpressionException;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.condition.conditionModel.ConditionModelFactory;
import org.bonitasoft.studio.condition.conditionModel.Expression_ProcessRef;
import org.bonitasoft.studio.condition.conditionModel.Operation_Compare;
import org.bonitasoft.studio.condition.conditionModel.Operation_Less_Equals;
import org.bonitasoft.studio.condition.conditionModel.Operation_NotUnary;
import org.bonitasoft.studio.condition.conditionModel.Operation_Unary;
import org.bonitasoft.studio.condition.ui.expression.ComparisonExpressionLoadException;
import org.bonitasoft.studio.condition.ui.expression.XtextComparisonExpressionLoader;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.builders.ExpressionBuilder;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.parameter.builders.ParameterBuilder;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.builders.BooleanDataTypeBuilder;
import org.bonitasoft.studio.model.process.builders.DataBuilder;
import org.bonitasoft.studio.model.process.builders.DoubleDataTypeBuilder;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class ComparisonExpressionConverterTest {

    private ComparisonExpressionConverter comparisonExpressionConverter;

    @Mock
    private XtextComparisonExpressionLoader loader;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Data variable;
    private Parameter parameter;
    private Expression binaryExpression;
    private Data validVariable;
    private Expression notUnaryExpression;
    private Expression unaryExpression;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        comparisonExpressionConverter = new ComparisonExpressionConverter(loader);
        variable = DataBuilder.aData().withName("amount").havingDataType(DoubleDataTypeBuilder.aDoubleDataType()).build();
        validVariable = DataBuilder.aData().withName("valid").havingDataType(BooleanDataTypeBuilder.aBooleanDataType())
                .build();
        parameter = ParameterBuilder.aParameter().withName("commission").withType(Double.class.getName()).build();
        binaryExpression = ExpressionBuilder.anExpression()
                .withExpressionType(ExpressionConstants.CONDITION_TYPE)
                .withContent("amount <= commission")
                .withReturnType(Boolean.class.getName())
                .havingReferencedElements(EcoreUtil.copy(variable), EcoreUtil.copy(parameter)).build();

        notUnaryExpression = ExpressionBuilder.anExpression()
                .withExpressionType(ExpressionConstants.CONDITION_TYPE)
                .withContent("!valid")
                .withReturnType(Boolean.class.getName())
                .havingReferencedElements(EcoreUtil.copy(validVariable)).build();

        unaryExpression = ExpressionBuilder.anExpression()
                .withExpressionType(ExpressionConstants.CONDITION_TYPE)
                .withContent("valid")
                .withReturnType(Boolean.class.getName())
                .havingReferencedElements(EcoreUtil.copy(validVariable)).build();

    }

    @Test
    public void should_appliesTo_Condition_expression_type() throws Exception {
        assertThat(comparisonExpressionConverter.appliesTo(binaryExpression)).isTrue();
        assertThat(comparisonExpressionConverter.appliesTo(null)).isFalse();
        assertThat(comparisonExpressionConverter
                .appliesTo(ExpressionBuilder.anExpression().withExpressionType(ExpressionConstants.CONSTANT_TYPE).build()))
                        .isFalse();
    }

    @Test
    public void should_throw_an_InvalidExpressionException_when_converting_a_studio_expression_into_an_engine_expression_if_parsing_return_null()
            throws Exception {
        when(loader.loadConditionExpression(binaryExpression.getContent(), null)).thenReturn(null);

        thrown.expect(InvalidExpressionException.class);

        comparisonExpressionConverter.convert(binaryExpression);
    }

    @Test
    public void should_convert_a_studio_expression_into_an_engine_expression_returns_null_if_parsing_fail()
            throws Exception {
        when(loader.loadConditionExpression(binaryExpression.getContent(), null))
                .thenThrow(new ComparisonExpressionLoadException(""));

        thrown.expect(InvalidExpressionException.class);

        comparisonExpressionConverter.convert(binaryExpression);
    }

    @Test
    public void should_convert_a_studio_expression_into_an_engine_expression_for_binary_operation() throws Exception {
        final Operation_Compare binaryOperation = ConditionModelFactory.eINSTANCE.createOperation_Compare();
        final Operation_Less_Equals less_Equals = ConditionModelFactory.eINSTANCE.createOperation_Less_Equals();
        final Expression_ProcessRef processRef = ConditionModelFactory.eINSTANCE.createExpression_ProcessRef();
        processRef.setValue(EcoreUtil.copy(variable));
        less_Equals.setLeft(processRef);
        final Expression_ProcessRef param_Ref = ConditionModelFactory.eINSTANCE.createExpression_ProcessRef();
        param_Ref.setValue(EcoreUtil.copy(parameter));
        less_Equals.setRight(param_Ref);
        binaryOperation.setOp(less_Equals);

        when(loader.loadConditionExpression(binaryExpression.getContent(), null)).thenReturn(binaryOperation);

        final org.bonitasoft.engine.expression.Expression engineExpression = comparisonExpressionConverter
                .convert(binaryExpression);
        assertThat(engineExpression).isNotNull();
        assertThat(engineExpression.getExpressionType()).isEqualTo(ExpressionType.TYPE_CONDITION.name());
        assertThat(engineExpression.getContent()).isEqualTo("<=");
        assertThat(engineExpression.getReturnType()).isEqualTo(Boolean.class.getName());
        assertThat(engineExpression.getDependencies()).hasSize(2);
        final org.bonitasoft.engine.expression.Expression leftOperand = engineExpression.getDependencies().get(0);
        assertThat(leftOperand.getExpressionType()).isEqualTo(ExpressionType.TYPE_VARIABLE.name());
        assertThat(leftOperand.getContent()).isEqualTo("amount");

        final org.bonitasoft.engine.expression.Expression rightOperand = engineExpression.getDependencies().get(1);
        assertThat(rightOperand.getExpressionType()).isEqualTo(ExpressionType.TYPE_PARAMETER.name());
        assertThat(rightOperand.getContent()).isEqualTo("commission");
    }

    @Test
    public void should_convert_a_studio_expression_into_an_engine_expression_for_notunary_operation() throws Exception {
        final Operation_Compare operation_Compare = ConditionModelFactory.eINSTANCE.createOperation_Compare();
        final Operation_NotUnary unaryOperation = ConditionModelFactory.eINSTANCE.createOperation_NotUnary();
        final Expression_ProcessRef processRef = ConditionModelFactory.eINSTANCE.createExpression_ProcessRef();
        processRef.setValue(EcoreUtil.copy(validVariable));
        unaryOperation.setValue(processRef);
        operation_Compare.setOp(unaryOperation);

        when(loader.loadConditionExpression(notUnaryExpression.getContent(), null)).thenReturn(operation_Compare);

        final org.bonitasoft.engine.expression.Expression engineExpression = comparisonExpressionConverter
                .convert(notUnaryExpression);
        assertThat(engineExpression).isNotNull();
        assertThat(engineExpression.getExpressionType()).isEqualTo(ExpressionType.TYPE_CONDITION.name());
        assertThat(engineExpression.getContent()).isEqualTo("!");
        assertThat(engineExpression.getReturnType()).isEqualTo(Boolean.class.getName());
        assertThat(engineExpression.getDependencies()).hasSize(1);
        final org.bonitasoft.engine.expression.Expression operand = engineExpression.getDependencies().get(0);
        assertThat(operand.getExpressionType()).isEqualTo(ExpressionType.TYPE_VARIABLE.name());
        assertThat(operand.getContent()).isEqualTo("valid");
    }

    @Test
    public void should_convert_a_studio_expression_into_an_engine_expression_for_unary_operation() throws Exception {
        final Operation_Compare operation_Compare = ConditionModelFactory.eINSTANCE.createOperation_Compare();
        final Operation_Unary unaryOperation = ConditionModelFactory.eINSTANCE.createOperation_Unary();
        final Expression_ProcessRef processRef = ConditionModelFactory.eINSTANCE.createExpression_ProcessRef();
        processRef.setValue(EcoreUtil.copy(validVariable));
        unaryOperation.setValue(processRef);
        operation_Compare.setOp(unaryOperation);
        when(loader.loadConditionExpression(unaryExpression.getContent(), null)).thenReturn(operation_Compare);

        final org.bonitasoft.engine.expression.Expression engineExpression = comparisonExpressionConverter
                .convert(unaryExpression);
        assertThat(engineExpression).isNotNull();
        assertThat(engineExpression.getExpressionType()).isEqualTo(ExpressionType.TYPE_VARIABLE.name());
        assertThat(engineExpression.getContent()).isEqualTo("valid");
        assertThat(engineExpression.getReturnType()).isEqualTo(Boolean.class.getName());
        assertThat(engineExpression.getDependencies()).isEmpty();
    }

}
