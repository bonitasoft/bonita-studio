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
import java.io.FileInputStream;
import java.nio.file.Paths;

import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.FileLocator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NamedJSONFileStoreTest {

    @Mock
    private IRepositoryStore parentStore;
    private NamedJSONFileStore jsonFileStore;
    private IFile iResource;
    private File jsonFile;
    private File jsonFileWithoutName;
    private File jsonFileWithoutId;

    @Before
    public void setUp() throws Exception {
        jsonFileStore = spy(new NamedJSONFileStore("myJson.json", parentStore));
        jsonFile = Paths.get(FileLocator.toFileURL(JSONFileStoreTest.class.getResource("/myJson.json")).toURI())
                .toFile();
        jsonFileWithoutName = Paths
                .get(FileLocator.toFileURL(JSONFileStoreTest.class.getResource("/noNameJson.json")).toURI()).toFile();
        jsonFileWithoutId = Paths
                .get(FileLocator.toFileURL(JSONFileStoreTest.class.getResource("/noIdJson.json")).toURI()).toFile();
        iResource = mock(IFile.class, RETURNS_DEEP_STUBS);
        doReturn(iResource).when(jsonFileStore).getResource();
    }

    @Test
    public void should_throw_IllegalAccessError_if_name_attribute_not_found() throws Exception {
        when(iResource.exists()).thenReturn(true);
        when(iResource.getLocation().toFile()).thenReturn(jsonFileWithoutName);
        when(iResource.getContents()).thenReturn(new FileInputStream(jsonFileWithoutName));

        IDisplayable displayable = Adapters.adapt(jsonFileStore, IDisplayable.class);
        assertThat(displayable.getDisplayName()).isNull();
    }

    @Test
    public void should_reuturn_null_if_id_attribute_not_found() throws Exception {
        when(iResource.exists()).thenReturn(true);
        when(iResource.getLocation().toFile()).thenReturn(jsonFileWithoutId);
        when(iResource.getContents()).thenReturn(new FileInputStream(jsonFileWithoutId));

        assertThat(jsonFileStore.getId()).isNull();
    }

    @Test
    public void should_get_name_attribute() throws Exception {
        when(iResource.exists()).thenReturn(true);
        when(iResource.getLocation().toFile()).thenReturn(jsonFile);
        when(iResource.getContents()).thenReturn(new FileInputStream(jsonFile));

        IDisplayable displayable = Adapters.adapt(jsonFileStore, IDisplayable.class);
        final String name = displayable.getDisplayName();

        assertThat(name).isEqualTo("Step1");
    }

    @Test
    public void should_get_id_attribute() throws Exception {
        when(iResource.exists()).thenReturn(true);
        when(iResource.getLocation().toFile()).thenReturn(jsonFile);
        when(iResource.getContents()).thenReturn(new FileInputStream(jsonFile));

        final String id = jsonFileStore.getId();

        assertThat(id).isEqualTo("c09d5655-9b54-4315-b37a-693a18c52a1d");
    }

}
