/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram.general.connectors;

import org.bonitasoft.studio.swtbot.framework.BotDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

public class BotConnectDatabaseDialog extends BotDialog {

    private SWTBotShell activeShell;

    public BotConnectDatabaseDialog(final SWTGefBot bot, SWTBotShell activeShell) {
        super(bot, org.bonitasoft.studio.sqlbuilder.ex.Messages.connectionInfoDialogTitle);
        this.activeShell = activeShell;
    }

    public BotConnectDatabaseDialog configure(final String driverClassName, final String jdbcURL, final String userName,
            final String password) {
        bot.textWithLabel(org.bonitasoft.studio.sqlbuilder.ex.Messages.className).setText(driverClassName);
        bot.textWithLabel(org.bonitasoft.studio.sqlbuilder.ex.Messages.JDBCUrl).setText(jdbcURL);
        bot.textWithLabel(org.bonitasoft.studio.sqlbuilder.ex.Messages.username).setText(userName);
        bot.textWithLabel(org.bonitasoft.studio.sqlbuilder.ex.Messages.password).setText(password);
        return this;
    }

    public void ok(final boolean isAskingUpdateConnectorConfiguration) {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.OK_LABEL)));
        bot.button(IDialogConstants.OK_LABEL).click();

        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.sqlbuilder.ex.Messages.connect));
        bot.button(IDialogConstants.OK_LABEL).click();

        if (isAskingUpdateConnectorConfiguration) {
            bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.sqlbuilder.ex.Messages.updateConfigurationTitle));
            bot.button(IDialogConstants.YES_LABEL).click();
        }
        activeShell.setFocus();
    }

}
