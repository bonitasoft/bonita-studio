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
package org.bonitasoft.studio.swtbot.framework.projectExplorer;

import org.bonitasoft.studio.swtbot.framework.BotDialog;
import org.bonitasoft.studio.ui.i18n.Messages;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

public class LivingApplicationProjectExplorerBot extends ProjectExplorerBot {

    public LivingApplicationProjectExplorerBot(SWTGefBot bot) {
        super(bot);
    }

    @Override
    public void newLivingApplication() {
        clickOnContextualMenu(getApplicationFolderTreeItem(), "New");
    }

    public void renameApplication(String oldName, String newName) {
        clickOnContextualMenu(getApplicationTreeItem(oldName), "Rename...");
        bot.waitUntil(Conditions.shellIsActive(Messages.rename));
        SWTBotShell activeShell = bot.activeShell();
        bot.text().setText(newName);
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
    }

    public void deleteApplication(String application) {
        clickOnContextualMenu(getApplicationTreeItem(application), "Delete");
    }

    public BotDialog exportApplication() {
        clickOnContextualMenu(getApplicationFolderTreeItem(), "Export...");
        return new BotDialog(bot, org.bonitasoft.studio.la.i18n.Messages.exportApplicationDescriptor);
    }

    public void openApplication(String application) {
        clickOnContextualMenu(getApplicationTreeItem(application), "Open");
    }

    public void deployApplication(String application) {
        clickOnContextualMenu(getApplicationTreeItem(application), "Deploy");
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.application.i18n.Messages.selectArtifactToDeployTitle));
        SWTBotShell activeShell = bot.activeShell();
        bot.button(org.bonitasoft.studio.application.i18n.Messages.deploy).click();
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.application.i18n.Messages.deployStatus));
        activeShell = bot.activeShell();
        bot.button(IDialogConstants.CLOSE_LABEL).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
    }

    public SWTBotTreeItem getApplicationTreeItem(String application) {
        return getTreeItem(getApplicationFolderTreeItem(), addXmlExtension(application));
    }

    public SWTBotTreeItem getApplicationFolderTreeItem() {
        return getTreeItem(getProjectTreeItem(), "Application descriptors");
    }

    private String addXmlExtension(String application) {
        if (!application.endsWith(".xml")) {
            application += ".xml";
        }
        return application;
    }

}
