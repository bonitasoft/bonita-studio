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
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.form.WidgetDependency;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.BoundaryEvent;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.XORGateway;
import org.bonitasoft.studio.model.simulation.SimulationPackage;
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
import org.eclipse.emf.edit.command.SetCommand;
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

    public ConvertBPMNTypeCommand(TransactionalEditingDomain domain,EClass targetEClass, final GraphicalEditPart node, ElementTypeResolver elementTypeResolver) {
        super(domain, ConvertBPMNTypeCommand.class.getName(), null);
        this.targetEClass = targetEClass ;
        this.node = node ;
        this.elementTypeResolver = elementTypeResolver ;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand#doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
     */
    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor,IAdaptable adaptable) throws ExecutionException {
        // Unselect text edit part in case it has focus
        // Avoid NPE when editing label and switching activity type
        node.getViewer().getSelectionManager().setSelection(new StructuredSelection());

        final TransactionalEditingDomain editingDomain = node.getEditingDomain();
        final EObject sourceElement = node.resolveSemanticElement();
        final IFigure nodeFigure = node.getFigure();
        Point location = nodeFigure.getBounds().getTopLeft().getCopy();
        nodeFigure.translateToAbsolute(location);
        Dimension size = nodeFigure.getSize();
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
        IElementType targetElementType = elementTypeResolver.getElementType(parentEditPart, targetEClass);
        CreateViewRequest activityRequest = CreateViewRequestFactory.getCreateShapeRequest(targetElementType, node.getDiagramPreferencesHint());
        activityRequest.setLocation(location);
        activityRequest.setSize(size) ;
        /*
         * Set this extended metadata in order that in the creation of the
         * command it will not forbid the creation because of superposition with
         * the old editpart.
         */
        activityRequest.getExtendedData().put("convertOperation", "true");
        Command command = parentEditPart.getCommand(activityRequest);

        command.execute();
        command.dispose();
        
        IAdaptable targetAdapter = (IAdaptable) ((List<?>) activityRequest.getNewObject()).get(0);
        Node newNode = (Node) targetAdapter.getAdapter(EObject.class);

      
        
        /* Need a refresh when we are in OffscreenEditPart */
        parentEditPart.refresh();

        if(newNode == null) {
            return null;
        }

        final EObject targetElement = newNode.getElement();
        final GraphicalEditPart targetEditPart = (GraphicalEditPart) parentEditPart.findEditPart(parentEditPart, targetElement);

        if (ProcessPackage.eINSTANCE.getActivity().isSuperTypeOf(targetEClass)) {
            ChangePropertyValueRequest fillReq = new ChangePropertyValueRequest("Fill Color", Properties.ID_FILLCOLOR, ((FillStyle) node.getNotationView()
                    .getStyle(NotationPackage.eINSTANCE.getFillStyle())).getFillColor());
            targetEditPart.performRequest(fillReq);
            ChangePropertyValueRequest lineReq = new ChangePropertyValueRequest("Line Color", Properties.ID_LINECOLOR, ((LineStyle) node.getNotationView()
                    .getStyle(NotationPackage.eINSTANCE.getLineStyle())).getLineColor());
            targetEditPart.performRequest(lineReq);
        }

        if(node.getChildren().isEmpty()){
            Request req = new ToggleConnectionLabelsRequest(false) ;
            targetEditPart.performRequest(req)  ;
        }else{
            Request req = new ToggleConnectionLabelsRequest(true) ;
            targetEditPart.performRequest(req)  ;
        }



        List<ICommand> commands = moveConnectionSourceAndTarget(node, editingDomain, parentEditPart, targetEditPart);

        List<Pair<EObject, Point>> childPositions = new ArrayList<Pair<EObject, Point>>();
        for (Object object : node.getChildren()) {
            if (object instanceof GraphicalEditPart) {
                GraphicalEditPart editPart = (GraphicalEditPart) object;
                Point point = editPart.getFigure().getBounds().getLocation().getCopy();
                EObject eObject = editPart.resolveSemanticElement();
                childPositions.add(new Pair<EObject, Point>(eObject, point));
            }
        }

        if(sourceElement instanceof Widget){
            updateWidgetContingencies(targetEditPart.getEditingDomain(),(Widget)sourceElement,(Widget)targetElement) ;
        }


        try {
            CopyEObjectFeaturesCommand operation = new CopyEObjectFeaturesCommand(editingDomain, sourceElement, targetElement);
            OperationHistoryFactory.getOperationHistory().execute(operation, monitor, null);
        } catch (ExecutionException e) {
            BonitaStudioLog.error(e);
        }
        if(targetEditPart != null){
            final EditPolicy editPolicy = targetEditPart.getEditPolicy(EditPolicyRoles.CANONICAL_ROLE);
            if( editPolicy != null){
                ((CanonicalEditPolicy)editPolicy).refresh() ; //REFRESH Boundary when converting from an offset editpart
            }
        }

        if (targetElement instanceof XORGateway) {
            editingDomain.getCommandStack().execute(
                    SetCommand.create(editingDomain, targetElement, SimulationPackage.Literals.SIMULATION_ACTIVITY__EXCLUSIVE_OUTGOING_TRANSITION, true));
        }

        if (sourceElement instanceof Activity) {
            // reset boundary connections
            commands.clear();

            for (BoundaryEvent boundaryEvent : ((Activity) targetElement).getBoundaryIntermediateEvents()) {
                final GraphicalEditPart boundaryEp = (GraphicalEditPart) targetEditPart.findEditPart(targetEditPart, boundaryEvent);

                if (boundaryEp != null) {
                    for (int i = 0; i < boundaryEvent.getOutgoing().size(); i++) {
                        final Connection outgoing = boundaryEvent.getOutgoing().get(i);
                        final GraphicalEditPart boundaryTargetEP = (GraphicalEditPart) parentEditPart.findEditPart(parentEditPart.getRoot(),outgoing.getTarget());

                        if(boundaryTargetEP != null){

                            ConnectionEditPart ep = (ConnectionEditPart) GMFTools.findEditPart((EditPart) parentEditPart.getRoot().getChildren().get(0),outgoing) ;
                            if(ep != null){
                                try {
                                    new DeleteCommand(ep.getNotationView()).execute(null, null);
                                } catch (ExecutionException e) {
                                    BonitaStudioLog.error(e) ;
                                }
                            }

                            AbstractEMFOperation recreateExceptionFlowsOperation = new AbstractEMFOperation(editingDomain, "Recreate Exception flow") {

                                @Override
                                protected IStatus doExecute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
                                    IElementType elementType = ElementTypeRegistry.getInstance().getType("org.bonitasoft.studio.diagram.SequenceFlow_4001");
                                    Edge edge = ViewService.getInstance().createEdge(elementType,
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
                            } catch (ExecutionException e) {
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

        //        if (targetElement instanceof ConnectableElement) {
        //            convertConnectorsAndKPIEvent(editingDomain, (ConnectableElement) targetElement);
        //        }

        // Delete the old node : at this point the old node is already delete,
        // that's the cause of the NPE
        // diagramEditDomain.getDiagramCommandStack().execute(new
        // ICommandProxy(new DeleteCommand(node.getNotationView())));

        targetEditPart.getViewer().flush();
        targetEditPart.refresh();// need to call refresh in order that it
        targetEditPart.getViewer().select(targetEditPart);
        targetEditPart.getRoot().refresh();
        /* Need to refresh the boundaries elment at the end of the conversion */
        refreshBoundaryElements(editingDomain, targetElement, targetEditPart, childPositions);
        return CommandResult.newOKCommandResult(targetEditPart);
    }

    protected static void refreshBoundaryElements(TransactionalEditingDomain editingDomain, final EObject targetElement,
            final GraphicalEditPart targetEditPart, List<Pair<EObject, Point>> childPositions) {
        for (Pair<EObject, Point> pair : childPositions) {
            final EObject childToResetPosition = pair.getFirst();
            if (targetElement.eContents().contains(childToResetPosition)) {
                GraphicalEditPart editPart = (GraphicalEditPart) targetEditPart.findEditPart(targetEditPart, childToResetPosition);
                if (editPart != null) {
                    Point childPosition = pair.getSecond();
                    childPosition.x = childPosition.x - targetEditPart.getFigure().getBounds().x;
                    childPosition.y = childPosition.y - targetEditPart.getFigure().getBounds().y;
                    ICommand moveCommand = new SetBoundsCommand(editPart.getEditingDomain(),
                            DiagramUIMessages.Commands_MoveElement, new EObjectAdapter(
                                    (View) editPart.getModel()), childPosition);
                    editPart.getDiagramEditDomain().getDiagramCommandStack().execute(new ICommandProxy(moveCommand)) ;
                } else {
                    /*
                     * If there is no editpart, it must be that a the boundary
                     * can't be set on this kind of Element
                     */
                    if (targetElement instanceof Activity) {

                        AbstractEMFOperation operation = new AbstractEMFOperation(editingDomain, "Remove boundary child") {

                            @Override
                            protected IStatus doExecute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
                                /*
                                 * Remove the BoundaryEvent from the list and
                                 * its outgoing transition
                                 */
                                ((Activity) targetElement).getBoundaryIntermediateEvents().remove(childToResetPosition);
                                if (childToResetPosition instanceof BoundaryEvent) {
                                    ((BoundaryEvent) childToResetPosition).getOutgoing().clear();
                                }
                                return new Status(IStatus.OK, "org.bonitasoft.studio.diagram.common", "Remove boundary child succeeded");
                            }
                        };
                        try {
                            operation.execute(new NullProgressMonitor(), null);
                        } catch (ExecutionException e) {
                            BonitaStudioLog.error(e);
                        }

                    }
                }
            }
            targetEditPart.getRoot().refresh();
        }
    }

    protected static List<ICommand> moveConnectionSourceAndTarget(final GraphicalEditPart node, TransactionalEditingDomain editingDomain,
            final GraphicalEditPart parentEditPart, final GraphicalEditPart targetEditPart) {
        List<ICommand> commands = new ArrayList<ICommand>();
        for (org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart sourceConnection : (List<org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart>) node.getSourceConnections()) {

            SetConnectionEndsCommand setConnectionEndCommand = new SetConnectionEndsCommand(editingDomain, Messages.setConnectionEndCommandLabel);
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

        for (org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart targetConnection : (List<org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart>) node
                .getTargetConnections()) {
            if (!targetConnection.getTarget().equals(targetConnection.getSource())) {
                SetConnectionEndsCommand setConnectionEndCommand = new SetConnectionEndsCommand(editingDomain, Messages.setConnectionEndCommandLabel);
                setConnectionEndCommand.setEdgeAdaptor(targetConnection);
                setConnectionEndCommand.setNewSourceAdaptor(targetConnection.getSource());
                setConnectionEndCommand.setNewTargetAdaptor(targetEditPart);
                commands.add(setConnectionEndCommand);
            }
        }
        try {
            new CompositeCommand(Messages.setConnectionsCommandLabel, commands).execute(new NullProgressMonitor(), parentEditPart);
        } catch (Exception ex) {
            BonitaStudioLog.error(ex);
        }
        return commands;
    }

    private static void updateWidgetContingencies(TransactionalEditingDomain editingDomain,Widget sourceElement,Widget targetElement) {
        Form f = ModelHelper.getForm(sourceElement) ;
        List<WidgetDependency> widgetsDependencies = ModelHelper.getAllItemsOfType(f, FormPackage.eINSTANCE.getWidgetDependency()) ;
        for(WidgetDependency w : widgetsDependencies){
            if(w.getWidget() != null && w.getWidget().equals(sourceElement)){
                editingDomain.getCommandStack().execute(new SetCommand(editingDomain, w, FormPackage.eINSTANCE.getWidgetDependency_Widget(), targetElement)) ;
            }
        }
    }

    @Override
    protected void didUndo(Transaction tx) {
        super.didUndo(tx);
    }

    @Override
    protected void didRedo(Transaction tx) {
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

