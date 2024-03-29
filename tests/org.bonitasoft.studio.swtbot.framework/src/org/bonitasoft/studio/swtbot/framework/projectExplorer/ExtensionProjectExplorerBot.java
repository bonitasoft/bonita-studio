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
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

public class ExtensionProjectExplorerBot extends ProjectExplorerBot {

    public ExtensionProjectExplorerBot(SWTGefBot bot) {
        super(bot);
    }

    public void buildExtension(String name) {
        clickOnContextualMenu(getExtensionProjectTreeItem(name), "Build");
    }

    public void runTests(String name) {
        clickOnContextualMenu(getExtensionProjectTreeItem(name), "Run JUnit Test");
        bot.waitUntil(BonitaBPMConditions.noPopupActive(), 200000);
    }

    public void deployExtension(String name) {
        clickOnContextualMenu(getExtensionProjectTreeItem(name), "Deploy");
        bot.waitUntil(Conditions.shellIsActive(Messages.deploySuccessTitle), 200000);
        SWTBotShell activeShell = bot.activeShell();
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
    }

    public void deleteExtension(String name) {
        clickOnContextualMenu(getExtensionProjectTreeItem(name), "Delete");
    }

    public void openExtensionPomFile(String name) {
        SWTBotTreeItem pomTreeItem = getTreeItem(getExtensionProjectTreeItem(name), "pom.xml");
        clickOnContextualMenu(pomTreeItem, "Open");
    }

    public SWTBotTreeItem getExtensionsFolderTreeItem() {
        return getTreeItem(getProjectTreeItem(), "Extensions");
    }

    public SWTBotTreeItem getExtensionProjectTreeItem(String restApi) {
        return getTreeItem(getExtensionsFolderTreeItem(), restApi);
    }

}
