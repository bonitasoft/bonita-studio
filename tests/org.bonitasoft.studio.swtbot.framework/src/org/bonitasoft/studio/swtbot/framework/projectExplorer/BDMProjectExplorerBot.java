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

import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.bdm.BotBdmEditor;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

public class BDMProjectExplorerBot extends ProjectExplorerBot {

    public BDMProjectExplorerBot(SWTGefBot bot) {
        super(bot);
    }

    public BotBdmEditor openBdm() {
        clickOnContextualMenu(getBdmTreeItem(), "Open");
        return new BotBdmEditor(bot);
    }

    public void deleteBdm() {
        clickOnContextualMenu(getBdmTreeItem(), "Delete");
    }

    public void deployBdm() {
        clickOnContextualMenu(getBdmTreeItem(), "Deploy");
        bot.activeShell().bot().waitUntil(Conditions.shellIsActive(Messages.bdmDeployedTitle),15000);
        SWTBotShell activeShell = bot.activeShell();
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
    }

    public SWTBotTreeItem getBdmFolderTreeItem() {
        return getTreeItem(getProjectTreeItem(), "Business Data Model");
    }

    public SWTBotTreeItem getBdmTreeItem() {
        return getTreeItem(getBdmFolderTreeItem(), "bom.xml");
    }

}
