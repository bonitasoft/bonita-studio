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

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import org.bonitasoft.engine.bdm.BDMQueryUtil;
import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.Query;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;

public class QueryValidator implements IValidator<Query> {

    QueryNameValidator queryNameValidator;
    QueryParameterNameValidator parameterValidator;
    IObservableValue<Query> querySelectedObservable = new WritableValue<>();
    private IObservableValue<BusinessObject> boSelectedObservable;

    public QueryValidator(IObservableValue<BusinessObject> boSelectedObservable) {
        this.boSelectedObservable = boSelectedObservable;
        queryNameValidator = new QueryNameValidator(boSelectedObservable);
        parameterValidator = new QueryParameterNameValidator(querySelectedObservable);
    }

    @Override
    public IStatus validate(Query value) {
        querySelectedObservable.setValue(value);
        MultiStatus globalStatus = new MultiStatus(BusinessObjectPlugin.PLUGIN_ID, 0, "", null);

        validate(queryNameValidator, value, globalStatus);
        validateQueryParameters(globalStatus);
        if (!hasCountQuery(value)) {
            globalStatus.add(ValidationStatus
                    .info(String.format(Messages.missingCountQuery, BDMQueryUtil.getCountQueryName(value.getName()))));
        }

        return globalStatus;
    }

    private void validate(IValidator validator, Object value, MultiStatus globalStatus) {
        IStatus status = validator.validate(value);
        if (!status.isOK()) {
            if (status instanceof MultiStatus) {
                Arrays.asList(status.getChildren())
                        .stream()
                        .filter(s -> !s.isOK())
                        .forEach(globalStatus::add);
            } else {
                globalStatus.add(status);
            }
        }
    }

    private void validateQueryParameters(MultiStatus globalStatus) {
        querySelectedObservable.getValue().getQueryParameters()
                .forEach(parameter -> validate(parameterValidator, parameter, globalStatus));
    }

    private boolean hasCountQuery(Query query) {
        BusinessObject businessObject = boSelectedObservable.getValue();
        String returnType = query.getReturnType();
        if (List.class.getName().equals(returnType)) {
            String countQueryName = BDMQueryUtil.getCountQueryName(query.getName());
            return businessObject.getQueries().stream().anyMatch(countQueryWithName(countQueryName));
        }
        return true;
    }

    private Predicate<Query> countQueryWithName(final String name) {
        return query -> Objects.equals(name, query.getName()) && Objects.equals(query.getReturnType(), Long.class.getName());
    }

}
