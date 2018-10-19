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

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.bonitasoft.studio.swtbot.framework.organization.BotManageOrganizationWizard;
import org.bonitasoft.studio.swtbot.framework.projectExplorer.organization.OrganizationProjectExplorerBot;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

public class ProjectExplorerBot extends BotBase {

    protected String projectName;

    public ProjectExplorerBot(SWTGefBot bot) {
        super(bot);
        projectName = RepositoryManager.getInstance().getCurrentRepository().getName();
    }

    public BotManageOrganizationWizard newOrganization() {
        getProjectExplorerTree().getTreeItem(projectName).contextMenu().menu("New").menu("Organization...").click();
        return new BotManageOrganizationWizard(bot);
    }

    public OrganizationProjectExplorerBot organization() {
        return new OrganizationProjectExplorerBot(bot);
    }

    public SWTBotTreeItem getProjectTreeItem() {
        return getProjectExplorerTree().getTreeItem(projectName);
    }

    protected SWTBotTreeItem getTreeItem(SWTBotTreeItem parent, String item) {
        parent.expand();
        bot.waitUntilWidgetAppears(nodeAvailable(parent, item));
        return parent.getNode(item);
    }

    protected void clickOnContextualMenu(SWTBotTreeItem node, String menu) {
        bot.waitUntilWidgetAppears(contextMenuAvailable(node, menu));
        node.contextMenu(menu).click();
    }

    protected SWTBotTree getProjectExplorerTree() {
        return bot.treeWithId("org.bonitasoft.studio.application.projectExplorerTree");
    }

    protected ICondition nodeAvailable(SWTBotTreeItem item, String node) {
        return new ICondition() {

            @Override
            public boolean test() throws Exception {
                try {
                    item.getNode(node);
                    return true;
                } catch (WidgetNotFoundException e) {
                    return false;
                }
            }

            @Override
            public void init(SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                return String.format("The node %s of '%s' isn't available", node, item);
            }
        };
    }

    protected ICondition contextMenuAvailable(SWTBotTreeItem item, String menu) {
        return new ICondition() {

            @Override
            public boolean test() throws Exception {
                return item.contextMenu().menuItems().contains(menu);
            }

            @Override
            public void init(SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                return String.format("The menu '%s' of '%s' isn't available", menu, item);
            }
        };
    }

}
