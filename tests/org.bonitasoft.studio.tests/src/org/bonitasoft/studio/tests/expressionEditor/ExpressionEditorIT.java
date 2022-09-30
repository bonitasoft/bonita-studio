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
package org.bonitasoft.studio.tests.expressionEditor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.expression.assertions.ExpressionAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.ui.jface.SWTBotConstants;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.swtbot.framework.SWTBotConnectorTestUtil;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.diagram.general.data.AbstractBotDataWizardPage;
import org.bonitasoft.studio.swtbot.framework.expression.BotExpressionEditorDialog;
import org.bonitasoft.studio.swtbot.framework.expression.BotScriptExpressionEditor;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class ExpressionEditorIT implements SWTBotConstants {

    private final SWTGefBot bot = new SWTGefBot();

    public SWTGefBotRule rule = new SWTGefBotRule(bot);

    @Before
    public void cleanup() throws Exception {
        var currentRepository = RepositoryManager.getInstance().getCurrentRepository().orElseThrow();
        currentRepository.getRepositoryStore(DiagramRepositoryStore.class)
                .getChildren()
                .forEach(DiagramFileStore::delete);
    }

    private void createConnectorDefinition(String id) {
        SWTBotConnectorTestUtil.activateConnectorDefinitionShell(bot);
        fillDefinition(id);
        bot.button(IDialogConstants.NEXT_LABEL).click();
        addInputsToConnectorDef();
        bot.button(IDialogConstants.NEXT_LABEL).click();
        createConnectorConfigurationWizard("connectorExpressionWizardTest");
        bot.button(IDialogConstants.FINISH_LABEL).click();
    }

    private void fillDefinition(String id) {
        bot.textWithLabel("Definition id *").setText(id);
    }

    private void addInputsToConnectorDef() {
        bot.button("Add...").click();
        bot.button("Add...").click();
        bot.table().click(1, 2);
        bot.ccomboBox().setSelection(Boolean.class.getName());
        bot.button("Add...").click();
        bot.table().click(2, 2);
        bot.ccomboBox().setSelection(Double.class.getName());
        bot.button("Add...").click();
        bot.table().click(3, 2);
        bot.ccomboBox().setSelection(Float.class.getName());
        bot.button("Add...").click();
        bot.table().click(4, 2);
        bot.ccomboBox().setSelection(Integer.class.getName());
        bot.button("Add...").click();
        bot.table().click(5, 2);
        bot.ccomboBox().setSelection(List.class.getName());
    }

    private void createConnectorConfigurationWizard(String pageId) {
        bot.button("Add...").click();
        bot.textWithLabel("Page id *").setText(pageId);
        createWidget("Input1", "Text", 0);
        createWidget("Input2", "Text", 1);
        createWidget("Input3", "Text", 2);
        createWidget("Input4", "Text", 3);
        createWidget("Input5", "Text", 4);
        createWidget("Input6", "Text", 5);
        bot.button("Apply").click();
    }

    private void createWidget(String widgetId, String widgetType, int inputIndex) {
        bot.button("Add...").click();
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                bot.shell(org.bonitasoft.studio.connector.model.i18n.Messages.addWidget).activate();
                SWTBotShell activeShell = bot.activeShell();
                activeShell.setFocus();
                return activeShell.isActive();
            }

            @Override
            public String getFailureMessage() {
                return "Shell " + org.bonitasoft.studio.connector.model.i18n.Messages.addWidget + " did not activate";
            }
        });
        assertFalse("button ok should be disabled",
                bot.button(IDialogConstants.OK_LABEL).isEnabled());
        bot.textWithLabel("Widget id*").setText(widgetId);
        bot.comboBoxWithLabel("Widget type").setSelection(widgetType);
        if (!widgetType.equals("Group")) {
            bot.comboBoxWithLabel("Input *").setSelection(inputIndex);
        } else {
            assertFalse("inputs combo box should be disabled for Group widget",
                    bot.comboBoxWithLabel("Input *").isEnabled());
        }
        assertTrue("button ok should be enabled",
                bot.button(IDialogConstants.OK_LABEL).isEnabled());
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(org.eclipse.swtbot.swt.finder.waits.Conditions.shellIsActive("New connector definition"));
    }

    @Test
    public void testConnectorExpression() {
        final String id = "connectorExpressionTest";
        createConnectorDefinition(id);
        SWTBotConnectorTestUtil.activateConnectorTestWizard(bot);
        bot.text().setText(id);
        bot.table().select(id);
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.textWithId(SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 0).setText("Hello World");
        assertEquals("wrong value for input1", bot.textWithLabel("Input1").getText(), "Hello World");
        editGroovyEditor(1, "Input2", Boolean.class.getName(), "booleanScriptTest", "1==1;");
        editGroovyEditor(2, "Input3", Double.class.getName(), "doubleScriptTest", "(double)9.345+1.256;");
        editGroovyEditor(3, "Input4", Float.class.getName(), "floatScriptTest", "(float)9.345+1.256;");
        editGroovyEditor(4, "Input5", Integer.class.getName(), "integerScriptTest", "(int)9+10;");
        editGroovyEditor(5, "Input6", List.class.getName(), "listScriptTest", "def list=[1,2,3];");
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

    @Test
    public void testExpressionEditorConstantExpressionTyping() throws Exception {
        var workbenchWindow = new BotApplicationWorkbenchWindow(bot);

        var diagramEditor = workbenchWindow.createNewDiagram();
        diagramEditor.activeProcessDiagramEditor()
                .selectElement("Pool");
        var contractTab = diagramEditor
                .getDiagramPropertiesPart()
                .selectExecutionTab()
                .selectContractTab();

        contractTab.selectInputTab()
                .add()
                .setName("myContractInput");

        diagramEditor
                .getDiagramPropertiesPart()
                .selectDataTab()
                .selectPoolDataTab()
                .addData()
                .setName("myProcessData")
                .typeDefaultValueExpression("myContractInput")
                .finishAfter(300);

        EObject pool = diagramEditor.activeProcessDiagramEditor().getSelectedSemanticElement();
        assertThat(pool).isInstanceOf(Pool.class);

        var data = ModelHelper.getAllElementOfTypeIn(pool, Data.class);
        assertThat(data)
                .hasSize(1)
                .extracting(Data::getDefaultValue)
                .allSatisfy(defaultValue -> assertThat(defaultValue)
                        .hasType(ExpressionConstants.CONSTANT_TYPE)
                        .hasNoReferencedElements());
    }

    private void editGroovyEditor(int buttonIndex, String inputName, String inputtype, String scriptName,
            String groovyScript) {
        SWTBotShell activeShell = bot.activeShell();

        bot.toolbarButtonWithId(SWTBOT_ID_EDITBUTTON, buttonIndex).click();
        BotScriptExpressionEditor editorDialog = new BotExpressionEditorDialog(bot, activeShell)
                .selectScriptTab()
                .setName(scriptName)
                .setScriptContent(groovyScript);
        assertFalse("return type combobox should be disabled", bot.comboBoxWithLabel("Return type").isEnabled());
        assertEquals("return type should be" + inputtype, bot.comboBoxWithLabel("Return type").getText(), inputtype);
        editorDialog.ok();
        activeShell.setFocus();
        assertEquals("wrong value for " + inputName, bot.textWithLabel(inputName).getText(), scriptName);
    }
}
