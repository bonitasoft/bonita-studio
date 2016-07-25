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

package org.bonitasoft.studio.groovy.ui.dialog;

import org.bonitasoft.studio.groovy.library.IFunctionsCategories;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author Romain Bioteau
 *
 */
public class FunctionCategoriesProvider implements IStructuredContentProvider {


	/**
	 * FunctionCategoriesProvider constructor comment.
	 */
	public FunctionCategoriesProvider() {
		super();
	}

	/**
	 * Do nothing when disposing,
	 */
	public void dispose() {
	}

	/**
	 * Returns the elements to display in the viewer. The inputElement is ignored for this
	 * provider.
	 */
	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof IFunctionsCategories)
			return ((IFunctionsCategories) inputElement).getCategories().toArray();
		
		return null;
	}

	/**
	 * Required method from IStructuredContentProvider. The input is assumed to not change 
	 * for the SimpleListContentViewer so do nothing here.
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}


}
