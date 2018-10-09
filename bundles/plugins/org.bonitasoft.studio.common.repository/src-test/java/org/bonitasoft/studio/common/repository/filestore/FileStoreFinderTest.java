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

import java.util.Arrays;
import java.util.Optional;

import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.model.IDeployable;
import org.bonitasoft.studio.common.repository.model.IRenamable;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPart;
import org.junit.Test;

public class FileStoreFinderTest {

    private static final String ORGA_NAME = "ACME.organization";
    private static final String DOCUMENT_NAME = "doc.pdf";

    @Test
    public void should_find_element_to_rename() {
        FileStoreFinder finder = spy(FileStoreFinder.class);

        IFile file = mock(IFile.class);
        when(file.getName()).thenReturn(ORGA_NAME);
        IStructuredSelection selection = new StructuredSelection(file);
        doReturn(Optional.of(selection)).when(finder).getCurrentStructuredSelection();
        Repository repository = initRepository();

        Optional<IRenamable> elementToRename = finder.findElementToRename(repository);
        assertThat(elementToRename).isPresent();
        FileStoreWithInterface fileStore = (FileStoreWithInterface) elementToRename.get();
        assertThat(fileStore.getName()).isEqualTo(ORGA_NAME);

        when(file.getName()).thenReturn(DOCUMENT_NAME);

        elementToRename = finder.findElementToRename(repository);
        assertThat(elementToRename).isEmpty();
    }

    @Test
    public void should_find_element_to_deploy() {
        FileStoreFinder finder = spy(FileStoreFinder.class);

        IFile file = mock(IFile.class);
        when(file.getName()).thenReturn(ORGA_NAME);
        IAdaptable adaptable = mock(IAdaptable.class);
        when(adaptable.getAdapter(IResource.class)).thenReturn(file);
        IStructuredSelection selection = new StructuredSelection(adaptable);
        doReturn(Optional.of(selection)).when(finder).getCurrentStructuredSelection();
        Repository repository = initRepository();

        Optional<IDeployable> elementToDeploy = finder.findElementToDeploy(repository);
        assertThat(elementToDeploy).isPresent();
        FileStoreWithInterface fileStore = (FileStoreWithInterface) elementToDeploy.get();
        assertThat(fileStore.getName()).isEqualTo(ORGA_NAME);

        when(file.getName()).thenReturn(DOCUMENT_NAME);

        elementToDeploy = finder.findElementToDeploy(repository);
        assertThat(elementToDeploy).isEmpty();
    }

    private Repository initRepository() {
        Repository repository = mock(Repository.class);
        IRepositoryStore<? extends IRepositoryFileStore> storeWithInterfaces = initStoreWithInterfaces();
        IRepositoryStore<? extends IRepositoryFileStore> storeWithoutInterface = initStoreWithoutInterface();
        when(repository.getAllStores()).thenReturn(Arrays.asList(storeWithInterfaces, storeWithoutInterface));
        return repository;
    }

    private IRepositoryStore<? extends IRepositoryFileStore> initStoreWithInterfaces() {
        IRepositoryStore<FileStoreWithInterface> repositoryStore = mock(IRepositoryStore.class);
        FileStoreWithInterface fileStore = new FileStoreWithInterface(ORGA_NAME, repositoryStore);
        when(repositoryStore.getChildren()).thenReturn(Arrays.asList(fileStore));
        return repositoryStore;
    }

    private IRepositoryStore<? extends IRepositoryFileStore> initStoreWithoutInterface() {
        IRepositoryStore<FileStoreWithoutInterface> repositoryStore = mock(IRepositoryStore.class);
        FileStoreWithoutInterface fileStore = new FileStoreWithoutInterface(DOCUMENT_NAME, repositoryStore);
        when(repositoryStore.getChildren()).thenReturn(Arrays.asList(fileStore));
        return repositoryStore;
    }

}

class FileStoreWithoutInterface extends AbstractFileStore {

    public FileStoreWithoutInterface(String fileName, IRepositoryStore<? extends IRepositoryFileStore> parentStore) {
        super(fileName, parentStore);
    }

    @Override
    public Object getContent() throws ReadFileStoreException {
        return null;
    }

    @Override
    public Image getIcon() {
        return null;
    }

    @Override
    protected void doSave(Object content) {
    }

    @Override
    protected IWorkbenchPart doOpen() {
        return null;
    }

    @Override
    protected void doClose() {
    }
}

class FileStoreWithInterface extends AbstractFileStore implements IDeployable, IRenamable {

    public FileStoreWithInterface(String fileName, IRepositoryStore<? extends IRepositoryFileStore> parentStore) {
        super(fileName, parentStore);
    }

    @Override
    public Object getContent() throws ReadFileStoreException {
        return null;
    }

    @Override
    public Image getIcon() {
        return null;
    }

    @Override
    protected void doSave(Object content) {
    }

    @Override
    protected IWorkbenchPart doOpen() {
        return null;
    }

    @Override
    protected void doClose() {
    }

    @Override
    public void rename(String newName) {
    }

    @Override
    public Optional<String> retrieveNewName() {
        return null;
    }

    @Override
    public void deploy() {
    }

}
