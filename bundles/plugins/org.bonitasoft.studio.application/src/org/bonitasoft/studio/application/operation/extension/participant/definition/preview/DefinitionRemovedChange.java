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

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.operation.extension.participant.preview.ChangePreview;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;

public class DefinitionRemovedChange implements ChangePreview {

    private ConnectorDefinition definition;
    private long nbOfRemovedConfigurations;

    public DefinitionRemovedChange(ConnectorDefinition definition, long nbOfRemovedConfigurations) {
        this.definition = definition;
        this.nbOfRemovedConfigurations = nbOfRemovedConfigurations;
    }

    @Override
    public String getDescription() {
        return String.format(Messages.definitionRemovedDescription, definition.getId(), definition.getVersion(),
                nbOfRemovedConfigurations);
    }

    @Override
    public List<ChangePreview> getDetails() {
        return List.of();
    }

    @Override
    public Kind getKind() {
        return Kind.REMOVE;
    }

    @Override
    public boolean hasBreakingChanges() {
        return false;
    }

    @Override
    public ChangePreview getParent() {
        return null;
    }

    @Override
    public boolean showInPreviewDialog() {
        return nbOfRemovedConfigurations > 0;
    }

}
