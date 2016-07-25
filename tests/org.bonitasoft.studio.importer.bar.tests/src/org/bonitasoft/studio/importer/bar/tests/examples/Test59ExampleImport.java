/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.importer.bar.tests.examples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorImplRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorSourceRepositoryStore;
import org.bonitasoft.studio.importer.bar.tests.BarImporterTestUtil;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Aurelien Pupier
 * Import Example processes exported from BOS-SP 5.9.1
 */
public class Test59ExampleImport {

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
	public void testBuyAMiniMigration() throws Exception{
		String barName = "Buy_a_MINI--3.2.bar";
		final MainProcess mainProc = testExampleMigration(barName);
		
		final ConnectorDefRepositoryStore defStore = (ConnectorDefRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
		assertNotNull(defStore.getDefinition("MiniDueDateConnector","1.0.0"));
		assertNotNull(defStore.getDefinition("MiniStockCheckConnector","1.0.0"));
		
		final ConnectorImplRepositoryStore implStore = (ConnectorImplRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ConnectorImplRepositoryStore.class);
		ConnectorImplementation dueDateImplementation = implStore.getImplementation("MiniDueDateConnector-impl","1.0.0");
		assertNotNull(dueDateImplementation);
		ConnectorImplementation stockCheckImplementation = implStore.getImplementation("MiniStockCheckConnector-impl","1.0.0");
		assertNotNull(stockCheckImplementation);
		
		final ConnectorSourceRepositoryStore sourceStore = (ConnectorSourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ConnectorSourceRepositoryStore.class);
		assertNotNull(sourceStore.getChild(dueDateImplementation.getImplementationClassname()));
		assertNotNull(sourceStore.getChild(stockCheckImplementation.getImplementationClassname()));
		
		final List<Connector> connectors = ModelHelper.getAllItemsOfType(
				mainProc, ProcessPackage.Literals.CONNECTOR);
		assertEquals("Invalid number of connector", 2, connectors.size());
	}
	
	@Test
	public void testWebPurchase() throws Exception{
		String barName = "Web_Purchase--1.5.bar";
		testExampleMigration(barName);
	}
	
	@Test
	public void testRequestForAdvancedPayment() throws Exception{
		String barName = "Request_For_Advance_Payment--1.2.bar";
		testExampleMigration(barName);
	}
	
	@Test
	public void testPurchaseOrderValidation() throws Exception{
		String barName = "Purchase_order_validation--1.0.bar";
		testExampleMigration(barName);
	}
	
	@Test
	public void testDeliveryProcess() throws Exception{
		String barName = "Order_lifecycle--1.0.bar";
		testExampleMigration(barName);
	}
	
	@Test
	public void testCreditRequest() throws Exception{
		String barName = "Customer--1.0.bar";
		testExampleMigration(barName);
	}

	private MainProcess testExampleMigration(String barName) throws Exception {
		final URL url = Test59ExampleImport.class.getResource(barName);
		final File migratedProc =  BarImporterTestUtil.migrateBar(url);
		assertNotNull("Fail to migrate bar file", migratedProc);
		assertNotNull("Fail to migrate bar file", migratedProc.exists());
		final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
		return BarImporterTestUtil.getMainProcess(resource);
	}
	
}
