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
package org.bonitasoft.studio.application.views.provider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.jdt.internal.ui.navigator.JavaNavigatorContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

public class BonitaExplorerContentProvider extends JavaNavigatorContentProvider {

    private TreeViewer viewer;

    @Override
    public boolean hasChildren(Object element) {
        if (UIDArtifactFilters.isUIDArtifact(element)) {
            return false;
        }
        return super.hasChildren(element);
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        if (UIDArtifactFilters.isUIDArtifact(parentElement)) {
            return new Object[0];
        }
        if (parentElement instanceof IFolder && isEnvironmentsFolder((IFolder) parentElement)) {
            return addLocalEnvironment(parentElement);
        }
        return super.getChildren(parentElement);
    }

    // The local environment is not persisted in a fileStore, we must 'fake' it to display it.
    private Object[] addLocalEnvironment(Object parentElement) {
        IFolder parent = (IFolder) parentElement;
        IFile localEnv = parent.getFile("Local.xml");
        List<Object> children = new ArrayList<>();
        children.add(localEnv);
        children.addAll(Arrays.asList(super.getChildren(parentElement)));
        return children.toArray();
    }

    private boolean isEnvironmentsFolder(IFolder parentElement) {
        return Objects.equals(parentElement.getName(), "environements");
    }
    
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        super.inputChanged(viewer, oldInput, newInput);
        this.viewer = (TreeViewer) viewer;
    }

    @Override
    protected void postAdd(Object parent, Object element, Collection<Runnable> runnables) {
        super.postAdd(parent, element, runnables);
        if (viewer.testFindItem(parent) == null
                && RepositoryManager.getInstance()
                        .getCurrentRepository()
                        .getAllStores()
                        .stream()
                        .map(IRepositoryStore::getResource)
                        .anyMatch(parent::equals)) {
            //Force refresh to update parent visibility in Project Explorer
            runnables.add(() -> viewer.refresh(true));
        }
    }

}
