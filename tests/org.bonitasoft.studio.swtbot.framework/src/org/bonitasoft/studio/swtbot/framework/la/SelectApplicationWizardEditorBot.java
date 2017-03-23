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

import org.bonitasoft.studio.swtbot.framework.BotDialog;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;

public abstract class SelectApplicationWizardEditorBot extends BotDialog {

    public SelectApplicationWizardEditorBot(SWTGefBot bot, String dialogTitle) {
        super(bot, dialogTitle);
    }

    /**
     * select a given set of applications
     */
    public SelectApplicationWizardEditorBot select(String... appToSelect) {
        table().select(appToSelect);
        return this;
    }

    /**
     * click on delete
     */
    public abstract void finish();

    public SWTBotTable table() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.table()));
        final SWTBotTable table = bot.table();
        return table;
    }
}
