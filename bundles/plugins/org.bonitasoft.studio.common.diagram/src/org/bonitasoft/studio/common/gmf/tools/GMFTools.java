/**
 * Copyright (C) 2009-2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.gmf.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.gmf.tools.convert.ConvertBPMNTypeCommand;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.gmf.runtime.diagram.ui.editparts.BorderedBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.INodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.ruler.CompoundSnapToHelperEx;
import org.eclipse.gmf.runtime.diagram.ui.internal.ruler.SnapToGridEx;
import org.eclipse.gmf.runtime.diagram.ui.internal.ruler.SnapToGuidesEx;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramEditDomain;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequestFactory;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.Node;

/**
 * @author Mickael Istria
 * @author Aurelien Pupier
 */
public class GMFTools {

    public static final String PROCESS_DIAGRAM = "processDiagram";
    public static final String FORM_DIAGRAM = "formDiagram";
    public static final double MINIMAL_ZOOM_DISPLAY = 0.60;

    public static interface ElementTypeResolver {

        /**
         * @param parentEditPart
         * @param targetEClass
         * @return The {@link IElementType} to use to put an instance of
         *         targetEClass into parent
         */
        public IElementType getElementType(GraphicalEditPart parentEditPart, EClass targetEClass);

    }

    /**
     * 
     */
    public static GraphicalEditPart create(EClass targetEClass, final GraphicalEditPart node,
            ElementTypeResolver elementTypeResolver) {
        IElementType type = elementTypeResolver.getElementType(node, targetEClass);
        if (type != null) {
            Node newNode = createNode(node, type);

            final EObject targetElement = newNode.getElement();
            final GraphicalEditPart targetEditPart = (GraphicalEditPart) node.findEditPart(node, targetElement);
            targetEditPart.getViewer().select(targetEditPart);
            return node;
        } else {
            return null;
        }
    }

    /**
     * @param node
     * @param type
     * @return
     */
    public static Node createNode(final GraphicalEditPart node, IElementType type) {
        CreateViewRequest boundaryRequest = CreateViewRequestFactory.getCreateShapeRequest(type,
                node.getDiagramPreferencesHint());
        Command command = node.getCommand(boundaryRequest);

        final IDiagramEditDomain diagramEditDomain = node.getDiagramEditDomain();
        diagramEditDomain.getDiagramCommandStack().execute(command);

        IAdaptable targetAdapter = (IAdaptable) ((List<?>) boundaryRequest.getNewObject()).get(0);
        Node newNode = (Node) targetAdapter.getAdapter(EObject.class);
        return newNode;
    }

    /**
     * @param targetEClass
     * @param node
     * @return
     */
    public static GraphicalEditPart convert(EClass targetEClass, final GraphicalEditPart node,
            ElementTypeResolver elementTypeResolver, String editorType) {
        TransactionalEditingDomain editingDomain = node.getEditingDomain();
        final ConvertBPMNTypeCommand cmd = new ConvertBPMNTypeCommand(editingDomain, targetEClass, node,
                elementTypeResolver);
        try {
            OperationHistoryFactory.getOperationHistory().execute(cmd, null, null);
        } catch (ExecutionException e) {
            BonitaStudioLog.error(e);
        }
        GraphicalEditPart targetEditPart = (GraphicalEditPart) cmd.getCommandResult().getReturnValue();
        return targetEditPart;
    }

    /**
     * returns the the appropriate snap helper(s), this method will always reach
     * for the first reachable DiagramEditPart using the passed edit part, then
     * use this Diagram edit part to get the snap helper
     * 
     * @param editPart
     *        , edit part to get the snap helper for
     * @return
     */
    static public Object getSnapHelper(GraphicalEditPart editPart) {
        // get the diagram Edit Part
        GraphicalEditPart diagramEditPart = editPart;

        if (diagramEditPart == null) {
            return null;
        }

        List<SnapToHelper> snapStrategies = new ArrayList<>();
        EditPartViewer viewer = diagramEditPart.getViewer();

        Boolean val = (Boolean) editPart.getViewer().getProperty(RulerProvider.PROPERTY_RULER_VISIBILITY);

        if (val != null && val.booleanValue()) {
            snapStrategies.add(new SnapToGuidesEx(diagramEditPart));
        }

        val = (Boolean) viewer.getProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED);
        if (val != null && val.booleanValue()) {
            snapStrategies.add(new CustomSnapToGeometryEx(diagramEditPart));
        }

        val = (Boolean) viewer.getProperty(SnapToGrid.PROPERTY_GRID_ENABLED);

        if (val != null && val.booleanValue()) {
            snapStrategies.add(new SnapToGridEx(diagramEditPart));
        }

        if (snapStrategies.size() == 0) {
            return null;
        }

        if (snapStrategies.size() == 1) {
            return snapStrategies.get(0);
        }

        SnapToHelper ss[] = new SnapToHelper[snapStrategies.size()];
        for (int i = 0; i < snapStrategies.size(); i++) {
            ss[i] = snapStrategies.get(i);
        }
        return new CompoundSnapToHelperEx(ss);
    }

    public static IGraphicalEditPart findEditPart(EditPart containerEditPart, EObject elementToFind) {

        final EObject containerElement = ((IGraphicalEditPart) containerEditPart).resolveSemanticElement();
        if (ModelHelper.getEObjectID(containerElement) != null
                && ModelHelper.getEObjectID(containerElement).equals(ModelHelper.getEObjectID(elementToFind))) {
            return (IGraphicalEditPart) containerEditPart;
        }

        for (Object child : containerEditPart.getChildren()) {
            if (child instanceof IGraphicalEditPart) {
                final EObject childResolvedSemanticElement = ((IGraphicalEditPart) child).resolveSemanticElement();
                final String eObjectID = ModelHelper.getEObjectID(childResolvedSemanticElement);
                if (eObjectID != null && Objects.equals(eObjectID, ModelHelper.getEObjectID(elementToFind))) {
                    return (IGraphicalEditPart) child;
                }
                if (!((IGraphicalEditPart) child).getTargetConnections().isEmpty()
                        || !((IGraphicalEditPart) child).getSourceConnections().isEmpty()) {
                    for (Object ep : ((IGraphicalEditPart) child).getTargetConnections()) {
                        final EObject resolveSemanticElement = ((IGraphicalEditPart) ep).resolveSemanticElement();
                        if (resolveSemanticElement != null
                                && Objects.equals(ModelHelper.getEObjectID(resolveSemanticElement),
                                        (ModelHelper.getEObjectID(elementToFind)))) {
                            return (IGraphicalEditPart) ep;
                        }
                    }

                    for (Object ep : ((IGraphicalEditPart) child).getSourceConnections()) {
                        final EObject resolveSemanticElement = ((IGraphicalEditPart) ep).resolveSemanticElement();
                        if (resolveSemanticElement != null && Objects.equals(
                                ModelHelper.getEObjectID(resolveSemanticElement), ModelHelper.getEObjectID(elementToFind))) {
                            return (IGraphicalEditPart) ep;
                        }
                    }
                }

                IGraphicalEditPart ep = findEditPart((EditPart) child, elementToFind);
                if (ep != null) {
                    return ep;
                }
            }
        }
        return null;

    }

    /**
     * @param toCopyElements
     * @return
     */
    public static List<IGraphicalEditPart> addMissingConnectionsAndBoundaries(List<IGraphicalEditPart> toCopyElements) {
        List<IGraphicalEditPart> res = new ArrayList<>();
        // first, add nodes
        for (IGraphicalEditPart part : toCopyElements) {
            if (part instanceof ShapeNodeEditPart) {
                res.add(part);
            }
        }
        // then, add boundaries
        List<IGraphicalEditPart> boundaries = new ArrayList<>();
        for (IGraphicalEditPart part : res) {
            for (Object child : part.getChildren()) {
                if (child instanceof BorderedBorderItemEditPart && !res.contains(child)) {
                    boundaries.add((BorderedBorderItemEditPart) child);
                }
            }
        }
        res.addAll(boundaries);
        // finally, add transitions between contained nodes
        List<IGraphicalEditPart> transitions = new ArrayList<>();
        for (IGraphicalEditPart part : res) {
            if (part instanceof INodeEditPart) {
                INodeEditPart node = (INodeEditPart) part;
                for (Object conn : node.getSourceConnections()) {
                    ConnectionEditPart connection = (ConnectionEditPart) conn;
                    if (res.contains(connection.getTarget()) && !transitions.contains(conn)) {
                        transitions.add((ConnectionEditPart) conn);
                    }
                }
            }
        }
        res.addAll(transitions);
        return res;
    }

    /**
     * @param parts
     * @return
     */
    public static IGraphicalEditPart getFirstNodeShapeEditPart(List<IGraphicalEditPart> parts) {
        int i = 0;
        IGraphicalEditPart refNode = null;
        while (refNode == null) {
            if (parts.get(i) instanceof ShapeNodeEditPart) {
                refNode = parts.get(i);
            } else {
                i++;
            }
        }
        return refNode;
    }

}
