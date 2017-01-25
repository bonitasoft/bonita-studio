/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.properties.test;

import static org.junit.Assert.assertEquals;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestResources implements SWTBotConstants {

    private final SWTGefBot bot = new SWTGefBot();

    @Test
    public void testCorrectLabelProvider() throws Exception {
        new BotApplicationWorkbenchWindow(bot).importBOSArchive()
                .setArchive(
                        TestResources.class.getResource("TestExportToBPMNDiagram_1_0.bos"))
                .finish();

        final SWTBotGefEditor gefEditor = bot.gefEditor(bot.activeEditor().getTitle());
        gefEditor.getEditPart("Pool1").parent().select();

        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_APPLICATION).setFocus();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Resources");
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_APPLICATION).setFocus();
        bot.treeWithId(APPLICATION_RESOURCES_TREE_ID).getTreeItem("Resources").expand().select("application");
        assertEquals("Invalid selection", "application",
                bot.treeWithId(APPLICATION_RESOURCES_TREE_ID).selection().get(0, 0));

        gefEditor.getEditPart("Pool2").parent().select();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_APPLICATION).setFocus();
        bot.treeWithId(APPLICATION_RESOURCES_TREE_ID).getTreeItem("Resources").expand().select("application");
        assertEquals("Invalid selection", "application",
                bot.treeWithId(APPLICATION_RESOURCES_TREE_ID).selection().get(0, 0));
    }

}
