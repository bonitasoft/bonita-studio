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

import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.studio.common.repository.core.ActiveOrganizationProvider;
import org.bonitasoft.studio.identity.organization.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.swtbot.framework.BotDialog;
import org.bonitasoft.studio.swtbot.framework.organization.BotOrganizationEditor;
import org.bonitasoft.studio.ui.i18n.Messages;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

public class OrganizationProjectExplorerBot extends ProjectExplorerBot {

    private static final String ORGA_EXT = "." + OrganizationRepositoryStore.ORGANIZATION_EXT;
    private ActiveOrganizationProvider activeOrganizationProvider;

    public OrganizationProjectExplorerBot(SWTGefBot bot) {
        super(bot);
        activeOrganizationProvider = new ActiveOrganizationProvider();
    }

    @Override
    public void newOrganization() {
        clickOnContextualMenu(getOrganizationFolderTreeItem(), "New");
    }

    public BotDialog exportOrganization() {
        clickOnContextualMenu(getOrganizationFolderTreeItem(), "Export...");
        return new BotDialog(bot, org.bonitasoft.studio.identity.i18n.Messages.exportOrganizationTitle);
    }

    public BotDialog exportOrganization(String organization) {
        clickOnContextualMenu(getOrganizationTreeItem(organization), "Export...");
        return new BotDialog(bot, org.bonitasoft.studio.identity.i18n.Messages.exportOrganizationTitle);
    }

    public BotOrganizationEditor openOrganization(String organization) {
        clickOnContextualMenu(getOrganizationTreeItem(organization), "Open");
        String editorName = String.format("%s.%s", organization, OrganizationRepositoryStore.ORGANIZATION_EXT);
        waitUntilActiveEditorTitleIs(editorName, Optional.empty());
        return new BotOrganizationEditor(bot, editorName);
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
        clickOnContextualMenu(getTreeItem(organizationTreeItem, getDisplayName(organization)), "Deploy");
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.identity.i18n.Messages.deployOrganizationTitle));
        bot.text().setText(user);
        bot.button("Deploy").click();
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.identity.i18n.Messages.deployInformationTitle));
        SWTBotShell activeShell = bot.activeShell();
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
    }

    public void deleteOrganization(String organization) {
        SWTBotTreeItem organizationTreeItem = getOrganizationFolderTreeItem();
        clickOnContextualMenu(getTreeItem(organizationTreeItem, getDisplayName(organization)), "Delete");
    }

    private SWTBotTreeItem getOrganizationFolderTreeItem() {
        return getTreeItem(getProjectTreeItem(), "Organizations");
    }

    private SWTBotTreeItem getOrganizationTreeItem(String organization) {
        SWTBotTreeItem organizationFolderTreeItem = getOrganizationFolderTreeItem();
        return getTreeItem(organizationFolderTreeItem, getDisplayName(organization));
    }

    private String getDisplayName(String name) {
        String displayName = name;
        if (!displayName.endsWith(ORGA_EXT)) {
            displayName += ORGA_EXT;
        }
        if (Objects.equals(activeOrganizationProvider.getActiveOrganization(), name)) {
            displayName = String.format("%s  (active)", displayName);
        }
        return displayName;
    }

}
