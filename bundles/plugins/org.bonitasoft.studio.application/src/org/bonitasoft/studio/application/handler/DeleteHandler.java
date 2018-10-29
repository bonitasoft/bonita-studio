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

import java.util.ArrayList;
import java.util.List;
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
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;

public class DeleteHandler extends AbstractHandler {

    private FileStoreFinder selectionFinder = new FileStoreFinder();

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IStructuredSelection selection = (IStructuredSelection) selectionFinder.getSelectionInExplorer();
        List<IResource> selectedResources = new ArrayList<>();
        for (Object sel : selection.toList()) {
            selectedResources.add(((IAdaptable) sel).getAdapter(IResource.class));
        }
        Repository currentRepository = RepositoryManager.getInstance().getCurrentRepository();
        if ((selectedResources.size() == 1 && FileActionDialog
                .confirmDeletionQuestion(selectedResources.get(0).getName()))
                || FileActionDialog.confirmDeletionQuestionWithCustomMessage(
                        NLS.bind(IDEWorkbenchMessages.DeleteResourceAction_confirmN,
                                selectedResources.size()))) {
            for (IResource res : selectedResources) {
                IRepositoryFileStore fileStore = currentRepository
                        .getFileStore(res);
                if (fileStore != null) {
                    fileStore.delete();
                } else {
                    try {
                        res.delete(false, null);
                    } catch (CoreException e) {
                        BonitaStudioLog.error(e);
                    }
                }
            }
            AbstractFileStore.refreshExplorerView();
        }
        return null;
    }


    @Override
    public boolean isEnabled() {
        ISelection selection = selectionFinder.getSelectionInExplorer();
        Repository currentRepository = RepositoryManager.getInstance().getCurrentRepository();
        return selection instanceof IStructuredSelection
                ? selectionCanBeDeleted((IStructuredSelection) selection, currentRepository)
                : false;
    }

    protected boolean selectionCanBeDeleted(IStructuredSelection selection, Repository currentRepository) {
        for (Object sel : selection.toList()) {
            if (sel instanceof IAdaptable) {
                IResource adapter = ((IAdaptable) sel).getAdapter(IResource.class);
                if (adapter != null) {
                    if (Objects.equals(currentRepository.getProject(), adapter)) {
                        return false;
                    }
                    IRepositoryFileStore fileStore = currentRepository.getFileStore(adapter);
                    if (fileStore != null && !fileStore.canBeDeleted()) {
                        return false;
                    } else if (fileStore == null && currentRepository.getRepositoryStore(adapter) != null) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
        return selection.size() > 0;
    }

}
