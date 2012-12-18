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
package org.bonitasoft.studio.diagram.form.custom.tests;

import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Mickael Istria
 *
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class TestBug1682 extends SWTBotGefTestCase {

	protected Rectangle bounds;
	private IGraphicalEditPart part;
	private int nbParts;
	private int nbPartsAfterDelete;
	
	@Test
	public void testBug1682() throws Exception {
		Display.getDefault().syncExec(new Runnable() {

			public void run() {
				try {
					FormDiagramEditor editor = TestCommands.openFormEditorWithBaseTestForForm();
					nbParts = editor.getDiagramEditPart().getChildren().size();
					part = (IGraphicalEditPart)editor.getDiagramEditPart().getChildren().get(0);
					bounds = part.getFigure().getBounds();
					/*The editor for process*/
					String title = editor.getTitle();
					SWTBotGefEditor gefEditor = bot.gefEditor(title);
					/*get the form in the first process (pool)*/
					gefEditor.mainEditPart().children().get(0).select();
					gefEditor.clickContextMenu("Delete");
					nbPartsAfterDelete = editor.getDiagramEditPart().getChildren().size();					
					gefEditor.save();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		assertEquals("Part not deleted", nbParts - 1, nbPartsAfterDelete);

	}
}
