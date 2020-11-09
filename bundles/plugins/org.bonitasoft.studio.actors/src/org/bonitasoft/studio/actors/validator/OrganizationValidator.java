/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.actors.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bonitasoft.studio.actors.ActorsPlugin;
import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.model.organization.Group;
import org.bonitasoft.studio.actors.model.organization.Groups;
import org.bonitasoft.studio.actors.model.organization.Membership;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.model.organization.Role;
import org.bonitasoft.studio.actors.model.organization.Roles;
import org.bonitasoft.studio.actors.model.organization.User;
import org.bonitasoft.studio.actors.model.organization.Users;
import org.bonitasoft.studio.actors.ui.wizard.page.GroupContentProvider;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;

/**
 * @author Romain Bioteau
 */
public class OrganizationValidator implements IValidator<Organization>, ValidatorConstants {

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.databinding.validation.IValidator#validate(java.lang.Object)
     */
    @Override
    public IStatus validate(Organization organization) {
        MultiStatus validationStatus = new MultiStatus(ActorsPlugin.PLUGIN_ID, 0, null, null);
        Users users = organization.getUsers();
        if (users != null) {
            for (final User u : users.getUser()) {
                if (u.getUserName() == null || u.getUserName().isEmpty()) {
                    validationStatus.add(ValidationStatus.error(Messages.userNameMissing));
                }
                if (u.getPassword() == null || u.getPassword().getValue() == null
                        || u.getPassword().getValue().isEmpty()) {
                    validationStatus
                            .add(ValidationStatus.error(Messages.bind(Messages.userPasswordMissing, u.getUserName())));
                }

                if (u.getManager() != null && !u.getManager().isEmpty()) {
                    final IStatus status = checkManagerCycles(organization, u);
                    if (!status.isOK()) {
                        validationStatus.add(status);
                    }
                }

                boolean membershipFound = false;
                for (final Membership membership : organization.getMemberships().getMembership()) {
                    final String userName = membership.getUserName();
                    if (userName != null) {
                        if (userName.equals(u.getUserName())) {
                            membershipFound = true;
                            final String groupName = membership.getGroupName();
                            if (groupName == null) {
                                validationStatus
                                        .add(ValidationStatus
                                                .error(Messages.bind(Messages.missingGroup, u.getUserName())));
                            }
                            final String parentPath = membership.getGroupParentPath();
                            String groupPath = null;
                            if (parentPath == null) {
                                groupPath = GroupContentProvider.GROUP_SEPARATOR + groupName;
                            } else {
                                groupPath = parentPath + GroupContentProvider.GROUP_SEPARATOR + groupName;
                            }
                            final IStatus groupStatus = validateGroupExists(organization, groupPath, membership);
                            if (groupStatus.getSeverity() != IStatus.OK) {
                                validationStatus.add(groupStatus);
                            }

                            final String roleName = membership.getRoleName();
                            if (roleName == null) {
                                validationStatus
                                        .add(ValidationStatus
                                                .error(Messages.bind(Messages.missingRole, u.getUserName())));
                            }
                            final IStatus roleStatus = validateRoleExists(organization, roleName, membership);
                            if (roleStatus.getSeverity() != IStatus.OK) {
                                validationStatus.add(roleStatus);
                            }
                        }
                    }
                }
                if (!membershipFound) {
                    validationStatus
                            .add(ValidationStatus
                                    .error(Messages.bind(Messages.missingMembershipForUser, u.getUserName())));
                }
            }
        }

        Groups groups = organization.getGroups();
        if(groups != null) {
            List<String> invalidGroups = groups.getGroup().stream()
                    .map(Group::getName)
                    .filter(groupName -> groupName.contains("/"))
                    .collect(Collectors.toList());
            if (!invalidGroups.isEmpty()) {
                validationStatus.add(ValidationStatus.error(String.format(Messages.invalidCharInGroupName,
                        invalidGroups.stream().reduce((group1, group2) -> group1 + "\n" + group2).get())));
            }
        }
        return validationStatus;
    }

    private IStatus checkManagerCycles(final Organization organization, final User u) {
        String managerUsername = u.getManager();
        final List<String> managers = new ArrayList<>();
        managers.add(u.getUserName());
        managers.add(managerUsername);
        while (managerUsername != null) {
            managerUsername = getManagerOf(organization, managerUsername);
            if (managerUsername != null) {
                if (!managers.contains(managerUsername)) {
                    managers.add(managerUsername);
                } else {
                    managers.add(managerUsername);
                    return new Status(IStatus.ERROR, ActorsPlugin.PLUGIN_ID,
                            Messages.bind(Messages.managerCycleDetected, managers.toString()));
                }
            }
        }

        return ValidationStatus.ok();
    }

    private String getManagerOf(final Organization organization, final String managerUsername) {
        Users users = organization.getUsers();
        if (users != null) {
            for (final User u : users.getUser()) {
                if (managerUsername.equals(u.getUserName())) {
                    return u.getManager();
                }
            }
        }
        return null;
    }

    private IStatus validateRoleExists(final Organization organization, final String roleName,
            final Membership membership) {
        Roles roles = organization.getRoles();
        if (roles != null) {
            for (final Role role : roles.getRole()) {
                if (role.getName().equals(roleName)) {
                    return ValidationStatus.ok();
                }
            }
        }
        return ValidationStatus
                .error(Messages.bind(Messages.missingRoleInMembership, roleName, membership.getUserName()));
    }

    private IStatus validateGroupExists(final Organization organization, final String groupPath,
            final Membership membership) {
        Groups groups = organization.getGroups();
        if (groups != null) {
            for (final Group group : groups.getGroup()) {
                if (GroupContentProvider.getGroupPath(group).equals(groupPath)) {
                    return ValidationStatus.ok();
                }
            }
        }
        return ValidationStatus
                .error(Messages.bind(Messages.missingGroupInMembership, groupPath, membership.getUserName()));
    }

}
