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

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;

public class DeleteHandler extends AbstractHandler {

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        ISelectionService service = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
        ISelection selection = service.getSelection();
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
        ISelectionService service = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
        if (service == null) {
            return false;
        }
        ISelection selection = service.getSelection();

        if (selection instanceof IStructuredSelection
                && ((IStructuredSelection) selection).size() == 1) {
            Object sel = ((IStructuredSelection) selection).getFirstElement();
            if (sel instanceof IAdaptable && ((IAdaptable) sel).getAdapter(IResource.class) != null) {
                IResource adapter = ((IAdaptable) sel).getAdapter(IResource.class);
                if (adapter instanceof IFile) {
                    IRepositoryFileStore fileStore = RepositoryManager.getInstance().getCurrentRepository()
                            .getFileStore(adapter);
                    if (fileStore != null) {
                        return fileStore.canBeDeleted();
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
