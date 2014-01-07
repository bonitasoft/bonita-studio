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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.bonitasoft.studio.common.repository.filestore.PropertiesFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.preferences.EnginePreferenceConstants;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author Romain Bioteau
 *
 */
public class DeploymentConfigurationFileStore extends PropertiesFileStore {


	public static final String JAR_LIST = "jars";
	public static final String SEPARATOR = ",";

	public DeploymentConfigurationFileStore(String fileName,IRepositoryStore store) {
		super(fileName, store);
	}

	@Override
	public Image getIcon() {
		return Pics.getImage("engine_conf.gif",EnginePlugin.getDefault());
	}


	@Override
	protected IWorkbenchPart doOpen() {
		return null ;
	}

	@Override
	protected void doClose() {

	}

	/**
	 * @return
	 */
	public String getJaas() {
		return getContent().getProperty(EnginePreferenceConstants.REMOTE_DEPLOYMENT_JAAS_FILE);
	}

	/**
	 * @return
	 */
	public String getFactory() {
		return getContent().getProperty(EnginePreferenceConstants.REMOTE_DEPLOYMENT_FACTORY);
	}

	/**
	 * @return
	 */
	public String getUrl() {
		return getContent().getProperty(EnginePreferenceConstants.REMOTE_DEPLOYMENT_URL);
	}


	/**
	 * @return
	 */
	public List<String> getClientJars() {
		String list = (String)((Properties)getContent()).get(JAR_LIST) ;
		if(list == null || list.isEmpty()){
			return new ArrayList<String>() ;
		}
		String[] jars = list.split(SEPARATOR) ;
		return new ArrayList<String>(Arrays.asList(jars));
	}

	/**
	 * @return
	 */
	public String getDeploymentMode() {
		return getContent().getProperty(EnginePreferenceConstants.REMOTE_DEPLOYMENT_CHOICE);
	}

	public String getRESTServer() {
		return getContent().getProperty(EnginePreferenceConstants.REST_SERVER_ADDRESS_PROPERTY);
	}

	public void setJarList(List<String> jars) {
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

}
