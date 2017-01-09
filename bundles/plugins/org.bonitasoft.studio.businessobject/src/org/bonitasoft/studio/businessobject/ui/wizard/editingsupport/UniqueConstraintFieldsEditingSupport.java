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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.UniqueConstraint;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.dialog.ElementCheckboxSelectionDialog;
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
public class UniqueConstraintFieldsEditingSupport extends EditingSupport {

    private IObservableValue viewerObservableValue;

    /**
     * @param viewer
     * @param dbc
     */
    public UniqueConstraintFieldsEditingSupport(IObservableValue viewerObservableValue, ColumnViewer viewer) {
        super(viewer);
        this.viewerObservableValue = viewerObservableValue;
    }

    @Override
    protected CellEditor getCellEditor(final Object element) {
        return new DialogCellEditor((Composite) getViewer().getControl()) {

            @Override
            protected Object openDialogBox(Control cellEditorWindow) {
                ElementCheckboxSelectionDialog dialog = new ElementCheckboxSelectionDialog(cellEditorWindow.getShell(),
                        new FieldLabelProvider());
                BusinessObject businessObject = (BusinessObject) viewerObservableValue.getValue();
                List<Field> fields = businessObject.getFields();
                if (fields == null) {
                    fields = new ArrayList<Field>();
                }
                List<Field> boFields = new ArrayList<Field>();
                for (Field f : fields) {
                    if (f.isCollection() == null || !f.isCollection()) {
                        if (f instanceof SimpleField) {
                            boFields.add(f);
                        } else if (f instanceof RelationField && ((RelationField) f).getReference() != null) {
                            boFields.add(f);
                        }
                    }
                }
                dialog.setElements(boFields.toArray());
                @SuppressWarnings("unchecked")
                List<String> values = (List<String>) UniqueConstraintFieldsEditingSupport.this.getValue(element);
                if (values != null) {
                    Set<Object> selectedFields = new HashSet<Object>();
                    for (Field f : businessObject.getFields()) {
                        if (values.contains(f.getName())) {
                            selectedFields.add(f);
                        }
                    }
                    dialog.setSelectedElements(selectedFields);
                }

                dialog.setTitle(Messages.selectUniqueConstraintFieldsTitle);
                dialog.setMessage(Messages.selectUniqueConstraintFieldsMessage);
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
    protected Object getValue(Object element) {
        return ((UniqueConstraint) element).getFieldNames();
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
        ((UniqueConstraint) element).setFieldNames(fieldNames);
        getViewer().getControl().getDisplay().asyncExec(new Runnable() {

            @Override
            public void run() {
                getViewer().update(element, null);
            }
        });

    }

}
