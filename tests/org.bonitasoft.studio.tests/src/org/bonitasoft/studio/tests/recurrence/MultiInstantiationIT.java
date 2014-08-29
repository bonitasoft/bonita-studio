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
package org.bonitasoft.studio.tests.recurrence;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.model.expression.assertions.ExpressionAssert;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MultiInstanceType;
import org.bonitasoft.studio.model.process.MultiInstantiable;
import org.bonitasoft.studio.model.process.assertions.MultiInstantiableAssert;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.diagram.application.pageflow.BotAddFormWizardDialog;
import org.bonitasoft.studio.swtbot.framework.diagram.application.pageflow.BotPageflowPropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.general.actors.BotActorDefinitionPropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.general.data.BotAddDataDialog;
import org.bonitasoft.studio.swtbot.framework.diagram.general.form.data.BotDataPropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.general.form.general.BotGeneralPropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.general.recurrence.BotMultiInstanceTypeStackPanel;
import org.bonitasoft.studio.swtbot.framework.diagram.general.recurrence.BotNumberBasedStackPanel;
import org.bonitasoft.studio.swtbot.framework.diagram.general.recurrence.BotReccurencePropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.general.recurrence.BotStandardLoopTypeStackPanel;
import org.bonitasoft.studio.swtbot.framework.draw.BotGefProcessDiagramEditor;
import org.bonitasoft.studio.swtbot.framework.expression.BotConstantExpressionEditor;
import org.bonitasoft.studio.swtbot.framework.expression.BotExpressionEditorDialog;
import org.bonitasoft.studio.swtbot.framework.expression.BotScriptExpressionEditor;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * @author Romain Bioteau
 */
public class MultiInstantiationIT extends SWTBotGefTestCase implements SWTBotConstants {

    private static boolean disablePopup;

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
    @Before
    public void setUp() {
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE, false);
    }

    @Override
    @After
    public void tearDown() {
        bot.saveAllEditors();
    }

    @Test
    public void testStandardLoop() {
        final BotProcessDiagramPerspective botProcessDiagramPerspective = new BotApplicationWorkbenchWindow(bot).createNewDiagram();
        final MultiInstantiable multiInstantiable = (MultiInstantiable) botProcessDiagramPerspective.activeProcessDiagramEditor().selectElement("Step1")
                .getSelectedSemanticElement();
        final BotReccurencePropertySection iterationTabBot = botProcessDiagramPerspective
                .getDiagramPropertiesPart()
                .selectGeneralTab()
                .selectRecurrenceTab();

        final BotStandardLoopTypeStackPanel botStandardLoopType = iterationTabBot.selectStandardLoopType();
        botStandardLoopType.testAfter();
        BotExpressionEditorDialog botExpressionEditorDialog = botStandardLoopType.editLoopConditionExpression();
        final BotScriptExpressionEditor botScriptExpressionEditor = botExpressionEditorDialog.selectScriptTab();
        botScriptExpressionEditor.setName("condition");
        botScriptExpressionEditor.setScriptContent("instanceCount < 5");
        botExpressionEditorDialog.ok();

        botExpressionEditorDialog = botStandardLoopType.editMaximumLoopExpression();
        final BotConstantExpressionEditor botConstantExpressionEditor = botExpressionEditorDialog.selectConstantType();
        botConstantExpressionEditor.setValue("3");
        botExpressionEditorDialog.ok();

        MultiInstantiableAssert.assertThat(multiInstantiable).hasType(MultiInstanceType.STANDARD);
        MultiInstantiableAssert.assertThat(multiInstantiable).hasTestBefore(false);
        ExpressionAssert.assertThat(multiInstantiable.getLoopCondition()).hasName("condition").hasContent("instanceCount < 5")
        .hasType(ExpressionConstants.SCRIPT_TYPE)
        .hasReturnType(Boolean.class.getName());
        ExpressionAssert.assertThat(multiInstantiable.getLoopMaximum()).hasName("3").hasContent("3")
        .hasType(ExpressionConstants.CONSTANT_TYPE)
        .hasReturnType(Integer.class.getName());
    }

    @Test
    public void testParallelMultiInstantiation() {
        final BotProcessDiagramPerspective botProcessDiagramPerspective = new BotApplicationWorkbenchWindow(bot).createNewDiagram();
        final MultiInstantiable multiInstantiable = (MultiInstantiable) botProcessDiagramPerspective.activeProcessDiagramEditor().selectElement("Step1")
                .getSelectedSemanticElement();
        final BotReccurencePropertySection iterationTabBot = botProcessDiagramPerspective
                .getDiagramPropertiesPart()
                .selectGeneralTab()
                .selectRecurrenceTab();

        final BotMultiInstanceTypeStackPanel botParallelType = iterationTabBot.selectParallelType();
        final BotNumberBasedStackPanel botNumberBasedStackPanel = botParallelType.definedNumberOfInstances();
        botNumberBasedStackPanel.editNumberOfInstances().selectConstantType().setValue("8").ok();
        botNumberBasedStackPanel.editEarlyCompletionCondition().selectScriptTab().setName("completion").setScriptContent("true").ok();

        MultiInstantiableAssert.assertThat(multiInstantiable).hasType(MultiInstanceType.PARALLEL).isUseCardinality();
        ExpressionAssert.assertThat(multiInstantiable.getCardinalityExpression()).hasName("8").hasContent("8")
        .hasType(ExpressionConstants.CONSTANT_TYPE)
        .hasReturnType(Integer.class.getName());

        ExpressionAssert.assertThat(multiInstantiable.getCompletionCondition()).hasName("completion").hasContent("true")
        .hasType(ExpressionConstants.SCRIPT_TYPE)
        .hasReturnType(Boolean.class.getName());
    }

    @Test
    public void testMultiInstanceCardinality() throws ExecutionException {
        final BotProcessDiagramPerspective botProcessDiagramPerspective = new BotApplicationWorkbenchWindow(bot).createNewDiagram();
        final BotGefProcessDiagramEditor drawDiagram = botProcessDiagramPerspective.activeProcessDiagramEditor();
        final MultiInstantiable multiInstantiable = (MultiInstantiable) drawDiagram.selectElement("Step1").getSelectedSemanticElement();
        final AbstractProcess proc = ModelHelper.getParentProcess(multiInstantiable);
        drawDiagram.selectElement(proc.getName());

        BotAddDataDialog addDataBot = botProcessDiagramPerspective.getDiagramPropertiesPart().selectGeneralTab().selectDataTab()
                .addData();
        BotExpressionEditorDialog botExpressionEditorDialog = addDataBot.setName("nbTicketsAvailable").setType("Integer").editDefaultValueExpression();
        botExpressionEditorDialog.selectConstantType().setValue("20").ok();
        addDataBot.finish();

        final BotActorDefinitionPropertySection botActorDefinitionPropertySection = botProcessDiagramPerspective.getDiagramPropertiesPart().selectGeneralTab()
                .selectActorsDefinitionTab();

        botActorDefinitionPropertySection.selectActor("Employee actor").setSetAsInitiator();

        drawDiagram.selectElement("Step1");
        botProcessDiagramPerspective.getDiagramPropertiesPart().selectGeneralTab().selectActorAssignementTab().useBelowActor().selectActor("Employee actor");
        final BotNumberBasedStackPanel botNumberBasedStackPanel = botProcessDiagramPerspective.getDiagramPropertiesPart().selectGeneralTab()
                .selectRecurrenceTab().selectParallelType().definedNumberOfInstances();
        botNumberBasedStackPanel.editNumberOfInstances().selectConstantType().setValue("15").ok();
        botNumberBasedStackPanel.editEarlyCompletionCondition().selectScriptTab().setName("isThereTickets").setScriptContent("nbTicketsAvailable == 0").ok();

        addDataBot = botProcessDiagramPerspective.getDiagramPropertiesPart().selectGeneralTab().selectDataTab()
                .addData();
        botExpressionEditorDialog = addDataBot.setName("nbTickets").setType("Integer").editDefaultValueExpression();
        botExpressionEditorDialog.selectConstantType().setValue("0").ok();
        addDataBot.finish();

        final BotPageflowPropertySection botPageflowPropertySection = botProcessDiagramPerspective.getDiagramPropertiesPart().selectApplicationTab()
                .selectPageflowTab();
        final BotAddFormWizardDialog botDialog = botPageflowPropertySection.addForm();
        if (botDialog.canFlipToNextPage()) {
            botDialog.next();
        }
        botDialog.selectProcessDataTab().selectAll();
        botDialog.finish();

        botProcessDiagramPerspective.activeFormDiagramEditor().selectWidget("Nb Tickets Available");

        final BotGeneralPropertySection botGeneralPropertySection = botProcessDiagramPerspective.getFormPropertiesPart().selectGeneralTab().selectGeneralTab();
        botGeneralPropertySection.setFieldType("Message");

        final BotDataPropertySection botDataPropertySection = botProcessDiagramPerspective.getFormPropertiesPart().selectGeneralTab().selectDataTab();
        botDataPropertySection.editInitialValue().selectScriptTab().setName("nbTicketsAvailable")
        .setScriptContent("\"Only \"+nbTicketsAvailable+\" tickets available.\"").setReturnType(String.class.getName()).ok();

        botProcessDiagramPerspective.activeFormDiagramEditor().selectWidget("Nb Tickets");
        botProcessDiagramPerspective.getFormPropertiesPart().selectGeneralTab().selectGeneralTab().setFieldType("Text field")
        .setDisplayName("Nbr de Tickets à reserver");

        final BotDataPropertySection dataPropertySection = botProcessDiagramPerspective.getFormPropertiesPart().selectGeneralTab().selectDataTab();
        dataPropertySection.editInitialValue().selectConstantType().setValue("0").ok();
        dataPropertySection.selectOutputVariable("nbTickets", Integer.class.getName());
        dataPropertySection.editOutputOperationExpression().selectScriptTab().setName("nbTickets").setScriptContent("Integer.valueOf(field_nbTickets1)")
        .setReturnType(Integer.class.getName()).ok();

        // Save the form
        bot.saveAllEditors();
        final IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
        assertTrue(status.getMessage(), status.isOK());
    }

}
