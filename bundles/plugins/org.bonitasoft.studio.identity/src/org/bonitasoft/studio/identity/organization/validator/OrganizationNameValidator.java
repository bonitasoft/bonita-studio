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
import org.bonitasoft.studio.common.jface.databinding.validator.FileNameValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.MultiValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.MultiValidatorFactory;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.editor.formpage.AbstractOrganizationFormPage;
import org.bonitasoft.studio.identity.organization.repository.OrganizationFileStore;
import org.bonitasoft.studio.identity.organization.repository.OrganizationRepositoryStore;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class OrganizationNameValidator implements IValidator<String> {

    private EmptyInputValidator emptyInputValidator;
    private MultiValidator multiValidator;
    private OrganizationRepositoryStore repositoryStore;
    private AbstractOrganizationFormPage formPage;
    private FileNameValidator fileNameValidator;

    public OrganizationNameValidator(AbstractOrganizationFormPage formPage) {
        this.formPage = formPage;
        this.repositoryStore = formPage.getRepositoryAccessor().getRepositoryStore(OrganizationRepositoryStore.class);
        emptyInputValidator = new EmptyInputValidator(Messages.name);
        fileNameValidator = new FileNameValidator(Messages.organizationName);

        multiValidator = MultiValidatorFactory.multiValidator()
                .addValidator(emptyInputValidator)
                .addValidator(fileNameValidator)
                .addValidator(this::validateUnicity)
                .create();
    }

    private IStatus validateUnicity(Object newName) {
        boolean alreadyExists = repositoryStore.getChildren().stream()
                .map(OrganizationFileStore::getDisplayName)
                .filter(anOrgaName -> !Objects.equals(anOrgaName, formPage.observeWorkingCopy().getValue().getName()))
                .anyMatch(anOrgaName -> Objects.equals(anOrgaName, newName));
        return alreadyExists
                ? ValidationStatus.error(String.format(Messages.organizationAlreadyExists, newName))
                : ValidationStatus.ok();
    }

    @Override
    public IStatus validate(String value) {
        return multiValidator.validate(value);
    }

}
