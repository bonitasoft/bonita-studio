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
import static org.junit.Assert.assertThrows;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.junit.Test;

public class InputWidgetChangeTest {

    @Test
    public void should_not_be_a_breaking_change_if_input_is_not_defined() throws Exception {
        InputWidgetChange inputWidgetChange = new InputWidgetChange("myInput");
        inputWidgetChange.setNewWidgetType(ConnectorDefinitionFactory.eINSTANCE.createText());

        assertThat(inputWidgetChange.isBreakingChange(aConnectorConfiguration().build())).isFalse();
    }

    @Test
    public void should_not_be_a_breaking_change_if_input_expression_is_not_defined() throws Exception {
        InputWidgetChange inputWidgetChange = new InputWidgetChange("myInput");
        inputWidgetChange.setNewWidgetType(ConnectorDefinitionFactory.eINSTANCE.createText());

        assertThat(inputWidgetChange.isBreakingChange(aConnectorConfiguration()
                .havingParameters(aConnectorParameter()
                        .withKey("myInput"))
                .build())).isFalse();
    }

    @Test
    public void should_not_be_a_breaking_change_if_input_expression_has_no_value() throws Exception {
        InputWidgetChange inputWidgetChange = new InputWidgetChange("myInput");
        inputWidgetChange.setNewWidgetType(ConnectorDefinitionFactory.eINSTANCE.createText());

        assertThat(inputWidgetChange.isBreakingChange(aConnectorConfiguration()
                .havingParameters(aConnectorParameter()
                        .withKey("myInput")
                        .havingExpression(aConstantExpression()))
                .build())).isFalse();
    }

    @Test
    public void should_be_a_breaking_change_if_input_expression_is_TableExpression_and_new_widget_is_not_an_Array()
            throws Exception {
        InputWidgetChange inputWidgetChange = new InputWidgetChange("myInput");
        inputWidgetChange.setNewWidgetType(ConnectorDefinitionFactory.eINSTANCE.createText());

        assertThat(inputWidgetChange.isBreakingChange(aConnectorConfiguration()
                .havingParameters(aConnectorParameter()
                        .withKey("myInput")
                        .havingExpression(ExpressionFactory.eINSTANCE.createTableExpression()))
                .build())).isTrue();
    }

    @Test
    public void should_be_a_breaking_change_if_input_expression_is_ListExpression_and_new_widget_is_not_an_List()
            throws Exception {
        InputWidgetChange inputWidgetChange = new InputWidgetChange("myInput");
        inputWidgetChange.setNewWidgetType(ConnectorDefinitionFactory.eINSTANCE.createText());

        assertThat(inputWidgetChange.isBreakingChange(aConnectorConfiguration()
                .havingParameters(aConnectorParameter()
                        .withKey("myInput")
                        .havingExpression(ExpressionFactory.eINSTANCE.createListExpression()))
                .build())).isTrue();
    }

    @Test
    public void should_not_be_a_breaking_change_if_input_expression_is_TableExpression_and_new_widget_is_an_Array()
            throws Exception {
        InputWidgetChange inputWidgetChange = new InputWidgetChange("myInput");
        inputWidgetChange.setNewWidgetType(ConnectorDefinitionFactory.eINSTANCE.createArray());

        assertThat(inputWidgetChange.isBreakingChange(aConnectorConfiguration()
                .havingParameters(aConnectorParameter()
                        .withKey("myInput")
                        .havingExpression(ExpressionFactory.eINSTANCE.createTableExpression()))
                .build())).isFalse();
    }

    @Test
    public void should_not_be_a_breaking_change_if_input_expression_is_ListExpression_and_new_widget_is_a_List()
            throws Exception {
        InputWidgetChange inputWidgetChange = new InputWidgetChange("myInput");
        inputWidgetChange.setNewWidgetType(ConnectorDefinitionFactory.eINSTANCE.createList());

        assertThat(inputWidgetChange.isBreakingChange(aConnectorConfiguration()
                .havingParameters(aConnectorParameter()
                        .withKey("myInput")
                        .havingExpression(ExpressionFactory.eINSTANCE.createListExpression()))
                .build())).isFalse();
    }

    @Test
    public void should_not_be_a_breaking_change_if_input_expression_is_Expression_and_new_widget_is_a_Text()
            throws Exception {
        InputWidgetChange inputWidgetChange = new InputWidgetChange("myInput");
        inputWidgetChange.setNewWidgetType(ConnectorDefinitionFactory.eINSTANCE.createText());

        assertThat(inputWidgetChange.isBreakingChange(aConnectorConfiguration()
                .havingParameters(aConnectorParameter()
                        .withKey("myInput")
                        .havingExpression(aConstantExpression().withContent("Hello")))
                .build())).isFalse();
    }

    @Test
    public void should_clear_table_expression_when_applying_change()
            throws Exception {
        // Given
        InputWidgetChange inputWidgetChange = new InputWidgetChange("myInput");
        inputWidgetChange.setNewWidgetType(ConnectorDefinitionFactory.eINSTANCE.createText());
        ConnectorConfiguration configuration = aConnectorConfiguration()
                .havingParameters(aConnectorParameter()
                        .withKey("myInput")
                        .havingExpression(ExpressionFactory.eINSTANCE.createTableExpression()))
                .build();
        ConnectorDefinition connectorDefinition = ConnectorDefinitionFactory.eINSTANCE.createConnectorDefinition();
        Input myInput = ConnectorDefinitionFactory.eINSTANCE.createInput();
        myInput.setName("myInput");
        myInput.setType(String.class.getName());
        connectorDefinition.getInput().add(myInput);

        // When
        inputWidgetChange.apply(configuration, connectorDefinition);

        // Then
        assertThat(configuration.getParameters())
                .filteredOn(p -> p.getKey().equals("myInput"))
                .extracting("expression.content","expression.returnType","expression.type")
                .containsOnly(tuple(null, String.class.getName(), ExpressionConstants.CONSTANT_TYPE));
    }
    
    @Test
    public void should_clear_list_expression_when_applying_change()
            throws Exception {
        // Given
        InputWidgetChange inputWidgetChange = new InputWidgetChange("myInput");
        inputWidgetChange.setNewWidgetType(ConnectorDefinitionFactory.eINSTANCE.createText());
        ConnectorConfiguration configuration = aConnectorConfiguration()
                .havingParameters(aConnectorParameter()
                        .withKey("myInput")
                        .havingExpression(ExpressionFactory.eINSTANCE.createListExpression()))
                .build();
        ConnectorDefinition connectorDefinition = ConnectorDefinitionFactory.eINSTANCE.createConnectorDefinition();
        Input myInput = ConnectorDefinitionFactory.eINSTANCE.createInput();
        myInput.setName("myInput");
        myInput.setType(String.class.getName());
        connectorDefinition.getInput().add(myInput);

        // When
        inputWidgetChange.apply(configuration, connectorDefinition);

        // Then
        assertThat(configuration.getParameters())
                .filteredOn(p -> p.getKey().equals("myInput"))
                .extracting("expression.content","expression.returnType","expression.type")
                .containsOnly(tuple(null, String.class.getName(), ExpressionConstants.CONSTANT_TYPE));
    }
    
    @Test
    public void should_not_clear_expression_when_applying_change()
            throws Exception {
        // Given
        InputWidgetChange inputWidgetChange = new InputWidgetChange("myInput");
        inputWidgetChange.setNewWidgetType(ConnectorDefinitionFactory.eINSTANCE.createText());
        ConnectorConfiguration configuration = aConnectorConfiguration()
                .havingParameters(aConnectorParameter()
                        .withKey("myInput")
                        .havingExpression(aConstantExpression()
                                .withContent("Hello")))
                .build();
        ConnectorDefinition connectorDefinition = ConnectorDefinitionFactory.eINSTANCE.createConnectorDefinition();
        Input myInput = ConnectorDefinitionFactory.eINSTANCE.createInput();
        myInput.setName("myInput");
        myInput.setType(String.class.getName());
        connectorDefinition.getInput().add(myInput);

        // When
        inputWidgetChange.apply(configuration, connectorDefinition);

        // Then
        assertThat(configuration.getParameters())
                .filteredOn(p -> p.getKey().equals("myInput"))
                .extracting("expression.content","expression.returnType","expression.type")
                .containsOnly(tuple("Hello", String.class.getName(), ExpressionConstants.CONSTANT_TYPE));
    }
    
    @Test
    public void should_throw_illegal_argument_exception_when_input_not_found_in_definition()
            throws Exception {
        // Given
        InputWidgetChange inputWidgetChange = new InputWidgetChange("myInput");
        inputWidgetChange.setNewWidgetType(ConnectorDefinitionFactory.eINSTANCE.createText());
        ConnectorConfiguration configuration = aConnectorConfiguration()
                .havingParameters(aConnectorParameter()
                        .withKey("myInput")
                        .havingExpression(ExpressionFactory.eINSTANCE.createListExpression()))
                .build();
        ConnectorDefinition connectorDefinition = ConnectorDefinitionFactory.eINSTANCE.createConnectorDefinition();

        // Expected
        assertThrows(IllegalArgumentException.class, () ->  inputWidgetChange.apply(configuration, connectorDefinition));
    }

}
