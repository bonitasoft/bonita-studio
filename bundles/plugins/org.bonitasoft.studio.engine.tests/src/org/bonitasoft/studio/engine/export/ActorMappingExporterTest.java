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
package org.bonitasoft.studio.engine.export;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.nio.charset.Charset;

import org.bonitasoft.studio.model.actormapping.ActorMapping;
import org.bonitasoft.studio.model.actormapping.ActorMappingFactory;
import org.bonitasoft.studio.model.actormapping.ActorMappingsType;
import org.bonitasoft.studio.model.actormapping.Users;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ActorMappingExporterTest {

    @Spy
    private ActorMappingExporter actorMappingExporter;
    private Configuration configuration;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
        final ActorMappingsType mappingType = ActorMappingFactory.eINSTANCE.createActorMappingsType();
        final ActorMapping actorMapping = ActorMappingFactory.eINSTANCE.createActorMapping();
        actorMapping.setName("Employee actor");
        final Users users = ActorMappingFactory.eINSTANCE.createUsers();
        users.getUser().add("平仮名");
        actorMapping.setUsers(users);
        actorMapping.setGroups(ActorMappingFactory.eINSTANCE.createGroups());
        mappingType.getActorMapping().add(actorMapping);
        configuration.setActorMappings(mappingType);
        doReturn(folder.newFolder()).when(actorMappingExporter).getTempFolder();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        folder.delete();
    }

    @Test
    public void should_to_byteArray_returns_an_UTF8_byteArray() throws Exception {
        final byte[] content = actorMappingExporter.toByteArray(configuration);
        assertThat(content).isNotEmpty();
        assertThat(new String(content, Charset.forName("UTF-8"))).contains("平仮名");
    }

}
