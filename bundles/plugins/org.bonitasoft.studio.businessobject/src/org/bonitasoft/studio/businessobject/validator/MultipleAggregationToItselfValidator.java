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

import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.RelationField;
import org.bonitasoft.studio.businessobject.editor.model.RelationType;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class MultipleAggregationToItselfValidator implements IBDMValidator<BusinessObject> {

    @Override
    public IStatus validate(BusinessObject businessObject) {

        return aggregationRelationsToItself(businessObject)
                ? ValidationStatus.error(String.format(Messages.multipleAggregationToItself, businessObject.getSimpleName()))
                : ValidationStatus.ok();
    }

    private boolean aggregationRelationsToItself(BusinessObject businessObject) {
        return businessObject.getFields().stream()
                .filter(RelationField.class::isInstance)
                .map(RelationField.class::cast)
                .filter(field -> Objects.equals(field.getReference(), businessObject))
                .filter(field -> Objects.equals(field.getType(), RelationType.AGGREGATION))
                .anyMatch(RelationField::isCollection);
    }

    @Override
    public String getValidatorType() {
        return Messages.businessObject;
    }

}
