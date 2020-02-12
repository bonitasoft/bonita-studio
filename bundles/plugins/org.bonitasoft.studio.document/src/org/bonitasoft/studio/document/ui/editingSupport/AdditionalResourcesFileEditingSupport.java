/**
 * Copyright (C) 2020 BonitaSoft S.A.
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
package org.bonitasoft.studio.document.ui.editingSupport;

import org.bonitasoft.studio.document.SelectDocumentInBonitaStudioRepository;
import org.bonitasoft.studio.document.core.repository.DocumentFileStore;
import org.bonitasoft.studio.model.configuration.Resource;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class AdditionalResourcesFileEditingSupport extends EditingSupport {

    public AdditionalResourcesFileEditingSupport(ColumnViewer viewer) {
        super(viewer);
    }

    @Override
    protected CellEditor getCellEditor(Object element) {
        return new CustomDialogCellEditor((Composite) getViewer().getControl());
    }

    @Override
    protected boolean canEdit(Object element) {
        return true;
    }

    @Override
    protected Object getValue(Object element) {
        return ((Resource) element).getProjectPath();
    }

    @Override
    protected void setValue(Object element, Object value) {
        Resource resource = (Resource) element;
        if (value instanceof DocumentFileStore) {
            DocumentFileStore document = (DocumentFileStore) value;
            resource.setProjectPath(document.getName());
            if (resource.getBarPath() == null || resource.getBarPath().isEmpty()) {
                resource.setBarPath(document.getName());
            }
        }
    }

}

class CustomDialogCellEditor extends DialogCellEditor {

    public CustomDialogCellEditor(Composite parent) {
        super(parent);
    }

    @Override
    protected Object openDialogBox(Control cellEditorWindow) {
        SelectDocumentInBonitaStudioRepository dialog = new SelectDocumentInBonitaStudioRepository(
                cellEditorWindow.getShell());
        dialog.open();
        return dialog.getSelectedDocument();
    }
}
