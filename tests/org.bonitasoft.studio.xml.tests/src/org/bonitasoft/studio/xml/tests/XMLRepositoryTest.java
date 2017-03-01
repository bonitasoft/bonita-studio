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
package org.bonitasoft.studio.xml.tests;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.engine.command.ExportAsBarFileHandler;
import org.bonitasoft.studio.xml.repository.XSDFileStore;
import org.bonitasoft.studio.xml.repository.XSDRepositoryStore;

import junit.framework.TestCase;

/**
 * @author Mickael Istria
 */
public class XMLRepositoryTest extends TestCase {

    private static DiagramFileStore artifact;

    public void testDefaultArtifacts() {
        XSDRepositoryStore xrs = (XSDRepositoryStore) RepositoryManager.getInstance()
                .getRepositoryStore(XSDRepositoryStore.class);
        XSDFileStore artifact = xrs.getChild("soap-envelope.xsd");
        assertNotNull("Default XSDs not setup", artifact);
    }

    public void testFindType() {
        XSDRepositoryStore xrs = (XSDRepositoryStore) RepositoryManager.getInstance()
                .getRepositoryStore(XSDRepositoryStore.class);
        xrs.importInputStream("purchaseOrder", getClass().getResourceAsStream("purchaseOrder.xsd"));
        assertNotNull(xrs.findElementDeclaration("http://tempuri.org/po.xsd", "purchaseOrder"));
    }

    public void testFindNamespacesAndElements() {
        XSDRepositoryStore xrs = (XSDRepositoryStore) RepositoryManager.getInstance()
                .getRepositoryStore(XSDRepositoryStore.class);
        XSDFileStore fs = xrs.importInputStream("purchaseOrder", getClass().getResourceAsStream("purchaseOrder.xsd"));
        assertEquals("Could not guess the number of elements", 2, fs.getElements().size());
    }

    public void testGetArtifact() {
        XSDRepositoryStore xrs = (XSDRepositoryStore) RepositoryManager.getInstance()
                .getRepositoryStore(XSDRepositoryStore.class);
        XSDFileStore fs = xrs.importInputStream("purchaseOrder", getClass().getResourceAsStream("purchaseOrder.xsd"));
        assertNotSame("Get all XSD artifact does not work!", 0, xrs.getChildren().size());
    }

    public void testImportProcessWithXSD() throws Exception {
        fail("testImportProcessWithXSD not implemented");
        //		BarProcessor barProcessor = new BarProcessor("");
        //		File f = barProcessor.createDiagram(FileLocator.toFileURL(this.getClass().getResource("ProcessWithXSD--1.0.bar")), new NullProgressMonitor());
        //		DiagramRepositoryStore drs = (DiagramRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        //		artifact = drs.getChild(f.getName());
        //		XSDRepositoryStore xrs = (XSDRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(XSDRepositoryStore.class);
        //		XSDElementDeclaration xsd = xrs.findElementDeclaration("http://www.bonitasoft.org/xsd/employee", "Employee");
        //		assertNotNull("XSD not imported", xsd);
    }

    public void testExportProcessWithXSD() throws Exception {
        ExportAsBarFileHandler exportBar = new ExportAsBarFileHandler();
        fail();
        //		ExportAsBarFileCommand command = new ExportAsBarFileCommand();
        //		BusinessArchive bar = command.createBusinessArchive((Pool)artifact.getContent().getElements().get(0), false, null, null,Collections.EMPTY_SET );
        //		byte[] res = bar.getResource("xsd/employee.xsd");
        //		assertNotNull("XSD not exported", res);
        //		assertNotSame("XSD is an empty file", 0, res.length);
    }
}
