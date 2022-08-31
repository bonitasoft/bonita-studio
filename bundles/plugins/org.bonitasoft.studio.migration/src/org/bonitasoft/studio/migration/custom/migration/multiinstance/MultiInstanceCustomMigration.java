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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.migration.custom.migration.multiinstance;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;


/**
 * @author Romain Bioteau
 *
 */
public class MultiInstanceCustomMigration extends CustomMigration {

    private final Map<String, MultiInstanceMigrator> multiInstanceMigrators = new HashMap<String, MultiInstanceMigrator>();

    @Override
    public void migrateBefore(final Model model, final Metamodel metamodel) throws MigrationException {
        for (final Instance activityInstance : model.getAllInstances("process.Activity")) {
            final MultiInstanceMigrator multiInstanceMigrator = new MultiInstanceMigrator(activityInstance);
            multiInstanceMigrator.save(model, metamodel);
            multiInstanceMigrators.put(activityInstance.getUuid(), multiInstanceMigrator);
        }
    }

    @Override
    public void migrateAfter(final Model model, final Metamodel metamodel) throws MigrationException {
        for (final Instance activityInstance : model.getAllInstances("process.Activity")) {
            if (multiInstanceMigrators.containsKey(activityInstance.getUuid())) {
                multiInstanceMigrators.get(activityInstance.getUuid()).migrate(activityInstance, model, metamodel);
            }
        }
    }

}
