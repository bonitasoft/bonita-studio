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
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(SWTBotJunit4ClassRunner.class)
public class TestSForceCreateObject extends AbstractSforceTest {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        sftoolMockUtil.mockGetMandatoryFields("Account", Arrays.asList("Name"));
        sftoolMockUtil.mockGetFields("Account", Arrays.asList("Name", "OwnerId", "Ownership"));
    }


    @Test
    public void testCreateRequiredField() throws Exception {
        goToSalesForceWizard(CREATE_OBJECT);
        connectToSalesforce();
        sftoolMockUtil.mockGetMandatoryFields("Account", Arrays.asList("Name"));
        sftoolMockUtil.mockGetFields("Account", Arrays.asList("Name", "Owner", "Ownership"));
        bot.button(IDialogConstants.NEXT_LABEL).click();
        final SWTBotText typeText = bot.textWithLabel("Object type *");
        typeText.setFocus();
        typeText.typeText("Accou", 100);
        SWTBot proposalBot = bot.shellWithId(SWTBOT_ID_EXPRESSIONVIEWER_PROPOSAL_SHELL).bot();
        SWTBotTable proposalTAble = proposalBot.tableWithId(SWTBOT_ID_EXPRESSIONVIEWER_PROPOSAL_TABLE);
        proposalTAble.select(0);
        SWTBotTestUtil.pressEnter();

        bot.sleep(100);
        assertEquals("Auto-completion has failed.", "Account", typeText.getText());
        final SWTBotTable attributTable = bot.tableWithLabel("Object fields");
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return 2 == attributTable.columnCount();
            }

            @Override
            public String getFailureMessage() {
                return "Attributs table has less or more than two columns";
            }
        });
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return "Name".equals(attributTable.cell(0, 0));
            }

            @Override
            public String getFailureMessage() {
                return "Required fields are not set in the table";
            }
        });

        bot.button("Add row").click();
        attributTable.click(1, 0);
        bot.textWithId(SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 1).typeText("Ownershi", 250);
        proposalBot = bot.shellWithId(SWTBOT_ID_EXPRESSIONVIEWER_PROPOSAL_SHELL).bot();

        proposalTAble = proposalBot.tableWithId(SWTBOT_ID_EXPRESSIONVIEWER_PROPOSAL_TABLE);
        proposalTAble.select(0);
        SWTBotTestUtil.pressEnter();
        bot.button(IDialogConstants.CANCEL_LABEL).setFocus();
        assertEquals("Fields autocompletion doesn't works", "Ownership", attributTable.cell(1, 0));
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

}
