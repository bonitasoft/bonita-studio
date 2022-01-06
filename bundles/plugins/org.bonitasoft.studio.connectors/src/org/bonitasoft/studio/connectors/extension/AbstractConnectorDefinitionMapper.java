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

import java.util.Collections;
import java.util.Map;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.definition.Component;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.connector.model.definition.Page;
import org.bonitasoft.studio.connector.model.definition.TextArea;
import org.bonitasoft.studio.connector.model.definition.WidgetComponent;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;

/**
 * @author Romain Bioteau
 *
 */
public abstract class AbstractConnectorDefinitionMapper implements IConnectorDefinitionMapper{

	protected ConnectorDefinition definition;
	
	public AbstractConnectorDefinitionMapper(){
		ConnectorDefRepositoryStore connectorDefStore = (ConnectorDefRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
		for(ConnectorDefinition def : connectorDefStore.getDefinitions()){
			if(getDefinitionId().equals(def.getId())){
				this.definition = def;
				break;
			}
		}
	}
	
	public boolean appliesTo(String legacyConnectorId){
		return legacyConnectorId != null //valid value
				&& legacyConnectorId.equals(getLegacyConnectorId())//the legacy connectorId matches
				&& definition != null;//we found the corresponding new connector definition, it can be null for SP connectors for instance.
	}
	
	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.connectors.extension.IConnectorDefinitionMapper#getParameterKeyFor(java.lang.String)
	 */
	@Override
	public String getParameterKeyFor(String legacyParameterKey) {
		if(legacyParameterKey.startsWith("set")){
			legacyParameterKey = legacyParameterKey.substring(3);
		}
		for(Input input : definition.getInput()){
			if(input.getName().equalsIgnoreCase(legacyParameterKey)){
				return input.getName();
			}
		}
		return null;
	}
	
	@Override
	public String getInputReturnType(String inputName) {
		for(Input input : definition.getInput()){
			if(input.getName().equals(inputName)){
				return input.getType();
			}
		}
		return null;
	}

	@Override
	public String getOutputReturnType(String outputName) {
		for(Output output : definition.getOutput()){
			if(output.getName().equals(outputName)){
				return output.getType();
			}
		}
		return null;
	}
	@Override
	public Object transformParameterValue(String parameterKeyFor, Object value, Map<String, Object> otherInputs) {
		return value;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.connectors.extension.IConnectorDefinitionMapper#getDefinitionVersion()
	 */
	@Override
	public String getDefinitionVersion() {
		return definition.getVersion();
	}
	
	@Override
	public String getExpectedExpresstionType(String input, Object value) {
		WidgetComponent widget = getWidgetForInput(input);
		if(widget instanceof TextArea){
			return ExpressionConstants.PATTERN_TYPE;
		}
		return null;
	}
	
	private WidgetComponent getWidgetForInput(String input) {
		for(Page p : definition.getPage()){
			for(Component w : p.getWidget()){
				if(w instanceof WidgetComponent){
					if(((WidgetComponent) w).getInputName().equals(input)){
						return (WidgetComponent) w;
					}
				}
			}
		}
		return null;
	}

	protected boolean isGroovyString(String string) {
		return string != null && string.startsWith("${") && string.endsWith("}");
	}
	
	@Override
	public Map<String, Object> getAdditionalInputs(Map<String, Object> inputs) {
		return Collections.emptyMap();
	}
	
}
