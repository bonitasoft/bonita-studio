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
import org.bonitasoft.studio.swtbot.framework.connector.ConnectorImplementationWizardBot;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

public class ConnectorImplementationProjectExplorerBot extends ProjectExplorerBot {

    public ConnectorImplementationProjectExplorerBot(SWTGefBot bot) {
        super(bot);
    }

    @Override
    public ConnectorImplementationWizardBot newConnectorImplementation() {
        clickOnContextualMenu(getConnectorImplFolderTreeItem(), "New...");
        return new ConnectorImplementationWizardBot(bot,
                org.bonitasoft.studio.connectors.i18n.Messages.newConnectorImplementation);
    }

    public ConnectorImplementationWizardBot openConnectorImplementation(String connectorImpl, String version) {
        clickOnContextualMenu(getConnectorImplTreeItem(connectorImpl, version), "Open");
        return new ConnectorImplementationWizardBot(bot,
                org.bonitasoft.studio.connectors.i18n.Messages.editConnectorImplementation);
    }

    public BotDialog exportConnectorImplementation(String connectorImpl, String version) {
        clickOnContextualMenu(getConnectorImplTreeItem(connectorImpl, version), "Export...");
        return new BotDialog(bot, org.bonitasoft.studio.connectors.i18n.Messages.selectConnectorImplementationToExportTitle);
    }

    public BotDialog exportConnectorImplementation() {
        clickOnContextualMenu(getConnectorImplFolderTreeItem(), "Export...");
        return new BotDialog(bot, org.bonitasoft.studio.connectors.i18n.Messages.selectConnectorImplementationToExportTitle);
    }

    public void deleteConnectorImplementation(String connectorImpl, String version) {
        clickOnContextualMenu(getConnectorImplTreeItem(connectorImpl, version), "Delete");
    }

    public SWTBotTreeItem getConnectorImplTreeItem(String connectorDef, String version) {
        return getTreeItem(getConnectorImplFolderTreeItem(), toFileName(connectorDef, version));
    }

    public SWTBotTreeItem getConnectorImplFolderTreeItem() {
        return getTreeItem(getProjectTreeItem(), "Connector implementations");
    }

    public String toFileName(String connectorImpl, String version) {
        return String.format("%s-%s.impl", connectorImpl, version);
    }
}
