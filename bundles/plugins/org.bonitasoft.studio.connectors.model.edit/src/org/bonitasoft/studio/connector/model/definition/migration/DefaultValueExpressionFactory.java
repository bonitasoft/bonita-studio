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
package org.bonitasoft.studio.connector.model.definition.migration;

import java.util.Objects;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.connector.model.definition.Array;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.List;
import org.bonitasoft.studio.connector.model.definition.ScriptEditor;
import org.bonitasoft.studio.connector.model.definition.TextArea;
import org.bonitasoft.studio.connector.model.definition.WidgetComponent;
import org.bonitasoft.studio.model.expression.AbstractExpression;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.eclipse.e4.core.di.annotations.Creatable;

@Creatable
public class DefaultValueExpressionFactory {

    public AbstractExpression create(Input input, ConnectorDefinition definition) {
        final String inputClassName = input.getType();
        WidgetComponent widget = findWidget(input.getName(), definition);
        if (widget instanceof Array) {
            return ExpressionFactory.eINSTANCE
                    .createTableExpression();
        } else if (widget instanceof List) {
            return ExpressionFactory.eINSTANCE
                    .createListExpression();
        } else {
            final Expression expression = ExpressionFactory.eINSTANCE
                    .createExpression();
            expression.setReturnType(inputClassName);
            expression.setReturnTypeFixed(true);
            expression.setType(ExpressionConstants.CONSTANT_TYPE);
            expression.setName(input.getDefaultValue());
            expression.setContent(input.getDefaultValue());
            if (widget instanceof ScriptEditor) {
                expression.setType(ExpressionConstants.SCRIPT_TYPE);
                expression.setInterpreter(((ScriptEditor) widget)
                        .getInterpreter());
            } else if (widget instanceof TextArea) {
                expression.setType(ExpressionConstants.PATTERN_TYPE);
            }
            return expression;
        }
    }
    
    public WidgetComponent findWidget(String inputName, ConnectorDefinition definition) {
        return ModelHelper.getAllElementOfTypeIn(definition, WidgetComponent.class).stream()
                .filter(wc -> Objects.equals(inputName, wc.getInputName()))
                .findFirst()
                .orElse(null);
    }

}
