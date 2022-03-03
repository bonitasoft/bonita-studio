/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.contract.ui.property.input.edit;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertyEditingSupport;

/**
 * @author Romain Bioteau
 */
public class CheckboxPropertyEditingSupport extends PropertyEditingSupport {

    public CheckboxPropertyEditingSupport(final IPropertySourceProvider sourceProvider, final ColumnViewer viewer, final String propertyID) {
        super(viewer, sourceProvider, propertyID);
    }

    @Override
    protected void setValue(final Object element, final Object value) {
        super.setValue(element, value);
        getViewer().update(element, null);
    }

    @Override
    protected CellEditor getCellEditor(final Object element) {
        return new CheckboxCellEditor((Composite) getViewer().getControl(), SWT.CHECK);
    }

}
