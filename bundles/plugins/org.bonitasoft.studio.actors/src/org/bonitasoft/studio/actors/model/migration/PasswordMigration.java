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
package org.bonitasoft.studio.actors.model.migration;

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
public class PasswordMigration extends CustomMigration {

	private Map<Instance,String> passwords = new HashMap<Instance, String>();

	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance user : model.getAllInstances("organization.User")){
			String password = user.get("password");
			if(password != null && !password.isEmpty()){
				user.set("password", null);
				passwords.put(user, password);
			}
		}
	}

	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance user : model.getAllInstances("organization.User")){
			if(passwords.containsKey(user)){
				Instance passwordType = model.newInstance("organization.PasswordType");
				passwordType.set("value", passwords.get(user));
				passwordType.set("encrypted", false);
				user.set("password", passwordType);
			}
		}
	}

}
