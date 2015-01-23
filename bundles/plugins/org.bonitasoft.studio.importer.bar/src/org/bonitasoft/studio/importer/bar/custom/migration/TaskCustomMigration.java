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

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.migration.migrator.ReportCustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Romain Bioteau
 *
 */
public class TaskCustomMigration extends ReportCustomMigration {

	private Map<String, Boolean> overrideLanes = new HashMap<String,Boolean>();
	
	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance task : model.getAllInstances("process.Task")){
			boolean isSet = task.isSet(model.getMetamodel().getEAttribute("process.Task.overrideGroupsOfTheLane"));
			if(isSet){
				overrideLanes.put(task.getUuid(),(Boolean) task.get("overrideGroupsOfTheLane"));
			}else{
				overrideLanes.put(task.getUuid(),false);
			}
		}
	}
	
	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance task : model.getAllInstances("process.Task")){
			if(overrideLanes.containsKey(task.getUuid())){
				boolean override = overrideLanes.get(task.getUuid());
				task.set("overrideActorsOfTheLane", override);
			}
		}
	}
	
}
