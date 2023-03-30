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
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.process.Connector;

public class OutputTypeChange implements DefinitionOutputChange {

    private final String outputName;
    private final String type;
    private String oldType;

    public OutputTypeChange(String outputName, String oldType, String type) {
        this.outputName = outputName;
        this.oldType = oldType;
        this.type = type;
    }

    @Override
    public boolean isBreakingChange(Connector connector) {
        return findOutput(outputName, connector).isPresent();
    }

    @Override
    public void apply(Connector connector) {
        findOutput(outputName, connector)
                .ifPresent(output -> output.setType(type));

        connector.getOutputs().stream()
                .map(Operation::getRightOperand)
                .filter(expr -> ExpressionConstants.CONNECTOR_OUTPUT_TYPE.equals(expr.getType()))
                .filter(expr -> Objects.equals(expr.getName(), outputName))
                .forEach(expr -> expr.setReturnType(type));
    }
    
    @Override
    public String toString() {
        return String.format(Messages.outputTypeChangeDescription, outputName, oldType, type);
    }
   
}
