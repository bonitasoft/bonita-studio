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
package org.bonitasoft.studio.common.repository;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.bonitasoft.studio.common.extension.ExtensionContextInjectionFactory;
import org.bonitasoft.studio.common.repository.core.DatabaseHandler;
import org.bonitasoft.studio.common.repository.core.ProjectClasspathFactory;
import org.bonitasoft.studio.common.repository.core.ProjectManifestFactory;
import org.bonitasoft.studio.common.repository.jdt.JDTTypeHierarchyManager;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.IProgressMonitor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryTest {

    @Mock
    private IWorkspace workspace;
    @Mock
    private IProject project;
    @Mock
    private ExtensionContextInjectionFactory extensionContextInjectionFactory;
    @Mock
    private JDTTypeHierarchyManager jdtTypeHierarchyManager;
    @Mock
    private ProjectManifestFactory projectManifestFactory;
    @Mock
    private IProgressMonitor monitor;
    @Mock
    private ProjectClasspathFactory bonitaBPMProjectClasspath;
    @Mock
    private DatabaseHandler bonitaHomeHandler;

    @Test
    public void should_open_trigger_project_manifest_factory() throws Exception {
        final Repository repository = newRepository();

        repository.open(monitor);

        verify(projectManifestFactory).createProjectManifest(project, monitor);
    }

    @Test
    public void should_open_trigger_project_classpath_factory() throws Exception {
        final Repository repository = newRepository();

        repository.open(monitor);

        verify(bonitaBPMProjectClasspath).create(repository, monitor);
    }

    @Test
    public void should_not_refresh_project_when_deleting_a_closed_repository() throws Exception {
        final Repository repository = newRepository();

        repository.delete(monitor);

        verify(project, never()).refreshLocal(anyInt(), any(IProgressMonitor.class));
    }

    @Test
    public void should_refresh_project_when_deleting_an_open_repository() throws Exception {
        final Repository repository = newRepository();
        doReturn(true).when(repository).isBuildEnable();
        doReturn(true).when(project).isOpen();

        repository.delete(monitor);

        verify(project).refreshLocal(anyInt(), any(IProgressMonitor.class));
    }

    private Repository newRepository() {
        final Repository repo = spy(new Repository(workspace, project, extensionContextInjectionFactory, jdtTypeHierarchyManager, projectManifestFactory,
                bonitaBPMProjectClasspath, true));
        doReturn(bonitaHomeHandler).when(repo).getDatabaseHandler();
        return repo;
    }

}
