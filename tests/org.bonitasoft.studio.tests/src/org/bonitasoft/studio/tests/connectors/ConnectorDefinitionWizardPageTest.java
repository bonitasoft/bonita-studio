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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.definition.Checkbox;
import org.bonitasoft.studio.connector.model.definition.Component;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Group;
import org.bonitasoft.studio.connector.model.definition.Password;
import org.bonitasoft.studio.connector.model.definition.RadioGroup;
import org.bonitasoft.studio.connector.model.definition.Select;
import org.bonitasoft.studio.connector.model.definition.Text;
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
public class ConnectorDefinitionWizardPageTest {

    private SWTGefBot bot = new SWTGefBot();

    private void openConnectorDefinitionWizardPage(String id) {
        final String packageLang = "java.lang.";
        final String packageUtil = "java.util.";
        SWTBotPreferences.KEYBOARD_LAYOUT = "EN_US";
        Keyboard key = KeyboardFactory.getSWTKeyboard();
        SWTBotConnectorTestUtil.activateConnectorDefinitionShell(bot);
        SWTBotConnectorTestUtil.createConnectorDefinition(bot, id, "1.0.0");
        bot.button(IDialogConstants.NEXT_LABEL).click();
        SWTBotTable table = bot.table();
        bot.button("Add...").click();
        bot.button("Add...").click();
        bot.button("Add...").click();
        bot.button("Add...").click();
        table.click(1, 2);
        bot.ccomboBox().setSelection(packageLang + "Boolean");
        key.pressShortcut(Keystrokes.CR);
        bot.button("Add...").click();
        table.click(2, 2);
        bot.ccomboBox().setSelection(packageLang + "Double");
        key.pressShortcut(Keystrokes.CR);
        bot.button("Add...").click();
        table.click(3, 2);
        bot.ccomboBox().setSelection(packageLang + "Float");
        key.pressShortcut(Keystrokes.CR);
        bot.button("Add...").click();
        table.click(4, 2);
        bot.ccomboBox().setSelection(packageLang + "Integer");
        key.pressShortcut(Keystrokes.CR);
        bot.button("Add...").click();
        table.click(5, 2);
        bot.ccomboBox().setSelection(packageUtil + "List");
        key.pressShortcut(Keystrokes.CR);
        bot.button("Add...").click();
        table.click(6, 2);
        bot.ccomboBox().setSelection(packageUtil + "Map");
        key.pressShortcut(Keystrokes.CR);
        bot.button(IDialogConstants.NEXT_LABEL).click();

    }

    @Test
    public void testConnectorEditionWizardPage() throws Exception {
        final String connectorDefId = "testWizardPage";
        final String pageId = "pageTest";
        final String widgetId0 = "widgetTest0";
        final String widgetId1 = "widgetTest1";
        final String widgetId2 = "widgetTest2";
        final String widgetId3 = "widgetTest3";
        final String widgetId4 = "widgetTest4";
        String widgetId5 = "widgetTest5";
        openConnectorDefinitionWizardPage(connectorDefId);

        bot.button("Add...").click();
        assertFalse("button Apply should be disabled", bot.button(Messages.apply)
                .isEnabled());
        bot.textWithLabel("Page id *").setText(pageId);
        assertTrue("button Apply should be disabled", bot.button(Messages.apply)
                .isEnabled());
        createWidget(widgetId0, "Text", 0);
        createWidget(widgetId1, "Password", 1);
        createWidget(widgetId2, "Checkbox", 3);
        createWidget(widgetId3, "Select", 4);
        createWidget(widgetId4, "Radio group", 5);
        createWidget(widgetId5, "Group", 7);
        bot.button(Messages.apply).click();
        bot.button(IDialogConstants.FINISH_LABEL).click();
        ConnectorDefRepositoryStore store = (ConnectorDefRepositoryStore) RepositoryManager
                .getInstance().getRepositoryStore(
                        ConnectorDefRepositoryStore.class);
        ConnectorDefinition connectorDef = store.getDefinition(connectorDefId,
                "1.0.0");
        Component widget0 = connectorDef.getPage().get(0).getWidget().get(0);
        assertNotNull("widget " + widgetId0 + " for connector definition "
                + connectorDefId + " and page " + pageId + " was not created",
                widget0);
        assertTrue("widget " + widgetId0 + " for connector definition "
                + connectorDefId + " and page " + pageId
                + " should be a text widget", widget0 instanceof Text);
        Component widget1 = connectorDef.getPage().get(0).getWidget().get(1);
        assertNotNull("widget " + widgetId1 + " for connector definition "
                + connectorDefId + " and page " + pageId + " was not created",
                widget1);
        assertTrue("widget " + widgetId1 + " for connector definition "
                + connectorDefId + " and page " + pageId
                + " should be a text widget", widget1 instanceof Password);
        Component widget2 = connectorDef.getPage().get(0).getWidget().get(2);
        assertNotNull("widget " + widgetId2 + " for connector definition "
                + connectorDefId + " and page " + pageId + " was not created",
                widget2);
        assertTrue("widget " + widgetId2 + " for connector definition "
                + connectorDefId + " and page " + pageId
                + " should be a text widget", widget2 instanceof Checkbox);
        Component widget3 = connectorDef.getPage().get(0).getWidget().get(3);
        assertNotNull("widget " + widgetId3 + " for connector definition "
                + connectorDefId + " and page " + pageId + " was not created",
                widget3);
        assertTrue("widget " + widgetId3 + " for connector definition "
                + connectorDefId + " and page " + pageId
                + " should be a text widget", widget3 instanceof Select);
        Component widget4 = connectorDef.getPage().get(0).getWidget().get(4);
        assertNotNull("widget " + widgetId4 + " for connector definition "
                + connectorDefId + " and page " + pageId + " was not created",
                widget4);
        assertTrue("widget " + widgetId4 + " for connector definition "
                + connectorDefId + " and page " + pageId
                + " should be a text widget", widget4 instanceof RadioGroup);
        Component widget5 = connectorDef.getPage().get(0).getWidget().get(5);
        assertNotNull("widget " + widgetId5 + " for connector definition "
                + connectorDefId + " and page " + pageId + " was not created",
                widget5);
        assertTrue("widget " + widgetId5 + " for connector definition "
                + connectorDefId + " and page " + pageId
                + " should be a text widget", widget5 instanceof Group);
    }

    private void createWidget(String widgetId, String widgetType, int inputIndex) {
        SWTBotShell activeShell = bot.activeShell();
        bot.button("Add...").click();
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
        activeShell.setFocus();

    }

    @Test
    public void testConnectorDefinitionWizardPageValidityName() {
        final String connectorDefId = "testWizardPage1";
        final String pageId = "pageTest";
        final String pageIdLabelStar = Messages.pageId + " *";
        final String widgetIdStar = Messages.widgetId + "*";
        final String widgetIdValid = "myWidgetId";

        openConnectorDefinitionWizardPage(connectorDefId);
        bot.button(Messages.Add).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.newConnectorDefinition));

        // ----- Test Page id name validity ----- 
        assertFalse("button Apply should be disabled", bot.button(Messages.apply).isEnabled());

        // valid id
        bot.textWithLabel(pageIdLabelStar).setText(pageId);
        assertTrue("button Apply should be enabled", bot.button(Messages.apply).isEnabled());

        // whitespace in id
        bot.textWithLabel(pageIdLabelStar).setText("bla bla");
        assertFalse("button Apply should be disabled", bot.button(Messages.apply).isEnabled());

        // forbidden characters
        bot.textWithLabel(pageIdLabelStar).setText("//dfgjkdfg**");
        assertFalse("button Apply should be disabled", bot.button(Messages.apply).isEnabled());

        // valid id
        bot.textWithLabel(pageIdLabelStar).setText(pageId);
        assertTrue("button Apply should be enabled", bot.button(Messages.apply).isEnabled());

        SWTBotShell activeShell = bot.activeShell();
        // ----- test widget id validity ------
        bot.button(Messages.Add).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.addWidget));
        bot.comboBoxWithLabel(Messages.input + " *").setSelection(0);

        // valid text
        bot.textWithLabel(widgetIdStar).setText(widgetIdValid);
        assertTrue("button OK should be enabled", bot.button(IDialogConstants.OK_LABEL).isEnabled());

        // forbidden characters
        bot.textWithLabel(widgetIdStar).setText("//lfhsduh**");
        assertFalse("button OK should be disabled", bot.button(IDialogConstants.OK_LABEL).isEnabled());

        // whitespace
        bot.textWithLabel(widgetIdStar).setText("bla bla");
        assertFalse("button OK should be disabled", bot.button(IDialogConstants.OK_LABEL).isEnabled());

        // valid text
        bot.textWithLabel(widgetIdStar).setText(widgetIdValid);
        assertTrue("button OK should be enabled", bot.button(IDialogConstants.OK_LABEL).isEnabled());

        bot.button(IDialogConstants.OK_LABEL).click();
        activeShell.setFocus();
        bot.button(Messages.apply).click();
        bot.button(IDialogConstants.CANCEL_LABEL).click();

    }

}
