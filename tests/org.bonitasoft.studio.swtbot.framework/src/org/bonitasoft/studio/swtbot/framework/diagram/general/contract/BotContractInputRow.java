/**
 * Copyright (C) 2014-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.swtbot.framework.diagram.general.contract;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotList;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * @author Romain Bioteau
 */
public class BotContractInputRow extends BotBase {

    private TreeItem treeItem;
    private final SWTBotTreeItem swtBotTreeItem;

    private static final int NAME_COLUMN = 0;
    private static final int TYPE_COLUMN = 1;
    private static final int MULTIPLE_COLUMN = 2;
    private static final int DESCRIPTION_COLUMN = 3;

    public BotContractInputRow(final SWTGefBot bot, final int row) {
        super(bot);
        swtBotTreeItem = getTreeItem(inputTree(bot), row);
    }

    public BotContractInputRow(final SWTGefBot bot, final SWTBotTreeItem item) {
        super(bot);
        swtBotTreeItem = item;
    }

    private SWTBotTree inputTree(final SWTGefBot bot) {
        return bot.treeWithId(SWTBotConstants.SWTBOT_ID_CONTRACT_INPUT_TREE);
    }

    public BotContractInputRow setName(final String name) {
        waitForInputTree();
        swtBotTreeItem.setFocus();
        swtBotTreeItem.click(NAME_COLUMN);
        bot.textWithId(SWTBotConstants.SWTBOT_ID_INPUT_NAME_TEXTEDITOR)
        .typeText(name)
        .pressShortcut(Keystrokes.CR);
        return this;
    }

    private SWTBotTreeItem getTreeItem(final SWTBotTree swtBotTree, final int row) {
        Display.getDefault().syncExec(() -> treeItem = swtBotTree.widget.getItem(row));
        return new SWTBotTreeItem(treeItem);
    }

    public BotContractInputRow setDescription(final String description) {
        waitForInputTree();
        swtBotTreeItem.setFocus();
        swtBotTreeItem.click(DESCRIPTION_COLUMN);
        bot.textWithId(SWTBotConstants.SWTBOT_ID_INPUT_DESCRIPTION_TEXTEDITOR)
        .typeText(description)
        .pressShortcut(Keystrokes.CR);
        return this;
    }

    public BotContractInputRow setType(final String type) {
        waitForInputTree();
        swtBotTreeItem.setFocus();
        swtBotTreeItem.click(TYPE_COLUMN);
        final SWTBotList comboBox = bot.list();
        comboBox.select(type);
        comboBox.pressShortcut(Keystrokes.CR);
        return this;
    }

    public BotContractInputRow clickMultiple() {
        waitForInputTree();
        swtBotTreeItem.setFocus();
        swtBotTreeItem.click(MULTIPLE_COLUMN);
        return this;
    }

    private void waitForInputTree() {
        bot.waitUntil(Conditions.widgetIsEnabled(inputTree(bot)));
    }

    public BotContractInputRow select() {
        swtBotTreeItem.select();
        return this;
    }

    public BotContractInputRow getChildRow(final int childIndex) {
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return swtBotTreeItem.expand().getItems().length > childIndex;
            }

            @Override
            public String getFailureMessage() {
                return "Child at index " + childIndex + " doesn't exist";
            }
        });
        final SWTBotTreeItem[] items = swtBotTreeItem.expand().getItems();
        final SWTBotTreeItem childItem = items[childIndex];
        return new BotContractInputRow(bot, childItem);
    }

}
