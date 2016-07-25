/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
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
package org.bonitasoft.studio.common.diagram.tools;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.label.ILabelDelegate;
import org.eclipse.gmf.runtime.diagram.ui.tools.TextDirectEditManager;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Aurelien Pupier
 * 
 */
public class CustomTextDirectEditManager extends TextDirectEditManager {

    /**
     * @param source
     * @param editorType
     * @param locator
     */
    public CustomTextDirectEditManager(GraphicalEditPart source,
            Class editorType, CellEditorLocator locator) {
        super(source, editorType, locator);
    }

    /*
     * (non-Javadoc)
     * 
     * @seeorg.eclipse.gmf.runtime.diagram.ui.tools.TextDirectEditManager#
     * createCellEditorOn(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected CellEditor createCellEditorOn(Composite composite) {
        if (!Platform.getOS().equals(Platform.OS_MACOSX)) {
            ILabelDelegate label = (ILabelDelegate) getEditPart().getAdapter(
                    ILabelDelegate.class);
            int style = SWT.WRAP | SWT.MULTI;
            if (label != null /* && label.isTextWrapOn() */) {

                switch (label.getTextJustification()) {
                    case PositionConstants.LEFT:
                        style = style | SWT.LEAD;
                        break;
                    case PositionConstants.RIGHT:
                        style = style | SWT.TRAIL;
                        break;
                    case PositionConstants.CENTER:
                        style = style | SWT.CENTER;
                        break;
                    default:
                        break;
                }
            }
            return new CustomWrapTextCellEditor(composite, style);
        } else {
            //USE normal textcelleditor if running on mac
            return super.createCellEditorOn(composite);
        }
    }

    public static Class getTextCellEditorClass(GraphicalEditPart source) {
        if (!Platform.getOS().equals(Platform.OS_MACOSX)){
            return CustomWrapTextCellEditor.class;
        }else{
            return TextDirectEditManager.getTextCellEditorClass(source);
        }
    }


    // TODO : Avoid catching of NPE and find a better way to avoid
    // NPE when switching activity type while edit labels
    // and when deleting line/column in form editor
    @Override
    protected void commit() {
        try {
            super.commit();
        } catch (Exception ex) {
            BonitaStudioLog.error(ex);
        }
    }
    

    public CellEditor getCellEditor() {
    	return super.getCellEditor();
    }
}
