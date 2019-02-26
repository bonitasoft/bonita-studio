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
import java.util.Optional;

import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.FileStoreFinder;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.IStructuredSelection;

public class RenameHandler extends AbstractHandler {

    private FileStoreFinder selectionFinder = new FileStoreFinder();

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        Repository repo = RepositoryManager.getInstance().getCurrentRepository();
        selectionFinder.findElementToRename(repo)
                .ifPresent(elementToRename -> elementToRename.retrieveNewName().ifPresent(elementToRename::rename));
        return null;
    }

    @Override
    public boolean isEnabled() {
        Repository currentRepository = RepositoryManager.getInstance().getCurrentRepository();
        Optional<IStructuredSelection> selection = selectionFinder.getCurrentStructuredSelection();
        if (selection.isPresent() && selection.get().toList().size() == 1) {
            IResource resource = ((IAdaptable) selection.get().getFirstElement()).getAdapter(IResource.class);
            if (resource.getAdapter(IProject.class) != null) {
                return Objects.equals(resource.getAdapter(IProject.class), currentRepository.getProject());
            }
            return selectionFinder.findElementToRename(resource, RepositoryManager.getInstance().getCurrentRepository())
                    .isPresent();
        }
        return false;
    }

}
