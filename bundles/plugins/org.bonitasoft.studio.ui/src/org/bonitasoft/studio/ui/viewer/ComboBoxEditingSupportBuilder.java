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
package org.bonitasoft.studio.ui.viewer;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class ComboBoxEditingSupportBuilder<T, U> {

    private ColumnViewer viewer;
    private ComboBoxViewerCellEditor editor;
    private Optional<IStructuredContentProvider> contentProvider = Optional.empty();
    private Optional<IBaseLabelProvider> labelProvider = Optional.empty();
    private Optional<Function<T, Boolean>> canEditProvider = Optional.empty();
    private Optional<Function<T, U>> valueProvider = Optional.empty();
    private Optional<BiConsumer<T, U>> valueUpdater = Optional.empty();
    private Object input;

    public ComboBoxEditingSupportBuilder(ColumnViewer viewer) {
        this.viewer = viewer;
        this.editor = new ComboBoxViewerCellEditor((Composite) viewer.getControl(), SWT.BORDER | SWT.READ_ONLY);
        editor.setActivationStyle(ComboBoxViewerCellEditor.DROP_DOWN_ON_MOUSE_ACTIVATION);
    }

    public ComboBoxEditingSupportBuilder<T, U> withContentProvider(IStructuredContentProvider contentProvider) {
        this.contentProvider = Optional.ofNullable(contentProvider);
        return this;
    }

    public ComboBoxEditingSupportBuilder<T, U> withLabelProvider(IBaseLabelProvider labelProvider) {
        this.labelProvider = Optional.ofNullable(labelProvider);
        return this;
    }

    public ComboBoxEditingSupportBuilder<T, U> withCanEditProvider(Function<T, Boolean> canEditProvider) {
        this.canEditProvider = Optional.ofNullable(canEditProvider);
        return this;
    }

    public ComboBoxEditingSupportBuilder<T, U> withValueProvider(Function<T, U> valueProvider) {
        this.valueProvider = Optional.ofNullable(valueProvider);
        return this;
    }

    public ComboBoxEditingSupportBuilder<T, U> withValueUpdater(BiConsumer<T, U> valueUpdater) {
        this.valueUpdater = Optional.ofNullable(valueUpdater);
        return this;
    }

    public ComboBoxEditingSupportBuilder<T, U> withInput(Object input) {
        this.input = input;
        return this;
    }

    public EditingSupport create() {
        editor.setContentProvider(contentProvider.orElse(ArrayContentProvider.getInstance()));
        editor.setLabelProvider(labelProvider.orElse(new LabelProvider()));
        editor.setInput(input);
        return new EditingSupport(viewer) {

            @Override
            protected void setValue(Object element, Object value) {
                valueUpdater.ifPresent(updater -> updater.accept((T) element, (U) value));
            }

            @Override
            protected Object getValue(Object element) {
                return valueProvider.map(provider -> provider.apply((T) element)).orElse(null);
            }

            @Override
            protected CellEditor getCellEditor(Object element) {
                editor.getControl().addListener(SWT.Selection, e -> getViewer().getControl().forceFocus());
                return editor;
            }

            @Override
            protected boolean canEdit(Object element) {
                return canEditProvider.map(provider -> provider.apply((T) element)).orElse(true);
            }
        };
    }

}
