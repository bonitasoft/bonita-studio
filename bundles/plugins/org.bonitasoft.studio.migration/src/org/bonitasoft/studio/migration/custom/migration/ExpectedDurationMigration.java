/**
 * Copyright (C) 2016 BonitaSoft S.A.
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
package org.bonitasoft.studio.migration.custom.migration;

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.migration.utils.StringToExpressionConverter;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

public class ExpectedDurationMigration extends CustomMigration {

    protected Map<String, String> taskDurations = new HashMap<String, String>();

    @Override
    public void migrateBefore(Model model, Metamodel metamodel) throws MigrationException {
        for (Instance task : model.getAllInstances("process.Task")) {
            String duration = task.get("duration");
            if (duration != null) {
                taskDurations.put(task.getUuid(), (String) duration);
            }
        }
    }

    
    @Override
    public void migrateAfter(Model model, Metamodel metamodel) throws MigrationException {
        for (Instance task : model.getAllInstances("process.Task")) {
            Instance expression = task.get("expectedDuration");
            if (expression == null) {
                String duration = taskDurations.get(task.getUuid());
                if(duration != null){
                    expression = StringToExpressionConverter.createExpressionInstance(model, duration, duration, Long.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
                }
            }
            task.set("expectedDuration", expression);
        }
    }
}
