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

import java.util.Collection;
import java.util.Objects;

import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.editor.model.Package;
import org.bonitasoft.studio.businessobject.editor.model.RelationField;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;

public class AttributeReferenceExitenceValidator implements IBDMValidator {

    private IObservableValue<BusinessObjectModel> businessObjectModelObservable;

    public AttributeReferenceExitenceValidator(IObservableValue<BusinessObjectModel> businessObjectModelObservable) {
        this.businessObjectModelObservable = businessObjectModelObservable;
    }

    @Override
    public IStatus validate(Object value) {
        if (value instanceof BusinessObject) {
            return validateBusinessObjectAttributesExistenceReference((BusinessObject) value);
        } else if (value instanceof RelationField) {
            return validateAttributeExistenceReference((RelationField) value);
        }
        return ValidationStatus.ok();
    }

    private IStatus validateBusinessObjectAttributesExistenceReference(BusinessObject businessObject) {
        MultiStatus status = new MultiStatus(BusinessObjectPlugin.PLUGIN_ID, 0, "", null);

        businessObject.getFields().stream()
                .filter(RelationField.class::isInstance)
                .map(RelationField.class::cast)
                .map(this::validateAttributeExistenceReference)
                .forEach(status::add);

        return status;
    }

    private IStatus validateAttributeExistenceReference(RelationField field) {
        return businessObjectModelObservable.getValue().getPackages().stream()
                .map(Package::getBusinessObjects)
                .flatMap(Collection::stream)
                .anyMatch(businessObject -> Objects.equals(businessObject.getQualifiedName(),
                        field.getReference().getQualifiedName()))
                                ? ValidationStatus.ok()
                                : ValidationStatus
                                        .error(String.format(Messages.unknownBusinessObjectReference, field.getName()));
    }

    @Override
    public String getValidatorType() {
        return Messages.attributes;
    }

}
