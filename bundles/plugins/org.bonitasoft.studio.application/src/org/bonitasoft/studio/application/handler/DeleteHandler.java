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
package org.bonitasoft.studio.application.handler;

import java.util.Objects;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.filestore.FileStoreFinder;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

public class DeleteHandler extends AbstractHandler {

    private FileStoreFinder selectionFinder = new FileStoreFinder();

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        ISelection selection = selectionFinder.getSelectionInExplorer();
        IResource resource = ((IAdaptable) ((IStructuredSelection) selection).getFirstElement()).getAdapter(IResource.class);
        IRepositoryFileStore fileStore = RepositoryManager.getInstance().getCurrentRepository()
                .getFileStore(resource);
        if (FileActionDialog
                .confirmDeletionQuestion(resource.getName())) {
            if (fileStore != null) {
                fileStore.delete();
            } else {
                try {
                    resource.delete(false, null);
                    AbstractFileStore.refreshExplorerView();
                } catch (CoreException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        ISelection selection = selectionFinder.getSelectionInExplorer();
        Repository currentRepository = RepositoryManager.getInstance().getCurrentRepository();
        return selection instanceof IStructuredSelection
                ? selectionCanBeDeleted((IStructuredSelection) selection, currentRepository)
                : false;
    }

    protected boolean selectionCanBeDeleted(IStructuredSelection selection, Repository currentRepository) {
        if (selection.size() == 1 && selection.getFirstElement() instanceof IAdaptable) {
            IAdaptable sel = (IAdaptable) selection.getFirstElement();
            IResource adapter = sel.getAdapter(IResource.class);
            if (adapter != null) {
                if (Objects.equals(currentRepository.getProject(), adapter)) {
                    return false;
                }
                IRepositoryFileStore fileStore = currentRepository.getFileStore(adapter);
                if (fileStore != null) {
                    return fileStore.canBeDeleted();
                }
                return currentRepository.getRepositoryStore(adapter) == null;
            }
        }
        return false;
    }

}
