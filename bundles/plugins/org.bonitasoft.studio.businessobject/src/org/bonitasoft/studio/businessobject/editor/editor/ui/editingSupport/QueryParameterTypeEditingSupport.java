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

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

import org.bonitasoft.engine.bdm.model.QueryParameterTypes;
import org.bonitasoft.studio.businessobject.editor.model.QueryParameter;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class QueryParameterTypeEditingSupport extends EditingSupport {

    public static final String QUERY_PARAM_TYPE_COMBO_EDITOR_ID = "queryParamTypeComboEditorId";
    private Supplier<Boolean> isDefaultQuerySupplier;

    public QueryParameterTypeEditingSupport(ColumnViewer viewer, Supplier<Boolean> isDefaultQuerySupplier) {
        super(viewer);
        this.isDefaultQuerySupplier = isDefaultQuerySupplier;
    }

    @Override
    protected CellEditor getCellEditor(Object element) {
        ComboBoxViewerCellEditor cellEditor = new ComboBoxViewerCellEditor((Composite) getViewer().getControl(),
                SWT.READ_ONLY);
        cellEditor.getControl().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, QUERY_PARAM_TYPE_COMBO_EDITOR_ID);
        cellEditor.setContentProvider(ArrayContentProvider.getInstance());
        cellEditor.setActivationStyle(ComboBoxViewerCellEditor.DROP_DOWN_ON_MOUSE_ACTIVATION);
        cellEditor.getControl().addListener(SWT.Selection, e -> getViewer().getControl().getParent().setFocus());
        cellEditor.setLabelProvider(new LabelProviderBuilder<QueryParameterTypes>()
                .withTextProvider(QueryParameterTypes::getName)
                .createLabelProvider());
        cellEditor.setInput(QueryParameterTypes.values());
        return cellEditor;
    }

    @Override
    protected boolean canEdit(Object element) {
        return !isDefaultQuerySupplier.get();
    }

    @Override
    protected Object getValue(Object element) {
        QueryParameter param = (QueryParameter) element;
        return Arrays.asList(QueryParameterTypes.values())
                .stream()
                .filter(type -> Objects.equals(type.getName(), param.getClassName()))
                .findFirst()
                .orElse(null);
    }

    @Override
    protected void setValue(Object element, Object value) {
        QueryParameter param = (QueryParameter) element;
        param.setClassName(((QueryParameterTypes) value).getName());
    }

}
