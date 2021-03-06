/*******************************************************************************
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.tests.connectors.sforce;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(SWTBotJunit4ClassRunner.class)
public class TestSForceRetrieveObject extends AbstractSforceTest {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        sftoolMockUtil.mockGetFields("Account", Arrays.asList("AccountNumber", "Name", "Owner", "Ownership"));
    }

    @Test
    public void testRetrieveAutoCompletion() throws Exception {
        goToSalesForceWizard(RETRIEVE_OBJECT);
        connectToSalesforce();
        bot.button(IDialogConstants.NEXT_LABEL).click();

        final SWTBotText typeText = bot.textWithLabel("Object type *");
        typeText.setFocus();
        typeText.typeText("Accou", 100);
        SWTBot proposalBot = bot.shellWithId(SWTBOT_ID_EXPRESSIONVIEWER_PROPOSAL_SHELL).bot();
        SWTBotTable proposalTAble = proposalBot.tableWithId(SWTBOT_ID_EXPRESSIONVIEWER_PROPOSAL_TABLE);
        proposalTAble.select(0);
        SWTBotTestUtil.pressEnter();
        bot.sleep(100);
        assertEquals("Autocompletion in textField Object Type doesn't works",
                "Account", typeText.getText());
        final SWTBotTable attributsTable = bot
                .tableWithLabel("Objects identifiers *");
        bot.button(0).click();
        attributsTable.click(0, 0);
        bot.sleep(200);
        bot.textWithId(SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 1).typeText("test", 100);
        final SWTBotTable fieldsTable = bot.tableWithLabel("Object fields");
        bot.button(2).click();
        fieldsTable.click(0, 0);
        bot.sleep(200);
        bot.textWithId(SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 1).typeText("AccountNu", 150);
        proposalBot = bot.shellWithId(SWTBOT_ID_EXPRESSIONVIEWER_PROPOSAL_SHELL).bot();
        proposalTAble = proposalBot.tableWithId(SWTBOT_ID_EXPRESSIONVIEWER_PROPOSAL_TABLE);
        proposalTAble.select(0);
        SWTBotTestUtil.pressEnter();
        bot.button(IDialogConstants.CANCEL_LABEL).setFocus();
        bot.sleep(100);
        assertEquals("Autocompletion in object fields table doesn't works",
                "AccountNumber", fieldsTable.cell(0, 0));
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

}
