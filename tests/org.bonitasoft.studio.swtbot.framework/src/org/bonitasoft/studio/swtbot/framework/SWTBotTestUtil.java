/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.swtbot.framework;

import static org.bonitasoft.studio.actors.i18n.Messages.selectActor;
import static org.bonitasoft.studio.actors.i18n.Messages.setAsProcessInitiator;
import static org.bonitasoft.studio.actors.i18n.Messages.useTaskActors;
import static org.bonitasoft.studio.expression.editor.i18n.Messages.editExpression;
import static org.bonitasoft.studio.expression.editor.i18n.Messages.expressionTypeLabel;
import static org.bonitasoft.studio.expression.editor.i18n.Messages.returnType;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.diagram.custom.editPolicies.NextElementEditPolicy;
import org.bonitasoft.studio.diagram.custom.editPolicies.UpdateSizePoolSelectionEditPolicy;
import org.bonitasoft.studio.engine.command.RunProcessCommand;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolEditPart;
import org.bonitasoft.studio.swtbot.framework.conditions.ShellIsActiveWithThreadSTacksOnFailure;
import org.bonitasoft.studio.swtbot.framework.expression.BotExpressionEditorDialog;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.keyboard.Keyboard;
import org.eclipse.swtbot.swt.finder.keyboard.KeyboardFactory;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.matchers.WithId;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTableItem;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyList;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyList.ListElement;
import org.eclipse.xtext.ui.editor.reconciler.XtextReconciler;
import org.eclipse.xtext.ui.editor.validation.ValidationJob;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Assert;

public class SWTBotTestUtil implements SWTBotConstants {

    private static final int X_MARGIN = 90;
    private static final int Y_MARGIN = 80;
    public static final int CONTEXTUALPALETTE_STEP = 0;
    public static final int CONTEXTUALPALETTE_GATEWAY = 1;
    public static final int CONTEXTUALPALETTE_SEQUENCEFLOW = 2;
    //TOOLBAREVENT doesn't work, create a comment
    public static final int CONTEXTUALPALETTE_EVENT = 3;

    public static void createNewDiagram(final SWTWorkbenchBot wBot) {
        final int nbEditorsBefore = wBot.editors().size();
        wBot.waitUntil(Conditions.waitForWidget(WithId.withId(SWTBOT_ID_MAIN_SHELL)), 40000);
        wBot.waitUntil(Conditions.shellIsActive(wBot.shellWithId(SWTBOT_ID_MAIN_SHELL).getText()), 40000);
        wBot.waitUntil(Conditions.widgetIsEnabled(wBot.menu("File")), 40000);
        final SWTBotMenu menu = wBot.menu("File");
        menu.menu("New diagram").click();
        wBot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return nbEditorsBefore + 1 == wBot.editors().size();
            }

            @Override
            public String getFailureMessage() {
                return "Editor for new diagram has not been opened";
            }
        }, 30000, 100);
    }

    public static IStatus selectAndRunFirstPoolFound(final SWTGefBot gefbot) throws ExecutionException {
        gefbot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return gefbot.activeEditor() != null;
            }

            @Override
            public String getFailureMessage() {
                return "No active editor found";
            }
        });
        final SWTBotEditor botEditor = gefbot.activeEditor();
        final SWTBotGefEditor gmfEditor = gefbot.gefEditor(botEditor.getTitle());
        final List<SWTBotGefEditPart> runnableEPs = gmfEditor.editParts(new BaseMatcher<EditPart>() {

            @Override
            public boolean matches(final Object item) {
                return item instanceof PoolEditPart;
            }

            @Override
            public void describeTo(final Description description) {

            }
        });
        Assert.assertFalse(runnableEPs.isEmpty());
        gmfEditor.select(runnableEPs.get(0));
        final RunProcessCommand cmd = new RunProcessCommand(true);
        final RunnableWithResult<IStatus> runnable = new RunnableWithResult<IStatus>() {

            IStatus status = null;

            @Override
            public void run() {
                try {
                    status = (IStatus) cmd.execute(null);
                } catch (final ExecutionException e) {
                    BonitaStudioLog.error(e);
                    status = new Status(Status.ERROR, "org.bonitasoft.studio.tests.ex", "Error during execution");
                }
            }

            @Override
            public IStatus getResult() {
                return status;
            }

            @Override
            public void setStatus(final IStatus status) {
            }

            @Override
            public IStatus getStatus() {
                return status;
            }
        };
        Display.getDefault().syncExec(runnable);
        return runnable.getResult();
    }

    public static boolean testingBosSp() {
        return Platform.getBundle("org.bonitasoft.studioEx.console.libs") != null;
    }

    public static void selectTabbedPropertyView(final SWTBot viewerBot, final String tabText) {
        viewerBot.waitUntil(new DefaultCondition() {
            
            @Override
            public boolean test() throws Exception {
                final List<? extends Widget> widgets = viewerBot.getFinder()
                        .findControls(WidgetMatcherFactory.widgetOfType(TabbedPropertyList.class));
                Assert.assertTrue("No widget of type " + TabbedPropertyList.class.getName() + " has been found",
                        widgets.size() > 0);
               return UIThreadRunnable.syncExec(new Result<Boolean>() {

                    @Override
                    public Boolean run() {
                        try {
                            final TabbedPropertyList tabbedPropertyList = (TabbedPropertyList) widgets.get(0);
                            int i = 0;
                            boolean found = false;
                            ListElement currentTab;
                            final Method selectMethod = TabbedPropertyList.class.getDeclaredMethod("select",
                                    new Class[] { int.class });
                            selectMethod.setAccessible(true);
                            do {
                                currentTab = (org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyList.ListElement) tabbedPropertyList
                                        .getElementAt(i);
                                if (currentTab != null) {
                                    final String label = currentTab.getTabItem().getText();
                                    if (label.equals(tabText)) {
                                        found = true;
                                        selectMethod.invoke(tabbedPropertyList, i);
                                    }
                                }
                                i++;
                            } while (currentTab != null && !found);
                            if (!found) {
                                return false;
                            }
                        } catch (final Exception ex) {
                            BonitaStudioLog.error(ex);
                            return false;
                        }
                        return true;
                    }
                });
            }
            
            @Override
            public String getFailureMessage() {
                return "Can't find a tab item with " + tabText + " label";
            }
            
        }, 5000);
    }


    public static void waitUntilRootShellIsActive(SWTBot bot) {
        bot.waitUntil(new ShellIsActiveWithThreadSTacksOnFailure("Bonita Studio"), 40000);
        bot.shell("Bonita Studio").setFocus();
    }


    /**
     * select an event on diagram with the given name
     *
     * @param bot
     * @param gmfEditor
     * @param poolName
     * @param eventName
     */
    public static void selectEventOnProcess(final SWTGefBot bot, final SWTBotGefEditor gmfEditor, final String eventName) {
        final SWTBotGefEditPart event = gmfEditor.getEditPart(eventName).parent();
        event.click();
        event.select();
    }

    /**
     * select an event in the overview tree. Becarefull, if treeViewer exists in other views SWTBot may not find the one in
     * overview
     *
     * @param bot
     * @param poolName
     * @param eventName
     */
    public static void selectElementFromOverview(final SWTGefBot bot, final String poolName, final String laneName,
            final String eventName) {
        final SWTBotView view = bot.viewById(SWTBotTestUtil.VIEWS_TREE_OVERVIEW);
        view.show();
        view.setFocus();
        final SWTBotTree tree = bot.treeWithId(BONITA_OVERVIEW_TREE_ID);
        tree.setFocus();
        tree.getTreeItem(poolName).click();
        if (laneName == null) {
            tree.expandNode(poolName).select(eventName);
        } else {
            tree.expandNode(poolName).expandNode(laneName);
            if (eventName != null) {
                tree.getTreeItem(eventName);
            }
        }
    }

    public static void selectElementFromFormOverview(final SWTGefBot bot, final String widgetName) {
        final SWTBotView view = bot.viewById(SWTBotTestUtil.VIEWS_TREE_OVERVIEW);
        view.show();
        view.setFocus();
        final SWTBotTree tree = bot.treeWithId(BONITA_OVERVIEW_TREE_ID);
        tree.setFocus();
        bot.waitUntil(Conditions.widgetIsEnabled(tree.getTreeItem(widgetName)));
        tree.select(widgetName);
    }

    /**
     * @param gmfEditor
     * @param selectedElementName
     * @param elementIndex
     * @param isEvent
     * @param dropLocation
     */
    public static void selectElementInContextualPaletteAndDragIt(final SWTBotGefEditor gmfEditor,
            final String selectedElementName, final int elementIndex,
            final Point dropLocation) {
        SWTBotGefEditPart element;
        final SWTBotGefEditPart gep = gmfEditor.getEditPart(selectedElementName);
        Assert.assertNotNull("Error: No Edit Part \'" + selectedElementName + "\' found.", gep);
        element = gep.parent();
        element.select();
        final IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) element.part();
        final NextElementEditPolicy nextElementEditPolicy = (NextElementEditPolicy) graphicalEditPart
                .getEditPolicy(NextElementEditPolicy.NEXT_ELEMENT_ROLE);

        final IFigure toolbarFigure = nextElementEditPolicy.getFigure(elementIndex);
        final Point location = toolbarFigure.getBounds().getCenter().getCopy();
        toolbarFigure.translateToAbsolute(location);
        gmfEditor.drag(location.x, location.y, dropLocation.x, dropLocation.y);
    }

    public static void selectElementInContextualPaletteAndDragIt(final SWTBotGefEditor gmfEditor,
            final String selectedElementName, final int elementIndex,
            final int position) {
        final SWTBotGefEditPart gep = gmfEditor.getEditPart(selectedElementName);
        Assert.assertNotNull("Error: No Edit Part \'" + selectedElementName + "\' found.", gep);
        final SWTBotGefEditPart element = gep.parent();
        element.select();
        final IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) element.part();
        final NextElementEditPolicy nextElementEditPolicy = (NextElementEditPolicy) graphicalEditPart
                .getEditPolicy(NextElementEditPolicy.NEXT_ELEMENT_ROLE);

        final IFigure toolbarFigure = nextElementEditPolicy.getFigure(elementIndex);
        Point dropLocation = null;

        final int xPaletteDelta = computeXPaletteDelta(elementIndex);
        final int yPaletteDelta = computeYPaletteDelta(elementIndex);

        final Rectangle bounds = graphicalEditPart.getFigure().getBounds();
        switch (position) {
            case PositionConstants.NORTH:
                dropLocation = bounds.getTop().getCopy().translate(xPaletteDelta, -Y_MARGIN);
                break;
            case PositionConstants.SOUTH:
                dropLocation = bounds.getBottom().getCopy().translate(xPaletteDelta, Y_MARGIN);
                break;
            case PositionConstants.WEST:
                dropLocation = bounds.getLeft().getCopy().translate(-X_MARGIN, yPaletteDelta);
                break;
            case PositionConstants.EAST:
                dropLocation = bounds.getRight().getCopy().translate(X_MARGIN, yPaletteDelta);
                break;
            case PositionConstants.NORTH_EAST:
                dropLocation = bounds.getTopRight().getCopy().translate(X_MARGIN, -Y_MARGIN);
                break;
            case PositionConstants.NORTH_WEST:
                dropLocation = bounds.getTopLeft().getCopy().translate(-X_MARGIN, Y_MARGIN);
                break;
            case PositionConstants.SOUTH_EAST:
                dropLocation = bounds.getBottomRight().getCopy().translate(X_MARGIN, Y_MARGIN);
                break;
            case PositionConstants.SOUTH_WEST:
                dropLocation = bounds.getBottomLeft().getCopy().translate(-X_MARGIN, -Y_MARGIN);
                break;
            default:
                throw new RuntimeException("Invalid position specified");
        }

        final Point location = toolbarFigure.getBounds().getCenter().getCopy();
        toolbarFigure.translateToAbsolute(location);

        gmfEditor.drag(location.x, location.y, dropLocation.x, dropLocation.y);
    }

    private static int computeYPaletteDelta(final int elementIndex) {
        switch (elementIndex) {
            case CONTEXTUALPALETTE_EVENT:
                return FiguresHelper.EVENT_WIDTH/2;
            case CONTEXTUALPALETTE_GATEWAY:
                return FiguresHelper.GATEWAY_WIDTH/2 +1;
            case CONTEXTUALPALETTE_SEQUENCEFLOW:
                return 5;
            case CONTEXTUALPALETTE_STEP:
                return FiguresHelper.ACTIVITY_HEIGHT/2 + 1;
            default:
                return 0;
        }
    }
    
    private static int computeXPaletteDelta(final int elementIndex) {
        switch (elementIndex) {
            case CONTEXTUALPALETTE_EVENT:
                return FiguresHelper.EVENT_WIDTH/2;
            case CONTEXTUALPALETTE_GATEWAY:
                return FiguresHelper.GATEWAY_WIDTH/2;
            case CONTEXTUALPALETTE_SEQUENCEFLOW:
                return 5;
            case CONTEXTUALPALETTE_STEP:
                return FiguresHelper.ACTIVITY_WIDTH/2 - 15;
            default:
                return 0;
        }
    }

    /**
     * @param gmfEditor
     * @param selectedElementName
     * @param dropLocation
     */
    public static void selectTaskFromSelectedElementAndDragIt(final SWTBotGefEditor gmfEditor,
            final String selectedElementName, final Point dropLocation) {
        selectElementInContextualPaletteAndDragIt(gmfEditor, selectedElementName, CONTEXTUALPALETTE_STEP, dropLocation);
    }

    /**
     * @param gmfEditor
     * @param selectedElementName
     * @param dropLocation
     */
    public static void selectTransitionFromSelectedElementAndDragIt(final SWTBotGefEditor gmfEditor,
            final String selectedElementName,
            final Point dropLocation) {
        selectElementInContextualPaletteAndDragIt(gmfEditor, selectedElementName, CONTEXTUALPALETTE_SEQUENCEFLOW,
                dropLocation);
    }

    /**
     * add data wizard configuration (only for defined types as String Integer Boolean etc.)
     *
     * @param bot
     * @param name
     * @param type
     * @param multiplicity
     * @param defaultValue
     */
    public static void addNewData(final SWTBot bot, final String name, final String type, final boolean multiplicity,
            final String defaultValue) {
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.data.i18n.Messages.newVariable));
        bot.activeShell().setFocus();
        bot.textWithLabel(org.bonitasoft.studio.data.i18n.Messages.name + " *").setText(name);
        bot.comboBoxWithLabel(org.bonitasoft.studio.data.i18n.Messages.datatypeLabel).setSelection(type);
        if (multiplicity) {
            bot.checkBox(org.bonitasoft.studio.data.i18n.Messages.isMultiple).select();
        }
        if (defaultValue != null) {
            bot.textWithLabel(org.bonitasoft.studio.data.i18n.Messages.defaultValueLabel).setText(defaultValue);
        }
        bot.button(IDialogConstants.FINISH_LABEL).click();
    }

    public static void addNewCustomDataInitWithScript(final SWTGefBot bot,
            final String name, final String type, final String defaultValueAsScript) {
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.data.i18n.Messages.newVariable));
        bot.textWithLabel(org.bonitasoft.studio.properties.i18n.Messages.name).setText(name);
        bot.comboBoxWithLabel(org.bonitasoft.studio.properties.i18n.Messages.datatypeLabel)
                .setSelection(org.bonitasoft.studio.common.Messages.JavaType);
        bot.button(org.bonitasoft.studio.data.i18n.Messages.browseClasses).click();
        bot.text().setText(type);
        bot.waitUntil(Conditions.tableHasRows(bot.table(), 2));
        bot.table().select(0);
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.data.i18n.Messages.newVariable));
        clickOnPenToEditExpression(bot, 0);
        SWTBotTestUtil.setScriptExpression(bot, "result", defaultValueAsScript, type);
        bot.button(IDialogConstants.FINISH_LABEL).click();
    }

    /**
     * add data with option wizard configuration (only for defined types as String Integer Boolean etc.)
     *
     * @param bot
     * @param name
     * @param type
     * @param multiplicity
     * @param defaultValue
     */
    public static void addListOfOptionData(final SWTBot bot, final String name, final String type,
            final Map<String, List<String>> options,
            final boolean isMultiple, final String defaultValue) {
        bot.waitUntil(Conditions.shellIsActive("New variable"));
        bot.textWithLabel(org.bonitasoft.studio.properties.i18n.Messages.name + " *").setText(name);
        bot.comboBoxWithLabel(org.bonitasoft.studio.properties.i18n.Messages.datatypeLabel).setSelection(type);
        SWTBotShell activeShell = bot.activeShell();
        bot.button("List of options...").click();
        bot.waitUntil(Conditions.shellIsActive("List of options"));
        int i = 0;
        for (final String optionsName : options.keySet()) {
            bot.button("Add", 0).click();
            bot.table(0).click(i, 0);
            bot.text().setText(optionsName);
            int j = 0;
            for (final String option : options.get(optionsName)) {
                bot.button("Add", 1).click();
                bot.table(1).click(j, 0);
                bot.text().setText(option);
                j++;
            }
            i++;
        }
        bot.button(IDialogConstants.OK_LABEL).click();
        activeShell.setFocus();
        if (isMultiple) {
            bot.checkBox("Is multiple").select();
        }
        if (defaultValue != null) {
            bot.textWithLabel(org.bonitasoft.studio.properties.i18n.Messages.defaultValueLabel).setText(defaultValue);
            bot.sleep(1000);
        }
        bot.button(IDialogConstants.FINISH_LABEL).click();
    }

    /**
     * add a sequence flow between startElement and End Element
     *
     * @param bot
     * @param gmfEditor
     * @param startElementName
     * @param endElementName
     */
    public static void addSequenceFlow(final SWTGefBot bot, final SWTBotGefEditor gmfEditor, final String startElementName,
            final String endElementName,
            final int targetAnchorPosition) {
        final int nbConnection = ModelHelper
                .getAllItemsOfType(((IGraphicalEditPart) gmfEditor.mainEditPart().part()).resolveSemanticElement(),
                        ProcessPackage.Literals.SEQUENCE_FLOW)
                .size();
        final IGraphicalEditPart gep = (IGraphicalEditPart) gmfEditor.getEditPart(endElementName).parent().part();
        final IFigure figure = gep.getFigure();
        Point targetLocation = null;
        final Rectangle bounds = figure.getBounds();
        switch (targetAnchorPosition) {
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
                throw new RuntimeException("Unhandled position : " + targetAnchorPosition);
        }

        figure.translateToAbsolute(targetLocation);
        gmfEditor.mainEditPart().part().getViewer().findObjectAt(targetLocation);

        selectElementInContextualPaletteAndDragIt(gmfEditor, startElementName, CONTEXTUALPALETTE_SEQUENCEFLOW,
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
                return "Failed to create the sequenceflow between " + startElementName + " and " + endElementName;
            }
        }, 5000, 1000);
    }

    public Point getLocationOfElementInDiagram(final SWTGefBot bot, final SWTBotGefEditor gmfEditor,
            final String elementName) {
        final IGraphicalEditPart gep = (IGraphicalEditPart) gmfEditor.getEditPart(elementName).parent().part();
        final IFigure figure = gep.getFigure();
        final Rectangle dest = figure.getBounds().getCopy();
        figure.translateToAbsolute(dest);
        return dest.getLocation();
    }

    /**
     * @param bot
     * @param taskName
     * @param newDiagramName
     */
    public static void changeDiagramName(final SWTGefBot bot, final String newDiagramName) {

        final SWTBotEditor botEditor = bot.activeEditor();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

        gmfEditor.mainEditPart().click();

        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).setFocus();

        selectTabbedPropertyView(bot, "Diagram");
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(org.bonitasoft.studio.common.Messages.edit)));
        bot.button(org.bonitasoft.studio.common.Messages.edit).click();

        // Open new Shell
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.common.Messages.openNameAndVersionDialogTitle));

        bot.textWithLabel(org.bonitasoft.studio.common.Messages.name, 0).setText(newDiagramName);
        bot.button(IDialogConstants.OK_LABEL).click();

    }

    /**
     * @param bot
     * @param taskName
     * @param newName
     */
    public static void changeDiagramName(final SWTGefBot bot, final String taskName, final String newName) {

        final SWTBotEditor botEditor = bot.activeEditor();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

        gmfEditor.mainEditPart().click();

        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).setFocus();

        selectTabbedPropertyView(bot, "Diagram");
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button("Edit...")));
        bot.button("Edit...").click();

        // Open new Shell
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.common.Messages.openNameAndVersionDialogTitle));

        bot.textWithLabel(org.bonitasoft.studio.common.Messages.name, 0).setText(newName);
        bot.button(IDialogConstants.OK_LABEL).click();

    }

    /**
     * select in the overview tree the first transition flow in the given pool
     *
     * @param name
     * @param defaultFlow
     * @param condition
     */
    public static void configureSequenceFlow(final SWTGefBot bot, final String name, final String pool,
            final boolean defaultFlow, final String condition,
            final String expressionType) {
        bot.activeEditor().setFocus();
        final SWTBotView view = bot.viewById(SWTBotTestUtil.VIEWS_TREE_OVERVIEW);
        view.show();
        view.setFocus();
        final SWTBotTree overviewTree = bot.treeWithId(BONITA_OVERVIEW_TREE_ID);
        bot.waitUntil(Conditions.widgetIsEnabled(overviewTree));
        overviewTree.setFocus();
        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                SWTBotTreeItem item = null;
                try {
                    item = overviewTree.getTreeItem(pool);
                } catch (final TimeoutException e) {
                    return false;
                }
                return item != null;
            }

            @Override
            public void init(final SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                final StringBuilder res = new StringBuilder();
                for (final SWTBotTreeItem sti : overviewTree.getAllItems()) {
                    res.append(sti.getText() + "\n");
                }
                return "Pool " + pool + " not found in overview tree.\n" + res;
            }
        }, 10000, 500);
        final SWTBotTreeItem treeItem = overviewTree.getTreeItem(pool);
        if (!treeItem.isExpanded()) {
            treeItem.expand();
        }
        treeItem.select("Sequence Flow");
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        selectTabbedPropertyView(bot, "General");
        bot.waitUntil(Conditions.widgetIsEnabled(bot.textWithLabel("Condition")));
        bot.textWithLabel("Name").setText(name);
        bot.sleep(1000);
        if (defaultFlow) {
            bot.checkBox("Default flow").select();
        }
        //ExpressionConstants.VARIABLE_TYPE
        if (condition != null) {
            if (expressionType == ExpressionConstants.VARIABLE_TYPE) {
                // edit button
                bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 0).click();
                setVariableExpression(bot, condition);
            } else if (expressionType == ExpressionConstants.SCRIPT_TYPE) {
                // edit button
                bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 0).click();
                //edit expression shell
                setScriptExpression(bot, "script_" + name, condition, null);
                bot.sleep(600);
            } else if (expressionType == ExpressionConstants.CONSTANT_TYPE) {
                bot.textWithLabel("Condition").setText(condition);
                bot.sleep(600);
            } else if (expressionType == ExpressionConstants.CONDITION_TYPE) {
                bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 0).click();
                setComparisonExpression(bot, condition);
            } else {
                Assert.assertTrue("Error: Expression type " + expressionType + " is not supported.", false);
            }
        }

    }

    /**
     * @param bot
     * @param condition
     *        warning : sequence flow properties must be displayed before using this method
     */
    public static void initializeComparisonExpression(final SWTGefBot bot, final String condition) {
        bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 0).click();
        setComparisonExpression(bot, condition);
    }

    /**
     * @param bot
     * @param condition
     */
    private static void setComparisonExpression(final SWTGefBot bot, final String condition) {
        bot.waitUntil(Conditions.shellIsActive(editExpression));
        bot.tableWithLabel(expressionTypeLabel).select("Comparison");
        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return bot.styledText() != null;
            }

            @Override
            public void init(final SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                return "StyledText is not ready";
            }
        });
        bot.styledText().setFocus();
        bot.styledText().setText(condition);
    }

    public static StyleRange getTextStyleInEditExpressionDialog(final SWTGefBot bot, final String expressionType,
            final int line, final int column)
            throws OperationCanceledException, InterruptedException {
        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return bot.styledText() != null;
            }

            @Override
            public void init(final SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                return "StyledText is not ready";
            }
        });
        bot.styledText().setFocus();
        Job.getJobManager().join(ValidationJob.XTEXT_VALIDATION_FAMILY, Repository.NULL_PROGRESS_MONITOR);//Wait for ValidationJob
        Job.getJobManager().join(XtextReconciler.class.getName(), Repository.NULL_PROGRESS_MONITOR);//Wait for Reconciler Job
        bot.sleep(600);
        return bot.styledText().getStyle(line, column);
    }

    public static Keyboard getKeybord() {
        SWTBotPreferences.KEYBOARD_LAYOUT = "EN_US";
        return KeyboardFactory.getSWTKeyboard();
    }

    public static void pressEnter() {
        getKeybord().pressShortcut(Keystrokes.CR);
    }

    public static void pressDelete() {
        getKeybord().pressShortcut(Keystrokes.DELETE);
    }

    public static BotExpressionEditorDialog clickOnPenToEditExpression(final SWTGefBot bot, final int index) {
        bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, index).click();
        return new BotExpressionEditorDialog(bot, bot.activeShell());
    }

    /**
     * @param scriptName
     * @param expression
     * @param returnTypeOfScript
     */
    public static void setScriptExpression(final SWTGefBot bot, final String scriptName, final String expression,
            final String returnTypeOfScript) {
        bot.waitUntil(Conditions.shellIsActive(editExpression));
        bot.tableWithLabel(expressionTypeLabel).select("Script");
        bot.sleep(1000);
        // set the Script name
        bot.textWithLabel("Name").setText(scriptName);
        bot.styledText().setText(expression);
        if (returnTypeOfScript != null) {
            bot.comboBoxWithLabel(returnType).setText(returnTypeOfScript);
        }
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.OK_LABEL)));
        bot.button(IDialogConstants.OK_LABEL).click();
    }

    /**
     * @param bot
     * @param variableName
     */
    public static void setVariableExpression(final SWTGefBot bot, final String variableName) {
        bot.waitUntil(Conditions.shellIsActive(editExpression));
        bot.tableWithLabel(expressionTypeLabel).select("Variable");
        bot.sleep(1000);
        // select the variable
        final SWTBotTable tableVar = bot.table(1);
        for (int i = 0; i < tableVar.rowCount(); i++) {
            final SWTBotTableItem tableItem = tableVar.getTableItem(i);
            if (tableItem.getText().startsWith(variableName + " --")) {
                tableItem.select();
                break;
            }
        }
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.OK_LABEL)));
        bot.button(IDialogConstants.OK_LABEL).click();
    }

    /**
     * Select an actor in a Human task in the list of Process Actor
     *
     * @param bot
     * @param actor
     */
    public static void selectActorInHumanTask(final SWTGefBot bot, final String actor) {
        bot.viewById(VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        selectTabbedPropertyView(bot, "Actors");
        // "Use below actor"
        bot.radio(useTaskActors).click();
        // "Select Actor"
        bot.comboBoxWithLabel(selectActor).setSelection(actor);
    }

    /**
     * Select an actor and define it as an initiator
     *
     * @param bot
     * @param actor
     */
    public static void selectInitiatorinActor(final SWTGefBot bot, final String actor) {
        bot.viewById(VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        selectTabbedPropertyView(bot, "Actors");
        if (bot.button(setAsProcessInitiator).isEnabled()) {
            final int actorIdx = bot.table().indexOf(actor, org.bonitasoft.studio.actors.i18n.Messages.name);
            Assert.assertTrue("Error: no actor " + actor + " available", actorIdx != -1);
            bot.table().getTableItem(actorIdx).select();
            bot.button(setAsProcessInitiator).click();
        }
    }

    /**
     * Wait for the shell with its title name with the default timeout
     *
     * @param bot
     * @param shellTitle
     */
    public static void waitForActiveShell(final SWTGefBot bot, final String shellTitle) {
        bot.waitUntil(Conditions.shellIsActive(shellTitle));
    }

    public static void increasePoolWidth(final SWTBotGefEditor editor, final String poolName) {
        final SWTBotGefEditPart poolPart = editor.getEditPart(poolName).parent();
        poolPart.select();

        final IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) poolPart.part();
        final UpdateSizePoolSelectionEditPolicy addPoolSizeElementEditPolicy = (UpdateSizePoolSelectionEditPolicy) graphicalEditPart
                .getEditPolicy(UpdateSizePoolSelectionEditPolicy.UPDATE_POOL_SIZE_SELECTION_FEEDBACK_ROLE);

        final IFigure toolbarFigure = addPoolSizeElementEditPolicy.getFigure(UpdateSizePoolSelectionEditPolicy.ADD_RIGHT);
        final Point location = toolbarFigure.getBounds().getCenter().getCopy();
        toolbarFigure.translateToAbsolute(location);
        editor.click(location.x, location.y);
    }

    public static void increasePoolHeight(final SWTBotGefEditor editor, final String poolName) {
        final SWTBotGefEditPart poolPart = editor.getEditPart(poolName).parent();
        poolPart.select();

        final IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) poolPart.part();
        final UpdateSizePoolSelectionEditPolicy addPoolSizeElementEditPolicy = (UpdateSizePoolSelectionEditPolicy) graphicalEditPart
                .getEditPolicy(UpdateSizePoolSelectionEditPolicy.UPDATE_POOL_SIZE_SELECTION_FEEDBACK_ROLE);

        final IFigure toolbarFigure = addPoolSizeElementEditPolicy.getFigure(UpdateSizePoolSelectionEditPolicy.ADD_BOTTOM);
        final Point location = toolbarFigure.getBounds().getCenter().getCopy();
        toolbarFigure.translateToAbsolute(location);
        editor.click(location.x, location.y);
    }

    /**
     * @param bot
     */
    public static void editScriptConnector(final SWTGefBot bot, final String scriptName, final String scriptText) {
        editScriptConnector(bot, scriptName, scriptText, null);
    }

    /**
     * @param bot
     */
    public static void editScriptConnector(final SWTGefBot bot, final String scriptName, final String scriptText,
            final String scriptDescription) {
        // 1st page
        editConnector(bot, "Script", "Groovy");

        // 2nde page
        bot.textWithLabel("Name *").setText(scriptName);
        if (scriptDescription != null) {
            bot.textWithLabel("Description").setText(scriptDescription);
        }
        Assert.assertTrue("Error : Next button is not enable in Connectors Wizard.",
                bot.button(IDialogConstants.NEXT_LABEL).isEnabled());
        bot.button(IDialogConstants.NEXT_LABEL).click();

        // 3th page
        SWTBotShell activeShell = bot.activeShell();
        bot.toolbarButtonWithId(SWTBOT_ID_EDITBUTTON).click();
        bot.waitUntil(Conditions.widgetIsEnabled(bot.textWithLabel("Name")));
        bot.textWithLabel("Name").setText(scriptName);
        bot.styledText().setText(scriptText);
        bot.button(IDialogConstants.OK_LABEL).click();
        activeShell.setFocus();
        bot.button(IDialogConstants.NEXT_LABEL).click();

        // 4th page
        Assert.assertTrue("Error : finish button is not enable in Connectors Wizard.",
                bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.button(IDialogConstants.FINISH_LABEL).click();
    }

    /**
     * Set the connector type and the select the connector specified in the parameters
     *
     * @param bot
     * @param connectorType
     * @param connectorTool
     */
    public static void editConnector(final SWTGefBot bot, final String connectorType, final String connectorTool) {
        bot.waitUntil(Conditions.shellIsActive("Connectors"));
        bot.text().setText(connectorTool);
        bot.table().select(0);
        Assert.assertTrue("Error : No " + connectorTool + " " + connectorType + " found in the connector list",
                bot.button(IDialogConstants.NEXT_LABEL)
                        .isEnabled());

        bot.button(IDialogConstants.NEXT_LABEL).click();

    }

    public static void editEmailConnector(final SWTGefBot bot, final String emailName, final String emailDescription,
            final String from, final String to,
            final String subject, final String message) {
        // 1st page
        editConnector(bot, "Messaging", "Email");

        // 2nde page
        bot.textWithLabel("Name *").setText(emailName);
        if (emailDescription != null && !emailDescription.isEmpty()) {
            bot.textWithLabel("Description").setText(emailDescription);
        }
        Assert.assertTrue("Error : Next button is not enable in Connectors Wizard.",
                bot.button(IDialogConstants.NEXT_LABEL).isEnabled());
        bot.button(IDialogConstants.NEXT_LABEL).click();

        // 3th page
        Assert.assertTrue("Error : Next button is not enable in Connectors Wizard.",
                bot.button(IDialogConstants.NEXT_LABEL).isEnabled());
        bot.button(IDialogConstants.NEXT_LABEL).click();

        // 4th page
        bot.textWithLabel("From *").setText(from);
        bot.textWithLabel("To *").setText(to);
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.NEXT_LABEL)));
        Assert.assertTrue("Error : Next button is not enable in Connectors Wizard.",
                bot.button(IDialogConstants.NEXT_LABEL).isEnabled());
        bot.button(IDialogConstants.NEXT_LABEL).click();

        // 5th page
        bot.textWithLabel("Subject *").setText(subject);
        if (message != null && !message.isEmpty()) {
            bot.styledTextWithLabel("Message").setText(message);
        }
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.NEXT_LABEL)));
        Assert.assertTrue("Error : Next button is not enable in Connectors Wizard.",
                bot.button(IDialogConstants.NEXT_LABEL).isEnabled());
        bot.button(IDialogConstants.NEXT_LABEL).click();

        // 6th page
        Assert.assertTrue("Error : Next button is not enable in Connectors Wizard.",
                bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.button(IDialogConstants.FINISH_LABEL).click();

    }

    public static Point computeTargetLocation(final SWTBotGefEditor gmfEditor, final String sourceElement,
            final int position) {
        final SWTBotGefEditPart gep = gmfEditor.getEditPart(sourceElement);
        Assert.assertNotNull("Error: No Edit Part \'" + sourceElement + "\' found.", gep);
        final SWTBotGefEditPart element = gep.parent();
        final IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) element.part();

        switch (position) {
            case PositionConstants.NORTH:
                return graphicalEditPart.getFigure().getBounds().getTop().getCopy().translate(-20, -Y_MARGIN);
            case PositionConstants.SOUTH:
                return graphicalEditPart.getFigure().getBounds().getBottom().getCopy().translate(-20, Y_MARGIN);
            case PositionConstants.WEST:
                return graphicalEditPart.getFigure().getBounds().getLeft().getCopy().translate(-X_MARGIN, 10);
            case PositionConstants.EAST:
                return graphicalEditPart.getFigure().getBounds().getRight().getCopy().translate(X_MARGIN, 10);
            case PositionConstants.NORTH_EAST:
                return graphicalEditPart.getFigure().getBounds().getTopRight().getCopy().translate(X_MARGIN, -Y_MARGIN);
            case PositionConstants.NORTH_WEST:
                return graphicalEditPart.getFigure().getBounds().getTopLeft().getCopy().translate(-X_MARGIN, Y_MARGIN);
            case PositionConstants.SOUTH_EAST:
                return graphicalEditPart.getFigure().getBounds().getBottomRight().getCopy().translate(-X_MARGIN, Y_MARGIN);
            case PositionConstants.SOUTH_WEST:
                return graphicalEditPart.getFigure().getBounds().getBottomLeft().getCopy().translate(X_MARGIN, -Y_MARGIN);
            default:
                throw new RuntimeException("Invalid position specified");
        }

    }

    public static void selectExpressionProposal(final SWTBot bot, final String storageExpressionName,
            final String returnType, final int index) {
        bot.toolbarButtonWithId(SWTBOT_ID_EXPRESSIONVIEWER_DROPDOWN, index).click();
        final SWTBotShell proposalShell = bot.shellWithId(SWTBOT_ID_EXPRESSIONVIEWER_PROPOSAL_SHELL);
        final SWTBot proposalBot = proposalShell.bot();
        final SWTBotTable proposalTAble = proposalBot.tableWithId(SWTBOT_ID_EXPRESSIONVIEWER_PROPOSAL_TABLE);
        final int row = proposalTAble.indexOf(storageExpressionName + " -- " + returnType, 0);
        if (row == -1) {
            throw new WidgetNotFoundException(storageExpressionName + " not found in proposals");
        }
        proposalTAble.select(row);
        proposalTAble.pressShortcut(Keystrokes.CR);
        bot.waitUntil(Conditions.shellCloses(proposalShell));
    }

    public static List<String> listExpressionProposal(final SWTBot bot, final int index) {
        bot.toolbarButtonWithId(SWTBOT_ID_EXPRESSIONVIEWER_DROPDOWN, index).click();
        final SWTBotShell proposalShell = bot.shellWithId(SWTBOT_ID_EXPRESSIONVIEWER_PROPOSAL_SHELL);
        final SWTBot proposalBot = proposalShell.bot();
        final SWTBotTable proposalTAble = proposalBot.tableWithId(SWTBOT_ID_EXPRESSIONVIEWER_PROPOSAL_TABLE);

        final List<String> result = new ArrayList<>();
        for (int i = 0; i < proposalTAble.rowCount(); i++) {
            result.add(proposalTAble.getTableItem(i).getText());
        }
        proposalShell.close();
        return result;
    }

    public static void pressUndo() {
        getKeybord().pressShortcut(SWT.CTRL, 'z');
    }

    public static void pressRedo() {
        if (System.getProperty("os.name").equals("Linux")) {
            getKeybord().pressShortcut(SWT.SHIFT | SWT.CTRL, 'z');
        } else {
            getKeybord().pressShortcut(SWT.CTRL, 'y');
        }
    }


}
