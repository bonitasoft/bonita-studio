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

import org.bonitasoft.studio.connectors.extension.IConnectorDefinitionMapper;

/**
 * @author Romain Bioteau
 *
 */
public class HSQLConnectorMapper extends AbstractDatabaseConnectorDefinitionMapper implements IConnectorDefinitionMapper {

	private static final String HSQL_CONNECTOR_DEFINITION_ID = "database-hsqldb";
	private static final String LEGACY_HSQL_CONNECTOR_ID = "HSQLDB";


	@Override
	public String getLegacyConnectorId() {
		return LEGACY_HSQL_CONNECTOR_ID;
	}

	@Override
	public String getDefinitionId() {
		return HSQL_CONNECTOR_DEFINITION_ID;
	}

	protected String getUrlPrefix() {
		return "jdbc:hsqldb:";
	}

	protected String buildJdbcUrl(String hostName,String port,String dbName, Map<String, Object> otherInputs) {
		boolean constantHostName = true;
		boolean constantPort = true;
		boolean constantDbName = true;

		boolean isWebServer = (Boolean) (otherInputs.get("setWebServer") != null ? otherInputs.get("setWebServer") : false);
		boolean isServer = (Boolean) (otherInputs.get("setServer") != null ? otherInputs.get("setServer") : false);
		boolean isSSL = (Boolean) (otherInputs.get("setSsl") != null ? otherInputs.get("setSsl") : false);

		if(isGroovyString(hostName)){
			constantHostName =false;
			hostName = "\"+"+hostName.substring(2, hostName.length()-1)+"+\"";
		}
		if(isGroovyString(port)){
			constantPort =false;
			port = "\"+"+port.substring(2, port.length()-1)+"+\"";
		}
		if(isGroovyString(dbName)){
			constantDbName =false;
			dbName = "\"+"+dbName.substring(2, dbName.length()-1);
		}
		if(constantPort && constantDbName && constantHostName){
			if(isServer){
				if(isSSL){
					return getUrlPrefix()+"hsqls://"+hostName+":"+port+"/"+dbName;
				}else{
					return getUrlPrefix()+"hsql://"+hostName+":"+port+"/"+dbName;
				}
			}else if(isWebServer){
				if(isSSL){
					return getUrlPrefix()+"https://"+hostName+":"+port+"/"+dbName;
				}else{
					return getUrlPrefix()+"http://"+hostName+":"+port+"/"+dbName;
				}
			}else {
				return getUrlPrefix()+"file:"+dbName;
			}
		}else{
			if(isServer){
				if(isSSL){
					return "${\""+getUrlPrefix()+"hsqls://"+hostName+":"+port+"/"+dbName+"}";
				}else{
					return "${\""+getUrlPrefix()+"hsql://"+hostName+":"+port+"/"+dbName+"}";
				}
			}else if(isWebServer){
				if(isSSL){
					return "${\""+getUrlPrefix()+"https://"+hostName+":"+port+"/"+dbName+"}";
				}else{
					return "${\""+getUrlPrefix()+"http://"+hostName+":"+port+"/"+dbName+"}";
				}
			}else{
				return "${\""+getUrlPrefix()+"file:"+dbName+"}";
			}
		}
	}

}
