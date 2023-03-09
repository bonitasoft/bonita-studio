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
import java.util.Optional;

import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.process.Connector;

public interface DefinitionOutputChange {

    boolean isBreakingChange(Connector connector);

    void apply(Connector connector);
    
    default Optional<Output> findOutput(String outputName, Connector connector) {
        return connector.getOutputs().stream()
                .map(Operation::getRightOperand)
                .flatMap(expr -> expr.getReferencedElements().stream())
                .filter(Output.class::isInstance)
                .map(Output.class::cast)
                .filter(output -> Objects.equals(output.getName(), outputName))
                .findFirst();
    }


}
