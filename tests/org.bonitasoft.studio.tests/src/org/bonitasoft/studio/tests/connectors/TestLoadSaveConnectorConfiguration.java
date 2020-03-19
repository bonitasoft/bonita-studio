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
package org.bonitasoft.studio.tests.connectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connectors.repository.ConnectorConfRepositoryStore;
import org.bonitasoft.studio.swtbot.framework.SWTBotConnectorTestUtil;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Aurelie Zara
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class TestLoadSaveConnectorConfiguration {

    private SWTGefBot bot = new SWTGefBot();

    @Before
    public void initTest() {
        FileActionDialog.setDisablePopup(true);
    }

    public void createConnector(final String connectorDefinitionId) {
        final String widgetId = "textWidget";
        final String pageId = "connectorDefPageId";
        SWTBotConnectorTestUtil.activateConnectorDefinitionShell(bot);
        SWTBotShell activeShell = bot.activeShell();
        bot.textWithLabel("Definition id *").setText(connectorDefinitionId);
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.button("Add...").click();
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.button("Add...").click();
        bot.textWithLabel("Page id *").setText(pageId);
        bot.button("Add...").click();
        bot.textWithLabel("Widget id*").setText(widgetId);
        bot.textWithLabel("Display name").setText("text");
        bot.comboBoxWithLabel("Widget type").setSelection("Text");
        bot.comboBoxWithLabel("Input *").setSelection(0);
        bot.button(IDialogConstants.OK_LABEL).click();
        activeShell.setFocus();
        bot.button("Apply").click();
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.button("Add...").click();
        bot.button(IDialogConstants.FINISH_LABEL).click();
    }

    @Test
    public void testSaveLoadConnectorConfiguration() {
        final ConnectorConfRepositoryStore store = RepositoryManager.getInstance()
                .getRepositoryStore(ConnectorConfRepositoryStore.class);
        final int initialSize = store.getChildren().size();
        final String connectorDefId = "testLoadConnectorDefinition";
        final String version = "1.0.0";
        final String name = "testLoadConnector";
        final String name2 = "testLoadConnector2";
        final String dataName = "dataLoadConnector";
        final String saveName = "testLoadConnectorConfig";
        createConnector(connectorDefId);
        SWTBotTestUtil.createNewDiagram(bot);
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_DATA).show();
        createData(dataName);
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_EXECUTION).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_EXECUTION_CONNECTORS_OUT);
        bot.button("Add...").click();
        bot.text().setText(connectorDefId);
        bot.table().select(connectorDefId);
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.textWithLabel("Name *").setText(name);
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.textWithLabel("text").setText("hello world");
        bot.waitUntil(Conditions.waitForWidget(WidgetMatcherFactory.withMnemonic(IDialogConstants.NEXT_LABEL)), 1000);
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.sleep(1000); // Due to delayed observable on databinding
        SWTBotTestUtil.selectExpressionProposal(bot, dataName, String.class.getName(), 0);

        bot.button(IDialogConstants.FINISH_LABEL).click();
        bot.table().select(name + " -- " + connectorDefId + " (" + version + ") -- ON_FINISH");
        bot.button("Edit...").click();
        bot.button(IDialogConstants.NEXT_LABEL).click();
        SWTBotShell activeShell = bot.activeShell();
        bot.toolbarButton("Save").click();
        bot.textWithLabel("Name *").setText(saveName);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        activeShell.setFocus();
        bot.button(IDialogConstants.FINISH_LABEL).click();
        //add a new connector and load previous configuration connector
        SWTBotTestUtil.selectTabbedPropertyView(bot, SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_EXECUTION_CONNECTORS_OUT);
        bot.button("Add...").click();
        bot.text().setText(connectorDefId);
        bot.table().select(connectorDefId);
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.textWithLabel("Name *").setText(name2);
        bot.button(IDialogConstants.NEXT_LABEL).click();
        activeShell = bot.activeShell();
        bot.toolbarButton("Load").click();
        final SWTBotTree wizardTree = bot.treeWithId(SWTBotConstants.SELECTION_CONNECTOR_CONFIGURATION_TREE_ID);
        wizardTree.setFocus();
        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                wizardTree.getTreeItem(saveName).select();
                return wizardTree.selectionCount() > 0;
            }

            @Override
            public void init(final SWTBot bot) {

            }

            @Override
            public String getFailureMessage() {
                return "Cannot select tree item";
            }
        }, 10000, 1000);

        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.FINISH_LABEL)));
        bot.button(IDialogConstants.FINISH_LABEL).click();
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.OK_LABEL)));
        bot.button(IDialogConstants.OK_LABEL).click();

        activeShell.setFocus();
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.NEXT_LABEL)));
        bot.button(IDialogConstants.NEXT_LABEL).click();
        assertEquals("text field should be completed with hello world", bot.textWithLabel("text").getText(), "hello world");
        //remove configuration
        activeShell = bot.activeShell();
        bot.toolbarButton("Load").click();
        assertTrue("remove button should be enabled", bot.button("Remove").isEnabled());
        bot.tree().setFocus();
        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                bot.tree().getTreeItem(saveName).select();
                return bot.tree().selectionCount() > 0;
            }

            @Override
            public void init(final SWTBot bot) {

            }

            @Override
            public String getFailureMessage() {
                return "Cannot select tree item";
            }
        }, 10000, 500);
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button("Remove")));
        bot.button("Remove").click();
        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return bot.tree().getAllItems().length == initialSize;
            }

            @Override
            public void init(final SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                return "Fail to delete configuration";
            }
        });
        bot.button(IDialogConstants.CANCEL_LABEL).click();
        activeShell.setFocus();
        bot.button(IDialogConstants.CANCEL_LABEL).click();
        bot.saveAllEditors();
        assertEquals("invalid number of configuration", initialSize, store.getChildren().size());
    }

    private void createData(final String dataName) {
        SWTBotTestUtil.selectTabbedPropertyView(bot, SWTBotTestUtil.VIEWS_PROPERTIES_POOL_DATA_VARIABLES);
        bot.buttonWithId(SWTBotConstants.SWTBOT_ID_ADD_PROCESS_DATA).click();
        assertFalse(IDialogConstants.FINISH_LABEL + " should be disabled", bot
                .button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.textWithLabel("Name *").setText(dataName);
        assertTrue(IDialogConstants.FINISH_LABEL + " should be disabled", bot
                .button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.button(IDialogConstants.FINISH_LABEL).click();
    }

}
