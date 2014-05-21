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

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.connectors.extension.AbstractConnectorDefinitionMapper;

/**
 * @author Romain Bioteau
 *
 */
public abstract class AbstractDatabaseConnectorDefinitionMapper extends
		AbstractConnectorDefinitionMapper {

	private static final String LEGACY_QUERY_KEY = "setQuery";
	private static final String SCRIPT = "script";
	private static final String LEGACY_HOSTNAME_KEY = "setHostName";
	private static final String JDBC_URL_KEY = "url";
	private static final String LEGACY_PORT_KEY = "setPort";
	private static final String LEGACY_DB_NAME_PORT_KEY = "setDatabase";
	
	protected abstract String getUrlPrefix();
	
	protected String getUrlSuffix(){
		return "";
	}
	
	@Override
	public String getParameterKeyFor(String legacyParameterKey) {
		if(LEGACY_HOSTNAME_KEY.equals(legacyParameterKey)){
			return JDBC_URL_KEY;
		}
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
	
	@Override
	public Object transformParameterValue(String parameterKeyFor, Object value, Map<String, Object> otherInputs) {
		if(JDBC_URL_KEY.equals(parameterKeyFor) && value != null){
			Object port = otherInputs.get(LEGACY_PORT_KEY);
			String dbName = (String) otherInputs.get(LEGACY_DB_NAME_PORT_KEY);
			return buildJdbcUrl(value.toString(),port != null ? port.toString() : null,dbName,otherInputs);
		}
		return super.transformParameterValue(parameterKeyFor,value,otherInputs);
	}

	protected String buildJdbcUrl(String hostName,String port,String dbName, Map<String, Object> otherInputs) {
		boolean constantHostName = true;
		boolean constantPort = true;
		boolean constantDbName = true;
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
			if(port != null){
				return getUrlPrefix()+hostName+":"+port+"/"+dbName+getUrlSuffix();
			}else{
				return getUrlPrefix()+hostName+"/"+dbName+getUrlSuffix();
			}
		}else{
			if(getUrlSuffix() != null && !getUrlSuffix().isEmpty()){
				if(port != null){
					return "${\""+getUrlPrefix()+hostName+":"+port+"/"+dbName+"+\""+getUrlSuffix()+"\""+"}";
				}else{
					return "${\""+getUrlPrefix()+hostName+"/"+dbName+"+\""+getUrlSuffix()+"\""+"}";
				}
				
			}else{
				if(port != null){
					return "${\""+getUrlPrefix()+hostName+":"+port+"/"+dbName+"}";
				}else{
					return "${\""+getUrlPrefix()+hostName+"/"+dbName+"}";
				}
			}
		}
	}

}
