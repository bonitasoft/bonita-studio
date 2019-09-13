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
package org.bonitasoft.studio.validation.selector;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.model.IClientSelector;
import org.eclipse.gmf.runtime.notation.Shape;

/**
 * 
 * @author Baptiste Mesta
 *
 */
public class BonitaClientSelector implements IClientSelector {


	public boolean selects(Object object) {
		//we check objects from bonitastudio ( they are all instance of element )
		if(object instanceof EObject){
			MainProcess mainProc = ModelHelper.getMainProcess((EObject) object);
			if(mainProc != null && !mainProc.isEnableValidation()){
				return false;
			}
		}

		boolean select = (object instanceof EObject && ((EObject)object).eClass().getEPackage().getNsURI().contains("bonitasoft")) || object instanceof Shape ;
		return select;
	}


}
