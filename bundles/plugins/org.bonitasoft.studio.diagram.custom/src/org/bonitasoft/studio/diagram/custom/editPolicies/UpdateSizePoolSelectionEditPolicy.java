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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.diagram.custom.commands.UpdatePoolSizeCommand;
import org.bonitasoft.studio.diagram.custom.parts.CustomMainProcessEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomPoolCompartmentEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomPoolEditPart;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MessageFlowEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SequenceFlowEditPart;
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
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editparts.ZoomListener;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.jface.viewers.StructuredSelection;

/**
 * @author Romain Bioteau
 * @author Aurelien Pupier : integration of Evenemential subprocess
 */
public class UpdateSizePoolSelectionEditPolicy extends SelectionEditPolicy implements ZoomListener {

    public static final String UPDATE_POOL_SIZE_SELECTION_FEEDBACK_ROLE = "updatePoolSizeselectionFeedback"; //$NON-NLS-1$

    /*The list of figure that catch mouse event to launch the command to change the span*/
    protected Map<String,IFigure> figures = new HashMap<String,IFigure>();

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

    private final FigureListener figureListener;

    private IFigure sourceFigure;

    /**
     * same as focus of SelectionEditPolicy but accessible...
     */
    private boolean hasFocus;

    private int state;
    private ShapeNodeEditPart poolEditPart;

    private boolean haveLanes;

    private ZoomManager zoomManager;

    /**
     * 
     */
    public UpdateSizePoolSelectionEditPolicy() {
        figureListener = new FigureListener() {

            public void figureMoved(IFigure source) {
                hideSelection();
                if(hasFocus || state == EditPart.SELECTED || state == EditPart.SELECTED_PRIMARY){
                    showSelection();
                }
            }
        };
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editpolicies.SelectionEditPolicy#hideSelection()
     */
    @Override
    protected void hideSelection() {
        if (layer != null) {
            for (IFigure fig : figures.values()) {
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
        if(!(((IGraphicalEditPart) getHost()).resolveSemanticElement() instanceof Pool)){
            return ;
        }
        if(getHost() instanceof ITextAwareEditPart || getHost() instanceof ShapeCompartmentEditPart) {
            return ;
        }

        poolEditPart = (ShapeNodeEditPart) getHost() ;

        haveLanes = false ;
        for(Object o : poolEditPart.getChildren()){
            if(o instanceof CustomPoolCompartmentEditPart){
                if(!((CustomPoolCompartmentEditPart)o).getPoolLanes().isEmpty()){
                    haveLanes = true ;
                    break ;
                }
            } 
        }


        if (sourceFigure == null) {
            sourceFigure = poolEditPart.getFigure();
            sourceFigure.addFigureListener(figureListener);
        }


        hideSelection();
        layer = getLayer(LayerConstants.HANDLE_LAYER);
        Rectangle ref = sourceFigure.getBounds();

        if (ref.x == 0 && ref.y == 0 && ref.height == 0 && ref.width == 0) {
            return;
        }


        if(zoomManager.getZoom() > GMFTools.MINIMAL_ZOOM_DISPLAY){

            showSelectionForAddRight(zoomManager.getZoom());
            showSelectionForRemoveRight(zoomManager.getZoom());

            if(!haveLanes){
                showSelectionForAddBottom(zoomManager.getZoom());
                showSelectionForRemoveBottom(zoomManager.getZoom());
            }
        }
    }


    private ShapeNodeEditPart findPoolEditPart(EditPart host) {
        EditPart result = host ;

        if(host instanceof SequenceFlowEditPart){
            result = ((AbstractConnectionEditPart) host).getSource();
        }

        if(host instanceof MessageFlowEditPart){
            result = ((AbstractConnectionEditPart) host).getSource();
        }

        /*first search for a CustomLaneEditPart*/
        while(!(result instanceof CustomPoolEditPart) && result != null){
            result = result.getParent() ;
        }

        if(result == null){
            return null ;
        }

        return (ShapeNodeEditPart) result;
    }

    private void showSelectionForAddRight(double zoom) {
        if(sourceFigure == null){
            if(poolEditPart == null){
                poolEditPart = findPoolEditPart(getHost());
                if(poolEditPart == null) {
                    return ;
                }
            }
            sourceFigure = poolEditPart.getFigure() ;
        }
        Rectangle ref = sourceFigure.getBounds();
        IFigure f = new ImageFigure(Pics.getImage(PicsConstants.plusBlack));
        f.setSize(20, 20);
        f.setLocation(ref.getRight().getCopy().translate(10, -20));
        f.getBounds().performScale(zoom) ;
        f.addMouseListener(new MouseListenerForSpan(UpdateSizePoolSelectionEditPolicy.ADD_RIGHT));
        layer.add(f);
        figures.put(UpdateSizePoolSelectionEditPolicy.ADD_RIGHT,f);

    }


    private void showSelectionForAddBottom(double zoom) {
        if(sourceFigure == null){
            if(poolEditPart == null){
                poolEditPart = findPoolEditPart(getHost());
                if(poolEditPart == null) {
                    return ;
                }
            }
            sourceFigure = poolEditPart.getFigure() ;
        }
        Rectangle ref = sourceFigure.getBounds();
        IFigure f = new ImageFigure(Pics.getImage(PicsConstants.plusBlack));
        f.setSize(20, 20);
        f.setLocation(ref.getBottom().getCopy().translate(20, 0));
        f.addMouseListener(new MouseListenerForSpan(UpdateSizePoolSelectionEditPolicy.ADD_BOTTOM));
        f.getBounds().performScale(zoom);
        layer.add(f);
        figures.put(UpdateSizePoolSelectionEditPolicy.ADD_BOTTOM,f);

    }

    private void showSelectionForRemoveBottom(double zoom) {
        if(sourceFigure == null){
            if(poolEditPart == null){
                poolEditPart = findPoolEditPart(getHost());
                if(poolEditPart == null) {
                    return ;
                }
            }
            sourceFigure = poolEditPart.getFigure() ;
        }
        Rectangle ref = sourceFigure.getBounds();
        IFigure f = new ImageFigure(Pics.getImage(PicsConstants.minusBlack));
        f.setSize(20, 20);
        f.setLocation(ref.getBottom().getCopy().translate(20, -20));
        f.getBounds().performScale(zoom);
        f.addMouseListener(new MouseListenerForSpan(UpdateSizePoolSelectionEditPolicy.REMOVE_BOTTOM));
        layer.add(f);
        figures.put(UpdateSizePoolSelectionEditPolicy.REMOVE_BOTTOM,f);

    }

    /**
     * 
     */
    private void showSelectionForRemoveRight(double zoom) {
        if(sourceFigure == null){
            if(poolEditPart == null){
                poolEditPart = findPoolEditPart(getHost());
                if(poolEditPart == null) {
                    return ;
                }
            }
            sourceFigure = poolEditPart.getFigure() ;
        }
        Rectangle ref = sourceFigure.getBounds();
        IFigure f = new ImageFigure(Pics.getImage(PicsConstants.minusBlack));
        f.setSize(20, 20);
        f.setLocation(ref.getLeft().getCopy().translate(-25, -10));

        f.addMouseListener(new MouseListenerForSpan(UpdateSizePoolSelectionEditPolicy.REMOVE_RIGHT));
        f.getBounds().performScale(zoom);
        layer.add(f);
        figures.put(UpdateSizePoolSelectionEditPolicy.REMOVE_RIGHT,f);

    }


    protected void refresh() {
        hideSelection();
        showSelection();
    }

    @Override
    public void activate() {
        super.activate();
        zoomManager = ((DiagramRootEditPart) getHost().getRoot()).getZoomManager();
        zoomManager.addZoomListener(this) ;
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

        private final String type;

        /**
         * 
         */
        public MouseListenerForSpan(String type) {
            this.type = type;
        }

        @Override
        public void mousePressed(MouseEvent me) {
            try {

                IFigure  f = ((CustomMainProcessEditPart) poolEditPart.getParent()).getFigure() ;
                IFigure p = f ;
                while(!(p instanceof Viewport)){
                    p = p.getParent();
                }

                int y = ((Viewport)p).getVerticalRangeModel().getValue() ;
                int x = ((Viewport)p).getHorizontalRangeModel().getValue() ;


                IUndoableOperation c = new UpdatePoolSizeCommand(poolEditPart, type);
                OperationHistoryFactory.getOperationHistory().execute(c,null,null);
                me.consume();


                poolEditPart.getViewer().setSelection(new StructuredSelection(poolEditPart));
                refresh();
                poolEditPart.getViewer().setSelection(new StructuredSelection(getHost()));

                if(type.equals(ADD_RIGHT)){
                    ((Viewport)p).setHorizontalLocation(x+150);
                }

                ((Viewport)p).setVerticalLocation(y);


            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public IFigure getFigure(String figureId){
        return figures.get(figureId);
    }

    public void zoomChanged(double zoom) {
        hideSelection() ;
    }
}
