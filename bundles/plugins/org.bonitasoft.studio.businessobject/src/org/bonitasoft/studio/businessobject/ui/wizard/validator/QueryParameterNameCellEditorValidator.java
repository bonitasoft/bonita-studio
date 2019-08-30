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
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.viewers.ICellEditorValidator;

import org.bonitasoft.engine.bdm.BDMQueryUtil;
import org.bonitasoft.engine.bdm.model.Query;
import org.bonitasoft.engine.bdm.model.QueryParameter;

/**
 * @author Romain Bioteau
 * 
 */
public class QueryParameterNameCellEditorValidator implements ICellEditorValidator, IValidator {

    private QueryParameter param;

    private Query query;

    public QueryParameterNameCellEditorValidator(Query query, QueryParameter param) {
        this.param = param;
        this.query = query;
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
        if (value.toString().equalsIgnoreCase(BDMQueryUtil.START_INDEX_PARAM_NAME) || value.toString().equalsIgnoreCase(BDMQueryUtil.MAX_RESULTS_PARAM_NAME)) {
            return ValidationStatus.error(Messages.bind(Messages.queryParameterNameReservedForPagination, value.toString()));
        }
        for (QueryParameter parameter : query.getQueryParameters()) {
            if (parameter.getName().equalsIgnoreCase(value.toString()) && !this.param.equals(parameter)) {
                return ValidationStatus.error(Messages.bind(Messages.queryParameterNameAlreadyExists, value.toString()));
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
