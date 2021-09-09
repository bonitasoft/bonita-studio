/**
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.sqlbuilder.ex.builder.viewer;

import org.bonitasoft.studio.sqlbuilder.ex.builder.viewer.element.BonitaInsertTableElement;
import org.bonitasoft.studio.sqlbuilder.ex.builder.viewer.element.BonitaSelectTableElement;
import org.bonitasoft.studio.sqlbuilder.ex.builder.viewer.element.BonitaUpdateTreeElement;
import org.bonitasoft.studio.sqlbuilder.ex.builder.viewer.element.BonitaValueTableElement;
import org.eclipse.datatools.sqltools.sqlbuilder.views.fullselect.FullSelectTableElement;
import org.eclipse.datatools.sqltools.sqlbuilder.views.fullselect.OrderByTableElement;
import org.eclipse.datatools.sqltools.sqlbuilder.views.insert.InsertTableElement;
import org.eclipse.datatools.sqltools.sqlbuilder.views.select.SelectTableElement;
import org.eclipse.datatools.sqltools.sqlbuilder.views.update.UpdateTreeElement;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;

/**
 * @author Romain Bioteau
 *
 */
public class BonitaModifier implements ICellModifier {
  
	private Object oldValue;
	
	@Override
    public boolean canModify(Object element, String property) {
        return true;
    }

    @Override
    public Object getValue(Object target, String property) {
        return target;
    }

    protected Object currentData, currentProperty, currentValue;

    @Override
    public void modify(java.lang.Object element, java.lang.String property, java.lang.Object value) {

        Object data = null;
        if (element instanceof TableItem) {
            data = ((TableItem) element).getData();
        }
        else if (element instanceof TableViewer) {
            data = ((TableViewer) element).getTable().getData();
        }
        
        // appears that the table returns the table element as the value if there
        // is no change to the cell contents - don't need to modify anything in this case
        // In Eclipse 3.1, modify() seems to called twice - with actual update and then with 
        // TableElement as update value. Don't updated instance values if no update required,
        // since asynchronous task for actual update may not have completed yet and is using
        // same instance of Modifier. 
        if (data != value) {  
        	currentData = data;
        	currentProperty = property;
        	currentValue = value;
        	
            if (data instanceof SelectTableElement) {
                oldValue = ((SelectTableElement) data).getColumnText(0);
            } else if (data instanceof InsertTableElement) {
                oldValue = ((InsertTableElement) data).getColumnText(1);
            } else if (data instanceof UpdateTreeElement) {
                oldValue = ((UpdateTreeElement) data).getColumnText(1);
            } else if (data instanceof BonitaSelectTableElement) {
                oldValue = ((BonitaSelectTableElement) data).getColumnText(0);
            } else if (data instanceof BonitaInsertTableElement) {
                oldValue = ((BonitaInsertTableElement) data).getColumnText(1);
            } else if (data instanceof BonitaUpdateTreeElement) {
                oldValue = ((BonitaUpdateTreeElement) data).getColumnText(1);
            }
        	
            Display.getCurrent().asyncExec(new Runnable() {

				@Override
                public void run() {
                    if (currentData instanceof SelectTableElement) {
                        ((SelectTableElement) currentData).modify(currentProperty, currentValue);
                    }
                    if (currentData instanceof BonitaSelectTableElement) {
                        ((BonitaSelectTableElement) currentData).modify(currentProperty, currentValue, oldValue);
                    }
                    else if (currentData instanceof BonitaInsertTableElement) {
                        ((BonitaInsertTableElement) currentData).modify(currentProperty, currentValue, oldValue);
                    }
                    else if (currentData instanceof BonitaUpdateTreeElement) {
                        ((BonitaUpdateTreeElement) currentData).modify(currentProperty, currentValue, oldValue);
                    }
                    else if (currentData instanceof FullSelectTableElement) {
                        ((FullSelectTableElement) currentData).modify(currentProperty, currentValue);
                    }
                    else if (currentData instanceof BonitaValueTableElement) {
                        ((BonitaValueTableElement) currentData).modify(currentProperty, currentValue);
                    }
                    else if (currentData instanceof OrderByTableElement) {
                        ((OrderByTableElement) currentData).modify(currentProperty, currentValue);
                    }
                }
            });
        }
    }

}
