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
package org.bonitasoft.studio.application.operation.extension.participant.definition.preview;

import java.util.List;

import org.bonitasoft.studio.application.operation.extension.participant.preview.ChangePreview;
import org.bonitasoft.studio.connector.model.definition.migration.model.DefinitionOutputChange;
import org.bonitasoft.studio.connector.model.definition.migration.model.NewOutputChange;
import org.bonitasoft.studio.connector.model.definition.migration.model.RemovedOutputChange;

public class OutputChange implements ChangePreview {

    private DefinitionOutputChange change;
    private ChangePreview parent;

    public OutputChange(ChangePreview parent, DefinitionOutputChange change) {
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
        if(change instanceof NewOutputChange) {
            return Kind.ADD;
        }
        if(change instanceof RemovedOutputChange) {
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

    @Override
    public boolean showInPreviewDialog() {
        return false;
    }

}
