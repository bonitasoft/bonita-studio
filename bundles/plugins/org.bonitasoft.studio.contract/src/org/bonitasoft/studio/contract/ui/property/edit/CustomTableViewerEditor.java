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
package org.bonitasoft.studio.contract.ui.property.edit;

import org.eclipse.jface.viewers.CellEditor.LayoutData;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

/**
 * This is an editor-implementation for {@link Table}
 *
 * @since 3.3
 */
public class CustomTableViewerEditor extends ColumnViewerEditor {

    /**
     * This viewer's table editor.
     */
    private final TableEditor tableEditor;


    /**
     * @param viewer
     *        the viewer the editor is attached to
     * @param focusCellManager
     *        the cell focus manager if one used or <code>null</code>
     * @param editorActivationStrategy
     *        the strategy used to decide about the editor activation
     * @param feature
     *        the feature mask
     */
    public CustomTableViewerEditor(final TableViewer viewer,
            final ColumnViewerEditorActivationStrategy editorActivationStrategy,
            final int feature) {
        super(viewer, editorActivationStrategy, feature);
        tableEditor = new TableEditor(viewer.getTable());
    }

    /**
     * Create a customized editor with focusable cells
     *
     * @param viewer
     *        the viewer the editor is created for
     * @param focusCellManager
     *        the cell focus manager if one needed else <code>null</code>
     * @param editorActivationStrategy
     *        activation strategy to control if an editor activated
     * @param feature
     *        bit mask controlling the editor
     *        <ul>
     *        <li>{@link ColumnViewerEditor#DEFAULT}</li>
     *        <li>{@link ColumnViewerEditor#TABBING_CYCLE_IN_ROW}</li>
     *        <li>{@link ColumnViewerEditor#TABBING_HORIZONTAL}</li>
     *        <li>{@link ColumnViewerEditor#TABBING_MOVE_TO_ROW_NEIGHBOR}</li>
     *        <li>{@link ColumnViewerEditor#TABBING_VERTICAL}</li>
     *        </ul>
     * @see #create(TableViewer, ColumnViewerEditorActivationStrategy, int)
     */
    public static void create(final TableViewer viewer,
            final ColumnViewerEditorActivationStrategy editorActivationStrategy,
            final int feature) {
        final CustomTableViewerEditor editor = new CustomTableViewerEditor(viewer, editorActivationStrategy, feature);
        viewer.setColumnViewerEditor(editor);
    }


    @Override
    protected void setEditor(final Control w, final Item item, final int columnNumber) {
        tableEditor.setEditor(w, (TableItem) item, columnNumber);
    }

    @Override
    protected void setLayoutData(final LayoutData layoutData) {
        tableEditor.grabHorizontal = layoutData.grabHorizontal;
        tableEditor.horizontalAlignment = layoutData.horizontalAlignment;
        tableEditor.minimumWidth = layoutData.minimumWidth;
        tableEditor.verticalAlignment = layoutData.verticalAlignment;

        if (layoutData.minimumHeight != SWT.DEFAULT) {
            tableEditor.minimumHeight = layoutData.minimumHeight;
        }
    }

    @Override
    protected void updateFocusCell(final ViewerCell focusCell,
            final ColumnViewerEditorActivationEvent event) {
        // Update the focus cell when we activated the editor with these 2
        // events
        if (event.eventType == ColumnViewerEditorActivationEvent.PROGRAMMATIC
                || event.eventType == ColumnViewerEditorActivationEvent.TRAVERSAL) {

            //            final List l = getViewer().getSelectionFromWidget();
            //
            //            if (!l.contains(focusCell.getElement())) {
            //                getViewer().setSelection(
            //                        new StructuredSelection(focusCell.getElement()), true);
            //            }
        }
    }
}