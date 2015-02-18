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

import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.eclipse.jface.viewers.ColumnLabelProvider;

import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.RelationField.Type;

/**
 * @author Romain Bioteau
 * 
 */
public class RelationKindLabelProvider extends ColumnLabelProvider {

    @Override
    public String getText(Object element) {
        if (element instanceof RelationField) {
            RelationField field = (RelationField) element;
            if (Type.COMPOSITION == field.getType()) {
                return Messages.composition;
            } else if (Type.AGGREGATION == field.getType()) {
                return Messages.aggregation;
            }
        }
        if (element instanceof Type) {
            if (Type.COMPOSITION == element) {
                return Messages.composition;
            } else if (Type.AGGREGATION == element) {
                return Messages.aggregation;
            }
        }
        return "";
    }

}
