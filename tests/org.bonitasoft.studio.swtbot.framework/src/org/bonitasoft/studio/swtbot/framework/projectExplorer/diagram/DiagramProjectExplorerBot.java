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
package org.bonitasoft.studio.swtbot.framework.projectExplorer.diagram;

import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.projectExplorer.ProjectExplorerBot;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

@SuppressWarnings("restriction")
public class DiagramProjectExplorerBot extends ProjectExplorerBot {

    public DiagramProjectExplorerBot(SWTGefBot bot) {
        super(bot);
    }

    @Override
    public BotProcessDiagramPerspective newDiagram() {
        clickOnContextualMenu(getDiagramFolderTreeItem(), "New");
        return new BotProcessDiagramPerspective(bot);
    }

    public BotProcessDiagramPerspective openDiagram(String diagramName, String version) {
        clickOnContextualMenu(getDiagramTreeItem(diagramName, version), "Open");
        return new BotProcessDiagramPerspective(bot);
    }

    /**
     * Duplicate a diagram. The behavior is to add '-copy' at the end of the original name.
     * WARNING: It doesn't work if the diagram to duplicate has several pools (pool Text have all the same id..)
     */
    public void duplicateDiagram(String diagramName, String version) {
        clickOnContextualMenu(getDiagramTreeItem(diagramName, version), "Duplicate...");
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.common.Messages.openNameAndVersionDialogTitle));
        SWTBotShell activeShell = bot.activeShell();
        activeShell.setFocus();
        bot.textWithId("org.bonitasoft.studio.common.diagram.dialog.name.text").setText(diagramName + "-copy");
        SWTBotText poolText = bot.textWithId("org.bonitasoft.studio.common.diagram.dialog.poolName.text");
        poolText.setText(poolText.getText() + "-copy");
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
    }

    public void renameDiagram(String oldDiagramName, String newDiagramName, String version, String newPoolName) {
        clickOnContextualMenu(getDiagramTreeItem(oldDiagramName, version), "Rename...");
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.common.Messages.openNameAndVersionDialogTitle));
        SWTBotShell activeShell = bot.activeShell();
        activeShell.setFocus();
        bot.textWithId("org.bonitasoft.studio.common.diagram.dialog.name.text").setText(newDiagramName);
        bot.textWithId("org.bonitasoft.studio.common.diagram.dialog.poolName.text").setText(newPoolName);
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
    }

    public void deleteDiagram(String diagramName, String version) {
        clickOnContextualMenu(getDiagramTreeItem(diagramName, version), "Delete");
    }

    public void deploy(String diagramName, String version) {
        clickOnContextualMenu(getDiagramTreeItem(diagramName, version), "Deploy");
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.engine.i18n.Messages.deployDoneTitle));
        SWTBotShell activeShell = bot.activeShell();
        activeShell.setFocus();
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
    }

    private SWTBotTreeItem getDiagramFolderTreeItem() {
        return getTreeItem(getProjectTreeItem(), "Diagrams");
    }

    public SWTBotTreeItem getDiagramTreeItem(String diagramName, String version) {
        SWTBotTreeItem diagramFolderTreeItem = getDiagramFolderTreeItem();
        return getTreeItem(diagramFolderTreeItem, getDiagramFilename(diagramName, version));
    }

    private String getDiagramFilename(String diagram, String version) {
        return String.format("%s-%s.proc", diagram, version);
    }

}
