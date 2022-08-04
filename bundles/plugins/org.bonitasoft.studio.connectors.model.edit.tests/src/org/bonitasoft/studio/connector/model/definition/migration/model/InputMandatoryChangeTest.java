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
import static org.bonitasoft.studio.model.connectorconfiguration.builders.ConnectorConfigurationBuilder.aConnectorConfiguration;
import static org.bonitasoft.studio.model.connectorconfiguration.builders.ConnectorParameterBuilder.aConnectorParameter;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.aConstantExpression;

import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.junit.Test;

public class InputMandatoryChangeTest {
    
    @Test
    public void should_be_a_breaking_change_when_input_not_defined() throws Exception {
        InputMandatoryChange change = new InputMandatoryChange("myInput");
       
        assertThat(change.isBreakingChange(aConnectorConfiguration().build())).isTrue();
    }

    @Test
    public void should_be_a_breaking_change_when_input_has_no_expression_defined() throws Exception {
        InputMandatoryChange change = new InputMandatoryChange("myInput");
       
        assertThat(change.isBreakingChange(aConnectorConfiguration()
                .havingParameters(aConnectorParameter().withKey("myInput"))
                .build())).isTrue();
    }
    
    @Test
    public void should_be_a_breaking_change_when_input_no_value_defined() throws Exception {
        InputMandatoryChange change = new InputMandatoryChange("myInput");
       
        assertThat(change.isBreakingChange(aConnectorConfiguration()
                .havingParameters(aConnectorParameter()
                        .withKey("myInput")
                        .havingExpression(aConstantExpression()))
                .build())).isTrue();
    }
    
    @Test
    public void should_not_be_a_breaking_change_when_input_has_value_defined() throws Exception {
        InputMandatoryChange change = new InputMandatoryChange("myInput");
       
        assertThat(change.isBreakingChange(aConnectorConfiguration()
                .havingParameters(aConnectorParameter()
                        .withKey("myInput")
                        .havingExpression(aConstantExpression().withContent("Hello")))
                .build())).isFalse();
    }
    
    @Test
    public void should_not_be_a_breaking_change_when_input_is_a_ListExpression() throws Exception {
        InputMandatoryChange change = new InputMandatoryChange("myInput");
       
        assertThat(change.isBreakingChange(aConnectorConfiguration()
                .havingParameters(aConnectorParameter()
                        .withKey("myInput")
                        .havingExpression(ExpressionFactory.eINSTANCE.createListExpression()))
                .build())).isFalse();
    }
    
    @Test
    public void should_not_be_a_breaking_change_when_input_is_a_TableExpression() throws Exception {
        InputMandatoryChange change = new InputMandatoryChange("myInput");
       
        assertThat(change.isBreakingChange(aConnectorConfiguration()
                .havingParameters(aConnectorParameter()
                        .withKey("myInput")
                        .havingExpression(ExpressionFactory.eINSTANCE.createTableExpression()))
                .build())).isFalse();
    }

}
