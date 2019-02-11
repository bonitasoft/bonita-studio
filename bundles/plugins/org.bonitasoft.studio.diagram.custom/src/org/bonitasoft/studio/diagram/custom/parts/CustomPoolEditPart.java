/*
 * Copyright (C) 2009-2011 BonitaSoft S.A.
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

import org.bonitasoft.studio.diagram.custom.editPolicies.CustomDragDropEditPolicy;
import org.bonitasoft.studio.diagram.custom.figures.VerticalLabel;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolEditPart;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.tools.DragEditPartsTrackerEx;
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
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 */
public class CustomPoolEditPart extends PoolEditPart {

    private CustomPoolCompartmentEditPart compartment;
    public Dimension currentSize;

    public CustomPoolEditPart(final View view) {
        super(view);
    }

    public Dimension getCurrentSize() {
        return currentSize;
    }

    private static int CONSTANT_LEFT_MARGIN = 20;
    private static final int CONSTANT_RIGHT_MARGIN = 600;
    public static final int CONSTANT_DEFAULT_HEIGHT = 250;
    public static final int MIN_POOL_WIDTH = 800;

    public static int getDefaultWidth() {
        int width = 0;
        if (PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null 
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell() != null) {
            width = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().getClientArea().width - CONSTANT_RIGHT_MARGIN;
        }
        return Math.max(MIN_POOL_WIDTH, width);
    }

    /**
     * @return the defaultHeight
     */
    public int getDefaultHeight() {
        return CONSTANT_DEFAULT_HEIGHT;
    }

    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        removeEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE);
        installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE,
                new CustomDragDropEditPolicy());

    }

    @Override
    public void showTargetFeedback(final Request request) {

    }

    @Override
    protected void handleNotificationEvent(final Notification notification) {
        super.handleNotificationEvent(notification);
        if (compartment == null) {
            for (final Object o : getChildren()) {
                if (o instanceof CustomPoolCompartmentEditPart) {
                    compartment = (CustomPoolCompartmentEditPart) o;
                }
            }
        }
    }

    @Override
    protected void refreshBounds() {
        if (compartment == null) {
            for (final Object o : getChildren()) {
                if (o instanceof CustomPoolCompartmentEditPart) {
                    compartment = (CustomPoolCompartmentEditPart) o;
                }
            }
        }
        int width = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Width())).intValue();

        if (width == -1 && getSize().width != 0) {
            width = getSize().width;
        }

        int height = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Height())).intValue();

        if (width == -1) {
            width = getFigure().getPreferredSize().getCopy().width;
        }
        if (height == -1) {
            height = getFigure().getPreferredSize().getCopy().height;
        }

        if (compartment != null && compartment.getPoolLanes() != null) {
            compartment.refreshPoolLanes();
        }

        if (compartment != null && compartment.getPoolLanes() != null && compartment.getPoolLanes().size() > 0) {
            height = 0;
            for (final CustomLaneEditPart lane : compartment.getPoolLanes()) {
                height = height + lane.getFigure().getPreferredSize().height;
            }
        }

        final Dimension size = new Dimension(width, height);
        getFigure().setPreferredSize(size);
        getFigure().setSize(size);
        currentSize = size;

        for (final Object o : getChildren()) {
            if (o instanceof GraphicalEditPart) {
                if (o instanceof CustomPoolCompartmentEditPart) {
                    for (final Object o2 : ((CustomPoolCompartmentEditPart) o).getChildren()) {
                        if (o2 instanceof CustomLaneEditPart) {
                            ((CustomLaneEditPart) o2).refreshBounds();
                        }
                    }
                }
            }
        }
    }

    @Override
    public DragTracker getDragTracker(final Request request) {
        return new DragEditPartsTrackerEx(this) {

            /**
             * Don't move on the side.
             *
             * @see org.eclipse.gef.tools.AbstractTool#getLocation()
             */
            @Override
            protected Point getLocation() {
                final Point p = super.getLocation();
                p.x = getStartLocation().x;
                return p;
            }
        };
    }

    @Override
    protected IFigure createNodeShape() {
        final CustomPoolFigure figure = new CustomPoolFigure();
        figure.setUseLocalCoordinates(true);
        return primaryShape = figure;
    }
    
    @Override
    protected NodeFigure createNodePlate() {
         NodeFigure nodeFigure = new DefaultSizeNodeFigure(new Dimension(-1, 100));
         nodeFigure.setMinimumSize(new Dimension(-1, 100));
        return nodeFigure;
    }

    public class CustomPoolFigure extends PoolFigure {

        /**
         * @generated
         */
        private VerticalLabel fFigurePoolNameFigure;
        /**
         * @generated
         */
        private RectangleFigure fFigurePoolContainerFigure;
        private GridData constraintPoolNameContainerFigure0;

        /**
         * @generated
         */
        public CustomPoolFigure() {
            final GridLayout layoutThis = new GridLayout();
            layoutThis.numColumns = 2;
            layoutThis.makeColumnsEqualWidth = false;
            layoutThis.horizontalSpacing = -2;
            layoutThis.verticalSpacing = 0;
            layoutThis.marginWidth = 0;
            layoutThis.marginHeight = 0;
            setLayoutManager(layoutThis);

            setLineWidth(1);
            setOutline(true);
            setBackgroundColor(THIS_BACK);
            final Dimension defaultSize = new Dimension(getMapMode().DPtoLP(getDefaultWidth()),
                    getMapMode().DPtoLP(getDefaultHeight()));
            this.setSize(defaultSize);
            this.setPreferredSize(defaultSize);
            setMinimumSize(new Dimension(MIN_POOL_WIDTH, getMapMode().DPtoLP(100)));
            currentSize = new Dimension(defaultSize);
            setLocation(new Point(20, 20));
            createContents();
        }
        
        @Override
        public void validate() {
            super.validate();
            if (fFigurePoolNameFigure != null) {
                fFigurePoolNameFigure.validate();
            }
        }

        @Override
        public void invalidate() {
            super.invalidate();
            if (fFigurePoolNameFigure != null) {
                fFigurePoolNameFigure.invalidate();
            }
        }

        /**
         * @generated
         */
        private void createContents() {

            final RectangleFigure poolNameContainerFigure0 = new RectangleFigure();

            poolNameContainerFigure0.setOutline(false);
            poolNameContainerFigure0.setFill(false);

            constraintPoolNameContainerFigure0 = new GridData();
            constraintPoolNameContainerFigure0.verticalAlignment = GridData.FILL;
            constraintPoolNameContainerFigure0.horizontalAlignment = GridData.FILL;
            constraintPoolNameContainerFigure0.horizontalIndent = 0;
            constraintPoolNameContainerFigure0.horizontalSpan = 1;
            constraintPoolNameContainerFigure0.verticalSpan = 1;
            constraintPoolNameContainerFigure0.widthHint = 20;
            constraintPoolNameContainerFigure0.grabExcessHorizontalSpace = false;
            constraintPoolNameContainerFigure0.grabExcessVerticalSpace = true;

            this.add(poolNameContainerFigure0, constraintPoolNameContainerFigure0);

            final GridLayout layoutPoolNameContainerFigure0 = new GridLayout();
            layoutPoolNameContainerFigure0.numColumns = 1;
            layoutPoolNameContainerFigure0.makeColumnsEqualWidth = true;
            layoutPoolNameContainerFigure0.horizontalSpacing = 10;
            layoutPoolNameContainerFigure0.verticalSpacing = 0;
            layoutPoolNameContainerFigure0.marginWidth = 0;
            layoutPoolNameContainerFigure0.marginHeight = 0;
            poolNameContainerFigure0.setLayoutManager(layoutPoolNameContainerFigure0);

            fFigurePoolNameFigure = new VerticalLabel();
            fFigurePoolNameFigure.setText(""); //$NON-NLS-1$

            final GridData constraintFFigurePoolNameFigure = new GridData();
            constraintFFigurePoolNameFigure.verticalAlignment = GridData.FILL;
            constraintFFigurePoolNameFigure.horizontalAlignment = GridData.FILL;
            constraintFFigurePoolNameFigure.horizontalIndent = 3;
            constraintFFigurePoolNameFigure.horizontalSpan = 1;
            constraintFFigurePoolNameFigure.verticalSpan = 1;
            constraintFFigurePoolNameFigure.grabExcessHorizontalSpace = false;
            constraintFFigurePoolNameFigure.grabExcessVerticalSpace = true;
            poolNameContainerFigure0.add(fFigurePoolNameFigure, constraintFFigurePoolNameFigure);

            fFigurePoolContainerFigure = new RectangleFigure();
            fFigurePoolContainerFigure.setOutline(true);
            fFigurePoolContainerFigure.setFill(false);
            fFigurePoolContainerFigure.setBorder(null);

            final GridData constraintFFigurePoolContainerFigure = new GridData();
            constraintFFigurePoolContainerFigure.verticalAlignment = GridData.FILL;
            constraintFFigurePoolContainerFigure.horizontalAlignment = GridData.FILL;
            constraintFFigurePoolContainerFigure.horizontalIndent = 5;
            constraintFFigurePoolContainerFigure.horizontalSpan = 1;
            constraintFFigurePoolContainerFigure.verticalSpan = 1;
            constraintFFigurePoolContainerFigure.grabExcessHorizontalSpace = true;
            constraintFFigurePoolContainerFigure.grabExcessVerticalSpace = true;
            this.add(fFigurePoolContainerFigure, constraintFFigurePoolContainerFigure);

        }

        /**
         * @generated
         */
        private boolean myUseLocalCoordinates = false;

        /**
         * @generated
         */
        @Override
        protected boolean useLocalCoordinates() {
            return myUseLocalCoordinates;
        }

        /**
         * @generated
         */
        protected void setUseLocalCoordinates(final boolean useLocalCoordinates) {
            myUseLocalCoordinates = useLocalCoordinates;
        }

        /**
         * @generated
         */
        @Override
        public WrappingLabel getFigurePoolNameFigure() {
            return fFigurePoolNameFigure;
        }

        /**
         * @generated
         */
        @Override
        public RectangleFigure getFigurePoolContainerFigure() {
            return fFigurePoolContainerFigure;
        }

        public GridData getLabelGridData() {
            return constraintPoolNameContainerFigure0;

        }

    }

    /**
     * @generated
     */
    static final Color THIS_BACK = ColorConstants.white;

    public void refreshBoundsAfterRemove(final EditPart ep) {
        if (compartment == null) {
            for (final Object o : getChildren()) {
                if (o instanceof CustomPoolCompartmentEditPart) {
                    compartment = (CustomPoolCompartmentEditPart) o;
                }
            }
        }
        int width = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Width())).intValue();

        if (width == -1 && getSize().width != 0) {
            width = getSize().width;
        }

        int height = 0;

        if (width == -1) {
            width = getFigure().getPreferredSize().getCopy().width;
        }
        if (compartment != null && compartment.getPoolLanes() != null) {
            compartment.refreshPoolLanes();
        }

        if (compartment != null && compartment.getPoolLanes() != null && compartment.getPoolLanes().size() > 0) {
            height = 0;
            for (final CustomLaneEditPart lane : compartment.getPoolLanes()) {
                if (!lane.equals(ep)) {
                    height = height + lane.getFigure().getPreferredSize().height;
                }
            }
        }

        if (height == 0) {
            height = getSize().height;
        }

        final Dimension size = new Dimension(width, height);
        getFigure().setPreferredSize(size);
        currentSize = size;

        final int y = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getLocation_Y())).intValue();

        final Point loc = new Point(CONSTANT_LEFT_MARGIN, y);

        ((GraphicalEditPart) getParent()).setLayoutConstraint(
                this,
                getFigure(),
                new Rectangle(loc, size));

        for (final Object o : getChildren()) {
            if (o instanceof GraphicalEditPart) {
                if (o instanceof CustomPoolCompartmentEditPart) {
                    for (final Object o2 : ((CustomPoolCompartmentEditPart) o).getChildren()) {
                        if (o2 instanceof CustomLaneEditPart) {
                            ((CustomLaneEditPart) o2).refreshBounds();
                        }
                    }
                }

            }
        }

    }

    @Override
    protected void setFont(final FontData fontData) {
        super.setFont(fontData);
        if (fontData != null && ((Element) resolveSemanticElement()).getName() != null) {
            final Font font = new Font(Display.getCurrent(), fontData);
            final int height = FigureUtilities.getStringExtents(((Element) resolveSemanticElement()).getName(),
                    font).height;
            font.dispose();
            ((CustomPoolFigure) getContentPane()).getLabelGridData().widthHint = height + 2;
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
