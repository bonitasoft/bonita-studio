/**
 * Copyright (C) 2022 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.swtbot.framework.configuration;

import org.bonitasoft.studio.configuration.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotDialog;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;


public class BotEnvironmentDetails extends BotDialog {

    public BotEnvironmentDetails(SWTGefBot bot, String envName) {
        super(bot, String.format(Messages.detailsEnvironment, envName));
    }
    
    @Override
    public void ok() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(Messages.modify)));
        final SWTBotShell activeShell = bot.activeShell();
        bot.button(Messages.modify).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
    }
    
    
    public void modify() {
        ok();
    }
    
    public String getName() {
        return bot.textWithId(SWTBOT_ID_ENV_NAME).getText();
    }
    
    public String getDescription() {
        return bot.textWithId(SWTBOT_ID_ENV_DESC).getText();
    }

    public void setName(String name) {
        bot.textWithId(SWTBOT_ID_ENV_NAME).setText(name);
    }

    public void setDescription(String description) {
        bot.textWithId(SWTBOT_ID_ENV_DESC).setText(description);
    }
    

}
