/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.parameters.property.section.provider;

import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.parameters.i18n.Messages;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 *
 */
public class ParameterTypeLabelProvider extends ColumnLabelProvider {

	@Override
	public String getText(final Object element) {
		if(element instanceof Parameter){
			final String t = ((Parameter) element).getTypeClassname() ;
			if(t != null){
				if(t.equals(String.class.getName())){
					return Messages.textType ;
				}else if(t.equals(Integer.class.getName())){
					return Messages.integerType ;
				}else if(t.equals(Double.class.getName())){
					return Messages.doubleType ;
				}else if(t.equals(Boolean.class.getName())){
					return Messages.booleanType ;
				}
			}
		}else if(element instanceof String){
			final String t = (String) element ;
			if(t != null){
				if(t.equals(String.class.getName())){
					return Messages.textType ;
				}else if(t.equals(Integer.class.getName())){
					return Messages.integerType ;
				}else if(t.equals(Double.class.getName())){
					return Messages.doubleType ;
				}else if(t.equals(Boolean.class.getName())){
					return Messages.booleanType ;
				}
			}
		}
		return super.getText(element);
	}

	@Override
	public Image getImage(final Object element) {
		return null ;
	}


}
