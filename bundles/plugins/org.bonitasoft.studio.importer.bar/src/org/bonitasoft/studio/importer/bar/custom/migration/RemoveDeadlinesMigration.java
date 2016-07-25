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

import org.bonitasoft.studio.importer.bar.i18n.Messages;
import org.bonitasoft.studio.migration.migrator.ReportCustomMigration;
import org.bonitasoft.studio.migration.utils.DeadlineMigrationStore;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Romain Bioteau
 *
 */
public class RemoveDeadlinesMigration extends ReportCustomMigration {

	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance deadline : model.getAllInstances("process.Deadline")){
			Instance connector =  deadline.get("connector");
			Instance container = deadline.getContainer();
			if(container != null && (container.instanceOf("process.Task") || container.instanceOf("process.CallActivity"))){
				DeadlineMigrationStore.addDeadline(container.getUuid(),deadline);
				addReportChange((String) connector.get("name"),"Deadline", deadline.getContainer().getUuid(), Messages.deadlinesToNonInterruptingEventMigrationDescription, Messages.connectorProperty, IStatus.ERROR);
			}else{
				addReportChange((String) connector.get("name"),"Deadline", deadline.getContainer().getUuid(), Messages.removeDeadlinesMigrationDescription, Messages.connectorProperty, IStatus.ERROR);
			}
			model.delete(deadline);
		}
	}


}
