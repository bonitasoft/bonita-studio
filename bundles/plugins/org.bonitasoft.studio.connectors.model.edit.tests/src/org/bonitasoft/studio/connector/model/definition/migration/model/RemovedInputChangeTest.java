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

import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.junit.Test;

public class RemovedInputChangeTest {

    @Test
    public void should_not_be_a_breaking_change() throws Exception {
        Input input = ConnectorDefinitionFactory.eINSTANCE.createInput();
        RemovedInputChange change = new RemovedInputChange(input);
        
        assertThat(change.isBreakingChange(aConnectorConfiguration().build())).isFalse();
    }
    
    @Test
    public void should_remove_input_from_configuration_when_applying_change() throws Exception {
        // Given
        Input input = ConnectorDefinitionFactory.eINSTANCE.createInput();
        input.setName("myInput");
        input.setType(String.class.getName());
        RemovedInputChange change = new RemovedInputChange(input);
        ConnectorConfiguration configruation = aConnectorConfiguration()
                .havingParameters(aConnectorParameter()
                        .withKey("myInput"))
                .build();
     
        // When
        change.apply(configruation, null);
        
        // Then
        assertThat(configruation.getParameters()).isEmpty();
    }

}
