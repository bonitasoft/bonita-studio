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

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

/**
 * @author Romain Bioteau
 */
public class BotDataBasedStackPanel extends AbstractBotInputOutputStackPanel {

    public BotDataBasedStackPanel(final SWTGefBot bot) {
        super(bot);
    }

    public BotDataBasedStackPanel selectInputListVariable(final String listDataName) {
        bot.comboBoxWithLabelInGroup(Messages.inputList + " *", Messages.input).setSelection(listDataName);
        return this;
    }

    public BotDataBasedStackPanel setIteratorName(final String iteratorName) {
        bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_TRANSACTIONAL_TEXT_EDIT_BUTTON).click();
        bot.textWithId(SWTBotConstants.SWTBOT_ID_ITERATOR_NAME_TEXTEDITOR)
	        .setText(iteratorName);
        bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_TRANSACTIONAL_TEXT_OK_BUTTON).click();
        return this;
    }


    public BotDataBasedStackPanel setIteratorReturnType(final String returnType) {
        bot.comboBoxWithLabelInGroup(Messages.type + " *", Messages.input).setText(returnType);
        bot.sleep(200); //Delayed Observable
        return this;
    }

}
