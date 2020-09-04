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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.engine.export.expression.converter.comparison;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.engine.expression.Expression;
import org.bonitasoft.engine.expression.ExpressionType;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.condition.conditionModel.ConditionModelFactory;
import org.bonitasoft.studio.condition.conditionModel.Expression_Boolean;
import org.bonitasoft.studio.condition.conditionModel.Expression_Double;
import org.bonitasoft.studio.condition.conditionModel.Expression_Integer;
import org.bonitasoft.studio.condition.conditionModel.Expression_ProcessRef;
import org.bonitasoft.studio.condition.conditionModel.Expression_String;
import org.bonitasoft.studio.model.expression.builders.ExpressionBuilder;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.parameter.builders.ParameterBuilder;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.builders.DataBuilder;
import org.bonitasoft.studio.model.process.builders.DoubleDataTypeBuilder;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Romain Bioteau
 *
 */
public class ExpressionConditionModelSwitchTest {

    private ExpressionConditionModelSwitch conditionModelSwitch;
    private Data variable;
    private Parameter parameter;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        variable = DataBuilder.aData().withName("amount").havingDataType(DoubleDataTypeBuilder.aDoubleDataType()).build();
        parameter = ParameterBuilder.aParameter().withName("commission").withType(Double.class.getName()).build();
        final ExpressionBuilder builder = ExpressionBuilder.anExpression()
        .withExpressionType(ExpressionConstants.CONDITION_TYPE)
                .withContent("amount <= 10000 + commission")
        .withReturnType(Boolean.class.getName())
                .havingReferencedElements(variable, parameter);
        conditionModelSwitch = new ExpressionConditionModelSwitch(builder.build());
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_doSwitch_a_Expression_Boolean_returns_a_constant_engine_expression_with_boolean_return_type() throws Exception {
        final Expression_Boolean expression_Boolean = ConditionModelFactory.eINSTANCE.createExpression_Boolean();
        expression_Boolean.setValue(true);
        final Expression expression = conditionModelSwitch.doSwitch(expression_Boolean);
        assertThat(expression).isNotNull();
        assertThat(expression.getExpressionType()).isEqualTo(ExpressionType.TYPE_CONSTANT.name());
        assertThat(expression.getContent()).isEqualTo("true");
        assertThat(expression.getReturnType()).isEqualTo(Boolean.class.getName());
    }

    @Test
    public void should_doSwitch_a_Expression_Double_returns_a_constant_engine_expression_with_double_return_type() throws Exception {
        final Expression_Double expression_Double = ConditionModelFactory.eINSTANCE.createExpression_Double();
        expression_Double.setValue(2000d);
        final Expression expression = conditionModelSwitch.doSwitch(expression_Double);
        assertThat(expression).isNotNull();
        assertThat(expression.getExpressionType()).isEqualTo(ExpressionType.TYPE_CONSTANT.name());
        assertThat(expression.getContent()).isEqualTo("2000.0");
        assertThat(expression.getReturnType()).isEqualTo(Double.class.getName());
    }

    @Test
    public void should_doSwitch_a_Expression_Integer_returns_a_constant_engine_expression_with_long_return_type() throws Exception {
        final Expression_Integer expression_Integer = ConditionModelFactory.eINSTANCE.createExpression_Integer();
        expression_Integer.setValue(2000);
        final Expression expression = conditionModelSwitch.doSwitch(expression_Integer);
        assertThat(expression).isNotNull();
        assertThat(expression.getExpressionType()).isEqualTo(ExpressionType.TYPE_CONSTANT.name());
        assertThat(expression.getContent()).isEqualTo("2000");
        assertThat(expression.getReturnType()).isEqualTo(Long.class.getName());
    }

    @Test
    public void should_doSwitch_a_Expression_String_returns_a_constant_engine_expression_with_String_return_type() throws Exception {
        final Expression_String expression_String = ConditionModelFactory.eINSTANCE.createExpression_String();
        expression_String.setValue("hello");
        Expression expression = conditionModelSwitch.doSwitch(expression_String);
        assertThat(expression).isNotNull();
        assertThat(expression.getExpressionType()).isEqualTo(ExpressionType.TYPE_CONSTANT.name());
        assertThat(expression.getContent()).isEqualTo("hello");
        assertThat(expression.getReturnType()).isEqualTo(String.class.getName());

        expression_String.setValue("");
        expression = conditionModelSwitch.doSwitch(expression_String);
        assertThat(expression.getContent()).isEqualTo("");

        expression_String.setValue(null);
        expression = conditionModelSwitch.doSwitch(expression_String);
        assertThat(expression.getContent()).isEqualTo("");
    }

    @Test
    public void should_doSwitch_a_Expression_ProcessRef_returns_a_variable_engine_expression() throws Exception {
        final Expression_ProcessRef expression_ProcessRef = ConditionModelFactory.eINSTANCE.createExpression_ProcessRef();
        expression_ProcessRef.setValue(EcoreUtil.copy(variable));
        final Expression expression = conditionModelSwitch.doSwitch(expression_ProcessRef);
        assertThat(expression).isNotNull();
        assertThat(expression.getExpressionType()).isEqualTo(ExpressionType.TYPE_VARIABLE.name());
        assertThat(expression.getContent()).isEqualTo("amount");
        assertThat(expression.getReturnType()).isEqualTo(Double.class.getName());

    }

    @Test
    public void should_doSwitch_a_Expression_ProcessRef_returns_a_parameter_engine_expression() throws Exception {
        final Expression_ProcessRef expression_ProcessRef = ConditionModelFactory.eINSTANCE.createExpression_ProcessRef();
        expression_ProcessRef.setValue(EcoreUtil.copy(parameter));
        final Expression expression = conditionModelSwitch.doSwitch(expression_ProcessRef);
        assertThat(expression).isNotNull();
        assertThat(expression.getExpressionType()).isEqualTo(ExpressionType.TYPE_PARAMETER.name());
        assertThat(expression.getContent()).isEqualTo("commission");
        assertThat(expression.getReturnType()).isEqualTo(Double.class.getName());

    }

}
