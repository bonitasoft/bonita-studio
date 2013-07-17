/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.importer.bar.custom.migration;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.importer.bar.custom.migration.connector.Connector5Descriptor;
import org.bonitasoft.studio.importer.bar.i18n.Messages;
import org.bonitasoft.studio.migration.migrator.ReportCustomMigration;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Metamodel;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.Model;

/**
 * @author Romain Bioteau
 *
 */
public class ConnectorMigration extends ReportCustomMigration {

	private List<Connector5Descriptor> descriptors = new ArrayList<Connector5Descriptor>();

	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance connector : model.getAllInstances("process.Connector")){
			if(connector.getUuid() == null){
				connector.setUuid(EcoreUtil.generateUUID());
			}
			final Connector5Descriptor connectorDescriptor = new Connector5Descriptor(connector);
			if(connectorDescriptor.canBeMigrated()){
				descriptors.add(connectorDescriptor);
			}else{
				addReportChange((String) connector.get("name"),connector.getType().getEClass().getName(), connector.getContainer().getUuid(), Messages.removeConnectorMigrationDescription, Messages.connectorProperty, IStatus.ERROR);
				model.delete(connector);
			}
		}
	}
	
	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance connector : model.getAllInstances("process.Connector")){
			for(Connector5Descriptor descriptor : descriptors){
				if(descriptor.appliesTo(connector)){
					descriptor.migrate(model, connector, getConverter(model, getScope(connector)));
					addReportChange((String) connector.get("name"),connector.getType().getEClass().getName(), connector.getContainer().getUuid(), Messages.connectorMigrationDescription, Messages.connectorProperty, IStatus.WARNING);
				}
			}
		}
	}
}
