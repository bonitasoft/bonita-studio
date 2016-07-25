/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.businessobject.ui.wizard.provider;

import org.bonitasoft.studio.common.NamingUtils;
import org.eclipse.jface.viewers.ColumnLabelProvider;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;

/**
 * @author Romain Bioteau
 * 
 */
public class FieldTypeLabelProvider extends ColumnLabelProvider {

    @Override
    public String getText(Object element) {
        if (element instanceof FieldType) {
            return ((FieldType) element).name();
        }
        if (element instanceof SimpleField) {
            return ((SimpleField) element).getType().name();
        }
        if (element instanceof BusinessObject) {
            return NamingUtils.getSimpleName(((BusinessObject) element).getQualifiedName());
        }
        if (element instanceof RelationField && ((RelationField) element).getReference() != null) {
            return NamingUtils.getSimpleName(((RelationField) element).getReference().getQualifiedName());
        } else if (((RelationField) element).getReference() == null) {
            return "";
        }
        return super.getText(element);
    }

}
