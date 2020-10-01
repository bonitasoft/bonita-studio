/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.diagram.custom.refactoring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.diagram.custom.i18n.Messages;
import org.bonitasoft.studio.diagram.custom.parts.CustomPoolEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomSequenceFlowEditPart;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.SourceElement;
import org.bonitasoft.studio.model.process.TargetElement;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessElementTypes;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.INodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest.ViewAndElementDescriptor;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.emf.type.core.commands.DeferredSetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;

/**
 * @author Romain Bioteau
 * 
 */
public class ExtractAsCallActivityTransactionalCommand extends AbstractTransactionalCommand {

    private DiagramEditor editor;

    public ExtractAsCallActivityTransactionalCommand(DiagramEditor editor) {
        super(editor.getEditingDomain(), ExtractAsCallActivityTransactionalCommand.class.getName(), null);
        this.editor = editor;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand#doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor,
     * org.eclipse.core.runtime.IAdaptable)
     */
    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
        List<?> selectedEditParts = editor.getDiagramGraphicalViewer().getSelectedEditParts();
        List<IGraphicalEditPart> selectedGraphicalEditParts = new ArrayList<IGraphicalEditPart>();
        for (Object ep : selectedEditParts) {
            if (ep instanceof IGraphicalEditPart) {
                selectedGraphicalEditParts.add((IGraphicalEditPart) ep);
            }
        }
        List<IGraphicalEditPart> parts = GMFTools.addMissingConnectionsAndBoundaries(selectedGraphicalEditParts);
        List<String> errors = evaluateErrors(parts);
        if (!errors.isEmpty()) {
            return CommandResult.newErrorCommandResult(createErrorMessage(errors));
        } else {
            extractAsCallActivity(parts);
        }
        return CommandResult.newOKCommandResult();
    }

    private void extractAsCallActivity(List<IGraphicalEditPart> parts) {
        IGraphicalEditPart refNode = GMFTools.getFirstNodeShapeEditPart(parts);
        EditPart tmp = refNode;
        while (!(tmp instanceof MainProcessEditPart)) {
            tmp = tmp.getParent();
        }
        MainProcessEditPart processEp = (MainProcessEditPart) tmp;
        MainProcess processSemantic = (MainProcess) processEp.resolveSemanticElement();

        ViewAndElementDescriptor viewDescriptor = new ViewAndElementDescriptor(
                new CreateElementRequestAdapter(new CreateElementRequest(ProcessElementTypes.Pool_2007)),
                Node.class,
                ((IHintedType) ProcessElementTypes.Pool_2007).getSemanticHint(),
                processEp.getDiagramPreferencesHint());

        final CreateViewAndElementRequest poolReq = new CreateViewAndElementRequest(viewDescriptor);
        poolReq.setLocation(new Point(20, processEp.getFigure().getSize().height - 1));
        Dimension size = new Dimension(processEp.getFigure().getSize().width, computePoolHeight(parts));
        poolReq.setSize(size);
        // processEp.getCommand(req).execute();
        CompositeCommand cc = new CompositeCommand("Create subprocess");
        // String id = NamingUtils.convertToId(((AbstractProcess)processEp.resolveSemanticElement()).getName(),(Element)
        // req.getViewAndElementDescriptor().getElementAdapter().getAdapter(EObject.class));
        final Command createCommand = processEp.getCommand(poolReq);
        cc.add(new CommandProxy(createCommand));

        int i = NamingUtils.getMaxElements(processSemantic, Messages.extractAsSubprocessNewPoolName);
        i++;
        final String extractedSubprocessName = Messages.extractAsSubprocessNewPoolName + i;
        cc.add(createSetCommand(poolReq, ProcessPackage.Literals.ELEMENT__NAME, extractedSubprocessName, processSemantic));
        cc.add(createSetCommand(poolReq, ProcessPackage.Literals.ABSTRACT_PROCESS__VERSION, "1.0", processSemantic));

        IGraphicalEditPart containerEp = (IGraphicalEditPart) refNode.getParent();

        IElementType subprocessType = ProcessElementTypes.CallActivity_3063;
        final ViewAndElementDescriptor subprocessDescriptor = new ViewAndElementDescriptor(
                new CreateElementRequestAdapter(new CreateElementRequest(subprocessType)),
                Node.class,
                ((IHintedType) subprocessType).getSemanticHint(),
                processEp.getDiagramPreferencesHint());
        final CreateViewAndElementRequest createSubprocessNodeReq = new CreateViewAndElementRequest(subprocessDescriptor);
        final IFigure figure = refNode.getFigure();
        final Point location = figure.getBounds().getLeft().getCopy();
        figure.getParent().translateToAbsolute(location);
        createSubprocessNodeReq.setLocation(location);
        final Command createSubprocessCommand = containerEp.getCommand(createSubprocessNodeReq);
        cc.add(new CommandProxy(createSubprocessCommand));
        cc.add(createSetCommand(createSubprocessNodeReq, ProcessPackage.Literals.CALL_ACTIVITY__CALLED_ACTIVITY_NAME,
                ExpressionHelper.createConstantExpression(extractedSubprocessName, String.class.getName()), processSemantic));
        new ICommandProxy(cc).execute();
        cc.dispose();

        processEp.refresh();
        containerEp.refresh();
        EObject newPool = (EObject) poolReq.getViewAndElementDescriptor().getElementAdapter().getAdapter(EObject.class);
        IGraphicalEditPart ep = GMFTools.findEditPart(processEp, newPool);
        ep.getEditingDomain()
                .getCommandStack()
                .execute(
                        SetCommand.create(ep.getEditingDomain(), ((Node) ep.getNotationView()).getLayoutConstraint(),
                                NotationPackage.eINSTANCE.getSize_Width(), size.width));
        ep.getEditingDomain()
                .getCommandStack()
                .execute(
                        SetCommand.create(ep.getEditingDomain(), ((Node) ep.getNotationView()).getLayoutConstraint(),
                                NotationPackage.eINSTANCE.getSize_Height(), size.height));

        cc = new CompositeCommand("Populate subprocess");
        final CallActivity newSubProcess = (CallActivity) subprocessDescriptor.getElementAdapter().getAdapter(EObject.class);
        final IGraphicalEditPart newSubprocessPart = GMFTools.findEditPart(processEp, newSubProcess);
        for (SourceElement element : findTarget(parts)) {
            for (Connection flow : element.getOutgoing()) {
                ReconnectRequest reconnect = new ReconnectRequest(RequestConstants.REQ_RECONNECT_SOURCE);
                reconnect.setConnectionEditPart(findConnectionPart(processEp, flow));
                reconnect.setTargetEditPart(newSubprocessPart);
                cc.add(new CommandProxy(reconnect.getTarget().getCommand(reconnect)));
            }
        }
        for (TargetElement element : findSources(parts)) {
            for (Connection flow : element.getIncoming()) {
                ReconnectRequest reconnect = new ReconnectRequest(RequestConstants.REQ_RECONNECT_TARGET);
                reconnect.setConnectionEditPart(findConnectionPart(processEp, flow));
                reconnect.setTargetEditPart(newSubprocessPart);
                cc.add(new CommandProxy(reconnect.getTarget().getCommand(reconnect)));
            }
        }
        cc.add(new MoveItemsAndCopyDataCommand(processEp.getEditingDomain(), poolReq, subprocessDescriptor, parts));
        new ICommandProxy(cc).execute();
        cc.dispose();

        cc = new CompositeCommand("clean");
        for (IGraphicalEditPart part : GMFTools.addMissingConnectionsAndBoundaries(parts)) {
            if (!(part instanceof ConnectionEditPart)
                    ||
                    (part instanceof ConnectionEditPart && ((ConnectionEditPart) part).getSource() != newSubprocessPart && ((ConnectionEditPart) part)
                            .getTarget() != newSubProcess)) {
                cc.add(new DeleteCommand(part.getNotationView()));
                cc.add(new DestroyElementCommand(new DestroyElementRequest(part.resolveSemanticElement(), false)));
            }
        }
        new ICommandProxy(cc).execute();
    }

    private int computePoolHeight(List<IGraphicalEditPart> elements) {
        IGraphicalEditPart mostBottomElement = null;
        int maxHeight = 0;
        for (IGraphicalEditPart element : elements) {
            if (!(element instanceof CustomSequenceFlowEditPart)) {
                IGraphicalEditPart parent = (IGraphicalEditPart) element.getParent();
                while (!(parent instanceof CustomPoolEditPart)) {
                    parent = (IGraphicalEditPart) parent.getParent();
                }
                Rectangle bounds = element.getFigure().getBounds().getCopy();

                FiguresHelper.translateToAbsolute(element.getFigure(), bounds);
                int height = bounds.y - parent.getFigure().getBounds().y;
                if (height > maxHeight) {
                    maxHeight = height;
                    mostBottomElement = element;
                }
            }
        }
        return maxHeight + mostBottomElement.getFigure().getBounds().height + 20 < 250 ? 250 : maxHeight + mostBottomElement.getFigure().getBounds().height
                + 20;
    }

    protected DeferredSetValueCommand createSetCommand(final CreateViewAndElementRequest req, EStructuralFeature feature, Object value,
            EObject anEObjectInSameDomain) {
        return new DeferredSetValueCommand(new SetRequest(
                anEObjectInSameDomain,
                feature,
                value))
        {

            @Override
            public EObject getElementToEdit() {
                return (EObject) req.getViewAndElementDescriptor().getElementAdapter().getAdapter(EObject.class);
            }
        };
    }

    private String createErrorMessage(List<String> errors) {
        StringBuilder res = new StringBuilder();
        res.append(Messages.cannotExtractSubprocessMessage);
        res.append("\n");
        for (String error : errors) {
            res.append("* ");
            res.append(error);
            res.append("\n");
        }
        return res.toString();
    }

    /**
     * @param parts
     * @return
     */
    private List<String> evaluateErrors(List<IGraphicalEditPart> parts) {
        List<String> res = new ArrayList<String>();
        if (hasNoNode(parts)) {
            res.add(Messages.noNodeInSelectionForExtract);
        }
        for (IGraphicalEditPart part : parts) {
            Element item = (Element) part.resolveSemanticElement();
            if (!(item instanceof SourceElement || item instanceof TargetElement || item instanceof SequenceFlow)) {
                res.add(Messages.bind(Messages.cannotCopyElementBecauseOfType, item.getName()));
            }
        }
        if (!allPartsInSamePool(parts)) {
            res.add(Messages.selectedItemsNotInSameProcess);
        }
        List<TargetElement> sourceParts = findSources(parts);
        if (sourceParts.size() > 1) {
            StringBuilder sourceList = new StringBuilder();
            for (TargetElement srcPart : sourceParts) {
                sourceList.append(srcPart.getName());
                sourceList.append(" ");
            }
            res.add(Messages.bind(Messages.severalSubprocessEntryPoints, sourceList.toString()));
        }
        List<SourceElement> targetParts = findTarget(parts);
        if (targetParts.size() > 1) {
            StringBuilder targetList = new StringBuilder();
            for (SourceElement srcPart : targetParts) {
                targetList.append(srcPart.getName());
            }
            res.add(Messages.bind(Messages.severalSubprocessEntryPoints, targetList.toString()));
        }
        return res;
    }

    /**
     * @param allParts
     * @return
     */
    private List<SourceElement> findTarget(List<IGraphicalEditPart> parts) {
        Map<SourceElement, Integer> nbOutgoings = new HashMap<SourceElement, Integer>();
        for (IGraphicalEditPart part : parts) {
            EObject node = part.resolveSemanticElement();
            if (node instanceof SourceElement) {
                SourceElement source = (SourceElement) node;
                nbOutgoings.put(source, source.getOutgoing().size());
            }
        }
        Set<SourceElement> nodes = nbOutgoings.keySet();
        if (nodes.size() == 0) {
            return Collections.emptyList();
        } else {
            AbstractProcess process = ModelHelper.getParentProcess(nodes.iterator().next());
            for (SequenceFlow transition : getAllRelatedTransitions(nodes, process)) {
                nbOutgoings.put(transition.getSource(), nbOutgoings.get(transition.getSource()) - 1);
            }

            List<SourceElement> outPoints = new ArrayList<SourceElement>();
            for (Entry<SourceElement, Integer> entry : nbOutgoings.entrySet()) {
                if (entry.getValue() != 0) {
                    outPoints.add(entry.getKey());
                }
            }
            return outPoints;
        }
    }

    /**
     * @param nodes
     * @return
     */
    private List<SequenceFlow> getAllRelatedTransitions(Set<? extends EObject> nodes, AbstractProcess process) {
        List<SequenceFlow> res = new ArrayList<SequenceFlow>();
        for (EObject eObject : ModelHelper.getAllItemsOfType(process, ProcessPackage.Literals.SEQUENCE_FLOW)) {
            SequenceFlow flow = (SequenceFlow) eObject;
            if (nodes.contains(flow.getSource()) && nodes.contains(flow.getTarget())) {
                res.add(flow);
            }
        }
        return res;
    }

    /**
     * @param parts
     * @return
     */
    private List<TargetElement> findSources(List<IGraphicalEditPart> parts) {
        Map<TargetElement, Integer> nbIncomings = new HashMap<TargetElement, Integer>();
        for (IGraphicalEditPart part : parts) {
            EObject node = part.resolveSemanticElement();
            if (node instanceof TargetElement) {
                TargetElement target = (TargetElement) node;
                nbIncomings.put(target, target.getIncoming().size());
            }
        }
        Set<EObject> nodes = new HashSet<EObject>();
        for (IGraphicalEditPart part : parts) {
            if (!(part instanceof ConnectionEditPart)) {
                nodes.add(part.resolveSemanticElement());
            }
        }
        if (nodes.size() == 0) {
            return Collections.emptyList();
        } else {
            AbstractProcess process = ModelHelper.getParentProcess(nodes.iterator().next());
            for (SequenceFlow flow : getAllRelatedTransitions(nodes, process)) {
                nbIncomings.put(flow.getTarget(), nbIncomings.get(flow.getTarget()) - 1);
            }

            List<TargetElement> entryPoints = new ArrayList<TargetElement>();
            for (Entry<TargetElement, Integer> entry : nbIncomings.entrySet()) {
                if (entry.getValue() != 0) {
                    entryPoints.add(entry.getKey());
                }
            }
            return entryPoints;
        }
    }

    /**
     * @param parts
     * @return
     */
    private boolean hasNoNode(List<IGraphicalEditPart> parts) {
        for (IGraphicalEditPart part : parts) {
            if (part.resolveSemanticElement() instanceof SourceElement || part.resolveSemanticElement() instanceof TargetElement) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param parts
     * @return
     */
    private boolean allPartsInSamePool(List<IGraphicalEditPart> parts) {
        AbstractProcess container = null;
        for (IGraphicalEditPart part : parts) {
            if (part.resolveSemanticElement() instanceof SourceElement || part.resolveSemanticElement() instanceof TargetElement) {
                final AbstractProcess parentProcess = ModelHelper.getParentProcess(part.resolveSemanticElement());
                if (container == null) {
                    container = parentProcess;
                } else if (!parentProcess.equals(container)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @param processEp
     * @param flow
     * @return
     */
    protected ConnectionEditPart findConnectionPart(final MainProcessEditPart processEp, final Connection flow) {
        INodeEditPart nodePart = (INodeEditPart) GMFTools.findEditPart(processEp, flow.getSource());
        for (Object transition : nodePart.getSourceConnections()) {
            if (((ConnectionEditPart) transition).resolveSemanticElement().equals(flow)) {
                return (ConnectionEditPart) transition;
            }
        }
        return null;
    }

}
