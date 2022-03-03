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

import org.assertj.core.util.Strings;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.preference.ServersComposite;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;

public class BotMavenServersConfigurationPage extends BotMavenConfigurationPage {

    public BotMavenServersConfigurationPage(SWTGefBot bot) {
        super(bot);
    }

    public BotMavenServersConfigurationPage createMasterPasswordIfEmpty() {
        if (Strings.isNullOrEmpty(bot.textWithLabel(Messages.encryptionMasterPassword).getText())) {
            bot.toolbarButtonWithId("org.bonitasoft.studio.ui.widget.textWidget.editButton").click();
            bot.textWithLabel(Messages.encryptionMasterPassword).setText("password");
            bot.toolbarButtonWithId("org.bonitasoft.studio.ui.widget.textWidget.validateEdit").click();
        }
        return this;
    }

    public BotMavenServersConfigurationPage addServer(String id) {
        bot.toolbarButtonWithId(ServersComposite.ADD_SERVER_BUTTON_ID).click();
        setId(ServersComposite.DEFAULT_SERVER_NAME, id);
        return this;
    }

    public BotMavenServersConfigurationPage setId(String oldId, String newId) {
        getServersTable().select(oldId);
        bot.comboBox().setText(newId);
        return this;
    }

    public BotMavenServersConfigurationPage selectUserPasswordAuthentication() {
        bot.radio(Messages.userPasswordAuthentication).click();
        return this;
    }

    public BotMavenServersConfigurationPage selectSshAuthentication() {
        bot.radio(Messages.sshAuthentication).click();
        return this;
    }

    public BotMavenServersConfigurationPage setUsername(String userName) {
        bot.textWithLabel(Messages.username).setText(userName);
        return this;
    }

    public BotMavenServersConfigurationPage setPassword(String password) {
        bot.textWithLabel(Messages.password).setText(password);
        return this;
    }

    public BotMavenServersConfigurationPage encryptPassword() {
        bot.toolbarButtonWithId(ServersComposite.ENCRYPT_PWD_BUTTON_ID).click();
        return this;
    }

    public BotMavenServersConfigurationPage setPassphrase(String passphrase) {
        bot.textWithLabel(Messages.passphrase).setText(passphrase);
        return this;
    }

    private SWTBotTable getServersTable() {
        return bot.tableWithId(ServersComposite.SERVERS_VIEWER_ID);
    }

}
