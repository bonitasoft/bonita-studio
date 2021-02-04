/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.data;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.assertj.core.api.Assertions;
import org.bonitasoft.studio.common.DataTypeLabels;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.IntegerType;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPropertiesViewFolder;
import org.bonitasoft.studio.swtbot.framework.diagram.general.data.BotAddDataWizardPage;
import org.bonitasoft.studio.swtbot.framework.diagram.general.data.BotDataPropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.general.data.BotEditDataDialog;
import org.bonitasoft.studio.swtbot.framework.draw.BotGefProcessDiagramEditor;
import org.bonitasoft.studio.swtbot.framework.expression.BotExpressionEditorDialog;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.workspace.AbstractEMFOperation;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Baptiste Mesta
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class DataWizardIT {

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule botRule = new SWTGefBotRule(bot);

    @Test
    public void testCreateData() throws Exception {
        SWTBotTestUtil.createNewDiagram(bot);
        final SWTBotEditor botEditor = bot.activeEditor();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

        final IGraphicalEditPart part = (IGraphicalEditPart) gmfEditor.mainEditPart().part();
        final MainProcess model = (MainProcess) part.resolveSemanticElement();
        final Pool pool = (Pool) model.getElements().get(0);

        gmfEditor.getEditPart(pool.getName()).parent().select();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        addDataOnSelectedElementWithName("newData", SWTBotConstants.VIEWS_PROPERTIES_POOL_DATA_VARIABLES);

        assertTrue("no data added", pool.getData().size() == 1);
        assertTrue("wrong data added", pool.getData().get(0).getName().equals("newData"));
    }

    @Test
    public void testEditData() throws Exception {
        new BotApplicationWorkbenchWindow(bot).importBOSArchive()
                .setArchive(
                        DataWizardIT.class.getResource("ProcessWithData_1_0.bos"))
                .finish();

        final BotProcessDiagramPerspective botProcessDiagramPerspective = new BotProcessDiagramPerspective(bot);
        final BotProcessDiagramPropertiesViewFolder diagramPropertiesPart = botProcessDiagramPerspective
                .getDiagramPropertiesPart();
        final BotGefProcessDiagramEditor activeProcessDiagramEditor = botProcessDiagramPerspective
                .activeProcessDiagramEditor();
        final SWTBotGefEditor gmfEditor = activeProcessDiagramEditor.getGmfEditor();
        IGraphicalEditPart part = (IGraphicalEditPart) gmfEditor.mainEditPart().part();
        MainProcess model = (MainProcess) part.resolveSemanticElement();
        Pool pool = (Pool) model.getElements().get(0);
        gmfEditor.select(pool.getName());
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_DATA).show();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_DATA).setFocus();
        final BotDataPropertySection dataTab = diagramPropertiesPart.selectDataTab().selectPoolDataTab();
        dataTab.processDataList().select(1);
        final BotEditDataDialog editDataDialog = dataTab.edit();
        editDataDialog.setName("anewName");
        editDataDialog.setType("Integer");
        editDataDialog.ok();
        final BotApplicationWorkbenchWindow applicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        applicationWorkbenchWindow.save();

        Data editedData = pool.getData().get(0);
        assertEquals("wrong rename", editedData.getName(), "anewName");
        assertTrue(String.format("wrong change type. Expected Inetger but have %s", editedData.getDataType()),
                editedData.getDataType() instanceof IntegerType);
    }

    @Test
    public void testRemoveData() throws Exception {

        SWTBotTestUtil.createNewDiagram(bot);

        final SWTBotEditor botEditor = bot.activeEditor();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

        final IGraphicalEditPart part = (IGraphicalEditPart) gmfEditor.mainEditPart().part();
        final MainProcess model = (MainProcess) part.resolveSemanticElement();
        final Pool pool = (Pool) model.getElements().get(0);

        gmfEditor.getEditPart(pool.getName()).parent().select();

        addDataOnSelectedElementWithName("newData", SWTBotConstants.VIEWS_PROPERTIES_POOL_DATA_VARIABLES);
        addDataOnSelectedElementWithName("dataToNotBeRemoved", SWTBotConstants.VIEWS_PROPERTIES_POOL_DATA_VARIABLES);

        final int nbData = pool.getData().size();
        final Data firstData = pool.getData().get(0);
        bot.tableWithId(SWTBotConstants.SWTBOT_ID_PROCESS_DATA_LIST)
                .select(firstData.getName() + " -- " + firstData.getDataType().getName());
        // button("Remove")
        bot.buttonWithId(SWTBotConstants.SWTBOT_ID_REMOVE_PROCESS_DATA).click();
        bot.button(IDialogConstants.OK_LABEL).click();

        SWTBotTestUtil.waitUntilRootShellIsActive(bot);
        bot.menu("File");
        final BotApplicationWorkbenchWindow applicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        applicationWorkbenchWindow.save();

        assertEquals("data not removed", nbData - 1, pool.getData().size());
        assertFalse("the wrong data was removed", firstData.equals(pool.getData().get(0)));
    }

    @Test
    public void testMoveData() throws Exception {
        final Pool pool = createProcessWithData();
        final Lane lane = (Lane) pool.getElements().get(0);
        final SWTBotEditor botEditor = bot.activeEditor();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

        final SWTBotGefEditPart parent = gmfEditor.getEditPart("Step1").parent();
        final Activity step = (Activity) ((IGraphicalEditPart) parent.part()).resolveSemanticElement();
        final SWTBotGefEditPart poolPart = gmfEditor.getEditPart(pool.getName()).parent();
        poolPart.select();
        poolPart.click();
        addDataOnSelectedElementWithName("dataToMove", SWTBotConstants.VIEWS_PROPERTIES_POOL_DATA_VARIABLES);
        final int nbPoolData = pool.getData().size();
        final int nbStepData = step.getData().size();
        bot.tableWithId(SWTBotConstants.SWTBOT_ID_PROCESS_DATA_LIST).select("dataToMove -- Text");
        // button("Move...")
        bot.button(Messages.moveData).click();
        bot.tree().getTreeItem("Pool " + pool.getName()).getNode("Lane " + lane.getName()).select("Task Step1");
        bot.button(IDialogConstants.FINISH_LABEL).click();
        SWTBotTestUtil.waitUntilRootShellIsActive(bot);
        bot.menu("File").menu("Save").click();
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return nbStepData + 1 == step.getData().size();
            }

            @Override
            public String getFailureMessage() {
                return "data not removed";
            }
        });

        assertEquals("data not added", nbPoolData - 1, pool.getData().size());
    }

    private Pool createProcessWithData() {
        SWTBotTestUtil.createNewDiagram(bot);
        final SWTBotEditor botEditor = bot.activeEditor();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

        final IGraphicalEditPart part = (IGraphicalEditPart) gmfEditor.mainEditPart().part();
        final MainProcess model = (MainProcess) part.resolveSemanticElement();
        final Pool pool = (Pool) model.getElements().get(0);

        gmfEditor.getEditPart(pool.getName()).parent().select();
        final String dataName = "newData";
        addDataOnSelectedElementWithName(dataName, SWTBotConstants.VIEWS_PROPERTIES_POOL_DATA_VARIABLES);
        return pool;
    }

    /**
     * Add a Text Data
     *
     * @param dataName
     */
    private void addDataOnSelectedElementWithName(final String dataName, final String tabId) {
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_DATA).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, tabId);

        bot.buttonWithId(SWTBotConstants.SWTBOT_ID_ADD_PROCESS_DATA).click();
        // Shell "New variable"
        bot.waitUntil(Conditions.shellIsActive(Messages.newVariable));

        bot.textWithLabel(Messages.name + " *").setText(dataName);
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.FINISH_LABEL)));
        bot.button(IDialogConstants.FINISH_LABEL).click();
        SWTBotTestUtil.waitUntilRootShellIsActive(bot);
        final BotApplicationWorkbenchWindow applicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        applicationWorkbenchWindow.save();
        SWTBotTestUtil.waitUntilRootShellIsActive(bot);
    }

    @Test
    public void testDataDefaultValueReturnType() throws Exception {
        SWTBotTestUtil.createNewDiagram(bot);
        final SWTBotEditor botEditor = bot.activeEditor();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

        final IGraphicalEditPart part = (IGraphicalEditPart) gmfEditor.mainEditPart().part();
        final MainProcess model = (MainProcess) part.resolveSemanticElement();
        final Pool pool = (Pool) model.getElements().get(0);

        gmfEditor.getEditPart(pool.getName()).parent().select();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_DATA).show();

        SWTBotTestUtil.selectTabbedPropertyView(bot, SWTBotTestUtil.VIEWS_PROPERTIES_POOL_DATA_VARIABLES);

        bot.buttonWithId(SWTBotConstants.SWTBOT_ID_ADD_PROCESS_DATA).click();
        // Shell "New variable"
        bot.waitUntil(Conditions.shellIsActive(Messages.newVariable));

        final String dataName = "myDataName";
        bot.textWithLabel(Messages.name + " *").setText(dataName);

        String defaultValue = "test return type";
        bot.textWithLabel(Messages.defaultValueLabel).setText(defaultValue);
        bot.sleep(500);

        SWTBotShell activeShell = bot.activeShell();
        bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON).click();
        assertEquals("Expression return type should be " + String.class.getName(), String.class.getName(),
                bot.comboBoxWithLabel(Messages.returnType).getText());
        bot.button(IDialogConstants.CANCEL_LABEL).click();
        activeShell.setFocus();
        bot.comboBoxWithLabel(Messages.datatypeLabel).setSelection(DataTypeLabels.integerDataType);

        defaultValue = "50";
        bot.textWithLabel(Messages.defaultValueLabel).setText(defaultValue);
        bot.sleep(500);
        bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON).click();
        assertEquals("Expression return type should be " + Integer.class.getName(), Integer.class.getName(),
                bot.comboBoxWithLabel(Messages.returnType)
                        .getText());

        bot.button(IDialogConstants.CANCEL_LABEL).click();
        activeShell.setFocus();
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.FINISH_LABEL)));
        bot.button(IDialogConstants.FINISH_LABEL).click();
    }

    @Test
    public void testProcessDataCanBeInitializedWithProcessContractInput() throws ExecutionException {
        final BotApplicationWorkbenchWindow botApplicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        final BotProcessDiagramPerspective diagramPerspective = botApplicationWorkbenchWindow.createNewDiagram();
        final BotGefProcessDiagramEditor activeProcessDiagramEditor = diagramPerspective.activeProcessDiagramEditor();
        final Pool pool = (Pool) activeProcessDiagramEditor.getSelectedSemanticElement();
        Display.getDefault().syncExec(new Runnable() {

            @Override
            public void run() {
                try {
                    new AbstractEMFOperation(TransactionUtil.getEditingDomain(pool), "Prepare Pool with Contract Input") {

                        @Override
                        protected IStatus doExecute(final IProgressMonitor monitor, final IAdaptable info)
                                throws ExecutionException {
                            final Contract contract = ProcessFactory.eINSTANCE.createContract();
                            final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
                            contractInput.setName("input1");
                            contractInput.setType(ContractInputType.TEXT);
                            contract.getInputs().add(contractInput);
                            pool.setContract(contract);
                            return Status.OK_STATUS;
                        }
                    }.execute(null, null);
                } catch (final ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        final BotDataPropertySection botDataPropertySection = diagramPerspective.getDiagramPropertiesPart().selectDataTab()
                .selectPoolDataTab();
        final BotAddDataWizardPage addData = botDataPropertySection.addData();
        addData.setDefaultValueExpression("input1");
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

    @Test
    public void testTaskDataCantBeInitializedWithTaskContractInput() throws ExecutionException {
        final BotApplicationWorkbenchWindow botApplicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        final BotProcessDiagramPerspective diagramPerspective = botApplicationWorkbenchWindow.createNewDiagram();
        final BotGefProcessDiagramEditor activeProcessDiagramEditor = diagramPerspective.activeProcessDiagramEditor();
        final Task task = (Task) activeProcessDiagramEditor.selectElement("Step1").getSelectedSemanticElement();
        Display.getDefault().syncExec(new Runnable() {

            @Override
            public void run() {
                try {
                    new AbstractEMFOperation(TransactionUtil.getEditingDomain(task), "Prepare Task with Contract Input") {

                        @Override
                        protected IStatus doExecute(final IProgressMonitor monitor, final IAdaptable info)
                                throws ExecutionException {
                            final Contract contract = ProcessFactory.eINSTANCE.createContract();
                            final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
                            contractInput.setName("input1");
                            contractInput.setType(ContractInputType.TEXT);
                            contract.getInputs().add(contractInput);
                            task.setContract(contract);
                            return Status.OK_STATUS;
                        }
                    }.execute(null, null);
                } catch (final ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        final BotDataPropertySection botDataPropertySection = diagramPerspective.getDiagramPropertiesPart().selectDataTab()
                .selectLocalDataTab();
        final BotAddDataWizardPage addData = botDataPropertySection.addData();
        SWTBotShell activeShell = bot.activeShell();
        final BotExpressionEditorDialog editDefaultValueExpression = addData.editDefaultValueExpression();
        Assertions
                .assertThat(editDefaultValueExpression
                        .isTypeAvailable(org.bonitasoft.studio.contract.i18n.Messages.contractInputTypeLabel))
                .isFalse();
        editDefaultValueExpression.cancel();
        activeShell.setFocus();
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

    @Test
    public void testMaxLengthDescription() {
        final BotApplicationWorkbenchWindow botApplicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        final BotProcessDiagramPerspective diagramPerspective = botApplicationWorkbenchWindow.createNewDiagram();
        final BotProcessDiagramPropertiesViewFolder diagramPropertiesPart = diagramPerspective.getDiagramPropertiesPart();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_DATA).show();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_DATA).setFocus();
        final BotDataPropertySection dataTab = diagramPropertiesPart.selectDataTab().selectPoolDataTab();

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
    public void testCreateDataWithExistingId() {
        //Add the data myData on pool
        final String dataName = "myData_ToTestCreateDataWithExistingId";
        final String dataName1 = "myData_ToTestCreateDataWithExistingId1";
        final BotApplicationWorkbenchWindow botApplicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        final BotProcessDiagramPerspective diagramPerspective = botApplicationWorkbenchWindow.createNewDiagram();
        final BotProcessDiagramPropertiesViewFolder diagramPropertiesPart = diagramPerspective.getDiagramPropertiesPart();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_DATA).show();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_DATA).setFocus();
        final BotDataPropertySection dataTab = diagramPropertiesPart.selectDataTab().selectPoolDataTab();

        BotAddDataWizardPage addDataDialog = dataTab.addData();

        addDataDialog.setName(dataName);
        addDataDialog.finish();

        //try to add a data myData on step
        diagramPerspective.activeProcessDiagramEditor().selectElement("Step1");
        addDataDialog = diagramPropertiesPart.selectDataTab().selectLocalDataTab().addData();;
        addDataDialog.setName(dataName);
        assertThat(bot.button(IDialogConstants.FINISH_LABEL).isEnabled()).isFalse();
        addDataDialog.setName(dataName1);
        addDataDialog.finish();

        //add a second task and add a data named myData1
        diagramPerspective.activeProcessDiagramEditor().addElement("Step1", "Human", PositionConstants.EAST);
        addDataDialog = diagramPropertiesPart.selectDataTab().selectLocalDataTab().addData();
        addDataDialog.setName(dataName1);
        addDataDialog.finish();
    }

    public static void getDataSection(final SWTGefBot bot) {
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_DATA).show();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_DATA).setFocus();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Data");
    }

}
