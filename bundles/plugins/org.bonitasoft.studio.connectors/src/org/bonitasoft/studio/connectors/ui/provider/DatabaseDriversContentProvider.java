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
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesFileStore;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesRepositoryStore;
import org.eclipse.jface.viewers.ArrayContentProvider;

/**
 * @author Aurelie Zara
 *
 */
public class DatabaseDriversContentProvider extends ArrayContentProvider {
	
	
	private DatabaseConnectorPropertiesRepositoryStore store;
	
	public DatabaseDriversContentProvider(){
		store = (DatabaseConnectorPropertiesRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DatabaseConnectorPropertiesRepositoryStore.class) ;
	}

	@Override
	public Object[] getElements(Object element) {
		List<String> jars = new ArrayList<String>();
		if (element instanceof String){
			String currentConnector = (String)element;
			DatabaseConnectorPropertiesFileStore properties=(DatabaseConnectorPropertiesFileStore)store.getChild(getDBPrefFilename(currentConnector), true);
			if (properties!=null){
				jars.addAll(properties.getJarList());
			}
		}
		return jars.toArray();
	}
	
	 
	protected String getDBPrefFilename(String connectorId) {
		return connectorId+"."+DatabaseConnectorPropertiesRepositoryStore.CONF_EXT;
	}

}
