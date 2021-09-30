/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connector.model.definition.wizard;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.connector.model.definition.Array;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.ScriptEditor;
import org.bonitasoft.studio.connector.model.definition.TextArea;
import org.bonitasoft.studio.connector.model.definition.WidgetComponent;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationFactory;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.AbstractExpression;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.TableExpression;

public class ConnectorConfigurationSupport {

    private final ConnectorConfiguration connectorConfiguration;

    public ConnectorConfigurationSupport(ConnectorConfiguration connectorConfiguration) {
        this.connectorConfiguration = connectorConfiguration;
    }

    public ConnectorParameter getConnectorParameter(final String inputName, final WidgetComponent object, final Input input) {
        for (final ConnectorParameter param : connectorConfiguration.getParameters()) {
            if (param.getKey().equals(inputName)) {
                if (param.getExpression() == null) {
                    param.setExpression(createExpression(object, input));
                } else {
                    if (param.getExpression() instanceof Expression) {
                        final Expression exp = (Expression) param.getExpression();
                        if (!exp.isReturnTypeFixed()) {
                            exp.setReturnTypeFixed(true);
                        }
                        final String type = input.getType();
                        if (type != null && !type.equals(exp.getReturnType())) {
                            exp.setReturnType(type);
                        }
                    }

                }
                return param;
            }
        }
        final ConnectorParameter parameter = ConnectorConfigurationFactory.eINSTANCE.createConnectorParameter();
        parameter.setKey(inputName);
        parameter.setExpression(createExpression(object, input));
        connectorConfiguration.getParameters().add(parameter);
        return parameter;
    }

    public ConnectorParameter findConnectorParameter(final String inputName) {
        for (final ConnectorParameter param : connectorConfiguration.getParameters()) {
            if (param.getKey().equals(inputName)) {
                return param;
            }
        }
        return null;
    }

    private AbstractExpression createExpression(final WidgetComponent widget, final Input input) {
        final String inputClassName = input.getType();
        if (widget instanceof Array) {
            final TableExpression expression = ExpressionFactory.eINSTANCE.createTableExpression();
            return expression;
        } else if (widget instanceof org.bonitasoft.studio.connector.model.definition.List) {
            final ListExpression expression = ExpressionFactory.eINSTANCE.createListExpression();
            return expression;
        } else {
            final Expression expression = ExpressionFactory.eINSTANCE.createExpression();
            expression.setReturnType(inputClassName);
            expression.setReturnTypeFixed(true);
            expression.setType(ExpressionConstants.CONSTANT_TYPE);
            expression.setName(input.getDefaultValue());
            expression.setContent(input.getDefaultValue());
            if (widget instanceof ScriptEditor) {
                expression.setType(ExpressionConstants.SCRIPT_TYPE);
                expression.setInterpreter(((ScriptEditor) widget).getInterpreter());
            } else if (widget instanceof TextArea) {
                expression.setType(ExpressionConstants.PATTERN_TYPE);
            }
            return expression;
        }
    }
}
