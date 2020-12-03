/*
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
package org.bonitasoft.studio.diagram.custom.parts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.common.figures.CollapsableEventSubprocessFigure;
import org.bonitasoft.studio.common.gmf.tools.CustomRubberbandDragTracker;
import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.common.gmf.tools.MultipleShapesHorizontalMoveTool;
import org.bonitasoft.studio.diagram.custom.editPolicies.CustomDragDropEditPolicy;
import org.bonitasoft.studio.diagram.custom.editPolicies.CustomSnapFeedbackPolicy;
import org.bonitasoft.studio.diagram.custom.editPolicies.CustomSubProcessResizableCompartmentEditPolicy;
import org.bonitasoft.studio.diagram.custom.figures.CustomSubprocessShapeCompartmentFigure;
import org.bonitasoft.studio.model.process.Container;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SubProcessEventSubProcessCompartment2EditPart;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutAnimator;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gef.tools.DeselectAllTracker;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.figures.ShapeCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.Properties;
import org.eclipse.gmf.runtime.diagram.ui.render.editparts.RenderedDiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.ChangePropertyValueRequest;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.StructuredSelection;

/**
 * @author Romain Bioteau
 */
public class CustomSubprocessEventCompartmentEditPart extends SubProcessEventSubProcessCompartment2EditPart {

    private static Rectangle SINGLETON = new Rectangle();
    private int beforeCollapseWidth;
    private int beforeCollapseHeight;
    private int beforeExpandWidth;
    private int beforeExpandHeight;
    private Integer beforeExpandColor;

    public CustomSubprocessEventCompartmentEditPart(View view) {
        super(view);
    }

    @Override
    public void setSelected(int value) {
        getParent().setSelected(value);
    }

    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        figure.setToolTip(null);
    }

    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE,
                new CustomSubProcessResizableCompartmentEditPolicy());
        removeEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE);
        installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE,
                new CustomDragDropEditPolicy());
        removeEditPolicy(EditPolicyRoles.SNAP_FEEDBACK_ROLE);
        installEditPolicy(EditPolicyRoles.SNAP_FEEDBACK_ROLE,
                new CustomSnapFeedbackPolicy());

    }

    @Override
    public Object getAdapter(Class key) {

        if (key == SnapToHelper.class) {
            EditPart parent = getParent();
            while (!(parent instanceof DiagramEditPart)) {
                parent = parent.getParent();
            }
            return GMFTools.getSnapHelper((GraphicalEditPart) parent);
        }

        return super.getAdapter(key);
    }

    @Override
    public IFigure createFigure() {
        ShapeCompartmentFigure scf = new CustomSubprocessShapeCompartmentFigure(getCompartmentName(), getMapMode());
        scf.getContentPane().setLayoutManager(getLayoutManager());
        scf.getContentPane().addLayoutListener(LayoutAnimator.getDefault());
        scf.setTitleVisibility(false);
        return scf;
    }

    @Override
    public ResizableCompartmentFigure getCompartmentFigure() {
        return (ResizableCompartmentFigure) getFigure();
    }

    @Override
    protected void handleNotificationEvent(Notification event) {
        super.handleNotificationEvent(event);
        Object feature = event.getFeature();
        if (NotationPackage.eINSTANCE.getDrawerStyle_Collapsed().equals(feature)) {
            updateBackgroundColor(event.getNewBooleanValue());
            updateCollapse(event.getNewBooleanValue());
        }
    }

    protected void updateBackgroundColor(boolean collapsed) {
        CollapsableEventSubprocessFigure figure = (CollapsableEventSubprocessFigure) ((IGraphicalEditPart) getParent())
                .getContentPane();
        if (!collapsed) {
            beforeExpandColor = (Integer) ((IGraphicalEditPart) getParent())
                    .getStructuralFeatureValue(NotationPackage.eINSTANCE.getFillStyle_FillColor());
            ChangePropertyValueRequest colorReq = new ChangePropertyValueRequest("Fill Color", Properties.ID_FILLCOLOR,
                    FigureUtilities.colorToInteger(ColorConstants.white));
            getParent().performRequest(colorReq);
            figure.setUseGradient(false);
        } else {
            figure.setUseGradient(true);
            ChangePropertyValueRequest colorReq = null;
            if (beforeExpandColor == null) {
                colorReq = new ChangePropertyValueRequest("Fill Color", Properties.ID_FILLCOLOR,
                        ((IGraphicalEditPart) getParent())
                                .getPreferredValue(NotationPackage.eINSTANCE.getFillStyle_FillColor()));
            } else {
                colorReq = new ChangePropertyValueRequest("Fill Color", Properties.ID_FILLCOLOR, beforeExpandColor);
            }
            getParent().performRequest(colorReq);
        }
    }

    public void setBeforeExpandColor(Integer beforeExpandColor) {
        this.beforeExpandColor = beforeExpandColor;
    }

    /**
     * Resize the parent editpart wether the compartment is collapsed or expanded
     * 
     * @param collapsed
     */
    protected void updateCollapse(boolean collapsed) {
        ZoomManager zm = ((RenderedDiagramRootEditPart) getRoot()).getZoomManager();
        getViewer().select(getParent());
        ChangeBoundsRequest req = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
        req.setEditParts(getParent());
        Dimension dimension = new Dimension();
        if (collapsed) {
            if (((IGraphicalEditPart) getParent()).getFigure().getBounds().width == beforeExpandWidth
                    && beforeExpandWidth != 0) {
                beforeCollapseWidth = getMaxWidth() - ((IGraphicalEditPart) getParent()).getFigure().getBounds().x + 15;
                beforeCollapseHeight = getMaxHeight() - ((IGraphicalEditPart) getParent()).getFigure().getBounds().y + 30;

            } else {
                beforeCollapseWidth = ((IGraphicalEditPart) getParent()).getFigure().getBounds().width;
                beforeCollapseHeight = ((IGraphicalEditPart) getParent()).getFigure().getBounds().height;
            }

            if (beforeExpandWidth == 0 || beforeExpandHeight == 0) {
                beforeExpandWidth = ((IGraphicalEditPart) getParent()).getFigure().getPreferredSize().width;
                beforeExpandHeight = ((IGraphicalEditPart) getParent()).getFigure().getPreferredSize().height;
            }

            dimension.width = beforeExpandWidth - beforeCollapseWidth;
            dimension.height = beforeExpandHeight - beforeCollapseHeight;
        } else {
            beforeExpandWidth = ((IGraphicalEditPart) getParent()).getFigure().getBounds().width;
            beforeExpandHeight = ((IGraphicalEditPart) getParent()).getFigure().getBounds().height;
            if (getChildren().isEmpty() && (beforeCollapseWidth == 0 || beforeCollapseHeight == 0)) {
                beforeCollapseWidth = ((IGraphicalEditPart) getParent()).getFigure().getPreferredSize().width * 4;
                beforeCollapseHeight = ((IGraphicalEditPart) getParent()).getFigure().getPreferredSize().height * 3;
            } else if (beforeCollapseWidth == 0 || beforeCollapseHeight == 0) {
                beforeCollapseWidth = getMaxWidth() - ((IGraphicalEditPart) getParent()).getFigure().getBounds().x + 30;
                beforeCollapseHeight = getMaxHeight() - ((IGraphicalEditPart) getParent()).getFigure().getBounds().y + 50;
            }
            beforeCollapseWidth = Math.round((float) (beforeCollapseWidth * zm.getZoom()));
            beforeCollapseHeight = Math.round((float) (beforeCollapseHeight * zm.getZoom()));
            beforeExpandHeight = Math.round((float) (beforeExpandHeight * zm.getZoom()));
            beforeExpandWidth = Math.round((float) (beforeExpandWidth * zm.getZoom()));

            dimension.width = beforeCollapseWidth - beforeExpandWidth;
            dimension.height = beforeCollapseHeight - beforeExpandHeight;
        }

        req.setSizeDelta(dimension);
        FiguresHelper.AVOID_OVERLAP_ENABLE = false;
        CollapsableEventSubprocessFigure figure = (CollapsableEventSubprocessFigure) ((IGraphicalEditPart) getParent())
                .getContentPane();
        if (!collapsed) {

            beforeCollapseWidth = Math
                    .round((float) (((IGraphicalEditPart) getParent()).getFigure().getBounds().width * zm.getZoom()));
            beforeCollapseHeight = Math
                    .round((float) (((IGraphicalEditPart) getParent()).getFigure().getBounds().height * zm.getZoom()));

            IGraphicalEditPart _container = (IGraphicalEditPart) getParent().getParent();
            IFigure referenceFigure = ((IGraphicalEditPart) getParent()).getFigure();
            Rectangle bounds = referenceFigure.getBounds().getCopy();
            referenceFigure.translateToAbsolute(bounds);
            IFigure parentFigure = referenceFigure.getParent();
            while (parentFigure != null) {
                if (parentFigure instanceof Viewport) {
                    Viewport viewport = (Viewport) parentFigure;
                    bounds.translate(
                            viewport.getHorizontalRangeModel().getValue(),
                            viewport.getVerticalRangeModel().getValue());
                    parentFigure = parentFigure.getParent();
                } else {
                    parentFigure = parentFigure.getParent();
                }
            }
            bounds = bounds.scale(zm.getZoom());

            List<IGraphicalEditPart> rightEps = getChildrenToMoveHorizontaly(bounds.x + bounds.width,
                    bounds.y + bounds.height, zm.getZoom());
            List<IGraphicalEditPart> bottomEps = getChildrenToMoveVerticaly(bounds.x, bounds.y + bounds.height,
                    zm.getZoom());

            ChangeBoundsRequest changBoundReq = new ChangeBoundsRequest(REQ_MOVE_CHILDREN);

            int xDelta = 0;
            for (IGraphicalEditPart child : rightEps) {
                Rectangle childBounds = child.getFigure().getBounds().getCopy();
                IFigure childFigure = child.getFigure();
                childFigure.translateToAbsolute(childBounds);
                IFigure parent = childFigure.getParent();
                while (parent != null) {
                    if (parent instanceof Viewport) {
                        Viewport viewport = (Viewport) parent;
                        childBounds.translate(
                                viewport.getHorizontalRangeModel().getValue(),
                                viewport.getVerticalRangeModel().getValue());
                        parent = parent.getParent();
                    } else {
                        parent = parent.getParent();
                    }
                }
                if (child.getFigure().getBounds().x - 10 < bounds.x + bounds.width + dimension.width) {
                    childBounds = childBounds.scale(zm.getZoom());
                    int tmpDelta = bounds.x + bounds.width + dimension.width - childBounds.x + 20;
                    if (tmpDelta > xDelta) {
                        xDelta = tmpDelta;
                    }
                }
            }

            changBoundReq.setEditParts(rightEps);
            changBoundReq.setMoveDelta(new Point(xDelta, 0));

            ChangeBoundsRequest changBoundVReq = new ChangeBoundsRequest(REQ_MOVE_CHILDREN);

            int yDelta = 0;
            for (IGraphicalEditPart child : bottomEps) {
                Rectangle childBounds = child.getFigure().getBounds().getCopy();
                IFigure childFigure = child.getFigure();
                childFigure.translateToAbsolute(childBounds);
                IFigure parent = childFigure.getParent();
                while (parent != null) {
                    if (parent instanceof Viewport) {
                        Viewport viewport = (Viewport) parent;
                        childBounds.translate(
                                viewport.getHorizontalRangeModel().getValue(),
                                viewport.getVerticalRangeModel().getValue());
                        parent = parent.getParent();
                    } else {
                        parent = parent.getParent();
                    }
                }
                if (childBounds.y - 10 < bounds.y + bounds.height + dimension.height) {
                    childBounds = childBounds.scale(zm.getZoom());
                    int tmpDelta = bounds.y + bounds.height + dimension.height - childBounds.y + 20;
                    if (tmpDelta > yDelta) {
                        yDelta = tmpDelta;
                    }
                }
            }
            changBoundVReq.setEditParts(bottomEps);
            changBoundVReq.setMoveDelta(new Point(0, yDelta));

            getDiagramEditDomain().getDiagramCommandStack().execute(_container.getCommand(changBoundReq));
            getDiagramEditDomain().getDiagramCommandStack().execute(_container.getCommand(changBoundVReq));

        }

        getParent().performRequest(req);
        FiguresHelper.AVOID_OVERLAP_ENABLE = true;

        updateLabelLayout(collapsed);
        figure.validate();

        getViewer().setSelection(new StructuredSelection(Collections.EMPTY_LIST));
        getViewer().setSelection(new StructuredSelection(getParent()));
    }

    protected List<IGraphicalEditPart> getChildrenToMoveVerticaly(int xPosition, int yPosition, double zoom) {
        IGraphicalEditPart _container = (IGraphicalEditPart) getParent().getParent();
        // the children that will be moved around
        List<IGraphicalEditPart> bottomChildren = new ArrayList<>();

        if (_container != null && _container.resolveSemanticElement() instanceof Container) {
            List children = null;
            if (_container.resolveSemanticElement() instanceof Container) {
                children = _container.getChildren();
            } else if (_container instanceof ShapeCompartmentEditPart) {
                children = ((ShapeCompartmentEditPart) _container).getChildren();
            }
            if (children == null) {
                throw new IllegalArgumentException("The part " + _container + " did not contain elements"); //$NON-NLS-1$ //$NON-NLS-2$
            }

            for (Object child : children) {
                if (child instanceof ShapeNodeEditPart && !child.equals(getParent())) {
                    MultipleShapesHorizontalMoveTool.setBoundsForOverlapComputation((IGraphicalEditPart) child, SINGLETON);
                    SINGLETON = SINGLETON.scale(zoom);
                    ((DiagramEditPart) getViewer().getContents()).getFigure().translateToRelative(SINGLETON);
                    if (SINGLETON.x + SINGLETON.width >= xPosition && SINGLETON.y > yPosition) {
                        bottomChildren.add((IGraphicalEditPart) child);
                    }
                }
            }

        }
        return bottomChildren;
    }

    private void updateLabelLayout(boolean collapsed) {
        GridData constraintFFigureEventSubProcessNameFigure = new GridData();

        constraintFFigureEventSubProcessNameFigure.horizontalIndent = 0;
        constraintFFigureEventSubProcessNameFigure.horizontalSpan = 1;
        constraintFFigureEventSubProcessNameFigure.verticalSpan = 1;
        constraintFFigureEventSubProcessNameFigure.grabExcessHorizontalSpace = true;

        IFigure figure = ((IGraphicalEditPart) getParent()).getContentPane();
        if (collapsed) {
            for (Object child : figure.getChildren()) {
                if (child instanceof WrappingLabel) {
                    constraintFFigureEventSubProcessNameFigure.grabExcessVerticalSpace = true;
                    constraintFFigureEventSubProcessNameFigure.verticalAlignment = GridData.FILL;
                    constraintFFigureEventSubProcessNameFigure.horizontalAlignment = GridData.FILL;
                    figure.setConstraint((IFigure) child, constraintFFigureEventSubProcessNameFigure);
                    ((WrappingLabel) child).repaint();
                }
            }
        } else {
            for (Object child : figure.getChildren()) {
                if (child instanceof WrappingLabel) {
                    constraintFFigureEventSubProcessNameFigure.verticalAlignment = GridData.BEGINNING;
                    constraintFFigureEventSubProcessNameFigure.horizontalAlignment = GridData.CENTER;
                    constraintFFigureEventSubProcessNameFigure.grabExcessVerticalSpace = false;
                    figure.setConstraint((IFigure) child, constraintFFigureEventSubProcessNameFigure);
                    ((WrappingLabel) child).repaint();
                }
            }
        }

    }

    private int getMaxHeight() {
        int max = 0;
        for (Object child : getChildren()) {
            if (child instanceof IGraphicalEditPart) {
                int childHeight = ((IGraphicalEditPart) child).getFigure().getBounds().y
                        + ((IGraphicalEditPart) child).getFigure().getBounds().height;
                if (childHeight > max) {
                    max = childHeight;
                }
            }
        }
        return max;
    }

    private int getMaxWidth() {
        int max = 0;
        for (Object child : getChildren()) {
            if (child instanceof IGraphicalEditPart) {
                int childWidth = ((IGraphicalEditPart) child).getFigure().getBounds().x
                        + ((IGraphicalEditPart) child).getFigure().getBounds().width;
                if (childWidth > max) {
                    max = childWidth;
                }
            }
        }
        return max;
    }

    @Override
    public DragTracker getDragTracker(Request req) {
        if (!supportsDragSelection()) {
            return super.getDragTracker(req);
        }

        if (req instanceof SelectionRequest
                && ((SelectionRequest) req).getLastButtonPressed() == 3) {
            return new DeselectAllTracker(this) {

                @Override
                protected boolean handleButtonDown(int button) {
                    getCurrentViewer().select(CustomSubprocessEventCompartmentEditPart.this);
                    return true;
                }
            };
        }
        return new CustomRubberbandDragTracker() {

            @Override
            protected void handleFinished() {
                if (getViewer().getSelectedEditParts().isEmpty()) {
                    getViewer().select(CustomSubprocessEventCompartmentEditPart.this);
                }
            }
        };
    }

    protected List<IGraphicalEditPart> getChildrenToMoveHorizontaly(int xPosition, int yPosition, double zoom) {
        IGraphicalEditPart _container = (IGraphicalEditPart) getParent().getParent();
        // the children that will be moved around
        List<IGraphicalEditPart> rightChildren = new ArrayList<>();

        if (_container != null && _container.resolveSemanticElement() instanceof Container) {
            List children = null;
            if (_container.resolveSemanticElement() instanceof Container) {
                children = _container.getChildren();
            } else if (_container instanceof ShapeCompartmentEditPart) {
                children = ((ShapeCompartmentEditPart) _container).getChildren();
            }
            if (children == null) {
                throw new IllegalArgumentException("The part " + _container + " did not contain elements"); //$NON-NLS-1$ //$NON-NLS-2$
            }
            // now iterate over the compartment children
            // and take those that are on the right.
            for (Object child : children) {
                if (child instanceof ShapeNodeEditPart) {
                    MultipleShapesHorizontalMoveTool.setBoundsForOverlapComputation((IGraphicalEditPart) child, SINGLETON);
                    SINGLETON = SINGLETON.scale(zoom);
                    ((DiagramEditPart) getViewer().getContents()).getFigure().translateToRelative(SINGLETON);
                    if (SINGLETON.x > xPosition && SINGLETON.y < yPosition
                            && SINGLETON.y + SINGLETON.height + 60 > yPosition) {
                        rightChildren.add((IGraphicalEditPart) child);
                    }
                }
            }
        }
        return rightChildren;
    }

}
