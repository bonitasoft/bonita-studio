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
package org.bonitasoft.studio.tests.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCombo;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestThrowCatchMessage implements SWTBotConstants {

    private final String catchMessageEventName = "send order";
    private final String sectionTitle = "Messages";
    private final String messageContentSectionTitle = "Message content";
    private final String correlationSectionTitle = "Correlation";
    private final String messageName = "orderMessage";
    private final String throwMessageEventName = "receive order";
    private final String targetPool = "Online shop";
    private final String targetTask = "receive order";
    private final String correlationKey1 = "customerId";
    private final String correlationValue1 = "customerId";
    private final String correlationKey2 = "orderDate";
    private final String correlationValue2 = "orderDate";
    private final String messageContent1 = "product";
    private final String messageContent2 = "customerInformation";
    private final String messageContent3 = "orderInformation";

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule rule = new SWTGefBotRule(bot);

    @Test
    public void testThrowCathMessage() throws IOException {
        new BotApplicationWorkbenchWindow(bot).importBOSArchive()
                .setArchive(
                        TestThrowCatchMessage.class.getResource("DemoMessageContentCorrelation-1.0.bos"))
                .finish();
        final SWTBotEditor botEditor = bot.activeEditor();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

        SWTBotTestUtil.selectEventOnProcess(bot, gmfEditor,
                catchMessageEventName);
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL)
                .setFocus();
        bot.waitUntil(
                Conditions.widgetIsEnabled(bot.textWithLabel(Messages.name)),
                10000);
        SWTBotTestUtil.selectTabbedPropertyView(bot, sectionTitle);
        bot.button(Messages.AddSimple).click();
        bot.waitUntil(Conditions
                .shellIsActive(Messages.messageEventAddWizardPageTitle));
        assertFalse(IDialogConstants.FINISH_LABEL
                + " button should be disabled",
                bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.textWithLabel(Messages.name + " *").setText(messageName);
        assertFalse(IDialogConstants.FINISH_LABEL
                + " button should be disabled",
                bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.textWithLabel(Messages.processNameLabel + " *").setText(targetPool);
        assertFalse(IDialogConstants.FINISH_LABEL
                + " button should be disabled",
                bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.textWithLabel(Messages.eventNameLabel + " *").setText(targetTask);
        bot.waitUntil(Conditions.widgetIsEnabled(bot
                .button(IDialogConstants.FINISH_LABEL)), 5000);

        bot.tabItem(Messages.correlation).activate();

        bot.checkBox(Messages.useCorrelationkeys).click();

        bot.button(Messages.AddCorrelation).click();
        bot.button(Messages.AddCorrelation).click();
        bot.button(Messages.AddCorrelation).click();
        // last line for testing empty expression

        bot.table().click(0, 0);
        bot.textWithId(SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 2).setText(
                correlationKey1);
        bot.table().click(0, 1);
        bot.textWithId(SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 2).setText(
                correlationValue1);

        // test button disabled when enter same correlation key
        bot.table().click(1, 0);
        bot.textWithId(SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 2).setText(
                correlationKey1);

        bot.table().click(2, 0);

        assertFalse(IDialogConstants.FINISH_LABEL
                + " button should be disabled",
                bot.button(IDialogConstants.FINISH_LABEL).isEnabled());

        bot.table().click(1, 0);
        bot.textWithId(SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 2).setText(
                correlationKey2);
        bot.table().click(1, 1);
        bot.textWithId(SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 2).setText(
                correlationValue2);

        bot.tabItem(Messages.addMessageContent).activate();

        bot.button(Messages.addMessageContentButton).click();
        bot.button(Messages.addMessageContentButton).click();
        bot.button(Messages.addMessageContentButton).click();
        // last line for testing empty expressions
        bot.button(Messages.addMessageContentButton).click();
        bot.table().click(0, 0);
        bot.textWithId(SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 2).setText(
                messageContent1);
        bot.table().click(0, 1);
        bot.textWithId(SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 2).setText(
                messageContent1);
        // test button disabled when enter same message content id;
        bot.table().click(1, 0);
        bot.textWithId(SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 2).setText(
                messageContent1);
        bot.table().click(1, 0);
        bot.textWithId(SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 2).setText(
                messageContent2);
        bot.table().click(1, 1);
        bot.textWithId(SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 2).setText(
                messageContent2);
        bot.table().click(2, 0);
        bot.textWithId(SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 2).setText(
                messageContent3);
        bot.table().click(2, 1);
        bot.textWithId(SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 2).setText(
                messageContent3);
        bot.table().click(2, 0);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "General");
        SWTBotTestUtil.selectElementFromOverview(bot, targetPool, null,
                throwMessageEventName);

        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL)
                .setFocus();
        SWTBotTestUtil
                .selectTabbedPropertyView(bot, messageContentSectionTitle);
        bot.button(Messages.autoFillMessageContent).click();
        bot.waitUntil(Conditions.widgetIsEnabled(bot
                .textWithId(SWTBOT_ID_EXPRESSIONVIEWER_TEXT)), 5000);
        assertEquals(bot.labelWithId(SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 0)
                .getText(), messageContent1);
        assertEquals(bot.labelWithId(SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 1)
                .getText(), messageContent2);
        assertEquals(bot.labelWithId(SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 2)
                .getText(), messageContent3);
        // index for expression viewer start at index 1 because of the
        // description in the form widget section
        assertEquals(messageContent1,
                bot.textWithId(SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 0).getText());
        assertEquals(messageContent2,
                bot.textWithId(SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 1).getText());
        assertEquals(messageContent3,
                bot.textWithId(SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 2).getText());

        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL)
                .setFocus();
        SWTBotTestUtil.selectTabbedPropertyView(bot, correlationSectionTitle);
        bot.button(Messages.autoFillMessageContent).click();
        assertEquals(correlationKey1, bot.table().cell(0, 0));
        assertEquals(correlationKey2, bot.table().cell(1, 0));
    }

    @Test
    public void testCathMessageNotAllowed() throws IOException {
        new BotApplicationWorkbenchWindow(bot).importBOSArchive()
                .setArchive(
                        TestThrowCatchMessage.class.getResource("TestCatchMessageSelectionTest-1.0.bos"))
                .finish();

        final SWTBotEditor botEditor = bot.activeEditor();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

        SWTBotTestUtil.selectEventOnProcess(bot, gmfEditor, "Message2");
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL)
                .setFocus();

        final SWTBotCombo combo = bot
                .comboBoxWithLabel(Messages.selectMessageEventLabel);

        for (final String s : combo.items()) {
            Assert.assertFalse(
                    "The message throw from the same pool should not be available in the combo.",
                    s.equals("theMessage"));
        }
    }

}
