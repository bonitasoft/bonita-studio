/*
 * Copyright (c) 2007, Intalio Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
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
 * 
 * Contributors:
 *     Intalio Inc. - initial API and implementation
 *     BonitasSoft S.A - Vertical Adaptation + BOS Model
 *
 * Date         Author             Changes
 * Sep 11, 2007      Antoine Toulme     Created
 * Dec 17, 2010      Romain Bioteau     Modified for BOS Studio and Change Tool Direction
 */
package org.bonitasoft.studio.common.gmf.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.model.process.Container;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.internal.ui.rulers.GuideEditPart;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.tools.SimpleDragTracker;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.swt.graphics.Cursor;

/**
 * This class implements a tool to move all the pools' shapes
 * present on the right of the mouse location on the left or the right.
 *
 *
 * @author <a href="http://www.intalio.com">Intalio Inc.</a>
 * @author <a href="mailto:atoulme@intalio.com">Antoine Toulme</a>
 */
public class MultipleShapesVerticalMoveTool extends SimpleDragTracker {

	private static final Rectangle SINGLETON = new Rectangle();

	/**
	 * the guide figure to use for feedback
	 */
	private IFigure guideline;
	/**
	 * the initial position when the mouse was pressed
	 * for the first time.
	 */
	private Integer _initialPosition;

	private Integer _initPosNoZoom;

	/**
	 * the list of moving shapes that are concerned
	 * by the move.
	 */
	private List<IGraphicalEditPart> _movingShapes = Collections.emptyList();

	/**
	 * the list of subprocesses that will be resized by the tool as well
	 */
	private List<IGraphicalEditPart> _subProcesses = Collections.emptyList();

	/**
	 * the container edit part, only a pool, that we execute on.
	 */
	private IGraphicalEditPart _container;

	/**
	 * Default constructor, with no args as required
	 * by the palette.
	 */
	public MultipleShapesVerticalMoveTool() {
		guideline = new GuideEditPart.GuideLineFigure();
		guideline.setVisible(false);
	}

	/**
	 * Erases the guide and the feedback shown by the container
	 * of the elements.
	 */
	protected void eraseSourceFeedback() {
		if (guideline.getParent() != null) {
			guideline.getParent().remove(guideline);
		}


		if (_container != null) {
			_container.eraseSourceFeedback(getSourceRequest());
		}

		ChangeBoundsRequest request = 
			new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
		request.setEditParts(Collections.emptyList());
		request.setSizeDelta(new Dimension(0, 0));
		request.setMoveDelta(new Point(0, 0));
		for (IGraphicalEditPart part : _movingShapes) {
			part.eraseSourceFeedback(request);
		}

		ChangeBoundsRequest spRequest = new ChangeBoundsRequest(
				RequestConstants.REQ_RESIZE);
		spRequest.setEditParts(Collections.emptyList());
		spRequest.setSizeDelta(new Dimension(0, 0));
		spRequest.setMoveDelta(new Point(0, 0));
		for (IGraphicalEditPart sp : _subProcesses) {
			sp.eraseSourceFeedback(spRequest);
		}
	}

	/**
	 * creates the command to move the shapes left or right.
	 */
	protected Command getCommand() {
		if (_container == null) {
			return null;
		}
		CompoundCommand command = new CompoundCommand("Multiple Shape Move");
		TransactionalEditingDomain editingDomain = _container.getEditingDomain();

		Point moveDelta  = ((ChangeBoundsRequest) getSourceRequest()).getMoveDelta().getCopy();
		Dimension spSizeDelta = new Dimension(moveDelta.x, moveDelta.y);
		ZoomManager zoomManager = ((DiagramRootEditPart) 
				getCurrentViewer().getRootEditPart()).getZoomManager();
		spSizeDelta.scale(1/zoomManager.getZoom());
		command.add(_container.getCommand(getSourceRequest()));


		for (IGraphicalEditPart sp : _subProcesses) {
			Dimension spDim = sp.getFigure().getBounds().getSize().getCopy();
			spDim.expand(spSizeDelta);
			SetBoundsCommand setBounds = 
				new SetBoundsCommand(editingDomain,"MultipleShape Move", sp, spDim);
			command.add(new ICommandProxy(setBounds));
		}
		return command;
	}

	/**
	 * the command will move things around.
	 */
	protected String getCommandName() {
		return REQ_MOVE_CHILDREN;
	}

	/**
	 * @return the current location without the zoom.
	 */
	protected int getCurrentPositionZoomed() {
		Point pt = getLocation();
		((IGraphicalEditPart) ((IDiagramGraphicalViewer) 
				getCurrentViewer()).getRootEditPart().
				getChildren().get(0)).getFigure().translateToRelative(pt);
		return  pt.y;
	}

	/**
	 * @return the current position with a zoom multiplier
	 */
	protected int getCurrentPosition() {
		int position = getCurrentPositionZoomed();
		ZoomManager zoomManager = ((DiagramRootEditPart) 
				getCurrentViewer().getRootEditPart()).getZoomManager();
		if (zoomManager != null) {
			position = (int)Math.round(position * zoomManager.getZoom());
		}
		return position;
	}

	/**
	 * the name used to debug the tool.
	 */
	protected String getDebugName() {
		return "Multiple Shape Horizontal Move";  //$NON-NLS-1$
	}

	/**
	 * if the drag is permitted, show the cursor.
	 * 
	 * Else if a drag is occuring show it too, or show the 
	 * no cursor.
	 */
	protected Cursor getDefaultCursor() {
		if (isMoveValid()) {
			return SharedCursors.SIZENS;
		} else {
			if (getState() == STATE_DRAG_IN_PROGRESS) {
				return SharedCursors.SIZENS;
			} 
			return SharedCursors.ARROW;
		}
	}

	/**
	 * The move is valid if the editor selection
	 * contains at least one pool.
	 * @return true if the move is valid
	 */
	private boolean isMoveValid() {
		if (getCurrentViewer() == null || getCurrentInput() == null) {
			return true;
		}
		Object obj = getCurrentViewer().findObjectAt(getCurrentInput().getMouseLocation());
		return true ;//obj instanceof IGraphicalEditPart && ((IGraphicalEditPart) obj).resolveSemanticElement() instanceof Graph;
	}

	/**
	 * Moved to public so that it may be called programmatically
	 * by an other tool.
	 */
	public boolean handleButtonDown(int button) {
		Object underMouse = getCurrentViewer().findObjectAt(getCurrentInput().getMouseLocation());
		if (!(underMouse instanceof IGraphicalEditPart)) {
			return true;
		}
		stateTransition(STATE_INITIAL, STATE_DRAG_IN_PROGRESS);
		_initialPosition = getCurrentPosition();
		_initPosNoZoom = getCurrentPositionZoomed();

		// calculate the initial selection
		// of shapes that should move
		_container = (IGraphicalEditPart) findPool(underMouse);

		// the children that will be moved around
		List<IGraphicalEditPart> bottomChildren = new ArrayList<IGraphicalEditPart>();
		List<IGraphicalEditPart> subProcesses = new ArrayList<IGraphicalEditPart>();

		if (_container != null && _container.resolveSemanticElement() instanceof Container) {
			List children  = null;
			if (_container.resolveSemanticElement() instanceof Container) {
				children = _container.getChildren();
			} else if (_container instanceof ShapeCompartmentEditPart) {
				children = ((ShapeCompartmentEditPart) _container).getChildren();
			}
			if (children == null) {
				throw new IllegalArgumentException("The part " + _container + " did not contain elements"); //$NON-NLS-1$ //$NON-NLS-2$
			}
			// now iterate over the compartment children
			// and take those that are on the right.
			for (Object child : children) {
				if (child instanceof ShapeNodeEditPart) {
					setBoundsForOverlapComputation((IGraphicalEditPart) child,SINGLETON) ;
					((DiagramEditPart) getCurrentViewer().getContents()).getFigure().translateToRelative(SINGLETON);
					if (SINGLETON.y > _initPosNoZoom) {
						bottomChildren.add((IGraphicalEditPart) child);
					} 
				}
			}
		}
		_movingShapes = bottomChildren;
		_subProcesses = subProcesses;

		updateSourceRequest();
		showSourceFeedback();

		return true;
	}

	public void setBoundsForOverlapComputation(IGraphicalEditPart ep, Rectangle result) {
		if (ep.resolveSemanticElement() instanceof FlowElement) {
			IFigure fig = ((IGraphicalEditPart)ep).getContentPane() ;
			result.setBounds(fig.getBounds());
			fig.translateToAbsolute(result);
			return;
		} else {
			result.setBounds(ep.getFigure().getBounds());
			ep.getFigure().translateToAbsolute(result);
		}
	}

	/**
	 * Finds a part that represents a Graph, acting recursively.
	 * @param part
	 * @return
	 */
	private IGraphicalEditPart findPool(Object part) {
		if (part instanceof IGraphicalEditPart) {
			if (((IGraphicalEditPart) part) instanceof ShapeCompartmentEditPart) {
				return (IGraphicalEditPart) part;
			}
			return findPool(((IGraphicalEditPart) part).getParent());
		}
		return null;
	}

	protected boolean handleButtonUp(int button) {
		if (stateTransition(STATE_DRAG_IN_PROGRESS, STATE_TERMINAL)) {
			setCurrentCommand(getCommand());
			executeCurrentCommand();
		}

		eraseSourceFeedback();

		_movingShapes = Collections.EMPTY_LIST;
		_initialPosition = null;


		setState(STATE_INITIAL);

		_container = null;
		return true;
	}

	protected boolean movedPastThreshold() {
		return true;
	}

	/**
	 * Shows a nice guideline to show the move 
	 */
	protected void showSourceFeedback() {
		if (_container == null) {
			return;
		}
		if (guideline.getParent() == null) {
			addFeedback(guideline);
		}
		Rectangle bounds = Rectangle.SINGLETON.getCopy();
		bounds.y = getCurrentPositionZoomed();

		Rectangle containerBounds = _container.getFigure().getBounds().getCopy();



		_container.getFigure().translateToAbsolute(containerBounds);

		((DiagramEditPart) getCurrentViewer().getContents())
		.getFigure().translateToRelative(containerBounds);


		bounds.x = containerBounds.x ;
		bounds.height = 1;
		bounds.width = containerBounds.width ;

		ZoomManager zoomManager = ((DiagramRootEditPart) getCurrentViewer().getRootEditPart()).getZoomManager();
		bounds.performScale(zoomManager.getZoom()) ;
		
		guideline.setBounds(bounds);
		guideline.setVisible(getState() == STATE_DRAG_IN_PROGRESS);

		ChangeBoundsRequest request = 
			new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
		request.setMoveDelta(((ChangeBoundsRequest) getSourceRequest()).getMoveDelta());
		request.setSizeDelta(new Dimension(0, 0));
		request.setEditParts(_movingShapes);

		for (IGraphicalEditPart part : _movingShapes) {
			part.showSourceFeedback(request);
		}

		ChangeBoundsRequest spRequest = new ChangeBoundsRequest(
				RequestConstants.REQ_RESIZE);
		Point moveDelta  = ((ChangeBoundsRequest) getSourceRequest()).getMoveDelta().getCopy();
		Dimension spSizeDelta = new Dimension(moveDelta.x, moveDelta.y);
		spRequest.setSizeDelta(spSizeDelta);
		spRequest.setMoveDelta(new Point(0, 0));
		spRequest.setEditParts(_subProcesses);

		for (IGraphicalEditPart sp : _subProcesses) {
			sp.showSourceFeedback(spRequest);
		}
		((DiagramEditPart) getCurrentViewer().getContents()).getRoot().refresh();
	}

	/**
	 * Creates the initial request,
	 * overridden to use a ChangeBoundsRequest.
	 */
	@Override
	protected Request createSourceRequest() {
		ChangeBoundsRequest request = new ChangeBoundsRequest(getCommandName());
		request.setSizeDelta(new Dimension(0, 0));
		return request;
	}

	/**
	 * Creates the children move request
	 */
	@Override
	protected void updateSourceRequest() {
		super.updateSourceRequest();
		int moved = getCurrentPosition() - _initialPosition;
		((ChangeBoundsRequest) getSourceRequest()).
		setMoveDelta(new Point(0, moved));
		((ChangeBoundsRequest) getSourceRequest()).
		setEditParts(_movingShapes);
	}
	
	 @Override
	    protected void executeCurrentCommand() {
	    	FiguresHelper.AVOID_OVERLAP_ENABLE = false ;
	    	super.executeCurrentCommand();
	    	FiguresHelper.AVOID_OVERLAP_ENABLE = true ;
	    	CompoundCommand cc = new CompoundCommand("Check Overlap") ;
	    	for(IGraphicalEditPart ep : _movingShapes){
	    		 Location loc = (Location) ((Node) ep.getNotationView()).getLayoutConstraint() ;
				 Point oldLoc = new Point(loc.getX(), loc.getY()) ;
				 Point newLoc = FiguresHelper.handleCompartmentMargin(ep, loc.getX(), loc.getY(),(ep.resolveSemanticElement() instanceof SubProcessEvent)) ;
				 if((newLoc.x != 0 && newLoc.y != 0) && !newLoc.equals(oldLoc)){
					 cc.add(new ICommandProxy(new SetBoundsCommand(_container.getEditingDomain(), "Check Overlap", new EObjectAdapter(ep.getNotationView()),newLoc))) ;
				 }
	    	}
	    	executeCommand(cc) ;
	    }
}
