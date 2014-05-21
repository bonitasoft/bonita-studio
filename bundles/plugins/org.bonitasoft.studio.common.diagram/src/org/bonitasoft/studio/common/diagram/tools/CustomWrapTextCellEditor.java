package org.bonitasoft.studio.common.diagram.tools;
import org.eclipse.gmf.runtime.gef.ui.internal.parts.WrapTextCellEditor;
import org.eclipse.swt.widgets.Composite;

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

/**
 * @author Aurelien Pupier
 *
 * Custom the WrapTextCellEditor in order to avoid to add line separator at the validation by enter key pressed.
 */
public class CustomWrapTextCellEditor extends WrapTextCellEditor {

	
	/**
	 * @param composite
	 * @param style
	 */
	public CustomWrapTextCellEditor(Composite composite, int style) {
		super(composite,style);
	}

	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.TextCellEditor#doGetValue()
	 */
	@Override
	protected Object doGetValue() {
		Object value = super.doGetValue();
		/*Force to remove line separator otherwise they will add to the value*/
		if(value instanceof String){
			value = ((String)value).replaceAll(System.getProperty("line.separator"), "");
		}
		return value;
	}
	
}
