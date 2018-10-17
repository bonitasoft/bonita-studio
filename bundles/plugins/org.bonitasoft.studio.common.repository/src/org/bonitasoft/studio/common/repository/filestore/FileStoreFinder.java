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

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IDeployable;
import org.bonitasoft.studio.common.repository.model.IRenamable;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

public class FileStoreFinder {

    public Optional<IRenamable> findElementToRename(Repository currentRepository) {
        return getCurrentStructuredSelection()
                .map(selection -> {
                    Object element = selection.getFirstElement();
                    if (element instanceof IProject) {
                        return RepositoryManager.getInstance().getRepository(((IProject) element).getName());
                    }
                    if (element instanceof IResource) {
                        return findElementToRename(((IResource) element), currentRepository).orElse(null);
                    }
                    return null;
                });
    }

    public Optional<IRenamable> findElementToRename(IResource resource, Repository currentRepository) {
        return findFileStore(resource.getName(), currentRepository)
                .filter(IRenamable.class::isInstance)
                .map(IRenamable.class::cast)
                .filter(IRenamable::canBeRenamed);
    }

    public Optional<IDeployable> findElementToDeploy(Repository currentRepository) {
        return getCurrentStructuredSelection()
                .map(selection -> {
                    Object element = selection.getFirstElement();
                    if (element instanceof IAdaptable) {
                        IResource resource = ((IAdaptable) element).getAdapter(IResource.class);
                        if (resource != null) {
                            Optional<IDeployable> toDeploy = findElementToDeploy(resource.getName(), currentRepository);
                            if (!toDeploy.isPresent()) {
                                IProject project = (resource).getProject();
                                if (project != null) {
                                    toDeploy = findElementToDeploy(project.getName(), currentRepository);
                                }
                            }
                            return toDeploy.orElse(null);
                        }
                    }
                    return null;
                });
    }

    public Optional<IDeployable> findElementToDeploy(String resourceName, Repository currentRepository) {
        return findFileStore(resourceName, currentRepository)
                .filter(IDeployable.class::isInstance)
                .map(IDeployable.class::cast);
    }

    public Optional<? extends IRepositoryFileStore> findSelectedFileStore(Repository currentRepository) {
        return getCurrentStructuredSelection()
                .map(selection -> {
                    Object element = selection.getFirstElement();
                    if (element instanceof IResource) {
                        return findFileStore(((IResource) element).getName(), currentRepository).orElse(null);
                    }
                    return null;
                });
    }

    protected Optional<IStructuredSelection> getCurrentStructuredSelection() {
        return getCurrentViewPart()
                .map(vp -> vp.getViewSite().getSelectionProvider().getSelection())
                .filter(IStructuredSelection.class::isInstance)
                .map(IStructuredSelection.class::cast);
    }

    private Optional<IViewPart> getCurrentViewPart() {
        IWorkbenchPart activePart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().getActivePart();
        return Optional.ofNullable(activePart.getAdapter(IViewPart.class));
    }

    private Optional<? extends IRepositoryFileStore> findFileStore(String resourceName, Repository currentRepository) {
        return currentRepository.getAllStores().stream()
                .map(IRepositoryStore::getChildren)
                .flatMap(Collection::stream)
                .filter(fileStore -> Objects.equals(fileStore.getName(), resourceName))
                .findFirst();
    }

}
