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

import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.editor.model.Package;
import org.bonitasoft.studio.businessobject.editor.model.RelationField;
import org.bonitasoft.studio.businessobject.editor.model.RelationType;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class AggregationAndCompositionValidator implements IBDMValidator<BusinessObject> {

    private IObservableValue<BusinessObjectModel> modelObservable;

    public AggregationAndCompositionValidator(IObservableValue<BusinessObjectModel> modelObservable) {
        this.modelObservable = modelObservable;
    }

    @Override
    public IStatus validate(BusinessObject businessObject) {

        boolean usedInAggregation = modelObservable.getValue().getPackages().stream()
                .map(Package::getBusinessObjects)
                .flatMap(Collection::stream)
                .anyMatch(aBo -> areInRelation(aBo, businessObject, RelationType.AGGREGATION));
        boolean usedInComposition = modelObservable.getValue().getPackages().stream()
                .map(Package::getBusinessObjects)
                .flatMap(Collection::stream)
                .anyMatch(aBo -> areInRelation(aBo, businessObject, RelationType.COMPOSITION));

        return usedInAggregation && usedInComposition
                ? ValidationStatus
                        .warning(String.format(Messages.boUsedInCompositionAndAggregation, businessObject.getSimpleName()))
                : ValidationStatus.ok();
    }

    private boolean areInRelation(BusinessObject parent, BusinessObject child, RelationType relation) {
        return parent.getFields().stream()
                .filter(RelationField.class::isInstance)
                .map(RelationField.class::cast)
                .filter(field -> Objects.equals(field.getReference(), child))
                .anyMatch(field -> Objects.equals(field.getType(), relation));
    }

    @Override
    public String getValidatorType() {
        return Messages.businessObject;
    }

}
