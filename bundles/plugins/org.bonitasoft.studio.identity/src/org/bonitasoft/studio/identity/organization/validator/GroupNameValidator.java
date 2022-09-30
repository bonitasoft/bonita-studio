/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
import org.bonitasoft.studio.common.databinding.validator.InputLengthValidator;
import org.bonitasoft.studio.common.databinding.validator.MultiValidator;
import org.bonitasoft.studio.common.databinding.validator.MultiValidatorFactory;
import org.bonitasoft.studio.common.databinding.validator.RegExpValidator;
import org.bonitasoft.studio.identity.actors.validator.ValidatorConstants;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.editor.formpage.AbstractOrganizationFormPage;
import org.bonitasoft.studio.identity.organization.model.organization.Group;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class GroupNameValidator implements IValidator<String> {

    private IObservableValue<Group> selectedGroupObservable;
    private AbstractOrganizationFormPage formPage;

    private EmptyInputValidator emptyInputValidator;
    private InputLengthValidator inputLengthValidator;
    private RegExpValidator regExpValidator;
    private IValidator<String> uniquenessValidator;
    private MultiValidator multiValidator;

    public GroupNameValidator(AbstractOrganizationFormPage formPage,
            IObservableValue<Group> selectedGroupObservable) {
        this.formPage = formPage;
        this.selectedGroupObservable = selectedGroupObservable;

        emptyInputValidator = new EmptyInputValidator(Messages.name);
        inputLengthValidator = new InputLengthValidator(Messages.name, ValidatorConstants.GROUP_NAME_MAX_LENGTH);
        regExpValidator = new RegExpValidator(Messages.illegalCharacter, "^[^/]*$");
        uniquenessValidator = this::validateUniqueness;
        multiValidator = MultiValidatorFactory.multiValidator()
                .addValidator(emptyInputValidator)
                .addValidator(inputLengthValidator)
                .addValidator(regExpValidator)
                .addValidator(uniquenessValidator)
                .create();
    }

    @Override
    public IStatus validate(String value) {
        return multiValidator.validate(value);
    }

    private IStatus validateUniqueness(String value) {
        return formPage.observeWorkingCopy().getValue().getGroups().getGroup().stream()
                .filter(aGroup -> !Objects.equals(aGroup, selectedGroupObservable.getValue()))
                .filter(aGroup -> Objects.equals(aGroup.getName(), value))
                .filter(aGroup -> sameParentPath(aGroup, selectedGroupObservable.getValue()))
                .findAny()
                .map(aGroup -> ValidationStatus.error(Messages.groupNameAlreadyExistsForLevel))
                .orElse(ValidationStatus.ok());
    }

    private boolean sameParentPath(Group group1, Group group2) {
        if (group1.getParentPath() == null) {
            return group2.getParentPath() == null;
        }
        return Objects.equals(group1.getParentPath(), group2.getParentPath());
    }

}
