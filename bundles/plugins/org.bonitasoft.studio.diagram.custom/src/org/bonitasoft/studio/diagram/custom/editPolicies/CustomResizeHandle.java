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

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.AbstractHandle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gef.handles.RelativeHandleLocator;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.tools.ResizeTracker;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;

/**
 * @author Romain Bioteau
 *
 */
public class CustomResizeHandle extends AbstractHandle {

	/**
	 * The default size for square handles.
	 */
	protected static final int DEFAULT_HANDLE_SIZE = 12;
	private int cursorDirection;
	private boolean isContrained = true ;

	{
		init();
	}

	/**
	 * Null constructor
	 */
	public CustomResizeHandle() {
	}

	public CustomResizeHandle(GraphicalEditPart owner, int direction) {
		setOwner(owner);

		RelativeHandleLocator locator = new RelativeHandleLocator(owner.getFigure(), direction){
			protected Rectangle getReferenceBox() {
				IFigure f = getReferenceFigure();
				if (f instanceof HandleBounds){
					Rectangle r = ((HandleBounds) f).getHandleBounds() ;
					return r;
				}
				return super.getReferenceBox();
			}
		};

		setLocator(locator);
		setCursor(Cursors.getDirectionalCursor(direction, owner.getFigure()
				.isMirrored()));
		cursorDirection = direction;
	}

	/**
	 * Creates a SquareHandle for the given <code>GraphicalEditPart</code> with
	 * the given <code>Locator</code>.
	 * 
	 * @param owner
	 *            the owner
	 * @param loc
	 *            the locator
	 */
	public CustomResizeHandle(GraphicalEditPart owner, Locator loc) {
		super(owner, loc);
	}

	/**
	 * Creates a SquareHandle for the given <code>GraphicalEditPart</code> with
	 * the given <code>Cursor</code> using the given <code>Locator</code>.
	 * 
	 * @param owner
	 *            The editpart which provided this handle
	 * @param loc
	 *            The locator to position the handle
	 * @param c
	 *            The cursor to display when the mouse is over the handle
	 */
	public CustomResizeHandle(GraphicalEditPart owner, Locator loc, Cursor c) {
		super(owner, loc, c);
	}

	public CustomResizeHandle(GraphicalEditPart host, int direction, boolean isContrained) {
		this(host,direction)  ;
		this.isContrained = isContrained ;
	}

	/**
	 * Returns the color for the outside of the handle.
	 * 
	 * @return the color for the border
	 */
	protected Color getBorderColor() {
		return  ColorConstants.black;
	}

	/**
	 * Returns the color for the inside of the handle.
	 * 
	 * @return the color of the handle
	 */
	protected Color getFillColor() {
		return ColorConstants.white;
	}

	/**
	 * Initializes the handle.
	 */
	protected void init() {
		setPreferredSize(new Dimension(DEFAULT_HANDLE_SIZE, DEFAULT_HANDLE_SIZE));
	}

	/**
	 * Returns <code>true</code> if the handle's owner is the primary selection.
	 * 
	 * @return <code>true</code> if the handles owner has primary selection.
	 */
	protected boolean isPrimary() {
		return getOwner().getSelected() == EditPart.SELECTED_PRIMARY;
	}

	/**
	 * Draws the handle with fill color and outline color dependent on the
	 * primary selection status of the owner editpart.
	 * 
	 * @param g
	 *            The graphics used to paint the figure.
	 */
	public void paintFigure(Graphics g) {
		Rectangle r = getBounds() ;
		switch (cursorDirection) {
		case PositionConstants.SOUTH_EAST:
			g.drawImage(Pics.getImage("resize_SE.gif"),r.getLocation()) ;
			break;
		case PositionConstants.NORTH:
			g.drawImage(Pics.getImage("resize_N.gif"),r.getLocation().translate(0, -2)) ;
			break;
		case PositionConstants.SOUTH:
			g.drawImage(Pics.getImage("resize_S.gif"),r.getLocation().translate(0, 5)) ;
			break;
		case PositionConstants.WEST:
			g.drawImage(Pics.getImage("resize_W.gif"),r.getLocation().translate(-2, 0)) ;
			break;
		case PositionConstants.EAST:
			g.drawImage(Pics.getImage("resize_E.gif"),r.getLocation().translate(5, 0)) ;
			break;
		default:
			break;
		}


	}

	@Override
	protected DragTracker createDragTracker() {
		return new ResizeTracker(getOwner(), cursorDirection){
			@Override
			protected void updateSourceRequest() {
				ChangeBoundsRequest request = (ChangeBoundsRequest) getSourceRequest();
				Dimension d = getDragMoveDelta();

				Point location = new Point(getLocation());
				Point moveDelta = new Point(0, 0);
				Dimension resizeDelta = new Dimension(0, 0);

				if (getOwner() != null) {

					if(isContrained){
						request.setConstrainedResize(true);
						int origHeight = getOwner().getFigure().getBounds().height;
						int origWidth = getOwner().getFigure().getBounds().width;
						float ratio = 1;

						if (origWidth != 0 && origHeight != 0)
							ratio = ((float) origHeight / (float) origWidth);

						if (getResizeDirection() == PositionConstants.SOUTH_EAST) {
							if (d.height > (d.width * ratio))
								d.width = (int) (d.height / ratio);
							else
								d.height = (int) (d.width * ratio);
						} else if (getResizeDirection() == PositionConstants.NORTH_WEST) {
							if (d.height < (d.width * ratio))
								d.width = (int) (d.height / ratio);
							else
								d.height = (int) (d.width * ratio);
						} else if (getResizeDirection() == PositionConstants.NORTH_EAST) {
							if (-(d.height) > (d.width * ratio))
								d.width = -(int) (d.height / ratio);
							else
								d.height = -(int) (d.width * ratio);
						} else if (getResizeDirection() == PositionConstants.SOUTH_WEST) {
							if (-(d.height) < (d.width * ratio))
								d.width = -(int) (d.height / ratio);
							else
								d.height = -(int) (d.width * ratio);
						}
					}
				}else{
					request.setConstrainedResize(false);
				}

				request.setCenteredResize(getCurrentInput().isModKeyDown(SWT.MOD1));

				if ((getResizeDirection() & PositionConstants.NORTH) != 0) {
					if (getCurrentInput().isControlKeyDown()) {
						resizeDelta.height -= d.height;
					}
					moveDelta.y += d.height;
					resizeDelta.height -= d.height;
				}
				if ((getResizeDirection() & PositionConstants.SOUTH) != 0) {
					if (getCurrentInput().isControlKeyDown()) {
						moveDelta.y -= d.height;
						resizeDelta.height += d.height;
					}
					resizeDelta.height += d.height;
				}
				if ((getResizeDirection() & PositionConstants.WEST) != 0) {
					if (getCurrentInput().isControlKeyDown()) {
						resizeDelta.width -= d.width;
					}
					moveDelta.x += d.width;
					resizeDelta.width -= d.width;
				}
				if ((getResizeDirection() & PositionConstants.EAST) != 0) {
					if (getCurrentInput().isControlKeyDown()) {
						moveDelta.x -= d.width;
						resizeDelta.width += d.width;
					}
					resizeDelta.width += d.width;
				}

				request.setMoveDelta(moveDelta);
				request.setSizeDelta(resizeDelta);
				request.setLocation(location);
				request.setEditParts(getOperationSet());

				request.getExtendedData().clear();
			}

			@Override
			protected List<IGraphicalEditPart> createOperationSet() {
				ArrayList<IGraphicalEditPart> res = new ArrayList<IGraphicalEditPart>();
				for (Object selection : getCurrentViewer().getSelectedEditParts()) {
					if (isResizable((IGraphicalEditPart)selection)) {
						res.add((IGraphicalEditPart)selection);
					}
				}
				return res;
			}
		};
	}

	/**
	 * @param selection
	 * @return
	 */
	protected boolean isResizable(IGraphicalEditPart selection) {
		return selection != null && (selection.resolveSemanticElement() instanceof Activity || selection.resolveSemanticElement() instanceof SubProcessEvent);
	}


}
