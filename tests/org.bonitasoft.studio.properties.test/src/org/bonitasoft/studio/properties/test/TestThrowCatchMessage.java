/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.properties.test;

import java.io.IOException;

import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author aurelie Zara
 *
 */

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestThrowCatchMessage extends SWTBotGefTestCase {
    private final String catchMessageEventName="send order";
    private final String sectionTitle="Messages";
    private final String messageContentSectionTitle="Message content";
    private final String correlationSectionTitle="Correlation";
    private final String messageName="orderMessage";
    private final String throwMessageEventName="receive order";
    private final String targetPool="Online shop";
    private final String targetTask="receive order";
    private final String correlationKey1="customerId";
    private final String correlationValue1="customerId";
    private final String correlationKey2="orderDate";
    private final String correlationValue2="orderDate";
    private final String messageContent1="product";
    private final String messageContent2="customerInformation";
    private final String messageContent3="orderInformation";


    @Test
    public void testThrowCathMessage() throws IOException{
        SWTBotTestUtil.importProcessWIthPathFromClass(bot, "DemoMessageContentCorrelation-1.0.bos", "BOS Archive", "DemoMessageContentCorrelation", this.getClass(), false);
        SWTBotEditor botEditor = bot.activeEditor();
        SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

        SWTBotTestUtil.selectEventOnProcess(bot, gmfEditor, catchMessageEventName);
        testThrowMessageConfiguration();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "General");
        SWTBotTestUtil.selectElementFromOverview(bot, targetPool, null, "Start Message Event "+throwMessageEventName);
        testCatchMessageProperties();
    }

    private void testThrowMessageConfiguration(){
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).setFocus();
        bot.waitUntil(Conditions.widgetIsEnabled(bot.textWithLabel(Messages.name)),10000);
        SWTBotTestUtil.selectTabbedPropertyView(bot, sectionTitle);
        bot.button(Messages.Add).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.messageEventAddWizardPageTitle));
        assertFalse(IDialogConstants.FINISH_LABEL+" button should be disabled",bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.textWithLabel(Messages.name+" *").setText(messageName);
        assertFalse(IDialogConstants.FINISH_LABEL+" button should be disabled",bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.textWithLabel(Messages.processNameLabel+" *").setText(targetPool);
        assertFalse(IDialogConstants.FINISH_LABEL+" button should be disabled",bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.textWithLabel(Messages.eventNameLabel+" *").setText(targetTask);
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.FINISH_LABEL)),5000);
        bot.radio(Messages.useCorrelationkeys).click();
        bot.waitUntil(Conditions.widgetIsEnabled(bot.buttonInGroup(Messages.AddCorrelation,Messages.correlation)),5000);
        bot.buttonInGroup(Messages.AddCorrelation,Messages.correlation).click();
        bot.buttonInGroup(Messages.AddCorrelation,Messages.correlation).click();
        bot.buttonInGroup(Messages.AddCorrelation,Messages.correlation).click();
        //last line for testing empty expression
        bot.tableInGroup(Messages.correlation).click(0,0);
        bot.textInGroup(Messages.correlation).setText(correlationKey1);
        bot.tableInGroup(Messages.correlation).click(0,1);
        bot.textInGroup(Messages.correlation).setText(correlationValue1);
        //test button disabled when enter same correlation key
        bot.tableInGroup(Messages.correlation).click(1,0);
        bot.textInGroup(Messages.correlation).setText(correlationKey1);
        bot.tableInGroup(Messages.correlation).click(2,0);
        assertFalse(IDialogConstants.FINISH_LABEL+" button should be disabled",bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.tableInGroup(Messages.correlation).click(1,0);
        bot.textInGroup(Messages.correlation).setText(correlationKey2);
        bot.tableInGroup(Messages.correlation).click(1,1);
        bot.textInGroup(Messages.correlation).setText(correlationValue2);
        bot.buttonInGroup(Messages.addMessageContentButton, Messages.addMessageContent).click();
        bot.buttonInGroup(Messages.addMessageContentButton, Messages.addMessageContent).click();
        bot.buttonInGroup(Messages.addMessageContentButton, Messages.addMessageContent).click();
        //last line for testing empty expressions
        bot.buttonInGroup(Messages.addMessageContentButton, Messages.addMessageContent).click();
        bot.tableInGroup(Messages.addMessageContent).click(0, 0);
        bot.textInGroup(Messages.addMessageContent).setText(messageContent1);
        bot.tableInGroup(Messages.addMessageContent).click(0,1);
        bot.textInGroup(Messages.addMessageContent).setText(messageContent1);
        //test button disabled when enter same message content id;
        bot.tableInGroup(Messages.addMessageContent).click(1,0);
        bot.textInGroup(Messages.addMessageContent).setText(messageContent1);
        bot.tableInGroup(Messages.addMessageContent).click(2,0);
        assertFalse(IDialogConstants.FINISH_LABEL+" button should be disabled",bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.tableInGroup(Messages.addMessageContent).click(1,0);
        bot.textInGroup(Messages.addMessageContent).setText(messageContent2);
        bot.tableInGroup(Messages.addMessageContent).click(1,1);
        bot.textInGroup(Messages.addMessageContent).setText(messageContent2);
        bot.tableInGroup(Messages.addMessageContent).click(2,0);
        bot.textInGroup(Messages.addMessageContent).setText(messageContent3);
        bot.tableInGroup(Messages.addMessageContent).click(2,1);
        bot.textInGroup(Messages.addMessageContent).setText(messageContent3);
        bot.tableInGroup(Messages.addMessageContent).click(2,0);//need click elsewhere to trigger validation
        bot.button(IDialogConstants.FINISH_LABEL).click();
    }

    private void testCatchMessageProperties(){
        editMessageContent();
        editCorrelation();
    }

    private void editMessageContent(){
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).setFocus();
        SWTBotTestUtil.selectTabbedPropertyView(bot, messageContentSectionTitle);
        bot.button(Messages.autoFillMessageContent).click();
        bot.waitUntil(Conditions.widgetIsEnabled(bot.comboBox()),5000);
        assertEquals(bot.comboBox(0).selection(),messageContent1+" ("+String.class.getName()+")");
        assertEquals(bot.comboBox(1).selection(),messageContent2+" ("+String.class.getName()+")");
        assertEquals(bot.comboBox(2).selection(),messageContent3+" ("+String.class.getName()+")");
        bot.waitUntil(Conditions.widgetIsEnabled(bot.text()),5000);
        //index for expression viewer start at index 1 because of the description in the form widget section
        assertEquals(messageContent1,bot.text(1).getText());
        assertEquals(messageContent2,bot.text(2).getText());
        assertEquals(messageContent3,bot.text(3).getText());
    }

    private void editCorrelation(){
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).setFocus();
        SWTBotTestUtil.selectTabbedPropertyView(bot, correlationSectionTitle);
        bot.button(Messages.autoFillMessageContent).click();
        assertEquals(correlationKey1,bot.table().cell(0,0));
        assertEquals(correlationKey2,bot.table().cell(1, 0));
    }

    @Override
    @After
    public void tearDown(){
        bot.saveAllEditors();
    }
}
