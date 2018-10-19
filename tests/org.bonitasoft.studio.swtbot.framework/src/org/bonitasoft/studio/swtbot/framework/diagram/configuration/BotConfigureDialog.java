/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.swtbot.framework.diagram.configuration;

import org.bonitasoft.studio.configuration.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotDialog;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

public class BotConfigureDialog extends BotDialog {

    public BotConfigureDialog(final SWTGefBot bot, final String processName) {
        super(bot, Messages.bind(Messages.configurationTitle, "Local", processName));
    }

    public BotActorMappingConfigurationPage selectActorMapping() {
        bot.table().select(org.bonitasoft.studio.actors.i18n.Messages.actorMapping);
        return new BotActorMappingConfigurationPage(bot);
    }

    public BotJavaDependenciesConfigurationPage selectJavaDependencies() {
        bot.table().select(Messages.javaDependencies);
        return new BotJavaDependenciesConfigurationPage(bot);
    }
}
