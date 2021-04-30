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
package org.bonitasoft.studio.swtbot.framework.preferences.maven;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.bonitasoft.studio.swtbot.framework.preferences.BotPreferencesDialog;
import org.bonitasoft.studio.ui.widget.NativeTabFolderWidget;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

public class BotMavenConfigurationPage extends BotBase {

    public BotMavenConfigurationPage(SWTGefBot bot) {
        super(bot);
    }

    public BotMavenRepositoriesConfigurationPage repositories() {
        selectTab(Messages.repositories);
        return new BotMavenRepositoriesConfigurationPage(bot);
    }

    public BotMavenServersConfigurationPage servers() {
        selectTab(Messages.servers);
        return new BotMavenServersConfigurationPage(bot);
    }

    public BotMavenProxiesConfigurationPage proxies() {
        selectTab(Messages.proxies);
        return new BotMavenProxiesConfigurationPage(bot);
    }

    public BotMavenMirrorsConfigurationPage mirrors() {
        selectTab(Messages.mirrors);
        return new BotMavenMirrorsConfigurationPage(bot);
    }

    private void selectTab(String tabName) {
        if (NativeTabFolderWidget.useCTabFolder()) {
            bot.cTabItem(tabName).activate(); // Windaube
        } else {
            bot.tabItem(tabName).activate();
        }
    }

    public BotMavenConfigurationPage apply() {
        bot.button("Apply").click();
        return this;
    }

    public BotPreferencesDialog back() {
        bot.button(org.bonitasoft.studio.preferences.i18n.Messages.BonitaPreferenceDialog_back).click();
        return new BotPreferencesDialog(bot);
    }

}
