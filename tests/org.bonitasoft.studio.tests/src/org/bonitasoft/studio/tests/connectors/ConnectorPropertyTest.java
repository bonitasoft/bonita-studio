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

import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.SWTBotConnectorTestUtil;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory;
import org.junit.Test;

/**
 * @author Aur�lie Zara
 */
public class ConnectorPropertyTest extends SWTBotGefTestCase {

    public void createConnector(String connectorDefinitionId) {
        final String widgetId = "textWidget";
        final String pageId = "connectorDefPageId";
        SWTBotConnectorTestUtil.activateConnectorDefinitionShell(bot);
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
        bot.button("Apply").click();
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.button("Add...").click();
        bot.button(IDialogConstants.FINISH_LABEL).click();
    }

    @Test
    public void testAddConnectorsToPool() {
        final String connectorDefinitionId = "connectorDefTest";
        final String version = "1.0.0";
        final String name = "test";
        final String dataName = "dataTest";
        SWTBotTestUtil.createNewDiagram(bot);
        createConnector(connectorDefinitionId);
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        createData(dataName);
        addConnector(connectorDefinitionId, name, dataName, version);
        editConnector(connectorDefinitionId, name, version, dataName);
        bot.activeEditor().saveAndClose();
    }

    public void testUpDownConnectorsList() {
        final String connectorDefinitionId = "connectorDefTestForUpDownButtonTest";
        final String version = "1.0.0";
        final String name = "test";
        final String dataName = "dataTest";
        SWTBotTestUtil.createNewDiagram(bot);
        createConnector(connectorDefinitionId);
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        createData(dataName);
        addConnector(connectorDefinitionId, name + "1", dataName, version);
        addConnector(connectorDefinitionId, name + "2", dataName, version);
        bot.table().select(0);
        bot.button(Messages.down).click();
        assertTrue("Down on connectors doesn't refresh the list correctly",
                bot.table().getTableItem(1).getText().contains(name + "1"));
        bot.button(Messages.up).click();
        assertTrue("up on connectors doesn't refresh the list correctly",
                bot.table().getTableItem(0).getText().contains(name + "1"));
        bot.activeEditor().saveAndClose();
    }

    private void addConnector(String connectorDefinitionId, String name,
            String dataName, String version) {
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Connectors");
        bot.button("Add...").click();
        assertFalse(IDialogConstants.NEXT_LABEL + " should be disabled", bot
                .button(IDialogConstants.NEXT_LABEL).isEnabled());
        assertFalse(IDialogConstants.FINISH_LABEL + " should be disabled", bot
                .button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.tree().expandNode("Uncategorized").select(connectorDefinitionId + " (" + version + ")");
        assertTrue(IDialogConstants.NEXT_LABEL + " should be disabled", bot
                .button(IDialogConstants.NEXT_LABEL).isEnabled());
        assertFalse(IDialogConstants.FINISH_LABEL + " should be disabled", bot
                .button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.button(IDialogConstants.NEXT_LABEL).click();
        assertFalse(IDialogConstants.FINISH_LABEL + " should be disabled", bot
                .button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.textWithLabel("Name *").setText(name);
        assertTrue(IDialogConstants.FINISH_LABEL + " should be disabled", bot
                .button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.textWithLabel("text").setText("hello world");
        bot.waitUntil(Conditions.waitForWidget(WidgetMatcherFactory.withMnemonic(IDialogConstants.NEXT_LABEL)), 1000);
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.comboBox().setSelection(dataName + " (java.lang.String)");
        bot.button(IDialogConstants.FINISH_LABEL).click();
    }

    private void editConnector(String connectorDefinitionId, String name,
            String version, String dataName) {
        bot.table().select(
                name + " -- " + connectorDefinitionId + " (" + version
                        + ") -- ON_FINISH");
        bot.button("Edit...").click();
        assertEquals("the name text field is not well completed", bot
                .textWithLabel("Name *").getText(), name);
        bot.button(IDialogConstants.NEXT_LABEL).click();
        assertEquals(
                "the widget text should be completed with the text hello world",
                "hello world", bot.textWithLabel("text").getText());
        bot.button(IDialogConstants.NEXT_LABEL).click();
        assertEquals(dataName
                + " (java.lang.String) should be selected in the comboBox",
                dataName + " (java.lang.String)", bot.comboBox().getText());
        assertEquals(
                "the text field should be completed with the ouptut name Ouput1",
                "Output1", bot.text().getText());
        bot.button(IDialogConstants.FINISH_LABEL).click();
    }

    private void createData(String dataName) {
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Data");
        bot.button("Add...").click();
        assertFalse(IDialogConstants.FINISH_LABEL + " should be disabled", bot
                .button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.textWithLabel("Name").setText(dataName);
        assertTrue(IDialogConstants.FINISH_LABEL + " should be disabled", bot
                .button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.button(IDialogConstants.FINISH_LABEL).click();
    }

}
