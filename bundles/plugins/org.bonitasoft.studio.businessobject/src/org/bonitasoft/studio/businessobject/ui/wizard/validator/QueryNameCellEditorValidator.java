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

import org.bonitasoft.engine.bdm.BDMQueryUtil;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.Query;
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
 * 
 */
public class QueryNameCellEditorValidator implements ICellEditorValidator, IValidator {

    public static final int MAX_QUERY_NAME_LENGTH = 150;

    private final BusinessObject bo;

    private final Query query;

    public QueryNameCellEditorValidator(BusinessObject bo, Query query) {
        this.bo = bo;
        this.query = query;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ICellEditorValidator#isValid(java.lang.Object)
     */
    @Override
    public String isValid(Object value) {
        final IStatus status = doValidate(value);
        if (!status.isOK()) {
            return status.getMessage();
        }
        return null;
    }

    protected IStatus doValidate(Object value) {
        String name = null;
        if (value != null) {
            name = value.toString();
        }

        IStatus status = JavaConventions.validateMethodName(name, JavaCore.VERSION_1_6, JavaCore.VERSION_1_6);
        if (!status.isOK()) {
            return status;
        }
        status = new InputLengthValidator(name, MAX_QUERY_NAME_LENGTH).validate(name);
        if (!status.isOK()) {
            return status;
        }
        for (final String queryName : BDMQueryUtil.getAllProvidedQueriesNameForBusinessObject(bo)) {
            if (name.equalsIgnoreCase(queryName)) {
                return ValidationStatus.error(Messages.bind(Messages.queryNameReserved, name));
            }
        }
        for (final Query query : bo.getQueries()) {
            if (query.getName().equalsIgnoreCase(name) && !this.query.equals(query)) {
                return ValidationStatus.error(Messages.queryNameAlreadyExists);
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
