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
package org.bonitasoft.studio.connector.model.definition.wizard;

import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;

/**
 * @author Romain Bioteau
 * 
 */
public class ConnectorDefinitionExplorerLabelProvider extends ConnectorDefinitionTreeLabelProvider {

   

    public ConnectorDefinitionExplorerLabelProvider(
			DefinitionResourceProvider messageProvider) {
		super(messageProvider);
	}

	protected String getLabelFor(Object element) {
        String connectorDefinitionLabel = messageProvider
                .getConnectorDefinitionLabel((ConnectorDefinition) element);
        if(connectorDefinitionLabel==null){
            connectorDefinitionLabel = ((ConnectorDefinition) element).getId();
        }
        return connectorDefinitionLabel;
    }

    

}
