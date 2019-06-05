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
package org.bonitasoft.studio.swtbot.framework.organization;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotWizardDialog;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

public class BotManageOrganizationWizard extends BotWizardDialog {

    public BotManageOrganizationWizard(final SWTGefBot bot) {
        super(bot, Messages.manageOrganizationTitle);
    }

    public void selectOrganization(final String name) {
        final int index = bot.table().indexOf(name, Messages.name);
        bot.table().select(index);
    }

    public BotManageUserWizardpage manageUsers() {
        return new BotManageUserWizardpage(bot);
    }

    @Override
    public void finish() {
        final SWTBotShell shell = bot.activeShell();
        super.finish();
        //this is a long-running operation, wait that the shell closes
        bot.waitUntil(Conditions.shellCloses(shell),25000);
    }

}
