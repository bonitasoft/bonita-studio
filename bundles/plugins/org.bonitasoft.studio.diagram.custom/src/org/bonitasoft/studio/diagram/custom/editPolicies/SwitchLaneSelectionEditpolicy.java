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
package org.bonitasoft.studio.diagram.custom.editPolicies;

import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.diagram.custom.commands.SwitchLaneCommand;
import org.bonitasoft.studio.diagram.custom.parts.CustomLaneEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomPoolCompartmentEditPart;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;

/**
 * @author Aurelien Pupier
 */
public class SwitchLaneSelectionEditpolicy extends
		AbstractSwitchLaneSelectionEditPolicy {

	/**
	 * Convenient method.
	 * @return the Widget relative to the Host.
	 */
	protected List<CustomLaneEditPart> getLanes() {
		if(getHost().getParent() instanceof CustomPoolCompartmentEditPart){
			return ((CustomPoolCompartmentEditPart) getHost().getParent()).getPoolLanes();
		} else {
			return Collections.emptyList();
		}		
	}

	protected IUndoableOperation getSwitchLaneCommand(String type) {
		IUndoableOperation c = new SwitchLaneCommand<CustomLaneEditPart>((IGraphicalEditPart) getHost(), type);
		return c;
	}

}
