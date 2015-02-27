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
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.viewers.ICellEditorValidator;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.bdm.validator.SQLNameValidator;

/**
 * @author Romain Bioteau
 * 
 */
public class BusinessObjectNameCellEditorValidator implements ICellEditorValidator, IValidator {

    public static final int MAX_TABLE_NAME_LENGTH = 30;

    private BusinessObjectModel model;

    private BusinessObject bo;

    private SQLNameValidator sqlNameValidator;

    public BusinessObjectNameCellEditorValidator(BusinessObjectModel model, BusinessObject bo) {
        this.model = model;
        this.bo = bo;
        this.sqlNameValidator = new SQLNameValidator(MAX_TABLE_NAME_LENGTH);
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
        if (bo == null) {
            return ValidationStatus.ok();
        }
        if (value == null || value.toString().isEmpty()) {
            return ValidationStatus.error(Messages.error_emptyName);
        }
        IStatus status = new InputLengthValidator(value.toString(), MAX_TABLE_NAME_LENGTH).validate(value);
        if (!status.isOK()) {
            return status;
        }
        if (!isUnique(value.toString())) {
            return ValidationStatus.error(Messages.businessObjectNameAlreadyExists);
        }
        if (((String) value).contains(" ")) {
            return ValidationStatus.error(Messages.errorMessageNoWhitespaceInDataTypeNames);
        }
        if (((String) value).contains("_")) {
            return ValidationStatus.error(Messages.errorMessageNoUnderscoreInBoNames);
        }
        if (sqlNameValidator.isSQLKeyword(value.toString())) {
            return ValidationStatus.error(Messages.bind(Messages.reservedKeyWord, value.toString()));
        }
        if (!sqlNameValidator.isValid(value.toString())) {
            return ValidationStatus.error(Messages.bind(Messages.invalidSQLIdentifier, value.toString()));
        }
        return JavaConventions.validateJavaTypeName((String) value, JavaCore.VERSION_1_6, JavaCore.VERSION_1_6);
    }

    protected boolean isUnique(String value) {
        for (BusinessObject bo : model.getBusinessObjects()) {
            if (!this.bo.equals(bo) && NamingUtils.getSimpleName(bo.getQualifiedName()).equalsIgnoreCase(value)) {
                return false;
            }
        }
        return true;
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
