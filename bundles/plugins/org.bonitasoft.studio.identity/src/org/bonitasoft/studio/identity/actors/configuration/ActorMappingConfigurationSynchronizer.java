/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.identity.actors.configuration;

import org.bonitasoft.studio.configuration.extension.IConfigurationSynchronizer;
import org.bonitasoft.studio.model.actormapping.ActorMapping;
import org.bonitasoft.studio.model.actormapping.ActorMappingFactory;
import org.bonitasoft.studio.model.actormapping.ActorMappingPackage;
import org.bonitasoft.studio.model.actormapping.ActorMappingsType;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationPackage;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Actor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * @author Romain Bioteau
 *
 */
public class ActorMappingConfigurationSynchronizer implements IConfigurationSynchronizer {


	private int added;

	@Override
	public void synchronize(Configuration configuration, AbstractProcess process, CompoundCommand cc, EditingDomain editingDomain) {
		addNewActors(configuration,process,editingDomain,cc);
		removeDeletedActors(configuration,process,editingDomain,cc) ;
	}

	private void addNewActors(Configuration configuration, AbstractProcess process, EditingDomain editingDomain, CompoundCommand cc) {
		added = 0;
		ActorMappingsType mappings = configuration.getActorMappings() ;
		boolean newMapping = false ;
		if(mappings == null && !process.getActors().isEmpty()){
			mappings = ActorMappingFactory.eINSTANCE.createActorMappingsType() ;
			newMapping = true ;
		}
		for(Actor actor : process.getActors()){
			String actorName = actor.getName() ;

			boolean exists = false ;
			for(ActorMapping mapping : mappings.getActorMapping()){
				if(mapping.getName().equals(actorName)){
					exists = true ;
					break ;
				}
			}
			if(!exists){
				ActorMapping mapping = ActorMappingFactory.eINSTANCE.createActorMapping() ;
				mapping.setName(actorName) ;
				mapping.setGroups( ActorMappingFactory.eINSTANCE.createGroups()) ;
				mapping.setRoles( ActorMappingFactory.eINSTANCE.createRoles()) ;
				mapping.setMemberships( ActorMappingFactory.eINSTANCE.createMembership()) ;
				mapping.setUsers( ActorMappingFactory.eINSTANCE.createUsers()) ;
				cc.append(AddCommand.create(editingDomain, mappings, ActorMappingPackage.Literals.ACTOR_MAPPINGS_TYPE__ACTOR_MAPPING, mapping)) ;
				added++;
			}
		}
		if(newMapping){
			cc.append(SetCommand.create(editingDomain, configuration, ConfigurationPackage.Literals.CONFIGURATION__ACTOR_MAPPINGS, mappings)) ;
		}
	}

	private void removeDeletedActors(Configuration configuration, AbstractProcess process, EditingDomain editingDomain, CompoundCommand cc) {
		ActorMappingsType mappings = configuration.getActorMappings() ;
		if(mappings != null){
			int removed = 0;
			for(ActorMapping mapping : mappings.getActorMapping()){
				String actorName = mapping.getName() ;
				boolean exists = false ;
				for(Actor actor : process.getActors()){
					if(actor.getName().equals(actorName)){
						exists = true ;
						break ;
					}
				}
				if(!exists){
					removed++;
					cc.append(RemoveCommand.create(editingDomain, mappings, ActorMappingPackage.Literals.ACTOR_MAPPINGS_TYPE__ACTOR_MAPPING, mapping)) ;
				}
			}
			if(removed > 0 ){
				if(mappings.getActorMapping().size() == removed + added){
					cc.append(SetCommand.create(editingDomain, configuration, ConfigurationPackage.Literals.CONFIGURATION__ACTOR_MAPPINGS,   null )) ;
				}
			}
		}

	}

	@Override
	public String getFragmentContainerId() {
		return null;
	}

	@Override
	public EStructuralFeature getDependencyKind() {
		return null;
	}


}
