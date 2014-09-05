/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.swtbot.framework.widget;

import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;


/**
 * @author Romain Bioteau
 *
 */
public class BotTreeWidget extends BotWidget {

    public BotTreeWidget(final SWTBotTree swtBotTree) {
        super(swtBotTree);
    }

    @Override
    public SWTBotTree getSWTBotWidget() {
        return (SWTBotTree) bot;
    }

    public BotTreeWidget selectAll() {
        getSWTBotWidget().select(getSWTBotWidget().getAllItems());
        return this;
    }

    public BotTreeWidget select(final int indexInTree) {
        getSWTBotWidget().select(indexInTree);
        return this;
    }

    public BotTreeWidget select(final String... items) {
        getSWTBotWidget().select(items);
        return this;
    }

    public ICondition emptyCondition() {
        return new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return getSWTBotWidget().getAllItems().length == 0;
            }

            @Override
            public String getFailureMessage() {
                return "Tree is not empty";
            }
        };
    }

}
