/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.repository.filestore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.model.IDeployable;
import org.bonitasoft.studio.common.repository.model.IRenamable;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.junit.Test;

public class FileStoreFinderTest {

    private static final String DIAGRAMS_FOLDER_NAME = "Diagrams";
    private static final String ORGANIZATION_FOLDER_NAME = "Organization";
    private static final String FILE_NAME = "ACME.organization";
    private static final String DOCUMENT_NAME = "doc.pdf";

    @Test
    public void should_find_element_to_rename() {
        FileStoreFinder finder = spy(FileStoreFinder.class);

        IFile file = mock(IFile.class);
        IFolder parent = mock(IFolder.class);
        when(parent.getName()).thenReturn(ORGANIZATION_FOLDER_NAME);
        when(file.getName()).thenReturn(FILE_NAME);
        when(file.getParent()).thenReturn(parent);

        IStructuredSelection selection = new StructuredSelection(file);
        doReturn(Optional.of(selection)).when(finder).getCurrentStructuredSelection();
        Repository repository = mock(Repository.class);
        DeployableFileStore deployableFileStore = mock(DeployableFileStore.class);
        when(deployableFileStore.getName()).thenReturn(FILE_NAME);
        when(deployableFileStore.canBeRenamed()).thenReturn(true);
        when(repository.getFileStore(file)).thenReturn(deployableFileStore);
        Optional<IRenamable> elementToRename = finder.findElementToRename(repository);
        assertThat(elementToRename).isPresent();
        DeployableFileStore fileStore = (DeployableFileStore) elementToRename.get();
        assertThat(fileStore.getName()).isEqualTo(FILE_NAME);

        when(file.getName()).thenReturn(DOCUMENT_NAME);
        NotDeployableFileStore notDeployableFileStore = mock(NotDeployableFileStore.class);
        when(notDeployableFileStore.getName()).thenReturn(DOCUMENT_NAME);
        when(repository.getFileStore(file)).thenReturn(notDeployableFileStore);
        elementToRename = finder.findElementToRename(repository);
        assertThat(elementToRename).isEmpty();
    }

    @Test
    public void should_find_element_to_deploy() {
        FileStoreFinder finder = spy(FileStoreFinder.class);

        IFile file = mock(IFile.class);
        IFolder parent = mock(IFolder.class);
        when(parent.getName()).thenReturn(ORGANIZATION_FOLDER_NAME);
        when(file.getName()).thenReturn(FILE_NAME);
        when(file.getParent()).thenReturn(parent);

        IAdaptable adaptable = mock(IAdaptable.class);
        when(adaptable.getAdapter(IResource.class)).thenReturn(file);
        IStructuredSelection selection = new StructuredSelection(adaptable);
        doReturn(Optional.of(selection)).when(finder).getCurrentStructuredSelection();
        Repository repository = mock(Repository.class);

        DeployableFileStore deployableFileStore = mock(DeployableFileStore.class);
        when(deployableFileStore.getName()).thenReturn(FILE_NAME);
        when(repository.getFileStore(file)).thenReturn(deployableFileStore);
        Optional<IDeployable> elementToDeploy = finder.findElementToDeploy(repository);
        assertThat(elementToDeploy).isPresent();
        DeployableFileStore fileStore = (DeployableFileStore) elementToDeploy.get();
        assertThat(fileStore.getName()).isEqualTo(FILE_NAME);

        when(file.getName()).thenReturn(DOCUMENT_NAME);
        NotDeployableFileStore notDeployableFileStore = mock(NotDeployableFileStore.class);
        when(notDeployableFileStore.getName()).thenReturn(DOCUMENT_NAME);
        when(repository.getFileStore(file)).thenReturn(notDeployableFileStore);
        elementToDeploy = finder.findElementToDeploy(repository);
        assertThat(elementToDeploy).isEmpty();
    }

}

abstract class NotDeployableFileStore implements IRepositoryFileStore {
}

abstract class DeployableFileStore implements IRepositoryFileStore, IDeployable, IRenamable {

}
