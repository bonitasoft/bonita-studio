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

import java.util.Optional;

import org.bonitasoft.studio.application.views.provider.UIDArtifactFilters;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.FileStoreFinder;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

public class OpenHandler extends AbstractHandler {

    private FileStoreFinder fileStoreFinder = new FileStoreFinder();

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        ISelection selection = fileStoreFinder.getSelectionInExplorer();
        IResource resource = ((IAdaptable) ((IStructuredSelection) selection).getFirstElement()).getAdapter(IResource.class);
        Optional<? extends IRepositoryFileStore> fileStore = fileStoreFinder.findFileStore(resource.getName(),
                RepositoryManager.getInstance().getCurrentRepository());
        if (fileStore.isPresent()) {
            fileStore.get().open();
        } else {
            try {
                IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), (IFile) resource);
            } catch (PartInitException e) {
                BonitaStudioLog.error(e);
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
        ISelection selection = fileStoreFinder.getSelectionInExplorer();
        if (selection instanceof IStructuredSelection
                && ((IStructuredSelection) selection).size() == 1) {
            Object sel = ((IStructuredSelection) selection).getFirstElement();
            if (sel instanceof IAdaptable && ((IAdaptable) sel).getAdapter(IResource.class) != null) {
                IResource adapter = ((IAdaptable) sel).getAdapter(IResource.class);
                if (adapter instanceof IFile || UIDArtifactFilters.isUIDArtifact(adapter)) {
                    IRepositoryFileStore fileStore = RepositoryManager.getInstance().getCurrentRepository()
                            .getFileStore(adapter);
                    if (fileStore != null) {
                        return !fileStore.getName().endsWith(".jar");
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
