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


import org.bonitasoft.studio.model.actormapping.MembershipType;
import org.bonitasoft.studio.model.actormapping.provider.MembershipTypeItemProvider;
import org.bonitasoft.studio.model.edit.custom.i18n.Messages;
import org.eclipse.emf.common.notify.AdapterFactory;

public class CustomMembershipTypeItemProvider extends MembershipTypeItemProvider {

	public CustomMembershipTypeItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
	
	@Override
	public String getText(Object object) {
		MembershipType type = (MembershipType) object ;
		String path = type.getGroup() ;
		String groupName = "" ;
		if(path != null && !path.isEmpty()){
			String[] split = path.split("/") ;
			groupName = split[split.length-1] ;
		}
		
		return Messages.belongsTo + " " + groupName + " " + Messages.havingRole +" " + type.getRole()  ;
	}
	

}
