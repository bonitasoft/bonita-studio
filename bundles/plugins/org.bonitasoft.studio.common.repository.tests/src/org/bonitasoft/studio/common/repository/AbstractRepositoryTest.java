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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.bonitasoft.studio.common.extension.ExtensionContextInjectionFactory;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.DatabaseHandler;
import org.bonitasoft.studio.common.repository.jdt.JDTTypeHierarchyManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AbstractRepositoryTest {

    @Mock
    private IWorkspace workspace;
    @Mock
    private IProject project;
    @Mock
    private BonitaProject bonitaProject;
    @Mock
    private ExtensionContextInjectionFactory extensionContextInjectionFactory;
    @Mock
    private JDTTypeHierarchyManager jdtTypeHierarchyManager;
    @Mock
    private IProgressMonitor monitor;
    @Mock
    private DatabaseHandler bonitaHomeHandler;
    @Mock
    private IWorkspaceRoot root;
    @Mock
    private IEventBroker eventBroker;

    @Test
    void should_not_refresh_project_when_deleting_a_closed_repository() throws Exception {
        final AbstractRepository repository = newRepository();

        repository.delete(monitor);

        verify(project, never()).refreshLocal(anyInt(), any(IProgressMonitor.class));
    }

    @Test
    void should_refresh_project_when_deleting_an_open_repository() throws Exception {
        final AbstractRepository repository = newRepository();
        doReturn(true).when(project).isOpen();

        repository.delete(monitor);

        verify(project).refreshLocal(anyInt(), any(IProgressMonitor.class));
    }

    @Test
    void should_return_the_correct_file_store_when_two_resources_have_the_same_name() throws Exception {
        IResource resource1 = mock(IResource.class);
        IResource resource2 = mock(IResource.class);
        when(resource1.getName()).thenReturn("name.xml");
        when(resource2.getName()).thenReturn("name.xml");
        when(workspace.getRoot()).thenReturn(root);
        when(resource2.getWorkspace()).thenReturn(workspace);

        IRepositoryStore repositoryStore1 = mock(IRepositoryStore.class);
        IRepositoryStore repositoryStore2 = mock(IRepositoryStore.class);
        IFolder container1 = mock(IFolder.class);
        IFolder container2 = mock(IFolder.class);
        when(repositoryStore1.getResource()).thenReturn(container1);
        when(repositoryStore2.getResource()).thenReturn(container2);
        when(resource1.getParent()).thenReturn(container1);
        when(resource2.getParent()).thenReturn(container2);

        IRepositoryFileStore fileStore1 = mock(IRepositoryFileStore.class);
        IRepositoryFileStore fileStore2 = mock(IRepositoryFileStore.class);
        when(repositoryStore1.getChild("name.xml", false)).thenReturn(fileStore1);
        when(repositoryStore2.getChild("name.xml", false)).thenReturn(fileStore2);

        AbstractRepository repository = newRepository();
        doReturn(Arrays.asList(repositoryStore1, repositoryStore2)).when(repository).getAllStores();

        assertThat(repository.getFileStore(resource1)).isEqualTo(fileStore1);
        assertThat(repository.getFileStore(resource2)).isEqualTo(fileStore2);
    }

    private AbstractRepository newRepository() throws CoreException, MigrationException {
        lenient().doReturn(project).when(bonitaProject).getAppProject();
        return spy(new TestRepository(workspace, bonitaProject,
                extensionContextInjectionFactory,
                jdtTypeHierarchyManager, 
                eventBroker));
    }
    
}


