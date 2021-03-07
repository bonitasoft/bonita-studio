/*******************************************************************************
 * Copyright (C) 2013-2014 Bonitasoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.tests.connectors.sforce;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.core.maven.AddDependencyOperation;
import org.bonitasoft.studio.connector.wizard.sforce.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.junit.Before;
import org.junit.Rule;

import com.sforce.ws.ConnectionException;

public abstract class AbstractSforceTest implements SWTBotConstants {

    protected static final String LOGIN = "qa@bonitasoft.com";

    protected static final String SECURITY_TOKEN = "CHkQLnYpzupsIWYseTtN1ofx";

    protected static final String PASSWORD = "Bonita12345";

    protected static final String CREATE_OBJECT = "Create Object";

    protected static final String RETRIEVE_OBJECT = "Retrieve Object";

    protected static final String QUERY_OBJECT = "Query Objects";

    protected static final String DELETE_OBJECT = "Delete Object";

    protected static final String UPDATE_OBJECT = "Update Object";

    protected SalesforceMockUtil sftoolMockUtil;

    protected final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule rule = new SWTGefBotRule(bot);

    @Before
    public void setUp() throws Exception {
        new AddDependencyOperation("org.bonitasoft.connectors", "bonita-connector-salesforce", "1.1.3")
            .run(AbstractRepository.NULL_PROGRESS_MONITOR);
        
        SWTBotTestUtil.getKeybord();
        sftoolMockUtil = new SalesforceMockUtil();
        sftoolMockUtil.initMock();
        sftoolMockUtil.mockGetAllSForceObjects();
    }

    protected KeyStroke getEnter() {
        return Keystrokes.CR;
    }

    protected void goToSalesForceWizard(final String salesforceConnector) {
        bot.menu("Development").menu("Connectors").menu("Test connector...")
                .click();
        assertEquals(
                "Menu : Development/Connectors/Test connector... doesn't exist",
                "Test connector", bot.activeShell().getText());
        bot.text().setText(salesforceConnector);
        bot.table().select(salesforceConnector);
        bot.waitUntil(new DefaultCondition() {

            @Override
            public final boolean test() throws Exception {
                return bot.button(IDialogConstants.NEXT_LABEL).isEnabled();
            }

            @Override
            public final String getFailureMessage() {
                return "Error : No " + salesforceConnector + " Salesforce found in the connector list";
            }
        });
        bot.button(IDialogConstants.NEXT_LABEL).click();
    }

    public void connectToSalesforce() throws Exception {
        assertTrue("Connect button is disabled while user is not connected",
                bot.button(Messages.connect).isEnabled());
        assertFalse("Button disconect is enabled while user is not connected",
                bot.button(Messages.disconnect).isEnabled());
        SWTBotShell activeShell = bot.activeShell();
        bot.button("Connect").click();

        final SWTBotText loginText = bot.textWithLabel(Messages.login);
        loginText.setFocus();
        loginText.setText(LOGIN);
        assertEquals("Error writing the login field", LOGIN,
                loginText.getText());
        final SWTBotText passwordText = bot.textWithLabel(Messages.password);
        passwordText.setFocus();
        passwordText.setText(PASSWORD);
        assertEquals("Error writing the password field", PASSWORD,
                passwordText.getText());
        final SWTBotText securityTokenText = bot.textWithLabel(Messages.token);
        securityTokenText.setFocus();
        securityTokenText.setText(SECURITY_TOKEN);
        assertEquals("Error writing the security token field", SECURITY_TOKEN,
                securityTokenText.getText());
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.testConnectTitle),
                50000);
        sftoolMockUtil.setSalesforceIsLogged(true);
        assertEquals(Messages.testConnectTitle, bot.activeShell().getText());
        bot.button(IDialogConstants.OK_LABEL).click();
        activeShell.setFocus();
        assertFalse("Connect button is enabled while user is connected", bot
                .button(Messages.connect).isEnabled());
        assertTrue("Button disconect is disabled while user is connected", bot
                .button(Messages.disconnect).isEnabled());
    }

    public void disconnectToSalesforce() throws ConnectionException {
        assertFalse("Connect button is enabled while user is connected", bot
                .button(Messages.connect).isEnabled());
        assertTrue("Button disconect is disabled while user is connected", bot
                .button(Messages.disconnect).isEnabled());
        bot.button(Messages.disconnect).click();
        sftoolMockUtil.setSalesforceIsLogged(false);
    }

}
