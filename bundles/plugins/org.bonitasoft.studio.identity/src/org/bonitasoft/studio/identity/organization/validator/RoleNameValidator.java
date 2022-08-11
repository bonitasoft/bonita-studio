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

import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.MultiValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.MultiValidatorFactory;
import org.bonitasoft.studio.identity.actors.validator.ValidatorConstants;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.Role;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class RoleNameValidator implements IValidator<String> {

    private IObservableValue<Role> selectedRoleObservable;
    private IObservableValue<Organization> organizationObservable;

    private EmptyInputValidator emptyInputValidator;
    private InputLengthValidator inputLengthValidator;
    private IValidator<String> uniquenessValidator;
    private MultiValidator multiValidator;

    public RoleNameValidator(IObservableValue<Organization> organizationObservable,
            IObservableValue<Role> selectedRoleObservable) {
        this.organizationObservable = organizationObservable;
        this.selectedRoleObservable = selectedRoleObservable;

        emptyInputValidator = new EmptyInputValidator(Messages.name);
        inputLengthValidator = new InputLengthValidator(Messages.name, ValidatorConstants.LONG_FIELD_MAX_LENGTH);
        uniquenessValidator = this::validateUniqueness;
        multiValidator = MultiValidatorFactory.multiValidator()
                .addValidator(emptyInputValidator)
                .addValidator(inputLengthValidator)
                .addValidator(uniquenessValidator)
                .create();
    }

    @Override
    public IStatus validate(String value) {
        return multiValidator.validate(value);
    }

    private IStatus validateUniqueness(String value) {
        return organizationObservable.getValue().getRoles().getRole().stream()
                .filter(aRole -> !Objects.equals(aRole, selectedRoleObservable.getValue()))
                .filter(aRole -> Objects.equals(aRole.getName(), value))
                .findAny()
                .map(aRole -> ValidationStatus.error(Messages.roleNameAlreadyExists))
                .orElse(ValidationStatus.ok());
    }

}
