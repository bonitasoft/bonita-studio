/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.migration.custom.migration;

import java.util.List;

import org.bonitasoft.studio.common.DataTypeLabels;
import org.bonitasoft.studio.common.NamingUtils;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Romain Bioteau
 *
 */
public class BusinessObjectTypeCustomMigration extends CustomMigration {

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edapt.migration.CustomMigration#migrateAfter(org.eclipse.emf.edapt.spi.migration.Model, org.eclipse.emf.edapt.migration.Metamodel)
	 */
	@Override
	public void migrateAfter(final Model model, final Metamodel metamodel)
			throws MigrationException {
		for(final Instance diagramInstance : model.getAllInstances("process.MainProcess")){
			final List<Instance> datatypes = diagramInstance.get("datatypes");
			if(!containsBusinessObjectType(datatypes)){
				final Instance datatypeInstance = model.newInstance("process.BusinessObjectType");
				datatypeInstance.set("name",NamingUtils.convertToId(DataTypeLabels.businessObjectType));
				diagramInstance.add("datatypes", datatypeInstance);
			}

		}
	}

	private boolean containsBusinessObjectType(final List<Instance> datatypes) {
		for(final Instance dt : datatypes){
			if(dt.instanceOf("process.BusinessObjectType")){
				return true;
			}
		}
		return false;
	}


}
