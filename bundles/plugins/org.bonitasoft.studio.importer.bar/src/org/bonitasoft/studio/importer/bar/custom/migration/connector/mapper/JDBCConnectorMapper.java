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

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.connectors.extension.AbstractConnectorDefinitionMapper;
import org.bonitasoft.studio.connectors.extension.IConnectorDefinitionMapper;

/**
 * @author Romain Bioteau
 *
 */
public class JDBCConnectorMapper extends AbstractConnectorDefinitionMapper implements IConnectorDefinitionMapper {

	private static final String JDBC4_CONNECTOR_DEFINITION_ID = "database-jdbc";
	private static final String LEGACY_JDBC_CONNECTOR_ID = "GenericJDBCConnector";
	private static final String LEGACY_BATCH_JDBC_CONNECTOR_ID = "GenericJDBCBatchConnector";
	
	private static final String LEGACY_QUERY_KEY = "setQuery";
	private static final String SCRIPT = "script";

	@Override
	public boolean appliesTo(String legacyConnectorId) {
		return legacyConnectorId != null && (legacyConnectorId.equals(LEGACY_JDBC_CONNECTOR_ID)
				|| legacyConnectorId.equals(LEGACY_BATCH_JDBC_CONNECTOR_ID));
	}
	
	@Override
	public String getLegacyConnectorId() {
		return LEGACY_JDBC_CONNECTOR_ID;
	}
	
	@Override
	public String getDefinitionId() {
		return JDBC4_CONNECTOR_DEFINITION_ID;
	}

	@Override
	public String getParameterKeyFor(String legacyParameterKey) {
		if(LEGACY_QUERY_KEY.equals(legacyParameterKey)){
			return SCRIPT;
		}
		return super.getParameterKeyFor(legacyParameterKey);
	}
	
	@Override
	public String getExpectedExpresstionType(String input, Object value) {
		if(SCRIPT.equals(input) && !(value.toString().startsWith("${") && value.toString().endsWith("}"))){
			return ExpressionConstants.PATTERN_TYPE;
		}
		return super.getExpectedExpresstionType(input, value);
	}


}
