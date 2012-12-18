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
package org.bonitasoft.studio.importer.jpdl.tests;

import java.io.File;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Mickael Istria
 *
 */
public class TestBug1618 {

    @Test
    @Ignore
    public void testBug1618() throws Exception {
        File tmpFolder = new File("testBug1618");
        tmpFolder.mkdir();
        // Import process
        DiagramRepositoryStore drs = (DiagramRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);

        //		ProcessArtifact artifact = drs.importFrom(new JBPM3ToProc(), getClass().getResource("bug1618/processdefinition.xml"), new NullProgressMonitor());
        //		artifact.open();
        //		IEditorPart currentEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        //		ProcessDiagramEditor editor = (ProcessDiagramEditor) currentEditor;
        //		PoolEditPart part = (PoolEditPart) editor.getDiagramEditPart().getChildren().get(0);
        //		PoolPoolCompartmentEditPart part2 = null;
        //		for (Object child : part.getChildren()) {
        //			if (child instanceof PoolPoolCompartmentEditPart) {
        //				part2 = (PoolPoolCompartmentEditPart)child;
        //			}
        //		}
        //		IGraphicalEditPart toMovePart = (IGraphicalEditPart)part2.getChildren().get(1);
        //		String toMovePartName = ((Element)toMovePart.resolveSemanticElement()).getName();
        //		// Move figure
        //		SetBoundsCommand moveCommand = new SetBoundsCommand(toMovePart.getEditingDomain(), "Move Figure", new EObjectAdapter(toMovePart.getNotationView()), new Rectangle(600, 600, toMovePart.getFigure().getSize().width, toMovePart.getFigure().getSize().height));
        //		toMovePart.getDiagramEditDomain().getDiagramCommandStack().execute(new ICommandProxy(moveCommand)) ;
        //
        //		assertTrue("Editor should be dirty after moving a task", editor.isDirty());
        //		assertTrue(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().saveEditor(editor, false));
        //		assertTrue(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeEditor(editor, true));
        //		artifact.open();
        //		// retrieve figure
        //		currentEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        //		editor = (ProcessDiagramEditor) currentEditor;
        //		part = (PoolEditPart) editor.getDiagramEditPart().getChildren().get(0);
        //		for (Object child : part.getChildren()) {
        //			if (child instanceof PoolPoolCompartmentEditPart) {
        //				part2 = (PoolPoolCompartmentEditPart)child;
        //			}
        //		}
        //		toMovePart = (IGraphicalEditPart)part2.getChildren().get(1);
        //		assertEquals(toMovePartName, ((Element)toMovePart.resolveSemanticElement()).getName());
        //		Point afterLocation = toMovePart.getFigure().getBounds().getLocation();
        //		assertEquals(600, afterLocation.y);
        //		assertEquals(600, afterLocation.x);
        //		assertTrue(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeEditor(editor, true));
    }

}
