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

import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.diagram.custom.parts.CustomLaneCompartmentEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomLaneEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomPoolCompartmentEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomSubProcessEvent2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomSubprocessEventCompartmentEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomTextAnnotation2EditPart;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Container;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.bonitasoft.studio.model.process.TextAnnotation;
import org.bonitasoft.studio.model.process.diagram.edit.parts.LaneLaneCompartmentEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SequenceFlowEditPart;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToGuides;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableShapeEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.internal.ruler.DiagramGuide;
import org.eclipse.gmf.runtime.diagram.ui.internal.ruler.commands.ChangeGuideCommand;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.draw2d.ui.graphics.ColorRegistry;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.FillStyle;
import org.eclipse.gmf.runtime.notation.Guide;
import org.eclipse.gmf.runtime.notation.LineStyle;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;


/**
 * @author Mickael Istria
 *
 * This class overrides the {@link XYLayoutEditPolicy} createChildEditPolicy method
 * to use a custom {@link EditPolicy} PRIMARY_DRAG_ROLE, which hides the sourceFeedback
 */
public class CustomFeedbackXYLayoutPolicy extends XYLayoutEditPolicy implements EditPolicy {

	/*
	 * Removes Handles and rectangle around figure
	 * 
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.XYLayoutEditPolicy#createChildEditPolicy(org.eclipse.gef.EditPart)
	 */
	protected EditPolicy createChildEditPolicy(final EditPart child) {
		if ( child instanceof ShapeEditPart 
				&& (((ShapeEditPart)child).resolveSemanticElement() instanceof Activity
						|| ((ShapeEditPart)child).resolveSemanticElement() instanceof SubProcessEvent)){
			return new CustomResizableEditPolicyEx() ;
		}else if ( child instanceof ShapeEditPart 
				&& (((ShapeEditPart)child).resolveSemanticElement() instanceof FlowElement 
						&& !(((ShapeEditPart)child).resolveSemanticElement() instanceof Pool)
						|| ((ShapeEditPart)child).resolveSemanticElement() instanceof TextAnnotation)) {
			return new ResizableShapeEditPolicy() {

				@Override
				public void showPrimarySelection() {
					/*Allow black rectangle selection feedback and resize only for TextAnnotation*/
					if(((ShapeEditPart)child).resolveSemanticElement() instanceof TextAnnotation){
						super.showPrimarySelection();
					}else{
						super.showSelection();
						//BUGFIX PROBLEM WITH SOME CUT ELEMENT ON DIAGRAM
						hideSelection();
						IFigure layer = getLayer(LayerConstants.HANDLE_LAYER) ;
						layer.setBackgroundColor(ColorConstants.black) ;
						layer.setVisible(true) ;
						for(Object f : layer.getChildren()){
							((IFigure)f).setBackgroundColor(ColorConstants.black) ;
							((IFigure)f).setVisible(true) ;
						}
					}
				}

				@Override
				public void addSelectionHandles() {
					if (child instanceof CustomTextAnnotation2EditPart) {
						super.addSelectionHandles();
					}
					// else do nothing
				}

				@Override
				public void showSourceFeedback(Request request) {
					if(request instanceof ChangeBoundsRequest){
						Command moveCommand = getMoveCommand((ChangeBoundsRequest)request);
						if (moveCommand != null && moveCommand.canExecute()) {
							super.showSourceFeedback(request);
						} else {
							eraseSourceFeedback(request);
						}
					}
				}

				@Override
				protected IFigure createDragSourceFeedbackFigure() {
					IFigure res = null;
					/*For TextAnnotation use the figuredescriptor in order to have the exact same UI for teh feedback on the move*/
					if (child instanceof CustomTextAnnotation2EditPart){
						res = ((CustomTextAnnotation2EditPart) child).createCustomNoteFigureDescriptor();
						res.setBounds(((AbstractGraphicalEditPart) child).getFigure().getBounds().getCopy());
					} else {
						/*In other case use a svgFigure*/

						Rectangle bounds = ((ShapeEditPart)child).getFigure().getBounds() ; 
						final View childNotationView = ((IGraphicalEditPart)child).getNotationView();
						Color background = ColorRegistry.getInstance().getColor(((FillStyle) childNotationView.getStyle(NotationPackage.eINSTANCE.getFillStyle())).getFillColor()) ;
						Color foreground = ColorRegistry.getInstance().getColor(((LineStyle) childNotationView.getStyle(NotationPackage.eINSTANCE.getLineStyle())).getLineColor()) ; 
						res = FiguresHelper.getSelectedFigure(((ShapeEditPart)child).resolveSemanticElement().eClass(),bounds.width,bounds.height,foreground,background);
						res.getSize().performScale(((DiagramRootEditPart) getHost().getRoot()).getZoomManager().getZoom()) ;

					}

					if(res != null)
						addFeedback(res);
					return res;
				}



				@Override
				protected Command getMoveCommand(ChangeBoundsRequest request) {
					if (request.getLocation() == null) {
						return super.getMoveCommand(request);
					}
					IGraphicalEditPart host = (IGraphicalEditPart)getHost();
					Container hostParent = ModelHelper.getParentContainer(host.resolveSemanticElement());

					IGraphicalEditPart target = null ;
					if(host.getViewer().findObjectAt(request.getLocation()) instanceof IGraphicalEditPart){
						target = (IGraphicalEditPart) host.getViewer().findObjectAt(request.getLocation());
					}

					Container targetParent = null ;
					if(target != null){
						targetParent = ModelHelper.getParentContainer(target.resolveSemanticElement());
					}

					if (target == null || hostParent.equals(targetParent)) {
						//Avoid figure overlap
						if(target != null && (target instanceof ShapeNodeEditPart 
								|| (target instanceof ITextAwareEditPart && !getHost().getChildren().contains(target)))){
							if(target instanceof SequenceFlowEditPart
									|| target instanceof CustomPoolCompartmentEditPart 
									|| target instanceof CustomLaneCompartmentEditPart  
									|| target instanceof LaneLaneCompartmentEditPart
									|| target.equals(getHost())){

								return super.getMoveCommand(request);
							}

							return UnexecutableCommand.INSTANCE ;
						}
						return super.getMoveCommand(request);
					}else{
						return UnexecutableCommand.INSTANCE;
					}
				}
			};
		}

		return super.createChildEditPolicy(child);
	}	

	/**
	 * Override in order to change the location if a figure overrides another
	 */
	@Override
	protected Command createChangeConstraintCommand(final EditPart child,
			Object constraint) {

		Rectangle newBounds = (Rectangle) constraint;
		View shapeView = (View) child.getModel();
		final Point location = newBounds.getLocation() ;
		final Dimension size = newBounds.getSize() ;

		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();

		final EObjectAdapter adapter =  new EObjectAdapter(shapeView) ;

		ICommand boundsCommand = 
			new SetBoundsCommand(editingDomain,
					DiagramUIMessages.SetLocationCommand_Label_Resize,
					adapter,
					newBounds){
			@Override
			protected CommandResult doExecuteWithResult(
					IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {

				if (adapter == null)
					return CommandResult.newErrorCommandResult("SetBoundsCommand: viewAdapter does not adapt to IView.class"); //$NON-NLS-1$

				View view  = (View)adapter.getAdapter(View.class);

				if (location != null) {
					Point newLoc = location ;
					if(FiguresHelper.AVOID_OVERLAP_ENABLE){ 
						do{
							newLoc = FiguresHelper.handleCompartmentMargin((GraphicalEditPart) child, newLoc.x, newLoc.y,getHost() instanceof CustomSubprocessEventCompartmentEditPart)  ;	
						}while(!newLoc.equals(FiguresHelper.handleCompartmentMargin((GraphicalEditPart) child, newLoc.x, newLoc.y,getHost() instanceof CustomSubprocessEventCompartmentEditPart)));
					}
					ViewUtil.setStructuralFeatureValue(view,NotationPackage.eINSTANCE.getLocation_X(), Integer.valueOf(newLoc.x));
					ViewUtil.setStructuralFeatureValue(view,NotationPackage.eINSTANCE.getLocation_Y(), Integer.valueOf(newLoc.y));
				}
				if (size != null) {
					ViewUtil.setStructuralFeatureValue(view,NotationPackage.eINSTANCE.getSize_Width(), Integer.valueOf(size.width));
					ViewUtil.setStructuralFeatureValue(view,NotationPackage.eINSTANCE.getSize_Height(), Integer.valueOf(size.height));
				}
				return CommandResult.newOKCommandResult();
			}
		}; 

		return new ICommandProxy(boundsCommand);
	}

	/**
	 * Override to compute position of Subprocess event children when changing parent
	 */
	@Override
	protected Command createAddCommand(EditPart child, Object constraint) {
		if ( child instanceof CustomSubProcessEvent2EditPart && constraint instanceof Rectangle) {
			Rectangle rect = (Rectangle) constraint;

			CompoundCommand cmds = new CompoundCommand("Move Subprocesss Event") ;

			ICommand boundsCommand = new SetBoundsCommand(((ShapeEditPart) child).getEditingDomain(),
					DiagramUIMessages.SetLocationCommand_Label_Resize,
					new EObjectAdapter((View) child.getModel()),
					rect.getTopLeft()); 

			cmds.add(new ICommandProxy(boundsCommand)) ;

			ShapeCompartmentEditPart compartment = null ;
			for(Object c : child.getChildren()){
				if(c instanceof ShapeCompartmentEditPart){
					compartment =  (ShapeCompartmentEditPart) c; 
				}
			}
			
			Location origin = (Location) ((Node) child.getModel()).getLayoutConstraint() ;
			for(Object ep : compartment.getChildren()){
				if(ep instanceof IGraphicalEditPart){
					Node view = (Node) ((IGraphicalEditPart) ep).getModel() ;
					Location loc = (Location) view.getLayoutConstraint() ;
					Point delta = new Point(loc.getX()-origin.getX(),loc.getY()-origin.getY()) ;
					
					Point newLoc = new Point(rect.getTopLeft().x+delta.x,rect.getTopLeft().y + delta.y);

					boundsCommand = new SetBoundsCommand(((IGraphicalEditPart) ep).getEditingDomain(),
							DiagramUIMessages.SetLocationCommand_Label_Resize,
							new EObjectAdapter((View) ((IGraphicalEditPart) ep).getModel()),
							newLoc); 

					cmds.add(new ICommandProxy(boundsCommand)) ;
				}
			}

			return cmds.unwrap() ;
		}else{
			return super.createAddCommand(child, constraint);
		}

	}

	@Override
	protected Command getResizeChildrenCommand(final ChangeBoundsRequest request) {
		if(request.getExtendedData().get(CustomResizableEditPolicyEx.MOVE_COMPARTMENT_CHILDREN) != null){
			return super.getResizeChildrenCommand(request);
		}else{

			CompoundCommand resize = new CompoundCommand("Resize") ;
			resize.add(super.getResizeChildrenCommand(request)) ;

			if(!(getHost() instanceof CustomSubprocessEventCompartmentEditPart)){
				ChangeBoundsRequest req = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE) ;
				req.setEditParts(getHost().getParent());
				for(Object ep : request.getEditParts()){
					if(ep instanceof IGraphicalEditPart &&!(ep instanceof CustomLaneEditPart)){
						Rectangle bounds = request.getTransformedRectangle(((IGraphicalEditPart) ep).getFigure().getBounds().getCopy()) ;
						Rectangle containerBounds = ((IGraphicalEditPart) getHost()).getFigure().getBounds().getCopy() ;
						getHostFigure().translateToAbsolute(containerBounds) ;
						getHostFigure().translateToAbsolute(bounds) ;
						int yDelta = 0 ;
						if(bounds.height + bounds.y + 30 > containerBounds.y + containerBounds.height){
							yDelta = ((bounds.height + bounds.y)  + 50 - (containerBounds.y + containerBounds.height)) ;
							req.setResizeDirection(PositionConstants.SOUTH) ;
						}

						int xDelta = 0 ;
						if(bounds.width + bounds.x + 50 > containerBounds.x + containerBounds.width){
							xDelta = ((bounds.width + bounds.x) + 100 - (containerBounds.x + containerBounds.width)) ;
							req.setResizeDirection(PositionConstants.EAST) ;
						}
						Dimension delta = req.getSizeDelta() ;
						if(delta != null){
							if(delta.width < xDelta){
								delta.width = xDelta ;
							}
							if(delta.height < yDelta){
								delta.height = yDelta ;
							}
						}else{
							delta = new Dimension(xDelta, yDelta) ;
						}
						req.setSizeDelta(delta) ;

					}
				}
				if(getHost() instanceof CustomLaneCompartmentEditPart && req.getSizeDelta().width > 0){
					if(req.getSizeDelta().width > 0 || req.getSizeDelta().height>0){
						req.getEditParts().remove(getHost().getParent()) ;
						req.getEditParts().add(getHost().getParent().getParent().getParent()) ;
						resize.add(getHost().getParent().getParent().getParent().getCommand(req)) ;
					}
				}else{
					if(req.getSizeDelta().width > 0 || req.getSizeDelta().height>0){
						resize.add(getHost().getParent().getCommand(req)) ;
					}
				}
				if(req.getSizeDelta().width > 0 || req.getSizeDelta().height > 0){
					resize.add(new Command() {
						@Override
						public void execute() {
							super.execute();
							getHost().getViewer().select(getHost().getParent());
						}
					});
				}
			}
			return resize.unwrap() ;
		}
	}


	@Override
	protected Command createChangeConstraintCommand(
			ChangeBoundsRequest request, EditPart child, Object constraint) {


		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
		.getEditingDomain();
		Command cmd = null ;
		if(request.getExtendedData().get(CustomResizableEditPolicyEx.MOVE_COMPARTMENT_CHILDREN) != null){
			cmd = super.createChangeConstraintCommand(child, constraint);
		}else{
			cmd = createChangeConstraintCommand(child, constraint);
		}

		View view = (View)child.getModel();
		if ((request.getResizeDirection() & PositionConstants.NORTH_SOUTH) != 0) {
			Integer guidePos = (Integer)request.getExtendedData()
			.get(SnapToGuides.KEY_HORIZONTAL_GUIDE);
			if (guidePos != null) {
				int hAlignment = ((Integer)request.getExtendedData()
						.get(SnapToGuides.KEY_HORIZONTAL_ANCHOR)).intValue();
				ChangeGuideCommand cgm = new ChangeGuideCommand(editingDomain, view, true);
				cgm.setNewGuide(findGuideAt(guidePos.intValue(), true), hAlignment);
				cmd = cmd.chain(new ICommandProxy(cgm));
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
				cgm.setNewGuide(findGuideAt(guidePos.intValue(), false), vAlignment);
				cmd = cmd.chain(new ICommandProxy(cgm));
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
				cgm = new ChangeGuideCommand(editingDomain,view, true);;
				int hAlignment = ((Integer)request.getExtendedData()
						.get(SnapToGuides.KEY_HORIZONTAL_ANCHOR)).intValue();
				cgm.setNewGuide(findGuideAt(guidePos.intValue(), true), hAlignment);
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
				cgm.setNewGuide(findGuideAt(guidePos.intValue(), false), vAlignment);
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

}
