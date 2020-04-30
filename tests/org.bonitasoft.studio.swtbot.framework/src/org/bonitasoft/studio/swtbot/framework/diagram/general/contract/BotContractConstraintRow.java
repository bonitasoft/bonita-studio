/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTableItem;

/**
 * @author Romain Bioteau
 */
public class BotContractConstraintRow extends BotBase {

    private TableItem tableItem;
    private final SWTBotTableItem swtBotTableItem;

    private static final int NAME_COLUMN = 0;
    private static final int EXPRESSION_COLUMN = 1;
    private static final int ERROR_MESSAGE_COLUMN = 2;

    private final SWTBotTable constraintTable;
    private final int row;

    public BotContractConstraintRow(final SWTGefBot bot, final int row) {
        super(bot);
        this.row = row;
        constraintTable = bot.tableWithId(SWTBotConstants.SWTBOT_ID_CONTRACT_CONSTRAINT_TABLE);
        swtBotTableItem = getTableItem(bot.tableWithId(SWTBotConstants.SWTBOT_ID_CONTRACT_CONSTRAINT_TABLE), row);
    }

    public BotContractConstraintRow setName(final String name) {
        constraintTable.setFocus();
        constraintTable.click(row, NAME_COLUMN);
        bot.textWithId(SWTBotConstants.SWTBOT_ID_CONSTRAINT_NAME_TEXTEDITOR)
	        .typeText(name)
	        .pressShortcut(Keystrokes.CR);
        bot.waitUntil(textApplied(name, NAME_COLUMN));
        return this;
    }

    public BotContractConstraintRow setExpression(final String expression) {
        constraintTable.setFocus();
        constraintTable.click(row, EXPRESSION_COLUMN);
        bot.button("...").click();
        bot.waitUntilWidgetAppears(Conditions.widgetIsEnabled(bot.styledText()));
        bot.styledText().setText(expression);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        return this;
    }

    private SWTBotTableItem getTableItem(final SWTBotTable swtBotTable, final int row) {
        Display.getDefault().syncExec(new Runnable() {

            @Override
            public void run() {
                final Table table = swtBotTable.widget;
                tableItem = table.getItem(row);
            }
        });
        return new SWTBotTableItem(tableItem);

    }

    public BotContractConstraintRow setErrorMessages(final String errorMessage) {
        constraintTable.setFocus();
        constraintTable.click(row, ERROR_MESSAGE_COLUMN);
        bot.textWithId(SWTBotConstants.SWTBOT_ID_CONSTRAINT_ERROR_MESSAGE_TEXTEDITOR) 
	        .typeText(errorMessage)
	        .pressShortcut(Keystrokes.CR);
        bot.waitUntil(textApplied(errorMessage, ERROR_MESSAGE_COLUMN));
        return this;
    }

    public BotContractConstraintRow select() {
        swtBotTableItem.select();
        return this;
    }

    private ICondition textApplied(final String text, final int column) {
        return new ICondition() {

            @Override
            public boolean test() throws Exception {
                return text.equals(constraintTable.getTableItem(row).getText(column));
            }

            @Override
            public void init(final SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                return "item not found: " + text + " in " + constraintTable.getTableItem(row).getText(column);
            }
        };
    }
}
