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


import org.bonitasoft.studio.diagram.custom.commands.CompartmentRepositionEObjectCommand;
import org.bonitasoft.studio.diagram.custom.parts.CustomPoolEditPart;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToGuides;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.FlowLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.commands.AddCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableEditPolicyEx;
import org.eclipse.gmf.runtime.diagram.ui.internal.ruler.DiagramGuide;
import org.eclipse.gmf.runtime.diagram.ui.internal.ruler.commands.ChangeGuideCommand;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateUnspecifiedTypeRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.emf.commands.core.commands.RepositionEObjectCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.Guide;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author Romain Bioteau
 *
 */
public class CompartmentEditPolicy extends FlowLayoutEditPolicy {

	private EStructuralFeature feature = null;

	@Override
	public Command getCommand(Request request) {

		if(request.getType() == RequestConstants.REQ_RESIZE_CHILDREN
				&& request instanceof ChangeBoundsRequest){
			ChangeBoundsRequest changeBoundsRequest = (ChangeBoundsRequest)request;
			EditPart ep = (EditPart) changeBoundsRequest.getEditParts().get(0);
			if(ep instanceof IGraphicalEditPart){
				Node node = (Node)((IGraphicalEditPart)ep).getModel() ;
				Size size =(Size)node.getLayoutConstraint() ;
				Dimension s = new Dimension(size.getWidth(),size.getHeight());
				if(ep instanceof CustomPoolEditPart){
					if(s.height < 0){
						s.height = ((CustomPoolEditPart)ep).getDefaultHeight();
					}

					if(s.width < 0){
						s.width = ((CustomPoolEditPart)ep).getDefaultWidth();
					}
				}

				Location loc =(Location)node.getLayoutConstraint() ;
				Dimension delta = changeBoundsRequest.getSizeDelta();
				Rectangle constraint = new Rectangle(loc.getX(), loc.getY(), s.width + delta.width, s.height + delta.height);
				return createChangeConstraintCommand((ChangeBoundsRequest) request,ep,constraint);
			}
		}

		return super.getCommand(request);
	}

	protected Command createChangeConstraintCommand(
			EditPart child,
			Object constraint) {
		org.eclipse.draw2d.geometry.Rectangle newBounds = (org.eclipse.draw2d.geometry.Rectangle) constraint;
		View shapeView = (View) child.getModel();

		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
		.getEditingDomain();

		ICommand boundsCommand = 
			new SetBoundsCommand(editingDomain,
					DiagramUIMessages.SetLocationCommand_Label_Resize,
					new EObjectAdapter(shapeView),
					newBounds); 
		return new ICommandProxy(boundsCommand);
	}


	protected Command createChangeConstraintCommand(
			ChangeBoundsRequest request, EditPart child, Object constraint) {


		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
		.getEditingDomain();

		Command cmd = createChangeConstraintCommand(child, constraint);
		View view = (View)child.getModel();
		if ((request.getResizeDirection() & PositionConstants.NORTH_SOUTH) != 0) {
			Integer guidePos = (Integer)request.getExtendedData()
			.get(SnapToGuides.KEY_HORIZONTAL_GUIDE);
			if (guidePos != null) {
				int hAlignment = ((Integer)request.getExtendedData()
						.get(SnapToGuides.KEY_HORIZONTAL_ANCHOR)).intValue();
				ChangeGuideCommand cgm = new ChangeGuideCommand(editingDomain, view, true);
				//cgm.setNewGuide(findGuideAt(guidePos.intValue(), true), hAlignment);
				//cmd = cmd.chain(new ICommandProxy(cgm));
			} else if (DiagramGuide.getInstance().getHorizontalGuide(view) != null) {
				// SnapToGuides didn't provide a horizontal guide, but this part is attached
				// to a horizontal guide.  Now we check to see if the part is attached to
				// the guide along the edge being resized.  If that is the case, we need to
				// detach the part from the guide; otherwise, we leave it alone.
				int alignment = DiagramGuide.getInstance().getHorizontalAlignment(view);
				int edgeBeingResized = 0;
				if ((request.getResizeDirection() & PositionConstants.NORTH) != 0)
					edgeBeingResized = -1;
				else
					edgeBeingResized = 1;
				if (alignment == edgeBeingResized) {
					ChangeGuideCommand cgm = new ChangeGuideCommand(editingDomain, view, true);
					cmd = cmd.chain(new ICommandProxy(cgm));
				}
			}
		}

		if ((request.getResizeDirection() & PositionConstants.EAST_WEST) != 0) {
			Integer guidePos = (Integer)request.getExtendedData()
			.get(SnapToGuides.KEY_VERTICAL_GUIDE);
			if (guidePos != null) {
				int vAlignment = ((Integer)request.getExtendedData()
						.get(SnapToGuides.KEY_VERTICAL_ANCHOR)).intValue();
				ChangeGuideCommand cgm = new ChangeGuideCommand(editingDomain, view, false);
				//cgm.setNewGuide(findGuideAt(guidePos.intValue(), false), vAlignment);
				//cmd = cmd.chain(new ICommandProxy(cgm));
			} else if (DiagramGuide.getInstance().getVerticalGuide(view) != null) {
				int alignment = DiagramGuide.getInstance().getVerticalAlignment(view);
				int edgeBeingResized = 0;
				if ((request.getResizeDirection() & PositionConstants.WEST) != 0)
					edgeBeingResized = -1;
				else
					edgeBeingResized = 1;
				if (alignment == edgeBeingResized) {
					ChangeGuideCommand cgm = new ChangeGuideCommand(editingDomain, view, false);
					cmd = cmd.chain(new ICommandProxy(cgm));
				}
			}
		}

		if (request.getType().equals(REQ_MOVE_CHILDREN)
				|| request.getType().equals(REQ_ALIGN_CHILDREN)) {
			Integer guidePos = (Integer)request.getExtendedData()
			.get(SnapToGuides.KEY_HORIZONTAL_GUIDE);
			ChangeGuideCommand cgm = null;
			if (guidePos != null) {
				cgm = new ChangeGuideCommand(editingDomain,view, true);
				int hAlignment = ((Integer)request.getExtendedData()
						.get(SnapToGuides.KEY_HORIZONTAL_ANCHOR)).intValue();
				// cgm.setNewGuide(findGuideAt(guidePos.intValue(), true), hAlignment);
			}else {
				Guide theOldGuide = DiagramGuide.getInstance().getHorizontalGuide(view);
				if (theOldGuide!=null)
					cgm = new ChangeGuideCommand(editingDomain,view, true);
			}
			// If know this creates a lot of extra commands.  They are currently
			// required for attaching/detaching shapes to guides
			if (cgm!=null)
				cmd = cmd.chain(new ICommandProxy(cgm));

			guidePos = (Integer)request.getExtendedData()
			.get(SnapToGuides.KEY_VERTICAL_GUIDE);
			cgm = null;
			if (guidePos != null) {
				cgm = new ChangeGuideCommand(editingDomain, view, false);
				int vAlignment = ((Integer)request.getExtendedData()
						.get(SnapToGuides.KEY_VERTICAL_ANCHOR)).intValue();
				//   cgm.setNewGuide(findGuideAt(guidePos.intValue(), false), vAlignment);
			}else {
				Guide theOldGuide = DiagramGuide.getInstance().getVerticalGuide(view);
				if (theOldGuide!=null)
					cgm = new ChangeGuideCommand(editingDomain, view, true);
			}
			// If know this creates a lot of extra commands.  They are currently
			// required for attaching/detaching shapes to guides
			if (cgm!=null)
				cmd = cmd.chain(new ICommandProxy(cgm));
		}


		return cmd;
	}

	protected Command createAddCommand(EditPart child, EditPart after) {
		int index = getHost().getChildren().indexOf(after);
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
		AddCommand command = new AddCommand(editingDomain, new EObjectAdapter((View)getHost().getModel()),
				new EObjectAdapter((View)child.getModel()), index);
		return new ICommandProxy(command);
	}

	protected EditPolicy createChildEditPolicy(EditPart child) {
		ResizableEditPolicyEx policy = new CustomResizableEditPolicyEx();
		policy.setResizeDirections(PositionConstants.NORTH | PositionConstants.SOUTH | PositionConstants.EAST );
		return policy;
	}

	protected Command createMoveChildCommand(EditPart child, EditPart after) {	

		int newIndex;
		int displacement;

		int childIndex = getHost().getChildren().indexOf(child);
		int afterIndex = getHost().getChildren().indexOf(after);	

		if(afterIndex == -1) {
			newIndex = getHost().getChildren().size()-1;			
			displacement = newIndex - childIndex;
		} else {		
			newIndex = afterIndex;
			displacement = afterIndex - childIndex;
			if (childIndex <= afterIndex) {
				newIndex--;
				displacement--;			
			}
		}

		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();

		RepositionEObjectCommand command = new CompartmentRepositionEObjectCommand(child, editingDomain, "", 
				(EList<?>)((View)child.getParent().getModel()).getElement().eGet(feature), 
				((View)child.getModel()).getElement(), 
				displacement, newIndex);	

		return new ICommandProxy(command);
	}

	protected Command getCreateCommand(CreateRequest request) {
		return null;
	}

	protected Command getDeleteDependantCommand(Request request) {
		return null;
	}

	protected Command getOrphanChildrenCommand(Request request) {
		return null;
	}

	/**
	 * @param feature has to be an EList
	 */
	public CompartmentEditPolicy(EStructuralFeature feature) {
		super();
		this.feature = feature;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.FlowLayoutEditPolicy#isHorizontal()
	 */
	@Override
	protected boolean isHorizontal() {
		return false;
	}

	@Override
	public void eraseTargetFeedback(Request request) {
		super.eraseTargetFeedback(request);
		if (RequestConstants.REQ_DROP.equals(request.getType()))
			eraseLayoutTargetFeedback(request);
	}
	


	@Override
	protected void showLayoutTargetFeedback(Request request) {
		if(request instanceof CreateUnspecifiedTypeRequest){
			IElementType newElementType = (IElementType) ((CreateUnspecifiedTypeRequest) request).getElementTypes().get(0);
			/*Allow only Pool and Evenmential pool to have a feedback as it is the only to have right to be created here*/
			if(newElementType != null){
				Class<?> ec = newElementType.getEClass().getInstanceClass();
				if(Pool.class.equals(ec)){
					super.showLayoutTargetFeedback(request);
				}
			}
		} else {
			super.showLayoutTargetFeedback(request);
		}
	}
}
