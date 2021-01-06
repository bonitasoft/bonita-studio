/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 35000 Grenoble
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
package org.bonitasoft.studio.tests.timer;

import static org.bonitasoft.studio.common.Messages.daysLabel;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Date;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.expression.BotExpressionEditorDialog;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTabItem;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestTimer implements SWTBotConstants {

    private static final String JAVA_LANG_LONG = "java.lang.Long";
    private static final String DEFAULT_TIMER_NAME = "Timer1";

    private final String editExpressionShellLabel = "Edit expression";

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule rule = new SWTGefBotRule(bot);

    @Test
    public void testEditTimerCondition() throws IOException {
        /* Import process */
        new BotApplicationWorkbenchWindow(bot).importBOSArchive()
                .setArchive(
                        TestTimer.class.getResource("TestTimer_1_0.bos"))
                .finish();

        final SWTBotGefEditor gefEditor = bot.gefEditor(bot.activeEditor().getTitle());
        /* Select step on which there is the connector to test */

        gefEditor.select(gefEditor.getEditPart("StartTimer").parent());

        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).setFocus();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "General");
        bot.button(Messages.editCondition).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.timerConditionWizardTitle));

        /* In the wizard add the data */
        final String testValue = "0 0 12 1/1 * ? *";
        bot.textWithId(SWTBOT_ID_EXPRESSIONVIEWER_TEXT).setText(testValue);
        bot.sleep(500); // Due to delayed observable on databinding
        bot.button(IDialogConstants.FINISH_LABEL).click();
        bot.activeEditor().save();
        /* Ensure that the field is correctly fill */
        assertEquals(testValue, bot.textWithLabel(Messages.timerCondition).getText());
    }

    /**
     * Set properties on a "Every Minute" Timer in a new Diagram.
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test
    public void testEditTimerConditionEveryMinute() throws Exception {

        // create new Diagram
        final SWTBotGefEditor gmfEditor = createNewEmptyDiagram();
        createStartTimerDiagram(gmfEditor);

        // "Timer1", "Every minute"
        setCycleType(gmfEditor, TestTimer.DEFAULT_TIMER_NAME, Messages.minutes);

        // in the shell editor for 'Every minute'
        bot.textWithLabel(Messages.every).setText("5");

        bot.button(Messages.generateCronButtonLabel).click();
        bot.button(IDialogConstants.FINISH_LABEL).click();

        assertEquals("Error: Wrong Timer Condition", "0 0/5 * 1/1 * ? *",
                bot.textWithLabel(Messages.timerCondition).getText());

        bot.menu("File").menu("Save").click();
        final IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
        assertTrue(status.getMessage(), status.isOK());
    }

    /**
     * Set properties on a "Every Year" Timer in a new Diagram.
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test
    public void testEditTimerConditionEveryYear() throws Exception {

        // create new Diagram
        final SWTBotGefEditor gmfEditor = createNewEmptyDiagram();
        createStartTimerDiagram(gmfEditor);

        // Every year
        setCycleType(gmfEditor, TestTimer.DEFAULT_TIMER_NAME, Messages.yearly);

        bot.radio(Messages.the).click();
        bot.comboBox(1).setSelection(Messages.second);
        bot.comboBox(3).setSelection(Messages.april);
        bot.comboBox(4).setSelection("08");
        bot.comboBox(5).setSelection("30");
        bot.button(Messages.generateCronButtonLabel).click();
        bot.button(IDialogConstants.FINISH_LABEL).click();

        assertEquals("Error: Wrong Timer Condition", "0 30 8 ? 4 MON#2 *",
                bot.textWithLabel(Messages.timerCondition).getText());

        bot.menu("File").menu("Save").click();
        final IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
        assertTrue(status.getMessage(), status.isOK());

    }

    /**
     * Set properties on a "Every Month" Timer in a new Diagram.
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testEditTimerConditionEveryMonth() throws Exception {

        // create new Diagram
        final SWTBotGefEditor gmfEditor = createNewEmptyDiagram();
        createStartTimerDiagram(gmfEditor);

        // "Timer1", "Every month"
        setCycleType(gmfEditor, TestTimer.DEFAULT_TIMER_NAME, Messages.monthly);

        bot.radio(Messages.the).click();
        bot.comboBox(0).setSelection(Messages.second);
        bot.comboBox(1).setSelection(Messages.tuesday);
        bot.button(Messages.generateCronButtonLabel).click();
        bot.button(IDialogConstants.FINISH_LABEL).click();

        Assert.assertEquals("Error: Wrong Timer Condition", "0 0 12 ? 1/1 TUE#2 *",
                bot.textWithLabel(Messages.timerCondition).getText());

        bot.menu("File").menu("Save").click();
        final IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
        assertTrue(status.getMessage(), status.isOK());

    }

    /**
     * Set properties on a "Every Week" Timer in a new Diagram.
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testEditTimerConditionEveryWeek() throws Exception {

        // create new Diagram
        final SWTBotGefEditor gmfEditor = createNewEmptyDiagram();
        createStartTimerDiagram(gmfEditor);

        // Timer 1, Every week
        setCycleType(gmfEditor, TestTimer.DEFAULT_TIMER_NAME, Messages.weekly);

        bot.checkBox(Messages.wednesday).select();
        // "at"
        bot.comboBox(0).setSelection("09");
        bot.comboBox(1).setSelection("15");
        bot.button(Messages.generateCronButtonLabel).click();
        bot.button(IDialogConstants.FINISH_LABEL).click();

        Assert.assertEquals("Error: Wrong Timer Condition", "0 15 9 ? * WED *",
                bot.textWithLabel(Messages.timerCondition).getText());

        bot.menu("File").menu("Save").click();
        final IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
        assertTrue(status.getMessage(), status.isOK());

    }

    /**
     * Set properties on a "Every Day" Timer in a new Diagram.
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testEditTimerConditionEveryDay() throws Exception {

        // create new Diagram
        final SWTBotGefEditor gmfEditor = createNewEmptyDiagram();
        createStartTimerDiagram(gmfEditor);

        setCycleType(gmfEditor, TestTimer.DEFAULT_TIMER_NAME, Messages.daily);

        // "Every day at"
        bot.comboBox(0).setSelection("14");
        bot.comboBox(1).setSelection("00");
        bot.button(Messages.generateCronButtonLabel).click();
        bot.button(IDialogConstants.FINISH_LABEL).click();

        // "Every day at xx:yy:zz"
        Assert.assertEquals("Error: Wrong Timer Condition", "0 0 14 1/1 * ? *",
                bot.textWithLabel(Messages.timerCondition).getText());

        bot.menu("File").menu("Save").click();
        final IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
        assertTrue(status.getMessage(), status.isOK());

    }

    /**
     * Set properties on a "Every Hour" Timer in a new Diagram.
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testEditTimerConditionEveryHour() throws Exception {

        // create new Diagram
        final SWTBotGefEditor gmfEditor = createNewEmptyDiagram();
        createStartTimerDiagram(gmfEditor);

        // "Timer1", "Every hour"
        setCycleType(gmfEditor, TestTimer.DEFAULT_TIMER_NAME, Messages.hourly);

        // text with label "Every"
        bot.textWithLabel(Messages.hourLabel).setText("4");
        bot.button(Messages.generateCronButtonLabel).click();
        bot.button(IDialogConstants.FINISH_LABEL).click();

        // "Every 4 hours"
        Assert.assertEquals("Error: Wrong Timer Condition", "0 0 0/1 1/1 * ? *",
                bot.textWithLabel(Messages.timerCondition).getText());

        bot.menu("File").menu("Save").click();
        final IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
        assertTrue(status.getMessage(), status.isOK());

    }

    /**
     * Set properties on a Duration Timer .
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testEditDurationTimerCondition() throws Exception {

        // Create a new Diagram and add a timer and a Service Task linked
        final SWTBotGefEditor gmfEditor = addTimerAndTaskToDiagram();

        // Set Timer1
        editTimerCondition(gmfEditor, DEFAULT_TIMER_NAME);

        // Select the Duration
        bot.radio(Messages.durationLabel).click();

        // Add a Duration of 2 days, 2 hours and 2 minutes.
        bot.spinnerWithLabel(org.bonitasoft.studio.common.Messages.daysLabel).setSelection(2);
        bot.spinnerWithLabel(org.bonitasoft.studio.common.Messages.hoursLabel).setSelection(2);
        bot.spinnerWithLabel(org.bonitasoft.studio.common.Messages.minutesLabel).setSelection(2);

        bot.button(Messages.generateDurationLabel).click();
        bot.button(IDialogConstants.FINISH_LABEL).click();

        final String conditionRes = 2 + " " + daysLabel + " " + "02:02:00";

        Assert.assertEquals("Error: Wrong Timer Condition", conditionRes,
                bot.textWithLabel(Messages.timerCondition).getText());

        bot.menu("File").menu("Save").click();
        final IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
        assertTrue(status.getMessage(), status.isOK());

    }

    /**
     * Set properties on a Fixed Date Timer .
     *
     * @throws InterruptedException
     * @throws ParseException
     */
    @Test
    public void testEditFixedDateTimerCondition() throws Exception {

        final SWTBotGefEditor gmfEditor = addTimerAndTaskToDiagram();

        // Set Timer1
        editTimerCondition(gmfEditor, DEFAULT_TIMER_NAME);

        // Select the date
        bot.radio(Messages.fixedDate).click();
        final Date d = new Date();
        bot.dateTime(0).setDate(d);
        bot.dateTime(1).setDate(d);
        bot.button(Messages.generateFixedDateLabel).click();
        bot.button(IDialogConstants.FINISH_LABEL).click();

        Assert.assertFalse("Error: Wrong Timer Condition", bot.textWithLabel(Messages.timerCondition).getText().isEmpty());

        bot.menu("File").menu("Save").click();
        final IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
        assertTrue(status.getMessage(), status.isOK());

    }

    /**
     * Set properties on a Fixed Date Timer .
     *
     * @throws InterruptedException
     * @throws ParseException
     */
    @Test
    public void testEditVariableScriptTimerCondition() throws Exception {

        final SWTBotGefEditor gmfEditor = addTimerAndTaskToDiagram();

        // Set Timer1
        editTimerCondition(gmfEditor, DEFAULT_TIMER_NAME);
        SWTBotShell activeShell = bot.activeShell();
        bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 0).click();

        // wait for "Edit Expression" shell
        bot.waitUntil(Conditions.shellIsActive(editExpressionShellLabel));
        new BotExpressionEditorDialog(bot, bot.activeShell()).selectScriptTab();

        bot.waitUntil(Conditions.widgetIsEnabled(bot.textWithLabel("Name")));
        bot.textWithLabel("Name").setText("myScript");

        bot.styledText().setText("120000");

        Assert.assertEquals("Error: Wrong Timer Condition setted", "120000", bot.styledText().getText());

        // "Return type" , "java.lang.Long"
        bot.comboBoxWithLabel(org.bonitasoft.studio.groovy.ui.Messages.returnType).setSelection(TestTimer.JAVA_LANG_LONG);

        Assert.assertEquals("Error: Wrong Timer Condition return type setted", TestTimer.JAVA_LANG_LONG,
                bot.comboBoxWithLabel(org.bonitasoft.studio.groovy.ui.Messages.returnType).getText());

        // in the shell editor for 'Every hour'
        bot.button(IDialogConstants.OK_LABEL).click();

        activeShell.setFocus();

        assertEquals("xpression not created correctly", "myScript", bot.text().getText());

        bot.button(IDialogConstants.FINISH_LABEL).click();

        Assert.assertEquals("Error: Wrong Timer Condition", "00:02:00",
                bot.textWithLabel(Messages.timerCondition).getText());

        bot.menu("File").menu("Save").click();
        final IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
        assertTrue(status.getMessage(), status.isOK());
    }

    /**
     * Set properties on a Fixed Date Timer .
     *
     * @throws InterruptedException
     * @throws ParseException
     */
    @Test
    public void testEditVariableConstantTimerCondition() throws Exception {

        final SWTBotGefEditor gmfEditor = addTimerAndTaskToDiagram();

        // Set Timer1
        editTimerCondition(gmfEditor, DEFAULT_TIMER_NAME);

        SWTBotShell activeShell = bot.activeShell();
        bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 0).click();

        // wait for "Edit Expression" shell
        bot.waitUntil(Conditions.shellIsActive(editExpressionShellLabel));

        new BotExpressionEditorDialog(bot, bot.activeShell())
        .selectScriptTab()
        .setScriptContent("120000")
        .setName("120000")
        .setReturnType(Long.class.getName())
        .ok();

        activeShell.setFocus();

        Assert.assertEquals("Error: Content of text field is not corrected.", "120000", bot.text().getText());
        bot.button(IDialogConstants.FINISH_LABEL).click();

        Assert.assertEquals("Error: Wrong Timer Condition", "00:02:00",
                bot.textWithLabel(Messages.timerCondition).getText());
        editTimerCondition(gmfEditor, DEFAULT_TIMER_NAME);
        Assert.assertTrue("Error: Wrong Timer Condition", bot.radio(Messages.durationLabel).isSelected());
        bot.button(IDialogConstants.CANCEL_LABEL).click();
        bot.menu("File").menu("Save").click();
        final IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
        assertTrue(status.getMessage(), status.isOK());

    }

    /**
     * @return
     */
    private SWTBotGefEditor addTimerAndTaskToDiagram() {
        // create new Diagram
        SWTBotTestUtil.createNewDiagram(bot);
        final SWTBotEditor activeEditor = bot.activeEditor();
        final String editorTitle = activeEditor.getTitle();

        final SWTBotGefEditor gmfEditor = bot.gefEditor(editorTitle);

        // add a Timer
        gmfEditor.activateTool("Timer");
        gmfEditor.click(400, 100);
        gmfEditor.click(400, 200);

        // Add Transition Timer1 -> Step1
        SWTBotTestUtil.selectTransitionFromSelectedElementAndDragIt(gmfEditor, "Step1", new Point(405, 105));

        // Add Service Task & Transition Timer1 -> Step2
        SWTBotTestUtil.selectTaskFromSelectedElementAndDragIt(gmfEditor, "Timer1", new Point(610, 110));
        return gmfEditor;
    }

    /**
     * In an editor, add a Start Timer, a Humand task and a Transition between them.
     *
     * @param gmfEditor
     * @throws InterruptedException
     */
    private void createStartTimerDiagram(final SWTBotGefEditor gmfEditor)
            throws InterruptedException {
        gmfEditor.activateTool("Start timer");
        gmfEditor.click(200, 100);
        final Matcher<Widget> matcher = WidgetMatcherFactory.withLabel("Timer1");
        bot.waitUntilWidgetAppears(Conditions.waitForWidget(matcher));
        gmfEditor.click(150, 150);

        // "Timer1" -> "Step1"
        SWTBotTestUtil.selectTaskFromSelectedElementAndDragIt(gmfEditor, "Timer1", new Point(400, 100));

    }

    /**
     * Create a Diagram, and remove the initial Start and Task.
     *
     * @return
     * @throws InterruptedException
     */
    private SWTBotGefEditor createNewEmptyDiagram() throws InterruptedException {
        SWTBotTestUtil.createNewDiagram(bot);
        final SWTBotEditor activeEditor = bot.activeEditor();
        final String editorTitle = activeEditor.getTitle();

        final SWTBotGefEditor gmfEditor = bot.gefEditor(editorTitle);

        // remove actual diagram
        gmfEditor.getEditPart("Start1").parent().select();

        bot.sleep(500);
        SWTBotTestUtil.pressDelete();
        gmfEditor.getEditPart("Step1").parent().select();

        bot.sleep(500);
        SWTBotTestUtil.pressDelete();
        return gmfEditor;
    }

    /**
     * Open the General Tab, launch the editor shell of a Timer and select the type of timer
     *
     * @param gmfEditor
     * @param timerName
     * @param typeToSelect
     */
    private void setCycleType(final SWTBotGefEditor gmfEditor, final String timerName, final String typeToSelect) {
        editTimerCondition(gmfEditor, timerName);

        bot.radio(Messages.cycle).click();
        final SWTBotTabItem tabItem = bot.tabItem(typeToSelect);
        Assert.assertNotNull("Error: " + typeToSelect + " element does not exist.", tabItem);
        tabItem.activate();
    }

    /**
     * Open the General Tab, launch the editor shell of a Timer
     *
     * @param gmfEditor
     * @param timerName
     */
    private void editTimerCondition(final SWTBotGefEditor gmfEditor, final String timerName) {
        gmfEditor.getEditPart(timerName).parent().select();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "General");

        bot.button(Messages.editCondition).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.timerConditionWizardTitle));
    }
}
