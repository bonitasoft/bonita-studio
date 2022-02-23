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
package org.bonitasoft.studio.diagram.custom.editPolicies;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.diagram.custom.figures.SubprocessCollapseHandle;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ResizableCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableCompartmentEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;

/**
 * @author Romain Bioteau
 *
 */
public class CustomSubProcessResizableCompartmentEditPolicy extends
		ResizableCompartmentEditPolicy {

	@Override
	protected List<SubprocessCollapseHandle> createCollapseHandles() {
		IGraphicalEditPart part = (IGraphicalEditPart) getHost() ;
		List<SubprocessCollapseHandle> collapseHandles = new ArrayList<SubprocessCollapseHandle>();
		collapseHandles.add(new SubprocessCollapseHandle(part));
		return collapseHandles;
	}
	
	protected List<SubprocessCollapseHandle> createSelectionHandles() {
		List<SubprocessCollapseHandle> selectionHandles = new ArrayList<SubprocessCollapseHandle>();
		selectionHandles.addAll(createCollapseHandles());
		return selectionHandles;
	}

	protected void hideSelection() {}
	
	@Override
	public void deactivate() {
		super.deactivate();
		super.hideSelection();
		if (getHost().getSelected() == EditPart.SELECTED_NONE) {
			ResizableCompartmentFigure compartmentFigure = getCompartmentFigure();
			if (compartmentFigure != null) {
				compartmentFigure.setSelected(false);
			}
		}
	}
	
	@Override
	public void activate() {
		super.activate();
		showSelection() ;
	}
	
	private ResizableCompartmentFigure getCompartmentFigure() {
		ResizableCompartmentFigure compartmentFigure = null;
		if (getGraphicalEditPart() instanceof ResizableCompartmentEditPart) {
			compartmentFigure = ((ResizableCompartmentEditPart) getGraphicalEditPart())
				.getCompartmentFigure();
		} else if (getGraphicalEditPart().getFigure() instanceof ResizableCompartmentFigure) {
			compartmentFigure = (ResizableCompartmentFigure) getGraphicalEditPart()
				.getFigure();
		}
		// TODO: remove later. this is a temporary fix for defect
		// RATLC00522565
		// eventually we will put the BorderedNodeFigure inside the resizable
		// compartment
		else if (getGraphicalEditPart().getFigure() instanceof BorderedNodeFigure) {
			BorderedNodeFigure gpf = (BorderedNodeFigure) getGraphicalEditPart()
				.getFigure();
			IFigure f = gpf.getMainFigure();
			if (f instanceof ResizableCompartmentFigure) {
				compartmentFigure = (ResizableCompartmentFigure) f;
			}
		}

		return compartmentFigure;
	}
	
	private IGraphicalEditPart getGraphicalEditPart() {
		return (IGraphicalEditPart) getHost();
	}
}
