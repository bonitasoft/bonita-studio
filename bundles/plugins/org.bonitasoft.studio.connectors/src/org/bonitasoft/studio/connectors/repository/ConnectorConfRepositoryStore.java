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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.repository.filestore.DefinitionConfigurationFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.util.ConnectorConfigurationAdapterFactory;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.swt.graphics.Image;


/**
 * @author Romain Bioteau
 *
 */
public class ConnectorConfRepositoryStore extends AbstractEMFRepositoryStore<DefinitionConfigurationFileStore> {

    private static final String STORE_NAME = "connectors-conf";
    private static final Set<String> extensions = new HashSet<String>() ;
    public static final String CONF_EXT = "connectorconfig";
    static{
        extensions.add(CONF_EXT) ;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#createRepositoryFileStore(java.lang.String)
     */
    @Override
    public DefinitionConfigurationFileStore createRepositoryFileStore(String fileName) {
        return new DefinitionConfigurationFileStore(fileName, this);
    }


    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getName()
     */
    @Override
    public String getName() {
        return STORE_NAME ;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getDisplayName()
     */
    @Override
    public String getDisplayName() {
        return Messages.connectorConfRepositoryName;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getIcon()
     */
    @Override
    public Image getIcon() {
        return Pics.getImage("conf.png",ConnectorPlugin.getDefault());
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getCompatibleExtensions()
     */
    @Override
    public Set<String> getCompatibleExtensions() {
        return extensions;
    }


    public List<ConnectorConfiguration> getFilterConfigurations() {
        List<ConnectorConfiguration> result = new ArrayList<ConnectorConfiguration>() ;
        for(IRepositoryFileStore child : getChildren()){
            result.add((ConnectorConfiguration) child.getContent()) ;
        }
        return result ;
    }

    public List<ConnectorConfiguration> getFilterConfigurationsFor(String defintionId,String definitionVersion) {
        List<ConnectorConfiguration> result = new ArrayList<ConnectorConfiguration>() ;
        for(ConnectorConfiguration child : getFilterConfigurations()){
            if(child.getDefinitionId().equals(defintionId) && child.getVersion().equals(definitionVersion) ){
                result.add(child) ;
            }
        }
        return result ;
    }


    @Override
    protected void addAdapterFactory(ComposedAdapterFactory adapterFactory) {
        adapterFactory.addAdapterFactory(new ConnectorConfigurationAdapterFactory());
    }

}
