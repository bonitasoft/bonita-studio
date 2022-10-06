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
import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.process.Connector;
import org.eclipse.emf.ecore.EObject;

public class RemovedOutputChange implements DefinitionOutputChange {

    private final Output removedOutput;

    public RemovedOutputChange(Output removedOutput) {
        this.removedOutput = removedOutput;
    }

    public Output getRemovedOutput() {
        return removedOutput;
    }

    @Override
    public boolean isBreakingChange(Connector connector) {
        return findOutput(removedOutput.getName(), connector).isPresent();
    }

    @Override
    public void apply(Connector connector) {
        connector.getOutputs().stream()
                .map(Operation::getRightOperand)
                .forEach(expr -> expr.getReferencedElements()
                        .removeIf(ref -> isOutputRefWithName(ref, getRemovedOutput().getName())));

        connector.getOutputs()
                .removeIf(operation -> isConnectorOutputExpressionWithName(operation.getRightOperand(),
                        getRemovedOutput().getName()));
    }

    private boolean isConnectorOutputExpressionWithName(Expression expression, String name) {
        return ExpressionConstants.CONNECTOR_OUTPUT_TYPE.equals(expression.getType())
                && Objects.equals(name, expression.getName());
    }

    private boolean isOutputRefWithName(EObject ref, String name) {
        return ref instanceof Output && Objects.equals(((Output) ref).getName(), name);
    }
    
    @Override
    public String toString() {
        return String.format(Messages.removedOutputChangeDescription, removedOutput.getName());
    }
}
