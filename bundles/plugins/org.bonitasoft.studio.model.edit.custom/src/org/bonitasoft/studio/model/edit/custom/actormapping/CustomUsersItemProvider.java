/**
 * Copyright (C) 2009-2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.model.edit.custom.actormapping;


import java.util.Collection;

import org.bonitasoft.studio.model.actormapping.Users;
import org.bonitasoft.studio.model.actormapping.provider.UsersItemProvider;
import org.eclipse.emf.common.notify.AdapterFactory;


public class CustomUsersItemProvider extends UsersItemProvider {

	public CustomUsersItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
	@Override
	public Collection<?> getChildren(Object object) {
		return ((Users)object).getUser();
	}
	
}
