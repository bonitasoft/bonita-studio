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
package org.bonitasoft.studio.common.repository;

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.filestore.IEMFOptionProvider;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

/**
 * @author Romain Bioteau
 *
 */
public class BosResourceOptionsProvider {

	private static final String ADVANCED_LOAD_OPTIONS = "org.bonitasoft.studio.common.repository.emfOptionProvider";
	private final static IConfigurationElement[] providers = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(ADVANCED_LOAD_OPTIONS);
	
	public static Map<String,Object> getLoadOptions(String productVersion) {
		Map<String,Object> options = new HashMap<String, Object>();
		for(IConfigurationElement elem : providers){
			try {
				final IEMFOptionProvider provider = (IEMFOptionProvider) elem.createExecutableExtension("emfOptionProvider");
				options.putAll(provider.getLoadOptions(productVersion));
			} catch (CoreException e) {
				BonitaStudioLog.error(e, CommonRepositoryPlugin.PLUGIN_ID);
			}
		}
    	return options;
	}

	public static Map<String,Object> getSaveOptions() {
		Map<String,Object> options = new HashMap<String, Object>();
		for(IConfigurationElement elem : providers){
			try {
				final IEMFOptionProvider provider = (IEMFOptionProvider) elem.createExecutableExtension("emfOptionProvider");
				options.putAll(provider.getSaveOptions());
			} catch (CoreException e) {
				BonitaStudioLog.error(e, CommonRepositoryPlugin.PLUGIN_ID);
			}
		}
		return options;
	}

}
