/**
 * Copyright (C) 2009-2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.bonitasoft.studio.diagram.custom.editPolicies;

import org.bonitasoft.studio.common.gmf.ChangeBoundsRequestUtil;
import org.bonitasoft.studio.diagram.custom.parts.CustomSubprocessEventCompartmentEditPart;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.BoundaryEvent;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.StartErrorEvent;
import org.bonitasoft.studio.model.process.StartEvent;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.bonitasoft.studio.model.process.diagram.edit.parts.LaneEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolEditPart;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.draw2d.ui.graphics.ColorRegistry;
import org.eclipse.gmf.runtime.notation.FillStyle;
import org.eclipse.gmf.runtime.notation.LineStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

/**
 * @author Romain Bioteau
 *
 */
public class CustomDragDropEditPolicy extends DragDropEditPolicy {

    @Override
    protected Command getDropCommand(final ChangeBoundsRequest request) {
        if(dropNotAllowed(request)){
            return UnexecutableCommand.INSTANCE;
        }
        return super.getDropCommand(request);
    }

    private boolean dropNotAllowed(final ChangeBoundsRequest request) {
        return getHost() instanceof LaneEditPart
                || getHost() instanceof PoolEditPart
                || isAnIllegalMove(request)
                || isTargetaCollapseSubprocess(request)
                || isSourceAndTargetAreEventSubProc(request)
                || isSourceFromExpandSubprocessAndSourceAlreadyConnected(request)
                || getHost().getParent() != null && getHost().getParent() instanceof MainProcessEditPart
                || isInvalidBoundaryMove(request)
                || !ChangeBoundsRequestUtil.isMovingToAnotherProcess(getHost(), request)
                || isALabelEditPart(request);
    }

    private boolean isALabelEditPart(final ChangeBoundsRequest request) {
        for (final Object selectedEditPart : request.getEditParts()) {
            if (selectedEditPart instanceof LabelEditPart) {
                return true;
            }
        }
        return false;
    }

    private boolean isSourceAndTargetAreEventSubProc(final ChangeBoundsRequest request) {
        final EditPartViewer hostViewer = getHost().getViewer();
        if(hostViewer.findObjectAt(request.getLocation()) instanceof IGraphicalEditPart){
            final IGraphicalEditPart target = (IGraphicalEditPart) hostViewer.findObjectAt(request.getLocation());
            if(target.resolveSemanticElement() instanceof SubProcessEvent){
                for(final Object ep : request.getEditParts()){
                    if(ep instanceof IGraphicalEditPart){
                        if(((IGraphicalEditPart) ep).resolveSemanticElement() instanceof SubProcessEvent){
                            return true ;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isAnIllegalMove(final ChangeBoundsRequest request) {
        //STart event can't be moved to SubProcessEvent
        if(getHost().getViewer().findObjectAt(request.getLocation()) instanceof IGraphicalEditPart){
            final IGraphicalEditPart target = (IGraphicalEditPart) getHost().getViewer().findObjectAt(request.getLocation());
            if(target.resolveSemanticElement() instanceof SubProcessEvent){
                for(final Object ep : request.getEditParts()){
                    if(ep instanceof IGraphicalEditPart){
                        if(((IGraphicalEditPart) ep).resolveSemanticElement() instanceof StartEvent){
                            return true ;
                        }
                    }
                }
            }
        }
        //Start error event can't be put in Pool or lane
        for(final Object ep : request.getEditParts()){
            if(ep instanceof IGraphicalEditPart){
                if(((IGraphicalEditPart) ep).resolveSemanticElement() instanceof StartErrorEvent){
                    return true ;
                }else if(((IGraphicalEditPart) ep).resolveSemanticElement() instanceof Lane){
                    return true ;
                }
            }
        }


        //Pool can't be moved
        if(getHost() instanceof MainProcessEditPart){
            for(final Object ep : request.getEditParts()){
                if(ep instanceof IGraphicalEditPart){
                    if(!(((IGraphicalEditPart) ep).resolveSemanticElement() instanceof Pool)){
                        return true ;
                    }
                }
            }
        }

        return false;
    }

    private boolean isSourceFromExpandSubprocessAndSourceAlreadyConnected(final ChangeBoundsRequest request) {
        for(final Object ep : request.getEditParts()){
            if(ep instanceof IGraphicalEditPart){
                if(((IGraphicalEditPart) ep).getParent() instanceof CustomSubprocessEventCompartmentEditPart){
                    if(!((IGraphicalEditPart) ep).getTargetConnections().isEmpty()){
                        for(final Object c : ((IGraphicalEditPart) ep).getTargetConnections()){
                            if(!request.getEditParts().contains(((ConnectionEditPart)c).getSource())){
                                return true ;
                            }
                        }
                    }
                    if(!((IGraphicalEditPart) ep).getSourceConnections().isEmpty()){
                        for(final Object c : ((IGraphicalEditPart) ep).getSourceConnections()){
                            if(!request.getEditParts().contains(((ConnectionEditPart)c).getTarget())){
                                return true ;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isTargetaCollapseSubprocess(final ChangeBoundsRequest request) {
        if(request.getLocation() == null){
            return false ;
        }
        final EditPart ep = getHost().getParent().getViewer().findObjectAt(request.getLocation()) ;
        return ep instanceof IGraphicalEditPart && ((IGraphicalEditPart) ep).resolveSemanticElement() instanceof SubProcessEvent && isCollapsed(ep);
    }

    private boolean isCollapsed(EditPart ep) {
        if(ep instanceof ShapeCompartmentEditPart){
            return !((ShapeCompartmentEditPart) ep).getCompartmentFigure().isExpanded() ;
        }else{
            if(ep instanceof ITextAwareEditPart){
                ep=ep.getParent() ;
            }
            for(final Object child : ep.getChildren()){
                if(child instanceof ShapeCompartmentEditPart){
                    return !((ShapeCompartmentEditPart) child).getCompartmentFigure().isExpanded() ;
                }
            }
        }
        return false;
    }

    /**
     * @param request
     * @return
     */
    private boolean isInvalidBoundaryMove(final ChangeBoundsRequest request) {
        for(final Object ep : request.getEditParts()){
            if(((IGraphicalEditPart)ep).resolveSemanticElement() instanceof BoundaryEvent){
                final EditPart objectAt = getHost().getViewer().findObjectAt(request.getLocation());
                if(objectAt instanceof IGraphicalEditPart){
                    final IGraphicalEditPart target = (IGraphicalEditPart) objectAt;
                    final EObject targetEObject = target.resolveSemanticElement();
                    return !(targetEObject instanceof Activity);
                }else{
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * @see org.eclipse.gef.EditPolicy#showTargetFeedback(org.eclipse.gef.Request)
     */

    @Override
    public void showTargetFeedback(final Request request) {
        if(((IGraphicalEditPart) getHost()).resolveSemanticElement() instanceof SubProcessEvent){
            final Command c = getCommand(request);
            final EditPart parent = getHost().getParent();
            final View parentNotationView = ((IGraphicalEditPart)parent).getNotationView();
            final IFigure parentHostFigure = getHostFigure().getParent();
            if (c != null && c.canExecute() && !isCollapsed(getHost())){
                if(!dropNotAllowed((ChangeBoundsRequest) request)) {
                    if(parent != null && parentNotationView != null &&  parentNotationView.getStyle(NotationPackage.eINSTANCE.getLineStyle()) != null){
                        final Color foreground = ColorRegistry.getInstance().getColor(((LineStyle) parentNotationView.getStyle(NotationPackage.eINSTANCE.getLineStyle())).getLineColor()) ;
                        final Color background = ColorRegistry.getInstance().getColor(((FillStyle) parentNotationView.getStyle(NotationPackage.eINSTANCE.getFillStyle())).getFillColor()) ;
                        parentHostFigure.setForegroundColor(FigureUtilities.lighter(foreground)) ;
                        parentHostFigure.setBackgroundColor(FigureUtilities.lighter(background)) ;
                    }
                } else {
                    if(parent != null && parentNotationView != null &&  parentNotationView.getStyle(NotationPackage.eINSTANCE.getLineStyle()) != null){
                        final Color background = ColorRegistry.getInstance().getColor(((FillStyle) parentNotationView.getStyle(NotationPackage.eINSTANCE.getFillStyle())).getFillColor()) ;
                        parentHostFigure.setForegroundColor(ColorConstants.red) ;
                        parentHostFigure.setBackgroundColor(FigureUtilities.lighter(background)) ;
                    }
                }
            }
        }
        return; // DO NOTHING
    }

    @Override
    public void eraseTargetFeedback(final Request request) {
        if(((IGraphicalEditPart) getHost()).resolveSemanticElement() instanceof SubProcessEvent){
            if(getHost().getParent() != null && ((IGraphicalEditPart)getHost().getParent()).getNotationView() != null &&  ((IGraphicalEditPart)getHost().getParent()).getNotationView().getStyle(NotationPackage.eINSTANCE.getLineStyle()) != null){
                final Color foreground = ColorRegistry.getInstance().getColor(((LineStyle) ((IGraphicalEditPart)getHost().getParent()).getNotationView().getStyle(NotationPackage.eINSTANCE.getLineStyle())).getLineColor()) ;
                final Color background = ColorRegistry.getInstance().getColor(((FillStyle) ((IGraphicalEditPart)getHost().getParent()).getNotationView().getStyle(NotationPackage.eINSTANCE.getFillStyle())).getFillColor()) ;
                getHostFigure().getParent().setForegroundColor(foreground) ;
                getHostFigure().getParent().setBackgroundColor(background) ;
            }
        }
        return; // DO NOTHING
    }

    
    
}

