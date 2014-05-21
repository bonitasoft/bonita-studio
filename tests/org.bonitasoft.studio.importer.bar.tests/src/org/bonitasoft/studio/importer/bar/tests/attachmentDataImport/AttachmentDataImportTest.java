package org.bonitasoft.studio.importer.bar.tests.attachmentDataImport;

import java.io.File;
import java.net.URL;
import java.util.List;

import junit.framework.TestCase;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.data.attachment.repository.DocumentFileStore;
import org.bonitasoft.studio.data.attachment.repository.DocumentRepositoryStore;
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
		URL fileURL2 = FileLocator.toFileURL(AttachmentDataImportTest.class.getResource("ProcessWithAttachment--1.0.bar")); //$NON-NLS-1$
		File migratedProcess = BarImporterTestUtil.migrateBar(fileURL2);
		 assertNotNull("Fail to migrate bar file", migratedProcess);
	     assertNotNull("Fail to migrate bar file", migratedProcess.exists());
	     final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProcess);
	     final MainProcess mainProcess = BarImporterTestUtil.getMainProcess(resource);
	     final List<AbstractProcess> pools = ModelHelper.getAllProcesses(mainProcess);
	     assertEquals("only one process should be defined",1, pools.size());
	     final Pool pool= (Pool)pools.get(0);
	     final List<Document> documents = pool.getDocuments();
	     assertTrue("an attachmentData should be defined", !pool.getDocuments().isEmpty());
	     final Document document = pool.getDocuments().get(0);
	     assertNotNull("no document name has been setted",document.getName());
	     assertTrue("document should be setted as internal", document.isIsInternal());
	     assertNotNull("default value should have been setted",document.getDefaultValueIdOfDocumentStore());
	     DocumentRepositoryStore store = (DocumentRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(DocumentRepositoryStore.class);
	     assertTrue("a document file should have been imported", !store.getChildren().isEmpty());
	     DocumentFileStore fileStore = store.getChildren().get(0);
	     assertNotNull("no document name has been setted",fileStore.getName());
	}
}
