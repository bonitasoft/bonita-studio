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

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.editor.editor.filter.IndexableFieldFilter;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.Field;
import org.bonitasoft.studio.businessobject.editor.model.FieldType;
import org.bonitasoft.studio.businessobject.editor.model.Index;
import org.bonitasoft.studio.businessobject.editor.model.SimpleField;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;

public class IndexFieldsValidator implements IBDMValidator<Index> {

    private IndexableFieldFilter indexableFieldFilter = new IndexableFieldFilter();

    @Override
    public IStatus validate(Index index) {
        MultiStatus status = new MultiStatus(BusinessObjectPlugin.PLUGIN_ID, 0, "", null);

        status.add(validateEmptiness(index));
        status.add(validateUnknownFields(index));
        status.add(validateDuplicate(index));
        status.add(validateForbiddenType(index));

        return status;
    }

    private IStatus validateForbiddenType(Index index) {
        BusinessObject bo = (BusinessObject) index.eContainer();
        List<Field> fieldsNotIndexable = index.getFieldNames().stream()
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
            return String.format(Messages.cantUseMultipleTypeInIndex, field.getName());
        } else if (field instanceof SimpleField && ((SimpleField) field).getType() == FieldType.TEXT) {
            return String.format(Messages.cantUseTextTypeInIndex, field.getName());
        }
        return String.format(Messages.cantHaveNullReferenceInIndex, field.getName());
    }

    private Optional<Field> findCorrespondingField(String fieldName, BusinessObject bo) {
        return bo.getFields().stream().filter(field -> Objects.equals(field.getName(), fieldName)).findFirst();
    }

    private IStatus validateDuplicate(Index index) {
        return index.getFieldNames().size() > index.getFieldNames().stream().distinct().count()
                ? ValidationStatus.error(String.format(Messages.fieldsDuplicated, index.getName()))
                : ValidationStatus.ok();
    }

    private IStatus validateUnknownFields(Index index) {
        BusinessObject bo = (BusinessObject) index.eContainer();
        List<String> unkownFields = index.getFieldNames().stream()
                .filter(fieldName -> bo.getFields().stream().map(Field::getName).noneMatch(fieldName::equals))
                .collect(Collectors.toList());
        return unkownFields.isEmpty()
                ? ValidationStatus.ok()
                : ValidationStatus.error(String.format(Messages.indexReferencesUnknownAttributes, index.getName(),
                        unkownFields.toString()));
    }

    private IStatus validateEmptiness(Index index) {
        return index.getFieldNames().isEmpty()
                ? ValidationStatus.error(String.format(Messages.indexFieldEmptiness, index.getName()))
                : ValidationStatus.ok();
    }

    @Override
    public String getValidatorType() {
        return Messages.indexes;
    }

}
