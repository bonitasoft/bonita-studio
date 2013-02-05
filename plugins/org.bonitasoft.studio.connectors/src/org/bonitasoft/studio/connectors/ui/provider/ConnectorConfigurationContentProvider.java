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

package org.bonitasoft.studio.connectors.ui.provider;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.definition.AbstractDefinitionRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.repository.ConnectorConfRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.osgi.framework.Bundle;

/**
 * @author Aurelie Zara
 *
 */
public class ConnectorConfigurationContentProvider implements
		ITreeContentProvider {

	
	private final List<ConnectorDefinition> connectorDefList;
	 private final DefinitionResourceProvider messageProvider;
	 private final ConnectorConfRepositoryStore connectorConfStore;
	
	public ConnectorConfigurationContentProvider(){
		 final AbstractDefinitionRepositoryStore<?> connectorDefStore = (AbstractDefinitionRepositoryStore<?>) RepositoryManager
	                .getInstance().getRepositoryStore(
	                		ConnectorDefRepositoryStore.class);
	        connectorDefList = connectorDefStore.getDefinitions();
	        connectorConfStore = (ConnectorConfRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(ConnectorConfRepositoryStore.class);
	    
	     final Bundle bundle = getBundle();
	        messageProvider = DefinitionResourceProvider.getInstance(
	                connectorDefStore, bundle);

	}
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	@Override
	public void dispose() {
		

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		  

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getElements(java.lang.Object)
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		List<Category> categories = messageProvider.getAllCategories();
        return categories.toArray();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	@Override
	public Object[] getChildren(Object parentElement) {
		final List<Object> result = new ArrayList<Object>();
		if (parentElement instanceof Category){
			Category category = (Category) parentElement;
			for (ConnectorDefinition def: connectorDefList){
				for (Category cat : def.getCategory()){
					if (cat.getId().equals(category.getId())){
						result.add(def);
					}
				}
			}
			return result.toArray();
		} else {
			if (parentElement instanceof ConnectorDefinition){
				ConnectorDefinition connectorDef = (ConnectorDefinition)parentElement;
				result.addAll(connectorConfStore.getFilterConfigurationsFor(connectorDef.getId(), connectorDef.getVersion()));
				return result.toArray();
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
	 */
	@Override
	public Object getParent(Object element) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
	 */
	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof Category){
			return true;
		}
		if (element instanceof ConnectorDefinition){
			Object[] children = getChildren(element);
			if (children!=null && children.length>0){
				return true;
			}
		}
		return false;
			
	}
	
	
	protected Bundle getBundle() {
		return ConnectorPlugin.getDefault().getBundle();
	}

	
	protected Class<?> getDefStoreClass() {
		return ConnectorDefRepositoryStore.class;
	}

}
