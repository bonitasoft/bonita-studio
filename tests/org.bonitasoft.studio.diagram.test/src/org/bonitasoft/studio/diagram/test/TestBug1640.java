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

import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Mickael Istria
 *
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class TestBug1640 extends SWTBotGefTestCase {


	@Test
	public void testBug1640() throws Exception {
		//close editors that were re-opened (for form closed editors)
		bot.saveAllEditors();
		bot.closeAllEditors();

		// Create first process
		SWTBotTestUtil.createNewDiagram(bot);
		bot.menu("Diagram").click().menu("Save").click();
		SWTBotGefEditor editor = bot.gefEditor(bot.editors().get(0).getTitle());
		assertTrue(!editor.isDirty());

		final ProcessDiagramEditor processEditor = (ProcessDiagramEditor) bot.editors().get(0).getReference().getEditor(false);
		editor.click(10, 10);

		// Rename it
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				MainProcess diagram = (MainProcess)processEditor.getDiagramEditPart().resolveSemanticElement();
				processEditor.getEditingDomain().getCommandStack().execute(new SetCommand(processEditor.getEditingDomain(),
						diagram,
						ProcessPackage.Literals.ELEMENT__NAME,
						"TestBug1640_1)"));
			}
		});
		assertTrue(editor.isDirty());
		bot.menu("Diagram").click().menu("Save").click();
		int tries = 5;
		do {
			Thread.sleep(5000);
			tries --;
		} while (bot.editors().size() != 1 && tries > 0);
		bot.closeAllEditors();

		assertFalse(editor.isActive());

		// Create 2nd process
		SWTBotTestUtil.createNewDiagram(bot);
		SWTBotTestUtil.waitUntilBonitaBPmShellIsActive(bot);
		bot.menu("Diagram").click().menu("Save").click();
		editor = bot.gefEditor(bot.editors().get(0).getTitle());
		assertTrue(!editor.isDirty());
		//TODO: use the avsolute coordinate
		Point center = ((IGraphicalEditPart)editor.getEditPart("Step1").part()).getFigure().getBounds().getCenter();
		editor.drag(center.x+20, center.y+20, center.x + 200, center.y + 200);
		Assert.assertTrue(editor.isDirty());
		bot.menu("Diagram").click().menu("Save").click();
		// Check that editor is not closed
		Assert.assertEquals(1, bot.editors().size());
	}

	@Override
	@After
	public void tearDown() {
		bot.saveAllEditors();
		bot.closeAllEditors();
	}

}
