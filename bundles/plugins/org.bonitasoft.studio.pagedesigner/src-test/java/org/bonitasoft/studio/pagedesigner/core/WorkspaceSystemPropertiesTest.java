/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.pagedesigner.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.File;

import org.bonitasoft.studio.pagedesigner.core.repository.WebFragmentRepositoryStore;
import org.bonitasoft.studio.pagedesigner.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.pagedesigner.core.repository.WebWidgetRepositoryStore;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author Romain Bioteau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class WorkspaceSystemPropertiesTest {

    private WorkspaceSystemProperties workspaceSystemProperties;

    @Mock
    private WebPageRepositoryStore webPageRepository;

    @Mock
    private WebFragmentRepositoryStore webFragmentRepository;

    @Mock
    private WebWidgetRepositoryStore webWidgetRepository;

    @Mock
    private IFolder widgetResource;

    @Mock
    private IFolder formResource;

    @Mock
    private IFolder fragmentResource;

    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        workspaceSystemProperties = spy(new WorkspaceSystemProperties());

        final File widgetFolder = tmpFolder.newFolder("widgets");
        when(widgetResource.getLocation()).thenReturn(Path.fromOSString(widgetFolder.getAbsolutePath()));

        final File formsFolder = tmpFolder.newFolder("forms");
        when(formResource.getLocation()).thenReturn(Path.fromOSString(formsFolder.getAbsolutePath()));

        final File fragmentFolder = tmpFolder.newFolder("fragment");
        when(fragmentResource.getLocation()).thenReturn(Path.fromOSString(fragmentFolder.getAbsolutePath()));

        when(webWidgetRepository.getResource()).thenReturn(widgetResource);
        when(webFragmentRepository.getResource()).thenReturn(fragmentResource);
        when(webPageRepository.getResource()).thenReturn(formResource);

        doReturn(webPageRepository).when(workspaceSystemProperties).getWebPageRepository();
        doReturn(webFragmentRepository).when(workspaceSystemProperties).getWebFragmentRepository();
        doReturn(webWidgetRepository).when(workspaceSystemProperties).getWebWidgetRepository();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test(expected = IllegalStateException.class)
    public void should_getPageRepositoryLocation_throw_an_IllegalStateException_if_store_is_not_loaded() throws Exception {
        doReturn(null).when(workspaceSystemProperties).getWebPageRepository();
        workspaceSystemProperties.getPageRepositoryLocation();
    }

    @Test(expected = IllegalStateException.class)
    public void should_getFragmentRepositoryLocation_throw_an_IllegalStateException_if_store_is_not_loaded() throws Exception {
        doReturn(null).when(workspaceSystemProperties).getWebFragmentRepository();
        workspaceSystemProperties.getFragmentRepositoryLocation();
    }

    @Test(expected = IllegalStateException.class)
    public void should_getWidgetRepositoryLocation_throw_an_IllegalStateException_if_store_is_not_loaded() throws Exception {
        doReturn(null).when(workspaceSystemProperties).getWebWidgetRepository();
        workspaceSystemProperties.getWidgetRepositoryLocation();
    }

    @Test
    public void should_getPageRepositoryLocation_return_a_valid_system_properties_for_page_repository() throws Exception {
        assertThat(workspaceSystemProperties.getPageRepositoryLocation()).isEqualTo(
                "-Drepository.pages=\"" + formResource.getLocation().toFile().getAbsolutePath() + "\"");
    }

    @Test
    public void should_getFragmentRepositoryLocation_return_a_valid_system_properties_for_fragment_repository() throws Exception {
        assertThat(workspaceSystemProperties.getFragmentRepositoryLocation()).isEqualTo(
                "-Drepository.fragments=\"" + fragmentResource.getLocation().toFile().getAbsolutePath() + "\"");
    }

    @Test
    public void should_getWidgetRepositoryLocation_return_a_valid_system_properties_for_fragment_repository() throws Exception {
        assertThat(workspaceSystemProperties.getWidgetRepositoryLocation()).isEqualTo(
                "-Drepository.widgets=\"" + widgetResource.getLocation().toFile().getAbsolutePath() + "\"");
    }

    @Test
    public void should_aSystemProperty_return_a_well_formatted_system_property_argument() throws Exception {
        assertThat(WorkspaceSystemProperties.aSystemProperty("aProperty.name", "myValue")).isEqualTo("-DaProperty.name=myValue");
        final File folder = tmpFolder.newFolder();
        assertThat(WorkspaceSystemProperties.aSystemProperty("aProperty.name", folder)).isEqualTo("-DaProperty.name=\"" + folder.getAbsolutePath() + "\"");
    }

    @Test
    public void should_getRestAPIURL_return_the_rest_resource_server_url() throws Exception {
        assertThat(workspaceSystemProperties.getRestAPIURL(6666)).isEqualTo(
                "-Dworkspace.api.rest.url=http://localhost:6666/api/workspace");
    }
}
