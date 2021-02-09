/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connector.model.definition.dialog;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.repository.provider.ExtendedCategory;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author Romain Bioteau
 *
 */
public class DefinitionCategoryContentProvider implements ITreeContentProvider {

	private List<ExtendedCategory> categories = new ArrayList<>();

	public DefinitionCategoryContentProvider(){
		
	}
	
	public DefinitionCategoryContentProvider(List<ExtendedCategory> categories){
		this.categories = categories;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getElements(java.lang.Object)
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof List){
			categories  = (List<ExtendedCategory>) inputElement;
			List<Category> rootCategory = new ArrayList<>();
			for(Category c : categories){
				if(c.getParentCategoryId() == null || c.getParentCategoryId().isEmpty()){
					rootCategory.add(c);
				}
			}
			return rootCategory.toArray(new Category[rootCategory.size()]);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	@Override
	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof Category){
			String parentId = ((Category) parentElement).getId();
			List<Category> children = new ArrayList<>();
			for(Category c : categories){
				if(parentId.equals(c.getParentCategoryId())){
					children.add(c);
				}
			}
			return children.toArray(new Category[children.size()]);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
	 */
	@Override
	public Object getParent(Object element) {
		if(element instanceof Category){
			String parentId = ((Category) element).getParentCategoryId();
			if(parentId!= null){
				for(Category c : categories){
					if(parentId.equals(c.getId())){
						return c;
					}
				}
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
	 */
	@Override
	public boolean hasChildren(Object element) {
		Object[] children = getChildren(element);
		return children != null && children.length > 0;
	}

	public boolean isLeafCategory(ConnectorDefinition def,Category category){
		boolean isRoot = true;
		boolean hasChild = false;
		if(def.getCategory().size() == 1){
			return true;
		}
		if(category.getParentCategoryId() == null && def.getCategory().size() == 1){
			return true;
		}
		for(Category c : def.getCategory()){
			if(!c.getId().equals(category.getId())){
				if(category.getParentCategoryId() != null && category.getParentCategoryId().equals(c.getId())) {
					isRoot = false;
				}
				if(c.getParentCategoryId() != null && c.getParentCategoryId().equals(category.getId())) {
					hasChild = true;
				}
			}
		}
		return !isRoot && !hasChild;
	}
}
