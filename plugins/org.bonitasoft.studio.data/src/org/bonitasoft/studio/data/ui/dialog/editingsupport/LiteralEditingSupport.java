/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.data.ui.dialog.editingsupport;

import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.model.process.EnumType;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
/**
 * @author Romain Bioteau
 *
 */
public class LiteralEditingSupport extends EditingSupport {


    private EnumType type;

    public LiteralEditingSupport(ColumnViewer viewer) {
        super(viewer);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.EditingSupport#canEdit(java.lang.Object)
     */
    @Override
    protected boolean canEdit(Object element) {
        return true;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.EditingSupport#getCellEditor(java.lang.Object)
     */
    @Override
    protected CellEditor getCellEditor(final Object element) {
        TextCellEditor editor = new TextCellEditor((Composite) getViewer().getControl(), SWT.NONE) ;
        editor.setValidator(new ICellEditorValidator() {

            @Override
            public String isValid(Object input) {
                if(input == null || input.toString().isEmpty()){
                    return Messages.dataNameIsEmpty ;
                }

                return null;
            }
        }) ;
        return  editor;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.EditingSupport#getValue(java.lang.Object)
     */
    @Override
    protected Object getValue(Object element) {
        return element;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.EditingSupport#setValue(java.lang.Object, java.lang.Object)
     */
    @Override
    protected void setValue(Object element, Object value) {
        if(value != null){
            int i = type.getLiterals().indexOf(element) ;
            type.getLiterals().remove(element) ;
            if(i != -1){
                type.getLiterals().add(i,value.toString()) ;
            }
            getViewer().refresh() ;
        }
    }

    public void setEnumType(EnumType type){
        this.type = type ;
    }

}
