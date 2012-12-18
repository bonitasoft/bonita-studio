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

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.groovy.ui.widgets.TextOrDataForCellEditor;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Romain Bioteau
 *
 */
public class TextOrDataForInputParamsCellEditor extends TextOrDataForCellEditor{

	private String[] initialValue;

	public TextOrDataForInputParamsCellEditor(Composite parent,FocusListener focusListener,String[] initialValue) {
		super(parent, null, focusListener);
		this.initialValue = initialValue ;
		addDatas();
	}

	@Override
	protected Set<String> addDatas() {
		Set<String> result = new HashSet<String>(); 
		if(initialValue != null){
			for(String item : initialValue){
				combo.add(item);
				result.add(item) ;
 			}
		}
		return result ;
	}
}
