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
package org.bonitasoft.studio.connector.model.definition.dialog.suport;

import org.bonitasoft.studio.connector.model.definition.Array;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;


/**
 * @author Romain Bioteau
 *
 */
public class CaptionEditingSupport extends EditingSupport {

    private final Array array;


    public CaptionEditingSupport(ColumnViewer viewer, Array array) {
        super(viewer);
        this.array = array ;
    }


    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.EditingSupport#getCellEditor(java.lang.Object)
     */
    @Override
    protected CellEditor getCellEditor(Object element) {
        TextCellEditor cellEditor = new TextCellEditor((Composite) getViewer().getControl());
        cellEditor.setValidator(new ICellEditorValidator() {

            @Override
            public String isValid(Object value) {
                for(String item : array.getColsCaption()){
                    if(item.equals(value)){
                        return "Item already exists" ;
                    }
                }
                return null;
            }
        });
        return cellEditor ;
    }


    @Override
    protected boolean canEdit(Object element) {
        return true;
    }


    @Override
    protected Object getValue(Object element) {
        if(element != null){
            return element.toString() ;
        }
        return "";
    }


    @Override
    protected void setValue(Object element, Object value) {
        if(value != null){
            int i = array.getColsCaption().indexOf(element) ;
            array.getColsCaption().remove(element) ;
            if(i != -1){
                array.getColsCaption().add(i,value.toString()) ;
            }
            getViewer().refresh() ;
        }
    }

}
