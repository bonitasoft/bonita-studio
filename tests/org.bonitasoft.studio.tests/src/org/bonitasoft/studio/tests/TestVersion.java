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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import junit.framework.TestCase;

import org.bonitasoft.studio.diagram.custom.commands.NewDiagramCommandHandler;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.PlatformUI;

/**
 * @author Mickael Istria
 *
 */
public class TestVersion extends TestCase {

	public void testNewProcessVersionMatchesProduct() throws Exception {
		String resourceName ="";
		if(Platform.getProduct().getId().equals("org.bonitasoft.studioEx.product")){
			resourceName = "bonitastudioEx-features.product";
		} else if(Platform.getProduct().getId().equals("org.bonitasoft.studio.product")){
			resourceName = "bonitastudio-features.product";
		}
		InputStream productFile = FileLocator.toFileURL(Platform.getProduct().getDefiningBundle().getResource(resourceName)).openStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(productFile));
		String line = null;
		boolean found = false;
		String version = null;
		while ((line = reader.readLine()) != null) {
			if (line.startsWith("<product") && line.contains("version=\"")) {
				found = true;
				String rightPart = line.split("version=\"")[1];
				version = rightPart.substring(0, rightPart.indexOf("\""));
			}
		}
		assertNotNull("Version is null!", version);
		NewDiagramCommandHandler command = new NewDiagramCommandHandler();
		command.execute(null);
		ProcessDiagramEditor editor = (ProcessDiagramEditor)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		String bonitaVersion = ((MainProcess)editor.getDiagramEditPart().resolveSemanticElement()).getBonitaVersion();
		assertEquals("New Process Version <" + bonitaVersion + "> does not match product version <" + version + ">", version, bonitaVersion);
	}
}
