/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.diagram.tools;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.figures.CustomSVGFigure;
import org.bonitasoft.studio.common.figures.DecoratorSVGFigure;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.swt.graphics.Color;

/**
 * @author Mickael Istria
 */
public class FiguresHelper {

    public static final int EVENT_WIDTH = 30;

    public static final int BOUNDARY_EVENT_WIDTH = 25;

    public static final int BIG_EVENT_WIDTH = 34;

    public static final int GATEWAY_WIDTH = 43;

    public static final int ACTIVITY_HEIGHT = 50;

    public static final int ACTIVITY_WIDTH = 100;

    public static final int TEXTANNOTATION_HEIGHT = 50;

    public static final int TEXTANNOTATION_WIDTH = 100;

    private static final double TWO_PI = 2 * Math.PI;

    private static final int NB_POINTS_DRAW_CIRCLE = 50;

    public final static String CONNECTOR_DECORATOR = "connecteur.svgz";

    public final static String HUMAN_DECORATOR = "task.svgz";

    public final static String RECEIVE_DECORATOR = "enveloppe-vide.svgz";

    public final static String SEND_DECORATOR = "enveloppe-pleine.svgz";

    public final static String LOOP_DECORATOR = "loop.svgz";

    public final static String SCRIPT_DECORATOR = "scriptTask.svgz";

    public final static String SERVICE_DECORATOR = "serviceTask.svgz";

    public final static String SUBPROCESS_DECORATOR = "plus.svgz";

    public static final String TIMER_DECORATOR = "timer.svgz";

    public static final String SUBPROCESS_LOOP_DECORATOR = "plus_loop.svgz";

    public static final String SUBPROCESS_DECORATOR_COLLAPSE = "minus.svgz";

    public static String SUBPROCESS_DECORATOR_EXPAND = "plus.svgz";

    public static final String MULTI_SUB_DECORATOR = "plus_multi.svgz";

    public static final String MULTI_SEQUENTIAL_SUB_DECORATOR = "plus_multi_sequential.svgz";

    public static final String MULTIINSTANCE_DECORATOR = "multiInstance.svgz";

    public static final String MULTIINSTANCE_SEQUENTIAL_DECORATOR = "multiInstance-sequential.svgz";

    public static final String LOOP_SUB_DECORATOR = "plus_loop.svgz";

    public static final String SUBPROCEVENT_TIMER_DECORATOR = "subProcTimer.svgz";

    public static final String SUBPROCEVENT_SIGNAL_DECORATOR = "subProcSignal.svgz";

    public static final String SUBPROCEVENT_MESSAGE_DECORATOR = "subProcMessage.svgz";

    public static final String SUBPROCEVENT_ERROR_DECORATOR = "subProcError.svgz";

    private static final int LINE_LENGTH = 20;

    public static boolean AVOID_OVERLAP_ENABLE = true;

    public static IFigure getSelectedFigure(final EClass eClass, final int width, final int height, final Color foreground, final Color background) {

        final String eclassName = eClass.getName();
        final CustomSVGFigure svgFigure = new CustomSVGFigure();
        svgFigure.setURI("platform:/plugin/org.bonitasoft.studio.pics/icons/figures/" + eclassName + "_selected.svgz"); //$NON-NLS-1$ //$NON-NLS-2$

        if (ProcessPackage.Literals.ACTIVITY.isSuperTypeOf(eClass) || ProcessPackage.Literals.SUB_PROCESS_EVENT.equals(eClass)) {
            if (height != 0 && width != -1) {
                svgFigure.setSize(width, height);
            } else {
                svgFigure.setSize(ACTIVITY_WIDTH, ACTIVITY_HEIGHT);
            }

            if (foreground != null && background != null) {
                svgFigure.setColor(foreground, background);
            }

        } else if (ProcessPackage.Literals.GATEWAY.isSuperTypeOf(eClass)) {
            svgFigure.setSize(GATEWAY_WIDTH, GATEWAY_WIDTH);
        } else if (ProcessPackage.Literals.END_EVENT.isSuperTypeOf(eClass)) {
            svgFigure.setSize(EVENT_WIDTH, EVENT_WIDTH);
        } else if (ProcessPackage.Literals.START_EVENT.isSuperTypeOf(eClass)) {
            svgFigure.setSize(EVENT_WIDTH, EVENT_WIDTH);
        } else if (ProcessPackage.Literals.BOUNDARY_EVENT.isSuperTypeOf(eClass)) {
            svgFigure.setSize(BOUNDARY_EVENT_WIDTH + 6, BOUNDARY_EVENT_WIDTH + 6);
        } else if (ProcessPackage.Literals.TEXT_ANNOTATION.isSuperTypeOf(eClass)) {
            svgFigure.setSize(TEXTANNOTATION_WIDTH, TEXTANNOTATION_HEIGHT);
        } else {
            svgFigure.setSize(EVENT_WIDTH, EVENT_WIDTH);
        }
        return svgFigure;
    }

    /**
     * Convenient method to translate bounds to Absolute coordinate
     *
     * @param owner
     * @param b
     */
    public static void translateToAbsolute(final IFigure owner, final Rectangle b) {
        owner.translateToAbsolute(b);
        IFigure parentFigure = owner.getParent();
        while (parentFigure != null) {
            if (parentFigure instanceof Viewport) {
                final Viewport viewport = (Viewport) parentFigure;
                b.translate(
                        viewport.getHorizontalRangeModel().getValue(),
                        viewport.getVerticalRangeModel().getValue());
                parentFigure = parentFigure.getParent();
            }
            else {
                parentFigure = parentFigure.getParent();
            }
        }
    }

    /**
     * Convenient method to translate bounds to Absolute coordinate
     *
     * @param owner
     * @param p
     */
    public static void translateToAbsolute(IFigure owner, final Point p) {
        while (owner != null) {
            if (owner instanceof Viewport) {
                final Viewport viewport = (Viewport) owner;
                p.translate(
                        viewport.getHorizontalRangeModel().getValue(),
                        viewport.getVerticalRangeModel().getValue());
                owner = owner.getParent();
            }
            else {
                owner = owner.getParent();
            }
        }
    }

    public static Point handleCompartmentMargin(final IGraphicalEditPart ep, final int x, final int y, final boolean notAllowExentedMargins) {
        final IFigure editPartFigure = ep.getFigure();
        if (ep.getParent() instanceof ShapeCompartmentEditPart) {
            final GraphicalEditPart epCompartment = (GraphicalEditPart) ep.getParent();
            final Dimension parentSize = ((GraphicalEditPart) ep.getParent()).getFigure().getSize().getCopy();
            final Point parentLoc = ((GraphicalEditPart) ep.getParent()).getFigure().getBounds().getCopy().getLocation();
            if (!(parentSize.height == 0 && parentSize.width == 0)) {
                Rectangle newBounds = null;
                if (editPartFigure.getSize().width == 0 && editPartFigure.getSize().height == 0) {
                    newBounds = new Rectangle(new Point(x, y), editPartFigure.getPreferredSize());
                } else {
                    newBounds = new Rectangle(new Point(x, y), editPartFigure.getSize());
                }
                final EditPart parent = ep.getParent().getParent();

                for (final Object child : epCompartment.getChildren()) {

                    if (child instanceof IGraphicalEditPart && !child.equals(ep)) {
                        boolean compartment = false;
                        for (final Object o : ((IGraphicalEditPart) child).getChildren()) {
                            if (o instanceof ShapeCompartmentEditPart) {
                                if (((ShapeCompartmentEditPart) o).getCompartmentFigure().isExpanded() && ep.getTargetConnections().isEmpty()
                                        && ep.getSourceConnections().isEmpty()) {
                                    compartment = true;
                                }

                            }
                        }
                        if (compartment && parent.getChildren().contains(child)) {
                            newBounds = translate((IGraphicalEditPart) child, newBounds);
                        }
                        if (!compartment) {
                            newBounds = translate((IGraphicalEditPart) child, newBounds);
                        }
                    }
                }

                /*
                 * Handle Pool and Lanes inner margins
                 */
                if (editPartFigure.getBounds().height == 0) {
                    if (parentSize.height + parentLoc.y < newBounds.y + editPartFigure.getPreferredSize().height) {
                        if (notAllowExentedMargins) {
                            newBounds.y = parentSize.height + parentLoc.y - editPartFigure.getPreferredSize().height - 10;
                        }
                    } else if (newBounds.y - parentLoc.y - 10 <= 0) {
                        newBounds.y = 10 + parentLoc.y;
                    }
                } else {
                    if (parentSize.height + parentLoc.y < newBounds.y + editPartFigure.getBounds().height) {
                        if (notAllowExentedMargins) {
                            newBounds.y = parentSize.height + parentLoc.y - editPartFigure.getBounds().height - 10;
                        }
                    } else if (newBounds.y - parentLoc.y - 10 <= 0) {
                        newBounds.y = 10 + parentLoc.y;
                    }
                }

                if (editPartFigure.getBounds().width == 0) {
                    if (parentSize.width + parentLoc.x < editPartFigure.getPreferredSize().width + newBounds.x) {
                        if (notAllowExentedMargins) {
                            newBounds.x = parentSize.width + parentLoc.x - editPartFigure.getPreferredSize().width - 10;
                        }
                    } else if (x - parentLoc.x - 10 <= 0) {
                        newBounds.x = 25 + parentLoc.x;
                    }
                } else {
                    if (parentSize.width + parentLoc.x < editPartFigure.getBounds().width + newBounds.x) {
                        if (notAllowExentedMargins) {
                            newBounds.x = parentSize.width + parentLoc.x - editPartFigure.getBounds().width - 10;
                        }
                    } else if (x - parentLoc.x - 10 <= 0) {
                        newBounds.x = 25 + parentLoc.x;
                    }
                }

                final Point res = new Point(newBounds.x, newBounds.y);
                return res;
            }
        }

        return new Point(0, 0);
    }

    private static Rectangle translate(final IGraphicalEditPart child, final Rectangle newBounds) {
        final Rectangle childBounds = child.getFigure().getBounds().getCopy();
        final Bounds b = (Bounds) ((Node) child.getModel()).getLayoutConstraint();
        childBounds.x = b.getX();
        childBounds.y = b.getY();
        if (childBounds.width <= 0) {
            childBounds.width = child.getFigure().getPreferredSize().width;
        }
        if (childBounds.height <= 0) {
            childBounds.height = child.getFigure().getPreferredSize().height;
        }
        final Rectangle oldBound = newBounds.getCopy();
        childBounds.expand(new Insets(15, 15, 15, 15));

        if (childBounds.intersects(newBounds)) {

            final Set<Integer> directionTry = new HashSet<Integer>();
            final Set<Integer> allDirection = new HashSet<Integer>();
            allDirection.add(PositionConstants.NORTH_WEST);
            allDirection.add(PositionConstants.NORTH_EAST);
            allDirection.add(PositionConstants.SOUTH_EAST);
            allDirection.add(PositionConstants.SOUTH_WEST);

            while (childBounds.intersects(newBounds)) {

                if (directionTry.containsAll(allDirection)) {
                    return oldBound;
                }

                if (childBounds.x >= newBounds.x && childBounds.y >= newBounds.y) {
                    newBounds.translate(-1, -1);
                    directionTry.add(PositionConstants.NORTH_WEST);
                } else if (childBounds.x <= newBounds.x && childBounds.y >= newBounds.y) {
                    newBounds.translate(1, -1);
                    directionTry.add(PositionConstants.NORTH_EAST);
                } else if (childBounds.x <= newBounds.x && childBounds.y <= newBounds.y) {
                    newBounds.translate(1, 1);
                    directionTry.add(PositionConstants.SOUTH_EAST);
                } else if (childBounds.x >= newBounds.x && childBounds.y <= newBounds.y) {
                    newBounds.translate(-1, 1);
                    directionTry.add(PositionConstants.SOUTH_WEST);
                }
            }
        }
        return newBounds;
    }

    public static Dimension getMinimumCompartmentSize(final ShapeCompartmentEditPart ep) {
        final Dimension size = new Dimension(50, 100);

        for (final Object child : ep.getChildren()) {
            if (child instanceof GraphicalEditPart) {
                final int y = ((Integer) ((GraphicalEditPart) child).getStructuralFeatureValue(NotationPackage.eINSTANCE.getLocation_Y())).intValue();
                int h = ((GraphicalEditPart) child).getFigure().getPreferredSize().height + y;
                if (h < 100) {
                    h = 100;
                }
                final int w = ((IGraphicalEditPart) child).getFigure().getPreferredSize().width
                        + ((IGraphicalEditPart) child).getFigure().getBounds().getLocation().x;
                final Dimension tmp = new Dimension(w, h);
                if (size.height < tmp.height) {
                    size.height = tmp.height;
                }
                if (size.width < tmp.width) {
                    size.width = tmp.width;
                }
            }
        }
        // size.height =size.height +10;
        // size.width = size.width+10 ;
        return size;
    }

    public static void translateToRelative(final IFigure owner, final Rectangle b) {
        owner.translateToRelative(b);
    }

    public static void resizeActivitiesFigure(final IGraphicalEditPart parentEp, final String text) {

        final int lineNumber = text.length() / LINE_LENGTH;

        final ChangeBoundsRequest req = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);

        final int currentWidth = parentEp.getFigure().getSize().width;
        final int defaultWidth = parentEp.getFigure().getPreferredSize().width;
        final int withDeltaFromDefault = currentWidth - defaultWidth;

        final int currentHeight = parentEp.getFigure().getSize().height;
        final int defaultHeight = parentEp.getFigure().getPreferredSize().height;
        final int heightDeltaFromDefault = currentHeight - defaultHeight;

        req.setSizeDelta(new Dimension(20 * lineNumber - withDeltaFromDefault, 10 * lineNumber - heightDeltaFromDefault));
        req.setConstrainedResize(true);
        req.setCenteredResize(true);
        req.setResizeDirection(PositionConstants.CENTER);
        req.setEditParts(parentEp);
        // avoid to perform request on element creation (figure have no size)
        if (currentWidth > 0 && currentHeight > 0 && req.getSizeDelta().width > 0 && req.getSizeDelta().height > 0) {
            final Command cmd = parentEp.getCommand(req);
            if (cmd != null) {
                parentEp.getDiagramEditDomain().getDiagramCommandStack().execute(cmd);
            }
        }

    }

    public static Color getFeedbackColor(final EClass eClass) {
        if (ProcessPackage.Literals.ACTIVITY.isSuperTypeOf(eClass)) {
            return ColorRegistry.ACTIVITY_BLUE;
        } else if (ProcessPackage.Literals.GATEWAY.isSuperTypeOf(eClass)) {
            return ColorRegistry.GATEWAY_DARK_GREEN;
        } else if (ProcessPackage.Literals.END_EVENT.isSuperTypeOf(eClass)) {
            return ColorRegistry.ENDEVENT_DARK_RED;
        } else if (ProcessPackage.Literals.END_ERROR_EVENT.isSuperTypeOf(eClass)) {
            return ColorRegistry.ENDEVENT_DARK_RED;
        } else if (ProcessPackage.Literals.END_MESSAGE_EVENT.isSuperTypeOf(eClass)) {
            return ColorRegistry.ENDEVENT_DARK_RED;
        } else if (ProcessPackage.Literals.END_SIGNAL_EVENT.isSuperTypeOf(eClass)) {
            return ColorRegistry.ENDEVENT_DARK_RED;
        } else if (ProcessPackage.Literals.END_TERMINATED_EVENT.isSuperTypeOf(eClass)) {
            return ColorRegistry.ENDEVENT_DARK_RED;
        } else if (ProcessPackage.Literals.START_EVENT.isSuperTypeOf(eClass)) {
            return ColorRegistry.STARTEVENT_DARK_GREEN;
        } else if (ProcessPackage.Literals.START_ERROR_EVENT.isSuperTypeOf(eClass)) {
            return ColorRegistry.STARTEVENT_DARK_GREEN;
        } else if (ProcessPackage.Literals.START_TIMER_EVENT.isSuperTypeOf(eClass)) {
            return ColorRegistry.STARTEVENT_DARK_GREEN;
        } else if (ProcessPackage.Literals.START_MESSAGE_EVENT.isSuperTypeOf(eClass)) {
            return ColorRegistry.STARTEVENT_DARK_GREEN;
        } else if (ProcessPackage.Literals.START_SIGNAL_EVENT.isSuperTypeOf(eClass)) {
            return ColorRegistry.STARTEVENT_DARK_GREEN;
        } else if (ProcessPackage.Literals.BOUNDARY_EVENT.isSuperTypeOf(eClass)) {
            return ColorRegistry.ACTIVITY_BLUE;
        } else {
            return ColorRegistry.ACTIVITY_BLUE;
        }
    }

    public static DecoratorSVGFigure getDecoratorFigure(final String figurePath) {
        final DecoratorSVGFigure svgFigure = new DecoratorSVGFigure();
        svgFigure.setURI("platform:/plugin/org.bonitasoft.studio.pics/icons/decoration/svg/" + figurePath);
        svgFigure.setSize(16, 16);
        return svgFigure;
    }

    public static int getWidthFor(final IElementType elementType) {
        if (elementType.getId().contains("Event")) {
            return BIG_EVENT_WIDTH;
        } else if (elementType.getId().contains("Gateway")) {
            return GATEWAY_WIDTH;
        } else {
            return ACTIVITY_WIDTH;
        }
    }

    public static int getHeightFor(final IElementType elementType) {
        if (elementType.getId().contains("Event")) {
            return BIG_EVENT_WIDTH;
        } else if (elementType.getId().contains("Gateway")) {
            return GATEWAY_WIDTH;
        } else {
            return ACTIVITY_HEIGHT;
        }
    }

    public static PointList CirclePointList(final Rectangle anchRect) {
        final PointList points = new PointList(NB_POINTS_DRAW_CIRCLE);
        final double angle = TWO_PI / NB_POINTS_DRAW_CIRCLE;
        final Point center = anchRect.getCenter();
        final int centerX = center.x;
        final int centerY = center.y;

        final int halfWidth = anchRect.width / 2;
        final int halfHeight = anchRect.height / 2;

        double angleT = 0;
        while (angleT < TWO_PI) {
            points.addPoint((int) (halfWidth * Math.cos(angleT) + centerX), (int) (halfHeight * Math.sin(angleT) + centerY));
            angleT += angle;
        }
        // add last point, the same than the first point
        points.addPoint((int) (halfWidth * Math.cos(0) + centerX), (int) (halfHeight * Math.sin(0) + centerY));
        return points;
    }
}
