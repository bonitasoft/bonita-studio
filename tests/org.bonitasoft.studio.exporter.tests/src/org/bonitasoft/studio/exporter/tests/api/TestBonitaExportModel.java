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

package org.bonitasoft.studio.exporter.tests.api;

import org.bonitasoft.studio.diagram.custom.commands.NewDiagramCommandHandler;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.exporter.extension.BonitaModelExporterImpl;
import org.bonitasoft.studio.exporter.extension.IBonitaModelExporter;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.ui.PlatformUI;

import junit.framework.TestCase;

/**
 * @author Romain Bioteau
 */
public class TestBonitaExportModel extends TestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testExportNewProcess() throws Exception {
        final DiagramFileStore artifact = new NewDiagramCommandHandler().newDiagram();
        artifact.open();

        final DiagramEditor editor = (DiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                .getActiveEditor();
        editor.doSave(new NullProgressMonitor());
        final String editorName = editor.getTitle().replaceFirst("(.*)\\s\\((.*)\\)", "$1");
        final String editorVersion = editor.getTitle().replaceFirst("(.*)\\s\\((.*)\\)", "$2");
        final IBonitaModelExporter exporter = new BonitaModelExporterImpl((MainProcessEditPart) editor.getDiagramEditPart());
        assertTrue("Bad diagram version in exporter", exporter.getDiagram().getVersion().equals(editorVersion));
        assertEquals("Bad diagram name in exporter", exporter.getDiagram().getName(), editorName);
        assertTrue("Bad pool number in exporter", exporter.getPools().size() == 1);
        artifact.delete();
    }

}
