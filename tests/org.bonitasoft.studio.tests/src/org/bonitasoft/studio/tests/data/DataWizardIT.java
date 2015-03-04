/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.data;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.common.DataTypeLabels;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.IntegerType;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPropertiesViewFolder;
import org.bonitasoft.studio.swtbot.framework.diagram.general.data.BotAddDataWizardPage;
import org.bonitasoft.studio.swtbot.framework.diagram.general.data.BotDataPropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.general.data.BotEditDataDialog;
import org.bonitasoft.studio.swtbot.framework.draw.BotGefProcessDiagramEditor;
import org.bonitasoft.studio.swtbot.framework.expression.BotExpressionEditorDialog;
import org.bonitasoft.studio.swtbot.framework.widget.BotTableWidget;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Baptiste Mesta
 *
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class DataWizardIT extends SWTBotGefTestCase {


    private boolean askRename;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        askRename = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getBoolean(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE);
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE, false);
    }

    @After
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE, askRename);
    }

    /**
     * @throws Exception
     */
    @Test
    public void testCreateData() throws Exception {
        SWTBotTestUtil.createNewDiagram(bot);
        final SWTBotEditor botEditor = bot.activeEditor();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

        final IGraphicalEditPart part = (IGraphicalEditPart)gmfEditor.mainEditPart().part();
        final MainProcess model = (MainProcess)part.resolveSemanticElement();
        final Pool pool = (Pool)model.getElements().get(0);

        gmfEditor.getEditPart(pool.getName()).parent().select();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        addDataOnSelectedElementWithName("newData");

        assertTrue("no data added",pool.getData().size()==1);
        assertTrue("wrong data added",pool.getData().get(0).getName().equals("newData"));

        final BotApplicationWorkbenchWindow applicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);

        applicationWorkbenchWindow.close();
    }

    @Test
    public void testEditData() throws Exception {
        SWTBotTestUtil.importProcessWIthPathFromClass(bot, "ProcessWithData_1_0.bos", "Bonita 6.x", "ProcessWithData", this.getClass(), false);

        final BotProcessDiagramPerspective botProcessDiagramPerspective = new BotProcessDiagramPerspective(bot);
        final BotProcessDiagramPropertiesViewFolder diagramPropertiesPart = botProcessDiagramPerspective.getDiagramPropertiesPart();
        final BotGefProcessDiagramEditor activeProcessDiagramEditor = botProcessDiagramPerspective.activeProcessDiagramEditor();
        final SWTBotGefEditor gmfEditor = activeProcessDiagramEditor.getGmfEditor();
        final IGraphicalEditPart part = (IGraphicalEditPart)gmfEditor.mainEditPart().part();
        final MainProcess model = (MainProcess)part.resolveSemanticElement();
        final Pool pool = (Pool)model.getElements().get(0);
        gmfEditor.select(pool.getName());
        final BotDataPropertySection dataTab = diagramPropertiesPart.selectGeneralTab().selectDataTab();
        dataTab.dataList().select(1);
        final BotEditDataDialog editDataDialog = dataTab.edit();
        editDataDialog.setName("anewName");
        editDataDialog.setType("Integer");
        editDataDialog.ok();
        final BotApplicationWorkbenchWindow applicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        applicationWorkbenchWindow.save();

        final Data firstData = pool.getData().get(0);
        assertEquals("wrong rename", firstData.getName(), "anewName");
        assertTrue("wrong change type",firstData.getDataType() instanceof IntegerType);

        applicationWorkbenchWindow.close();
    }

    @Test
    public void testRemoveData() throws Exception {

        SWTBotTestUtil.createNewDiagram(bot);

        final SWTBotEditor botEditor = bot.activeEditor();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

        final IGraphicalEditPart part = (IGraphicalEditPart)gmfEditor.mainEditPart().part();
        final MainProcess model = (MainProcess)part.resolveSemanticElement();
        final Pool pool = (Pool)model.getElements().get(0);

        gmfEditor.getEditPart(pool.getName()).parent().select();

        addDataOnSelectedElementWithName("newData");
        addDataOnSelectedElementWithName("dataToNotBeRemoved");

        final int nbData = pool.getData().size();
        final Data firstData = pool.getData().get(0);
        bot.table().select(firstData.getName()+" -- "+firstData.getDataType().getName());
        // button("Remove")
        bot.button(Messages.removeData).click();
        bot.button(IDialogConstants.OK_LABEL).click();

        SWTBotTestUtil.waitUntilBonitaBPmShellIsActive(bot);
        final SWTBotMenu menuDiagram = bot.menu("Diagram");
        final BotApplicationWorkbenchWindow applicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        applicationWorkbenchWindow.save();


        assertEquals("data not removed",nbData -1, pool.getData().size());
        assertFalse("the wrong data was removed",firstData.equals(pool.getData().get(0)));

        SWTBotTestUtil.waitUntilBonitaBPmShellIsActive(bot);
        applicationWorkbenchWindow.close();
    }

    @Test
    public void testMoveData() throws Exception {
        final Pool pool = createProcessWithData();
        final Lane lane = (Lane)pool.getElements().get(0);
        final SWTBotEditor botEditor = bot.activeEditor();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

        final SWTBotGefEditPart parent = gmfEditor.getEditPart("Step1").parent();
        final Activity step = (Activity) ((IGraphicalEditPart) parent.part()).resolveSemanticElement();
        final SWTBotGefEditPart poolPart = gmfEditor.getEditPart(pool.getName()).parent();
        poolPart.select();
        poolPart.click();
        addDataOnSelectedElementWithName("dataToMove");
        final int nbPoolData = pool.getData().size();
        final int nbStepData = step.getData().size();
        bot.table().select("dataToMove -- Text");
        // button("Move...")
        bot.button(Messages.moveData).click();
        bot.tree().getTreeItem("Pool "+pool.getName()).getNode("Lane "+lane.getName()).select("Task Step1");
        bot.button(IDialogConstants.FINISH_LABEL).click();
        SWTBotTestUtil.waitUntilBonitaBPmShellIsActive(bot);
        bot.menu("Diagram").menu("Save").click();
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return nbStepData +1 == step.getData().size();
            }

            @Override
            public String getFailureMessage() {
                return "data not removed";
            }
        });
        assertEquals("data not added",nbPoolData -1, pool.getData().size());
        SWTBotTestUtil.waitUntilBonitaBPmShellIsActive(bot);
        bot.menu("Diagram").menu("Close").click();
    }


    private Pool createProcessWithData() {
        SWTBotTestUtil.createNewDiagram(bot);
        final SWTBotEditor botEditor = bot.activeEditor();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());


        final IGraphicalEditPart part = (IGraphicalEditPart)gmfEditor.mainEditPart().part();
        final MainProcess model = (MainProcess)part.resolveSemanticElement();
        final Pool pool = (Pool)model.getElements().get(0);

        gmfEditor.getEditPart(pool.getName()).parent().select();
        final String dataName = "newData";
        addDataOnSelectedElementWithName(dataName);
        return pool;
    }

    /**Add a Text Data
     *
     * @param dataName
     */
    private void addDataOnSelectedElementWithName(final String dataName) {
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Data");

        bot.button("Add...").click();
        // Shell "New variable"
        bot.waitUntil(Conditions.shellIsActive(Messages.newVariable));

        bot.textWithLabel(Messages.name+" *").setText(dataName);
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.FINISH_LABEL)));
        bot.button(IDialogConstants.FINISH_LABEL).click();
        SWTBotTestUtil.waitUntilBonitaBPmShellIsActive(bot);
        final BotApplicationWorkbenchWindow applicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        applicationWorkbenchWindow.save();
        SWTBotTestUtil.waitUntilBonitaBPmShellIsActive(bot);
    }

    @Test
    public void testDataDefaultValueReturnType() throws Exception {
        SWTBotTestUtil.createNewDiagram(bot);
        final SWTBotEditor botEditor = bot.activeEditor();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

        final IGraphicalEditPart part = (IGraphicalEditPart)gmfEditor.mainEditPart().part();
        final MainProcess model = (MainProcess)part.resolveSemanticElement();
        final Pool pool = (Pool)model.getElements().get(0);

        gmfEditor.getEditPart(pool.getName()).parent().select();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();

        SWTBotTestUtil.selectTabbedPropertyView(bot, "Data");

        bot.button("Add...").click();
        // Shell "New variable"
        bot.waitUntil(Conditions.shellIsActive(Messages.newVariable));

        final String dataName = "myDataName";
        bot.textWithLabel(Messages.name +" *").setText(dataName);

        String defaultValue = "test return type";
        bot.textWithLabel(Messages.defaultValueLabel).setText(defaultValue);
        bot.sleep(500);

        bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON).click();
        assertEquals("Expression return type should be "+String.class.getName(),String.class.getName(),bot.comboBoxWithLabel(Messages.returnType).getText());
        bot.button(IDialogConstants.OK_LABEL).click();


        bot.comboBoxWithLabel(Messages.datatypeLabel).setSelection(DataTypeLabels.integerDataType);

        defaultValue = "50";
        bot.textWithLabel(Messages.defaultValueLabel).setText(defaultValue);
        bot.sleep(500);
        bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON).click();
        assertEquals("Expression return type should be "+Integer.class.getName(),Integer.class.getName(),bot.comboBoxWithLabel(Messages.returnType).getText());

        bot.button(IDialogConstants.OK_LABEL).click();

        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.FINISH_LABEL)));
        bot.button(IDialogConstants.FINISH_LABEL).click();
        SWTBotTestUtil.waitUntilBonitaBPmShellIsActive(bot);
        final BotApplicationWorkbenchWindow applicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        applicationWorkbenchWindow.save();

        SWTBotTestUtil.waitUntilBonitaBPmShellIsActive(bot);
        applicationWorkbenchWindow.close();
    }

    @Test
    public void testDatacantBeInitializeByItself(){
        final BotApplicationWorkbenchWindow botApplicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        final BotProcessDiagramPerspective diagramPerspective = botApplicationWorkbenchWindow.createNewDiagram();
        diagramPerspective.activeProcessDiagramEditor().selectDiagram();
        diagramPerspective.getDiagramPropertiesPart().selectGeneralTab().selectDiagramTab().setName("DataInit");

        final EObject selectedSemanticElement = diagramPerspective.activeProcessDiagramEditor().selectElement("Step1").getSelectedSemanticElement();
        final AbstractProcess proc = ModelHelper.getParentProcess(selectedSemanticElement);

        // add data to Process
        diagramPerspective.activeProcessDiagramEditor().selectElement(proc.getName());
        BotDataPropertySection botDataPropertySection = diagramPerspective.getDiagramPropertiesPart().selectGeneralTab().selectDataTab();
        BotAddDataWizardPage addData = botDataPropertySection.addData();
        addData.setName("procVar_1").setType("Text").finishAndAdd().setName("procVar_2").setType("Integer").finish();

        diagramPerspective.activeProcessDiagramEditor().addElementAfter("Step1", SWTBotTestUtil.CONTEXTUALPALETTE_STEP, PositionConstants.EAST);

        // set data on step1 Task
        diagramPerspective.activeProcessDiagramEditor().selectElement("Step1");
        botDataPropertySection = diagramPerspective.getDiagramPropertiesPart().selectGeneralTab().selectDataTab();
        addData = botDataPropertySection.addData();
        addData.setName("varS1_1").setType("Text").finishAndAdd().setName("varS1_2").setType("Integer").finish();

        // set data on step2 Task
        diagramPerspective.activeProcessDiagramEditor().selectElement("Step2");
        botDataPropertySection = diagramPerspective.getDiagramPropertiesPart().selectGeneralTab().selectDataTab();
        addData = botDataPropertySection.addData();
        addData.setName("varS2_1").setType("Text").finishAndAdd().setName("varS2_2").setType("Integer").finish();
        botApplicationWorkbenchWindow.save();

        // check only process variables are available in tasks data edit expression
        diagramPerspective.activeProcessDiagramEditor().selectElement("Step1");
        botDataPropertySection = diagramPerspective.getDiagramPropertiesPart().selectGeneralTab().selectDataTab();

        // Test on var varS1_1
        botDataPropertySection.dataList().select("varS1_1" + " -- " + "Text");
        BotEditDataDialog editDataWizardPage = botDataPropertySection.edit();
        BotExpressionEditorDialog editDefaultValueExpression = editDataWizardPage.editDefaultValueExpression();
        BotTableWidget variableList = editDefaultValueExpression.selectVariableTab().variableList();

        assertThat(variableList.containsItem("varS1_1" + " -- " + "Text")).overridingErrorMessage("Error: Task data can't be initialized by itself").isFalse();
        assertThat(variableList.containsItem("varS1_2" + " -- " + "Integer")).overridingErrorMessage(
                "Error: Task data can't be initialized by a sibling task data").isFalse();

        assertThat(variableList.containsItem("varS2_1" + " -- " + "Text")).overridingErrorMessage(
                "Error: Task data can't be initialized by task data").isFalse();
        assertThat(variableList.containsItem("varS2_2" + " -- " + "Integer")).overridingErrorMessage(
                "Error: Task data can't be initialized by task data").isFalse();

        assertThat(variableList.containsItem("procVar_1" + " -- " + "Text")).overridingErrorMessage(
                "Error:  Task data sould be initialized by Process data",variableList.getSWTBotWidget().rowCount()).isTrue();
        assertThat(variableList.containsItem("procVar_2" + " -- " + "Integer")).overridingErrorMessage(
                "Error:  Task data sould be initialized by Process data", variableList.getSWTBotWidget().rowCount()).isTrue();

        editDefaultValueExpression.cancel();
        editDataWizardPage.cancel();

        // Test on var varS1_2
        botDataPropertySection.dataList().select("varS1_2" + " -- " + "Integer");
        editDataWizardPage = botDataPropertySection.edit();
        editDefaultValueExpression = editDataWizardPage.editDefaultValueExpression();
        variableList = editDefaultValueExpression.selectVariableTab().variableList();

        assertThat(variableList.containsItem("varS1_2" + " -- " + "Integer")).overridingErrorMessage(
                "Error: Task data can't be initialized by itself").isFalse();
        assertThat(variableList.containsItem("varS1_1" + " -- " + "Text")).overridingErrorMessage(
                "Error: Task data can't be initialized by a sibling task data").isFalse();

        assertThat(variableList.containsItem("varS2_1" + " -- " + "Text")).overridingErrorMessage(
                "Error: Task data can't be initialized by task data").isFalse();
        assertThat(variableList.containsItem("procVar_1" + " -- " + "Text")).overridingErrorMessage(
                "Error:  Task data sould be initialized by Process data").isTrue();

        assertThat(variableList.containsItem("varS2_2" + " -- " + "Integer")).overridingErrorMessage(
                "Error: Task data can't be initialized by task data").isFalse();
        assertThat(variableList.containsItem("procVar_2" + " -- " + "Integer")).overridingErrorMessage(
                "Error:  Task data sould be initialized by Process data").isTrue();

        bot.button(IDialogConstants.CANCEL_LABEL).click();
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

    @Test
    public void testMaxLengthDescription() {
        final BotApplicationWorkbenchWindow botApplicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        final BotProcessDiagramPerspective diagramPerspective = botApplicationWorkbenchWindow.createNewDiagram();
        final BotProcessDiagramPropertiesViewFolder diagramPropertiesPart = diagramPerspective.getDiagramPropertiesPart();
        final BotDataPropertySection dataTab = diagramPropertiesPart.selectGeneralTab().selectDataTab();

        final BotAddDataWizardPage addDataDialog = dataTab.addData();

        addDataDialog.setName("MyName");
        assertThat(bot.button(IDialogConstants.FINISH_LABEL).isEnabled()).isFalse().overridingErrorMessage(
                "We shouldn't be able to create a data with an Uppercase as first charater");
        addDataDialog.setName("myName");
        String text270 = "";
        for (int i = 0; i < 270; i++) {
            text270 += "a";
        }
        addDataDialog.setDescription(text270);
        assertThat(bot.button(IDialogConstants.FINISH_LABEL).isEnabled()).isFalse().overridingErrorMessage(
                "We shouldn't be able to put more than 255 characters");
        String text255 = "";
        for (int i = 0; i < 254; i++) {
            text255 += "b";
        }
        addDataDialog.setDescription(text255);
        assertThat(bot.button(IDialogConstants.FINISH_LABEL).isEnabled()).isTrue().overridingErrorMessage(
                "We should be able to put at least 254 characters");
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

    @Test
    public void testCreateDataWithExistingId(){
        //Add the data myData on pool
        final String dataName = "myData";
        final String dataName1 = "myData1";
        final BotApplicationWorkbenchWindow botApplicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        final BotProcessDiagramPerspective diagramPerspective = botApplicationWorkbenchWindow.createNewDiagram();
        final BotProcessDiagramPropertiesViewFolder diagramPropertiesPart = diagramPerspective.getDiagramPropertiesPart();
        final BotDataPropertySection dataTab = diagramPropertiesPart.selectGeneralTab().selectDataTab();

        BotAddDataWizardPage addDataDialog = dataTab.addData();

        addDataDialog.setName(dataName);
        addDataDialog.finish();

        //try to add a data myData on step
        diagramPerspective.activeProcessDiagramEditor().selectElement("Step1");
        addDataDialog = dataTab.addData();
        addDataDialog.setName(dataName);
        assertThat(bot.button(IDialogConstants.FINISH_LABEL).isEnabled()).isFalse();
        addDataDialog.setName(dataName1);
        addDataDialog.finish();


        //add a second task and add a data named myData1
        diagramPerspective.activeProcessDiagramEditor().addElement("Step1", "Human", PositionConstants.EAST);
        addDataDialog = dataTab.addData();
        addDataDialog.setName(dataName1);
        addDataDialog.finish();
    }

    public static void getDataSection(final SWTGefBot bot ){
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).setFocus();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Data");
    }


}
