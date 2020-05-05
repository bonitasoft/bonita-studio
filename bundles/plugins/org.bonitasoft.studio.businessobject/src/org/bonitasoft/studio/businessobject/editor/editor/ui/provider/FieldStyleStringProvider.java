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
package org.bonitasoft.studio.businessobject.editor.editor.ui.provider;

import java.util.function.Function;

import org.bonitasoft.studio.businessobject.editor.editor.ui.styler.AttributeTypeStyler;
import org.bonitasoft.studio.businessobject.editor.model.Field;
import org.bonitasoft.studio.businessobject.editor.model.RelationField;
import org.bonitasoft.studio.businessobject.editor.model.SimpleField;
import org.bonitasoft.studio.ui.ColorConstants;
import org.eclipse.jface.viewers.StyledString;

public class FieldStyleStringProvider implements Function<Field, StyledString> {

    private AttributeTypeStyler attributesSimpleTypeStyler;
    private AttributeTypeStyler attributesComplexTypeStyler;

    public FieldStyleStringProvider() {
        attributesSimpleTypeStyler = new AttributeTypeStyler(ColorConstants.SIMPLE_TYPE_RGB);
        attributesComplexTypeStyler = new AttributeTypeStyler(ColorConstants.COMPLEX_TYPE_RGB);
    }

    @Override
    public StyledString apply(Field field) {
        StyledString styledString = new StyledString(field.getName());
        if (field instanceof SimpleField) {
            String type = ((SimpleField) field).getType().toString();
            styledString.append(
                    String.format(" -- %s%s", type.substring(0, 1), type.substring(1, type.length()).toLowerCase()),
                    attributesSimpleTypeStyler);
        } else {
            styledString.append(String.format(" -- %s", ((RelationField) field).getReference().getSimpleName()),
                    attributesComplexTypeStyler);
        }
        return styledString;
    }

    public void dispose() {
        attributesSimpleTypeStyler.dispose();
        attributesComplexTypeStyler.dispose();
    }

}
