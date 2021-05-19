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
import org.bonitasoft.studio.application.preference.RepositoriesComposite;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCheckBox;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;

public class BotMavenRepositoriesConfigurationPage extends BotMavenConfigurationPage {

    public BotMavenRepositoriesConfigurationPage(SWTGefBot bot) {
        super(bot);
    }

    public BotMavenRepositoriesConfigurationPage selectProfile(String profile) {
        bot.comboBoxWithId(RepositoriesComposite.PROFILE_COMBO_ID).setSelection(profile);
        return this;
    }

    public BotMavenRepositoriesConfigurationPage addRepository(String name) {
        bot.toolbarButtonWithId(RepositoriesComposite.ADD_REPOSITORY_BUTTON_ID).click();
        setName(RepositoriesComposite.DEFAULT_REPOSITORY_NAME, name);
        return this;
    }

    public BotMavenRepositoriesConfigurationPage setName(String oldName, String newName) {
        getRepositoriesTable().select(oldName);
        bot.textWithLabel(Messages.name).setText(newName);
        return this;
    }

    public BotMavenRepositoriesConfigurationPage setId(String repoName, String id) {
        getRepositoriesTable().select(repoName);
        bot.textWithLabel(Messages.id).setText(id);
        return this;
    }

    public BotMavenRepositoriesConfigurationPage setUrl(String repoName, String url) {
        getRepositoriesTable().select(repoName);
        bot.textWithLabel(Messages.url).setText(url);
        return this;
    }

    public BotMavenRepositoriesConfigurationPage setReleases(String repoName, boolean enable, String updatePolicy,
            String checksumPolicy) {
        setRepositoryPolicy(repoName, Messages.releases, enable, updatePolicy, checksumPolicy);
        return this;
    }

    public BotMavenRepositoriesConfigurationPage setSnapshots(String repoName, boolean enable, String updatePolicy,
            String checksumPolicy) {
        setRepositoryPolicy(repoName, Messages.snapshots, enable, updatePolicy, checksumPolicy);
        return this;
    }

    private void setRepositoryPolicy(String repoName, String groupName, boolean enable, String updatePolicy,
            String checksumPolicy) {
        getRepositoriesTable().select(repoName);

        SWTBotCheckBox enableCheckbox = bot.checkBoxInGroup(groupName);
        if ((enableCheckbox.isChecked() && !enable)
                || (!enableCheckbox.isChecked() && enable)) {
            enableCheckbox.click();
        }

        bot.comboBoxInGroup(groupName, 0).setSelection(updatePolicy);
        bot.comboBoxInGroup(groupName, 1).setSelection(checksumPolicy);
    }

    private SWTBotTable getRepositoriesTable() {
        return bot.tableWithId(RepositoriesComposite.REPOSITORIES_VIEWER_ID);
    }

}
