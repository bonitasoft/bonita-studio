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
package org.bonitasoft.studio.businessobject.ui.wizard.editingsupport;

import org.bonitasoft.engine.bdm.model.field.Field;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Romain Bioteau
 * 
 */
public class MandatoryEditingSupport extends EditingSupport {

    public MandatoryEditingSupport(ColumnViewer viewer) {
        super(viewer);
    }

    @Override
    protected void setValue(Object element, Object value) {
        boolean mandatory = (Boolean) value;
        ((Field) element).setNullable(!mandatory);
        getViewer().update(element, null);
    }

    @Override
    protected Object getValue(Object element) {
        return !((Field) element).isNullable();
    }

    @Override
    protected CellEditor getCellEditor(Object element) {
        return new CheckboxCellEditor((Composite) getViewer().getControl(), SWT.CHECK);
    }

    @Override
    protected boolean canEdit(Object element) {
        if (element instanceof Field) {
            if (((Field) element).isCollection() != null && ((Field) element).isCollection()) {
                return false;
            }
        }
        return true;
    }

}
