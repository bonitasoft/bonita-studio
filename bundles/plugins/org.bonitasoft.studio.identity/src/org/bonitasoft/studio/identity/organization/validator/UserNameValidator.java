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
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.User;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class UserNameValidator implements IValidator<String> {

    protected static final Integer USERNAME_MAX_LENGTH = 50;

    private IObservableValue<User> selectedUserObservable;
    private IObservableValue<Organization> organizationObservable;

    private EmptyInputValidator emptyInputValidator;
    private InputLengthValidator inputLengthValidator;
    private IValidator<String> uniquenessValidator;
    private MultiValidator multiValidator;

    public UserNameValidator(IObservableValue<Organization> organizationObservable,
            IObservableValue<User> selectedUserObservable) {
        this.organizationObservable = organizationObservable;
        this.selectedUserObservable = selectedUserObservable;

        emptyInputValidator = new EmptyInputValidator(Messages.userName);
        inputLengthValidator = new InputLengthValidator(Messages.userName, USERNAME_MAX_LENGTH);
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
        return organizationObservable.getValue().getUsers().getUser().stream()
                .filter(aUser -> !Objects.equals(aUser, selectedUserObservable.getValue()))
                .filter(aUser -> Objects.equals(aUser.getUserName(), value))
                .findAny()
                .map(aUser -> ValidationStatus.error(Messages.userNameAlreadyExists))
                .orElse(ValidationStatus.ok());
    }

}
