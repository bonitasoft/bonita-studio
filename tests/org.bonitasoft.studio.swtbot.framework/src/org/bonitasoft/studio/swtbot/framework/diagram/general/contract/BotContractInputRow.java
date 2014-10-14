/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.swtbot.framework.diagram.general.contract;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;


/**
 * @author Romain Bioteau
 *
 */
public class BotContractInputRow extends BotBase {

    private final int row;

    private TreeItem treeItem;

    private static final int NAME_COLUMN = 0;
    private static final int TYPE_COLUMN = 1;
    private static final int MULTIPLE_COLUMN = 2;
    private static final int MANDATORY_COLUMN = 3;
    //    private static final int MAPPING_COLUMN = 4;
    //private static final int CONSTRAINT_COLUMN = 5;
    private static final int DESCRIPTION_COLUMN = 4;

    public BotContractInputRow(final SWTGefBot bot, final int row) {
        super(bot);
        this.row = row;
    }

    public BotContractInputRow setName(final String name){
        final SWTBotTreeItem item = getTreeItem(bot.treeWithId(SWTBotConstants.SWTBOT_ID_CONTRACT_INPUT_TREE), row);
        item.setFocus();
        item.click(NAME_COLUMN);
        final SWTBotText botText = bot.textWithId(SWTBotConstants.SWTBOT_ID_INPUT_NAME_TEXTEDITOR);
        botText.setText(name);
        SWTBotTestUtil.pressEnter();
        return this;
    }

    public DefaultCondition cellEditorIsInactive(final AbstractSWTBot<? extends Widget> botText) {
        return new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return !botText.isActive();
            }

            @Override
            public String getFailureMessage() {
                return null;
            }
        };
    }

    private SWTBotTreeItem getTreeItem(final SWTBotTree swtBotTree, final int row) {
        Display.getDefault().syncExec(new Runnable() {

            @Override
            public void run() {
                final Tree tree = swtBotTree.widget;
                treeItem = tree.getItem(row);
            }
        });
        return new SWTBotTreeItem(treeItem);

    }

    public BotContractInputRow setDescription(final String description) {
        final SWTBotTreeItem item = getTreeItem(bot.treeWithId(SWTBotConstants.SWTBOT_ID_CONTRACT_INPUT_TREE), row);
        item.setFocus();
        item.click(DESCRIPTION_COLUMN);
        final SWTBotText botText = bot.text(1);
        botText.setText(description);
        SWTBotTestUtil.pressEnter();
        return this;
    }

    protected DefaultCondition rowIsInactive(final SWTBotTreeItem item) {
        return new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return !item.isActive();
            }

            @Override
            public String getFailureMessage() {
                return "Row " + row + " is still active";
            }
        };
    }

    protected DefaultCondition rowIsActive(final SWTBotTreeItem item) {
        return new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return item.isActive();
            }

            @Override
            public String getFailureMessage() {
                return "Row " + row + " is not active";
            }
        };
    }

    public BotContractInputRow setType(final String type) {
        final SWTBotTreeItem item = getTreeItem(bot.treeWithId(SWTBotConstants.SWTBOT_ID_CONTRACT_INPUT_TREE), row);
        item.setFocus();
        item.click(TYPE_COLUMN);
        final SWTBotCCombo comboBox = bot.ccomboBox();
        comboBox.setSelection(type);
        SWTBotTestUtil.pressEnter();
        return this;
    }

    public BotContractInputRow clickMultiple() {
        final SWTBotTreeItem item = getTreeItem(bot.treeWithId(SWTBotConstants.SWTBOT_ID_CONTRACT_INPUT_TREE), row);
        item.setFocus();
        item.click(MULTIPLE_COLUMN);
        return this;
    }

    public BotContractInputRow clickMandatory() {
        final SWTBotTreeItem item = getTreeItem(bot.treeWithId(SWTBotConstants.SWTBOT_ID_CONTRACT_INPUT_TREE), row);
        item.setFocus();
        item.click(MANDATORY_COLUMN);
        return this;
    }

    public BotContractInputRow select() {
        final SWTBotTreeItem item = getTreeItem(bot.treeWithId(SWTBotConstants.SWTBOT_ID_CONTRACT_INPUT_TREE), row);
        item.select();
        return this;
    }

}
