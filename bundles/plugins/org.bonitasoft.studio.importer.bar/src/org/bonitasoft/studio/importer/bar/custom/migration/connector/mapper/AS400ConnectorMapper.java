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

import org.bonitasoft.studio.connectors.extension.IConnectorDefinitionMapper;

/**
 * @author Romain Bioteau
 *
 */
public class AS400ConnectorMapper extends AbstractDatabaseConnectorDefinitionMapper implements IConnectorDefinitionMapper {

	private static final String AS400_CONNECTOR_DEFINITION_ID = "database-as400";
	private static final String LEGACY_AS400_CONNECTOR_ID = "AS400";


	@Override
	public String getLegacyConnectorId() {
		return LEGACY_AS400_CONNECTOR_ID;
	}
	
	@Override
	public String getDefinitionId() {
		return AS400_CONNECTOR_DEFINITION_ID;
	}

	protected String getUrlPrefix() {
		return "jdbc:as400://";
	}

	@Override
	protected String getUrlSuffix() {
		return ";prompt=false";
	}


}
