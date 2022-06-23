/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.swtbot.framework.diagram.general;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.model.process.InputMappingAssignationType;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCombo;

public class BotCallActivityInputMappingPropertySection extends BotBase {

    public BotCallActivityInputMappingPropertySection(final SWTGefBot bot) {
        super(bot);
    }

    public BotCallActivityInputMappingPropertySection fetchContract() {
        bot.button(Messages.fetchContract).click();
        return this;
    }

    public BotCallActivityInputMappingPropertySection addInputMapping() {
        bot.buttonWithId(SWTBotConstants.SWTBOT_ID_CALLACTIVITY_MAPPING_ADD_INPUT).click();
        return this;
    }

    public BotCallActivityInputMappingPropertySection updateInputMapping(final int index, final String processData,
            final String returnType,
            final InputMappingAssignationType assignationType,
            final String calledProcessElement) {
        if (processData != null) {
            SWTBotTestUtil.selectExpressionProposal(bot, processData, returnType, index);
        }
        if (assignationType != null) {
            final SWTBotCombo assignationTypeCombo = bot
                    .comboBoxWithId(SWTBotConstants.SWTBOT_ID_CALLACTIVITY_MAPPING_INPUT_ASSIGNATIONTYPE, index);
            switch (assignationType) {
                case CONTRACT_INPUT:
                    assignationTypeCombo.setSelection(Messages.assignToContractInput);
                    break;
                case DATA:
                    assignationTypeCombo.setSelection(Messages.assignToData);
                    break;
                default:
                    break;
            }
        }
        if (calledProcessElement != null) {
            bot.ccomboBoxWithId(SWTBotConstants.SWTBOT_ID_CALLACTIVITY_MAPPING_INPUT_CALLEDTARGET, index)
                    .setText(calledProcessElement);
        }
        return this;
    }

    public BotCallActivityInputMappingPropertySection deleteInputMapping(final int index) {
        bot.buttonWithId(SWTBotConstants.SWTBOT_ID_CALLACTIVITY_MAPPING_DELETE_INPUT, index).click();
        return this;
    }

}
