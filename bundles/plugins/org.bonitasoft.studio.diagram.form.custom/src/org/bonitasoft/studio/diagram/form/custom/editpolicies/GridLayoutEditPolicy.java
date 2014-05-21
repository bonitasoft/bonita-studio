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

package org.bonitasoft.studio.diagram.form.custom.editpolicies;

import java.util.List;

import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.diagram.form.custom.commands.AddColumnCommand;
import org.bonitasoft.studio.diagram.form.custom.commands.AddRowCommand;
import org.bonitasoft.studio.diagram.form.custom.commands.GridLayoutMoveCommand;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.form.WidgetLayoutInfo;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FormEditPart;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;

/**
 * @author Aurelien Pupier
 * 
 */
public class GridLayoutEditPolicy extends AbstractGridLayoutEditPolicy {

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#
	 * createChangeConstraintCommand(org.eclipse.gef.EditPart, java.lang.Object)
	 */
	@Override
	protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.editpolicies.LayoutEditPolicy#getCreateCommand(org.eclipse
	 * .gef.requests.CreateRequest)
	 */
	@Override
	protected Command getCreateCommand(CreateRequest request) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#
	 * getMoveChildrenCommand(org.eclipse.gef.Request)
	 */
	@Override
	protected Command getMoveChildrenCommand(Request request) {
		CompoundCommand command = new CompoundCommand();		

		if (request instanceof ChangeBoundsRequest) {
			ChangeBoundsRequest req = (ChangeBoundsRequest) request;
			List<?> editParts = req.getEditParts();
			Point requestLocation = req.getLocation();
			
			Point point = requestLocation.getCopy().translate(getLayoutOrigin().getNegated());
			FiguresHelper.translateToAbsolute(((IGraphicalEditPart) getHost()).getContentPane(), point);
			Point newPoint = getGridLayoutManager().getConstraintFor(point);

			for (int i = 0; i < editParts.size(); i++) {
				IGraphicalEditPart child = (IGraphicalEditPart) editParts.get(i);
				if(point.x > 0 && point.y > 0 && getGridLayoutManager().canAddFigure(newPoint, child.getFigure())){
					command.add(createMoveChildCommand(child, newPoint));
				} else {
					if( getHost() instanceof FormEditPart){
						InsertionPoint iPoint = (getGridLayoutManager().getClosestInsertionPoint(newPoint, point)) ;
						Rectangle dim = (Rectangle) getGridLayoutManager().getConstraint(child.getFigure()) ;
						int width = dim.width ;
						int height = dim.height ;
						Point insertionPoint = iPoint.getPoint();
						if(iPoint.getOutBoundsPosition().equals(InsertionPoint.Position.RIGHT)){
							for(int j = 0 ; i < width ; i++){
								command.add(new ICommandProxy(new AddColumnCommand((FormEditPart) getHost(), insertionPoint.x + j)));
							}
							command.add(createMoveChildCommand(child, iPoint.getPoint()));
						} else if(iPoint.getOutBoundsPosition().equals(InsertionPoint.Position.DOWN)){
							for(int j = 0 ; i < height ; i++){
								command.add(new ICommandProxy(new AddRowCommand((FormEditPart) getHost(), insertionPoint.y + j)));
							}
							command.add(createMoveChildCommand(child, insertionPoint));
						} else if(iPoint.getOutBoundsPosition().equals(InsertionPoint.Position.LEFT)){
							for(int j = 0 ; i < width ; i++){
								command.add(new ICommandProxy(new AddColumnCommand((FormEditPart) getHost(), insertionPoint.x + j)));
							}
							command.add(createMoveChildCommand(child, insertionPoint));
						} else if(iPoint.getOutBoundsPosition().equals(InsertionPoint.Position.UP)){
							for(int j = 0 ; i < height ; i++){
								command.add(new ICommandProxy(new AddRowCommand((FormEditPart) getHost(), insertionPoint.y + j)));
							}
							command.add(createMoveChildCommand(child, insertionPoint));
						}
					}
				}
			}

		}
		return command.unwrap();
	}


	/**
	 * @return true if the request location is on a widget (ie on nothing or on
	 *         the GridFormLayer)
	 */
	protected boolean onExisting(ChangeBoundsRequest request) {
		IGraphicalEditPart part = (IGraphicalEditPart) getHost();

		/* translate the point in absolute location and considering the margin */
		Point point = request.getLocation().getCopy().getTranslated(getLayoutOrigin().getNegated());
		FiguresHelper.translateToAbsolute(((IGraphicalEditPart) getHost()).getContentPane(), point);

		// need to translate from the margin but don't know exactly why
		int margin = ((AbstractGridLayer) ((IGraphicalEditPart) getHost()).getContentPane()).getMargin();

		IFigure target = part.getContentPane().findFigureAt(point.translate(part.getContentPane().getBounds().getTopLeft()).translate(margin, margin));


		/*
		 * check that there is not already a widget at the place and that we are
		 * in one of the column
		 */
		Point requestLocation = request.getLocation();
		Point locPoint = requestLocation.getCopy().translate(getLayoutOrigin().getNegated());
		FiguresHelper.translateToAbsolute(((IGraphicalEditPart) getHost()).getContentPane(), locPoint);
		Point newPoint = getGridLayoutManager().getConstraintFor(locPoint);
		Point insertionPoint = getGridLayoutManager().getClosestInsertionPoint(newPoint).getPoint() ;
		boolean onBorder = insertionPoint.x == 0 || insertionPoint.y == getGridFigure().getNumLine() || insertionPoint.x == getGridFigure().getNumColumn();
		if(onBorder){
			return false ;
		}

		return target != null && /*! (target instanceof DefaultSizeNodeFigure)*/!target.equals(part.getFigure());
	}

	/**
	 * @param child
	 * @param point
	 * @return
	 */
	protected Command createMoveChildCommand(IGraphicalEditPart child, Point point) {
		TransactionalEditingDomain domain = ((IGraphicalEditPart) getHost()).getEditingDomain();
		return new ICommandProxy(new GridLayoutMoveCommand(domain, child, (IGraphicalEditPart) getHost(), point));
	}

	/**
	 * Treat case of REQ_DROP.
	 * 
	 * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#eraseTargetFeedback(org
	 *      .eclipse.gef.Request)
	 */
	@Override
	public void eraseTargetFeedback(Request request) {
		if (REQ_ADD.equals(request.getType())
				|| REQ_RESIZE_CHILDREN.equals(request.getType())
				|| REQ_CREATE.equals(request.getType())
				|| REQ_CLONE.equals(request.getType())
				|| REQ_MOVE.equals(request.getType()))
			eraseLayoutTargetFeedback(request);
		/* add here the missing case in super class */
		if (org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants.REQ_DROP.equals(request.getType())) {
			eraseLayoutTargetFeedback(request);
		}
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#
	 * createChildEditPolicy(org.eclipse.gef.EditPart)
	 */
	@Override
	protected EditPolicy createChildEditPolicy(EditPart child) {
		return new NonResizableEditPolicy() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.gef.editpolicies.ResizableEditPolicy#eraseSourceFeedback
			 * (org.eclipse.gef.Request)
			 */
			@Override
			public void eraseSourceFeedback(Request request) {
				if (RequestConstants.REQ_DROP.equals(request.getType())
						&& request instanceof ChangeBoundsRequest){
					eraseChangeBoundsFeedback((ChangeBoundsRequest) request);
				} else {
					super.eraseSourceFeedback(request);
				}
			}

			@Override
			protected IFigure createDragSourceFeedbackFigure() {
				RectangleFigure r = new RectangleFigure();
				FigureUtilities.makeGhostShape(r);
				r.setLineStyle(Graphics.LINE_DOT);
				r.setBackgroundColor(ColorConstants.blue) ;
				r.setBounds(getInitialFeedbackBounds());
				r.setAlpha(50);
				addFeedback(r);
				return r;
			}

			/**
			 * Move outside of the numcolumn of the grid or on an existing
			 * widget is not allowed.
			 * 
			 * @see org.eclipse.gef.editpolicies.NonResizableEditPolicy#getMoveCommand(org.eclipse.gef.requests.ChangeBoundsRequest)
			 */
			@Override
			protected Command getMoveCommand(ChangeBoundsRequest request) {

				if(request.getLocation() == null){//TODO findout why sometimes location is null on Mac
					return null ;
				}

				Point point = request.getLocation().getCopy().getTranslated(
						getLayoutOrigin().getNegated());

				//TODO : use layoutmanager in order to be aware of span
				FiguresHelper.translateToAbsolute(((IGraphicalEditPart) getHost()).getContentPane(), point);
				if (onExisting( request)) {
					return null;//UnexecutableCommand.INSTANCE;
				}
				return super.getMoveCommand(request);
			}

			/**
			 * don't show anymore the feedback if we are out of the grid or on
			 * an existing widget
			 */
			@Override
			public void showSourceFeedback(Request request) {
				if (request instanceof ChangeBoundsRequest) {
					Point point = ((ChangeBoundsRequest) request).getLocation().getCopy().getTranslated(getLayoutOrigin().getNegated());
					FiguresHelper.translateToAbsolute(((AbstractGraphicalEditPart) getHost()).getContentPane(), point);
					if (!onExisting((ChangeBoundsRequest) request)) {
						super.showSourceFeedback(request);
					}else {
						eraseSourceFeedback(request);
					}
					
					if(RequestConstants.REQ_DROP.equals(request.getType())){
						showChangeBoundsFeedback((ChangeBoundsRequest) request);
					}
				}
				
			}
		};
	}

	/** This command is the most important to be implemented when a reparent command happen.
	 * it update the new layout info and refresh the visualization based on the new constraint.
	 * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createAddCommand(org.eclipse.gef.EditPart, java.lang.Object)
	 */
	@Override
	protected Command createAddCommand(EditPart child, Object constraint) {
		if ( child instanceof IGraphicalEditPart){
			Widget w = (Widget) ((IGraphicalEditPart) child).resolveSemanticElement();
			WidgetLayoutInfo lInfo = w.getWidgetLayoutInfo();
			if( constraint instanceof Point) {
				Point point = (Point) constraint;
				/*Change layout info based on the constraint*/
				CompositeCommand cc = new CompositeCommand("Composite command to set position and refresh bounds (case constraint Point)");
				SetRequest setRequest1 = new SetRequest(lInfo, FormPackage.Literals.WIDGET_LAYOUT_INFO__COLUMN, point.x);
				cc.add(new SetValueCommand(setRequest1));
				SetRequest setRequest2 = new SetRequest(lInfo, FormPackage.Literals.WIDGET_LAYOUT_INFO__LINE, point.y);
				cc.add(new SetValueCommand(setRequest2));
				/*Refresh the editpart just after changed the layout info,
				 * else it will be displayed with the old location*/
				IUndoableOperation c = new org.eclipse.gmf.runtime.diagram.ui.internal.commands.RefreshEditPartCommand((IGraphicalEditPart)child, false);
				//				RefreshEditPartCommand c = new RefreshEditPartCommand((IGraphicalEditPart) child);
				cc.add(c);
				return new ICommandProxy(cc);
			} else if(constraint instanceof Rectangle){
				Rectangle r = (Rectangle) constraint;
				/*Change layout info based on the constraint*/				
				CompositeCommand cc = new CompositeCommand("Composite command to set position and refresh bounds (case constraint Rectangle)");
				SetRequest setRequest1 = new SetRequest(lInfo, FormPackage.Literals.WIDGET_LAYOUT_INFO__COLUMN, r.x);
				cc.add(new SetValueCommand(setRequest1));
				SetRequest setRequest2 = new SetRequest(lInfo, FormPackage.Literals.WIDGET_LAYOUT_INFO__LINE, r.y);
				cc.add(new SetValueCommand(setRequest2));
				SetRequest setRequest3 = new SetRequest(lInfo, FormPackage.Literals.WIDGET_LAYOUT_INFO__HORIZONTAL_SPAN, r.width);
				cc.add(new SetValueCommand(setRequest3));
				SetRequest setRequest4 = new SetRequest(lInfo, FormPackage.Literals.WIDGET_LAYOUT_INFO__VERTICAL_SPAN, r.height);
				cc.add(new SetValueCommand(setRequest4));
				/*Refresh the editpart just after changed the layout info,
				 * else it will be displayed with the old location*/
				IUndoableOperation c = new org.eclipse.gmf.runtime.diagram.ui.internal.commands.RefreshEditPartCommand((IGraphicalEditPart)child, false);
				//				RefreshEditPartCommand c = new RefreshEditPartCommand((IGraphicalEditPart) child);
				cc.add(c);
				return new ICommandProxy(cc);
			}
		}
		return super.createAddCommand(child, constraint);
	}


}
