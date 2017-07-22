/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.connectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.swtbot.framework.SWTBotConnectorTestUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.keyboard.Keyboard;
import org.eclipse.swtbot.swt.finder.keyboard.KeyboardFactory;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class SWTBotConnectorDefinitionTest {

    private SWTGefBot bot = new SWTGefBot();

    @Test
    public void testCreate() throws Exception {
        final String id = "test";
        final String version = "1.0.0";
        SWTBotConnectorTestUtil.activateConnectorDefinitionShell(bot);
        bot.textWithLabel("Definition id *").setText(id);
        assertTrue(IDialogConstants.NEXT_LABEL + " should be enabled", bot
                .button(IDialogConstants.NEXT_LABEL).isEnabled());
        assertTrue(IDialogConstants.FINISH_LABEL + " should be enabled", bot
                .button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.button(IDialogConstants.FINISH_LABEL).click();
        ConnectorDefRepositoryStore store = (ConnectorDefRepositoryStore) RepositoryManager
                .getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
        ConnectorDefinition connectorDef = store.getDefinition(id, version);
        assertNotNull("the connectorDef file was not created", connectorDef);
    }

    @Test
    public void testIdSyntaxError() throws Exception {
        final String id2 = "id.?";
        final String textLabel = "Definition id *";
        SWTBotConnectorTestUtil.activateConnectorDefinitionShell(bot);
        testButtonDisabled("", textLabel);
        testButtonDisabled(id2, textLabel);
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

    @Test
    public void testVersionSyntaxError() throws Exception {
        final String id = "test0";
        final String version2 = "epd?";
        final String textLabel = "Version *";
        SWTBotConnectorTestUtil.activateConnectorDefinitionShell(bot);
        bot.textWithLabel("Definition id *").setText(id);
        testButtonDisabled("", textLabel);
        testButtonDisabled(version2, textLabel);
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

    @Test
    public void testExistingConnectorDefinition() throws Exception {
        final String id = "test1";
        final String textLabel = "Definition id *";
        final String textLabelVersion = "Version *";
        final String version = "1.1.0";
        SWTBotConnectorTestUtil.activateConnectorDefinitionShell(bot);
        bot.textWithLabel(textLabel).setText(id);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        SWTBotConnectorTestUtil.activateConnectorDefinitionShell(bot);
        testButtonDisabled(id, textLabel);
        bot.textWithLabel(textLabelVersion).setText(version);
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.FINISH_LABEL)), 5000);
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

    @Test
    public void testSelectCategory() throws Exception {
        final String id = "test2";
        final String version = "1.0.0";
        SWTBotConnectorTestUtil.activateConnectorDefinitionShell(bot);
        SWTBotConnectorTestUtil.createConnectorDefinition(bot, id, version);
        bot.treeWithLabel(Messages.categoryLabel).select(0);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        ConnectorDefRepositoryStore store = (ConnectorDefRepositoryStore) RepositoryManager
                .getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
        ConnectorDefinition connectorDef = store.getDefinition(id, version);
        assertEquals("category size should be equal to 1", 1, connectorDef.getCategory().size());
    }

    @Test
    public void testNewCategory() throws Exception {
        final String id = "test3";
        final String version = "1.0.0";
        final String categoryId = "category1";
        SWTBotConnectorTestUtil.activateConnectorDefinitionShell(bot);
        SWTBotConnectorTestUtil.createConnectorDefinition(bot, id, version);
        SWTBotConnectorTestUtil.createNewCategory(bot, categoryId);
        bot.treeWithLabel(Messages.categoryLabel).select(categoryId);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        ConnectorDefRepositoryStore store = (ConnectorDefRepositoryStore) RepositoryManager
                .getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
        ConnectorDefinition connectorDef = store.getDefinition(id, version);
        assertEquals("category list size should be equal to 1", connectorDef.getCategory().size(), 1);
    }

    @Test
    public void testCreateExistingCategory() throws Exception {
        final String id = "test7";
        final String version = "1.0.0";
        final String categoryId = "category3";
        SWTBotConnectorTestUtil.activateConnectorDefinitionShell(bot);
        SWTBotConnectorTestUtil.createConnectorDefinition(bot, id, version);
        SWTBotConnectorTestUtil.createNewCategory(bot, categoryId);
        SWTBotShell activeShell = bot.activeShell();
        bot.button("New...").click();
        bot.textWithLabel("Id").setText(categoryId);
        assertFalse("Ok button should be disabled when trying to create an existing category",
                bot.button(IDialogConstants.OK_LABEL).isEnabled());
        bot.button(IDialogConstants.CANCEL_LABEL).click();
        activeShell.setFocus();
        bot.button(IDialogConstants.FINISH_LABEL).click();
    }

    @Test
    public void testAddInputs() throws Exception {
        final String id = "test5";
        final String textLabel = "Definition id *";
        final String version = "1.0.0";
        final String inputName = "testInput";
        final String mandatory = "Mandatory";
        final String value = "hello";
        SWTBotConnectorTestUtil.activateConnectorDefinitionShell(bot);
        bot.textWithLabel(textLabel).setText(id);
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.button("Add...").click();
        bot.button("Add...").click();
        bot.button("Add...").click();
        bot.button("Remove").click();
        SWTBotTable table = bot.table();
        table.click(0, 0);
        bot.sleep(500);
        bot.text(0).setText(inputName);
        table.click(0, 1);
        bot.ccomboBox().setSelection(mandatory);
        SWTBotPreferences.KEYBOARD_LAYOUT = "EN_US";
        Keyboard key = KeyboardFactory.getSWTKeyboard();
        key.pressShortcut(Keystrokes.CR);
        bot.waitUntil(Conditions.widgetIsEnabled(table));
        table.click(0, 3);
        bot.text().setText("hello");
        bot.button(IDialogConstants.FINISH_LABEL).click();
        ConnectorDefRepositoryStore store = (ConnectorDefRepositoryStore) RepositoryManager
                .getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
        ConnectorDefinition connectorDef = store.getDefinition(id, version);
        assertEquals("wrong number of inputs", connectorDef.getInput().size(), 2);
        Input input = connectorDef.getInput().get(0);
        assertEquals("wrong input name", input.getName(), inputName);
        assertEquals("wrong input value", input.getDefaultValue(), value);
        assertTrue("input should be mandatory", input.isMandatory());
    }

    @Test
    public void testAddOutputs() throws Exception {
        final String id = "test6";
        final String textLabel = "Definition id *";
        final String version = "1.0.0";
        final String outputName = "output";
        final String type = "java.lang.Boolean";
        SWTBotConnectorTestUtil.activateConnectorDefinitionShell(bot);
        bot.textWithLabel(textLabel).setText(id);
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.button("Add...").click();
        bot.button("Add...").click();
        bot.button("Add...").click();
        bot.button("Remove").click();
        SWTBotTable table = bot.table();
        table.click(0, 0);
        bot.sleep(500);
        bot.text(0).setText(outputName);
        table.click(0, 1);
        bot.ccomboBox().setSelection(type);
        Keyboard key = KeyboardFactory.getSWTKeyboard();
        key.pressShortcut(Keystrokes.CR);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        ConnectorDefRepositoryStore store = (ConnectorDefRepositoryStore) RepositoryManager
                .getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
        ConnectorDefinition connectorDef = store.getDefinition(id, version);
        assertEquals("wrong number of outputs", connectorDef.getOutput().size(), 2);
        Output output = connectorDef.getOutput().get(0);
        assertEquals("wrong output name", output.getName(), outputName);
        assertEquals("wrong type of output", output.getType(), type);
    }

    private void testButtonDisabled(String id, String label) throws Exception {
        bot.textWithLabel(label).setText(id);
        assertFalse("finish button should be disabled", bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
    }
}
