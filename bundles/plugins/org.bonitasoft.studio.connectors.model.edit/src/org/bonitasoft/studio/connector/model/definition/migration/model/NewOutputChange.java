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

import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.bonitasoft.studio.model.process.Connector;

public class NewOutputChange implements DefinitionOutputChange {

    private final Output newOutput;

    public NewOutputChange(Output newOutput) {
        this.newOutput = newOutput;
    }

    @Override
    public boolean isBreakingChange(Connector connector) {
        return false;
    }

    @Override
    public void apply(Connector connector) {
        // Nothing to do
    }
    
    @Override
    public String toString() {
        return String.format(Messages.newOutputChangeDescription, newOutput.getName());
    }
    
}
