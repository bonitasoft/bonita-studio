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
import org.bonitasoft.studio.common.repository.provider.FileStoreLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.ContainerCheckedTreeViewer;

/**
 * @author Baptiste Mesta
 */
public class CheckboxRepositoryTreeViewer extends ContainerCheckedTreeViewer {

    private class RepositoryTreeContentProvider implements ITreeContentProvider {

        private final WeakHashMap<IRepositoryStore, Object[]> cache = new WeakHashMap<IRepositoryStore, Object[]>();

        @Override
        public Object[] getChildren(final Object parentElement) {
            if (parentElement instanceof IRepositoryStore) {
                if (!cache.containsKey(parentElement)) {
                    final List<IRepositoryFileStore> result = new ArrayList<IRepositoryFileStore>();
                    for (final IRepositoryFileStore child : ((IRepositoryStore<IRepositoryFileStore>) parentElement).getChildren()) {
                        if (child != null && child.canBeExported()) {
                            result.add(child);
                        }
                    }
                    cache.put((IRepositoryStore) parentElement, result.toArray());
                }
                return cache.get(parentElement);

            }
            return null;
        }

        @Override
        public Object getParent(final Object element) {
            if (element instanceof IRepositoryFileStore) {
                return ((IRepositoryFileStore) element).getParentStore();
            }
            return null;
        }

        @Override
        public boolean hasChildren(final Object element) {
            if (element instanceof IRepositoryStore<?>) {
                return !((IRepositoryStore<?>) element).isEmpty();
            }
            return false;
        }

        @Override
        public Object[] getElements(final Object element) {
            if (element instanceof Collection<?>) {
                if (((Collection<?>) element).size() == 1) {
                    final IRepositoryStore store = (IRepositoryStore) ((Collection<?>) element).iterator().next();
                    return getChildren(store);
                } else {
                    return ((Collection<?>) element).toArray();
                }

            } else if (element instanceof Object[]) {
                return (Object[]) element;
            }
            return null;
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

    private RepositoryTreeContentProvider localContentProvider;

    /**
     * @param parent
     * @param style
     * @param showImages
     */
    public CheckboxRepositoryTreeViewer(final Composite parent, final int style) {
        super(parent, style | SWT.VIRTUAL);
        initialize();
    }

    /**
	 *
	 */
    private void initialize() {
        setLabelProvider(new FileStoreLabelProvider());
        localContentProvider = new RepositoryTreeContentProvider();
        setContentProvider(localContentProvider);
        addFilter(new ViewerFilter() {

            @Override
            public boolean select(final Viewer arg0, final Object parentElement, final Object element) {
                if (element instanceof IRepositoryStore) {
                    final ITreeContentProvider provider = (ITreeContentProvider) getContentProvider();
                    return provider.hasChildren(element);
                }
                return true;
            }
        });
    }

}
