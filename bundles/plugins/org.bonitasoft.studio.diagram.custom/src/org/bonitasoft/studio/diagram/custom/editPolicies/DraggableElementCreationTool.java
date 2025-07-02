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
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.bpm.model.process.SubProcessEvent;
import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.model.process.diagram.edit.commands.SequenceFlowCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.TextAnnotationAttachmentCreateCommand;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessElementTypes;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.commands.SetConnectionBendpointsCommand;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest.ConnectionViewAndElementDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateUnspecifiedTypeRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest.ViewAndElementDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.tools.CreationTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Connector;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

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
    public DraggableElementCreationTool(UnspecifiedTypeCreationTool tool, DraggableElement draggableElement,
            ZoomManager zoomManager) {
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
        final CreateUnspecifiedTypeRequest createUnspecifiedTypeRequest = (CreateUnspecifiedTypeRequest) tool
                .createCreateRequest();
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
            helper = getTargetEditPart().getAdapter(SnapToHelper.class);
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
            helper = getTargetEditPart().getAdapter(SnapToHelper.class);
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
            helper.snapPoint(request, PositionConstants.HORIZONTAL | PositionConstants.VERTICAL,
                    new PrecisionRectangle[] {
                            baseRect, jointRect },
                    preciseDelta);
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
            ICommandProxy overallCommand = new ICommandProxy(
                    new AbstractTransactionalCommand(editPart.getEditingDomain(), command.getLabel(), null) {

                        @Override
                        protected CommandResult doExecuteWithResult(IProgressMonitor arg0, IAdaptable arg1)
                                throws ExecutionException {
                            command.execute();
                            List<ICommandProxy> cmds = getICommandProxies(command);
                            IAdaptable targetAdapter = null;
                            for (ICommandProxy c : cmds) {
                                final CommandResult commandResult = ((ICommandProxy) c).getICommand()
                                        .getCommandResult();
                                if (commandResult != null
                                        && commandResult.getReturnValue() != null
                                        && !((List<?>) commandResult.getReturnValue()).isEmpty())
                                    targetAdapter = (IAdaptable) ((List<?>) commandResult.getReturnValue())
                                            .get(0);
                            }
                            if (targetAdapter instanceof CreateViewAndElementRequest) {
                                targetAdapter = ((CreateViewAndElementRequest) targetAdapter)
                                        .getViewAndElementDescriptor();
                            } else if (targetAdapter instanceof ViewAndElementDescriptor) {
                                // keep as is
                            } else {
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
                                final String semanticHint = ((ViewAndElementDescriptor) targetAdapter)
                                        .getSemanticHint();
                                boolean isAnnotationLink = ProcessElementTypes.TextAnnotation_3015.getId()
                                        .contains(semanticHint);
                                // can't use DeferredCreateConnectionViewAndElementCommand as it relies on not yet created edit part...
                                Command connectionEdgeCommand;
                                IAdaptable sourceAdapter = draggableElement.getHost();
                                Function<IAdaptable, EObject> getModel = adapter -> adapter.getAdapter(View.class)
                                        .getElement();
                                EditElementCommand connectionElementCommand;
                                if (!isAnnotationLink) {
                                    CreateRelationshipRequest relationRequest = new CreateRelationshipRequest(
                                            getModel.apply(sourceAdapter),
                                            getModel.apply(targetAdapter),
                                            ProcessElementTypes.SequenceFlow_4001);
                                    connectionElementCommand = new SequenceFlowCreateCommand(
                                            relationRequest,
                                            getModel.apply(sourceAdapter), getModel.apply(targetAdapter));
                                    connectionRequest = new CreateConnectionViewAndElementRequest(
                                            ProcessElementTypes.SequenceFlow_4001,
                                            ((IHintedType) ProcessElementTypes.SequenceFlow_4001).getSemanticHint(),
                                            getPreferencesHint());
                                    connectionRequest.setLocation(creationLocation);
                                    connectionEdgeCommand = CreateConnectionViewRequest.getCreateCommand(
                                            connectionRequest.getConnectionViewDescriptor(), draggableElement.getHost(),
                                            targetAdapter, GMFTools.getDiagramEditPart(editPart));
                                } else {
                                    //need to inverse source and target for textAnnotation
                                    CreateRelationshipRequest relationRequest = new CreateRelationshipRequest(
                                            getModel.apply(targetAdapter),
                                            getModel.apply(sourceAdapter),
                                            ProcessElementTypes.TextAnnotationAttachment_4003);
                                    connectionElementCommand = new TextAnnotationAttachmentCreateCommand(
                                            relationRequest,
                                            getModel.apply(targetAdapter), getModel.apply(sourceAdapter));
                                    connectionRequest = new CreateConnectionViewAndElementRequest(
                                            ProcessElementTypes.TextAnnotationAttachment_4003,
                                            ((IHintedType) ProcessElementTypes.TextAnnotationAttachment_4003)
                                                    .getSemanticHint(),
                                            getPreferencesHint());
                                    connectionRequest.setLocation(creationLocation);
                                    connectionEdgeCommand = CreateConnectionViewRequest.getCreateCommand(
                                            connectionRequest.getConnectionViewDescriptor(), targetAdapter,
                                            draggableElement.getHost(),
                                            GMFTools.getDiagramEditPart(editPart));
                                }
                                if (connectionElementCommand.canExecute() && connectionEdgeCommand.canExecute()) {
                                    connectionElementCommand.execute(new NullProgressMonitor(), null);
                                    connectionEdgeCommand.execute();
                                    EObject element = (EObject) connectionElementCommand.getCommandResult()
                                            .getReturnValue();
                                    final ConnectionViewAndElementDescriptor connectionDescriptor = (ConnectionViewAndElementDescriptor) connectionRequest
                                            .getNewObject();
                                    final Connector edge = (Connector) connectionDescriptor.getAdapter(Edge.class);
                                    edge.setElement(element);
                                }
                            }
                            if (targetAdapter instanceof ViewAndElementDescriptor) {
                                /*
                                 * Edit part does not exist yet, because command is still in execution.
                                 * It only gets drawn in post-commit.
                                 */
                                Node node = targetAdapter.getAdapter(Node.class);
                                // parentEditPart should be the same as #editPart in most cases, but just in case it has been retargeted...
                                IGraphicalEditPart parentEditPart = (IGraphicalEditPart) editPart.getViewer()
                                        .getEditPartRegistry().get(node.eContainer());
                                final Location loc = (Location) node.getLayoutConstraint();
                                final Point newLoc = FiguresHelper.handleCompartmentMargin(node, figure, parentEditPart,
                                        loc.getX(), loc.getY(),
                                        (node.getElement() instanceof SubProcessEvent));
                                if (parentEditPart instanceof ShapeCompartmentEditPart) {
                                    final ShapeCompartmentEditPart compartment = (ShapeCompartmentEditPart) parentEditPart;
                                    final Bounds parentBounds = (Bounds) ((Node) ((IGraphicalEditPart) parentEditPart
                                            .getParent()).getNotationView())
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

                                var overlapCommand = new SetBoundsCommand(editPart.getEditingDomain(),
                                        "Check Overlap",
                                        new EObjectAdapter(node), newLoc);
                                overlapCommand.execute(new NullProgressMonitor(), null);

                                if (connectionRequest != null) {
                                    final ConnectionViewAndElementDescriptor connectionDescriptor = (ConnectionViewAndElementDescriptor) connectionRequest
                                            .getNewObject();
                                    final Connector edge = (Connector) connectionDescriptor.getAdapter(Edge.class);

                                    if (edge != null) {
                                        final SetConnectionBendpointsCommand setConnectionBendPointsCommand = new SetConnectionBendpointsCommand(
                                                editPart.getEditingDomain());
                                        setConnectionBendPointsCommand.setEdgeAdapter(connectionDescriptor);
                                        final PointList bendpoints = new PointList();
                                        bendpoints.addPoint(0, 0);
                                        bendpoints.addPoint(0, 0);
                                        setConnectionBendPointsCommand.setNewPointList(bendpoints,
                                                bendpoints.getFirstPoint(), bendpoints.getLastPoint());
                                        setConnectionBendPointsCommand.execute(new NullProgressMonitor(), null);

                                    }
                                }
                            }
                            return CommandResult.newOKCommandResult(targetAdapter);
                        }

                        /**
                         * Get The {@link ICommandProxy} that compose this command
                         * 
                         * @param command {@link ICommandProxy} or {@link CompoundCommand}
                         * @return the list of {@link ICommandProxy}
                         */
                        private List<ICommandProxy> getICommandProxies(Command command) {
                            if (command instanceof ICommandProxy) {
                                return List.of((ICommandProxy) command);
                            } else if (command instanceof CompoundCommand) {
                                final CompoundCommand cmd = (CompoundCommand) command;
                                Stream<Command> cmdStream = cmd.getCommands().stream()
                                        .filter(Command.class::isInstance).map(Command.class::cast);
                                Stream<ICommandProxy> iStream = cmdStream
                                        .flatMap(c -> getICommandProxies(c).stream());
                                return iStream.collect(Collectors.toList());
                            } else {
                                return List.of();
                            }
                        }
                    });
            // execute all commands at once not to corrupt the stack
            editPart.getDiagramEditDomain().getDiagramCommandStack().execute(overallCommand);
            // then select created edit part
            Optional<Node> createdNode = Optional.ofNullable(overallCommand.getICommand().getCommandResult())
                    .map(CommandResult::getReturnValue).map(IAdaptable.class::cast)
                    .map(a -> a.getAdapter(Node.class));
            createdNode.ifPresent(n -> {
                final IGraphicalEditPart targetEditPart = (IGraphicalEditPart) editPart.getViewer()
                        .getEditPartRegistry().get(n);
                Display.getDefault().asyncExec(() -> {
                    editPart.getViewer().select(targetEditPart);
                    // Should be uncommented to have a consistent behavior between all creation request
//                    if ( targetEditPart.isActive() ) {
//                        targetEditPart.performRequest(new Request(RequestConstants.REQ_DIRECT_EDIT));
//                        revealEditPart(targetEditPart);
//                    }
                 });
            });
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
