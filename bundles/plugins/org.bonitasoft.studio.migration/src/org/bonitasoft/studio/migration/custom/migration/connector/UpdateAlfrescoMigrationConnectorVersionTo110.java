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


public class UpdateAlfrescoMigrationConnectorVersionTo110 extends CustomMigration {

    @Override
    public void migrateAfter(final Model model, final Metamodel metamodel) throws MigrationException {
        for (final Instance connectorInstance : model.getAllInstances("process.Connector")) {
            final String defId = connectorInstance.get("definitionId");
            if (isProvidedAlfrescoConnectorDef(defId)) {
                updateVersion(connectorInstance);
            }
        }
    }

    private void updateVersion(final Instance connectorInstance) {
        final String defVersion = connectorInstance.get("definitionVersion");
        if (defVersion.equals("1.0.0")) {
            connectorInstance.set("definitionVersion", "1.1.0");
            final Instance connectorConfigInstance = connectorInstance.get("configuration");
            if (connectorConfigInstance != null) {
                connectorConfigInstance.set("version", "1.1.0");
            }
        }
    }

    private boolean isProvidedAlfrescoConnectorDef(final String defId) {
        return defId.equals("Alfresco34CreateFolderByPath") ||
                defId.equals("Alfresco34DeleteFileByPath") ||
                defId.equals("Alfresco34DeleteItemById") ||
                defId.equals("Alfresco34UploadFileByPath") ||
                defId.equals("Alfresco42CreateFolderByPath") ||
                defId.equals("Alfresco42DeleteFileByPath") ||
                defId.equals("Alfresco42DeleteItemById") ||
                defId.equals("Alfresco42UploadFileByPath");
    }
}
