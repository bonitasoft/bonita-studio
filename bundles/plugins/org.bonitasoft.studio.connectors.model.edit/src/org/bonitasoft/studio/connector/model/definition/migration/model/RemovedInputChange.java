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

import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;

public class RemovedInputChange implements DefinitionInputChange {

    private final Input removedInput;

    public RemovedInputChange(Input removedInput) {
        this.removedInput = removedInput;
    }

    @Override
    public boolean isBreakingChange(ConnectorConfiguration configruation) {
        return false;
    }

    @Override
    public void apply(ConnectorConfiguration configuration, ConnectorDefinition targetDefinition) {
        configuration.getParameters()
                .removeIf(parameter -> Objects.equals(parameter.getKey(), removedInput.getName()));
    }
    
    @Override
    public String toString() {
        return String.format(Messages.removedInputChangeDescription, removedInput.getName());
    }
}
