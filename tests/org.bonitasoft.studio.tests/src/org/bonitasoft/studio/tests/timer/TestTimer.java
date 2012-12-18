/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.tests.timer;

import static org.bonitasoft.studio.diagram.custom.Messages.localConfigurationFor;
import static org.bonitasoft.studio.common.Messages.daysLabel;
import static org.bonitasoft.studio.properties.i18n.Messages.deadlineVarNameLabel;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotList;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * @author Aurelien Pupier
 *
 */
public class TestTimer extends SWTBotGefTestCase {


    private static final String JAVA_LANG_LONG = "java.lang.Long";
    private static final String DEFAULT_TIMER_NAME = "Timer1";
    private static boolean disablePopup;

    private final String editExpressionShellLabel = "Edit expression";


    @BeforeClass
    public static void setUpBeforeClass() {
        disablePopup = FileActionDialog.getDisablePopup();
        FileActionDialog.setDisablePopup(true);
    }


    @AfterClass
    public static void tearDownAfterClass() {

        FileActionDialog.setDisablePopup(disablePopup);
    }

    @Override
    @After
    public void tearDown() {
        // if it fails before the end of the test
        bot.saveAllEditors();
    }

    public void testEditTimerCondition() throws IOException{
        /*Import process*/
        SWTBotTestUtil.importProcessWIthPathFromClass(bot, "TestTimer_1_0.bos", "BOS Archive", "TestTimer", this.getClass(), false);
        SWTBotGefEditor gefEditor = bot.gefEditor(bot.activeEditor().getTitle());
        /*Select step on which there is the connector to test*/

        gefEditor.select(gefEditor.getEditPart("StartTimer").parent());

        /*Open connector configuration wizard to test the connector*/
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).setFocus();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "General");
        bot.button(Messages.editCondition).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.timerConditionWizardTitle));

        /*In the wizard add the data*/
        bot.list().select(Messages.StartTimerCondition_script);
        String testValue = "Value for test";
        bot.text().setText(testValue);
        bot.sleep(1000); // Due to delayed observable on databinding
        bot.button(IDialogConstants.FINISH_LABEL).click();
        bot.activeEditor().save();
        /*Ensure that the field is correctly fill*/
        assertEquals(testValue, bot.textWithLabel(Messages.timerCondition).getText());
        bot.saveAllEditors();
        bot.closeAllEditors();

    }

    /** Set properties on a "Every Minute" Timer in a new Diagram.
     * 
     * @throws IOException
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public void testEditTimerConditionEveryMinute() throws Exception{

        // create new Diagram
        SWTBotGefEditor gmfEditor = createNewEmptyDiagram();
        createStartTimerDiagram(gmfEditor);

        // "Timer1", "Every minute"
        changeTimerProperties(gmfEditor, TestTimer.DEFAULT_TIMER_NAME, Messages.StartTimerCondition_everyMinutes);

        // in the shell editor for 'Every minute'
        bot.textWithLabel(Messages.StartTimerCondition_every).setText("5");
        bot.button(IDialogConstants.FINISH_LABEL).click();

        Assert.assertEquals("Error: Wrong Timer Condition",Messages.StartTimerCondition_every+" 5 "+Messages.StartTimerCondition_minutes,bot.textWithLabel(Messages.timerCondition).getText());

        bot.menu("Diagram").menu("Save").click();
        IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
        assertTrue(status.getMessage(),status.isOK());


    }


    /** Set properties on a "Every Year" Timer in a new Diagram.
     * 
     * @throws IOException
     * @throws InterruptedException
     * @throws ExecutionException 
     */
    public void testEditTimerConditionEveryYear() throws Exception{

        // create new Diagram
        SWTBotGefEditor gmfEditor = createNewEmptyDiagram();
        createStartTimerDiagram(gmfEditor);
        AbstractProcess proc = ModelHelper.getParentProcess(((IGraphicalEditPart)gmfEditor.getEditPart("Step1").part()).resolveSemanticElement());

        // Every year
        changeTimerProperties(gmfEditor, TestTimer.DEFAULT_TIMER_NAME, Messages.StartTimerCondition_everyYear);

        // in the shell editor for 'Every year'
        bot.checkBox().select();
        // "Every"
        bot.textWithLabel(Messages.StartTimerCondition_every).setText("3");
        bot.button(IDialogConstants.FINISH_LABEL).click();

        Assert.assertEquals("Error: Wrong Timer Condition",Messages.StartTimerCondition_every+" 3 "+Messages.StartTimerCondition_dayOfTheYear,bot.textWithLabel(Messages.timerCondition).getText());

        bot.menu("Diagram").menu("Save").click();
        IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
        assertTrue(status.getMessage(),status.isOK());

    }

    /** Set properties on a "Fixe Date" Timer in a new Diagram.
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    public void testEditTimerConditionFixeDate() throws Exception{

        // create new Diagram
        SWTBotGefEditor gmfEditor = createNewEmptyDiagram();
        createStartTimerDiagram(gmfEditor);
        // Every year
        changeTimerProperties(gmfEditor, TestTimer.DEFAULT_TIMER_NAME, Messages.StartTimerCondition_fixedDate);

        bot.button(IDialogConstants.FINISH_LABEL).click();

        Assert.assertTrue("Error: Wrong Timer Condition",bot.textWithLabel(Messages.timerCondition).getText().startsWith("at"));

        bot.menu("Diagram").menu("Save").click();
        IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
        assertTrue(status.getMessage(),status.isOK());

    }

    /** Set properties on a "Every Month" Timer in a new Diagram.
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    public void testEditTimerConditionEveryMonth() throws Exception{

        // create new Diagram
        SWTBotGefEditor gmfEditor = createNewEmptyDiagram();
        createStartTimerDiagram(gmfEditor);

        // "Timer1", "Every month"
        changeTimerProperties(gmfEditor, TestTimer.DEFAULT_TIMER_NAME, Messages.StartTimerCondition_everyMonth);

        // in the shell editor for 'Every month'
        bot.checkBox().select();

        // "Day of month in week"
        bot.comboBox(0).setSelection(Messages.StartTimerCondition_dayOfMonthInWeek);
        // "Every"
        bot.textWithLabel(Messages.StartTimerCondition_every).setText("2");
        // "Monday"
        bot.comboBox(1).setSelection(Messages.monday);
        bot.button(IDialogConstants.FINISH_LABEL).click();

        Assert.assertEquals("Error: Wrong Timer Condition",Messages.StartTimerCondition_every+" 2 "+Messages.monday+" "+Messages.StartTimerCondition_ofEachMonth,bot.textWithLabel(Messages.timerCondition).getText());

        bot.menu("Diagram").menu("Save").click();
        IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
        assertTrue(status.getMessage(),status.isOK());

    }
    /** Set properties on a "Every Week" Timer in a new Diagram.
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    public void testEditTimerConditionEveryWeek() throws Exception{

        // create new Diagram
        SWTBotGefEditor gmfEditor = createNewEmptyDiagram();
        createStartTimerDiagram(gmfEditor);

        // Timer 1, Every week
        changeTimerProperties(gmfEditor, TestTimer.DEFAULT_TIMER_NAME, Messages.StartTimerCondition_everyWeek);

        // in the shell editor for 'Every minute'
        bot.checkBox().select();

        bot.comboBox().setSelection(Messages.wednesday);
        // "at"
        bot.dateTimeWithLabel(Messages.StartTimerCondition_at).setDate(new Date());

        bot.button(IDialogConstants.FINISH_LABEL).click();

        Assert.assertEquals("Error: Wrong Timer Condition",Messages.StartTimerCondition_every+" "+Messages.wednesday+" "+Messages.StartTimerCondition_ofEachWeek,bot.textWithLabel(Messages.timerCondition).getText());

        bot.menu("Diagram").menu("Save").click();
        IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
        assertTrue(status.getMessage(),status.isOK());

    }

    /** Set properties on a "Every Day" Timer in a new Diagram.
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    public void testEditTimerConditionEveryDay() throws Exception{

        // create new Diagram
        SWTBotGefEditor gmfEditor = createNewEmptyDiagram();
        createStartTimerDiagram(gmfEditor);

        changeTimerProperties(gmfEditor, TestTimer.DEFAULT_TIMER_NAME, Messages.StartTimerCondition_everyDay);

        // in the shell editor for 'Every day'
        bot.checkBox().select();


        Calendar calendar = Calendar.getInstance();

        String s = Messages.StartTimerCondition_everyDayAt + " " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE)
                + ":" + calendar.get(Calendar.SECOND);
        System.out.println("s = "+s);
        // "Every day at"
        bot.dateTimeWithLabel(Messages.StartTimerCondition_everyDayAt).setDate(calendar.getTime());

        bot.button(IDialogConstants.FINISH_LABEL).click();

        // "Every day at xx:yy:zz"
        Assert.assertEquals("Error: Wrong Timer Condition",s,bot.textWithLabel(Messages.timerCondition).getText());

        bot.menu("Diagram").menu("Save").click();
        IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
        assertTrue(status.getMessage(),status.isOK());

    }

    /** Set properties on a "Every Hour" Timer in a new Diagram.
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    public void testEditTimerConditionEveryHour() throws Exception{

        // create new Diagram
        SWTBotGefEditor gmfEditor = createNewEmptyDiagram();
        createStartTimerDiagram(gmfEditor);

        // "Timer1", "Every hour"
        changeTimerProperties(gmfEditor, TestTimer.DEFAULT_TIMER_NAME, Messages.StartTimerCondition_everyHour);

        // in the shell editor for 'Every hour'
        bot.checkBox().select();

        // text with label "Every"
        bot.textWithLabel(Messages.StartTimerCondition_every).setText("4");

        bot.button(IDialogConstants.FINISH_LABEL).click();

        // "Every 4 hours"
        Assert.assertEquals("Error: Wrong Timer Condition",Messages.StartTimerCondition_every+" 4 "+Messages.StartTimerCondition_hours,bot.textWithLabel(Messages.timerCondition).getText());

        bot.menu("Diagram").menu("Save").click();
        IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
        assertTrue(status.getMessage(),status.isOK());

    }

    /** Set properties on a Duration Timer .
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    public void testEditDurationTimerCondition() throws Exception{

        // Create a new Diagram and add a timer and a Service Task linked
        SWTBotGefEditor gmfEditor = addTimerAndTaskToDiagram();

        // Set Timer1
        editTimerCondition(gmfEditor,DEFAULT_TIMER_NAME);

        // Select the Duration
        bot.radio(Messages.durationLabel).click();

        // Add a Duration of 2 days, 2 hours and 2 minutes.
        bot.spinnerWithLabel(org.bonitasoft.studio.common.Messages.daysLabel).setSelection(2);
        bot.spinnerWithLabel(org.bonitasoft.studio.common.Messages.hoursLabel).setSelection(2);
        bot.spinnerWithLabel(org.bonitasoft.studio.common.Messages.minutesLabel).setSelection(2);
        // in the shell editor for 'Every hour'

        bot.button(IDialogConstants.FINISH_LABEL).click();

        String conditionRes = 2+" "+daysLabel+" "+"02:02:00";

        Assert.assertEquals("Error: Wrong Timer Condition",conditionRes,bot.textWithLabel(Messages.timerCondition).getText());

        bot.menu("Diagram").menu("Save").click();
        IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
        assertTrue(status.getMessage(),status.isOK());

    }




    /**Set properties on a Fixed Date Timer .
     * 
     * @throws InterruptedException
     * @throws ParseException
     */
    public void testEditVariableTimerCondition() throws Exception{

        SWTBotGefEditor gmfEditor = addTimerAndTaskToDiagram();

        // Set Timer1
        editTimerCondition(gmfEditor,DEFAULT_TIMER_NAME);

        // Select the Variable
        bot.radio(Messages.varDataType).click();
        bot.waitUntil(Conditions.widgetIsEnabled(bot.textWithLabel(deadlineVarNameLabel)));
        SWTBotText text = bot.textWithLabel(deadlineVarNameLabel);
        
        Assert.assertEquals("Error: Wrong Initial Timer Condition setted","0",text.getText());

        text.setText("120000");
        bot.sleep(1000);

        Assert.assertEquals("Error: Wrong Timer Condition setted","120000",text.getText());
        bot.button(IDialogConstants.FINISH_LABEL).click();

        bot.waitUntil(Conditions.widgetIsEnabled(bot.textWithLabel(Messages.timerCondition)));
        Assert.assertEquals("Error: Wrong Timer Condition","00:02:00",bot.textWithLabel(Messages.timerCondition).getText());

        bot.menu("Diagram").menu("Save").click();
        IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
        assertTrue(status.getMessage(),status.isOK());


    }

    /**Set properties on a Fixed Date Timer .
     * 
     * @throws InterruptedException
     * @throws ParseException
     */
    public void testEditVariableScriptTimerCondition() throws Exception{

        SWTBotGefEditor gmfEditor = addTimerAndTaskToDiagram();

        // Set Timer1
        editTimerCondition(gmfEditor,DEFAULT_TIMER_NAME);

        // Select the Variable
        bot.radio(Messages.varDataType).click();

        bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 0).click();


        // wait for "Edit Expression" shell
        bot.waitUntil(Conditions.shellIsActive(editExpressionShellLabel));


        bot.table().select("Script");

        bot.waitUntil(Conditions.widgetIsEnabled(bot.textWithLabel("Name *")));
        bot.textWithLabel("Name *").setText("myScript");

        bot.styledText().setText("120000");

        Assert.assertEquals("Error: Wrong Timer Condition setted","120000",bot.styledText().getText());

        // "Return type" , "java.lang.Long"
        bot.comboBoxWithLabel(org.bonitasoft.studio.groovy.ui.Messages.returnType).setSelection(TestTimer.JAVA_LANG_LONG);

        Assert.assertEquals("Error: Wrong Timer Condition return type setted",TestTimer.JAVA_LANG_LONG,bot.comboBoxWithLabel(org.bonitasoft.studio.groovy.ui.Messages.returnType).getText());
        bot.sleep(3000);

        // in the shell editor for 'Every hour'
        bot.button(IDialogConstants.OK_LABEL).click();

        assertEquals("xpression not created correctly", "myScript",bot.text().getText());


        bot.button(IDialogConstants.FINISH_LABEL).click();

        String conditionRes = "00:02:00";
        bot.sleep(1000);
        Assert.assertEquals("Error: Wrong Timer Condition",conditionRes,bot.textWithLabel(Messages.timerCondition).getText());

        bot.menu("Diagram").menu("Save").click();
        IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
        assertTrue(status.getMessage(),status.isOK());


    }

    /**Set properties on a Fixed Date Timer .
     * 
     * @throws InterruptedException
     * @throws ParseException
     */
    public void testEditVariableConstantTimerCondition() throws Exception{

        SWTBotGefEditor gmfEditor = addTimerAndTaskToDiagram();

        // Set Timer1
        editTimerCondition(gmfEditor,DEFAULT_TIMER_NAME);

        // Select and Edit the Variable
        bot.radio(Messages.varDataType).click();
        bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 0).click();


        // wait for "Edit Expression" shell
        bot.waitUntil(Conditions.shellIsActive(editExpressionShellLabel));


        bot.table().select("Constant");

        Matcher<Widget> matcher = WidgetMatcherFactory.withLabel("Value");

        bot.waitUntil(Conditions.waitForWidget(matcher));
        bot.textWithLabel("Value").setText("120000");

        Assert.assertEquals("Error: Wrong Timer Condition setted","120000",bot.textWithLabel("Value").getText());

        // "Return type" , "java.lang.Long"
        SWTBotCombo returnTypeCombo = bot.comboBoxWithLabel(org.bonitasoft.studio.groovy.ui.Messages.returnType);
        returnTypeCombo.setSelection(TestTimer.JAVA_LANG_LONG);

        Assert.assertEquals("Error: Wrong Timer Condition return type setted",TestTimer.JAVA_LANG_LONG,returnTypeCombo.getText());
        bot.sleep(3000);

        // in the shell editor for 'Every hour'
        bot.button(IDialogConstants.OK_LABEL).click();


        Assert.assertEquals("Error: Content of text field is not corrected.", "120000", bot.text().getText());
        //assertEquals("xpression not created correctly", "myScript",bot.text().getText());


        bot.button(IDialogConstants.FINISH_LABEL).click();

        String conditionRes = "00:02:00";
        bot.sleep(1000);
        Assert.assertEquals("Error: Wrong Timer Condition",conditionRes,bot.textWithLabel(Messages.timerCondition).getText());

        bot.menu("Diagram").menu("Save").click();
        IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
        assertTrue(status.getMessage(),status.isOK());


    }


    /**
     * @return
     */
    private SWTBotGefEditor addTimerAndTaskToDiagram() {
        // create new Diagram
        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotEditor activeEditor = bot.activeEditor();
        String editorTitle = activeEditor.getTitle();

        SWTBotGefEditor gmfEditor = bot.gefEditor(editorTitle);

        // add a Timer
        gmfEditor.activateTool("Timer");
        gmfEditor.click(400, 100);
        gmfEditor.click(400, 200);

        // Add Transition Timer1 -> Step1
        SWTBotTestUtil.selectTransitionFromSelectedElementAndDragIt(gmfEditor, "Step1",  new Point(405, 105) );

        // Add Service Task & Transition Timer1 -> Step2
        SWTBotTestUtil.selectTaskFromSelectedElementAndDragIt(gmfEditor, "Timer1", new Point(610,110));
        return gmfEditor;
    }


    /** In an editor, add a Start Timer, a Humand task and a Transition between them.
     * @param gmfEditor
     * @throws InterruptedException
     */
    private void createStartTimerDiagram(SWTBotGefEditor gmfEditor)
            throws InterruptedException {
        gmfEditor.activateTool("Start timer");
        gmfEditor.click(200, 100);
        Matcher<Widget> matcher =  WidgetMatcherFactory.withLabel("Timer1");
        bot.waitUntilWidgetAppears(Conditions.waitForWidget(matcher));
        gmfEditor.click(150, 150);

        // "Timer1" -> "Step1"
        SWTBotTestUtil.selectTaskFromSelectedElementAndDragIt(gmfEditor, "Timer1", new Point(400,100));

    }


    /** Create a Diagram, and remove the initial Start and Task.
     * @return
     * @throws InterruptedException
     */
    private SWTBotGefEditor createNewEmptyDiagram() throws InterruptedException {
        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotEditor activeEditor = bot.activeEditor();
        String editorTitle = activeEditor.getTitle();

        SWTBotGefEditor gmfEditor = bot.gefEditor(editorTitle);


        // remove actual diagram
        gmfEditor.getEditPart("Start1").parent().select();

        bot.sleep(500);
        SWTBotTestUtil.pressDelete();
        gmfEditor.getEditPart("Step1").parent().select();

        bot.sleep(500);
        SWTBotTestUtil.pressDelete();
        return gmfEditor;
    }

    /** Open the General Tab, launch the editor shell of a Timer and select the type of timer
     * @param gmfEditor
     * @param timerName
     * @param typeToSelect
     * 
     */
    private void changeTimerProperties(SWTBotGefEditor gmfEditor, String timerName, String typeToSelect) {
        editTimerCondition(gmfEditor, timerName);

        SWTBotList table = bot.list();
        Assert.assertTrue("Error: "+typeToSelect+" element does not exist.", table.indexOf(typeToSelect)!=-1);

        table.select(typeToSelect);
    }


    /** Open the General Tab, launch the editor shell of a Timer
     * @param gmfEditor
     * @param timerName
     */
    private void editTimerCondition(SWTBotGefEditor gmfEditor, String timerName) {
        gmfEditor.getEditPart(timerName).parent().select();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "General");

        bot.button(Messages.editCondition).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.timerConditionWizardTitle));
    }
}
