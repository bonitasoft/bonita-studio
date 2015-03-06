/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.gmf.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.diagram.tools.BonitaConnectionTypes;
import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gmf.runtime.common.core.command.AbstractCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.commands.DeferredCreateConnectionViewAndElementCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.commands.SetConnectionBendpointsCommand;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest.ConnectionViewAndElementDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest.ViewAndElementDescriptor;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Connector;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author Romain Bioteau
 */
public class InsertElementOnSequenceFlowCommand extends AbstractCommand {

    private final boolean correctOffset;
    private final Collection objects;
    private final IGraphicalEditPart targetEditPart;
    private final Map epRegistry;
    private EditPart originalTargetEditPart;
    private SetConnectionBendpointsCommand setBendpointsCmdForNewEdge;
    private SetConnectionBendpointsCommand setBendpointsCmdForOriginalEdge;
    private DeferredCreateConnectionViewAndElementCommand createConnectionCommand;
    private final Command creationCommand;
    private SetBoundsCommand correctOffsetCmd;

    public InsertElementOnSequenceFlowCommand(final Command command,
            final IGraphicalEditPart targetEditPart, final EditPartViewer viewer,
            final boolean correctOffset) {
        super("Insert element on SequenceFlow");
        this.correctOffset = correctOffset;
        creationCommand = command;
        objects = DiagramCommandStack.getReturnValues(creationCommand);
        this.targetEditPart = targetEditPart;
        epRegistry = viewer.getEditPartRegistry();
    }

    @Override
    protected CommandResult doExecuteWithResult(
            final IProgressMonitor monitor, final IAdaptable info)
            throws ExecutionException {
        if (targetEditPart instanceof ConnectionEditPart) {
            final ConnectionEditPart targetConnectionEditPart = (ConnectionEditPart) targetEditPart;
            for (final Iterator i = objects.iterator(); i.hasNext();) {
                final Object object = i.next();
                if (object instanceof ViewAndElementDescriptor) {
                    final ViewAndElementDescriptor descriptor = (ViewAndElementDescriptor) object;
                    final ShapeEditPart editPart = (ShapeEditPart) epRegistry.get(descriptor.getAdapter(View.class));
                    final Bounds b = (Bounds) ((Node) editPart.getNotationView()).getLayoutConstraint();
                    correctOffsetCmd = new SetBoundsCommand(editPart.getEditingDomain(), "", descriptor, new Point(b.getX() - 25, b.getY() - 25));

                    final ReconnectRequest reconnect = new ReconnectRequest(RequestConstants.REQ_RECONNECT_TARGET);
                    reconnect.setConnectionEditPart(targetConnectionEditPart);
                    reconnect.setTargetEditPart(editPart);
                    final CompoundCommand reconnectCommand = (CompoundCommand) editPart.getCommand(reconnect);

                    final CreateConnectionViewAndElementRequest connectionRequest = new CreateConnectionViewAndElementRequest(
                            BonitaConnectionTypes.getElementType("org.bonitasoft.studio.diagram.SequenceFlow_4001"),
                            ((IHintedType) BonitaConnectionTypes.getElementType("org.bonitasoft.studio.diagram.SequenceFlow_4001")).getSemanticHint(),
                            new PreferencesHint("org.bonitasoft.studio.diagram"));
                    originalTargetEditPart = targetConnectionEditPart.getTarget();
                    createConnectionCommand = new DeferredCreateConnectionViewAndElementCommand(connectionRequest, descriptor, originalTargetEditPart,
                            editPart.getViewer());
                    if (correctOffset) {
                        correctOffsetCmd.execute(monitor, info);
                    }
                    final CompoundCommand ccc = (CompoundCommand) reconnectCommand.getChildren()[1];
                    for (final Object proxy : ccc.getChildren()) {
                        if (proxy instanceof ICommandProxy) {
                            final ICommand iCommand = ((ICommandProxy) proxy).getICommand();
                            if (iCommand.canExecute()) {
                                iCommand.execute(monitor, info);
                            }
                        }
                    }
                    createConnectionCommand.execute(monitor, info);

                    if (connectionRequest != null) {
                        final ConnectionViewAndElementDescriptor connectionDescriptor = (ConnectionViewAndElementDescriptor) connectionRequest.getNewObject();
                        final Connector edge = (Connector) connectionDescriptor.getAdapter(Edge.class);
                        final org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart connectionEP = (org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart) editPart
                                .getViewer().getEditPartRegistry().get(edge);
                        if (connectionEP != null) {
                            final TransactionalEditingDomain editingDomain = connectionEP.getEditingDomain();
                            setBendpointsCmdForNewEdge = createSetBendpointsCommand(monitor, info, edge, editingDomain);
                            setBendpointsCmdForNewEdge.execute(monitor, info);
                            setBendpointsCmdForOriginalEdge = createSetBendpointsCommand(monitor, info, (Connector) targetEditPart.getNotationView(),
                                    editingDomain);
                            setBendpointsCmdForOriginalEdge.execute(monitor, info);
                        }
                    }
                }
            }
        }
        correctBoundsToAvoidCollisions(monitor, info);
        return CommandResult.newOKCommandResult();
    }

    @SuppressWarnings("restriction")
    protected SetConnectionBendpointsCommand createSetBendpointsCommand(
            final IProgressMonitor monitor,
            final IAdaptable info,
            final Connector edge,
            final TransactionalEditingDomain domain) {
        final SetConnectionBendpointsCommand setConnectionBendPointsCommand = new SetConnectionBendpointsCommand(domain);
        setConnectionBendPointsCommand.setEdgeAdapter(new EObjectAdapter(edge));
        final PointList bendpoints = new PointList();
        bendpoints.addPoint(0, 0);
        bendpoints.addPoint(0, 0);
        setConnectionBendPointsCommand.setNewPointList(bendpoints, bendpoints.getFirstPoint(), bendpoints.getLastPoint());
        return setConnectionBendPointsCommand;
    }

    protected void correctBoundsToAvoidCollisions(final IProgressMonitor monitor,
            final IAdaptable info) throws ExecutionException {
        final List editparts = new ArrayList();
        for (final Iterator i = objects.iterator(); i.hasNext();) {
            final Object object = i.next();
            if (object instanceof IAdaptable) {
                final Object editPart = epRegistry.get(((IAdaptable) object).getAdapter(View.class));
                if (editPart != null) {
                    editparts.add(editPart);
                }
            }
        }

        for (final Object ep : editparts) {
            if (ep instanceof IGraphicalEditPart) {
                final Location loc = (Location) ((Node) ((IGraphicalEditPart) ep).getNotationView()).getLayoutConstraint();
                final Point newLoc = FiguresHelper.handleCompartmentMargin((IGraphicalEditPart) ep, loc.getX(), loc.getY(),
                        ((IGraphicalEditPart) ep).resolveSemanticElement() instanceof SubProcessEvent);
                if (((IGraphicalEditPart) ep).getParent() instanceof ShapeCompartmentEditPart
                        && !(((IGraphicalEditPart) ((IGraphicalEditPart) ep).getParent()).resolveSemanticElement() instanceof SubProcessEvent)) {
                    final ShapeCompartmentEditPart compartment = (ShapeCompartmentEditPart) ((IGraphicalEditPart) ep).getParent();
                    while (newLoc.y + 65 > compartment.getFigure().getBounds().height) {
                        newLoc.y = newLoc.y - 10;
                    }
                    while (newLoc.x + 100 > compartment.getFigure().getBounds().width) {
                        newLoc.x = newLoc.x - 10;
                    }
                }
                new SetBoundsCommand(((IGraphicalEditPart) ep).getEditingDomain(), "Check Overlap", new EObjectAdapter(
                        ((IGraphicalEditPart) ep).getNotationView()), newLoc).execute(monitor, info);
            }
        }
    }

    @Override
    protected CommandResult doRedoWithResult(final IProgressMonitor progressMonitor,
            final IAdaptable info) throws ExecutionException {
        if (creationCommand != null && creationCommand.canExecute()) {
            creationCommand.execute();
        }
        return doExecuteWithResult(progressMonitor, info);
    }

    @Override
    protected CommandResult doUndoWithResult(final IProgressMonitor monitor,
            final IAdaptable info) throws ExecutionException {
        if (setBendpointsCmdForOriginalEdge != null) {
            setBendpointsCmdForOriginalEdge.undo(monitor, info);
        }
        if (createConnectionCommand != null) {
            createConnectionCommand.undo(monitor, info);
        }
        if (targetEditPart instanceof ConnectionEditPart && originalTargetEditPart != null) {
            final ReconnectRequest reconnect = new ReconnectRequest(RequestConstants.REQ_RECONNECT_TARGET);
            reconnect.setConnectionEditPart((ConnectionEditPart) targetEditPart);
            reconnect.setTargetEditPart(originalTargetEditPart);
            final CompoundCommand reconnectCommand = (CompoundCommand) originalTargetEditPart.getCommand(reconnect);
            final CompoundCommand ccc = (CompoundCommand) reconnectCommand.getChildren()[1];
            for (final Object proxy : ccc.getChildren()) {
                if (proxy instanceof ICommandProxy) {
                    ((ICommandProxy) proxy).getICommand().execute(monitor, info);
                }
            }
        }
        if (correctOffset && correctOffsetCmd != null && correctOffsetCmd.canUndo()) {
            correctOffsetCmd.undo(monitor, info);
        }
        if (creationCommand != null && creationCommand.canUndo()) {
            creationCommand.undo();
            creationCommand.dispose();
        }
        return CommandResult.newOKCommandResult();
    }

}
