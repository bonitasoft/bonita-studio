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
package org.bonitasoft.studio.expression.editor.filter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * @author Romain Bioteau
 *
 */
public class HiddenExpressionTypeFilter extends ViewerFilter {

	private Set<String> contentTypes;

	public HiddenExpressionTypeFilter(String[] contentTypes){
		this.contentTypes = new HashSet<String>(Arrays.asList(contentTypes))  ;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean select(Viewer viewer, Object context, Object element) {
		if(element instanceof Expression){
			return !contentTypes.contains(((Expression)element).getType());
		} else if(element instanceof IExpressionProvider){
			return !contentTypes.contains(((IExpressionProvider) element).getExpressionType());
		}
		return true ;
	}

}
