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
package org.bonitasoft.studio.maven.operation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Properties;

import org.apache.maven.archetype.catalog.Archetype;
import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.common.RestAPIExtensionNature;
import org.bonitasoft.studio.maven.model.RestAPIExtensionArchetypeConfiguration;
import org.bonitasoft.studio.maven.operation.CreateCustomPageProjectOperation;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionRepositoryStore;
import org.eclipse.core.internal.resources.ProjectDescription;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.m2e.core.project.IProjectConfigurationManager;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreateCustomPageProjectOperationTest {

    @Mock
    private IProgressMonitor monitor;
    @Mock
    private RestAPIExtensionRepositoryStore store;
    @Mock
    private IFolder repoStoreFolder;

    @Test
    public void should_create_an_eclipse_project_from_archetype() throws Exception {
        final IProjectConfigurationManager projectConfigurationManager = mock(IProjectConfigurationManager.class);
        final RestAPIExtensionRepositoryStore repository = mock(RestAPIExtensionRepositoryStore.class,
                Mockito.RETURNS_DEEP_STUBS);
        when(repository.getResource().getLocation()).thenReturn(Path.fromOSString("/"));
        final IProject newProject = mock(IProject.class);
        final IProject parentProject = mock(IProject.class);
        when(newProject.getDescription()).thenReturn(new ProjectDescription());
        final IFile pagePropertyFile = mock(IFile.class);
        when(newProject.getFile("src/main/resources/page.properties")).thenReturn(pagePropertyFile);
        when(newProject.getParent()).thenReturn(parentProject);
        when(projectConfigurationManager.createArchetypeProjects(any(Path.class),
                any(Archetype.class),
                anyString(),
                anyString(),
                anyString(),
                anyString(),
                any(Properties.class),
                any(ProjectImportConfiguration.class),
                eq(monitor))).thenReturn(Arrays.asList(newProject));
        final CreateCustomPageProjectOperation operation = spy(new CreateCustomPageProjectOperation(
                repository,
                projectConfigurationManager,
                new ProjectImportConfiguration(),
                RestAPIExtensionArchetypeConfiguration.defaultArchetypeConfiguration()));

        operation.doRun(monitor);
        
        StatusAssert.assertThat(operation.getStatus()).isOK();
        final ArgumentCaptor<IProjectDescription> argumentCaptor = ArgumentCaptor.forClass(IProjectDescription.class);
        verify(newProject).setDescription(argumentCaptor.capture(), eq(monitor));
        verify(pagePropertyFile).setCharset("UTF-8", monitor);

        final IProjectDescription projectDescription = argumentCaptor.getValue();
        assertThat(projectDescription.getNatureIds()).containsOnly("org.eclipse.m2e.core.maven2Nature",
                RestAPIExtensionNature.NATURE_ID);
        assertThat(projectDescription.getBuildSpec()).extracting("name").containsOnly(
                "org.eclipse.m2e.core.maven2Builder");
    }
    
}
