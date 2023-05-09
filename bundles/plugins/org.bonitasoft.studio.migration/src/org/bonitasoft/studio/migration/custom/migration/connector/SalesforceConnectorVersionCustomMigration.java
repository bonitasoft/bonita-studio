/**
 * Copyright (C) 2013 BonitaSoft S.A.
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

/**
 * @author Romain Bioteau
 */
public class SalesforceConnectorVersionCustomMigration extends CustomMigration {

    @Override
    public void migrateAfter(Model model, Metamodel metamodel)
            throws MigrationException {
        for (Instance connectorInstance : model.getAllInstances("process.Connector")) {
            String defId = connectorInstance.get("definitionId");
            String defVersion = connectorInstance.get("definitionVersion");
            if (isProvidedSalesforceConnectorDef(defId) && defVersion.equals("1.0.0")) {
                connectorInstance.set("definitionVersion", "1.0.1");
            }
        }
    }

    private boolean isProvidedSalesforceConnectorDef(String defId) {
        return defId.equals("salesforce-createsobject") ||
                defId.equals("salesforce-querysobjects") ||
                defId.equals("salesforce-deletesobjects") ||
                defId.equals("salesforce-retrievesobjects") ||
                defId.equals("salesforce-updatesobject");
    }

}
