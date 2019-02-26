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

import java.util.Optional;

import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IDeployable;
import org.bonitasoft.studio.common.repository.model.IRenamable;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;

public class FileStoreFinder {

    private static final String BONITA_PROJECT_EXPLORER_ID = "org.bonitasoft.studio.application.project.explorer";

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
        return findFileStore(resource, currentRepository)
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
                            Optional<IDeployable> toDeploy = findElementToDeploy(resource, currentRepository);
                            if (!toDeploy.isPresent()) {
                                IProject project = (resource).getProject();
                                if (project != null) {
                                    toDeploy = findElementToDeploy(project, currentRepository);
                                }
                            }
                            return toDeploy.orElse(null);
                        }
                    }
                    return null;
                });
    }

    public Optional<IDeployable> findElementToDeploy(IResource resource, Repository currentRepository) {
        return findFileStore(resource, currentRepository)
                .filter(IDeployable.class::isInstance)
                .map(IDeployable.class::cast);
    }

    public Optional<? extends IRepositoryFileStore> findSelectedFileStore(Repository currentRepository) {
        return getCurrentStructuredSelection()
                .map(selection -> {
                    Object element = selection.getFirstElement();
                    if (element instanceof IResource) {
                        return findFileStore(((IResource) element), currentRepository).orElse(null);
                    }
                    return null;
                });
    }

    public Optional<IStructuredSelection> getCurrentStructuredSelection() {
        return getExplorerViewPart()
                .map(vp -> vp.getViewSite().getSelectionProvider().getSelection())
                .filter(IStructuredSelection.class::isInstance)
                .map(IStructuredSelection.class::cast);
    }

    private Optional<IViewPart> getExplorerViewPart() {
        return Optional.ofNullable(
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(BONITA_PROJECT_EXPLORER_ID));
    }

    public Optional<? extends IRepositoryFileStore> findFileStore(IResource resource, Repository currentRepository) {
        return Optional.ofNullable(currentRepository.getFileStore(resource));
    }

    public ISelection getSelectionInExplorer() {
        return getExplorerViewPart().map(vp -> vp.getViewSite().getSelectionProvider().getSelection())
                .orElse(new StructuredSelection());
    }

}
