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

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.bdm.model.Index;
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
public class IndexNameCellEditorValidator implements ICellEditorValidator, IValidator {

    public static final int MAX_INDEX_NAME_LENGTH = 20;

    private final BusinessObjectModel bom;

    private final Index index;

    private final SQLNameValidator sqlNameValidator;

    public IndexNameCellEditorValidator(final BusinessObjectModel bom, final Index index) {
        this.bom = bom;
        this.index = index;
        sqlNameValidator = new SQLNameValidator(MAX_INDEX_NAME_LENGTH);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ICellEditorValidator#isValid(java.lang.Object)
     */
    @Override
    public String isValid(final Object value) {
        final IStatus status = doValidate(value);
        if (!status.isOK()) {
            return status.getMessage();
        }
        return null;
    }

    protected IStatus doValidate(final Object value) {
        IStatus status = JavaConventions.validateFieldName((String) value, JavaCore.VERSION_1_6, JavaCore.VERSION_1_6);
        if (!status.isOK()) {
            return status;
        }
        status = new InputLengthValidator(value.toString(), MAX_INDEX_NAME_LENGTH).validate(value);
        if (!status.isOK()) {
            return status;
        }
        if (sqlNameValidator.isSQLKeyword(value.toString())) {
            return ValidationStatus.error(Messages.bind(Messages.reservedKeyWord, value.toString()));
        }
        if (!sqlNameValidator.isValid(value.toString())) {
            return ValidationStatus.error(Messages.bind(Messages.invalidSQLIdentifier, value.toString()));
        }
        for (final BusinessObject bo : bom.getBusinessObjects()) {
            for (final Index index : bo.getIndexes()) {
                if (index.getName().equalsIgnoreCase(value.toString()) && !this.index.equals(index)) {
                    return ValidationStatus.error(Messages.indexNameAlreadyExists);
                }
            }
        }
        return ValidationStatus.ok();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.databinding.validation.IValidator#validate(java.lang.Object)
     */
    @Override
    public IStatus validate(final Object value) {
        return doValidate(value);
    }

}
