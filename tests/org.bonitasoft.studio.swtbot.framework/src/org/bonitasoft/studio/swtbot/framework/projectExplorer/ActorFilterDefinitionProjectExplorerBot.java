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
import org.bonitasoft.studio.swtbot.framework.connector.ConnectorDefinitionWizardBot;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

public class ActorFilterDefinitionProjectExplorerBot extends ProjectExplorerBot {

    public ActorFilterDefinitionProjectExplorerBot(SWTGefBot bot) {
        super(bot);
    }

    @Override
    public ConnectorDefinitionWizardBot newConnectorDefinition() {
        clickOnContextualMenu(getActorFilterDefFolderTreeItem(), "New...");
        return new ConnectorDefinitionWizardBot(bot,
                org.bonitasoft.studio.actors.i18n.Messages.newFilterDefinition);
    }

    public ConnectorDefinitionWizardBot openActorFilterDefinition(String actorFilterDef, String version) {
        clickOnContextualMenu(getActorFilterDefTreeItem(actorFilterDef, version), "Open");
        return new ConnectorDefinitionWizardBot(bot, org.bonitasoft.studio.actors.i18n.Messages.editFilterDefinition);
    }

    public ConnectorDefinitionWizardBot renameActorFilterDefinition(String actorFilterDef, String version) {
        clickOnContextualMenu(getActorFilterDefTreeItem(actorFilterDef, version), "Rename...");
        return new ConnectorDefinitionWizardBot(bot, org.bonitasoft.studio.actors.i18n.Messages.editFilterDefinition);
    }

    public BotDialog exportActorFilterFromDefinition(String actorFilterDef, String version) {
        clickOnContextualMenu(getActorFilterDefTreeItem(actorFilterDef, version), "Export...");
        return new BotDialog(bot, org.bonitasoft.studio.actors.i18n.Messages.selectFilterImplementationToExportTitle);
    }

    public void deleteActorFilterDefinition(String connectorDef, String version) {
        clickOnContextualMenu(getActorFilterDefTreeItem(connectorDef, version), "Delete");
    }

    public SWTBotTreeItem getActorFilterDefTreeItem(String actorFilterDef, String version) {
        return getTreeItem(getActorFilterDefFolderTreeItem(), toFileName(actorFilterDef, version));
    }

    public SWTBotTreeItem getActorFilterDefFolderTreeItem() {
        return getTreeItem(getProjectTreeItem(), "Actor filter definitions");
    }

    public String toFileName(String connectorDef, String version) {
        return String.format("%s-%s.def", connectorDef, version);
    }

}
