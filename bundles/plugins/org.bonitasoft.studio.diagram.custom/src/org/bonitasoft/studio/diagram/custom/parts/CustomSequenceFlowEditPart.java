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

package org.bonitasoft.studio.diagram.custom.parts;

import org.bonitasoft.studio.diagram.custom.Activator;
import org.bonitasoft.studio.diagram.custom.editPolicies.SequenceFlowCreationEditPolicy;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.process.Gateway;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.SequenceFlowConditionType;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SequenceFlowEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SequenceFlowNameEditPart;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Cursor;


/**
 * @author Romain Bioteau
 */
public class CustomSequenceFlowEditPart extends SequenceFlowEditPart {

    private static final int CONDITIONNAL = 0;
    private static final int DEFAULT = 1;
    private static final int ALL = 2;
    private final NotificationListener notificationListener = new NotificationListener() {

        @Override
        public void notifyChanged(final Notification notification) {
            handleNotificationEvent(notification);
        }
    };

    public CustomSequenceFlowEditPart(final View view) {
        super(view);
    }

    @Override
    protected boolean addFixedChild(final EditPart childEditPart) {
        if (childEditPart instanceof SequenceFlowNameEditPart) {
            ((SequenceFlowNameEditPart) childEditPart).setLabel(
                    ((CustomSequenceFlowFigure) getFigure()).getFigureLinkLabel());
            return true;
        }
        return false;
    }

    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.CREATION_ROLE,
                new SequenceFlowCreationEditPolicy());
    }

    @Override
    public void activate() {
        super.activate();
        final SequenceFlow modelElement = (SequenceFlow) resolveSemanticElement();
        DiagramEventBroker.getInstance(getEditingDomain()).addNotificationListener(modelElement.getCondition(), ExpressionPackage.Literals.EXPRESSION__CONTENT,
                notificationListener);
        checkDecorator();
    }

    @Override
    public void deactivate() {
        super.deactivate();
        final EObject modelElement = resolveSemanticElement();
        if (modelElement instanceof SequenceFlow) {
            DiagramEventBroker.getInstance(getEditingDomain()).removeNotificationListener(modelElement, notificationListener);
        }
    }

    @Override
    protected void handleNotificationEvent(final Notification notification) {
        final Object feature = notification.getFeature();
        if (feature.equals(ProcessPackage.Literals.SEQUENCE_FLOW__IS_DEFAULT)
                || feature.equals(ProcessPackage.Literals.SEQUENCE_FLOW__CONDITION)
                || feature.equals(ProcessPackage.Literals.SEQUENCE_FLOW__CONDITION_TYPE)
                || feature.equals(ExpressionPackage.Literals.EXPRESSION__CONTENT)) {
            checkDecorator();
        } else if (feature.equals(NotationPackage.Literals.ROUTING_STYLE__CLOSEST_DISTANCE)) {
            refreshRouterChange();
            refreshSourceAnchor();
            refreshTargetAnchor();
        } else if (feature.equals(NotationPackage.Literals.EDGE__TARGET)) {
            refreshRouterChange();
        }
        super.handleNotificationEvent(notification);
    }

    private void checkDecorator() {
        final SequenceFlow semantic = (SequenceFlow) resolveSemanticElement();
        if (semantic == null) {
            return;
        }
        final CustomSequenceFlowFigure sequenceFlowFigure = (CustomSequenceFlowFigure) getFigure();
        if (semantic.isIsDefault()) {
            sequenceFlowFigure.removeDecoration(ALL);
            sequenceFlowFigure.addDecoration(DEFAULT);
        } else {
            sequenceFlowFigure.removeDecoration(ALL);
            final boolean hasDiamond =
                    !(semantic.getSource() instanceof Gateway)
                            && (semantic.getConditionType() == SequenceFlowConditionType.DECISION_TABLE
                            || semantic.getConditionType() == SequenceFlowConditionType.EXPRESSION
                                    && semantic.getCondition() != null && semantic.getCondition().getContent() != null
                                    && !semantic.getCondition().getContent().isEmpty());
            if (hasDiamond) {
                sequenceFlowFigure.removeDecoration(ALL);
                sequenceFlowFigure.addDecoration(CONDITIONNAL);
            }
        }

    }

    /**
     * Choose the relevant children EditPart to use according to the preferences.
     * If the user choose to display the Name of transition return the first child,
     * if the user choose to display the condition on transition return the second child.
     * It permits to use the direct edit policies of the correct children
     */
    @Override
    public EditPart getPrimaryChildEditPart() {
        final boolean condition = Activator.getDefault().getBonitaPreferenceStore().getBoolean(BonitaPreferenceConstants.SHOW_CONDITION_ON_TRANSITION);
        if (condition) {
            if (getChildren().size() > 1) {
                return (EditPart) getChildren().get(1);
            }
        } else {
            if (getChildren().size() > 0) {
                return (EditPart) getChildren().get(0);
            }
        }

        return null;
    }

    @Override
    protected Connection createConnectionFigure() {
        return new CustomSequenceFlowFigure();
    }

    /**
     * @generated
     */
    public class CustomSequenceFlowFigure extends PolylineConnectionEx {

        
        /**
         * @generated
         */
        private WrappingLabel fFigureLinkLabel;
        /**
         * @generated
         */
        private WrappingLabel fFigureLinkConditionLabel;

        /**
         * @generated
         */
        public CustomSequenceFlowFigure() {
            createContents();
            setTargetDecoration(createTargetDecoration());
            losangeDecoration = createConditionalSourceDecoration();
            defaultDecoration = createDefaultSourceDecoration();
        }

        /**
         * @generated
         */
        private void createContents() {

            fFigureLinkLabel = new WrappingLabel();

            fFigureLinkLabel.setText("");

            this.add(fFigureLinkLabel);

            fFigureLinkConditionLabel = new WrappingLabel();

            fFigureLinkConditionLabel.setText("");

            this.add(fFigureLinkConditionLabel);

        }

        /**
         * @generated
         */
        private RotatableDecoration createTargetDecoration() {
            final PolygonDecoration df = new PolygonDecoration();
            df.setFill(true);
            df.setBackgroundColor(ColorConstants.black);
            final PointList pl = new PointList();
            pl.addPoint(getMapMode().DPtoLP(0)
                    , getMapMode().DPtoLP(0)
                    );
            pl.addPoint(getMapMode().DPtoLP(-1)
                    , getMapMode().DPtoLP(1)
                    );
            pl.addPoint(getMapMode().DPtoLP(-1)
                    , getMapMode().DPtoLP(-1)
                    );
            pl.addPoint(getMapMode().DPtoLP(0)
                    , getMapMode().DPtoLP(0)
                    );
            df.setTemplate(pl);
            df.setScale(getMapMode().DPtoLP(7)
                    , getMapMode().DPtoLP(3)
                    );
            return df;
        }

 
        public WrappingLabel getFigureLinkLabel() {
            return fFigureLinkLabel;
        }
        
        @Override
        public void setCursor(Cursor cursor) {
            super.setCursor(SharedCursors.HAND);
        }

        public WrappingLabel getFigureLinkConditionLabel() {
            return fFigureLinkConditionLabel;
        }

        private final RotatableDecoration losangeDecoration;
        private final RotatableDecoration defaultDecoration;
        private RotatableDecoration currentDecorator;

        public void removeDecoration(final int type) {
            if (type == CONDITIONNAL) {
                if (losangeDecoration.equals(currentDecorator)) {
                    setSourceDecoration(null);
                }
            } else if (type == DEFAULT) {
                if (defaultDecoration.equals(currentDecorator)) {
                    setSourceDecoration(null);
                }
            } else if (type == ALL) {
                setSourceDecoration(null);
            }
        }

        public void addDecoration(final int type) {
            if (type == CONDITIONNAL) {
                setSourceDecoration(losangeDecoration);
                currentDecorator = losangeDecoration;
            } else if (type == DEFAULT) {
                setSourceDecoration(defaultDecoration);
                currentDecorator = defaultDecoration;
            }
        }

        private RotatableDecoration createConditionalSourceDecoration() {
            final PolygonDecoration df = new PolygonDecoration();
            df.setFill(true);
            df.setLineWidth(1);
            df.setBackgroundColor(ColorConstants.white);
            final PointList pl = new PointList();
            pl.addPoint(0, 0);
            pl.addPoint(-1, 1);
            pl.addPoint(-2, 0);
            pl.addPoint(-1, -1);
            df.setTemplate(pl);
            df.setScale(getMapMode().DPtoLP(7), getMapMode().DPtoLP(5));
            return df;
        }

        private RotatableDecoration createDefaultSourceDecoration() {

            final PolylineDecoration df = new PolylineDecoration();
            final Point startPoint = new Point(-2, -1);
            final Point endPoint = new Point(-1, 1);
            df.setFill(true);
            df.setLineWidth(1);
            df.setOutline(true);
            df.addPoint(startPoint);
            df.addPoint(endPoint);
            final PointList pl = new PointList();
            pl.addPoint(startPoint);
            pl.addPoint(endPoint);
            df.setTemplate(pl);
            df.setStart(startPoint);
            df.setEnd(endPoint);
            df.setScale(getMapMode().DPtoLP(5), getMapMode().DPtoLP(5));

            return df;
        }

    }

}
