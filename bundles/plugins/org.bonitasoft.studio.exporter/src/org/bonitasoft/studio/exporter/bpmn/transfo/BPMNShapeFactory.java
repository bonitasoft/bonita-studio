/**
 * Copyright (C) 2017 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.exporter.bpmn.transfo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.xml.namespace.QName;

import org.bonitasoft.studio.exporter.extension.IBonitaModelExporter;
import org.bonitasoft.studio.model.process.BoundaryEvent;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.eclipse.draw2d.AbstractRouter;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.diagram.ui.internal.util.LabelViewConstants;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.PointListUtilities;
import org.eclipse.gmf.runtime.notation.BasicCompartment;
import org.eclipse.gmf.runtime.notation.DecorationNode;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.omg.spec.bpmn.di.BPMNDiagram;
import org.omg.spec.bpmn.di.BPMNEdge;
import org.omg.spec.bpmn.di.BPMNLabel;
import org.omg.spec.bpmn.di.BPMNLabelStyle;
import org.omg.spec.bpmn.di.BPMNShape;
import org.omg.spec.bpmn.di.DiFactory;
import org.omg.spec.bpmn.model.TBoundaryEvent;
import org.omg.spec.dd.dc.Bounds;
import org.omg.spec.dd.dc.DcFactory;
import org.omg.spec.dd.dc.DcPackage;
import org.omg.spec.dd.dc.Font;

public class BPMNShapeFactory {

    private static final int HORIZONTAL_SPACING = 50;

    private IBonitaModelExporter modelExporter;

    private BPMNDiagram bpmnDiagram;

    public BPMNShapeFactory(IBonitaModelExporter modelExporter, BPMNDiagram bpmnDiagram) {
        this.modelExporter = modelExporter;
        this.bpmnDiagram = bpmnDiagram;
    }

    public BPMNShape createPool(String participantId, Pool pool) {
        final BPMNShape processShape = DiFactory.eINSTANCE.createBPMNShape();
        processShape.setBpmnElement(QName.valueOf(participantId));
        Node poolNode = modelExporter.getElementNotationNode(pool);
        final org.eclipse.gmf.runtime.notation.Bounds bounds = modelExporter.getBounds(poolNode);
        final Bounds processBounds = DcFactory.eINSTANCE.createBounds();

        processBounds.setHeight(computePoolHeight(pool));
        processBounds.setWidth(bounds.getWidth());
        processBounds.setX(bounds.getX());
        processBounds.setY(computePoolY(modelExporter.getPools(), pool, modelExporter));
        processShape.setBounds(processBounds);
        processShape.setId(modelExporter.getEObjectID(poolNode));
        processShape.setIsHorizontal(true);
        return processShape;
    }

    private int computePoolHeight(Pool pool) {
        List<Lane> lanes = pool.getElements().stream()
                .filter(Lane.class::isInstance)
                .map(Lane.class::cast)
                .collect(Collectors.toList());
        Node poolNode = modelExporter.getElementNotationNode(pool);
        final org.eclipse.gmf.runtime.notation.Bounds bounds = modelExporter.getBounds(poolNode);
        return lanes.isEmpty() ? bounds.getHeight() : lanes.stream()
                .map(modelExporter::getElementNotationNode)
                .map(modelExporter::getBounds)
                .mapToInt(org.eclipse.gmf.runtime.notation.Bounds::getHeight)
                .sum();
    }

    private int computePoolY(List<Pool> pools, Pool pool, IBonitaModelExporter modelExporter) {
        int index = modelExporter.getPools().indexOf(pool);
        int y = 0;
        while (index > 0) {
            y += (computePoolHeight(pools.get(index - 1))
                    + HORIZONTAL_SPACING);
            index--;
        }
        return y;
    }

    @SuppressWarnings("unchecked")
    public BPMNShape create(EObject bonitaElement, String bpmnElementId, Bounds parentBounds) {
        Node node = modelExporter.getElementNotationNode(bonitaElement);
        final BPMNShape elementShape = DiFactory.eINSTANCE.createBPMNShape();
        if (bonitaElement instanceof SubProcessEvent) {
            elementShape.setIsExpanded((boolean) node.getPersistedChildren().stream()
                    .filter(BasicCompartment.class::isInstance)
                    .map(BasicCompartment.class::cast)
                    .findFirst()
                    .map(compartment -> !((BasicCompartment) compartment).isCollapsed())
                    .orElse(true));
        }
        elementShape.setBpmnElement(QName.valueOf(bpmnElementId));
        org.eclipse.gmf.runtime.notation.Bounds bounds = modelExporter.getBounds(node);
        final Bounds elementBounds = DcFactory.eINSTANCE.createBounds();
        elementBounds.setHeight(bounds.getHeight());
        elementBounds.setWidth(bounds.getWidth());
        elementBounds.setX(bounds.getX() + parentBounds.getX());
        elementBounds.setY(bounds.getY() + parentBounds.getY());
        elementShape.setBounds(elementBounds);
        elementShape.setId(modelExporter.getEObjectID(node));

        attachLabel(node, bonitaElement instanceof Element ? ((Element) bonitaElement).getName() : "",
                elementShape);

        return elementShape;
    }

    public BPMNShape createLane(String laneId, Lane lane, Bounds parentBounds) {
        final BPMNShape laneShape = DiFactory.eINSTANCE.createBPMNShape();
        laneShape.setBpmnElement(QName.valueOf(laneId));
        Node laneNode = modelExporter.getElementNotationNode(lane);
        final org.eclipse.gmf.runtime.notation.Bounds bounds = modelExporter.getBounds(laneNode);
        final Bounds laneBounds = DcFactory.eINSTANCE.createBounds();
        laneBounds.setHeight(bounds.getHeight());
        laneBounds.setWidth(parentBounds.getWidth() - 30);
        laneBounds.setX(parentBounds.getX() + 30);
        laneBounds.setY(parentBounds.getY()
                + computeLaneY(modelExporter.getLanes(modelExporter.getParentPool(lane)), lane, modelExporter));
        laneShape.setBounds(laneBounds);
        laneShape.setId(modelExporter.getEObjectID(laneNode));
        laneShape.setIsHorizontal(true);
        return laneShape;
    }

    private int computeLaneY(List<Lane> lanes, Lane lane, IBonitaModelExporter modelExporter) {
        int index = lanes.indexOf(lane);
        int y = 0;
        while (index > 0) {
            y = y + modelExporter.getBounds(modelExporter.getElementNotationNode(lanes.get(index - 1))).getHeight();
            index--;
        }
        return y;
    }

    public BPMNShape createBoundary(BoundaryEvent boundaryEvent, TBoundaryEvent bpmnBoundary, Bounds parentBounds) {
        final BPMNShape boundaryShape = DiFactory.eINSTANCE.createBPMNShape();
        Node boundaryNode = modelExporter.getElementNotationNode(boundaryEvent);
        boundaryShape.setBpmnElement(QName.valueOf(bpmnBoundary.getId()));
        final org.eclipse.gmf.runtime.notation.Bounds bounds = modelExporter.getBounds(boundaryNode);
        int xOffset = bounds.getX();
        if (xOffset == 0) {
            xOffset = 20;
        }
        int yOffset = bounds.getY();
        if (yOffset == 0) {
            yOffset = (int) parentBounds.getHeight() - (bounds.getHeight() / 2);
        }
        final Bounds boundaryBounds = DcFactory.eINSTANCE.createBounds();
        boundaryBounds.setHeight(bounds.getHeight());
        boundaryBounds.setWidth(bounds.getWidth());
        boundaryBounds.setX(parentBounds.getX() + xOffset);
        boundaryBounds.setY(parentBounds.getY() + yOffset);
        boundaryShape.setBounds(boundaryBounds);
        boundaryShape.setId(modelExporter.getEObjectID(boundaryNode));
        attachLabel(boundaryNode, boundaryEvent.getName(), boundaryShape);
        return boundaryShape;
    }

    @SuppressWarnings("unchecked")
    private void attachLabel(Node node, String labelText,
            final BPMNShape elementShape) {
        final Font font = createFont(node);
        if (font != null) {
            final BPMNLabel label = DiFactory.eINSTANCE.createBPMNLabel();
            final BPMNLabelStyle labelStyle = getLabelStyle(font);
            label.setId(EcoreUtil.generateUUID());
            label.setLabelStyle(QName.valueOf(labelStyle.getId()));
            node.getPersistedChildren().stream()
                    .filter(DecorationNode.class::isInstance)
                    .map(DecorationNode.class::cast)
                    .findFirst()
                    .ifPresent(labelNode -> {
                        Location offsetLocation = modelExporter.getLocation((Node) labelNode);
                        if (offsetLocation != null) {
                            org.eclipse.gmf.runtime.notation.Bounds absoluteBounds = NotationFactory.eINSTANCE
                                    .createBounds();

                            //Here we use some default constant values to avoid a dependency on a set Display
                            //The output diemension values are sligthly the same between windows and linux
                            Dimension dimension = new Dimension((int) (labelText.length() * 7.42), (int) (11 * 1.6));
                            absoluteBounds.setWidth(dimension.width);
                            absoluteBounds.setHeight(dimension.height);

                            final Point location = toRectangle(elementShape.getBounds()).getBottom()
                                    .getTranslated(new Point(offsetLocation.getX(), offsetLocation.getY()));
                            location.translate(-dimension.width / 2, 0);

                            absoluteBounds.setY(location.y);
                            absoluteBounds.setX(location.x);
                            final Bounds elementBounds = DcFactory.eINSTANCE.createBounds();
                            elementBounds.setX(absoluteBounds.getX());
                            elementBounds.setY(absoluteBounds.getY());
                            elementBounds.setHeight(absoluteBounds.getHeight());
                            elementBounds.setWidth(absoluteBounds.getWidth());
                            label.setBounds(elementBounds);
                            elementShape.setBPMNLabel(label);
                        }
                    });
        }
    }

    private BPMNLabelStyle getLabelStyle(final Font font) {
        final Comparator<Font> fontComparator = new Comparator<Font>() {

            @Override
            public int compare(final Font font1, final Font font2) {
                for (final EStructuralFeature f : DcPackage.Literals.FONT.getEStructuralFeatures()) {
                    if (!font1.eGet(f).equals(font2.eGet(f))) {
                        return 1;
                    }
                }
                return 0;
            }
        };
        for (final BPMNLabelStyle style : bpmnDiagram.getBPMNLabelStyle()) {
            if (fontComparator.compare(style.getFont(), font) == 0) {
                return style;
            }
        }
        final BPMNLabelStyle labelStyle = DiFactory.eINSTANCE.createBPMNLabelStyle();
        labelStyle.setFont(font);
        labelStyle.setId(EcoreUtil.generateUUID());
        bpmnDiagram.getBPMNLabelStyle().add(labelStyle);
        return labelStyle;
    }

    private Font createFont(final View shape) {
        final FontStyle fontStyle = (FontStyle) shape.getStyle(NotationPackage.Literals.FONT_STYLE);
        if (fontStyle != null) {
            final Font font = DcFactory.eINSTANCE.createFont();
            font.setIsBold(fontStyle.isBold());
            font.setIsItalic(fontStyle.isItalic());
            font.setIsStrikeThrough(fontStyle.isStrikeThrough());
            font.setIsUnderline(fontStyle.isUnderline());
            font.setName(fontStyle.getFontName());
            font.setSize(fontStyle.getFontHeight());
            return font;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public BPMNEdge createBPMNEdge(final String bpmnFlowId, EObject bonitaElement) {
        Edge bonitaEdge = modelExporter.getElementNotationEdge(bonitaElement);
        if (bonitaEdge != null) {
            final BPMNEdge edge = DiFactory.eINSTANCE.createBPMNEdge();
            edge.setBpmnElement(QName.valueOf(bpmnFlowId));
            edge.setId(modelExporter.getEObjectID(bonitaEdge));

            PolylineConnection conn = createConnectorFigure(bonitaEdge);
            PointList points = conn.getPoints();
            for (int i = 0; i < points.size(); i++) {
                final org.omg.spec.dd.dc.Point sourcePoint = DcFactory.eINSTANCE.createPoint();
                Point point = points.getPoint(i);
                sourcePoint.setX(point.x);
                sourcePoint.setY(point.y);
                edge.getWaypoint().add(sourcePoint);
            }

            if (bonitaElement instanceof SequenceFlow) {
                bonitaEdge.getPersistedChildren().stream()
                        .filter(DecorationNode.class::isInstance)
                        .findFirst()
                        .ifPresent(decorationNode -> attachEdgeLabel((DecorationNode) decorationNode, edge,
                                ((SequenceFlow) bonitaElement).getName(), bonitaEdge));
            }
            return edge;
        }
        return null;
    }

    private void attachEdgeLabel(final DecorationNode decorationNode, final BPMNEdge edge, String labelText,
            Edge bonitaEdge) {
        Font font = createFont(bonitaEdge);
        if (font != null) {
            final BPMNLabel label = DiFactory.eINSTANCE.createBPMNLabel();
            Location relativeLocation = (Location) decorationNode.getLayoutConstraint();

            Point offSet = new Point(relativeLocation.getX(), relativeLocation.getY());
            org.eclipse.gmf.runtime.notation.Bounds absoluteBounds = NotationFactory.eINSTANCE.createBounds();
            PointList pList = new PointList();
            edge.getWaypoint().stream().map(wayPoint -> new PrecisionPoint(wayPoint.getX(), wayPoint.getY()))
                    .forEach(pList::addPoint);

            Point referencePoint = PointListUtilities.calculatePointRelativeToLine(pList, 0,
                    LabelViewConstants.MIDDLE_LOCATION, true);
            Point location = LabelHelper.calculatePointRelativeToPointOnLine(pList, referencePoint, offSet);
            //Here we use some default constant values to avoid a dependency on a set Display
            //The output diemension values are sligthly the same between windows and linux
            Dimension dimension = new Dimension((int) (labelText.length() * 7.42), (int) (11 * 1.6));
            absoluteBounds.setWidth(dimension.width);
            absoluteBounds.setHeight(dimension.height);
            location.translate(-1 * dimension.width / 2, -1 * dimension.height / 2);

            absoluteBounds.setWidth(dimension.width);
            absoluteBounds.setHeight(dimension.height);
            absoluteBounds.setX(location.x);
            absoluteBounds.setY(location.y);
            final Bounds elementBounds = DcFactory.eINSTANCE.createBounds();
            elementBounds.setX(absoluteBounds.getX());
            elementBounds.setY(absoluteBounds.getY());
            elementBounds.setHeight(absoluteBounds.getHeight());
            elementBounds.setWidth(absoluteBounds.getWidth());
            edge.setBPMNLabel(label);
        }
    }

    @SuppressWarnings("unchecked")
    private PolylineConnection createConnectorFigure(Edge bonitaEdge) {
        Bounds sourceLocation = getBPMNShapeBounds(modelExporter.getEObjectID(bonitaEdge.getSource()));
        Bounds targetLocation = getBPMNShapeBounds(modelExporter.getEObjectID(bonitaEdge.getTarget()));

        PolylineConnection conn = new PolylineConnection();
        AbstractRouter router = new CustomRectilinearRouter();
        conn.setConnectionRouter(router);
        final List<RelativeBendpoint> pointList = ((RelativeBendpoints) bonitaEdge.getBendpoints()).getPoints();
        List<org.eclipse.draw2d.RelativeBendpoint> figureConstraint = new ArrayList<>();
        for (int i = 0; i < pointList.size(); i++) {
            RelativeBendpoint relativeBendpoint = (RelativeBendpoint) pointList.get(i);
            IFigure sourceFigure = new Figure();
            sourceFigure.setBounds(toRectangle(sourceLocation));
            IFigure targetFigure = new Figure();
            targetFigure.setBounds(toRectangle(targetLocation));
            conn.setSourceAnchor(new CustomAnchor(sourceFigure));
            conn.setTargetAnchor(new CustomAnchor(targetFigure));
            org.eclipse.draw2d.RelativeBendpoint rbp = new org.eclipse.draw2d.RelativeBendpoint(conn);
            rbp.setRelativeDimensions(
                    new Dimension(relativeBendpoint.getSourceX(), relativeBendpoint.getSourceY()),
                    new Dimension(relativeBendpoint.getTargetX(), relativeBendpoint.getTargetY()));
            if (pointList.size() == 1) {
                rbp.setWeight(0.5f);
            } else {
                rbp.setWeight(i / ((float) pointList.size() - 1));
            }
            figureConstraint.add(rbp);
        }
        conn.setRoutingConstraint(figureConstraint);
        router.route(conn);
        return conn;
    }

    private Rectangle toRectangle(Bounds bounds) {
        return new PrecisionRectangle(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
    }

    private Bounds getBPMNShapeBounds(String shapeUUID) {
        return bpmnDiagram.getBPMNPlane().getDiagramElement().stream()
                .filter(BPMNShape.class::isInstance)
                .map(BPMNShape.class::cast)
                .filter(shape -> Objects.equals(shape.getId(), shapeUUID))
                .map(BPMNShape::getBounds)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("No shape found with id %s", shapeUUID)));
    }

}
