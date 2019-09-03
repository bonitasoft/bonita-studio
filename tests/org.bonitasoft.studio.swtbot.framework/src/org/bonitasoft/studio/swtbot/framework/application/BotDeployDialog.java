/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.swtbot.framework.application;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotDialog;
import org.bonitasoft.studio.swtbot.framework.widget.BotTreeWidget;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;

public class BotDeployDialog extends BotDialog {

    public BotDeployDialog(final SWTGefBot bot) {
        super(bot, Messages.selectArtifactToDeployTitle);
    }

    public BotDeployDialog searchArtifacts(final String searchText) {
        bot.text(0).setText(searchText);
        return this;
    }

    public BotDeployDialog setDefaultUser(String username) {
        bot.textWithLabelInGroup(org.bonitasoft.studio.actors.i18n.Messages.defaultUser, Messages.deployOptions)
                .setText(username);
        return this;
    }

    public boolean isDefaultUserEnabled() {
        return bot.textWithLabelInGroup(org.bonitasoft.studio.actors.i18n.Messages.defaultUser, Messages.deployOptions)
                .isEnabled();
    }

    public BotDeployDialog cleanBDMDatabase(boolean clean) {
        if (clean) {
            bot.checkBoxInGroup(Messages.cleanBDMDatabase, Messages.deployOptions).select();
        } else {
            bot.checkBoxInGroup(Messages.cleanBDMDatabase, Messages.deployOptions).deselect();
        }
        return this;
    }

    public boolean isCleanBDMDatabaseEnabled() {
        return bot.checkBoxInGroup(Messages.cleanBDMDatabase, Messages.deployOptions).isEnabled();
    }

    public boolean isCleanBDMDatabaseSelected() {
        return bot.checkBoxInGroup(Messages.cleanBDMDatabase, Messages.deployOptions).isChecked();
    }

    public BotDeployDialog runValidation(boolean validate) {
        if (validate) {
            bot.checkBoxInGroup(Messages.validateProcess, Messages.deployOptions).select();
        } else {
            bot.checkBoxInGroup(Messages.validateProcess, Messages.deployOptions).deselect();
        }
        return this;
    }

    public boolean isValidateSelected() {
        return bot.checkBoxInGroup(Messages.validateProcess, Messages.deployOptions).isChecked();
    }

    public BotDialog deploy() {
        bot.button(Messages.deploy).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.deployStatus), 120000);
        return new BotDialog(bot, Messages.deployStatus);
    }

    public boolean isDeployEnabled() {
        return bot.button(Messages.deploy).isEnabled();
    }

    public BotTreeWidget artifactsTree() {
        return new BotTreeWidget(bot.tree());
    }

    public BotDeployDialog selectNone() {
        bot.button(Messages.selectNone).click();
        return this;
    }
    
    public BotDeployDialog selectAll() {
        bot.button(Messages.selectAll).click();
        return this;
    }
    
    public BotDeployDialog selectLatest() {
        bot.button(Messages.selectLatestVersion).click();
        return this;
    }
}
