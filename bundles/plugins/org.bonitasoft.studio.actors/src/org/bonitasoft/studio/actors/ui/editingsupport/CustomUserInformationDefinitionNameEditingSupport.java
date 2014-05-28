/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.actors.ui.editingsupport;

import java.util.HashSet;

import org.bonitasoft.studio.actors.validator.CustomerUserInformationDefinitionNameValidator;
import org.bonitasoft.studio.common.jface.ColumnViewerUpdateListener;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ObservableValueEditingSupport;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * @author Florine Boudin
 *
 */
public class CustomUserInformationDefinitionNameEditingSupport extends ObservableValueEditingSupport {
	
    private DataBindingContext dbc;
    
    /**
     * @param viewer
     * @param dbc
     */
	public CustomUserInformationDefinitionNameEditingSupport(ColumnViewer viewer, DataBindingContext dbc) {
		super(viewer, dbc);
        this.dbc = dbc;
	}


	@Override
	protected IObservableValue doCreateCellEditorObservable(CellEditor cellEditor) {
        return SWTObservables.observeText(cellEditor.getControl(), SWT.Modify);
	}

	@Override
	protected IObservableValue doCreateElementObservable(Object element, ViewerCell viewerCell) {
        IObservableValue observeValue = PojoObservables.observeValue(element, "name");
        observeValue.addValueChangeListener(new ColumnViewerUpdateListener(getViewer(), element));
        return observeValue;
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
        TextCellEditor textCellEditor = new TextCellEditor((Composite) getViewer().getControl());
        Text textControl = (Text) textCellEditor.getControl();
        textControl.setTextLimit(8);
        return textCellEditor;
    }
	
	@Override
	protected Binding createBinding(IObservableValue target, IObservableValue model) {
        UpdateValueStrategy targetToModel = new UpdateValueStrategy();
        targetToModel.setAfterGetValidator(new CustomerUserInformationDefinitionNameValidator(new HashSet<String>()));
        return dbc.bindValue(target, model, targetToModel, null);
    }

}
