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

import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.bdm.model.Index;
import org.bonitasoft.studio.businessobject.ui.wizard.validator.IndexNameCellEditorValidator;
import org.bonitasoft.studio.businessobject.ui.wizard.validator.UniqueConstraintNameCellEditorValidator;
import org.bonitasoft.studio.common.jface.ColumnViewerUpdateListener;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.internal.databinding.beans.BeanObservableValueDecorator;
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
 * @author Romain Bioteau
 */
public class IndexNameEditingSupport extends ObservableValueEditingSupport {

    private final DataBindingContext dbc;

    private final BusinessObjectModel bom;

    /**
     * @param viewer
     * @param dbc
     */
    public IndexNameEditingSupport(final ColumnViewer viewer, final DataBindingContext dbc, final BusinessObjectModel bom) {
        super(viewer, dbc);
        this.dbc = dbc;
        this.bom = bom;
    }

    @Override
    protected CellEditor getCellEditor(final Object element) {
        final TextCellEditor textCellEditor = new TextCellEditor((Composite) getViewer().getControl());
        final Text textControl = (Text) textCellEditor.getControl();
        textControl.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBotConstants.SWTBOT_ID_UNIQUE_INDEX_NAME_TEXTEDITOR);
        textControl.setTextLimit(UniqueConstraintNameCellEditorValidator.MAX_CONSTRAINT_NAME_LENGTH + 5);
        return textCellEditor;
    }

    @Override
    protected IObservableValue doCreateElementObservable(final Object element, final ViewerCell cell) {
        final IObservableValue observeValue = PojoObservables.observeValue(element, "name");
        observeValue.addValueChangeListener(new ColumnViewerUpdateListener(getViewer(), element));
        return observeValue;
    }

    @Override
    protected IObservableValue doCreateCellEditorObservable(final CellEditor cellEditor) {
        return SWTObservables.observeText(cellEditor.getControl(), SWT.Modify);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.databinding.viewers.ObservableValueEditingSupport#createBinding(org.eclipse.core.databinding.observable.value.IObservableValue,
     * org.eclipse.core.databinding.observable.value.IObservableValue)
     */
    @Override
    protected Binding createBinding(final IObservableValue target, final IObservableValue model) {
        final UpdateValueStrategy targetToModel = new UpdateValueStrategy();
        targetToModel.setAfterGetValidator(new IndexNameCellEditorValidator(bom,
                (Index) ((BeanObservableValueDecorator) model).getObserved()));
        return dbc.bindValue(target, model, targetToModel, null);
    }

}
