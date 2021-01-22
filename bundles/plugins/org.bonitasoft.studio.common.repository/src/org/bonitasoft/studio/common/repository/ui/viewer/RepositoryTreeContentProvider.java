/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.repository.ui.viewer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.WeakHashMap;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class RepositoryTreeContentProvider implements ITreeContentProvider {

    private final WeakHashMap<IRepositoryStore<?>, Object[]> cache = new WeakHashMap<IRepositoryStore<?>, Object[]>();

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang
     * .Object)
     */
    @Override
    public Object[] getChildren(final Object parentElement) {
        if (parentElement instanceof IRepositoryStore) {
            if (!cache.containsKey(parentElement)) {
                final List<IRepositoryFileStore<?>> result = new ArrayList<>();
                for (final IRepositoryFileStore<?> child : ((IRepositoryStore<?>) parentElement).getChildren()) {
                    if (child != null && child.canBeExported()) {
                        result.add(child);
                    }
                }
                cache.put((IRepositoryStore<?>) parentElement, result.toArray());
            }
            return cache.get(parentElement);

        }
        return new Object[0];
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang
     * .Object)
     */
    @Override
    public Object getParent(final Object element) {
        if (element instanceof IRepositoryFileStore) {
            return ((IRepositoryFileStore<?>) element).getParentStore();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang
     * .Object)
     */
    @Override
    public boolean hasChildren(final Object element) {
        if (element instanceof IRepositoryStore<?>) {
            return !((IRepositoryStore<?>) element).isEmpty();
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.viewers.IStructuredContentProvider#getElements(
     * java.lang.Object)
     */
    @Override
    public Object[] getElements(final Object element) {
        if (element instanceof Collection<?>) {
            if (((Collection<?>) element).size() == 1) {
                final IRepositoryStore<?> store = (IRepositoryStore<?>) ((Collection<?>) element).iterator().next();
                return getChildren(store);
            } else {
                return ((Collection<?>) element).toArray();
            }
        }
        return new Object[0];
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    @Override
    public void dispose() {
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse
     * .jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    @Override
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
    }

}
