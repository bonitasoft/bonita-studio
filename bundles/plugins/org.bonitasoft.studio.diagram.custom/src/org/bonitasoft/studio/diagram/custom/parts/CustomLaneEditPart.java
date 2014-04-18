/*
 * /*
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

import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.diagram.custom.Activator;
import org.bonitasoft.studio.diagram.custom.editPolicies.CustomDragDropEditPolicy;
import org.bonitasoft.studio.diagram.custom.editPolicies.CustomLaneItemSemanticEditPolicy;
import org.bonitasoft.studio.diagram.custom.editPolicies.CustomResizableEditPolicyEx;
import org.bonitasoft.studio.diagram.custom.figures.VerticalLabel;
import org.bonitasoft.studio.diagram.custom.parts.CustomPoolEditPart.CustomPoolFigure;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.diagram.edit.parts.LaneEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 */
public class CustomLaneEditPart extends LaneEditPart {

    private CustomPoolCompartmentEditPart poolCompartment;

    /**
     * @return the poolCompartment
     */
    public CustomPoolCompartmentEditPart getPoolCompartment() {
        return poolCompartment;
    }

    public CustomLaneEditPart(View view) {
        super(view);
        addEditPartListener(new EditPartListener() {

            public void selectedStateChanged(EditPart arg0) {
                refreshBounds();
            }

            public void removingChild(EditPart arg0, int arg1) {

            }

            public void partDeactivated(EditPart arg0) {

            }

            public void partActivated(EditPart arg0) {
                if (!(getParent() instanceof CustomPoolCompartmentEditPart)) {
                    logWrongParent();
                } else {
                    poolCompartment = (CustomPoolCompartmentEditPart) getParent();
                }
            }

            public void childAdded(EditPart arg0, int arg1) {

            }
        });
    }

    @Override
    protected NodeFigure createNodePlate() {
        NodeFigure figure = new DefaultSizeNodeFigure(getMapMode().DPtoLP(975), getMapMode().DPtoLP(100));
        figure.setMinimumSize(new Dimension(975, 100));
        return figure;
    }

    protected IFigure setupContentPane(IFigure nodeShape) {
        if (nodeShape.getLayoutManager() == null) {
            ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
            layout.setSpacing(0);
            nodeShape.setLayoutManager(layout);
        }
        return nodeShape; // use nodeShape itself as contentPane
    }

    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        removeEditPolicy(EditPolicyRoles.SEMANTIC_ROLE);
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
                new CustomLaneItemSemanticEditPolicy());
        removeEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE);
        installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE,
                new CustomDragDropEditPolicy());

    }

    public EditPolicy getPrimaryDragEditPolicy() {
        EditPolicy result = super.getPrimaryDragEditPolicy();
        if (result instanceof ResizableEditPolicy) {
            ResizableEditPolicy ep = new CustomResizableEditPolicyEx();
            ep.setResizeDirections(PositionConstants.NORTH | PositionConstants.SOUTH);
            return ep;
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#showSourceFeedback(org.eclipse.gef.Request)
     */
    @Override
    public void showSourceFeedback(Request request) {
        if (request instanceof ChangeBoundsRequest) {
            if (request.getType().equals(RequestConstants.REQ_RESIZE)) {
                super.showSourceFeedback(request);
            }
        }

    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#showTargetFeedback(org.eclipse.gef.Request)
     */
    @Override
    public void showTargetFeedback(Request request) {
        // DO NOT SHOW FEEDBACK
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart#handleNotificationEvent(org.eclipse.emf.common.notify.Notification)
     */
    @Override
    protected void handleNotificationEvent(Notification notification) {
        Object feature = notification.getFeature();
        if (NotationPackage.eINSTANCE.getSize_Width().equals(feature)
                || NotationPackage.eINSTANCE.getSize_Height().equals(feature)
                || NotationPackage.eINSTANCE.getLocation_X().equals(feature)
                || NotationPackage.eINSTANCE.getLocation_Y().equals(feature)) {

            refreshBounds();
            for (CustomLaneEditPart lane : ((CustomPoolCompartmentEditPart) getParent()).getPoolLanes()) {
                if (!lane.equals(this)) {
                    lane.refreshBounds();
                }
            }
            ((CustomPoolEditPart) getParent().getParent()).refreshBounds();

        } else {
            super.handleNotificationEvent(notification);
        }
    }

    protected void refreshOverlapping() {
        RootEditPart root = getRoot();
        for (Object child : root.getChildren()) {
            if (child instanceof MainProcessEditPart) {
                for (Object child1 : ((MainProcessEditPart) child).getChildren()) {
                    if (child1 instanceof CustomPoolEditPart && !child1.equals(getParent())) {
                        ((CustomPoolEditPart) child1).refreshBounds();
                    }
                }
            }
        }
    }

    @Override
    protected void refreshBounds() {
        if (getParent() != null) {
            if (!(getParent() instanceof CustomPoolCompartmentEditPart)) {
                logWrongParent();
            } else {
                poolCompartment = (CustomPoolCompartmentEditPart) getParent();
                Rectangle compartmentBounds = ((GraphicalEditPart) getParent()).getFigure().getBounds().getCopy();

                int offset = 0;
                if (getParentCompartment() != null) {
                    List<CustomLaneEditPart> lanes = getParentCompartment().getPoolLanes();
                    for (CustomLaneEditPart i : lanes) {
                        if (!i.equals(this)) {
                            offset = offset + i.getFigure().getPreferredSize().height;
                        } else {
                            break;
                        }
                    }
                }
                CustomPoolEditPart parent = (CustomPoolEditPart) getParent().getParent();

                int width = parent.getFigure().getSize().width;
                int height = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Height())).intValue();

                if (height == -1) {
                    height = getFigure().getPreferredSize().height;
                }

                // Set first lane size to the pool size
                if (poolCompartment != null && poolCompartment.getPoolLanes() != null) {
                    height = computeLaneHeight(poolCompartment.getPoolLanes());
                }
                if (height < getFigure().getMinimumSize().height) {
                    height = getFigure().getMinimumSize().height;
                }

                if (width < getFigure().getMinimumSize().width) {
                    width = getFigure().getMinimumSize().width + 25;
                }

                int x = ((CustomPoolFigure) ((CustomPoolEditPart) poolCompartment.getParent()).getContentPane()).getLabelGridData().widthHint + 5;

                Dimension size = new Dimension(width - getMapMode().DPtoLP(x), height);
                getFigure().setPreferredSize(size);
                getFigure().setSize(size);

                int y = compartmentBounds.getTopLeft().y + offset;
                Point loc = new Point(getMapMode().DPtoLP(x), y);

                ((GraphicalEditPart) getParent()).setLayoutConstraint(
                        this,
                        getFigure(),
                        new Rectangle(loc, size));

            }
        }

    }

    private void logWrongParent() {
        EObject resolvedSemanticElement = resolveSemanticElement();
        String currentLaneName = resolvedSemanticElement instanceof Element ? ((Element) resolvedSemanticElement).getName() : resolvedSemanticElement
                .toString();
        EObject parentResolvedSemanticElement = ((GraphicalEditPart) getParent()).resolveSemanticElement();
        String containerName = parentResolvedSemanticElement instanceof Element ? ((Element) parentResolvedSemanticElement).getName()
                : parentResolvedSemanticElement.toString();
        BonitaStudioLog.warning("Warning: the lane" + currentLaneName + " is contained in another thing than a PoolCompartment. It is contained in: "
                + containerName, Activator.PLUGIN_ID);
    }

    private int computeLaneHeight(List<CustomLaneEditPart> poolLanes) {
        int height = 0;
        if (poolLanes.size() == 0 && ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Height())).intValue() == -1) {
            height = ((CustomPoolEditPart) getParent().getParent()).getFigure().getBounds().getCopy().height;
            if (height == 0) {
                height = ((Integer) ((CustomPoolEditPart) getParent().getParent()).getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Height()))
                        .intValue();
            }
        } else if (poolLanes.size() == 1 && ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Height())).intValue() == -1) {
            height = ((CustomPoolEditPart) getParent().getParent()).getFigure().getBounds().getCopy().height;
            if (height == 0) {
                height = ((Integer) ((CustomPoolEditPart) getParent().getParent()).getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Height()))
                        .intValue();
            }
        } else {
            if (((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Height())).intValue() == -1) {
                height = getFigure().getPreferredSize().height;
            } else {
                height = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Height())).intValue();
            }

        }

        return height;
    }

    private CustomPoolCompartmentEditPart getParentCompartment() {
        return poolCompartment;
    }

    @Override
    protected IFigure createNodeShape() {
        CustomLaneFigure figure = new CustomLaneFigure();
        figure.setUseLocalCoordinates(true);
        return primaryShape = figure;
    }

    public class CustomLaneFigure extends LaneFigure {

        private VerticalLabel fFigureLaneNameFigure;

        private RectangleFigure fFigureLaneContainerFigure;

        private GridData constraintNameContainerFigure0;

        public CustomLaneFigure() {

            GridLayout layoutThis = new GridLayout();
            layoutThis.numColumns = 2;
            layoutThis.makeColumnsEqualWidth = false;
            layoutThis.horizontalSpacing = 0;
            layoutThis.verticalSpacing = 0;
            layoutThis.marginWidth = 5;
            layoutThis.marginHeight = 5;
            this.setLayoutManager(layoutThis);

            this.setLineWidth(1);
            this.setOutline(true);
            this.setFill(true);
            this.setBackgroundColor(THIS_BACK);
            this.setPreferredSize(new Dimension(getMapMode().DPtoLP(975),
                    getMapMode().DPtoLP(100)));
            this.setMinimumSize(new Dimension(getMapMode().DPtoLP(975),
                    getMapMode().DPtoLP(100)));
            createContents();
        }

        @Override
        public void validate() {
            super.validate();
            if (fFigureLaneNameFigure != null) {
                fFigureLaneNameFigure.validate();
            }
        }

        @Override
        public void invalidate() {
            super.invalidate();
            if (fFigureLaneNameFigure != null) {
                fFigureLaneNameFigure.invalidate();
            }
        }

        private void createContents() {

            fFigureLaneNameFigure = new VerticalLabel();
            fFigureLaneNameFigure.setText(""); //$NON-NLS-1$

            constraintNameContainerFigure0 = new GridData();
            constraintNameContainerFigure0.verticalAlignment = GridData.FILL;
            constraintNameContainerFigure0.horizontalAlignment = GridData.CENTER;
            constraintNameContainerFigure0.horizontalIndent = 3;
            constraintNameContainerFigure0.horizontalSpan = 1;
            constraintNameContainerFigure0.verticalSpan = 1;
            constraintNameContainerFigure0.widthHint = 20;
            constraintNameContainerFigure0.grabExcessHorizontalSpace = false;
            constraintNameContainerFigure0.grabExcessVerticalSpace = true;
            this.add(fFigureLaneNameFigure, constraintNameContainerFigure0);

            fFigureLaneContainerFigure = new RectangleFigure();
            fFigureLaneContainerFigure.setBorder(null);
            fFigureLaneContainerFigure.setOutline(false);

            fFigureLaneContainerFigure.setLineWidth(0);
            fFigureLaneContainerFigure.setFill(false);
            fFigureLaneContainerFigure.setXOR(false);// BUG : XOR=true cause error on export as image

            GridData constraintFFigureLaneContainerFigure = new GridData();
            constraintFFigureLaneContainerFigure.verticalAlignment = GridData.FILL;
            constraintFFigureLaneContainerFigure.horizontalAlignment = GridData.FILL;
            constraintFFigureLaneContainerFigure.horizontalIndent = 0;
            constraintFFigureLaneContainerFigure.horizontalSpan = 1;
            constraintFFigureLaneContainerFigure.verticalSpan = 1;
            constraintFFigureLaneContainerFigure.grabExcessHorizontalSpace = true;
            constraintFFigureLaneContainerFigure.grabExcessVerticalSpace = true;
            this.add(fFigureLaneContainerFigure,
                    constraintFFigureLaneContainerFigure);

        }

        private boolean myUseLocalCoordinates = false;

        protected boolean useLocalCoordinates() {
            return myUseLocalCoordinates;
        }

        protected void setUseLocalCoordinates(boolean useLocalCoordinates) {
            myUseLocalCoordinates = useLocalCoordinates;
        }

        public WrappingLabel getFigureLaneNameFigure() {
            return fFigureLaneNameFigure;
        }

        public RectangleFigure getFigureLaneContainerFigure() {
            return fFigureLaneContainerFigure;
        }

        public GridData getLabelGridData() {
            return constraintNameContainerFigure0;
        }

    }

    static final Color THIS_BACK = ColorConstants.white;

    public CustomLaneCompartmentEditPart getCompartment() {
        for (Object child : getChildren()) {
            if (child instanceof CustomLaneCompartmentEditPart) {
                return (CustomLaneCompartmentEditPart) child;
            }
        }
        return null;
    }

    @Override
    protected void setFont(FontData fontData) {
        super.setFont(fontData);
        if (fontData != null && ((Element) resolveSemanticElement()).getName() != null) {
            final Font font = new Font(Display.getCurrent(), fontData);
            int height = FigureUtilities.getStringExtents(((Element) resolveSemanticElement()).getName(), font).height;
            font.dispose();
            ((CustomLaneFigure) getContentPane()).getLabelGridData().widthHint = height + 2;
        }
    }

    @Override
    public void activate() {
        super.activate();
        primaryShape.validate();
    }

    @Override
    public void deactivate() {
        super.deactivate();
        primaryShape.invalidate();
    }
}
