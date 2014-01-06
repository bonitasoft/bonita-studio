/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.tests;

import junit.framework.TestCase;

import org.bonitasoft.studio.diagram.custom.commands.NewDiagramCommandHandler;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.eclipse.ui.PlatformUI;

/**
 * @author Mickael Istria
 *
 */
public class TestVersion extends TestCase {

	public void testNewProcessVersionMatchesProduct() throws Exception {
		String version = "6.2.2";//TO BE MODIFIED AT EACH RELEASE
		NewDiagramCommandHandler command = new NewDiagramCommandHandler();
		command.execute(null);
		ProcessDiagramEditor editor = (ProcessDiagramEditor)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		String bonitaVersion = ((MainProcess)editor.getDiagramEditPart().resolveSemanticElement()).getBonitaVersion();
		assertEquals("New Process Version <" + bonitaVersion + "> does not match product version <" + version + ">", version, bonitaVersion);
	}
}
