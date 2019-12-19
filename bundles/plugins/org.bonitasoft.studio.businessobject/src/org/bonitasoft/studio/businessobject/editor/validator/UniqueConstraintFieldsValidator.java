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

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.editor.editor.filter.IndexableFieldFilter;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.Field;
import org.bonitasoft.studio.businessobject.editor.model.FieldType;
import org.bonitasoft.studio.businessobject.editor.model.SimpleField;
import org.bonitasoft.studio.businessobject.editor.model.UniqueConstraint;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;

public class UniqueConstraintFieldsValidator implements IBDMValidator<UniqueConstraint> {

    private IndexableFieldFilter indexableFieldFilter = new IndexableFieldFilter();

    @Override
    public IStatus validate(UniqueConstraint constraint) {
        MultiStatus status = new MultiStatus(BusinessObjectPlugin.PLUGIN_ID, 0, "", null);

        status.add(validateEmptiness(constraint));
        status.add(validateUnknownFields(constraint));
        status.add(validateDuplicate(constraint));
        status.add(validateForbiddenType(constraint));

        return status;
    }

    private IStatus validateForbiddenType(UniqueConstraint constraint) {
        BusinessObject bo = (BusinessObject) constraint.eContainer();
        List<Field> fieldsNotIndexable = constraint.getFieldNames().stream()
                .map(fieldName -> findCorrespondingField(fieldName, bo))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(field -> !indexableFieldFilter.isIndexableField(field))
                .collect(Collectors.toList());
        if (fieldsNotIndexable.isEmpty()) {
            return ValidationStatus.ok();
        }
        String message = fieldsNotIndexable.stream()
                .map(this::getForbiddenTypeErrorMessage)
                .collect(Collectors.joining("\n"));
        return ValidationStatus.error(message);
    }

    private String getForbiddenTypeErrorMessage(Field field) {
        if (field.isCollection()) {
            return String.format(Messages.cantUseMultipleTypeInConstraint, field.getName());
        } else if (field instanceof SimpleField && ((SimpleField) field).getType() == FieldType.TEXT) {
            return String.format(Messages.cantUseTextTypeInConstraint, field.getName());
        }
        return String.format(Messages.cantHaveNullReferenceInConstraint, field.getName());
    }

    private Optional<Field> findCorrespondingField(String fieldName, BusinessObject bo) {
        return bo.getFields().stream().filter(field -> Objects.equals(field.getName(), fieldName)).findFirst();
    }

    private IStatus validateDuplicate(UniqueConstraint constraint) {
        return constraint.getFieldNames().size() > constraint.getFieldNames().stream().distinct().count()
                ? ValidationStatus.error(String.format(Messages.fieldsDuplicated, constraint.getName()))
                : ValidationStatus.ok();
    }

    private IStatus validateUnknownFields(UniqueConstraint constraint) {
        BusinessObject bo = (BusinessObject) constraint.eContainer();
        List<String> unkownFields = constraint.getFieldNames().stream()
                .filter(fieldName -> bo.getFields().stream().map(Field::getName).noneMatch(fieldName::equals))
                .collect(Collectors.toList());
        return unkownFields.isEmpty()
                ? ValidationStatus.ok()
                : ValidationStatus.error(String.format(Messages.constraintReferencesUnknownAttributes, constraint.getName(),
                        unkownFields.toString()));
    }

    private IStatus validateEmptiness(UniqueConstraint constraint) {
        return constraint.getFieldNames().isEmpty()
                ? ValidationStatus.error(String.format(Messages.constraintFieldEmptiness, constraint.getName()))
                : ValidationStatus.ok();
    }

    @Override
    public String getValidatorType() {
        return Messages.constraints;
    }

}
