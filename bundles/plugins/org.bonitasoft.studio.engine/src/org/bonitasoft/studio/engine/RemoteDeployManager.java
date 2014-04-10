/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
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
package org.bonitasoft.studio.engine;

import org.bonitasoft.engine.bpm.bar.BusinessArchive;
import org.bonitasoft.studio.engine.preferences.EnginePreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;


/**
 * @author Mickael Istria
 *
 */
public class RemoteDeployManager {

	/**
	 * @return
	 */
	public static boolean isDeploymentRemote() {
		return !getStore().getString(EnginePreferenceConstants.REMOTE_DEPLOYMENT_CHOICE).equals(EnginePreferenceConstants.STANDARD_MODE);
	}


	private static IPreferenceStore getStore() {
		return EnginePlugin.getDefault().getPreferenceStore();
	}


	public static ClassLoader createClassLoader() {
		//DeploymentConfigurationArtifact artifact = findCurrentArtifact();
//		if (artifact != null) {
//			List<URL> urls = new ArrayList<URL>();
//			for (IFile file : artifact.getClientJars()) {
//				try {
//					urls.add(file.getLocationURI().toURL());
//				} catch (Exception ex) {
//					BonitaStudioLog.log(ex);
//				}
//			}
//			return new NonLockingJarFileClassLoader("RemoteDeployClassLoader",urls.toArray(new URL[] {}), BusinessArchive.class.getClassLoader());
//		} else {
//			
//		}
		return BusinessArchive.class.getClassLoader();
	}


//	private static DeploymentConfigurationArtifact findCurrentArtifact() {
//		String artifactId = getStore().getString(RemoteDeployConstants.CURRENT_CONFIG);
//		if (artifactId == null || artifactId.length() < 0) {
//			return null;
//		} else {
//			return DeploymentConfigurationRepository.getInstance().getArtifact(artifactId);
//		}
//	}

	/**
	 * @return
	 */
	public static String getAPIType() {
		return getStore().getString(EnginePreferenceConstants.REMOTE_DEPLOYMENT_CHOICE);
	}

	public static boolean useEJB() {
		return getStore().getString(EnginePreferenceConstants.REMOTE_DEPLOYMENT_CHOICE).equals(EnginePreferenceConstants.EJB2_MODE) 
			|| getStore().getString(EnginePreferenceConstants.REMOTE_DEPLOYMENT_CHOICE).equals(EnginePreferenceConstants.EJB3_MODE) ;
	}

}
