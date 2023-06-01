/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connectors.ui.provider;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * @author aurelie zara
 *
 */
public class DabaBaseConnectorDefinitionLabelProvider extends LabelProvider {

	private final DefinitionResourceProvider messageProvider;
	private final String DATABASE = "database";

	public DabaBaseConnectorDefinitionLabelProvider() {
		super();
		ConnectorDefRepositoryStore connectorDefStore = (ConnectorDefRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class) ;
		messageProvider = DefinitionResourceProvider.getInstance(connectorDefStore, ConnectorPlugin.getDefault().getBundle()) ;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof ConnectorDefinition) {
			return getLabelFor(element);

		}
		return null;
	}

	private String getLabelFor(Object element) {
		String connectorDefinitionLabel = messageProvider
				.getConnectorDefinitionLabel((ConnectorDefinition) element);
		if(connectorDefinitionLabel==null){
			connectorDefinitionLabel = ((ConnectorDefinition) element).getId();
		}
		String text = connectorDefinitionLabel
				+ " (" + ((ConnectorDefinition) element).getVersion() + ")";
		return text;
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof ConnectorDefinition) {
			ConnectorDefinition connector = (ConnectorDefinition)element;
			return messageProvider.getDefinitionIcon(connector);
		}

		return null;
	}

}
