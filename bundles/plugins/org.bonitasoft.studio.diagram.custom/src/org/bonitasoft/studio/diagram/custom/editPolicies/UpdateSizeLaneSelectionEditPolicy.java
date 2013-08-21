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

import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.diagram.custom.commands.UpdatePoolSizeCommand;
import org.bonitasoft.studio.diagram.custom.parts.CustomLaneEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomMainProcessEditPart;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolEditPart;
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
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.ZoomListener;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.jface.viewers.StructuredSelection;

/**
 * @author Romain Bioteau
 */
public class UpdateSizeLaneSelectionEditPolicy extends SelectionEditPolicy implements ZoomListener{


	/*The list of figure that catch mouse event to launch the command to change the span*/
	protected List<IFigure> figures = new ArrayList<IFigure>();

	public static final String ADD_BOTTOM = "addBottom"; //$NON-NLS-1$
	public static final String REMOVE_BOTTOM = "removeBottom"; //$NON-NLS-1$

	public static final Object UPDATE_LANE_SIZE_SELECTION_FEEDBACK_ROLE = "updateLaneSizeselectionFeedback"; //$NON-NLS-1$

	/* The handler layer on which the figures are draw*/
	protected IFigure layer = null;

	private FigureListener figureListener;

	private IFigure sourceFigure;

	/**
	 * same as focus of SelectionEditPolicy but accessible...
	 */
	private boolean hasFocus;

	private int state;
	private ShapeNodeEditPart laneEditPart;

	private ZoomManager zoomManager;


	/**
	 * 
	 */
	public UpdateSizeLaneSelectionEditPolicy() {
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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.SelectionEditPolicy#showSelection()
	 */
	@Override
	protected void showSelection() {
		if(!(((IGraphicalEditPart) getHost()).resolveSemanticElement() instanceof Lane)){
			return ;
		}

		laneEditPart = (ShapeNodeEditPart) getHost() ;

		if (sourceFigure == null) {
			sourceFigure = laneEditPart.getFigure();
			sourceFigure.addFigureListener(figureListener);
		}


		hideSelection();
		layer = getLayer(LayerConstants.HANDLE_LAYER);
		/*
		 * need to flush in order to have the new correct size of the host
		 * figure
		 */
		//		poolEditPart.getViewer().flush();
		//FIXME
		Rectangle ref = sourceFigure.getBounds().getCopy();
		FiguresHelper.translateToAbsolute(sourceFigure, ref);
		if (ref.x == 0 && ref.y == 0 && ref.height == 0 && ref.width == 0) {
			return;
		}

		if(zoomManager.getZoom() > GMFTools.MINIMAL_ZOOM_DISPLAY){
			showSelectionForAddBottom();
			showSelectionForRemoveBottom();
			showSelectionForAddRight(zoomManager.getZoom());
			showSelectionForRemoveRight(zoomManager.getZoom());
		}

	}

	private void showSelectionForAddRight(double zoom) {
		IFigure poolFigure = getPoolEditPart().getFigure();
		Rectangle ref = poolFigure.getBounds();
		IFigure f = new ImageFigure(Pics.getImage(PicsConstants.plusBlack));
		f.setSize(20, 20);
		f.setLocation(ref.getRight().translate(10, -20));
		f.getBounds().performScale(zoom) ;
		f.addMouseListener(new MouseListenerForSpan(UpdateSizePoolSelectionEditPolicy.ADD_RIGHT));
		layer.add(f);
		figures.add(f);

	}

	 private IGraphicalEditPart getPoolEditPart() {
		EditPart ep = getHost();
		if(ep instanceof IGraphicalEditPart){
			IGraphicalEditPart pool = (IGraphicalEditPart) ep;
			while (pool != null && !(pool instanceof PoolEditPart)) {
				pool = (IGraphicalEditPart) pool.getParent();
			}
			if(pool != null){
				return pool;
			}
		}
		return null;
	}


	private void showSelectionForRemoveRight(double zoom) {
	        IFigure poolFigure = getPoolEditPart().getFigure();
	        Rectangle ref = poolFigure.getBounds();
	        IFigure f = new ImageFigure(Pics.getImage(PicsConstants.minusBlack));
	        f.setSize(20, 20);
	        f.setLocation(ref.getLeft().translate(-25, -10));

	        f.addMouseListener(new MouseListenerForSpan(UpdateSizePoolSelectionEditPolicy.REMOVE_RIGHT));
	        f.getBounds().performScale(zoom);
	        layer.add(f);
	        figures.add(f);
	    }
	
	private ShapeNodeEditPart findLaneEditPart(EditPart host) {
		EditPart result = host ;
		EditPart tempEditPart = result;
		/*first search for a CustomLaneEditPart*/
		while(!(result instanceof CustomLaneEditPart) && result != null){
			result = result.getParent() ;
		}

		if(result == null){
			return null ;
		}

		return (ShapeNodeEditPart) result;
	}

	private void showSelectionForAddBottom() {
		if(sourceFigure == null){
			if(laneEditPart == null){
				laneEditPart = findLaneEditPart(getHost());
				if(laneEditPart == null)
					return ;
			}
			sourceFigure = laneEditPart.getFigure() ;
		}
		Rectangle ref = sourceFigure.getBounds().getCopy();
		FiguresHelper.translateToAbsolute(sourceFigure, ref);
		IFigure f = new ImageFigure(Pics.getImage(PicsConstants.plusBlack));
		f.setSize(20, 20);
		f.setLocation(ref.getBottom().translate(0, 1));
		f.addMouseListener(new MouseListenerForSpan(UpdateSizeLaneSelectionEditPolicy.ADD_BOTTOM));
		layer.add(f);
		figures.add(f);

	}

	private void showSelectionForRemoveBottom() {
		if(sourceFigure == null){
			if(laneEditPart == null){
				laneEditPart = findLaneEditPart(getHost());
				if(laneEditPart == null)
					return ;
			}
			sourceFigure = laneEditPart.getFigure() ;
		}
		Rectangle ref = sourceFigure.getBounds().getCopy();
		FiguresHelper.translateToAbsolute(sourceFigure, ref);
		IFigure f = new ImageFigure(Pics.getImage(PicsConstants.minusBlack));
		f.setSize(20, 20);
		f.setLocation(ref.getBottom().translate(0, -20));
		f.addMouseListener(new MouseListenerForSpan(UpdateSizeLaneSelectionEditPolicy.REMOVE_BOTTOM));
		layer.add(f);
		figures.add(f);

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

				IFigure  f = ((CustomMainProcessEditPart) laneEditPart.getParent().getParent().getParent()).getFigure() ;
				IFigure p = f ;
				while(!(p instanceof Viewport)){
					p = p.getParent();
				}

				int y = ((Viewport)p).getVerticalRangeModel().getValue() ;

				IGraphicalEditPart targetEp = laneEditPart;
				if(type.equals(UpdateSizePoolSelectionEditPolicy.ADD_RIGHT)||type.equals(UpdateSizePoolSelectionEditPolicy.REMOVE_RIGHT)){
					targetEp = getPoolEditPart();
				}
				
				IUndoableOperation c = new UpdatePoolSizeCommand(targetEp, type);
				OperationHistoryFactory.getOperationHistory().execute(c,null,null);
				me.consume();
				laneEditPart.getViewer().setSelection(new StructuredSelection(targetEp));
				refresh();
				laneEditPart.getViewer().setSelection(new StructuredSelection(getHost()));
				((Viewport)p).setVerticalLocation(y);


			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

	public void zoomChanged(double zoom) {
		hideSelection() ;
	}
}
