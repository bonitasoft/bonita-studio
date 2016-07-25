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
package org.bonitasoft.studio.xml.tests;

import junit.framework.TestCase;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * @author Mickael Istria
 *
 */
public class XMLConnectorTest extends TestCase {

	public void testXMLOutput() throws Exception {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		fail();
//		List<ConnectorInputDescriptor> desc = new ArrayList<ConnectorInputDescriptor>();
//		ConnectorInputDescriptor xmlInput = new ConnectorInputDescriptor();
//		xmlInput.setClass(Document.class);
//		xmlInput.setParameterName("input");
//		xmlInput.setWidgetClass(Text.class);
//		desc.add(xmlInput);
//		List<ConnectorWizardCreationPage> pages = new ArrayList<ConnectorWizardCreationPage>();
//		pages.add(new ConnectorWizardCreationPageMock("dummy", desc));
//		String connectorId = "testXMLOutput";
//		assertTrue("generation failed", CreateOrEditConnectorWizard.generateConnector(shell, null, "TestXMLOutput", "org.bonitasoft.test.xml", connectorId, null, null, null, pages, Collections.EMPTY_LIST, ProcessConnector.class, new NullProgressMonitor()));
//		ExtensionProjectUtil.getConnectorAPI(true);
//		File descFile = new File(ExtensionProjectUtil.getJavaProject().getProject().getLocation() + "/bin/org/bonitasoft/test/xml/TestXMLOutput.xml");
//		BufferedReader reader = new BufferedReader(new FileReader(descFile));
//		String line = null;
//		boolean found = false;
//		while (!found && (line = reader.readLine()) != null) {
//			if (line.contains("<xml/>")) {
//				found = true;
//			}
//		}
//		reader.close();
//		assertTrue("no xml element found", found);
//		ConnectorDescription connector = ConnectorRepository.getInstance().getConnectorAPI().getConnector(connectorId);
//		assertNotNull("Generated connector could not be found by connector api", connector);
	}
}
