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
package org.bonitasoft.studio.tests.actors;

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
import org.bonitasoft.studio.identity.actors.repository.ActorFilterDefRepositoryStore;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.keyboard.Keyboard;
import org.eclipse.swtbot.swt.finder.keyboard.KeyboardFactory;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class ActorFilterDefinitionWizardPageTest {

    private SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule rule = new SWTGefBotRule(bot);

    private void openActorFilterDefinitionWizardPage(String id) {
        final String packageLang = "java.lang.";
        final String packageUtil = "java.util.";
        SWTBotPreferences.KEYBOARD_LAYOUT = "EN_US";
        Keyboard key = KeyboardFactory.getSWTKeyboard();
        SWTBotActorFilterUtil.activateActorFilterDefinitionShell(bot);
        SWTBotActorFilterUtil.createActorFilterDefinition(bot, id, "1.0.0");
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.NEXT_LABEL)), 5000);
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
    public void testActorFilterEditionWizardPage() throws Exception {
        final String actorFilterId = "testWizardPage";
        final String pageId = "pageTest";
        final String widgetId0 = "widgetTest0";
        final String widgetId1 = "widgetTest1";
        final String widgetId2 = "widgetTest2";
        final String widgetId3 = "widgetTest3";
        final String widgetId4 = "widgetTest4";
        String widgetId5 = "widgetTest5";
        openActorFilterDefinitionWizardPage(actorFilterId);
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
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.FINISH_LABEL)));
        bot.button(IDialogConstants.FINISH_LABEL).click();
        ActorFilterDefRepositoryStore store = (ActorFilterDefRepositoryStore) RepositoryManager
                .getInstance().getRepositoryStore(
                        ActorFilterDefRepositoryStore.class);
        ConnectorDefinition actorDefinition = store.getDefinition(
                actorFilterId, "1.0.0");

        Component widget0 = actorDefinition.getPage().get(0).getWidget().get(0);
        assertNotNull("widget " + widgetId0 + " for actor filter definition "
                + actorFilterId + " and page " + pageId + " was not created", widget0);
        assertTrue("widget " + widgetId0 + " for actor filter definition "
                + actorFilterId + " and page " + pageId
                + " should be a text widget", widget0 instanceof Text);

        Component widget1 = actorDefinition.getPage().get(0).getWidget().get(1);
        assertNotNull("widget " + widgetId1 + " for actor definition "
                + actorFilterId + " and page " + pageId + " was not created",
                widget1);
        assertTrue("widget " + widgetId1 + " for actor filter definition "
                + actorFilterId + " and page " + pageId
                + " should be a text widget", widget1 instanceof Password);

        Component widget2 = actorDefinition.getPage().get(0).getWidget().get(2);
        assertNotNull("widget " + widgetId2 + " for actor definition "
                + actorFilterId + " and page " + pageId + " was not created",
                widget2);
        assertTrue("widget " + widgetId2 + " for actor filter definition "
                + actorFilterId + " and page " + pageId
                + " should be a text widget", widget2 instanceof Checkbox);

        Component widget3 = actorDefinition.getPage().get(0).getWidget().get(3);
        assertNotNull("widget " + widgetId3 + " for actor definition "
                + actorFilterId + " and page " + pageId + " was not created",
                widget3);
        assertTrue("widget " + widgetId3 + " for actor filter definition "
                + actorFilterId + " and page " + pageId
                + " should be a text widget", widget3 instanceof Select);

        Component widget4 = actorDefinition.getPage().get(0).getWidget().get(4);
        assertNotNull("widget " + widgetId4 + " for connector definition "
                + actorFilterId + " and page " + pageId + " was not created",
                widget4);
        assertTrue("widget " + widgetId4 + " for actor filter definition "
                + actorFilterId + " and page " + pageId
                + " should be a text widget", widget4 instanceof RadioGroup);

        Component widget5 = actorDefinition.getPage().get(0).getWidget().get(5);
        assertNotNull("widget " + widgetId5 + " for actor filter definition "
                + actorFilterId + " and page " + pageId + " was not created",
                widget5);
        assertTrue("widget " + widgetId5 + " for actor filter definition "
                + actorFilterId + " and page " + pageId
                + " should be a text widget", widget5 instanceof Group);
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
        bot.waitUntil(org.eclipse.swtbot.swt.finder.waits.Conditions.shellIsActive(Messages.newFilterDefinition));
    }

}
