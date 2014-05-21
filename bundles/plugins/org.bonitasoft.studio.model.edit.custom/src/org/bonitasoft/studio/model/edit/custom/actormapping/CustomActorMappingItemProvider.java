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


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.model.actormapping.ActorMapping;
import org.bonitasoft.studio.model.actormapping.Groups;
import org.bonitasoft.studio.model.actormapping.Membership;
import org.bonitasoft.studio.model.actormapping.Roles;
import org.bonitasoft.studio.model.actormapping.Users;
import org.bonitasoft.studio.model.actormapping.provider.ActorMappingItemProvider;
import org.eclipse.emf.common.notify.AdapterFactory;


public class CustomActorMappingItemProvider extends ActorMappingItemProvider {

	public CustomActorMappingItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
	
	@Override
	public String getText(Object object) {
		String label = ((ActorMapping)object).getName();
		return label == null || label.length() == 0 ? super.getText(object) : label; //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	public Collection<?> getElements(Object object) {
		List<Object> result = new ArrayList<Object>() ;
		if(object instanceof ActorMapping){
			result.add(((ActorMapping) object).getGroups()) ;
			result.add(((ActorMapping) object).getRoles()) ;
			result.add(((ActorMapping) object).getMemberships()) ;
			result.add(((ActorMapping) object).getUsers()) ;
			return result;
		}
	
		return getChildren(object);
	}
	
	@Override
	public Collection<?> getChildren(Object object) {
		if(object instanceof Groups){
			return ((Groups) object).getGroup() ;
		}else if(object instanceof Roles){
			return ((Roles) object).getRole() ;
		}else if(object instanceof Users){
			return ((Users) object).getUser() ;
		}else if(object instanceof Membership){
			return ((Membership) object).getMembership() ;
		}
		return super.getChildren(object);
	}

}
