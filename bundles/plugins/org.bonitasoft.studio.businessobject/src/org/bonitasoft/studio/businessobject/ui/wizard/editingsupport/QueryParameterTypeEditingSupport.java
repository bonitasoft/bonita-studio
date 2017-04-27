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
package org.bonitasoft.studio.businessobject.ui.wizard.editingsupport;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bonitasoft.studio.common.jface.ColumnViewerUpdateListener;
import org.bonitasoft.studio.common.jface.databinding.CellEditorViewerProperties;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.jface.databinding.viewers.ObservableValueEditingSupport;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Romain Bioteau
 */
public class QueryParameterTypeEditingSupport extends ObservableValueEditingSupport {

    public QueryParameterTypeEditingSupport(final ColumnViewer viewer, final DataBindingContext dbc) {
        super(viewer, dbc);
    }

    @Override
    protected CellEditor getCellEditor(final Object element) {
        final ComboBoxViewerCellEditor cellEditor = new ComboBoxViewerCellEditor((Composite) getViewer().getControl(),
                SWT.READ_ONLY);
        cellEditor.setLabelProvider(new LabelProvider());
        cellEditor.setContentProvider(ArrayContentProvider.getInstance());
        cellEditor.setInput(getAvailableClassnames());
        return cellEditor;
    }

    @Override
    protected IObservableValue doCreateElementObservable(final Object element, final ViewerCell cell) {
        final IObservableValue observeValue = PojoObservables.observeValue(element, "className");
        observeValue.addValueChangeListener(new ColumnViewerUpdateListener(getViewer(), element));
        return observeValue;
    }

    @Override
    protected IObservableValue doCreateCellEditorObservable(final CellEditor cellEditor) {
        final IValueProperty cellEditorProperty = new CellEditorViewerProperties().value(ViewerProperties.singleSelection());
        return cellEditorProperty.observe(cellEditor);
    }

    protected List<String> getAvailableClassnames() {
        return Arrays.asList(String.class.getName(), Boolean.class.getName(), Integer.class.getName(), Long.class.getName(),
                Double.class.getName(), LocalDate.class.getName(), LocalDateTime.class.getName(),
                OffsetDateTime.class.getName(), Date.class.getName());
    }
}
