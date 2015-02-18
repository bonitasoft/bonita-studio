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

import org.bonitasoft.studio.businessobject.ui.wizard.listener.ColumnViewerUpdateListener;
import org.bonitasoft.studio.businessobject.ui.wizard.validator.QueryParameterNameCellEditorValidator;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.internal.databinding.beans.BeanObservableValueDecorator;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ObservableValueEditingSupport;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import org.bonitasoft.engine.bdm.model.Query;
import org.bonitasoft.engine.bdm.model.QueryParameter;

/**
 * @author Romain Bioteau
 * 
 */
@SuppressWarnings({ "restriction" })
public class QueryParameterNameEditingSupport extends ObservableValueEditingSupport {

    private DataBindingContext dbc;

    private Query query;

    private Binding queryBinding;

    /**
     * @param queryBinding
     * @param viewer
     * @param dbc
     */
    public QueryParameterNameEditingSupport(Query query, Binding queryBinding, ColumnViewer viewer, DataBindingContext dbc) {
        super(viewer, dbc);
        this.dbc = dbc;
        this.query = query;
        this.queryBinding = queryBinding;
    }

    @Override
    protected CellEditor getCellEditor(Object element) {
        return new TextCellEditor((Composite) getViewer().getControl());
    }

    @Override
    protected IObservableValue doCreateElementObservable(final Object element, ViewerCell cell) {
        IObservableValue observeValue = PojoObservables.observeValue(element, "name");
        observeValue.addValueChangeListener(new ColumnViewerUpdateListener(getViewer(), element));
        observeValue.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(ValueChangeEvent event) {
                queryBinding.validateTargetToModel();
            }
        });
        return observeValue;
    }

    @Override
    protected IObservableValue doCreateCellEditorObservable(CellEditor cellEditor) {
        return SWTObservables.observeText(cellEditor.getControl(), SWT.Modify);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.databinding.viewers.ObservableValueEditingSupport#createBinding(org.eclipse.core.databinding.observable.value.IObservableValue,
     * org.eclipse.core.databinding.observable.value.IObservableValue)
     */
    @Override
    protected Binding createBinding(IObservableValue target, IObservableValue model) {
        UpdateValueStrategy targetToModel = new UpdateValueStrategy();
        targetToModel.setAfterGetValidator(new QueryParameterNameCellEditorValidator(query, (QueryParameter) ((BeanObservableValueDecorator) model)
                .getObserved()));
        return dbc.bindValue(target, model, targetToModel, null);
    }

}
