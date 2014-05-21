/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.bonitasoft.studio.common.repository.filestore.PropertiesFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author Aurelie Zara
 *
 */
public class DatabaseConnectorPropertiesFileStore extends PropertiesFileStore {
	public final String DEFAULT="default";
	public final String JAR_LIST="jars";
	public final String SEPARATOR=";";
	public final String AUTO_Add_DRIVERS = "auto";

	/**
	 * @param fileName
	 * @param store
	 */
	public DatabaseConnectorPropertiesFileStore(String fileName,
			IRepositoryStore<DatabaseConnectorPropertiesFileStore> store) {
		super(fileName, store);
		
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.repository.model.IRepositoryFileStore#getIcon()
	 */
	@Override
	public Image getIcon() {
		return Pics.getImage("databases_driver.png",ConnectorPlugin.getDefault());
	}
	
	public void setAutoAddDriver(Boolean b){
		Properties properties = getContent();
		properties.put(AUTO_Add_DRIVERS, b.toString());
		save(properties);
	}
	
	public boolean getAutoAddDriver(){
		Boolean b = Boolean.valueOf((String)((Properties)getContent()).get(AUTO_Add_DRIVERS));
		return b;
	}
	
	public void setDefault(String jarName){
		 Properties properties = getContent();
		 if(jarName == null){
			 properties.remove(DEFAULT);
		 }else{
			 properties.put(DEFAULT, jarName);
		 }
		
		 save(properties);
	}
	
	public String getDefault(){
		String jarName =  (String)((Properties)getContent()).get(DEFAULT);
		return jarName;
	}
	
	
	public List<String> getJarList(){
		String list = (String)((Properties)getContent()).get(JAR_LIST) ;
		if(list == null || list.isEmpty()){
			return new ArrayList<String>() ;
		}
		String[] jars = list.split(SEPARATOR) ;
		return new ArrayList<String>(Arrays.asList(jars));
	}
	
	
	public void setJarList(List<String> jars){
		Properties properties = (Properties) getContent() ;
		StringBuilder sb = new StringBuilder() ;
		if(!jars.isEmpty()){
			for(String id : jars){
				sb.append(id) ;
				sb.append(SEPARATOR) ;
			}
			sb.delete( sb.length()-1, sb.length()) ;
		}
		properties.put(JAR_LIST, sb.toString()) ;
		save(properties) ;
	}
	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doOpen()
	 */
	@Override
	protected IWorkbenchPart doOpen() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doClose()
	 */
	@Override
	protected void doClose() {

	}


}
