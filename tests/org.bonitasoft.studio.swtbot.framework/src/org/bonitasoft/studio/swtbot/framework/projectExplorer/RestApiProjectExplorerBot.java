/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.projectExplorer;

import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.conditions.BonitaBPMConditions;
import org.bonitasoft.studio.swtbot.framework.restApi.RestAPIExtensionCreationWizardBot;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

public class RestApiProjectExplorerBot extends ProjectExplorerBot {

    public RestApiProjectExplorerBot(SWTGefBot bot) {
        super(bot);
    }

    public RestAPIExtensionCreationWizardBot createRestApiFromProjectFolder() {
        SWTBotTreeItem projectTreeItem = getProjectTreeItem();
        bot.waitUntil(contextMenuAvailable(projectTreeItem, "New"));
        projectTreeItem.contextMenu().menu("New").menu("REST API Extension...").click();
        bot.waitUntil(Conditions.shellIsActive(Messages.newRestApiExtensionTitle));
        return new RestAPIExtensionCreationWizardBot(bot);
    }

    public RestAPIExtensionCreationWizardBot createRestApi() {
        clickOnContextualMenu(getRestApiFolderTreeItem(), "New...");
        bot.waitUntil(Conditions.shellIsActive(Messages.newRestApiExtensionTitle));
        return new RestAPIExtensionCreationWizardBot(bot);
    }

    public void buildRestApi(String restApi) {
        clickOnContextualMenu(getRestApiTreeItem(restApi), "Build");
    }

    public void runRestApiTests(String restApi) {
        clickOnContextualMenu(getRestApiTreeItem(restApi), "Run JUnit Test");
        bot.waitUntil(BonitaBPMConditions.noPopupActive(), 200000);
    }

    public void deployRestAPi(String restApi) {
        clickOnContextualMenu(getRestApiTreeItem(restApi), "Deploy");
        bot.waitUntil(Conditions.shellIsActive(Messages.deploySuccessTitle), 200000);
        SWTBotShell activeShell = bot.activeShell();
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
    }

    public void deleteRestApi(String restApi) {
        clickOnContextualMenu(getRestApiTreeItem(restApi), "Delete");
    }

    public void openRestApiPomFile(String restApi) {
        SWTBotTreeItem pomTreeItem = getTreeItem(getRestApiTreeItem(restApi), "pom.xml");
        clickOnContextualMenu(pomTreeItem, "Open");
    }

    public SWTBotTreeItem getRestApiFolderTreeItem() {
        return getTreeItem(getProjectTreeItem(), "REST API extensions");
    }

    public SWTBotTreeItem getRestApiTreeItem(String restApi) {
        return getTreeItem(getRestApiFolderTreeItem(), restApi);
    }

}
