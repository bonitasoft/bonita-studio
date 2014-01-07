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
package org.bonitasoft.studio.engine.store;

import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 *
 */
public class DeploymentConfigurationRepositoryStore extends AbstractRepositoryStore {

    private static final String STORE_NAME = "deployment" ;
    public static final String CONF_EXT = "properties" ;
    private static final Set<String> extensions = new HashSet<String>() ;
    static{
        extensions.add(CONF_EXT) ;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryStore#createRepositoryFileStore(java.lang.String)
     */
    @Override
    public IRepositoryFileStore createRepositoryFileStore(String fileName) {
        return new DeploymentConfigurationFileStore(fileName,this);
    }


    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryStore#getName()
     */
    @Override
    public String getName() {
        return STORE_NAME;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryStore#getDisplayName()
     */
    @Override
    public String getDisplayName() {
        return Messages.engineConfigurations;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryStore#getIcon()
     */
    @Override
    public Image getIcon() {
        return Pics.getImage("engine_conf.gif",EnginePlugin.getDefault());
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryStore#getCompatibleExtensions()
     */
    @Override
    public Set<String> getCompatibleExtensions() {
        return extensions;
    }

    @Override
    public List<IRepositoryFileStore> getChildren() {
        List<IRepositoryFileStore> result = super.getChildren() ;
        Enumeration<URL> profiles = EnginePlugin.getDefault().getBundle().findEntries(STORE_NAME, "*.*", false);
        if(	profiles != null ){
            while (profiles.hasMoreElements()) {
                URL url = profiles.nextElement();
                String[] segments = url.getFile().split("/") ;
                String fileName = segments[segments.length-1] ;
                if(fileName.lastIndexOf(".") != -1){
                    String extension = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()) ;
                    if(extensions.contains(extension)){
                        result.add(new URLDeploymentConfigurationFileStore(url,this)) ;
                    }
                }
            }
        }
        return result;
    }


    @Override
    public IRepositoryFileStore getChild(String fileName) {
        IRepositoryFileStore file =	super.getChild(fileName) ;
        if(file == null){
            URL url = EnginePlugin.getDefault().getBundle().getResource(STORE_NAME+ "/" +fileName);
            if(url != null){
                return new URLDeploymentConfigurationFileStore(url,this) ;
            }else{
                return null ;
            }
        }else{
            return file ;
        }
    }

}
