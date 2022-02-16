/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.ui.viewer;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class EditingSupportBuilder<T> {

    private final ColumnViewer viewer;
    private Predicate<T> canEditFunction = e -> true;
    private Function<T, CellEditor> cellEditorFunction;
    private Function<T, Object> valueProvider = Object::toString;
    private BiConsumer<T, Object> valueUpdater = (e, v) -> Function.identity();
    private String widgetId;
    private boolean reuseCellEditor = true;

    public EditingSupportBuilder(ColumnViewer viewer) {
        this.viewer = viewer;
        this.cellEditorFunction = e -> new TextCellEditor((Composite) viewer.getControl());
    }

    public EditingSupportBuilder<T> withCellEditorProvider(Function<T, CellEditor> cellEditorFunction) {
        this.cellEditorFunction = cellEditorFunction;
        return this;
    }

    public EditingSupportBuilder<T> withValueProvider(Function<T, Object> valueProvider) {
        this.valueProvider = valueProvider;
        return this;
    }

    public EditingSupportBuilder<T> withValueUpdater(BiConsumer<T, Object> valueUpdater) {
        this.valueUpdater = valueUpdater;
        return this;
    }

    public EditingSupportBuilder<T> withCanEditProvider(Predicate<T> canEditFunction) {
        this.canEditFunction = canEditFunction;
        return this;
    }
    
    public EditingSupportBuilder<T> doNotReuseCellEditor() {
        this.reuseCellEditor = false;
        return this;
    }

    public EditingSupportBuilder<T> withId(String id) {
        this.widgetId = id;
        return this;
    }

    public EditingSupport create() {
        return new EditingSupport(viewer) {

            private CellEditor editor;

            @Override
            protected void setValue(Object element, Object value) {
                valueUpdater.accept((T) element, value);
                viewer.getControl().getDisplay().asyncExec(() -> viewer.update(element, null));
            }

            @Override
            protected Object getValue(Object element) {
                return valueProvider.apply((T) element);
            }

            @Override
            protected CellEditor getCellEditor(Object element) {
                if(editor == null || !reuseCellEditor) {
                    editor = cellEditorFunction.apply((T) element);
                    Control control = editor.getControl();
                    if (control != null && control.getData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY) == null && widgetId != null) {
                        control.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, widgetId);
                    }
                }
                return editor;
            }

            @Override
            protected boolean canEdit(Object element) {
                try {
                    final T e = (T) element;
                    return canEditFunction.test(e);
                } catch (final ClassCastException e) {
                    return false;
                }

            }
        };
    }

}
