/*******************************************************************************
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.tests.connectors.sforce;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestSForceConnection extends AbstractSforceTest {

	@Test
	public void testConnectionConnect() throws Exception {
		goToSalesForceWizard(CREATE_OBJECT);
		connectToSalesforce();
		bot.button(IDialogConstants.CANCEL_LABEL).click();
	}

	@Test
	public void testConnectionDisconnect() throws Exception {
		goToSalesForceWizard(CREATE_OBJECT);
		connectToSalesforce();
		disconnectToSalesforce();
		bot.button(IDialogConstants.CANCEL_LABEL).click();
	}

}
