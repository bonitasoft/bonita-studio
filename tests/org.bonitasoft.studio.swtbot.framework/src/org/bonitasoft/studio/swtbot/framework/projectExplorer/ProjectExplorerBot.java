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

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.bonitasoft.studio.swtbot.framework.ConditionBuilder;
import org.bonitasoft.studio.swtbot.framework.bdm.DefineBdmWizardBot;
import org.bonitasoft.studio.swtbot.framework.connector.ConnectorDefinitionWizardBot;
import org.bonitasoft.studio.swtbot.framework.connector.ConnectorImplementationWizardBot;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.organization.BotManageOrganizationWizard;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

@SuppressWarnings("restriction")
public class ProjectExplorerBot extends BotBase {

    protected String projectName;

    public ProjectExplorerBot(SWTGefBot bot) {
        super(bot);
        projectName = RepositoryManager.getInstance().getCurrentRepository().getName();
        showExplorerView();
    }

    public void showExplorerView() {
        SWTBotView explorerView = bot.viewById("org.bonitasoft.studio.application.project.explorer");
        if (explorerView == null) {
            throw new IllegalStateException("Project explorer view is not present");
        }
        explorerView.show();
        explorerView.setFocus();
    }

    public BotManageOrganizationWizard newOrganization() {
        SWTBotTreeItem projectTreeItem = getProjectTreeItem();
        bot.waitUntil(contextMenuAvailable(projectTreeItem, "New"));
        projectTreeItem.contextMenu().menu("New").menu("Organization...").click();
        return new BotManageOrganizationWizard(bot);
    }

    public DefineBdmWizardBot newBdm() {
        SWTBotTreeItem projectTreeItem = getProjectTreeItem();
        bot.waitUntil(contextMenuAvailable(projectTreeItem, "New"));
        projectTreeItem.contextMenu().menu("New").menu("Business Data Model...").click();
        return new DefineBdmWizardBot(bot, org.bonitasoft.studio.businessobject.i18n.Messages.manageBusinessDataModelTitle);
    }

    public void newLivingApplication() {
        SWTBotTreeItem projectTreeItem = getProjectTreeItem();
        bot.waitUntil(contextMenuAvailable(projectTreeItem, "New"));
        projectTreeItem.contextMenu().menu("New").menu("Application descriptor").click();
    }

    public BotProcessDiagramPerspective newDiagram() {
        SWTBotTreeItem projectTreeItem = getProjectTreeItem();
        bot.waitUntil(contextMenuAvailable(projectTreeItem, "New"));
        projectTreeItem.contextMenu().menu("New").menu("Diagram").click();
        return new BotProcessDiagramPerspective(bot);
    }

    public ConnectorDefinitionWizardBot newConnectorDefinition() {
        SWTBotTreeItem projectTreeItem = getProjectTreeItem();
        bot.waitUntil(contextMenuAvailable(projectTreeItem, "New"));
        projectTreeItem.contextMenu().menu("New").menu("Connector definition...").click();
        return new ConnectorDefinitionWizardBot(bot,
                org.bonitasoft.studio.connectors.i18n.Messages.newConnectorDefinition);
    }

    public ConnectorImplementationWizardBot newConnectorImplementation() {
        SWTBotTreeItem projectTreeItem = getProjectTreeItem();
        bot.waitUntil(contextMenuAvailable(projectTreeItem, "New"));
        projectTreeItem.contextMenu().menu("New").menu("Connector implementation...").click();
        return new ConnectorImplementationWizardBot(bot,
                org.bonitasoft.studio.connectors.i18n.Messages.newConnectorImplementation);
    }

    public ConnectorDefinitionWizardBot newActorFilterDefinition() {
        SWTBotTreeItem projectTreeItem = getProjectTreeItem();
        bot.waitUntil(contextMenuAvailable(projectTreeItem, "New"));
        projectTreeItem.contextMenu().menu("New").menu("Actor filter definition...").click();
        return new ConnectorDefinitionWizardBot(bot, org.bonitasoft.studio.actors.i18n.Messages.newFilterDefinition);
    }

    public ConnectorImplementationWizardBot newActorFilterImplementation() {
        SWTBotTreeItem projectTreeItem = getProjectTreeItem();
        bot.waitUntil(contextMenuAvailable(projectTreeItem, "New"));
        projectTreeItem.contextMenu().menu("New").menu("Actor filter implementation...").click();
        return new ConnectorImplementationWizardBot(bot, org.bonitasoft.studio.actors.i18n.Messages.newFilterImplementation);
    }

    public OrganizationProjectExplorerBot organization() {
        return new OrganizationProjectExplorerBot(bot);
    }

    public BDMProjectExplorerBot bdm() {
        return new BDMProjectExplorerBot(bot);
    }

    public LivingApplicationProjectExplorerBot livingApplication() {
        return new LivingApplicationProjectExplorerBot(bot);
    }

    public DiagramProjectExplorerBot diagram() {
        return new DiagramProjectExplorerBot(bot);
    }

    public ConnectorDefinitionProjectExplorerBot connectorDefinition() {
        return new ConnectorDefinitionProjectExplorerBot(bot);
    }

    public ConnectorImplementationProjectExplorerBot connectorImplementation() {
        return new ConnectorImplementationProjectExplorerBot(bot);
    }

    public ActorFilterDefinitionProjectExplorerBot actorFilterDefinition() {
        return new ActorFilterDefinitionProjectExplorerBot(bot);
    }

    public ActorFilterImplementationProjectExplorerBot actorFilterImplementation() {
        return new ActorFilterImplementationProjectExplorerBot(bot);
    }

    public SWTBotTreeItem getProjectTreeItem() {
        return getProjectExplorerTree().getTreeItem(projectName);
    }

    protected SWTBotTreeItem getTreeItem(SWTBotTreeItem parent, String item) {
        parent.expand();
        bot.waitUntil(nodeAvailable(parent, item));
        return parent.getNode(item);
    }

    protected void clickOnContextualMenu(SWTBotTreeItem node, String menu) {
        bot.waitUntil(contextMenuAvailable(node, menu));
        node.contextMenu(menu).click();
    }

    protected SWTBotTree getProjectExplorerTree() {
        return bot.treeWithId("org.bonitasoft.studio.application.projectExplorerTree");
    }

    public ICondition nodeAvailable(SWTBotTreeItem item, String node) {
        return new ConditionBuilder()
                .withTest(() -> {
                    try {
                        item.getNode(node);
                        return true;
                    } catch (WidgetNotFoundException e) {
                        return false;
                    }
                })
                .withFailureMessage(() -> String.format("The node %s of '%s' isn't available", node, item))
                .create();
    }

    public ICondition contextMenuAvailable(SWTBotTreeItem item, String menu) {
        return new ConditionBuilder()
                .withTest(() -> item.contextMenu().menuItems().contains(menu))
                .withFailureMessage(() -> String.format("The menu '%s' of '%s' isn't available (%s)", menu, item,
                        item.contextMenu().menuItems()))
                .create();
    }

    public void waitUntilActiveEditorTitleIs(String title, Optional<String> extension) {
        String expectedTitle = extension.isPresent() ? title + extension.get() : title;
        ICondition condition = new ConditionBuilder()
                .withTest(() -> {
                    try {
                        return Objects.equals(bot.activeEditor().getTitle(), expectedTitle);
                    } catch (WidgetNotFoundException e) {
                        return false;
                    }
                })
                .withFailureMessage(() -> String.format("The active editor title should be  %s instead of %s", expectedTitle,
                        bot.activeEditor().getTitle()))
                .create();
        bot.waitUntil(condition);
    }

}
