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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.swtbot.framework.SWTBotConnectorTestUtil;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class ConnectorDefinitionTranslationsTest {

    private SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule rule = new SWTGefBotRule(bot);

    private void openConnectorDefinitionWizardPage(String id, String categoryId) throws Exception {
        SWTBotPreferences.KEYBOARD_LAYOUT = "EN_US";
        SWTBotConnectorTestUtil.activateConnectorDefinitionShell(bot);
        SWTBotConnectorTestUtil.createConnectorDefinition(bot, id, "1.0.0");
        SWTBotConnectorTestUtil.createNewCategory(bot, categoryId);
        bot.button(IDialogConstants.NEXT_LABEL).click();
        SWTBotTable table = bot.table();
        bot.button("Add...").click();
        bot.button("Add...").click();
        bot.button("Add...").click();
        bot.button("Add...").click();
        table.click(1, 2);
        bot.ccomboBox().setSelection(Boolean.class.getName());
        bot.button("Add...").click();
        table.click(2, 2);
        bot.ccomboBox().setSelection(Double.class.getName());
        bot.button("Add...").click();
        table.click(3, 2);
        bot.ccomboBox().setSelection(Float.class.getName());
        bot.button("Add...").click();
        table.click(4, 2);
        bot.ccomboBox().setSelection(Integer.class.getName());
        bot.button("Add...").click();
        table.click(5, 2);
        bot.ccomboBox().setSelection(List.class.getName());
        bot.button("Add...").click();
        table.click(6, 2);
        bot.ccomboBox().setSelection(Map.class.getName());
        bot.button(IDialogConstants.NEXT_LABEL).click();
    }

    @Before
    public void connectorEditionWizardPage() throws Exception {
        final String connectorDefId = "testTranslationWizardPage";
        final String categoryId = "categoryEWP";
        final String pageId = "pageTest";
        final String widgetId0 = "widgetTest0";
        final String widgetId1 = "widgetTest1";
        final String widgetId2 = "widgetTest2";
        final String widgetId3 = "widgetTest3";
        final String widgetId4 = "widgetTest4";
        final String widgetId5 = "widgetTest5";
        openConnectorDefinitionWizardPage(connectorDefId, categoryId);
        bot.button("Add...").click();
        assertFalse("button Apply should be disabled", bot.button("Apply")
                .isEnabled());
        bot.textWithLabel("Page id *").setText(pageId);
        assertTrue("button Apply should be disabled", bot.button("Apply")
                .isEnabled());
        createWidget(widgetId0, "Text", 0);
        createWidget(widgetId1, "Password", 1);
        createWidget(widgetId2, "Checkbox", 3);
        createWidget(widgetId3, "Select", 4);
        createWidget(widgetId4, "Radio group", 5);
        createWidget(widgetId5, "Group", 7);
        bot.button("Apply").click();
    }

    @Test
    public void testConnectorDefinitionTranslation() throws Exception {
        final int nbEditorsBefore = bot.editors().size();
        final String language = "en";
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.button(IDialogConstants.NEXT_LABEL).click();
        int line = bot.table().indexOf(language, "Locale");
        bot.table().getTableItem(line).check();
        bot.button(IDialogConstants.FINISH_LABEL).click();
        ConnectorDefRepositoryStore store = (ConnectorDefRepositoryStore) RepositoryManager
                .getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
        DefinitionResourceProvider resourceProvider = DefinitionResourceProvider
                .getInstance(store, ConnectorPlugin.getDefault().getBundle());
        List<File> resources = resourceProvider
                .getExistingLocalesResource(store.getDefinition(
                        "testTranslationWizardPage", "1.0.0"));
        assertEquals("resources list should contain 2 resources",
                resources.size(), 2);
        for (File file : resources) {
            if (file.getName().contains("_en")) {
                Properties properties = new Properties();
                InputStream stream = new FileInputStream(file);
                properties.load(stream);
                assertTrue(
                        "properties file doesn't contain key widgetTest4.description",
                        properties.containsKey("widgetTest4.description"));
                assertTrue(
                        "properties file doesn't contain key widgetTest2.description",
                        properties.containsKey("widgetTest2.description"));
                assertTrue(
                        "properties file doesn't contain key widgetTest0.description",
                        properties.containsKey("widgetTest0.description"));
                assertTrue(
                        "properties file doesn't contain key widgetTest5.description",
                        properties.containsKey("widgetTest5.description"));
                assertTrue(
                        "properties file doesn't contain key widgetTest3.description",
                        properties.containsKey("widgetTest3.description"));
                assertTrue(
                        "properties file doesn't contain key widgetTest1.description",
                        properties.containsKey("widgetTest1.description"));
                assertTrue(
                        "properties file doesn't contain key widgetTest4.label",
                        properties.containsKey("widgetTest4.label"));
                assertTrue(
                        "properties file doesn't contain key widgetTest2.label",
                        properties.containsKey("widgetTest2.label"));
                assertTrue(
                        "properties file doesn't contain key widgetTest0.label",
                        properties.containsKey("widgetTest0.label"));
                assertTrue(
                        "properties file doesn't contain key widgetTest5.label",
                        properties.containsKey("widgetTest5.label"));
                assertTrue(
                        "properties file doesn't contain key widgetTest3.label",
                        properties.containsKey("widgetTest3.label"));
                assertTrue(
                        "properties file doesn't contain key widgetTest1.label",
                        properties.containsKey("widgetTest1.label"));
                assertTrue(
                        "properties file doesn't contain key category1.category",
                        properties.containsKey("categoryEWP.category"));
                assertTrue(
                        "properties file doesn't contain key pageTest.pageDescription",
                        properties.containsKey("pageTest.pageDescription"));
                assertTrue(
                        "properties file doesn't contain key pageTest.pageTitle",
                        properties.containsKey("pageTest.pageTitle"));
                stream.close();
            }

        }
        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return nbEditorsBefore + 1 == bot.editors().size();
            }

            @Override
            public void init(SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                return "Editor for new diagram has not been opened.";
            }
        }, 30000);
    }

    private void createWidget(String widgetId, String widgetType, int inputIndex)
            throws Exception {
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

}
