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
package org.bonitasoft.studio.importer.bar.tests.connector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bonitasoft.engine.bpm.connector.ConnectorEvent;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.importer.bar.tests.BarImporterTestUtil;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.AbstractExpression;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
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
        final Connector c = connectors.get(0);
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
        final Expression leftOp = c.getOutputs().get(0).getLeftOperand();
        final Expression rightOp = c.getOutputs().get(0).getLeftOperand();
        final Operator op = c.getOutputs().get(0).getOperator();
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
        if ("org.bonitasoft.studio.product".equals(Platform.getProduct().getId())) {
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
        assertEquals("Invalid number of connector", 17, connectors.size());
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
        final MainProcess mainProc = importBar("SalesforceDeleteObjectsMigrationUseCase--1.0.bar");
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
        final List<Connector> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);
        assertEquals("Invalid number of connector", 1, connectors.size());
        final Map<String, AbstractExpression> expectedParameterCOnfiguration = new HashMap<String, AbstractExpression>();
        expectedParameterCOnfiguration.put("username",
                ExpressionHelper.createConstantExpression("admin", String.class.getName()));
        expectedParameterCOnfiguration.put("url",
                ExpressionHelper.createConstantExpression("http://cmis.alfresco.com/service/cmis", String.class.getName()));
        expectedParameterCOnfiguration.put("repository",
                ExpressionHelper.createConstantExpression("Main Repository", String.class.getName()));
        expectedParameterCOnfiguration.put("folder_path",
                ExpressionHelper.createConstantExpression("/", String.class.getName()));
        expectedParameterCOnfiguration.put("subfolder_name",
                ExpressionHelper.createConstantExpression("Bonita", String.class.getName()));
        checkParameters(connectors, expectedParameterCOnfiguration);
    }

    @Test
    public void testCMISDeleteFolderConnectorMigration() throws Exception {
        final MainProcess mainProc = importBar("CMISDeleteFolderMigrationUseCase--1.0.bar");
        final List<Connector> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);
        assertEquals("Invalid number of connector", 1, connectors.size());
        final Map<String, AbstractExpression> expectedParameterCOnfiguration = new HashMap<String, AbstractExpression>();
        expectedParameterCOnfiguration.put("username",
                ExpressionHelper.createConstantExpression("admin", String.class.getName()));
        expectedParameterCOnfiguration.put("url",
                ExpressionHelper.createConstantExpression("http://cmis.alfresco.com/service/cmis", String.class.getName()));
        expectedParameterCOnfiguration.put("repository",
                ExpressionHelper.createConstantExpression("Main Repository", String.class.getName()));
        expectedParameterCOnfiguration.put("folder_path",
                ExpressionHelper.createConstantExpression("/Bonita", String.class.getName()));
        expectedParameterCOnfiguration.put("binding_type",
                ExpressionHelper.createConstantExpression("atompub", String.class.getName()));
        checkParameters(connectors, expectedParameterCOnfiguration);
    }

    @Test
    public void testCMISUploadDocumentConnectorMigration() throws Exception {
        final MainProcess mainProc = importBar("CMISUploadDocumentMigrationUseCase--1.0.bar");
        final List<Connector> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);
        assertEquals("Invalid number of connector", 1, connectors.size());
        final Map<String, AbstractExpression> expectedParameterCOnfiguration = new HashMap<String, AbstractExpression>();
        expectedParameterCOnfiguration.put("username",
                ExpressionHelper.createConstantExpression("admin", String.class.getName()));
        expectedParameterCOnfiguration.put("url",
                ExpressionHelper.createConstantExpression("http://cmis.alfresco.com/service/cmis", String.class.getName()));
        expectedParameterCOnfiguration.put("repository",
                ExpressionHelper.createConstantExpression("Main Repository", String.class.getName()));
        expectedParameterCOnfiguration.put("folder_path",
                ExpressionHelper.createConstantExpression("/Bonita/", String.class.getName()));
        expectedParameterCOnfiguration.put("binding_type",
                ExpressionHelper.createConstantExpression("atompub", String.class.getName()));
        expectedParameterCOnfiguration.put("document",
                ExpressionHelper.createConstantExpression("newFile", String.class.getName()));
        expectedParameterCOnfiguration.put("destinationName",
                ExpressionHelper.createConstantExpression("document.txt", String.class.getName()));
        checkParameters(connectors, expectedParameterCOnfiguration);
    }

    @Test
    public void testCMISDeleteObjectConnectorMigration() throws Exception {
        final MainProcess mainProc = importBar("CMISDeleteObjectMigrationUseCase--1.0.bar");
        final List<Connector> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);
        assertEquals("Invalid number of connector", 1, connectors.size());
        final Map<String, AbstractExpression> expectedParameterCOnfiguration = new HashMap<String, AbstractExpression>();
        expectedParameterCOnfiguration.put("username",
                ExpressionHelper.createConstantExpression("admin", String.class.getName()));
        expectedParameterCOnfiguration.put("url",
                ExpressionHelper.createConstantExpression("http://cmis.alfresco.com/service/cmis", String.class.getName()));
        expectedParameterCOnfiguration.put("repository",
                ExpressionHelper.createConstantExpression("Main Repository", String.class.getName()));
        expectedParameterCOnfiguration.put("binding_type",
                ExpressionHelper.createConstantExpression("atompub", String.class.getName()));
        expectedParameterCOnfiguration.put("document_path",
                ExpressionHelper.createConstantExpression("/Bonita", String.class.getName()));
        checkParameters(connectors, expectedParameterCOnfiguration);
    }

    @Test
    public void testSetBonitaVariableOnFinishConnectorMigration() throws Exception {
        final MainProcess mainProc = importBar("SetVariableOnStepOnFinish--1.0.bar");
        final List<Connector> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);
        assertEquals("Invalid number of connector", 0, connectors.size());

        final Activity activity = (Activity) ModelHelper.findElement(mainProc, "Step1", true);
        final EList<Operation> operations = activity.getOperations();
        assertEquals("No operations has been created", 1, operations.size());

        final Operation operation = operations.get(0);
        assertEquals("The Operator should be Assignment.", ExpressionConstants.ASSIGNMENT_OPERATOR,
                operation.getOperator().getType());
        final Expression dataUpdated = operation.getLeftOperand();
        assertEquals("The type is not correct.", ExpressionConstants.VARIABLE_TYPE, dataUpdated.getType());
        assertEquals("The name of the data is not correct", "poolData", dataUpdated.getName());

        final Expression value = operation.getRightOperand();
        assertEquals("The type is not correct.", ExpressionConstants.VARIABLE_TYPE, value.getType());
        assertEquals("The name of the data is not correct", "stepData", value.getName());

    }

    @Test
    public void testSetBonitaVariableOnEnterConnectorMigration() throws Exception {
        final MainProcess mainProc = importBar("SetVariableOnStepOnEnter--1.0.bar");
        final List<Connector> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);
        assertEquals("Invalid number of connector", 0, connectors.size());

        final Activity activity = (Activity) ModelHelper.findElement(mainProc, "Step1", true);
        final EList<Operation> operations = activity.getOperations();
        assertEquals("No operations has been created", 1, operations.size());
        final Operation operation = operations.get(0);
        operation.getLeftOperand();

        //TODO: check that there is an entry in the report
    }

    @Test
    public void testSetBonitaVariablesOnFinishConnectorMigration() throws Exception {
        final MainProcess mainProc = importBar("SetVariablesOnStepOnFinish--1.0.bar");
        final List<Connector> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);
        assertEquals("Invalid number of connector", 0, connectors.size());

        final Activity activity = (Activity) ModelHelper.findElement(mainProc, "Step1", true);
        final EList<Operation> operations = activity.getOperations();
        assertEquals("No operations has been created", 2, operations.size());
    }

    @Test
    public void testSetBonitaVariablesOnEnterConnectorMigration() throws Exception {
        final MainProcess mainProc = importBar("SetVariablesOnStepOnEnter--1.0.bar");
        final List<Connector> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);
        assertEquals("Invalid number of connector", 0, connectors.size());

        final Activity activity = (Activity) ModelHelper.findElement(mainProc, "Step1", true);
        final EList<Operation> operations = activity.getOperations();
        assertEquals("No operations has been created", 2, operations.size());
        //TODO: check that there is an entry in the report
    }

    @Test
    public void testSetBonitaVariableOnProcessConnectorMigration() throws Exception {
        final MainProcess mainProc = importBar("SetVarOnPool--1.0.bar");
        final List<Connector> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);
        assertEquals("Invalid number of connector", 0, connectors.size());

        final Activity activity = (Activity) ModelHelper.findElement(mainProc, "Step1", true);
        final EList<Operation> operations = activity.getOperations();
        assertEquals("No operations should have been created", 0, operations.size());
        //TODO: check that there is an entry in the report

    }

    @Test
    public void testSetBonitaVariableOnStartConnectorMigration() throws Exception {
        final MainProcess mainProc = importBar("SetVariableOnstart--1.0.bar");
        final List<Connector> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);
        assertEquals("Invalid number of connector", 0, connectors.size());

        final Activity activity = (Activity) ModelHelper.findElement(mainProc, "Step1", true);
        final EList<Operation> operations = activity.getOperations();
        assertEquals("No operations has been created", 1, operations.size());
        //TODO: check that there is an entry in the report

    }

    @Test
    public void testSetBonitaVariableOnResumeAndSuspendConnectorMigration() throws Exception {
        final MainProcess mainProc = importBar("SetVariableOnResumeAndSuspend--1.0.bar");
        final List<Connector> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);
        assertEquals("Invalid number of connector", 0, connectors.size());

        final Activity activity = (Activity) ModelHelper.findElement(mainProc, "Step1", true);
        final EList<Operation> operations = activity.getOperations();
        assertEquals("Operations has been created but it shouldn't", 0, operations.size());
        //TODO: check that there is an entry in the report

    }

    @Test
    public void testSetBonitaVariableWithEmptyValueConnectorMigration() throws Exception {
        final MainProcess mainProc = importBar("SetVariableWithValueNotFiled--1.0.bar");
        final List<Connector> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);
        assertEquals("Invalid number of connector", 0, connectors.size());

        final Activity activity = (Activity) ModelHelper.findElement(mainProc, "Step1", true);
        final EList<Operation> operations = activity.getOperations();
        assertEquals("No operations has been created", 1, operations.size());

    }

    @Test
    public void testSetBonitaVariableInFormConnectorMigration() throws Exception {
        final MainProcess mainProc = importBar("SetVarInForm--1.0.bar");
        final List<Connector> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);
        assertEquals("Invalid number of connector", 0, connectors.size());

        final Activity activity = (Activity) ModelHelper.findElement(mainProc, "Step1", true);
        final EList<Operation> operations = activity.getOperations();
        assertEquals("No operations should have been created", 0, operations.size());
    }

    @Test
    public void testSetBonitaVariableConnectorMigration() throws Exception {
        final MainProcess mainProc = importBar("VariousConnectorSetVariableToMigrate--1.0.bar");
        final List<Connector> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);
        assertEquals("Invalid number of connector", 0, connectors.size());

        final Activity activity = (Activity) ModelHelper.findElement(mainProc, "Etape2", true);
        final EList<Operation> operations = activity.getOperations();
        assertEquals("5 operations should have been created", 5, operations.size());

        // check return type in operations
        for (final Operation operation : operations) {
            final String opeName = operation.getLeftOperand().getName();
            if ("varInt".equals(opeName)) {
                final String intergerName = Integer.class.getName();
                assertEquals("Return type of left operand created after connector migration must be " + intergerName,
                        operation.getLeftOperand().getReturnType(), intergerName);
                assertEquals("Return type of right operand created after connector migration must be " + intergerName,
                        operation.getRightOperand().getReturnType(), intergerName);
            } else if ("varBool".equals(opeName)) {
                final String booleanName = Boolean.class.getName();
                assertEquals("Return type of left operand created after connector migration must be " + booleanName,
                        operation.getLeftOperand().getReturnType(), booleanName);
                assertEquals("Return type of right operand created after connector migration must be " + booleanName,
                        operation.getRightOperand().getReturnType(), booleanName);

            }
        }
    }

    @Test
    public void testScriptOutputConnectorMigration() throws Exception {
        final MainProcess mainProc = importBar("ScriptConnectorsOutputs--1.0.bar");
        final List<Connector> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);
        assertEquals("Invalid number of connector", 1, connectors.size());

        final Connector connector = connectors.get(0);
        final EList<Operation> operations = connector.getOutputs();
        assertEquals("3 operations should have been created during the migration", 3, operations.size());

        // check return type in operations
        final String objectClassName = Object.class.getName();
        final String booleanClassName = Boolean.class.getName();
        for (final Operation operation : operations) {
            final String rightName = operation.getRightOperand().getName();
            if ("migratedScript".equals(rightName)) {
                assertEquals("Return type of right operand created after connector migration must be " + objectClassName,
                        objectClassName, operation.getRightOperand().getReturnType());
            } else if ("p1".equals(rightName)) {
                assertEquals("Return type of right operand created after connector migration must be " + booleanClassName,
                        booleanClassName, operation.getRightOperand().getReturnType());

            }
        }
    }

    @Test
    public void should_import_process_with_connectors_in_forms_and_widget_dependencies() throws Exception {
        final MainProcess mainProc = importBar("Reporte_de_Eventos--2.0.bar");
        final List<Expression> expressions = ModelHelper.getAllItemsOfType(
                mainProc, ExpressionPackage.Literals.EXPRESSION);
        assertThat(expressions).extracting("name", "type")
                .contains(tuple("field_tema", ExpressionConstants.FORM_FIELD_TYPE));
    }

    private MainProcess importBar(final String barName) throws Exception {
        final URL url = TestConnectorMigrationUseCase.class.getResource(barName);
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        new File(resource.getURI().toFileString()).deleteOnExit();
        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);
        return mainProc;
    }

    private void checkParameters(final List<Connector> connectors,
            Map<String, AbstractExpression> expectedParameterCOnfiguration) {
        final Connector c = connectors.get(0);
        final ConnectorConfiguration configuration = c.getConfiguration();
        for (final Entry<String, AbstractExpression> expectedConnectorParameter : expectedParameterCOnfiguration
                .entrySet()) {
            final EList<ConnectorParameter> parameters = configuration.getParameters();
            boolean found = false;
            final String currentParameterKey = expectedConnectorParameter.getKey();
            for (final ConnectorParameter connectorParameter : parameters) {
                if (currentParameterKey.equals(connectorParameter.getKey())) {
                    final AbstractExpression expectedValueExpression = expectedConnectorParameter.getValue();
                    final AbstractExpression actualValueExpression = connectorParameter.getExpression();
                    if (expectedValueExpression instanceof Expression) {
                        assertEquals("Wrong name of expression for parameter " + currentParameterKey,
                                ((Expression) expectedValueExpression).getName(),
                                ((Expression) actualValueExpression).getName());
                        assertEquals("Wrong type of expression for parameter " + currentParameterKey,
                                ((Expression) expectedValueExpression).getType(),
                                ((Expression) actualValueExpression).getType());
                        assertEquals("Wrong content of expression for parameter " + currentParameterKey,
                                ((Expression) expectedValueExpression).getContent(),
                                ((Expression) actualValueExpression).getContent());
                        found = true;
                    } else {
                        assertTrue("Missing implementation", false);
                    }
                }
            }
            assertTrue("The expected connector parameter" + currentParameterKey + " was not found.", found);
        }
    }

}
