/*
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
package org.bonitasoft.studio.diagram.custom.parts;

import org.bonitasoft.studio.common.gmf.tools.CustomRubberbandDragTracker;
import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.diagram.custom.editPolicies.CustomDragDropEditPolicy;
import org.bonitasoft.studio.diagram.custom.editPolicies.CustomSnapFeedbackPolicy;
import org.bonitasoft.studio.diagram.custom.figures.CustomShapeCompartmentFigure;
import org.bonitasoft.studio.model.process.Container;
import org.bonitasoft.studio.model.process.diagram.edit.parts.LaneLaneCompartmentEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.policies.LaneLaneCompartmentCanonicalEditPolicy;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutAnimator;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gef.tools.DeselectAllTracker;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.ShapeCompartmentFigure;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author Romain Bioteau
 */
public class CustomLaneCompartmentEditPart extends LaneLaneCompartmentEditPart {

	public CustomLaneCompartmentEditPart(View view) {
		super(view);
	}


	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();
		figure.setToolTip(null);
	}


	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.CREATION_ROLE,
				new CreationEditPolicy());
		installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE,
				new CustomDragDropEditPolicy());
		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE,
				new LaneLaneCompartmentCanonicalEditPolicy());
		removeEditPolicy(EditPolicyRoles.SNAP_FEEDBACK_ROLE);
		installEditPolicy(EditPolicyRoles.SNAP_FEEDBACK_ROLE,
				new CustomSnapFeedbackPolicy());
	}


	@Override
	public IFigure createFigure() {
		ShapeCompartmentFigure scf = new CustomShapeCompartmentFigure(getCompartmentName(), getMapMode());
		scf.setBorder(null) ;
		scf.getContentPane().setLayoutManager(getLayoutManager());
		scf.getContentPane().addLayoutListener(LayoutAnimator.getDefault());
		scf.setTitleVisibility(false);

		return scf;
	}


	@Override
	public void showSourceFeedback(Request request) {
		//DO NOT SHOW FEEDBACK
	}

	@Override
	public void showTargetFeedback(Request request) {
		if(request instanceof ChangeBoundsRequest){
			if(!(((GraphicalEditPart)((ChangeBoundsRequest)request).getEditParts().get(0)).resolveSemanticElement() instanceof Container)){
				super.showTargetFeedback(request);
			}
		}else{
			super.showTargetFeedback(request);
		}
	}

	@Override
	public void setSelected(int value) {
		getParent().setSelected(value) ;
	}
	
	
	@Override
	public DragTracker getDragTracker(Request req) {
		if (!supportsDragSelection())
			return super.getDragTracker(req);

		if (req instanceof SelectionRequest
			&& ((SelectionRequest) req).getLastButtonPressed() == 3)
			return new DeselectAllTracker(this) {

				protected boolean handleButtonDown(int button) {
					getCurrentViewer().select(CustomLaneCompartmentEditPart.this);
					return true;
				}
			};
		return new CustomRubberbandDragTracker() {

			protected void handleFinished() {
				if (getViewer().getSelectedEditParts().isEmpty())
					getViewer().select(CustomLaneCompartmentEditPart.this);
			}
		};
	}
	
	/**
	 * @Generated BonitaSoft
	 */
	@Override
	public Object getAdapter(Class key) {

		if (key == SnapToHelper.class) {
			EditPart parent = getParent();
			while (!(parent instanceof DiagramEditPart)) {
				parent = parent.getParent();
			}
			return GMFTools.getSnapHelper((GraphicalEditPart) parent);
		}

		return super.getAdapter(key);
	}
}

