/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.validator;

import java.util.Objects;

import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.UniqueConstraint;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;

public class UniqueConstraintNameValidator implements IBDMValidator<UniqueConstraint> {

    public static final int MAX_COLUMN_NAME_LENGTH = 20;

    private CustomSQLNameValidator sqlNameValidator;

    public UniqueConstraintNameValidator() {
        this.sqlNameValidator = new CustomSQLNameValidator(MAX_COLUMN_NAME_LENGTH);
    }

    @Override
    public IStatus validate(UniqueConstraint constraint) {
        String name = constraint.getName();
        if (name == null || name.isEmpty()) {
            return ValidationStatus.error(Messages.constraintNameRequired);
        }
        MultiStatus status = new MultiStatus(BusinessObjectPlugin.PLUGIN_ID, 0, "", null);

        status.add(validateJavaConvention(name));
        status.add(validateNameLength(name));
        status.add(sqlNameValidator.validate(name));
        status.add(validateUniqueness(constraint));

        return status;
    }

    private IStatus validateUniqueness(UniqueConstraint constraint) {
        BusinessObject parent = (BusinessObject) constraint.eContainer();
        return parent.getUniqueConstraints().stream()
                .filter(aConstraint -> !Objects.equals(aConstraint, constraint))
                .map(UniqueConstraint::getName)
                .anyMatch(constraint.getName()::equalsIgnoreCase)
                        ? ValidationStatus.error(Messages.constraintNameAlreadyExists)
                        : ValidationStatus.ok();
    }

    protected IStatus validateJavaConvention(String name) {
        return JavaConventions.validateFieldName(name, JavaCore.VERSION_1_8, JavaCore.VERSION_1_8);
    }

    private IStatus validateNameLength(String name) {
        return new InputLengthValidator(name, MAX_COLUMN_NAME_LENGTH).validate(name);
    }

    @Override
    public String getValidatorType() {
        return Messages.constraints;
    }

}
