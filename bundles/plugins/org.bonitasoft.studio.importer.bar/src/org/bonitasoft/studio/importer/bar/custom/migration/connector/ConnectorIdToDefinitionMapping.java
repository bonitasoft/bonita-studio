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
package org.bonitasoft.studio.importer.bar.custom.migration.connector;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.connectors.extension.IConnectorDefinitionMapper;
import org.bonitasoft.studio.importer.bar.BarImporterPlugin;
import org.bonitasoft.studio.importer.bar.custom.migration.connector.mapper.CustomConnectorDefinitionMapper;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

/**
 * @author Romain Bioteau
 *
 */
public class ConnectorIdToDefinitionMapping {

	private static final String CONNECTOR_DEFINITION_MAPPER = "org.bonitasoft.studio.connectors.connectorDefinitionMapper";
	private static ConnectorIdToDefinitionMapping INSTANCE ;
	private List<IConnectorDefinitionMapper> mappers = new ArrayList<IConnectorDefinitionMapper>();

	private ConnectorIdToDefinitionMapping(){
		for(IConfigurationElement element : BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(CONNECTOR_DEFINITION_MAPPER)){
			try {
				mappers.add((IConnectorDefinitionMapper) element.createExecutableExtension("class"));
			} catch (CoreException e) {
				BonitaStudioLog.error(e, BarImporterPlugin.PLUGIN_ID);
			}
		}
	}

	public static ConnectorIdToDefinitionMapping getInstance(){
		if(INSTANCE == null){
			INSTANCE = new ConnectorIdToDefinitionMapping();
		}
		return INSTANCE;
	}

	public IConnectorDefinitionMapper getDefinitionMapper(String connectorId){
		for(IConnectorDefinitionMapper mapper : mappers){
			if(mapper.appliesTo(connectorId)){
				return mapper;
			}
		}
		final CustomConnectorDefinitionMapper customMapper = new CustomConnectorDefinitionMapper();
		if(customMapper.appliesTo(connectorId)){
			return customMapper;
		}
		return null;
	}
}
