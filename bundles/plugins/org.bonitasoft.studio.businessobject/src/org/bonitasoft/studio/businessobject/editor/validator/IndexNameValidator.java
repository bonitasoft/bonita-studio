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
package org.bonitasoft.studio.businessobject.editor.validator;

import java.util.Collection;
import java.util.Objects;

import org.bonitasoft.engine.bdm.validator.SQLNameValidator;
import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.editor.model.Index;
import org.bonitasoft.studio.businessobject.editor.model.Package;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;

public class IndexNameValidator implements IBDMValidator<Index> {

    public static final int MAX_INDEX_NAME_LENGTH = 20;

    private SQLNameValidator sqlNameValidator;

    private IObservableValue<BusinessObjectModel> modelObservable;

    public IndexNameValidator(IObservableValue<BusinessObjectModel> modelObservable) {
        this.modelObservable = modelObservable;
        this.sqlNameValidator = new SQLNameValidator(MAX_INDEX_NAME_LENGTH);
    }

    @Override
    public IStatus validate(Index index) {
        String name = index.getName();
        if (name == null || name.isEmpty()) {
            return ValidationStatus.error(Messages.indexWithoutName);
        }
        MultiStatus status = new MultiStatus(BusinessObjectPlugin.PLUGIN_ID, 0, "", null);

        status.add(validateJavaConvention(name));
        status.add(validateNameLength(name));
        status.add(validateSqlValidity(name));
        status.add(validateUniqueness(index));

        return status;
    }

    private IStatus validateUniqueness(Index index) {
        boolean nameDuplicated = modelObservable.getValue().getPackages().stream()
                .map(Package::getBusinessObjects)
                .flatMap(Collection::stream)
                .map(BusinessObject::getIndexes)
                .flatMap(Collection::stream)
                .anyMatch(anIndex -> Objects.equals(anIndex.getName().toLowerCase(), index.getName().toLowerCase())
                        && !Objects.equals(anIndex, index));
        if (nameDuplicated) {
            return ValidationStatus.error(Messages.indexNameAlreadyExists);
        }
        return ValidationStatus.ok();
    }

    private IStatus validateSqlValidity(String name) {
        return sqlNameValidator.isValid(name)
                ? ValidationStatus.ok()
                : sqlNameValidator.isSQLKeyword(name)
                        ? ValidationStatus.error(Messages.bind(Messages.reservedKeyWord, name))
                        : ValidationStatus.error(Messages.bind(Messages.invalidSQLIdentifier, name));
    }

    private IStatus validateNameLength(String name) {
        return new InputLengthValidator(name, MAX_INDEX_NAME_LENGTH).validate(name);
    }

    protected IStatus validateJavaConvention(String name) {
        return JavaConventions.validateFieldName(name, JavaCore.VERSION_1_8, JavaCore.VERSION_1_8);
    }

    @Override
    public String getValidatorType() {
        return Messages.indexes;
    }

}
