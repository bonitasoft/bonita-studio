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

import org.bonitasoft.studio.sqlbuilder.ex.builder.viewer.element.BonitaCriteriaElement;
import org.eclipse.datatools.sqltools.sqlbuilder.views.criteria.CriteriaGridViewer;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;

/**
 * @author Romain Bioteau
 *
 */
public class CriteriaModifier implements ICellModifier {

	private Object oldValue;

	public boolean canModify(Object element, String property) {
		// check for instance of single predicate expression
		if (element instanceof BonitaCriteriaElement) {
			BonitaCriteriaElement criteriaElement = (BonitaCriteriaElement) element;
			String op = criteriaElement.getOperator();
			if (op != null && property.equals(CriteriaGridViewer.P_STATEMENT_VALUE)) {
				if (op.equals("IS NULL") || op.equals("IS NOT NULL")) {
					return false;
				}
			}
			else if (op != null && property.equals(CriteriaGridViewer.P_STATEMENT_COLUMN)) {
				if (op.equals("EXISTS")) {
					return false;
				}
			}
		}
		return true;
	}

	Object currentData, currentName, currentValue;

	public void modify(Object element, String name, Object value) {
		Object data = ((TableItem) element).getData();
		// appears that the table returns the table element as the value if there
		// is no change to the cell contents - don't need to modify anything in this case
		// In Eclipse 3.1, modify() seems to called twice - with actual update and then with 
		// TableElement as update value. Don't updated instance values if no update required,
		// since asynchronous task for actual update may not have completed yet and is using
		// same instance of Modifier. 
		if (data != value ){
			currentData = data;
			currentName = name;
			currentValue = value;
			if(data instanceof BonitaCriteriaElement){
				if(currentName.equals(CriteriaGridViewer.P_STATEMENT_COLUMN)){
					if(((BonitaCriteriaElement) data).getColumn() != null){
						if(((BonitaCriteriaElement) data).getColumn().getName() != null){
							oldValue = ((BonitaCriteriaElement) data).getColumn().getName() ;
						}else {
							oldValue = ((BonitaCriteriaElement) data).getColumnText(0) ;
						}
					}else{
						oldValue = null ;
					}
				}else{
					oldValue = ((BonitaCriteriaElement) data).getObjectValue() ;
				}
			}

			Display.getCurrent().asyncExec(new Runnable() {

				public void run() {
					((BonitaCriteriaElement) currentData).setElementProperty(currentName, currentValue,oldValue );
				}
			});
		}
		return;
	}

	public Object getValue(Object target, String property) {
		return target;
	}

}
