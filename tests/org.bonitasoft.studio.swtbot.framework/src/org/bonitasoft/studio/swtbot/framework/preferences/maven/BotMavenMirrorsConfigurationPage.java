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
import org.bonitasoft.studio.application.preference.MirrorsComposite;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;

public class BotMavenMirrorsConfigurationPage extends BotMavenConfigurationPage {

    public BotMavenMirrorsConfigurationPage(SWTGefBot bot) {
        super(bot);
    }

    public BotMavenMirrorsConfigurationPage addMirror(String name) {
        bot.toolbarButtonWithId(MirrorsComposite.ADD_MIRROR_BUTTON_ID).click();
        setName(MirrorsComposite.DEFAULT_MIRROR_NAME, name);
        return this;
    }

    public BotMavenMirrorsConfigurationPage setId(String name, String id) {
        getMirrorsTable().select(name);
        bot.textWithLabel(Messages.id + " *").setText(id);
        return this;
    }

    public BotMavenMirrorsConfigurationPage setName(String oldName, String newName) {
        getMirrorsTable().select(oldName);
        bot.textWithLabel(Messages.name).setText(newName);
        return this;
    }

    public BotMavenMirrorsConfigurationPage setUrl(String name, String url) {
        getMirrorsTable().select(name);
        bot.textWithLabel(Messages.url + " *").setText(url);
        return this;
    }

    public BotMavenMirrorsConfigurationPage setMirrorOf(String name, String mirrorOf) {
        getMirrorsTable().select(name);
        bot.textWithLabel(Messages.mirrorOf).setText(mirrorOf);
        return this;
    }

    private SWTBotTable getMirrorsTable() {
        return bot.tableWithId(MirrorsComposite.MIRRORS_VIEWER_ID);
    }

}
