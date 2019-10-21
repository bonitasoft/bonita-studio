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
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

public abstract class UpdateConnectorDefinitionMigration extends CustomMigration {

    protected static final String VERSION_FEATURE_NAME = "version";
    protected static final String DEFINITION_VERSION_FEATURE_NAME = "definitionVersion";
    protected static final String DEFINITION_ID_FEATURE_NAME = "definitionId";

    @Override
    public void migrateAfter(final Model model, final Metamodel metamodel) throws MigrationException {
        for (final Instance connectorInstance : model.getAllInstances("process.Connector")) {
            final String defId = connectorInstance.get(DEFINITION_ID_FEATURE_NAME);
            if (shouldUpdateId(defId)) {
                final String newDefinitionId = getNewDefinitionId();
                if (newDefinitionId == null) {
                    throw new IllegalStateException(
                            String.format("A new definition id must be provided to migrate '%s'", defId));
                }
                connectorInstance.set(DEFINITION_ID_FEATURE_NAME, newDefinitionId);
            }
            if (shouldUpdateVersion(defId)) {
                updateVersion(connectorInstance);
            }
        }
        for (final Instance connectorConfigInstance : model
                .getAllInstances("connectorconfiguration.ConnectorConfiguration")) {
            final String defId = connectorConfigInstance.get(DEFINITION_ID_FEATURE_NAME);
            if (shouldUpdateId(defId)) {
                final String newDefinitionId = getNewDefinitionId();
                if (newDefinitionId == null) {
                    throw new IllegalStateException(
                            String.format("A new definition id must be provided to migrate '%s'", defId));
                }
                connectorConfigInstance.set(DEFINITION_ID_FEATURE_NAME, newDefinitionId);
            }
            if (shouldUpdateVersion(defId)) {
                updateConfigVersion(connectorConfigInstance);
            }
        }
    }

    private void updateVersion(final Instance connectorInstance) {
        final String defVersion = connectorInstance.get(DEFINITION_VERSION_FEATURE_NAME);
        final String previousDefinitionVersion = getOldDefinitionVersion();
        if (defVersion.equals(previousDefinitionVersion)) {
            connectorInstance.set(DEFINITION_VERSION_FEATURE_NAME, getNewDefinitionVersion());
        }
    }

    private void updateConfigVersion(final Instance connectorConfigInstance) {
        final String defVersion = connectorConfigInstance.get(VERSION_FEATURE_NAME);
        final String previousDefinitionVersion = getOldDefinitionVersion();
        if (defVersion.equals(previousDefinitionVersion)) {
            connectorConfigInstance.set(VERSION_FEATURE_NAME, getNewDefinitionVersion());
        }
    }

    protected boolean shouldUpdateId(String defId) {
        return false;
    }

    protected String getNewDefinitionId() {
        return null;
    }

    protected abstract boolean shouldUpdateVersion(final String defId);

    protected abstract String getNewDefinitionVersion();

    protected abstract String getOldDefinitionVersion();

}
