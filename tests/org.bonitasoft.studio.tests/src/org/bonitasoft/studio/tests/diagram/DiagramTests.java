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
package org.bonitasoft.studio.tests.diagram;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.data.i18n.Messages.datatypeLabel;
import static org.bonitasoft.studio.data.i18n.Messages.name;
import static org.bonitasoft.studio.data.i18n.Messages.newVariable;
import static org.bonitasoft.studio.form.properties.i18n.Messages.formFieldType;
import static org.bonitasoft.studio.properties.i18n.Messages.activityType;
import static org.bonitasoft.studio.properties.i18n.Messages.activityType_task;
import static org.bonitasoft.studio.properties.i18n.Messages.addForm;
import static org.bonitasoft.studio.properties.i18n.Messages.addFormTitle;
import static org.junit.Assert.*;

import java.util.List;

import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.identity.User;
import org.bonitasoft.engine.identity.UserNotFoundException;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.diagram.custom.editPolicies.ActivitySwitchEditPolicy;
import org.bonitasoft.studio.diagram.custom.figures.SlideMenuBarFigure;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.BOSWebServerManager;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.conditions.AssertionCondition;
import org.bonitasoft.studio.swtbot.framework.draw.BotGefProcessDiagramEditor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Mickael Istria
 * @author Florine Boudin
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class DiagramTests {

    private final SWTGefBot bot = new SWTGefBot();
    private static final String DATA_NAME_LABEL = name + " *";
    private static final String PAGEFLOW_LABEL = "Pageflow";

    @Test
    public void testDiagramTest() {
        SWTBotTestUtil.createNewDiagram(bot);
        final SWTBotEditor botEditor = bot.activeEditor();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
        gmfEditor.activateTool(Messages.Step_title);
        gmfEditor.click(200, 200);
        bot.waitUntil(Conditions.widgetIsEnabled(bot.toolbarButton("Save")));
        bot.toolbarButton("Save").click();
        final IGraphicalEditPart part = (IGraphicalEditPart) gmfEditor.mainEditPart().part();
        final MainProcess model = (MainProcess) part.resolveSemanticElement();
        final Pool pool = (Pool) model.getElements().get(0);
        final Lane lane = (Lane) pool.getElements().get(0);
        Assert.assertEquals("Lane should contain 3 nodes", 3, lane.getElements().size());
    }

    @Test
    public void testConvert() throws Exception {
        SWTBotTestUtil.createNewDiagram(bot);
        final SWTBotEditor botEditor = bot.activeEditor();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
        final ProcessDiagramEditor processEditor = (ProcessDiagramEditor) gmfEditor.getReference().getEditor(false);
        final SWTBotGefEditPart part = getPartRecursively(gmfEditor.rootEditPart(), "Step1");
        part.select();
        IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) part.part();
        final ActivitySwitchEditPolicy convertPolicy = (ActivitySwitchEditPolicy) graphicalEditPart
                .getEditPolicy(ActivitySwitchEditPolicy.SWITCH_TYPE_ROLE);
        final Class<?> beforeClass = graphicalEditPart.resolveSemanticElement().getClass();
        final SlideMenuBarFigure toolbarFigure = convertPolicy.getToolbarFigure();
        final int x = toolbarFigure.getBounds().x + toolbarFigure.getBounds().width / 2;
        int y = toolbarFigure.getBounds().y + toolbarFigure.getBounds().height / 2;
        gmfEditor.click(x, y);
        y += toolbarFigure.getBounds().height; // move cursor down to first sub item
        gmfEditor.click(x, y);
        bot.waitUntil(Conditions.widgetIsEnabled(bot.toolbarButton("Save")));
        bot.toolbarButton("Save").click();
        graphicalEditPart = (IGraphicalEditPart) processEditor.getDiagramGraphicalViewer().getSelectedEditParts().get(0);
        final Activity newModelElement = (Activity) graphicalEditPart.resolveSemanticElement();
        Assert.assertEquals("Step1", newModelElement.getName());
        Assert.assertNotSame(beforeClass, newModelElement.getClass());
    }

    @Test
    public void should_undo_redo_after_lane_creation() throws Exception {
        final BotApplicationWorkbenchWindow botApplicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        final BotGefProcessDiagramEditor activeProcessDiagramEditor = botApplicationWorkbenchWindow.createNewDiagram()
                .activeProcessDiagramEditor();
        final EObject mainProcess = activeProcessDiagramEditor.selectDiagram().getSelectedSemanticElement();
        final List<Pool> allPools = ModelHelper.getAllElementOfTypeIn(mainProcess, Pool.class);
        final Pool pool = allPools.get(0);
        activeProcessDiagramEditor.addLaneOnPool(pool.getName());
        bot.waitUntil(new AssertionCondition() {

            @Override
            protected void makeAssert() throws Exception {
                assertThat(pool.getElements()).hasSize(2);
            }
        });

        SWTBotTestUtil.pressUndo();
        bot.waitUntil(new AssertionCondition() {

            @Override
            protected void makeAssert() throws Exception {
                assertThat(pool.getElements()).hasSize(1);
            }
        });
        SWTBotTestUtil.pressRedo();
        bot.waitUntil(new AssertionCondition() {

            @Override
            protected void makeAssert() throws Exception {
                assertThat(pool.getElements()).hasSize(2);
            }
        });
    }

    /**
     * Create & run a 4 tasks process
     *
     * @author Florine Boudin
     * @throws Exception
     */
    @Test
    public void test4TasksDiagram() throws Exception {

        // create a new process
        SWTBotTestUtil.createNewDiagram(bot);

        assertThat(bot.viewById("org.bonitasoft.studio.views.overview").isActive()).isFalse();

        bot.menu("View").menu("Show overview").click();
        assertThat(bot.viewById("org.bonitasoft.studio.views.overview").isActive()).isTrue();

        assertThat(bot.viewById("org.bonitasoft.studio.views.overview.tree").isActive()).isFalse();
        bot.menu("Edit").menu("Find").click();
        assertThat(bot.viewById("org.bonitasoft.studio.views.overview.tree").isActive()).isTrue();

        //Create 3 variables
        final SWTBotEditor botEditor = bot.activeEditor();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
        gmfEditor.click(200, 200);

        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_DATA).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, SWTBotTestUtil.VIEWS_PROPERTIES_POOL_DATA_VARIABLES);

        // Create 3 new variables
        setNewVariable("varText", "Text", false);
        setNewVariable("varBoolean", "Boolean", false);
        setNewVariable("varInteger", "Integer", false);

        // check 3 variables where created
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_DATA).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, SWTBotTestUtil.VIEWS_PROPERTIES_POOL_DATA_VARIABLES);

        // check the table has 3 variables
        final SWTBotTable table = bot.tableWithId(SWTBotConstants.SWTBOT_ID_PROCESS_DATA_LIST);
        Assert.assertEquals("Error: wrong number of variable created", 3, table.rowCount());

        final String[] taskNameList = { "Step1", "Step2", "Step3", "Step4" };
        // Create 3 human tasks
        int i = 400;
        for (final String taskName : taskNameList) {

            setTaskAsHuman(gmfEditor, taskName);

            if (taskName != "Step4") {
                // create new Task
                Assert.assertNotNull("Error: No " + taskName + " task found.", gmfEditor.getEditPart(taskName));
                SWTBotTestUtil.selectTaskFromSelectedElementAndDragIt(gmfEditor, taskName, new Point(i, 110));
                i += 200;
            }

        }
        bot.menu("File").menu("Save");

        // check the gmfEditor has 4 human tasks
        final IGraphicalEditPart ig = (IGraphicalEditPart) gmfEditor.mainEditPart().part();
        final MainProcess mp = (MainProcess) ig.resolveSemanticElement();

        final int nbHumanTasks = ModelHelper.getAllItemsOfType(mp, ProcessPackage.Literals.TASK).size();
        Assert.assertEquals("Error: wrong number of tasks in the process.", 4, nbHumanTasks);

        final int nbTransistions = ModelHelper.getAllItemsOfType(mp, ProcessPackage.Literals.CONNECTION).size();
        Assert.assertEquals("Error: wrong number of connections in the process.", 4, nbTransistions);

        // For the 3 first tasks, add a form
        int itmp = 0;
        // create a form
        for (final String nametask : taskNameList) {

            if (nametask != "Step4") {
                gmfEditor.getEditPart(nametask).click();
                bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_APPLICATION).show();
                SWTBotTestUtil.selectTabbedPropertyView(bot, PAGEFLOW_LABEL);
                gmfEditor.getEditPart(nametask).click();
                // add a form
                bot.button(addForm, 0).click();
                // first shell "Add form..."
                bot.waitUntil(Conditions.shellIsActive(addFormTitle));

                if (SWTBotTestUtil.testingBosSp()) {
                    //second shell "Add form..."
                    final List<Button> buttons = bot.getFinder().findControls(new BaseMatcher<Button>() {

                        @Override
                        public void describeTo(final Description description) {

                        }

                        @Override
                        public boolean matches(final Object item) {
                            if (item instanceof Button) {
                                return IDialogConstants.NEXT_LABEL.equals(((Button) item).getText());
                            }
                            return false;
                        }

                    });
                    if (!buttons.isEmpty()) {
                        final SWTBotButton nextButton = bot.button(IDialogConstants.NEXT_LABEL);
                        if (nextButton != null) {
                            nextButton.click();
                        }
                    }
                }

                bot.tabItem(org.bonitasoft.studio.properties.i18n.Messages.processData).activate();

                // add 1 variable in the form
                if (itmp == 0) {
                    bot.tree().getTreeItem("varText").check();
                } else if (itmp == 1) {
                    bot.tree().getTreeItem("varInteger").check();
                } else {
                    bot.tree().getTreeItem("varBoolean").check();
                }

                final SWTBotShell activeShell = bot.activeShell();
                bot.button(IDialogConstants.FINISH_LABEL).click();
                bot.waitUntil(Conditions.shellCloses(activeShell));
                // add script to conver to an integer on "Step3"
                if (nametask.equals("Step2")) {
                    final SWTBotGefEditor formEditor = bot.gefEditor(bot.activeEditor().getTitle());
                    formEditor.getEditPart("Var Integer").click();
                    bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_FORM_GENERAL).show();
                    SWTBotTestUtil.selectTabbedPropertyView(bot, "Data");
                    bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 1).click();
                    final String valueOf = "Integer.valueOf(field_varInteger1)";
                    SWTBotTestUtil.setScriptExpression(bot, "theInteger", valueOf, Integer.class.getName());
                    bot.waitUntil(new DefaultCondition() {

                        @Override
                        public boolean test() throws Exception {
                            return bot.textWithId(ExpressionViewer.SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 2).getText()
                                    .equals("theInteger");
                        }

                        @Override
                        public String getFailureMessage() {
                            return "Expression not set properly";
                        }
                    }, 10000);
                }

                bot.activeEditor().saveAndClose();
                itmp++;
            }
        }

        // Create the form for the 4th task
        gmfEditor.getEditPart("Step4").click();

        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_APPLICATION).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, PAGEFLOW_LABEL);

        bot.button(addForm, 0).click();

        // first shell "Add form..."
        bot.waitUntil(Conditions.shellIsActive(addFormTitle));

        if (SWTBotTestUtil.testingBosSp()) {
            //second shell "Add form..."
            bot.button(IDialogConstants.NEXT_LABEL).click();
        }
        bot.tabItem(org.bonitasoft.studio.properties.i18n.Messages.processData).activate();
        bot.checkBox("Select all").select();

        // last shell "Add form..."
        bot.button(IDialogConstants.FINISH_LABEL).click();

        final SWTBotGefEditor formEditor = bot.gefEditor(bot.activeEditor().getTitle());

        final String[] varTab = { "Var Text", "Var Boolean", "Var Integer" };
        for (final String s : varTab) {

            formEditor.getEditPart(s).click();
            bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_FORM_GENERAL).show();
            SWTBotTestUtil.selectTabbedPropertyView(bot, "General");

            // "Field type"
            bot.comboBoxWithLabel(formFieldType).setSelection("Text");
            bot.activeEditor().save();
        }

        bot.activeEditor().saveAndClose();
        final IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
        assertTrue(status.getMessage(), status.isOK());

    }

    /**
     * Change the type of a Task to be 'Human'
     *
     * @param gmfEditor
     * @param nameTask name of the Task
     */
    private void setTaskAsHuman(final SWTBotGefEditor gmfEditor, final String nameTask) {
        gmfEditor.getEditPart(nameTask).click();

        // set the Task as Human Task
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "General");

        // "Task type" , "Human"
        bot.comboBoxWithLabel(activityType).setSelection(activityType_task);
    }

    /**
     * Add and set a new variable in the data entry of the General tab of a Task
     *
     * @param varName name of the variable
     * @param varType type of the variable : "Text", "Integer", "String", "Boolean", etc...
     * @param autoGenerateForm BOS-SP only : true if the checkBox must be selected, else false
     */
    private void setNewVariable(final String varName, final String varType, final boolean autoGenerateForm) {
        bot.buttonWithId(SWTBotConstants.SWTBOT_ID_ADD_PROCESS_DATA).click();

        // open shell "New variable"
        bot.waitUntil(Conditions.shellIsActive(newVariable));

        // "Name"
        bot.textWithLabel(DATA_NAME_LABEL).setText(varName);

        // "Data type"
        bot.comboBoxWithLabel(datatypeLabel).setSelection(varType);

        bot.button(IDialogConstants.FINISH_LABEL).click();
    }

    public SWTBotGefEditPart getPartRecursively(final SWTBotGefEditPart from, final String label) {
        for (final SWTBotGefEditPart child : from.children()) {
            final Element model = (Element) ((IGraphicalEditPart) child.part()).resolveSemanticElement();
            if (model.getName().equals(label)) {
                return child;
            }
        }
        SWTBotGefEditPart res = null;
        for (final SWTBotGefEditPart child : from.children()) {
            res = getPartRecursively(child, label);
            if (res != null) {
                return res;
            }
        }
        return res;
    }
}
