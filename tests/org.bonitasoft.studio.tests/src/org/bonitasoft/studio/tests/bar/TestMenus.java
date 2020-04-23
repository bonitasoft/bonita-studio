/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.bar;

import static org.junit.Assert.assertEquals;

import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestMenus {

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule rule = new SWTGefBotRule(bot);

    @Test
    public void should_have_only_valid_menus() {
        SWTBotTestUtil.waitUntilRootShellIsActive(bot);
        final SWTBotMenu mainMenu = bot.menu("File");
        Display.getDefault().syncExec(new Runnable() {

            @Override
            public void run() {
                final Menu parent = mainMenu.widget.getParent();
                String menus = "";
                int nbRunMenus = 0;
                for (final MenuItem item : parent.getItems()) {
                    menus += "\n" + item.getText();
                    if (item.getText().toLowerCase().trim().equals("run")) {
                        nbRunMenus++;
                    }
                }
                assertEquals("Run menu should not appears", 0, nbRunMenus);
                if (Platform.getProduct().getId().equals("org.bonitasoft.studioEx.product")) {
                    assertEquals(String.format("Menu bar polluted by third-party menus.\n available menu:\n%s\n", menus), 9,
                            parent.getItemCount());
                } else if (Platform.getProduct().getId().equals("org.bonitasoft.studio.product")) {
                    assertEquals(String.format("Menu bar polluted by third-party menus.\n available menu:\n%s\n", menus), 7,
                            parent.getItemCount());
                }
            }
        });
    }

}
