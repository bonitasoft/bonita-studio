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
package org.bonitasoft.studio.connectors.repository;

import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.AbstractDefFileStore;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;


/**
 * @author Romain Bioteau
 * @author Baptiste Mesta
 *
 */
public class ConnectorDefFileStore extends AbstractDefFileStore {

    public ConnectorDefFileStore(String fileName, AbstractEMFRepositoryStore<ConnectorDefFileStore> store) {
        super(fileName, store);
    }

    @Override
    protected Bundle getBundle() {
        return ConnectorPlugin.getDefault().getBundle();
    }

    @Override
    public String getDisplayName() {
        ConnectorDefRepositoryStore store = (ConnectorDefRepositoryStore) getParentStore();
        ConnectorDefinition def = getContent();
        if(def != null){
            String defName = store.getResourceProvider().getConnectorDefinitionLabel(def);
            if(defName == null){
                defName = def.getId();
            }
            return defName +" ("+def.getVersion()+")";
        }
        return super.getDisplayName();
    }

    @Override
    public Image getIcon() {
        ConnectorDefRepositoryStore store = (ConnectorDefRepositoryStore) getParentStore();
        ConnectorDefinition def = getContent();
        if(def != null){
            return store.getResourceProvider().getDefinitionIcon(def);
        }
        return null;
    }


    
}
