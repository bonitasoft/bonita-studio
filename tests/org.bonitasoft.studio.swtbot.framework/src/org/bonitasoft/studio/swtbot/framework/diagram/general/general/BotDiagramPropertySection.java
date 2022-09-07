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
package org.bonitasoft.studio.swtbot.framework.diagram.general.general;

import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCheckBox;

/**
 * @author Romain Bioteau
 */
public class BotDiagramPropertySection extends BotBase {

    public BotDiagramPropertySection(final SWTGefBot bot) {
        super(bot);
    }

    public BotDiagramPropertySection setName(final String pName) {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button("Edit...")));
        bot.button("Edit...").click();

        // Open new Shell
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.common.Messages.openNameAndVersionDialogTitle));

        bot.textWithLabel(org.bonitasoft.studio.common.Messages.name, 0).setText(pName);
        bot.button(IDialogConstants.OK_LABEL).click();
        return this;
    }

    public BotDiagramPropertySection setVersion(final String pVersion) {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button("Edit...")));
        bot.button("Edit...").click();

        // Open new Shell
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.common.Messages.openNameAndVersionDialogTitle));

        bot.textWithLabel(org.bonitasoft.studio.common.Messages.version, 0).setText(pVersion);
        bot.button(IDialogConstants.OK_LABEL).click();
        return this;
    }

    public BotDiagramPropertySection setDescription(final String description) {
        bot.textWithLabel(org.bonitasoft.studio.common.Messages.descriptionTitle).setText(description);
        return this;
    }

    public BotDiagramPropertySection setActivateValidation(final boolean activate) {
        final SWTBotCheckBox checkBox = bot.checkBox(Messages.GeneralSection_ActivateValidation);
        if (activate) {
            checkBox.select();
        } else {
            checkBox.deselect();
        }
        return this;
    }

}
