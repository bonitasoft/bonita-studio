/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.importer.bar.custom.migration.connector.mapper;

import java.util.Map;

import org.bonitasoft.studio.connectors.extension.AbstractConnectorDefinitionMapper;
import org.bonitasoft.studio.connectors.extension.IConnectorDefinitionMapper;

/**
 * @author Maxence Raoux
 * 
 */
public class CMISCreateFolderConnectorMapper extends
		AbstractConnectorDefinitionMapper implements IConnectorDefinitionMapper {

	private static final String CMIS_CONNECTOR_DEFINITION_ID = "cmis-createfolder";
	private static final String LEGACY_CMIS_CONNECTOR_ID = "createSubfolder";

	@Override
	public String getLegacyConnectorId() {
		return LEGACY_CMIS_CONNECTOR_ID;
	}

	@Override
	public String getDefinitionId() {
		return CMIS_CONNECTOR_DEFINITION_ID;
	}

	@Override
	public String getParameterKeyFor(String legacyParameterKey) {
		if ("setRepositoryName".equals(legacyParameterKey)) {
			return "repository";
		} else if ("setParentFolderPath".equals(legacyParameterKey)) {
			return "folder_path";
		} else if ("setSubfolderName".equals(legacyParameterKey)) {
			return "subfolder_name";
		} else {
			return super.getParameterKeyFor(legacyParameterKey);
		}
	}
	
	@Override
	public Object transformParameterValue(String parameterKeyFor, Object value,
			Map<String, Object> otherInputs) {
		if("binding_type".equals(parameterKeyFor)){
			if(value instanceof String){
				if("ATOM".equals(value)){
					return "atompub";
				} else {
					return "webservices";
				}
			}
		}
		return super.transformParameterValue(parameterKeyFor, value, otherInputs);
	}
	
}
