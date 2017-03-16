/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.swtbot.framework.la;

import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotDialog;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

public class NewApplicationWizardBot extends BotDialog {

    public NewApplicationWizardBot(SWTGefBot bot, String dialogTitle) {
        super(bot, dialogTitle);
    }

    public BotApplicationEditor createApplication(String token, String displayName) {
        bot.textWithLabel(Messages.applicationToken).setText(token);
        bot.textWithLabel(Messages.displayName).setText(displayName);
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(Messages.create)));
        SWTBotShell activeShell = bot.activeShell();
        bot.button(Messages.create).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
        return new BotApplicationEditor(bot, bot.activeEditor());
    }
}
