/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 *
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

import org.bonitasoft.studio.exporter.Messages;
import org.bonitasoft.studio.migration.migrator.ReportCustomMigration;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;
/**
 * @author Florine Boudin
 *
 */
public class AutoLoginMigration extends ReportCustomMigration {


	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {

		for(Instance processApplication : model.getAllInstances("process.ProcessApplication")){
			final boolean isAutoLogin = processApplication.get("autoLogin");
			final String autoLoginId = processApplication.get("autoLoginId");
			if(isAutoLogin ){
				if(autoLoginId!=null && !autoLoginId.isEmpty()){
					addReportChange(	(String) processApplication.get("name"),
							ProcessPackage.Literals.POOL.getName(),
							processApplication.getUuid(),
							Messages.autoLoginMessageMigration,
							Messages.autoLoginNameMigration,
							IStatus.ERROR);
				}
			}
		}
	}
}
