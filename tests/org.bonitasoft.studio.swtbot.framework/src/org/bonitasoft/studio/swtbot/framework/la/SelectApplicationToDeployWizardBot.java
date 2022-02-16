/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.la;

import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotDialog;
import org.bonitasoft.studio.ui.page.SelectionSinglePage;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;

public class SelectApplicationToDeployWizardBot extends BotDialog {

    public SelectApplicationToDeployWizardBot(SWTGefBot bot, String dialogTitle) {
        super(bot, dialogTitle);
    }

    /**
     * select an application to deploy
     */
    public SelectApplicationToDeployWizardBot select(String appToSelect) {
        table().select(appToSelect);
        return this;
    }

    public void deploy() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(Messages.deploy)));
        final SWTBotShell activeShell = bot.activeShell();
        bot.button(Messages.deploy).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
        bot.waitUntil(Conditions.shellIsActive(Messages.deployDoneTitle));
        bot.button(IDialogConstants.OK_LABEL).click();
    }

    public SWTBotTable table() {
        return bot.tableWithId(SelectionSinglePage.TABLE_ID);
    }
}
