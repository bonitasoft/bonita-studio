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
import org.bonitasoft.studio.identity.organization.editor.control.role.RoleList;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotMultiPageEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;

public class BotOrganizationRoleEditor extends BotBase {

    private BotOrganizationEditor botOrganizationEditor;
    private SWTBotMultiPageEditor editor;

    public BotOrganizationRoleEditor(SWTGefBot bot, SWTBotMultiPageEditor editor,
            BotOrganizationEditor botOrganizationEditor) {
        super(bot);
        this.editor = editor;
        this.botOrganizationEditor = botOrganizationEditor;
    }

    public BotOrganizationRoleEditor addRole(String roleName, String roleDisplayName) {
        bot.toolbarButtonWithId(RoleList.ADD_ROLE_BUTTON_ID).click();
        selectRole(Messages.defaultRoleName + "1");
        setDisplayName(roleDisplayName);
        selectRole(roleDisplayName);
        setName(roleName);
        return this;
    }

    public BotOrganizationRoleEditor removeRole(String RoleDisplayName) {
        selectRole(RoleDisplayName);
        bot.toolbarButtonWithId(RoleList.REMOVE_BUTTON_ID).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.deleteRoleTitle));
        SWTBotShell activeShell = bot.activeShell();
        bot.button(IDialogConstants.YES_LABEL).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
        return this;
    }

    public BotOrganizationRoleEditor setName(String newName) {
        bot.toolbarButtonWithId("org.bonitasoft.studio.ui.widget.textWidget.editButton").click();
        bot.textWithLabel(Messages.name).setText(newName);
        bot.toolbarButtonWithId("org.bonitasoft.studio.ui.widget.textWidget.validateEdit").click();
        return this;
    }

    public BotOrganizationRoleEditor setDisplayName(String newDisplayName) {
        bot.textWithLabel(Messages.displayName).setText(newDisplayName);
        bot.sleep(500); // Need some times to refresh the list viewer, to take into account the update
        return this;
    }

    public BotOrganizationRoleEditor setDescription(String description) {
        bot.textWithLabel(Messages.description).setText(description);
        return this;
    }

    public BotOrganizationRoleEditor selectRole(String displayName) {
        getRoleTable().getTableItem(displayName).select();
        return this;
    }

    private SWTBotTable getRoleTable() {
        return bot.tableWithId(RoleList.ROLE_LIST_VIEWER_ID);
    }

    public BotOrganizationRoleEditor save() {
        editor.save();
        return this;
    }

    public void close() {
        editor.close();
    }
}
