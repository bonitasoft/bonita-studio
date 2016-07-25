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
public class WebServiceConnectorMapper extends AbstractConnectorDefinitionMapper implements IConnectorDefinitionMapper {

	private static final String LEGACY_WEBSERVICE_CONNECTOR_ID = "CXFClient";
	private static final String LEGACY_SECURED_XML_WEBSERVICE_CONNECTOR_ID = "SecureWSConnectorCXF_2_4_0";
	

	private static final String LEGACY_USERNAME = "setAuthUserName";
	private static final String USERNAME = "userName";
	private static final String LEGACY_PASSWORD = "setAuthPassword";
	private static final String PASSWORD = "password";
	private static final String LEGACY_ENVELOPPE = "setEnveloppe";
	private static final String ENVELOPPE = "envelope";
	private static final String WEBSERVICE_CONNECTOR_DEFINITION_ID = "webservice";
	private static final String LEGACY_REQUEST = "setRequest";
	private static final String LEGACY_TARGET_NS = "setTargetNS" ;
	private static final String TARGET_NS = "serviceNS";

	@Override
	public boolean appliesTo(String legacyConnectorId) {
		return legacyConnectorId != null && (legacyConnectorId.equals(LEGACY_WEBSERVICE_CONNECTOR_ID)
				|| legacyConnectorId.equals(LEGACY_SECURED_XML_WEBSERVICE_CONNECTOR_ID));
	}
	
	@Override
	public String getLegacyConnectorId() {
		return LEGACY_WEBSERVICE_CONNECTOR_ID;
	}
	
	@Override
	public String getDefinitionId() {
		return WEBSERVICE_CONNECTOR_DEFINITION_ID;
	}

	@Override
	public String getParameterKeyFor(String legacyParameterKey) {
		if(LEGACY_USERNAME.equals(legacyParameterKey)){
			return USERNAME;
		}
		if(LEGACY_PASSWORD.equals(legacyParameterKey)){
			return PASSWORD;
		}
		if(LEGACY_REQUEST.equals(legacyParameterKey)){
			return ENVELOPPE;
		}
		if(LEGACY_ENVELOPPE.equals(legacyParameterKey)){
			return ENVELOPPE;
		}
		if(LEGACY_TARGET_NS.equals(legacyParameterKey)){
			return TARGET_NS;
		}
		return super.getParameterKeyFor(legacyParameterKey);
	}
	
	@Override
	public String getExpectedExpresstionType(String input, Object value) {
		if(ENVELOPPE.equals(input) && !(value.toString().startsWith("${") && value.toString().endsWith("}"))){
			return ExpressionConstants.PATTERN_TYPE;
		}
		return super.getExpectedExpresstionType(input, value);
	}


}
