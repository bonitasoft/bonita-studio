/**
 * Copyright (C) 2021 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.identity.organization.validator;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.model.organization.Membership;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.User;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

import com.google.common.base.Strings;

public class UserMembershipValidator implements IValidator<User> {

    private IObservableList<Membership> memberships;
    private MembershipGroupValidator membershipGroupValidator;
    private MembershipRoleValidator membershipRoleValidator;

    public UserMembershipValidator(IObservableValue<Organization> organizationObservable,
            IObservableList<Membership> memberships) {
        this.memberships = memberships;
        this.membershipGroupValidator = new MembershipGroupValidator(organizationObservable);
        this.membershipRoleValidator = new MembershipRoleValidator(organizationObservable);
    }

    @Override
    public IStatus validate(User user) {
        List<Membership> userMemberships = getUserMemberships(user);
        for (Membership membership : userMemberships) {
            if (Strings.isNullOrEmpty(membership.getGroupName())) {
                return ValidationStatus
                        .error(String.format(Messages.membershipEmpty, user.getUserName(), Messages.groupName));
            }
            if (Strings.isNullOrEmpty(membership.getRoleName())) {
                return ValidationStatus
                        .error(String.format(Messages.membershipEmpty, user.getUserName(), Messages.role));
            }
            IStatus groupStatus = membershipGroupValidator.validate(membership);
            if (!groupStatus.isOK()) {
                return groupStatus;
            }
            IStatus roleStatus = membershipRoleValidator.validate(membership);
            if (!roleStatus.isOK()) {
                return roleStatus;
            }
        }
        return ValidationStatus.ok();
    }

    private List<Membership> getUserMemberships(User user) {
        return memberships.stream()
                .filter(membership -> Objects.equals(membership.getUserName(), user.getUserName()))
                .collect(Collectors.toList());
    }

}
