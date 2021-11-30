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

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.designer.core.repository.WebWidgetFileStore;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.json.JSONObject;
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
public class WebWidgetFileStoreTest {

    @Mock
    private IRepositoryStore parentStore;

    private WebWidgetFileStore webWidgetFileStore;
    @Mock
    private IFolder folderResource;
    @Mock
    private IFile jsonResource;

    private File jsonWidgetFile;

    private File invalidJsonWidgetFile;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        webWidgetFileStore = spy(new WebWidgetFileStore("pbButton", parentStore));
        jsonWidgetFile = Paths.get(WebWidgetFileStoreTest.class.getResource("/pbButton.json").toURI()).toFile();
        invalidJsonWidgetFile = Paths.get(WebWidgetFileStoreTest.class.getResource("/invalidJson.json").toURI()).toFile();
        folderResource = mock(IFolder.class);
        jsonResource = mock(IFile.class, RETURNS_DEEP_STUBS);
        doReturn(folderResource).when(webWidgetFileStore).getResource();
        doReturn(jsonResource).when(folderResource).getFile("pbButton.json");
        when(parentStore.validate(Mockito.any(), Mockito.any()))
        .thenReturn(ValidationStatus.ok());
    }

    @Test
    public void should_get_content_of_child_json_file() throws Exception {
        doReturn(true).when(folderResource).exists();
        doReturn(true).when(jsonResource).exists();
        when(jsonResource.getLocation().toFile()).thenReturn(jsonWidgetFile);

        final JSONObject content = webWidgetFileStore.getContent();

        assertThat(content).isNotNull();
    }

    @Test(expected = ReadFileStoreException.class)
    public void should_throw_a_ReadFileStoreException_if_json_file_is_invalid() throws Exception {
        doReturn(true).when(folderResource).exists();
        doReturn(true).when(jsonResource).exists();
        when(jsonResource.getLocation().toFile()).thenReturn(invalidJsonWidgetFile);

        webWidgetFileStore.getContent();
    }

    @Test
    public void should_be_exportable_only_if_a_custom_widget() throws Exception {
        doReturn(true).when(webWidgetFileStore).getBooleanAttribute("custom");

        final boolean canBeExported = webWidgetFileStore.canBeExported();

        assertThat(canBeExported).isTrue();
    }

    @Test
    public void should_not_be_exportable_if_a_not_custom_widget() throws Exception {
        doReturn(false).when(webWidgetFileStore).getBooleanAttribute("custom");

        final boolean canBeExported = webWidgetFileStore.canBeExported();

        assertThat(canBeExported).isFalse();
    }
}
