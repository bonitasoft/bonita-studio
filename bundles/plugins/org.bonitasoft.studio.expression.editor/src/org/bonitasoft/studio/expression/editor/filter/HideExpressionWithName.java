/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.expression.editor.filter;

import java.util.Arrays;
import java.util.HashSet;

import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * @author Romain Bioteau
 *
 */
public class HideExpressionWithName extends ViewerFilter {

	private HashSet<String> namesToExclude;

	public HideExpressionWithName(String[] namesToExclude) {
		this.namesToExclude = new HashSet<String>(Arrays.asList(namesToExclude))  ;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if(namesToExclude.isEmpty()){
			return true;
		}
		if(element instanceof Expression){
			return !namesToExclude.contains(((Expression)element).getName());
		}else if( element instanceof String){
			return !namesToExclude.contains(element);
		}
		return true ;
	}

}
