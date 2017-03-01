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

import java.util.List;
import java.util.Objects;

import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.waits.WaitForObjectCondition;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBotControl;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyList;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyList.ListElement;

public class TabbedPropertyListBot extends AbstractSWTBotControl<TabbedPropertyList> {

    private final Widget parent;
    private final SWTBot bot;

    public TabbedPropertyListBot(TabbedPropertyList tabbedPropertyList, Widget parent, SWTBot bot)
            throws WidgetNotFoundException {
        super(tabbedPropertyList);
        this.parent = parent;
        this.bot = bot;
    }

    public TabbedPropertyListBot selectTab(String text) {
        final WaitForObjectCondition<ListElement> waitForTabs = Conditions.waitForWidget(widgetOfType(ListElement.class),
                parent);
        bot.waitUntil(waitForTabs);
        final List<ListElement> tabs = waitForTabs.getAllMatches();
        for (final ListElement tab : tabs) {
            if (Objects.equals(text, tab.getTabItem().getText())) {
                new ListElementBot(tab).click();
            }
        }
        return this;
    }

}
