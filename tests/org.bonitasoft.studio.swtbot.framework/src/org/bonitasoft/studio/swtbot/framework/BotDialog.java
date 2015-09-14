/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework;

import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.WaitForObjectCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

/**
 * Base of dialog.
 *
 * @author Joachim Segala
 */
public class BotDialog extends BotBase {

    private final String dialogTitle;

    public BotDialog(final SWTGefBot bot, final String dialogTitle) {
        super(bot);
        this.dialogTitle = dialogTitle;
        bot.waitUntil(Conditions.shellIsActive(dialogTitle), 10000);
    }

    /**
     * Click on OK.
     */
    public void ok() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.OK_LABEL)));
        final SWTBotShell activeShell = bot.activeShell();
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
    }

    /**
     * Click on Cancel.
     */
    public void cancel() {
        if (dialogTitle != null && !dialogTitle.isEmpty()) {
            final SWTBotShell shell = bot.shell(dialogTitle);
            shell.setFocus();
            final WaitForObjectCondition<Widget> waitForWidgetCondition = Conditions.waitForWidget(
                    WidgetMatcherFactory.withMnemonic(IDialogConstants.CANCEL_LABEL), shell.widget);
            bot.waitUntil(waitForWidgetCondition, 10000, 100);
            final List<Widget> allMatches = waitForWidgetCondition.getAllMatches();
            if (!allMatches.isEmpty()) {
                final StringBuilder sb = new StringBuilder("Cancel button matches :\n");
                for (final Widget widget : allMatches) {
                    Display.getDefault().syncExec(new Runnable() {

                        @Override
                        public void run() {
                            sb.append(widget.toString());
                            sb.append("\n");
                        }
                    });
                }
                BonitaStudioLog.debug(sb.toString(), "org.bonitasoft.studio.swtbot.framework");
            } else {
                BonitaStudioLog.debug("No Cancel button match :(", "org.bonitasoft.studio.swtbot.framework");
            }
        } else {
            BonitaStudioLog.debug("No Dialog title set", "org.bonitasoft.studio.swtbot.framework");
        }

        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.CANCEL_LABEL)));
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

    protected String getDialogTitle() {
        return dialogTitle;
    }

    /**
     * Return true if the message is found in the dialog.
     *
     * @param pMessage
     * @return
     */
    protected boolean isErrorMessage(final String pMessage) {
        try {
            bot.text(" " + pMessage);
            return true;
        } catch (final WidgetNotFoundException e) {
            BonitaStudioLog.warning(pMessage + " error message not found.", this.getClass().getName());
            return false;
        }
    }

    protected boolean hasTextVisible(final String aText) {
        try {
            bot.text(aText);
            return true;
        } catch (final WidgetNotFoundException e) {
            BonitaStudioLog.warning(aText + " not found.", this.getClass().getName());
            return false;
        }
    }

}
