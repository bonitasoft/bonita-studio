/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.importer.test.bos;

import java.io.File;

import junit.framework.TestCase;

import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.operation.ImportBosArchiveOperation;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;


/**
 * @author Romain Bioteau
 *
 */
public class TestBOSArchiveImport extends TestCase {

    public void testImportBOSArchiveLight() throws Exception {
        final ImportBosArchiveOperation operation = new ImportBosArchiveOperation();
        File file = new File(FileLocator.toFileURL(TestBOSArchiveImport.class.getResource("MyDiagram_1_0.bos")).getFile());
        operation.setArchiveFile(file.getAbsolutePath());
        IStatus status = operation.run(new NullProgressMonitor());
        assertNotNull(status);
        assertTrue(status.getMessage(),status.isOK());
    }

    public void testImportBOSArchiveFull() throws Exception {
        final ImportBosArchiveOperation operation = new ImportBosArchiveOperation();
        File file = new File(FileLocator.toFileURL(TestBOSArchiveImport.class.getResource("testRepo_100912_1757.bos")).getFile());
        operation.setArchiveFile(file.getAbsolutePath());
        IStatus status = operation.run(new NullProgressMonitor());
        assertNotNull(status);
        assertTrue(status.getMessage(),status.isOK());

    }

    public void testImportBOSArchiveDemoProcess() throws Exception {
        final ImportBosArchiveOperation operation = new ImportBosArchiveOperation();
        File file = new File(FileLocator.toFileURL(TestBOSArchiveImport.class.getResource("FillDBForDemo_1_0.bos")).getFile());
        operation.setArchiveFile(file.getAbsolutePath());
        IStatus status = operation.run(new NullProgressMonitor());
        assertNotNull(status);
        assertTrue(status.getMessage(),status.isOK());


        assertTrue("Missing connector source after import",  ResourcesPlugin.getWorkspace().getRoot().getProject(CommonRepositoryPlugin.getCurrentRepository()).getFolder("src-connectors").getFolder("org").getFolder("bonitasoft").getFolder("connector").getFolder("demo").getFile("FillDBImpl.java").exists());

    }

}
