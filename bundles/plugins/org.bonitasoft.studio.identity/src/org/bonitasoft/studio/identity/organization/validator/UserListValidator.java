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

import org.bonitasoft.studio.identity.IdentityPlugin;
import org.bonitasoft.studio.identity.organization.model.organization.Membership;
import org.bonitasoft.studio.identity.organization.model.organization.Memberships;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationPackage;
import org.bonitasoft.studio.identity.organization.model.organization.User;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.emf.databinding.EMFObservables;

public class UserListValidator implements IValidator<User> {

    private IObservableValue<User> selecteduserObservable = new WritableValue<>();
    private UserNameValidator userNameValidator;
    private UserPasswordValidator userPasswordValidator;
    private UserMembershipValidator userMembershipValidator;

    public UserListValidator(IObservableValue<Organization> organizationObservable, DataBindingContext ctx) {
        userNameValidator = new UserNameValidator(organizationObservable, selecteduserObservable);
        userPasswordValidator = new UserPasswordValidator();

        IObservableValue<Memberships> memberships = EMFObservables.observeDetailValue(ctx.getValidationRealm(),
                organizationObservable, OrganizationPackage.Literals.ORGANIZATION__MEMBERSHIPS);
        IObservableList<Membership> membership = EMFObservables.observeDetailList(ctx.getValidationRealm(),
                memberships, OrganizationPackage.Literals.MEMBERSHIPS__MEMBERSHIP);
        userMembershipValidator = new UserMembershipValidator(organizationObservable, membership);
    }

    @Override
    public IStatus validate(User user) {
        selecteduserObservable.setValue(user);
        MultiStatus globalStatus = new MultiStatus(IdentityPlugin.PLUGIN_ID, 0, "", null);

        globalStatus.add(userNameValidator.validate(user.getUserName()));
        globalStatus.add(userPasswordValidator.validate(user.getPassword().getValue()));
        globalStatus.add(userMembershipValidator.validate(user));

        return globalStatus;
    }

}
