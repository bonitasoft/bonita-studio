/**
 * Copyright (C) 2019 BonitaSoft S.A.
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
package org.bonitasoft.studio.maven;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionDescriptor;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionFileStore;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionRepositoryStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CustomPageProjectFileStoreTest {

    @Mock
    private RestAPIExtensionDescriptor raed;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_open_Index_groovy_file() throws Exception {
        final RestAPIExtensionRepositoryStore parentStore = mock(RestAPIExtensionRepositoryStore.class, Mockito.RETURNS_DEEP_STUBS);
        final IProject restApiProject = mock(IProject.class);
        when(restApiProject.getName()).thenReturn("my-rest-api");
        final IFile indexGroovy = mock(IFile.class);
        when(restApiProject.getFile(Path.fromOSString("src/main/groovy/Index.groovy"))).thenReturn(indexGroovy);
        final IFile propertyFile = mock(IFile.class);
        when(restApiProject.getFile("src/main/resources/page.properties")).thenReturn(propertyFile);
        when(parentStore.getResource().getWorkspace().getRoot().getProject(restApiProject.getName())).thenReturn(restApiProject);

        final RestAPIExtensionFileStore fileStore = spy(new RestAPIExtensionFileStore(restApiProject.getName(), parentStore));
        final RestAPIExtensionDescriptor descriptor = spy(new RestAPIExtensionDescriptor(restApiProject));
        final Properties pageProperties = new Properties();
        pageProperties.put("apiExtensions", "myApi");
        pageProperties.put("myApi.classFileName", "Index.groovy");
        doReturn(pageProperties).when(descriptor).getPageProperties();
        doReturn(descriptor).when(fileStore).getContent();
        doReturn(null).when(fileStore).openEditors(any(IWorkbenchPage.class), any(CustomPageMavenProjectDescriptor.class));
        final IWorkbenchPage page = mock(IWorkbenchPage.class);
        doReturn(page).when(fileStore).getActivePage();

        fileStore.doOpen();

        final ArgumentCaptor<RestAPIExtensionDescriptor> argumentCaptor = ArgumentCaptor.forClass(RestAPIExtensionDescriptor.class);
        verify(fileStore).openEditors(eq(page), argumentCaptor.capture());
        final RestAPIExtensionDescriptor restAPIExtensionDescriptor = argumentCaptor.getValue();
        assertThat(restAPIExtensionDescriptor.getFilesToOpen()).contains(indexGroovy);
        assertThat(restAPIExtensionDescriptor.getPropertyFile()).isEqualTo(propertyFile);
    }

    @Test
    public void should_return_null_if_open_throw_a_PartInitException() throws Exception {
        final RestAPIExtensionRepositoryStore parentStore = mock(RestAPIExtensionRepositoryStore.class, Mockito.RETURNS_DEEP_STUBS);

        final RestAPIExtensionFileStore fileStore = spy(new RestAPIExtensionFileStore("my-rest-api", parentStore));
        final IWorkbenchPage page = mock(IWorkbenchPage.class);
        doReturn(page).when(fileStore).getActivePage();

        final IWorkbenchPart part = fileStore.doOpen();

        assertThat(part).isNull();
    }

    @Test
    public void shouldFilterResourcesToExport() throws Exception {
        final RestAPIExtensionRepositoryStore parentStore = mock(RestAPIExtensionRepositoryStore.class, Mockito.RETURNS_DEEP_STUBS);
        final IProject restApiProject = mock(IProject.class);
        doReturn(restApiProject).when(raed).getProject();
        final List<IResource> members = new ArrayList<IResource>();
        final IResource resourceOK = mock(IResource.class);
        final IPath pathOK = mock(IPath.class);
        doReturn(pathOK).when(resourceOK).getProjectRelativePath();
        doReturn(new String[] { "src", "main", "groovy", "Index.groovy" }).when(pathOK).segments();
        members.add(resourceOK);

        final IResource resourceBin = mock(IResource.class);
        final IPath pathBin = mock(IPath.class);
        doReturn(pathBin).when(resourceBin).getProjectRelativePath();
        doReturn(new String[] { "bin", "main", "groovy", "Index.groovy" }).when(pathBin).segments();
        members.add(resourceBin);

        final IResource resourceTarget = mock(IResource.class);
        final IPath pathTarget = mock(IPath.class);
        doReturn(pathTarget).when(resourceTarget).getProjectRelativePath();
        doReturn(new String[] { "target", "main", "groovy", "Index.groovy" }).when(pathTarget).segments();
        members.add(resourceTarget);

        doReturn(members.toArray(new IResource[3])).when(restApiProject).members();
        final RestAPIExtensionFileStore fileStore = spy(new RestAPIExtensionFileStore("my-rest-api", parentStore));

        final List<IResource> resourcesToExport = fileStore.findResourcesToExport(raed);

        assertThat(resourcesToExport).containsOnly(resourceOK);
    }
    
}
