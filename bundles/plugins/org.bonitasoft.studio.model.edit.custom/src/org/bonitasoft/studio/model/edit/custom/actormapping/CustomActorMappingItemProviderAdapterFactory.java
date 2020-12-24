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

import org.bonitasoft.studio.model.actormapping.provider.ActorMappingItemProviderAdapterFactory;
import org.eclipse.emf.common.notify.Adapter;


public class CustomActorMappingItemProviderAdapterFactory extends ActorMappingItemProviderAdapterFactory {
	
	@Override
	public Adapter createActorMappingAdapter() {
		if (actorMappingItemProvider == null) {
			actorMappingItemProvider = new CustomActorMappingItemProvider(this);
		}

		return actorMappingItemProvider;
	}
	
	@Override
	public Adapter createGroupsAdapter() {
		if (groupsItemProvider == null) {
			groupsItemProvider = new CustomGroupsItemProvider(this);
		}

		return groupsItemProvider;
	}
	
	@Override
	public Adapter createRolesAdapter() {
		if (rolesItemProvider == null) {
			rolesItemProvider = new CustomRolesItemProvider(this);
		}

		return rolesItemProvider;
	}
	
	@Override
	public Adapter createUsersAdapter() {
		if (usersItemProvider == null) {
			usersItemProvider = new CustomUsersItemProvider(this);
		}

		return usersItemProvider;
	}
	
	@Override
	public Adapter createMembershipAdapter() {
		if (membershipItemProvider == null) {
			membershipItemProvider = new CustomMembershipItemProvider(this);
		}

		return membershipItemProvider;
	}
	
	@Override
	public Adapter createMembershipTypeAdapter() {
		if (membershipTypeItemProvider == null) {
			membershipTypeItemProvider = new CustomMembershipTypeItemProvider(this);
		}

		return membershipTypeItemProvider;
	}

}
