/**
 * Copyright (C) 2021 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.swtbot.framework.application.editor.project;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotWizardDialog;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

public class BotExtensionWizard extends BotWizardDialog {

    public BotExtensionWizard(SWTGefBot bot, String dialogTitle) {
        super(bot, dialogTitle, org.bonitasoft.studio.ui.i18n.Messages.importLabel);
    }

    public BotExtensionWizard setGroupId(String groupId) {
        bot.textWithLabel("Group ID *").setText(groupId);
        return this;
    }
    
    public BotExtensionWizard setArtifactId(String artifactId) {
        bot.textWithLabel("Artifact ID *").setText(artifactId);
        return this;
    }
    
    public BotExtensionWizard setVersion(String version) {
        bot.textWithLabel("Version *").setText(version);
        return this;
    }

    public BotExtensionWizard manual() {
        bot.radio(Messages.manual).click();
        return this;
    }
    
    public BotExtensionWizard fromFile() {
        bot.radio(Messages.fromFile).click();
        return this;
    }
    
    @Override
    protected int onFinishTimeout() {
        return 90000;
    }

}
