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

import org.bonitasoft.studio.connector.wizard.sforce.i18n.Messages;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCombo;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(SWTBotJunit4ClassRunner.class)
public class TestSForceDeleteObject extends AbstractSforceTest {

	@Test
	public void testDeleteAutoCompletion() throws Exception {
		goToSalesForceWizard(DELETE_OBJECT);
		connectToSalesforce();
		bot.button(IDialogConstants.NEXT_LABEL).click();

		final SWTBotCombo combo = bot
				.comboBoxInGroup(Messages.filterAutoCompletionTitle);
		combo.setSelection("Contact");
		assertEquals("AutoCompletion doesn't works on Object type field",
				"Contact", combo.getText());
		bot.button(IDialogConstants.CANCEL_LABEL).click();
	}
}
