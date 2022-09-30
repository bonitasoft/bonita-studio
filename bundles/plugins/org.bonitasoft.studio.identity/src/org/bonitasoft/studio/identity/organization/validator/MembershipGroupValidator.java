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

import java.util.Objects;

import org.bonitasoft.studio.common.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.editor.provider.content.GroupContentProvider;
import org.bonitasoft.studio.identity.organization.model.organization.Membership;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class MembershipGroupValidator implements IValidator<Membership> {

    private EmptyInputValidator emptyValidator;
    private IObservableValue<Organization> organizationObservable;

    public MembershipGroupValidator(IObservableValue<Organization> organizationObservable) {
        this.organizationObservable = organizationObservable;
        emptyValidator = new EmptyInputValidator(Messages.groupName);
    }

    @Override
    public IStatus validate(Membership value) {
        IStatus status = emptyValidator.validate(value.getGroupName());
        if (status.isOK()) {
            status = validateGroupExists(value);
        }
        return status;
    }

    private IStatus validateGroupExists(Membership value) {
        String groupPath = GroupContentProvider.getGroupPath(value.getGroupName(), value.getGroupParentPath());
        return organizationObservable.getValue().getGroups().getGroup().stream()
                .anyMatch(group -> Objects.equals(GroupContentProvider.getGroupPath(group), groupPath))
                        ? ValidationStatus.ok()
                        : ValidationStatus.error(
                                Messages.bind(Messages.missingGroupInMembership, groupPath, value.getUserName()));
    }

}
