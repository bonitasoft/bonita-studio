/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.bug;

import static org.bonitasoft.studio.repository.themes.i18n.Messages.themeRepository;

import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.junit.Test;

/**
 * @author Mickael Istria
 */
public class TestBugsSWTBot extends SWTBotGefTestCase {

    @Test
    public void testOpenHtmlEditor() throws Exception {
        SWTBotTestUtil.createNewDiagram(bot);

        final SWTBotView botView = bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_APPLICATION);
        if (!botView.isActive()) {
            botView.show();
        }
        botView.setFocus();
        SWTBotTestUtil.selectTabbedPropertyView(bot, themeRepository);

        final SWTBotView properties = bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_APPLICATION);
        properties.bot().button(Messages.Edit, 1).click();
        final SWTBotEditor activeEditor = bot.activeEditor();
        assertEquals("org.eclipse.wst.html.core.htmlsource.source", activeEditor.getReference().getId());
    }

    @Override
    protected void tearDown() throws Exception {
        bot.closeAllEditors();
    }
}
