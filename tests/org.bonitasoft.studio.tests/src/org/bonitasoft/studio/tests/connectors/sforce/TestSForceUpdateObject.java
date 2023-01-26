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

import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(SWTBotJunit4ClassRunner.class)
public class TestSForceUpdateObject extends AbstractSforceTest {

	@Test
	public void testUpdateAutoCompletion() throws Exception {
		goToSalesForceWizard(UPDATE_OBJECT);
		connectToSalesforce();
		bot.button(IDialogConstants.NEXT_LABEL).click();
		final SWTBotText typeText = bot.textWithLabel("Object type *");
		typeText.setFocus();
		typeText.typeText("Accou", 100);
		final SWTBot proposalBot = bot.shellWithId(SWTBOT_ID_EXPRESSIONVIEWER_PROPOSAL_SHELL).bot();
    	final SWTBotTable proposalTAble = proposalBot.tableWithId(SWTBOT_ID_EXPRESSIONVIEWER_PROPOSAL_TABLE);
		proposalTAble.select(0);
		SWTBotTestUtil.pressEnter();
		bot.sleep(100);
		assertEquals("Autocompletion in textField Object Type doesn't works",
				"Account", typeText.getText());
		bot.button(IDialogConstants.CANCEL_LABEL).click();
	}
}
