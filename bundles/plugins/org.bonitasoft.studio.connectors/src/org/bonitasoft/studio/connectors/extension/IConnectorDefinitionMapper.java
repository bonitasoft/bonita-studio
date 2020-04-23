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
package org.bonitasoft.studio.connectors.extension;

import java.util.Map;

/**
 * @author Romain Bioteau
 *
 */
public interface IConnectorDefinitionMapper {

	boolean appliesTo(String legacyConnectorId);
	
	String getParameterKeyFor(String legacyParameterKey);
	
	String getDefinitionId();
	
	String getDefinitionVersion();
	
	String getLegacyConnectorId();

	String getInputReturnType(String inputName);

	Object transformParameterValue(String parameterKeyFor, Object value, Map<String, Object> otherInputs);

	String getExpectedExpresstionType(String input, Object value);

	Map<String, Object> getAdditionalInputs(Map<String, Object> inputs);

	String getOutputReturnType(String outputName);
	
}
