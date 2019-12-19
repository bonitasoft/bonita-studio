/**
 * Copyright (C) 2019 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.businessobject.editor.editor.ui.editingSupport;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.model.BusinessDataModelFormPage;
import org.bonitasoft.studio.businessobject.editor.editor.ui.provider.TypeLabelProvider;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.FetchType;
import org.bonitasoft.studio.businessobject.editor.model.FieldType;
import org.bonitasoft.studio.businessobject.editor.model.Package;
import org.bonitasoft.studio.businessobject.editor.model.RelationField;
import org.bonitasoft.studio.businessobject.editor.model.RelationType;
import org.bonitasoft.studio.businessobject.editor.model.SimpleField;
import org.bonitasoft.studio.businessobject.editor.model.builder.RelationFieldBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.SimpleFieldBuilder;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class FieldTypeEditingSupport extends EditingSupport {

    public static final String TYPE_COMBO_EDITOR_ID = "FieldTypeEditingSupport.Combo";

    private StyledCellLabelProvider labelProvider;
    private BusinessDataModelFormPage formPage;

    public FieldTypeEditingSupport(ColumnViewer viewer, BusinessDataModelFormPage formPage) {
        super(viewer);
        this.formPage = formPage;
        labelProvider = new TypeLabelProvider(formPage.observeWorkingCopy());
    }

    @Override
    protected CellEditor getCellEditor(Object element) {
        ComboBoxViewerCellEditor cellEditor = new ComboBoxViewerCellEditor((Composite) getViewer().getControl(),
                SWT.READ_ONLY);
        cellEditor.getControl().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, TYPE_COMBO_EDITOR_ID);
        cellEditor.setContentProvider(ArrayContentProvider.getInstance());
        cellEditor.setActivationStyle(ComboBoxViewerCellEditor.DROP_DOWN_ON_MOUSE_ACTIVATION);
        cellEditor.getControl().addListener(SWT.Selection, e -> getViewer().getControl().getParent().setFocus());
        cellEditor.setLabelProvider(labelProvider);
        cellEditor.setInput(getInput());
        return cellEditor;
    }

    protected List<Object> getInput() {
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
        result.addAll(formPage.observeWorkingCopy().getValue().getPackages()
                .stream()
                .map(Package::getBusinessObjects)
                .flatMap(Collection::stream)
                .collect(Collectors.toList()));
        return result;
    }

    @Override
    protected boolean canEdit(Object element) {
        return true;
    }

    @Override
    protected Object getValue(Object element) {
        if (element instanceof SimpleField) {
            return ((SimpleField) element).getType();
        } else if (element instanceof RelationField) {
            return ((RelationField) element).getReference();
        }
        return null;
    }

    @Override
    protected void setValue(Object element, Object value) {
        if (element instanceof SimpleField) {
            if (value instanceof FieldType) {
                ((SimpleField) element).setType((FieldType) value);
            } else if (value instanceof BusinessObject) {
                updateToRelationField((SimpleField) element, (BusinessObject) value);
            }
        } else if (element instanceof RelationField) {
            if (value instanceof BusinessObject) {
                ((RelationField) element).setReference((BusinessObject) value);
            } else if (value instanceof FieldType) {
                updateToSimpleField((RelationField) element, (FieldType) value);
            }
        }
        formPage.updateFieldDetailsTopControl();
        formPage.getEditorContribution().refreshConstraintList();
        formPage.getEditorContribution().refreshIndexList();
    }

    // TODO test me
    private void updateToSimpleField(RelationField field, FieldType type) {
        BusinessObject parent = (BusinessObject) field.eContainer();
        int index = parent.getFields().indexOf(field);
        parent.getFields().remove(field);
        SimpleField simpleField = new SimpleFieldBuilder()
                .withName(field.getName())
                .withCollection(field.isCollection())
                .withNullable(field.isNullable())
                .withLength(255)
                .withType(type)
                .create();
        parent.getFields().add(index, simpleField);
    }

    // TODO test me
    private void updateToRelationField(SimpleField field, BusinessObject reference) {
        BusinessObject parent = (BusinessObject) field.eContainer();
        int index = parent.getFields().indexOf(field);
        parent.getFields().remove(field);
        RelationField relationField = new RelationFieldBuilder()
                .withName(field.getName())
                .withCollection(field.isCollection())
                .withNullable(field.isNullable())
                .withType(RelationType.AGGREGATION)
                .withFetchType(FetchType.LAZY)
                .withReference(reference)
                .create();
        parent.getFields().add(index, relationField);
    }

}
