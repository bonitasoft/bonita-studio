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

public class UpdateAlfrescoConnectorDefinitionsMigration extends CustomMigration {

    protected static final String VERSION_FEATURE_NAME = "version";
    protected static final String DEFINITION_VERSION_FEATURE_NAME = "definitionVersion";
    protected static final String DEFINITION_ID_FEATURE_NAME = "definitionId";

    @Override
    public void migrateAfter(final Model model, final Metamodel metamodel) throws MigrationException {
        for (final Instance connectorInstance : model.getAllInstances("process.Connector")) {
            final String defId = connectorInstance.get(DEFINITION_ID_FEATURE_NAME);
            if (isCreateFolderByPathDef(defId)) {
                updateConnector(connectorInstance, "AlfrescoCreateFolderByPath", "2.0.0");
            }
            if (isDeleteFolderByPathDef(defId)) {
                updateConnector(connectorInstance, "AlfrescoDeleteFileByPath", "2.0.0");
            }
            if (isDeleteDeleteItemByIdDef(defId)) {
                updateConnector(connectorInstance, "AlfrescoDeleteItemById", "2.0.0");
            }
            if (isUploadFileByPathDef(defId)) {
                updateConnector(connectorInstance, "AlfrescoUploadFileByPath", "2.0.0");
            }
        }
        for (final Instance connectorInstance : model.getAllInstances("connectorconfiguration.ConnectorConfiguration")) {
            final String defId = connectorInstance.get(DEFINITION_ID_FEATURE_NAME);
            if (isCreateFolderByPathDef(defId)) {
                updateConfig(connectorInstance, "AlfrescoCreateFolderByPath", "2.0.0");
            }
            if (isDeleteFolderByPathDef(defId)) {
                updateConfig(connectorInstance, "AlfrescoDeleteFileByPath", "2.0.0");
            }
            if (isDeleteDeleteItemByIdDef(defId)) {
                updateConfig(connectorInstance, "AlfrescoDeleteItemById", "2.0.0");
            }
            if (isUploadFileByPathDef(defId)) {
                updateConfig(connectorInstance, "AlfrescoUploadFileByPath", "2.0.0");
            }
        }
    }

    private boolean isCreateFolderByPathDef(String defId) {
        return "Alfresco34CreateFolderByPath".equals(defId) || "Alfresco42CreateFolderByPath".equals(defId);
    }

    private boolean isDeleteFolderByPathDef(String defId) {
        return "Alfresco34DeleteFileByPath".equals(defId) || "Alfresco42DeleteFileByPath".equals(defId);
    }

    private boolean isDeleteDeleteItemByIdDef(String defId) {
        return "Alfresco34DeleteItemById".equals(defId) || "Alfresco42DeleteItemById".equals(defId);
    }

    private boolean isUploadFileByPathDef(String defId) {
        return "Alfresco34UploadFileByPath".equals(defId) || "Alfresco42UploadFileByPath".equals(defId);
    }

    private void updateConnector(final Instance connectorInstance, String newDefId, String newVersion) {
        connectorInstance.set(DEFINITION_VERSION_FEATURE_NAME, newVersion);
        connectorInstance.set(DEFINITION_ID_FEATURE_NAME, newDefId);
    }

    private void updateConfig(final Instance connectorConfigInstance, String newDefId, String newVersion) {
        connectorConfigInstance.set(VERSION_FEATURE_NAME, newVersion);
        connectorConfigInstance.set(DEFINITION_ID_FEATURE_NAME, newDefId);
    }



}
