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
package org.bonitasoft.studio.swtbot.framework.preferences;

import static org.eclipse.swtbot.swt.finder.waits.Conditions.widgetIsEnabled;

import org.bonitasoft.studio.preferences.dialog.BonitaPreferenceDialog;
import org.bonitasoft.studio.swtbot.framework.BotDialog;
import org.bonitasoft.studio.swtbot.framework.preferences.maven.BotMavenConfigurationPage;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

public class BotPreferencesDialog extends BotDialog {

    public BotPreferencesDialog(SWTGefBot bot) {
        super(bot, "Preferences");
    }

    public BotMavenConfigurationPage openMavenConfigurationPage() {
        bot.waitUntil(widgetIsEnabled(bot.toolbarButtonWithId(BonitaPreferenceDialog.MAVEN_PAGE_ID)));
        bot.toolbarButtonWithId(BonitaPreferenceDialog.MAVEN_PAGE_ID).click();
        return new BotMavenConfigurationPage(bot);
    }

    public void close() {
        bot.activeShell().close();
    }

}
