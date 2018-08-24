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

import org.bonitasoft.engine.bdm.model.QueryParameterTypes;
import org.bonitasoft.studio.common.jface.ColumnViewerUpdateListener;
import org.bonitasoft.studio.common.jface.databinding.CellEditorViewerProperties;
import org.bonitasoft.studio.ui.converter.ConverterBuilder;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.jface.databinding.viewers.ObservableValueEditingSupport;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Romain Bioteau
 */
public class QueryParameterTypeEditingSupport extends ObservableValueEditingSupport {

    private DataBindingContext dbc;

    public QueryParameterTypeEditingSupport(final ColumnViewer viewer, final DataBindingContext dbc) {
        super(viewer, dbc);
        this.dbc = dbc;
    }

    @Override
    protected CellEditor getCellEditor(final Object element) {
        final ComboBoxViewerCellEditor cellEditor = new ComboBoxViewerCellEditor((Composite) getViewer().getControl(),
                SWT.READ_ONLY);
        cellEditor.setLabelProvider(new LabelProviderBuilder<QueryParameterTypes>()
                .withTextProvider(QueryParameterTypes::getName).createLabelProvider());
        cellEditor.setContentProvider(ArrayContentProvider.getInstance());
        cellEditor.setInput(QueryParameterTypes.values());
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

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.databinding.viewers.ObservableValueEditingSupport#createBinding(org.eclipse.core.databinding.observable.value.IObservableValue,
     * org.eclipse.core.databinding.observable.value.IObservableValue)
     */
    @Override
    protected Binding createBinding(IObservableValue target, IObservableValue model) {
        UpdateValueStrategy targetToModel = new UpdateValueStrategy(
                UpdateValueStrategy.POLICY_CONVERT);
        targetToModel
                .setConverter(
                        ConverterBuilder.<QueryParameterTypes, String> newConverter()
                                .fromType(QueryParameterTypes.class)
                                .toType(String.class)
                                .withConvertFunction(type -> type != null ? type.getClazz().getName() : null)
                                .create());
        return dbc.bindValue(target, model, targetToModel, null);
    }

}
