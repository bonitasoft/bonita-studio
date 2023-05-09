/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.swtbot.framework.organization;

import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.editor.control.group.GroupList;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.bonitasoft.studio.swtbot.framework.ConditionBuilder;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotMultiPageEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

public class BotOrganizationGroupEditor extends BotBase {

    private BotOrganizationEditor botOrganizationEditor;
    private SWTBotMultiPageEditor editor;

    public BotOrganizationGroupEditor(SWTGefBot bot, SWTBotMultiPageEditor editor,
            BotOrganizationEditor botOrganizationEditor) {
        super(bot);
        this.editor = editor;
        this.botOrganizationEditor = botOrganizationEditor;
    }

    // Only root parents are supported in this test framework
    public BotOrganizationGroupEditor moveSubGroup(String newParent, String groupDisplayName, String... parents) {
        SWTBotTreeItem subGroupItem = getSubGroupSWTBotTreeItem(groupDisplayName, parents);
        subGroupItem.dragAndDrop(getGroupSWTBotTreeItem(newParent));
        return this;
    }

    public BotOrganizationGroupEditor addSubGroup(String groupName, String groupDisplayName, String... parents) {
        bot.toolbarButtonWithId(GroupList.ADD_SUB_GROUP_BUTTON_ID).click();
        selectSubGroup(Messages.defaultGroupName + "1", parents);
        setDisplayName(groupDisplayName);
        selectSubGroup(groupDisplayName, parents);
        setName(groupName);
        return this;
    }

    public BotOrganizationGroupEditor addGroup(String groupName, String groupDisplayName) {
        bot.toolbarButtonWithId(GroupList.ADD_GROUP_BUTTON_ID).click();
        selectGroup(Messages.defaultGroupName + "1");
        setDisplayName(groupDisplayName);
        selectGroup(groupDisplayName);
        setName(groupName);
        return this;
    }

    public BotOrganizationGroupEditor removeGroup(String groupDisplayName) {
        selectGroup(groupDisplayName);
        bot.toolbarButtonWithId(GroupList.REMOVE_BUTTON_ID).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.deleteGroupTitle));
        SWTBotShell activeShell = bot.activeShell();
        bot.button(IDialogConstants.YES_LABEL).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
        return this;
    }

    public BotOrganizationGroupEditor setDescription(String description) {
        bot.textWithLabel(Messages.description).setText(description);
        return this;
    }

    public BotOrganizationGroupEditor setDisplayName(String newDisplayName) {
        bot.textWithLabel(Messages.displayName).setText(newDisplayName);
        bot.sleep(500); // Need some times to refresh the list viewer, to take into account the update
        return this;
    }

    public BotOrganizationGroupEditor setName(String newName) {
        bot.toolbarButtonWithId("org.bonitasoft.studio.ui.widget.textWidget.editButton").click();
        bot.textWithLabel(Messages.name).setText(newName);
        bot.toolbarButtonWithId("org.bonitasoft.studio.ui.widget.textWidget.validateEdit").click();
        return this;
    }

    public BotOrganizationGroupEditor selectSubGroup(String displayName, String... parents) {
        getSubGroupSWTBotTreeItem(displayName, parents).select();
        return this;
    }

    private SWTBotTreeItem getSubGroupSWTBotTreeItem(String displayName, String... parents) {
        bot.waitUntil(treeItemAvailable(getGroupTree(), parents[0]));
        SWTBotTreeItem parentTreeItem = getGroupTree().getTreeItem(parents[0]);
        parentTreeItem.expand();
        parentTreeItem.select();

        for (int i = 1; i < parents.length; i++) {
            bot.waitUntil(nodeAvailable(parentTreeItem, parents[i]));
            parentTreeItem = parentTreeItem.getNode(parents[i]);
            parentTreeItem.expand();
            parentTreeItem.select();
        }

        bot.waitUntil(nodeAvailable(parentTreeItem, displayName));
        return parentTreeItem.getNode(displayName);
    }

    public BotOrganizationGroupEditor selectGroup(String displayName) {
        getGroupSWTBotTreeItem(displayName).select();
        return this;
    }

    private SWTBotTreeItem getGroupSWTBotTreeItem(String displayName) {
        bot.waitUntil(treeItemAvailable(getGroupTree(), displayName));
        return getGroupTree().getTreeItem(displayName);
    }

    private SWTBotTree getGroupTree() {
        return bot.treeWithId(GroupList.GROUP_LIST_VIEWER_ID);
    }

    public ICondition nodeAvailable(SWTBotTreeItem item, String node) {
        return new ConditionBuilder()
                .withTest(() -> {
                    try {
                        item.getNode(node);
                        return true;
                    } catch (WidgetNotFoundException e) {
                        return false;
                    }
                })
                .withFailureMessage(() -> String.format("The node %s of '%s' isn't available", node, item))
                .create();
    }

    public ICondition treeItemAvailable(SWTBotTree tree, String item) {
        return new ConditionBuilder()
                .withTest(() -> {
                    try {
                        tree.getTreeItem(item);
                        return true;
                    } catch (WidgetNotFoundException e) {
                        return false;
                    }
                })
                .withFailureMessage(() -> String.format("The node %s isn't available", item))
                .create();
    }

    public BotOrganizationGroupEditor save() {
        editor.save();
        return this;
    }

    public void close() {
        editor.close();
    }

}
