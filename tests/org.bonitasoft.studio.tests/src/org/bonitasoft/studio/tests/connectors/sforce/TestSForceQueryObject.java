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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.bonitasoft.studio.connector.wizard.sforce.i18n.Messages;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotLink;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotList;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotStyledText;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(SWTBotJunit4ClassRunner.class)
public class TestSForceQueryObject extends AbstractSforceTest {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        sftoolMockUtil.mockGetFields("Contact", Arrays.asList("Id", "IsDeleted", "Name", "FirstName", "LastName", "Phone"));
    }

    @Test
    public void testQuerySwitchEditor() throws Exception {
        goToSalesForceWizard(QUERY_OBJECT);
        connectToSalesforce();
        bot.button(IDialogConstants.NEXT_LABEL).click();
        final SWTBotStyledText queryText = bot.styledTextWithLabel("Query *");
        assertTrue("Textfield query must be enabled when helper is close",
                queryText.isEnabled());
        final SWTBotLink l = bot.link(1);
        l.click("View/close query generator");
        assertFalse("Textfield query must be disabled when helper is open",
                queryText.isEnabled());
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

    @Test
    public void testQueryHelperList() throws Exception {
        goToSalesForceWizard(QUERY_OBJECT);
        connectToSalesforce();
        bot.button(IDialogConstants.NEXT_LABEL).click();
        final SWTBotLink l = bot.link(1);
        l.click("View/close query generator");
        final SWTBotCombo typeCombo = bot
                .comboBoxWithLabel(Messages.queryObjectType);
        typeCombo.setSelection("Contact");
        assertEquals("Autocompletion doesn't works", "Contact",
                typeCombo.getText());

        final SWTBotList possibleFiled = bot.listWithLabel(
                Messages.queryObjectFields, 0);
        final String[] selection = { "Id", "Name", "FirstName", "LastName", "Phone" };

        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                possibleFiled.select(selection);
                return possibleFiled.selectionCount() == selection.length;
            }

            @Override
            public void init(final SWTBot bot) {

            }

            @Override
            public String getFailureMessage() {
                return "Some fields are not selected expected :"+selection.length+" but was :"+possibleFiled.selectionCount();
            }
        },10000,500);


        bot.button(Messages.add + " >").click();

        final SWTBotList chooseFields = bot.listWithLabel(Messages.queryObjectFields,
                1);
        assertEquals(
                "Not all selected fields from possible field have move to choose fields after add button click",
                chooseFields.itemCount(), selection.length);
        chooseFields.select("Phone");
        bot.button("< " + Messages.remove).click();
        assertEquals("The remove field button doesn't works",
                chooseFields.itemCount(), selection.length - 1);
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

    @Test
    public void testQuerySimpleGeneration() throws Exception {
        goToSalesForceWizard(QUERY_OBJECT);
        connectToSalesforce();
        bot.button(IDialogConstants.NEXT_LABEL).click();
        final SWTBotLink l = bot.link(1);
        l.click("View/close query generator");
        final SWTBotCombo typeCombo = bot
                .comboBoxWithLabel(Messages.queryObjectType);

        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return Arrays.asList(typeCombo.items()).contains("Contact");
            }

            @Override
            public void init(final SWTBot bot) {

            }

            @Override
            public String getFailureMessage() {
                return "Contact item not found in combo";
            }
        },10000,500);

        typeCombo.setSelection("Contact");
        final SWTBotList possibleFiled = bot.listWithLabel(
                Messages.queryObjectFields, 0);
        possibleFiled.select("Id");
        bot.button(Messages.add + " >").click();
        assertEquals(
                "The query must be : 'select id from contact', but it is : "
                        + bot.styledTextWithLabel("Query *").getText()
                        .toLowerCase(),
                        bot.styledTextWithLabel("Query *").getText().toLowerCase()
                        .replaceAll(" ", ""), "SELECT Id FROM Contact"
                        .toLowerCase().replaceAll(" ", ""));
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

    @Test
    public void testQueryTrickyGeneration() throws Exception {
        goToSalesForceWizard(QUERY_OBJECT);
        connectToSalesforce();
        bot.button(IDialogConstants.NEXT_LABEL).click();
        final SWTBotLink l = bot.link(1);
        l.click("View/close query generator");
        final SWTBotCombo typeCombo = bot
                .comboBoxWithLabel(Messages.queryObjectType);
        typeCombo.setSelection("Contact");
        final SWTBotList possibleFiled = bot.listWithLabel(
                Messages.queryObjectFields, 0);
        possibleFiled.select("Id");
        bot.button(Messages.add + " >").click();
        final SWTBotCombo cond1Combo = bot.comboBoxWithLabel(
                Messages.queryObjectConditions, 0);
        assertFalse(cond1Combo.isEnabled());
        final SWTBotCombo field1Combo = bot.comboBoxWithLabel(
                Messages.queryObjectConditions, 1);
        field1Combo.setSelection("IsDeleted");
        final SWTBotCombo cond2Combo = bot.comboBoxWithLabel(
                Messages.queryObjectConditions, 2);
        assertTrue(cond2Combo.getText().contains("="));
        final SWTBotCombo field2Combo = bot.comboBoxWithLabel(
                Messages.queryObjectConditions, 3);
        field2Combo.setText("false");
        bot.button(Messages.add).click();
        assertTrue(cond1Combo.isEnabled());
        assertEquals(
                "The query must be : 'select id from contact', but it is : "
                        + bot.styledTextWithLabel("Query *").getText()
                        .toLowerCase().replaceAll(" ", ""), bot
                        .styledTextWithLabel("Query *").getText().toLowerCase()
                        .replaceAll(" ", ""),
                        "SELECT Id FROM Contact WHERE  IsDeleted = false".toLowerCase()
                        .replaceAll(" ", ""));
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

}
