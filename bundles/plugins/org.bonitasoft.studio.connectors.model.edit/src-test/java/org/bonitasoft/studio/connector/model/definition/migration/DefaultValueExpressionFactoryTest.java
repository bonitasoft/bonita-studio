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
package org.bonitasoft.studio.connector.model.definition.migration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.expression.assertions.ExpressionAssert.assertThat;

import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.Page;
import org.bonitasoft.studio.connector.model.definition.WidgetComponent;
import org.bonitasoft.studio.model.expression.AbstractExpression;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.junit.Before;
import org.junit.Test;

public class DefaultValueExpressionFactoryTest {

    private DefaultValueExpressionFactory factory;

    @Before
    public void createFixture() throws Exception {
        factory = new DefaultValueExpressionFactory();
    }

    @Test
    public void should_create_a_constant_expression_for_string_input() throws Exception {
        //Given 
        Input input = ConnectorDefinitionFactory.eINSTANCE.createInput();
        WidgetComponent wc = ConnectorDefinitionFactory.eINSTANCE.createText();
        input.setType(String.class.getName());
        ConnectorDefinition def = createDefinition(input, wc);

        // When
        Expression expression = (Expression) factory.create(input, def);

        // Then
        Assertions.assertThat(expression.getName()).isNull();
        Assertions.assertThat(expression.getContent()).isNull();
        assertThat(expression)
                .isReturnTypeFixed()
                .hasType(ExpressionConstants.CONSTANT_TYPE)
                .hasReturnType(String.class.getName());
    }
    
    @Test
    public void should_create_a_script_expression_for_string_input() throws Exception {
        //Given 
        Input input = ConnectorDefinitionFactory.eINSTANCE.createInput();
        WidgetComponent wc = ConnectorDefinitionFactory.eINSTANCE.createScriptEditor();
        input.setType(String.class.getName());
        ConnectorDefinition def = createDefinition(input, wc);

        // When
        Expression expression = (Expression) factory.create(input, def);

        // Then
        Assertions.assertThat(expression.getName()).isNull();
        Assertions.assertThat(expression.getContent()).isNull();
        assertThat(expression)
                .isReturnTypeFixed()
                .hasType(ExpressionConstants.SCRIPT_TYPE)
                .hasInterpreter(ExpressionConstants.GROOVY)
                .hasReturnType(String.class.getName());
    }
    
    @Test
    public void should_create_a_pattern_expression_for_string_input() throws Exception {
        //Given 
        Input input = ConnectorDefinitionFactory.eINSTANCE.createInput();
        WidgetComponent wc = ConnectorDefinitionFactory.eINSTANCE.createTextArea();
        input.setType(String.class.getName());
        ConnectorDefinition def = createDefinition(input, wc);

        // When
        Expression expression = (Expression) factory.create(input, def);

        // Then
        Assertions.assertThat(expression.getName()).isNull();
        Assertions.assertThat(expression.getContent()).isNull();
        assertThat(expression)
                .isReturnTypeFixed()
                .hasType(ExpressionConstants.PATTERN_TYPE)
                .hasReturnType(String.class.getName());
    }

    @Test
    public void should_create_a_constant_expression_for_string_input_with_default_value() throws Exception {
        //Given 
        Input input = ConnectorDefinitionFactory.eINSTANCE.createInput();
        input.setType(String.class.getName());
        input.setDefaultValue("hello world");
        WidgetComponent wc = ConnectorDefinitionFactory.eINSTANCE.createText();
        ConnectorDefinition def = createDefinition(input, wc);

        // When
        Expression expression = (Expression) factory.create(input, def);

        // Then
        assertThat(expression)
                .hasName("hello world")
                .hasContent("hello world")
                .isReturnTypeFixed()
                .hasType(ExpressionConstants.CONSTANT_TYPE)
                .hasReturnType(String.class.getName());
    }

    @Test
    public void should_create_a_constant_expression_for_integer_input_with_default_value() throws Exception {
        //Given 
        Input input = ConnectorDefinitionFactory.eINSTANCE.createInput();
        input.setType(Integer.class.getName());
        input.setDefaultValue("1");
        WidgetComponent wc = ConnectorDefinitionFactory.eINSTANCE.createText();
        ConnectorDefinition def = createDefinition(input, wc);

        // When
        Expression expression = (Expression) factory.create(input, def);

        // Then
        assertThat(expression)
                .hasName("1")
                .hasContent("1")
                .isReturnTypeFixed()
                .hasType(ExpressionConstants.CONSTANT_TYPE)
                .hasReturnType(Integer.class.getName());
    }

    @Test
    public void should_create_a_list_expression_for_collection_input() throws Exception {
        //Given 
        Input input = ConnectorDefinitionFactory.eINSTANCE.createInput();
        WidgetComponent wc = ConnectorDefinitionFactory.eINSTANCE.createList();
        input.setType(List.class.getName());
        ConnectorDefinition def = createDefinition(input, wc);

        // When
        AbstractExpression expression = factory.create(input, def);

        // Then
        Assertions.assertThat(expression).isInstanceOf(ListExpression.class);
    }

    @Test
    public void should_create_a_table_expression_for_map_input() throws Exception {
        //Given 
        Input input = ConnectorDefinitionFactory.eINSTANCE.createInput();
        input.setType(Map.class.getName());
        WidgetComponent wc = ConnectorDefinitionFactory.eINSTANCE.createArray();
        ConnectorDefinition def = createDefinition(input, wc);

        // When
        AbstractExpression expression = factory.create(input, def);

        // Then
        Assertions.assertThat(expression).isInstanceOf(TableExpression.class);
    }

    private ConnectorDefinition createDefinition(Input input, WidgetComponent wc) {
        ConnectorDefinition def = ConnectorDefinitionFactory.eINSTANCE.createConnectorDefinition();
        def.getInput().add(input);
        Page page =ConnectorDefinitionFactory.eINSTANCE.createPage();
        page.getWidget().add(wc);
        def.getPage().add(page);
        return def;
    }

}
