/*
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
package org.bonitasoft.studio.model.process.diagram.navigator;

import org.bonitasoft.studio.model.process.diagram.part.ProcessVisualIDRegistry;
import org.eclipse.jface.viewers.ViewerSorter;

/**
 * @generated
 */
public class ProcessNavigatorSorter extends ViewerSorter {

	/**
	* @generated
	*/
	private static final int GROUP_CATEGORY = 7008;

	/**
	* @generated
	*/
	public int category(Object element) {
		if (element instanceof ProcessNavigatorItem) {
			ProcessNavigatorItem item = (ProcessNavigatorItem) element;
			return ProcessVisualIDRegistry.getVisualID(item.getView());
		}
		return GROUP_CATEGORY;
	}

}
