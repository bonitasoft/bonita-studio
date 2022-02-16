/**
 * Copyright (C) 2011-2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.tests.iteration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.model.expression.assertions.ExpressionAssert;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MultiInstanceType;
import org.bonitasoft.studio.model.process.MultiInstantiable;
import org.bonitasoft.studio.model.process.assertions.MultiInstantiableAssert;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.composite.BotOperationComposite;
import org.bonitasoft.studio.swtbot.framework.conditions.AssertionCondition;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.diagram.general.actors.BotActorDefinitionPropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.general.data.BotAddDataWizardPage;
import org.bonitasoft.studio.swtbot.framework.diagram.general.iteration.BotDataBasedStackPanel;
import org.bonitasoft.studio.swtbot.framework.diagram.general.iteration.BotMultiInstanceTypeStackPanel;
import org.bonitasoft.studio.swtbot.framework.diagram.general.iteration.BotNumberBasedStackPanel;
import org.bonitasoft.studio.swtbot.framework.diagram.general.iteration.BotReccurencePropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.general.iteration.BotStandardLoopTypeStackPanel;
import org.bonitasoft.studio.swtbot.framework.diagram.general.operations.BotOperationsPropertySection;
import org.bonitasoft.studio.swtbot.framework.draw.BotGefProcessDiagramEditor;
import org.bonitasoft.studio.swtbot.framework.expression.BotExpressionEditorDialog;
import org.bonitasoft.studio.swtbot.framework.expression.BotScriptExpressionEditor;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class MultiInstantiationIT implements SWTBotConstants {

    private SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule swtGefBotRule = new SWTGefBotRule(bot);

    @Test
    public void testStandardLoop() {
        final BotProcessDiagramPerspective botProcessDiagramPerspective = new BotApplicationWorkbenchWindow(bot)
                .createNewDiagram();
        final MultiInstantiable multiInstantiable = (MultiInstantiable) botProcessDiagramPerspective
                .activeProcessDiagramEditor().selectElement("Step1")
                .getSelectedSemanticElement();
        final BotReccurencePropertySection iterationTabBot = botProcessDiagramPerspective
                .getDiagramPropertiesPart()
                .selectGeneralTab()
                .selectIterationTab();

        final BotStandardLoopTypeStackPanel botStandardLoopType = iterationTabBot.selectStandardLoopType();
        botStandardLoopType.testAfter();
        BotExpressionEditorDialog botExpressionEditorDialog = botStandardLoopType.editLoopConditionExpression();
        final BotScriptExpressionEditor botScriptExpressionEditor = botExpressionEditorDialog.selectScriptTab();
        botScriptExpressionEditor.setName("condition");
        botScriptExpressionEditor.setScriptContent("instanceCount < 5");
        botExpressionEditorDialog.ok();

        botExpressionEditorDialog = botStandardLoopType.editMaximumLoopExpression();
        botExpressionEditorDialog.selectScriptTab()
           .setScriptContent("3")
           .ok();

        MultiInstantiableAssert.assertThat(multiInstantiable).hasType(MultiInstanceType.STANDARD);
        MultiInstantiableAssert.assertThat(multiInstantiable).hasTestBefore(false);
        ExpressionAssert.assertThat(multiInstantiable.getLoopCondition()).hasName("condition")
                .hasContent("instanceCount < 5")
                .hasType(ExpressionConstants.SCRIPT_TYPE)
                .hasReturnType(Boolean.class.getName());
        ExpressionAssert.assertThat(multiInstantiable.getLoopMaximum()).hasName("maximumLoop()").hasContent("3")
                .hasType(ExpressionConstants.SCRIPT_TYPE)
                .hasReturnType(Integer.class.getName());
    }

    @Test
    public void testMultiInstantiationUI() {
        final BotProcessDiagramPerspective botProcessDiagramPerspective = new BotApplicationWorkbenchWindow(bot)
                .createNewDiagram();
        final MultiInstantiable multiInstantiable = (MultiInstantiable) botProcessDiagramPerspective
                .activeProcessDiagramEditor().selectElement("Step1")
                .getSelectedSemanticElement();
        final BotReccurencePropertySection iterationTabBot = botProcessDiagramPerspective
                .getDiagramPropertiesPart()
                .selectGeneralTab()
                .selectIterationTab();

        iterationTabBot.selectSequentialType();
        MultiInstantiableAssert.assertThat(multiInstantiable).hasType(MultiInstanceType.SEQUENTIAL);

        bot.waitUntil(new AssertionCondition() {

            @Override
            protected void makeAssert() throws Exception {
                MultiInstantiableAssert.assertThat(multiInstantiable).hasType(MultiInstanceType.SEQUENTIAL);
            }
        });

        final BotMultiInstanceTypeStackPanel botParallelType = iterationTabBot.selectParallelType();
        bot.waitUntil(new AssertionCondition() {

            @Override
            protected void makeAssert() throws Exception {
                MultiInstantiableAssert.assertThat(multiInstantiable).hasType(MultiInstanceType.PARALLEL);
            }
        });

        final BotNumberBasedStackPanel botNumberBasedStackPanel = botParallelType.definedNumberOfInstances();
        bot.waitUntil(new AssertionCondition() {

            @Override
            protected void makeAssert() throws Exception {
                MultiInstantiableAssert.assertThat(multiInstantiable).isUseCardinality();
            }
        });

        botNumberBasedStackPanel.editNumberOfInstances().selectScriptTab()
            .setScriptContent("8")
            .ok();
        bot.waitUntil(new AssertionCondition() {

            @Override
            protected void makeAssert() throws Exception {
                ExpressionAssert.assertThat(multiInstantiable.getCardinalityExpression()).hasName("numberOfInstancesToCreate()").hasContent("8")
                        .hasType(ExpressionConstants.SCRIPT_TYPE)
                        .hasReturnType(Integer.class.getName());
            }
        });

        botNumberBasedStackPanel.editEarlyCompletionCondition().selectScriptTab().setName("completion")
                .setScriptContent("true").ok();
        bot.waitUntil(new AssertionCondition() {

            @Override
            protected void makeAssert() throws Exception {
                ExpressionAssert.assertThat(multiInstantiable.getCompletionCondition()).hasName("completion")
                        .hasContent("true")
                        .hasType(ExpressionConstants.SCRIPT_TYPE)
                        .hasReturnType(Boolean.class.getName());
            }
        });
    }

    @Test
    public void testMultiInstanceCardinality() throws Exception {
        final BotProcessDiagramPerspective botProcessDiagramPerspective = new BotApplicationWorkbenchWindow(bot)
                .createNewDiagram();
        final BotGefProcessDiagramEditor drawDiagram = botProcessDiagramPerspective.activeProcessDiagramEditor();
        final MultiInstantiable multiInstantiable = (MultiInstantiable) drawDiagram.selectElement("Step1")
                .getSelectedSemanticElement();
        final AbstractProcess proc = ModelHelper.getParentProcess(multiInstantiable);
        drawDiagram.selectElement(proc.getName());

        BotAddDataWizardPage addDataBot = botProcessDiagramPerspective.getDiagramPropertiesPart().selectDataTab()
                .selectPoolDataTab()
                .addData();
        BotExpressionEditorDialog botExpressionEditorDialog = addDataBot.setName("nbTicketsAvailable").setType("Integer")
                .editDefaultValueExpression();
        botExpressionEditorDialog.selectScriptTab().setScriptContent("20").ok();
        addDataBot.finish();

        final BotActorDefinitionPropertySection botActorDefinitionPropertySection = botProcessDiagramPerspective
                .getDiagramPropertiesPart().selectGeneralTab()
                .selectActorsDefinitionTab();

        botActorDefinitionPropertySection.selectActor("Employee actor").setSetAsInitiator();

        drawDiagram.selectElement("Step1");
        botProcessDiagramPerspective.getDiagramPropertiesPart().selectGeneralTab().selectActorAssignementTab()
                .useBelowActor().selectActor("Employee actor");
        final BotNumberBasedStackPanel botNumberBasedStackPanel = botProcessDiagramPerspective.getDiagramPropertiesPart()
                .selectGeneralTab()
                .selectIterationTab().selectParallelType().definedNumberOfInstances();
        botNumberBasedStackPanel.editNumberOfInstances().selectScriptTab().setScriptContent("15").ok();
        botNumberBasedStackPanel.editEarlyCompletionCondition().selectScriptTab().setName("isThereTickets")
                .setScriptContent("nbTicketsAvailable == 0").ok();

        addDataBot = botProcessDiagramPerspective.getDiagramPropertiesPart().selectDataTab().selectLocalDataTab()
                .addData();
        botExpressionEditorDialog = addDataBot.setName("nbTickets").setType("Integer").editDefaultValueExpression();
        botExpressionEditorDialog.selectScriptTab().setScriptContent("0").ok();
        addDataBot.finish();
        drawDiagram.selectElement("Step1");

        bot.saveAllEditors();
        final IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
        assertTrue(status.getMessage(), status.isOK());
    }

    @Test
    public void testMultiInstanceEraseButton() {
        // create Diagram
        final BotApplicationWorkbenchWindow botApplicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        final BotProcessDiagramPerspective botProcessDiagramPerspective = botApplicationWorkbenchWindow.createNewDiagram();
        final BotGefProcessDiagramEditor drawDiagram = botProcessDiagramPerspective.activeProcessDiagramEditor();
        final MultiInstantiable multiInstantiable = (MultiInstantiable) drawDiagram.selectElement("Step1")
                .getSelectedSemanticElement();
        final AbstractProcess proc = ModelHelper.getParentProcess(multiInstantiable);
        drawDiagram.selectElement(proc.getName());

        BotAddDataWizardPage addDataBot = botProcessDiagramPerspective.getDiagramPropertiesPart().selectDataTab()
                .selectPoolDataTab()
                .addData();
        final BotExpressionEditorDialog botExpressionEditorDialog = addDataBot.setName("vip").setType("Java Object")
                .setClassname("java.util.List")
                .editDefaultValueExpression();
        botExpressionEditorDialog.selectScriptTab().setName("vipScript")
                .setScriptContent("[\"Armelle\",\"Ben\",\"Cedric\",\"Damien\"]")
                .setReturnType("java.util.List").ok();
        addDataBot.finish();

        botApplicationWorkbenchWindow.save();

        // Add MultiInstance on The human Task
        drawDiagram.selectElement("Step1");
        addDataBot = botProcessDiagramPerspective.getDiagramPropertiesPart().selectDataTab().selectLocalDataTab()
                .addData();
        addDataBot.setName("vipName").setType("Text").finish();

        botApplicationWorkbenchWindow.save();

        final BotReccurencePropertySection iterationTab = botProcessDiagramPerspective.getDiagramPropertiesPart()
                .selectGeneralTab()
                .selectIterationTab();

        // select the multi-instantiate radio buton
        final BotDataBasedStackPanel botDataBasedStackPanel = iterationTab.selectParallelType().listOfData();
        // set collection
        botDataBasedStackPanel.selectInputListVariable("vip -- java.util.List");
        // set Input Data
        botDataBasedStackPanel.setIteratorName("vipName").setIteratorReturnType(String.class.getName());
        // set ouput data
        botDataBasedStackPanel.setStoreOutputResult(true).selectResultDataForEachInstance("vipName -- Text");
        // set output results
        botDataBasedStackPanel.setStoreOutputResult(true).selectListOfAppenedResults("vip -- java.util.List");

        // erase collection
        Assert.assertTrue(bot.toolbarButtonWithTooltip(Messages.clearSelection, 0).isEnabled());
        bot.toolbarButtonWithTooltip(Messages.clearSelection, 0).click();
        Assert.assertTrue("Error: Collection is not erased !", bot.comboBox(0).getText().isEmpty());

        // erase ouput Data
        Assert.assertTrue(bot.toolbarButtonWithTooltip(Messages.clearSelection, 1).isEnabled());
        bot.toolbarButtonWithTooltip(Messages.clearSelection, 1).click();
        Assert.assertTrue("Error: Output Data is not erased !", bot.comboBox(2).getText().isEmpty());

        // erase output Collection
        Assert.assertTrue(bot.toolbarButtonWithTooltip(Messages.clearSelection, 2).isEnabled());
        bot.toolbarButtonWithTooltip(Messages.clearSelection, 2).click();
        Assert.assertTrue("Error: Output Collection is not erased !", bot.comboBox(3).getText().isEmpty());

        botApplicationWorkbenchWindow.save();
        SWTBotTestUtil.waitUntilRootShellIsActive(bot);
    }

    @Test
    public void testMultiInstanceUpdateComboBoxAfterDataRemoved() {
        // create Diagram
        final BotApplicationWorkbenchWindow botApplicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        final BotProcessDiagramPerspective botProcessDiagramPerspective = botApplicationWorkbenchWindow.createNewDiagram();
        final BotGefProcessDiagramEditor drawDiagram = botProcessDiagramPerspective.activeProcessDiagramEditor();
        final MultiInstantiable multiInstantiable = (MultiInstantiable) drawDiagram.selectElement("Step1")
                .getSelectedSemanticElement();
        final AbstractProcess proc = ModelHelper.getParentProcess(multiInstantiable);
        drawDiagram.selectElement(proc.getName());

        BotAddDataWizardPage addDataBot = botProcessDiagramPerspective.getDiagramPropertiesPart().selectDataTab()
                .selectPoolDataTab()
                .addData();
        BotExpressionEditorDialog botExpressionEditorDialog = addDataBot.setName("vip").setType("Java Object")
                .setClassname("java.util.List")
                .editDefaultValueExpression();
        botExpressionEditorDialog.selectScriptTab().setName("vipScript")
                .setScriptContent("[\"Armelle\",\"Ben\",\"Cedric\",\"Damien\"]")
                .setReturnType("java.util.List").ok();
        addDataBot = (BotAddDataWizardPage) addDataBot.finishAndAdd();
        botExpressionEditorDialog = addDataBot.setName("vip2").setType("Java Object").setClassname("java.util.List")
                .editDefaultValueExpression();
        botExpressionEditorDialog.selectScriptTab().setName("vipScript").setScriptContent("[\"A\",\"B\",\"C\",\"D\"]").ok();
        addDataBot.finish();

        botApplicationWorkbenchWindow.save();

        // Add MultiInstance on The human Task
        drawDiagram.selectElement("Step1");
        addDataBot = botProcessDiagramPerspective.getDiagramPropertiesPart().selectDataTab().selectLocalDataTab()
                .addData();
        addDataBot = (BotAddDataWizardPage) addDataBot.setName("vipName").setType("Text").finishAndAdd();
        addDataBot.setName("nbTickets").setType("Integer").editDefaultValueExpression().selectScriptTab().setScriptContent("0")
                .ok();
        addDataBot.finish();

        botApplicationWorkbenchWindow.save();
        
        drawDiagram.selectElement("Step1");

        // Set properties of Multi-Instance
        final BotReccurencePropertySection iterationTab = botProcessDiagramPerspective.getDiagramPropertiesPart()
                .selectGeneralTab()
                .selectIterationTab();

        // select the multi-instantiate radio buton
        final BotDataBasedStackPanel botDataBasedStackPanel = iterationTab.selectParallelType().listOfData();
        // set collection
        botDataBasedStackPanel.selectInputListVariable("vip -- java.util.List");
        // set Input Data
        botDataBasedStackPanel.setIteratorName("vipName").setIteratorReturnType(String.class.getName());
        // set ouput data
        botDataBasedStackPanel.setStoreOutputResult(true).selectResultDataForEachInstance("vipName -- Text");
        // set output results
        botDataBasedStackPanel.selectListOfAppenedResults("vip -- java.util.List");

        bot.waitUntil(new AssertionCondition() {

            @Override
            protected void makeAssert() throws Exception {
                // Check references of input collection, input data, outputdata and output result exist
                assertNotNull("Error: Input Collection used in the MultiInstantiation is not referenced in the Model.",
                        multiInstantiable.getCollectionDataToMultiInstantiate());
                assertNotNull("Error: Input Data used in the MultiInstantiation is not referenced in the Model.",
                        multiInstantiable.getIteratorExpression());
                assertEquals("vipName", multiInstantiable.getIteratorExpression().getName());
                
                assertNotNull("Error: Output Data used in the MultiInstantiation is not referenced in the Model.",
                        multiInstantiable.getOutputData());
                assertNotNull("Error: Output Result used in the MultiInstantiation is not referenced in the Model.",
                        multiInstantiable.getListDataContainingOutputResults());
            }
        }, 10000, 100);
      

        // remove vip collection and vipName Text
        drawDiagram.selectElement(proc.getName());
        org.bonitasoft.studio.swtbot.framework.diagram.general.data.BotDataPropertySection selectDataTab = botProcessDiagramPerspective
                .getDiagramPropertiesPart().selectDataTab().selectPoolDataTab();
        botProcessDiagramPerspective.getDiagramPropertiesPart().selectDataTab().selectPoolDataTab().processDataList()
                .select("vip -- java.util.List -- Default value: vipScript");
        selectDataTab.remove();

        // Check empty comboBox in MultiInstance
        drawDiagram.selectElement("Step1");
        selectDataTab = botProcessDiagramPerspective
                .getDiagramPropertiesPart().selectDataTab().selectLocalDataTab();
        botProcessDiagramPerspective.getDiagramPropertiesPart().selectDataTab().selectLocalDataTab().processDataList()
                .select("vipName -- Text");
        selectDataTab.remove();
        SWTBotTestUtil.waitUntilRootShellIsActive(bot);

        bot.waitUntil(new AssertionCondition() {

            @Override
            protected void makeAssert() throws Exception {
                assertNull(
                        "Error: Input Collection is still referenced in the MultiInstantiation Model after being removed.",
                        multiInstantiable.getCollectionDataToMultiInstantiate());
                assertTrue(multiInstantiable.getIteratorExpression().getName().equals("vipName"));
                assertNull("Error: Output Data is still referenced in the MultiInstantiation Model after being removed.",
                        multiInstantiable.getOutputData());
                assertNull("Error: Output Result is still referenced in the MultiInstantiation Model after being removed.",
                        multiInstantiable.getListDataContainingOutputResults());
            }
        });

        botProcessDiagramPerspective
                .getDiagramPropertiesPart().selectGeneralTab().selectIterationTab();
        // check Collection empty
        Assert.assertTrue("Error: Collection is not empty !", bot.comboBox(0).getText().isEmpty());

        // check ouput Data empty
        Assert.assertTrue("Error: Output Data is not empty !", bot.comboBox(2).getText().isEmpty());

        // check output Collection empty
        Assert.assertTrue("Error: Output Collection is not empty !", bot.comboBox(3).getText().isEmpty());

        botApplicationWorkbenchWindow.save();
        SWTBotTestUtil.waitUntilRootShellIsActive(bot);
    }

    @Test
    public void testMultiInstanceCollection() {
        final BotApplicationWorkbenchWindow botApplicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        final BotProcessDiagramPerspective botProcessDiagramPerspective = botApplicationWorkbenchWindow.createNewDiagram();
        final BotGefProcessDiagramEditor drawDiagram = botProcessDiagramPerspective.activeProcessDiagramEditor();
        final MultiInstantiable multiInstantiable = (MultiInstantiable) drawDiagram.selectElement("Step1")
                .getSelectedSemanticElement();
        final AbstractProcess proc = ModelHelper.getParentProcess(multiInstantiable);
        drawDiagram.selectElement(proc.getName());

        BotAddDataWizardPage addDataBot = botProcessDiagramPerspective.getDiagramPropertiesPart().selectDataTab()
                .selectPoolDataTab()
                .addData();
        BotExpressionEditorDialog botExpressionEditorDialog = addDataBot.setName("nbTicketsAvailable").setType("Integer")
                .editDefaultValueExpression();
        botExpressionEditorDialog.selectScriptTab().setScriptContent("20").ok();
        addDataBot = (BotAddDataWizardPage) addDataBot.finishAndAdd();
        botExpressionEditorDialog = addDataBot.setName("vip").setType("Java Object").setClassname("java.util.List")
                .editDefaultValueExpression();
        botExpressionEditorDialog.selectScriptTab().setName("vipScript")
                .setScriptContent("[\"Armelle\",\"Ben\",\"Cedric\",\"Damien\"]")
                .setReturnType("java.util.List").ok();
        addDataBot = (BotAddDataWizardPage) addDataBot.finishAndAdd();
        botExpressionEditorDialog = addDataBot.setName("alreadyVip").setType("Java Object").setClassname("java.util.List")
                .editDefaultValueExpression();
        botExpressionEditorDialog.selectScriptTab().setName("vipScript").setScriptContent("[null]")
                .setReturnType("java.util.List").ok();
        addDataBot.finish();

        final BotActorDefinitionPropertySection botActorDefinitionPropertySection = botProcessDiagramPerspective
                .getDiagramPropertiesPart().selectGeneralTab()
                .selectActorsDefinitionTab();
        botActorDefinitionPropertySection.selectActor("Employee actor").setSetAsInitiator();

        drawDiagram.selectElement("Step1");
        botProcessDiagramPerspective.getDiagramPropertiesPart().selectGeneralTab().selectActorAssignementTab()
                .useBelowActor().selectActor("Employee actor");

        addDataBot = botProcessDiagramPerspective.getDiagramPropertiesPart().selectDataTab().selectLocalDataTab()
                .addData();
        botExpressionEditorDialog = addDataBot.setName("nbTickets").setType("Integer").editDefaultValueExpression();
        botExpressionEditorDialog.selectScriptTab().setScriptContent("0").ok();
        addDataBot = (BotAddDataWizardPage) addDataBot.finishAndAdd();

        addDataBot.setName("vipName").setType("Text").finish();

        botApplicationWorkbenchWindow.save();

        // Set properties of Multi-Instance
        // Set properties of Multi-Instance
        final BotReccurencePropertySection iterationTab = botProcessDiagramPerspective.getDiagramPropertiesPart()
                .selectGeneralTab()
                .selectIterationTab();

        // select the multi-instantiate radio buton
        final BotDataBasedStackPanel botDataBasedStackPanel = iterationTab.selectParallelType().listOfData();
        // set collection
        botDataBasedStackPanel.selectInputListVariable("vip -- java.util.List");
        // set Input Data
        botDataBasedStackPanel.setIteratorName("vipName").setIteratorReturnType(String.class.getName());
        // set ouput data
        botDataBasedStackPanel.setStoreOutputResult(true).selectResultDataForEachInstance("vipName -- Text");
        // set output results
        botDataBasedStackPanel.selectListOfAppenedResults("alreadyVip -- java.util.List");

        // Edit the Completion condition
        botDataBasedStackPanel.editEarlyCompletionCondition().selectScriptTab().setName("isOK")
                .setScriptContent("(vip.isEmpty())||(nbTicketsAvailable==0)")
                .ok();

        // Add operation to update the number of available tickets
        final BotOperationsPropertySection operationTab = botProcessDiagramPerspective.getDiagramPropertiesPart()
                .selectExecutionTab().selectOperationTab();
        operationTab.addOperation();
        final BotOperationComposite botOperationComposite = operationTab.getOperation(0);
        botOperationComposite.selectLeftOperand("vip", "java.util.List");
        botOperationComposite.editRightOperand().selectScriptTab().setName("removeUser")
                .setScriptContent("List vipList = new ArrayList(vip)\nvipList.remove(vipName)\nreturn vipList")
                .setReturnType(List.class.getName())
                .ok();

        botApplicationWorkbenchWindow.save();
        SWTBotTestUtil.waitUntilRootShellIsActive(bot);
    }
}
