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
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.Role;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;

public class RoleListValidator implements IValidator<Role> {

    private IObservableValue<Role> selectedRoleObservable = new WritableValue<>();
    private RoleNameValidator roleNameValidator;
    private DisplayNameValidator displayNameValidator;

    public RoleListValidator(IObservableValue<Organization> organizationObservable) {
        roleNameValidator = new RoleNameValidator(organizationObservable, selectedRoleObservable);
        displayNameValidator = new DisplayNameValidator();
    }

    @Override
    public IStatus validate(Role group) {
        selectedRoleObservable.setValue(group);
        MultiStatus globalStatus = new MultiStatus(IdentityPlugin.PLUGIN_ID, 0, "", null);

        globalStatus.add(roleNameValidator.validate(group.getName()));
        globalStatus.add(displayNameValidator.validate(group.getName()));

        return globalStatus;
    }

}
