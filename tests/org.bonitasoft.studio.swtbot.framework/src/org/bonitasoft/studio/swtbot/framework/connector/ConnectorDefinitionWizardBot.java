/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.swtbot.framework.connector;

import org.bonitasoft.studio.swtbot.framework.BotWizardDialog;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

public class ConnectorDefinitionWizardBot extends BotWizardDialog {

    public ConnectorDefinitionWizardBot(SWTGefBot bot, String dialogTitle) {
        super(bot, dialogTitle);
    }

    public ConnectorDefinitionWizardBot withDefinitionId(String id) {
        bot.textWithId("org.bonitasoft.studio.connector.model.definition.wizard.idText").setText(id);
        return this;
    }

    public ConnectorDefinitionWizardBot withDefinitionVersion(String version) {
        bot.textWithId("org.bonitasoft.studio.connector.model.definition.wizard.versionText").setText(version);
        return this;
    }

    public ConnectorDefinitionWizardBot withDefinitionDisplayName(String displayName) {
        bot.textWithId("org.bonitasoft.studio.connector.model.definition.wizard.displayNameText").setText(displayName);
        return this;
    }

}
