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

import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.Query;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;

public class QueryNameValidator implements IBDMValidator<Query> {

    public static final int MAX_QUERY_NAME_LENGTH = 150;

    private IObservableValue<BusinessObject> boSelectedObservable;

    public QueryNameValidator(IObservableValue<BusinessObject> boSelectedObservable) {
        this.boSelectedObservable = boSelectedObservable;
    }

    @Override
    public IStatus validate(Query value) {
        String name = value.getName();
        if (name == null || name.isEmpty()) {
            return ValidationStatus.error(Messages.queryNameRequired);
        }

        MultiStatus status = new MultiStatus(BusinessObjectPlugin.PLUGIN_ID, 0, "", null);

        status.add(validateJavaConvention(name));
        status.add(validateNameLength(name));
        status.add(validateProvidedQueryName(name));
        status.add(validateUniqueness(value));

        return status;
    }

    private IStatus validateUniqueness(Query query) {
        return boSelectedObservable.getValue().getQueries().stream()
                .filter(aQuery -> !Objects.equals(aQuery, query))
                .map(Query::getName)
                .anyMatch(query.getName()::equals)
                        ? ValidationStatus.error(Messages.queryNameAlreadyExists)
                        : ValidationStatus.ok();
    }

    private IStatus validateProvidedQueryName(String name) {
        return boSelectedObservable.getValue().getDefaultQueries().stream()
                .map(Query::getName)
                .anyMatch(name::equalsIgnoreCase)
                        ? ValidationStatus.error(Messages.bind(Messages.queryNameReserved, name))
                        : ValidationStatus.ok();
    }

    private IStatus validateNameLength(String name) {
        return new InputLengthValidator(name, MAX_QUERY_NAME_LENGTH).validate(name);
    }

    protected IStatus validateJavaConvention(String name) {
        return JavaConventions.validateFieldName(name, JavaCore.VERSION_1_8, JavaCore.VERSION_1_8);
    }

    @Override
    public String getValidatorType() {
        return Messages.queries;
    }

}
