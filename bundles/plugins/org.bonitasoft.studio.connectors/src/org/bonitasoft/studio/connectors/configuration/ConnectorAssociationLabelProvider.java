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
package org.bonitasoft.studio.connectors.configuration;

import org.bonitasoft.studio.common.repository.model.IDefinitionRepositoryStore;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.model.configuration.DefinitionMapping;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Image;


/**
 * @author Romain Bioteau
 *
 */
public class ConnectorAssociationLabelProvider extends ColumnLabelProvider {

    private final DefinitionResourceProvider resourceProvider;
    private final IDefinitionRepositoryStore defStore;

    public ConnectorAssociationLabelProvider(DefinitionResourceProvider resourceProvider,IDefinitionRepositoryStore store){
        this.resourceProvider = resourceProvider ;
        defStore = store ;
    }

    public String getDefinitionText(DefinitionMapping association){
        ConnectorDefinition definition = defStore.getDefinition(association.getDefinitionId(), association.getDefinitionVersion()) ;
        if(definition != null){
        String connectorDefinitionLabel = resourceProvider.getConnectorDefinitionLabel(definition);
        if(connectorDefinitionLabel == null){
            connectorDefinitionLabel = definition.getId();
        }
        return connectorDefinitionLabel + " ("+definition.getVersion()+")";
        } else {
        	return "defintion not found for "+ association.getDefinitionId() + " with version" + association.getDefinitionVersion();
        }
    }

    public Image getDefinitionImage(DefinitionMapping association){
        ConnectorDefinition definition = defStore.getDefinition(association.getDefinitionId(), association.getDefinitionVersion()) ;
        return resourceProvider.getDefinitionIcon(definition) ;
    }

    public String getImplementationText(DefinitionMapping association){
        return association.getImplementationId() + " ("+association.getImplementationVersion()+")";
    }

    public String getImplementationId(String implementationLabel){
        return implementationLabel.substring(implementationLabel.indexOf(" ("));
    }

    public String getImplementationVersion(String implementationLabel){
        return implementationLabel.substring(implementationLabel.indexOf(" (")+2,implementationLabel.lastIndexOf(")"));
    }


}
