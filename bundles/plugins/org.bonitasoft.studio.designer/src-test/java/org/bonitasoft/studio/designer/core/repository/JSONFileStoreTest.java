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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Supplier;

import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.fakes.IResourceFakesBuilder;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IFile;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

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
        // iResource = mock(IFile.class, RETURNS_DEEP_STUBS);
        //  doReturn(iResource).when(jsonFileStore).getResource();
        when(parentStore.validate(Mockito.anyString(), Mockito.any(InputStream.class)))
                .thenReturn(ValidationStatus.ok());
    }

    @Test
    public void should_get_content_as_a_JSONObject() throws Exception {
        iResource = IResourceFakesBuilder.anIFile()
                .exists()
                .withContentSupplier(inputStreamSupplier(jsonFile))
                .build();
        doReturn(iResource).when(jsonFileStore).getResource();

        final Map<String, Object> content = jsonFileStore.getContent();

        assertThat(content).isNotNull();
    }

    private Supplier<InputStream> inputStreamSupplier(File file) {
        return () -> {
            try {
                return new FileInputStream(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Test(expected = IllegalStateException.class)
    public void should_throw_IllegalStateException_if_resource_does_not_exists() throws Exception {
        iResource = IResourceFakesBuilder.anIFile()
                .build();
        doReturn(iResource).when(jsonFileStore).getResource();

        jsonFileStore.getContent();
    }

    @Test(expected = ReadFileStoreException.class)
    public void should_throw_ReadFileStoreException_if_content_cannot_be_parsed() throws Exception {
        iResource = IResourceFakesBuilder.anIFile()
                .exists()
                .withContentSupplier(inputStreamSupplier(invalidJsonFile))
                .build();
        doReturn(iResource).when(jsonFileStore).getResource();

        jsonFileStore.getContent();
    }

    @Test
    public void should_get_string_attribute_from_JSONObject() throws Exception {
        iResource = IResourceFakesBuilder.anIFile()
                .exists()
                .withContentSupplier(inputStreamSupplier(jsonFile))
                .build();
        doReturn(iResource).when(jsonFileStore).getResource();

        final String name = jsonFileStore.getStringAttribute("name");

        assertThat(name).isEqualTo("Step1");
    }

}
