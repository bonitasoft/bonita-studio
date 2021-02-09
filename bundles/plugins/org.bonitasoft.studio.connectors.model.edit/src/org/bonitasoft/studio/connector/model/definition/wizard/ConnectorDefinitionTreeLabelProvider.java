/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.connector.model.definition.wizard;

import org.bonitasoft.studio.common.repository.provider.ExtendedCategory;
import org.bonitasoft.studio.common.repository.provider.ExtendedConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.AbstractUniqueDefinitionContentProvider;
import org.bonitasoft.studio.connector.model.definition.UnloadableConnectorDefinition;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 */
public class ConnectorDefinitionTreeLabelProvider extends LabelProvider {

    @Override
    public String getText(Object element) {
        if (AbstractUniqueDefinitionContentProvider.ROOT.equals(element)) {
            return Messages.all;
        }
        if (element instanceof ExtendedConnectorDefinition) {
            return getLabelFor(element);
        } else if (element instanceof ExtendedCategory) {
            String label = ((ExtendedCategory) element).getLabel();
            if (label == null) {
                return ((ExtendedCategory) element).getId();
            }
            return label;
        }
        return super.getText(element);
    }

    protected String getLabelFor(Object element) {
        if(!(element instanceof ExtendedConnectorDefinition)){
            return null;
        }
        ExtendedConnectorDefinition def = (ExtendedConnectorDefinition) element;
        String desc = def.getConnectorDefinitionDescription();
        String connectorDefinitionLabel = def.getConnectorDefinitionLabel();
        if(connectorDefinitionLabel==null){
            connectorDefinitionLabel = def.getId();
        }
        String text = connectorDefinitionLabel
                + " (" + def.getVersion() + ")";
        if (desc != null && !desc.isEmpty()) {
            text = text + " -- " + desc;
        }
        return text;
    }

    @Override
    public Image getImage(Object element) {
        if (element instanceof UnloadableConnectorDefinition) {
            return Pics.getImage(PicsConstants.error);
        } else if (element instanceof ExtendedConnectorDefinition) {
            return ((ExtendedConnectorDefinition) element).getImage();
        } else if (element instanceof ExtendedCategory) {
            return ((ExtendedCategory)element).getImage();
        }
        return super.getImage(element);
    }

}
