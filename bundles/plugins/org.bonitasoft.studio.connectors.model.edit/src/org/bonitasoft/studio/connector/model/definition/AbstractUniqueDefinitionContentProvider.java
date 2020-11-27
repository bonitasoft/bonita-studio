/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connector.model.definition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.repository.Messages;

/**
 * @author Romain Bioteau
 * 
 */
public abstract class AbstractUniqueDefinitionContentProvider extends AbstractDefinitionContentProvider {

	public static final Object ROOT = new Object();
	
	public AbstractUniqueDefinitionContentProvider(boolean userDefinitionOnly) {
		super(userDefinitionOnly);
	}
	
	public AbstractUniqueDefinitionContentProvider() {
		super();
	}
	
	@Override
	public Object[] getElements(Object element) {
		return new Object[]{ROOT};
	}

	@Override
	public boolean hasChildren(Object element) {
		if(ROOT.equals(element)){
			return true;
		}
		return super.hasChildren(element);
	}

	@Override
	public Object[] getChildren(Object element) {
		if(ROOT.equals(element)){
			return super.getElements(ROOT);
		}else if (element instanceof Category) {
			Category cat = (Category) element;
			List<Object> result = new ArrayList<Object>();
			Set<String> addedId = new HashSet<String>();
			String parentId = cat.getId();
			for(Category c : messageProvider.getAllCategories()){
				if(parentId.equals(c.getParentCategoryId())){
					result.add(c);
				}
			}
			for (ConnectorDefinition def : connectorDefList) {
				if (def instanceof UnloadableConnectorDefinition) {
					if(cat.getId().equals(unloadableCategoryName)){
						if(!addedId.contains(def.getId())){
							addedId.add(def.getId());
							result.add(def);
						}
					}
				} else {
					if (def.getCategory().isEmpty()
							&& cat.getId().equals(Messages.uncategorized)) {//FIXME category id is nls string????
						if(!addedId.contains(def.getId())){
							result.add(def);
							addedId.add(def.getId());
						}
					}
					for (Category c : def.getCategory()) {
						if (c.getId().equals(((Category) element).getId())) {
							if(definitionCategoryContentProvider.isLeafCategory(def,c)){
								if(!addedId.contains(def.getId())){
									result.add(def);
									addedId.add(def.getId());
								}
							}else if(def.getCategory().size() == 1){
								if(!addedId.contains(def.getId())){
									result.add(def);
									addedId.add(def.getId());
								}
							}
						}
					}
				}
			}
			return result.toArray();
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		if(element instanceof Category){
			Object parent = super.getParent(element);
			if(parent == null){
				return ROOT;
			}
			return parent;
		}
		return super.getParent(element);
	}
	
}
