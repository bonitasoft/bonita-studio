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

import org.bonitasoft.studio.connector.model.definition.IDefinitionRepositoryStore;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;


/**
 * @author Romain Bioteau
 *
 */
public class ConnectorConfiguraitonLabelProvider extends LabelProvider {

    private final IDefinitionRepositoryStore store;

    public ConnectorConfiguraitonLabelProvider(IDefinitionRepositoryStore store) {
        this.store = store;
    }

    @Override
    public String getText(Object selection) {
        if (selection instanceof ConnectorConfiguration) {
            final ConnectorConfiguration config = (ConnectorConfiguration) selection;
            return config.getName();
        }
        if (selection instanceof ConnectorParameter) {
            final ConnectorParameter param = (ConnectorParameter) selection;
            if(param.getExpression() instanceof Expression){
                final String value = ((Expression)param.getExpression()).getContent() ;
                return param.getKey() + "=" + value;
            }else{
                return param.getKey() ;
            }

        }
        return selection.toString();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(Object element) {
        if (element instanceof ConnectorConfiguration) {
            return store.getResourceProvider().getDefinitionIcon(
                    store.getDefinition(((ConnectorConfiguration) element).getDefinitionId(), ((ConnectorConfiguration) element).getVersion()));
        }
        if(element instanceof ConnectorParameter) {
            return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
        }
        return super.getImage(element);
    }

}
