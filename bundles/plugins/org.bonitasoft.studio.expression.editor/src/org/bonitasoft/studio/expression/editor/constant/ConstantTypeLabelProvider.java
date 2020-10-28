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
package org.bonitasoft.studio.expression.editor.constant;

import org.eclipse.jface.viewers.LabelProvider;

/**
 * @author Romain Bioteau
 *
 */
public class ConstantTypeLabelProvider extends LabelProvider {

    @Override
    public String getText(Object element) {
        //		if(element != null){
        //			if(element.toString().equals(String.class.getName())){
        //				return Messages.text ;
        //			}else if(element.toString().equals(Integer.class.getName())){
        //				return Messages.integer ;
        //			}else if(element.toString().equals(Long.class.getName())){
        //				return Messages.integer ;
        //			}else if(element.toString().equals(Float.class.getName())){
        //				return Messages.decimal ;
        //			}else if(element.toString().equals(Double.class.getName())){
        //				return Messages.decimal ;
        //			}
        //		}
        return super.getText(element);
    }

}
