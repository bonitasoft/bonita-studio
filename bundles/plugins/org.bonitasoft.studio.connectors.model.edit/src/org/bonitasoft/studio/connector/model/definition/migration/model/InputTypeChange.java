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

import java.util.Objects;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.Expression;

public class InputTypeChange implements DefinitionInputChange {

    private final String inputName;
    private final String type;
    private String oldType;

    public InputTypeChange(String inputName, String oldType, String type) {
        this.inputName = inputName;
        this.oldType = oldType;
        this.type = type;
    }

    @Override
    public boolean isBreakingChange(ConnectorConfiguration configruation) {
        return findParameter(inputName, configruation)
                .filter(p -> p.getExpression() != null)
                .map(p -> hasValue(p.getExpression()))
                .orElse(false);
    }

    @Override
    public void apply(ConnectorConfiguration configuration, ConnectorDefinition targetDefinition) {
        configuration.getParameters().stream().filter(p -> Objects.equals(p.getKey(), inputName))
                .findFirst()
                .map(ConnectorParameter::getExpression)
                .filter(Expression.class::isInstance)
                .map(Expression.class::cast)
                .ifPresent(expr -> {
                    if(!ExpressionConstants.SCRIPT_TYPE.equals(expr.getType())) {
                        expr.setName(null);
                        expr.setContent(null);
                        expr.setType(ExpressionConstants.CONSTANT_TYPE);
                    } 
                    expr.setReturnType(type);
                });
    }
    
    @Override
    public String toString() {
        return String.format(Messages.inputTypeChangeDescription, inputName, oldType, type);
    }
}
