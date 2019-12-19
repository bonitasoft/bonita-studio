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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.RelationField;
import org.bonitasoft.studio.businessobject.editor.model.RelationType;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class CyclicCompositionValidator implements IBDMValidator<BusinessObject> {

    @Override
    public IStatus validate(BusinessObject businessObject) {

        List<RelationField> compositionReferences = businessObject.getFields().stream()
                .filter(RelationField.class::isInstance)
                .map(RelationField.class::cast)
                .filter(field -> Objects.equals(field.getType(), RelationType.COMPOSITION))
                .collect(Collectors.toList());

        boolean cycleFound = compositionReferences.stream()
                .anyMatch(compositionReference -> hasCircularReference(compositionReference, new ArrayList<>(),
                        businessObject));

        return cycleFound
                ? ValidationStatus
                        .error(String.format(Messages.cyclicComposition, businessObject.getSimpleName()))
                : ValidationStatus.ok();
    }

    protected boolean hasCircularReference(RelationField field, List<BusinessObject> references, BusinessObject boRef) {
        BusinessObject reference = field.getReference();
        if (!references.contains(reference)) {
            references.add(reference);
            if (Objects.equals(reference, boRef)) {
                return true;
            }
            return reference.getFields().stream()
                    .filter(aField -> !Objects.equals(field, aField))
                    .filter(RelationField.class::isInstance)
                    .map(RelationField.class::cast)
                    .filter(aField -> Objects.equals(aField.getType(), RelationType.COMPOSITION))
                    .anyMatch(aField -> hasCircularReference(aField, references, boRef));
        }
        return references.contains(boRef);
    }

    @Override
    public String getValidatorType() {
        return Messages.businessObject;
    }
}
