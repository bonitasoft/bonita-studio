/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.commands.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(SWTBotJunit4ClassRunner.class)
public class DeleteCommandTest   {

    private SWTGefBot bot = new SWTGefBot();
    
    @Before
    public void setUp() throws Exception {
        bot.saveAllEditors();
        bot.closeAllEditors();
    }

    @After
    public void tearDown() throws Exception {
        bot.activeEditor().saveAndClose();
        bot.closeAllEditors();
    }

    @Test
    public void deleteElementsInDiagram() throws Exception {
        SWTBotTestUtil.pressEnter();
        SWTBotTestUtil.importProcessWIthPathFromClass(bot, "Elements2Delete-1.0.bos", SWTBotTestUtil.IMPORTER_TITLE_BONITA, "Elements2Delete", this.getClass(), false);
        final SWTBotGefEditor editor1 = bot.gefEditor(bot.activeEditor().getTitle());
        SWTBotGefEditPart part = editor1.getEditPart("Step1").parent();
        part.select();
        editor1.clickContextMenu("Delete");
        SWTBotGefEditPart step = editor1.getEditPart("Step1");
        assertNull("Step1 should have been deleted", step);

        part = editor1.getEditPart("Fin1").parent();
        part.select();
        editor1.clickContextMenu("Delete");
        step = editor1.getEditPart("Fin1");
        assertNull("Fin1 should have been deleted", step);

        // remove lane with elements inside
        part = editor1.getEditPart("Lane1").parent();
        part.select();
        editor1.clickContextMenu("Delete");
        step = editor1.getEditPart("Lane1");
        assertNull("Lane1 should have been deleted", step);

        // check the elements from the Lane1 are gone in the Lane2 after Lane1 deleted
        part = editor1.getEditPart("Message2").parent();
        assertNotNull("Message2 should still be present", part);

        // Remove Lane2
        part = editor1.getEditPart("Lane2").parent();
        part.select();
        editor1.clickContextMenu("Delete");
        step = editor1.getEditPart("Lane2");
        assertNull("Lane2 should have been deleted", step);

        // check the elements from the Lane2 are still in the diagram
        part = editor1.getEditPart("Message2").parent();
        assertNotNull("Message2 should still be present", part);

        // Remove the Pool
        part = editor1.getEditPart("Pool2Delete").parent();
        part.select();
        editor1.clickContextMenu("Delete");
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button("Yes")));
        bot.button("Yes").click();
        step = editor1.getEditPart("Pool2Delete");
        assertNull("Pool2Delete should have been deleted", step);

    }

}
