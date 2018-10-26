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
import org.bonitasoft.studio.swtbot.framework.organization.BotManageOrganizationWizard;
import org.bonitasoft.studio.ui.i18n.Messages;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

@SuppressWarnings("restriction")
public class OrganizationProjectExplorerBot extends ProjectExplorerBot {

    private static final String ORGA_EXT = ".organization";

    public OrganizationProjectExplorerBot(SWTGefBot bot) {
        super(bot);
    }

    @Override
    public BotManageOrganizationWizard newOrganization() {
        clickOnContextualMenu(getOrganizationFolderTreeItem(), "New...");
        return new BotManageOrganizationWizard(bot);
    }

    public BotDialog exportOrganization() {
        clickOnContextualMenu(getOrganizationFolderTreeItem(), "Export...");
        return new BotDialog(bot, org.bonitasoft.studio.actors.i18n.Messages.exportOrganizationTitle);
    }

    public BotDialog exportOrganization(String organization) {
        clickOnContextualMenu(getOrganizationTreeItem(organization), "Export...");
        return new BotDialog(bot, org.bonitasoft.studio.actors.i18n.Messages.exportOrganizationTitle);
    }

    public BotManageOrganizationWizard openOrganization(String organization) {
        clickOnContextualMenu(getOrganizationTreeItem(organization), "Open");
        return new BotManageOrganizationWizard(bot);
    }

    public void renameOrganization(String oldName, String newName) {
        clickOnContextualMenu(getOrganizationTreeItem(oldName), "Rename...");
        bot.waitUntil(Conditions.shellIsActive(Messages.rename));
        SWTBotShell activeShell = bot.activeShell();
        bot.text().setText(newName);
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
    }

    public void deployOrganization(String organization, String user) {
        SWTBotTreeItem organizationTreeItem = getOrganizationFolderTreeItem();
        clickOnContextualMenu(getTreeItem(organizationTreeItem, addOrgaExtension(organization)), "Deploy");
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.actors.i18n.Messages.deployOrganizationTitle));
        bot.text().setText(user);
        bot.button("Deploy").click();
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.actors.i18n.Messages.deployInformationTitle));
        SWTBotShell activeShell = bot.activeShell();
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
    }

    private SWTBotTreeItem getOrganizationFolderTreeItem() {
        return getTreeItem(getProjectTreeItem(), "Organization");
    }

    private SWTBotTreeItem getOrganizationTreeItem(String organization) {
        SWTBotTreeItem organizationFolderTreeItem = getOrganizationFolderTreeItem();
        return getTreeItem(organizationFolderTreeItem, addOrgaExtension(organization));
    }

    private String addOrgaExtension(String orgaName) {
        if (!orgaName.endsWith(ORGA_EXT)) {
            orgaName += ORGA_EXT;
        }
        return orgaName;
    }

}
