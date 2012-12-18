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
package org.bonitasoft.studio.diagram.custom.parts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.diagram.custom.editPolicies.CustomDragDropEditPolicy;
import org.bonitasoft.studio.diagram.custom.editPolicies.CustomSnapFeedbackPolicy;
import org.bonitasoft.studio.diagram.custom.figures.CustomShapeCompartmentFigure;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.EventSubProcessPool;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EventSubProcessPoolPoolCompartmentEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.Lane2EditPart;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutAnimator;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.figures.ShapeCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author Aurelien Pupier
 */
public class CustomEventSubProcessPoolPoolCompartmentEditPart extends
		EventSubProcessPoolPoolCompartmentEditPart {
	
	private List<Lane2EditPart> poolLanes ;

	/**
	 * @return the poolLanes
	 */
	public List<Lane2EditPart> getPoolLanes() {
		return poolLanes;
	}
	
	

	public CustomEventSubProcessPoolPoolCompartmentEditPart(View view) {
		super(view);
		poolLanes = new LinkedList<Lane2EditPart>();
		addEditPartListener(new EditPartListener() {

			public void selectedStateChanged(EditPart arg0) {
			}

			public void removingChild(EditPart ep, int arg1) {
				((CustomEventSubProcessPoolEditPart)getParent()).refreshBoundsAfterRemove(ep) ;
			}

			public void partDeactivated(EditPart arg0) {

			}

			public void partActivated(EditPart arg0) {
				((CustomEventSubProcessPoolEditPart)getParent()).refreshBounds();
			}

			public void childAdded(EditPart ep, int arg1) {
				refreshPoolLanes();
				if(ep instanceof Lane2EditPart){
					reparentContentToNewChild((Lane2EditPart) ep);
					((CustomEventSubProcessPoolEditPart)getParent()).refreshBounds();
				}
			}
		});
	}



	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();
		figure.setToolTip(null);
	}

	public void refreshPoolLanes(){
		poolLanes.clear();
		EventSubProcessPool p = (EventSubProcessPool) resolveSemanticElement() ;
		for(Element e : p.getElements()){
			if(e instanceof Lane){
				Lane2EditPart lEp = (Lane2EditPart) findEditPart(this, e);
				if(lEp != null)
					poolLanes.add(lEp);
			}
		}
	}

	
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		removeEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE);
		installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE,
				new CustomDragDropEditPolicy());
		removeEditPolicy(EditPolicyRoles.SNAP_FEEDBACK_ROLE);
		installEditPolicy(EditPolicyRoles.SNAP_FEEDBACK_ROLE,
				new CustomSnapFeedbackPolicy());
	}


	@Override
	public IFigure createFigure() {
		ShapeCompartmentFigure scf = new CustomShapeCompartmentFigure(getCompartmentName(), getMapMode());
		scf.getContentPane().setLayoutManager(getLayoutManager());
		scf.getContentPane().addLayoutListener(LayoutAnimator.getDefault());
		scf.setTitleVisibility(false);
		return scf;
	}

	@Override
	public ResizableCompartmentFigure getCompartmentFigure() {	
		return (ResizableCompartmentFigure) getFigure();
	}


	public void reparentContentToNewChild(Lane2EditPart lane) {

		List<EditPart> epToMove = new ArrayList<EditPart>();
		for(Object o : getChildren()){
			if(o instanceof Lane2EditPart){

				for(Object o1 : getChildren()){
					if(!(o1 instanceof Lane2EditPart)){
						epToMove.add((EditPart) o1);
					}
				}
			}
		}

		if(poolLanes.size() == 1){
			createMoveRequest(lane,epToMove);
		}

	}
	
	private void createMoveRequest(GraphicalEditPart lane,
			List<EditPart> epToMove2) {

		ChangeBoundsRequest request = new ChangeBoundsRequest();
		request.setEditParts(epToMove2);
		request.setType(RequestConstants.REQ_ADD);
		CompartmentEditPart compartment = null ;
		for(Object o : lane.getChildren()){
			if (o instanceof CompartmentEditPart){
				compartment = (CompartmentEditPart) o ;
			}
		}
		if(compartment == null){
			compartment =  (CompartmentEditPart) lane;
		}
		if(compartment.getEditPolicy(EditPolicyRoles.CREATION_ROLE) == null){
			compartment.installEditPolicy(EditPolicyRoles.CREATION_ROLE, new CreationEditPolicy());
		}
	
		CreationEditPolicy cep = (CreationEditPolicy) compartment.getEditPolicy(EditPolicyRoles.CREATION_ROLE);
		compartment.getDiagramEditDomain().getDiagramCommandStack().execute(cep.getCommand(request));

	}
	
	@Override
	public void setSelected(int value) {
		getParent().setSelected(value) ;
	}
	
	@Override
	public void refreshConnections() {
		try {
			super.refreshConnections();
		} catch (Exception ex) {
			BonitaStudioLog.error(ex);
		}
	}
}
