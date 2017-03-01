/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.draw;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.diagram.custom.editPolicies.NextElementEditPolicy;
import org.bonitasoft.studio.diagram.custom.editPolicies.UpdateSizeLaneSelectionEditPolicy;
import org.bonitasoft.studio.diagram.custom.parts.CustomLaneEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomPoolNameEditPart;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.model.process.impl.LaneImpl;
import org.bonitasoft.studio.model.process.impl.PoolImpl;
import org.bonitasoft.studio.swtbot.framework.BotQAConstants;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPropertiesViewFolder;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.junit.Assert;

/**
 * Gef process diagram editor.
 *
 * @author Joachim Segala
 */
public class BotGefProcessDiagramEditor extends BotGefBaseEditor {

    public BotGefProcessDiagramEditor(final SWTGefBot bot) {
        super(bot);
    }

    /**
     * Add an element from the palette relative to an existing element.
     *
     * @param pSourceElement
     * @param pTool
     * @param pOrientation
     */
    public void addElement(final String pSourceElement, final String pTool, final int pOrientation) {
        final Point target = computeTargetLocation(pSourceElement, pOrientation);
        final Point delta = getDelta(pSourceElement);

        gmfEditor.activateTool(pTool);
        gmfEditor.click(delta.x + target.x, delta.y + target.y);
    }

    /**
     * Add an element from the contextual palette of one element.
     *
     * @param pSourceElement
     * @param pContextualPaletteId (see org.bonitasoft.studio.qa.util.SWTBotConstants.java)
     * @param pOrientation (org.eclipse.draw2d.PositionConstants)
     */
    public void addElementAfter(final String pSourceElement, final int pContextualPaletteId, final int pOrientation) {
        final SWTBotGefEditPart gep = gmfEditor.getEditPart(pSourceElement);
        Assert.assertNotNull("Error: No Edit Part \'" + pSourceElement + "\' found.", gep);
        final SWTBotGefEditPart element = gep.parent();
        element.select();
        final IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) element.part();
        final NextElementEditPolicy nextElementEditPolicy = (NextElementEditPolicy) graphicalEditPart
                .getEditPolicy(NextElementEditPolicy.NEXT_ELEMENT_ROLE);

        final IFigure toolbarFigure = nextElementEditPolicy.getFigure(pContextualPaletteId);
        final Point dropLocation = computeTargetLocation(pSourceElement, pOrientation);

        final Point location = toolbarFigure.getBounds().getCenter().getCopy();
        toolbarFigure.translateToAbsolute(location);

        final Point delta = getDelta(pSourceElement);

        gmfEditor.drag(location.x, location.y, delta.x + dropLocation.x, delta.y + dropLocation.y);
    }

    /**
     * Add a sequence flow between two elements.
     *
     * @param pFromElement
     * @param pToElement
     * @param pTargetAnchorPosition (Anchor on target element)
     */
    public void addSequenceFlowBetween(final String pFromElement, final String pToElement, final int pTargetAnchorPosition) {
        final int nbConnection = ModelHelper
                .getAllItemsOfType(((IGraphicalEditPart) gmfEditor.mainEditPart().part()).resolveSemanticElement(),
                        ProcessPackage.Literals.SEQUENCE_FLOW)
                .size();
        final IGraphicalEditPart gep = (IGraphicalEditPart) gmfEditor.getEditPart(pToElement).parent().part();
        final IFigure figure = gep.getFigure();
        Point targetLocation = null;
        final Rectangle bounds = figure.getBounds();
        switch (pTargetAnchorPosition) {
            case PositionConstants.NORTH:
                targetLocation = bounds.getTop().getCopy().translate(-10, 10);
                break;
            case PositionConstants.SOUTH:
                targetLocation = bounds.getBottom().getCopy().translate(-10, -10);
                break;
            case PositionConstants.EAST:
                targetLocation = bounds.getRight().getCopy().translate(-10, 8);
                break;
            case PositionConstants.WEST:
                targetLocation = bounds.getLeft().getCopy().translate(10, 8);
                break;
            default:
                throw new RuntimeException("Unhandled position : " + pTargetAnchorPosition);
        }

        figure.translateToAbsolute(targetLocation);
        gmfEditor.mainEditPart().part().getViewer().findObjectAt(targetLocation);

        selectElementInContextualPaletteAndDragIt(pFromElement, BotQAConstants.CONTEXTUAL_PALETTE_SEQUENCE_FLOW,
                targetLocation);
        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return nbConnection + 1 == ModelHelper
                        .getAllItemsOfType(((IGraphicalEditPart) gmfEditor.mainEditPart().part()).resolveSemanticElement(),
                                ProcessPackage.Literals.SEQUENCE_FLOW)
                        .size();
            }

            @Override
            public void init(final SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                return "Failed to create the sequenceflow between " + pFromElement + " and " + pToElement;
            }
        }, 5000, 1000);
    }

    private Point computeTargetLocation(final String pSourceElement, final int pOrientation) {
        final SWTBotGefEditPart gep = gmfEditor.getEditPart(pSourceElement);
        Assert.assertNotNull("Error: No Edit Part \'" + pSourceElement + "\' found.", gep);
        final SWTBotGefEditPart element = gep.parent();
        final IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) element.part();
        switch (pOrientation) {
            case PositionConstants.NORTH:
                return graphicalEditPart.getFigure().getBounds().getTop().getCopy().translate(-30, -70);
            case PositionConstants.SOUTH:
                return graphicalEditPart.getFigure().getBounds().getBottom().getCopy().translate(-30, 70);
            case PositionConstants.WEST:
                return graphicalEditPart.getFigure().getBounds().getTopLeft().getCopy().translate(-140, 0);
            case PositionConstants.EAST:
                return graphicalEditPart.getFigure().getBounds().getTopRight().getCopy().translate(80, 0);
            case PositionConstants.NORTH_EAST:
                return graphicalEditPart.getFigure().getBounds().getTopRight().getCopy().translate(80, -70);
            case PositionConstants.NORTH_WEST:
                return graphicalEditPart.getFigure().getBounds().getTopLeft().getCopy().translate(-80, 70);
            case PositionConstants.SOUTH_EAST:
                return graphicalEditPart.getFigure().getBounds().getBottomRight().getCopy().translate(-80, 70);
            case PositionConstants.SOUTH_WEST:
                return graphicalEditPart.getFigure().getBounds().getBottomLeft().getCopy().translate(80, -70);
            default:
                throw new RuntimeException("Invalid position specified");
        }

    }

    private void selectElementInContextualPaletteAndDragIt(final String pFromElement, final int pContextualPaletteId,
            final Point pDropLocation) {
        SWTBotGefEditPart element;
        final SWTBotGefEditPart gep = gmfEditor.getEditPart(pFromElement);
        Assert.assertNotNull("Error: No Edit Part \'" + pFromElement + "\' found.", gep);
        element = gep.parent();
        element.select();
        final IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) element.part();
        final NextElementEditPolicy nextElementEditPolicy = (NextElementEditPolicy) graphicalEditPart
                .getEditPolicy(NextElementEditPolicy.NEXT_ELEMENT_ROLE);

        final IFigure toolbarFigure = nextElementEditPolicy.getFigure(pContextualPaletteId);
        final Point location = toolbarFigure.getBounds().getCenter().getCopy();
        toolbarFigure.translateToAbsolute(location);
        gmfEditor.drag(location.x, location.y, pDropLocation.x, pDropLocation.y);
    }

    /**
     * Add new lane to the pool.
     *
     * @param pPoolName
     */
    public void addLaneOnPool(final String pPoolName) {
        final Point target = getPosition(pPoolName).translate(10, 10);
        final Point scroll = getCurrentScroll();

        gmfEditor.activateTool("Lane");

        if (scroll.y > target.y || scroll.x > target.x) {
            gmfEditor.click(target.x, target.y);
        } else {
            gmfEditor.click(target.x - scroll.x, target.y - scroll.y);
        }
    }

    /**
     * Add new pool after another.
     *
     * @param pPoolName
     */
    public void addPoolAfter(final String pPoolName) {
        final SWTBotGefEditPart gep = gmfEditor.getEditPart(pPoolName);
        Assert.assertNotNull("Error: No Edit Part \'" + pPoolName + "\' found.", gep);
        final SWTBotGefEditPart element = gep.parent();
        final IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) element.part();
        final Point target = graphicalEditPart.getFigure().getBounds().getBottomLeft().getCopy().translate(5, 5);

        gmfEditor.activateTool("Pool");
        gmfEditor.click(target.x, target.y);
    }

    /**
     * Move an element from a Lane to another.
     *
     * @param pElement
     * @param pLaneTarget
     */
    public void moveElementToLane(final String pElement, final String pLaneTarget) {
        final Point source = getPosition(pElement);
        final Point pool = getPositionOfPoolOfElement(pElement);
        final Point laneTarget = getPositionOfLaneInPool(getPoolName(pElement), pLaneTarget);
        final Point scroll = getCurrentScroll();

        gmfEditor.drag(pElement, -scroll.x + pool.x + laneTarget.x + source.x, -scroll.y + pool.y + laneTarget.y + 40);
    }

    private Point getPosition(final String pElement) {
        final SWTBotGefEditPart gep = gmfEditor.getEditPart(pElement);
        Assert.assertNotNull("Error: No Edit Part \'" + pElement + "\' found.", gep);
        final SWTBotGefEditPart element = gep.parent();
        final IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) element.part();
        return graphicalEditPart.getFigure().getBounds().getTopLeft().getCopy();
    }

    private Point getPositionOfLaneOfElement(final String pElement) {
        SWTBotGefEditPart gep = gmfEditor.getEditPart(pElement);
        while (gep != null) {
            gep = gep.parent();
            final IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) gep.part();
            if (graphicalEditPart.resolveSemanticElement() instanceof LaneImpl) {
                return graphicalEditPart.getFigure().getBounds().getTopLeft().getCopy();
            }
            gep = gep.parent();
        }
        return new Point(0, 0);
    }

    private Point getPositionOfPoolOfElement(final String pElement) {
        SWTBotGefEditPart gep = gmfEditor.getEditPart(pElement);
        while (gep != null) {
            gep = gep.parent();
            final IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) gep.part();
            if (graphicalEditPart.resolveSemanticElement() instanceof PoolImpl) {
                return graphicalEditPart.getFigure().getBounds().getTopLeft().getCopy();
            }
            gep = gep.parent();
        }
        return new Point(0, 0);
    }

    private Point getCurrentScroll() {
        final ProcessDiagramEditor processEditor = (ProcessDiagramEditor) gmfEditor.getReference().getEditor(false);
        final FigureCanvas canvas = (FigureCanvas) processEditor.getDiagramGraphicalViewer().getControl();
        return canvas.getViewport().getViewLocation();
    }

    private Point getDelta(final String pSourceElement) {
        final Point laneLocation = getPositionOfLaneOfElement(pSourceElement);
        final Point poolLocation = getPositionOfPoolOfElement(pSourceElement);
        final Point scroll = getCurrentScroll();
        return new Point(-scroll.x + poolLocation.x + laneLocation.x, -scroll.y + poolLocation.y + laneLocation.y);
    }

    /**
     * Click on one of the lane size control (+,-)
     *
     * @param pLane
     * @param pLaneSizeId (see org.bonitasoft.studio.qa.util.SWTBotConstants.java)
     */
    public void updateLaneSize(final String pLane, final int pLaneSizeId) {
        SWTBotGefEditPart element;
        final SWTBotGefEditPart gep = gmfEditor.getEditPart(pLane);
        Assert.assertNotNull("Error: No Edit Part \'" + pLane + "\' found.", gep);
        element = gep.parent();
        element.select();
        final IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) element.part();
        final UpdateSizeLaneSelectionEditPolicy updateSizeLaneEditPolicy = (UpdateSizeLaneSelectionEditPolicy) graphicalEditPart
                .getEditPolicy(UpdateSizeLaneSelectionEditPolicy.UPDATE_LANE_SIZE_SELECTION_FEEDBACK_ROLE);
        final IFigure toolbarFigure = updateSizeLaneEditPolicy.getFigure(pLaneSizeId);

        final Point location = toolbarFigure.getBounds().getCenter().getCopy();
        toolbarFigure.translateToAbsolute(location);
        gmfEditor.click(location.x, location.y);
    }

    /**
     * Scroll to bottom.
     */
    public void scrollToBottom() {
        UIThreadRunnable.syncExec(new VoidResult() {

            @Override
            public void run() {
                final ProcessDiagramEditor processEditor = (ProcessDiagramEditor) gmfEditor.getReference().getEditor(false);
                final FigureCanvas canvas = (FigureCanvas) processEditor.getDiagramGraphicalViewer().getControl();
                Point current;
                Point next = canvas.getViewport().getViewLocation();
                do {
                    current = next.getCopy();
                    canvas.getViewport().setVerticalLocation(current.y + 10);
                    next = canvas.getViewport().getViewLocation();
                } while (!current.equals(next));
            }
        });
    }

    /**
     * Scroll to top.
     */
    public void scrollToTop() {
        UIThreadRunnable.syncExec(new VoidResult() {

            @Override
            public void run() {
                final ProcessDiagramEditor processEditor = (ProcessDiagramEditor) gmfEditor.getReference().getEditor(false);
                final FigureCanvas canvas = (FigureCanvas) processEditor.getDiagramGraphicalViewer().getControl();
                canvas.getViewport().setVerticalLocation(0);
            }
        });
    }

    /**
     * Add a start event to a pool.
     * Usefull for new pool.
     *
     * @param pPoolName
     */
    public void addStartEvent(final String pPoolName) {
        final Point target = getPosition(pPoolName).translate(100, 100);
        final Point scroll = getCurrentScroll();

        gmfEditor.activateTool("Start");
        gmfEditor.click(target.x - scroll.x, target.y - scroll.y);
    }

    private String getPoolName(final String pElement) {
        final SWTBotGefEditPart gep = gmfEditor.getEditPart(pElement);
        return getPoolName(gep);
    }

    private String getPoolName(SWTBotGefEditPart gep) {
        while (gep != null) {
            gep = gep.parent();
            final IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) gep.part();
            if (graphicalEditPart.resolveSemanticElement() instanceof PoolImpl) {
                //TODO: Access to the name via SWTBotGefEditPart instead of IGraphicalEditPart
                return ((CustomPoolNameEditPart) graphicalEditPart.getChildren().get(0)).getEditText();
            }
            gep = gep.parent();
        }
        return null;
    }

    private Point getPositionOfLaneInPool(final String pPool, final String pLane) {
        final SWTBotGefEditPart pool = gmfEditor.getEditPart(pPool);

        final List<SWTBotGefEditPart> lanes = getLaneInPool(pool.parent(), pLane);

        if (lanes.size() == 1) {
            final IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) lanes.get(0).part();
            return graphicalEditPart.getFigure().getBounds().getTopLeft().getCopy();
        }
        return new Point(0, 0);
    }

    private List<SWTBotGefEditPart> getLaneInPool(final SWTBotGefEditPart pGef, final String pLabel) {
        final List<SWTBotGefEditPart> descendants = new ArrayList<SWTBotGefEditPart>();

        final List<SWTBotGefEditPart> children = pGef.children();
        for (final SWTBotGefEditPart child : children) {
            if (child.part() instanceof CustomLaneEditPart) {
                final ITextAwareEditPart textEditPart = (ITextAwareEditPart) child.children().get(0).part();
                final String d = textEditPart.getEditText();
                if (d.equals(pLabel)) {
                    descendants.add(child);
                }
            } else {
                descendants.addAll(getLaneInPool(child, pLabel));
            }
        }
        return descendants;
    }

    public BotGefProcessDiagramEditor selectDiagram() {
        gmfEditor.mainEditPart().select();
        return this;
    }

    public BotGefProcessDiagramEditor renameStep(final String oldName, final String newName) {
        gmfEditor.doubleClick(oldName);
        new BotProcessDiagramPropertiesViewFolder(bot).selectGeneralTab().selectGeneralTab().setName(newName);
        return this;
    }

    /**
     * @param sourceName
     * @param targetName
     */
    public BotGefProcessDiagramEditor selectFlowBetween(final String sourceName, final String targetName) {
        final SWTBotGefEditPart source = gmfEditor.getEditPart(sourceName).parent();
        final SWTBotGefEditPart target = gmfEditor.getEditPart(targetName).parent();
        for (final SWTBotGefConnectionEditPart outFlow : source.sourceConnections()) {
            for (final SWTBotGefConnectionEditPart inFlow : target.targetConnections()) {
                final ConnectionEditPart inPart = inFlow.part();
                final ConnectionEditPart outPart = outFlow.part();
                final RunnableWithResult<Boolean> runnableWithResult = new RunnableWithResult<Boolean>() {

                    Boolean res = false;

                    @Override
                    public void run() {
                        res = EcoreUtil.equals((EObject) inPart.getModel(), (EObject) outPart.getModel());
                    }

                    @Override
                    public Boolean getResult() {
                        return res;
                    }

                    @Override
                    public void setStatus(final IStatus status) {
                    }

                    @Override
                    public IStatus getStatus() {
                        return Status.OK_STATUS;
                    }
                };
                Display.getDefault().syncExec(runnableWithResult);
                if (runnableWithResult.getResult()) {
                    Display.getDefault().syncExec(new Runnable() {

                        @Override
                        public void run() {
                            inFlow.part().getViewer().deselectAll();
                            inFlow.part().setFocus(true);
                            inFlow.part().getViewer().setSelection(new StructuredSelection(inFlow.part()));

                        }
                    });
                    return this;
                }
            }
        }
        throw new IllegalStateException("No Flow found between " + sourceName + " and " + targetName);
    }

}
