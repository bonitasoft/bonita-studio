/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.migration.custom.migration.connector;

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Romain Bioteau
 *
 */
public class ConnectorIgnoreErrorMigration extends CustomMigration {

	private Map<String, Boolean> ignoreValue = new HashMap<String, Boolean>();

	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {
		for (Instance connectorInstance : model.getAllInstances("process.Connector")){
			ignoreValue.put(connectorInstance.getUuid(),(Boolean) connectorInstance.get("ignoreErrors"));
		}
	}
	
	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for (Instance connectorInstance : model.getAllInstances("process.Connector")){
			Boolean value = ignoreValue.get(connectorInstance.getUuid());
			connectorInstance.unset(ProcessPackage.Literals.CONNECTOR__IGNORE_ERRORS);
			connectorInstance.set("ignoreErrors", value);
		}
	}
	
}
