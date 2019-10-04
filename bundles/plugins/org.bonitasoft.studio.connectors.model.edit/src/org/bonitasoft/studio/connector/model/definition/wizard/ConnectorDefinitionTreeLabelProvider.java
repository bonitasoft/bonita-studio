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

import org.bonitasoft.studio.connector.model.definition.AbstractUniqueDefinitionContentProvider;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.UnloadableConnectorDefinition;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 * 
 */
public class ConnectorDefinitionTreeLabelProvider extends LabelProvider {

    protected final DefinitionResourceProvider messageProvider;

    public ConnectorDefinitionTreeLabelProvider(
            DefinitionResourceProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    @Override
    public String getText(Object element) {
    	if(AbstractUniqueDefinitionContentProvider.ROOT.equals(element)){
    		return Messages.all;
    	}
        if (element instanceof ConnectorDefinition) {
            return getLabelFor(element);
        } else if (element instanceof Category) {
            return messageProvider.getCategoryLabel((Category) element);
        }
        return super.getText(element);
    }

    protected String getLabelFor(Object element) {
        String desc = messageProvider
                .getConnectorDefinitionDescription((ConnectorDefinition) element);
        String connectorDefinitionLabel = messageProvider
                .getConnectorDefinitionLabel((ConnectorDefinition) element);
        if(connectorDefinitionLabel==null){
            connectorDefinitionLabel = ((ConnectorDefinition) element).getId();
        }
        String text = connectorDefinitionLabel
                + " (" + ((ConnectorDefinition) element).getVersion() + ")";
        if (desc != null && !desc.isEmpty()) {
            text = text + " -- " + desc;
        }
        return text;
    }

    @Override
    public Image getImage(Object element) {
        if (element instanceof UnloadableConnectorDefinition) {
            return Pics.getImage(PicsConstants.error);
        } else if (element instanceof ConnectorDefinition) {
            return messageProvider
                    .getDefinitionIcon((ConnectorDefinition) element);
        } else if (element instanceof Category) {
            return messageProvider.getCategoryIcon((Category) element);
        }
        return super.getImage(element);
    }

}
