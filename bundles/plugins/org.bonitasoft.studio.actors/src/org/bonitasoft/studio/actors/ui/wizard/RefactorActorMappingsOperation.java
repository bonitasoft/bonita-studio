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
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.compare.AttributeChange;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.match.DefaultComparisonFactory;
import org.eclipse.emf.compare.match.DefaultEqualityHelperFactory;
import org.eclipse.emf.compare.match.DefaultMatchEngine;
import org.eclipse.emf.compare.match.IComparisonFactory;
import org.eclipse.emf.compare.match.IMatchEngine;
import org.eclipse.emf.compare.match.eobject.IEObjectMatcher;
import org.eclipse.emf.compare.postprocessor.IPostProcessor;
import org.eclipse.emf.compare.rcp.EMFCompareRCPPlugin;
import org.eclipse.emf.compare.scope.DefaultComparisonScope;
import org.eclipse.emf.compare.scope.IComparisonScope;
import org.eclipse.emf.compare.utils.UseIdentifiers;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.operation.IRunnableWithProgress;

/**
 * @author Romain Bioteau
 *
 */
public class RefactorActorMappingsOperation implements IRunnableWithProgress {

	private final Organization oldOrganization;
	private final Organization newOrganization;

	public RefactorActorMappingsOperation(final Organization oldOrganization,
			final Organization newOrganization) {
		this.oldOrganization = oldOrganization;
		this.newOrganization = newOrganization;

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void run(final IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		monitor.beginTask(Messages.refactoringActorMappings,IProgressMonitor.UNKNOWN);

		final ProcessConfigurationRepositoryStore confStore = RepositoryManager.getInstance().getRepositoryStore(ProcessConfigurationRepositoryStore.class);
		final DiagramRepositoryStore diagramStore = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
		final List<ActorMappingsType> actorMappings = getAllActorMappings(confStore, diagramStore);

        final IComparisonScope scope = new DefaultComparisonScope(newOrganization, oldOrganization, null);

		final IEObjectMatcher matcher = DefaultMatchEngine.createDefaultEObjectMatcher(UseIdentifiers.NEVER);
		final IComparisonFactory comparisonFactory = new DefaultComparisonFactory(new DefaultEqualityHelperFactory());
		final IMatchEngine matchEngine = new DefaultMatchEngine(matcher, comparisonFactory);
	    final IMatchEngine.Factory.Registry matchEngineRegistry = EMFCompareRCPPlugin.getDefault().getMatchEngineFactoryRegistry();
	    final IPostProcessor.Descriptor.Registry<String> postProcessorRegistry = EMFCompareRCPPlugin.getDefault().getPostProcessorRegistry();
		final EMFCompare comparator = EMFCompare.builder()
	                                           .setMatchEngineFactoryRegistry(matchEngineRegistry)
	                                           .setPostProcessorRegistry(postProcessorRegistry)
	                                           .build();

		final Comparison comparison = comparator.compare(scope);

		// Merges all differences from model1 to model2
		final List<Diff> differences = comparison.getDifferences();
		for(final Diff difference : differences){
			final TreeIterator<EObject> eAllContents = difference.eAllContents();
			while (eAllContents.hasNext()) {
				final EObject eObject = eAllContents.next();
				if(eObject instanceof AttributeChange){
					final AttributeChange updateAttributeChange = (AttributeChange) eObject;
                    final Object oldElement = updateAttributeChange.getValue();
                    final Object newElement = updateAttributeChange.getSource();
					final EAttribute attribute = updateAttributeChange.getAttribute();
                    if(attribute.equals(OrganizationPackage.Literals.GROUP__NAME)){
						refactorGroup((Group)oldElement,(Group)newElement,actorMappings);
						refactorMembership((Group)oldElement,(Group)newElement,actorMappings);
					}else if(attribute.equals(OrganizationPackage.Literals.ROLE__NAME)){
						refactorRole((Role)oldElement,(Role)newElement,actorMappings);
						refactorMembership((Role)oldElement,(Role)newElement,actorMappings);
					}else if(attribute.equals(OrganizationPackage.Literals.USER__USER_NAME)){
						refactorUsername((User)oldElement,(User)newElement,actorMappings);
					}else if(attribute.equals(OrganizationPackage.Literals.MEMBERSHIP__USER_NAME)){
						refactorUsername((org.bonitasoft.studio.actors.model.organization.Membership)oldElement,(org.bonitasoft.studio.actors.model.organization.Membership)newElement,actorMappings);
					}else if(attribute.equals(OrganizationPackage.Literals.MEMBERSHIP__GROUP_NAME)){
						refactorGroup((org.bonitasoft.studio.actors.model.organization.Membership)oldElement,(org.bonitasoft.studio.actors.model.organization.Membership)newElement,actorMappings);
					}else if(attribute.equals(OrganizationPackage.Literals.GROUP__PARENT_PATH)){
						refactorGroup((Group)oldElement,(Group)newElement,actorMappings);
						refactorMembership((Group)oldElement,(Group)newElement,actorMappings);
					}else if(attribute.equals(OrganizationPackage.Literals.MEMBERSHIP__GROUP_PARENT_PATH)){
						refactorGroup((org.bonitasoft.studio.actors.model.organization.Membership)oldElement,(org.bonitasoft.studio.actors.model.organization.Membership)newElement,actorMappings);
					}
				}
			}

            final List<ModelElementChangeLeftTarget> newElementChange = ModelHelper.getAllItemsOfType(difference,
                    DiffPackage.Literals.MODEL_ELEMENT_CHANGE_LEFT_TARGET);
            final List<ModelElementChangeRightTarget> oldElementChange = ModelHelper.getAllItemsOfType(difference,
                    DiffPackage.Literals.MODEL_ELEMENT_CHANGE_RIGHT_TARGET);
            if (!newElementChange.isEmpty() && !oldElementChange.isEmpty()) {
                final ModelElementChangeLeftTarget leftTarget = newElementChange.get(0);
                final ModelElementChangeRightTarget rightTarget = oldElementChange.get(0);
                final EObject newEObject = leftTarget.getLeftElement();
                final EObject oldEObject = rightTarget.getRightElement();
                if (newEObject instanceof Group && oldEObject instanceof Group) {
                    refactorGroup((Group) oldEObject, (Group) newEObject, actorMappings);
                    refactorMembership((Group) oldEObject, (Group) newEObject, actorMappings);
                }
                if (newEObject instanceof Role && oldEObject instanceof Role) {
                    refactorRole((Role) oldEObject, (Role) newEObject, actorMappings);
                    refactorMembership((Role) oldEObject, (Role) newEObject, actorMappings);
                }
                if (newEObject instanceof User && oldEObject instanceof User) {
                    refactorUsername((User) oldEObject, (User) newEObject, actorMappings);
                }
                if (newEObject instanceof Membership && oldEObject instanceof Membership) {
                    refactorUsername((org.bonitasoft.studio.actors.model.organization.Membership) oldEObject,
                            (org.bonitasoft.studio.actors.model.organization.Membership) newEObject, actorMappings);
                    refactorGroup((org.bonitasoft.studio.actors.model.organization.Membership) oldEObject,
                            (org.bonitasoft.studio.actors.model.organization.Membership) newEObject, actorMappings);
                }
            }
		}
		diagramStore.refresh();
	}


	protected void refactorUsername(
			final org.bonitasoft.studio.actors.model.organization.Membership oldMembership,
			final org.bonitasoft.studio.actors.model.organization.Membership newMembership,
			final List<ActorMappingsType> actorMappings) {
		for(final ActorMappingsType ac :actorMappings){
			if(!ac.getActorMapping().isEmpty()){
				final Users users = ac.getActorMapping().get(0).getUsers();
				if(users != null){
					if(users.getUser().remove(oldMembership.getUserName())){
						users.getUser().add(newMembership.getUserName());
						saveChange(ac);
					}
				}
			}
		}
	}

	protected void refactorMembership(final Role oldRole, final Role newRole,
			final List<ActorMappingsType> actorMappings) {
		for(final ActorMappingsType ac :actorMappings){
			if(!ac.getActorMapping().isEmpty()){
				final Membership membership = ac.getActorMapping().get(0).getMemberships();
				if(membership != null){
					for(final MembershipType membershipType : membership.getMembership()){
						if(membershipType.getRole().equals(oldRole.getName())){
							membershipType.setRole(newRole.getName());
							saveChange(ac);
						}
					}
				}
			}
		}
	}

	protected void refactorMembership(final Group oldGroup, final Group newGroup,
			final List<ActorMappingsType> actorMappings) {
		for(final ActorMappingsType ac :actorMappings){
			if(!ac.getActorMapping().isEmpty()){
				final Membership membership = ac.getActorMapping().get(0).getMemberships();
				if(membership != null){
					for(final MembershipType membershipType : membership.getMembership()){
						if(membershipType.getGroup().equals(GroupContentProvider.getGroupPath(oldGroup))){
							membershipType.setGroup(GroupContentProvider.getGroupPath(newGroup));
							saveChange(ac);
						}
					}
				}
			}
		}
	}

	protected void refactorUsername(final User oldUser, final User newUser,
			final List<ActorMappingsType> actorMappings) {
		for(final ActorMappingsType ac :actorMappings){
			if(!ac.getActorMapping().isEmpty()){
				final Users users = ac.getActorMapping().get(0).getUsers();
				if(users != null){
					if(users.getUser().remove(oldUser.getUserName())){
						users.getUser().add(newUser.getUserName());
						saveChange(ac);
					}
				}
			}
		}
	}

	protected void refactorRole(final Role oldRole, final Role newRole,
			final List<ActorMappingsType> actorMappings) {
		for(final ActorMappingsType ac :actorMappings){
			if(!ac.getActorMapping().isEmpty()){
				final Roles roles = ac.getActorMapping().get(0).getRoles();
				if(roles != null){
					if(roles.getRole().remove(oldRole.getName())){
						roles.getRole().add(newRole.getName());
						saveChange(ac);
					}
				}
			}
		}
	}

	protected void refactorGroup(final Group oldGroup, final Group newGroup, final List<ActorMappingsType> actorMappings) {
		for(final ActorMappingsType ac :actorMappings){
			if(!ac.getActorMapping().isEmpty()){
				final Groups groups = ac.getActorMapping().get(0).getGroups();
				if(groups != null){
					if(groups.getGroup().remove(GroupContentProvider.getGroupPath(oldGroup))){
						groups.getGroup().add(GroupContentProvider.getGroupPath(newGroup));
						saveChange(ac);
					}
				}
			}
		}
	}

	protected void refactorGroup(final org.bonitasoft.studio.actors.model.organization.Membership oldMembership, final org.bonitasoft.studio.actors.model.organization.Membership newMembership, final List<ActorMappingsType> actorMappings) {
		for(final ActorMappingsType ac :actorMappings){
			if(!ac.getActorMapping().isEmpty()){
				final Groups groups = ac.getActorMapping().get(0).getGroups();
				if(groups != null){
					if(groups.getGroup().remove(GroupContentProvider.getGroupPath(oldMembership.getGroupName(),oldMembership.getGroupParentPath()))){
						groups.getGroup().add(GroupContentProvider.getGroupPath(newMembership.getGroupName(),newMembership.getGroupParentPath()));
						saveChange(ac);
					}
				}
			}
		}
	}


	private void saveChange(final ActorMappingsType ac) {
		try {
			final Resource eResource = ac.eResource();
			if(eResource != null){
				eResource.save(Collections.emptyMap());
			}
		} catch (final IOException e) {
			BonitaStudioLog.error(e);
		}
	}

	protected List<ActorMappingsType> getAllActorMappings(
			final ProcessConfigurationRepositoryStore confStore,
			final DiagramRepositoryStore diagramStore) {
		final List<ActorMappingsType> allActorMappings = new ArrayList<ActorMappingsType>();
		for(final ProcessConfigurationFileStore fileStore : confStore.getChildren()){
			final Configuration c = fileStore.getContent();
			if(c != null && c.getActorMappings() != null){
				allActorMappings.add(c.getActorMappings());
			}
		}
		for(final DiagramFileStore fileStore : diagramStore.getChildren()){
			for(final AbstractProcess process: fileStore.getProcesses()){
				for(final Configuration c : process.getConfigurations()){
					if(c != null && c.getActorMappings() != null){
						allActorMappings.add(c.getActorMappings());
					}
				}
			}

		}
		return allActorMappings;
	}

}
