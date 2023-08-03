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

import org.bonitasoft.bpm.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.bpm.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.bpm.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.bpm.model.expression.AbstractExpression;
import org.bonitasoft.bpm.model.expression.Expression;

public interface DefinitionInputChange {

    boolean isBreakingChange(ConnectorConfiguration configuration);

    void apply(ConnectorConfiguration configuration, ConnectorDefinition targetDefinition);

    default Optional<ConnectorParameter> findParameter(String inputName, ConnectorConfiguration configuration) {
        return configuration.getParameters().stream()
                .filter(p -> Objects.equals(inputName, p.getKey()))
                .findFirst();
    }
    
    default boolean hasValue(AbstractExpression expression) {
        if(expression instanceof Expression) {
            return ((Expression) expression).hasContent();
        }
        //ListExpression and TableExpression
        return true;
    }

}
