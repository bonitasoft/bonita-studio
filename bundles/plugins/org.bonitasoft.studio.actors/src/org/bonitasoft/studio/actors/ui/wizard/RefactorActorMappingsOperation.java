/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.actors.ui.wizard;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.model.organization.Group;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.model.organization.OrganizationPackage;
import org.bonitasoft.studio.actors.model.organization.Role;
import org.bonitasoft.studio.actors.model.organization.User;
import org.bonitasoft.studio.actors.ui.wizard.page.GroupContentProvider;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.model.actormapping.ActorMappingsType;
import org.bonitasoft.studio.model.actormapping.Groups;
import org.bonitasoft.studio.model.actormapping.Membership;
import org.bonitasoft.studio.model.actormapping.MembershipType;
import org.bonitasoft.studio.model.actormapping.Roles;
import org.bonitasoft.studio.model.actormapping.Users;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.diff.metamodel.DiffPackage;
import org.eclipse.emf.compare.diff.metamodel.ModelElementChangeLeftTarget;
import org.eclipse.emf.compare.diff.metamodel.ModelElementChangeRightTarget;
import org.eclipse.emf.compare.diff.metamodel.UpdateAttribute;
import org.eclipse.emf.compare.diff.service.DiffService;
import org.eclipse.emf.compare.match.metamodel.MatchModel;
import org.eclipse.emf.compare.match.service.MatchService;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.operation.IRunnableWithProgress;

/**
 * @author Romain Bioteau
 *
 */
public class RefactorActorMappingsOperation implements IRunnableWithProgress {

	private Organization oldOrganization;
	private Organization newOrganization;

	public RefactorActorMappingsOperation(Organization oldOrganization,
			Organization newOrganization) {
		this.oldOrganization = oldOrganization;
		this.newOrganization = newOrganization;
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		monitor.beginTask(Messages.refactoringActorMappings,IProgressMonitor.UNKNOWN);
	
		ProcessConfigurationRepositoryStore confStore = (ProcessConfigurationRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ProcessConfigurationRepositoryStore.class);
		DiagramRepositoryStore diagramStore = (DiagramRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
		List<ActorMappingsType> actorMappings = getAllActorMappings(confStore, diagramStore);

		// Matching model elements
		MatchModel match = MatchService.doMatch(newOrganization,oldOrganization, Collections.<String, Object> emptyMap());
		// Computing differences
		DiffModel diff = DiffService.doDiff(match, false);
		// Merges all differences from model1 to model2
		List<DiffElement> differences = new ArrayList<DiffElement>(diff.getOwnedElements());
		for(DiffElement difference : differences){
			List<UpdateAttribute> updatedAttributes = ModelHelper.getAllItemsOfType(difference, DiffPackage.Literals.UPDATE_ATTRIBUTE);
			for(UpdateAttribute updatedAttribute : updatedAttributes){
				EObject oldElement = updatedAttribute.getRightElement();
				EObject newElement = updatedAttribute.getLeftElement();
				if(updatedAttribute.getAttribute().equals(OrganizationPackage.Literals.GROUP__NAME)){
					refactorGroup((Group)oldElement,(Group)newElement,actorMappings);
					refactorMembership((Group)oldElement,(Group)newElement,actorMappings);
				}else if(updatedAttribute.getAttribute().equals(OrganizationPackage.Literals.ROLE__NAME)){
					refactorRole((Role)oldElement,(Role)newElement,actorMappings);
					refactorMembership((Role)oldElement,(Role)newElement,actorMappings);
				}else if(updatedAttribute.getAttribute().equals(OrganizationPackage.Literals.USER__USER_NAME)){
					refactorUsername((User)oldElement,(User)newElement,actorMappings);
				}else if(updatedAttribute.getAttribute().equals(OrganizationPackage.Literals.MEMBERSHIP__USER_NAME)){
					refactorUsername((org.bonitasoft.studio.actors.model.organization.Membership)oldElement,(org.bonitasoft.studio.actors.model.organization.Membership)newElement,actorMappings);
				}else if(updatedAttribute.getAttribute().equals(OrganizationPackage.Literals.MEMBERSHIP__GROUP_NAME)){
					refactorGroup((org.bonitasoft.studio.actors.model.organization.Membership)oldElement,(org.bonitasoft.studio.actors.model.organization.Membership)newElement,actorMappings);
				}else if(updatedAttribute.getAttribute().equals(OrganizationPackage.Literals.GROUP__PARENT_PATH)){
					refactorGroup((Group)oldElement,(Group)newElement,actorMappings);
					refactorMembership((Group)oldElement,(Group)newElement,actorMappings);
				}else if(updatedAttribute.getAttribute().equals(OrganizationPackage.Literals.MEMBERSHIP__GROUP_PARENT_PATH)){
					refactorGroup((org.bonitasoft.studio.actors.model.organization.Membership)oldElement,(org.bonitasoft.studio.actors.model.organization.Membership)newElement,actorMappings);
				}
			}
			List<ModelElementChangeLeftTarget> newElementChange = ModelHelper.getAllItemsOfType(difference, DiffPackage.Literals.MODEL_ELEMENT_CHANGE_LEFT_TARGET);
			List<ModelElementChangeRightTarget> oldElementChange = ModelHelper.getAllItemsOfType(difference, DiffPackage.Literals.MODEL_ELEMENT_CHANGE_RIGHT_TARGET);
			if(!newElementChange.isEmpty() && !oldElementChange.isEmpty()){
				ModelElementChangeLeftTarget leftTarget = newElementChange.get(0);
				ModelElementChangeRightTarget rightTarget = oldElementChange.get(0);
				EObject newEObject = leftTarget.getLeftElement();
				EObject oldEObject = rightTarget.getRightElement();
				if(newEObject instanceof Group && oldEObject instanceof Group){
					refactorGroup((Group)oldEObject,(Group)newEObject,actorMappings);
					refactorMembership((Group)oldEObject,(Group)newEObject,actorMappings);
				}
				if(newEObject instanceof Role && oldEObject instanceof Role){
					refactorRole((Role)oldEObject,(Role)newEObject,actorMappings);
					refactorMembership((Role)oldEObject,(Role)newEObject,actorMappings);
				}
				if(newEObject instanceof User && oldEObject instanceof User){
					refactorUsername((User)oldEObject,(User)newEObject,actorMappings);
				}
				if(newEObject instanceof Membership && oldEObject instanceof Membership){
					refactorUsername((org.bonitasoft.studio.actors.model.organization.Membership)oldEObject,(org.bonitasoft.studio.actors.model.organization.Membership)newEObject,actorMappings);
					refactorGroup((org.bonitasoft.studio.actors.model.organization.Membership)oldEObject,(org.bonitasoft.studio.actors.model.organization.Membership)newEObject,actorMappings);
				}
			}
		}
		diagramStore.refresh();
	}
	

	protected void refactorUsername(
			org.bonitasoft.studio.actors.model.organization.Membership oldMembership,
			org.bonitasoft.studio.actors.model.organization.Membership newMembership,
			List<ActorMappingsType> actorMappings) {
		for(ActorMappingsType ac :actorMappings){
			if(!ac.getActorMapping().isEmpty()){
				Users users = ac.getActorMapping().get(0).getUsers();
				if(users != null){
					if(users.getUser().remove(oldMembership.getUserName())){
						users.getUser().add(newMembership.getUserName());
						saveChange(ac);
					}
				}
			}
		}
	}

	protected void refactorMembership(Role oldRole, Role newRole,
			List<ActorMappingsType> actorMappings) {
		for(ActorMappingsType ac :actorMappings){
			if(!ac.getActorMapping().isEmpty()){
				Membership membership = ac.getActorMapping().get(0).getMemberships();
				if(membership != null){
					for(MembershipType membershipType : membership.getMembership()){
						if(membershipType.getRole().equals(oldRole.getName())){
							membershipType.setRole(newRole.getName());
							saveChange(ac);
						}
					}
				}
			}
		}
	}

	protected void refactorMembership(Group oldGroup, Group newGroup,
			List<ActorMappingsType> actorMappings) {
		for(ActorMappingsType ac :actorMappings){
			if(!ac.getActorMapping().isEmpty()){
				Membership membership = ac.getActorMapping().get(0).getMemberships();
				if(membership != null){
					for(MembershipType membershipType : membership.getMembership()){
						if(membershipType.getGroup().equals(GroupContentProvider.getGroupPath(oldGroup))){
							membershipType.setGroup(GroupContentProvider.getGroupPath(newGroup));
							saveChange(ac);
						}
					}
				}
			}
		}
	}

	protected void refactorUsername(User oldUser, User newUser,
			List<ActorMappingsType> actorMappings) {
		for(ActorMappingsType ac :actorMappings){
			if(!ac.getActorMapping().isEmpty()){
				Users users = ac.getActorMapping().get(0).getUsers();
				if(users != null){
					if(users.getUser().remove(oldUser.getUserName())){
						users.getUser().add(newUser.getUserName());
						saveChange(ac);
					}
				}
			}
		}
	}

	protected void refactorRole(Role oldRole, Role newRole,
			List<ActorMappingsType> actorMappings) {
		for(ActorMappingsType ac :actorMappings){
			if(!ac.getActorMapping().isEmpty()){
				Roles roles = ac.getActorMapping().get(0).getRoles();
				if(roles != null){
					if(roles.getRole().remove(oldRole.getName())){
						roles.getRole().add(newRole.getName());
						saveChange(ac);
					}
				}
			}
		}
	}

	protected void refactorGroup(Group oldGroup, Group newGroup, List<ActorMappingsType> actorMappings) {
		for(ActorMappingsType ac :actorMappings){
			if(!ac.getActorMapping().isEmpty()){
				Groups groups = ac.getActorMapping().get(0).getGroups();
				if(groups != null){
					if(groups.getGroup().remove(GroupContentProvider.getGroupPath(oldGroup))){
						groups.getGroup().add(GroupContentProvider.getGroupPath(newGroup));
						saveChange(ac);
					}
				}
			}
		}
	}
	
	protected void refactorGroup(org.bonitasoft.studio.actors.model.organization.Membership oldMembership, org.bonitasoft.studio.actors.model.organization.Membership newMembership, List<ActorMappingsType> actorMappings) {
		for(ActorMappingsType ac :actorMappings){
			if(!ac.getActorMapping().isEmpty()){
				Groups groups = ac.getActorMapping().get(0).getGroups();
				if(groups != null){
					if(groups.getGroup().remove(GroupContentProvider.getGroupPath(oldMembership.getGroupName(),oldMembership.getGroupParentPath()))){
						groups.getGroup().add(GroupContentProvider.getGroupPath(newMembership.getGroupName(),newMembership.getGroupParentPath()));
						saveChange(ac);
					}
				}
			}
		}
	}


	private void saveChange(ActorMappingsType ac) {
		try {
			Resource eResource = ac.eResource();
			if(eResource != null){
				eResource.save(Collections.emptyMap());
			}
		} catch (IOException e) {
			BonitaStudioLog.error(e);
		}
	}

	protected List<ActorMappingsType> getAllActorMappings(
			ProcessConfigurationRepositoryStore confStore,
			DiagramRepositoryStore diagramStore) {
		List<ActorMappingsType> allActorMappings = new ArrayList<ActorMappingsType>();
		for(ProcessConfigurationFileStore fileStore : confStore.getChildren()){
			Configuration c = fileStore.getContent();
			if(c != null && c.getActorMappings() != null){
				allActorMappings.add(c.getActorMappings());
			}
		}
		for(DiagramFileStore fileStore : diagramStore.getChildren()){
			for(AbstractProcess process: fileStore.getProcesses()){
				for(Configuration c : process.getConfigurations()){
					if(c != null && c.getActorMappings() != null){
						allActorMappings.add(c.getActorMappings());
					}
				}
			}

		}
		return allActorMappings;
	}

}
