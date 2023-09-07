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

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.model.IDeployable;
import org.bonitasoft.studio.common.repository.model.IRenamable;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.junit.jupiter.api.Test;

class FileStoreFinderTest {

    private static final String DIAGRAMS_FOLDER_NAME = "Diagrams";
    private static final String ORGANIZATION_FOLDER_NAME = "Organization";
    private static final String FILE_NAME = "ACME.organization";
    private static final String DOCUMENT_NAME = "doc.pdf";

    @Test
    void should_find_element_to_rename() {
        FileStoreFinder finder = spy(FileStoreFinder.class);

        IFile file = mock(IFile.class);
        IFolder parent = mock(IFolder.class);
        when(parent.getName()).thenReturn(ORGANIZATION_FOLDER_NAME);
        when(file.getName()).thenReturn(FILE_NAME);
        when(file.getParent()).thenReturn(parent);

        IStructuredSelection selection = new StructuredSelection(file);
        doReturn(Optional.of(selection)).when(finder).getCurrentStructuredSelection();
        AbstractRepository repository = mock(AbstractRepository.class);
        DeployableFileStore deployableFileStore = new DeployableFileStore(FILE_NAME);
        when(repository.getFileStore(file)).thenReturn(deployableFileStore);
        Optional<IRenamable> elementToRename = finder.findElementToRename(repository);
        assertThat(elementToRename).isPresent();
        DeployableFileStore fileStore = (DeployableFileStore) elementToRename.get();
        assertThat(fileStore.getName()).isEqualTo(FILE_NAME);

        when(file.getName()).thenReturn(DOCUMENT_NAME);
        NotDeployableFileStore notDeployableFileStore = new NotDeployableFileStore(DOCUMENT_NAME);
        when(repository.getFileStore(file)).thenReturn(notDeployableFileStore);
        elementToRename = finder.findElementToRename(repository);
        assertThat(elementToRename).isEmpty();
    }

    @Test
    void should_find_element_to_deploy() {
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
        AbstractRepository repository = mock(AbstractRepository.class);

        DeployableFileStore deployableFileStore = new DeployableFileStore(FILE_NAME);
        when(repository.getFileStore(file)).thenReturn(deployableFileStore);
        Optional<IDeployable> elementToDeploy = finder.findElementToDeploy(repository);
        assertThat(elementToDeploy).isPresent();
        DeployableFileStore fileStore = (DeployableFileStore) elementToDeploy.get();
        assertThat(fileStore.getName()).isEqualTo(FILE_NAME);

        when(file.getName()).thenReturn(DOCUMENT_NAME);
        NotDeployableFileStore notDeployableFileStore = new NotDeployableFileStore(DOCUMENT_NAME);
        when(repository.getFileStore(file)).thenReturn(notDeployableFileStore);
        elementToDeploy = finder.findElementToDeploy(repository);
        assertThat(elementToDeploy).isEmpty();
    }

    private static class NotDeployableFileStore implements IRepositoryFileStore {

        private String name;

        public NotDeployableFileStore(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public IRepositoryStore getParentStore() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Object getContent() throws ReadFileStoreException {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public IResource getResource() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Set getRelatedResources() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Set getRelatedFileStore() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public boolean isShared() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean isReadOnly() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public void setReadOnly(boolean readOnly) {
            // TODO Auto-generated method stub

        }

        @Override
        public IWorkbenchPart open() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void close() {
            // TODO Auto-generated method stub

        }

        @Override
        public void delete() {
            // TODO Auto-generated method stub

        }

        @Override
        public void renameLegacy(String newName) {
            // TODO Auto-generated method stub

        }

        @Override
        public void save(Object content) {
            // TODO Auto-generated method stub

        }

        @Override
        public boolean canBeShared() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean canBeExported() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public IStatus export(String targetAbsoluteFilePath) throws IOException {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public byte[] toByteArray() throws IOException {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public boolean canBeDeleted() {
            // TODO Auto-generated method stub
            return false;
        }
    }

    private static class DeployableFileStore implements IRepositoryFileStore, IDeployable, IRenamable {

        private String name;

        public DeployableFileStore(String name) {
            this.name = name;
        }

        @Override
        public void rename(String newName) {
            // TODO Auto-generated method stub

        }

        @Override
        public Optional<String> retrieveNewName() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void deployInUI() {
            // TODO Auto-generated method stub

        }

        @Override
        public IStatus deploy(APISession session, Map<String, Object> options, IProgressMonitor monitor) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public IRepositoryStore getParentStore() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Object getContent() throws ReadFileStoreException {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public IResource getResource() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Set getRelatedResources() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Set getRelatedFileStore() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public boolean isShared() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean isReadOnly() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public void setReadOnly(boolean readOnly) {
            // TODO Auto-generated method stub

        }

        @Override
        public IWorkbenchPart open() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void close() {
            // TODO Auto-generated method stub

        }

        @Override
        public void delete() {
            // TODO Auto-generated method stub

        }

        @Override
        public void renameLegacy(String newName) {
            // TODO Auto-generated method stub

        }

        @Override
        public void save(Object content) {
            // TODO Auto-generated method stub

        }

        @Override
        public boolean canBeShared() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean canBeExported() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public IStatus export(String targetAbsoluteFilePath) throws IOException {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public byte[] toByteArray() throws IOException {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public boolean canBeDeleted() {
            // TODO Auto-generated method stub
            return false;
        }

    }
}
