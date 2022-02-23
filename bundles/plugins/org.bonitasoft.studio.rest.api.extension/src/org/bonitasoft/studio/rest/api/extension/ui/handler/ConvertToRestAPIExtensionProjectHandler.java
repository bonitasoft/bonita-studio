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
package org.bonitasoft.studio.rest.api.extension.ui.handler;

import java.util.Optional;

import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.FileStoreFinder;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.maven.CustomPageProjectFileStore;
import org.bonitasoft.studio.maven.ImportProjectException;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;

public class ConvertToRestAPIExtensionProjectHandler extends AbstractHandler {

    private FileStoreFinder selectionFinder = new FileStoreFinder();

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        CustomPageProjectFileStore fStore = getSelectedCustomPageProjectFileStore();
        if (fStore != null) {
            try {
                fStore.importProject();
            } catch (ImportProjectException e) {
                new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.importFailedTitle,
                        Messages.importFailedMsg, e).open();
            }
        }
        return null;
    }

    @Override
    public boolean isEnabled() {
        if (RepositoryManager.getInstance().hasActiveRepository()) {
            return getSelectedCustomPageProjectFileStore() != null;
        }
        return false;
    }

    private CustomPageProjectFileStore getSelectedCustomPageProjectFileStore() {
        Optional<IStructuredSelection> currentStructuredSelection = selectionFinder.getCurrentStructuredSelection();
        if (currentStructuredSelection.isPresent()) {
            IStructuredSelection selection = currentStructuredSelection.get();
            if (selection.size() == 1) {
                Object selectedObject = selection.getFirstElement();
                if (selectedObject instanceof IAdaptable) {
                    IResource resource =  ((IAdaptable) selectedObject).getAdapter(IResource.class);
                    Optional<? extends IRepositoryFileStore> fStore = selectionFinder.findFileStore(resource,
                            RepositoryManager.getInstance().getCurrentRepository());
                    return fStore.filter(CustomPageProjectFileStore.class::isInstance)
                            .map(CustomPageProjectFileStore.class::cast)
                            .filter(CustomPageProjectFileStore::canBeImported)
                            .orElse(null);
                }
            }
        }
        return null;
    }

}
