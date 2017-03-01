/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.swtbot.framework;

import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.widgetOfType;

import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.waits.WaitForObjectCondition;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyList;

public class BotPropertiesView extends BotBase {

    private final SWTBotView botView;

    public BotPropertiesView(final SWTGefBot bot, SWTBotView botView) {
        super(bot);
        this.botView = botView;
    }

    public void selectTab(String text) {
        final WaitForObjectCondition<TabbedPropertyList> waitForTabbedPropertyList = Conditions.waitForWidget(
                widgetOfType(TabbedPropertyList.class),
                botView.getWidget());
        bot.waitUntil(waitForTabbedPropertyList);
        if (waitForTabbedPropertyList.getAllMatches().isEmpty()) {
            throw new WidgetNotFoundException("No TabbedPropertyList found.");
        }
        final TabbedPropertyList tabbedPropertyList = waitForTabbedPropertyList.get(0);
        final TabbedPropertyListBot tabbedPropertyListBot = new TabbedPropertyListBot(tabbedPropertyList,
                botView.getWidget(), bot);
        tabbedPropertyListBot.selectTab(text);
    }

}
