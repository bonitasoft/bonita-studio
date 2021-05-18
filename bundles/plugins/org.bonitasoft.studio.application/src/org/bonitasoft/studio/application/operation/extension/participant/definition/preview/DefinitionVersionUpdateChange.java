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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.repository.extension.update.preview.ChangePreview;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.migration.ConnectorConfigurationMigrator;
import org.bonitasoft.studio.connector.model.definition.migration.DefinitionChangesVisitor;
import org.bonitasoft.studio.connector.model.i18n.Messages;

public class DefinitionVersionUpdateChange implements ChangePreview {

    private ConnectorConfigurationMigrator migrator;
    private long nbOfConfigurationsToUpdate;
    private long nbOfConfigurationsWithBreakingChanges;

    public DefinitionVersionUpdateChange(ConnectorConfigurationMigrator migrator,
            long nbOfConfigurationsToUpdate,
            long nbOfConfigurationsWithBreakingChanges) {
        this.migrator = migrator;
        this.nbOfConfigurationsToUpdate = nbOfConfigurationsToUpdate;
        this.nbOfConfigurationsWithBreakingChanges = nbOfConfigurationsWithBreakingChanges;
    }

    @Override
    public String getDescription() {
        ConnectorDefinition targetDefinition = migrator.getTargetDefinition();
        return String.format(Messages.definitionUpdateChangeDescription,
                targetDefinition.getId(),
                migrator.getFromVersion(),
                targetDefinition.getVersion(),
                nbOfConfigurationsToUpdate);
    }

    @Override
    public List<ChangePreview> getDetails() {
        DefinitionChangesVisitor definitionChangesVisitor = migrator.getVisitor();
        return Stream.concat(definitionChangesVisitor.getInputChanges().stream()
                .map(c -> new InputChange(this, c)),
                definitionChangesVisitor.getOutputChanges().stream()
                        .map(c -> new OutputChange(this, c)))
                .collect(Collectors.toList());
    }

    @Override
    public Kind getKind() {
        return Kind.UPDATE;
    }

    @Override
    public boolean hasBreakingChanges() {
        return nbOfConfigurationsWithBreakingChanges > 0;
    }

    @Override
    public ChangePreview getParent() {
        return null;
    }

    @Override
    public boolean showInPreviewDialog() {
        return nbOfConfigurationsToUpdate > 0;
    }

}
