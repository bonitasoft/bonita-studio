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
import org.eclipse.jface.viewers.LabelProvider;

import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;

/**
 * @author Romain Bioteau
 * 
 */
public class FieldLabelProvider extends LabelProvider {

    @Override
    public String getText(Object element) {
        if (element instanceof SimpleField) {
            return ((Field) element).getName() + " -- " + ((SimpleField) element).getType().name();
        }
        if (element instanceof RelationField) {
            if (((RelationField) element).getReference() != null) {
                return ((Field) element).getName() + " -- "
                        + NamingUtils.getSimpleName(((RelationField) element).getReference().getQualifiedName());
            }
            return ((Field) element).getName();
        }
        return super.getText(element);
    }

}
