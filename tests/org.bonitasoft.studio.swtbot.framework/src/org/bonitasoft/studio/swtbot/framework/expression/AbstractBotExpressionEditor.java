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

import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

/**
 * @author Romain Bioteau
 */
public abstract class AbstractBotExpressionEditor extends BotBase {

    private final BotExpressionEditorDialog botExpressionEditorDialog;

    public AbstractBotExpressionEditor(final SWTGefBot bot, final BotExpressionEditorDialog botExpressionEditorDialog) {
        super(bot);
        this.botExpressionEditorDialog = botExpressionEditorDialog;
    }

    public AbstractBotExpressionEditor setReturnType(final String returnType) {
        bot.comboBoxWithLabel(Messages.returnType).setText(returnType);
        return this;
    }

    public String getReturnType() {
        return bot.comboBoxWithLabel(Messages.returnType).selection();
    }

    public void ok() {
        botExpressionEditorDialog.ok();
    }

    public void cancel() {
        botExpressionEditorDialog.cancel();
    }

}
