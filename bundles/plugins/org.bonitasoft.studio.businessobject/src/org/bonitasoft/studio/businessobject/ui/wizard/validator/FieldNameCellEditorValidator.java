/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.businessobject.ui.wizard.validator;

import java.util.Objects;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.validator.SQLNameValidator;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.viewers.ICellEditorValidator;

/**
 * @author Romain Bioteau
 */
public class FieldNameCellEditorValidator implements ICellEditorValidator, IValidator {

    public static final int MAX_COLUMN_NAME_LENGTH = 50;

    private BusinessObject bo;

    private Field field;

    private SQLNameValidator sqlNameValidator;

    public FieldNameCellEditorValidator(BusinessObject bo, Field field) {
        this.bo = bo;
        this.field = field;
        this.sqlNameValidator = new SQLNameValidator(MAX_COLUMN_NAME_LENGTH);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ICellEditorValidator#isValid(java.lang.Object)
     */
    @Override
    public String isValid(Object value) {
        IStatus status = doValidate(value);
        if (!status.isOK()) {
            return status.getMessage();
        }
        return null;
    }

    protected IStatus doValidate(Object value) {
        IStatus status = JavaConventions.validateFieldName((String) value, JavaCore.VERSION_1_8, JavaCore.VERSION_1_8);
        if (!status.isOK()) {
            return status;
        }
        status = new InputLengthValidator(value.toString(), MAX_COLUMN_NAME_LENGTH).validate(value);
        if (!status.isOK()) {
            return status;
        }
        if (sqlNameValidator.isSQLKeyword(value.toString())) {
            return ValidationStatus.error(Messages.bind(Messages.reservedKeyWord, value.toString()));
        }
        if (!sqlNameValidator.isValid(value.toString())) {
            return ValidationStatus.error(Messages.bind(Messages.invalidSQLIdentifier, value.toString()));
        }
        if (value.toString().equalsIgnoreCase(Field.PERSISTENCE_ID)) {
            return ValidationStatus.error(Messages.bind(Messages.reservedKeyWord, Field.PERSISTENCE_ID));
        } else if (value.toString().equalsIgnoreCase(Field.PERSISTENCE_VERSION)) {
            return ValidationStatus.error(Messages.bind(Messages.reservedKeyWord, Field.PERSISTENCE_VERSION));
        }
        for (Field field : bo.getFields()) {
            if (field.getName().equalsIgnoreCase(value.toString()) && !this.field.equals(field)) {
                return ValidationStatus.error(Messages.fieldNameAlreadyExists);
            }
        }
        if (!Objects.equals(value.toString().substring(0, 1), value.toString().substring(0, 1).toLowerCase())) {
            return ValidationStatus.warning(Messages.fieldNameShouldStartsWithLowercase);
        }
        return ValidationStatus.ok();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.databinding.validation.IValidator#validate(java.lang.Object)
     */
    @Override
    public IStatus validate(Object value) {
        return doValidate(value);
    }

}
