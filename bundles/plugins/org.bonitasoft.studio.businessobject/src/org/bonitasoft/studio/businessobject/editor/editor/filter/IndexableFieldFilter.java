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
package org.bonitasoft.studio.businessobject.editor.editor.filter;

import java.util.Objects;

import org.bonitasoft.studio.businessobject.editor.model.Field;
import org.bonitasoft.studio.businessobject.editor.model.FieldType;
import org.bonitasoft.studio.businessobject.editor.model.RelationField;
import org.bonitasoft.studio.businessobject.editor.model.SimpleField;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class IndexableFieldFilter extends ViewerFilter {

    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        return isIndexableField((Field) element);
    }

    public boolean isIndexableField(Field field) {
        return !(isMultiple(field) || hasTextType(field) || hasNullReference(field));
    }

    private boolean hasTextType(Field field) {
        if (field instanceof SimpleField) {
            return Objects.equals(((SimpleField) field).getType(), FieldType.TEXT);
        }
        return false;
    }

    private boolean isMultiple(Field field) {
        return field.isCollection();
    }

    private boolean hasNullReference(Field f) {
        if (f instanceof RelationField) {
            return ((RelationField) f).getReference() == null;
        }
        return false;
    }

}
