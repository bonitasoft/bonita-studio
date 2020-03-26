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
package org.bonitasoft.studio.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.extension.IConfigurationIdProvider;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

/**
 * @author Romain Bioteau
 *
 */
public class ConfigurationIdProvider {

	private static final String CONF_ID_PROVIDER = "org.bonitasoft.studio.common.configurationIdProvider";
	protected static final String PRIORITY = "priority";
	private static final String CLASS = "configurationIdProvider";
	private static IConfigurationIdProvider configurationIdProvider;
	private static IConfigurationIdProvider bosConfigurationIdProvider;
	
	public static IConfigurationIdProvider getConfigurationIdProvider(){
		if(configurationIdProvider == null){
			List<IConfigurationElement> configurationProviders = new ArrayList<IConfigurationElement>();
			for(IConfigurationElement element : BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(CONF_ID_PROVIDER)){
				configurationProviders.add(element);
			}
			Assert.isTrue(!configurationProviders.isEmpty());
			Collections.sort(configurationProviders, new Comparator<IConfigurationElement>() {

				@Override
				public int compare(IConfigurationElement e1, IConfigurationElement e2) {
					int	p1 = 0;
					int p2 = 0 ;
					try{
						p1 = Integer.parseInt(e1.getAttribute(PRIORITY));
					}catch (NumberFormatException e) {
						p1 = 0 ;
					}
					try{
						p2 = Integer.parseInt(e2.getAttribute(PRIORITY));
					}catch (NumberFormatException e) {
						p2 = 0 ;
					}
					return  p2 - p1; //Highest Priority first
				}

			}) ;
			try {
				configurationIdProvider = (IConfigurationIdProvider) configurationProviders.get(0).createExecutableExtension(CLASS);
			} catch (CoreException e) {
				BonitaStudioLog.error(e,Activator.PLUGIN_ID);
			}
		}
		return configurationIdProvider;
	}

	public static IConfigurationIdProvider getBosConfigurationIdProvider() {
		if(bosConfigurationIdProvider == null){
			List<IConfigurationElement> configurationProviders = new ArrayList<IConfigurationElement>();
			for(IConfigurationElement element : BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(CONF_ID_PROVIDER)){
				configurationProviders.add(element);
			}
			Assert.isTrue(!configurationProviders.isEmpty());
			Collections.sort(configurationProviders, new Comparator<IConfigurationElement>() {

				@Override
				public int compare(IConfigurationElement e1, IConfigurationElement e2) {
					int	p1 = 0;
					int p2 = 0 ;
					try{
						p1 = Integer.parseInt(e1.getAttribute(PRIORITY));
					}catch (NumberFormatException e) {
						p1 = 0 ;
					}
					try{
						p2 = Integer.parseInt(e2.getAttribute(PRIORITY));
					}catch (NumberFormatException e) {
						p2 = 0 ;
					}
					return  p1-p2 ; //Lowest Priority first
				}

			}) ;
			try {
				bosConfigurationIdProvider = (IConfigurationIdProvider) configurationProviders.get(0).createExecutableExtension(CLASS);
			} catch (CoreException e) {
				BonitaStudioLog.error(e,Activator.PLUGIN_ID);
			}
		}
		return bosConfigurationIdProvider;
	}
}
