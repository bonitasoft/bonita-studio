/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.importer.bar.custom.migration;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.importer.bar.custom.migration.connector.Connector5Descriptor;
import org.bonitasoft.studio.importer.bar.i18n.Messages;
import org.bonitasoft.studio.migration.MigrationPlugin;
import org.bonitasoft.studio.migration.migrator.ReportCustomMigration;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Romain Bioteau
 */
public class ConnectorMigration extends ReportCustomMigration {

    private final List<Connector5Descriptor> descriptors = new ArrayList<Connector5Descriptor>();

    @Override
    public void migrateBefore(final Model model, final Metamodel metamodel)
            throws MigrationException {
        for (final Instance connector : model.getAllInstances("process.Connector")) {
            if (connector.getContainer() != null) {
                if (connector.getUuid() == null) {
                    connector.setUuid(EcoreUtil.generateUUID());
                }
                final Connector5Descriptor connectorDescriptor = new Connector5Descriptor(connector);
                if (connectorDescriptor.canBeMigrated()) {
                    descriptors.add(connectorDescriptor);
                } else {
                    final String legacyConnectorID = connectorDescriptor.getLegacyConnectorID();
                    final String removeConnectorMigrationDescription = getMigrationReportMessageForNonMigratedConnector(legacyConnectorID);
                    addReportChange((String) connector.get("name"),
                            connector.getType().getEClass().getName(),
                            connector.getContainer().getUuid(),
                            removeConnectorMigrationDescription,
                            Messages.connectorProperty,
                            IStatus.ERROR);
                    model.delete(connector);
                }
            } else {
                BonitaStudioLog.debug("Connector instance has no container: " + connector.get("name"), MigrationPlugin.PLUGIN_ID);
                model.delete(connector);
            }
        }
    }

    private String getMigrationReportMessageForNonMigratedConnector(final String legacyConnectorID) {
        String removeConnectorMigrationDescription = Messages.removeConnectorMigrationDescription;
        if ("StartTask".equals(legacyConnectorID)) {
            // No specific message known
        } else if ("FinishTask".equals(legacyConnectorID)) {
            removeConnectorMigrationDescription += "\n" + Messages.connectorMigrationFinishTaskDescription;
        } else if ("ExecuteTask".equals(legacyConnectorID)) {
            // No specific message known
        } else if ("startInstanceConnector".equals(legacyConnectorID)) {
            removeConnectorMigrationDescription += "\n" + Messages.connectorMigrationStartInstanceDescription;
        } else if ("getUser".equals(legacyConnectorID)) {
            removeConnectorMigrationDescription += "\n" + Messages.connectorMigrationUseScriptAndApiDescription;
        } else if ("getTaskAuthor".equals(legacyConnectorID)) {
            removeConnectorMigrationDescription += "\n" + Messages.connectorMigrationUseScriptAndApiDescription;
        } else if ("getProcessInstanceInitiator".equals(legacyConnectorID)) {
            removeConnectorMigrationDescription += "\n" + Messages.connectorMigrationUseScriptAndApiDescription;
        } else if ("DeleteDocuments".equals(legacyConnectorID)) {
            // No specific message known
        } else if ("DeleteDocument".equals(legacyConnectorID)) {
            // No specific message known
        } else if ("AddDocumentVersion".equals(legacyConnectorID)) {
            // No specific message known
        } else if ("AddDocuments".equals(legacyConnectorID)) {
            // No specific message known
        } else if ("BS-AddAttachments".equals(legacyConnectorID)) {
            // No specific message known
        } else if ("GetDocumentVersions".equals(legacyConnectorID)) {
            // No specific message known
        } else if ("GetDocuments".equals(legacyConnectorID)) {
            // No specific message known
        } else if ("GetDocumentContent".equals(legacyConnectorID)) {
            // No specific message known
        } else if ("GetDocument".equals(legacyConnectorID)) {
            // No specific message known
        } else if ("AddComment".equals(legacyConnectorID)) {
            // No specific message known
        } else {
            removeConnectorMigrationDescription = Messages.customConnectorMigrationDescription;
        }
        return removeConnectorMigrationDescription;
    }

    @Override
    public void migrateAfter(final Model model, final Metamodel metamodel)
            throws MigrationException {
        for (final Instance connector : model.getAllInstances("process.Connector")) {
            for (final Connector5Descriptor descriptor : descriptors) {
                if (descriptor.appliesTo(connector)) {
                    final String connectorName = (String) connector.get("name");
                    final String connectorTypeName = connector.getType().getEClass().getName();
                    final String connectorContainerUUID = connector.getContainer().getUuid();
                    descriptor.migrate(model, connector, getConverter(model, getScope(connector)));
                    if (descriptor.isBonitaSetVarConnector()) {
                        addReportChange(
                                connectorName,
                                connectorTypeName,
                                connectorContainerUUID,
                                descriptor.getReportChangeMessage(),
                                Messages.connectorProperty,
                                IStatus.WARNING);
                    } else {
                        addReportChange(
                                connectorName,
                                connectorTypeName,
                                connectorContainerUUID,
                                descriptor.getReportChangeMessage(),
                                Messages.connectorProperty,
                                IStatus.WARNING);
                    }
                }
            }
        }
    }
}
