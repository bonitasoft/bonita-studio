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
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

/**
 * Base of dialog.
 *
 * @author Joachim Segala
 */
public abstract class BotWizardDialog extends BotDialog {

    private String finishButtonLabel = IDialogConstants.FINISH_LABEL;

    protected BotWizardDialog(final SWTGefBot bot, final String dialogTitle) {
        super(bot, dialogTitle);
    }

    protected BotWizardDialog(final SWTGefBot bot, final String dialogTitle, String finishButtonLabel) {
        super(bot, dialogTitle);
        this.finishButtonLabel = finishButtonLabel;
    }

    /**
     * Click on finish.
     */
    public void finish() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(finishButtonLabel)));
        final SWTBotShell activeShell = bot.activeShell();
        bot.button(finishButtonLabel).click();
        bot.waitUntil(Conditions.shellCloses(activeShell), onFinishTimeout());
    }

    protected int onFinishTimeout() {
        return 5000;
    }

    /**
     * Click on next.
     */
    public BotWizardDialog next() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.NEXT_LABEL)), 5000);
        bot.button(IDialogConstants.NEXT_LABEL).click();
        return this;
    }

    /**
     * Click on back.
     */
    public BotWizardDialog back() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.BACK_LABEL)), 5000);
        bot.button(IDialogConstants.BACK_LABEL).click();
        return this;
    }

    public boolean canFlipToNextPage() {
        try {
            return bot.button(IDialogConstants.NEXT_LABEL).isEnabled();
        } catch (final WidgetNotFoundException e) {
            return false;
        }
    }

}
