/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.diagram.custom.clipboard;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;

/**
 * @author Mickael Istria
 *
 */
public class Clipboard {
	//
	// CLIPBOARD
	//
	private static List<IGraphicalEditPart> parts = new ArrayList<IGraphicalEditPart>();
	
	
	/**
	 * @return
	 */
	public static boolean hasThingsToCopy() {
		return parts != null && !parts.isEmpty();
	}


	/**
	 * @param toCopyElements
	 */
	public static void setToCopyEditParts(List<IGraphicalEditPart> toCopyElements) {
		List<IGraphicalEditPart> res = GMFTools.addMissingConnectionsAndBoundaries(toCopyElements);
		parts = res;
	}


	/**
	 * @return
	 */
	public static List<IGraphicalEditPart> getToCopy() {
		return parts;
	}
}
