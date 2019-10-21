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

@SuppressWarnings("restriction")
public class ConnectorDefinitionProjectExplorerBot extends ProjectExplorerBot {

    public ConnectorDefinitionProjectExplorerBot(SWTGefBot bot) {
        super(bot);
    }

    @Override
    public ConnectorDefinitionWizardBot newConnectorDefinition() {
        clickOnContextualMenu(getConnectorDefFolderTreeItem(), "New...");
        return new ConnectorDefinitionWizardBot(bot,
                org.bonitasoft.studio.connector.model.i18n.Messages.newConnectorDefinition);
    }

    public ConnectorDefinitionWizardBot openConnectorDefinition(String connectorDef, String version) {
        clickOnContextualMenu(getConnectorDefTreeItem(connectorDef, version), "Open");
        return new ConnectorDefinitionWizardBot(bot, org.bonitasoft.studio.connectors.i18n.Messages.editConnectorDefinition);
    }

    public ConnectorDefinitionWizardBot renameConnectorDefinition(String connectorDef, String version) {
        clickOnContextualMenu(getConnectorDefTreeItem(connectorDef, version), "Rename...");
        return new ConnectorDefinitionWizardBot(bot, org.bonitasoft.studio.connectors.i18n.Messages.editConnectorDefinition);
    }

    public BotDialog exportConnectorFromDefinition(String connectorDef, String version) {
        clickOnContextualMenu(getConnectorDefTreeItem(connectorDef, version), "Export...");
        return new BotDialog(bot, org.bonitasoft.studio.connectors.i18n.Messages.selectConnectorImplementationToExportTitle);
    }

    public void deleteConnectorDefinition(String connectorDef, String version) {
        clickOnContextualMenu(getConnectorDefTreeItem(connectorDef, version), "Delete");
    }

    public SWTBotTreeItem getConnectorDefTreeItem(String connectorDef, String version) {
        return getTreeItem(getConnectorDefFolderTreeItem(), toFileName(connectorDef, version));
    }

    public SWTBotTreeItem getConnectorDefFolderTreeItem() {
        return getTreeItem(getProjectTreeItem(), "Connector definitions");
    }

    public String toFileName(String connectorDef, String version) {
        return String.format("%s-%s.def", connectorDef, version);
    }

}
