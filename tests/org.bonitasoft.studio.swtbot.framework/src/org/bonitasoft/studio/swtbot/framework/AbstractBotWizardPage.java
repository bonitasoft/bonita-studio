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
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.waits.Conditions;

/**
 * Base of dialog.
 *
 * @author Joachim Segala
 */
public abstract class AbstractBotWizardPage extends BotBase {

    public AbstractBotWizardPage(final SWTGefBot bot) {
        super(bot);
    }

    /**
     * Click on finish.
     */
    public void finish() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.FINISH_LABEL)), 5000);
        bot.button(IDialogConstants.FINISH_LABEL).click();
    }

    /**
     * Click on next.
     */
    public AbstractBotWizardPage next() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.NEXT_LABEL)), 5000);
        bot.button(IDialogConstants.NEXT_LABEL).click();
        return this;
    }

    public AbstractBotWizardPage back() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.BACK_LABEL)), 5000);
        bot.button(IDialogConstants.BACK_LABEL).click();
        return this;
    }

    public boolean canFlipToNextPage() {
        try {
            bot.button(IDialogConstants.NEXT_LABEL);
        } catch (final WidgetNotFoundException e) {
            return false;
        }
        return bot.button(IDialogConstants.NEXT_LABEL).isEnabled();
    }

    public boolean canFlipToPreviousPage() {
        try {
            bot.button(IDialogConstants.BACK_LABEL);
        } catch (final WidgetNotFoundException e) {
            return false;
        }
        return bot.button(IDialogConstants.BACK_LABEL).isEnabled();
    }
}
