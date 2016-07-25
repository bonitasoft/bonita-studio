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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.bonitasoft.studio.groovy.library.IFunction;
import org.bonitasoft.studio.groovy.library.IFunctionCategory;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author Romain Bioteau
 *
 */
public class FunctionContentProvider implements ITreeContentProvider{

	

	public void dispose() {
		
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
	}


	public Object[] getChildren(Object parentElement) {
		return null;
	}


	public Object getParent(Object element) {
		return null;
	}


	public boolean hasChildren(Object element) {
		return false;
	}

	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof IFunctionCategory){
			List<IFunction> functions = ((IFunctionCategory) inputElement).getFunctions();
			Collections.sort(functions, new Comparator<IFunction>(){

				public int compare(IFunction o1, IFunction o2) {
					return o1.getName().compareTo(o2.getName());
				}
				
			});
			return functions.toArray();
		}
		return null ;
	}

}
