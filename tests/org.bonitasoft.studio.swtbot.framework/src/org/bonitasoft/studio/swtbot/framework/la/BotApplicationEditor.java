/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.swtbot.framework.la;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.ui.forms.widgets.Section;

public class BotApplicationEditor extends BotBase {

    private final SWTBotEditor editor;
    private final SWTGefBot bot;
    private final List<String> knownSections;

    public BotApplicationEditor(SWTGefBot bot, SWTBotEditor editor) {
        super(bot);
        this.editor = editor;
        this.bot = bot;
        knownSections = new ArrayList<>();
        knownSections.add(Messages.overview);
        knownSections.add(Messages.navigation);
        knownSections.add(Messages.orphanPage);
        knownSections.add(Messages.lookNFeel);
    }

    public void close() {
        editor.close();
    }

    public BotApplicationEditor save() {
        editor.save();
        return this;
    }

    /**
     * set the expanded state of this descriptor at true the the expanded state of others descriptors at false.
     */
    public BotApplicationEditor selectDescriptor(String token) {
        bot.getDisplay().syncExec(() -> {
            final List<? extends Section> sections = bot.widgets(WidgetMatcherFactory.widgetOfType(Section.class));
            sections.stream()
                    .filter(section -> !knownSections.contains(section.getText()))
                    .forEach(section -> section.setExpanded(Objects.equals(section.getText(), token)));
        });
        return this;
    }

    public String getTitle() {
        return editor.getTitle();
    }

    /**
     * save before deploy, else a pop up may appear
     */
    public BotApplicationEditor deploy() {
        editor.save();
        bot.toolbarButtonWithId("org.bonitasoft.studio.la.ui.editor.deploy").click();
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.application.i18n.Messages.selectArtifactToDeployTitle));
        SWTBotShell activeShell = bot.activeShell();
        bot.button(org.bonitasoft.studio.application.i18n.Messages.deploy).click();
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.application.i18n.Messages.deployStatus));
        activeShell = bot.activeShell();
        bot.button(IDialogConstants.CLOSE_LABEL).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
        return this;
    }

    public void delete() {
        bot.toolbarButtonWithId("org.bonitasoft.studio.la.ui.editor.delete").click();
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.ui.i18n.Messages.deleteConfirmation));
        bot.button(IDialogConstants.YES_LABEL).click();
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.ui.i18n.Messages.deleteDoneTitle));
        bot.shell(org.bonitasoft.studio.ui.i18n.Messages.deleteDoneTitle).activate();
        bot.button(IDialogConstants.OK_LABEL).click();
    }

    public NewApplicationDescriptorWizardBot add() {
        bot.toolbarButtonWithId("org.bonitasoft.studio.la.ui.editor.addDescriptor").click();
        return new NewApplicationDescriptorWizardBot(bot, Messages.addApplicationDescriptorTitle);
    }

    public BotApplicationEditor rename(String newName) {
        bot.toolbarButtonWithId("org.bonitasoft.studio.la.ui.editor.rename").click();
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.ui.i18n.Messages.rename));
        bot.textWithLabel(org.bonitasoft.studio.ui.i18n.Messages.renameFile).setText(newName);
        bot.button(IDialogConstants.OK_LABEL).click();
        return this;
    }

    public BotApplicationEditor setToken(String token) {
        bot.toolbarButtonWithId("org.bonitasoft.studio.ui.widget.textWidget.editButton").click();
        bot.textWithLabel(Messages.applicationToken).setText(token);
        bot.toolbarButtonWithId("org.bonitasoft.studio.ui.widget.textWidget.validateEdit").click();
        return this;
    }

    public BotApplicationEditor setTokenAndRedeploy(String token) {
        setToken(token);
        bot.waitUntil(Conditions.shellIsActive(Messages.redeployAfterRenameTitle));
        bot.button(IDialogConstants.YES_LABEL).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.redeployDoneTitle));
        bot.button(IDialogConstants.OK_LABEL).click();
        return this;
    }

    public BotApplicationEditor setDisplayName(String displayName) {
        bot.textWithLabel(Messages.displayName).setText(displayName);
        return this;
    }

    public BotApplicationEditor setVersion(String version) {
        bot.textWithLabel(Messages.version).setText(version);
        return this;
    }

    public BotApplicationEditor setProfile(String profile) {
        bot.textWithLabel(Messages.profile).setText(profile);
        return this;
    }

    public BotApplicationEditor setDescription(String description) {
        bot.textWithLabel(Messages.description).setText(description);
        return this;
    }

    public BotApplicationEditor setLayout(String layout) {
        bot.textWithLabel(Messages.layout).setText(layout);
        return this;
    }

    public BotApplicationEditor setTheme(String theme) {
        bot.textWithLabel(Messages.theme).setText(theme);
        return this;
    }

    public BotApplicationEditor setHomePageToken(String token) {
        bot.textWithLabel(Messages.homePageToken).setText(token);
        return this;
    }

    public SWTBotTree getNavigationTree() {
        return bot.treeWithId("org.bonitasoft.studio.la.ex.ui.editor.menuTreeViewer");
    }

    public BotApplicationEditor addOnePageMenu(String menuName) {
        bot.button(Messages.addOnePageMenu).click();
        bot.textWithId(SWTBOT_ID_APPLICATION_MENU_COLUMN_EDITOR).typeText(menuName).pressShortcut(Keystrokes.CR);
        return this;
    }

    public BotApplicationEditor addMultiPageMenu(String topMenuName, String subMenuName) {
        bot.button(Messages.addMultiPageMenu).click();
        bot.textWithId(SWTBOT_ID_APPLICATION_MENU_COLUMN_EDITOR).typeText(subMenuName).pressShortcut(Keystrokes.CR);
        return setMenuName(Messages.defaultMenuLabel, topMenuName);
    }

    /**
     * don't use this method for sub menu, it doesn't work
     */
    public BotApplicationEditor setMenuName(String oldName, String newName) {
        getNavigationTree().getTreeItem(oldName).click(0);
        SWTBotText menuCellEditor = bot.textWithId(SWTBOT_ID_APPLICATION_MENU_COLUMN_EDITOR);
        menuCellEditor.typeText(newName);
        menuCellEditor.pressShortcut(Keystrokes.CR);
        return this;
    }

    public BotApplicationEditor setSubMenuName(String parentName, String oldName, String newName) {
        getNavigationTree().getTreeItem(parentName).getNode(oldName).click();
        SWTBotText menuCellEditor = bot.textWithId(SWTBOT_ID_APPLICATION_MENU_COLUMN_EDITOR);
        menuCellEditor.typeText(newName);
        menuCellEditor.pressShortcut(Keystrokes.CR);
        return this;
    }

    public BotApplicationEditor setMenuPageName(String menu, String pageName) {
        getNavigationTree().getTreeItem(menu).click(1);
        SWTBotText pageCellEditor = bot.textWithId(SWTBOT_ID_APPLICATION_PAGE_COLUMN_EDITOR);
        pageCellEditor.typeText(pageName);
        pageCellEditor.pressShortcut(Keystrokes.CR);
        return this;
    }

    public BotApplicationEditor setSubMenuPageName(String parentName, String subMenuName, String pageName) {
        getNavigationTree().getTreeItem(parentName).getNode(subMenuName).click(1);
        SWTBotText pageCellEditor = bot.textWithId(SWTBOT_ID_APPLICATION_PAGE_COLUMN_EDITOR);
        pageCellEditor.typeText(pageName);
        pageCellEditor.pressShortcut(Keystrokes.CR);
        return this;
    }

    public BotApplicationEditor setMenuPageToken(String menu, String pageToken) {
        getNavigationTree().getTreeItem(menu).click(2);
        SWTBotText tokenCellEditor = bot.textWithId(SWTBOT_ID_APPLICATION_TOKEN_COLUMN_EDITOR);
        tokenCellEditor.typeText(pageToken);
        tokenCellEditor.pressShortcut(Keystrokes.CR);
        return this;
    }

    public BotApplicationEditor setSubMenuPageToken(String parentName, String subMenuName, String pageToken) {
        getNavigationTree().getTreeItem(parentName).getNode(subMenuName).click(2);
        SWTBotText tokenCellEditor = bot.textWithId(SWTBOT_ID_APPLICATION_TOKEN_COLUMN_EDITOR);
        tokenCellEditor.typeText(pageToken);
        tokenCellEditor.pressShortcut(Keystrokes.CR);
        return this;
    }

    public BotApplicationEditor addSubMenu(String parentName, String subMenuName) {
        getNavigationTree().select(parentName);
        bot.button(Messages.addChild).click();
        getNavigationTree().getTreeItem(parentName).getNode("My menu").click(0);
        bot.waitUntil(Conditions.widgetIsEnabled(bot.textWithId(SWTBOT_ID_APPLICATION_MENU_COLUMN_EDITOR)));
        SWTBotText menuCellEditor = bot.textWithId(SWTBOT_ID_APPLICATION_MENU_COLUMN_EDITOR);
        menuCellEditor.typeText(subMenuName);
        menuCellEditor.pressShortcut(Keystrokes.CR);
        return this;
    }

    public BotApplicationEditor removeMenu(String menu) {
        getNavigationTree().select(menu);
        bot.buttonWithId("org.bonitasoft.studio.la.ex.ui.editor.menuRemove").click();
        return this;
    }

    public BotApplicationEditor removeSubMenu(String parentName, String subMenu) {
        getNavigationTree().getTreeItem(parentName).select(subMenu);
        bot.buttonWithId("org.bonitasoft.studio.la.ex.ui.editor.menuRemove").click();
        return this;
    }

    public BotApplicationEditor upMenu(String menu) {
        getNavigationTree().select(menu);
        bot.button(Messages.up).click();
        return this;
    }

    public BotApplicationEditor downMenu(String menu) {
        getNavigationTree().select(menu);
        bot.button(Messages.down).click();
        return this;
    }

    public BotApplicationEditor upSubMenu(String parentName, String subMenu) {
        getNavigationTree().getTreeItem(parentName).select(subMenu);
        bot.button(Messages.up).click();
        return this;
    }

    public BotApplicationEditor downSubMenu(String parentName, String subMenu) {
        getNavigationTree().getTreeItem(parentName).select(subMenu);
        bot.button(Messages.down).click();
        return this;
    }

    public SWTBotTree getOrphanTree() {
        return bot.treeWithId("org.bonitasoft.studio.la.ex.ui.editor.orphanPagesTreeViewer");
    }

    public BotApplicationEditor addOrphanPage(String pageName) {
        bot.button(Messages.addOrphanPage).click();
        bot.textWithId(SWTBOT_ID_APPLICATION_PAGE_COLUMN_EDITOR).typeText(pageName).pressShortcut(Keystrokes.CR);
        return this;
    }

    public BotApplicationEditor setOrphanPageName(String oldName, String newName) {
        getOrphanTree().getTreeItem(oldName).click();
        bot.textWithId(SWTBOT_ID_APPLICATION_PAGE_COLUMN_EDITOR).typeText(newName).pressShortcut(Keystrokes.CR);
        return this;
    }

    public BotApplicationEditor setOrphanPageToken(String pageName, String pageToken) {
        getOrphanTree().getTreeItem(pageName).click(1);
        bot.textWithId(SWTBOT_ID_APPLICATION_TOKEN_COLUMN_EDITOR).typeText(pageToken).pressShortcut(Keystrokes.CR);
        return this;
    }

    public BotApplicationEditor removeOrphanPage(String pageName) {
        getOrphanTree().select(pageName);
        bot.buttonWithId("org.bonitasoft.studio.la.ex.ui.editor.orphanRemove").click();
        return this;
    }
}
