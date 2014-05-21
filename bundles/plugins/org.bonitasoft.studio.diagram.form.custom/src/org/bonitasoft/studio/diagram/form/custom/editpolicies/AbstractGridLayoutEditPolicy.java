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

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ScalableFreeformLayeredPane;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;

/**
 * Adapted ConstrainedLayoutEditPolicy for GridLayout.
 * Show a grey feedback for each cell.
 */
public abstract class AbstractGridLayoutEditPolicy extends ConstrainedLayoutEditPolicy {
	/** the drag target figure */
	private Shape[] dragTargetFeedbackArray = null;
	private Polyline insertionLine;
	private ImageFigure arrow;

	/**
	 * Gets the constraint of the point. The created constraint is a
	 * GridLayoutConstraint. Thus, it's not a reference to the request
	 * constraint.
	 * 
	 * @param point
	 * @return Object GridLayoutConstraint
	 */
	public final Object getConstraintFor(Point point) {
		return getGridLayoutManager().getConstraintFor(point);
	}

	/**
	 * Gets the constraint of the rectangle.
	 * <p>
	 * The created constraint is a GridLayoutConstraint. Thus, it's not a
	 * reference to the request constraint.
	 * 
	 * @param rectangle
	 * @return Object GridLayoutConstraint
	 */
	public final Object getConstraintFor(Rectangle rectangle) {
		return getGridLayoutManager().getConstraintFor(rectangle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#getConstraintFor
	 * (org.eclipse.gef.requests.ChangeBoundsRequest,
	 * org.eclipse.gef.GraphicalEditPart)
	 */
	protected Object getConstraintFor(ChangeBoundsRequest request, GraphicalEditPart child) {
		//TODO : take care of case with multiple selection on editpart
		Point newPoint = request.getLocation().getCopy().translate(getLayoutOrigin().getNegated());
		FiguresHelper.translateToAbsolute(((AbstractGraphicalEditPart) getHost()).getContentPane(), newPoint);
		Rectangle r = child.getFigure().getBounds().getCopy();
		r.x = newPoint.x;
		r.y = newPoint.y;
		return getGridLayoutManager().getConstraintFor(r);
	}

	/**
	 * Gets the dragTargetFeedback figure. Creates a new dragTargetFeedback
	 * figure if it is null.
	 * 
	 * @return IFigure
	 */
	protected Shape[] getDragTargetFeedbackArray(int size) {
		if (dragTargetFeedbackArray == null) {
			dragTargetFeedbackArray = new Shape[size];
			for (int i = 0; i < size; i++) {
				Shape dragTargetFeedback = new RectangleFigure();
				FigureUtilities.makeGhostShape(dragTargetFeedback);
				dragTargetFeedback.setLineStyle(Graphics.LINE_SOLID);
				dragTargetFeedback.setBackgroundColor(ColorConstants.gray);
				addFeedback(dragTargetFeedback);
				dragTargetFeedbackArray[i] = dragTargetFeedback;
			}
		}

		return dragTargetFeedbackArray;
	}

	/**
	 * Returns the layout container figure as <code>GridFigure</code>.
	 * 
	 * @return GridFigure
	 */
	protected AbstractGridLayer getGridFigure() {
		return (AbstractGridLayer ) getLayoutContainer();
	}

	/**
	 * Returns the layout manager of the <code>GridFigure</code> as
	 * <code>GridLayout</code>.
	 * 
	 * @return GridLayout
	 */
	protected GridLayoutManager getGridLayoutManager() {
		return (GridLayoutManager) getGridFigure().getGridLayout();
	}

	/**
	 * Erases the DragTargetFeedback.
	 * 
	 * @see com.ibm.etools.gef.editpolicies.LayoutEditPolicy#eraseDragTargetFeedback(Request)
	 */
	protected void eraseLayoutTargetFeedback(Request request) {
		eraseRectanglesFeedback(request) ;
		eraseInsertionFeedback(request) ;
	}

	/**
	 * Shows the DragTargetFeedback.
	 * 
	 * @see com.ibm.etools.gef.editpolicies.LayoutEditPolicy#showDragTargetFeedback(Request)
	 */
	protected void showLayoutTargetFeedback(Request request) {

		List<Rectangle> rectangles = new ArrayList<Rectangle>();
		boolean showInsertionFeedback = false ;
		boolean canAdd = false ;
		if (request instanceof CreateRequest) {
			/*show feedback only if it is in the grid*/
			Point point = ((CreateRequest) request).getLocation().getCopy().translate(getLayoutOrigin().getNegated());
			FiguresHelper.translateToAbsolute(((AbstractGraphicalEditPart) getHost()).getContentPane(), point);
			Point newPoint = getGridLayoutManager().getConstraintFor(point);

			if(point.x > 0  && point.y > 0 && getGridLayoutManager().canAddFigure(newPoint)){
				Rectangle rectFeedback = new Rectangle(newPoint,new Dimension(1,1));
				rectangles.add(rectFeedback);
			}	else {
				Point newGridPoint = getGridLayoutManager().getConstraintFor(point);
				InsertionPoint iPoint = getGridLayoutManager().getClosestInsertionPoint(newGridPoint, point);
				if(!iPoint.getOutBoundsPosition().equals(InsertionPoint.Position.UNDEFINE)){
					showInsertionFeedback = true ;
					showInsertionLineFeedback(request,iPoint,1,1) ;
				}
			}
		} else if (request instanceof ChangeBoundsRequest) {
			ChangeBoundsRequest changeBoundsRequest = (ChangeBoundsRequest) request;
			ListIterator<GraphicalEditPart> children = changeBoundsRequest.getEditParts().listIterator();
			int width = 1 ;
			int height = 1 ;
			while (children.hasNext()) {
				// get child
				GraphicalEditPart child = (GraphicalEditPart) children.next();
				/*show feedback only if it is in the grid*/
				Point point = ((ChangeBoundsRequest) request).getLocation().getCopy().translate(getLayoutOrigin().getNegated());
				FiguresHelper.translateToAbsolute(((IGraphicalEditPart) getHost()).getContentPane(), point);
				Point newGridPoint = getGridLayoutManager().getConstraintFor(point);

				width = ((Rectangle)getGridLayoutManager().getConstraint(child.getFigure())).width ;
				height = ((Rectangle)getGridLayoutManager().getConstraint(child.getFigure())).height ;

				if(point.x > 0  && point.y > 0 &&getGridLayoutManager().canAddFigure(newGridPoint, child.getFigure())){
					canAdd = true ;
					rectangles.add((Rectangle) getConstraintFor(changeBoundsRequest, child));
				}				
			}
			if(!canAdd){
				Point point = ((ChangeBoundsRequest) request).getLocation().getCopy().translate(getLayoutOrigin().getNegated());
				FiguresHelper.translateToAbsolute(((IGraphicalEditPart) getHost()).getContentPane(), point);
				Point newGridPoint = getGridLayoutManager().getConstraintFor(point);
				InsertionPoint iPoint = getGridLayoutManager().getClosestInsertionPoint(newGridPoint, point);
				if(!iPoint.getOutBoundsPosition().equals(InsertionPoint.Position.UNDEFINE)){
					showInsertionFeedback = true ;
					showInsertionLineFeedback(request,iPoint,width,height) ;
				}
			}
		}
		if(!rectangles.isEmpty()){
			drawFeedback(rectangles);
		}else {
			eraseRectanglesFeedback(request);
		}
		if(!showInsertionFeedback){
			eraseInsertionFeedback(request);
		}
	}

	protected void eraseInsertionFeedback(Request request) {
		if (insertionLine != null) {
			removeFeedback(insertionLine);
			insertionLine = null;
		}
		if(getFeedbackLayer().getChildren().contains(arrow)){
			removeFeedback(arrow) ;
			arrow = null ;
		}
	}

	protected void eraseRectanglesFeedback(Request request) {
		if (null != dragTargetFeedbackArray) {
			for (int i = 0; i < dragTargetFeedbackArray.length; i++) {
				removeFeedback(dragTargetFeedbackArray[i]);
			}
			dragTargetFeedbackArray = null;
			super.eraseTargetFeedback(request);
		}
	}


	/**
	 * 
	 * @param request
	 * @return a PositionConstant
	 */
	protected int getFeedbackDirectionFor(Request request, InsertionPoint instertionPoint) {		
		if(instertionPoint.getOutBoundsPosition()!=InsertionPoint.Position.UNDEFINE){
			if(instertionPoint.getOutBoundsPosition().equals(InsertionPoint.Position.UP)){
				return PositionConstants.NORTH ;
			}
			else if(instertionPoint.getOutBoundsPosition().equals(InsertionPoint.Position.LEFT)){
				return PositionConstants.WEST ;
			}
			else if(instertionPoint.getOutBoundsPosition().equals(InsertionPoint.Position.RIGHT)){
				return PositionConstants.EAST ;
			}
			else if(instertionPoint.getOutBoundsPosition().equals(InsertionPoint.Position.DOWN)){
				return PositionConstants.SOUTH ;
			}
		}
		return PositionConstants.SOUTH;	
	}


	protected void showInsertionLineFeedback(Request request, InsertionPoint instertionPoint, int width, int height) {
		if (getHost().getChildren().size() == 0)
			return;
		Polyline fb = getLineFeedback();

		//direction is a PositionConstant
		int direction = getFeedbackDirectionFor(request,instertionPoint);
		ImageFigure arrow = getArrowFeedback(direction) ;
		arrow.setSize(16, 16) ;

		if(direction == PositionConstants.NORTH || direction == PositionConstants.SOUTH){
			Point p1 = getGridLayoutManager().getGridField(new Rectangle(instertionPoint.getPoint(), new Dimension(width,height))).getTopLeft();
			p1.x = p1.x + getGridFigure().getMargin() ;
			p1.y = p1.y + getGridFigure().getMargin()  ;

			Point p2 = getGridLayoutManager().getGridField(new Rectangle(instertionPoint.getPoint(), new Dimension(width,height))).getTopRight() ;
			p2.x = p2.x + getGridFigure().getMargin()  ;
			p2.y = p2.y + getGridFigure().getMargin()  ;

			fb.setPoint(p1, 0);
			fb.setPoint(p2, 1);

			if(direction == PositionConstants.NORTH){
				arrow.setLocation(new Point(p1.x + ((p2.x - p1.x)/2)-8, p1.y -16)) ;
			}else{
				arrow.setLocation(new Point(p1.x + ((p2.x - p1.x)/2)-8, p1.y )) ;
			}


		}else{
			Point p1 = getGridLayoutManager().getGridField(new Rectangle(instertionPoint.getPoint(), new Dimension(width,height))).getTopLeft();
			p1.x = p1.x + getGridFigure().getMargin() ;
			p1.y = p1.y + getGridFigure().getMargin()  ;

			Point p2 = getGridLayoutManager().getGridField(new Rectangle(instertionPoint.getPoint(), new Dimension(width,height))).getBottomLeft() ;
			p2.x = p2.x + getGridFigure().getMargin()  ;
			p2.y = p2.y + getGridFigure().getMargin()  ;

			fb.setPoint(p1, 0);
			fb.setPoint(p2, 1);
			if(direction == PositionConstants.EAST){
				arrow.setLocation(new Point(p2.x ,p1.y + ((p2.y - p1.y)/2)-8)) ;
			}else if(direction == PositionConstants.WEST){
				arrow.setLocation(new Point(p2.x - 16,p1.y + ((p2.y - p1.y)/2)-8)) ;
			}
		}

	}


	/**
	 * Draw the shapes based on the list of shapes inside dragTargetFeedBackArray
	 * 
	 * @param rectangles
	 */
	protected void drawFeedback(List<Rectangle> rectangles) {
		IFigure f = getGridFigure();
		while (!(f instanceof ScalableFreeformLayeredPane)) {
			f = f.getParent();
		}

		GridLayoutManager layout = getGridLayoutManager();
		Shape shapes[] = getDragTargetFeedbackArray(rectangles.size());
		for (int i = 0; i < shapes.length; i++) {

			Rectangle rect = rectangles.get(i);

			Rectangle r = layout.getGridField(rect);
			r.translate(getLayoutOrigin());

			f.translateToParent(r);

			shapes[i].setBounds(r);
			if (getGridFigure().isDrawGrid()) {
				shapes[i].setForegroundColor(getGridFigure().getGridColor());
				shapes[i].setAlpha(50) ;
			}
		}	
	}

	@Override
	public void deactivate() {
		super.deactivate();
		dragTargetFeedbackArray = null;
	}


	/**
	 * Lazily creates and returns a <code>Polyline</code> Figure for use as
	 * feedback.
	 * 
	 * @return a Polyline figure
	 */
	protected Polyline getLineFeedback() {
		if (insertionLine == null) {
			insertionLine = new Polyline();
			insertionLine.setLineWidth(2);
			insertionLine.setForegroundColor(ColorConstants.red) ;
			insertionLine.addPoint(new Point(0, 0));
			insertionLine.addPoint(new Point(10, 10));
			addFeedback(insertionLine);
		}
		return insertionLine;
	}

	protected ImageFigure getArrowFeedback(int position) {
		if(getFeedbackLayer().getChildren().contains(arrow)){
			removeFeedback(arrow) ;
			arrow = null ;
		}

		switch (position) {
		case PositionConstants.EAST: 

			arrow = new ImageFigure(Pics.getImage(PicsConstants.formArrowRight)) ;
			addFeedback(arrow);

			return arrow;
		case PositionConstants.WEST: 

			arrow = new ImageFigure(Pics.getImage(PicsConstants.formArrowLeft)) ;
			addFeedback(arrow);

			return arrow;
		case PositionConstants.NORTH: 

			arrow = new ImageFigure(Pics.getImage(PicsConstants.formArrowTop)) ;
			addFeedback(arrow) ;

			return arrow;
		case PositionConstants.SOUTH: 

			arrow = new ImageFigure(Pics.getImage(PicsConstants.formArrowBottom)) ;
			addFeedback(arrow) ;

			return arrow;
		default:

			arrow = new ImageFigure(Pics.getImage(PicsConstants.formArrowBottom)) ;
			addFeedback(arrow) ;

			return  arrow;
		}

	}
}