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

import org.bonitasoft.engine.bdm.validator.SQLNameValidator;
import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.Field;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;

public class FieldNameValidator implements IBDMValidator<Field> {

    public static final int MAX_COLUMN_NAME_LENGTH = 50;

    private SQLNameValidator sqlNameValidator;

    public FieldNameValidator() {
        this.sqlNameValidator = new SQLNameValidator(MAX_COLUMN_NAME_LENGTH);
    }

    @Override
    public IStatus validate(Field field) {
        String name = field.getName();
        if (name == null || name.isEmpty()) {
            return ValidationStatus.error(Messages.attributeNameRequired);
        }
        MultiStatus status = new MultiStatus(BusinessObjectPlugin.PLUGIN_ID, 0, "", null);

        status.add(validateJavaConvention(name));
        status.add(validateNameLength(name));
        status.add(validateSqlValidity(name));
        status.add(validateReservedFieldNames(name));
        status.add(validateUniqueness(field));
        status.add(validateFirstCharacter(name));

        return status;
    }

    private IStatus validateFirstCharacter(String name) {
        return !Objects.equals(name.substring(0, 1), name.substring(0, 1).toLowerCase())
                ? ValidationStatus.warning(Messages.fieldNameShouldStartsWithLowercase)
                : ValidationStatus.ok();
    }

    private IStatus validateUniqueness(Field field) {
        BusinessObject parent = (BusinessObject) field.eContainer();
        return parent.getFields().stream()
                .filter(aField -> !Objects.equals(aField, field))
                .map(Field::getName)
                .anyMatch(field.getName()::equalsIgnoreCase)
                        ? ValidationStatus.error(Messages.fieldNameAlreadyExists)
                        : ValidationStatus.ok();
    }

    private IStatus validateReservedFieldNames(String name) {
        if (name.equalsIgnoreCase(org.bonitasoft.engine.bdm.model.field.Field.PERSISTENCE_ID)) {
            return ValidationStatus.error(
                    Messages.bind(Messages.reservedKeyWord, org.bonitasoft.engine.bdm.model.field.Field.PERSISTENCE_ID));
        } else if (name.equalsIgnoreCase(org.bonitasoft.engine.bdm.model.field.Field.PERSISTENCE_VERSION)) {
            return ValidationStatus.error(Messages.bind(Messages.reservedKeyWord,
                    org.bonitasoft.engine.bdm.model.field.Field.PERSISTENCE_VERSION));
        }
        return ValidationStatus.ok();
    }

    private IStatus validateSqlValidity(String name) {
        return sqlNameValidator.isValid(name)
                ? ValidationStatus.ok()
                : sqlNameValidator.isSQLKeyword(name)
                        ? ValidationStatus.error(Messages.bind(Messages.reservedKeyWord, name))
                        : ValidationStatus.error(Messages.bind(Messages.invalidSQLIdentifier, name));
    }

    private IStatus validateNameLength(String name) {
        return new InputLengthValidator(name, MAX_COLUMN_NAME_LENGTH).validate(name);
    }

    protected IStatus validateJavaConvention(String name) {
        return JavaConventions.validateFieldName(name, JavaCore.VERSION_1_8, JavaCore.VERSION_1_8);
    }

    @Override
    public String getValidatorType() {
        return Messages.attributes;
    }

}
