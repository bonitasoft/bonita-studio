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

import java.util.List;

import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessElementTypes;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.DeferredCreateConnectionViewAndElementCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.commands.SetConnectionBendpointsCommand;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest.ConnectionViewAndElementDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateUnspecifiedTypeRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest.ViewAndElementDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.tools.CreationTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Connector;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;

/**
 * @author Mickael Istria
 * @author Aurelien Pupier : improve resource management (free handles)
 */
public class DraggableElementCreationTool extends CreationTool implements DragTracker {

    private final DraggableElement draggableElement;
    private final IFigure figure;
    private final UnspecifiedTypeCreationTool tool;

    // compartment feedback
    private boolean dragged;

    private PrecisionRectangle sourceRectangle, compoundSrcRect;
    private SnapToHelper helper;
    private Cursor cursor;
    private Image image;
    private final ZoomManager zoomManager;

    /**
     * @param toolEntry The tool entry to create the node
     * @param draggableElement
     */
    public DraggableElementCreationTool(UnspecifiedTypeCreationTool tool, DraggableElement draggableElement, ZoomManager zoomManager) {
        this.tool = tool;
        this.draggableElement = draggableElement;
        this.zoomManager = zoomManager;
        this.figure = createImage();

        // Remove cursor
        //	initCursor();
        setDefaultCursor(Pics.getClosedHandCursor());
        setUnloadWhenFinished(true);
    }


    /**
     * @return
     */
    private IFigure createImage() {
        final CreateUnspecifiedTypeRequest createUnspecifiedTypeRequest = (CreateUnspecifiedTypeRequest) tool.createCreateRequest();
        final EClass eClass = ((IElementType) createUnspecifiedTypeRequest.getElementTypes().get(0)).getEClass();
        final IFigure svgFigure = FiguresHelper.getSelectedFigure(eClass, -1, -1, null, null);
        final Rectangle r = svgFigure.getBounds().getCopy();
        r.performScale(zoomManager.getZoom());
        svgFigure.setBounds(r);
        return svgFigure;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gef.tools.AbstractTool#getCommandName()
     */
    @Override
    protected String getCommandName() {
        return null;
    }

    @Override
    protected Request createTargetRequest() {
        return tool.createCreateRequest();
    }

    @Override
    protected boolean handleDrag() {
        dragged = true;
        if (getTargetEditPart() != null) {
            helper = (SnapToHelper) getTargetEditPart().getAdapter(SnapToHelper.class);
        }

        updateTargetUnderMouse();
        updateTargetRequest();
        redrawFeedback();

        return super.handleDrag();
    }

    @Override
    protected boolean handleMove() {
        super.handleMove();
        redrawFeedback();
        if (getTargetEditPart() != null) {
            helper = (SnapToHelper) getTargetEditPart().getAdapter(SnapToHelper.class);
        }
        return true;
    }

    @Override
    protected void updateTargetRequest() {
        ((CreateRequest) getTargetRequest()).setLocation(getLocation());
        final CreateRequest req = getCreateRequest();
        req.getExtendedData().clear();

        if (isInState(STATE_DRAG_IN_PROGRESS)) {
            snapPoint(req);
        } else {
            req.setLocation(getLocation());
            req.setSize(null);
        }

    }

    protected void snapPoint(CreateRequest request) {
        if (helper != null && figure != null) {
            final PrecisionRectangle baseRect = sourceRectangle.getPreciseCopy();
            final PrecisionRectangle jointRect = compoundSrcRect.getPreciseCopy();
            Point location = getLocation().getTranslated(-figure.getSize().width / 2, -figure.getSize().height / 2);
            final PrecisionPoint preciseDelta = new PrecisionPoint(location.preciseX(), location.preciseY());
            baseRect.translate(preciseDelta);
            jointRect.translate(preciseDelta);
            helper.snapPoint(request, PositionConstants.HORIZONTAL | PositionConstants.VERTICAL, new PrecisionRectangle[] {
                    baseRect, jointRect }, preciseDelta);
            request.setLocation(preciseDelta);
        }

    }

    @SuppressWarnings("deprecation")
    private void captureSourceDimensions() {
        if (figure == null)
            return;

        final PrecisionRectangle bounds = new PrecisionRectangle(figure.getBounds());
        bounds.performScale(zoomManager.getZoom());
        figure.translateToAbsolute(bounds);

        if (sourceRectangle == null) {
            if (figure instanceof HandleBounds)
                sourceRectangle = new PrecisionRectangle(
                        ((HandleBounds) figure).getHandleBounds());
            else
                sourceRectangle = new PrecisionRectangle(figure.getBounds());
            figure.translateToAbsolute(sourceRectangle);

        }

        if (compoundSrcRect == null)
            compoundSrcRect = new PrecisionRectangle(bounds);
        else
            compoundSrcRect = compoundSrcRect.union(bounds);
    }

    @Override
    protected void setState(int state) {
        captureSourceDimensions();
        super.setState(state);
    }

    private void redrawFeedback() {
        this.calculateCursor();
        final Command command = getCommand();

        if (command != null && command.canExecute()) {

            if (!draggableElement.getLayer().getChildren().contains(figure)) {
                draggableElement.getLayer().add(figure);
            }
            final IFigure parentFigure = draggableElement.getLayer();

            final Point location = ((CreateRequest) getTargetRequest()).getLocation();
            FiguresHelper.translateToAbsolute(parentFigure, location);

            showTargetCompartmentFeedback();
            Point translated = getLocation().getTranslated(-figure.getSize().width / 2, -figure.getSize().height / 2);
            FiguresHelper.translateToAbsolute(figure, translated);
            figure.setLocation(translated);
        } else {
            if (draggableElement.getLayer().getChildren().contains(figure)) {
                draggableElement.getLayer().remove(figure);
            }
        }
    }

    @Override
    protected boolean handleButtonDown(int button) {
        setCursor(Pics.getClosedHandCursor());
        if (stateTransition(STATE_INITIAL, STATE_DRAG)) {
            getCreateRequest().setLocation(getLocation());
            //lockTargetEditPart(getTargetEditPart());
            // Snap only when size on drop is employed
            if (getTargetEditPart() != null)
                helper = (SnapToHelper) getTargetEditPart().getAdapter(SnapToHelper.class);
        }
        return true;

    }

    @Override
    protected boolean handleButtonUp(int button) {

        if (stateTransition(STATE_DRAG | STATE_DRAG_IN_PROGRESS, STATE_TERMINAL)) {
            eraseTargetFeedback();
        }

        if (!dragged) {
            this.reactivate();
            return true;
        }
        createItem();

        setState(STATE_TERMINAL);
        handleFinished();

        return true;
    }


    private void createItem() {
        updateTargetUnderMouse();
        if (draggableElement.getLayer().getChildren().contains(figure)) {
            draggableElement.getLayer().remove(figure);
        }
        final GraphicalEditPart editPart = (GraphicalEditPart) getTargetEditPart();
        Point creationLocation = getLocation()
                .getTranslated(-figure.getSize().width / 2, -figure.getSize().height / 2);
        if (editPart != null) {
            getCreateRequest().setLocation(creationLocation);
            final Command command = getCommand();
            IAdaptable targetAdapter = null;
            if (command instanceof ICommandProxy) {
                editPart.getDiagramEditDomain().getDiagramCommandStack().execute(command);
                final ICommand iCommand = ((ICommandProxy) command).getICommand();
                final CommandResult commandResult = iCommand.getCommandResult();
                if (commandResult != null
                        && commandResult.getReturnValue() != null
                        && !((List<?>) commandResult.getReturnValue()).isEmpty())
                    targetAdapter = (IAdaptable) ((List<?>) commandResult.getReturnValue()).get(0);
            } else if (command instanceof CompoundCommand) {
                final CompoundCommand cmd = (CompoundCommand) command;
                editPart.getDiagramEditDomain().getDiagramCommandStack().execute(cmd);
                if (cmd != null) {
                    for (final Object c : cmd.getCommands()) {
                        if (c instanceof ICommandProxy) {
                            final CommandResult commandResult = ((ICommandProxy) c).getICommand().getCommandResult();
                            if (commandResult != null
                                    && commandResult.getReturnValue() != null
                                    && !((List<?>) commandResult.getReturnValue()).isEmpty())
                                targetAdapter = (IAdaptable) ((List<?>) commandResult.getReturnValue()).get(0);
                        }
                    }
                }
            }
            if (targetAdapter == null) {
                final CreateUnspecifiedTypeRequest nodeRequest = (CreateUnspecifiedTypeRequest) getTargetRequest();

                for (final Object item : nodeRequest.getElementTypes()) {
                    final IElementType type = (IElementType) item;
                    final CreateRequest subReq = nodeRequest.getRequestForType(type);
                    final List<?> newObject = (List<?>) subReq.getNewObject();
                    if (newObject != null &&
                            !newObject.isEmpty()) {
                        final IAdaptable adaptable = (IAdaptable) newObject.get(0);
                        if (adaptable.getAdapter(Node.class) != null) {
                            targetAdapter = adaptable;
                        }
                    }
                }
            }
            CreateConnectionViewAndElementRequest connectionRequest = null;
            if (targetAdapter instanceof ViewAndElementDescriptor) {
                //
                final String semanticHint = ((ViewAndElementDescriptor) targetAdapter).getSemanticHint();
                DeferredCreateConnectionViewAndElementCommand connectionCommand = null;
                if (!ProcessElementTypes.TextAnnotation_3015.getId().contains(semanticHint)) {
                    connectionRequest = new CreateConnectionViewAndElementRequest(ProcessElementTypes.SequenceFlow_4001,
                            ((IHintedType) ProcessElementTypes.SequenceFlow_4001).getSemanticHint(), getPreferencesHint());
                    connectionRequest.setLocation(creationLocation);
                    connectionCommand = new DeferredCreateConnectionViewAndElementCommand(connectionRequest, draggableElement.getHost(), targetAdapter,
                            editPart.getViewer());

                } else if (ProcessElementTypes.TextAnnotation_3015.getId().contains(semanticHint)) {
                    connectionRequest = new CreateConnectionViewAndElementRequest(ProcessElementTypes.TextAnnotationAttachment_4003,
                            ((IHintedType) ProcessElementTypes.TextAnnotationAttachment_4003).getSemanticHint(), getPreferencesHint());
                    connectionRequest.setLocation(creationLocation);
                    //need to inverse source and target for textAnnotation
                    connectionCommand = new DeferredCreateConnectionViewAndElementCommand(connectionRequest, targetAdapter, draggableElement.getHost(),
                            editPart.getViewer());
                }
                if (connectionCommand.canExecute()) {
                    editPart.getDiagramEditDomain().getDiagramCommandStack().execute(new ICommandProxy(connectionCommand));
                }
            }
            if (targetAdapter != null && targetAdapter instanceof ViewAndElementDescriptor) {
                final IGraphicalEditPart targetEditPart = (IGraphicalEditPart) editPart.getViewer().getEditPartRegistry()
                        .get(targetAdapter.getAdapter(View.class));
                final Location loc = (Location) ((Node) targetEditPart.getNotationView()).getLayoutConstraint();
                final Point newLoc = FiguresHelper.handleCompartmentMargin(targetEditPart, loc.getX(), loc.getY(),
                        (targetEditPart.resolveSemanticElement() instanceof SubProcessEvent));
                if (targetEditPart.getParent() instanceof ShapeCompartmentEditPart) {
                    final ShapeCompartmentEditPart compartment = (ShapeCompartmentEditPart) targetEditPart.getParent();
                    final Bounds parentBounds = (Bounds) ((Node) ((IGraphicalEditPart) targetEditPart.getParent().getParent()).getNotationView())
                            .getLayoutConstraint();
                    if (compartment.resolveSemanticElement() instanceof SubProcessEvent) {
                        newLoc.translate(-parentBounds.getX(), -parentBounds.getY());
                    }
                    while (newLoc.y + 65 > compartment.getFigure().getBounds().height) {
                        newLoc.y = newLoc.y - 10;
                    }
                    while (newLoc.x + 100 > compartment.getFigure().getBounds().width) {
                        newLoc.x = newLoc.x - 10;
                    }
                    if (compartment.resolveSemanticElement() instanceof SubProcessEvent) {
                        newLoc.translate(parentBounds.getX(), parentBounds.getY());
                    }
                }

                executeCommand(new ICommandProxy(new SetBoundsCommand(targetEditPart.getEditingDomain(), "Check Overlap",
                        new EObjectAdapter(targetEditPart.getNotationView()), newLoc)));

                if (connectionRequest != null) {
                    final ConnectionViewAndElementDescriptor connectionDescriptor = (ConnectionViewAndElementDescriptor) connectionRequest.getNewObject();
                    final Connector edge = (Connector) connectionDescriptor.getAdapter(Edge.class);
                    final ConnectionEditPart connectionEP = (ConnectionEditPart) editPart.getViewer().getEditPartRegistry().get(edge);

                    if (connectionEP != null) {
                        final SetConnectionBendpointsCommand setConnectionBendPointsCommand = new SetConnectionBendpointsCommand(
                                connectionEP.getEditingDomain());
                        setConnectionBendPointsCommand.setEdgeAdapter(connectionDescriptor);
                        final PointList bendpoints = new PointList();
                        bendpoints.addPoint(0, 0);
                        bendpoints.addPoint(0, 0);
                        setConnectionBendPointsCommand.setNewPointList(bendpoints, bendpoints.getFirstPoint(), bendpoints.getLastPoint());
                        connectionEP.getDiagramEditDomain().getDiagramCommandStack().execute(new ICommandProxy(setConnectionBendPointsCommand));

                    }
                }

                editPart.getViewer().select(targetEditPart);
            }
        }
    }

    @Override
    public void eraseTargetFeedback() {
        if (draggableElement.getLayer().getChildren().contains(figure)) {
            draggableElement.getLayer().remove(figure);
        }
        hideTargetCompartmentFeedback();
        super.eraseTargetFeedback();
    }

    /**
     * 
     */
    private void showTargetCompartmentFeedback() {
        // DO NOTHING
    }

    /**
     * 
     */
    private void hideTargetCompartmentFeedback() {
        // DO NOTHING
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gmf.runtime.diagram.ui.tools.CreationTool#deactivate()
     */
    @Override
    public void deactivate() {
        super.deactivate();
        if (cursor != null && !cursor.isDisposed()) {
            cursor.dispose();
        }
        if (image != null && !image.isDisposed()) {
            image.dispose();
        }
    }

    @Override
    public void activate() {
        super.activate();
        //	initCursor();
    }

}
