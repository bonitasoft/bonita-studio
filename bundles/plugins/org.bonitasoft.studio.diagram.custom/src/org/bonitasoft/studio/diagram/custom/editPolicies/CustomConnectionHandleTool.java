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

package org.bonitasoft.studio.diagram.custom.editPolicies;

import java.util.List;

import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * @author Romain Bioteau
 *
 */
public class CustomConnectionHandleTool extends UnspecifiedTypeConnectionTool  implements DragTracker{

	private final EditPart owner;

	public CustomConnectionHandleTool(EditPart owner,List<IElementType> type) {
		super(type);
		this.owner = owner ;
	}


	/**
	 * In order to set the source edit part of the connection tool we return
	 * the owner when the target edit part return null
	 */
	@Override
	protected EditPart getTargetEditPart() {
		if( super.getTargetEditPart() == null){
			return owner ;
		}
		return super.getTargetEditPart();

	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.tools.TargetingTool#updateTargetUnderMouse()
	 */
	@Override
	protected boolean updateTargetUnderMouse() {
		final boolean r = super.updateTargetUnderMouse() ;
		final EditPart editPart = getTargetEditPart() ;
		if(((IGraphicalEditPart)editPart).resolveSemanticElement() instanceof Pool){
			setTargetEditPart(null);//pool cannot be connected to anything
		}
		return r ;
	}

}
