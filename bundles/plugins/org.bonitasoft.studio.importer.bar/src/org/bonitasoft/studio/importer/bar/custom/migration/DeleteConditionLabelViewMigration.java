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

import org.bonitasoft.studio.migration.migrator.ReportCustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;
import org.eclipse.gmf.runtime.notation.NotationPackage;


/**
 * @author Romain Bioteau
 *
 */
public class DeleteConditionLabelViewMigration extends ReportCustomMigration {

    private static final String VIEW_TYPE = "6002";

    
    @Override
    public void migrateBefore(Model model, Metamodel metamodel) throws MigrationException {
        for(Instance decorator :model.getAllInstances(NotationPackage.Literals.DECORATION_NODE)){
            String viewType = decorator.get(NotationPackage.Literals.VIEW__TYPE);
            if(VIEW_TYPE.equals(viewType)){
            	model.delete(decorator);
            }
        }
    }

    @Override
    public void migrateAfter(Model model, Metamodel metamodel) throws MigrationException {
    

    }

}
