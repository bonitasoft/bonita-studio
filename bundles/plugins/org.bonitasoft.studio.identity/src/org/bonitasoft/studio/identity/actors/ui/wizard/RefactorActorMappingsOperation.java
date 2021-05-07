/**
 * Copyright (C) 2013-2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.identity.actors.ui.wizard;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.identity.IdentityPlugin;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.model.organization.Group;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationPackage;
import org.bonitasoft.studio.identity.organization.model.organization.Role;
import org.bonitasoft.studio.identity.organization.model.organization.User;
import org.bonitasoft.studio.identity.organization.ui.provider.content.GroupContentProvider;
import org.bonitasoft.studio.model.actormapping.ActorMapping;
import org.bonitasoft.studio.model.actormapping.ActorMappingPackage;
import org.bonitasoft.studio.model.actormapping.Groups;
import org.bonitasoft.studio.model.actormapping.Membership;
import org.bonitasoft.studio.model.actormapping.MembershipType;
import org.bonitasoft.studio.model.actormapping.Roles;
import org.bonitasoft.studio.model.actormapping.Users;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationPackage;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.compare.AttributeChange;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.scope.DefaultComparisonScope;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.ReplaceCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.operation.IRunnableWithProgress;

/**
 * @author Romain Bioteau
 */
public class RefactorActorMappingsOperation implements IRunnableWithProgress {

    private final Organization oldOrganization;
    private final Organization newOrganization;

    public RefactorActorMappingsOperation(final Organization oldOrganization,
            final Organization newOrganization) {
        this.oldOrganization = oldOrganization;
        this.newOrganization = newOrganization;

    }

    @Override
    public void run(final IProgressMonitor monitor) throws InvocationTargetException,
            InterruptedException {
        monitor.beginTask(Messages.refactoringActorMappings, IProgressMonitor.UNKNOWN);

        final ProcessConfigurationRepositoryStore confStore = RepositoryManager.getInstance()
                .getRepositoryStore(ProcessConfigurationRepositoryStore.class);
        final DiagramRepositoryStore diagramStore = RepositoryManager.getInstance()
                .getRepositoryStore(DiagramRepositoryStore.class);

        final Comparison comparison = compareOrganizations();

        List<Configuration> configurations = getConfigurations(confStore, diagramStore);
        for (Configuration config : configurations) {
            EditingDomain editingDomain = TransactionUtil.getEditingDomain(config);
            if (editingDomain == null) {
                editingDomain = confStore.getEditingDomain();
            }
            refactorConfiguration(config, comparison, editingDomain);
            if(config.eContainer() == null) {
                try {
                    final Resource eResource = config.eResource();
                    if (eResource != null) {
                        eResource.save(Collections.emptyMap());
                    }
                } catch (final IOException e) {
                    BonitaStudioLog.error(e);
                }
            }
          
        }
        diagramStore.refresh();
    }

    private void refactorConfiguration(Configuration config, Comparison comparison,
            EditingDomain editingDomain) {
        final List<Diff> differences = comparison.getDifferences();
        for (final Diff difference : differences) {
            if (difference instanceof AttributeChange) {
                CompoundCommand cc = new CompoundCommand("Refactoring " + config.getName());
                final AttributeChange updateAttributeChange = (AttributeChange) difference;
                final Object oldElement = updateAttributeChange.getMatch().getRight();
                final Object newElement = updateAttributeChange.getMatch().getLeft();
                final EAttribute attribute = updateAttributeChange.getAttribute();
                if (OrganizationPackage.Literals.GROUP__NAME.equals(attribute)) {
                    cc.appendIfCanExecute(refactorGroup((Group) oldElement, (Group) newElement, config, editingDomain));
                    cc.appendIfCanExecute(
                            refactorMembership((Group) oldElement, (Group) newElement, config, editingDomain));
                } else if (OrganizationPackage.Literals.ROLE__NAME.equals(attribute)) {
                    cc.appendIfCanExecute(refactorRole((Role) oldElement, (Role) newElement, config, editingDomain));
                    cc.appendIfCanExecute(
                            refactorMembership((Role) oldElement, (Role) newElement, config, editingDomain));
                } else if (OrganizationPackage.Literals.USER__USER_NAME.equals(attribute)) {
                    cc.appendIfCanExecute(
                            refactorUsername((User) oldElement, (User) newElement, config, editingDomain));
                } else if (OrganizationPackage.Literals.MEMBERSHIP__USER_NAME.equals(attribute)) {
                    cc.appendIfCanExecute(
                            refactorUsername((org.bonitasoft.studio.identity.organization.model.organization.Membership) oldElement,
                                    (org.bonitasoft.studio.identity.organization.model.organization.Membership) newElement, config,
                                    editingDomain));
                } else if (OrganizationPackage.Literals.MEMBERSHIP__GROUP_NAME.equals(attribute)) {
                    cc.appendIfCanExecute(
                            refactorGroup((org.bonitasoft.studio.identity.organization.model.organization.Membership) oldElement,
                                    (org.bonitasoft.studio.identity.organization.model.organization.Membership) newElement, config,
                                    editingDomain));
                } else if (OrganizationPackage.Literals.GROUP__PARENT_PATH.equals(attribute)) {
                    cc.appendIfCanExecute(refactorGroup((Group) oldElement, (Group) newElement, config, editingDomain));
                    cc.appendIfCanExecute(
                            refactorMembership((Group) oldElement, (Group) newElement, config, editingDomain));
                } else if (OrganizationPackage.Literals.MEMBERSHIP__GROUP_PARENT_PATH.equals(attribute)) {
                    cc.appendIfCanExecute(
                            refactorGroup((org.bonitasoft.studio.identity.organization.model.organization.Membership) oldElement,
                                    (org.bonitasoft.studio.identity.organization.model.organization.Membership) newElement, config,
                                    editingDomain));
                }
                editingDomain.getCommandStack().execute(cc);
                cc.dispose();
            }
        }
    }

    private Comparison compareOrganizations() {
        return EMFCompare.builder()
                .build()
                .compare(new DefaultComparisonScope(newOrganization, oldOrganization, null));
    }

    private Command refactorUsername(
            final org.bonitasoft.studio.identity.organization.model.organization.Membership oldMembership,
            final org.bonitasoft.studio.identity.organization.model.organization.Membership newMembership,
            final Configuration configuration, EditingDomain editingDomain) {
        CompoundCommand cc = new CompoundCommand();
        for (ActorMapping ac : configuration.getActorMappings().getActorMapping()) {
            final Users users = ac.getUsers();
            if (users != null) {
                if (users.getUser().contains(oldMembership.getUserName())) {
                    cc.appendIfCanExecute(new ReplaceCommand(editingDomain, users.getUser(),
                            oldMembership.getUserName(), newMembership.getUserName()));
                }
            }
        }
        return cc;
    }

    private Command refactorMembership(final Role oldRole, final Role newRole,
            final Configuration configuration, EditingDomain editingDomain) {
        CompoundCommand cc = new CompoundCommand();
        for (ActorMapping ac : configuration.getActorMappings().getActorMapping()) {
            final Membership memberships = ac.getMemberships();
            if (memberships != null) {
                for (final MembershipType membershipType : memberships.getMembership()) {
                    if (membershipType.getRole().equals(oldRole.getName())) {
                        cc.appendIfCanExecute(SetCommand.create(editingDomain, membershipType,
                                ActorMappingPackage.Literals.MEMBERSHIP_TYPE__ROLE, newRole.getName()));
                    }
                }
            }
        }
        return cc;
    }

    private Command refactorMembership(final Group oldGroup, final Group newGroup,
            final Configuration configuration, EditingDomain editingDomain) {
        CompoundCommand cc = new CompoundCommand();
        for (ActorMapping ac : configuration.getActorMappings().getActorMapping()) {
            final Membership memberships = ac.getMemberships();
            if (memberships != null) {
                for (final MembershipType membershipType : memberships.getMembership()) {
                    if (membershipType.getGroup().equals(GroupContentProvider.getGroupPath(oldGroup))) {
                        cc.appendIfCanExecute(SetCommand.create(editingDomain, membershipType,
                                ActorMappingPackage.Literals.MEMBERSHIP_TYPE__GROUP,
                                GroupContentProvider.getGroupPath(newGroup)));
                    }
                }
            }
        }
        return cc;
    }

    private Command refactorUsername(final User oldUser, final User newUser,
            final Configuration configuration, EditingDomain editingDomain) {
        CompoundCommand cc = new CompoundCommand();
        for (ActorMapping ac : configuration.getActorMappings().getActorMapping()) {
            final Users users = ac.getUsers();
            if (users != null) {
                if (users.getUser().contains(oldUser.getUserName())) {
                    cc.appendIfCanExecute(RemoveCommand.create(editingDomain, users,
                            ActorMappingPackage.Literals.USERS__USER, oldUser.getUserName()));
                    cc.appendIfCanExecute(AddCommand.create(editingDomain, users,
                            ActorMappingPackage.Literals.USERS__USER, newUser.getUserName()));
                }
            }
        }
        if (Objects.equals(configuration.getUsername(), oldUser.getUserName())) {
            cc.appendIfCanExecute(SetCommand.create(editingDomain, configuration,
                    ConfigurationPackage.Literals.CONFIGURATION__USERNAME, newUser.getUserName()));
        }
        return cc;
    }

    private Command refactorRole(final Role oldRole, final Role newRole,
            final Configuration configuration, EditingDomain editingDomain) {
        CompoundCommand cc = new CompoundCommand();
        for (ActorMapping ac : configuration.getActorMappings().getActorMapping()) {
            final Roles roles = ac.getRoles();
            if (roles != null) {
                if (roles.getRole().contains(oldRole.getName())) {
                    cc.appendIfCanExecute(
                            new ReplaceCommand(editingDomain, roles.getRole(), oldRole.getName(), newRole.getName()));
                }
            }
        }
        return cc;
    }

    private Command refactorGroup(Group oldGroup, Group newGroup, Configuration configuration,
            EditingDomain editingDomain) {
        CompoundCommand cc = new CompoundCommand();
        for (ActorMapping ac : configuration.getActorMappings().getActorMapping()) {
            final Groups groups = ac.getGroups();
            if (groups != null) {
                if (groups.getGroup().contains(GroupContentProvider.getGroupPath(oldGroup))) {
                    cc.appendIfCanExecute(RemoveCommand.create(editingDomain, groups,
                            ActorMappingPackage.Literals.GROUPS__GROUP, GroupContentProvider.getGroupPath(oldGroup)));
                    cc.appendIfCanExecute(AddCommand.create(editingDomain, groups,
                            ActorMappingPackage.Literals.GROUPS__GROUP, GroupContentProvider.getGroupPath(newGroup)));
                    //                    cc.append(new ReplaceCommand(editingDomain, groups.getGroup(), GroupContentProvider.getGroupPath(oldGroup),
                    //                            GroupContentProvider.getGroupPath(newGroup)));
                }
            }
        }
        return cc;
    }

    private Command refactorGroup(final org.bonitasoft.studio.identity.organization.model.organization.Membership oldMembership,
            final org.bonitasoft.studio.identity.organization.model.organization.Membership newMembership,
            final Configuration configuration, EditingDomain editingDomain) {
        CompoundCommand cc = new CompoundCommand();
        for (ActorMapping ac : configuration.getActorMappings().getActorMapping()) {
            final Groups groups = ac.getGroups();
            if (groups != null) {
                if (groups.getGroup().contains(GroupContentProvider.getGroupPath(oldMembership.getGroupName(),
                        oldMembership.getGroupParentPath()))) {
                    cc.appendIfCanExecute(
                            RemoveCommand.create(editingDomain, groups, ActorMappingPackage.Literals.GROUPS__GROUP,
                                    GroupContentProvider.getGroupPath(oldMembership.getGroupName(),
                                            oldMembership.getGroupParentPath())));
                    cc.appendIfCanExecute(
                            AddCommand.create(editingDomain, groups, ActorMappingPackage.Literals.GROUPS__GROUP,
                                    GroupContentProvider.getGroupPath(newMembership.getGroupName(),
                                            newMembership.getGroupParentPath())));

                    //                    cc.appendIfCanExecute(new ReplaceCommand(editingDomain, groups.getGroup(),
                    //                            GroupContentProvider.getGroupPath(oldMembership.getGroupName(),
                    //                                    oldMembership.getGroupParentPath()),
                    //                            GroupContentProvider.getGroupPath(newMembership.getGroupName(),
                    //                                    newMembership.getGroupParentPath())));
                }
            }
        }
        return cc;
    }

    private List<Configuration> getConfigurations(
            final ProcessConfigurationRepositoryStore confStore,
            final DiagramRepositoryStore diagramStore) {
        final List<Configuration> configurations = new ArrayList<>();
        for (final ProcessConfigurationFileStore fileStore : confStore.getChildren()) {
            Configuration c = null;
            try {
                c = fileStore.getContent();
            } catch (ReadFileStoreException e) {
                BonitaStudioLog.warning(e.getMessage(), IdentityPlugin.PLUGIN_ID);
            }
            if (c != null && c.getActorMappings() != null) {
                configurations.add(c);
            }
        }
        for (final DiagramFileStore fileStore : diagramStore.getChildren()) {
            for (final AbstractProcess process : fileStore.getProcesses(true)) {
                for (final Configuration c : process.getConfigurations()) {
                    if (c != null && c.getActorMappings() != null) {
                        configurations.add(c);
                    }
                }
            }
        }
        return configurations;
    }
}
