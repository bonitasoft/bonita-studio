/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
 * 
 */
public class CheckboxRepositoryTreeViewer extends ContainerCheckedTreeViewer {

	private class RepositoryTreeContentProvider implements ITreeContentProvider {

		private WeakHashMap<IRepositoryStore, Object[]> cache = new WeakHashMap<IRepositoryStore, Object[]>();

		@Override
		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof IRepositoryStore) {
				if(!cache.containsKey(parentElement)){
					List<IRepositoryFileStore> result = new ArrayList<IRepositoryFileStore>() ;
					for(IRepositoryFileStore child : ((IRepositoryStore<IRepositoryFileStore>) parentElement).getChildren()){
						if(child!= null && child.canBeExported()){
							result.add(child) ;
						}
					}
					cache.put((IRepositoryStore) parentElement, result.toArray());
				}
				return cache.get(parentElement);

			}
			return null;
		}

		@Override
		public Object getParent(Object element) {
			if (element instanceof IRepositoryFileStore) {
				return ((IRepositoryFileStore) element).getParentStore();
			}
			return null;
		}

		@Override
		public boolean hasChildren(Object element) {
			if (element instanceof IRepositoryStore) {
				Object[] children =  getChildren(element);
				return children != null && children.length > 0  ;
			}
			return false;
		}

		@Override
		public Object[] getElements(Object element) {
			if (element instanceof Collection<?>) {
				if(((Collection<?>) element).size() == 1){
					IRepositoryStore store = (IRepositoryStore) ((Collection<?>) element).iterator().next() ;
					return getChildren(store);

				}else{
					return ((Collection<?>) element).toArray();
				}

			} else if (element instanceof Object[]) {
				return (Object[]) element;
			}
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
		 */
		@Override
		public void dispose() {
			// TODO Auto-generated method stub

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse
		 * .jface.viewers.Viewer, java.lang.Object, java.lang.Object)
		 */
		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

		}

	}

	private RepositoryTreeContentProvider localContentProvider;

	/**
	 * @param parent
	 * @param style
	 * @param showImages
	 *            TODO
	 */
	public CheckboxRepositoryTreeViewer(Composite parent, int style) {
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
			public boolean select(Viewer arg0, Object parentElement, Object element) {
				if(element instanceof IRepositoryStore){
					ITreeContentProvider provider = (ITreeContentProvider) getContentProvider() ;
					return provider.hasChildren(element) ;
				}
				return true;
			}
		}) ;
	}

}
