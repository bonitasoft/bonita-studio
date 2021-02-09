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
package org.bonitasoft.studio.swtbot.framework.application.menu;

import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

/**
 * @author Romain Bioteau
 */
public class AbstractBotMenu extends BotBase {

    public AbstractBotMenu(final SWTGefBot bot) {
        super(bot);
    }

    protected void openMenu(final String menuLabel) {
        SWTBotTestUtil.waitUntilRootShellIsActive(bot);
        bot.menu(menuLabel).click();
    }

    public boolean isEnabled(final String menuLabel) {
        SWTBotTestUtil.waitUntilRootShellIsActive(bot);
        return bot.menu(menuLabel).isEnabled();
    }

    protected void waitForMainShell(SWTWorkbenchBot bot) {
        SWTBotTestUtil.waitUntilRootShellIsActive(bot);
    }

}
