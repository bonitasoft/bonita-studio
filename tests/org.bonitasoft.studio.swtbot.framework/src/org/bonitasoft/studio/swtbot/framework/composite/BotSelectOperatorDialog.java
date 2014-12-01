/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.swtbot.framework.composite;

import java.util.Arrays;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotDialog;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

/**
 * @author Aurelien Pupier
 */
public class BotSelectOperatorDialog extends BotDialog {

    public BotSelectOperatorDialog(final SWTGefBot bot) {
        super(bot, Messages.dialogTitleSelectOperator);
    }

    public List<String> getAvailableOperators() {
        return Arrays.asList(bot.comboBox().items());
    }

    /**
     * @param operatorName see {@link ExpressionConstants} for possible valid values
     */
    public void selectOperator(final String operatorName) {
        bot.comboBox().setSelection(operatorName);
    }

    public void selectOperator(final int index) {
        bot.comboBox().setSelection(index);
    }

}
