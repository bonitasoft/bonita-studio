/**
 * Copyright (C) 2016 BonitaSoft S.A.
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
package org.bonitasoft.studio.engine.export;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.bonitasoft.studio.model.configuration.builders.ActorMappingBuilder.anActorMapping;
import static org.bonitasoft.studio.model.configuration.builders.ActorMappingsTypeBuilder.anActorMappingsType;
import static org.bonitasoft.studio.model.configuration.builders.ConfigurationBuilder.aConfiguration;
import static org.bonitasoft.studio.model.configuration.builders.MembershipBuilder.aMembership;
import static org.bonitasoft.studio.model.parameter.builders.ParameterBuilder.aBooleanParameter;
import static org.bonitasoft.studio.model.parameter.builders.ParameterBuilder.aStringParameter;
import static org.bonitasoft.studio.model.parameter.builders.ParameterBuilder.anIntegerParameter;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Map;

import org.bonitasoft.engine.bpm.bar.actorMapping.Actor;
import org.bonitasoft.engine.bpm.bar.actorMapping.ActorMapping;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.junit.Test;

public class BarExporterTest {

    @Test
    public void should_transform_parameters_in_configuration_as_a_Map() throws Exception {
        final BarExporter barExporter = BarExporter.getInstance();

        final Map<String, String> parameters = barExporter.getParameters(aConfiguration().havingParameters(
                aStringParameter("host", "localhost"),
                anIntegerParameter("port", 8080),
                aBooleanParameter("useHTTPs", true),
                aStringParameter("parameterNotSet", null))
                .build());

        assertThat(parameters).contains(entry("host", "localhost"), entry("port", "8080"), entry("useHTTPs", "true"),
                entry("parameterNotSet", null));
    }

    @Test
    public void should_transform_actor_mappings_in_configuration_as_engine_actor_mappings() throws Exception {
        final BarExporter barExporter = BarExporter.getInstance();

        final ActorMapping actorMapping = barExporter.getActorMapping(aConfiguration()
                .havingActorMapping(anActorMappingsType()
                        .havingMapping(anActorMapping().withActor("Employee").havingUsers("romain").havingGroups("/root")
                                .havingRoles("manager", "dev").havingMemberships(aMembership("/root/rd", "dev"))))
                .build());

        final Actor employeeActor = new Actor("Employee");
        employeeActor.addUser("romain");
        employeeActor.addGroup("/root");
        employeeActor.addRoles(Arrays.asList("manager", "dev"));
        employeeActor.addMembership("/root/rd", "dev");
        assertThat(actorMapping.getActors()).containsExactly(employeeActor);
    }

    @Test
    public void should_export_empty_configuration_actor_mapping_as_empty_engine_actor_mappings() throws Exception {
        final BarExporter barExporter = BarExporter.getInstance();

        final ActorMapping actorMapping = barExporter.getActorMapping(aConfiguration().build());

        assertThat(actorMapping).isEqualTo(new org.bonitasoft.engine.bpm.bar.actorMapping.ActorMapping());
    }

    @Test
    public void should_create_empty_configuration_when_confId_is_none() throws Exception {
        BarExporter exporter = spy(BarExporter.class);
        AbstractProcess process = mock(AbstractProcess.class);
        doReturn(null).when(exporter).getProcessConfigurationRepositoryStore();
        doNothing().when(exporter).synchronizeConfiguration(any(), any());
        exporter.getConfiguration(process, ConfigurationPreferenceConstants.NONE_CONFIGURAITON);
        verify(exporter).createEmptyConfiguration(ConfigurationPreferenceConstants.NONE_CONFIGURAITON);
    }

}
