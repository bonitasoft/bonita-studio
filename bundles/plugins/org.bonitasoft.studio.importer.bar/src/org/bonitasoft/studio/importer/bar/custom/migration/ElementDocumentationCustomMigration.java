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
package org.bonitasoft.studio.importer.bar.custom.migration;

import org.bonitasoft.studio.importer.bar.i18n.Messages;
import org.bonitasoft.studio.migration.migrator.ReportCustomMigration;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Romain Bioteau
 *
 */
public class ElementDocumentationCustomMigration extends ReportCustomMigration {

	private static final int DOCUMENTATION_MAX_LENGTH = 254;

	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance element : model.getAllInstances("process.Element")){
			String documentation = element.get("documentation");
			if(documentation.length() > DOCUMENTATION_MAX_LENGTH){
				documentation = documentation.substring(0, DOCUMENTATION_MAX_LENGTH-1);
				element.set("documentation", documentation);
				addReportChange((String) element.get("name"), 
						element.getEClass().getName(),
						element.getUuid(), 
						Messages.tooLongDescriptionHasBeenTruncated, 
						Messages.descripitonProperty, 
						IStatus.WARNING);
			}
			
		}
	}
	
}
