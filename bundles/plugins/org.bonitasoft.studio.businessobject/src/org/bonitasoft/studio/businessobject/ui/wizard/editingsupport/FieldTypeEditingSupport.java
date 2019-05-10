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

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.RelationField.FetchType;
import org.bonitasoft.engine.bdm.model.field.RelationField.Type;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.businessobject.core.difflog.IDiffLogger;
import org.bonitasoft.studio.businessobject.ui.wizard.provider.FieldTypeLabelProvider;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Romain Bioteau
 */
public class FieldTypeEditingSupport extends EditingSupport {

    public static final String TYPE_COMBO_EDITOR_ID = "FieldTypeEditingSupport.Combo";

    private final BusinessObjectModel bom;

    private final IObservableList fieldsList;

    private final IObservableValue selectedFieldObservableValue;

    private IDiffLogger diffLogger;

    private IObservableValue boObservableValue;

    private BusinessObjectNameComparator boComparator;

    public FieldTypeEditingSupport(final ColumnViewer viewer, final BusinessObjectModel bom,
            final IObservableList fieldsList,
            final IObservableValue selectedFieldObservableValue,
            IObservableValue boObservableValue,
            IDiffLogger diffLogger) {
        super(viewer);
        this.bom = bom;
        this.fieldsList = fieldsList;
        this.selectedFieldObservableValue = selectedFieldObservableValue;
        this.boObservableValue = boObservableValue;
        this.diffLogger = diffLogger;
        this.boComparator = new BusinessObjectNameComparator();
    }

    @Override
    protected CellEditor getCellEditor(final Object element) {
        final ComboBoxViewerCellEditor cellEditor = new ComboBoxViewerCellEditor((Composite) getViewer().getControl(),
                SWT.READ_ONLY);
        cellEditor.getControl().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, TYPE_COMBO_EDITOR_ID);
        cellEditor.setContentProvider(ArrayContentProvider.getInstance());
        cellEditor.setActivationStyle(ComboBoxViewerCellEditor.DROP_DOWN_ON_MOUSE_ACTIVATION);
        cellEditor.getControl().addListener(SWT.Selection, e -> getViewer().getControl().getParent().setFocus());
        final FieldTypeLabelProvider labelProvider = new FieldTypeLabelProvider();
        cellEditor.setLabelProvider(labelProvider);
        cellEditor.setInput(getInput(labelProvider));
        return cellEditor;
    }

    protected List<Object> getInput(final FieldTypeLabelProvider labelProvider) {
        final List<Object> result = new ArrayList<>();
        result.addAll(newArrayList(FieldType.BOOLEAN,
                FieldType.LOCALDATE,
                FieldType.LOCALDATETIME,
                FieldType.OFFSETDATETIME,
                FieldType.DOUBLE,
                FieldType.FLOAT,
                FieldType.INTEGER,
                FieldType.LONG,
                FieldType.STRING,
                FieldType.TEXT,
                FieldType.DATE));
        List<BusinessObject> businessObjects = bom.getBusinessObjects();
        businessObjects.sort(boComparator);
        result.addAll(businessObjects);
        return result;
    }

    @Override
    protected boolean canEdit(final Object element) {
        return true;
    }

    @Override
    protected Object getValue(final Object element) {
        if (element instanceof SimpleField) {
            return ((SimpleField) element).getType();
        } else if (element instanceof RelationField) {
            return ((RelationField) element).getReference();
        }
        return null;
    }

    @Override
    protected void setValue(final Object element, final Object value) {
        if (element instanceof SimpleField) {
            if (value instanceof FieldType) {
                FieldType oldType = ((SimpleField) element).getType();
                ((SimpleField) element).setType((FieldType) value);
                selectedFieldObservableValue.setValue(null);
                selectedFieldObservableValue.setValue(element);
                diffLogger.fieldTypeChanged(((BusinessObject) boObservableValue.getValue()).getQualifiedName(),
                        ((Field) element).getName(), oldType, value);
            } else if (value instanceof BusinessObject) {
                updateToRelationField((SimpleField) element, (BusinessObject) value,
                        (BusinessObject) boObservableValue.getValue());
            }
        }
        if (element instanceof RelationField) {
            if (value instanceof BusinessObject) {
                BusinessObject oldType = ((RelationField) element).getReference();
                ((RelationField) element).setReference((BusinessObject) value);
                diffLogger.fieldTypeChanged(((BusinessObject) boObservableValue.getValue()).getQualifiedName(),
                        ((Field) element).getName(), oldType, value);
            } else if (value instanceof FieldType) {
                updateToSimpleField((RelationField) element, (FieldType) value,
                        (BusinessObject) boObservableValue.getValue());
            }
        }
        getViewer().getControl().getDisplay().asyncExec(new Runnable() {

            @Override
            public void run() {
                getViewer().update(element, null);
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void updateToSimpleField(final RelationField field, final FieldType type, BusinessObject parent) {
        final int index = fieldsList.indexOf(field);
        if (index != -1 && fieldsList.remove(field)) {
            BusinessObject oldType = field.getReference();
            final SimpleField simpleField = new SimpleField();
            simpleField.setType(type);
            simpleField.setCollection(field.isCollection());
            simpleField.setName(field.getName());
            simpleField.setNullable(field.isNullable());
            simpleField.setLength(255);
            fieldsList.add(index, simpleField);
            selectedFieldObservableValue.setValue(simpleField);
            diffLogger.fieldTypeChanged(parent.getQualifiedName(), simpleField.getName(), oldType, type);
        }
    }

    @SuppressWarnings("unchecked")
    private void updateToRelationField(final SimpleField field, final BusinessObject reference, BusinessObject parent) {
        final int index = fieldsList.indexOf(field);
        if (index != -1 && fieldsList.remove(field)) {
            FieldType oldType = field.getType();
            final RelationField realtionField = new RelationField();
            realtionField.setType(Type.AGGREGATION);
            realtionField.setFetchType(FetchType.LAZY);
            realtionField.setReference(reference);
            realtionField.setCollection(field.isCollection());
            realtionField.setName(field.getName());
            realtionField.setNullable(field.isNullable());
            fieldsList.add(index, realtionField);
            selectedFieldObservableValue.setValue(realtionField);
            diffLogger.fieldTypeChanged(parent.getQualifiedName(),
                    realtionField.getName(), oldType,
                    reference);
        }

    }

    private class BusinessObjectNameComparator implements Comparator<BusinessObject> {

        @Override
        public int compare(BusinessObject o1, BusinessObject o2) {
            return o1.getSimpleName().compareTo(o2.getSimpleName());
        }

    }
}
