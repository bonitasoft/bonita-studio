/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.gmf.tools.convert;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.Pair;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.gmf.tools.CopyEObjectFeaturesCommand;
import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.common.gmf.tools.GMFTools.ElementTypeResolver;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.BoundaryEvent;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.MessageFlow;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ThrowMessageEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.AbstractEMFOperation;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.diagram.core.commands.SetConnectionEndsCommand;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.Properties;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.requests.ChangePropertyValueRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequestFactory;
import org.eclipse.gmf.runtime.diagram.ui.requests.ToggleConnectionLabelsRequest;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.FillStyle;
import org.eclipse.gmf.runtime.notation.LineStyle;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.StructuredSelection;

/**
 * @author Romain Bioteau
 *
 */
public class ConvertBPMNTypeCommand extends AbstractTransactionalCommand {

    private final EClass targetEClass;
    private final GraphicalEditPart node;
    private final ElementTypeResolver elementTypeResolver;

    public ConvertBPMNTypeCommand(final TransactionalEditingDomain domain,final EClass targetEClass, final GraphicalEditPart node, final ElementTypeResolver elementTypeResolver) {
        super(domain, ConvertBPMNTypeCommand.class.getName(), null);
        this.targetEClass = targetEClass ;
        this.node = node ;
        this.elementTypeResolver = elementTypeResolver ;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand#doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
     */
    @Override
    protected CommandResult doExecuteWithResult(final IProgressMonitor monitor,final IAdaptable adaptable) throws ExecutionException {
        // Unselect text edit part in case it has focus
        // Avoid NPE when editing label and switching activity type
        node.getViewer().getSelectionManager().setSelection(new StructuredSelection());

        final TransactionalEditingDomain editingDomain = node.getEditingDomain();
        final EObject sourceElement = node.resolveSemanticElement();
        final IFigure nodeFigure = node.getFigure();
        final Point location = nodeFigure.getBounds().getTopLeft().getCopy();
        nodeFigure.translateToAbsolute(location);
        final Dimension size = nodeFigure.getSize();
        final RootEditPart root = node.getRoot();
        if(root instanceof DiagramRootEditPart){
            final ZoomManager zoomManager = ((DiagramRootEditPart)root).getZoomManager();
            if(zoomManager != null){
                size.performScale(zoomManager.getZoom());
            }
        }

        final GraphicalEditPart parentEditPart = (GraphicalEditPart) node.getParent();
        // TODO check in lanes

        // TODO guess elementType from target instead of hardcoding. Depends on
        // target type and container
        final IElementType targetElementType = elementTypeResolver.getElementType(parentEditPart, targetEClass);
        final CreateViewRequest activityRequest = CreateViewRequestFactory.getCreateShapeRequest(targetElementType, node.getDiagramPreferencesHint());
        activityRequest.setLocation(location);
        activityRequest.setSize(size) ;
        /*
         * Set this extended metadata in order that in the creation of the
         * command it will not forbid the creation because of superposition with
         * the old editpart.
         */
        activityRequest.getExtendedData().put("convertOperation", "true");
        final Command command = parentEditPart.getCommand(activityRequest);

        command.execute();
        command.dispose();

        final IAdaptable targetAdapter = (IAdaptable) ((List<?>) activityRequest.getNewObject()).get(0);
        final Node newNode = (Node) targetAdapter.getAdapter(EObject.class);



        /* Need a refresh when we are in OffscreenEditPart */
        parentEditPart.refresh();

        if(newNode == null) {
            return null;
        }

        final EObject targetElement = newNode.getElement();
        final GraphicalEditPart targetEditPart = (GraphicalEditPart) parentEditPart.findEditPart(parentEditPart, targetElement);

        if (ProcessPackage.eINSTANCE.getActivity().isSuperTypeOf(targetEClass)) {
            final ChangePropertyValueRequest fillReq = new ChangePropertyValueRequest("Fill Color", Properties.ID_FILLCOLOR, ((FillStyle) node.getNotationView()
                    .getStyle(NotationPackage.eINSTANCE.getFillStyle())).getFillColor());
            targetEditPart.performRequest(fillReq);
            final ChangePropertyValueRequest lineReq = new ChangePropertyValueRequest("Line Color", Properties.ID_LINECOLOR, ((LineStyle) node.getNotationView()
                    .getStyle(NotationPackage.eINSTANCE.getLineStyle())).getLineColor());
            targetEditPart.performRequest(lineReq);
        }

        if(node.getChildren().isEmpty()){
            final Request req = new ToggleConnectionLabelsRequest(false) ;
            targetEditPart.performRequest(req)  ;
        }else{
            final Request req = new ToggleConnectionLabelsRequest(true) ;
            targetEditPart.performRequest(req)  ;
        }



        final List<ICommand> commands = moveConnectionSourceAndTarget(node, editingDomain, parentEditPart, targetEditPart);

        final List<Pair<EObject, Point>> childPositions = new ArrayList<Pair<EObject, Point>>();
        for (final Object object : node.getChildren()) {
            if (object instanceof GraphicalEditPart) {
                final GraphicalEditPart editPart = (GraphicalEditPart) object;
                final Point point = editPart.getFigure().getBounds().getLocation().getCopy();
                final EObject eObject = editPart.resolveSemanticElement();
                childPositions.add(new Pair<EObject, Point>(eObject, point));
            }
        }

        try {
            final CopyEObjectFeaturesCommand operation = new CopyEObjectFeaturesCommand(editingDomain, sourceElement, targetElement);
            OperationHistoryFactory.getOperationHistory().execute(operation, monitor, null);
        } catch (final ExecutionException e) {
            BonitaStudioLog.error(e);
        }
        if(targetEditPart != null){
            final EditPolicy editPolicy = targetEditPart.getEditPolicy(EditPolicyRoles.CANONICAL_ROLE);
            if( editPolicy != null){
                ((CanonicalEditPolicy)editPolicy).refresh() ; //REFRESH Boundary when converting from an offset editpart
            }
        }

        if (sourceElement instanceof Activity) {
            // reset boundary connections
            commands.clear();

            handleBoundaries(monitor, editingDomain, parentEditPart, targetElement, targetEditPart);
        }

        targetEditPart.getViewer().flush();
        targetEditPart.refresh();// need to call refresh in order that it
        targetEditPart.getViewer().select(targetEditPart);
        targetEditPart.getRoot().refresh();
        /* Need to refresh the boundaries element at the end of the conversion */
        refreshBoundaryElements(editingDomain, targetElement, targetEditPart, childPositions);
        return CommandResult.newOKCommandResult(targetEditPart);
    }

    protected void handleBoundaries(final IProgressMonitor monitor, final TransactionalEditingDomain editingDomain, final GraphicalEditPart parentEditPart,
            final EObject targetElement, final GraphicalEditPart targetEditPart) {
        for (final BoundaryEvent boundaryEvent : ((Activity) targetElement).getBoundaryIntermediateEvents()) {
            final GraphicalEditPart boundaryEp = (GraphicalEditPart) targetEditPart.findEditPart(targetEditPart, boundaryEvent);

            if (boundaryEp != null) {
                for (int i = 0; i < boundaryEvent.getOutgoing().size(); i++) {
                    final Connection outgoing = boundaryEvent.getOutgoing().get(i);
                    final GraphicalEditPart boundaryTargetEP = (GraphicalEditPart) parentEditPart.findEditPart(parentEditPart.getRoot(),outgoing.getTarget());

                    if(boundaryTargetEP != null){

                        final ConnectionEditPart ep = (ConnectionEditPart) GMFTools.findEditPart((EditPart) parentEditPart.getRoot().getChildren().get(0),outgoing) ;
                        if(ep != null){
                            try {
                                new DeleteCommand(ep.getNotationView()).execute(null, null);
                            } catch (final ExecutionException e) {
                                BonitaStudioLog.error(e) ;
                            }
                        }

                        final AbstractEMFOperation recreateExceptionFlowsOperation = new AbstractEMFOperation(editingDomain, "Recreate Exception flow") {

                            @Override
                            protected IStatus doExecute(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
                                final IElementType elementType = ElementTypeRegistry.getInstance().getType("org.bonitasoft.studio.diagram.SequenceFlow_4001");
                                final Edge edge = ViewService.getInstance().createEdge(elementType,
                                        ModelHelper.getDiagramFor(ModelHelper.getMainProcess(targetElement),editingDomain),
                                        ((IHintedType) elementType).getSemanticHint(), -1, true, targetEditPart.getDiagramPreferencesHint());
                                edge.setElement(outgoing);
                                edge.setSource(boundaryEp.getNotationView());
                                edge.setTarget(boundaryTargetEP.getNotationView());

                                edge.setVisible(true);

                                return new Status(IStatus.OK, "org.bonitasoft.studio.diagram.common", "Recreate Exception flow succeeded");
                            }
                        };
                        try {
                            recreateExceptionFlowsOperation.execute(monitor, null);
                        } catch (final ExecutionException e) {
                            BonitaStudioLog.error(e);
                        }
                    } else {
                        // TODO: remove target from list because it has been
                        // removed? for now this done at the end, in
                        // refreshBoundaryElements
                    }
                }
            }
        }
    }

    protected static void refreshBoundaryElements(final TransactionalEditingDomain editingDomain, final EObject targetElement,
            final GraphicalEditPart targetEditPart, final List<Pair<EObject, Point>> childPositions) {
        for (final Pair<EObject, Point> pair : childPositions) {
            final EObject childToResetPosition = pair.getFirst();
            if (targetElement.eContents().contains(childToResetPosition)) {
                final GraphicalEditPart editPart = (GraphicalEditPart) targetEditPart.findEditPart(targetEditPart, childToResetPosition);
                if (editPart != null) {
                    final Point childPosition = pair.getSecond();
                    childPosition.x = childPosition.x - targetEditPart.getFigure().getBounds().x;
                    childPosition.y = childPosition.y - targetEditPart.getFigure().getBounds().y;
                    final ICommand moveCommand = new SetBoundsCommand(editPart.getEditingDomain(),
                            DiagramUIMessages.Commands_MoveElement, new EObjectAdapter(
                                    (View) editPart.getModel()), childPosition);
                    editPart.getDiagramEditDomain().getDiagramCommandStack().execute(new ICommandProxy(moveCommand)) ;
                } else {
                    /*
                     * If there is no editpart, it must be that a the boundary
                     * can't be set on this kind of Element
                     */
                    if (targetElement instanceof Activity) {
                        /*
                         * Remove the BoundaryEvent from the list and
                         * its outgoing transition
                         */
                        final AbstractEMFOperation operation = new RemoveBoundaryWithItsFlows(editingDomain, (BoundaryEvent) childToResetPosition,
                                (Activity) targetElement);
                        try {
                            operation.execute(new NullProgressMonitor(), null);
                        } catch (final ExecutionException e) {
                            BonitaStudioLog.error(e);
                        }

                    }
                }
            }
            targetEditPart.getRoot().refresh();
        }
    }

    protected static List<ICommand> moveConnectionSourceAndTarget(final GraphicalEditPart node, final TransactionalEditingDomain editingDomain,
            final GraphicalEditPart parentEditPart, final GraphicalEditPart targetEditPart) {
        final List<ICommand> commands = new ArrayList<ICommand>();

        for (final org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart sourceConnection : (List<org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart>) node.getSourceConnections()) {
            if (sourceConnection.resolveSemanticElement() instanceof MessageFlow) {
                if (!(targetEditPart.resolveSemanticElement() instanceof ThrowMessageEvent)) {
                    //Delete message flow as it's no more consistent on new element type
                    editingDomain.getCommandStack().execute(
                            org.eclipse.emf.edit.command.DeleteCommand.create(editingDomain, sourceConnection.resolveSemanticElement()));
                    commands.add(new DeleteCommand(editingDomain, sourceConnection.getNotationView()));
                    continue;
                }
            }
            final SetConnectionEndsCommand setConnectionEndCommand = new SetConnectionEndsCommand(editingDomain, Messages.setConnectionEndCommandLabel);
            setConnectionEndCommand.setEdgeAdaptor(sourceConnection);
            if (sourceConnection.getTarget().equals(sourceConnection.getSource())) { // Recursive
                // Connection
                setConnectionEndCommand.setNewSourceAdaptor(targetEditPart);
                setConnectionEndCommand.setNewTargetAdaptor(targetEditPart);
                } else {
                setConnectionEndCommand.setNewSourceAdaptor(targetEditPart);
                setConnectionEndCommand.setNewTargetAdaptor(sourceConnection.getTarget());
                }
            commands.add(setConnectionEndCommand);
        }

        for (final org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart targetConnection : (List<org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart>) node
                .getTargetConnections()) {
            if (targetConnection.resolveSemanticElement() instanceof MessageFlow) {
                if (!(targetEditPart.resolveSemanticElement() instanceof AbstractCatchMessageEvent)) {
                    //Delete message flow as it's no more consistent on new element type
                    editingDomain.getCommandStack().execute(
                            org.eclipse.emf.edit.command.DeleteCommand.create(editingDomain, targetConnection.resolveSemanticElement()));
                    commands.add(new DeleteCommand(editingDomain, targetConnection.getNotationView()));
                    continue;
                }
            }
            if (!targetConnection.getTarget().equals(targetConnection.getSource())) {
                final SetConnectionEndsCommand setConnectionEndCommand = new SetConnectionEndsCommand(editingDomain, Messages.setConnectionEndCommandLabel);
                setConnectionEndCommand.setEdgeAdaptor(targetConnection);
                setConnectionEndCommand.setNewSourceAdaptor(targetConnection.getSource());
                setConnectionEndCommand.setNewTargetAdaptor(targetEditPart);
                commands.add(setConnectionEndCommand);
            }
        }
        try {
            new CompositeCommand(Messages.setConnectionsCommandLabel, commands).execute(new NullProgressMonitor(), parentEditPart);
        } catch (final Exception ex) {
            BonitaStudioLog.error(ex);
        }
        return commands;
    }


    @Override
    protected void didUndo(final Transaction tx) {
        super.didUndo(tx);
    }

    @Override
    protected void didRedo(final Transaction tx) {
        super.didRedo(tx);
    }

    @Override
    public boolean canUndo() {
        return super.canUndo();
    }

    @Override
    public boolean canRedo() {
        return super.canRedo();
    }

}

