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

import java.util.Objects;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.BonitaProjectNature;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Path;

public class BonitaProjectPropertyTester extends PropertyTester {

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (receiver instanceof IAdaptable) {
            IResource resource = ((IAdaptable) receiver).getAdapter(IResource.class);
            if (Objects.equals(property, "isBonitaProject")) {
                return isBonitaProjectTest(resource, expectedValue);
            }
            if (Objects.equals(property, "isBonitaStoreContainer")) {
                return isBonitaStoreContainer(resource);
            }
            if (Objects.equals(property, "isContainer")) {
                return resource instanceof IContainer && !isContainedInBonitaStore(resource);
            }
        }
        return false;
    }

    private boolean isContainedInBonitaStore(IResource resource) {
        IContainer parent = resource.getParent();
        while (parent != null && !isBonitaStoreContainer(parent)) {
            parent = parent.getParent();
        }
        return parent != null;
    }

    private boolean isBonitaStoreContainer(Object receiver) {
        if (RepositoryManager.getInstance().hasActiveRepository()
                && RepositoryManager.getInstance().getCurrentRepository().isLoaded()) {
            AbstractRepository currentRepository = RepositoryManager.getInstance().getCurrentRepository();
            if (receiver instanceof IFolder && !isDocumentationStore(receiver)) {
                for (final IRepositoryStore<? extends IRepositoryFileStore> store : currentRepository.getAllStores()) {
                    if (Objects.equals(store.getResource(), receiver)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isDocumentationStore(Object receiver) {
        return ((IFolder) receiver).getProjectRelativePath().equals(Path.fromOSString("documentation"));
    }

    private boolean isBonitaProjectTest(Object receiver, Object expectedValue) {
        if (receiver instanceof IProject) {
            try {
                return receiver instanceof IProject && ((IProject) receiver).isOpen()
                        && ((IProject) receiver).hasNature(BonitaProjectNature.NATURE_ID);
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
                return false;
            }
        } else if (receiver instanceof IFolder && expectedValue != null) {
            return receiver instanceof IFolder && Objects.equals(((IResource) receiver).getName(), expectedValue);
        }
        return false;
    }

}
