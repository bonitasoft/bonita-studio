/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
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
