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

import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.bonitasoft.studio.swtbot.framework.BotDialog;
import org.bonitasoft.studio.swtbot.framework.exception.ItemNotFoundException;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCombo;

/**
 * @author Romain Bioteau
 */
public class BotActorAssignementPropertySection extends BotBase {

    public BotActorAssignementPropertySection(final SWTGefBot bot) {
        super(bot);
    }

    public BotActorAssignementPropertySection useBelowActor() {
        bot.radio(Messages.useTaskActors).click();
        return this;
    }

    public BotActorAssignementPropertySection useActorDefinedInLane() {
        bot.radio(Messages.useActorsDefinedInLane).click();
        return this;
    }

    public BotActorAssignementPropertySection selectActor(final String actorName) {
        final SWTBotCombo comboBoxWithLabel = bot.comboBoxWithLabel(Messages.selectActor);
        for (final String item : comboBoxWithLabel.items()) {
            if (item.contains(actorName)) {
                comboBoxWithLabel.setSelection(item);
                return this;
            }
        }
        throw new ItemNotFoundException(actorName, comboBoxWithLabel.items());

    }

    public BotDialog setActorFilter() {
        throw new RuntimeException("Not yet implemented");
    }

    public BotDialog editActorFilter() {
        throw new RuntimeException("Not yet implemented");
    }

    public BotActorAssignementPropertySection removeActorFilter() {
        throw new RuntimeException("Not yet implemented");
    }

}
