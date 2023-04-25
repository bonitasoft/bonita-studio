/*
 * Copyright (C) 2009-2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
import java.util.LinkedList;
import java.util.List;

import org.bonitasoft.studio.common.gmf.tools.CustomRubberbandDragTracker;
import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.diagram.custom.editPolicies.CustomDragDropEditPolicy;
import org.bonitasoft.studio.diagram.custom.editPolicies.CustomSnapFeedbackPolicy;
import org.bonitasoft.studio.diagram.custom.figures.CustomShapeCompartmentFigure;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolPoolCompartmentEditPart;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutAnimator;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gef.tools.DeselectAllTracker;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.figures.ShapeCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author Romain Bioteau
 */
public class CustomPoolCompartmentEditPart extends PoolPoolCompartmentEditPart {

    private final class CustomPoolCompartmentEditPartListener implements EditPartListener {

        @Override
        public void selectedStateChanged(final EditPart arg0) {
        }

        @Override
        public void removingChild(final EditPart ep, final int arg1) {
            ((CustomPoolEditPart) getParent()).refreshBoundsAfterRemove(ep);
            for (final CustomLaneEditPart l : getPoolLanes()) {
                l.refreshBounds();
            }
        }

        @Override
        public void partDeactivated(final EditPart arg0) {

        }

        @Override
        public void partActivated(final EditPart arg0) {
            ((CustomPoolEditPart) getParent()).refreshBounds();
        }

        @Override
        public void childAdded(final EditPart ep, final int arg1) {
            refreshPoolLanes();
            if (ep instanceof CustomLaneEditPart) {
                reparentContentToNewChild((CustomLaneEditPart) ep);
                ((CustomPoolEditPart) getParent()).refreshBounds();
            }
        }
    }

    private final List<CustomLaneEditPart> poolLanes;
    private final CustomPoolCompartmentEditPartListener listener;

    /**
     * @return the poolLanes
     */
    public List<CustomLaneEditPart> getPoolLanes() {
        return poolLanes;
    }

    public CustomPoolCompartmentEditPart(final View view) {
        super(view);
        poolLanes = new LinkedList<CustomLaneEditPart>();
        listener = new CustomPoolCompartmentEditPartListener();
    }

    @Override
    public Object getAdapter(final Class key) {

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
    protected void refreshVisuals() {
        super.refreshVisuals();
        figure.setToolTip(null);
    }

    public void refreshPoolLanes() {
        poolLanes.clear();
        final Pool p = (Pool) resolveSemanticElement();
        for (final Element e : p.getElements()) {
            if (e instanceof Lane) {
                final EditPart lEp = findEditPart(this, e);
                if (lEp instanceof CustomLaneNameEditPart) {
                    poolLanes.add((CustomLaneEditPart) lEp.getParent());
                } else if (lEp instanceof CustomLaneEditPart) {
                    poolLanes.add((CustomLaneEditPart) lEp);
                }
            }
        }
    }

    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        removeEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE);
        installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE,
                new CustomDragDropEditPolicy());
        removeEditPolicy(EditPolicyRoles.SNAP_FEEDBACK_ROLE);
        installEditPolicy(EditPolicyRoles.SNAP_FEEDBACK_ROLE,
                new CustomSnapFeedbackPolicy());

    }

    @Override
    public IFigure createFigure() {
        final ShapeCompartmentFigure scf = new CustomShapeCompartmentFigure(getCompartmentName(), getMapMode());
        scf.getContentPane().setLayoutManager(getLayoutManager());
        scf.getContentPane().addLayoutListener(LayoutAnimator.getDefault());
        scf.setTitleVisibility(false);
        return scf;
    }

    @Override
    public ResizableCompartmentFigure getCompartmentFigure() {
        return (ResizableCompartmentFigure) getFigure();
    }

    public void reparentContentToNewChild(final CustomLaneEditPart lane) {

        final List<EditPart> epToMove = new ArrayList<EditPart>();
        for (final Object o : getChildren()) {
            if (o instanceof CustomLaneEditPart) {

                for (final Object o1 : getChildren()) {
                    if (!(o1 instanceof CustomLaneEditPart)) {
                        epToMove.add((EditPart) o1);
                    }
                }
            }
        }

        if (poolLanes.size() == 1) {
            createMoveRequest(lane, epToMove);
        }

    }

    private void createMoveRequest(final GraphicalEditPart lane,
            final List<EditPart> epToMove2) {

        final ChangeBoundsRequest request = new ChangeBoundsRequest();
        request.setEditParts(epToMove2);
        request.setType(RequestConstants.REQ_ADD);
        CompartmentEditPart compartment = null;
        for (final Object o : lane.getChildren()) {
            if (o instanceof CompartmentEditPart) {
                compartment = (CompartmentEditPart) o;
            }
        }
        if (compartment == null) {
            compartment = (CompartmentEditPart) lane;
        }
        if (compartment.getEditPolicy(EditPolicyRoles.CREATION_ROLE) == null) {
            compartment.installEditPolicy(EditPolicyRoles.CREATION_ROLE, new CreationEditPolicy());
        }

        final CreationEditPolicy cep = (CreationEditPolicy) compartment.getEditPolicy(EditPolicyRoles.CREATION_ROLE);
        compartment.getDiagramEditDomain().getDiagramCommandStack().execute(cep.getCommand(request));
        getViewer().getRootEditPart().refresh();

    }

    @Override
    public DragTracker getDragTracker(final Request req) {
        if (!supportsDragSelection()) {
            return super.getDragTracker(req);
        }

        if (req instanceof SelectionRequest
                && ((SelectionRequest) req).getLastButtonPressed() == 3) {
            return new DeselectAllTracker(this) {

                @Override
                protected boolean handleButtonDown(final int button) {
                    getCurrentViewer().select(CustomPoolCompartmentEditPart.this);
                    return true;
                }
            };
        }
        return new CustomRubberbandDragTracker() {

            @Override
            protected void handleFinished() {
                if (getViewer().getSelectedEditParts().isEmpty()) {
                    getViewer().select(CustomPoolCompartmentEditPart.this);
                }
            }
        };
    }

    @Override
    public void activate() {
        addEditPartListener(listener);
        super.activate();
    }

    @Override
    public void deactivate() {
        super.deactivate();
        removeEditPartListener(listener);
    }
}
