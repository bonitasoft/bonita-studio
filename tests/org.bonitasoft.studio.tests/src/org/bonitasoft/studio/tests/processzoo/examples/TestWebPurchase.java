/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.processzoo.examples;

import static org.bonitasoft.studio.actors.i18n.Messages.selectActorTitle;
import static org.bonitasoft.studio.actors.i18n.Messages.useTaskActors;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestWebPurchase implements SWTBotConstants {

    private String diagramTitle = null;

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule rule = new SWTGefBotRule(bot);

    @Test
    public void testWebPurchase() throws Exception {
        SWTBotTestUtil.createNewDiagram(bot);
        final SWTBotEditor botEditor = bot.activeEditor();
        diagramTitle = botEditor.getTitle();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

        final SWTBotGefEditPart element = gmfEditor.getEditPart("Step1");
        element.click();
        gmfEditor.clickContextMenu("Delete");
        gmfEditor.click(200, 200);
        gmfEditor.clickContextMenu("Delete");
        gmfEditor.click(200, 200);
        configurePool(gmfEditor);
        stepGateWayXor(gmfEditor);
        stepSalesReview(gmfEditor);
        stepMoreInfo(gmfEditor);
        stepPay(gmfEditor);
        stepReject(gmfEditor);
        stepExpressDelivery(gmfEditor);
        stepArchive(gmfEditor);
        finalEvent(gmfEditor);
        bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_SAVE_EDITOR).click();
        configureProcess(gmfEditor);
        runProcess(gmfEditor);
    }

    private void configurePool(final SWTBotGefEditor gmfEditor) {
        bot.viewById(SWTBotTestUtil.VIEWS_TREE_OVERVIEW).show();
        SWTBotView viewById = bot.viewById(SWTBotTestUtil.VIEWS_TREE_OVERVIEW);
        viewById.setFocus();
        bot.tree().select(0);

        gmfEditor.setFocus();

        new BotProcessDiagramPerspective(bot).getDiagramPropertiesPart()
                .selectGeneralTab()
                .selectPoolTab()
                .setName("Web Purchase")
                .setVersion("1.6");

        gmfEditor.setFocus();

        selectPoolVariablesTabbedPropertyView();
        bot.buttonWithId(SWTBOT_ID_ADD_PROCESS_DATA).click();
        SWTBotTestUtil.addNewData(bot, "customerEmail", "Text", false, null);
        bot.buttonWithId(SWTBOT_ID_ADD_PROCESS_DATA).click();
        SWTBotTestUtil.addNewData(bot, "customerName", "Text", false, null);
        bot.buttonWithId(SWTBOT_ID_ADD_PROCESS_DATA).click();
        SWTBotTestUtil.addNewData(bot, "customerPhone", "Text", false, null);
        final Map<String, List<String>> options = new HashMap<>();
        final ArrayList<String> choices = new ArrayList<>();
        choices.add("TV");
        choices.add("Mobile Phone");
        choices.add("Laptop");
        options.put("PromotionalProducts", choices);
        bot.buttonWithId(SWTBOT_ID_ADD_PROCESS_DATA).click();
        SWTBotTestUtil.addListOfOptionData(bot, "products", "Text", options, false, "TV");
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).setFocus();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Actors");
        bot.button("Add").click();
        bot.table().click(0, 0);
        final SWTBotButton initatorButton = bot.button("Set as initiator");
        bot.waitUntil(Conditions.widgetIsEnabled(initatorButton));
        initatorButton.click();
    }

    private void stepGateWayXor(final SWTBotGefEditor gmfEditor) {
        SWTBotTestUtil.selectElementInContextualPaletteAndDragIt(gmfEditor, "Start1",
                SWTBotTestUtil.CONTEXTUALPALETTE_GATEWAY, PositionConstants.EAST);
        SWTBotTestUtil.selectTabbedPropertyView(bot, "General");
        bot.comboBoxWithLabel(Messages.gatewayType).setSelection(Messages.gatwetypeXor);
        SWTBotTestUtil.configureSequenceFlow(bot, "sequenceFlow1", "Web Purchase", false, null, null);
    }

    private void stepSalesReview(final SWTBotGefEditor gmfEditor) {
        SWTBotTestUtil.selectElementInContextualPaletteAndDragIt(gmfEditor, "Gateway1",
                SWTBotTestUtil.CONTEXTUALPALETTE_STEP, PositionConstants.EAST);
        SWTBotTestUtil.selectTabbedPropertyView(bot, "General");
        bot.textWithLabel("Name").setText("Sales Review");
        bot.sleep(1000);
        bot.comboBoxWithLabel("Task type").setSelection("Human");
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).setFocus();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Actors");
        bot.radio(useTaskActors).click();
        bot.comboBoxWithLabel(selectActorTitle).setSelection(0);

        bot.activeShell().setFocus();
        selectDataVariablesTabbedPropertyView();
        final Map<String, List<String>> options = new HashMap<>();
        final List<String> choices = new ArrayList<>();
        choices.add("Approve");
        choices.add("Reject");
        choices.add("More Info");
        options.put("Approval", choices);
        bot.button(Messages.AddSimple).click();
        SWTBotTestUtil.addListOfOptionData(bot, "decision", "Text", options, false, "Approve");
        bot.editorByTitle(diagramTitle).show();
        bot.editorByTitle(diagramTitle).setFocus();
        SWTBotTestUtil.configureSequenceFlow(bot, "customerOrder", "Web Purchase", false, "true",
                ExpressionConstants.SCRIPT_TYPE);
    }

    protected void selectDataVariablesTabbedPropertyView() {
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_DATA).show();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_DATA).setFocus();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Local variables");
    }

    protected void selectPoolVariablesTabbedPropertyView() {
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_DATA).show();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_DATA).setFocus();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Pool variables");
    }

    private void stepMoreInfo(final SWTBotGefEditor gmfEditor) {
        bot.editorByTitle(diagramTitle).show();
        bot.editorByTitle(diagramTitle).setFocus();
        SWTBotTestUtil.selectElementInContextualPaletteAndDragIt(gmfEditor, "Sales Review",
                SWTBotTestUtil.CONTEXTUALPALETTE_STEP, PositionConstants.SOUTH);
        SWTBotTestUtil.selectTabbedPropertyView(bot, "General");
        bot.textWithLabel("Name").setText("More Info");
        bot.sleep(1000);
        bot.comboBoxWithLabel("Task type").setSelection("Human");
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).setFocus();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Actors");
        bot.radio(useTaskActors).click();
        bot.comboBoxWithLabel(selectActorTitle).setSelection(0);
        selectDataVariablesTabbedPropertyView();
        bot.button("Add...").click();
        SWTBotTestUtil.addNewData(bot, "comment", "Text", false, "add a comment");
        bot.editorByTitle(diagramTitle).show();
        bot.editorByTitle(diagramTitle).setFocus();
        SWTBotTestUtil.configureSequenceFlow(bot, "add a comment", "Web Purchase", false, "decision==\"More info\"",
                ExpressionConstants.SCRIPT_TYPE);
        SWTBotTestUtil.addSequenceFlow(bot, gmfEditor, "More Info", "Gateway1", PositionConstants.SOUTH);
        SWTBotTestUtil.configureSequenceFlow(bot, "sequenceFlow2", "Web Purchase", false, "true",
                ExpressionConstants.SCRIPT_TYPE);
    }

    private void stepPay(final SWTBotGefEditor gmfEditor) {
        bot.editorByTitle(diagramTitle).show();
        bot.editorByTitle(diagramTitle).setFocus();
        SWTBotTestUtil.selectElementInContextualPaletteAndDragIt(gmfEditor, "Sales Review",
                SWTBotTestUtil.CONTEXTUALPALETTE_STEP, PositionConstants.EAST);
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).setFocus();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "General");
        bot.textWithLabel("Name").setText("Pay");
        bot.sleep(1000);
        gmfEditor.getEditPart("Pay").click();
        bot.comboBoxWithLabel("Task type").setSelection("Human");
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Actors");
        bot.radio(useTaskActors).click();
        bot.comboBoxWithLabel(selectActorTitle).setSelection("Actor1");

        selectDataVariablesTabbedPropertyView();
        gmfEditor.getEditPart("Pay").click();
        bot.button("Add...").click();
        SWTBotTestUtil.addNewData(bot, "chooseExpressDelivery", "Boolean", false, "true");
        bot.button("Add...").click();
        SWTBotTestUtil.addNewData(bot, "creditCardNumber", "Text", false, "add your credit card number");
        bot.button("Add...").click();
        try {
            SWTBotTestUtil.addNewData(bot, "expirationDate", "Date", false, "Now");
        } catch (IllegalArgumentException e) {
            bot.captureScreenshot("screenshots/illegalArgumentException.jpg");
            throw e;
        }
        bot.editorByTitle(diagramTitle).show();
        bot.editorByTitle(diagramTitle).setFocus();
        SWTBotTestUtil.configureSequenceFlow(bot, "if approved", "Web Purchase", false, "decision==\"Approve\"",
                ExpressionConstants.SCRIPT_TYPE);
    }

    private void stepReject(final SWTBotGefEditor gmfEditor) {
        SWTBotTestUtil
                .selectElementInContextualPaletteAndDragIt(gmfEditor, "Sales Review",
                        SWTBotTestUtil.CONTEXTUALPALETTE_STEP,
                        PositionConstants.SOUTH_EAST);
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "General");
        bot.textWithLabel("Name").setText("Reject");
        bot.sleep(1000);
        SWTBotTestUtil.configureSequenceFlow(bot, "if not approved", "Web Purchase", false, "decision==\"Reject\"",
                ExpressionConstants.SCRIPT_TYPE);
    }

    private void stepExpressDelivery(final SWTBotGefEditor gmfEditor) {
        SWTBotTestUtil.selectElementInContextualPaletteAndDragIt(gmfEditor, "Pay",
                SWTBotTestUtil.CONTEXTUALPALETTE_STEP,
                PositionConstants.EAST);
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "General");
        bot.textWithLabel("Name").setText("Express Delivery");
        bot.sleep(500);
        SWTBotTestUtil.configureSequenceFlow(bot, "if yes", "Web Purchase", false, "chooseExpressDelivery",
                ExpressionConstants.VARIABLE_TYPE);
    }

    private void stepArchive(final SWTBotGefEditor gmfEditor) {
        SWTBotTestUtil.selectElementInContextualPaletteAndDragIt(gmfEditor, "Pay",
                SWTBotTestUtil.CONTEXTUALPALETTE_STEP,
                PositionConstants.SOUTH_EAST);
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "General");
        bot.textWithLabel("Name").setText("Archive");
        bot.sleep(500);
        SWTBotTestUtil.configureSequenceFlow(bot, "if no", "Web Purchase", false, "chooseExpressDelivery",
                ExpressionConstants.VARIABLE_TYPE);
        SWTBotTestUtil.addSequenceFlow(bot, gmfEditor, "Reject", "Archive", PositionConstants.WEST);
        SWTBotTestUtil.configureSequenceFlow(bot, "sequenceFlow3", "Web Purchase", false, null, null);
        SWTBotTestUtil.addSequenceFlow(bot, gmfEditor, "Express Delivery", "Archive", PositionConstants.NORTH);
        SWTBotTestUtil.configureSequenceFlow(bot, "sequenceFlow4", "Web Purchase", false, null, null);
    }

    private void finalEvent(final SWTBotGefEditor gmfEditor) {
        gmfEditor.activateTool("End");
        final Point targetLocation = SWTBotTestUtil.computeTargetLocation(gmfEditor, "Archive", PositionConstants.EAST);
        gmfEditor.click(targetLocation.x, targetLocation.y);
        SWTBotTestUtil.addSequenceFlow(bot, gmfEditor, "Archive", "End1", PositionConstants.WEST);
        SWTBotTestUtil.configureSequenceFlow(bot, "sequenceFlow4", "Web Purchase", false, null,
                ExpressionConstants.VARIABLE_TYPE);
    }

    private void configureProcess(final SWTBotGefEditor gmfEditor) {
        final IGraphicalEditPart part = (IGraphicalEditPart) gmfEditor.mainEditPart().part();
        final MainProcess model = (MainProcess) part.resolveSemanticElement();
        final Pool pool = (Pool) model.getElements().get(0);
        final String processLabel = pool.getName() + " (" + pool.getVersion() + ")";
        bot.toolbarDropDownButtonWithId(SWTBotConstants.SWTBOT_ID_CONFIGURE_TOOLITEM).click();
        bot.waitUntil(Conditions.shellIsActive("Local configuration for " + processLabel));
        bot.table().getTableItem("Actor mapping").select();
        bot.tree().getTreeItem("Actor1 -- Not mapped").select();
        SWTBotShell activeShell = bot.activeShell();
        bot.button("Groups...").click();
        bot.table().getTableItem(1).check();
        bot.sleep(1000);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        activeShell.setFocus();
        bot.button(IDialogConstants.FINISH_LABEL).click();
    }

    private void runProcess(final SWTBotGefEditor gmfEditor) throws Exception {
        final IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
        assertTrue(status.getMessage(), status.isOK());
    }
}
