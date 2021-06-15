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

import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.allOf;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.widgetOfType;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withId;

import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.bonitasoft.studio.swtbot.framework.ConditionBuilder;
import org.bonitasoft.studio.swtbot.framework.bdm.BotBdmEditor;
import org.bonitasoft.studio.swtbot.framework.connector.ConnectorDefinitionWizardBot;
import org.bonitasoft.studio.swtbot.framework.connector.ConnectorImplementationWizardBot;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

public class ProjectExplorerBot extends BotBase {

    private static final String NEW_CONNECTOR_DEF_COMMAND = "org.bonitasoft.studio.connectors.newDefinition";
    private static final String NEW_CONNECTOR_IMPL_COMMAND = "org.bonitasoft.studio.connectors.newImplementation";
    private static final String NEW_FILTER_DEF_COMMAND = "org.bonitasoft.studio.actors.newFilterDef";
    private static final String NEW_FILTER_IMPL_COMMAND = "org.bonitasoft.studio.actors.newFilterImpl";

    private CommandExecutor commandExecutor = new CommandExecutor();

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

    public void newOrganization() {
        SWTBotTreeItem projectTreeItem = getProjectTreeItem();
        bot.waitUntil(contextMenuAvailable(projectTreeItem, "New"));
        projectTreeItem.contextMenu().menu("New").menu("Organization").click();
    }

    public BotBdmEditor newBdm() {
        SWTBotTreeItem projectTreeItem = getProjectTreeItem();
        bot.waitUntil(contextMenuAvailable(projectTreeItem, "New"));
        projectTreeItem.contextMenu().menu("New")
                .menu(org.bonitasoft.studio.application.i18n.Messages.businessDataModel)
                .click();
        return new BotBdmEditor(bot);
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
        bot.waitUntil(Conditions.widgetIsEnabled(bot.menu("Development")), 10000);
        bot.getDisplay().asyncExec(() -> commandExecutor.executeCommand(NEW_CONNECTOR_DEF_COMMAND, null));
        bot.waitUntil(Conditions.shellIsActive("New connector definition"), 10000);
        return new ConnectorDefinitionWizardBot(bot,
                org.bonitasoft.studio.connectors.i18n.Messages.newConnectorDefinition);
    }

    public ConnectorImplementationWizardBot newConnectorImplementation() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.menu("Development")), 10000);
        bot.getDisplay().asyncExec(() -> commandExecutor.executeCommand(NEW_CONNECTOR_IMPL_COMMAND, null));
        bot.waitUntil(Conditions.shellIsActive("New connector implementation"), 10000);
        return new ConnectorImplementationWizardBot(bot,
                org.bonitasoft.studio.connectors.i18n.Messages.newConnectorImplementation);
    }

    public ConnectorDefinitionWizardBot newActorFilterDefinition() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.menu("Development")), 10000);
        bot.getDisplay().asyncExec(() -> commandExecutor.executeCommand(NEW_FILTER_DEF_COMMAND, null));
        return new ConnectorDefinitionWizardBot(bot, org.bonitasoft.studio.identity.i18n.Messages.newFilterDefinition);
    }

    public ConnectorImplementationWizardBot newActorFilterImplementation() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.menu("Development")), 10000);
        bot.getDisplay().asyncExec(() -> commandExecutor.executeCommand(NEW_FILTER_IMPL_COMMAND, null));
        bot.waitUntil(Conditions.shellIsActive(Messages.newFilterImplementation), 10000);
        return new ConnectorImplementationWizardBot(bot,
                org.bonitasoft.studio.identity.i18n.Messages.newFilterImplementation);
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

    public SWTBotTree getProjectExplorerTree() {
        bot.waitUntil(
                Conditions.waitForWidget(
                        allOf(widgetOfType(Tree.class),
                                withId("org.bonitasoft.studio.application.projectExplorerTree"))),
                120000);
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
        String expectedTitle = extension.map(ext -> title + ext).orElse(title);
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return Objects.equals(ProjectExplorerBot.this.bot.activeEditor().getTitle(), expectedTitle);
            }

            @Override
            public String getFailureMessage() {
                return String.format("The active editor title should be  %s instead of %s", expectedTitle,
                        ProjectExplorerBot.this.bot.activeEditor().getTitle());
            }
        }, 10000);
    }

    public void validate() {
        SWTBotTreeItem projectTreeItem = getProjectTreeItem();
        bot.waitUntil(contextMenuAvailable(projectTreeItem, "Validate"));
        projectTreeItem.contextMenu().menu("Validate").click();
    }

}
