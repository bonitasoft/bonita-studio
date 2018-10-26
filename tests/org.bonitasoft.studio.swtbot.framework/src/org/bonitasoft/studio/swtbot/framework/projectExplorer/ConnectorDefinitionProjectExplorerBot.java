/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
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
