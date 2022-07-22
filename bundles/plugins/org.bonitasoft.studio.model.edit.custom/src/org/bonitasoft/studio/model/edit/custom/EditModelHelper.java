/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.model.edit.custom;

import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * @author Romain Bioteau
 *
 */
public class EditModelHelper {

	public static AbstractProcess getParentProcess(EObject object) {
		EObject process = object;
		while (process != null &&  !(process instanceof AbstractProcess && !(process instanceof SubProcessEvent))) {
			if (process.eContainer() != null) {
				process = process.eContainer();
			} else {
				break;
			}
		}
		if (process instanceof AbstractProcess)
			return (AbstractProcess) process;
		else
			return null;
	}

	public static String getEObjectID(EObject eObject) {
		Resource eResource = eObject.eResource();
		if (eResource != null) {
			return eResource.getURIFragment(eObject);
		}
		return null;
	}

}
