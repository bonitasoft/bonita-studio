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

import static org.eclipse.swtbot.swt.finder.matchers.WithId.withId;

import java.util.Objects;
import java.util.stream.Stream;

import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.editor.control.user.InformationSection;
import org.bonitasoft.studio.identity.organization.editor.control.user.ManageCustomInformationSection;
import org.bonitasoft.studio.identity.organization.editor.control.user.MembershipSection;
import org.bonitasoft.studio.identity.organization.editor.control.user.UserList;
import org.bonitasoft.studio.identity.organization.editor.editingsupport.MembershipGroupEditingSupport;
import org.bonitasoft.studio.identity.organization.editor.editingsupport.MembershipRoleEditingSupport;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.bonitasoft.studio.swtbot.framework.ConditionBuilder;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotMultiPageEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.ui.forms.widgets.Section;

public class BotOrganizationUserEditor extends BotBase {

    private SWTBotMultiPageEditor editor;

    public BotOrganizationUserEditor(SWTGefBot bot, SWTBotMultiPageEditor editor) {
        super(bot);
        this.editor = editor;
    }

    // Make sure that the correct user is selected and the correct data tab too
    public BotOrganizationUserEditor setLocationInformations(String buildingInfo, String personalRoom,
            String address, String city, String state, String zipCode, String country) {
        bot.textWithLabel(Messages.buildingLabel).setText(buildingInfo);
        bot.textWithLabel(Messages.roomLabel).setText(personalRoom);
        bot.textWithLabel(Messages.addressLabel).setText(address);
        bot.textWithLabel(Messages.cityLabel).setText(city);
        bot.textWithLabel(Messages.stateLabel).setText(state);
        bot.textWithLabel(Messages.zipCodeLabel).setText(zipCode);
        bot.textWithLabel(Messages.countryLabel).setText(country);
        return this;
    }

    // Make sure that the correct user is selected and the correct data tab too
    public BotOrganizationUserEditor setContactInformations(String email,
            String phone, String mobile, String fax, String webSite) {
        bot.textWithLabel(Messages.emailLabel).setText(email);
        bot.textWithLabel(Messages.phoneLabel).setText(phone);
        bot.textWithLabel(Messages.mobileLabel).setText(mobile);
        bot.textWithLabel(Messages.faxLabel).setText(fax);
        bot.textWithLabel(Messages.websiteLabel).setText(webSite);
        return this;
    }

    // Make sure that the correct user is selected and the correct data tab too
    public BotOrganizationUserEditor setCustomInformation(String name, String value) {
        getCustomInfoUserTable().getTableItem(name).click(1);
        bot.waitUntil(Conditions.waitForWidget(withId(InformationSection.CUSTOM_INFO_VALUE_TEXT_ID)));
        SWTBotText botText = bot.textWithId(InformationSection.CUSTOM_INFO_VALUE_TEXT_ID);
        botText.setText(value);
        botText.pressShortcut(Keystrokes.CR);
        return this;
    }

    public BotOrganizationUserEditor selectProfessionalDataTab(String userDisplayName) {
        selectUser(userDisplayName);
        if (useCTabItem()) {
            bot.cTabItem(Messages.professionalData).activate();
        } else {
            bot.tabItem(Messages.professionalData).activate();
        }
        return this;
    }

    public BotOrganizationUserEditor selectPersonnalDataTab(String userDisplayName) {
        selectUser(userDisplayName);
        if (useCTabItem()) {
            bot.cTabItem(Messages.personalData).activate();
        } else {
            bot.tabItem(Messages.personalData).activate();
        }
        return this;
    }

    public BotOrganizationUserEditor selectCustomDataTab(String userDisplayName) {
        selectUser(userDisplayName);
        if (useCTabItem()) {
            bot.cTabItem(Messages.other).activate();
        } else {
            bot.tabItem(Messages.other).activate();
        }
        return this;
    }

    public BotOrganizationUserEditor addMembership(String userDisplayName, String groupPath, String role) {
        selectUser(userDisplayName);
        int newIndex = getMembershipTable().rowCount();
        bot.toolbarButtonWithId(MembershipSection.ADD_MEMBERSHIP_BUTTON_ID).click();
        setMembershipGroup(newIndex, groupPath);
        bot.sleep(500);
        setMembershipRole(newIndex, role);
        return this;
    }

    public BotOrganizationUserEditor removeMembership(String userDisplayName, int index) {
        selectUser(userDisplayName);
        selectMembershipRow(index);
        bot.toolbarButtonWithId(MembershipSection.REMOVE_MEMBERSHIP_BUTTON_ID).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.deleteMembershipTitle));
        SWTBotShell activeShell = bot.activeShell();
        bot.button(IDialogConstants.YES_LABEL).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
        return this;
    }

    public BotOrganizationUserEditor setMembershipGroup(int index, String groupPath) {
        SWTBotShell activeShell = bot.activeShell();
        getMembershipTable().click(index, 0);
        SWTBot activeBot = activeShell.bot();
        SWTBotCCombo combo = activeBot.ccomboBoxWithId(MembershipGroupEditingSupport.GROUP_COMBO_EDITOR_ID);
        activeBot.waitUntil(new ConditionBuilder()
                .withTest(() -> Stream.of(combo.items()).anyMatch(groupPath::equals))
                .withFailureMessage(() -> String.format("Group '%s' not found in combo", groupPath))
                .create());
        combo.setSelection(groupPath);
        combo.pressShortcut(Keystrokes.CR);
        return this;
    }

    public BotOrganizationUserEditor setMembershipRole(int index, String role) {
        SWTBotShell activeShell = bot.activeShell();
        getMembershipTable().click(index, 1);
        SWTBot activeBot = activeShell.bot();
        SWTBotCCombo combo = activeBot.ccomboBoxWithId(MembershipRoleEditingSupport.ROLE_COMBO_EDITOR_ID);
        activeBot.waitUntil(new ConditionBuilder()
                .withTest(() -> Stream.of(combo.items()).anyMatch(role::equals))
                .withFailureMessage(() -> String.format("Role '%s' not found in combo", role))
                .create());
        combo.setSelection(role);
        combo.pressShortcut(Keystrokes.CR);
        return this;
    }

    private void selectMembershipRow(int row) {
        SWTBotText botText = bot.textWithLabel(Messages.password);
        SWTBotTable membershipTable = getMembershipTable();
        membershipTable.select(row);
        //Close the proposal popup
        bot.sleep(200);
        botText.setFocus();
    }

    public BotOrganizationUserEditor addUser(String username, String firstName, String lastName) {
        bot.toolbarButtonWithId(UserList.ADD_USER_BUTTON_ID).click();
        selFirstName(Messages.defaultUserName + "1", firstName);
        setLastName(Messages.defaultUserName + "1", lastName);
        setUsername(toUserDisplayName(firstName, lastName), username);
        return this;
    }

    public BotOrganizationUserEditor removeUser(String userDisplayName) {
        selectUser(userDisplayName);
        bot.toolbarButtonWithId(UserList.REMOVE_BUTTON_ID).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.deleteUserTitle));
        SWTBotShell activeShell = bot.activeShell();
        bot.button(IDialogConstants.YES_LABEL).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
        return this;
    }

    public BotOrganizationUserEditor setJobTitle(String userDisplayName, String jobTitle) {
        selectUser(userDisplayName);
        bot.textWithLabel(Messages.jobLabel).setText(jobTitle);
        return this;
    }

    public BotOrganizationUserEditor setTitle(String userDisplayName, String title) {
        selectUser(userDisplayName);
        bot.textWithLabel(Messages.userTitle).setText(title);
        return this;
    }

    private BotOrganizationUserEditor selFirstName(String userDisplayName, String firstName) {
        selectUser(userDisplayName);
        bot.textWithLabel(Messages.firstName).setText(firstName);
        bot.sleep(500);
        return this;
    }

    private BotOrganizationUserEditor setLastName(String userDisplayName, String lastName) {
        selectUser(userDisplayName);
        bot.textWithLabel(Messages.lastName).setText(lastName);
        bot.sleep(500);
        return this;
    }

    public BotOrganizationUserEditor setPassword(String userDisplayName, String password) {
        selectUser(userDisplayName);
        bot.textWithLabel(Messages.password).setText(password);
        return this;
    }

    public BotOrganizationUserEditor setUsername(String userDisplayName, String username) {
        selectUser(userDisplayName);
        bot.toolbarButtonWithId("org.bonitasoft.studio.ui.widget.textWidget.editButton").click();
        bot.textWithLabel(Messages.userName).setText(username);
        bot.toolbarButtonWithId("org.bonitasoft.studio.ui.widget.textWidget.validateEdit").click();
        return this;
    }

    public BotOrganizationUserEditor expandCustomInfoSection() {
        bot.getDisplay().syncExec(() -> bot.widgets(WidgetMatcherFactory.widgetOfType(Section.class)).stream()
                .filter(section -> Objects.equals(section.getText(), Messages.manageCustomInfo))
                .forEach(section -> section.setExpanded(true)));
        bot.waitUntil(new ConditionBuilder()
                .withTest(() -> {
                    try {
                        bot.tableWithId(ManageCustomInformationSection.CUSTOM_INFO_LIST_VIEWER_ID);
                        return true;
                    } catch (WidgetNotFoundException e) {
                        return false;
                    }
                })
                .withFailureMessage(() -> "Custom info section has not been expanded.")
                .create());
        return this;
    }

    public BotOrganizationUserEditor addCustomInformationDefinition(String name, String description) {
        bot.toolbarButtonWithId(ManageCustomInformationSection.ADD_CUSTOM_INFO_BUTTON_ID).click();
        renameCustomInformationDefinition(Messages.defaultCustomUserInformationName + "1", name);
        setCustomInformationDefinitionDescription(name, description);
        return this;
    }

    public BotOrganizationUserEditor removeCustomInformationDefinition(String name) {
        getCustomInfoDefinitionTable().getTableItem(name).select();
        bot.toolbarButtonWithId(ManageCustomInformationSection.REMOVE_CUSTOM_INFO_BUTTON_ID).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.otherInformationGroupRemoveDialogTitle));
        SWTBotShell activeShell = bot.activeShell();
        bot.button(IDialogConstants.YES_LABEL).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
        return this;
    }

    public BotOrganizationUserEditor renameCustomInformationDefinition(String oldName, String newName) {
        getCustomInfoDefinitionTable().getTableItem(oldName).click();
        SWTBotText botText = bot.textWithId(ManageCustomInformationSection.CUSTOM_INFO_DEFINITION_NAME_TEXTEDITOR);
        botText.setText(newName);
        botText.pressShortcut(Keystrokes.CR);
        return this;
    }

    public BotOrganizationUserEditor setCustomInformationDefinitionDescription(String name, String description) {
        getCustomInfoDefinitionTable().getTableItem(name).click(1);
        SWTBotText botText = bot.textWithId(ManageCustomInformationSection.CUSTOM_INFO_DEFINITION_DESCRIPTION_TEXTEDITOR);
        botText.setText(description);
        botText.pressShortcut(Keystrokes.CR);
        return this;
    }

    public BotOrganizationUserEditor selectUser(String userDisplayName) {
        getUserTable().getTableItem(userDisplayName).select();
        return this;
    }

    private SWTBotTable getUserTable() {
        return bot.tableWithId(UserList.USER_LIST_VIEWER_ID);
    }

    private SWTBotTable getCustomInfoDefinitionTable() {
        return bot.tableWithId(ManageCustomInformationSection.CUSTOM_INFO_LIST_VIEWER_ID);
    }

    private SWTBotTable getCustomInfoUserTable() {
        return bot.tableWithId(InformationSection.CUSTOM_INFO_LIST_VIEWER_ID);
    }

    private SWTBotTable getMembershipTable() {
        return bot.tableWithId(MembershipSection.MEMBERSHIP_LIST_VIEWER_ID);
    }

    public BotOrganizationUserEditor save() {
        editor.save();
        return this;
    }

    public void close() {
        editor.close();
    }

    public String toUserDisplayName(String firstName, String lastName) {
        return String.format("%s %s", firstName, lastName);
    }

    private boolean useCTabItem() {
        return Objects.equals(Platform.OS_MACOSX, Platform.getOS()) || Objects.equals(Platform.OS_WIN32, Platform.getOS());
    }

}
