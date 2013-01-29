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

import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.filestore.PropertiesFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.resources.IResource;
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
		// TODO Auto-generated method stub
		return null;
	}

	public void setDefault(String jarName){
		 Properties properties = getContent();
		 properties.put(DEFAULT, jarName);
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
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doClose()
	 */
	@Override
	protected void doClose() {
		// TODO Auto-generated method stub
		
	}


}
