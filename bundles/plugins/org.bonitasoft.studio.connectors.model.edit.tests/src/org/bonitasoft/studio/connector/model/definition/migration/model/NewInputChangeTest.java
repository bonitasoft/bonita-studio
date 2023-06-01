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

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.migration.DefaultValueExpressionFactory;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.junit.Test;

public class NewInputChangeTest {

    private DefaultValueExpressionFactory defaultValueExpressionFactory = new DefaultValueExpressionFactory();

    @Test
    public void should_be_a_breaking_change_if_mandatory_without_a_default_value() throws Exception {
        Input mandatoryInput = ConnectorDefinitionFactory.eINSTANCE.createInput();
        mandatoryInput.setMandatory(true);
        NewInputChange change = new NewInputChange(mandatoryInput, defaultValueExpressionFactory);

        assertThat(change.isBreakingChange(aConnectorConfiguration().build())).isTrue();
    }

    @Test
    public void should_be_a_breaking_change_if_mandatory_without_an_empty_default_value() throws Exception {
        Input mandatoryInput = ConnectorDefinitionFactory.eINSTANCE.createInput();
        mandatoryInput.setMandatory(true);
        mandatoryInput.setDefaultValue("");
        NewInputChange change = new NewInputChange(mandatoryInput, defaultValueExpressionFactory);

        assertThat(change.isBreakingChange(aConnectorConfiguration().build())).isTrue();
    }

    @Test
    public void should_not_be_a_breaking_change_if_mandatory_with_a_default_value() throws Exception {
        Input mandatoryInput = ConnectorDefinitionFactory.eINSTANCE.createInput();
        mandatoryInput.setMandatory(true);
        mandatoryInput.setDefaultValue("Hello");
        NewInputChange change = new NewInputChange(mandatoryInput, defaultValueExpressionFactory);

        assertThat(change.isBreakingChange(aConnectorConfiguration().build())).isFalse();
    }

    @Test
    public void should_not_be_a_breaking_change_if_not_mandatory() throws Exception {
        Input input = ConnectorDefinitionFactory.eINSTANCE.createInput();
        NewInputChange change = new NewInputChange(input, defaultValueExpressionFactory);

        assertThat(change.isBreakingChange(aConnectorConfiguration().build())).isFalse();
    }

    @Test
    public void should_add_a_new_parameter_to_configuration_when_applying_change() throws Exception {
        // Given
        Input input = ConnectorDefinitionFactory.eINSTANCE.createInput();
        input.setName("myInput");
        input.setType(String.class.getName());
        NewInputChange change = new NewInputChange(input, defaultValueExpressionFactory);
        ConnectorConfiguration configuration = aConnectorConfiguration().build();

        // When
        change.apply(configuration, ConnectorDefinitionFactory.eINSTANCE.createConnectorDefinition());

        // Then
        assertThat(configuration.getParameters())
                .filteredOn(p -> p.getKey().equals("myInput"))
                .extracting("expression.returnType","expression.type")
                .containsExactly(tuple(String.class.getName(), ExpressionConstants.CONSTANT_TYPE));
    }

}
