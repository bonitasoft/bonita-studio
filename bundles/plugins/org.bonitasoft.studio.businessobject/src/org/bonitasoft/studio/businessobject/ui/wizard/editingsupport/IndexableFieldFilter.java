/**
 * Copyright (C) 2017 BonitaSoft S.A.
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
package org.bonitasoft.studio.businessobject.ui.wizard.editingsupport;

import java.util.List;
import java.util.stream.Collectors;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;

public class IndexableFieldFilter {

    public List<Field> selectIndexableFields(BusinessObject businessObject) {
        return businessObject.getFields().stream()
                .filter(this::removeMultipleAttributes)
                .filter(this::removeTextAttributes)
                .filter(this::removeNullReferences)
                .collect(Collectors.toList());
    }

    private boolean removeMultipleAttributes(Field f) {
        return f.isCollection() == null || !f.isCollection();
    }

    private boolean removeTextAttributes(Field f) {
        if (f instanceof SimpleField) {
            return !((SimpleField) f).getType().equals(FieldType.TEXT);
        }
        return true;
    }

    private boolean removeNullReferences(Field f) {
        if (f instanceof RelationField) {
            return ((RelationField) f).getReference() != null;
        }
        return true;
    }
}
