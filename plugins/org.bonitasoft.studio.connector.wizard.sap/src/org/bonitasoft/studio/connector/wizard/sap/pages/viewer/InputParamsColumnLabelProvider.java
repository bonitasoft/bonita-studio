/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
 
package org.bonitasoft.studio.connector.wizard.sap.pages.viewer;

import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ViewerColumn;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 *
 */
public class InputParamsColumnLabelProvider extends ColumnLabelProvider {

	private int columnIndex;
	private Color bgColor;
	
	public InputParamsColumnLabelProvider(int columnIndex){
		this.columnIndex = columnIndex ;
	}
	
	@Override
	protected void initialize(ColumnViewer viewer, ViewerColumn column) {
		if (! Platform.getWS().equals(Platform.WS_GTK)) {
			bgColor = new Color(Display.getCurrent(), 230, 230, 230);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.CellLabelProvider#update(org.eclipse.jface.viewers.ViewerCell)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String getText(Object input) {
		if(((List<Object>)input).size()-1 >= columnIndex ){
			Object object = ((List<Object>)input).get(columnIndex);
			return object != null ? object.toString() : "";
		}else{
			return ""; //$NON-NLS-1$
		}
	}

	@Override
	public Color getBackground(Object element) {
		return bgColor;
	}
	
	public void dispose(ColumnViewer viewer, ViewerColumn column) {
		if (bgColor != null) {
			bgColor.dispose();
		}
		dispose();
	}
	
	
}
