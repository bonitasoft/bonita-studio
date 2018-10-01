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
package org.bonitasoft.studio.application.property.tester;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IDeployable;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

public class DeployableResourcePropertyTester extends PropertyTester {

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        Repository currentRepository = RepositoryManager.getInstance().getCurrentRepository();
        IFile file = ((IAdaptable) receiver).getAdapter(IFile.class);
        if (file != null) {
            return findElementToDeploy(file, currentRepository).isPresent();
        }
        IResource resource = ((IAdaptable) receiver).getAdapter(IResource.class);
        if (resource != null) {
            IProject project = (resource).getProject();
            return project != null
                    ? findElementToDeploy(project, currentRepository).isPresent()
                    : false;
        }
        return false;
    }

    public Optional<IDeployable> findElementToDeploy(Repository currentRepository) {
        return getCurrentViewPart()
                .map(vp -> {
                    ISelection selection = vp.getViewSite().getSelectionProvider().getSelection();
                    if (selection instanceof IStructuredSelection) {
                        Object element = ((IStructuredSelection) selection).getFirstElement();
                        if (element instanceof IAdaptable) {
                            IFile file = ((IAdaptable) element).getAdapter(IFile.class);
                            if (file != null) {
                                return findElementToDeploy(file, currentRepository).orElse(null);
                            }
                            IResource resource = ((IAdaptable) element).getAdapter(IResource.class);
                            if (resource != null) {
                                IProject project = (resource).getProject();
                                return project != null
                                        ? findElementToDeploy(project, currentRepository).orElse(null)
                                        : null;
                            }
                        }
                    }
                    return null;
                });
    }

    private Optional<IDeployable> findElementToDeploy(IFile file, Repository currentRepository) {
        Optional<IDeployable> deployable = currentRepository.getAllStores().stream()
                .map(IRepositoryStore::getChildren)
                .flatMap(Collection::stream)
                .filter(IDeployable.class::isInstance)
                .filter(fileStore -> Objects.equals(fileStore.getName(), file.getName()))
                .findFirst()
                .map(IDeployable.class::cast);
        if (!deployable.isPresent()) {
            IProject project = file.getProject();
            if (project != null) {
                return findElementToDeploy(project, currentRepository);
            }
        }
        return deployable;
    }

    private Optional<IDeployable> findElementToDeploy(IProject resource, Repository currentRepository) {
        IRepositoryFileStore fileStore = currentRepository.getFileStore(resource);
        if (fileStore != null && fileStore instanceof IDeployable) {
            return Optional.of((IDeployable) fileStore);
        }
        return Optional.empty();
    }

    private Optional<IViewPart> getCurrentViewPart() {
        IWorkbenchPart activePart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().getActivePart();
        return Optional.ofNullable(activePart.getAdapter(IViewPart.class));
    }

}
