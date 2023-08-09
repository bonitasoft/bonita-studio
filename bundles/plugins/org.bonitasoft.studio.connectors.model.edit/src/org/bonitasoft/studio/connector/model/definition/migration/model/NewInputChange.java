/**
 * Copyright (C) 2021 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.connector.model.definition.migration.model;

import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.bpm.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.bpm.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.migration.DefaultValueExpressionFactory;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.bonitasoft.bpm.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.bpm.model.connectorconfiguration.ConnectorConfigurationFactory;
import org.bonitasoft.bpm.model.connectorconfiguration.ConnectorParameter;

public class NewInputChange implements DefinitionInputChange {

    private final Input newInput;
    private DefaultValueExpressionFactory defaultValueExpressionFactory;

    public NewInputChange(Input newInput, DefaultValueExpressionFactory defaultValueExpressionFactory) {
        this.newInput = newInput;
        this.defaultValueExpressionFactory = defaultValueExpressionFactory;
    }

    @Override
    public boolean isBreakingChange(ConnectorConfiguration configruation) {
        return newInput.isMandatory() && (newInput.getDefaultValue() == null || newInput.getDefaultValue().isBlank());
    }

    @Override
    public void apply(ConnectorConfiguration configuration, ConnectorDefinition targetDefinition) {
        configuration.getParameters().add(createParameter(targetDefinition));
    }
    
    private ConnectorParameter createParameter(ConnectorDefinition targetDefinition) {
        ConnectorParameter parameter = ConnectorConfigurationFactory.eINSTANCE.createConnectorParameter();
        parameter.setKey(newInput.getName());
        parameter.setExpression(defaultValueExpressionFactory.create(newInput, targetDefinition));
        return parameter;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(newInput.isMandatory() ? 
                String.format(Messages.newMandatoryInputChangeDescription, newInput.getName())
                : String.format(Messages.newInputChangeDescription, newInput.getName()));
        if(Strings.hasText(newInput.getDefaultValue())) {
            sb.append(String.format(" (%s: %s)", Messages.defaultValue, newInput.getDefaultValue()));
        }
        return sb.toString();
    }
}
