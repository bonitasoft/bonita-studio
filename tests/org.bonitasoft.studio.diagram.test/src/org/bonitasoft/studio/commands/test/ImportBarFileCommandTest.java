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
package org.bonitasoft.studio.commands.test;

import java.io.IOException;

import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * @author Baptiste Mesta
 *
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class ImportBarFileCommandTest extends SWTBotGefTestCase {

	private static boolean before;
	
	
	/**
	 * This test ensures that a import a bpmn file create a new process from this file
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	@Test
	public void testImportBPMN2() throws InterruptedException, IOException {
		
		SWTBotTestUtil.importProcessWIthPathFromClass(bot, "standardProcess.bpmn", "BPMN 2.0", "standardProcess", getClass(), false);
		
		SWTBotEditor botEditor = bot.activeEditor();
		SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

		IGraphicalEditPart part = (IGraphicalEditPart)gmfEditor.mainEditPart().part();
		MainProcess model = (MainProcess)part.resolveSemanticElement();
		Pool pool = (Pool)model.getElements().get(0);
		assertNotNull("no pool found (import failed?)",pool);
		assertEquals("standardProcess (1.0)", botEditor.getTitle());
	}

}

