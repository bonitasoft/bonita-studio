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
package org.bonitasoft.studio.swtbot.framework.diagram.general.actors;

import java.util.Objects;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;

/**
 * @author Romain Bioteau
 */
public class BotActorDefinitionPropertySection extends BotBase {

    public BotActorDefinitionPropertySection(final SWTGefBot bot) {
        super(bot);
    }

    public BotActorDefinitionPropertySection addActor(final String name, final String description) {
        bot.button(Messages.addActor).click();
        final SWTBotTable table = bot.table();

        table.click(table.rowCount() - 1, 1);
        bot.text()
                .typeText(name)
                .pressShortcut(Keystrokes.CR);

        table.click(table.rowCount() - 1, 2);
        bot.text()
                .typeText(description)
                .pressShortcut(Keystrokes.CR);
        return this;
    }

    public BotActorDefinitionPropertySection deleteActor(final String actorName) {
        selectActor(actorName);
        bot.button(Messages.delete).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.deleteActorsTitle));
        bot.button(IDialogConstants.OK_LABEL).click();
        return this;
    }

    public BotActorDefinitionPropertySection selectActor(final String actorName) {
        final SWTBotTable table = bot.table();
        final int actorIdx = table.indexOf(actorName, org.bonitasoft.studio.actors.i18n.Messages.name);
        table.getTableItem(actorIdx).select();
        return this;
    }

    public BotActorDefinitionPropertySection setSetAsInitiator() {
        if (Objects.equals(bot.buttonWithId("org.bonitasoft.studio.actors.ui.section.initiatorButton").getText(),
                Messages.setAsProcessInitiator)) {
            bot.button(Messages.setAsProcessInitiator).click();
        }
        return this;
    }

}
