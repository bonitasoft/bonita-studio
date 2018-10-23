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
package org.bonitasoft.studio.swtbot.framework.projectExplorer.bdm;

import org.bonitasoft.studio.swtbot.framework.bdm.DefineBdmWizardBot;
import org.bonitasoft.studio.swtbot.framework.projectExplorer.ProjectExplorerBot;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

public class BDMProjectExplorerBot extends ProjectExplorerBot {

    public BDMProjectExplorerBot(SWTGefBot bot) {
        super(bot);
    }

    public DefineBdmWizardBot openBdm() {
        clickOnContextualMenu(getBdmTreeItem(), "Open");
        return new DefineBdmWizardBot(bot, org.bonitasoft.studio.businessobject.i18n.Messages.manageBusinessDataModelTitle);
    }

    public void deleteBdm() {
        clickOnContextualMenu(getBdmTreeItem(), "Delete");
    }

    public void deployBdm() {
        clickOnContextualMenu(getBdmTreeItem(), "Deploy");
    }

    public SWTBotTreeItem getBdmFolderTreeItem() {
        return getTreeItem(getProjectTreeItem(), "Business Data Model");
    }

    public SWTBotTreeItem getBdmTreeItem() {
        return getTreeItem(getBdmFolderTreeItem(), "bom.xml");
    }

}
