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
package org.bonitasoft.studio.swtbot.framework.diagram.general.iteration;

import org.bonitasoft.studio.common.ui.jface.SWTBotConstants;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.bonitasoft.studio.swtbot.framework.expression.BotExpressionEditorDialog;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

/**
 * @author Romain Bioteau
 */
public class BotStandardLoopTypeStackPanel extends BotBase {

    public BotStandardLoopTypeStackPanel(final SWTGefBot bot) {
        super(bot);
    }

    public BotStandardLoopTypeStackPanel testAfter() {
        bot.radio(Messages.testAfterLabel).click();
        return this;
    }

    public BotStandardLoopTypeStackPanel testBefore() {
        bot.radio(Messages.testBeforeLabel).click();
        return this;
    }

    public BotExpressionEditorDialog editLoopConditionExpression() {
    	SWTBotShell activeShell = bot.activeShell();
        bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_EDITBUTTON, 0).click();
        return new BotExpressionEditorDialog(bot,activeShell);
    }

    public BotExpressionEditorDialog editMaximumLoopExpression() {
    	SWTBotShell activeShell = bot.activeShell();
        bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_EDITBUTTON, 1).click();
        return new BotExpressionEditorDialog(bot,activeShell);
    }

}
