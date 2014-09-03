/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;

/**
 * Base of dialog.
 *
 * @author Joachim Segala
 */
public class BotDialog extends BotBase {

    public BotDialog(final SWTGefBot bot) {
        super(bot);
    }

    /**
     * Click on OK.
     */
    public void ok() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.OK_LABEL)));
        bot.button(IDialogConstants.OK_LABEL).click();
    }

    /**
     * Click on Cancel.
     */
    public void cancel() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.CANCEL_LABEL)));
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

}
