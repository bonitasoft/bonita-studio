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

import org.bonitasoft.studio.ui.i18n.Messages;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

public class DeleteApplicationWizardBot extends SelectApplicationWizardEditorBot {

    public DeleteApplicationWizardBot(SWTGefBot bot, String dialogTitle) {
        super(bot, dialogTitle);
    }

    @Override
    public DeleteApplicationWizardBot select(String... appToSelect) {
        super.select(appToSelect);
        return this;
    }

    public void delete() {
        finish();
    }

    /**
     * click on delete
     */
    @Override
    public void finish() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(Messages.delete)));
        SWTBotShell activeShell = bot.activeShell();
        bot.button(Messages.delete).click();
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.deleteDoneTitle));
        bot.shell(Messages.deleteDoneTitle).activate();
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
    }
}
