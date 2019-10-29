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
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
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
public class BusinessObjectNameCellEditorValidator implements ICellEditorValidator, IValidator<String> {

    public static final int MAX_TABLE_NAME_LENGTH = 30;

    private BusinessObjectModel model;
    private SQLNameValidator sqlNameValidator;

    private BusinessObject businessObject;

    public BusinessObjectNameCellEditorValidator(BusinessObjectModel model,
            BusinessObject businessObject) {
        this.model = model;
        this.businessObject = businessObject;
        this.sqlNameValidator = new SQLNameValidator(MAX_TABLE_NAME_LENGTH);
    }

    @Override
    public String isValid(Object value) {
        IStatus status = validate((String) value);
        if (!status.isOK()) {
            return status.getMessage();
        }
        return null;
    }

    @Override
    public IStatus validate(String value) {
        return validateBoName(value);
    }

    private IStatus validateBoName(String simpleName) {
        if (simpleName == null || simpleName.isEmpty()) {
            return ValidationStatus.error(Messages.error_emptyName);
        }
        return performValidationsWithFailFast(simpleName, this::validateUniqueness,
                this::validateInputLength, this::validateInvalidChar,
                this::validateSQLValidity, this::validateJavaTypeName);
    }

    private IStatus performValidationsWithFailFast(String input, IValidator<String>... validators) {
        for (IValidator<String> validator : validators) {
            IStatus status = validator.validate(input);
            if (!status.isOK()) {
                return status;
            }
        }
        return ValidationStatus.ok();
    }

    protected IStatus validateJavaTypeName(String name) {
        return JavaConventions.validateJavaTypeName(name, JavaCore.VERSION_1_8, JavaCore.VERSION_1_8);
    }

    protected IStatus validateSQLValidity(String name) {
        if (sqlNameValidator.isSQLKeyword(name)) {
            return ValidationStatus.error(Messages.bind(Messages.reservedKeyWord, name));
        }
        if (!sqlNameValidator.isValid(name)) {
            return ValidationStatus.error(Messages.bind(Messages.invalidSQLIdentifier, name));
        }
        return ValidationStatus.ok();
    }

    protected IStatus validateInvalidChar(String name) {
        if (name.contains(" ")) {
            return ValidationStatus.error(Messages.errorMessageNoWhitespaceInDataTypeNames);
        }
        if (name.contains("_")) {
            return ValidationStatus.error(Messages.errorMessageNoUnderscoreInBoNames);
        }
        return ValidationStatus.ok();
    }

    protected IStatus validateInputLength(String name) {
        return new InputLengthValidator(name, MAX_TABLE_NAME_LENGTH).validate(name);
    }

    protected IStatus validateUniqueness(String name) {
        int max = Objects.equals(businessObject.getSimpleName(), name) ? 1 : 0;
        if (model.getBusinessObjects().stream()
                .filter(otherBo -> Objects.equals(name, otherBo.getSimpleName()))
                .count() > max) {
            return ValidationStatus.error(Messages.businessObjectNameAlreadyExists);
        } ;
        return ValidationStatus.ok();
    }

}
