/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.migration.custom.migration.form;

import java.util.HashMap;

import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Florine Boudin
 *
 */
public class GroupIteratorMigration extends CustomMigration {

	HashMap<String, String> nameMap = new HashMap<String, String>();
	HashMap<String, String> classNameMap = new HashMap<String, String>();



	@Override
	public void migrateBefore(Model model, Metamodel metamodel) throws MigrationException {

		for(Instance instance : model.getAllInstances("form.Group")){
			String uuid = instance.getUuid();

			// Iterator name
			String itName = instance.get("iteratorName");
			if(itName!=null && !itName.isEmpty()){
				nameMap.put(uuid, itName);
			}

			// Iterator class name
			String itClassName = instance.get("iteratorClassName");
			if(itClassName !=null && !itClassName.isEmpty()){
				classNameMap.put(uuid, itClassName);
			}
		}
	}

	@Override
	public void migrateAfter(Model model, Metamodel metamodel) throws MigrationException {

		for(Instance group : model.getAllInstances("form.Group")){
			String uuid = group.getUuid();
			Instance instance = model.newInstance("form.GroupIterator");
			instance.set("name", "");
			if(nameMap.containsKey(uuid)){
				instance.set("name", nameMap.get(uuid));
			}
				instance.set("className", Object.class.getName());
			group.set("iterator", instance);
		}
	}

}
