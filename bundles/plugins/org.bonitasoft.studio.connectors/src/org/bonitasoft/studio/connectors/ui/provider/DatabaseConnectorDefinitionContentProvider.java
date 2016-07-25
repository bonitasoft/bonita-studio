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
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.osgi.framework.Bundle;

/**
 * @author Aurelie Zara
 *
 */
public class DatabaseConnectorDefinitionContentProvider extends ArrayContentProvider{


	private final List<ConnectorDefinition> connectorDefList;
	private static final String DATASOURCE_CONNECTOR_D = "database-datasource";
	
	public DatabaseConnectorDefinitionContentProvider() {
		ConnectorDefRepositoryStore connectorDefStore = (ConnectorDefRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class) ;
		connectorDefList = connectorDefStore.getDefinitions();
	}


	protected Bundle getBundle() {
		return ConnectorPlugin.getDefault().getBundle();
	}


	protected Class<?> getDefStoreClass() {
		return ConnectorDefRepositoryStore.class;
	}


	@Override
	public Object[] getElements(Object element) {
		List<ConnectorDefinition> result = new ArrayList<ConnectorDefinition>();
		if (element instanceof Category) {
			Category cat = (Category) element;

			for (ConnectorDefinition def : connectorDefList) {
				for(Category category : def.getCategory()){
					if (category.getId().equals(cat.getId()) && !def.getId().equals(DATASOURCE_CONNECTOR_D)){
						result.add(def);
					}
				}
			}
		}
		return result.toArray();
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


	



}
