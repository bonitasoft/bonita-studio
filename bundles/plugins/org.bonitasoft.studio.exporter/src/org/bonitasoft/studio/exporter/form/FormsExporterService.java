/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.exporter.form;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.exporter.Activator;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gmf.runtime.common.core.service.ProviderPriority;



/**
 * The service which will be give the correct FormsExporter to p
 * 
 * @author Aurelien Pupier
 *
 */
public class FormsExporterService{
	
	private static final String CLASS = "class";

	/**
	 * The name of the 'name' XML attribute.
	 */
	private static final String A_NAME = "name"; //$NON-NLS-1$

	/**
	 * The name of the 'Priority' XML element.
	 */
	private static final String E_PRIORITY = "Priority"; //$NON-NLS-1$
	
	private final static FormsExporterService INSTANCE = new FormsExporterService();
	
	private FormsExporter formsExporter = null;
	
	public static FormsExporterService getInstance(){
		return INSTANCE;
	}
	
	public FormsExporter getFormsExporter(){
		if(formsExporter == null){
			initFormsExporter();
		}
		return formsExporter;
	}

	private void initFormsExporter() {
		IConfigurationElement[] cfgElts = Platform.getExtensionRegistry()
		.getExtensionPoint(Activator.PLUGIN_ID, "formsExporter")
		.getConfigurationElements();
		int maxPriority = 0;
		for(IConfigurationElement cfgElt : cfgElts){
			int newPriority = ProviderPriority.parse(getPriority(cfgElt)).getOrdinal();
			if(maxPriority < newPriority){
				try {
					formsExporter = (FormsExporter) cfgElt.createExecutableExtension(CLASS);
					//set the new priority only if the instanciation had worked
					maxPriority = newPriority;
				} catch (CoreException e) {
					BonitaStudioLog.error(e);
				}
			}
		}		
	}
	
	
	/**
	 * Get the priority of the Provider's configuration element
	 * 
	 * @param element
	 *            The configuration elements describing the provider.
	 * @return the priority of the specified configuration element
	 */
	public String getPriority(IConfigurationElement element) {
		return element.getChildren(E_PRIORITY)[0].getAttribute(A_NAME);
	}
	
}
