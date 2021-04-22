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
package org.bonitasoft.studio.application.operation.definition.preview;

import java.util.List;

import org.bonitasoft.studio.connector.model.definition.migration.model.DefinitionInputChange;
import org.bonitasoft.studio.connector.model.definition.migration.model.NewInputChange;
import org.bonitasoft.studio.connector.model.definition.migration.model.RemovedInputChange;

public class InputChange implements ChangePreview {

    private DefinitionInputChange change;
    private ChangePreview parent;

    public InputChange(ChangePreview parent, DefinitionInputChange change) {
        this.parent = parent;
        this.change = change;
    }
    
    @Override
    public String getDescription() {
        return change.toString();
    }

    @Override
    public List<ChangePreview> getDetails() {
        return List.of();
    }

    @Override
    public Kind getKind() {
        if(change instanceof NewInputChange) {
            return Kind.ADD;
        }
        if(change instanceof RemovedInputChange) {
            return Kind.REMOVE;
        }
        return Kind.UPDATE;
    }

    @Override
    public boolean hasBreakingChanges() {
        return false;
    }
    
    @Override
    public ChangePreview getParent() {
        return parent;
    }

}
