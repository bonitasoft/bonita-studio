/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.swtbot.framework.organization;

import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.la.SelectApplicationWizardEditorBot;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

public class OpenOrganizationWizardBot extends SelectApplicationWizardEditorBot {

    private String orga;

    public OpenOrganizationWizardBot(SWTGefBot bot, String dialogTitle) {
        super(bot, dialogTitle);
    }

    @Override
    public OpenOrganizationWizardBot select(String... orgaToSelect) {
        this.orga = orgaToSelect.length > 0 ? orgaToSelect[0] : "";
        super.select(orgaToSelect);
        return this;
    }

    @Override
    public void finish() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(Messages.open)));
        SWTBotShell activeShell = bot.activeShell();
        bot.button(Messages.open).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
        bot.sleep(500); //Multipage xml editor run an asynchronous sanity check on the resource. Let him finish first.
    }

    public BotOrganizationEditor open() {
        finish();
        return new BotOrganizationEditor(bot, orga);
    }
}
