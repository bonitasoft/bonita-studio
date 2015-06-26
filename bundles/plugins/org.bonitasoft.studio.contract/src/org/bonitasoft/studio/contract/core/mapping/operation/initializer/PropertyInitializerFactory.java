/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.contract.core.mapping.operation.initializer;

import java.util.Objects;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.model.process.BusinessObjectData;

public class PropertyInitializerFactory {

    public IPropertyInitializer newPropertyInitializer(final FieldToContractInputMapping mapping, final BusinessObjectData data, final Field rootField) {
        final Field field = mapping.getField();
        if (field instanceof SimpleField) {
            return new SimpleFieldPropertyInitializer(firstMultipleParentBusinessObject(mapping), (SimpleField) field, mapping.getContractInput());
        } else if (field instanceof RelationField) {
            final RelationField relationField = (RelationField) field;
            if (Objects.equals(field, rootField)) {
                return field.isCollection() ?
                        new BusinessObjectListInitializer(relationField, mapping.getContractInput(), toRefName(mapping, data)) :
                        new BusinessObjectInitializer(relationField, toRefName(mapping, data));
            }
            return field.isCollection() ?
                    new MultipleCompositionReferencePropertyInitializer(firstMultipleParentBusinessObject(mapping), relationField, mapping.getContractInput(),
                            toRefName(mapping.getParent(), data))
                    : new CompositionReferencePropertyInitializer(relationField, mapping.getContractInput(), toRefName(mapping, data));
        }
        throw new UnsupportedOperationException(field.getClass().getName() + " is not supported");
    }

    private BusinessObject firstMultipleParentBusinessObject(final FieldToContractInputMapping mapping) {
        FieldToContractInputMapping parentMapping = mapping.getParent();
        while (parentMapping != null && !parentMapping.getField().isCollection()) {
            parentMapping = parentMapping.getParent();
        }

        return parentMapping != null ? ((RelationField) parentMapping.getField()).getReference() : null;
    }

    private String toRefName(final FieldToContractInputMapping mapping, final BusinessObjectData data) {
        return mapping.getParent() != null ? mapping.getParent().getField().getName() + "Var" + "." + mapping.getField().getName() : data.getName() + "."
                + mapping.getField().getName();
    }

}
