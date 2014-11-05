/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.migration.custom.migration.connector;

import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Metamodel;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.Model;

public abstract class UpdateConnectorVersionMigration extends CustomMigration {

    public UpdateConnectorVersionMigration() {
        super();
    }

    @Override
    public void migrateAfter(final Model model, final Metamodel metamodel) throws MigrationException {
        for (final Instance connectorInstance : model.getAllInstances("process.Connector")) {
            final String defId = connectorInstance.get("definitionId");
            if (shouldUpdateVersion(defId)) {
                updateVersion(connectorInstance);
            }
        }
        for (final Instance connectorInstance : model.getAllInstances("connectorconfiguration.ConnectorConfiguration")) {
            final String defId = connectorInstance.get("definitionId");
            if (shouldUpdateVersion(defId)) {
                updateConfigVersion(connectorInstance);
            }
        }
    }

    private void updateVersion(final Instance connectorInstance) {
        final String defVersion = connectorInstance.get("definitionVersion");
        final String previousDefinitionVersion = getOldDefinitionVersion();
        if (defVersion.equals(previousDefinitionVersion)) {
            connectorInstance.set("definitionVersion", getNewDefinitionVersion());
        }
    }

    private void updateConfigVersion(final Instance connectorConfigInstance) {
        final String defVersion = connectorConfigInstance.get("version");
        final String previousDefinitionVersion = getOldDefinitionVersion();
        if (defVersion.equals(previousDefinitionVersion)) {
            connectorConfigInstance.set("version", getNewDefinitionVersion());
        }
    }

    protected abstract boolean shouldUpdateVersion(final String defId);

    protected abstract String getNewDefinitionVersion();

    protected abstract String getOldDefinitionVersion();

}
