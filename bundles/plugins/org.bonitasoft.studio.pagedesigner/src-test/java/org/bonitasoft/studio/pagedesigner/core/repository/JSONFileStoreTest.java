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
package org.bonitasoft.studio.pagedesigner.core.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.File;
import java.nio.file.Paths;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.resources.IResource;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class JSONFileStoreTest {

    @Mock
    private IRepositoryStore<? extends IRepositoryFileStore> parentStore;
    private JSONFileStore jsonFileStore;
    private IResource iResource;
    private File jsonFile;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        jsonFileStore = spy(new JSONFileStore("myJson.json", parentStore));
        jsonFile = Paths.get(JSONFileStoreTest.class.getResource("/myJson.json").toURI()).toFile();
        iResource = mock(IResource.class, RETURNS_DEEP_STUBS);
        doReturn(iResource).when(jsonFileStore).getResource();
    }

    @Test
    public void should_get_content_as_a_JSONObject() throws Exception {
        when(iResource.exists()).thenReturn(true);
        when(iResource.getLocation().toFile()).thenReturn(jsonFile);

        final JSONObject content = jsonFileStore.getContent();

        assertThat(content).isNotNull();
    }

    @Test(expected = IllegalStateException.class)
    public void should_throw_IllegalStateException_if_resource_does_not_exists() throws Exception {
        when(iResource.exists()).thenReturn(false);

        jsonFileStore.getContent();
    }

    @Test
    public void should_get_name_attribute_from_JSONObject() throws Exception {
        when(iResource.exists()).thenReturn(true);
        when(iResource.getLocation().toFile()).thenReturn(jsonFile);

        final String name = jsonFileStore.getDisplayName();

        assertThat(name).isEqualTo("Step1");
    }

    @Test
    public void should_get_id_attribute_from_JSONObject() throws Exception {
        when(iResource.exists()).thenReturn(true);
        when(iResource.getLocation().toFile()).thenReturn(jsonFile);

        final String id = jsonFileStore.getId();

        assertThat(id).isEqualTo("c09d5655-9b54-4315-b37a-693a18c52a1d");
    }

}
