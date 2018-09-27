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
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

public class DeployableResourcePropertyTester extends PropertyTester {

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (receiver instanceof IFile) {
            return findElementToDeploy((IFile) receiver, RepositoryManager.getInstance().getCurrentRepository()).isPresent();
        }
        return false;
    }

    public Optional<IDeployable> findElementToDeploy(Repository currentRepository) {
        return getCurrentViewPart()
                .map(vp -> {
                    ISelection selection = vp.getViewSite().getSelectionProvider().getSelection();
                    if (selection instanceof IStructuredSelection) {
                        Object element = ((IStructuredSelection) selection).getFirstElement();
                        if (element instanceof IFile) {
                            IFile file = (IFile) element;
                            return findElementToDeploy(file, currentRepository).orElse(null);
                        } // TODO: IProject for rest API
                    }
                    return null;
                });
    }

    private Optional<IDeployable> findElementToDeploy(IFile file, Repository currentRepository) {
        return currentRepository.getAllStores().stream()
                .map(IRepositoryStore::getChildren)
                .flatMap(Collection::stream)
                .filter(IDeployable.class::isInstance)
                .filter(fileStore -> Objects.equals(fileStore.getName(), file.getName()))
                .findFirst()
                .map(IDeployable.class::cast);
    }

    private Optional<IViewPart> getCurrentViewPart() {
        IWorkbenchPart activePart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().getActivePart();
        return Optional.ofNullable(activePart.getAdapter(IViewPart.class));
    }

}
