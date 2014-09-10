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
package org.bonitasoft.studio.swtbot.framework.diagram.general.operations;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.bonitasoft.studio.swtbot.framework.expression.BotExpressionEditorDialog;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;


/**
 * @author Romain Bioteau
 *
 */
public class BotOperationsPropertySection extends BotBase {

    public BotOperationsPropertySection(final SWTGefBot bot) {
        super(bot);
    }

    public BotOperationsPropertySection add() {
        bot.button(Messages.addAction).click();
        return this;
    }

    public BotOperationsPropertySection selectOutputVariable(final String variableName, final String returnType, final int operationIndex) {
        SWTBotTestUtil.setOutputStorageExpressionByName(bot, variableName, returnType, operationIndex * 2);
        return this;
    }

    public BotExpressionEditorDialog editActionExpression(final int operationIndex) {
        bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_EDITBUTTON, operationIndex).click();
        return new BotExpressionEditorDialog(bot);
    }

}
