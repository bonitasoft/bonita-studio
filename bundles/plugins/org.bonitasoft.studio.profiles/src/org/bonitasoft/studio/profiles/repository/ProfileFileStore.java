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
package org.bonitasoft.studio.profiles.repository;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.bonitasoft.studio.common.repository.filestore.PropertiesFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.profiles.ProfilePlugin;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author Romain Bioteau
 *
 */
public class ProfileFileStore extends PropertiesFileStore {

    private static final String FEATURES_SET = "features";
    private static final String SEPARATOR = ",";
    private static final String DESCRIPTION = "description";
    private static final String PROVIDED = "provided";

    public ProfileFileStore(String fileName, IRepositoryStore<?> store) {
        super(fileName, store);
    }


    @Override
    protected Properties load(InputStream is) {
        Properties properties = super.load(is);
        if(properties.get(PROVIDED) == null){
            properties.put(PROVIDED, "false") ;
        }
        return properties ;
    }

    @Override
    public Image getIcon() {
        return Pics.getImage("profile.png",ProfilePlugin.getDefault());
    }

    public Set<String> getFeatures(){
        String list = (String)getContent().get(FEATURES_SET) ;
        String[] categorIds = list != null ? list.split(SEPARATOR) : new String[]{};
        return new HashSet<String>(Arrays.asList(categorIds));
    }

    public boolean isProvided() {
        return Boolean.valueOf((String) getContent().get(PROVIDED)) ;
    }

    public String getProfileDescription() {
        return (String) getContent().get(DESCRIPTION);
    }

    /**
     * Util method to store enabled features
     * @param featureList
     * @throws Exception
     */
    public void saveFeatures(Set<String> featureList) {
        Properties properties = getContent() ;
        StringBuilder sb = new StringBuilder() ;
        for(String id : featureList){
            sb.append(id) ;
            sb.append(SEPARATOR) ;
        }
        if(sb.length() != 0){
        	sb.delete( sb.length()-1, sb.length()) ;
        }
        properties.put(FEATURES_SET, sb.toString()) ;
        save(properties) ;
    }

    public void saveDescription(String description) {
        Properties properties = getContent() ;
        properties.put(DESCRIPTION, description) ;
        save(properties) ;
    }

    public void setProvided(boolean isProvided) {
        Properties properties = getContent() ;
        properties.put(PROVIDED,String.valueOf(isProvided));
    }

    @Override
    protected IWorkbenchPart doOpen() {
        return null ;
    }

    @Override
    protected void doClose() {

    }


}
