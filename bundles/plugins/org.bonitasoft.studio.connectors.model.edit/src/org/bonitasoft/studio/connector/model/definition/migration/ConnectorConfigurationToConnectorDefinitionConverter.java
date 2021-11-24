/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.connector.model.definition.migration;

import java.util.List;

import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.bonitasoft.studio.model.process.Connector;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.emf.ecore.util.EcoreUtil;

@Creatable
public class ConnectorConfigurationToConnectorDefinitionConverter {

    public ConnectorDefinition convert(Connector connector) {
        // Migrate to target version definition
        ConnectorDefinition sourceDef = ConnectorDefinitionFactory.eINSTANCE.createConnectorDefinition();
        sourceDef.setId(connector.getDefinitionId());
        sourceDef.setVersion(connector.getDefinitionVersion());

        connector.getConfiguration().getParameters()
                .stream()
                .map(cp -> toInput(cp))
                .forEach(sourceDef.getInput()::add);

        connector.getOutputs().stream()
                .flatMap(op -> op.getRightOperand().getReferencedElements().stream())
                .filter(Output.class::isInstance)
                .map(Output.class::cast)
                .forEach(output -> {
                    if (sourceDef.getOutput().stream().map(Output::getName)
                            .noneMatch(name -> name.equals(output.getName()))) {
                        sourceDef.getOutput().add(EcoreUtil.copy(output));
                    }
                });
        return sourceDef;
    }

    private Input toInput(ConnectorParameter cp) {
        Input input = ConnectorDefinitionFactory.eINSTANCE.createInput();
        input.setName(cp.getKey());
        if (cp.getExpression() instanceof Expression) {
            input.setType(((Expression) cp.getExpression()).getReturnType());
        } else if (cp.getExpression() instanceof ListExpression || cp.getExpression() instanceof TableExpression) {
            input.setType(List.class.getName());
        }
        return input;
    }

}
