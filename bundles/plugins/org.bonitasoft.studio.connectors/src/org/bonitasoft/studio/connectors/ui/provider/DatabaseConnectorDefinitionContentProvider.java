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

import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class DatabaseConnectorDefinitionContentProvider extends ArrayContentProvider {


	private final ConnectorDefRepositoryStore connectorDefStore;
	private static final String DATASOURCE_CONNECTOR_DEF_ID = "database-datasource";
	
	public DatabaseConnectorDefinitionContentProvider(ConnectorDefRepositoryStore connectorDefStore) {
	      this.connectorDefStore = connectorDefStore;
	}

	@Override
	public Object[] getElements(Object element) {
		if (element instanceof Category) {
			Category cat = (Category) element;
			return connectorDefStore.getResourceProvider().getConnectorDefinitionRegistry().getDefinitions().stream()
			        .filter(def -> !DATASOURCE_CONNECTOR_DEF_ID.equals(def.getId()))
	                .filter(def -> def.getCategory().stream().map(Category::getId).anyMatch(cat.getId()::equals))
	                .toArray();
		}
		return new Object[0];
	}

	@Override
	public void dispose() {

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

	}


	



}
