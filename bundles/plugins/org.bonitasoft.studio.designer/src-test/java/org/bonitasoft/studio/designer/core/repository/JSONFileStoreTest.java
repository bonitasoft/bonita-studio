/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.designer.core.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Paths;

import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IFile;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class JSONFileStoreTest {

    @Mock
    private IRepositoryStore parentStore;
    private JSONFileStore jsonFileStore;
    private IFile iResource;
    private File jsonFile;
    private File invalidJsonFile;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        jsonFileStore = spy(new JSONFileStore("myJson.json", parentStore));
        jsonFile = Paths.get(JSONFileStoreTest.class.getResource("/myJson.json").toURI()).toFile();
        invalidJsonFile = Paths.get(JSONFileStoreTest.class.getResource("/invalidJson.json").toURI()).toFile();
        iResource = mock(IFile.class, RETURNS_DEEP_STUBS);
        doReturn(iResource).when(jsonFileStore).getResource();
        when(parentStore.validate(Mockito.anyString(), Mockito.any(InputStream.class)))
                .thenReturn(ValidationStatus.ok());
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

    @Test(expected = ReadFileStoreException.class)
    public void should_throw_ReadFileStoreException_if_content_cannot_be_parsed() throws Exception {
        when(iResource.exists()).thenReturn(true);
        when(iResource.getLocation().toFile()).thenReturn(invalidJsonFile);

        jsonFileStore.getContent();
    }

    @Test
    public void should_get_string_attribute_from_JSONObject() throws Exception {
        when(iResource.exists()).thenReturn(true);
        when(iResource.getLocation().toFile()).thenReturn(jsonFile);

        final String name = jsonFileStore.getStringAttribute("name");

        assertThat(name).isEqualTo("Step1");
    }

}
