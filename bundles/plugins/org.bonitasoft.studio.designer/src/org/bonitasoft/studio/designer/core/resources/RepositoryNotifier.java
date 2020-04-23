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
package org.bonitasoft.studio.designer.core.resources;

import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.filestore.FileStoreChangeEvent;
import org.bonitasoft.studio.common.repository.filestore.FileStoreChangeEvent.EventType;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * @author Romain Bioteau
 */
public class RepositoryNotifier {

    private final IRepository repository;

    public RepositoryNotifier(final IRepository repository) {
        this.repository = repository;
    }

    public void postImport() {
        repository.handleFileStoreEvent(new FileStoreChangeEvent(EventType.POST_IMPORT, null));
    }

    public void preImport() {
        repository.handleFileStoreEvent(new FileStoreChangeEvent(EventType.PRE_IMPORT, null));
    }

    private void delete(IRepositoryFileStore fileStore) {
        if (fileStore != null) {
            fileStore.delete();
        }
    }

    public void postDelete(final IRepositoryFileStore fileStore) throws ResourceNotFoundException {
        if (fileStore != null) {
            fileStore.getParentStore().refresh();
            AbstractFileStore.refreshExplorerView();
            repository.handleFileStoreEvent(new FileStoreChangeEvent(EventType.POST_DELETE, fileStore));
        }
    }

    public void postClose(final IRepositoryFileStore fileStore) throws ResourceNotFoundException {
        checkExists(fileStore);
        repository.handleFileStoreEvent(new FileStoreChangeEvent(EventType.POST_CLOSE, fileStore));
    }

    public void preOpen(final IRepositoryFileStore fileStore) throws LockedResourceException, ResourceNotFoundException {
        if (fileStore != null) {
            fileStore.getParentStore().refresh();
            checkExists(fileStore);
            repository.handleFileStoreEvent(new FileStoreChangeEvent(EventType.PRE_OPEN, fileStore));
        }
    }

    public void postSave(final IRepositoryFileStore fileStore) {
        if (fileStore != null) {
            new WorkspaceJob("Post save refresh") {
                
                @Override
                public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
                    fileStore.getParentStore().refresh();
                    AbstractFileStore.refreshExplorerView();
                    return Status.OK_STATUS;
                }
            }.schedule();
            repository.handleFileStoreEvent(new FileStoreChangeEvent(EventType.POST_SAVE, fileStore));
        }
    }

    private void checkExists(final IRepositoryFileStore fileStore) throws ResourceNotFoundException {
        if (!fileStore.getResource().exists()) {
            throw new ResourceNotFoundException(fileStore.getResource().getLocation().toString());
        }
    }

    public void dispatch(final WorkspaceAPIEvent event, final IRepositoryFileStore fileStore) throws LockedResourceException, ResourceNotFoundException {
        switch (event) {
            case PRE_OPEN:
                preOpen(fileStore);
                break;
            case POST_SAVE:
                postSave(fileStore);
                break;
            case POST_CLOSE:
                postClose(fileStore);
                break;
            case DELETE:
                delete(fileStore);
                break;
            case POST_DELETE:
                postDelete(fileStore);
                break;
            case PRE_IMPORT:
                preImport();
                break;
            case POST_IMPORT:
                postImport();
                break;
            default:
                throw new IllegalStateException("Unknown action");
        }

    }

}
