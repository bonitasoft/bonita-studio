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
package org.bonitasoft.studio.connector.wizard.sap;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.bonitasoft.studio.connectors.Activator;
import org.bonitasoft.studio.connectors.wizards.SaveConfigurationWizard;
import org.bonitasoft.studio.repository.connectors.configuration.ConfigurationRepository;
import org.eclipse.core.runtime.Status;
import org.ow2.bonita.connector.core.ConnectorDescription;
import org.ow2.bonita.connector.core.configuration.Configuration;
import org.ow2.bonita.connector.core.configuration.Parameter;

/**
 * @author Aurelien Pupier
 */
public class SaveSapConfigurationWizard extends SaveConfigurationWizard {

	public SaveSapConfigurationWizard(ConnectorDescription connector,
			Map<String, Object> currentParameters,
			Configuration parentConfiguration) {
		super(connector, currentParameters, parentConfiguration);
	}
	
	@Override
	public boolean performFinish() {
		if(connector.getId().equals("SAPCallFunction")){
		Configuration config = null;
		if (parentConfiguration != null && page.useInheritance()) {
			config = new Configuration(page.getConfigName(), parentConfiguration);
			for (Entry<String, Object> entry : currentParameter.entrySet()) {
				if (! entry.getValue().equals(parentConfiguration.getParameter(entry.getKey())))
					config.addParameter(new Parameter(entry.getKey(), getValue(entry.getValue(),SEPARATOR_ROW), entry.getValue().getClass().getName()));
			}
		} else {
			config = new Configuration(page.getConfigName(), connector);
			for (Entry<String, Object> entry : currentParameter.entrySet()) {
				//TODO parameter only support string type for now
				String key = entry.getKey();
				Object value = entry.getValue();
				String parsedValue;
				if(key.equals("setOutputParameters")){
					/*Remove the last parameter of each row as it is a data*/
					parsedValue = getValueWithoutData(value,SEPARATOR_ROW);
				} else {
					parsedValue = getValue(value,SEPARATOR_ROW);
				}
				
				config.addParameter(new Parameter(key, parsedValue, value.getClass().getName()));
			} 
		}
		try {
			ConfigurationRepository.getInstance().save(config,null);
		} catch (Exception ex) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR,Activator.PLUGIN_ID,ex.getMessage(),ex));
		}
		return true;
		} else {
			return super.performFinish();
		}
	}

	/**
	 * The last entry of each row is removed as it is a data
	 * @param entry
	 * @param separator
	 * @return
	 */
	private String getValueWithoutData(Object entry, String separator) {
		if(entry instanceof Collection<?>){
			StringBuilder valueBuilder = new StringBuilder();
			Iterator<?> it =  ((Collection<?>)entry).iterator();
			while(it.hasNext()){
				Object item = it.next();
				valueBuilder.append(getValueWithoutLast(item,SEPARATOR_COLUMN));
				if(it.hasNext()){
					valueBuilder.append(separator);
				}
			}
			return valueBuilder.toString();
		}else{
			return entry != null && entry.toString().length()>0 ? entry.toString() : "";
		}
	}

	private Object getValueWithoutLast(Object entry, String separator) {
		if(entry instanceof Collection<?>){
			StringBuilder valueBuilder = new StringBuilder();
			Iterator<?> it =  ((Collection<?>)entry).iterator();
			while(it.hasNext()){
				Object item = it.next();				
				if(it.hasNext()){
					valueBuilder.append(getValueWithoutLast(item,SEPARATOR_COLUMN));
					valueBuilder.append(separator);
				}
			}
			return valueBuilder.toString();
		}else{
			return entry != null && entry.toString().length()>0 ? entry.toString() : "";
		}
	}
	

	
	

}
