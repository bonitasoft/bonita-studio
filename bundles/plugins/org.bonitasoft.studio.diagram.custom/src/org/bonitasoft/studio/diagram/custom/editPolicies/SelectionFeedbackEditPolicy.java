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
package org.bonitasoft.studio.diagram.custom.editPolicies;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.bpm.model.process.BoundaryEvent;
import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.common.figures.CustomSVGFigure;
import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.ZoomListener;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.graphics.ColorRegistry;
import org.eclipse.gmf.runtime.notation.LineStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author Mickael Istria
 * @author Romain Bioteau
 */
public class SelectionFeedbackEditPolicy extends SelectionEditPolicy implements ZoomListener, LayerConstants {

    public static final Object BONITA_SELECTION_FEEDBACK_ROLE = "BonitaSelectionFeedback"; //$NON-NLS-1$
    public static final String BONITA_FEEDBACK_LAYER = "BonitaFeedbackLayer"; //$NON-NLS-1$

    private static Map<EditPart, IFigure> feedbackFigures = new HashMap<EditPart, IFigure>();

    private RoundedRectangle feedBackFigure;
    private IFigure layer;
    private FigureListener figureListener;
    private int selectionColor;
    private boolean useSelectionColor;
    private ZoomManager zoomManager;
    private boolean isActive;

    public SelectionFeedbackEditPolicy(final EClass eClass) {
        feedBackFigure = new RoundedRectangle() {

            @Override
            protected void outlineShape(Graphics graphics) {
                // None
            }

            @Override
            protected void fillShape(Graphics graphics) {
                var backGroundColor = useSelectionColor ? ColorRegistry.getInstance().getColor(selectionColor)
                        : FiguresHelper.getFeedbackColor(eClass);
                graphics.setAdvanced(true);
                graphics.setBackgroundColor(backGroundColor);
                graphics.setAlpha(30);
                graphics.fillRoundRectangle(getBounds(), 20, 20);
            };
        };

        figureListener = source -> {
            hideFeedback();
            List<?> selectedEditPart = getHost().getViewer().getSelectedEditParts();
            if (selectedEditPart.contains(getHost()) && !figureIsDisplayed()) {
                showFeedback(zoomManager.getZoom());
            }
        };

    }

    @Override
    public void activate() {
        super.activate();
        this.zoomManager = ((DiagramRootEditPart) getHost().getRoot()).getZoomManager();
        zoomManager.addZoomListener(this);
        var sourceFigure = getHostFigure();
        sourceFigure.addFigureListener(figureListener);
        isActive = true;
    }

    @Override
    public void showSelection() {
        if (isActive) {
            if (zoomManager != null) {
                showFeedback(zoomManager.getZoom());
            } else {
                showFeedback(1);
            }
        }
    }

    void showFeedback(double zoom) {
        if (layer == null) {
            layer = getLayer(BONITA_FEEDBACK_LAYER);
        }

        if (figureIsDisplayed()) {
            return;
        }
        IFigure hostFigure = getHostFigure();
        if (((IGraphicalEditPart) getHost()).resolveSemanticElement() instanceof BoundaryEvent) {
            
            Rectangle bounds = hostFigure.getBounds().getCopy();
            //get the absolute coordinate of bounds
            hostFigure.translateToAbsolute(bounds);

            IFigure parentFigure = hostFigure.getParent();
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

            bounds.getSize().performScale(zoomManager.getZoom());
            int scaled = (int) (Math.round(15 * zoomManager.getZoom()));
            Insets in = new Insets(scaled, scaled, scaled, scaled);
            feedBackFigure.setBounds(bounds.getCopy().expand(in));

            Point dest = new Point(
                    bounds.x - 1
                            - (feedBackFigure.getSize().width - hostFigure.getBounds().width * zoomManager.getZoom())
                                    / 2,
                    bounds.y - (feedBackFigure.getSize().height
                            - hostFigure.getBounds().height * zoomManager.getZoom()) / 2);

            feedBackFigure.setLocation(dest);

        } else {
            if (((IGraphicalEditPart) getHost()).getContentPane() instanceof CustomSVGFigure) {
                useSelectionColor = true;
                selectionColor = ((LineStyle) ((View) getHost().getModel())
                        .getStyle(NotationPackage.eINSTANCE.getLineStyle())).getLineColor();
            } else {
                useSelectionColor = false;
            }

            Rectangle bounds = hostFigure.getBounds().getCopy();
            //get the absolute coordinate of bounds
            feedBackFigure.translateToAbsolute(bounds);

            IFigure parentFigure = feedBackFigure.getParent();
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

            bounds.getSize().performScale(zoomManager.getZoom());
            int scaled = (int) (Math.round(15 * zoomManager.getZoom()));
            Insets in = new Insets(scaled, scaled, scaled, scaled);
            feedBackFigure.setBounds(bounds.getCopy().expand(in));

            Point dest = new Point(
                    bounds.x - 1 - (feedBackFigure.getSize().width - hostFigure.getBounds().width) / 2,
                    bounds.y - (feedBackFigure.getSize().height - hostFigure.getBounds().height) / 2);

            feedBackFigure.setLocation(dest);
        }

        feedbackFigures.put(getHost(), feedBackFigure);
        feedBackFigure.setVisible(true);
        feedBackFigure.repaint();

        if (!layer.getChildren().contains(feedBackFigure)) {
            layer.add(feedBackFigure, 0);
        }

    }

    @Override
    public void hideSelection() {
        if (isActive) {
            hideFeedback();
        }
    }

    private void hideFeedback() {
        if (figureIsDisplayed()) {
            layer.remove(feedBackFigure);
        }
        feedbackFigures.remove(getHost());
    }

    /**
     * @return
     */
    private boolean figureIsDisplayed() {
        return feedBackFigure != null && layer != null && layer.getChildren().contains(feedBackFigure);
    }

    public static IFigure getFeedbackFigure(EditPart editPart) {
        return feedbackFigures.get(editPart);
    }

    @Override
    public void deactivate() {
        super.deactivate();
        zoomManager.removeZoomListener(this);
        feedbackFigures.clear();
        getHostFigure().removeFigureListener(figureListener);
        layer = null;
        feedBackFigure = null;
        isActive = false;
    }

    @Override
    protected IFigure getLayer(Object layer) {
        if (layer.equals(BONITA_FEEDBACK_LAYER)) {
            if (getHost() instanceof IGraphicalEditPart) {
                if (((IGraphicalEditPart) getHost()).resolveSemanticElement() instanceof BoundaryEvent) {
                    return super.getLayer(FEEDBACK_LAYER);
                } else {
                    return ((IGraphicalEditPart) getHost()).getFigure().getParent().getParent();
                }
            }
        }
        return super.getLayer(layer);
    }

    public void zoomChanged(double zoom) {
        hideFeedback();
    }

    public void performCollapse() {
        hideFeedback();
    }
}
