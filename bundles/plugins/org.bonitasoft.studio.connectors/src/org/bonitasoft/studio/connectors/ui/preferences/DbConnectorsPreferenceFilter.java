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

package org.bonitasoft.studio.connectors.ui.preferences;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * @author Aurelie Zara
 *
 */
public class DbConnectorsPreferenceFilter extends ViewerFilter {

	DefinitionResourceProvider messageProvider ;
	private String searchString;
	
	public DbConnectorsPreferenceFilter(){
		ConnectorDefRepositoryStore connectorDefStore = (ConnectorDefRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
		messageProvider = DefinitionResourceProvider.getInstance(connectorDefStore, ConnectorPlugin.getDefault().getBundle());
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (searchString == null || searchString.length()==0){
			return true;
		}
		ConnectorDefinition def = (ConnectorDefinition)element;
		String defLabel = messageProvider.getConnectorDefinitionLabel(def);
		if (defLabel.matches(searchString)){
			return true;
		}
		return false;
	}
	
	 

	  public void setSearchText(String s) {
	    // Search must be a substring of the existing value
	    this.searchString = ".*" + s + ".*";
	  }

}
