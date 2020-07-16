/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.swtbot.framework.diagram.execution;

import org.bonitasoft.studio.sqlbuilder.ex.Messages;
import org.bonitasoft.studio.swtbot.framework.AbstractBotWizardPage;
import org.bonitasoft.studio.swtbot.framework.diagram.general.connectors.BotConnectDatabaseDialog;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

public class BotDatabaseAccesInformationWizardPage extends AbstractBotWizardPage {

    public BotDatabaseAccesInformationWizardPage(final SWTGefBot bot) {
        super(bot);
    }

    public BotConnectDatabaseDialog connectToDatabase() {
        SWTBotShell activeShell = bot.activeShell();
        bot.buttonInGroup(Messages.connect, "Database connection").click();
        return new BotConnectDatabaseDialog(bot, activeShell);
    }

}
