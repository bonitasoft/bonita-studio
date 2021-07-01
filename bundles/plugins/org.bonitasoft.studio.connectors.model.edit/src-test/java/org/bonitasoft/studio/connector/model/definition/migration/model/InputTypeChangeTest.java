/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.connector.model.definition.migration.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.bonitasoft.studio.model.connectorconfiguration.builders.ConnectorConfigurationBuilder.aConnectorConfiguration;
import static org.bonitasoft.studio.model.connectorconfiguration.builders.ConnectorParameterBuilder.aConnectorParameter;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.aConstantExpression;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.aGroovyScriptExpression;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.aVariableExpression;
import static org.bonitasoft.studio.model.process.builders.DataBuilder.aData;
import static org.bonitasoft.studio.model.process.builders.StringDataTypeBuilder.aStringDataType;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.junit.Test;

public class InputTypeChangeTest {

    @Test
    public void should_be_a_breaking_change_if_input_is_defined_with_a_value() throws Exception {
        InputTypeChange change = new InputTypeChange("myInput", null, Integer.class.getName());

        assertThat(change.isBreakingChange(aConnectorConfiguration()
                .havingParameters(aConnectorParameter()
                        .withKey("myInput")
                        .havingExpression(aConstantExpression().withContent("Hello")))
                .build())).isTrue();
    }

    @Test
    public void should_not_be_a_breaking_change_if_input_is_defined_without_a_value() throws Exception {
        InputTypeChange change = new InputTypeChange("myInput", null, Integer.class.getName());

        assertThat(change.isBreakingChange(aConnectorConfiguration()
                .havingParameters(aConnectorParameter()
                        .withKey("myInput")
                        .havingExpression(aConstantExpression()))
                .build())).isFalse();
    }

    @Test
    public void should_not_be_a_breaking_change_if_input_is_not_defined() throws Exception {
        InputTypeChange change = new InputTypeChange("myInput", null, Integer.class.getName());

        assertThat(change.isBreakingChange(aConnectorConfiguration()
                .build())).isFalse();
    }

    @Test
    public void should_not_be_a_breaking_change_if_input_is_defined_without_expression() throws Exception {
        InputTypeChange change = new InputTypeChange("myInput", null, Integer.class.getName());

        assertThat(change.isBreakingChange(aConnectorConfiguration()
                .havingParameters(aConnectorParameter()
                        .withKey("myInput"))
                .build())).isFalse();
    }

    @Test
    public void should_clear_constant_expression_when_applying_change_on_existing_parameter() throws Exception {
        // Given
        InputTypeChange change = new InputTypeChange("myInput", null, Integer.class.getName());

        // When
        ConnectorConfiguration configuration = aConnectorConfiguration()
                .havingParameters(aConnectorParameter()
                        .withKey("myInput")
                        .havingExpression(aConstantExpression()
                                .withContent("Hello")
                                .withReturnType(String.class.getName())))
                .build();
        change.apply(configuration, null);

        // Then
        assertThat(configuration.getParameters())
                .filteredOn(p -> p.getKey().equals("myInput"))
                .extracting("expression.content","expression.returnType")
                .containsExactly(tuple(null, Integer.class.getName()));
    }
    
    @Test
    public void should_clear_variable_expression_when_applying_change_on_existing_parameter() throws Exception {
        // Given
        InputTypeChange change = new InputTypeChange("myInput", null, Integer.class.getName());

        // When
        ConnectorConfiguration configuration = aConnectorConfiguration()
                .havingParameters(aConnectorParameter()
                        .withKey("myInput")
                        .havingExpression(aVariableExpression()
                                .withName("myData")
                                .withContent("myData")
                                .havingReferencedElements(aData().havingDataType(aStringDataType()).withName("myData"))
                                .withReturnType(String.class.getName())))
                .build();
        change.apply(configuration, null);

        // Then
        assertThat(configuration.getParameters())
                .filteredOn(p -> p.getKey().equals("myInput"))
                .extracting("expression.content","expression.returnType","expression.type")
                .containsExactly(tuple(null, Integer.class.getName(), ExpressionConstants.CONSTANT_TYPE));
    }
    
    @Test
    public void should_not_clear_script_expression_when_applying_change_on_existing_parameter() throws Exception {
        // Given
        InputTypeChange change = new InputTypeChange("myInput", null, Integer.class.getName());

        // When
        ConnectorConfiguration configuration = aConnectorConfiguration()
                .havingParameters(aConnectorParameter()
                        .withKey("myInput")
                        .havingExpression(aGroovyScriptExpression()
                                .withName("myScript()")
                                .withContent("myData")
                                .havingReferencedElements(aData()
                                        .havingDataType(aStringDataType())
                                        .withName("myData"))
                                .withReturnType(String.class.getName())))
                .build();
        change.apply(configuration, null);

        // Then
        assertThat(configuration.getParameters())
                .filteredOn(p -> p.getKey().equals("myInput"))
                .extracting("expression.content","expression.returnType","expression.type")
                .containsExactly(tuple("myData", Integer.class.getName(), ExpressionConstants.SCRIPT_TYPE));
    }

}
