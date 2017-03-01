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
package org.bonitasoft.studio.swtbot.framework.expression;

import org.bonitasoft.studio.swtbot.framework.exception.ReadOnlyWidgetException;
import org.bonitasoft.studio.swtbot.framework.widget.BotTableWidget;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

/**
 * @author Romain Bioteau
 */
public class BotFormFieldExpressionEditor extends AbstractBotExpressionEditor {

    public BotFormFieldExpressionEditor(final SWTGefBot bot, final BotExpressionEditorDialog botExpressionEditorDialog) {
        super(bot, botExpressionEditorDialog);
    }

    /**
     * Select a variable in the table.
     * 
     * @param variableName
     * @param dataType
     */
    public BotFormFieldExpressionEditor selectFormField(final String fieldName) {
        bot.table(1).getTableItem(fieldName).select();
        return this;
    }

    @Override
    public AbstractBotExpressionEditor setReturnType(final String returnType) {
        throw new ReadOnlyWidgetException();
    }

    public BotTableWidget fieldList() {
        return new BotTableWidget(bot.table(1));
    }

}
