/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.swtbot.framework.conditions;

import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

public class SelectNodeUnder implements ICondition {

    private final String parentNode;
    private final String subNodeLabel;
    private final SWTBot bot;

    public SelectNodeUnder(final SWTBot bot, final String subNodeLabel, final String parentNode) {
        this.bot = bot;
        this.parentNode = parentNode;
        this.subNodeLabel = subNodeLabel;
    }

    public boolean test() throws Exception {
        bot.tree().unselect();
        final SWTBotTreeItem expandedNode = bot.tree().select(parentNode).expandNode(parentNode);
        expandedNode.select(subNodeLabel);
        return bot.tree().selectionCount() > 0;
    }

    public void init(final SWTBot bot) {

    }

    public String getFailureMessage() {
        final StringBuilder sb = new StringBuilder();
        final SWTBotTreeItem[] allItems = bot.tree().getAllItems();
        for (final SWTBotTreeItem swtBotTreeItem : allItems) {
            sb.append(swtBotTreeItem.getText()).append("///");
        }
        return "Cannot select tree item " + parentNode + " --> " + subNodeLabel + " in :\n" + sb.toString();
    }
}
