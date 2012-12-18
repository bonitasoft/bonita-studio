/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.jface;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.swt.custom.CLabel;


/**
 * @author Romain Bioteau
 *
 */
public class CellEditorValidationStatusListener implements ICellEditorListener {

    private final CLabel statusControl;
    private CellEditor editor;

    public CellEditorValidationStatusListener(CLabel statusControl){
        this.statusControl = statusControl;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ICellEditorListener#applyEditorValue()
     */
    @Override
    public void applyEditorValue() {
        statusControl.setText("");
        statusControl.setImage(null);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ICellEditorListener#cancelEditor()
     */
    @Override
    public void cancelEditor() {
        statusControl.setText("");
        statusControl.setImage(null);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ICellEditorListener#editorValueChanged(boolean, boolean)
     */
    @Override
    public void editorValueChanged(boolean oldValidState, boolean newValidState) {
        if(!newValidState){
            statusControl.setText(editor.getErrorMessage());
            statusControl.setImage(JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_ERROR));
        }else{
            statusControl.setText("");
            statusControl.setImage(null);
        }
    }

    public void setCellEditor(CellEditor editor){
        this.editor = editor;
    }
}
