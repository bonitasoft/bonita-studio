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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.Index;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.dialog.IndexFieldsSelectionDialog;
import org.bonitasoft.studio.businessobject.ui.wizard.provider.FieldLabelProvider;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * @author Romain Bioteau
 */
public class IndexFieldsEditingSupport extends EditingSupport {

    private IObservableValue viewerObservableValue;

    /**
     * @param viewer
     * @param dbc
     */
    public IndexFieldsEditingSupport(IObservableValue viewerObservableValue, ColumnViewer viewer) {
        super(viewer);
        this.viewerObservableValue = viewerObservableValue;
    }

    @Override
    protected CellEditor getCellEditor(final Object element) {
        return new DialogCellEditor((Composite) getViewer().getControl()) {

            @Override
            protected Object openDialogBox(Control cellEditorWindow) {
                BusinessObject businessObject = (BusinessObject) viewerObservableValue.getValue();

                IndexableFieldFilter indexableFieldFilter = new IndexableFieldFilter();
                List<Field> boFields = indexableFieldFilter.selectIndexableFields(businessObject);

                List<String> values = IndexFieldsEditingSupport.this.getValue(element);
                List<Field> selectedFields = new ArrayList<Field>();
                values.stream()
                        .forEach(fieldName -> businessObject.getFields()
                                .stream()
                                .filter(f -> fieldName.equals(f.getName()))
                                .forEach(f -> selectedFields.add(f)));

                IndexFieldsSelectionDialog dialog = new IndexFieldsSelectionDialog(cellEditorWindow.getShell(),
                        new FieldLabelProvider(), selectedFields, boFields, true, true);
                dialog.setTitle(Messages.bind(Messages.selectIndexFieldsTitle, ((Index) element).getName()));
                dialog.setMessage(Messages.selectIndexFieldsMessages + "\n" + Messages.warningTextIndex);
                if (dialog.open() == Dialog.OK) {
                    return dialog.getResult();
                }
                return null;
            }
        };
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.EditingSupport#canEdit(java.lang.Object)
     */
    @Override
    protected boolean canEdit(Object element) {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.EditingSupport#getValue(java.lang.Object)
     */
    @Override
    protected List<String> getValue(Object element) {
        List<String> fieldNames = ((Index) element).getFieldNames();
        return fieldNames == null ? Collections.emptyList() : fieldNames;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.EditingSupport#setValue(java.lang.Object, java.lang.Object)
     */
    @Override
    protected void setValue(final Object element, Object value) {
        if (value == null) {
            return;
        }
        Object[] fields = null;
        if (value instanceof Collection) {
            fields = ((Collection<?>) value).toArray();
        } else if (value instanceof Object[]) {
            fields = (Object[]) value;
        }
        if (fields == null) {
            fields = new Object[0];
        }
        List<String> fieldNames = new ArrayList<String>();
        for (Object f : fields) {
            if (f instanceof Field) {
                fieldNames.add(((Field) f).getName());
            } else if (f instanceof String) {
                fieldNames.add(f.toString());
            }
        }
        ((Index) element).setFieldNames(fieldNames);
        getViewer().getControl().getDisplay().asyncExec(new Runnable() {

            @Override
            public void run() {
                getViewer().update(element, null);
            }
        });

    }

}
