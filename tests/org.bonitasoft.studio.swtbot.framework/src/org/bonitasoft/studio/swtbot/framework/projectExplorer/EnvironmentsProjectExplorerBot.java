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

import org.bonitasoft.studio.swtbot.framework.configuration.BotEnvironmentDetails;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

public class EnvironmentsProjectExplorerBot extends ProjectExplorerBot {

    public EnvironmentsProjectExplorerBot(SWTGefBot bot) {
        super(bot);
    }

    public void newEnvironment(String environment) {
        clickOnContextualMenu(getEnvironmentFolderTreeItem(), "New...");
        bot.waitUntil(
                Conditions.shellIsActive(org.bonitasoft.studio.configuration.i18n.Messages.createEnvironmentTitle));
        SWTBotShell activeShell = bot.activeShell();
        activeShell.setFocus();
        bot.text().setText(environment);
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
    }

    public void delete(String environment) {
        clickOnContextualMenu(getEnvironmentTreeItem(environment), "Delete");
    }

    public BotEnvironmentDetails open(String environment) {
        clickOnContextualMenu(getEnvironmentTreeItem(environment), "Open");
        return new BotEnvironmentDetails(bot, environment);
    }

    public void setAsActiveEnvironment(String environment) {
        clickOnContextualMenu(getEnvironmentTreeItem(environment), "Set as active environment");
    }

    public SWTBotTreeItem getEnvironmentTreeItem(String environment) {
        var environmentFolderTreeItem = getEnvironmentFolderTreeItem();
        environmentFolderTreeItem.expand();
        String envTreeLabel = environmentFolderTreeItem.getNodes().stream()
                .filter(node -> isMatchingNode(environment, node))
                .findFirst()
                .orElseThrow();
        return getTreeItem(environmentFolderTreeItem, envTreeLabel);
    }

    public SWTBotTreeItem getEnvironmentFolderTreeItem() {
        return getTreeItem(getProjectTreeItem(), "Environments");
    }

    private boolean isMatchingNode(String environment, String treeNodeLabel) {
        treeNodeLabel = treeNodeLabel.endsWith("  (active)")
                ? treeNodeLabel.substring(0, treeNodeLabel.indexOf("  (active)")) : treeNodeLabel;
        if (!environment.endsWith(".xml")) {
            environment += ".xml";
        }
        return Objects.equals(treeNodeLabel, environment);
    }

}
