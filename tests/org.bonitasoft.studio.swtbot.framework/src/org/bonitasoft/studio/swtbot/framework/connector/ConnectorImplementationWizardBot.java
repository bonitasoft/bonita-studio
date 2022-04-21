/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.connector;

import org.bonitasoft.studio.swtbot.framework.BotWizardDialog;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

public class ConnectorImplementationWizardBot extends BotWizardDialog {

    public ConnectorImplementationWizardBot(SWTGefBot bot, String dialogTitle) {
        super(bot, dialogTitle);
    }

    public ConnectorImplementationWizardBot selectDefinition(String definitionId) {
        bot.textWithId("org.bonitasoft.studio.common.jface.treeExplorer.searchFieldText").setText(definitionId);
        bot.tableWithId(SWTBOT_ID_EXPLORER_RIGHT_TABLE).getTableItem(definitionId).select();
        return this;
    }

    public ConnectorImplementationWizardBot withImplementationId(String id) {
        bot.textWithId("org.bonitasoft.studio.connector.model.implementation.wizard.idText").setText(id);
        return this;
    }

    public ConnectorImplementationWizardBot withImplementationVersion(String version) {
        bot.textWithId("org.bonitasoft.studio.connector.model.implementation.wizard.versionText").setText(version);
        return this;
    }

    public ConnectorImplementationWizardBot withClassName(String className) {
        bot.textWithId("org.bonitasoft.studio.connector.model.implementation.wizard.classNameText").setText(className);
        return this;
    }

    public ConnectorImplementationWizardBot nextPage() {
        next();
        return this;
    }

}
