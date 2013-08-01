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
package org.bonitasoft.studio.importer.bar.tests.connector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.bonitasoft.engine.bpm.connector.ConnectorEvent;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.importer.bar.tests.BarImporterTestUtil;
import org.bonitasoft.studio.model.expression.AbstractExpression;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Romain Bioteau
 * 
 */
public class TestConnectorMigrationUseCase {

	private static boolean disablepopup;

	@BeforeClass
	public static void disablePopup() {
		disablepopup = FileActionDialog.getDisablePopup();
		FileActionDialog.setDisablePopup(true);
	}

	@AfterClass
	public static void resetdisablePopup() {
		FileActionDialog.setDisablePopup(disablepopup);
	}

	@Test
	public void testGroovyConnectorMigration() throws Exception {
		final MainProcess mainProc = importBar("GroovyConnectorMigrationUseCase--1.0.bar");

		final List<Connector> connectors = ModelHelper.getAllItemsOfType(
				mainProc, ProcessPackage.Literals.CONNECTOR);
		assertEquals("Invalid number of connector", 1, connectors.size());
		Connector c = connectors.get(0);
		assertEquals("grrovyConnector", c.getName());
		assertEquals(ConnectorEvent.ON_ENTER.name(), c.getEvent());
		assertEquals(1, c.getConfiguration().getParameters().size());
		final AbstractExpression expression = c.getConfiguration()
				.getParameters().get(0).getExpression();
		assertNotNull(expression);
		assertTrue(expression instanceof Expression);
		assertEquals(ExpressionConstants.SCRIPT_TYPE,
				((Expression) expression).getType());
		assertEquals(2, ((Expression) expression).getReferencedElements()
				.size());

		assertEquals(1, c.getOutputs().size());
		Expression leftOp = c.getOutputs().get(0).getLeftOperand();
		Expression rightOp = c.getOutputs().get(0).getLeftOperand();
		Operator op = c.getOutputs().get(0).getOperator();
		assertNotNull(rightOp);
		assertNotNull(leftOp);
		assertNotNull(op);

		assertFalse(leftOp.getContent().isEmpty());
		assertFalse(rightOp.getContent().isEmpty());

		assertEquals(ExpressionConstants.ASSIGNMENT_OPERATOR, op.getType());
		assertEquals(ExpressionConstants.VARIABLE_TYPE, leftOp.getType());
	}

	@Test
	public void testEmailConnectorMigration() throws Exception {
		final MainProcess mainProc = importBar("EmailConnectorMigrationUseCase--1.0.bar");

		final List<Connector> connectors = ModelHelper.getAllItemsOfType(
				mainProc, ProcessPackage.Literals.CONNECTOR);
		assertEquals("Invalid number of connector", 1, connectors.size());

	}

	@Test
	public void testEmailWithScriptConnectorMigration() throws Exception {
		final MainProcess mainProc = importBar("EmailConnectorWithScriptMessageMigrationUseCase--1.0.bar");

		final List<Connector> connectors = ModelHelper.getAllItemsOfType(
				mainProc, ProcessPackage.Literals.CONNECTOR);
		assertEquals("Invalid number of connector", 1, connectors.size());
	}

	@Test
	public void testJDBCConnectorMigration() throws Exception {
		final MainProcess mainProc = importBar("GenericJDBCConnectorMigration--1.0.bar");

		final List<Connector> connectors = ModelHelper.getAllItemsOfType(
				mainProc, ProcessPackage.Literals.CONNECTOR);
		assertEquals("Invalid number of connector", 2, connectors.size());
	}

	@Test
	public void testConnectorInFormsMigration() throws Exception {
		final MainProcess mainProc = importBar("ConnectorInFormsUseCase--1.0.bar");

		final List<Connector> connectors = ModelHelper.getAllItemsOfType(
				mainProc, ProcessPackage.Literals.CONNECTOR);
		assertEquals("Invalid number of connector", 9, connectors.size());
	}

	@Test
	public void testTalendConnectorsMigration() throws Exception {
		final MainProcess mainProc = importBar("talend_connectors.bar");

		final List<Connector> connectors = ModelHelper.getAllItemsOfType(
				mainProc, ProcessPackage.Literals.CONNECTOR);
		if("org.bonitasoft.studio.product".equals(Platform.getProduct().getId())){
			assertEquals("Invalid number of connector", 1, connectors.size());
		} else {
			assertEquals("Invalid number of connector", 2, connectors.size());
		}
	}

	@Test
	public void testWebServiceConnectorMigration() throws Exception {
		final MainProcess mainProc = importBar("WebServiceConnectorMigration--1.0.bar");

		final List<Connector> connectors = ModelHelper.getAllItemsOfType(
				mainProc, ProcessPackage.Literals.CONNECTOR);
		assertEquals("Invalid number of connector", 2, connectors.size());
	}

	@Test
	public void testDatabaseConnectorMigration() throws Exception {
		final MainProcess mainProc = importBar("DatabaseConnectorsUseCase--1.0.bar");
		final List<Connector> connectors = ModelHelper.getAllItemsOfType(
				mainProc, ProcessPackage.Literals.CONNECTOR);
		assertEquals("Invalid number of connector", 18, connectors.size());
	}

	@Test
	public void testSapConnectorMigration() throws Exception {
		final MainProcess mainProc = importBar("SapConnectorMigrationUseCase--1.0.bar");
		final List<Connector> connectors = ModelHelper.getAllItemsOfType(
				mainProc, ProcessPackage.Literals.CONNECTOR);
		assertEquals("Invalid number of connector", 1, connectors.size());
	}

	@Test
	public void testSalesForceCreateObjectConnectorMigration() throws Exception {
		final MainProcess mainProc = importBar("SalesforceCreateObjectMigrationUseCase--1.0.bar");
		final List<Connector> connectors = ModelHelper.getAllItemsOfType(
				mainProc, ProcessPackage.Literals.CONNECTOR);
		assertEquals("Invalid number of connector", 1, connectors.size());
	}

	@Test
	public void testSalesForceDeleteObjectsConnectorMigration()
			throws Exception {
		final MainProcess mainProc =importBar("SalesforceDeleteObjectsMigrationUseCase--1.0.bar");
		final List<Connector> connectors = ModelHelper.getAllItemsOfType(
				mainProc, ProcessPackage.Literals.CONNECTOR);
		assertEquals("Invalid number of connector", 1, connectors.size());
	}

	@Test
	public void testSalesForceQueryObjectsConnectorMigration() throws Exception {
		final MainProcess mainProc = importBar("SalesforceQueryObjectsMigrationUseCase--1.0.bar");
		final List<Connector> connectors = ModelHelper.getAllItemsOfType(
				mainProc, ProcessPackage.Literals.CONNECTOR);
		assertEquals("Invalid number of connector", 1, connectors.size());
	}

	@Test
	public void testSalesForceRetrieveObjectsConnectorMigration() throws Exception {
		final MainProcess mainProc = importBar("SalesforceRetrieveObjectsMigrationUseCase--1.0.bar");
		final List<Connector> connectors = ModelHelper.getAllItemsOfType(
				mainProc, ProcessPackage.Literals.CONNECTOR);
		assertEquals("Invalid number of connector", 1, connectors.size());
	}

	@Test
	public void testSalesForceUpdateObjectConnectorMigration() throws Exception {
		final MainProcess mainProc = importBar("SalesforceUpdateObjectMigrationUseCase--1.0.bar");
		final List<Connector> connectors = ModelHelper.getAllItemsOfType(
				mainProc, ProcessPackage.Literals.CONNECTOR);
		assertEquals("Invalid number of connector", 1, connectors.size());
	}

	@Test
	public void testAlfrescoConnectorsMigration() throws Exception {
		final MainProcess mainProc = importBar("AlfrescoConnectors--1.0.bar");
		final List<Connector> connectors = ModelHelper.getAllItemsOfType(
				mainProc, ProcessPackage.Literals.CONNECTOR);
		assertEquals("Invalid number of connector", 4, connectors.size());
	}

	@Test
	public void testTwitterDirectConnectorMigration() throws Exception {
		final MainProcess mainProc = importBar("TwitterDirectMigrationUseCase--1.0.bar");
		final List<Connector> connectors = ModelHelper.getAllItemsOfType(
				mainProc, ProcessPackage.Literals.CONNECTOR);
		assertEquals("Invalid number of connector", 1, connectors.size());
	}

	@Test
	public void testTwitterUpdateConnectorMigration() throws Exception {
		final MainProcess mainProc = importBar("TwitterUpdateMigrationUseCase--1.0.bar");
		final List<Connector> connectors = ModelHelper.getAllItemsOfType(
				mainProc, ProcessPackage.Literals.CONNECTOR);
		assertEquals("Invalid number of connector", 1, connectors.size());
	}

	@Test
	public void testCMISCreateFolderConnectorMigration() throws Exception {
		final MainProcess mainProc = importBar("CMISCreateFolderMigrationUseCase--1.0.bar");
		final List<Connector> connectors = ModelHelper.getAllItemsOfType(
				mainProc, ProcessPackage.Literals.CONNECTOR);
		assertEquals("Invalid number of connector", 1, connectors.size());
	}
	
	@Test
	public void testCMISDeleteFolderConnectorMigration() throws Exception {
		final MainProcess mainProc = importBar("CMISDeleteFolderMigrationUseCase--1.0.bar");
		final List<Connector> connectors = ModelHelper.getAllItemsOfType(
				mainProc, ProcessPackage.Literals.CONNECTOR);
		assertEquals("Invalid number of connector", 1, connectors.size());
	}
	
	@Test
	public void testCMISUploadDocumentConnectorMigration() throws Exception {
		final MainProcess mainProc = importBar("CMISUploadDocumentMigrationUseCase--1.0.bar");
		final List<Connector> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);
		assertEquals("Invalid number of connector", 1, connectors.size());
	}
	
	@Test
	public void testCMISDeleteObjectConnectorMigration() throws Exception {
		final MainProcess mainProc = importBar("CMISDeleteObjectMigrationUseCase--1.0.bar");
		final List<Connector> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);
		assertEquals("Invalid number of connector", 1, connectors.size());
	}
	
	@Test
	public void testSetBonitaVariableOnFinishConnectorMigration() throws Exception {
		final MainProcess mainProc = importBar("SetVariableOnStepOnFinish--1.0.bar");
		final List<Connector> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);
		assertEquals("Invalid number of connector", 0, connectors.size());

		Activity activity = (Activity) ModelHelper.findElement(mainProc, "Step1", true);
		final EList<Operation> operations = activity.getOperations();
		assertEquals("No operations has been created", 1, operations.size());
	}
	
	@Test
	public void testSetBonitaVariableOnEnterConnectorMigration() throws Exception {
		final MainProcess mainProc = importBar("SetVariableOnStepOnEnter--1.0.bar");
		final List<Connector> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);
		assertEquals("Invalid number of connector", 0, connectors.size());

		Activity activity = (Activity) ModelHelper.findElement(mainProc, "Step1", true);
		final EList<Operation> operations = activity.getOperations();
		assertEquals("No operations has been created", 1, operations.size());
		Operation operation = operations.get(0);
		operation.getLeftOperand();
		
		//TODO: check that there is an entry in the report
	}
	
	@Test
	public void testSetBonitaVariablesOnFinishConnectorMigration() throws Exception {
		final MainProcess mainProc = importBar("SetVariablesOnStepOnFinish--1.0.bar");
		final List<Connector> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);
		assertEquals("Invalid number of connector", 0, connectors.size());

		Activity activity = (Activity) ModelHelper.findElement(mainProc, "Step1", true);
		final EList<Operation> operations = activity.getOperations();
		assertEquals("No operations has been created", 2, operations.size());
	}
	
	@Test
	public void testSetBonitaVariablesOnEnterConnectorMigration() throws Exception {
		final MainProcess mainProc = importBar("SetVariablesOnStepOnEnter--1.0.bar");
		final List<Connector> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);
		assertEquals("Invalid number of connector", 0, connectors.size());
		
		Activity activity = (Activity) ModelHelper.findElement(mainProc, "Step1", true);
		final EList<Operation> operations = activity.getOperations();
		assertEquals("No operations has been created", 2, operations.size());
		//TODO: check that there is an entry in the report
	}
	
	@Test
	public void testSetBonitaVariableOnProcessConnectorMigration() throws Exception {
		final MainProcess mainProc = importBar("SetVarOnPool--1.0.bar");
		final List<Connector> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);
		assertEquals("Invalid number of connector", 0, connectors.size());
		
		Activity activity = (Activity) ModelHelper.findElement(mainProc, "Step1", true);
		final EList<Operation> operations = activity.getOperations();
		assertEquals("No operations should have been created", 0, operations.size());
		//TODO: check that there is an entry in the report
		
	}
	
	@Test
	public void testSetBonitaVariableOnStartConnectorMigration() throws Exception {
		final MainProcess mainProc = importBar("SetVariableOnStart--1.0.bar");
		final List<Connector> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);
		assertEquals("Invalid number of connector", 0, connectors.size());

		Activity activity = (Activity) ModelHelper.findElement(mainProc, "Step1", true);
		final EList<Operation> operations = activity.getOperations();
		assertEquals("No operations has been created", 1, operations.size());
		//TODO: check that there is an entry in the report
		
	}
	
	@Test
	public void testSetBonitaVariableOnResumeAndSuspendConnectorMigration() throws Exception {
		final MainProcess mainProc = importBar("SetVariableOnResumeAndSuspend--1.0.bar");
		final List<Connector> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);
		assertEquals("Invalid number of connector", 0, connectors.size());

		Activity activity = (Activity) ModelHelper.findElement(mainProc, "Step1", true);
		final EList<Operation> operations = activity.getOperations();
		assertEquals("Operations has been created but it shouldn't", 0, operations.size());
		//TODO: check that there is an entry in the report
		
	}
	
	@Test
	public void testSetBonitaVariableWithEmptyValueConnectorMigration() throws Exception {
		final MainProcess mainProc = importBar("SetVariableWithValueNotFiled--1.0.bar");
		final List<Connector> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);
		assertEquals("Invalid number of connector", 0, connectors.size());

		Activity activity = (Activity) ModelHelper.findElement(mainProc, "Step1", true);
		final EList<Operation> operations = activity.getOperations();
		assertEquals("No operations has been created", 1, operations.size());
			
	}
	
	@Test
	public void testSetBonitaVariableInFormConnectorMigration() throws Exception {
		final MainProcess mainProc = importBar("SetVarInForm--1.0.bar");
		final List<Connector> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);
		assertEquals("Invalid number of connector", 0, connectors.size());

		Activity activity = (Activity) ModelHelper.findElement(mainProc, "Step1", true);
		final EList<Operation> operations = activity.getOperations();
		assertEquals("No operations should have been created", 0, operations.size());
			
	}

	private MainProcess importBar(final String barName) throws Exception {
		final URL url = TestConnectorMigrationUseCase.class.getResource(barName);
		final File migratedProc = BarImporterTestUtil.migrateBar(url);
		assertNotNull("Fail to migrate bar file", migratedProc);
		assertNotNull("Fail to migrate bar file", migratedProc.exists());
		final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
		final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);
		return mainProc;
	}
	
}


