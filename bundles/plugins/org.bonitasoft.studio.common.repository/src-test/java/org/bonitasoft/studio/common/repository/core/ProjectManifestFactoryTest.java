/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.repository.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.bonitasoft.studio.fakes.IResourceFakesBuilder.anIFile;
import static org.bonitasoft.studio.fakes.IResourceFakesBuilder.anIFolder;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProjectManifestFactoryTest {

    @Mock
    private IProgressMonitor monitor;

    @Mock
    private IProject project;

    @Test
    public void should_create_a_new_META_INF_folder_and_new_MANIFEST_if_not_already_present() throws Exception {
        final ProjectManifestFactory projectManifestFactory = spy(new ProjectManifestFactory());

        final IFile manifestFile = (IFile) anIFile().build();
        final IFolder metaInfFolder = (IFolder) anIFolder().build();
        doNothing().when(projectManifestFactory).writeNewManifestFile(project, monitor, manifestFile);
        when(metaInfFolder.getFile("MANIFEST.MF")).thenReturn(manifestFile);
        when(project.getFolder("META-INF")).thenReturn(metaInfFolder);
        projectManifestFactory.createProjectManifest(project, monitor);

        verify(metaInfFolder).create(true, true, monitor);
        verify(projectManifestFactory).writeNewManifestFile(project, monitor, manifestFile);
    }

    @Test
    public void should_not_create_a_new_META_INF_folder_and_new_MANIFEST_if_already_present() throws Exception {
        final ProjectManifestFactory projectManifestFactory = spy(new ProjectManifestFactory());

        final IFile manifestFile = (IFile) anIFile().exists().build();
        final IFolder metaInfFolder = (IFolder) anIFolder().exists().build();
        doNothing().when(projectManifestFactory).writeNewManifestFile(project, monitor, manifestFile);
        when(metaInfFolder.getFile("MANIFEST.MF")).thenReturn(manifestFile);
        when(project.getFolder("META-INF")).thenReturn(metaInfFolder);
        projectManifestFactory.createProjectManifest(project, monitor);

        verify(metaInfFolder, never()).create(true, true, monitor);
        verify(projectManifestFactory, never()).writeNewManifestFile(project, monitor, manifestFile);
    }

    @Test
    public void should_manifest_headers_contains_valid_values() throws Exception {
        //When
        final ProjectManifestFactory projectManifestFactory = spy(new ProjectManifestFactory());
        doReturn("org.bonitasoft.studio.console.libs").when(projectManifestFactory).engineBundleSymbolicName();
        final Map<String, String> manifestHeaders = projectManifestFactory.createManifestHeaders("my project");

        //Then
        assertThat(manifestHeaders).contains(
                entry("Manifest-Version", "1.0"),
                entry("Bundle-ManifestVersion", "2"),
                entry("Bundle-Name", "my project"),
                entry("Bundle-SymbolicName", "myProject"),
                entry("Bundle-Version", "1.0.0.qualifier"),
                entry("Bundle-Vendor", "Bonitasoft S.A."),
                entry("Require-Bundle", "org.bonitasoft.studio.console.libs"),
                entry("Bundle-RequiredExecutionEnvironment", "JavaSE-1.8"));
    }

    @Test
    public void should_use_a_fallback_symbolic_name_if_project_name_cannot_be_converted_to_a_valid_java_identifiers()
            throws Exception {
        //When
        final ProjectManifestFactory projectManifestFactory = spy(new ProjectManifestFactory());
        doReturn("org.bonitasoft.studio.console.libs").when(projectManifestFactory).engineBundleSymbolicName();
        final Map<String, String> manifestHeaders = projectManifestFactory.createManifestHeaders("0001");

        //Then
        assertThat(manifestHeaders).contains(
                entry("Manifest-Version", "1.0"),
                entry("Bundle-ManifestVersion", "2"),
                entry("Bundle-Name", "0001"),
                entry("Bundle-SymbolicName", "org.bonitasoft.studio.myextensions"),
                entry("Bundle-Version", "1.0.0.qualifier"),
                entry("Bundle-Vendor", "Bonitasoft S.A."),
                entry("Require-Bundle", "org.bonitasoft.studio.console.libs"),
                entry("Bundle-RequiredExecutionEnvironment", "JavaSE-1.8"));
    }
}
