/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.importer.bar.tests.attachmentDataImport;

import java.io.File;
import java.net.URL;
import java.util.List;

import junit.framework.TestCase;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.document.core.repository.DocumentFileStore;
import org.bonitasoft.studio.document.core.repository.DocumentRepositoryStore;
import org.bonitasoft.studio.importer.bar.tests.BarImporterTestUtil;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class AttachmentDataImportTest   extends TestCase {

    private static boolean disablepopup;



    @BeforeClass
    public static void disablePopup(){
        disablepopup = FileActionDialog.getDisablePopup();
        FileActionDialog.setDisablePopup(true);
    }

    @AfterClass
    public static void resetdisablePopup(){
        FileActionDialog.setDisablePopup(disablepopup);
    }

    @Test
    public void testAttachmentDataProcessMigration() throws Exception{
        final URL fileURL2 = FileLocator.toFileURL(AttachmentDataImportTest.class.getResource("ProcessWithAttachment--1.0.bar")); //$NON-NLS-1$
        final File migratedProcess = BarImporterTestUtil.migrateBar(fileURL2);
        assertNotNull("Fail to migrate bar file", migratedProcess);
        assertNotNull("Fail to migrate bar file", migratedProcess.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProcess);
        final MainProcess mainProcess = BarImporterTestUtil.getMainProcess(resource);
        final List<AbstractProcess> pools = ModelHelper.getAllProcesses(mainProcess);
        assertEquals("only one process should be defined",1, pools.size());
        final Pool pool= (Pool)pools.get(0);
        final List<Document> documents = pool.getDocuments();
        assertTrue("an attachmentData should be defined", !documents.isEmpty());
        final Document document = documents.get(0);
        assertNotNull("no document name has been setted",document.getName());
        assertTrue("document should be setted as internal", document.getDocumentType().equals(org.bonitasoft.studio.model.process.DocumentType.INTERNAL));
        assertNotNull("default value should have been setted",document.getDefaultValueIdOfDocumentStore());
        final DocumentRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(DocumentRepositoryStore.class);
        assertTrue("a document file should have been imported", !store.isEmpty());
        final DocumentFileStore fileStore = store.getChildren().get(0);
        assertNotNull("no document name has been set", fileStore.getName());
    }
}
