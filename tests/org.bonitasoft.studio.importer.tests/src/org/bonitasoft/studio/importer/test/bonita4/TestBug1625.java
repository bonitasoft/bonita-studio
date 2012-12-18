/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.importer.test.bonita4;

import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Mickael Istria
 *
 */
public class TestBug1625  {

    @Test
    @Ignore
    public void testBug1625() throws Exception {
        fail("testBug1625 not implemented") ;
        //		BarProcessor barImporter = new BarProcessor("");
        //		barImporter.createDiagram(FileLocator.toFileURL(getClass().getResource("VaioDOA.bar")), new NullProgressMonitor());
        //		ProcessDiagramEditor editor = (ProcessDiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        //		MainProcess process = (MainProcess) editor.getDiagramEditPart().resolveSemanticElement();
        //		assertEquals("Process not imported normally", "VaioDOA", process.getName());
    }

}
