/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.exporter.extension;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.model.IModelSearch;
import org.bonitasoft.studio.model.process.Container;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ANDGateway2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.Activity2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.CallActivity2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.InclusiveGateway2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.LaneEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ReceiveTask2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ScriptTask2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SendTask2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ServiceTask2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SubProcessEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.Task2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.TextAnnotation2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.XORGateway2EditPart;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;

/**
 * @author Romain Bioteau
 */
public class BonitaModelExporterImpl implements IBonitaModelExporter {

    private Resource resource;
    private IModelSearch modelSearch;

    public BonitaModelExporterImpl(Resource resource,IModelSearch modelSearch) {
        this.resource = requireNonNull(resource);
        this.modelSearch = modelSearch;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.exporter.extension.IBonitaModelExporter#getDiagram()
     */
    public MainProcess getMainProcess() {
        return resource.getContents().stream()
                .filter(MainProcess.class::isInstance)
                .map(MainProcess.class::cast)
                .findFirst()
                .orElseThrow(
                        () -> new IllegalStateException(String.format("No MainProcess found in resource %s", resource)));
    }

    public Diagram getDiagram() {
        return resource.getContents().stream()
                .filter(Diagram.class::isInstance)
                .map(Diagram.class::cast)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("No Diagram found in resource %s", resource)));
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.exporter.extension.IBonitaModelExporter#getPools()
     */
    public List<Pool> getPools() {
        return getMainProcess().getElements().stream()
                .filter(Pool.class::isInstance)
                .map(Pool.class::cast)
                .collect(Collectors.toList());
    }

    public List<FlowElement> getFlowElements(Container container) {
        return modelSearch.getAllItemsOfType(container, FlowElement.class);
    }

    public List<Lane> getLanes(Pool pool) {
        return modelSearch.getAllItemsOfType(pool, Lane.class);
    }

    public Node getElementNotationNode(EObject modelElement) {
        List<Node> views = new ArrayList<>();
        TreeIterator<EObject> iterator = getDiagram().eAllContents();
        iterator.forEachRemaining(notationElement -> {
            if (notationElement instanceof Node && Objects.equals(((Node) notationElement).getElement(), modelElement)) {
                views.add((Node) notationElement);
                iterator.prune();
            }
        });
        if (views.isEmpty()) {
            throw new IllegalStateException(String.format("No view found for %s", modelElement));
        }
        return views.get(0);
    }

    @Override
    public Bounds getBounds(Node view) {
        LayoutConstraint layoutConstraint = view.getLayoutConstraint();
        if (layoutConstraint instanceof Bounds) {
            Bounds bounds = NotationFactory.eINSTANCE.createBounds();
            bounds.setHeight(((Bounds) layoutConstraint).getHeight());
            bounds.setWidth(((Bounds) layoutConstraint).getWidth());
            bounds.setX(((Bounds) layoutConstraint).getX());
            bounds.setY(((Bounds) layoutConstraint).getY());
            Dimension defaultSize = getDefaultSize(view);
            if (bounds.getHeight() == -1) {
                bounds.setHeight(defaultSize.height);
            }
            if (bounds.getWidth() == -1) {
                bounds.setWidth(defaultSize.width);
            }
            return bounds;
        }
        return null;
    }

    private Dimension getDefaultSize(Node node) {
        switch (Integer.valueOf(node.getType())) {
            case PoolEditPart.VISUAL_ID:
                return new Dimension(1000, 250);
            case LaneEditPart.VISUAL_ID:
                return new Dimension(975, 250);
            case Activity2EditPart.VISUAL_ID:
                return new Dimension(100, 50);
            case Task2EditPart.VISUAL_ID:
                return new Dimension(100, 50);
            case ScriptTask2EditPart.VISUAL_ID:
                return new Dimension(100, 50);
            case ServiceTask2EditPart.VISUAL_ID:
                return new Dimension(100, 50);
            case SendTask2EditPart.VISUAL_ID:
                return new Dimension(100, 50);
            case ReceiveTask2EditPart.VISUAL_ID:
                return new Dimension(100, 50);
            case CallActivity2EditPart.VISUAL_ID:
                return new Dimension(100, 50);
            case TextAnnotation2EditPart.VISUAL_ID:
                return new Dimension(100, 50);
            case XORGateway2EditPart.VISUAL_ID:
                return new Dimension(43, 43);
            case ANDGateway2EditPart.VISUAL_ID:
                return new Dimension(43, 43);
            case InclusiveGateway2EditPart.VISUAL_ID:
                return new Dimension(43, 43);
            case SubProcessEvent2EditPart.VISUAL_ID:
                return new Dimension(100, 50);
            default:
                return new Dimension(30, 30);
        }
    }

    @Override
    public Location getLocation(Node view) {
        LayoutConstraint layoutConstraint = view.getLayoutConstraint();
        if (layoutConstraint instanceof Location) {
            return (Location) layoutConstraint;
        }
        return null;
    }

    @Override
    public Edge getElementNotationEdge(EObject connection) {
        return (Edge) getDiagram().getPersistedEdges().stream()
                .filter(edge -> Objects.equals(((Edge) edge).getElement(), connection))
                .findFirst()
                .orElse(null);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.exporter.extension.IBonitaModelExporter#getEObjectID(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public String getEObjectID(EObject eObject) {
        return modelSearch.getEObjectID(eObject);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.exporter.extension.IBonitaModelExporter#getParentPool(org.bonitasoft.studio.model.process.Lane)
     */
    @Override
    public Pool getParentPool(Lane lane) {
        return modelSearch.getDirectParentOfType(lane, Pool.class);
    }

}
