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
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;


/**
 * @author Romain Bioteau
 *
 */
public class BotContractInputRow extends BotBase {

    private final int row;

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
        bot.table().click(row, NAME_COLUMN);
        bot.textWithId(SWTBotConstants.SWTBOT_ID_INPUT_NAME_TEXTEDITOR).setText(name);
        SWTBotTestUtil.pressEnter();
        bot.waitUntil(rowIsInactive());
        return this;
    }

    public BotContractInputRow setDescription(final String description) {
        bot.table().click(row, DESCRIPTION_COLUMN);
        bot.text(1).setText(description);
        SWTBotTestUtil.pressEnter();
        bot.waitUntil(rowIsInactive());
        return this;
    }

    protected DefaultCondition rowIsInactive() {
        return new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return !bot.table().getTableItem(row).isActive();
            }

            @Override
            public String getFailureMessage() {
                return "Row " + row + " is still active";
            }
        };
    }

    public BotContractInputRow setType(final String type) {
        bot.table().click(row, TYPE_COLUMN);
        bot.comboBox().setSelection(type);
        SWTBotTestUtil.pressEnter();
        bot.waitUntil(rowIsInactive());
        return this;
    }

    public BotContractInputRow clickMultiple() {
        bot.table().click(row, MULTIPLE_COLUMN);
        bot.waitUntil(rowIsInactive());
        return this;
    }

    public BotContractInputRow clickMandatory() {
        bot.table().click(row, MANDATORY_COLUMN);
        bot.waitUntil(rowIsInactive());
        return this;
    }

}
