/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.businessobject.ui.wizard.validator;

import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.viewers.ICellEditorValidator;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.UniqueConstraint;
import org.bonitasoft.engine.bdm.validator.SQLNameValidator;

/**
 * @author Romain Bioteau
 * 
 */
public class UniqueConstraintNameCellEditorValidator implements ICellEditorValidator, IValidator {

    public static final int MAX_CONSTRAINT_NAME_LENGTH = 20;

    private BusinessObject bo;

    private UniqueConstraint uConstraint;

    private SQLNameValidator sqlNameValidator;

    public UniqueConstraintNameCellEditorValidator(BusinessObject bo, UniqueConstraint uConstraint) {
        this.bo = bo;
        this.uConstraint = uConstraint;
        this.sqlNameValidator = new SQLNameValidator(MAX_CONSTRAINT_NAME_LENGTH);
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
        IStatus status = JavaConventions.validateFieldName((String) value, JavaCore.VERSION_1_6, JavaCore.VERSION_1_6);
        if (!status.isOK()) {
            return status;
        }
        status = new InputLengthValidator(value.toString(), MAX_CONSTRAINT_NAME_LENGTH).validate(value);
        if (!status.isOK()) {
            return status;
        }
        if (sqlNameValidator.isSQLKeyword(value.toString())) {
            return ValidationStatus.error(Messages.bind(Messages.reservedKeyWord, value.toString()));
        }
        if (!sqlNameValidator.isValid(value.toString())) {
            return ValidationStatus.error(Messages.bind(Messages.invalidSQLIdentifier, value.toString()));
        }
        for (UniqueConstraint uConstraint : bo.getUniqueConstraints()) {
            if (uConstraint.getName().equalsIgnoreCase(value.toString()) && !this.uConstraint.equals(uConstraint)) {
                return ValidationStatus.error(Messages.constraintNameAlreadyExists);
            }
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
