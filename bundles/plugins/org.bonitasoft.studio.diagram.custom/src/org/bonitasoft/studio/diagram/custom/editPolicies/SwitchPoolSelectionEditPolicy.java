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

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.diagram.custom.commands.SwitchPoolOrderCommand;
import org.bonitasoft.studio.diagram.custom.parts.CustomMainProcessEditPart;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.ZoomListener;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;

/**
 * @author Aurelien Pupier
 * @author Romain Bioteau - adaptation from AbstractCHangeSpanOnSelectionEditPolicy
 * This class show arrow to swith pool position
 */
public class SwitchPoolSelectionEditPolicy extends SelectionEditPolicy implements ZoomListener{

	public static final String SWITCH_POOL_SELECTION_FEEDBACK_ROLE = "switchPoolselectionFeedback"; //$NON-NLS-1$

	/*The list of figure that catch mouse event to launch the command to change the span*/
	protected List<IFigure> figures = new ArrayList<IFigure>();

	public static final String ADD_LEFT = "addLeft"; //$NON-NLS-1$
	public static final String ADD_RIGHT = "addRight"; //$NON-NLS-1$
	public static final String ADD_TOP = "addTop"; //$NON-NLS-1$
	public static final String ADD_BOTTOM = "addBottom"; //$NON-NLS-1$
	public static final String REMOVE_LEFT = "removeLeft"; //$NON-NLS-1$
	public static final String REMOVE_RIGHT = "removeRight"; //$NON-NLS-1$
	public static final String REMOVE_TOP = "removeTop"; //$NON-NLS-1$
	public static final String REMOVE_BOTTOM = "removeBottom"; //$NON-NLS-1$

	/* The map used to know which place are taken by a widget*/
	protected List<List<Boolean>> map = new ArrayList<List<Boolean>>();

	/* The handler layer on which the figures are draw*/
	protected IFigure layer = null;

	private FigureListener figureListener;

	private IFigure sourceFigure;

	/**
	 * same as focus of SelectionEditPolicy but accessible...
	 */
	private boolean hasFocus;

	private int state;

	private ZoomManager zoomManager;

	/**
	 * 
	 */
	public SwitchPoolSelectionEditPolicy() {
		figureListener = new FigureListener() {

			public void figureMoved(IFigure source) {
				hideSelection();
				if(hasFocus || state == EditPart.SELECTED || state == EditPart.SELECTED_PRIMARY){
					showSelection();
				}
			}
		};
	}

	@Override
	public void activate() {
		super.activate();
		this.zoomManager = ((DiagramRootEditPart) getHost().getRoot()).getZoomManager();
		zoomManager.addZoomListener(this) ;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.SelectionEditPolicy#hideSelection()
	 */
	@Override
	protected void hideSelection() {
		if (layer != null) {
			for (IFigure fig : figures) {
				// fig.erase();
				fig.removeFigureListener(figureListener);
				layer.remove(fig);
			}
			figures.clear();
			layer.repaint();
		}
		layer = null;
		map.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.SelectionEditPolicy#showSelection()
	 */
	@Override
	protected void showSelection() {
		if(getHost().getParent() instanceof CustomMainProcessEditPart){
			if (sourceFigure == null) {
				sourceFigure = getHostFigure();
				sourceFigure.addFigureListener(figureListener);
			}

			hideSelection();
			layer = getLayer(LayerConstants.HANDLE_LAYER);
			if(layer != null){
				/*
				 * need to flush in order to have the new correct size of the host
				 * figure
				 */
				((IGraphicalEditPart) getHost()).getViewer().flush();
				Rectangle ref = getHostFigure().getBounds();

				if (ref.x == 0 && ref.y == 0 && ref.height == 0 && ref.width == 0) {
					return;
				}

				if(zoomManager.getZoom() > GMFTools.MINIMAL_ZOOM_DISPLAY){
					showSelectionForAddBottom();
					showSelectionForAddTop();
				}
			}
		}
	}

	/**
	 * 
	 */
	private void showSelectionForAddBottom() {
		if (!isOnBottom()) {

			IFigure f = new ImageFigure(Pics.getImage(PicsConstants.arrowDown));
			f.setSize(20, 20);

			sourceFigure = getHostFigure() ;

			Rectangle bounds = sourceFigure.getBounds().getCopy();
			//get the absolute coordinate of bounds
			sourceFigure.translateToAbsolute(bounds);
			IFigure parentFigure = sourceFigure.getParent();
			while( parentFigure != null  ) {
				if(parentFigure instanceof Viewport) {
					Viewport viewport = (Viewport)parentFigure;
					bounds.translate(
							viewport.getHorizontalRangeModel().getValue(),
							viewport.getVerticalRangeModel().getValue());
					parentFigure = parentFigure.getParent();
				}
				else {
					parentFigure = parentFigure.getParent();
				}
			}

			f.setLocation(new Point(bounds.getBottomLeft().x,bounds.getBottom().y));

			f.addMouseListener(new MouseListenerForSpan(AbstractSwitchLaneSelectionEditPolicy.ADD_BOTTOM));
			layer.add(f);
			figures.add(f);
		}
	}

	private boolean isOnBottom() {
		CustomMainProcessEditPart mainProcEp = (CustomMainProcessEditPart) getHost().getParent();
		return mainProcEp.getChildren().indexOf(getHost()) == mainProcEp.getChildren().size()-1 ;
	}

	private boolean isOnTop() {
		CustomMainProcessEditPart mainProcEp = (CustomMainProcessEditPart) getHost().getParent();
		return mainProcEp.getChildren().indexOf(getHost()) == 0;
	}

	/**
	 * 
	 */
	private void showSelectionForAddTop() {
		if (!isOnTop()) {

			IFigure f = new ImageFigure(Pics.getImage(PicsConstants.arrowUp));
			f.setParent(getHostFigure());
			f.setSize(20, 20);



			sourceFigure = getHostFigure() ;

			Rectangle bounds = sourceFigure.getBounds().getCopy();
			//get the absolute coordinate of bounds
			sourceFigure.translateToAbsolute(bounds);
			IFigure parentFigure = sourceFigure.getParent();
			while( parentFigure != null  ) {
				if(parentFigure instanceof Viewport) {
					Viewport viewport = (Viewport)parentFigure;
					bounds.translate(
							viewport.getHorizontalRangeModel().getValue(),
							viewport.getVerticalRangeModel().getValue());
					parentFigure = parentFigure.getParent();
				}
				else {
					parentFigure = parentFigure.getParent();
				}
			}

			f.setLocation(new Point(bounds.getTopLeft().x,bounds.getTop().y-20));
			f.addMouseListener(new MouseListenerForSpan(AbstractSwitchLaneSelectionEditPolicy.ADD_TOP));
			layer.add(f);
			figures.add(f);
		}
	}

	protected void refresh() {
		hideSelection();
		showSelection();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.SelectionEditPolicy#deactivate()
	 */
	@Override
	public void deactivate() {
		super.deactivate();
		zoomManager.removeZoomListener(this) ;
		if(sourceFigure != null){
			sourceFigure.removeFigureListener(figureListener);
			sourceFigure = null;
		}
		layer = null;
		figures.clear();	
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.SelectionEditPolicy#setFocus(boolean)
	 */
	@Override
	protected void setFocus(boolean value) {
		hasFocus = value;
		super.setFocus(value);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.SelectionEditPolicy#setSelectedState(int)
	 */
	@Override
	protected void setSelectedState(int type) {
		state = type;
		super.setSelectedState(type);
	}

	private class MouseListenerForSpan extends MouseListener.Stub{

		private String type;

		/**
		 * 
		 */
		public MouseListenerForSpan(String type) {
			this.type = type;
		}

		public void mousePressed(MouseEvent me) {
			try {
				IUndoableOperation c = new SwitchPoolOrderCommand((IGraphicalEditPart) getHost(), type);
				OperationHistoryFactory.getOperationHistory().execute(c,null,null);
				me.consume();
				refresh();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

	public void zoomChanged(double zoom) {
		hideSelection();
	}
}
