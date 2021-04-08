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

import org.bonitasoft.engine.bdm.BDMQueryUtil;
import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.editor.model.Query;
import org.bonitasoft.studio.businessobject.editor.model.QueryParameter;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;

public class QueryParameterNameValidator implements IBDMValidator<QueryParameter> {

    private IObservableValue<Query> querySelectedObservable;

    public QueryParameterNameValidator(IObservableValue<Query> querySelectedObservable) {
        this.querySelectedObservable = querySelectedObservable;
    }

    @Override
    public IStatus validate(QueryParameter value) {
        String name = value.getName();
        if (name == null || name.isEmpty()) {
            return ValidationStatus.error(Messages.queryParameterNameRequired);
        }
        MultiStatus status = new MultiStatus(BusinessObjectPlugin.PLUGIN_ID, 0, "", null);

        status.add(validateJavaConvention(name));
        status.add(validateReservedName(name));
        status.add(validateUniqueness(value));

        return status;
    }

    private IStatus validateUniqueness(QueryParameter parameter) {
        return querySelectedObservable.getValue() == null
                ? ValidationStatus.ok()
                : querySelectedObservable.getValue().getQueryParameters().stream()
                        .filter(aParameter -> !Objects.equals(aParameter, parameter))
                        .map(QueryParameter::getName)
                        .anyMatch(parameter.getName()::equals)
                                ? ValidationStatus
                                        .error(Messages.bind(Messages.queryParameterNameAlreadyExists, parameter.getName()))
                                : ValidationStatus.ok();
    }

    private IStatus validateReservedName(String name) {
        return name.equalsIgnoreCase(BDMQueryUtil.START_INDEX_PARAM_NAME)
                || name.equalsIgnoreCase(BDMQueryUtil.MAX_RESULTS_PARAM_NAME)
                        ? ValidationStatus.error(Messages.bind(Messages.queryParameterNameReservedForPagination, name))
                        : ValidationStatus.ok();
    }

    protected IStatus validateJavaConvention(String name) {
        return JavaConventions.validateFieldName(name, JavaCore.VERSION_1_8, JavaCore.VERSION_1_8);
    }

    @Override
    public String getValidatorType() {
        return Messages.queries;
    }

}
