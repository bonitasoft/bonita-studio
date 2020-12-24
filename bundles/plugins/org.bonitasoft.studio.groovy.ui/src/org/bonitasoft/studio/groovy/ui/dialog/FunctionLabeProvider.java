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

import org.bonitasoft.studio.groovy.library.IFunction;
import org.eclipse.jface.viewers.LabelProvider;

/**
 * @author Romain Bioteau
 *
 */
public class FunctionLabeProvider extends LabelProvider {

	
	@Override
	public String getText(Object element) {
		if(element instanceof IFunction){

			if(((IFunction)element).getOwner().equals("DefaultGroovyMethods")){ //$NON-NLS-1$
				return ((IFunction) element).getSignature();
			}else{
				return ((IFunction) element).getSignature()+" - "+ ((IFunction) element).getOwner(); //$NON-NLS-1$
			}
		}
		return super.getText(element);
	}


}

