/**
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.configuration;

import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;
import static org.bonitasoft.studio.model.configuration.builders.ActorMappingsTypeBuilder.anActorMappingsType;
import static org.bonitasoft.studio.model.configuration.builders.ConfigurationBuilder.aConfiguration;
import static org.bonitasoft.studio.model.configuration.builders.DefinitionMappingBuilder.aDefinitionMapping;
import static org.bonitasoft.studio.model.parameter.builders.ParameterBuilder.aParameter;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;

import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.builders.ActorMappingBuilder;
import org.eclipse.core.runtime.IStatus;
import org.junit.Test;

public class ConfigurationValidatorTest {
    
    @Test
    public void should_fail_when_parameters_has_no_values() throws Exception {
        // Given
        ConfigurationValidator validator = new ConfigurationValidator(aPool()
                .withName("TestProcess")
                .withVersion("1.0")
                .build());
        Configuration configuration = aConfiguration()
                .havingParameters(aParameter()
                        .withName("host")
                        .withType(String.class.getName()))
                .build();
        
        // When
        IStatus status = validator.validate(configuration);
        
        // Then 
        assertThat(status).hasSeverity(IStatus.ERROR);
    }
    
    @Test
    public void should_not_fail_when_parameters_has_a_value() throws Exception {
        // Given
        ConfigurationValidator validator = new ConfigurationValidator(aPool()
                .withName("TestProcess")
                .withVersion("1.0")
                .build());
        Configuration configuration = aConfiguration()
                .havingParameters(aParameter()
                        .withName("host")
                        .withType(String.class.getName())
                        .withValue("localhost"))
                .build();
        
        // When
        IStatus status = validator.validate(configuration);
        
        // Then 
        assertThat(status).isOK();
    }
    
    @Test
    public void should_fail_when_parameter_value_is_not_a_valid_double() throws Exception {
        // Given
        ConfigurationValidator validator = new ConfigurationValidator(aPool()
                .withName("TestProcess")
                .withVersion("1.0")
                .build());
        Configuration configuration = aConfiguration()
                .havingParameters(aParameter()
                        .withName("amount")
                        .withType(Double.class.getName())
                        .withValue("200$"))
                .build();
        
        // When
        IStatus status = validator.validate(configuration);
        
        // Then 
        assertThat(status).hasSeverity(IStatus.ERROR);
    }
    
    @Test
    public void should_not_fail_when_parameter_value_is_valid_double() throws Exception {
        // Given
        ConfigurationValidator validator = new ConfigurationValidator(aPool()
                .withName("TestProcess")
                .withVersion("1.0")
                .build());
        Configuration configuration = aConfiguration()
                .havingParameters(aParameter()
                        .withName("amount")
                        .withType(Double.class.getName())
                        .withValue("200.00"))
                .build();
        
        // When
        IStatus status = validator.validate(configuration);
        
        // Then 
        assertThat(status).isOK();
    }
    
    @Test
    public void should_fail_when_parameter_value_is_not_a_valid_integer() throws Exception {
        // Given
        ConfigurationValidator validator = new ConfigurationValidator(aPool()
                .withName("TestProcess")
                .withVersion("1.0")
                .build());
        Configuration configuration = aConfiguration()
                .havingParameters(aParameter()
                        .withName("amount")
                        .withType(Integer.class.getName())
                        .withValue("one"))
                .build();
        
        // When
        IStatus status = validator.validate(configuration);
        
        // Then 
        assertThat(status).hasSeverity(IStatus.ERROR);
    }
    
    @Test
    public void should_not_fail_when_parameter_value_is_valid_integer() throws Exception {
        // Given
        ConfigurationValidator validator = new ConfigurationValidator(aPool()
                .withName("TestProcess")
                .withVersion("1.0")
                .build());
        Configuration configuration = aConfiguration()
                .havingParameters(aParameter()
                        .withName("amount")
                        .withType(Integer.class.getName())
                        .withValue("1"))
                .build();
        
        // When
        IStatus status = validator.validate(configuration);
        
        // Then 
        assertThat(status).isOK();
    }
    
    @Test
    public void should_fail_when_actor_is_not_mapped() throws Exception {
        // Given
        ConfigurationValidator validator = new ConfigurationValidator(aPool()
                .withName("TestProcess")
                .withVersion("1.0")
                .build());
        Configuration configuration = aConfiguration()
                .havingActorMapping(anActorMappingsType()
                        .havingMapping(ActorMappingBuilder.anActorMapping().withActor("Employee")))
                .build();
        
        // When
        IStatus status = validator.validate(configuration);
        
        // Then 
        assertThat(status).hasSeverity(IStatus.ERROR);
    }
    
    @Test
    public void should_not_fail_when_actor_is_mapped() throws Exception {
        // Given
        ConfigurationValidator validator = new ConfigurationValidator(aPool()
                .withName("TestProcess")
                .withVersion("1.0")
                .build());
        Configuration configuration = aConfiguration()
                .havingActorMapping(anActorMappingsType()
                        .havingMapping(ActorMappingBuilder.anActorMapping()
                                .withActor("Employee")
                                .havingUsers("walter.bates")))
                .build();
        
        // When
        IStatus status = validator.validate(configuration);
        
        // Then 
        assertThat(status).isOK();
    }
    
    @Test
    public void should_fail_when_connector_def_is_not_mapped() throws Exception {
        // Given
        ConfigurationValidator validator = new ConfigurationValidator(aPool()
                .withName("TestProcess")
                .withVersion("1.0")
                .build());
        Configuration configuration = aConfiguration()
                .havingDefinitionMappings(aDefinitionMapping()
                        .withDefinitionId("myConnector"))
                .build();
        
        // When
        IStatus status = validator.validate(configuration);
        
        // Then 
        assertThat(status).hasSeverity(IStatus.ERROR);
    }
    
    @Test
    public void should_not_fail_when_connector_def_is_mapped() throws Exception {
        // Given
        ConfigurationValidator validator = new ConfigurationValidator(aPool()
                .withName("TestProcess")
                .withVersion("1.0")
                .build());
        Configuration configuration = aConfiguration()
                .havingDefinitionMappings(aDefinitionMapping()
                        .withDefinitionId("myConnector")
                        .withImplementationId("myConnector-impl")
                        .withImplementationVersion("1.0.0"))
                .build();
        
        // When
        IStatus status = validator.validate(configuration);
        
        // Then 
        assertThat(status).isOK();
    }

}
