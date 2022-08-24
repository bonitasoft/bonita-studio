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

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.connector.model.definition.Array;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.List;
import org.bonitasoft.studio.connector.model.definition.WidgetComponent;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.expression.AbstractExpression;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.TableExpression;

public class InputWidgetChange implements DefinitionInputChange {

    private final String inputName;
    private WidgetComponent oldWidgetType;
    private WidgetComponent newWidgetType;

    public InputWidgetChange(String inputName) {
        this.inputName = inputName;
    }
    
    public void setNewWidgetType(WidgetComponent newWidgetType) {
        this.newWidgetType = newWidgetType;
    }
    
    public void setOldWidgetType(WidgetComponent oldWidgetType) {
        this.oldWidgetType = oldWidgetType;
    }
    
    public String getInputName() {
        return inputName;
    }
    
    public boolean isChange() {
        return newWidgetType != null && oldWidgetType != null;
    }

    @Override
    public boolean isBreakingChange(ConnectorConfiguration configruation) {
        return findParameter(inputName, configruation)
                .filter(p -> p.getExpression() != null)
                .map(p -> shouldUpdateTableExpression(p.getExpression()) || shouldUpdateListExpression(p.getExpression()))
                .orElse(false);
    }

    @Override
    public void apply(ConnectorConfiguration configuration,  ConnectorDefinition targetDefinition) {
        configuration.getParameters().stream().filter(p -> Objects.equals(p.getKey(), inputName))
                .findFirst()
                .filter(cp -> shouldUpdateListExpression(cp.getExpression())
                        || shouldUpdateTableExpression(cp.getExpression()))
                .ifPresent(cp -> cp.setExpression(ExpressionHelper.createConstantExpression(null,
                        findInputType(inputName, targetDefinition))));
    }
    
    @Override
    public String toString() {
        return String.format(Messages.inputWidgetChangeDescription,
                inputName, 
                oldWidgetType.eClass().getName(),
                newWidgetType.eClass().getName());
    }

    private boolean shouldUpdateTableExpression(AbstractExpression expression) {
        return expression instanceof TableExpression
                && !(newWidgetType instanceof Array);
    }

    private boolean shouldUpdateListExpression(AbstractExpression expression) {
        return expression instanceof ListExpression
                && !(newWidgetType instanceof List);
    }

    private String findInputType(String inputName, ConnectorDefinition targetDefinition) {
        return targetDefinition.getInput().stream()
                .filter(input -> Objects.equals(inputName, input.getName()))
                .map(Input::getType).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("No input '%s' found.", inputName)));
    }

}
