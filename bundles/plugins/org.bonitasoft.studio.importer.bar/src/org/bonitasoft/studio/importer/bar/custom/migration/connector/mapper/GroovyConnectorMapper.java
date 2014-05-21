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
package org.bonitasoft.studio.importer.bar.custom.migration.connector.mapper;

import java.util.Map;

import org.bonitasoft.studio.connectors.extension.AbstractConnectorDefinitionMapper;
import org.bonitasoft.studio.connectors.extension.IConnectorDefinitionMapper;

/**
 * @author Romain Bioteau
 *
 */
public class GroovyConnectorMapper extends AbstractConnectorDefinitionMapper implements IConnectorDefinitionMapper {

	private static final String GROOVY_SCRIPT_CONNECTOR_DEFINITION_ID = "scripting-groovy";
	private static final String LEGACY_GROOVY_CONNECTOR_ID = "GroovyConnector";
	private static final String SCRIPT = "script";


	@Override
	public String getLegacyConnectorId() {
		return LEGACY_GROOVY_CONNECTOR_ID;
	}

	@Override
	public String getDefinitionId() {
		return GROOVY_SCRIPT_CONNECTOR_DEFINITION_ID;
	}
	
	@Override
	public Object transformParameterValue(String input, Object value,Map<String, Object> otherInputs) {
		if(SCRIPT.equals(input) && value != null){
			return "${"+value.toString()+"}";
		}
		return super.transformParameterValue(input, value,otherInputs);
	}



}
