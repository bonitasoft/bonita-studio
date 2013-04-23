/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.diagram.test;

import org.bonitasoft.studio.application.actions.ImportFileCommand;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * @author Aurelien Pupier
 *
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class TestImportAndOverrideWithEditorOpen extends SWTBotGefTestCase {

    private static boolean before;

    @BeforeClass
    public static void setUpBeforeClass() {
        before = ImportFileCommand.isTest;
        ImportFileCommand.isTest = true;
    }

    @AfterClass
    public static void tearDownAfterClass() {
        ImportFileCommand.isTest = before;
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        bot.saveAllEditors();
        bot.closeAllEditors();
    }

    @Override
    protected void tearDown() throws Exception {
        bot.saveAllEditors();
        bot.closeAllEditors();
    }

    @Test
    public void testImportAndOverrideWithProcessEditorOpen() throws Exception {

        /* import the base process to override */
        SWTBotTestUtil.importProcessWIthPathFromClass(bot, "ProcWithSameNameAndVersion_1_0.bos", "Bonita 6.x", "ProcWithSameNameAndVersion", getClass(), false);
        SWTBotEditor botEditor = bot.activeEditor();
        SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

        IGraphicalEditPart part = (IGraphicalEditPart)gmfEditor.mainEditPart().part();
        MainProcess model = (MainProcess)part.resolveSemanticElement();
        assertNotNull("no pool found (import failed?)",model);
        assertContains("ProcWithSameNameAndVersion", botEditor.getTitle());
        assertEquals("Unique description 0", model.getDocumentation());

        SWTBotTestUtil.importProcessWIthPathFromClass(bot, "_1ProcWithSameNameAndVersion_1_0.bos", "Bonita 6.x", "ProcWithSameNameAndVersion", getClass(), false);
        botEditor = bot.activeEditor();
        gmfEditor = bot.gefEditor(botEditor.getTitle());

        part = (IGraphicalEditPart)gmfEditor.mainEditPart().part();
        model = (MainProcess)part.resolveSemanticElement();
        assertNotNull("no pool found (import failed?)",model);
        assertContains("ProcWithSameNameAndVersion", botEditor.getTitle());
        assertEquals("Unique description 1", model.getDocumentation());
    }

    @Test
    public void testImportAndOverrideWithFormEditorOpen() throws Exception {
        SWTBotTestUtil.importProcessWIthPathFromClass(bot, "_1ProcWithSameNameAndVersion_1_0.bos", "Bonita 6.x", "ProcWithSameNameAndVersion", getClass(), false);
        SWTBotEditor botEditor = bot.activeEditor();
        SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

        SWTBotTestUtil.createFormWhenOnAProcessWithStep(bot, gmfEditor, "Step1");
        SWTBotTestUtil.importProcessWIthPathFromClass(bot, "_2ProcWithSameNameAndVersion_1_0.bos", "Bonita 6.x", "ProcWithSameNameAndVersion", getClass(), false);

        botEditor = bot.activeEditor();
        gmfEditor = bot.gefEditor(botEditor.getTitle());
        IGraphicalEditPart part = (IGraphicalEditPart)gmfEditor.mainEditPart().part();
        MainProcess model = (MainProcess)part.resolveSemanticElement();
        assertNotNull("no pool found (import failed?)",model);
        assertContains("ProcWithSameNameAndVersion", botEditor.getTitle());
        assertEquals("Unique description 2", model.getDocumentation());
    }

}
