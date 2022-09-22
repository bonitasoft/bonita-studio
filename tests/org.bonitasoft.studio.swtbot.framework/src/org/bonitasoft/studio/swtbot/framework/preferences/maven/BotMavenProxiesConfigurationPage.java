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
import org.bonitasoft.studio.application.preference.ProxiesComposite;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;

public class BotMavenProxiesConfigurationPage extends BotMavenConfigurationPage {

    public BotMavenProxiesConfigurationPage(SWTGefBot bot) {
        super(bot);
    }

    public BotMavenProxiesConfigurationPage addProxy(String id) {
        bot.toolbarButtonWithId(ProxiesComposite.ADD_PROXY_BUTTON_ID).click();
        setId(ProxiesComposite.DEFAULT_PROXY_NAME, id);
        return this;
    }

    public BotMavenProxiesConfigurationPage setId(String oldId, String newId) {
        getProxiesTable().select(oldId);
        bot.textWithLabel(Messages.id + " *").setText(newId);
        return this;
    }

    public BotMavenProxiesConfigurationPage setProtocol(String id, String protocol) {
        getProxiesTable().select(id);
        bot.textWithLabel(Messages.protocol).setText(protocol);
        return this;
    }

    public BotMavenProxiesConfigurationPage setHost(String id, String host) {
        getProxiesTable().select(id);
        bot.textWithLabel(Messages.host + " *").setText(host);
        return this;
    }

    public BotMavenProxiesConfigurationPage setPort(String id, String port) {
        getProxiesTable().select(id);
        bot.textWithLabel(Messages.port).setText(port);
        return this;
    }

    public BotMavenProxiesConfigurationPage setNonProxyHosts(String id, String nonProxyHosts) {
        getProxiesTable().select(id);
        bot.textWithLabel(Messages.nonProxyHost).setText(nonProxyHosts);
        return this;
    }

    public BotMavenProxiesConfigurationPage setUsername(String userName) {
        bot.textWithLabel(Messages.username).setText(userName);
        return this;
    }

    public BotMavenProxiesConfigurationPage setPassword(String password) {
        bot.textWithLabel(Messages.password).setText(password);
        return this;
    }

    public BotMavenProxiesConfigurationPage encryptPassword() {
        bot.toolbarButtonWithId(ProxiesComposite.ENCRYPT_PWD_BUTTON_ID).click();
        return this;
    }

    private SWTBotTable getProxiesTable() {
        return bot.tableWithId(ProxiesComposite.PROXIES_VIEWER_ID);
    }

}
