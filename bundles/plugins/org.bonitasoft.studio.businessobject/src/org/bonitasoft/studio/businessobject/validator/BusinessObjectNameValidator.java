/**
 * Copyright (C) 2018 BonitaSoft S.A.
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
package org.bonitasoft.studio.businessobject.validator;

import java.util.Collection;
import java.util.Objects;

import org.bonitasoft.engine.bdm.validator.SQLNameValidator;
import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.editor.model.Package;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;

public class BusinessObjectNameValidator implements IBDMValidator<BusinessObject> {

    public static final int MAX_TABLE_NAME_LENGTH = 30;

    private IObservableValue<BusinessObjectModel> modelObservable;
    private SQLNameValidator sqlNameValidator;

    public BusinessObjectNameValidator(IObservableValue<BusinessObjectModel> modelObservable) {
        this.modelObservable = modelObservable;
        this.sqlNameValidator = new SQLNameValidator(MAX_TABLE_NAME_LENGTH);
    }

    @Override
    public IStatus validate(BusinessObject businessObject) {
        String name = businessObject.getSimpleName();
        if (name == null || name.isEmpty()) {
            return ValidationStatus.error(Messages.boNameRequired);
        }

        MultiStatus status = new MultiStatus(BusinessObjectPlugin.PLUGIN_ID, 0, "", null);

        status.add(validateNameLength(name));
        status.add(validateUniqueness((businessObject), name));
        status.add(validateWhiteSpaceCharacter(name));
        status.add(validateUnderscoreCharacter(name));
        status.add(validateSqlValidity(name));
        status.add(validateJavaConvention(name));

        return status;
    }

    protected IStatus validateJavaConvention(String name) {
        return JavaConventions.validateJavaTypeName(name, JavaCore.VERSION_1_8, JavaCore.VERSION_1_8);
    }

    private IStatus validateSqlValidity(String name) {
        return sqlNameValidator.isValid(name)
                ? ValidationStatus.ok()
                : sqlNameValidator.isSQLKeyword(name)
                        ? ValidationStatus.error(Messages.bind(Messages.reservedKeyWord, name))
                        : ValidationStatus.error(Messages.bind(Messages.invalidSQLIdentifier, name));
    }

    private IStatus validateWhiteSpaceCharacter(String name) {
        return name.contains(" ")
                ? ValidationStatus.error(Messages.errorMessageNoWhitespaceInDataTypeNames)
                : ValidationStatus.ok();
    }

    private IStatus validateUnderscoreCharacter(String name) {
        return name.contains("_")
                ? ValidationStatus.error(Messages.errorMessageNoUnderscoreInBoNames)
                : ValidationStatus.ok();
    }

    private IStatus validateUniqueness(BusinessObject selectedBo, String name) {
        return modelObservable.getValue().getPackages().stream()
                .map(Package::getBusinessObjects)
                .flatMap(Collection::stream)
                .filter(bo -> !Objects.equals(bo, selectedBo))
                .map(BusinessObject::getSimpleName)
                .map(String::toLowerCase)
                .anyMatch(name.toLowerCase()::equals)
                        ? ValidationStatus.error(Messages.businessObjectNameAlreadyExists)
                        : ValidationStatus.ok();
    }

    private IStatus validateNameLength(String name) {
        return new InputLengthValidator(name, MAX_TABLE_NAME_LENGTH).validate(name);
    }

    @Override
    public String getValidatorType() {
        return Messages.businessObject;
    }

}
